(defproject true-cause "0.1.0"
  :description "FIXME: write description"
  :min-lein-version "2.0.0"
  :url "http://true-cause-i-read-it-on-the-internet.net"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [compojure "1.4.0"]
                 [korma "0.4.2"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [hiccup "1.0.5"]
                 [org.slf4j/slf4j-nop "1.7.2"]
                 [clj-bonecp-url "0.1.1"]
                 [javax.servlet/servlet-api "2.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :profiles {:uberjar {:aot :all}}
  :ring {:handler true-cause.core/app})
