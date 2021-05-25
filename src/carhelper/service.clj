(ns carhelper.service
  (:require [carhelper.cars :as cars]))

(defn all-cars []
  (cars/get-all))

(defn get-younger []
  (cars/get-younger))

(defn get-mid []
  (cars/get-mid))

(defn get-older []
  (cars/get-older))