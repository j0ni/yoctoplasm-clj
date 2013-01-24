(ns yoctoplasm-clj.controllers.pages
  (:require [cemerick.friend :as friend]
            [monger.collection :as mc]
            [ring.util.response :as response]
            [yoctoplasm-clj.views.pages :as views]
            [yoctoplasm-clj.views.common :as common]
            [compojure.route :as route]
            [compojure.core :refer [GET POST defroutes]]))

(defn index [request]
  (views/index request))

(defn show [request]
  (let [{{id :id} :params} request
        page (mc/find-one-as-map "pages" {:slug id})]
    (cond
     (nil? page) (route/not-found (common/four-oh-four))
     :else (views/show page request))))

(defn new-page [request]
  (views/new-page request))

(defn create [request]
  (let [{{:strs [slug title body]} :form-params} request]
    (mc/insert "pages" {:slug slug :title title :body body})
    (response/redirect (str "/pages/" slug))))

(defroutes routes
  (GET "/" request (index request))
  (GET "/new" request (friend/authorize #{:admin} (new-page request)))
  (GET "/:id" request (friend/authorize #{:admin} (show request)))
  (POST "/" request (friend/authorize #{:admin} (create request))))
