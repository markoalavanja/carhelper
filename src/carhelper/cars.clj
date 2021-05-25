(ns carhelper.cars
  (:require [clojure.data.json :as json]
            [clojure.string :refer [replace lower-case]]))

(def json-cars (slurp "resources/cars.json"))
(def cars (json/read-str json-cars))

(defn mpg-to-li [cars]
  (clojure.string/replace cars "Miles_per_Gallon" "Litres_per_250km"))
(defn mtl [cars]
  (map #(into {} (map (fn [[k v]]
                        (vector (mpg-to-li k) v))
                      %))
       cars))

(defn to-kg [cars]
  (clojure.string/replace cars "Weight_in_lbs" "Weight_in_kg/2"))
(defn tokg [cars]
  (map #(into {} (map (fn [[k v]]
                        (vector (to-kg k) v))
                      %))
       cars))

(defn to-keyword [str]
  (keyword (lower-case (clojure.string/replace str " " "-"))))

(defn keywordise [cars]
  (map #(into {} (map (fn [[k v]]
                        (vector (to-keyword k) v))
                      %))
       cars))
(def cars (keywordise (tokg (mtl (json/read-str json-cars)))))

(defn score-power [score]
  (cond
    (nil? score) 0
    (<= score 90) 0
    (<= score 130) 1
    true 2))

(defn insert-hp-score [cars points]
  (assoc cars :score_hp points))

(defn sc-pwr [cars]
  (map #(insert-hp-score % (score-power (:horsepower %))) cars))

(def cars (sc-pwr cars))

(defn get-all []
  (json/write-str cars))

(defn get-younger []
  (filter #(= (:score_hp %) 2) cars))

(defn get-mid []
  (filter #(= (:score_hp %) 1) cars))

(defn get-older []
  (filter #(= (:score_hp %) 0) cars))