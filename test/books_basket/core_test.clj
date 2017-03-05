(ns books-basket.core-test
  (:require [clojure.test :refer :all]
            [books-basket.core :refer :all])
  (:import (books_basket.core Book)))

(deftest calculate-no-discount
  (testing "Give no discount when a book is not eligible for a discount."
    (is (== 10 (total [(Book. :history 10)])))
    (is (== 15 (total [(Book. :history 10) (Book. :history 5)])))))

(deftest calculate-fantasy-book
  (testing "Give 20% discount for each Fantasy book."
    (is (== 16 (total [(Book. :fantasy 20)])))
    (is (== 13 (total [(Book. :fantasy 10) (Book. :history 5)])))))

(deftest calculate-IT-book
  (testing "Give 30% discount for each IT book when there are more than two of them and 10% otherwise."
    (is (== 18 (total [(Book. :it 20)])))
    (is (== 42 (total [(Book. :it 20) (Book. :it 10) (Book. :it 30)])))))

(deftest calculate-travel-book
  (testing "Give 40% discount for Travel books when there are more than three of them and no discount otherwise."
    (is (== 20 (total [(Book. :travel 20)])))
    (is (== 48 (total [(Book. :travel 20) (Book. :travel 20) (Book. :travel 10) (Book. :travel 30)])))))