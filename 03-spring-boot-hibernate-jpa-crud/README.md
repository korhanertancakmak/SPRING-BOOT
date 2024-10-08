# [Hibernate/JPA CRUD]()

## [Overview]()
<div style="text-align:justify">

In this section, I'm going to give you an overview of **Hibernate** and **JPA**.
We'll cover the following topics:

* First off, we'll answer the question, what is **Hibernate**?
* Then we'll go through and look at the benefits of using **Hibernate**.
* We'll also find out what is **JPA**?
* And also look at the benefits of using **JPA**.
* And, finally, I'll show you some real quick code snippets
on how to make use of **JPA** in your application.

Alright, first off, what is **Hibernate**?
**Hibernate** is a framework for persisting or saving Java objects into a database,
it's a very popular framework that's used by a lot of enterprise Java projects.
Basically, at a very high level you'll have your Java application,
it'll make use of this **Hibernate** framework,
and you can use it for saving and retrieving data from the database.

Now, what are the benefits of using **Hibernate**?
Well, **Hibernate** handles all the low-level **SQL** code,
so it actually minimizes the amount of **JDBC** code that you have to develop.
**Hibernate** can handle the Object-to-Relational mapping (ORM),
and it makes it really easy to create apps
to store and retrieve objects from the database. 
Well, how does it work?
Again, **Hibernate** provides something called the Object-to-Relational mapping,
or you'll hear the buzzword or keyword called `ORM`.
As a developer, all you need to do is tell **Hibernate** 
how your Java class or object maps to the data in the database.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image01.png?raw=true" alt="image01">
</div>

In fact, you'll map your Java class to a given database table.
In this example, on the far left we have our Java class,
that's a student, it has four fields, ID, first name, last name, and email;
and notice how the field's first name and last name are spelled using **CamelCase**.
We have the **Hibernate** framework in the middle,
and on the far right we have the actual database table.
In this example, we have a table called **Student**.
It has ID, which is the primary key,
and there's the first name and last name,
and note its first_name and last_name.
We also have a field for email address.
What we'll do here is that this Java class will map to this given table,
and we set up the one-to-one mapping between the fields
and the actual columns in the database.
You can set up this mapping via a configuration file using xml,
or using Java annotations.
In this course will make use of Java annotations,
and I'll cover those technical details in some of the upcoming sections.

Now, what is **JPA**?
JPA is the `Jakarta Persistence API`,
previously known as the Java Persistence API.
It's the standard API for Object-to-Relational mapping, or ORM,
it's only a specification, defines a set of interfaces,
and it requires an implementation to be usable.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image02.png?raw=true" alt="image02">
</div>

