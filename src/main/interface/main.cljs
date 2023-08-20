(ns interface.main
  (:require
   [reagent.core :as r]
   [datascript.core :as ds]
   [data.app-state :as app]))

(def initial-db
  app/init-dev-db)

(defn routes
  []
  [:route1
   :route2])

(defn root-component
  [name]
  {:div
   [:div (str "Hello " name)]
   [:div "and"]
   [:div "Goodbye"]})

(defn start!
  [{:keys [initial-db routes root-component]}]
  (root-component))

(defn main
  []
  (start! {:initial-db initial-db
           :routes routes
           :root-component root-component}))
