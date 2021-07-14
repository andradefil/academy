(ns hospital.lesson1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(defn simulates-a-day
  []
  (def hospital (h.model/new-hospital))
  (def hospital (h.logic/arrived-at hospital :g-queue "111"))
  (def hospital (h.logic/arrived-at hospital :g-queue "222"))
  (def hospital (h.logic/arrived-at hospital :g-queue "333"))
  (pprint hospital)


  (def hospital (h.logic/arrived-at hospital :lab1 "444"))
  (def hospital (h.logic/arrived-at hospital :lab3 "555"))
  (pprint hospital)

  (def hospital (h.logic/was-attended-to hospital :lab1))
  (def hospital (h.logic/was-attended-to hospital :g-queue))
  (pprint hospital)
  )

(simulates-a-day)