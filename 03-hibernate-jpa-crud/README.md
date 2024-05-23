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

![image01](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-hibernate-jpa-crud/images/image01.png?raw=true)

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

![image02](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-hibernate-jpa-crud/images/image02.png?raw=true)

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

![image03](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-hibernate-jpa-crud/images/image03.png?raw=true)

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

![image04](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/03-hibernate-jpa-crud/images/image04.png?raw=true)

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
</div>

## [Setting Up Database Table]()
<div style="text-align:justify">


</div>

## [Setting Up Spring Boot Project]()
<div style="text-align:justify">


</div>

## [JPA Annotations]()
<div style="text-align:justify">


</div>

## [JPA Annotations]()
<div style="text-align:justify">


</div>

## [Saving a Java Object with JPA]()
<div style="text-align:justify">


</div>

## [Reading Objects with JPA]()
<div style="text-align:justify">


</div>

## [Querying Objects with JPA]()
<div style="text-align:justify">


</div>

## [Querying Objects with JPA]()
<div style="text-align:justify">


</div>

## [Updating Objects with JPA]()
<div style="text-align:justify">


</div>

## [Deleting Objects with JPA]()
<div style="text-align:justify">


</div>

## [Creating Database Tables from Java Code]()
<div style="text-align:justify">


</div>