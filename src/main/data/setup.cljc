(ns data.setup
  (:require
   [#?(:clj datomic.api :cljs datascript.core) :as d]
   [mount.core :as mount]
   [data.schema :as schema]
   [data.creatures :as creatures]
   [data.domains :as domains]
   [data.worlds :as worlds]))

;; (def cfg erver-type :dev-local
;;           :system "datomic-samples"})
;; (def client (d/client cfg))
;; (d/create-database client {:db-name "modular-roleplaying-framework"})
;; (def conn (d/connect client {:db-name "modular-roleplaying-framework"}))
;; (def db (d/db conn))

;; This file should be used to set up the config, the client, the database connection 

(comment
  ;; Transact all of the schemas in a single transaction
  (d/transact conn {:tx-data schema/merged-schemas})
  ;; Transact the default data that doesn't rely on other data
  (d/transact conn {:tx-data domains/default-domains})
  (d/transact conn {:tx-data creatures/creature-races})
  ;; Query to get the default domain entity ids to create the initial creatures
  ;; There is probably a better way to initialize these, but this works for now
  (def init-domain-entities (flatten (d/q '[:find ?e
                                            :where [?e :domain/id]]
                                          db)))
  ;; Transact the initial creatures
  (d/transact conn {:tx-data (creatures/example-creatures init-domain-entities)}))

;; (comment
;;   (d/delete-database client {:db-name "modular-roleplaying-framework"}))




;; (creatures/get-all-creatures d/q db '[*])



;; (defn app []
;;   (k/switch-route
;;    (fn [route] (-> route :data :name))
;;    :home [:html
;;           [:body
;;            [:h1 "Welcome!"]
;;            [:ul
;;             (for [i (range count)]
;;               [:li i])]]]
;;    :page "Page"
;;    nil "PAGE NOT FOUND"))

;; (def routes
;;   [["/" :home]
;;    ["/page1" :page]])

;; (defn main! []
;;   (k/start! {:routes routes
;;              :initial-db initial-db
;;              :root-component [app]}))

;; ;; All of the server stuff and routes and jetty things are for getting things running on localhost and eventually on an actual server. The datomic stuff below that is just for interacting with the database. Both are necessary.

;; (defonce server (atom nil))

;; ;; This will eventually be handled by the cljs side of things
;; (defn home-view [count]
;;   [:html
;;    [:body
;;     [:h1 "Welcome!"]
;;     [:ul
;;      (for [i (range count)]
;;        [:li i])]]])

;; ;; This will eventually be handled by the cljs side of things.
;; (defn routes []
;;   (c/routes
;;    (c/GET  "/" [count]
;;      {:status 200
;;       :headers {"Content-Type" "text/html"}
;;       :body (str (h/html (home-view (Integer. (or count 3)))))})
;;    (c/GET "/:foo" [foo]
;;      {:status 200
;;       :body (str "you asked for " foo)})
;;    (c/POST "/api" [:as req]
;;      (clojure.pprint/pprint (:body-params req))
;;      {:status 200
;;       :body {:hello 123}})))

;; (defn handler [req]
;;   ((routes) req))

;; (defn start-jetty! [& _]
;;   (reset!
;;    server
;;    (jetty/run-jetty (-> #'handler
;;                         muuntaja/wrap-format
;;                         (ring-defaults/wrap-defaults ring-defaults/api-defaults))
;;                     {:join? false
;;                      :port 3428})))

;; (defn stop-jetty! []
;;   (.stop @server)
;;   (reset! server nil))

;; (defn -main [& args]
;;   (start-jetty!))

;; (comment
;;   (start-jetty!)
;;   (stop-jetty!)
;;   (-main))
