

(ns demo.service.vega
  (:require
   [tablecloth.api :as tc]
   [rtable.plot.vega :as plot]
   [demo.env :refer [env]]))

(def spec
  {;:$schema "https://vega.github.io/schema/vega-lite/v4.json"
   :description "A simple bar chart with embedded data."
   :mark {:type "bar"
          ;:tooltip true
          :tooltip {:content "data"}}
   :encoding {:x {:field "a" :type "ordinal"}
              :y {:field "b" :type "quantitative"}}
   :data {:name "table"}})

(def data
  {:table [{:a "A" :b 28} {:a "B" :b 55} {:a "C" :b 43} {:a "D" :b 91}
           {:a "E" :b 81} {:a "F" :b 53} {:a "G" :b 19} {:a "H" :b 87}
           {:a "I" :b 52} {:a "J" :b 127}]})

(defn vegalite1 []
  (plot/vegalite env {:spec spec} data))

(def ds 
  (tc/dataset {:a ["A" "B" "C" "D" "E" "F" "G" "H" "I" "J"]
               :b [28 55 43 91 81 53 19 87 52 127]}))

(def spec-ds
  {;:$schema "https://vega.github.io/schema/vega-lite/v4.json"
   
   :description "A simple bar chart with embedded data."
   :mark {:type "bar"
          ;:tooltip true
          :tooltip {:content "data"}}
   :encoding {:x {:field "a" :type "ordinal"}
              :y {:field "b" :type "quantitative"}}})

(defn vegalite2 []
  (plot/vegalite-ds env {:cols [:a :b]
                         :spec spec-ds} ds))



(def vega-spec
  {:$schema "https://vega.github.io/schema/vega/v5.json"
   :width 400
   :height 247.2187886279357
   :padding {:top 10, :left 55, :bottom 40, :right 10}
   :data [{:name "table" }]
   :marks [{:type "symbol"
            :from {:data "table"}
            :encode {:enter {:x {:scale "x", :field "x"}
                             :y {:scale "y", :field "y"}
                             :fill {:value "steelblue"}
                             :fillOpacity {:value 1}}
                     :update {:shape "circle", :size {:value 70}, :stroke {:value "transparent"}}
                     :hover {:size {:value 210}, :stroke {:value "white"}}}}]
   :scales [{:name "x", :type "linear", :range "width", :zero false, :domain {:data "table", :field "x"}}
            {:name "y", :type "linear", :range "height", :nice true, :zero false, :domain {:data "table", :field "y"}}]
   :axes [{:orient "bottom", :scale "x"} {:orient "left", :scale "y"}]})

(def vega-data
  {:table [{:x 0, :y 7}
             {:x 1, :y 8}
             {:x 2, :y 7}
             {:x 3, :y 4}
             {:x 4, :y 6}]})


(defn vega1 []
  (plot/vega env {:spec vega-spec} vega-data))




