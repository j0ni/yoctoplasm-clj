(ns yoctoplasm-clj.server
  (:use [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.ring.session-store :refer [session-store]]
            [yoctoplasm-clj.controllers.pages]
            [yoctoplasm-clj.views.common :as common]))

(let [uri (get (System/getenv) "MONGOLAB_URI" "mongodb://127.0.0.1/yoctoplasm_clj_development")]
  (mg/connect-via-uri! uri))

;; indexes look like this:
;; (mc/ensure-index "pages" {:title 1})

(defroutes routes
  yoctoplasm-clj.controllers.pages/routes
  (route/resources "/")
  (route/not-found (common/four-oh-four)))

(def application (handler/site routes {:session {:store (session-store "sessions")}}))

(defn start [port]
  (ring/run-jetty #'application {:port (or port 3000) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))