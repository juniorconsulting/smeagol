(ns smeagol.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [smeagol.handler :refer :all]
            [cheshire.core :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/services"))]
      (is (= (:status response) 200))
      (is (= (:body response) (generate-string ["List of authed users services"])))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
