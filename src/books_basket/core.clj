(ns books-basket.core
  (:gen-class))

(defrecord Book [genre price])

(defmulti apply-discount (fn [[genre [number-of-books base-price]]] genre))
(defmethod apply-discount :fantasy [[_ [_ base-price]]] (* 0.8 base-price))
(defmethod apply-discount :it [[_ [number-of-books base-price]]] (if (> number-of-books 2) (* 0.7 base-price) (* 0.9 base-price)))
(defmethod apply-discount :travel [[_ [number-of-books base-price]]] (if (> number-of-books 3) (* 0.6 base-price) base-price))
(defmethod apply-discount :default [[_ [_ base-price]]] base-price)

(defn- calculate-base-price [books] (reduce + (map :price books)))

(defn- genre-entry [[genre books]]
  (vector genre (vector (count books) (calculate-base-price books))))

(defn- price-by-book-genre [books] (map genre-entry (group-by :genre books)))

(defn- calculate-price [books] (map apply-discount (price-by-book-genre books)))

(defn total [books] (reduce + (calculate-price books)))
