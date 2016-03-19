(defproject true-cause "2.0.0"
  :url "http://true-cause-i-read-it-on-the-internet.net"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [environ "1.0.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [hiccup "1.0.5"]
                 [korma "0.4.2"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [org.slf4j/slf4j-nop "1.7.2"]
                 [clj-bonecp-url "0.1.1"]]

  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.9.7"]
            [environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "true-cause.jar"
  :profiles
    {:production {:aot :all :env {:production true}}
     :uberjar {:aot :all}}
    :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                         [ring/ring-mock "0.3.0"]]}
  :ring {:handler true-cause.core/app})
