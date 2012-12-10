(ns yoctoplasm-clj.views.common
  (:use [hiccup.page :only [html5 include-css]]
        [hiccup.core :only [html]]
        [clojure.pprint :only [pprint]])
  (:require [yoctoplasm-clj.config :as config]
            [cemerick.friend :as friend]))


(defn layout [title body & request]
  (let [identity (friend/identity request)]
    ;; (pprint request)
    ;; (pprint identity)
    ;; (flush)
    (html5
     [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
      [:title title]
      (include-css "/assets/bootstrap/css/bootstrap.css"
                   "/assets/bootstrap/css/bootstrap-responsive.css")]
     [:body
      [:div {:class "navbar"}
       [:div {:class "navbar-inner"}
        [:a {:href "#" :class "brand"} config/site-name]
        [:ul {:class "nav pull-right"}
         (if (nil? identity)
           [:li [:a {:href "/users/login"} "Log in"]]
           [:li {:class "dropdown"}
            [:a {:class "dropdown-toggle" :data-toggle "dropdown"} identity
             [:b {:class "caret"}]]
            [:ul {:class "dropdown-menu"}
             [:li [:a {:href "/users/logout"} "Sign Out"]]]])]]]
      [:div {:id "content" :class "container"}
       [:h1 title]
       body]])))


(defn four-oh-four []
  (layout "Page Not Found"
          [:div {:id "four-oh-four" :class "hero-unit"}
           "The page you requested could not be found"]))