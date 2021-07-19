(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database
  []
  (d/delete-database db-uri))

