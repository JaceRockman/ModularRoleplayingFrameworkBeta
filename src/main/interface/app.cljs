(ns interface.app
  (:require
   [interface.widgets :refer [button]]
   [expo.root :as expo-root]
   [data.app-state :as app]
   [datascript.core :as ds]
   ["expo-status-bar" :refer [StatusBar]]
   ["react-native" :as rn]
   [reagent.core :as r]
   ["@react-navigation/native" :as rnn]
   ["@react-navigation/native-stack" :as rnn-stack])
  (:require-macros
   [interface.app :refer [profile]]))

(defonce shadow-splash (js/require "../assets/shadow-cljs.png"))
(defonce cljs-splash (js/require "../assets/cljs.png"))

(defonce Stack (rnn-stack/createNativeStackNavigator))

(defn get-counter-val
  [db]
  (first (ds/q '[:find ?e ?counter
                 :where [?e :counter/val ?counter]]
               db)))

(defn get-counter-enabled
  [db]
  (ffirst (ds/q '[:find ?counter
                  :where [?e :counter/enabled ?counter]]
                db)))

(defn inc-counter
  [db]
  (let [[eid val] (get-counter-val db)]
    (ds/transact! app/conn [[:db/add eid :counter/val (inc val)]])))

;; (inc-counter 8 1)


(defn home [db ^js props]
  (let [[_ counter-val] (get-counter-val db)
        tap-enabled? (get-counter-enabled db)]
    [:> rn/View {:style {:flex 1
                         :padding-vertical 50
                         :justify-content :space-between
                         :align-items :center
                         :background-color :white}}
     [:> rn/View {:style {:align-items :center}}
      [:> rn/Text {:style {:font-weight   :bold
                           :font-size     72
                           :color         :blue
                           :margin-bottom 20}} counter-val]
      [button {:on-press #(inc-counter db)
               :disabled? (not tap-enabled?)
               :style {:background-color :blue}}
       "Tap me, I'll count"]]
     [:> rn/View {:style {:align-items :center}}
      [button {:on-press (fn []
                           (-> props .-navigation (.navigate "About")))}
       "Tap me, I'll navigate"]]
     [:> rn/View
      [:> rn/View {:style {:flex-direction :row
                           :align-items :center
                           :margin-bottom 20}}
       [:> rn/Image {:style {:width  160
                             :height 160}
                     :source cljs-splash}]
       [:> rn/Image {:style {:width  160
                             :height 160}
                     :source shadow-splash}]]
      [:> rn/Text {:style {:font-weight :normal
                           :font-size   15
                           :color       :blue}}
       "Using: shadow-cljs+expo+reagent+re-frame"]]
     [:> StatusBar {:style "auto"}]]))

(defn- about
  [db]
  (r/with-let [[_ counter-val] (get-counter-val db)]
    [:> rn/View {:style {:flex 1
                         :padding-vertical 50
                         :padding-horizontal 20
                         :justify-content :space-between
                         :align-items :flex-start
                         :background-color :white}}
     [:> rn/View {:style {:align-items :flex-start}}
      [:> rn/Text {:style {:font-weight   :bold
                           :font-size     54
                           :color         :blue
                           :margin-bottom 20}}
       "About Example App"]
      [:> rn/Text {:style {:font-weight   :bold
                           :font-size     20
                           :color         :blue
                           :margin-bottom 20}}
       (str "Counter is at: " counter-val)]
      [:> rn/Text {:style {:font-weight :normal
                           :font-size   15
                           :color       :blue}}
       "Built with React Native, Expo, Reagent, re-frame, and React Navigation"]]
     [:> StatusBar {:style "auto"}]]))

(defn root [db]
  ;; The save and restore of the navigation root state is for development time bliss
  (r/with-let [!root-state (ffirst (ds/q '[:find ?navigation-root
                                           :where [1 :navigator/val ?navigation-root]]
                                         db))
               save-root-state! (fn [^js state]
                                  (ds/transact! app/conn [[:db/add 1 :navigator/val state]]))
               add-listener! (fn [^js navigation-ref]
                               (when navigation-ref
                                 (.addListener navigation-ref "state" save-root-state!)))]
    [:> rnn/NavigationContainer {:ref add-listener!
                                 :initialState (when !root-state (-> !root-state .-data .-state))}
     [:> Stack.Navigator
      [:> Stack.Screen {:name "Home"
                        :component (fn [props] (r/as-element [home db props]))
                        :options {:title "Example App"}}]
      [:> Stack.Screen {:name "About"
                        :component (fn [props] (r/as-element [about db props]))
                        :options {:title "About"}}]]]))

;; This would be a simpler way to do routing for the app
(defn my-root [db]
  (println (get-counter-val db))
  (case (ffirst (ds/q '[:find ?navigator
                        :where [?eid :navigator/val ?navigator]]
                      db))
    :home (r/as-element [home db {}])
    :about (r/as-element [about db {}])))

(defn render
  {:dev/after-load true}
  ([] (render @app/conn))
  ([db]
   (profile "render"
            (expo-root/render-root (r/as-element [root @app/conn])))))

;; re-render on every DB change
(ds/listen! app/conn :render
            (fn [tx-report]
              (render (:db-after tx-report))))

(defn init []
  (app/init-dev-db)
  (render))