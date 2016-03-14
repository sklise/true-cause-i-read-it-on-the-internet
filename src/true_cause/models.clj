(ns true-cause.models
  (:use korma.core korma.db)
  (:require [clojure.string :as string]))

(defdb true-cause
  (sqlite3 {:db "true.db"
            :delimiters ""}))

(defentity fact
  (entity-fields :id :url :content))

(defn slugify [s]
  (string/lower-case s))

(defn fact-count []
  (:cnt (first (select fact (fields (raw "count(*) cnt"))))))

(defn rand-fact []
  (first (select fact (where {:id (+ 1 (rand-int (fact-count)))}))))

(defn fact-by-slug [url]
  (first (select fact (where {:url (slugify url)}))))

(defn fact-or-create [url]
  (if-let [f (fact-by-slug url)]
    f
    (let [f {:url (slugify url) :content (url-to-words url)}]
      (insert fact (values f))
      f)))

(defn url-to-words [slug]
  (string/replace slug #"[-+]|%20" " "))
