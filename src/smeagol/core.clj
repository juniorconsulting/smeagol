(ns smeagol.core
  (:use [compojure.core :only [defroutes GET]]
        [ring.adapter.jetty :as ring])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [smeagol.routes.api :as api]
            [smeagol.models.migration :as schema]
            [smeagol.middleware.auth :refer [wrap-auth]])
  (:gen-class))

(defroutes app-routes
  api/routes
  (route/not-found "Not found."))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)
      (wrap-cors :access-control-allow-origin [#"http://localhost:3449"]
                 :access-control-allow-methods [:get :put :post :delete])
      (wrap-auth)))

(defn start [port]
  (run-jetty #'app {:port port
                  :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8081"))]
    (start port)))
