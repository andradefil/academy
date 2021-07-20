(ns ecommerce2.lesson2
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection))
(d/transact conn e.db/schema)

(def computer
  (e.model/new-product (e.model/uuid) "Expensive computer" "/expensive-computer" 35800.10M))
(def fancy-mobile
  (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M))
(def cheap-mobile
  (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M))
(def calculator {:product/name "4-operations Calculator"})

(d/transact conn [computer fancy-mobile cheap-mobile calculator])

(def another-cheap-mobile (e.model/new-product
                            (cheap-mobile :product/id)
                            "ANOTHER ONE"
                            "/another-one"
                            0.00001M))

(pprint (e.db/all-products (d/db conn)))
(pprint (another-cheap-mobile :product/id))
(d/transact conn [another-cheap-mobile])

(def products (e.db/all-products (d/db conn)))
(pprint products)

(e.db/delete-database!)