Now let's take a look at some **JPA** vendor implementations.
So we start with the **JPA Spec**,
it's just a list of interfaces, and we need an implementation.
One implementation here is **Hibernate**.
Just like with Java coding, they take those interfaces,
and they provide an implementation of those given interfaces.
And then there's another implementation here, **EclipseLink**.
Again, they have their own implementation of the **JPA** specification.
Now, there are other **JPA** vendors out [there](https://www.love2code.com/jpa-vendors),
you can go to the site, and I'll give you a list of those
**JPA** vendors that are out there.
But I'll go ahead and tell you
that **Hibernate** is probably 
the most popular implementation of the **JPA** specification, 
and it's also the default implementation used in **Spring Boot**.

Now, what are the benefits of using **JPA**?
By having a standard API, you're not locked into the vendor's implementation,
so you can maintain portable, flexible code 
by simply coding to the **JPA spec** or the interfaces.
So, theoretically, you can switch vendor implementations.
For example, if vendor **ABC** stops supporting their product,
then you could switch to vendor **XYZ** without any vendor lock-in, 
which is a good thing.
And the reason for this is that 
you're coding to the actual **JPA** specification or the standard API.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image03.png?raw=true" alt="image03">
</div>

Let's take a look at a scenario here of swapping vendor implementations.
We have our application, `MyBizApp`, that's the code that we're creating,
and we're writing our DAO code based on the **JPA spec**.
In version one of our project, we're using **EclipseLink**,
and then maybe for version two of our project
we want to move to a different implementation.
Again, I'm totally making this up,
but say, for example, EclipseLink is no longer around
or there are some issues with that given vendor implementation.
Well, we can easily swap in another JPA vendor implementation,
and we can swap in **Hibernate**.
So it's there up and running.
We pretty much have minimal code changes to make
because we're writing code to the standard specification.
We simply have to change our configuration
to tell it which vendor implementation to use,
and that's basically it.
So that's the main advantage here of using **JPA**
of coding to the standard API for Object-to-Relational mapping.

```java
// create Java object
Student theStudent = new Student("Paul", "Doe", "paul@luv2code.com");

// save it to database
entityManager.persist(theStudent);
```

Now let's take a look at a quick example on how to save a Java object with **JPA**.
The first thing we do is create the Java object,
and this is just really standard, basic Java here.
We simply use the **new** keyword.
And that's the first name, last name and email address.
Then we save this object to the database,
and so here we make use of the **entityManager**,
which is a special **JPA** object or **JPA** helper object.
We say `entityManager.persist`, and then we pass on our object.
And what happens in the background is that **JPA** will take the Java object
based on those mappings that we defined earlier,
and JPA will take that information and store it in the appropriate table
in the appropriate columns, and **JPA** will do all the work for you.
And, really, behind the scenes **Hibernate** is an implementation of **JPA**
so really, really **Hibernate** would do all the work for you,
but you kinda understand what I'm saying here at a very high level of talking about **JPA**.
This is all perfect and really simple.
If you remember in the old days of **JDBC**,
you would have to manually write the **SQL** code.
So you'd have to manually set those values,
manually execute the **SQL** statement, and so forth.
But here, **JPA**, with the help of **Hibernate**,
does all the work for you in the background.
So, as you can see, it's really simple here to save a Java object using **JPA**.

```java
// create Java object
Student theStudent = new Student("Paul", "Doe", "paul@luv2code.com");

// save it to database
entityManager.persist(theStudent);

// now retrieve from database using the primary key
int theId = 1;
Student myStudent = entityManager.find(Student.class, theId);
```

Now that we have something in the database, how do we retrieve it?
Well, there's a number of different options on how to retrieve objects,
but I'll show you here a very basic example by making use of the primary key.
Some of the coding here that you see is from what we had from the previous code,
and now I'm going to use this information to retrieve data 
from the database using the primary key of one.
And in this example, I know that our database was empty,
and we knew that the first item will have the primary key of one,
so I can use this `ID`.
I'll show you other techniques on how to retrieve objects later.
Here, I'll make use of this `entityManager.find`,
and I tell it what I want to get, a `Student.class`,
and I give the `ID` or the primary key.
Behind the scenes, **JPA** will say,
"_Okay, let me go look at this table called **Student**,
and let me find the students whose primary key matches this **ID**._"
It'll find that student object, and return it to us, and that's it.
And that's basically how we retrieve a Java object from the database.

Okay, so let's look at a scenario where you wanted to say,
"_Hey, I want to retrieve all the students,
not just one student but a list of students._"
So that's fine, we can set up a query and get all the students.

```java
TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

List<Student> students = theQuery.getResultList();
```

**JPA** has a language for queries,
here I'll use `entityManager.createQuery` ,
and it'll basically give us a list of all those student objects.
And then I can actually retrieve those objects
here from the query by saying `theQuery.getResultList`.
So behind the scenes it'll do the query for us,
give us a list of objects and then return it, and we can make use of it.
Again, notice we didn't have to do any low-level **SQL** coding or **JDBC** coding,
we simply added code here from using this in **entityManager**,
and we could actually retrieve those objects.
And so here we made use of a very basic query,
actually makes use of something called the **JPA Query Language**,
and we'll talk more about this in later sections,
but at a very high level it allows you to query,
and it'll give you all the students from a given database.
And you can also set up special wear clauses using `alike` conditions, and so on.
I'll share examples of that later on.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image04.png?raw=true" alt="image04">
</div>

Alright, so the nice thing to hear is
that I showed you a very high-level example here of using **Hibernate JPA**, 
with some real quick code snippets.
In the following sections we'll get into more technical details,
we'll dive into each one of these topics.
I'll show you how to set up a connection to the database,
how to make use of the **JPA** `EntityManager`,
then we'll walk through code examples for doing call the major `CRUD` features.
And when I say `CRUD` I mean **create**, **read**, **update**, and **delete**,
and we'll cover all of that in the following sections.
And then we'll wrap everything up with a small project,
so you can actually see how all of this comes together.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image05.png?raw=true" alt="image05">
</div>

I'm going to discuss the relationship between **Hibernate/JPA** and **JDBC**.
Now, a frequently asked question, how does **Hibernate/JPA** relate to **JDBC**?
Well, **Hibernate/JPA** actually uses **JDBC** for all database communications.
So really, **Hibernate/JPA** is just another layer of abstraction on top of **JDBC**.
So when your application uses the **Hibernate/JPA** framework,
your app will actually store and retrieve the objects using the **JPA API**.
**Hibernate/JPA** does a lot of the low-level work for you, but in the background,
it all goes through the standard **JDBC API**.
When we get into some of the later sections of the course,
we'll actually configure **Hibernate/JPA** to talk to your database. 
We'll actually configure it using a **JDBC** driver,
and we'll cover all the technical details of that in the following sections.
But **Hibernate/JPA** makes use of **JDBC** in the background 
for communicating with the database.
</div>

## [Setting Up Database Table]()
<div style="text-align:justify">

In this section, we'll discuss the **MySQL** database.
In this course, we'll use the **MySQL** database for our `CRUD` projects.
The **MySQL** system includes two parts:

* MySQL Database Server
* MySQL Workbench.

Now, the **MySQL Database Server** is the main engine of the database.
This is where we store the data for the actual database,
and it also has support for `CRUD` features on the data.

And then the **MySQL Workbench** is a client GUI for interacting with the database.
We can use the **MySQL Workbench** for creating database schemas and tables.
We can execute **SQL** queries and retrieve data,
perform inserts, updates, and deletes on the data,
and also administrative features, such as creating users and so on.
So really, it's just a GUI interface for interfacing or interacting with the database,
and we use that a lot during this course.

Now, there are two things that you'll need to do
before you continue on with the sections is that 
you'll have to install the **MySQL** software.
And step 1: You have to install the **MySQL Database Server**.
So you can go to the URL that I have [here](https://dev.mysql.com/downloads/mysql/).
They'll give you installation instructions for your appropriate operating system.
And then in step 2: You'll install the **MySQL Workbench** and again, 
using the link [here](https://dev.mysql.com/downloads/workbench/).
Now remember, the **MySQL Database Server**'s the domain engine,
the **MySQL Workbench** is the actual GUI interface for accessing the database.
Use these URLs, download the software, 
and then you'll be ready for the rest of the section.

Let's set up our database table.
Now we'll make use of two database scripts for getting started.
So we'll have this folder called `00-starter-sql-scripts`.
There's going to be two scripts here:

* `01-create-user.sql` 
* `02-student-tracker.sql`

These are starter files that you can download,
I'll give you details on that in a second,
and I'll also discuss what each of these files does.
Now, the first file here, `01-create-user.sql`,
this actually creates a new **MySQL** user for our application, 
and it's real simple here.
The user ID is going to be `springstudent` and the password will be `springstudent`.
And then we have the second file here, `02-student-tracker.sql`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image06.png?raw=true" alt="image06">
</div>

This will actually create the database table for our application called **student**,
and it'll create these four fields here:

* ID
* first_name
* last_name
* email

In the next section, I'll show you how to download these starter files,
and also I'll show you how to execute these files.

Now let's go ahead and start up **MySQL Workbench**.
And remember, this is the GUI for accessing the **MySQL database**.
I'll go ahead and select my root connection here.
And I'll log in using the root password specified during the installation process.
And I'll go ahead and open up the first `01-create-user.sql` script.

```sql
-- Drop user first if they exist
DROP USER if exist 'springstudent'@'localhost';

-- Now create user with prop privileges
CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';
```

So in a nutshell, this creates a new user, 
the user ID of `springstudent` and the password of `springstudent`.
And I can hit the little gold lightning bolt at the top, 
and it'll execute this statement.
And basically it went through and created the actual user.
Now, if I click on the administration tab over on the left-hand side and select users and privileges,
then we should see this new account that was just created, `springstudent`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image07.png?raw=true" alt="image07">
</div>

And now a bit more about this SQL here is that we've said `CREATE USER`,
user id `springstudent`, `IDENTIFIED BY` `springstudent`.
That's the password.
All right, so now let's go ahead and move out or close this connection.
And then let's go ahead and create a new connection for this `springstudent`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image08.png?raw=true" alt="image08">
</div>

So I'll hit on the plus here for connection.
I'll give a connection name, I'll just call it `springstudent`, and the username `springstudent`.
Let's go ahead and click on Test Connection.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image09.png?raw=true" alt="image09">
</div>

And then we'll enter the password of `springstudent`.
And now we have this new connection with `springstudent`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image10.png?raw=true" alt="image10">
</div>

And that's the one that we'll use in this course.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image11.png?raw=true" alt="image11">
</div>

So let's go ahead and use this connection to go ahead and connect to MySQL.
Let me click on the tab for **schemas**.
At the moment, we only have one **schema** for sys.
That's the system schema used internally by MySQL.
Let's go ahead and open up our SQL script.
And I'll open up the `02-student-tracker-sql`.

```sql
CREATE DATABASE  IF NOT EXISTS `student_tracker`;
USE `student_tracker`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
```

And behind the scenes, this basically creates the database schema for us called `student_tracker`.
And then it'll actually create a table for us called **student** that's going to have four columns here.
Id first name, last name, and email.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image12.png?raw=true" alt="image12">
</div>

Let's go ahead and hit the gold lightning bolt here to execute it.
And that looks okay.
Over on the left-hand side, let's hit the little arrows here to refresh, or reload the schemas.
And we should see this new schema.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image13.png?raw=true" alt="image13">
</div>

So this is great.
So we have `student_tracker`, that's our database schema name.
And then we have our table here, **student**.
And then we can take a look at the columns here.
For this table, we have these four columns.
Id, first name, last name, and email.
And we can also do a quick query on this table.
And nothing's there right now, right?
Because we only created the table.
We didn't actually insert any data yet, but don't worry, 
we'll actually write some Java code for inserting data into this database,
but at least we have the basic setup.
</div>

## [Setting Up Spring Boot Project]()
<div style="text-align:justify">

Now with automatic data source configuration in **Spring Boot**, 
**Hibernate** is the default implementation of **JPA**, 
and **EntityManager**'s a main component that we'll use for creating queries and so forth.
Now, remember that **EntityManager** is from the Jakarta Persistence API (JPA).

Now a bit more here on the data source configuration,
based on the configs, **Spring Boot** will automatically create the beans 
for **DataSource** and **EntityManager**,
and then we can inject these into our app.
For example, you can inject the **EntityManager** into your **DAO**,
and we'll actually do that soon.

To help us get started, we'll set up our project with a **Spring Initializr** 
[website](https://start.spring.io/).
We'll add the dependencies here for the **MySQL Driver** (`mysql-connector-j`),
and also for the **Spring Data JPA** (`spring-boot-starter-data-jpa`).
Now, in terms of the auto-configuration,
**Spring Boot** will **automatically configure** your data source for you 
based on the entries from our **Maven** `pom` file.
We'll add an entry here for our database (JDBC) driver, `mysql-connector-j`, 
and also for **Spring Data (ORM) JPA**, `spring-boot-starter-data-jpa`.
And also, **Spring Boot** will read the database connection information
from the `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

Let's take a quick look at the `application.properties` file.
We'll have three key entries here.
One for the data source URL, the username, and the password.
And this will use the database schema that we created
a little bit earlier, in some of the earlier sections,
we set up that database schema for `student_tracker`,
so that's the URL for connecting to the `student_tracker`.
And then we make use of that username and password,
`springstudent`, `springstudent`.
We created that also in one of our earlier sections.
Now, one thing to be aware of here is that there's no need to give the **JDBC** driver class name.
**Spring Boot** will automatically detect this based on the data source URL.

Now, what we'll do here is we'll actually create a **Spring Boot** command line application.
This will allow us to focus on the **Hibernate/JPA** coding.
Later in the course, we'll apply this to a **CRUD REST API**.
So we'll start small, with just a command line app, 
and then we'll move it to a **CRUD REST API** later in the course, 
so a lot of good things are coming on the **REST API** portion, 
but at least here let's just kind of start with the basics here with **Hibernate/JPA**.

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(String[] args) {
        return runner -> {
            System.out.println("Hello world");
        };
    }
}
```

Now here's the basic layout here for our command line application.
So we have our normal **Spring Boot** application, we have the normal _main_ method.
Then we'll have this `@Bean` here where we'll create this _CommandLineRunner_.
Now this _CommandLineRunner_ is from the **Spring** framework,
and it's basically a hook that allows us to execute code
after the **Spring Beans** have been loaded into the application context.
And then notice here in the coding we have return _runner_, and we do have _println_.
This little code's a Lambda expression,
and this is where we can basically add our own custom code.
At the moment, I'm simply saying "`Hello world`"
but later on we'll actually add some **DAO** code here for interacting with the database.
So at the moment, we're simply just kind of setting up the infrastructure, setting up the framework,
and then we'll actually add more code to this later on.
Let's go ahead and get this **Spring Boot** project set up,
just so we can get this piece in place.

Let's get started with creating our Spring Boot project.
So on a web browser, let's go to the Spring Initializr [website](https://start.spring.io).
At the website, in the project section, choose **Maven**.
Ensure your language is selected as **Java**.
And you can choose the latest Spring Boot version.
Avoid the snapshot versions, choose the latest released version.
Down in the project metadata, let's go ahead and set up our group as `com.luz2code`.
And then for artifact ID, I'll call it `cruddemo`.
Over in the dependency section, let's go ahead and add a new dependency here for the **MySQL** driver.
And also, let's add a dependency here for a **Spring Data JPA**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image14.png?raw=true" alt="image14">
</div>

All right, and just as a checkpoint, make sure you have these two dependencies
as I have shown here on the screen.
All right, let's go ahead and click on `Generate` and get this project created for us
and download it to our system.
We have this `cruddemo.zip`.
Let me swing over to my file system here.
So I'll move into this downloads'folder.
That's where I have this `cruddemo.zip` that was downloaded.
I'll unzip it.
I'll simply copy this folder.
And then I'll move up to my `dev-spring-boot` directory.
I'll move into this `03-spring-boot-hibernate-jpa-crud`.
And in this folder here, I'll actually paste that item.
And then I'll simply rename this just to kind of follow the numbering scheme that I have here.
And I'll call it `01-cruddemo-student`.
And inside this folder, we have our **Maven** `pom` file and all this other good stuff.

Now, let's go ahead and open this in IntelliJ.
Now let's get started with creating our command line app.
I'll open up my main **Spring Boot** application here.

```java
package com.luv2code.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
}
```

And I'll define this new @Bean here for **CommandLineRunner**.
This is for creating a command line application.

```java
package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("Hello World");
		};
	}
}
```

And again, remember, the **CommandLineRunner** is from the **Spring Boot** framework.
This little snippet of code here will be executed after the **Spring** beans have been loaded,
and we can use it within this given method.
I'll make use of this return runner.
This lambda expression, and I'll simply say, `hello world`.
So again, this is a Java lambda expression.
It's simply like a shortcut notation for providing an implementation of the **CommandLineRunner** interface.
Just some really nice shorthand notation for creating an implementation for **CommandLineRunner**.
And also, just as a reminder here, this will be executed after the **Spring** beans have been loaded,
and at the moment, we simply just have `Hello world`.
That's our custom code.
But later on, we'll actually make use of some beans that we've created,
and we'll use them within this given setup.
So right now, like I said earlier, we're just kind of setting up the infrastructure,
just the framework, and we'll kinda expand on this later.
But at least we have this piece set up and ready to go.

Now we need to do one more thing before we move forward,
and that's setting up our `application.properties` file.
In our `application.properties`, we need to add our **JDBC** database connection information.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

We need to add an entry here for our data source URL.
So we give the property name of `spring.datasource.url` and then we give that actual URL.
And this will connect to our `student_tracker`.
And that's that database schema that we set up in previous sections.
We'll need to set up another property here for the `spring.datasource.username`
and recall we have a user called `springstudent`.
I'll do a quick copy, paste here, and I'll simply update the property name
for `spring.datasource.password`.
And again, remember our password's the same as our username, purely for academic purposes.
So now at this point, we can go ahead and run this application and test it out.

```html
2024-05-23T19:36:02.845+03:00  INFO 41092 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
2024-05-23T19:36:03.129+03:00  INFO 41092 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-05-23T19:36:03.139+03:00  INFO 41092 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 6 ms. Found 0 JPA repository interfaces.
2024-05-23T19:36:03.389+03:00  INFO 41092 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-23T19:36:03.697+03:00  INFO 41092 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@3c3a0032
2024-05-23T19:36:03.698+03:00  INFO 41092 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-05-23T19:36:03.741+03:00  INFO 41092 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-05-23T19:36:03.770+03:00  INFO 41092 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-05-23T19:36:03.786+03:00  INFO 41092 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-05-23T19:36:03.998+03:00  INFO 41092 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-05-23T19:36:04.266+03:00  INFO 41092 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-05-23T19:36:04.269+03:00  INFO 41092 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:36:04.442+03:00  INFO 41092 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Started CruddemoApplication in 1.847 seconds (process running for 2.137)
Hello World
2024-05-23T19:36:04.455+03:00  INFO 41092 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:36:04.458+03:00  INFO 41092 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-05-23T19:36:04.466+03:00  INFO 41092 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```

And good stuff here.
We can go look at some of the logs here.
And let me scroll over here to the right-hand side. 
So it says that it's starting our application.
It says it added a connection.
So that means that the database connection was successful.
If you see this part, that means it's able to successfully connect to your database.
If not, it would've given you an error, but for now at least we see that that part is okay.
And then also if we scroll over a bit, then we see `hello world`.
Excellent, so that's our custom code that we set up for our command on application.
And then once it's done, then the application stops.
It's just a simple command on application that'll run and perform an operation.

Now let's break it on purpose.
Let's check to see if it really is connecting to the database.
So I'll just go ahead and add a bad password here.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudentasdfasdfasdfasda
```

Be sure to save that file.
And now if you run it again, then we should see some ugly exceptions.

```html
2024-05-23T19:39:35.212+03:00  INFO 41024 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
2024-05-23T19:39:35.492+03:00  INFO 41024 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-05-23T19:39:35.502+03:00  INFO 41024 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 5 ms. Found 0 JPA repository interfaces.
2024-05-23T19:39:35.780+03:00  INFO 41024 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-23T19:39:37.107+03:00  INFO 41024 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-05-23T19:39:37.144+03:00  INFO 41024 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-05-23T19:39:37.162+03:00  INFO 41024 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-05-23T19:39:37.316+03:00  INFO 41024 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-05-23T19:39:37.331+03:00  INFO 41024 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-23T19:39:38.367+03:00  WARN 41024 --- [           main] o.h.e.j.e.i.JdbcEnvironmentInitiator     : HHH000342: Could not obtain connection to query metadata

java.lang.NullPointerException: Cannot invoke "org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(java.sql.SQLException, String)" because the return value of "org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.sqlExceptionHelper()" is null
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.delegateWork(JdbcIsolationDelegate.java:116) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
    ... 33 common frames omitted

2024-05-23T19:39:38.372+03:00 ERROR 41024 --- [           main] j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA EntityManagerFactory: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
2024-05-23T19:39:38.372+03:00  WARN 41024 --- [           main] s.c.a.AnnotationConfigApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
2024-05-23T19:39:38.377+03:00  INFO 41024 --- [           main] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2024-05-23T19:39:38.392+03:00 ERROR 41024 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788) ~[spring-beans-6.1.8.jar:6.1.8]
    ... 44 common frames omitted
Caused by: org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:276) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	... 55 common frames omitted
Caused by: org.hibernate.HibernateException: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.determineDialect(DialectFactoryImpl.java:191) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	... 29 common frames omitted
```

Some ugly exceptions here.
So this is good 'cause we wanted to break it on purpose.
So this is kind of what we're looking for.
And let's scroll up for a bit and look at all of these exceptions here that were thrown.
And then scroll over a bit.
And we can see right at the point where it's setting up the actual pool.
It's starting the pool but exception during pool initialization meaning the database connection pool.
And it says that `access denied` for this given user.
So it fails because we're using the wrong password.
So behind the scenes, **Spring** really is trying to connect to your database using that information
from your `application.properties` file.
We broke it on purpose and that was desired.
Now let's go ahead and fix it, right?
Let's go ahead and swing it back to the correct password of `springstudent`.
Be sure we save this file and then swing over and then run it again.

```html
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-23T19:47:20.661+03:00  INFO 30980 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Starting CruddemoApplication using Java 21.0.2 with PID 30980 (D:\JAVA_STUDY\Github\dev-spring-boot\03-spring-boot-hibernate-jpa-crud\01-cruddemo-student\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\03-spring-boot-hibernate-jpa-crud\01-cruddemo-student)
2024-05-23T19:47:20.662+03:00  INFO 30980 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
2024-05-23T19:47:20.941+03:00  INFO 30980 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-05-23T19:47:20.950+03:00  INFO 30980 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 5 ms. Found 0 JPA repository interfaces.
2024-05-23T19:47:21.185+03:00  INFO 30980 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-23T19:47:21.485+03:00  INFO 30980 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@5460b754
2024-05-23T19:47:21.487+03:00  INFO 30980 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-05-23T19:47:21.540+03:00  INFO 30980 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-05-23T19:47:21.570+03:00  INFO 30980 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-05-23T19:47:21.588+03:00  INFO 30980 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-05-23T19:47:21.783+03:00  INFO 30980 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-05-23T19:47:22.049+03:00  INFO 30980 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-05-23T19:47:22.051+03:00  INFO 30980 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:47:22.207+03:00  INFO 30980 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Started CruddemoApplication in 1.778 seconds (process running for 2.046)
Hello World
2024-05-23T19:47:22.216+03:00  INFO 30980 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:47:22.218+03:00  INFO 30980 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-05-23T19:47:22.225+03:00  INFO 30980 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
```

And in this case, we see everything's okay.
So it's creating a pool and added connection started completed successfully.
So it can run, connect to the database and all that good stuff.
One thing I'd like to do is that when we're running these standalone applications,
I really wanna kind of just focus on doing some operation and then printing out the results.
I want to turn off some of the chatter, 
and one piece of chatter I want to turn off is this **Spring Boot banner**.
When I'm running a standalone application,
I really don't want to see the **Spring Boot banner** every time I know that I'm running **Spring Boot**.
There's no need for you to remind me every time I run the application.
So I'd like to turn off this banner, and you can easily do this via a configuration.
So let's swing over to our `application.properties`, and then we'll add an entry here
to turn off the **Spring Boot banner**.
I'll simply add this entry here.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off
```

That'll actually turn off the **Spring Boot banner**, so be sure to save that file.
And then when you run the application again:

```html
2024-05-23T19:50:50.014+03:00  INFO 7028 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Starting CruddemoApplication using Java 21.0.2 with PID 7028 (D:\JAVA_STUDY\Github\dev-spring-boot\03-spring-boot-hibernate-jpa-crud\01-cruddemo-student\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\03-spring-boot-hibernate-jpa-crud\01-cruddemo-student)
2024-05-23T19:50:50.017+03:00  INFO 7028 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
2024-05-23T19:50:50.296+03:00  INFO 7028 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-05-23T19:50:50.308+03:00  INFO 7028 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 6 ms. Found 0 JPA repository interfaces.
2024-05-23T19:50:50.535+03:00  INFO 7028 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-23T19:50:50.848+03:00  INFO 7028 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@a451491
2024-05-23T19:50:50.850+03:00  INFO 7028 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-05-23T19:50:50.893+03:00  INFO 7028 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-05-23T19:50:50.929+03:00  INFO 7028 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-05-23T19:50:50.947+03:00  INFO 7028 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-05-23T19:50:51.128+03:00  INFO 7028 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-05-23T19:50:51.393+03:00  INFO 7028 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-05-23T19:50:51.397+03:00  INFO 7028 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:50:51.569+03:00  INFO 7028 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Started CruddemoApplication in 1.819 seconds (process running for 2.093)
Hello World
2024-05-23T19:50:51.579+03:00  INFO 7028 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2024-05-23T19:50:51.582+03:00  INFO 7028 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-05-23T19:50:51.589+03:00  INFO 7028 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
```

You'll see that it runs without the Spring Boot banner being displayed every time.
Again, we're just trying to minimize some of the logging and minimize some of the chatter
that we see when we run our application.

And then also, I want to reduce the logging level.
So I want to set the logging level to warn such that it'll only show the warnings 
and the errors but not all the normal background logging information for **Spring**.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn
```

And again, I'm simply doing this just because we're creating a standalone application
and normal production environments. 
You may want to keep this on depending on your application requirements,
but for now we're simply going to change this.
So we'll see warning messages, and we'll see error messages,
but I basically want to say, _hey, don't print all that logging stuff_.
I'll simply want to see the information or the output from my application.

```html
Hello World

Process finished with exit code 0
```

And here we see this `hello world`.
And notice here we get the `hello world`, that's our own custom code.
We don't have all the **Spring Boot banner** stuff,
and we don't have all the **Spring Boot logger** information.
But one thing here is that don't worry, if there are problems or issues, 
it'll still log the warnings, and it'll also log the errors.
So if there's an exception or something, **Spring** will still tell you about it.
And then let's try and verify that.
Let's go back in here and let's try and break it again on purpose.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudentfsadfasdfasd

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn
```

Let's give a bad data source password.
Be sure to save that file.
And now we run our application again.

```html
2024-05-23T19:57:11.637+03:00  WARN 46532 --- [           main] o.h.e.j.e.i.JdbcEnvironmentInitiator     : HHH000342: Could not obtain connection to query metadata

java.lang.NullPointerException: Cannot invoke "org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(java.sql.SQLException, String)" because the return value of "org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.sqlExceptionHelper()" is null
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.delegateWork(JdbcIsolationDelegate.java:116) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentUsingJdbcMetadata(JdbcEnvironmentInitiator.java:290) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:123) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:77) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:130) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:221) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:189) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1431) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1502) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:75) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1835) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1784) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:952) ~[spring-context-6.1.8.jar:6.1.8]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624) ~[spring-context-6.1.8.jar:6.1.8]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:335) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352) ~[spring-boot-3.3.0.jar:3.3.0]
	at com.luv2code.cruddemo.CruddemoApplication.main(CruddemoApplication.java:12) ~[classes/:na]

2024-05-23T19:57:11.643+03:00 ERROR 46532 --- [           main] j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA EntityManagerFactory: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
2024-05-23T19:57:11.644+03:00  WARN 46532 --- [           main] s.c.a.AnnotationConfigApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
2024-05-23T19:57:11.660+03:00 ERROR 46532 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:952) ~[spring-context-6.1.8.jar:6.1.8]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624) ~[spring-context-6.1.8.jar:6.1.8]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:335) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363) ~[spring-boot-3.3.0.jar:3.3.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352) ~[spring-boot-3.3.0.jar:3.3.0]
	at com.luv2code.cruddemo.CruddemoApplication.main(CruddemoApplication.java:12) ~[classes/:na]
Caused by: org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:276) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:221) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:189) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1431) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1502) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:75) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1835) ~[spring-beans-6.1.8.jar:6.1.8]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1784) ~[spring-beans-6.1.8.jar:6.1.8]
	... 14 common frames omitted
Caused by: org.hibernate.HibernateException: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.determineDialect(DialectFactoryImpl.java:191) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.buildDialect(DialectFactoryImpl.java:87) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentWithDefaults(JdbcEnvironmentInitiator.java:152) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentUsingJdbcMetadata(JdbcEnvironmentInitiator.java:362) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:123) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:77) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:130) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	... 29 common frames omitted


Process finished with exit code 1
```

Then we still see the error, right?
We still see this given error message.
Scrolling to the top, it says, you know, access denied for `springstudents`.
So again, you'll still see the warnings, and you'll still see the errors
by making use of that logging level that we have set.
And let's just go back and fix it.
And run it one last time and everything's okay.
So we have our `hello world`.
So that's our own custom code.
We're all set up now.
We can start adding more custom code.
And so we'll add some code here for **DAO**s, connecting to a database, doing **Hibernate/JPA** stuff.
But at least we have our framework in place.
</div>

## [JPA Annotations]()
<div style="text-align:justify">

In this section, we'll cover the **JPA** development process.
Here's the JPA development process, our to-do list:

1. The first thing we need to do is annotate the Java class
2. And then we need to develop the Java code to perform the database operations

Let's just say **JPA**.
As mentioned, **Hibernate** is the default **JPA** implementation in **Spring Boot**.
Going forward in this course, I'll simply use the term **JPA**
instead of saying **JPA Hibernate** or **Hibernate JPA**, 
I'll simply say **JPA** because we know that by default,
**Hibernate** is used behind the scenes.
For terminology, we have this term called **Entity** class,
and that's a Java class mapped to a database table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image15.png?raw=true" alt="image15">
</div>

And here we'll make use of this object to relational mapping.
Again, mapping a Java class to a database table.
We'll have this Java class called **student** that has four fields:
id, firstName, lastName, and email.
We'll make use of **JPA**, and we'll map it to a given database table called **student**.
It has four columns: id, first_name, last_name, and email.
So we need to kind of provide mappings between the first_name and last_name.

At a minimum, the **Entity** class must be annotated with the `@Entity` annotation.
Also, it must have a public or protected no argument constructor.
The class can have other constructors, but again, as a minimum, 
you must have a public or protected no args constructor.

And now just a quick refresher here on constructors in Java.
Now remember when you're using constructors in Java,
if you don't declare any constructors, then Java will provide a no argument constructor for free.
However, if you declare constructors with arguments in your class,
then you do **NOT** get a no-argument constructor for free.
In this case, you have to explicitly declare a no-argument constructor.
So again, be aware of that.
And I'll kind of go through examples of the coding for this in this course.

With our Java annotations, we have two steps here:

1. Mapping a class to a database table
2. Mapping the fields to database columns

Let's get started here with step 1: Mapping a class to a database table.
So we have this class **student**, we have this database table called **student**.

```java
@Entitiy
@Table(name="student")
public class Student {
    // ...
}
```

Here, I make use of this entity annotation for my class,
and then I make use of the table annotation and I give `name="student"`.
That's the name of the database table that we're actually mapping this given Java class to.
And then in step two we map fields to database columns.

```java
@Entitiy
@Table(name="student")
public class Student {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    // ...
}
```

Here we have this class called **Student**.
And we need to map these given fields to the appropriate database columns.
And I can do that by making use of the column annotation.
You have `@Column(name="id")`.
That's the name of the actual database column.
For the next field here, _firstName_ have `@Column(name="first_name")`.
So again, notice here the spelling.
There's different spellings here between the Java class and the actual database column name,
and we annotate those accordingly.
One thing to be aware of here is that the `@Column` annotation is actually optional.
You actually don't have to provide it.
So if not specified, the column name is the same name as the Java field.
In general, I don't recommend this approach because if you refactor the Java code,
like maybe refactoring the field name, then it will not match the existing database columns.
And this is a breaking change, and you need to go through and update the database columns.
This may not be a big deal for a very small basic project that you're working with on your own.
However, if you're working in a large company working on an enterprise application at an MNC,
then you can't simply go through and change database columns, however you feel, 
because it could affect other teams within your company that are using that given database.
So again, be aware of that.
So I normally don't recommend this approach, but I wanted to let you know that it's available.
Also, the same applies to the `@Table` annotation.
If you don't provide the `@Table` annotation, then the database table name is the same as the class.
Again, the same thing applies here.
I don't normally recommend this.
I like to be explicit and annotate the class and fields accordingly.

Here's another term here, **primary key**.
So the primary key uniquely identifies each row in a table, must be a unique value,
and it cannot contain `NULL` values.

```sql
CREATE TABLE student (
    
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(45) DEFAULT NULL,
    last_name varchar(45) DEFAULT NULL,
    email varchar(45) DEFAULT NULL,
    PRIMARY KEY (id)
)
```

In the MySQL database, we can make use of an auto increment.
So we can define our primary key,
and then we can specify that it's an auto increment.
We have this column id of type int.
We say it's `NOT NULL`, and then we specify `AUTO_INCREMENT`.
Behind the scenes, MySQL will keep track of automatically incrementing this `id`
and make sure it's a unique value.
And also here at the line at the bottom for a primary key,
we specify that our given column `id` that this is the primary key for this given table.

```java
@Entitiy
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    // ...
}
```

And then, so in the **JPA** world, 
we have to specify the actual primary key or the identity here.
So we make use of this `@Id` annotation,
and then we specify how this given `id` or primary key, how the value generated.
Then I make use of `GenerationType.IDENTITY`.
What I'm saying is that this `id` value will be generated
by the database and managed by the database.
There's no need for our code to manually try and keep track of that given `id`.
It's a generated value.

| Name                       | Description                                                                              |
|----------------------------|------------------------------------------------------------------------------------------|
| `GenerationType.AUTO`      | Pick an appropriate strategy for the particular database                                 |
| `GenerationType.IDENTITY`  | Assign primary keys using database identity column                                       |
| `GenerationType.SEQUENCE`  | Assign primary keys using a database sequence                                            |
| `GenerationType.TABLE`     | Assign primary keys using an underlying<br/> database table to ensure uniqueness         |
| `GenerationType.UUID`      | Assign primary keys using a globally unique <br/> identifier (UUID) to ensure uniqueness |

Now, a bit more here on the `ID` generation strategies.
The first one here is `GenerationType.AUTO`.
That'll basically pick an appropriate strategy for the particular database.
Next, we have this `GenerationType.IDENTITY`.
That's the one that we're using.
So we're going to assign primary keys using the database identity column or primary key column.
Another approach here is the `GenerationType.SEQUENCE`.
You can assign primary keys using a database sequence.
And next we have `GenerationType.TABLE`.
We assign the primary keys using an underlying database table to ensure uniqueness.
And then finally, we have `GenerationType.UUID`.
Here we assign primary keys using a globally unique identifier or UUID to ensure uniqueness.
In general, I recommend teams start out by using the `GenerationType.IDENTITY`,
and that should cover most of the use cases.
However, if you have very specific use cases, you can apply some of the other generation types
based on your application requirements.

We have a very specific requirement for generating the ID.
And nothing that JPA provides out of the box matches our requirement.
You can actually define your own **CUSTOM** generation strategy for generating your **ID**.
You simply create a custom implementation of `org.hibernate.id.IdentifierGenerator` 
identifier generator interface.
You override the method or implement the method generate: `public Serializable generate(..)`.
And again, inside this generate method, you provide your own custom business logic
or corporate logic here for actually generating a given **ID**.
So they basically give you an extension point
where you can create your own custom generation strategy for creating your **ID**s.
Let's start mapping our class to a database table.

So the first thing we want to do is create this new package called `entity`
to hold our **Entity** class.
Let's go ahead and create a new package.
And the name of the package is `entity`.
And now we'll create a new class called **Student**.
And let's go ahead and map this class to a database table.

```java
package com.luv2code.cruddemo.entity;

@Entity
@Table(name="student")
public class Student {
    
}
```

So we need to start with the `@Entity` annotation.
And then, to map it to a table, I make use of the Table annotation
and give the name of the table.
And so in this example here, the name of the table is student.
And now we'll go through and map our fields to the database columns.

```java
package com.luv2code.cruddemo.entity;

@Entity
@Table(name="student")
public class Student {
    
    // define fields
    
    // define constructors
    
    // define getters/setters
    
    // define toString() method
}
```

I'll start off by just writing some quick comments to myself
just to kind of keep myself on track.
What I'll do here is I'll define the fields, define the constructors, the getters and setters,
and finally, a _toString_ method.
I know I'm going to have four fields here, id, firstName, and lastName.

```java
package com.luv2code.cruddemo.entity;

@Entity
@Table(name="student")
public class Student {
    
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;
    
    // define constructors
    public Student() {
        
    }
    
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // define getters/setters
    
    // define toString() method
}
```

I'll start off with the id field.
And we know that the id field is our primary key for this given student, 
so I make use of the `@Id` annotation, and then I specify how this value is generated.
And remember, the `.IDENTITY` basically says, 
_Hey, we'll let MySQL handle the auto-increment on that given `Id`_.
And then here I map it to the actual database column name.
So I give a `@Column`, and I give `name="id"`.
So again, matching to the actual database column name.
And let's do a similar thing here for the next field, _firstName_.
And it will map this to the database column
and the database column is named _first_name_.
And now I'd like to do is just kind of copy-paste this line for some of the other fields.
And then I'll go through and update it.
So we already did _firstName_.
So now this is really _lastName_. 
So _last_name_.
And I'll say field name is _lastName_.
That takes care of _lastName_.
And then we also have _email_.
So the column name is _email_ and update the field to be _email_.
Now let's move down here to this next section for defining the constructors.
And what I'll do is I'll start off by defining a no-argument constructor.
And what I'll do is I'll also create a constructor that accepts arguments.
What I'll do here for generating this constructor is that I'll say, use the three fields,
_firstName_, _lastName_, and _email_.
Don't include _id_ because we're not going to use that for creating the items.
We can also go through and generate our getter and setter methods.

```java
package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // define constructors
    public Student() {

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // define toString() method
    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}
```

And then for generating the getter and setter methods,
be sure to choose all of your fields, _id_, _firstName_, _lastName_, and _email_.
And then finally, we'll have our IDE generate a _toString_ method for us. 
And we'll go ahead and select all of those fields here
and then allow it to generate it for us.
Now, one thing to be aware of is that, if you're an advanced developer, 
you may have read about other types of solutions,
something like **Lombok** to help minimize coding and so forth.
If you have experience using **Lombok**, feel free to use it.
However, I'm not going to use it at this point
because I don't want to introduce too many new technologies.
I'd like to kind of see the code and see how everything works.
And once we get the basics working, then you can get very sophisticated
and add in all those other frameworks if you'd like.
But here, our IDE can help us out with this,
and we can make use of code folding to hide the code accordingly.
So anyway, that's in place and ready to go.
I simply want to make that statement because folks always pipe up about
some new favorite framework or whatever.
So we have our **Student** class mapped to a database table,
and we also mapped the fields to the appropriate database columns.
</div>

## [Saving a Java Object with JPA]()
<div style="text-align:justify">

Now, we'll cover the Java code for saving a Java object.
We'll create a sample application, and these are the features of the application.
We'll write code to: 

* Create a new student 
* Read a student 
* Update a student 
* Delete a student

And this is basically a **CRUD** application.
And we'll start off by creating a new student.
We'll cover some of the other features in the following sections.
We'll make use of a student data access object.
The data access object is responsible for interfacing with the database.
This is a very common design pattern: `Data Access Object (DAO)`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image16.png?raw=true" alt="image16">
</div>

Here, we have our application.
It's going to communicate with this data access object or DAO,
and the DAO will talk to our actual database.
So again, this is kind of like a helper class for communicating with the database.

| Methods             |
|---------------------|
| save(...)           |
| findById(...)       |
| findAll()           |
| findByLastName(...) |
| update(...)         |
| delete(...)         |
| deleteAll(...)      |


Our student data access object will have a number of methods.
We'll have a _save_ method for saving a student.
We'll have some finder methods for _findById_, _findAll_, and _findByLastName_.
We'll also have a method for updating the student.
And then we can also _delete_ and then also perform a deleting,
and we'll implement these methods in upcoming sections.

In our scenario, our data access object needs a **JPA EntityManager**.
Now the **JPA EntityManager**'s the main component for saving and retrieving entities.
So we have our student DAO here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image17.png?raw=true" alt="image17">
</div>

It's going to make use of an **EntityManager** that also has some other supporting components,
and then communicating with the actual database.

Now as far as the other supporting components, our **JPA EntityManager** needs a data source.
So the data source basically defines the database connection information,
and the **JPA EntityManager** and the data source are automatically created by **Spring Boot**.
Based on information from our **Maven** `pom` file,
and also information from our `application.properties`
such as the URL, user ID, and password, it'll actually connect to the database.
And then we can autowire or inject the **JPA EntityManager** into our Student DAO.
And I'll show you the coding for that in a second.
But just to kind of step back here
and kind of look at the architecture diagram above,
we have our data access object that's going to make use of an **EntityManager**.
That'll also make use of a given data source.
And we'll actually inject the **EntityManager** into our student DAO.

Let's take a small digression for a second.
Some of you may have done some pre-reading, or you may have seen some online media,
or read some blog posts.
And you may have read about **JpaRepository**.
And at this point in the course, you may wonder, well, what about **JpaRepository**?
Why are we not using it?
So just a bit of context here.
**Spring Data JPA** has a **JpaRepository** interface.
It provides **JPA** database access with minimal coding.
And here are some of the actual questions that came from other students in the class 
as this course was out and made available.

* "`Hey, why have we not simply used JpaRepository instead of using **EntityManager**?`"
* "`I've seen a lot of tutorials online, on YouTube, and they use JpaRepository.
What's the difference?
What's the context?`"

And then also, when you hear about **JpaRepository**,
and we talked about **EntityManager**, then you're probably wondering, well, which one do I use?
Like two questions here, "`What's the difference between the two?`"
And then also, here, the person at the bottom is saying,
"`Which use case should I use? 
EntityManager versus JpaRepository, which one runs better?`"
Once the course was released, I kind of went back,
saw the questions that came in and said,
hey, let's go ahead and put together some additional context here
to kind of discuss and address these questions.

So the short answer here is that, yes, we will use **JpaRepository** in this course.
We'll cover it later in the course.
And in this course, I want to show you various techniques for using **JPA**.
And in fact, knowing BOTH **EntityManager** and **JPA** will actually help you on future projects.
I've been on previous consulting projects
where we had various team members, and we had to solve some issues, or some issues came up.
And there were some people who only knew how to use **JpaRepository**.
They didn't understand all the details behind **EntityManager**.
So those folks were kind of at a disadvantage.
They really couldn't contribute to the team.
However, developers who knew both **EntityManager**, and **JpaRepository**, 
they could definitely contribute to the team and add value, help us resolve issues, 
and everything else.
So I recommend that you learn both of them.
And again, don't worry; we'll cover both of these technologies in this course.

Now as far as when to use which, in basic terms,
if you need `low-level control and flexibility`, use **EntityManager**.
If you want a `high level of abstraction`, then make use of **JpaRepository**.

Now let's dig into this a bit and talk more about these use cases here.
Starting with EntityManager.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image18.png?raw=true" alt="image18">
</div>

EntityManager's useful when you need low-level control over the database operations,
and you want to write custom queries.
**EntityManager** also provides low-level access to **JPA**,
and allows you to work directly with **JPA** entities.
And you can use this for complex queries that require advanced features
such as native SQL queries or stored procedure calls.
And also, **EntityManager** is great when you have customer requirements
that are not easily handled by higher level abstractions.

Now the use case for **JpaRepository** is that it provides commonly used 
`CRUD` operations out of the box.
This reduces the amount of code that you need to write.
It also has additional features such as pagination and sorting.
It can also generate queries based on Java method names.
And you can also create custom queries using the query annotation.

My recommendation is that the choice really depends on your application requirements
and developer preference, but one thing I'd like to point out is 
that you can actually use both of these in the same project.
You want to leverage the different features of each one.
Now, for learning purposes, I recommend that you start by learning **EntityManager** first,
then learn **JpaRepository**.
Because, basically, this will help you learn the low-level coding behind the scenes.
And then you can use **JpaRepository** for some of the more additional features there.
And again, like I mentioned earlier, knowing both **EntityManager** and **JpaRepository**
will help you on future projects.
Let's start writing some code for our student DAO.

Here, the development process for our Student DAO:

1. Step one, we'll define the DAO interface. 
2. Step two, we'll define the DAO implementation, and we'll inject that entity manager.
3. Step three, we'll update our main application.

Alright, starting here with step 1: Defining the DAO interface.

```java
import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    
    void save(Student theStudent);
}
```

So this is basically a Java interface, and we define a method here, _save_.
We have _save_, and they'll pass in a given student object.
And remember the student is our Java class, our **JPA** entity
that's mapped to a given database table.
And we did all the mapping work in a previous section.

And now in step 2: we define the DAO implementation.

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDAOImpl implements StudentDAO {
    
    private EntityManager entityManager;
    
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    
    @Override
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
```

Here we have our class **StudentDAOImpl**, implements **StudentDAO;** 
that's the interface from the previous code.
We have this field for **EntityManager**.
And then we have our constructor here for _StudentDAOImpl_
we have _theEntityManager_, and we have this specified as `@Autowired`
and we use this to inject _theEntityManager_ into our DAO,
and we make the assignment accordingly within this constructor.
And then we have an implementation here for this _save_ method.
So they'll pass on a student object, and then I can use this **JPA** entity manager
to actually save the Java object.
I accomplish this by saying `entityManager.persist` and I pass in _theStudent_,
and this will actually save the Java object to the database.
But there's still a little bit more work we need to do here.
So let's discuss this in a second.

The **Spring** provides a `@Transactional` annotation.
`@Transactional` annotation will **automatically** begin 
and end a transaction for your JPA code.
And so there's no need for you to explicitly do this in your Java code.
This **Spring** magic will actually happen behind the scenes.

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class StudentDAOImpl implements StudentDAO {
    
    private EntityManager entityManager;
    
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
```

And what we do is, we add this annotation on the actual method.
So for our _save_ method, we know we want this to run inside a transaction, 
so we make use of this `@Transactional` annotation. 
And behind the scenes, **Spring** will handle the transaction management.
Again, transactional is from the **Spring** framework.

And **Spring** also provides some specialized annotations for DAOs.
**Spring** has this `@Repository` annotation,
that's basically a sub annotation of `@Component`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image19.png?raw=true" alt="image19">
</div>

And so you have a normal component that we learned about earlier in the course,
and we saw some examples of `@RESTController`, and we had this `@Repository`.
So this is for annotating DAOs.

So this annotation is applied to DAO implementations 
and **Spring** will automatically register the DAO implementation, thanks to component-scanning.
And then **Spring** will provide translation of any JDBC related exceptions.
So here, when I say translation, meaning that if you have any checked JDBC exceptions
and then **Spring** will translate these to unchecked exceptions.
Basically makes the coding a bit easier here for interfacing with a given database.

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {
    
    private EntityManager entityManager;
    
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
```

So here's like an updated version of our DAO implementation.
So for our _DAOImpl_, we need to add this annotation for `@Repository`.
And again, a specialized annotation for repositories and DAOs.
It gives you support for component scanning and also translating JDBC exceptions.
So again, translating those checked JDBC exceptions into unchecked exceptions.
And that's the basics here, or the final coding here for our DAO implementation.
So key things here, the `@Transactional` annotation, and also the `@Repository` annotation.

```java
@SpringBootApplication
public class CruddemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            createStudent(studentDAO);
        }
    }
    
    private void createStudent(StudentDAO studentDAO) {
        
        // create the student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
        
        // save the student object
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);
        
        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
```

Now, in step 3: Update our main application.
So remember, back in our CRUD demo application, we had our little `hello world` example here.
And so for our _commandLineRunner_, the one thing
that we want to do is, inject the _StudentDAO_,
and then we'll call this method _createStudent_.
And this _createStudent_, we define right below.
So they pass in the DAO.
Basically in this _createStudent_,
we create the student object by using the **new** keyword,
we save the student by using our DAO, and then we display the `ID` of the saved student.
So here we say `Generated id`, and we give `tempStudent.getId`.
And then we could also go into our database and do a query on the table
and verify that the information is actually in the database.
And we'll accomplish that in our coding coming up.
We'll use the MySQL Workbench to verify information in the database.
Now, let's go ahead and move into our IDE, and let's start writing the code ourselves.

In step 1, we're going to define our DAO interface.
I'll create this new package `com.luv2code.cruddemo` package, named `DAO`.
And now I'll go ahead and create a new interface called **StudentDAO**.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    
    void save(Student theStudent);
}
```

We'll define this method here, _save_, we pass and a student.
Let me just go ahead and fix the imports here.
And this should import our student from our entity package.

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {
    
    // define field for entity manager
    private EntityManager entityManager;
    
    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    
    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
```

Now in step 2: we're going to define our DAO implementation, and our DAO package
will create this new class **StudentDAOImpl**.
And the first thing we do here is we say implement **StudentDAO**.
We're implementing that interface.
We can go ahead and say, `implement` the methods for us to kind of step things out.
Let's go ahead and annotate this class with a `@Repository` annotation.
This will give us support for component scanning 
and also handle the **Spring** support for our exception translation.
And I'll write some quick comments here to myself just to kind of keep myself on track.
We'll define the field for the entity manager,
inject the entity manager using constructor injection,
and then finally implement the _save_ method.
Alright, I'll define this field for the entity manager.
And now we'll inject the entity manager using constructor injection.
I'll simply generate this constructor.
And I'll make use of the `@Autowired` annotation.
Remember, the `@Autowired` annotation is optional if we only have one constructor, 
but I'll put it here just to make the code easier to follow and read.
And now I'll go and add the `@Transactional` annotation,
since we're performing an update on the database.
We're actually saving or storing an object in the database.
So I'll make use of this transactional annotation from the **Spring** framework.
So be sure to choose the item here from the **Spring** framework.
And remember, this `@Transactional` annotation
will kind of handle the transaction management for us accordingly.
And now I can kind of move into the method and do the work.
I simply say `entityManager.persist` and I give the student object.
And so this will actually save the student to the database.
Alright, so kind of scrolling up a bit.
So this is the main coding here that you'd want to have in place here
for that **StudentDAO** implementation.

Now in step 3: Update our main application.

```java
package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//System.out.println("Hello World");
            createStudent(studentDAO);
		};
	}
    
    private void createStudent (StudentDAO studentDAO) {
        
    }
}
```

And down on my **CommandLineRunner** section here,
I'm going to inject the **StudentDAO**.
We simply give a reference to the StudentDAO and **Spring** will inject it accordingly.
I'll remove the Hello World, and I'll add some custom code here for creating the student.
I'll call this method, _createStudent_, and I'll pass on the **StudentDAO**.
But at the moment, we haven't really created this method,
so I'll just allow the IDE to help me with creating that method.
And there's a little stub or skeleton for it.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    //public CommandLineRunner commandLineRunner(String[] args) {
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //System.out.println("Hello World");
            createStudent(studentDAO);
        };
    }

    private void createStudent (StudentDAO studentDAO) {

        // create the student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

        // save the student object
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
```

I'll write some quick comments here to myself just to kind of keep myself on track.
Alright, so what we want to do here is `create the student object`, 
`save the student object`, and `display the id of the saved student`.
I'll make use of the `System.out.println`, "`hey, creating new student object`".
And I create that object using the **new** keyword.
Then I go through and do a print on `saving a student`.
And then I call my student DAO and I say `studentDAO.save(tempStudent)`.
And then finally, I display the id of the saved student.
This is kind of the main coding here for creating the student and saving them into the database.
We make use of our student DAO, which is our helper/dependency to help us out with this functionality.
Now, before I run this application, I want to move over to MySQL workbench
and verify some information in the database.

So I'll swing over here, make a connection.
Once I'm connected, I'll go into the `student_tracker` database schema,
I'll choose this table here, student, I'll run a query on this table,
and notice here that this given database table is empty.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image20.png?raw=true" alt="image20">
</div>

There's nothing in it.
Remember, we created this table earlier, but we haven't added any students to it.
So right now it's empty, nothing's there.
Let's swing back over here to our IDE, and then we can run this application and test it out.

```html
Creating new student object...
Saving the student...
Saved student. Generated id: 1

Process finished with exit code 0
```

Alright, so our application executed it, said, 
_hey, creating a new student, saving a student, saved a student, generated id of 1_.
Let's swing over to our MySQL workbench and let's do a little refresh here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image21.png?raw=true" alt="image21">
</div>

Re-executing that query and then success.
So here we see that we have `Paul Doe, paul@luv2code.com` and then notice the id of `1`.
So that matches with the information shown when we actually ran our application.
So we created the student object.
It's making use of the primary key, the id of `1`.
And MySQL database will handle auto incrementing of that key for any new students that we add.
</div>

### [Primary Keys]()
<div style="text-align:justify">

I wanna just investigate a bit more on the database schema here for this student table.
Let's move to `student_tracker` and then the `student` table, 
and then let's go ahead and view information about the table.
So we'll simply say alter table.
Okay, we're not gonna really alter it.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image22.png?raw=true" alt="image22">
</div>

We're simply going to look at the database schema here for this table.
We see that this given table has four columns.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image23.png?raw=true" alt="image23">
</div>

ID, first name, last name, email.
`PK` means that ID is a primary key.
The `NN` means that it's a not null column.
And then the `AI` means auto increment.
So the MySQL database will handle auto increment in the primary key in managing it to make sure it's unique.

```sql
SELECT * FROM student_tracker.student;
```

And then we simply do a quick select on it, and we see that we have that one entry there.
Now what I'd like to do here is test out the auto increment feature, 
and we can test it out by writing some more code to create multiple students
and add those students, and then we should see the ID column auto increment using MySQL.
Let's go ahead and write some code to demonstrate this.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
            
            createMultipleStudents(studentDAO);
		};
	}

	private void createStudent (StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
```

I'll move back to my main application here, and I'll comment out `createStudent(studentDAO)`,
and I'll create this new method here, `createMultipleStudents(studentDAO)`.
I'll make use of the IDE to help me generate this method, _createMultipleStudents_,
and we'll go through, and we'll create multiple students here and then save those students using our DAO.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
            
            createMultipleStudents(studentDAO);
		};
	}

    private void createMultipleStudents (StudentDAO studentDAO) {

        // create multiple students
        System.out.println("Creating 3 student objects...");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

        // save the student objects
        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }
    
	private void createStudent (StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
```

Let me go ahead and do a little quick copy and paste on some of our code here.
So I'll just copy this section from our previous method, and then I'll paste it here.
Creating three student objects, and I'll call this `tempStudent1`,
and I'll give the student a new name, `John Doe`, update his email address
and let me copy this line and then paste it 3 number of times.
So I have `tempStudent2` and `tempStudent3`.
Let's just update their information just so it's different, and we can easily see it in the database.
`Mary Public`, and then `Bonita Applebum`.
So we have our three students created, _John_, _Mary_, and _Bonita_.
And then let's go ahead and write the appropriate code to save those student objects.
Here we'll say `studentDAO.save(tempStudent1)`.
Let me copy and paste these three numbers of times.
Save student two and student three.
Let's go ahead and run our application and test it out.

```html
Creating 3 student objects...
Saving the students...

Process finished with exit code 0
```

And so it said, creating three student objects, saving the students.
And let's swing over to our MySQL Workbench.
Let's run our query again.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image24.png?raw=true" alt="image24">
</div>

And success.
So we have those three new students, a total of four.
And notice here how MySQL handles the auto increment of the ID column.
Okay, it's done automatically in the background by the database, which is really cool.
And that's basically it.
So we saw an example of saving one student, and then also saving multiple students,
and also how MySQL handles the auto increment of the ID column.
</div>

### [Changing Index of MySQL Auto Increment]()
<div style="text-align:justify">

Another question that I get or a frequently asked question is,
"_How do I change the auto increment values? 
For example, I don't want to start the increment at number 1,
I'd like to start it at a different number like 1,000, 5,000 or 20,000. 
How can I do that?_"

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image25.png?raw=true" alt="image25">
</div>

What we can do is open up a new SQL window here.
We can write some code to alter the start of the auto increment.
I'll say `AUTO_INCREMENET=3000`.
That's the new index that we want to start from,
and we hit the yellow lightning bolt to execute it.
Now, we go back into our application.
We run our application again,
so we're going to add those three student objects.
We swing back to our application, we run that query one more time and get the latest.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image26.png?raw=true" alt="image26">
</div>

And now, notice here we have three new entries at the bottom: _John_, _Mary_, and _Bonita_.
But now these entries have IDs starting at 3000.
So, 3000, 3001,3002.
That's one approach there on how you can actually manage or change the actual start index of a given auto increment ID.

Another question I get, "_How do I reset the AUTO_INCREMENT values back to 1?_"
And what we can do here is make use of `TRUNCATE`.

```sql
TRUNCATE student_tracker.student
```

So this will truncate the database, and so this will actually remove data from the database
and reset the auto increment to start at 1.
Here I give "`TRUNCATE student_tracker.student`".
Let's execute this, and then refresh the query.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image27.png?raw=true" alt="image27">
</div>

Notice here that the data's been removed from the database and behind the scenes;
the auto increment is set up to start at 1.
Let's swing back over to our application, let's run our application again.
And let's run our query one more time in my MySQL Workbench.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image28.png?raw=true" alt="image28">
</div>

And then we see that we have those three students, but here the auto increment value
has been reset to 1, and it starts there: 1, 2, and 3.
And that's one solution there for resetting the auto increment.
</div>

## [Reading Objects with JPA]()
<div style="text-align:justify">

In this section, we're going to learn how to retrieve an object.
We're making some perfect progress here with creating our `CRUD` app.
We've covered everything on doing the `create` portion of it.
Now we'll focus on `read` an object.

```java
// retrieve/read from database using the primary key
// in this example, retrieve Student with primary key: 1

Student myStudent = entityManager.find(Student.class, 1);
```

Now, retrieving an object with **JPA**, in this example, 
I'm going to retrieve a student using a primary key of `1`.
Here I make use of `entityManager.find`.
I give the actual name of the **Entity** class, `Student.class`.
`1` is the primary key.
And once it finds the object, it'll return it, and we can assign it accordingly.
This is for the happy path.
In the event that they cannot find a student with that given primary key, then it'll return `null`.
So `null`'s returned if they don't find the given entity based on that primary key.
Let's look at the development process for retrieving an object using our DAO example:

1. We'll do is we'll add a new method to the DAO interface
2. We'll add a new method to the DAO implementation
3. Finally, we'll update our main application

```java
import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    
    //...
    
    Student findById(integer id);
}
```

Starting here with step 1: Add a new method to the DAO interface.
Here's our **studentDAO**.
We have this method here, _fineById_, and we pass in an integer of the _id_.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
// ...

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;
    // ...

    @Override
    public Student fineById(Integer id) {
        return entityManager.find(Student.class, id);
    }
}
```

