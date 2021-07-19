(ns ecommerce.lesson2
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce.db :as e.db]
            [ecommerce.model :as e.model]))

(def conn (e.db/open-connection))
(pprint conn)

(d/transact conn e.db/schema)

; No restrictions defined, my attribute can 'be NULL'
(let [calculator {:product/name "4-operations Calculator"}]
  (d/transact conn [calculator]))

; Datomic doesn't allow Nil values, missing/undefined values should be omited
;(let [alarm-clock {:product/name "A clock with an Alarm"
;                   :product/price nil}]
;  (d/transact conn [alarm-clock]))

(let [cheap-mobile (e.model/new-product
                     "Cheap Mobile"
                     "/cheap-mobile"
                     8882M)
      result @(d/transact conn [cheap-mobile])
      entity-id (first (vals (:tempids result)))]
  (pprint result)
  (pprint @(d/transact conn [[:db/add
                              entity-id
                              :product/price
                              0.1M]]))
  (pprint @(d/transact conn [[:db/retract
                              entity-id
                              :product/slug
                              "/cheap-mobile"]])))

(e.db/delete-database)
