(ns numparser.core
  (:require [clojure.string :as str]))

(defn- ascii->digit [ascii]
  (let [digit (int ascii)]
    (if (< 47 digit 58)
      (- digit 48)
      (throw (ex-info "failure: not digit." {:ascii ascii})))))

(defn- do-parse-int [chars index cache]
  (if (seq? chars)
    (let [[h & t] chars
          new-part (* (ascii->digit h)
                      (Math/round (Math/pow 10 index)))]
      (recur t
             (inc index)
             (cons new-part cache)))
    (reduce + cache)))

(defn parse-int [s]
  (if (empty? s)
    (throw (ex-info "failure: empty." {:input s}))
    (-> s
        seq
        reverse
        (do-parse-int 0 []))))

(defn parse-float [s]
  (if (empty? s)
    (throw (ex-info "failure: empty." {:input s}))
    (let [[int dec] (str/split s #"\.")]
      (+ (parse-int int)
         (* (parse-int (or dec "0")) (Math/pow 10 (- (count dec))))))))
