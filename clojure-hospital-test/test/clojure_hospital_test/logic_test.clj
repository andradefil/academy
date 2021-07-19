(ns clojure-hospital-test.logic-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all])
  (:require [clojure_hospital_test.logic :refer :all]))

(deftest fits-in-queue?-test
  ;zero boundary
  (testing "That it fits in the queue"
    (is (fits-in-queue? {:g-queue []} :g-queue))
    )
  ;limit boundary
  (testing "That it doesn't fit a new patient when the queue is full"
    (is (not (fits-in-queue? {:g-queue [1 5 2 4 3]} :g-queue)))
    )
  ;one above limit boundary
  (testing "That it doesn't fit a new patient when the queue is above limit"
    (is (not (fits-in-queue? {:g-queue [1 2 3 4 5 6]} :g-queue)))
    )
  ;below limit boundary
  (testing "That it fit a new patient when the queue is below the limit"
    (is (fits-in-queue? {:g-queue [1 2 3 4]} :g-queue))
    (is (fits-in-queue? {:g-queue [1 2]} :g-queue))
    )

  (testing "That is doesn't fit in the queue when the department doesn't exist"
    (is (not (fits-in-queue? {:g-queue [1 2 3 4 5]} :x-ray))))
  )

(deftest arrived-at-test
  (let [full-hospital [{:g-queue [1 58 96 74 32]}]]
    (testing "That the new patient will be added to the department if the queue is not full"
      ;bad implementation, only tests if what you wrote here is the same that
      ;you wrote in the function
      ;(is (= (update {:g-queue [1 2 3 4]} :g-queue conj 5)
      ;       (arrived-at {:g-queue [1 2 3 4]}, :g-queue 5)))
      ;
      ;(is (= (update {:g-queue [1 2]} :g-queue conj 5)
      ;       (arrived-at {:g-queue [1 2]}, :g-queue 5)))

      ;(is (= ({:g-queue 1 2 3 4 5}
      ;        (arrived-at {:g-queue [1 2 3 4]}, :g-queue 5))))
      ;
      ;(is (= ({:g-queue 1 2 5}
      ;        (arrived-at {:g-queue [1 2]}, :g-queue 5))))

      (is (= {:hospital {:g-queue [1 2 3 4 5]} :result :success}
             (arrived-at {:g-queue [1 2 3 4]}, :g-queue 5)))

      (is (= {:hospital {:g-queue [1 2 5]} :result :success}
             (arrived-at {:g-queue [1 2]}, :g-queue 5)))

      )
    (testing "That it won't add the new patient to the queue when the queue is full"
      ;classic terrible coding, the Exception is too generic
      ;(is (thrown? clojure.lang.ExceptionInfo
      ;     (arrived-at {:g-queue [1 58 96 74 32]}, :g-queue 47)))

      ;(is (nil?
      ;     (arrived-at {:g-queue [1 58 96 74 32]}, :g-queue 47)))

      ;(is (try
      ;      (arrived-at {:g-queue [1 58 96 74 32]} :g-queue 47)
      ;      false
      ;      (catch clojure.lang.ExceptionInfo e
      ;        (= :impossible-to-add-patient-to-queue (:type (ex-data e))))))

      (is (= {:hospital full-hospital, :result :failed-to-add-the-patient-to-the-queue}
             (arrived-at full-hospital, :g-queue, 47)))
      ))
  )