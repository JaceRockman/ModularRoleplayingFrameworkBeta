(ns data.creatures
  (:require
   [data.domains :as domains]))


(def creature-races [{:db/ident :race/elf}
                     {:db/ident :race/human}
                     {:db/ident :race/dwarf}])

(defn example-creatures
  [default-domain-entities]
  [{:creature/id #?(:clj (java.util.UUID/randomUUID) :cljs random-uuid)
    :creature/domains default-domain-entities
    :creature/name "aleksander"
    :creature/race [:race/elf :race/human]}
   {:creature/id #?(:clj (java.util.UUID/randomUUID) :cljs random-uuid)
    :creature/domains default-domain-entities
    :creature/name "eilonwey"
    :creature/race :race/elf}
   {:creature/id #?(:clj (java.util.UUID/randomUUID) :cljs random-uuid)
    :creature/domains default-domain-entities
    :creature/name "durflag"
    :creature/race :race/dwarf}])

(defn new-creature-defaults
  "A transaction schema for a new creature"
  []
  {:creature/id #?(:clj (java.util.UUID/randomUUID) :cljs random-uuid)
   :creature/domains domains/default-domains})


;; Pull Patterns
;; Pull patterns define the structure of the data to be returned given an entity id

(def pull-full-creature
  ['* :creature/race {:creature/domains [:domain/name]}])

(def pull-creature-name
  [:creature/name])


;; Queries
;; Queries retrieve entity ids, attributes, values, and times from the database

(defn get-all-creatures
  "Query for getting all of the creatures in the database.

  Arguments:
   - db connection
   - Pull Pattern
  Returns:
   - creature entity data structured with the given pull pattern"
  [q db pattern]
  (map first
       (q '{:find [(pull ?eid pattern)]
            :in [$ pattern]
            :where [[?eid :creature/id]]}
          db
          pattern)))

(defn get-creature-by-name
  "Returns creature information for all creature's with a given name

  Arguments:
   - db connection
   - Pull Pattern for a creature entity
   - A String that matches on the :creature/name value
  Returns:
   - creature entity data structured with the given pull pattern"
  [q db pattern creature-name]
  (map first
       (q '{:find [(pull ?eid pattern)]
              :in [$ pattern ?creature-name]
              :where [[?eid :creature/name ?creature-name]]}
            db
            pattern
            creature-name)))

;; Transactions
;; Transactions accumulate datoms to the databse

(defn create-creature
  "Takes transaction data for a creature and adds a new creature to the database"
  [transact conn creature-tx-data]
  (transact conn {:tx-data [(merge creature-tx-data {:creature/id #?(:clj (java.util.UUID/randomUUID) :cljs random-uuid)
                                                     :creature/domains domains/default-domains})]}))

(defn update-creature
  "Takes a creature uuid and updates it with the provided transaction data"
  [transact conn creature-uuid creature-tx-data]
  (transact conn {:tx-data [(merge creature-tx-data {:creature/id creature-uuid})]}))

(defn retract-creature
  "Retracts all datoms where the entity id or value is equal to the provided creature-id"
  [transact conn creature-uuid]
  (transact conn {:tx-data [[:db/retractEntity [:creature/id creature-uuid]]]}))

