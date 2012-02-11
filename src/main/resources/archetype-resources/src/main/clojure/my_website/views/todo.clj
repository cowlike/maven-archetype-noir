(ns my-website.views.todo
  (:require [my-website.views.common :as common])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial todo-item [{:keys [id title due]}]
    [:li {:id id} ;; maps define HTML attributes
        [:h3 title]
        [:span.due due]]) ;; add a class

(defpartial todos-list [items]
  [:ul#todoItems ;; set the id attribute
   (map todo-item items)])

(defpage "/" []
  (common/layout "Hello World"))

(defpage "/todo" []
  (common/layout "My Todo List" 
          (todos-list [{:id "todo1" :title "Get Milk" :due "today"}
                       {:id "todo2" :title "Browse" :due "whenever"}
                       {:id "todo3" :title "Go Running" :due "today"}])))

(defpage "/params" {:keys [id dir] :as params}
  (common/layout "Parameters"
          [:p (str "id = " id)]
          [:p (str "dir = " dir)]
          [:p (str "all = " params)]))