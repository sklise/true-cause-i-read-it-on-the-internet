(ns true-cause.core
  (:use [compojure.core])
  (:require [hiccup.page :refer [html5 include-js include-css]]
            [compojure.handler :as handler]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [true-cause.models :as models]))


(defn factpage [slug]
  (let [fa (models/fact-by-slug slug)]
    (html5
      [:head
        (include-css "/css/styles.css")
        [:title (str "It's true that " (:content fa))]]
      [:body
        [:h1 (:content fa)]
        [:h2 "Its really true 'cause I read it on the internet"]
        [:p [:a {:href (:url fa)} "Permalink"]]
        [:p [:a {:href "/"} "Tell me more true things."]]])))

(defroutes app-routes
  (GET "/" []
    "Hello World")
  (GET "/:f" [f]
    (factpage f)))

(def app
  (-> app-routes
      (wrap-resource "public")
      wrap-keyword-params
      wrap-params))