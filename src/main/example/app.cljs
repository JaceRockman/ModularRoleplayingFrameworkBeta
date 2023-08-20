(ns example.app
  (:require [example.widgets :refer [button]]
            [expo.root :as expo-root]
            [data.app-state :as app]
            [datascript.core :as ds]
            ["expo-status-bar" :refer [StatusBar]]
            ["react-native" :as rn]
            [reagent.core :as r]
            ["@react-navigation/native" :as rnn]
            ["@react-navigation/native-stack" :as rnn-stack]))

(defonce shadow-splash (js/require "../assets/shadow-cljs.png"))
(defonce cljs-splash (js/require "../assets/cljs.png"))

(defonce Stack (rnn-stack/createNativeStackNavigator))

(defn get-counter-val
  [db]
  (first (ds/q '[:find ?e ?counter
                 :where [?e :counter/val ?counter]]
               db)))

(defn get-counter-enabled
  []
  (ffirst (ds/q '[:find ?counter
                  :where [?e :counter/enabled ?counter]]
                @app/conn)))

(defn inc-counter
  []
  (let [[eid val] (get-counter-val @app/conn)]
    (ds/transact! app/conn [[:db/add eid :counter/val (inc val)]])))

;; (inc-counter 8 1)

(defn home [^js props]
  (r/with-let [[_ counter-val] (get-counter-val @app/conn)
               tap-enabled? (get-counter-enabled)]
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
      [button {:on-press #(inc-counter)
               :disabled? (not tap-enabled?)
               :style {:background-color :blue}}
       "Tap me, I'll count"]]
     [:> rn/View {:style {:align-items :center}}
      [button {:on-press #(ds/transact! app/conn [[:db/add "navigator" :navigator/val :about]])}
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
  []
  (r/with-let [[_ counter-val] (get-counter-val @app/conn)]
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

(defn root []
  ;; The save and restore of the navigation root state is for development time bliss
  (r/with-let [!root-state (ffirst (ds/q '[:find ?navigation-root
                                           :where [?e :navigation/root ?navigation-root]]
                                         @app/conn))
               save-root-state! (fn [^js state]
                                  (ds/transact! app/conn [[:db/add "navigation-root" :navigation/root state]]))
               add-listener! (fn [^js navigation-ref]
                               (when navigation-ref
                                 (.addListener navigation-ref "state" save-root-state!)))]
    [:> rnn/NavigationContainer {:ref add-listener!
                                 :initialState (when !root-state (-> !root-state .-data .-state))}
     [:> Stack.Navigator
      [:> Stack.Screen {:name "Home"
                        :component (fn [props] (r/as-element [home props]))
                        :options {:title "Example App"}}]
      [:> Stack.Screen {:name "About"
                        :component (fn [props] (r/as-element [about props]))
                        :options {:title "About"}}]]]))

(defn start
  {:dev/after-load true}
  []
  (expo-root/render-root (r/as-element [root])))

(defn init []
  (app/init-dev-db)
  (start))