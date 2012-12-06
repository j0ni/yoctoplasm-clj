(ns yoctoplasm-clj.views.common
  (:use [hiccup.page :only [html5 include-css]]))

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
    [:div {:id "header"}
     [:h1 {:class "container"} title]]
    [:div {:id "content" :class "container"} body]]))

(defn four-oh-four []
  (layout "Page Not Found"
          [:div {:id "four-oh-four" :class "hero-unit"}
           "The page you requested could not be found"]))