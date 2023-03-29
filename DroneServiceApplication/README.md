## Drones

[[_TOC_]]

---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task.

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach.

---

### Requirements

While implementing your solution **please take care of the following requirements**:

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### How to build

#### Requirements

- Java 8
- Java IDE (IntelliJ IDEA)
- H2 database (Optional you can use pgsql database)
- Postman(For testing )

### Steps by step for building and runing the project locally

- Clone  the link git clone https://oauth:glpat-PWtW7Pkwy2rePnQ5yWy4@gitlab.com/musala-coding-tasks-solutions/oluwatobiloba-agbeja.git

- Open the cloned project in intelliJ

- Go to maven the update Project to update all the maven dependencies

- Maven Build the project and run

- Before running, you can run the JUnit test cases to assert that everything is working correctly (I have included the JUnit tests)

---

### Testing the API
#### assumptions made for the purpose of this API design are:-

- Once the Medication is loaded to a specific drone it cannot be loaded to another drone at the same time.
- A drone can contain more than one medication as long as the sum of medication weight is not more than the drone weight and its state is still LOADED
- a drone with the same serial number can not be registered
- 

Note: the ContentType is application/json


----
- **Registering a drone** localhost:8080/api/v1/drone/register
  The payload should be in json format like this

[![succesful-register-drone-request.png](https://i.postimg.cc/SNxKPLWL/succesful-register-drone-request.png)](https://postimg.cc/PN0hpwVN)

Drone registration can fail if drone already exist
[![failed-existing-register-drone-request.png](https://i.postimg.cc/9fSwTZp2/failed-existing-register-drone-request.png)](https://postimg.cc/3yXwQyVf)

or when an invalid drone parameter is set
[![failed-params-register-drone.png](https://i.postimg.cc/c4JGnsRc/failed-params-register-drone.png)](https://postimg.cc/kDLHrPhV)
---
- **Checking available drones for loading;**


Before loading a drone with Medication you can first check the available drones to confirm that the drone is not in use

**localhost:8080/api/v1/drone/checkAvailableDrones**

[![available-drone-request.png](https://i.postimg.cc/DZ4d0XNJ/available-drone-request.png)](https://postimg.cc/dkvTSLww)



---
- **Loading a drone with medication items;**

**localhost:8080/api/v1/drone/load**

The payload will have the following fields

- serialNumber is the unique serial for the drone being loaded
- medication codes: previously saved medications unique codes
- medications: medications can be saved form here and the drone automatically loads it as well

the Medication items to be loaded for testing are code : **ICU14**

- The serialNumber is the unique serialNumber a drone that you register

[![succesfull-load-drone-request.png](https://i.postimg.cc/BQ42D67z/succesfull-load-drone-request.png)](https://postimg.cc/9zg4HWhP)
---

loading a drone with wrong medication data
[![load-drone-medication-code-validation.png](https://i.postimg.cc/SK3WzfHL/load-drone-medication-code-validation.png)](https://postimg.cc/Ny8y3msL)

loading a drone with a wrong medication code throws an error as well so is overweighting the dronw
[![load-drone-medication-validation.png](https://i.postimg.cc/fyjXpny5/load-drone-medication-validation.png)](https://postimg.cc/8fcjfYhr)
---
**Checking loaded medication items for a given drone;**

**localhost:8080/api/v1/drone/getDroneMedications?serial_number=QWOE2930421**

- Check which medication item is loaded to a specific drone.

[![drone-medication-request.png](https://i.postimg.cc/DfbBy9Vj/drone-medication-request.png)](https://postimg.cc/HjdQ6PkX)
---

- **Check drone battery level for a given drone;**

**localhost:8080/api/v1/drone/getDroneBatteryLevel?serial_number=QWOE2930421**

[![drone-battery-level-request.png](https://i.postimg.cc/Qx14SR09/drone-battery-level-request.png)](https://postimg.cc/QHNJdnLh)
---

Also periodic checks for drone battery is done and log on the console every 30 min

:scroll: **END** 
