(ns smeagol.middleware.auth
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :refer [blank?]]
            [ring.util.response :refer [response status]]))

(def API_URL "http://localhost:8000")

(defn check-token [token]
  "Asks the jrc-auth service wether the token supplied is valid"
  (if token
    (json/read-str (:body (client/post (str API_URL "/check-token/")
                                       {:content-type :json
                                        :accept :json
                                        :body (json/write-str {:token token})})) :key-fn keyword)
    {}))

(defn wrap-auth [handler]
  (fn [request]
    (if-let [userid (:userid (check-token (get (:headers request) "auth-token")))]
      (handler (assoc request :userid userid))
      (-> (response "Access denied")
          (status 403)))))
