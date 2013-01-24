(ns yoctoplasm-clj.controllers.users
  (:require [cemerick.friend :as friend]
            [ring.util.response :as response]
            [yoctoplasm-clj.views.users :as views]
            [compojure.core :refer [ANY GET defroutes]]))

(defroutes routes
  (GET "/login" [] (views/login-page))
  (friend/logout (ANY "/logout" [] (response/redirect "/"))))