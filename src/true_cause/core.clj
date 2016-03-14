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
      [:title (str "It's true that " (:content fact))]
      [:meta {:property "og:title"
              :content (:content fact)}]
      [:meta {:property "og:type"
              :content "website"}]
      [:meta {:property "og:site_name"
              :content "It's True 'Cause I Read It On The Internet"}]
      [:meta {:property "og:description"
              :content (str "It's true that " (:content fact) " because it's on the internet")}]]
    [:body
      [:h1 "It's true that"]
      [:h2 (:content fact)]
      [:h3 "'cause I read it on the internet"]
      [:p [:a {:href (:url fact)} "Permalink"] " " [:a {:href "/"} "Random"]]]))

(defroutes app-routes
  (GET "/" []
    (let [fa (models/rand-fact)]
      (page fa)))
  (GET "/:f" [f]
    (let [fa (models/fact-or-create f)]
      (page fa))))

(def app
  (-> app-routes
      (wrap-resource "public")
      wrap-keyword-params
      wrap-params))