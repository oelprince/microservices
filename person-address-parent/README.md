## Microservices Person Address Prototype

### Description

The prototype demonstrate three main entity Microservices and one query Microservice. Person, address and credit services that are implemented using event driven development and design patterns; CQRS, Saga, and Event Sourcing.

### Scenarios

#### Scenario 1 - Update data across microservices without using 2PC (two phase commit) using event sources and Saga patterns

Execute run.sh script to start docker composer and microservice application server:

![Execute run.sh](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot1.png "Execute run.sh")

Logs showing 3 Kafka configured topics person, address and credit.

![logs showing topics](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot2.png "Logs showing 3 topics person, address, credit.")

Creating new Person request.

![create new person request](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot3.png "Creating new Person request.")

Logs showing create person sequence of events.

![logs showing create person sequence of events](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot4.png "Logs showing create person sequence of events.")

Create new credit with score more than 600 (good credit).

![create new credit with score more than 600](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot5.png "Create new credit with score more than 600.")

Create new address.

![create new address](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot6.png "Create new address.")

![create new address](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot7.png "Create new address.")

![Update address with person id](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot8.png "Update the new address with person id.")

![logs show update address with person id sequence of events](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot9.png "Logs show update address with person id sequence of events.")


#### Scenario 2 - Showing validation that rejects update between microservices using event driven development, which create compensating transactions

![create a person with credit less than 600.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot10.png "Create a person with credit less than 600")

![create address relation ship.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot11.png "Create address relation ship.")

![logs show credit reject, which triggers compensating events.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot12.png "Logs show credit reject, which triggers compensating events.")


#### Scenario 3 - Resiliency between microservices events using event sourcing

![Create new person](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot13.png "Create new person.")

![create good credit for the new person](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot14.png "Create good credit for the new person.")

![shutdown person service.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot15.png "Shutdown person service.")

![Create new address.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot16.png "Create new address.")

![Update address with new person id.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot17.png "Update address with new person id.")

![logs shows that after bring back person service all events replay and commit.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot18.png "logs shows that after bring back person service all events replay and commit.")

![person updated with new address id.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot19.png "person updated with new address id.")


#### Scenario 4 - Enable query that span across services using CQRS

![Invoking person address query service will show a view of person and address aggregated data.](https://raw.githubusercontent.com/oelprince/experiments/feature/addingqueryservice/screenshots/screenshot20.png "Invoking person address query service will show a view of person and address aggregated data")

