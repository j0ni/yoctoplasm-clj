(ns yoctoplasm-clj.views.users
  (:require [yoctoplasm-clj.views.common :as common]))

(defn login-page []
  (common/layout
   ""
   [:form {:class "form-horizontal" :action "/login" :method "POST"}
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Username"]
     [:div {:class "controls"}
      [:input {:type "text" :placeholder "Username" :id "username" :name "username"}]]]
    [:div {:class "control-group"}
     [:div {:class "control-label"} "Password"]
     [:div {:class "controls"}
      [:input {:type "password" :placeholder "Password" :id "password" :name "password"}]]]
    [:div {:class "control-group"}
     [:div {:class "controls"}
      [:button {:type "submit" :class "btn"} "Sign In"]]]]))