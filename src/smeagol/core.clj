(ns smeagol.core
  (:use [compojure.core :only [defroutes GET]]
        [ring.adapter.jetty :as ring])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [smeagol.routes.api :as api]
            [smeagol.models.migration :as schema])
  (:gen-class))

(defroutes app-routes
  api/routes
  (route/not-found "Not found."))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))

(defn start [port]
  (run-jetty #'app {:port port
                  :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8081"))]
    (start port)))
