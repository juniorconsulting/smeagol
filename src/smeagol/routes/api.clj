(ns smeagol.routes.api 
  (:require [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [smeagol.controllers.services :refer :all])
  (:gen-class))

(defroutes routes
  (GET "/services" [] (response (get-all-services)))
  (GET "/service/:id" [id] (response (get-service id)))
  (POST "/service" [userid title] (response (add-service userid title))))