And then in step 2: Define our DAO implementation.
And here's the method that we implement here, _fineById_, pass in the _id_, 
and then I make use of `entityManager.find`.
I give the entity class of `student.class`, and then I give the primary key.
That's the `id`.
And again, if it's not found, it simply returns `null`.
And notice here there's no need to make use of the `@Transactional` annotation
since we're only doing a query.
We're not performing any updates or modifications to the database.
Simply read-only.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
// ...

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createMultipleStudents(studentDAO);
            
            readStudent(studentDAO);
		};
	}
    
    private void readStudent(StudentDAO studentDAO) {
        // create a student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

        // save the student objects
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());

        // retrieve student based on the id: primary key
        System.out.println("\nRetrieveing student with id: " + tempStudent.getId());
        
        Student myStudent = studentDAO.findById(tempStudent.getId());

        System.out.println("Found the student: " + myStudent);
    }

    // ...
}
```

Now in step 3: Update our main application.
Here inside our _commandLineRunner_, that's where we add our custom code.
I'll call this method _readStudent_, pass in the _studentDAO_,
and then that's the code that we'll create over here.
And we just go through the process here.
We create a student object, we save the student,
and we display the id of the saved student, and now I have that primary key, that _id_.
And then I can retrieve the student based on the _id_ or based on their primary key.
Here I make use of `studentDAO.fineById()`, and I give `tempStudent.getId()`,
and I'll refine that given student, and then we can print out that given student.
Alright, so that's the basic development process.
Now let's start writing the code.

We'll get started with step one of adding the new methods to our DAO interface.
Here's our student DAO interface and let's go ahead and add this new method
called findById returns a student, and we pass in the integer id,
and that's the basic coding here for this new method.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);
}
```

