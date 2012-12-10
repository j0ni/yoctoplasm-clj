(ns yoctoplasm-clj.server
  (:use [compojure.core :only [defroutes context GET]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as response]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.ring.session-store :refer [session-store]]
            [yoctoplasm-clj.controllers.pages :as pages]
            [yoctoplasm-clj.controllers.users :as users]
            [yoctoplasm-clj.views.common :as common]
            [yoctoplasm-clj.util.logging :as logging]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))

(def userdb {"joni" {:username "joni"
                     :password (creds/hash-bcrypt "lauren")
                     :roles #{:admin}}})

(defroutes routes
  (GET "/" [] (response/redirect "/pages"))
  (context "/pages" [] pages/routes)
  (context "/users" [] users/routes)
  (route/resources "/assets")
  (route/not-found (common/four-oh-four)))

(defn- mongo-init []
  (let [uri (get (System/getenv) "MONGOLAB_URI" "mongodb://127.0.0.1/yoctoplasm_clj_development")]
    (mg/connect-via-uri! uri)
    (mc/ensure-index "pages" {:slug 1} {:unique true})))

(defn init []
  (logging/init)
  (mongo-init))

(def yoctoplasm
  (handler/site
   (-> routes
       (logging/wrap-logger)
       (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn userdb)
                             :login-uri "/users/login"
                             :workflows [(workflows/interactive-form)]}))
   {:session {:store (session-store "sessions")}}))

(defn start [port]
  (init)
  (ring/run-jetty #'yoctoplasm {:port (or port 3000) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))