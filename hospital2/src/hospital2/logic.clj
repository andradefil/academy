(ns hospital2.logic
  (:use clojure.pprint)
  (:require [hospital2.model :as h.model]))

(defn this-moment
  []
  (h.model/to-ms (java.util.Date.)))

(pprint (this-moment))