(ns store.lesson5
  (:require [store.db :as s.db]
            [store.logic :as s.logic]))

(defn spent-a-lot>?
  [user-info]
  ;(println "spent-a-lot?" (:user-id user-info))
  (> (:total-price user-info) 500)
  )

(let [orders (s.db/all-orders)
      summary (s.logic/summary-per-user (s.db/all-orders))]
  (println summary)
  (println "keep" (keep spent-a-lot>? summary))
  (filter spent-a-lot>? summary)
  )

(println "ISOLATING....")
(println (range 10))
(println (take 2 (range 1000000000000)))


(println "ISOLATING 2....")
(let [sequence (range 10000000000000)]
  (println (take 2 sequence))
  (println (take 2 sequence)))

(defn filter1
  [x]
  (println "filter1" x)
  x
  )

(defn filter2
  [x]
  (println "filter2" x)
  x
  )

;(println (map filter2 (map filter1 (range 10))))
(println "\n\nRUNNING")

(->> '(1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10)
     (map filter1)
     (map filter2)
     println)