(ns hospital.collections
  (:use [clojure pprint]))

(defn test-vector
  []
  (let [g-queue [111 222]]
    (println g-queue)
    (println (conj g-queue 333))
    (println (conj g-queue 444))
    (println (pop g-queue))
    ))

(test-vector)

(println "\n\nLINKED LIST")
(defn test-vector
  []
  (let [g-queue '(111 222)]
    (println g-queue)
    (println (conj g-queue 333))
    (println (conj g-queue 444))
    (println (pop g-queue))
    ))

(test-vector)

(println "\n\nSET")
(defn test-vector
  []
  (let [g-queue #{111 222}]
    (println g-queue)
    (println (conj g-queue 111))
    (println (conj g-queue 333))
    (println (conj g-queue 444))
    ;(println (pop g-queue))
    ))

(test-vector)

(println "\n\nQUEUE")
(defn test-vector
  []
  (let [g-queue (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
    (println (seq g-queue))
    (println (seq (conj g-queue "333")))
    (println (seq (pop g-queue)))
    (println (peek g-queue))
    (pprint g-queue)
    ;(println (pop g-queue))
    ))

(test-vector)