Now, we'll move ahead to step two, we'll add a new method to the DAO implementation.
Let me go ahead and allow the IDE to implement the method for us, _findById_.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //return null;
        return entityManager.find(Student.class, id);
    }
}
```

And so now down here at the bottom we have this stub for the method,
looks pretty good, and we'll make use of that method on the entity manager,
we'll say `entityManager.find` will give the actual entity class, `student.class`, 
the primary key, the `id` that's passed in. 
So again, `student.class` is the entity class and then we have the actual primary key.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
            
            readStudent(studentDAO)
		};
	}

    private void readStudent (StudentDAO studentDAO) {
        // create a student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

        // save the student 
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + theId);

        // retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDAO.findById(theId);

        // display student
        System.out.println("Found the student: " + myStudent);
    }
    
	private void createMultipleStudents (StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent (StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
```

And then finally, in step 3: update the main application to use this new DAO method.
Down here in our _commandLineRunner_, we will comment out the call to this _createMultipleStudents_,
and then we'll call this method _readStudent_.
We'll use the IDE here to create that method for us, a stub for that method.
And before I write the code here, let me write some comments to myself just to keep myself on track.
We'll start off by creating a student object, we'll save the student, we'll display the ID of that student,
we'll retrieve the student based on the ID and then also display the student.
Creating a student, basic, right?
We simply create a new student for `Daffy Duck`.
We save the student using the code that we've created before, `studentDAO.save(tempStudent)`,
and then we'll display `theId` of the saved student.
I'll create a variable here, `theId` of `tempStudent.getId()`, I'll display it.
Let's do a print retrieving the student with the given id,
and then I make that call `studentDAO.findById(theId)`, and I pass in the id.
We're really executing our new code here that we just created.
And then we simply print out `myStudent`.
And that's the basic code in there for reading it,
so we kind of create a student first, we save them, then we retrieve them 
and display them kind of going full circle here.
Now let's go ahead and run this application and test it out:

