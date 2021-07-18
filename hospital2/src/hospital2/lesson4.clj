(ns hospital2.lesson4
  (:use clojure.pprint)
  (:require [hospital2.logic :as h.logic]))

(defrecord PrivatePatient [id, name, birthdate, situation])
(defrecord InsuredPatient [id, name, birthdate, health-insurance, situation])

(defn not-urgent?
  [patient]
  (not= :urgent (:situation patient :regular)))

(defprotocol Authorizable
  (needs-authorization? [patient exam cost]))

(extend-type PrivatePatient
  Authorizable
  (needs-authorization?
    [patient exam cost]
    (and (>= cost 50) (not-urgent? patient))
    ))

(extend-type InsuredPatient
  Authorizable
  (needs-authorization?
    [patient exam cost]
    (let [health-insurance (get patient :health-insurance)]
      (and (not (some #(= % exam) health-insurance)) (not-urgent? patient)))
    ))

;request { :patient patient, :cost cost, :exam exam }

(defn my-function
  [p]
  (println p)
  (class p))

(defmulti multi-test my-function)
;(multi-test "Willian")

(defn authorizer-type
  [request]
  (let [patient (:patient request)
        situation (:situation patient)
        urgent? (= :urgent situation)]
    (if urgent?
      :always-authorizer
      (class patient)))
  )

(defmulti needs-authorization-for-request? authorizer-type)

(defmethod needs-authorization-for-request?
  :always-authorizer
  [request]
  false)

(defmethod needs-authorization-for-request?
  PrivatePatient
  [request]
  (> (:cost request) 50))

(defmethod needs-authorization-for-request?
  InsuredPatient
  [request]
  (not (some #(= % (:exam request)) (:health-insurance (:patient request)))))

(let [private-patient (->PrivatePatient 14, "William", "09/08/1991", :urgent)
      insured-patient (->InsuredPatient 15, "William", "09/08/1991", [:x-ray, :ultrasound], :urgent)]
  (pprint (needs-authorization-for-request? {:patient private-patient, :cost 1000, :exam :blood-test}))
  (pprint (needs-authorization-for-request? {:patient insured-patient, :cost 1000, :exam :blood-test}))
  )


(let [private-patient (->PrivatePatient 14, "William", "09/08/1991", :regular)
      insured-patient (->InsuredPatient 15, "William", "09/08/1991", [:x-ray, :ultrasound], :regular)]
  (pprint (needs-authorization-for-request? {:patient private-patient, :cost 1000, :exam :blood-test}))
  (pprint (needs-authorization-for-request? {:patient insured-patient, :cost 1000, :exam :blood-test}))
  )


