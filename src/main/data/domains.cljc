(ns data.domains)

(def default-domains [{:domain/id #?(:cljs random-uuid :clj (java.util.UUID/randomUUID))
                       :domain/name "Physical"
                       :domain/resource-type-title "Equipment"
                       :domain/initiation-title "Coordination"
                       :domain/initiation-value 1
                       :domain/reaction-title "Reflexes"
                       :domain/reaction-value 1
                       :domain/continuation-title "Endurance"
                       :domain/continuation-value 1
                       :domain/dominance-title "Might"
                       :domain/dominance-value 6
                       :domain/competence-title "Finesse"
                       :domain/competence-value 6
                       :domain/resilience-title "Fortitude"
                       :domain/resilience-value 6}

                      {:domain/id #?(:cljs random-uuid :clj (java.util.UUID/randomUUID))
                       :domain/name "Spiritual"
                       :domain/resource-type-title "Trait"
                       :domain/initiation-title "Exertion"
                       :domain/initiation-value 1
                       :domain/reaction-title "Instinct"
                       :domain/reaction-value 1
                       :domain/continuation-title "Perseverance"
                       :domain/continuation-value 1
                       :domain/dominance-title "Willpower"
                       :domain/dominance-value 6
                       :domain/competence-title "Discipline"
                       :domain/competence-value 6
                       :domain/resilience-title "Tenacity"
                       :domain/resilience-value 6}

                      {:domain/id #?(:cljs random-uuid :clj (java.util.UUID/randomUUID))
                       :domain/name "Mental"
                       :domain/resource-type-title "Expertise"
                       :domain/initiation-title "Concentration"
                       :domain/initiation-value 1
                       :domain/reaction-title "Recognition"
                       :domain/reaction-value 1
                       :domain/continuation-title "Comprehension"
                       :domain/continuation-value 1
                       :domain/dominance-title "Intellect"
                       :domain/dominance-value 6
                       :domain/competence-title "Focus"
                       :domain/competence-value 6
                       :domain/resilience-title "Stability"
                       :domain/resilience-value 6}

                      {:domain/id #?(:cljs random-uuid :clj (java.util.UUID/randomUUID))
                       :domain/name "Social"
                       :domain/resource-type-title "Affiliation"
                       :domain/initiation-title "Persuasion"
                       :domain/initiation-value 1
                       :domain/reaction-title "Insight"
                       :domain/reaction-value 1
                       :domain/continuation-title "Connections"
                       :domain/continuation-value 1
                       :domain/dominance-title "Presence"
                       :domain/dominance-value 6
                       :domain/competence-title "Wit"
                       :domain/competence-value 6
                       :domain/resilience-title "Poise"
                       :domain/resilience-value 6}])

;; Queries




