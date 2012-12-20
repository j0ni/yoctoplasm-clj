(ns yoctoplasm-clj.views.pages
  (:require [yoctoplasm-clj.views.common :as common])
  (:import org.pegdown.PegDownProcessor))

(defn index [request]
  (common/layout "title goes here"
                 "content goes here"
                 request))

(defn show [page request]
  (let [{:keys [title body]} page
        formatted-body (.markdownToHtml (PegDownProcessor.) body)]
    (common/layout title formatted-body request)))

(defn new-page [request]
  (common/layout
   "Create Page"
   [:form {:class "form-horizontal" :action "/pages" :method "POST"}
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Slug"]
     [:div {:class "controls"}
      [:input {:type "text" :placeholder "Slug" :id "slug" :name "slug"}]]]
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Title"]
     [:div {:class "controls"}
      [:input {:type "text" :placeholder "Title" :id "title" :name "title"}]]]
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Content"]
     [:div {:class "controls"}
      [:textarea {:rows "20" :class "field span6" :id "body" :name "body"}]]]
    [:div {:class "control-group"}
     [:div {:class "controls"}
      [:button {:type "submit" :class "btn"} "Save"]]]]))