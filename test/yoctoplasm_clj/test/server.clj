(ns yoctoplasm-clj.test.server
  (:use midje.sweet
        yoctoplasm-clj.server))

(background (around :facts (let [req {:server-port 3000
                                      :server-name "localhost"
                                      :remote-addr "127.0.0.1"
                                      :uri "/"
                                      :scheme :http
                                      :request-method :get
                                      :headers {}}] ?form)))

(fact "GET / redirects"
  (yoctoplasm (merge req {:request-method :get :uri "/"})) => (contains {:status 302}))

(fact "GET / redirects to /pages"
  (yoctoplasm (merge req {:request-method :get :uri "/"})) => (contains {:headers {"Location" "/pages"}}))

(fact "GET /pages returns a 200 status"
  (yoctoplasm (merge req {:request-method :get :uri "/pages"})) => (contains {:status 200}))