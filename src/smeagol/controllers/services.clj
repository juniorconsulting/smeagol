(ns smeagol.controllers.services
  (:require [smeagol.util :as util]
            [rethinkdb.query :as r]))

(defn get-all-services []
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/run conn))))

(defn get-service [id]
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/get id)
        (r/run conn))))

(defn add-service [userid title]
  (with-open [conn (util/db-connect)]
    (-> (r/db "smeagol")
        (r/table "services")
        (r/insert {:title title, :userid userid})
        (r/run conn))))
