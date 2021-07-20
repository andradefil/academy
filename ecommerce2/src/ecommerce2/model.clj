(ns ecommerce2.model)

(defn uuid [] (java.util.UUID/randomUUID))

(defn new-product
  ([name slug price] (new-product (uuid) name slug price))
  ([uuid name slug price]
   {:product/id    uuid
    :product/name  name
    :product/slug  slug
    :product/price price}))

(defn new-category
  ([name] (new-category (uuid) name))
  ([uuid name]
   {:category/id   uuid
    :category/name name}))