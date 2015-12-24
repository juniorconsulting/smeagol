(defproject smeagol "0.0.1"
  :description "Service API for JrC."
  :url "http://github.com/juniorconsulting/smeagol"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.3.2"]
                 [com.apa512/rethinkdb "0.11.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-defaults "0.1.4"]
                 [instaparse "1.4.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler smeagol.core/app
         :init smeagol.models.migration/migrate}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]
                        [javax.servlet/servlet-api "2.5"]]}
   :uberjar {:aot :all}})
