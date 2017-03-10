(ns books-basket.core
  (:gen-class))

(defrecord Book [genre price])

(defn- calculate-base-price [books] (reduce + (map :price books)))

(defmulti calculate-price (fn [[genre books]] genre))
(defmethod calculate-price :fantasy [[_ books]]
  (* 0.8 (calculate-base-price books)))
(defmethod calculate-price :it [[_ books]]
  (if (> (count books) 2) (* 0.7 (calculate-base-price books)) (* 0.9 (calculate-base-price books))))
(defmethod calculate-price :travel [[_ books]]
  (if (> (count books) 3) (* 0.6 (calculate-base-price books)) (calculate-base-price books)))
(defmethod calculate-price :default [[_ books]]
  (calculate-base-price books))

(defn- price-by-book-genre [books] (map calculate-price (group-by :genre books)))

(defn total [books] (reduce + (price-by-book-genre books)))
