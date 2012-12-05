(defproject yoctoplasm-clj "0.1.0-SNAPSHOT"
  :description "A toy CMS-ish web application"
  :url "http://j0ni.ca/"
  :license {:name "GPL"
            :url "http://www.gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [com.novemberain/monger "1.4.1"]
                 [ring/ring-jetty-adapter "1.1.6"]]
  :plugins [[lein-ring "0.7.5"]]
  :ring {:handler yoctoplasm-clj.core/application})