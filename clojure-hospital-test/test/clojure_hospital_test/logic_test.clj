(ns clojure-hospital-test.logic-test
  (:require [clojure.test :refer :all])
  (:require [clojure_hospital_test.logic :refer :all]))

(deftest fits-in-queue?-test
  ;zero boundary
  (testing "That it fits in the queue"
    (is (fits-in-queue? {:g-queue []} :g-queue))
    )
  ;limit boundary
  (testing "That it doesn't fit a new patient when the queue is full"
    (is (not (fits-in-queue? {:g-queue [1 2 3 4 5]} :g-queue)))
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