```html
Creating new student object...
Saving the student...
Saved student. Generated id: 4
Retrieving student with id: 4
Found the student: Student{id=4, firstName='Daffy', lastName='Duck', email='daffy@luv2code.com'}

Process finished with exit code 0
```

And success.
So here at the bottom it says creating a new student,
saving, saved, the ID of four, and it prints out that student that it retrieved `Daffy Duck`.
And I always like to verify this in the database.
Move over to MySQL Workbench and then just do a query on that student table and success.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image29.png?raw=true" alt="image29">
</div>

So we see that `Daffy Duck` here is in the list of students for this given database.
</div>

## [Querying Objects with JPA]()
<div style="text-align:justify">

In this section, we'll learn how to query objects.
Taking a look at our progress here of building a `CRUD` app,
we've covered the basics on how to read a single object,
here we're going to learn how to query for multiple objects.
**JPA** has the JPA Query Language (JPQL).
It's a query language for retrieving objects.
It's similar in concept to SQL where you can select from a given table and grab some data accordingly.
You can also make use of constraints here 
or selectors like `where`, `like`, `order by`, `join`, so on and so forth.
However, the difference here is that JPQL is based on the **entity name** and **entity fields**,
as opposed to the direct table names and table columns.
When you use JPQL, by default you make use of the entity name and entity fields,
and we'll see some examples of this coming up.

```java
TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

List<Student> students = theQuery.getResultList();
```

