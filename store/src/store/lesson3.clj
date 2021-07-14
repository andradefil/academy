(ns store.lesson3
  (:require [store.db :as s.db]))

;(println (group-by :user (s.db/all-orders)))

(defn my-grouping
  [element]
  (:user element))

;(println (group-by my-grouping (s.db/all-orders)))

;(println (count (vals (group-by :user (s.db/all-orders)))))
;(println (map count (vals (group-by :user (s.db/all-orders)))))

(->> (s.db/all-orders)
     (group-by :user)
     vals
     (map count)
     println)

(defn counts-total-of-orders-per-user
  [[_ orders]]
  (count orders))

(defn counts-total-of-orders-per-user
  [[user orders]]
  {:user-id user
   :total-of-orders (count orders)})

(defn total-price-per-item
  [[_ details]]
  (* (get details :quantity 0) (get details :price-per-unit 0)))

(defn total-price-per-order
  [order]
  (->> order
       (map total-price-per-item)
       (reduce +)))

(defn total-price-per-user [orders]
  (->> orders
       (map :items)
       (map total-price-per-order)
       (reduce +)))

(defn counts-orders-and-total-price-per-user
  [[user orders]]
  {:user-id user
   :total-of-orders (count orders)
   :total-price (total-price-per-user orders)})

(->> (s.db/all-orders)
     (group-by :user)
     (map counts-orders-and-total-price-per-user)
     println)