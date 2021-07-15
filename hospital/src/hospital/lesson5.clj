(ns hospital.lesson5
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(defn arrive-at!
  [hospital person]
  (swap! hospital h.logic/arrived-at-with-pauses-and-log :g-queue person)
  )


(defn transfer!
  [hospital from to]
  (swap! hospital h.logic/transfer from to))

(defn simulates-a-day
  []
  (let [hospital (atom (h.model/new-hospital))]
    (arrive-at! hospital "Jhon")
    (arrive-at! hospital "Mary")
    (arrive-at! hospital "Daniela")
    (arrive-at! hospital "Willian")
    (pprint hospital)
    (transfer! hospital :g-queue :lab1)
    (transfer! hospital :g-queue :lab2)
    (transfer! hospital :g-queue :lab2)
    (transfer! hospital :lab2 :lab3)
    (pprint hospital)
    ))

(simulates-a-day)