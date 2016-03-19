(ns true-cause.models
  (:use clj-bonecp-url.core
        korma.core
        korma.db)
  (:require [clojure.string :as string]))

(def datasource
  (datasource-from-url
    (or (System/getenv "DATABASE_URL")
        "postgres://sklise@localhost:5432/truecause")))

(when (nil? @korma.db/_default)
  (korma.db/default-connection {:pool {:datasource datasource}}))

(defentity fact
  (entity-fields :id :url :content :source))

(defn slugify [s]
  (string/lower-case s))

; naively assume that there are as many facts as the greatest ID
(defn fact-count []
  (:id (last (select fact))))

; since we are naively assuming that there are as many facts as the greatest ID, ensure
; that we get a fact by recursing if there is no result
(defn rand-fact []
  (let [f (first (select fact (where {:id (+ 1 (rand-int (fact-count)))})))]
    (if f
      f
      rand-fact)
    ))

(defn fact-by-slug [url]
  (first (select fact (where {:url (slugify url)}))))

(defn url-to-words [slug]
  (string/replace slug #"[-+]|%20" " "))

(defn fact-or-create [url source]
  (if-let [f (fact-by-slug url)]
    f
    (let [f {:url (slugify url) :content (url-to-words url) :source source}]
      (insert fact (values f))
      f)))
