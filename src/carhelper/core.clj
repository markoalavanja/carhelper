(ns carhelper.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]
            [carhelper.api :as cars])
  (:gen-class))

(defroutes app 
  (wrap-params (GET "/cars" [] (cars/getall)))
  (wrap-params (GET "/carsyoung" [] (cars/get-younger)))
  (wrap-params (GET "/carsmid" [] (cars/get-mid)))
  (wrap-params (GET "/carsolder" [] (cars/get-older))))

(defn -main
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8000"))]
    (server/run-server (->
                       (wrap-defaults #'app (site-defaults :security false))
    {:port port}))))