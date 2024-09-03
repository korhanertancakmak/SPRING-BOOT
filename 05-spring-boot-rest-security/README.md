# [REST API SECURITY]()

## [Overview]()
<div style="text-align:justify">

In this section, we'll get an overview of **Spring Boot REST API Security**.
In this section, we'll learn how to 

* secure a **Spring Boot REST API** 
* define users and roles 
* protect URLs based on a given role
* store users, passwords, and roles in a database using plain text 
and also an encrypted format.

We're going to cover the most common **Spring Security** tasks that you'll need on a daily basis.
Now, this is not an A to Z reference.
For that, you can see the **Spring Security Reference Manual**.
I have a link [here](http://www.luv2code.com/spring-security-reference-manual).
That'll simply redirect you to the official **Spring Reference Manual**.

Let's look at the **Spring Security Model**.
**Spring Security** defines a framework for security.
It's implemented using servlet filters in the background.
You can make use of declarative security or programmatic security.
**Spring Security** using servlet filters.
Basically, servlet filters are used to pre-process and post-process the web requests.
These filters can route the web request based on security logic,
and **Spring** provides a bulk of the security functionality with servlet filters.
Let's take a look at Spring Security with some diagrams.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-api-security/images/image01.png" alt="image01">
</div>

You have a web browser on the left, and then on the right,
you have this protected web resource like my top-secret stuff.
The browser's attempting to access your top-secret information and get the results.
Now, this is where **Spring Security** comes into play.
These **Spring Security filters** will intercept those requests,
pre-process them, and then see if the user can actually access that protected resource.
**Spring Security** will look at your application security configuration.
And then, it'll also look at the user's passwords and roles
that are in your database to see if this user's authenticated, 
and also if they're authorized to access this web resource.
And this all happens in the background, thanks to **Spring Security**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-api-security/images/image02.png" alt="image02">
</div>

Okay, **Spring Security** in action with a flow chart.
We have these **Spring Security filters**, and then, basically,
we'll say, "Hey, is this web resource protected?"
Yes. 
Then, "Is this user authenticated?"
If they're not authenticated, then we'll send them to the login form,
show that login form, and then take the user ID and password and authenticate it.
We'll check to see if the user ID and password are valid
based on the information we have stored in our system.
Then, if they pass a test, so they're authenticated,
then we check, "Is this user authorized?
Do they have authorization to access this resource?"
Now, just like at your job, right?
You have a security badge, or at your university,
you have a student ID that can get you into the main building.
However, you may not be able to access all the rooms in the building.
It all depends on your access level or your authorization role.
Just because you have a good user ID and password,
that doesn't mean you have access to everything.
There's still additional levels of security in place,
and **Spring** has support for this using security roles.
If you don't have the given role, then it says access denied,
or if you do have the given role, then it'll actually show you the resource
and then give you access to this secure site.

Okay, so let's review some security concepts.
We have this idea of authentication that basically handles checking the user ID and password
with the credentials that are stored in your app or in your database.
And then we have the idea of authorization.
Here we check to see if the user has an authorized role.
As you can see, there are two levels of security that are in place for the **Spring Security** framework.

**Spring Security** has declarative security.
Here we define our application security constraints in a configuration.
This is handled by an all-Java config using a `@configuration` class.
And the nice thing about this is that it provides separation of concerns
between application code and security code.
And then there's also programmatic security.
Basically, **Spring** provides an API for custom application coding,
and this is where you can have greater customization
for your specific application requirements.
For real-world, real-time enterprise projects, you may say,
"_Hey, **Spring Security** gives me the basics.
However, at my company, we have additional business rules
or additional logic that we need to implement._"
Well, not a problem.
You can extend the framework for doing that.
And you can plug in your own custom security implementation.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <aartifactId>spring-boot-starter-security</aartifactId>
</dependency>
```

To enable **Spring Security**, the first thing we do is we edit the `pom.xml` file.
And in this file, we'll add the dependency for the `spring-boot-starter-security`.
And by adding this dependency, spring boot will automatically
secure all endpoints for the application.
No additional coding is required at this point.
However, we'll learn about customizing the configuration a little bit later.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-api-security/images/image03.png" alt="image03">
</div>

And once we run our application, and we access our app, then our application is secured.
As a result, **Spring Security** will prompt us to log in.
Now, by default, the username is `user`,
and the generated password is in the application logs.
Now, this is just for testing.
Later on, we'll learn other techniques to customize the user ID and the password,
including storing the information in the database.
But at the moment, we're just looking at the defaults for right now.

````properties
spring.security.user.name=scott
spring.security.user.password=tiger
````

You can also manage basic configuration with **Spring Security**.
In our application.properties file, you can override the default username and password.
We simply add the configuration for **Spring Security** username
and also **Spring Security** user password.

As I mentioned earlier, there are different techniques for defining users, passwords, and roles.
You can make use of in-memory authentication.
You can also use JDBC to define users, passwords, and roles in a database.
There's support for LDAP.
You can also use your own custom plugin,
your own authentication, and authorization coding,
and there are plenty of others out there.
You can look at the reference manual for a complete list,
but in this class, we'll actually cover the in-memory authentication.
And then, we'll also cover password storage in the database using JDBC.


Now, we need to refresh our database table,
because we added a lot of things, deleted, updated, and so on.
So let's move over to MySQL Workbench and log into our ``Spring Student Account``.
And basically, we just want to run that script
that we used earlier as far as creating a table and inserting the sample data.

````sql
CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'Leslie','Andrews','leslie@luv2code.com'),
	(2,'Emma','Baumgarten','emma@luv2code.com'),
	(3,'Avani','Gupta','avani@luv2code.com'),
	(4,'Yuri','Petrov','yuri@luv2code.com'),
	(5,'Juan','Vega','juan@luv2code.com');
````

So here it is.
So this is all of our fresh data here, so we're good to go.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-api-security/images/image04.png" alt="image04">
</div>

So we have a standard baseline to work from.
And now what I'd like to do is download some starter code just to get us up and running.
So we have this file here, `00-spring-boot-rest-security-employee-starter-code.zip`, that you'll download.
So the same code you saw before,
I simply added some supporting SQL scripts for us to do some database work,
but the majority of the file here in the code is the same.

And now let's go ahead and swing over to our file system,
and I'll simply unzip this file, and it's just a basic **Maven** project.
A lot of the code is what we created before.
And now let's go ahead and import this project into our IDE.
I'll go ahead and just do a rebuild all, and I'll also go through
and just make the appropriate project settings for `spring-boot-dev-tools`.
So under `Build`, `Execution`, `Deployment`, under `Compiler`,
we check the box here to build project automatically.
And then down in advanced settings, make sure that you have the option selected
for allow auto-make to start, and then go ahead and hit `OK`.

So this is our spring-boot-crud-demo that we created in previous sections.
All of this code is the same, so we have our `DAO`, our `entity`, our `rest`, and also our `service`.
And again, we've created all this code before, there's nothing new here as far as the Java coding.


</div>

## [Basic Configuration]()
<div style="text-align:justify">

</div>

## [Restrict URLs based on Roles]()
<div style="text-align:justify">

</div>


## [JDBC Authentication]()
<div style="text-align:justify">

</div>


## [Bcrypt Encryption]()
<div style="text-align:justify">

</div>


## [Custom Tables]()
<div style="text-align:justify">

</div>