(ns storage.script1)

(defn discounted-price
    "Returns the original price with a 10% discount"
    [original-price]
    (* original-price (- 1 0.10)))