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
            [cemerick.friend :as friend]
            [cemerick.friend.workflows :as workflows]
            [cemerick.friend.credentials :as creds]))

;; indexes look like this:
;; (mc/ensure-index "pages" {:title 1})

(def userdb {"joni" {:username "joni"
                     :password (creds/hash-bcrypt "lauren")
                     :roles #{::admin}}})

(defn login-page []
  (common/layout
   ""
   [:form {:class "form-horizontal" :action "/login" :method "POST"}
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Username"]
     [:div {:class "controls"}
      [:input {:type "text" :placeholder "Username" :id "username" :name "username"}]]]
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Password"]
     [:div {:class "controls"}
      [:input {:type "password" :placeholder "Password" :id "password" :name "password"}]]]
    [:div {:class "control-group"}
     [:div {:class "controls"}
      [:button {:type "submit" :class "btn"} "Sign In"]]]]))

(defroutes routes
  (context "/pages" [] pages/routes)
  (context "/users" [] users/routes)
  (GET "/login" [] (login-page))
  (route/resources "/")
  (route/not-found (common/four-oh-four)))

(defn connect-to-mongo []
  (let [uri (get (System/getenv) "MONGOLAB_URI" "mongodb://127.0.0.1/yoctoplasm_clj_development")]
    (mg/connect-via-uri! uri)))

(def application (handler/site routes {:session {:store (session-store "sessions")}}))

(def secured-app
  (-> application
      (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn userdb)
                            :workflows [(workflows/interactive-form)]})))

(defn start [port]
  (connect-to-mongo)
  (ring/run-jetty #'secured-app {:port (or port 3000) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))