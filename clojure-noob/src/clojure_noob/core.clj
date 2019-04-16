(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))

(map #(hash-map :human %1 :critter %2) [8.9 2.3 5.5 1.3] [0.0 1.0 0.2 0.4])