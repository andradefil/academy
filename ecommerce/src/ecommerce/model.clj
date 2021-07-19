(ns ecommerce.model)

(defn new-product
  [name slug price]
  {:name name :slug slug :price price})
