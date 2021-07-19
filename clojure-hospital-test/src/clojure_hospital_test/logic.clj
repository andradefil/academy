(ns clojure_hospital_test.logic)

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

(defn was-attended-to [hospital department]
  (update hospital department pop))


(defn next-patient
  [hospital department]
  (-> hospital
      department
      peek))

(defn transfer
  [hospital from to]
  (let [patient (next-patient hospital from)]
    (-> hospital
        (was-attended-to from)
        (arrived-at to patient))))


