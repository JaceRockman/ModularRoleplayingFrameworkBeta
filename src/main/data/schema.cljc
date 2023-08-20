(ns data.schema)

(def creature-schema [{:db/ident :creature/id
                       :db/valueType :db.type/uuid
                       :db/cardinality :db.cardinality/one
                       :db/unique :db.unique/identity
                       :db/doc "the unique id associated with a creature"}

                      {:db/ident :creature/creator
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/one
                       :db/doc "the user that created a creature"}

                      {:db/ident :creature/name
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "the name used to refer to a creature"}

                      {:db/ident :creature/portrait
                       :db/valueType :db.type/uri
                       :db/cardinality :db.cardinality/one
                       :db/doc "a link to a character's portrait"}

                      {:db/ident :creature/gender
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "a creature's gender if applicable"}

                      {:db/ident :creature/race
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/many
                       :db/doc "the groups that a creature belongs to inherited at birth"}

                      {:db/ident :creature/titles
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/many
                       :db/doc "any special titles belonging to a creature"}

                      {:db/ident :creature/description
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "additional flavor text or details that describe the character in depth"}

                      {:db/ident :creature/experience
                       :db/valueType :db.type/long
                       :db/cardinality :db.cardinality/one
                       :db/doc "a creature's accumulated experience points"}

                      {:db/ident :creature/domains
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/many
                       :db/doc "a list of domains that this creature can interact within"}

                      {:db/ident :creature/resources
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/many
                       :db/doc "a list of resources that belong to a creature"}

                      {:db/ident :creature/actions
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/many
                       :db/doc "a list of actions that the creature is able to take"}

                      {:db/ident :creature/notes
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/many
                       :db/doc "a list of additional notes related to a creature"}])


(def resource-schema [{:db/ident :resource/id
                       :db/valueType :db.type/uuid
                       :db/cardinality :db.cardinality/one
                       :db/unique :db.unique/identity
                       :db/doc "a resource's unique identifier"}

                      {:db/ident :resource/creator
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/one
                       :db/doc "the user that created the resource"}

                      {:db/ident :resource/name
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "a resource's name which does not have to be unique"}

                      {:db/ident :resource/domain
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/one
                       :db/doc "a resource's domain that determines its type (default: equipment, trait, expertise, affiliation, item)"}

                      {:db/ident :resource/properties
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/many
                       :db/doc "the list of properties that apply to the resource"}

                      {:db/ident :resource/quality-name
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "the name displayed for the resource's quality"}

                      {:db/ident :resource/quality-value
                       :db/valueType :db.type/long
                       :db/cardinality :db.cardinality/one
                       :db/doc "the quality bonus or penalty granted by the resource"}

                      {:db/ident :resource/power-name
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "the name displayed for the resource's power"}

                      {:db/ident :resource/power-value
                       :db/valueType :db.type/long
                       :db/cardinality :db.cardinality/one
                       :db/doc "the power bonus or penality granted by the resource"}

                      {:db/ident :resource/description
                       :db/valueType :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc "the non-mechanical flavortext of the resource"}])

(def domain-schema [{:db/ident :domain/id
                     :db/valueType :db.type/uuid
                     :db/cardinality :db.cardinality/one
                     :db/unique :db.unique/identity
                     :db/doc "a domain's unique identifier"}

                    {:db/ident :domain/name
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "a domain's title that should indicate it's use"}

                    {:db/ident :domain/icon
                     :db/valueType :db.type/ref
                     :db/cardinality :db.cardinality/one
                     :db/doc "a domain's icon"}

                    {:db/ident :domain/quality-damage
                     :db/valueType :db.type/tuple
                     :db/cardinality :db.cardinality/one
                     :db/tupleType :db.type/long
                     :db/doc "the damage accrued in a domain"}

                    {:db/ident :domain/resource-type-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for resource types to link them to this domain"}

                    ;; {:db/ident :domain/quality-title
                    ;;  :db/valueType :db.type/string
                    ;;  :db/cardinality :db.cardinality/one
                    ;;  :db/doc "the title used for this domain's quality (default is skills)"}

                    {:db/ident :domain/initiation-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's initiation stat"}

                    {:db/ident :domain/initiation-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's initiation stat"}

                    {:db/ident :domain/reaction-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's reaction stat"}

                    {:db/ident :domain/reaction-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's reaction stat"}

                    {:db/ident :domain/continuation-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's continuation stat"}

                    {:db/ident :domain/continuation-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's continuation stat"}

                    ;; {:db/ident :domain/power-title
                    ;;  :db/valueType :db.type/string
                    ;;  :db/cardinality:db/cardinality :db.cardinality/one
                    ;;  :db/doc "the title used for this domain's power (default is abilities)"}

                    {:db/ident :domain/dominance-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's dominance stat"}

                    {:db/ident :domain/dominance-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's dominance stat"}

                    {:db/ident :domain/competence-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's competence stat"}

                    {:db/ident :domain/competence-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's competence stat"}

                    {:db/ident :domain/resilience-title
                     :db/valueType :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "the title used for this domain's resilience stat"}

                    {:db/ident :domain/resilience-value
                     :db/valueType :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "the value for this domain's resilience stat"}])

(def world-schema [{:db/ident :world/id
                    :db/valueType :db.type/uuid
                    :db/cardinality :db.cardinality/one
                    :db/unique :db.unique/identity
                    :db/doc "the unique id for a world"}

                   {:db/ident :world/name
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "a world's name"}

                   {:db/ident :world/description
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "a short description of a world"}

                   {:db/ident :world/ruleset
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many
                    :db/doc "the rulesets available in a world"}

                   {:db/ident :world/domains
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many
                    :db/doc "the domains available in a world"}

                   {:db/ident :world/creatures
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many
                    :db/doc "the creatures available in a world"}

                   {:db/ident :world/resources
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many
                    :db/doc "the resources available in a world"}])

(def merged-schemas
  (concat creature-schema resource-schema domain-schema world-schema))

(def ds-merged-schemas
  (reduce (fn [schema atom] (assoc schema (:db/ident atom) atom)) merged-schemas))
