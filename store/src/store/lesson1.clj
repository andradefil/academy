(ns store.lesson1)

;["daniel" "willian" "carl" "paul" "anna"]
(map println ["daniel" "willian" "carl" "paul" "anna"])

(println (first ["daniel" "willian" "carl" "paul" "anna"]))

(println (rest ["daniel" "willian" "carl" "paul" "anna"]))

(println (next ["daniel" "willian" "carl" "paul" "anna"]))

(println (rest []))

(println (next []))

(println (seq []))
(println (seq [1 2 3 17]))

(println "\n\nMY MAP")
(defn my-map
  [function sequence]
  (let [first-element (first sequence)]
    (if first-element
      (function first-element)
      (my-map function (rest sequence))))
  )

(println "\n\nMY MAP")
(defn my-map
  [function sequence]
  (let [first-element (first sequence)]
    (if first-element
      (do
        (function first-element)
        (my-map function (rest sequence)))))
  )

(println "\n\nMY MAP STOPPING ONLY FOR NULL")
(defn my-map
  [function sequence]
  (let [first-element (first sequence)]
    (if (not (nil? first-element))
      (do
        (function first-element)
        (my-map function (rest sequence)))))
  )

;(my-map println ["daniel" "willian" "carl" "paul" "anna"])
;(my-map println ["daniel" false "carl" "paul" "anna"])
;(my-map println [])
;(my-map println (range 100000))

(println "\n\nMY MAP FOR RECUR")
(defn my-map
  [function sequence]
  (let [first-element (first sequence)]
    (if (not (nil? first-element))
      (do
        (function first-element)
        (recur function (rest sequence)))))
  )

;(my-map println ["daniel" "willian" "carl" "paul" "anna"])
;(my-map println ["daniel" false "carl" "paul" "anna"])
;(my-map println [])
(my-map println (range 100000))

