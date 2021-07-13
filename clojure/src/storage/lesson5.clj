(ns storage.lesson5)

(def storage {"Backpack" 10 "Shirt" 5})

(println storage)

(def storage {"Backpack" 10,
              "Shirt" 5})
(println storage)

(println "There are" (count storage) "elements")

(println "The keys are" (keys storage))
(println "The values are" (vals storage))

(def storage {:backpack 10,
              :shirt 5})

(println (assoc storage :chair 3))
(println storage)

(println (assoc storage :backpack 1))

(println storage)
(println (update storage :backpack inc))

(defn minus-one
  [value]
  (println "subtracting 1 from value" value)
  (- value 1))

(println (update storage :backpack minus-one))

(println (update storage :backpack #(- % 3)))


(println (dissoc storage :backpack))

(def order {:backpack {:quantity 2, :price 80}
            :shirt {:quantity 3, :price 40}})

(println order)

(assoc order :keychain {:quantity 1, :price 10})
(println order)

(def order (assoc order :keychain {:quantity 1, :price 10}))
(println order)

(println (order :backpack))


(println (get order :backpack))

(println (get order :chair))

(println (get order :chair {}))

(println (:chair order))

(println (:chair order {}))

(println (:quantity (:backpack order {})))

(println (update-in order [:backpack :quantity] inc))

(println (-> order
             :backpack
             :quantity))
(-> order
    :backpack
    :quantity
    println)