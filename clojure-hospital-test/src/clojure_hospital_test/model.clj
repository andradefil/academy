(ns clojure_hospital_test.model)

(def empty-queue clojure.lang.PersistentQueue/EMPTY)
(defn new-hospital
  []
  {:g-queue empty-queue
   :lab1 empty-queue
   :lab2 empty-queue
   :lab3 empty-queue
   })