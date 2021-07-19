(ns clojure_hospital_test.logic
  (:require [clojure_hospital_test.model :as h.model])
  (:require [schema.core :as s]))

; normal threading problem when the department is null
;(defn fits-in-queue?
;  [hospital department]
;  (-> hospital
;      department
;      count
;      (< 5))
;  )

;works even when the department is nil
;(defn fits-in-queue?
;  [hospital department]
;  (when-let [queue (get hospital department)]
;    (some-> hospital
;            department
;            count
;            (< 5)))
;  )

; also works even when the department is nil
(defn fits-in-queue?
  [hospital department]
  (some-> hospital
          department
          count
          (< 5))
  )

(defn- tries-to-add-to-the-queue
  [hospital department patient]
  (if (fits-in-queue? hospital department)
    (update hospital department conj patient))
  )

;(defn arrived-at
;  [hospital department patient]
;  (if-let [new-hospital (tries-to-add-to-the-queue hospital department patient)]
;    {:hospital new-hospital, :result :success}
;    {:hospital hospital, :result :failed-to-add-the-patient-to-the-queue}))

(defn arrived-at
  [hospital department patient]
  (if (fits-in-queue? hospital department)
    (update hospital department conj patient)
    (throw (ex-info "This department is full or doesn't exist" {:patient patient })))
  )

(s/defn was-attended-to :- h.model/Hospital
  [hospital :- h.model/Hospital,
   department :- s/Keyword]
  (update hospital department pop))


(s/defn next-patient :- h.model/PatientID
  [hospital :- h.model/Hospital,
   department :- s/Keyword]
  (-> hospital
      department
      peek))

(defn same-size?
  [hospital hospital-after from to]
  (= (+ (count (get hospital-after from)) (count (get % to)))
     (+ (count (get hospital from)) (count (get hospital to))))
  )

(s/defn transfer :- h.model/Hospital
  [hospital :- h.model/Hospital,
   from :- s/Keyword
   to :- s/Keyword]
  {
   :pre  [(contains? hospital from)
          (contains? hospital to)]
   :post [(same-size? hospital % from to)]
   }
  (let [patient (next-patient hospital from)]
    (-> hospital
        (was-attended-to from)
        (arrived-at to patient))))


