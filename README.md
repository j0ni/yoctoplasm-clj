# Yoctoplasm

A Clojure web application with CMSish tendencies, for experimenting
with Clojure on the web, and Heroku as a Clojure web deployment platform.

## Usage

Run it with foreman locally:

    $ foreman start

Or with leiningen:

    $ lein ring server

Run the tests:

    $ lein midje

Get louder logs:

    $ loglevel=debug lein ring server

Get loudest logs:

    $ loglevel=trace lein ring server

## License

Copyright © 2012 Jonathan Irving

Distributed under the Gnu Public License.
