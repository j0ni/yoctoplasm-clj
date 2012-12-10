(ns yoctoplasm-clj.controllers.pages
  (:use [compojure.core :only [defroutes GET]])
  (:require [yoctoplasm-clj.views.pages :as views]
            [cemerick.friend :as friend]))

(defn index [request]
  (views/index request))

(defn show [{{id "id"} :params} request]
  (views/show {:title id :body id} request))

(defroutes routes
  (GET "/" request (index request))
  (GET "/:id" request (friend/authorize #{::admin} (show request))))









