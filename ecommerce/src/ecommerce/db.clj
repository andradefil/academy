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

