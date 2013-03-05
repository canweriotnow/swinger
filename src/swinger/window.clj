(ns swinger.window
  (:use seesaw.core)
  (:use clojure.repl)
  )

(def f 
  (frame :title "Swinger" :on-close :exit))

(defn display [content]
  (config! f :content content)
  content)

(def b (button :text "Click Me!" :size [75 :by 50]))

(defn build-window [frm]
  (-> frm pack! show!)
  (config! frm :size [600 :by 800]))

(defn add-btn [btn]
  (display btn)
  (listen btn :action (fn [e] (alert e "Thanks!"))))

(def lb (listbox :model (-> 'seesaw.core ns-publics keys sort)))

(def area (text :multi-line? true :font "MONOSPACED-PLAIN-14"))

(def split 
  (left-right-split 
    (scrollable lb) 
    (scrollable area) 
    :divider-location 1/3))

(defn doc-str [s] (-> (symbol "seesaw.core" (name s)) resolve meta :doc))

(def rbs
  (for [i [:source :doc]]
    (radio :id i :class :type :text (name i))))

(def group (button-group))

(defn doc-fn [sc]
  (listen group :selection
          (fn [e]
            (when-let [s (selection e)]
              (cond (= s :doc) (doc-str sc)
                    (= s :source) (source sc)
                    )
              )
            )
          )

  )

(defn setup []
  (config! (select f [:.type]) :group group)
  (display (border-panel
             :north (horizontal-panel :items rbs)
             :center split
             :vgap 5 :hgap 5 :border 5
             ))
  (listen lb :selection
          (fn [e]
            (when-let [s (selection e)]
              (-> area
                  (text! (doc-str s))
                  (scroll! :to :top))))))


