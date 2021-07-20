(ns ecommerce2.lesson6
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection!))
(e.db/create-schema! conn)

(def electronics (e.model/new-category "Electronics"))
(def sports (e.model/new-category "Sports"))

(pprint @(e.db/create-categories! conn [electronics sports] "192.168.201.11"))

(def computer (e.model/new-product (e.model/uuid) "Expensive computer" "/expensive-computer" 35800.10M))
(def fancy-mobile (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M))
(def cheap-mobile (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M))
(def calculator {:product/name "4-operations Calculator"})
(def chess-board (e.model/new-product "Chess Board" "/chess-board" 30M))

(pprint @(e.db/create-products!
           conn [computer fancy-mobile cheap-mobile calculator chess-board]))
(pprint @(e.db/assign-category! conn [computer, fancy-mobile, cheap-mobile] electronics))
(pprint @(e.db/assign-category! conn [chess-board] sports))

; Create Product and a Category using nested maps
(pprint @(e.db/create-products!
           conn [{:product/name     "T-Shirt"
                  :product/slug     "/t-shirt"
                  :product/price    30M
                  :product/id       (e.model/uuid)
                  :product/category {:category/name "Clothing"
                                     :category/id   (e.model/uuid)}}
                 ]))

; Create a Product with and existing category
(def sports-id (:category/id sports))
(pprint @(e.db/create-products!
           conn [{:product/name     "Soccer Ball"
                  :product/slug     "/soccer-ball"
                  :product/price    30M
                  :product/id       (e.model/uuid)
                  :product/category [:category/id sports-id]}
                 ]))

(pprint (e.db/all-categories (d/db conn)))
(e.db/delete-database!)