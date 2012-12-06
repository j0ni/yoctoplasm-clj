(ns yoctoplasm-clj.views.common
  (:use [hiccup.page :only [html5 include-css]]
        [hiccup.core :only [html]])
  (:require [yoctoplasm-clj.site :as site]))


(defn layout [title & body]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (include-css "/bootstrap/css/bootstrap.css"
                 "/bootstrap/css/bootstrap-responsive.css")]
   [:body
    [:div {:class "navbar"}
     [:div {:class "navbar-inner"}
      [:a {:href "#" :class "brand"} site/site-name]
      [:ul {:class "nav pull-right"}
       [:li [:a {:href "/login"} "Log in"]]]]]
    
    [:div {:id "content" :class "container"}
     [:h1 title]
     (map #(html [:div {:class "section"} %]) body)]]))


(defn four-oh-four []
  (layout "Page Not Found"
          [:div {:id "four-oh-four" :class "hero-unit"}
           "The page you requested could not be found"]))