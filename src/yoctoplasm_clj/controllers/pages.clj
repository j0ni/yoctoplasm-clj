(ns yoctoplasm-clj.controllers.pages
  (:use [compojure.core :only [defroutes GET]])
  (:require [yoctoplasm-clj.views.pages :as view]
            [cemerick.friend :as friend]))

(defn index []
  (view/index))

(defn show [id]
  (view/show {:title id :body id}))

(defroutes routes
  (GET "/" [] (index))
  (GET "/:id" [id]
       (friend/authorize #{:admin}
                         {:response-msg "403 message thrown with unauthorized stone"}
                         (show id))))









