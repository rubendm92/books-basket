(ns books-basket.core-test
  (:require [clojure.test :refer :all]
            [books-basket.core :refer :all])
  (:import (books_basket.core Book)))

(deftest calculate-no-discount
  (testing "When no book is eligible for discount, the price is the sum of all book prices"
    (is (= 10 (total [(Book. :history 10)])))
    (is (= 15 (total [(Book. :history 10) (Book. :history 5)])))))
