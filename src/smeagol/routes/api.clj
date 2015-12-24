(ns smeagol.routes.api 
  (:require [compojure.core :refer [GET defroutes]]
            [ring.util.response :refer [resource-response response]])
  (:gen-class))

(defroutes routes
  (GET "/services" [] (response {:body "Hello, world!"})))
