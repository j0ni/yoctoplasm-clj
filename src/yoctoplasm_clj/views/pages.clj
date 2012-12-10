(ns yoctoplasm-clj.views.pages
  (:require [yoctoplasm-clj.views.common :as common]))

(defn index [request]
  (common/layout "title goes here"
                 "content goes here"
                 request))

(defn show [{:keys [title body]} page request]
  (common/layout title body request))