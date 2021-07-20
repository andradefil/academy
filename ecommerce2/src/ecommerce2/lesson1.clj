(ns ecommerce2.lesson1
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection))
(d/transact conn e.db/schema)

(let [computer (e.model/new-product "Expensive computer" "/expensive-computer" 35800.10M)
      fancy-mobile (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M)
      cheap-mobile (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M)
      calculator {:product/name "4-operations Calculator"}]
  (d/transact conn [computer fancy-mobile cheap-mobile calculator]))

;(e.db/delete-database)
