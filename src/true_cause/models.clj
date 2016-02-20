(ns true-cause.models
  (:use korma.core korma.db)
  (:require [clojure.string :as string]))

(defdb true-cause
  (sqlite3 {:db "true.db"}))

(defentity fact
  (entity-fields :id :url :content))

(defn fact-by-slug [slug]
  (first (select fact (where {:url slug}))))