And here's an example of retrieving all the students using JPQL.
I make use of this **entityManager**, and I say _createQuery_, and then I give `FROM Student`.
Now, this is the name of the actual **JPA** entity, the class name, and then we give, `Student.class`.
This will make sure that this is a typed query, 
and we make the assignment on the left-hand side that's the query.
And one thing here on the query is that **Student**, this is not the name of the database table.
All JPQL syntax is based on the **entity name** and **entity fields**.
Now, once we have this query created, then we can retrieve the data by saying 
`theQuery.getResultList` and it'll give us a list of students,
and then from there we can use it in our application accordingly.
But the key here is that when using JPQL, you make use of the **entity name** and also **entity fields**.

```java
TypedQuery<Student> theQuery = entityManager.createQuery(
                                    "FROM Student WHERE lastName = 'Doe'", Student.class);

List<Student> students = theQuery.getResultList();
```

Let's take a look at an example of retrieving students whose last name equals `Doe`.
Here I make use of **entityManager**, I say _createQuery_, I give `FROM Student WHERE lastName = Doe`.
_lastName_ is the field of the **JPA** entity, that's the actual field name.
So we're not using the actual database column name, we're using a **JPA** entity field name. 

```java
TypedQuery<Student> theQuery = entityManager.createQuery(
                        "FROM Student WHERE lastName = 'Doe' OR firstName='Daffy'", Student.class);

List<Student> students = theQuery.getResultList();
```

We can also retrieve students using predicates.
And note here _lastName_ and _firstName_, those are the names of the actual **JPA** entity Fields.

```java
TypedQuery<Student> theQuery = entityManager.createQuery(
                        "FROM Student WHERE email LIKE '%luv2code.com'", Student.class);

List<Student> students = theQuery.getResultList();
```

We could also retrieve students using the `LIKE` predicate.
In this example, I want to get a list of all the students whose email ends in `luv2code.com`.
I used a percent in this scenario to say let's match on anything that ends with `luv2code.com`.

In the previous examples, we kinda hard coded some the data like, you know, `lastName = Doe`.
Well, you may want to use this as a parameter.
So maybe a user entered this into a web form, and you want to read that information
and then search for that user's name accordingly.
Well, with JPQL we can make use of named parameters.

```java
public List<Student> findByLastName(String theLastName) {
    TypedQuery<Student> theQuery = entityManager.createQuery(
                                        "FROM Student WHERE lastName=:theData", Student.class);
    
    theQuery.setParameter("theData", theLastName);
    
    return theQuery.getResultList();
}
```

We have this method called _FindByLastName_.
This is a method that we're defining in our, maybe our DAO,
and then I have the _lastName_ being passed in.
I make use of `entityManager.createQuery`, I give `FROM Student WHERE lastName =`,
and then I make use of this special syntax here, I use `:theData`.
This is a JPQL named parameter, and these named parameters are prefixed with a colon.
And you can kind of think of this as a placeholder that we'll fill in later.
And then filling it in later on the next line of code, we give `theQuery.setParameter`,
we provide the name of the parameter, `theData`, and then we give the actual value, `theLastName`.

Now, let's talk about JPQL and the `select` clause.
The query examples I've shown you so far, they did not really specify a `select` clause.
And that's because the **Hibernate** implementation behind the scenes is lenient.
It allows for a **Hibernate** query language, HQL, which is different from JPQL.
For strict JPQL, the `select` clause is required.

```java
TypedQuery<Student> theQuery = 
                    entityManager.createQuery("select s FROM Student s", Student.class);
```

So really to follow the JPL standards strictly, 
we'd have to write our query such as `select s FROM Student s`.
Now, the first `s` is really just an identification variable or like an alias.
It basically gives us a reference to the student entity that's being returned.
Now, this `s`, it can be any name.
I simply just used it just because that's a good, you know, first initial for a student,
but you can use any name, any variable, whatever,
like a normal variable name or whatever or an alias.
This identification variable here, it's useful for when you have complex queries,
when you need to refer to the entity later and `WHERE` clauses and so on.
But again, when you're making use of strict JPQL, the `select` clause is required.

```java
TypedQuery<Student> theQuery = entityManager.createQuery(
                    "select s FROM Student s WHERE s.email LIKE '%luv2code.com'", Student.class);
```

And then we saw some other examples where we made use of queries and so forth for like,
you know, checking for a student that has an email or checking for a student with a given last name.
This is how you would kind of revise or refactor those queries using strict JPQL with a `select` clause.
Here in the first example, I have select `s FROM Student s WHERE s.email`.
So again, `s` is a reference to the entity or the object,
so we know it's a student, and we know that a student has a field called `email`, 
so that's why we have `s.email`, and then we give our normal `LIKE` like we had before.

```java
TypedQuery<Student> theQuery = entityManager.createQuery(
                    "select s FROM Student s WHERE s.lastName=:theData", Student.class);
```

Here, we have another example, `select s FROM Student s WHERE s.lastName` equals
whatever the data parameter that we're going to make use of.
Again, `s` is a reference to the entity and then `.lastName`, 
that's an actual field on the actual entity or the Java class.
Alright, so that's a good little piece of information here regarding some of the strict JPQL
in regard to the `select` clause.

Let's take a look at our development process of how we can add this into our DAO application:

1. Step 1: Add this new method to the DAO interface
2. Step 2: Add a new method to the DAO implementation
3. Step 3: Update the main application

Let's swing into our IDE and then step 1: Add a new method to the DAO interface.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();
}
```

I'll move in here, and I'll define this method, _findAll_, and it'll return a list of students.
I'll go ahead and fix the imports here for a second.
Make sure you import on `java.util.list`.
And now in step 2: Add a new method to the DAO implementation.
We'll allow the IDE to help us with creating the stub for that method, _findAll_.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //return null;
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        
        // return query results
        return theQuery.getResultList();
    }
}

```

And before I start writing the code, let me write some quick comments just to keep myself on track.
The game plan is to **create the query**, and **return query results**.
I'll go ahead and set up this **TypedQuery** of _student_, _theQuery_, 
and then I have `entityManager.createQuery` "`FROM Student`, `student.class`".
And remember `FROM Student`, **Student** is the actual JPA entity, the class name.
And also remember that this is not the name of the database table.
All JPQL syntax is based on the entity name, and entity fields, crucial.
Now that we have the query, then we can actually return the query results by saying
_theQuery_ that _getResultList_, and kind of stepping back here,
and that's all the main coding here for this _findAll_ method.

And just kind of moving right along here in step 3: Update our main application.
I'll move in here to the _commandLineRunner_.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
            
            queryForStudent(studentDAO);
		};
	}

    private void queryForStudent (StudentDAO studentDAO) {
        
        // get a list of students
        
        // display list of students
        
    }

	private void readStudent (StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents (StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent (StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
```

I'll comment out _readStudent_, and I'll call this new method _queryForStudents_.
And I pass in the _studentDAO_.
Let the IDE create the stub for us for this method.
Let me write some quick comments to kind of keep myself on track.

```java
private void queryForStudent (StudentDAO studentDAO) {

    // get a list of students
    List<Student> theStudents = studentDAO.findAll();
    
    // display list of students
    for (Student tempStudent : theStudents) {
        System.out.println(tempStudent);
    }
}
```

Okay, we want to get a list of students, and then display that list of students.
I'll dive in here and start writing the code.
I'll set up this list of students, equal studentDAO.findAll.
Fix the imports there for `java.util.list`.
And now just do a simple for loop here to display the list of students, by doing 
`System.out.println(tempStudent)`.
And that's basically it here for the coding for _queryForStudents_.
Let's go ahead and run this application and test it out.

```html
Student{id=1, firstName='John', lastName='Doe', email='john@luv2code.com'}
Student{id=2, firstName='Mary', lastName='Public', email='mary@luv2code.com'}
Student{id=3, firstName='Bonita', lastName='Applebum', email='bonita@luv2code.com'}
Student{id=4, firstName='Daffy', lastName='Duck', email='daffy@luv2code.com'}

Process finished with exit code 0
```

And success, the application printed out for students that it retrieved from the database.
And let's swing over to MySQL workbench, and verify this.
So we have the four students from the database also.
And this matches with what our application displayed.
Now let's swing back into our IDE, and I want to make a small enhancement to the application.
I want to sort by last name.
At the moment, things are just coming out the way we enter the data,
and I want to sort it alphabetically by last name.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //return null;
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);

        // return query results
        return theQuery.getResultList();
    }
}
```

Let's move back into our **studentDAOImpl**, and let's modify our query here,
"`FROM student order by lastName`", and in this case,
_lastName_ is the field of the **JPA** entity.
So that's the field from your Java class, not the database column but the actual **JPA** entity.
By default, when you say 

* `order by lastName`, it'll sort ascending, kind of `A to Z`
* `order by lastName` descending, `desc`, from `Z to A`

Or we could be very explicit, and we could say `order by lastName`, `asc`, that's for ascending, 
so up to you, but by default it's going to sort ascending.
And I'll move in here, and I'll just be very explicit here.
I'll just say `asc` for ascending, and let's run our application and test it out.

```html
Student{id=3, firstName='Bonita', lastName='Applebum', email='bonita@luv2code.com'}
Student{id=1, firstName='John', lastName='Doe', email='john@luv2code.com'}
Student{id=4, firstName='Daffy', lastName='Duck', email='daffy@luv2code.com'}
Student{id=2, firstName='Mary', lastName='Public', email='mary@luv2code.com'}

Process finished with exit code 0
```

And excellent, so it's sorting by lastName, ascending A to Z,
so we have `Bonita Applebum`, `Doe`, `Duck`, `Public`.
We could also swing back in here, and modify to sort descending, `order by lastName desc`,
descending, and let's run it one more time.

```html
Student{id=2, firstName='Mary', lastName='Public', email='mary@luv2code.com'}
Student{id=4, firstName='Daffy', lastName='Duck', email='daffy@luv2code.com'}
Student{id=1, firstName='John', lastName='Doe', email='john@luv2code.com'}
Student{id=3, firstName='Bonita', lastName='Applebum', email='bonita@luv2code.com'}

Process finished with exit code 0
```

And excellent, so it's sorting by lastName descending, Z to A, `Public`, `Duck`, `Doe`, `Applebum`.
And I can also test out the default by simply just removing desc, and now run the app and excellent.
So it's sorting by last name, ascending A to Z.

I want to do one small minor change in my _StudentDAOImpl_.
I want to remove the order by portion of it.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //return null;
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        // TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);

        // return query results
        return theQuery.getResultList();
    }
}
```

So I just kinda remove that piece and just have `FROM Student`.
Now, I want to add a new method to find a student by their last name.
The first thing I'll do is I'll go through and add the method to the DAO interface.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();
    
    List<Student> findByLastName(String theLastName);
}
```

We'll return a list of students, and I'll say _findByLastName_,
then pass in a string, _theLastName_.
And now I'll move to the DAO implementation, and I'll implement that given method.
I have this method stub.
Let me move in here and write some quick comments to myself.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //return null;
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        // TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        
        // create query
        
        
        // set query parameters
        
        
        // return query results
        return List.of();
    }
}
```

Here I'll just go through, and I'll create the query,
I'll set the query parameter, and then return the query results.

```java
@Override
public List<Student> findByLastName(String theLastName) {
        
    // create query
    TypedQuery<Student> theQuery = entityManager.createQuery(
                                        "FROM Student WHERE lastName=:theData", Student.class);
        
    // set query parameters
    theQuery.setParameter("theData", theLastName);
        
    // return query results
    return theQuery.getResultList();
}
```

Find our **TypedQuery** of **Student**, and I'll make use of `entityManager.createQuery`
and get the JPL query string, `FROM Student WHERE lastName` equals,
I use a `:theData`, comma, `Student.class`.
And let me knock this down to another line just so we can kind of see everything
on one screen without having to scroll back and forth.
And let me highlight this item here, `:theData`.
JPQL name parameters are prefixed with a colon.
And kind of think of this as a placeholder that we'll fill in later.
And we'll fill it in here in this next line of set the query parameters.
I get the name of the parameter, comma, the value.
Again, the name parameter is `theData` and then the actual value is `theLastName`.
That's the value that we're passing into this given method.
The nice thing about this is the approach that we're no longer hard coding it
like `where LastName` equals `Doe`.
We can pass in any parameter value for it.
And the last piece is very straightforward, right?
We simply return `theQuery.getResultList`.
And that's it there for the main coding here for this new method, _findByLastName_.
Now let's swing into our main application.
Move down here to our _commandLineRunner_.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			
            queryForStudentByLastName(studentDAO);
		};
	}

    private void queryForStudentByLastName (StudentDAO studentDAO) {
        
        // get a list of students
        
        // display list of students
        
    }

	private void queryForStudent (StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent (StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents (StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent (StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
```

We'll comment out our previous method _queryForStudents_, 
and then we'll call this new method _queryForStudentsByLastName_.
We're moving here into this method stub.
Write some quick comments to ourselves, get a list of students, and then display that list of students.

```java
private void queryForStudentByLastName (StudentDAO studentDAO) {
        
    // get a list of students
    List<Student> theStudent = studentDAO.findByLastName("Duck");
    
    // display list of students
    for (Student tempStudent : theStudent) {
        System.out.println(tempStudent);
    }
}
```

I'll set up theStudents equals `studentDAO.findByLastName` and we pass in a last name of `Duck`.
Hopefully we'll find `Daffy Duck`.
And then I'll simply do a list, and I'll do a print line on _tempStudent_.
And that's the coding there for _queryForStudentsByLastName_.
Now let's run our application and test this out.

```html
Student{id=4, firstName='Daffy', lastName='Duck', email='daffy@luv2code.com'}

Process finished with exit code 0
```

