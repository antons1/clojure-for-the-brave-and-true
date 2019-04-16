(ns clojure-noob.ch1)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symmetrize-body-parts
 "Expects a seq of maps that have a :name and :size"
 [asym-body-parts]
 (reduce #(into %1 (set [%2 (matching-part %2)])) [] asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn get-typed-number
  [number]
  (let [lastnum (take-last 1 (str number))]
    (condp = lastnum
      '(\1) (str number "st")
      '(\2) (str number "nd")
      '(\3) (str number "rd")
      (str number "th"))))

(defn alien-matching-parts
  [part limbs]
  (loop [current-limb limbs limb-set []]
    (if (= current-limb 1)
      limb-set
      (recur (dec current-limb) (conj limb-set
                                      {:name (clojure.string/replace
                                              (:name part) #"^(left|1st|first)-"
                                              (str (get-typed-number current-limb) "-"))
                                       :size (:size part)})))))

(alien-matching-parts {:name "first-leg" :size 4} 5)

(defn alien-symmetrize-body-parts
 [asym-body-parts n]
  (reduce #(into %1 (set (into [%2] (alien-matching-parts %2 n)))) [] asym-body-parts))

(set [{:name "a" :size 1} {:name "a" :size 1}])

(alien-symmetrize-body-parts asym-hobbit-body-parts 19)

(defn mapset
  "Expects a seq, returns a new coll consisting of the values of collection, with f applied to each"
  [f collection]
  (loop [remaining-collection collection
         finished-collection #{}]
    (if (empty? remaining-collection)
      finished-collection
      (let [[head & rest] remaining-collection]
        (recur rest (conj finished-collection (f head)))))))

(defn altmapset
  [f collection]
  (set (map f collection)))

(altmapset inc [1 2 3 4 5])

(conj #{} (inc 1))