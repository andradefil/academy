(ns ecommerce2.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection!
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database!
  []
  (d/delete-database db-uri))

;:tx-data [#datom[13194139534312 50 #inst"2021-07-19T22:05:17.187-00:00" 13194139534312 true]
;          #datom[72 10 :product/name 13194139534312 true]
;          #datom[72 40 23 13194139534312 true]
;          #datom[72 41 35 13194139534312 true]
;          #datom[72 62 "The name of the Product in the Catalog" 13194139534312 true]
;          #datom[73 10 :product/slug 13194139534312 true]
;          #datom[73 40 23 13194139534312 true]
;          #datom[73 41 35 13194139534312 true]
;          #datom[73 62 "The uri fragment required to access a Product using http" 13194139534312 true]
;          #datom[74 10 :product/price 13194139534312 true]
;          #datom[74 40 61 13194139534312 true]
;          #datom[74 41 35 13194139534312 true]
;          #datom[74 62 "The price of a Product with monetary precision" 13194139534312 true]
;          #datom[0 13 72 13194139534312 true]
;          #datom[0 13 73 13194139534312 true]
;          #datom[0 13 74 13194139534312 true]],

(def schema
  [
   ;Products
   {:db/ident       :product/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "The name of the Product in the Catalog"}
   {:db/ident       :product/slug
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "The uri fragment required to access a Product using http"}
   {:db/ident       :product/price
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "The price of a Product with monetary precision"}
   {:db/ident       :product/keyword
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/many}
   {:db/ident :product/id
    :db/valueType :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   {:db/ident :product/category
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   ;Categories
   {:db/ident :category/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :category/id
    :db/valueType :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   ;Transaction
   {:db/ident :tx-data/ip
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   ])

(defn create-schema! [conn]
  (d/transact conn schema) )

; db is a snapshot of Datomic in a particular point in the time
; explicitly pulling attributes
;(defn all-products [db]
;  (d/q '[:find (pull ?entity_id [*])
;         :where [?entity_id :product/name]] db))

; get all attributes
(defn all-products [db]
  (d/q '[:find (pull ?entity_id [*])
         :where [?entity_id :product/name]] db))

; String query = "some sql code"
; db.query(query)
; Is typing our variables in their names useful?
; this is not recommended as it exposes implementation needlessly
(def product-by-fixed-slug-q '[:find ?entity_id
                         :where [?entity_id :product/slug "/expensive-computer"]] )

(defn product-by-fixed-slug [db]
  (d/q product-by-fixed-slug-q db))

; String query = "select * from table where slug = :slug"
; db.query(query, {:slug "/some-slug"})
(defn product-by-slug [db slug]
  (d/q '[:find ?e
         :in $ ?search-slug
         :where [?e :product/slug ?search-slug]] db slug))

; ?entity_id, ?any_id ==> ?product ==> ?p
; since it is not used ...
(defn all-slugs [db]
  (d/q '[:find ?any-slug
         :where [_ :product/slug ?any-slug]] db))

; explicitly formatting our output
(defn all-prices [db price-point]
  (d/q '[:find ?any-price ?any-name
         :in $ ?min-price
         :keys product/price product/name
         :where [?product :product/price ?any-price]
                [(> ?any-price ?min-price)]
                [?product :product/name ?any-name]
         ] db price-point))

; Say I have a catalog with 10.000 products
; (> ?price 12000)            ===> 5000
; (> ?items-in-stock 100)     ===> 5000(10)
; 1st case 10,000 + 5,000 iterations = 15,000 iterations

; (> ?items-in-stock 100)     ===> 10
; (> ?price 12000)            ===> 10(5000)
; 2nd case 10,000 + 10 = 10,010 iterations

(defn all-products-by-keyword [db selected-keyword]
  (d/q '[:find (pull ?product [*])
          :in $ ?keyword
          :where [?product :product/keyword ?keyword]]
        db selected-keyword))

(defn product-by-id [db product-id]
  (d/pull db '[*] [:product/id product-id]))

(defn all-categories [db]
  (d/q '[:find (pull ?category [*])
         :where [?category :category/id]] db))

(defn reduce-to-categorized-product-list [catalog-items category]
 (reduce (fn [jobs product]
            (conj jobs [:db/add
                        [:product/id (:product/id product)]
                        :product/category
                        [:category/id (:category/id category)]]))
          []
          catalog-items
   ))

(defn assign-category! [conn catalog-items category]
  (let [jobs (reduce-to-categorized-product-list catalog-items category)]
    (d/transact conn jobs)))

(defn create-products!
  ([conn products ip]
   (let [tx-ip [:db/add "datomic.tx" :tx-data/ip ip]]
     (d/transact conn (conj products tx-ip))))
  ([conn products]
   (d/transact conn products)))

; Different business logic applies for different entities (i.e schema validation)
(defn create-categories!
  ([conn categories ip]
   (let [tx-ip [:db/add "datomic.tx" :tx-data/ip ip]]
     (d/transact conn (conj categories tx-ip))))
  ([conn categories]
   (d/transact conn categories)))

(defn products-and-category-names [db]
  (d/q '[:find ?name ?category-name
         :keys product category
         :where [?product :product/name ?name]
                [?product :product/category ?category]
                [?category :category/name ?category-name]] db))

; ?product ---:product/category ----> ?category [Forward navigation]
;(defn products-by-category-name [db category-name]
;  (d/q '[:find (pull ?product [:product/name
;                               :product/slug
;                               {:product/category [:category/name]}])
;         :in $ ?name
;         :where [?category :category/name ?name]
;                [?product :product/category ?category]] db category-name))


; ?category <---:product/category <---- ?product
(defn products-by-category-name [db category-name]
  (d/q '[:find (pull ?category [:category/name {:product/_category [:product/name :product/slug]}])
         :in $ ?name
         :where [?category :category/name ?name]] db category-name))

; #{0.1 10 30 8000 17}
; we can define with as key for the bucket to get aggregation of values duplications
(defn catalog-summary [db]
  (d/q '[:find (min ?price) (max ?price) (count ?price)
         :keys min-price max-price quantity
         :with ?product
         :where [?product :product/price ?price]] db))

(defn catalog-summary-by-category [db]
  (d/q '[:find ?category-name (min ?price) (max ?price) (count ?price)
         :keys category min-price max-price quantity
         :with ?product
         :where [?product :product/price ?price]
                [?product :product/category ?category]
                [?category :category/name ?category-name]] db))


; 2 queries in series
;(defn most-expensive-products [db]
;  (let [max-price (ffirst (d/q '[:find (max ?price)
;                                :where [?product :product/price ?price]] db))]
;    (d/q '[:find (pull ?product [*])
;           :in $ ?price
;           :where [?product :product/price ?price]] db max-price)))

; in a single nested query
(defn most-expensive-products [db]
  (d/q '[:find (pull ?product [*])
         ; in $ .... will not work, max-price is not a external argument
         :where [(q '[:find (max ?price)
                      :where [_ :product/price ?price]] $) [[?max-price]]]
         [?product :product/price ?max-price]] db))

(defn products-by-host-ip [db host-ip]
  (d/q '[:find (pull ?product [*])
         :in $ ?ip
         :where [?transaction :tx-data/ip ?ip]
                [?product :product/id _ ?transaction]] db host-ip))

