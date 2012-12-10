(ns yoctoplasm-clj.views.common
  (:use [hiccup.page :only [html5 include-css include-js]]
        [hiccup.core :only [html]]
        [clojure.pprint :only [pprint]])
  (:require [yoctoplasm-clj.config :as config]
            [cemerick.friend :as friend]))


(defn layout [title body & request]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (include-css "/assets/bootstrap/css/bootstrap.min.css"
                 "/assets/bootstrap/css/bootstrap-responsive.min.css")
    (include-js "/assets/jquery/jquery-1.8.3.min.js"
                "/assets/bootstrap/js/bootstrap.min.js")]
   [:body
    [:div {:class "navbar"}
     [:div {:class "navbar-inner"}
      [:a {:href "#" :class "brand"} config/site-name]
      [:ul {:class "nav pull-right"}
       (if-let [username (-> request first :session :cemerick.friend/identity :current)]
         [:li {:class "dropdown"}
          [:a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"} username
           [:span {:class "caret"}]]
          [:ul {:class "dropdown-menu" :role "menu"}
           [:li [:a {:href "/users/logout"} "Sign Out"]]]]
         [:li [:a {:href "/users/login"} "Log in"]])]]]
    [:div {:id "content" :class "container"}
     [:h1 title]
     body]]))


(defn four-oh-four []
  (layout "Page Not Found"
          [:div {:id "four-oh-four" :class "hero-unit"}
           "The page you requested could not be found"]))