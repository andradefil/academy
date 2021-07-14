(ns store.lesson4
  (:require [store.db :as s.db]
            [store.logic :as s.logic]))

(let [orders (s.db/all-orders)
      summary (s.logic/summary-per-user (s.db/all-orders))]
     (println "summary" summary)
     (println "sorted" (sort-by :total-price summary))
     (println "sorted in reverse" (reverse (sort-by :total-price summary)))
     (println "sorted" (sort-by :user-id summary))
     (println (get-in orders [0 :items :backpack :quantity])))

(defn summary-per-user-sorted
  [orders]
  (->> orders
       s.logic/summary-per-user
       (sort-by :total-price)
       reverse))

(defn top-2
  [summary]
  (take 2 summary))

(let [orders (s.db/all-orders)
      summary (summary-per-user-sorted orders)]
  (println "summary" summary)
  (println "first" (first summary))
  (println "second" (second summary))
  (println "rest" (rest summary))
  (println "total" (count summary))
  (println "class" (class summary))
  (println "nth" (nth summary 1))
  (println "get" (get summary 1))
  (println "take" (take 2 summary))
  (println "take" (top-2 summary))
  )



(let [orders (s.db/all-orders)
      summary (summary-per-user-sorted orders)]
  (println "summary" summary)
  (println "filter > 500" (filter #(> (:total-price %) 500) summary))
  (println "filter not empty > 500" (not (empty? (filter #(> (:total-price %) 500) summary))))
  (println "some > 500" (some #(> (:total-price %) 500) summary))
  )
