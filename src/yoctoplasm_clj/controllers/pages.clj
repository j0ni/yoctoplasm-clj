(ns yoctoplasm-clj.controllers.pages
  (:use [compojure.core :only [defroutes GET POST]]
        [clojure.pprint :only [pprint]])
  (:require [yoctoplasm-clj.views.pages :as views]
            [yoctoplasm-clj.views.common :as common]
            [cemerick.friend :as friend]
            [monger.collection :as mc]
            [ring.util.response :as response]
            [compojure.route :as route]))

(defn index [request]
  (views/index request))

(defn show [request]
  (let [{{id :id} :params} request
        page (mc/find-one-as-map "pages" {:slug id})]
    (cond
     (nil? page) (route/not-found (common/four-oh-four))
     :else (views/show (mc/find-one-as-map "pages" {:slug id}) request))))

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
