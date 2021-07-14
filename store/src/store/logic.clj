(ns store.logic)

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

(defn summary-per-user
  [orders]
  (->> orders
       (group-by :user)
       (map counts-orders-and-total-price-per-user)))