(ns store.lesson2)

(def array ["daniel" "willian" "carl" "paul" "lucy" "anna"])

(defn counter
  [array]
  (count array))

(defn counter2
  ([elements]
   (counter2 0 elements))

  ([total-so-far elements]
  (if (seq elements)
    (recur (inc total-so-far) (next elements))
    total-so-far)))

(println (counter array))
(println (counter2 0 array))
(println (counter2 0 (range 9999)))
(println (counter2 0 []))
(println (counter2 (range 10)))

(defn counter3
  [elements]
  (loop
    [total-so-far 0
     remaining-elements elements]
    (if (seq remaining-elements)
      (recur (inc total-so-far) (next remaining-elements))
      total-so-far))
  )

(println (counter3 array))