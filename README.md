# Hello Fresh API Framework
A Restful framework to run and validate API Tests.
## Components
This is a Hybrid Framework which is a combination of **Data Driven Development** and **Test Driven Development**. And it is written on the concepts of **BDD**.

This Framework key components are:

**Payload Parser**:
This contains Java Objects, which with the help of **ObjectMapper** can be converted to JSON or any JSON payload can be converted into these java objects.
It can be found at: ```src/main/java``` folder under package ```com.tests.support```

**Log4J**:
Every Test case needs checks as in what steps are going on during execution so that code can be easily debug during fail test scenario and user has a fair information as in how the test cases are flowing.

- After execution of tests the logs are saved in ```application.log``` file, which can be found in the ```root``` folder
- Properties can be found at: ```src/main/resources folder```, name is ```log4j.Properties```
```
e.g Use like this in your test case:
    logger.info("Logging in System"); //can be seen in console output
```
**Extent Report**:
- To generate Human readable Report where you can see clearly see which test cases passed and which one failed.
- Implementation can be found at folder ```src/main/java``` and package ```com.tests.ExtentReportListener```
- After every run an ```Extent.html``` gets created in ```test-output``` folder, go the folder path and double click on it view the report.
- ```ITestReporter``` listener has been used to implement it and this listener is being used in ```testng.xml``` which can be found in the root folder

**config.properties**
To make the framework more flexible which can be run on different **environments(URL)** and different room ids, I have created this file where you can provide the environment(URL) and the room id on which you want to test.

**Base Class**
- There are some functionities such as reading config.Properties, logger initialiation etc of the framework which needs to be extended to all the test cases for that Base Class (```TestBase```) has been used.

**Parallel Mode**
Parallel functionality has also been provided to save time in test execution.
- ```TestNG``` will run all the methods in the same class in the same thread, but each class will be run in a different separate thread.

## Getting Started
- Clone this repository.
- Import the project in your IDE as Maven Project

### Prerequisites

```
- You need a IDE such as Eclipse.
- Java v1.8
- Maven should be installed on your system.
- Other than that every other dependencies are covered in pom.xml (it will automatically download the other dependencies)
```


## Running the tests

Running the tests is very easy In eclipse or any other IDE, one just has to go to ```root``` folder and double click on ```testng.xml``` once it opens right click on the open file and ```Run As -> TestNG Suite```

### How it Works
Once test starts it goes like this:
- It goes to the class on which ```@Test``` is defined
- It checks whether the class has extended some other classs, ```TestBase``` in our case, goes to that class initialises the **Environment(BaseURI)**.

```---GIVEN---```
- Now depending on the data required it adds header, query param, body (if required) to the request.

`---WHEN---`
- Once it has all the data requried it starts performing the required actions with the help of GET, PUT or POST.

`---THEN---`
- Once all the actions have been performed its time for validating the test case i.e. do the `assertions`. If all the assertion passes the test case is marked as pass otherwise it is marked as failed.

**NOTE:**
For every test case logs are generated and stored in `application.log` and report is generated which is stored in `Extent.html`

## Built With

* [TestNG](https://testng.org/doc/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [RestAssured](http://rest-assured.io/) - Used to automate API

## Author
* **Rahul Raman** - [rahulraman](https://github.com/rahul-raman)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
