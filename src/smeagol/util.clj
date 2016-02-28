(ns smeagol.util
  (:require [rethinkdb.query :as r]))

(defn in? 
  "true if seq contains elm"
  [seq elem]
  (some #(= elem %) seq))

(defn db-connect []
  (r/connect :host "127.0.0.1" :port 28015))
