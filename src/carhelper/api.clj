(ns carhelper.api
  (:require [carhelper.service :as service]
            [ring.util.response :as response-utils]))

(defn getall []
  (try
    (response-utils/response (service/all-cars))
    (catch Exception e (response-utils/status (response-utils/response "cars.general.error") 400))))

(defn get-younger []
  (try
    (response-utils/response (service/get-younger))
    (catch Exception e (response-utils/status (response-utils/response "cars.general.error") 400))))

(defn get-mid []
  (try
    (response-utils/response (service/get-mid))
    (catch Exception e (response-utils/status (response-utils/response "cars.general.error") 400))))

(defn get-older []
  (try
    (response-utils/response (service/get-older))
    (catch Exception e (response-utils/status (response-utils/response "cars.general.error") 400))))