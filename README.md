# Super Simple Stocks
Super simple stocks is an application to manage trades on a set of stocks and it's a technical test as part of 
the hiring process for a very important company.

### 1. Assignment Description

##### Requirements

1.	Provide working source code that will:

    a.	For a given stock:
    
        i.    Calculate the dividend yield.
        ii.   Calculate the P/E Ratio.
        iii.  Record a trade, with timestamp, quantity of shares, buy or sell indicator and price.
        iv.   Calculate Stock Price based on trades recorded in past 15 minutes.

    b.	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

##### Constraints & Notes

1.	Written in one of these languages:
    
    * Java, C#, C++, Python.
    
2.	No database or GUI is required, all data need only be held in memory.

3.	Formulas and data provided are simplified representations for the purpose of this exercise.

##### Global Beverage Corporation Exchange

Stock Symbol  | Type | Last Dividend | Fixed Dividend | Par Value
------------- | ---- | ------------: | :------------: | --------: 
TEA           | Common    | 0  |    | 100
POP           | Common    | 8  |    | 100
ALE           | Common    | 23 |    | 60
GIN           | Preferred | 8  | 2% | 100
JOE           | Common    | 13 |    | 250



### 2. Solution

##### Architecture and Assumptions

In a _**S**ervice **O**riented **A**rchitecture (**SOA**)_, a software application is designed 
by defining components, which provides services to other components in other applications. Typically, 
those services are available to be consumed using a network through some specifics communication 
protocols. By definition a _**service**_ is a self-contained component, which offer a specific 
functionality and could be designed to perform one or more operations. This make possible reuse 
code just changing the way the service interoperates with other services.

Following the SOA approach, the solution for the assignment is designed to provide the service 
_**SimpleStockService**_, which has operations to calculate the dividend yield, P/E Ratio, 
Stock Price and record trades for a given stock. Besides, the service provides an operation 
to calculate the GBCE All Share Index for all stocks supported by the Super Simple Stocks application. 
Providing this service, all the requirements of the assignment are met.

Responding to one of the constraints, the implementation of the solution is written in JAVA 
language using Apache MAVEN as a software project management tool and Spring Framework. 
As it is widely known by the JAVA developer community, MAVEN 
provides some powerful features in software development, someone of them as: 
A easy and flexible way to build all artifacts in the project, generates quality reports, 
execution and reporting of unit test, support for continuous integration development, etc.

Spring give a powerful pattern as Inversion of Control, which allows us to have a cleaner code, 
aspect oriented programming AoP, etc. Although, Spring is used mainly as object container, 
for example to inject the information of the stocks supported by the application and to configure 
the data for the unit test, include other features of spring in the application is an straightforward 
task. Enable the use of annotations, scan spring components in the code and place holder for 
the properties are already done.

![Super Simple Stocks - Architecture of Solution](https://github.com/jainebri/Super-Simple-Stocks/blob/master/super-simple-stock/src/main/resources/images/super-simple-stocks-architecture.png "Super Simple Stocks - Architecture of Solution")

Although the approach is SOA, the implementation of the service is built as _**java library**_ as
a jar artifact named _**super-simple-stock-0.0.1-SNAPSHOT.jar**_. Because there are no explicit 
integration requirements, the simplest decision was to create a java library, which could be 
integrated in all the JAVA technologies. We don't know if the requirements are oriented to integrate 
the functionality within an existing web application just to complete the app, or they are intended to 
be published as web services to be consumed by external systems or they will be integrated as part of 
a SOA services choreography in a more complex system.

The java library for this assignment is the more flexible solution with the current requirements. 
For example, when an integration need shows up, specifying that an external stock market system wants 
to know the information for the stocks in the Super Simple Stocks app. One option could be to publish 
the service in the library as a _**RESTful Web Service**_. 

The design of the library will allow us to respond to this requirement very fast. To build a RESTful web 
service in Spring, the HTTP requests should be handled by a resource controller. A resource controller 
is a simple POJO (Plain Old Java Object) identified by the **@RestController** annotation. 
Each operation in the web service should be mapped with a method in the controller, which is accomplished
using the **@RequestMapping** annotation. In the solution architecture, the resource controller class
should be added in a new possible web service layer for integration purpose. The next snippet of code 
shows the resource controller to new 
integration requirement:

```java
package com.jpmorgan.stocks.simple.integration.ws.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleStocServiceController {

    @RequestMapping("/calculateDividendYield")
    public calculateDividendYield(@RequestParam(value="stock", defaultValue="") String stockSymbol) {
		// Get the simple stcok service from the factory
		SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();
		
		// Delegates the work to the java library
		return simpleStockService.calculateDividendYield(stockSymbol);
    }
}
```
Quite simple. Related to deployment aspects, we should make some changes in the maven 
configuration if we want to generate a war file instead a jar, for example, but those changes
are small. So, with maven as project management tool we have many possibilities to generate 
all the artifacts, as we need it.

##### Technical Design

The first technical decision in our implementation strategy is to provide a unique access to all services in the application. This is accomplished by defining the component **SimpleStocksServicesFactory**, which implements the factory pattern and acts as the interface to create all services in the Super Simple Stocks application. The services built by the factory are considered as _**border services**_ and they will be the entry point to the business functionalities for all the external applications that wants to integrate with the stocks library. Each border service is mapped to one unique method in the class SimpleStocksServicesFactory to creates the corresponding service instance, using as helper the service _**SpringService**_. This service is responsible to load the Spring context making available all services, architecture components, business model objects and utils defined to support the business functionalities. Additionally, provides a generic mechanism to gets all the beans configured in the Spring context.


![Super Simple Stocks - Technical Design Modeling](https://github.com/jainebri/Super-Simple-Stocks/blob/master/super-simple-stock/src/main/resources/images/super-simple-stocks-model.png "Super Simple Stocks - Technical Design Modeling")

For this technical test, the factory component just has one method _**getSimpleStocksService**_, that creates a singleton instance of the **SimpleStocksService**, which is the main service in the app and contains all method for the calculations. The class SimpleStocksServicesFactoryImpl is the implementation of the factory and implements a thread safe singleton pattern proposed by Bill Puigh. The next snippet of code ilustrates how to use the factory to create a service:

```java
SimpleStocksService simpleStockService = SimpleStocksServicesFactory.INSTANCE.getSimpleStocksService();
```

As all services are configured in the Spring framework, there are many possibilities to design and build the structure of the services, but for this application we have defined that the border services only can use the services in the backend layer. So, The service _**StocksEntityManager**_, is injected by IoC into the border service SimpleStocksService. As one of the constraints of the technical test is 'no database', the entity manager service represents the persistence layer of the application holding all data in memory and providing the methods to recover and store socks and trades in the app. The SimpleStocksService use the entity manager to simulate the database operations for the stocks application.

Finally, the SimpleStocksServiceImpl implements all the functionalities coding the bussiness rules  to make the calculations of the dividend yield, P/E Ratio, stock price, and GBCE All Share índex.

##### Unit Test

To test the code of the technical test, it has been used Test Driven Approach provided by maven, coding some junit test for each requirement. Additionally, it has been coded junit test to verify the availability of the services as the factory service and the simple stock service.

##### Try Yourself

The code for the technical test was built as an Eclipse project with a embedded version of Maven. To compile the code, download the folder super-simple-stock and import the project in Eclipse as a maven project. Alternatively, by console run the next command, working in the folder super-simple-stock:

     maven clean install

This will compile the code and will execute the unit test.







 

 
