/*
User 
- has a name <"User 1 to 30">
- has an assigned (randomly generated) Resource
- has a (randomly generated) time allocated for resource usage

  Status:
    - is assigned to any resource
    - is finished in processing the resource

    Processing a resource:
    - time allocated will decrease over time
    - if time is 0 then status of the user is finished
*/

class User {
    constructor(userNumber, time, resourceNumber) {
      this.userNumber = userNumber;
      this.time = time;
      this.resourceNumber = resourceNumber;
  
      this.isFinished = false;
      this.isAssigned = false;
  
      this.process = function() {
        this.time--;
  
        if (this.time == 0) {
          this.isFinished = true;
        }
      };
    }
  }

  /*
  Resource
  - has a name <"Resource 1 to 30">
  - has a designated user

    Status
    - isAvailable: if no user is using the resource

      Process:
      - process only if resource has a user assigned
      - if time reaches 0 then resource status will be available and remove assigned user
  */

  class Resource {
    constructor(resourceNumber) {
      this.resourceNumber = resourceNumber;
      this.user = null;
      this.isAvailable = true;
  
      this.process = function() {
        if (this.user != null) {
          this.user.process();
  
          if (this.user.time == 0) {
            this.isAvailable = true;
            this.user = null;
          }
        }
      };
  
      this.assignUser = function(user) {
        this.user = user;
        this.isAvailable = false;
      };
    }
  }
  
  // Array definition
  const users = [];
  const resources = [];
  
  // Max number for generating random number
  const MAX_NUMBER = 5;

  // Generate the random numbers of users and resources
  const numberOfUsers = _generateNumber(1, MAX_NUMBER);
  const numberOfResources = _generateNumber(1, MAX_NUMBER);
  
  // Sleep function - to mimic that a process is processing in 1 second irl
  function _sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  
  // Generate random number function - mix: 1, max: based on MAX_NUMBER
  function _generateNumber(min, max) {
    return Math.floor(Math.random() * max) + min;
  }
  
  // Function to determine if all users have finished processing, if yes then end the program/loop, else continue
  function _allUsersFinished() {
    for (let i = 0; i < numberOfUsers; i++) {
      if (!users[i].isFinished) {
        return false;
      }
    }
    return true;
  }
  
  // Function to assign a user to a resource
  //  For each user that is not assigned, if the user's assigned resource is available then assign it to the user for processing
  function _assignUserToResource() {
    users.forEach(user => {
      if (!user.isAssigned) {
        if (resources[user.resourceNumber].isAvailable) {
          resources[user.resourceNumber].assignUser(user);
          user.isAssigned = true;
        }
      }

    });
  
}
  
// Function to process the resources
//  For each resource that is not available and has a user that is assigned to it then display the status
  function _processResources() {
   
    resources.forEach(resource => {
      resource.process();
      if(resource.isAvailable == false && resource.user["isAssigned"]) {
        console.log("[PROCESSING] User", resource.user["userNumber"] + 1, "Resource", resource.resourceNumber + 1, "Time Left", resource.user["time"]);
      }
    });
  }
  
  
  // Function to show the waiting users and their assigned resources
  // For each user that is not assigned, display details
  function _showWaiting() {
    users.forEach(user => {
      if (!user.isAssigned) {
        console.log("[WAITING] User", user.userNumber + 1, "Resource", user.resourceNumber + 1);
        }
      });

  
  }

  // Function to display unused or finished processing resources
  //  For each resource, if there is no user assigned to it then display details
  function _showUnusedResources() {
    resources.forEach(function(resource) {
      if(resource.user === null) {
        console.log("[UNUSED RESOURCE] Resource", resource.resourceNumber + 1);
      }
    })
  }
  
  // Function to add users in an array
  function _populateUsers() {
    for (let i = 0; i < numberOfUsers; i++) {
      let userNumber = i;
      let time = _generateNumber(1, MAX_NUMBER);
      let resource = _generateNumber(0, numberOfResources - 1);

      users.push(new User(userNumber, time, resource));
      console.log("[USER]", userNumber + 1, "[RESOURCE]", resource + 1, "[TIME]", time);
    }
  }
  
  // Function to add resources in an array
  function _populateResources() {
    for (let i = 0; i < numberOfResources; i++) {
      let resourceNumber = i;

      resources.push(new Resource(resourceNumber));
    }
  }
  
  async function start() {
    let time = 0;

    console.log('********************************');
    console.log(`Number of Users: ${numberOfUsers}`);
    console.log(`Number of Resources: ${numberOfResources}`);
   
    _populateUsers();
    _populateResources();
  
    _assignUserToResource();
    _showUnusedResources();

    console.log(time, "second(s) has elapsed");
    console.log('********************************');

    // Main Loop
    while (!_allUsersFinished()) {
      _assignUserToResource();
      _processResources();
      _showWaiting();
      _showUnusedResources();
      time++;
      console.log(time, "second(s) has elapsed");
      console.log('======================================================');
      await _sleep(2000);
    }
  
    console.log(`It took ${time} second(s) to finish!`);
  }
  
  start();