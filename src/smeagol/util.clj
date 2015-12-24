(ns smeagol.util)

(defn in? 
  "true if seq contains elm"
  [seq elem]
  (some #(= elem %) seq))
