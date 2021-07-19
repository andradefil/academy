(ns ecommerce.core
  (:use [clojure.pprint])
  (:require [ecommerce.db :as e.db]))

(def conn (e.db/open-connection))
(pprint conn)

; Products
; id?
; name String 1 ==> Expensive Computer
; slug String 1 ==> /expensive_computer
; price Float 1 ==> 35000.10

; entity_id attribute_name attribute_value
; 15 name Expensive Computer
; 15 slug /expensive_computer
; 15 price 35000.10
; 17 name Fancy Mobile
; 17 slug /fancy_mobile
; 17 price 8888.88
