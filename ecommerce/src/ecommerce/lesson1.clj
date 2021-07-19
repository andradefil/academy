(ns ecommerce.lesson1
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce.db :as e.db]
            [ecommerce.model :as e.model]))

(def conn (e.db/open-connection))
(pprint conn)

(d/transact conn e.db/schema)

(let [computer (e.model/new-product
                 "Expensive computer"
                 "/expensive-computer"
                 35800.10M)]
  (d/transact conn [computer]))


(let [fancy-mobile (e.model/new-product
                     "Fancy Mobile"
                     "/fancy-mobile"
                     8882M)]
  (d/transact conn [fancy-mobile]))

; the current state of the dataset (SNAPSHOT)
(def db (d/db conn))

(d/q '[:find ?entity_id
       :where [?entity_id :product/name]] db)

(e.db/delete-database)
