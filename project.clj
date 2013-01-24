(defproject yoctoplasm-clj "0.1.0-SNAPSHOT"
  :description "A toy CMS-ish web application"
  :url "http://j0ni.ca/"
  :license {:name "GPL"
            :url "http://www.gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [cheshire "5.0.1"]
                 [com.novemberain/monger "1.4.2"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [clj-time "0.4.4"]
                 [com.cemerick/friend "0.1.3"]
                 [org.clojure/tools.logging "0.2.3"]
                 [clj-logging-config "1.9.10"]
                 [log4j/log4j "1.2.17"]]
  :profiles {:dev {:dependencies [[ring/ring-devel "1.1.6"]
                                  [midje "1.4.0"]
                                  [bultitude "0.1.7"]]
                   :plugins [[lein-midje "2.0.4"]]}}
  :plugins [[lein-ring "0.8.2"]]
  :ring {:handler yoctoplasm-clj.server/yoctoplasm
         :init yoctoplasm-clj.server/init}
  :min-lein-version "2.0.0")
