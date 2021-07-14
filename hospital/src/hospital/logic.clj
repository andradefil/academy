(ns hospital.logic
  (:use [clojure pprint]))

(defn arrived-at
  [hospital department person]
  (update hospital department conj person))

(defn was-attended-to [hospital department]
  (update hospital department pop))
