(ns hospital2.lesson1
  (:use clojure.pprint))

; { 15: {:id 15, name: "Richard"}, 23 {:id 23 "Jonas"}

(defn add-patient
  [patient patients]
  (if-let [id (:id patient)]
      (assoc patients id patient)
      (throw (ex-info "Patient missing ID" {:patient patient}))
    )
  )

(defn test-patient-list
  []
  (let [patients {}
        william {:id 15 :name "Willian" :birthdate "09/18/1981"}
        daniela {:id 20 :name "Daniela" :birthdate "09/18/1981"}
        paul {:id 25 :name "Paul" :birthdate "09/18/1981"}
        ]
    (pprint (add-patient william patients))
    (pprint (add-patient daniela patients))
    (pprint (add-patient paul patients))
    )
  )

(test-patient-list)

(defrecord Patient [id name birthdate])

(println (->Patient 15 "Willian" "09/08/20210"))
(pprint (->Patient "Willian" 14 "09/08/20210"))

(pprint (map->Patient {:id 15, :name "Willian", :birthdate "09/08/2021"}))

(let [willian (->Patient 15 "Willian" "09/08/2021")]
  (println (:id willian))
  (println (vals willian))
  (println (record? willian))
  (println (.name willian)))

(pprint (map->Patient {:id 15, :name "Willian", :birthdate "09/08/2021" :ssn "223"}))
(pprint (map->Patient {:name "Willian", :birthdate "09/08/2021" :ssn "223"}))
(pprint (->Patient nil, nil, "09/08/20210"))
(pprint (class (assoc (->Patient nil, nil, "09/08/20210") :id 30)))
(pprint (= (->Patient nil, nil, "09/08/20210") (->Patient nil, nil, "09/08/20210")))
(pprint (= (->Patient 30, nil, "09/08/20210") (->Patient nil, nil, "09/08/20210")))
