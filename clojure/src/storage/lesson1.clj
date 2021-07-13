(ns storage.lesson1)

(defn discounted-price
    "Returns the original price with a 10% discount"
    [original-price]
    (* original-price (- 1 0.10)))

(defn discounted-price
    "Returns the original price with a 10% discount, if the original price is higher than 100"
    [original-price]
    (if (> original-price 100)
        (let [  discount-rate (/ 10 100)
              discount        (* original-price discount-rate)]
            (- original-price discount))
        original-price))


(if (> 500 100)
    (println "Greater")
    (println "Less than or equals"))
