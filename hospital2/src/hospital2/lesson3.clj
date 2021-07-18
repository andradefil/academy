(ns hospital2.lesson3
  (:use clojure.pprint)
  (:require [hospital2.logic :as h.logic]))

(defn downloads-patient
  [id]
  (println "Downloading patient" id)
  (Thread/sleep 1000)
  {:id id, :downloading-at (h.logic/this-moment)})

(defn downloads-if-not-exists
  [cache id downloader]
  (if (contains? cache id)
    cache
    (let [patient (downloader id)]
      (assoc cache id patient))))


(defprotocol Downloadable
  (download! [this id]))

(defrecord Cache
  [cache downloader]
  Downloadable
  (download! [this id]
    (swap! cache downloads-if-not-exists id downloader)
    (get @cache id)))

(def patients (->Cache (atom {}), downloads-patient))

(pprint patients)
(download! patients 15)
(download! patients 30)
(download! patients 15)
(pprint patients)