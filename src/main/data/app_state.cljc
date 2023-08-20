(ns data.app-state
  (:require [datascript.core :as ds]
            [data.schema :as schema]
            [data.domains :as domains]
            [data.creatures :as creatures]))

(def conn (ds/create-conn))

(defn init-dev-db
  []
  (let [_ (ds/transact! conn creatures/creature-races)
        _ (ds/transact! conn domains/default-domains)
        _ (ds/transact! conn [[:db/add 2 :counter/val 0]
                              [:db/add 2 :counter/enabled true]])
        db (:db-after (ds/transact! conn creatures/creature-races))
        init-domain-entities (flatten (ds/q '[:find ?e
                                              :where [?e :domain/id]]
                                            db))]
    (:db-after (ds/transact! conn (creatures/example-creatures init-domain-entities)))))
