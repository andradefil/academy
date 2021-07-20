(ns ecommerce2.lesson4
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection!))
(e.db/create-schema! conn)

(def electronics (e.model/new-category "Electronics"))
(def sports (e.model/new-category "Sports"))

(pprint @(e.db/create-categories! conn [electronics sports]))

(def computer (e.model/new-product (e.model/uuid) "Expensive computer" "/expensive-computer" 35800.10M))
(def fancy-mobile (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M))
(def cheap-mobile (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M))
(def calculator {:product/name "4-operations Calculator"})
(def chess-board (e.model/new-product "Chess Board" "/chess-board" 30M))

(pprint @(e.db/create-products!
           conn [computer fancy-mobile cheap-mobile calculator chess-board]))

(pprint @(e.db/assign-category! conn [computer, fancy-mobile, cheap-mobile] electronics))
(pprint @(e.db/assign-category! conn [chess-board] sports))

(def products (e.db/all-products (d/db conn)))
(pprint products)

(pprint (e.db/products-and-category-names (d/db conn)))
(pprint (e.db/products-by-category-name (d/db conn) "Eletronics"))
(pprint (e.db/products-by-category-name (d/db conn) "Sports"))

(e.db/delete-database!)