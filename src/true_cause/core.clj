(ns true-cause.core
  (:use compojure.core)
  (:require [hiccup.page :refer [html5 include-js include-css]]
            [compojure.handler :as handler]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [true-cause.models :as models]))

(defn page [fact]
  (html5
    [:head
      (include-css "/css/styles.css")
      [:title (str "It's true that " (:content fact))]]
    [:body
      [:h1 (:content fact)]
      [:h2 "Its really true 'cause I read it on the internet"]
      [:p [:a {:href (:url fact)} "Permalink"]]
      [:p [:a {:href "/"} "Tell me more true things."]]]))

(defroutes app-routes
  (GET "/" []
    (let [fa (models/rand-fact)]
      (page fa)))
  (GET "/:f" [f]
    (let [fa (models/fact-by-slug f)]
      (page fa))))

(def app
  (-> app-routes
      (wrap-resource "public")
      wrap-keyword-params
      wrap-params))