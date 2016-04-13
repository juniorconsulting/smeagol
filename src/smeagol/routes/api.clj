(ns smeagol.routes.api 
  (:require [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [smeagol.controllers.services :refer :all])
  (:gen-class))

(defroutes routes
  (GET "/services" [:as {userid :userid}] (response (get-services userid)))
  (POST "/services" [title :as {userid :userid}] (response (add-service userid title)))
  (GET "/service/:id" [id] (response (get-service id)))
)
