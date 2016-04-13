(ns smeagol.controllers.services
  (:require [smeagol.util :as util]
            [rethinkdb.query :as r]))

(defn get-all-services []
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/run conn))))

(defn get-services [userid]
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/get-all [userid] {:index "userid"})
        (r/run conn))))

(defn get-service-by-id [id]
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/get id)
        (r/run conn))))

(defn get-service [id userid]
  (if-let [service (get-service-by-id id)]
    (if (== userid (get service "userid"))
      service
      "Access denied")
    "Service not found"))

(defn add-service [userid title]
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/insert {:title title, :userid userid})
        (r/run conn))))
