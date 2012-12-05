(ns yoctoplasm-clj.controllers.pages
  (:use [compojure.core :only [defroutes GET]])
  (:require [yoctoplasm-clj.views.pages :as view]))

(defn index []
  (view/index))

(defn show [id]
  (view/show {:title id :body id}))

(defroutes routes
  (GET "/" [] (index))
  (GET "/:id" [id] (show id)))