(ns books-basket.core
  (:gen-class))

(defrecord Book [genre price])

(defn total [books] (reduce + (map :price books)))

