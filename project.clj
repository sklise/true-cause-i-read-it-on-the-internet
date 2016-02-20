(defproject true-cause "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://true-cause-i-read-it-on-the-internet.net"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [compojure "1.4.0"]
                 [korma "0.4.0"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler true-cause.core/app})
