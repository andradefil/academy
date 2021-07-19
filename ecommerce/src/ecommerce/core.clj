(ns ecommerce.core
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce.db :as e.db]
            [ecommerce.model :as e.model]))

(def conn (e.db/open-connection))
(pprint conn)

(let [computer (e.model/new-product
                 "Expensive computer"
                 "/expensive-computer"
                 35800.10)]
  (d/transact conn [computer]))

