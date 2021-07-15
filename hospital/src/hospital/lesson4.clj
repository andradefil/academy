(ns hospital.lesson4
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(defn not-so-terrible-arrive-at!
  [hospital person]
  (swap! hospital h.logic/arrived-at-with-pauses :g-queue person)
  (println "After inserting" person)
  )

(defn simulates-a-day-in-parallel-with-mapv-refactored
  []
  (let [hospital (atom (h.model/new-hospital))
        patients ["111", "222", "333", "444", "555", "666"]
        will-start-thread #(.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital %))))]

    (mapv will-start-thread patients)

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

;(simulates-a-day-in-parallel-with-mapv-refactored)

(defn will-start-thread
  ([hospital]
   (fn [patient] (will-start-thread hospital patient)))
  ([hospital patient]
   (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital patient))))))

(defn simulates-a-day-in-parallel-with-mapv-extracted
  []
  (let [hospital (atom (h.model/new-hospital))
        patients ["111", "222", "333", "444", "555", "666"]]
    (mapv (will-start-thread hospital) patients)
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

;(simulates-a-day-in-parallel-with-mapv-extracted)

(defn will-start-thread-partial
  ([hospital patient]
   (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital patient))))))

(defn simulates-a-day-in-parallel-with-mapv-extracted-partial
  []
  (let [hospital (atom (h.model/new-hospital))
        patients ["111", "222", "333", "444", "555", "666"]
        will-start (partial will-start-thread-partial hospital)]
    (mapv will-start patients)
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

;(simulates-a-day-in-parallel-with-mapv-extracted-partial)

(defn simulates-a-day-in-parallel-with-mapv-extracted-partial-doseq
  []
  (let [hospital (atom (h.model/new-hospital))
        patients (range 6)]
    (doseq [patient patients]
      (will-start-thread hospital patient))

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

;(simulates-a-day-in-parallel-with-mapv-extracted-partial-doseq)

(defn simulates-a-day-in-parallel-with-mapv-extracted-dotimes
  []
  (let [hospital (atom (h.model/new-hospital))]
    (dotimes [patient 6]
      (will-start-thread hospital patient))

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

(simulates-a-day-in-parallel-with-mapv-extracted-dotimes)