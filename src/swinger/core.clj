(ns swinger.core
  (:use seesaw.core)
  (:require [swinger.window :as w])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (invoke-later
    (native!)
    (w/build-window w/f)
    (w/setup)
    ))
