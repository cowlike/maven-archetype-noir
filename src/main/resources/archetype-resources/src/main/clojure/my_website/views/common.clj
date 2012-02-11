(ns my-website.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defn layout [title & body]
  (html
    [:head [:title title]]
    [:body [:h1.header title] body]))