(ns hospital2.model
  (:use clojure.pprint))

(defprotocol Dateable
  (to-ms [t]))

(extend-type java.lang.Number
  Dateable
  (to-ms [this] this))

(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))

(extend-type java.util.Calendar
  Dateable
  (to-ms [this] (to-ms (.getTime this))))

(pprint (to-ms 56))
(pprint (to-ms (java.util.Date.)))
