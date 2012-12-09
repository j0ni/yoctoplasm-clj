(ns yoctoplasm-clj.util.logging
  (:require [clojure.tools.logging :as logger]
            [clojure.string :as string]
            [clojure.pprint :as p]
            [clj-logging-config.log4j :as config])
  (:import [org.apache.log4j PatternLayout ConsoleAppender]
           [java.io StringWriter]))

(defn- format-query-string [q]
  (if (nil? q) "" (str "?" q)))

(defn- pretty-map [m]
  (let [w (StringWriter.)]
    (p/pprint m w)
    (.toString w)))

(defn init []
  (let [appender (ConsoleAppender.
                  (PatternLayout. "%d{ISO8601} %m%n")
                  "System.out")
        level (keyword (get (System/getenv) "loglevel" "info"))]
    (config/set-loggers! :root {:level level :out appender})))

(defn wrap-logger [app]
  (fn [req]
    (let [{:keys [remote-addr request-method uri query-string]} req
          response (app req)
          {status :status} response]
    (logger/debugf "remote=%s method=%s uri=%s%s status=%s"
                   remote-addr
                   (string/upper-case (name request-method))
                   uri
                   (format-query-string query-string)
                   status)
    (logger/trace (pretty-map req))
    (logger/trace (pretty-map response))
    response)))