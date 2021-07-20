(ns ecommerce.lesson5
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce.db :as e.db]
            [ecommerce.model :as e.model]))

(def conn (e.db/open-connection))
(d/transact conn e.db/schema)

(let [computer (e.model/new-product "Expensive computer" "/expensive-computer" 35800.10M)
      mobile (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M)
      result @(d/transact conn [computer mobile])]
  (pprint result))

(def snapshot-of-previous-state (d/db conn))

(let [cheap-mobile (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M)
      calculator {:product/name "4-operations Calculator"}
      result @(d/transact conn [cheap-mobile calculator])]
  (pprint result))

; the snapshot of the current state of Datomic
(pprint (count (e.db/all-products (d/db conn))))

(pprint (count (e.db/all-products snapshot-of-previous-state)))

; use the snapshot in a state of Datomic before the 1st transaction
(pprint (count (e.db/all-products (d/as-of (d/db conn) #inst "2021-07-20T02:17:27.123-00:00"))))
; use the snapshot in a state of Datomic after the 1st transaction
(pprint (count (e.db/all-products (d/as-of (d/db conn) #inst "2021-07-20T02:17:27.341-00:00"))))

;(e.db/delete-database)
