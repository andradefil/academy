(ns hospital.lesson3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(def my-name "jhonny")
(def my-name "redefined")
(def my-name 12345)

(let [my-name "willian"]
  ;task1
  ;task2
  (println my-name)

  ;;shadowing the symbol
  (let [my-name "daniela"]
    ;task3
    ;task4
    (println my-name)
    )
  (println my-name)
  )

;(def hospital-grey (atom {}))

(defn test-atom
  []
  (let [hospital-grey (atom {:g-queue h.model/empty-queue})]
    (println hospital-grey)
    (pprint hospital-grey)
    (pprint (deref hospital-grey))
    (pprint @hospital-grey)

    (pprint (assoc @hospital-grey :lab1 h.model/empty-queue))
    (pprint @hospital-grey)

    (swap! hospital-grey assoc :lab1 h.model/empty-queue)
    (pprint @hospital-grey)
    (swap! hospital-grey assoc :lab2 h.model/empty-queue)
    (pprint @hospital-grey)

    ;normal immutable, with dereference, no effect
    (update @hospital-grey :lab1 conj "111")
    (swap! hospital-grey update :lab1 conj "111")
    (pprint hospital-grey)
    ))

;(test-atom)

(defn terrible-arrive-at!
  [hospital person]
  (swap! hospital h.logic/arrived-at-with-pauses-and-log :g-queue person)
  (println "After inserting" person)
  )

(defn simulates-a-day-in-parallel
  []
  (let [hospital (atom (h.model/new-hospital))]
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "111"))))
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "222"))))
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "333"))))
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "444"))))
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "555"))))
    (.start (Thread. (fn [] (terrible-arrive-at! hospital "666"))))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

(defn not-so-terrible-arrive-at!
  [hospital person]
  (swap! hospital h.logic/arrived-at-with-pauses :g-queue person)
  (println "After inserting" person)
  )

(defn simulates-a-day-in-parallel
  []
  (let [hospital (atom (h.model/new-hospital))]
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "111"))))
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "222"))))
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "333"))))
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "444"))))
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "555"))))
    (.start (Thread. (fn [] (not-so-terrible-arrive-at! hospital "666"))))

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))
    )
  )

(simulates-a-day-in-parallel)