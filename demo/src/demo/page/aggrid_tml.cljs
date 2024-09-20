(ns demo.page.aggrid-tml
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r]
   [rtable.tmlds.aggrid :as ag-tml]))

(defonce clicked? (r/atom false))

;(rf/dispatch [:css/set-theme-component :aggrid "material"])
;(rf/dispatch [:css/set-theme-component :aggrid "alpine"])
;(rf/dispatch [:css/set-theme-component :aggrid "balham-dark"])

(rf/dispatch [:css/set-theme-component :aggrid true]) ; default

(defn page [_]
  [:div.h-screen.w-screen.bg-blue-100
   ;; aggrid that gets loaded via transit-json
   (if @clicked?
     [ag-tml/aggrid-url {:style {;:width "800px" :height "600px"
                                 :width "100%" :height "100%"}
                         ;:theme "material" ;
                         :columns [{:field "close" :header "C" :resizable true}
                                   {:field "spike"
                                    :cellStyle {:color "red" :background-color "green"}
                                    :resizable true}
                                   {:field "doji"
                                    :type "rightAligned"
                                    :resizable true
                                    ;:valueGetter: p => p.data.athlete
                                    ; A Value Getter is a function that gets called for each row to return the Cell Value for a Column. 
                                    }
                                   {:field "spike-doji" :width 70
                                    :resizable true}
                                   {:field "doji-v" :width 70
                                    :cellClass "shaded-class"
                                    :resizable true}
                                   {:field "spike-doji-v" :width 70 :resizable true}
                                   {:field "long" :width 70
                                    :resizable true
                                    :cellStyle {:fontWeight "bold"}}
                                   {:field "short" :width 70
                                    :resizable true
                                    :cellClassRules {"bg-blue-500" (fn [p]
                                                                     (println "ccr: " p)
                                                                     (nil? (.-value p)))}}]}

      "/r/signal-no-date.transit-json"]
     [:button {:on-click #(reset! clicked? true)} (str "load transit-json")])])