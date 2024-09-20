(ns rtable.aggrid
  (:require
   [re-frame.core :as rf]
   ["ag-grid-react" :as rs :refer [AgGridReact]]))

(defn aggrid-impl
  "displays a seq in a table, uses ag-grid"
  [spec]
  [:> AgGridReact spec])

(defn ag-theme-classname [theme]
  (if (= theme true)
    ""
    (str "ag-theme-" theme)))

(defn aggrid-styled [{:keys [_style _theme] :as spec}]
  (let [global-theme (rf/subscribe [:css/theme-component :aggrid])]
    (fn [{:keys [style theme] :as spec}]
      [:div {:className (if theme
                          (ag-theme-classname theme)
                          (ag-theme-classname @global-theme))
             :style style}
       [aggrid-impl spec]])))

; simple wraper to create default box size
(defn aggrid [spec]
  [aggrid-styled spec])
