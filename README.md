# Sméagol

Sméagol is the backend for Junior Consultings service database. The goal is to provide a REST API for service credentials/information for the company as a whole and on a per project basis.

A service is something like Heroku, Mailchimp, Surveymonkey, Mandrill, etc.


We seem to agree that either Django/Python or Clojure are the best choices for technologies. In the intereset of learning something new and different, I propose we use Clojure.

# Clojure resources

TODO: List some resources for learning Clojure.

# Architecture

Base (business logic) entities could be:

* Users
* Services
* Credentials
* Projects

# Technology stack

## Database

[RethinkDB](https://www.rethinkdb.com/) is used for persistense.


## Security / Authentication

* [Buddy](https://github.com/funcool/buddy)

## HTTP / Routing / JSON

* [Ring](https://github.com/ring-clojure/ring) / [Compojure](https://github.com/weavejester/compojure)

# Dependencies

* [Leiningen](http://leiningen.org/)
* [RethinkDB](https://www.rethinkdb.com/)

# Setup

* Clone repo
* Start `rethinkdb`
* `lein ring server-headless`
