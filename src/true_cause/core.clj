(ns true-cause.core
  (:require [hiccup.page :refer [html5 include-js include-css]]
            [compojure.handler :refer [site]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
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
      [:h3 "'cause it's on the internet"]
      (if (:source fact)
        [:p [:a {:href (:source fact) :target "_blank"} "Here's the proof"]])
      [:p [:a {:href (:url fact)} "Permalink"] " " [:a {:href "/"} "Random"]]]))

(defroutes app-routes
  (GET "/" []
       (let [fa (models/rand-fact)]
         (page fa)))
  (GET "/:f" {{f :f s :source} :params}
       (let [fa (models/fact-or-create f s)]
         (page fa))))
  (ANY "*" []
       (route/not-found "404"))

(def app
  (wrap-params (wrap-keyword-params (wrap-resource app-routes "public"))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 3000))]
    (jetty/run-jetty (site #'app)  {:port port :join? false})))