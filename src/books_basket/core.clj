(ns books-basket.core
  (:gen-class))

(defrecord Book [genre price])

(def discounts
  {
   :fantasy (fn [number-of-books base-price] (* 0.8 base-price))
   :it (fn [number-of-books base-price] (if (> number-of-books 2) (* 0.7 base-price) (* 0.9 base-price)))
   :travel (fn [number-of-books base-price] (if (> number-of-books 3) (* 0.6 base-price) base-price))
   })

(defn- calculate-base-price [books] (reduce + (map :price books)))

(defn- genre-entry [[genre books]]
  (vector genre (vector (count books) (calculate-base-price books))))

(defn- price-by-book-genre [books] (map genre-entry (group-by :genre books)))

(defn- apply-discount [[genre [number-of-books base-price]]]
  ((get discounts genre (fn [number-of-books base-price] base-price)) number-of-books base-price))

(defn- calculate-price [books] (map apply-discount (price-by-book-genre books)))

(defn total [books] (reduce + (calculate-price books)))

