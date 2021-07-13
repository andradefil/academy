(ns storage.lesson4)

(def prices [30 700 1000])

(println (prices 0))

(println (get prices 0))
(println (get prices 2))

;(println (prices 17))

(println (get prices 17))
(println (get prices 17 0))

;(println (conj 5 prices))

(println (inc 5))

(println (update prices 0 inc))
(println (update prices 0 dec))


(defn add-1
  [value]
  (println "I'm adding 1 to the value = " value)
  (+ value 1))

(println (update prices 0 add-1))

(defn add-3
  [value]
  (println "I'm adding 3 to the value = " value)
  (+ value 3))

(println (update prices 0 add-3))

(println (update prices 0 #(+ % 1)))

(defn applies-discount?
  [original-price]
  ( > original-price 100))


(defn discounted-price
  "Returns the original price with a 10% discount, if the original price is higher than 100"
  [original-price]
  (if (applies-discount? original-price)
    (let [  discount-rate (/ 10 100)
          discount        (* original-price discount-rate)]
      (- original-price discount))
    original-price))

(println "apply discount")
(println (map discounted-price prices))

(println (range 10))

(println (filter even? (range 10)))

(println prices)
(println (filter applies-discount? prices))

(println prices)
(println "map after filter" (map discounted-price (filter applies-discount? prices)))

(println (reduce + prices))

(defn my-sum
  [value1 value2]
  (println "adding" value1 "and" value2)
  (+ value1 value2))

(println (reduce my-sum prices))

(println (reduce my-sum (range 10)))

(println (reduce my-sum 0 prices))

(println (reduce my-sum []))


