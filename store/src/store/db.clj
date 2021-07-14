(ns store.db)

(def order1 {:user 15
            :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 3 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})

(def order2 {:user 1
            :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 3 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})

(def order3 {:user 15
            :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 3 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})

(def order4 {:user 15
            :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 3 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})

(def order5 {:user 10
            :items {:backpack {:id :backpack :quantity 20 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 3 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})
(def order6 {:user 20
            :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}
                    :shirt {:id :shirt :quantity 10 :price-per-unit 40}
                    :shoes {:id :shoes :quantity 3}}})

(defn all-orders
  []
  [order1, order2, order3, order4, order5, order6])
