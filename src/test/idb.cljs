(ns test.idb
  (:require ["idb" :as idb]
            [cljs.test :refer [deftest async is]]))

(deftest idb-put
  (async done (.. (idb/openDB (str (random-uuid))
                              1
                              #js {:upgrade #(.createObjectStore % "store")})
                  (then #(.put % "store" "val" "key"))
                  (then (is true))
                  (catch #(js/console.error "err" %))
                  (then #(done)))))