# Sméagol

Sméagol is the backend for Junior Consultings service database. The goal is to provide a REST API for service credentials/information for the company as a whole and on a per project basis.

A service is something like Heroku, Mailchimp, Surveymonkey, Mandrill, etc.

The project is implemented as a microservice-inspired, RESTful API served over HTTP(S).
It is implemented in Clojure.
It uses [`jrc-auth`](https://github.com/juniorconsulting/jrc-auth) for authentication.
# Clojure resources

[Brave Clojure](http://www.braveclojure.com/) is a great resource for learning Clojure (and Emacs).

# Architecture

A service looks something like this:

```
{
    "id": "7af472ee-054e-4986-81a5-64163b4be67b",
    "password": "derp", // TODO: not really
    "projectids": [ // Ids of the projects that this service is associated with.
        2           // If empty, the service is assumed to be available to everyone
    ],
    "title": "Google Drive",
    "userid": 1, // jrc-auth userid
    "username": "oyvindrobertsen"
}
```

# Technology stack

## Database

[RethinkDB](https://www.rethinkdb.com/)

## HTTP / Routing / JSON

* [Ring](https://github.com/ring-clojure/ring) / [Compojure](https://github.com/weavejester/compojure)

# Dependencies

* [Leiningen](http://leiningen.org/)
* [RethinkDB](https://www.rethinkdb.com/)

# Setup

* Clone repo
* Start `rethinkdb`
* `lein ring server-headless`