Okay, great.
So it said student ID of four, first name `Daffy`, last name `Duck`.
Okay, that matches.
And now let's swing over to MySQL Workbench and verify this.
And we can see that the student ID of four, Daffy Duck.
Excellent, this all matches as desired.
And let's go through and just change the last name one more time.
Last name of `Doe`.
Save that and run it.

```html
Student{id=1, firstName='John', lastName='Doe', email='john@luv2code.com'}

Process finished with exit code 0
```

And we get this given output, student ID of one.
And then verifying this in our MySQL Workbench and good.
So this all looks really good.
We're really happy here that our query works as desired 
using these JPQL named parameters and our `WHERE` clause.
</div>

## [Updating Objects with JPA]()
<div style="text-align:justify">

In this section, we'll learn how to update an object.
And we're making some really good progress here with building a `CRUD` app.
We've covered the `create`, and `read` aspect.
Here, we'll focus on updating an object. 

```java
Student theStudent = entityManager.find(Student.class, 1);

// change first name to "Scooby"
theStudent.setFirstName("Scooby");

entityManager.merge(theStudent);
```

Now, to `update` an object, we first find the object by using `entityManager.find`.
Then we simply call a setter method on the objects to actually change their value.
There, we go ahead and perform an `entityManager.merge`.
So the _merge_ portion here will tell the entity manager
to update this given object or update the entity.

```java
int numRowsUpdated = entityManager.createQuery("UPDATE Student SET lastName='Tester'").executeUpdate();
```

Now, the previous example was for updating a single object.
You can also update multiple objects.
Here, we're going to update the last name for all students.
I'll make use of this `entityManager.createQuery`,
I'll perform an `UPDATE Student`, and I'll `SET lastName='Tester'`.
So we're basically changing the last name for everyone to have the _lastName_ of `Tester`.
And remember here, `UPDATE Student`, `Student` is the name of the **JPA** Entity, the class name,
and _lastName_ is the field of the **JPA** Entity.
And then we call _executeUpdate_ to actually execute this given statement.
And now this statement will execute, perform the work,
and then it'll return the number of rows that were updated.
So you could say, "_Hey, we updated five rows or updated 100 rows._"

Here's the development process for adding this to our DAO:

1. Add the new method to the DAO interface
2. Add the new method to the DAO implementation
3. Wrap it up by updating our main application

Alright, starting with step 1: Add a new method to the DAO interface.

```java
import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    // ...
    
    void update(Student theStudent);
}
```

We'll have this method here called _update_, and we'll pass in _theStudent_.

```java
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
// ...
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;
    // ...

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }
}

```

And then in step 2: Defining the DAO implementation,
we'll have this method here, update that takes a student,
and it will make use of this `entityManager.merge`.
We pass in _theStudent_.
This will perform an update for this given student.
Now, also notice here that we add the `@Transactional` annotation,
since we're actually performing an update on the database.
In the previous examples, like for all the queries and reads, 
those were kinda read-only, so there was no need for the `@Transactional` annotation,
but here, we're actually performing an update or a modification, 
so here, we need to make use of the `@Transactional` annotation.

```java
package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import java.util.List;
// ...

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {

			updateStudent(studentDAO);
		};
	}

    // ...
}
```

And then finally, here in step 3: Update our main application.
In our section here for our _commandLineRunner_, we call this method _updateStudent_,
and then we define the method for _updateStudent_.

```java
private void updateStudent(StudentDAO studentDAO) {

    // retrieve student based on the id: primary key
    int studentId = 1;
    System.out.println("Getting student with id: " + studentId);
    Student myStudent = studentDAO.findById(studentId);

    // change first name to "Scooby"
    System.out.println("Updating student...");
    myStudent.setFirstName("Scooby");

    // update the student
    studentDAO.update(myStudent);

    // display updated student
    System.out.println("Updated student: " + myStudent);
}
```

Inside the method, we simply retrieve the student based on their ID or their primary key,
make use of the `studentDAO.findById`.
We pass on the _studentId_ of `1`, and then we change the _firstName_ of the student to `Scooby`.
Then we make use of our DAO method, `studentDAO.update`,
and that'll call the method that we created in the previous code.
And then finally, we print out the updated student.

Now let's go ahead and run this application and test it out.

```html
Getting student with id: 1
Updating student...
Updated student: Student{id=1, firstName='Scooby', lastName='Doe', email='john@luv2code.com'}

Process finished with exit code 0
```

And excellent, so it says, getting student ID of `1`, updating the student,
and then finally printing out the updated student.
Notice here, the first name is `Scooby`.
Now let's go ahead and swing over to MySQL Workbench and let's verify this.
Go to my student table, do a query:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image30.png?raw=true" alt="image30">
</div>

And success.
Notice here, student ID of `1`, the first name is now `Scooby`,
and this matches with the actual code output that we had from our IDE.
Now let me swing back into my application and make a slight update here.

```java
private void updateStudent(StudentDAO studentDAO) {

    // retrieve student based on the id: primary key
    int studentId = 1;
    System.out.println("Getting student with id: " + studentId);
    Student myStudent = studentDAO.findById(studentId);
    
    // change first name to "Scooby"
    System.out.println("Updating student...");
    //myStudent.setFirstName("Scooby");

    // change first name to "John"
    myStudent.setFirstName("John");

    // update the student
    studentDAO.update(myStudent);

    // display updated student
    System.out.println("Updated student: " + myStudent);
}
```

I'll change the name back to `John`.
So just quick updates here in the code.
Be sure to save all that stuff and run it one more time.

```html
Getting student with id: 1
Updating student...
Updated student: Student{id=1, firstName='John', lastName='Doe', email='john@luv2code.com'}

Process finished with exit code 0
```

Here it says the first name of `John`, which is good.
Swing back into our MySQL Workbench, run our query one more time

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image31.png?raw=true" alt="image31">
</div>

And great.
So this kind of matches up with what we had.
So you can see here we're successful and updating a student.
We retrieved the student, made some modifications to it, and then we saved it to the database accordingly.
</div>

## [Deleting Objects with JPA]()
<div style="text-align:justify">

Now, we'll learn how to delete an object.
And with our development process for **JPA CRUD** Apps, we're almost done.
So we've covered the `create`, `read`, and `update`.
The final item here is performing a `delete` on a given object
and that'll cover the major steps for **CRUD** development.

```java
// retrieve the student
int id = 1;
Student theStudent = entityManager.find(Student.class, id);

// delete the student
entityManager.remove(theStudent);
```

To delete a student, we call this `entityManager.find`,
pass any `id`, the primary key of that given student.
Then we call `entityManager.remove`.
That'll actually delete the student from the database.

```java
int numRowsDeleted = entityManager.createQuery(
                "DELETE FROM Student WHERE lastName='Smith'")
                .executeUpdate();
```

We can also delete multiple students based on a condition.
Here I can say `entityManager.createQuery`,
`DELETE FROM Student WHERE lastName` equals `Smith`.
Here I'm basically deleting all students who have the last name of `Smith`.
And notice here the syntax `DELETE FROM Student`,
`Student` is the name of the **JPA** entity, the class name,
and last name's the actual field of the **JPA** entity.
Then I make use to this `executeUpdate` to execute this statement.
Now, one thing to be aware of here, you're probably wondering, well, we're doing a `DELETE`.
Why does it say `executeUpdate`?
Well, in the **API**, the method name `Update` is simply a generic term.
It basically means that we're **modifying** the database.
So we could be performing an `Update`, performing a `Delete`, or whatever.
We're simply doing something to the database.
So don't think of this as trying to match to the actual JPQL that we're using,
here it's just a generic term.
We're simply **modifying** the database.
And once this statement is executed, then it'll return the _numRowsDeleted_.
Here, we'll say that's the _numRowsDeleted_ that were deleted.
So you could tell the user, "_Hey, I perform this operation and I deleted X number of rows._"

```java
int numRowsDeleted = entityManager
                            .createQuery("DELETE FROM Student")
                            .executeUpdate();
```

Now you could also delete all the students from a database.
Here I'll make use of this `entityManager.createQuery`, `DELETE FROM Student`.
And notice here, there are no conditions.
We're just going to delete all the students.
And then I have this `.executeUpdate()`.
Again, it'll return the number of rows that were deleted.

Now, let's look at the development process for integrating this into our DAO application:

1. Add the new method to the DAO interface
2. Add the new method to the DAO implementation
3. Update the main app.

```java
import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    
    // ...
    
    void delete(Integer id);
}
```

Step 1: Adding a new method to the DAO interface.
We have this new method here, _delete_, we pass in an Integer id.
And then in step 2: Define the DAO implementation.

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
// ...

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;
    // ...

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }
}
```

We add this new method here, _delete_, and `Integer id`.
We make use of this `entityManager.find`, `Student.class` comma `id`.
And then we also say `entiteManager.remove`, `theStudent`.
And also we add the `@Transactional` annotation since we're performing a **delete**,
we're actually _modifying_ the database by executing this given code.
And then finally in step 3: Update the main application.

```java
import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
// ...

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			// queryForStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
			
            deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
        // delete the student
        int studentId = 3;

        System.out.println("Deleting student id: " + studentId);
        
        studentDAO.delete(studentId);
	}
    
    // ...
}
```

We move here into our _commandLineRunner_ section.
We call this method _deleteStudent_.
And then here's the coding for _deleteStudent_.
In this example, we have the _studentId_ of `3`, 
and then we make use of this `studentDAO.delete(studentId)`.
And that _delete_ method was implemented on the previous code for the given DAO.
Now, before we run this application, let's verify that we have data in the database,
and in particular the `studentId` of `3`.
I'll swing over to MySQL Workbench.
I'll just run a query here on this student table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image32.png?raw=true" alt="image32">
</div>

And we do have the `studentId` of `3`, `Bonita Applebum`.
Now let's go ahead and run our application.

```html
Deleting student id: 3

Process finished with exit code 0
```

And it says, hey, deleting `studentId` of `3`.
Let's verify this in the database.
Just run that query one more time.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image33.png?raw=true" alt="image33">
</div>

And success.
Notice here, `studentId` of `3` has been deleted.
No longer there.
So this kind of works out as desired.

I'd like to also add codes to our application
to **delete** all the students from the database.
Let's swing back into our IDE, and we'll start off by adding a new method here
to our **studentDAO** interface, and this is for deleting all the students.

```java
import com.luv2code.cruddemo.entity.Student;

public interface StudentDAO {
    
    // ...
    
    int deleteAll();
}
```

I'll have this method that returns an `int` because we want to know 
how many people we deleted from the database.
I'll call it `deleteAll()` and that's it.
So that's our new method here, `deleteAll()`.
Now let's go ahead and move into our DAO implementation,
and let's go ahead and implement this method. 

```java
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
// ...

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;
    // ...

    @Override
    @Transactional
    public int deleteAll() {
        // retrieve the student
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        // delete the student
        entityManager.remove(theStudent);
    }
}
```

I'll add the `@Transactional` annotation since we're performing a **delete**,
or we're **modifying** the database.
I'll set up this integer, numRowsDeleted,
I'll call `entityManager.createQuery` and I'll say `DELETE FROM Student`.
And then we execute this statement.
Now remember the method name **update** is just a generic term
more simply **modifying** the database.
And then we simply return the number of rows deleted and that's it.
That's all the coding here that we need for this method.
Now the final step here is just updating the main application.

```java
import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
// ...

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			// queryForStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
            // deleteStudent(studentDAO);
            
            deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {

        System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);        
	}
    
    // ...
}
```

Just moving down to our _commandLineRunner_ section,
let's comment out our previous line of code, and then we'll call this new method, _deleteAllStudent_.
We'll simply generate this method stub.
Just add a quick outline of what we're doing here, we're deleting all students.
We know that this _deleteAll_ was going to return our integer
as far as the number of rows deleted, so we simply make an assignment here,
and then we call `studentDAO.deleteAll`.
And finally, we print out how many rows were deleted.
Now, before we run this application again, let's verify that we have data in our database.
Let's go ahead and swing over to MySQL Workbench.
Just do a refresh on our query.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image34.png?raw=true" alt="image34">
</div>

So at the moment now we have three students, okay.
Let's go ahead and run our main application

```html
Deleting all students
Deleted row count: 3

Process finished with exit code 0
```

And great, it says deleting all students,
deleted row count equals three.
Swing back into our MySQL Workbench and do a query again: 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image35.png?raw=true" alt="image35">
</div>

And awesome.
So, notice here everyone's been deleted
and this matches up with the information presented by our application.
</div>

## [Creating Database Tables from Java Code]()
<div style="text-align:justify">

In this section, we're going to create database tables from Java code.
So previously we created the database tables by running an SQL script,
and we would run that script in the MySQL Workbench.
But there's another option here.
**Hibernate** provides the option to auto **automagically**
create the database tables for you.
So it'll actually create the tables based on Java code with **JPA/Hibernate** annotations.
And this is very useful for development and testing.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image36.png?raw=true" alt="image36">
</div>

So basically you have your Java code with the annotations
you run it through this **Hibernate** tool, and then it'll actually generate the SQL
and also execute that SQL and apply it to the given database.
So there's no need for you to write any of the SQL.
It'll actually generate and apply it on the fly, which is really cool.

```properties
spring.jpa.hibernate.ddl-auto=create
```

So in the **Hibernate** configuration file, you'll set up `application.properties`,
and you give a property value.
Here we'll call it `create`. 
So when you run your application, **Hibernate** will **drop** the tables
and then **create** them again from scratch.
And these are is all based on the **JPA/Hibernate** annotations that are in your Java code.

```java
@Entity
@Table(name="student")
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    // ... constructors, getters / setters
}
```

We have our student, and we have our **JPA/Hibernate** annotations here.
So it'll create a table called **student**, and then we have `id`
and then we also have all the columns.

```sql
create table student (id integer not null auto_increment, 
                      email varchar(255),
                      first_name varchar(255),
                      last_name varchar(255),
                      primary key (id))
