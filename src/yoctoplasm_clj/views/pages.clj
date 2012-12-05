(ns yoctoplasm-clj.views.pages
  (:require [yoctoplasm-clj.views.layout :as layout]))

(defn index []
  (layout/common "index title goes here"
                 "index content goes here"
                 "and here of course"))

(defn show [page]
  (layout/common (:title page) (:body page)))