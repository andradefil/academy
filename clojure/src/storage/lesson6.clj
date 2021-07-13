(ns storage.lesson6)

(def order {:backpack {:quantity 2, :price 80}
            :shirt {:quantity 3, :price 40}})

(defn prints-value-and-returns-15
  [value]
  (println "value:" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn prints-value-and-returns-15
  [value]
  (println "value:" (class value))
  15)

(defn prints-value-and-returns-15
  [[key value]]
  (println "key" key "&value" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn total-price-per-product
  [[_ value]]
  (* (-> value :quantity) (-> value :price)))


(println (map total-price-per-product order))

(println (reduce + (map total-price-per-product order)))

(defn order-total-price
  [order]
  (->> order
      (map total-price-per-product)
      (reduce +)))


(defn total-price-per-product
  [product]
  (* (-> product :quantity) (-> product :price)))

(defn order-total-price
  [order]
  (->> order
       vals
       (map total-price-per-product)
       (reduce +)))

(println (order-total-price order))

(def order {:backpack {:quantity 2, :price 80}
            :shirt {:quantity 3, :price 40}
            :keychain {:quantity 1}})

(defn for-free?
  [product]
  (<= 0 (get product :price 0) 0))

(println (filter for-free? (vals order)))

(defn for-free?
  [[_ item]]
  (<= 0 (get item :price 0) 0))

(println (filter for-free? order))

(defn for-free?
  [item]
  (<= 0 (get item :price 0) 0))

(println "filtering with anonymous function")
(println (filter (fn [[key, value]] (for-free? value)) order))

(println "filtering with lambda")
(println (filter #(for-free? (second %)) order))

(defn paid?
  [item]
  (not (for-free? item)))

(println "print with fn")
(println (paid? {:price 50}))
(println (paid? {:price 0}))

(comp not for-free?)

(def paid? (comp not for-free?))

(println "Paid with symbol")
(println (paid? {:price 50}))
(println (paid? {:price 0}))

