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
* Projects

# Technology stack

## Database

* PostgreSQL
* MySQL
* MongoDB
* DynamoDB
* Etc.

[SQLKorma](http://sqlkorma.com/) seems like a good choice for a SQL querying DSL. (Reasonably active development and clear documentation.)
[Lobos](https://github.com/budu/lobos) supports table creation and migrations.

## Security / Authentication

* [Buddy](https://github.com/funcool/buddy)

## HTTP / Routing

* [Ring](https://github.com/ring-clojure/ring) / [Compojure](https://github.com/weavejester/compojure)

## JSON encoding/decoding

* [Cheshire](https://github.com/dakrone/cheshire)

# Dependencies

* [Leiningen](http://leiningen.org/)

# Setup

* Clone repo
* `lein ring server`
