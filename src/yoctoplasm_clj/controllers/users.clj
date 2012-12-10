(ns yoctoplasm-clj.controllers.users
  (:use [compojure.core :only [defroutes GET ANY]])
  (:require [yoctoplasm-clj.views.users :as views]
            [cemerick.friend :as friend]
            [ring.util.response :as response]))

(defroutes routes
  (GET "/login" [] (views/login-page))
  (friend/logout (ANY "/logout" [] (response/redirect "/"))))