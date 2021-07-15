(ns hospital.lesson6
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

(defn fits-in-queue?
  [queue]
  (-> queue
      count,,,
      (<,,, 5)))

(defn arrived-at
  [queue patient]
  (if (fits-in-queue? queue)
    (conj queue patient)
    (throw (ex-info "The queue is full" {:trying-to-add patient}))))

(defn arrived-at!
  [hospital patient]
  (let [queue (get hospital :g-queue)]
    (ref-set queue (arrived-at @queue patient))))

(defn arrived-at!
  [hospital patient]
  (let [queue (get hospital :g-queue)]
    (alter queue arrived-at patient)))

(defn async-arrived-at!
  [hospital patient]
  (future
    (Thread/sleep (rand 5000))
    (dosync
      (println "Trying synchronized code for" patient)
      (arrived-at! hospital patient))))


(defn simulates-a-day-async
  []
  (let [hospital {:g-queue (ref h.model/empty-queue)
                  :lab1 (ref h.model/empty-queue)
                  :lab2 (ref h.model/empty-queue)
                  :lab3 (ref h.model/empty-queue)}
        ]

    (def futures (mapv #(async-arrived-at! hospital %) (range 10)))

    (future
      (dotimes [n 4])
      (Thread/sleep 2000)
      (pprint hospital)
      (pprint futures))
    ))

(simulates-a-day-async)

;(println (future 15))
;(println @(future ((Thread/sleep 1000)) 15))