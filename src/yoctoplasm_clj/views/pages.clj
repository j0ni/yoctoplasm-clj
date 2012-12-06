(ns yoctoplasm-clj.views.pages
  (:require [yoctoplasm-clj.views.common :as common]))

(defn index []
  (common/layout "index title goes here"
                 "index content goes here"
                 "and here of course"))

(defn show [page]
  (common/layout (:title page) (:body page)))