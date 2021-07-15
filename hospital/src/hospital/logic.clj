(ns hospital.logic
  (:use [clojure pprint]))

(defn fits-in-queue?
  [hospital department]
  (-> hospital
      (get,,, department)
      count ,,,
      (<,,, 5)
      ))

(defn arrived-at
  [hospital department person]
    (if (fits-in-queue? hospital department)
      (update hospital department conj person)
      (throw (ex-info "The queue is full" {:trying-to-add person})))
    )

(defn arrived-at-with-pauses
  [hospital department person]
  (if (fits-in-queue? hospital department)
    (do (Thread/sleep (* (rand) 2000))
        (update hospital department conj person))
    (throw (ex-info "The queue is full" {:trying-to-add person})))
  )

(defn was-attended-to [hospital department]
  (update hospital department pop))
