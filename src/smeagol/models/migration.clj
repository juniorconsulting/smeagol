(ns smeagol.models.migration 
  (:require [rethinkdb.query :as r]
            [smeagol.util :as util]))

(defn connect []
  (r/connect :host "127.0.0.1" :port 28015))

(defn database-exists? []
  (with-open [conn (connect)]
    (some #{"smeagol"} (r/run (r/db-list) conn))))

(defn create-database []
  (with-open [conn (connect)]
    (r/run (r/db-create "smeagol") conn)))

(defn table-exists [db name]
  (with-open [conn (connect)]
    (-> (r/db db)
        (r/table-list)
        (r/run conn)
        (util/in? name))))

(defn migrated? []
  (and
    (table-exists "smeagol" "services")
    (table-exists "smeagol" "projects")))

(defn create-table-if-not-exists [db name]
  (when (not (-> (table-exists db name)))
    (with-open [conn (connect)]
      (-> (r/db db)
          (r/table-create name)
          (r/run conn)))))

(defn migrate []
  (do 
    (if (not (database-exists?))
      (create-database))
    (when (not (migrated?))
      (do
        (create-table-if-not-exists "smeagol" "projects")
        (create-table-if-not-exists "smeagol" "services"))
      (println "Initialized database."))))
