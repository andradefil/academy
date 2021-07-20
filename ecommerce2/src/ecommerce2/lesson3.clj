(ns ecommerce2.lesson3
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [ecommerce2.db :as e.db]
            [ecommerce2.model :as e.model]))

(def conn (e.db/open-connection!))
(d/transact conn e.db/schema)

(def electronics (e.model/new-category "Eletronics"))
(def sports (e.model/new-category "Sports"))

(pprint @(e.db/create-categories! conn [electronics sports]))
(def categories (e.db/all-categories (d/db conn)))
(pprint categories)

(def computer
  (e.model/new-product (e.model/uuid) "Expensive computer" "/expensive-computer" 35800.10M))
(def fancy-mobile
  (e.model/new-product "Fancy Mobile" "/fancy-mobile" 8882M))
(def cheap-mobile
  (e.model/new-product "Cheap Mobile" "/cheap-mobile" 000.9M))
(def calculator {:product/name "4-operations Calculator"})
(def chess-board (e.model/new-product "Chess Board" "/chess-board" 30M))

(pprint @(e.db/create-products!
           conn [computer fancy-mobile cheap-mobile calculator chess-board]))

(def products (e.db/all-products (d/db conn)) )
(pprint products)

(pprint @(e.db/assign-category! conn [computer, fancy-mobile, cheap-mobile] electronics))
(pprint @(e.db/assign-category! conn [chess-board] sports))

(pprint (e.db/product-by-id (d/db conn) (:product/id computer)))

(pprint (e.db/all-products (d/db conn)))

(e.db/delete-database!)