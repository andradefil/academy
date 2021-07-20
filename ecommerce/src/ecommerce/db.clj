(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database
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
  [{:db/ident       :product/name
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
    :db/doc         "The price of a Product with monetary precision"}])

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
