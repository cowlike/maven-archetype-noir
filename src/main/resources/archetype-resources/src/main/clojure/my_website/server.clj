(ns my-website.server
  (:require [noir.server :as server]))

(defonce webserver (atom 0))

(server/load-views-ns 'views)

(defn start [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))
        starter (fn [_ port mode] (server/start port {:mode mode :ns 'my-website}))]
    (if (= 0 @webserver)
      (swap! webserver starter port mode)
      (println "already started"))))

(defn stop []
  (let [stopper (fn [cur] (do (server/stop cur) (println "Stopped") 0))]
    (if (= 0 @webserver)
      (println "not running")
      (swap! webserver stopper))))
