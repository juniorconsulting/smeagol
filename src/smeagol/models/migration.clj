(ns smeagol.models.migration 
  (:require [rethinkdb.query :as r]
            [smeagol.util :as util]))

(defn database-exists? []
  (with-open [conn (util/db-connect)]
    (some #{"smeagol"} (r/run (r/db-list) conn))))

(defn create-database []
  (with-open [conn (util/db-connect)]
    (r/run (r/db-create "smeagol") conn)))

(defn table-exists [db name]
  (with-open [conn (util/db-connect)]
    (-> (r/db db)
        (r/table-list)
        (r/run conn)
        (util/in? name))))

(defn index-exists [db table index]
  (with-open [conn (util/db-connect)]
    (-> (r/db db)
        (r/table table)
        (r/index-list)
        (r/run conn)
        (util/in? index))))

(defn migrated? []
  (and
    (table-exists "smeagol" "services")
    (table-exists "smeagol" "projects")
    (index-exists "smeagol" "services" "userid")))

(defn create-table-if-not-exists [db name]
  (when (not (-> (table-exists db name)))
    (with-open [conn (util/db-connect)]
      (-> (r/db db)
          (r/table-create name)
          (r/run conn)))))

(defn create-index-if-not-exists [db table index]
  (when (not (-> (index-exists db table index)))
    (with-open [conn (util/db-connect)]
      (-> (r/db db)
          (r/table table)
          (r/index-create index)
          (r/run conn)))))

(defn migrate []
  (do 
    (if (not (database-exists?))
      (create-database))
    (when (not (migrated?))
      (do
        (create-table-if-not-exists "smeagol" "projects")
        (create-table-if-not-exists "smeagol" "services")
        (create-index-if-not-exists "smeagol" "services" "userid"))
      (println "Initialized database."))))
