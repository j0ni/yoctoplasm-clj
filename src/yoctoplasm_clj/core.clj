(ns yoctoplasm-clj.core
  (:use [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [yoctoplasm-clj.controllers.pages]
            [yoctoplasm-clj.views.common :as common]))

;; initially nicked from the Heroku article
;; https://devcenter.heroku.com/articles/clojure-web-application

(defroutes routes
  yoctoplasm-clj.controllers.pages/routes
  (route/resources "/")
  (route/not-found (common/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty #'application {:port (or port 3000) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))