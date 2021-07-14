(ns storage.lesson6)

(def order {:backpack {:quantity 2, :price 80}
            :shirt {:quantity 3, :price 40}})

(defn prints-value-and-returns-15
  [value]
  (println "value:" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn prints-value-and-returns-15
  [value]
  (println "value:" (class value))
  15)

(defn prints-value-and-returns-15
  [[key value]]
  (println "key" key "&value" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn total-price-per-product
  [[_ value]]
  (* (-> value :quantity) (-> value :price)))


(println (map total-price-per-product order))

(println (reduce + (map total-price-per-product order)))

(defn order-total-price
  [order]
  (->> order
      (map total-price-per-product)
      (reduce +)))


(defn total-price-per-product
  [product]
  (* (-> product :quantity) (-> product :price)))

(defn order-total-price
  [order]
  (->> order
       vals
       (map total-price-per-product)
       (reduce +)))

(println (order-total-price order))

(def order {:backpack {:quantity 2, :price 80}
            :shirt {:quantity 3, :price 40}
            :keychain {:quantity 1}})

(defn for-free?
  [product]
  (<= 0 (get product :price 0) 0))

(println (filter for-free? (vals order)))

(defn for-free?
  [[_ item]]
  (<= 0 (get item :price 0) 0))

(println (filter for-free? order))

(defn for-free?
  [item]
  (<= 0 (get item :price 0) 0))

(println "filtering with anonymous function")
(println (filter (fn [[key, value]] (for-free? value)) order))

(println "filtering with lambda")
(println (filter #(for-free? (second %)) order))

(defn paid?
  [item]
  (not (for-free? item)))

(println "print with fn")
(println (paid? {:price 50}))
(println (paid? {:price 0}))

(comp not for-free?)

(def paid? (comp not for-free?))

(println "Paid with symbol")
(println (paid? {:price 50}))
(println (paid? {:price 0}))

(def my-line [[5 10] [10 20]])

(def just-numbers [1 2 3 4])

(def just-numbers "meu nome não é jhonny")

(def client {:name "Super Co."
             :location "Philadelphia"
             :description "The worldwide leader in plastic tableware."})

(let [name (:name client)
      location (:location client)
      _ (:description client)]
  (println name "-" location))

(let [{:keys [name location]} client]
  (println name "-" location))


(defn configure [val options]
  (let [{:keys [debug verbose] :or {debug false, verbose false}} options]
    (println "val =" val " debug =" debug " verbose =" verbose)))

(configure 12 {:debug true})


(def multiplayer-game-state
  {:joe {:class "Ranger"
         :weapon "Longbow"
         :score 100}
   :jane {:class "Knight"
          :weapon "Greatsword"
          :score 140}
   :ryan {:class "Wizard"
          :weapon "Mystic Staff"
          :score 150}})

(def transactions [{ :amount 39.2 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  { :amount 100 :is-test? true}
                  ])

(let [[first] transactions]
  (println first))
;= Joe is a Ranger wielding a Longbow

;(let [[_ & remains :as total] just-numbers]
;  (println "maoi" remains)
;  (println "oima" total))
;
;(let [[first :as entire-vector] just-numbers]
;  (println "first" first)
;  (println "all" entire-vector))
;
;(let [[p1 p2] my-line
;      [x1 y1] p1
;      [x2 y2] p2]
;  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))