```

So **Hibernate** will use this information to actually generate the SQL
and execute SQL and apply it to the given database.
So **Hibernate** will do all of this work for you behind the scenes, which is really cool.

```properties
spring.jpa.hibernate.ddl-auto=PROPERTY-VALUE
```

So there are different property values that you can set here.

| Property Value | Property Description                                                                                                     |
|----------------|--------------------------------------------------------------------------------------------------------------------------|
| `none`         | No action will be performed                                                                                              |
| `create-only`  | Database tables are only created                                                                                         |
| `drop`         | Database tables are dropped                                                                                              |
| `create`       | Database tables are dropped followed by database tables creation                                                         |
| `create-drop`  | Database tables are dropped followed by database tables creation.<br/> On application shutdown, drop the database tables |
| `validate`     | Validate the database tables schema                                                                                      |
| `update`       | Update the database tables schema                                                                                        |


So one value you can say is `none`.
So in that case, no action will be performed.
There's a `create-only`, so the database tables are only created.
For `drop`, database tables are dropped.
Now, when we say `drop`, that means that all data is lost,
everything goes away, you lose everything.
So for `create` option, there's the database tables are dropped,
followed by a database table creation.
For `create-drop`, it will drop the database tables and recreate on startup.
So basically when your application finishes running,
it'll actually drop the tables, so you'll have nothing at the end of the application.
And this is primarily useful for unit testing.
They also have the `validate` option to validate the database table schema.
And then there's `update`.
So if you add any new fields to your entity, it'll provide the appropriate updates
on the given database table.
For ease of development and testing, we'll actually use auto-configuration.

```properties
spring.jpa.hibernate.ddl-auto=create
```

So we'll make use of the property for `create`.
And so the database tables are **dropped** first and then **created** from scratch.
And so again, just a reminder, when database tables are **dropped**, all data is **lost**.
But this is fine just for dev and testing.
If you want to create the tables once and then keep the data,
then make you so the `update` config.

```properties
spring.jpa.hibernate.ddl-auto=update
```

However, be aware this will alter the database schema based on the latest code updates.
Be **very** careful here.
Only use this for basic projects because you could actually change the database schema
with this configuration, and it may affect other applications using that given database.

Now also, kind of my warning here, I want to just kind of full disclosure here.
Disclaimer, don't do this on **Production** databases.
You don't want to **drop** your **Production** data because if you drop your **Production** data,
all data is deleted.
And that's a really bad position to be in as a developer
and having to talk to your manager or your IT team and say, 
"_Hey I dropped all the production data._"
I understand there are backups out there and so on.
But you simply don't want to be in that position of having to say, 
"_I screwed up. 
I made a problem, I deleted all the data._"
So just be very careful here.
And instead, for production, you should have DBAs run SQL scripts
and let them manage the production data.
You want to try and stay hands off as much as possible.

Now, you may wonder, "`Well, what's the use case for this configuration?`"
Well, automatic table generation is useful for database integration testing using in-memory databases.
And it's also really good for basic hobby projects
where you're the only developer working on it in a small isolated fashion.

And so my recommendation here is in general,
I don't recommend auto generation for enterprise, real-time projects
because you can very easily drop production data if you're not careful.
Instead, I recommend using SQL scripts.
Corporate DBAs prefer SQL scripts for governance and code reviews.
The SQL scripts can be customized and fine-tuned for very complex database designs.
And you can also version-control these scripts.
And finally, you can make use of these SQL scripts to work with schema migration tools
such as **Liquibase** and **Flyway**.
The key takeaway here, table generation is very useful for small, basic hobby projects
that you're working with on your own.
For real-time, real world applications, make use of SQL scripts.
Let's test out this configuration.


The first thing I'd like to do is add some logging configs to log the SQL statements.
This will help us out, as we see how the configurations work, and also the actual, 
the SQL statements that are actually executed on the database.
Very good for diagnostics, and also as an academic exercise.
And what I'll do here is, I'll open up my `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn

# Add loging configs to display SQL statements
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.orm.jdbc.bind=trace
```

I'll add these logging configs here, to log the SQL statements.
So there are two configs that we need to add.
I'll set the `logging.level.org.hibernate.SQL` to `debug` to see **log SQL statements**,
so we're basically going to get some `debug` logs here.
And also I'll set the `logging.level.org.hibernate.orm.jdbc.bind=trace`
to see **Log values for SQL statements**.
This allows seeing the actual values that are being assigned for these statements.
Let's go ahead and save this file, and let's go ahead and move over to our main application.

```java
import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
// ...

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// System.out.println("Hello World");
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			// queryForStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
            // deleteAllStudent(studentDAO);
		};
	}

	// ...
}
```

I want to make some small modifications here in the app.
Move down to this _commandLineRunner_ section, 
and I'll comment out the code for _deleteAllStudent_,
and then I'll uncomment the code for _createMultipleStudents_.
And this is the code that'll basically add three students to our database.
Let's go ahead and run this application, and test it out real quickly.

```html
Creating 3 student objects...
Saving the students...
2024-05-26T15:11:46.285+03:00 DEBUG 50356 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:11:46.297+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [john@luv2code.com]
2024-05-26T15:11:46.297+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
2024-05-26T15:11:46.297+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Doe]
2024-05-26T15:11:46.327+03:00 DEBUG 50356 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:11:46.327+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [mary@luv2code.com]
2024-05-26T15:11:46.327+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Mary]
2024-05-26T15:11:46.327+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Public]
2024-05-26T15:11:46.335+03:00 DEBUG 50356 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:11:46.336+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [bonita@luv2code.com]
2024-05-26T15:11:46.336+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Bonita]
2024-05-26T15:11:46.336+03:00 TRACE 50356 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Applebum]

Process finished with exit code 0
```

Notice here we have some new logging statements here for debug and trace.
Just scrolling over here, we can see the actual `insert` statement, 
and also the values that they'll insert.
So this is for `John Doe`, and a similar insert here for `Mary Public`.
And then finally, my favorite, `Bonita Applebaum`.
And so we basically just added these configs here, for logging that data.
And let's just do a quick query here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image37.png?raw=true" alt="image37">
</div>

And yes, we have the data.
Now one thing to notice here, is that we have different IDs,
since we ran the app multiple times before, right?
We added some items, we deleted some items, and all that good stuff.
So that's why you see some slight differences here, on the actual ID.
Okay, what I'd like to do now is break the app on purpose.
I actually want to drop the table for **student**, such that it's no longer there.
We no longer have the table, no longer have the data, and run our app, and see what happens.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image38.png?raw=true" alt="image38">
</div>

I'll grab this **student** table, and then I'll drop the table, and I'll choose the option drop now.
And now, if I run my query again, then,

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image39.png?raw=true" alt="image39">
</div>

If you notice, at the bottom, we have an error message,
and it says table **student** does not exist.
Alright, so the table's dropped.
It no longer exists.
Even if we do a refresh over here on the left-hand side,
we'll see that there are no tables right now,
we only had one table, and that one table was **student**, and we dropped that table.
Now let's go ahead and move back into our application.
Let's run our application and see what happens.

```html
Creating 3 student objects...
Saving the students...
2024-05-26T15:20:08.512+03:00 DEBUG 48112 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:20:08.538+03:00 TRACE 48112 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [john@luv2code.com]
2024-05-26T15:20:08.539+03:00 TRACE 48112 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
2024-05-26T15:20:08.539+03:00 TRACE 48112 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Doe]
2024-05-26T15:20:08.563+03:00  WARN 48112 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 1146, SQLState: 42S02
2024-05-26T15:20:08.563+03:00 ERROR 48112 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : Table 'student_tracker.student' doesn't exist
2024-05-26T15:20:08.611+03:00 ERROR 48112 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.dao.InvalidDataAccessResourceUsageException: could not execute statement [Table 'student_tracker.student' doesn't exist] [insert into student (email,first_name,last_name) values (?,?,?)]; SQL [insert into student (email,first_name,last_name) values (?,?,?)]
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:277) ~[spring-orm-6.1.8.jar:6.1.8]
	at org.sprinframework.boot.SpringApplication.run(SpringApplication.java:1352) ~[spring-boot-3.3.0.jar:3.3.0]
    ... 41 common frames omitted
    at com.luv2code.cruddemo.CruddemoApplication.main(CruddemoApplication.java:16) ~[classes/:na]
Caused by: org.hibernate.exception.SQLGrammarException: could not execute statement [Table 'student_tracker.student' doesn't exist] [insert into student (email,first_name,last_name) values (?,?,?)]
	at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:66) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	at org.hiingframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-6.1.8.jar:6.1.8]
	... 71 common frames omitted
Caused by: java.sql.SQLSyntaxErrorException: Table 'student_tracker.student' doesn't exist
	at com.mernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:194) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
	... 65 common frames omitted


Process finished with exit code 1
```

And it fails as expected, right?
We have an exception, and it says table `student_tracker.student` doesn't exist.
We dropped our table, it no longer exists.


Now, I'd like to do is add a configuration here to auto create the table for me.
And let's move back into our `application.properties`,
and we'll add in this new configuration for auto create.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn

# Add loging configs to display SQL statements
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.orm.jdbc.bind=trace

# Configure JPA/Hibernate to auto create the tables
spring.jpa.hibernate.ddl-auto=create
```

I'll give this property here `spring.jpa.hibernate.ddl-auto=create`.
And remember, this property here will drop the table
and create the tables for our entity classes; every time the app is run.
And now we run the application.

```html
2024-05-26T15:25:12.912+03:00 DEBUG 43192 --- [           main] org.hibernate.SQL                        : drop table if exists student
2024-05-26T15:25:12.945+03:00 DEBUG 43192 --- [           main] org.hibernate.SQL                        : create table student (id integer not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
Creating 3 student objects...
Saving the students...
2024-05-26T15:25:13.311+03:00 DEBUG 43192 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:25:13.328+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [john@luv2code.com]
2024-05-26T15:25:13.329+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
2024-05-26T15:25:13.329+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Doe]
2024-05-26T15:25:13.412+03:00 DEBUG 43192 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:25:13.413+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [mary@luv2code.com]
2024-05-26T15:25:13.413+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Mary]
2024-05-26T15:25:13.413+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Public]
2024-05-26T15:25:13.429+03:00 DEBUG 43192 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:25:13.429+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [bonita@luv2code.com]
2024-05-26T15:25:13.429+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Bonita]
2024-05-26T15:25:13.429+03:00 TRACE 43192 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Applebum]
```

And let's take a look at our logs here.
Then we see that it dropped the table, if it exists for **student**,
and then it'll actually go through and create the table.
I wanted it to auto-create the table for me,
and that's the actual SQL that's being applied to the database.
And then we can move down here, and we can see the `insert` statements that are happening
for those three students that we're inserting.
Let's swing back into our MySQL workbench, let's run our query again.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image40.png?raw=true" alt="image40">
</div>

We have our data, **John**, **Mary**, and **Bonita**.
As you can see, there was no need for us to run an SQL script.
**JPA hibernate** actually did the work for us in the background.

Now let's go ahead and run the app a couple more times.
So I just ran it once.
Let's run it again.
That was two runs.
And notice here, every time it runs, it drops the table and then creates the table.
So you lose any previous data that was out there.
And let's verify this in our MySQL Workbench.
Notice here we only have three students.
Even though we ran the app multiple times, every time it would run,
it would drop the table and create it again from scratch.
Anyway, I just wanted you to be aware of how that are given configuration property works.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn

# Add loging configs to display SQL statements
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.orm.jdbc.bind=trace

# Configure JPA/Hibernate to auto create the tables
# the "update" config will keep existing data in the table
spring.jpa.hibernate.ddl-auto=update
```

Now let's go ahead and change this configuration to `update`
because what we'd like to do is keep the previous data.
And now let's go ahead and run our application one more time.

```html
Creating 3 student objects...
Saving the students...
2024-05-26T15:31:26.016+03:00 DEBUG 56484 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:31:26.028+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [john@luv2code.com]
2024-05-26T15:31:26.028+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
2024-05-26T15:31:26.028+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Doe]
2024-05-26T15:31:26.086+03:00 DEBUG 56484 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:31:26.087+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [mary@luv2code.com]
2024-05-26T15:31:26.087+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Mary]
2024-05-26T15:31:26.087+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Public]
2024-05-26T15:31:26.094+03:00 DEBUG 56484 --- [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
2024-05-26T15:31:26.095+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [bonita@luv2code.com]
2024-05-26T15:31:26.095+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Bonita]
2024-05-26T15:31:26.095+03:00 TRACE 56484 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Applebum]

Process finished with exit code 0
```

And now swing back over to our MySQL Workbench.
Run our query one more time.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image41.png?raw=true" alt="image41">
</div>

And notice here we have our old data that we added previously.
And now here's the new data added with that most recent run.
So it's keeping the old data and then also adding the new data.
So it's not dropping the table, simply using the existing table and adding the data accordingly.
And now let's run it a couple more times.
So that's one, run it one more.
So two more runs here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-spring-boot-hibernate-jpa-crud/images/image42.png?raw=true" alt="image42">
</div>

And so notice here we ran it twice, so that's six people that we added 
or six students that we added, and that's the new data that we have for our application.
Anyway, just wanted to show you how the configuration properties work
for automatically creating the database tables from Java Source Code.
</div>