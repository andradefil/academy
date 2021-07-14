(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

; GENERAL QUEUE
; lab1
; lab2
; lab3
(let [my-hospital (h.model/new-hospital)]
     (pprint my-hospital)
     (pprint (h.model/new-hospital))
     )