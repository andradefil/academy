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
