## Event Tracker Project

Week 12-14 Homework Project for Skill Distillery

### Overview

Key : [session = sesh]

The task was to think of an event tracking application. I came up with the idea for a 'Smoke Sesh' (marijuana smoking session) tracker. Essentially a journaling and strain tracking app. This application currently uses a two table database; one for the sesh and its detials, and another for the strain. The entities were mapped out such that a strain can be used for multiple sessions, while a session has a single strain.

The user is able to create, update, delete, and read both strains and sessions. The idea being that the user creates their own database of smoke/strain memories to rely on for later purchases of marijuana as opposed to using the big databases which show generic data about the various strains that are available. Various marijuana strains affect people in different ways, so trying to find new strains based on big lab data is generally unreliable. Future implementation of this application would allow a user to connect with fellow users, finding those that lean towards the same strains to then get a better idea of how they would respond and feel on a new strain.

### Rest API Endpoints

| Return Type | Route                 | Functionality                  |
|-------------|-----------------------|--------------------------------|
| `List<Strain>`  |`GET api/strains`        | Gets all strains                 |
| `Strain`        |`GET api/strains/{id}`   | Gets one strain by id            |
| `Strain`        |`POST api/strains`       | Creates a new strain             |
| `Strain`        |`PUT api/strains/{id}`   | Replaces an existing strain by id|
| `Boolean`     |`DELETE api/strains/{id}`| Deletes an existing strain by id |
| `List<Sesh>`  |`GET api/sessions`        | Gets all sessions                 |
| `Strain`        |`GET api/sessions/{id}`   | Gets one sesh by id            |
| `Strain`        |`POST api/sessions`       | Creates a new sesh             |
| `Strain`        |`PUT api/sessions/{id}`   | Replaces an existing sesh by id|
| `Boolean`     |`DELETE api/sessions/{id}`| Deletes an existing sesh by id |



- To import Postman mappings : [Smoke Sesh Mappings](https://github.com/BarrelTB/EventTrackerProject/blob/master/SmokeSeshMappings.postman_collection.json)

### Technologies used
- Spring Tool Suite (eclipse)
- Spring Boot
- JPA
- REST
- Postman
- JUnit
- MySql (workbench)
- Java

### Lessons Learned
- desc is a MySql reserved word (check for sql reserved words during database building)
- Better understanding how to implement RESTful services
