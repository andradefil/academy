(ns ecommerce2.lesson1
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection))
(d/transact conn e.db/schema)

(let [computer (e.model/new-product (e.model/uuid) "Expensive computer" "/expensive-computer" 35800.10M)
      fancy-mobile (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M)
      cheap-mobile (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M)
      calculator {:product/name "4-operations Calculator"}]
  (d/transact conn [computer fancy-mobile cheap-mobile calculator]))

(def products (e.db/all-products (d/db conn)))
(pprint products)
(def first-product-id (-> products
                          first
                          first
                          :product/id))
(println "The first product's id is" first-product-id)

(def first-product (e.db/product-by-id (d/db conn) first-product-id))
(pprint (first-product :product/name))


(e.db/delete-database!)
