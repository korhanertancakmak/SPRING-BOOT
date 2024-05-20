# [Spring Boot - Quick Start]()

In this course, you will learn how to:

* develop Spring Boot applications
* leverage Hibernate/JPA for database access
* develop a REST API using Spring Boot
* create a Spring MVC app with Spring Boot
* connect Spring Boot apps to a Database for CRUD development
* apply Spring Security to control application access
* leverage all Java configuration (no xml) and Maven

## [Spring Boot - Overview]()
<div style="text-align:justify">

Spring's a very popular framework for building Java applications.
It provides a large number of helper classes and annotations.
But now, the problem is that building a traditional Spring 
application is really hard, because you have a couple of questions.

* Which JAR dependencies do I need for this Spring project?
* How do I set up configuration?
* Should I use XML configuration or Java configuration?
* How do I install the server?
Tomcat, JBoss, WebSphere and so on.

And that's just the basics for getting started.
You haven't even really started building your real application yet.
So this is where the **Spring Boot** solution comes into play.

* Spring Boot makes it easier to get started with Spring development.
* It minimizes the amount of manual configuration you have to do.
* Spring Boot will perform the auto-configuration based on your properties file 
and the JAR classpath.
* It also helps to resolve dependency conflicts (Maven or Gradle).
* Spring Boot also provides an embedded HTTP server, 
so you can get started quickly.

So out of the box, it has support for embedded Tomcat, Jetty or Undertow, 
and those are simply HTTP servers 
that you can embed in your Spring Boot application.

Now what's the relationship between **Spring Boot** and **Spring**?
Well, Spring Boot uses Spring behind the scenes.
Spring Boot simply makes it easier to use Spring.
So at a very high level, you're using Spring Boot, 
but behind the scenes, there's still Spring code running.
So you'll need to learn Spring Boot and also learn Spring, 
and we'll cover all of that during this course.

Now Spring Boot provides the **Spring initializer**.
So this is a website for quickly creating a starter spring project.

![image01](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image01.png?raw=true)

Basically, you go to this website at [start.spring.io](https://start.spring.io/).
You select your dependencies, 
and then it'll actually create a **Maven** or **Gradle** project for you 
that you can download and import it into your IDE.
So you can import it into Eclipse, IntelliJ, NetBeans or so on.
Or you could use a plain text editor 
and use **Maven** at the command line.
And then you're okay and ready to go.
And we'll actually use Spring Initializer a lot 
in some of the following sections with setting up our **Spring Boot** projects.
So you'll get a lot of hands on practice with this.

So Spring Boot provides an embedded HTTP server,
so you can get started quickly.
So it has a port for Tomcat, Jetty, and Undertow.
So again, like I mentioned earlier, 
there's no need to install a server separately.
So you'll have your application like `mycoolap.jar`
so this JAR file will include your application code,
and it'll also include the embedded server.
So we'll have Tomcat embedded as part of your JAR file.
So the nice thing about this is that it's a self-contained unit.
There's nothing else that you have to install.
So there's no separate Tomcat installation you need
or JBoss installation or whatever.
You actually have the application server as part of your code, 
and you can run it independently, 
or you can run your application standalone.

So basically we can run our app standalone
because our apps include the embedded server.
So if I wanted to run my **Spring Boot** app, 
I could run it from the IDE 
or I could run it from the command line.
So again, we have this `mycoolapp.jar` 
that includes my code and also the embedded server, in this case Tomcat.
So at my command line, I could simply type `> java -jar mycoolapp.jar`.
In this case, `mycoolapp.jar` will actually run my application,
and it'll also spin up the server.
And my app is up and running in a standalone fashion.
Okay, so then you're probably wondering, 
all right well that's cool to kinda run it as just a Jar file,
but you know, my manager wants me to deploy our applications in a traditional way,
like my manager wants me to deploy a WAR file.
Well, no problem.

Spring Boot apps can also be deployed in the traditional fashion.
So you can deploy a WAR file to an external server like Tomcat, JBoss, 
or WebSphere, and it can work just like you would use it in the past.
All right, so here we have this Tomcat server.

![image02](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image02.png?raw=true)

Let's say it's deployed somewhere on your corporate network.
Then you could take your Spring Boot app, and then 
you can deploy that Spring Boot app as a WAR file with `.war` extension.
So now as a war file, you only have your code included.
There's no need to have the embedded server
because now you're deploying it in a traditional sense.
There's already a Tomcat server installed running elsewhere,
and we're simply deploying our WAR file to that server.
So that piece is fine.
And then also on that Tomcat server, 
there may be other project teams 
that are deploying their apps in the traditional way too.
So you can have the travel team deploying our travel WAR,
the e-commerce group, the shopping group deploying that WAR.
So you can take your Spring Boot app,
and you can also deploy it as a WAR file
to a traditional server like you did in the past.
So there are always some frequently asked questions
that I get regarding Spring Boot.

* Does Spring Boot replace Spring MVC, Spring REST, etc ...?
The answer is no.
So there's no competition here.
Instead, Spring Boot actually uses those technologies in the background.
So you have your Spring Core, you have your Spring AOP Spring MVC, Spring REST.
Spring Boot can use all of those technologies in the background.
There's no competition, there's no replacement.
Spring Boot is mainly about configuration.
So once you do your Spring Boot configs,
then you can make use of the regular Spring coding.
So there's no competition, there's no replacement.
Again, Spring Boot is really about helping 
you get started quickly with minimal configuration.

* Does the Spring Boot code run faster than regular Spring code?
And again, the answer's no because behind the scenes 
Spring Boot uses the same code of the Spring framework.
And like I stated, earlier Spring Boot is all about making it easier 
to get started by minimizing the configuration.
But behind the scenes, you have your normal Spring Core, Spring REST, 
Spring MVC and so on.

* Do I need a special IDE for Spring Boot?
And again, the answer is no.
You can use any IDE for Spring Boot apps.
You can even use a plain text editor.
The Spring team actually provides a free Spring Tool Suite, STS, 
which is basically a collection of IDE plugins,
and some IDEs provide fancy Spring tooling support.
So it's not a requirement.
So feel free to use the IDE that works best for you,
or you use Maven, the command line and a plain text editor, totally up to you.
</div>

### Spring Boot Initializr Demo
<div style="text-align:justify">

In this section, we'll get a demo of the **Spring Boot Initializr**.
Earlier we discussed the Spring Initializr.
It's a website where you can quickly create a starter Spring project.
So, it's at `start.spring.io`.
We basically go here and select our dependencies.
It'll actually create a Maven/Gradle project for us,
and then we can actually import that project into our IDE, 
like Eclipse, IntelliJ, NetBeans, and so on.
That's the basic idea of the Spring Initializr,
and we'll actually use this in this demo coming up here.

Now let's have a quick word on Maven.
When building your Java project, you may need additional JAR files.
For example, Spring, Hibernate, Commons Logging JSON and so forth.
And one approaches to simply download the JAR files 
from each project website and then, 
manually add those JAR files to your build classpath.

However, Maven provides a nice solution.
We simply tell Maven the projects that you're working on the dependencies, 
like Spring, Hibernate, etc.
Maven will go out and download the JAR files for those projects for you.
And Maven will automatically make those JAR files available 
during compile and run.
And you can kind of think of Maven as like your friendly helper or your personal shopper.
You give Maven a shopping list, say, 
"hey, I need dependencies A, B, C, and D" 
and Maven will go out, grab those jar files, add them to your classpath
and make them available during compile and run.
A really nice feature, and we'll use Maven in this course.
Now, we'll just kind of cover the basics at the moment.
I have more information on Maven,
I'll give you later in the course, 
but for now just think of Maven as a nice utility
that'll actually download the JAR files,
and make them available to your project,
like your friendly helper or your personal shopper.

So for an actual development process,
the first thing we'll do is configure our project 
at the Spring Initializr website, `start.spring.io`.
We'll download the actual zip file that it creates for us,
and then we'll actually unzip that file.
And then finally, we'll import that Maven project into our IDE.
All right, so let's go ahead and dive in and let's get started.

![image03](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image03.png?raw=true)

Okay, so let's go ahead and open up a web browser.
And so, the first step is configuring a project
at the Spring Initializr website.
So let's go ahead and visit `start.spring.io` in our web browser.
All right, so we're here at the Spring Initializr website.
We can generate different types of projects.
Here, we'll select Maven, different languages here.
I'll choose Java.
And then also, as far as the Spring Boot version.
I'll choose the most recent version that they have here.
Avoid the snapshot versions because they are an alpha or beta versions.

![image04](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image04.png?raw=true)

We can move into our project metadata where we can set up our coordinates here.
So I'll set up the group ID, `com.luv2code.springboot.demo`.
And as far as the artifact ID, this is the actual name of my application,
so I'll just call it `mycoolapp`.

![image05](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image05.png?raw=true)

And for dependencies here, this is where we go through 
and basically just choose the Spring Boot starters that we want
or the actual dependencies that we want
for our applications.
So here, I'll just keep it simple.
I'll just choose web.
So this will give us the full stack web development
with Tomcat and Spring MVC.
So be sure to click that option and make sure it's selected, 
and it appears here as far as the selected dependency. 
And then from there, go down to the bottom and download the zip file 
by clicking on generate project.

![image06](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image06.png?raw=true)

So in the bottom corner there of your browser, 
you'll see that the file was downloaded.
So that's `mycoolapp.zip`.
I can swing over to my file system, and the next step is unzipping that file.
So I'll just move into my downloads directory on my computer.
And here's that `mycoolapp.zip` 
that was just created by the Spring Initializr website.
I'll simply unzip it or uncompressed it.
And then, I'll basically just take this folder here
and just copy it.
So in my file system, I have a directory here called `dev-spring-boot`.
This could be in any directory on your computer.
So I'll just paste `mycoolapp.zip` here into `dev-spring-boot`.
Again, this could be any location on your file system.
Inside here, we have this `Pom` file and some other files and source and so on.
And we'll talk more about this in a bit.

![image07](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image07.png?raw=true)

Okay, so let's go ahead and swing over to your favorite IDE.
So I'll just import this Maven project.
And now I just need to browse over to where the Maven Project is located.
So, I know it's in this `dev-spring-boot` directory
that I'm using on my file system, and it's in the `mycoolapp` directory.
And I just hit open.
So give it some time.
It's going to import the Maven Project 
and actually download all the appropriate Maven dependencies
that are needed for this given item.
So Maven is finished downloading the internet.
And so now I have this `mycoolapp` as my Maven project here,
and there are a number of files that are in this project.

![image08](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image08.png?raw=true)

So we have the Palm xml, that's the Maven file.
We have some MavenW files.
I'll talk more about those later.
Then we also have this `mycoolapplication.java`.
There's a resources directory and there's also a test directory.
So in later sections,
we'll actually cover every file on this project.
But for right now,
let's take a look at this Spring Boot application.
It's called my cool application.

```java
@SpringBootApplication
public class MycoolappApplication {

    public static void main(String[] args) {
		SpringApplication.run(MycoolappApplication.class, args);
	}

}
```

We make use of a little fancy spring annotation up here
for **SpringBootApplication**.
I'll talk more about that later.
But basically says, hey, 
we have this Spring-Boot application,
and then we use this piece here to actually bootstrap the Spring-Boot application.
So we say `springapplication.run`,
and we give the actual class name that we're going to run.
So that's our my cool application.
And again, I'll get into all the gory details
on how this Spring Boot application annotation works and also, 
how the main method works.
But for now, let's try and run it just really quickly here.
So it's important here when you run this that you run it as a Java application, 
not server because Spring Boot actually includes its own server
so there's no need to run it on the server.
Run it as a Java application.

```html
  .   ____          _            __ _ _
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
\\/  ___)| |_)| | | | | || (_| |  ) ) ) )
'  |____| .__|_| |_|_| |_\__, | / / / /
=========|_|==============|___/=/_/_/_/
:: Spring Boot ::                (v3.2.5)

2024-05-16T17:53:18.622+03:00  INFO 25216 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : Starting MycoolappApplication using Java 21.0.2 with PID 25216 (D:\JAVA_STUDY\Github\dev-spring-boot\mycoolapp\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\mycoolapp)
2024-05-16T17:53:18.625+03:00  INFO 25216 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-16T17:53:19.337+03:00  INFO 25216 --- [mycoolapp] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-16T17:53:19.345+03:00  INFO 25216 --- [mycoolapp] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-16T17:53:19.346+03:00  INFO 25216 --- [mycoolapp] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-16T17:53:19.381+03:00  INFO 25216 --- [mycoolapp] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-16T17:53:19.382+03:00  INFO 25216 --- [mycoolapp] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 722 ms
2024-05-16T17:53:19.615+03:00  INFO 25216 --- [mycoolapp] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-16T17:53:19.621+03:00  INFO 25216 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 1.255 seconds (process running for 1.601)
```

And then, if we review the logs here,
we'll see some interesting pieces of information.
So it's starting our my cool application.
And remember, Spring-Boot includes an embedded server.
So it says, ooh, Tomcat is initialized on port 8080, nice.
And then at the bottom it'll say,
Tomcat has been started on port 8080, so this is great.
So our application is started up and running.
So that means we have an application that's running
at, that has an embedded Tomcat server with it.
We haven't added any real code to our project yet,
so we have not added any controllers.
But at least at this point,
we know that a server is up and running,
and we simply need to do some more coding or configuration for it.
But again, we'll get into all those glory details in a bit.
Okay, so we have the basics going,
but we still need to do a bit more work.
</div>

### Spring Boot—Create a REST Controller
<div style="text-align:justify">

Here, I'm going to create a basic REST controller, 
because in the previous section we had Spring-Boot up and running.
So what we'll do here is we'll actually create
a basic REST controller that'll say `Hello World!`.
Creating a REST controller is really easy.

```java
@RestController
public class FunRestController {
}
```

So if we wanted to create a REST controller,
we'd simply say `@RestController` and call it **FunRestController**.
And then here we're going to expose the forward slash `/`
that'll simply return `Hello World`.

```java
@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }
}
```

So I'll simply set up a `@GetMapping("/")`.
I'll have this method called _sayHello_ that returns a string.
And the string that it returns is `Hello World!`.
That's it.
This is really just a very basic `hello world` controller,
and this will actually run in our Spring Boot application.
All right, so let's start writing this code.
We'll add our own REST controller, for that `Hello World` example.
Now let's go ahead and swing back into our IDE.
If our application's currently running,
let's go ahead and stop it now.
And, what I want to do is actually create a new package.
And the actual name that I'll give 
for the package, I'll call it **rest**.
So `mycoolapp.rest`.
Alright, so I have this new package here.
So now I'm going to create a new class in this package, 
I'll call it **FunRestController**.

```java
public class FunRestController {

    // expose "/" that return "Hello World"
}
```

So here we have a new class, and again,
the name is **FunRestController**.
Okay, so here's our **FunRestController**.
There's nothing in it.
So let me write a quick comment here.
So I'm going to expose a slash endpoint,
that'll simply return `Hello World.`
All right, so that's my game plan.
Those are my marching orders.
So building REST controllers, 
we start with the annotation `@RestController`.
Okay, so import that **RestController** annotation.

```java
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // expose "/" that return "Hello World"
}
```

I'll set up a get mapping, forward slash.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }
}
```

And I'll just define a method here, _sayHello_.
And I'll just return `Hello World!`.
So that's basically it as far as
the coding here for the `@RestController`.
Okay, so let's go ahead and run our application.
We run **MycoolappApplication** not **FunRestController**.

```html
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

2024-05-17T09:13:09.984+03:00  INFO 61084 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : Starting MycoolappApplication using Java 21.0.2 with PID 61084 (D:\JAVA_STUDY\Github\dev-spring-boot\mycoolapp\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\mycoolapp)
2024-05-17T09:13:09.986+03:00  INFO 61084 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-17T09:13:10.712+03:00  INFO 61084 --- [mycoolapp] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-17T09:13:10.721+03:00  INFO 61084 --- [mycoolapp] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-17T09:13:10.722+03:00  INFO 61084 --- [mycoolapp] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-17T09:13:10.763+03:00  INFO 61084 --- [mycoolapp] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-17T09:13:10.764+03:00  INFO 61084 --- [mycoolapp] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 739 ms
2024-05-17T09:13:11.017+03:00  INFO 61084 --- [mycoolapp] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-17T09:13:11.022+03:00  INFO 61084 --- [mycoolapp] [           main] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 1.336 seconds (process running for 1.636)
```

All right, let me kind of take a look at my console real quickly.
All right, so everything is started, up and running, that's great.
I'll swing over to my web browser. And open a page `localhost:8080`.

![image09](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image09.png?raw=true)

This is awesome.
Our REST controller is up and running.
So we're getting our `Hello World!` response based on the code 
that we just created in that **FunRestController**.
</div>

## [Spring Framework - Overview]()
<div style="text-align:justify">

I'm going to give you an overview of the **Spring Framework**.
So first off, here's the official website for Spring.
It's at `www.spring.io`.
So, this is where you can get all the documentation for spring.
You can download Spring.
You can look at some tutorials, and also some getting started guides.
And, we'll use this website a lot during this course.
So here, let's talk about the goals of spring.

* Lightweight development with Java POJOs(Plain-Old-Java-Objects),
makes it much simpler to build as compared to the heavyweight EJBs
from the early versions of J2EE.
* We want to promote loose coupling by making use of dependency injection.
So instead of hard-wiring your objects together,
you simply specify the wiring via configuration file or annotations, 
and we'll cover that a lot in this course.
* The main thing here is to minimize boilerplate Java code.
So in the early days of J2EE, there's a lot of code that you had to write.
And so the folks at Spring, they created a collection
of helper classes to make it easier,
and again to minimize all the boilerplate code.
And, we'll see some examples of that a little later in the course.

![image10](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image10.png?raw=true)

Now, this is kind of the big picture here of the **Spring framework**.
And, this is kinda like the **core** framework.
And, what I'll do is I'll actually go through these various sections here,
and just give you a quick overview as far as what they're about.
All right, so let's go ahead and start here with the **core container**.
So, the **core container** is like the heartthrob, the main;
I guess, the main item here of **Spring**.
So basically, it manages how _beans_ are created.
It has a **bean factory** for creating the _beans_.
It basically can read config files for setting properties and dependencies.
And also, the context here is really the **Spring container**
that holds the _beans_ in memory, and then as SpEL, 
that's for the **Spring Expression Language**.
So, it's a little language 
we can use within the config files to refer to other _beans_,
and we'll see examples of that later.
But that's kind of the **core container** for creating _beans_ 
and then making those _beans_ available.

Let's move over to the **AOP**.
So, this is where you have support for **Aspect Oriented Programming**.
So basically with **AOP** in a nutshell, 
it allows you to create these application wide services 
like logging, security, transactions, instrumentation,
and then you can apply these services to your objects in a declarative fashion.
So, no need to modify your code to have support for this.
You add a config in the config file or an annotation,
and that service will be applied to your application.
And, we'll see the examples of this primarily 
when we get into some transaction work,
but some other areas, too, we'll play around with it a bit.

![image11](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image11.png?raw=true)

So then moving forward here,
let's take a look at the data access section or integration.
So basically here, this is for like communicating with the database,
either a relational database or a NoSQL database,
and also making use of like a message queue.
So in the top left, you have **JDBC**.
Basically, **Spring** provides some helper classes 
to make it much easier to access a database using **JDBC**.
And by using these spring **JDBC** classes,
you can actually reduce your source code by over 50%.
So, a lot of good helper classes there.
The next little bullet there is **ORM**, for **Object to Relational Mapping**.
This is probably the most popular section of this module here.
Basically, it allows you to hook into **Hibernate** or hook into **JPA**.
So, a lot of support, and a lot of synergies between **Spring** and **Hibernate**.
**JMS** or **Java Message Service** allows you 
to send messages to a message queue in an asynchronous fashion,
that's a core part of Java EE.
Here they basically provide helper classes to allow you 
to make use of the **Java Message Service**.
And again, you can reduce your code by over 50%
by making use of **Spring's JMS** integration.
And then also **Spring** has support
for a **transaction manager** or supporting **transactions**,
and you can do this in a very lightweight fashion.
So you can make use of **transactions** on methods, on database calls,
and pretty much anything you want, it's very flexible.
And a lot of other projects in the world or on the web
make use of the **Spring transaction manager**.

![image12](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image12.png?raw=true)

This is basically the home for the **Spring MVC framework**.
All right, so you can build web applications using the **Spring Core**,
and also making use of **Spring Controllers** and **Spring View**.
So you have the full MVC layout here,
and we'll cover this a good deal later on in the course.
They also have other modules here
where you can actually interface with other web technologies
like maybe **JSF** or **Struts**, you can do that.
But pretty much, you know, if you're using **Spring** already,
then it makes sense to just go ahead and use **Spring MVC**,
no need to pull in any other **APIs**, but you can do that if you want.
They also have support for remoting here,
so you can actually make use of web remoting
where you can have external clients make calls into the **Spring container**.
Think of it as like a way of doing like remote procedure calls, 
or RPC or also doing distributed computing.
So that's available also in this web module.

![image13](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image13.png?raw=true)

Also, there's this other item here, instrumentation.
So basically Spring has a lot of sophisticated fancy technology behind the scenes,
and so here you can actually make use of class loader implementations
to work with different app servers.
For example, it can be used to create a Java agent,
so you can remotely monitor and instrument your application 
using **JMX** (Java Management Extension).
As a developer, you wouldn't normally build an agent yourself,
you would simply use the agents provided by the Spring team
or your app server vendor.
But the nice thing about it is
to know that behind the scenes it's making use of some really cool technology,
such as **AOP** code weaving by code manipulation, and so on.
So that's what you get in the instrumentation model.

![image14](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image14.png?raw=true)

Spring has support for **TDD** (test-driven development),
so the framework includes mock objects 
for mocking out servlets, JNDI access, and so on.
And you can do all of this outside of the container.
You can also make use of integration tests
by creating an application context
and wiring up your desired objects.
So testing is a first-class citizen here
when making use of the **Spring framework**.
So there's a lot of good support for it.

So that's basically it.
That's a high-level discussion here of the **Spring framework**.
In some of the following sections, 
we're going to move forward, and we're going to focus on work
with the **Spring Core container**.
We'll also make use of the **Spring Web** for doing **Spring MVC**,
and then we'll also make use of **Spring Data Access and Integration**.
So those are the key items that we'll take a look at in this course.

Now we're going to discuss Spring Projects.
So what exactly are Spring Projects?
Well, these are just additional Spring **_modules_**
that are built on top of the **core** framework.
So think of them as simply add-ons.
You only use what you need.
So they have projects here for **Spring Cloud** and **Spring Data**.
So cloud for doing cloud development, data for database integration.
They also have **Spring Batch** for creating batch processes.
**Spring Security** for securing your application.
And then, we have **Spring Web Services**for doing like **RESTful** 
and **SOAP** web services, and then also **Spring LDAP** 
for accessing **LDAP** servers.
And this is only a small sprinkling of what's out there.
So the Spring community, they're very active, very vibrant,
and now they're creating all these additional projects
that you can use in your application if needed.
But again, these are all optional.
But the best place to get information on these projects here
is simply by going to Spring's website, `spring.io`,
like I mentioned in the previous section.
So you can select the option here for **Projects**,
and then it'll give you some of the projects here
that are available online.
And each one of these projects is tabs here,
you can simply click on it and go and get more details.
But here they simply give you a nice little
one line or two lines blurb
as far as what the project's about.
So anyway, there's a lot of good stuff out here.
So if you have a lot of free time, you know,
I'd say take a look at this and see what's going on.
But again, a lot of good documentation out here
and a lot of good examples.
And each little tab here is kinda like a world in its own
that you kind of enter and get into.
So we could probably write a book on each one of these little tabs
or write a course on each one of these little tabs.
There's so much information out there.
But anyway, I simply wanted to give you an overview
and let you know that, hey, these other projects are out there and available.
You can visit Spring's website at `spring.io`, go to the **Projects** tab,
and then you can just read up on each one of those projects and see 
if you could use them in your own project.
</div>

## [What is Maven?]()
<div style="text-align:justify">

In this section, we'll cover a **Maven Crash** course.
So here's the relationship between **Spring Boot** and **Maven**.

* When you generate projects using a Spring Initializer website at `start.spring.io`,
it can generate a **Maven** project for you.
* In this section, we're going to learn the basics of **Maven**,
  - viewing dependencies in the **Maven** `pom.xml` file,
  - and also we'll discuss **Spring Boot** starters for **Maven**.

Alright, so what is Maven?
Well, Maven is a project management tool for your application.
So the most popular use of Maven is for build management and dependencies.

What problems does Maven actually solve?
Well, when you're building your Java project, 
you may need additional JAR files like **Spring** JAR files,
**Hibernate** JAR files, so on and so forth.
And one approach is to simply download those JAR files from each project website,
and then you'll manually add those JAR files to your build path or your class path.
  
![image15](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image15.png?raw=true)

So here's how my project would work without **Maven**.
So I have my super cool app, 
I'm going to need the **Spring** JAR files 
**Hibernate**, **Commons Logging**, and so on.
And then so me the developer, 
I actually need to go to 
each one of these project websites and download them.
So I need to download the **Spring** JAR files,
**Hibernate** JAR files, **Commons Logging**.
If I'm doing any **JSON** work, 
I need to pull those JAR files down also
and then manually add those to my build path.
Now, **Maven** can actually help us with this process 
and do a lot of this work for us.
So the **Maven** solution is that you simply tell **Maven**
the projects you're working with, **Spring**, **Hibernate**,
and so on, and **Maven** will actually go out and download
those JAR files for you from the internet.
And then **Maven** will make those JAR files available
during compile and runtime.
So you can kind of think of **Maven**
as like your friendly helper or your personal shopper.
You simply give them a shopping list,
they'll go out on town, purchase everything for you,
and bring it back for you to make use of,
which is really cool, I think. 

![image16](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image16.png?raw=true)

So here's how our project would work with **Maven**.
So there's a **Maven Central Repository** that's remote, it's on the internet.
And then we have our super cool app.
We simply tell **Maven;** hey, here's our shopping list.
So me, the developer, I write out my shopping list,
I give it to **Maven**, and then **Maven** will go off 
and download the **Spring** JAR files, the **Hibernate** JAR files,
**Commons**, **JSON**, anything else.
It'll download all that stuff and pull it to my computer
and make it available for me to use. 
So that allows me the developer, 
instead of having to do all that manual work, 
I can just kind of sit here and drink my coffee.
**Maven** will go out and do the work, 
and then I can continue on coding 
once **Maven**'s pulled everything down for me.
So let's go ahead and dive in.

![image17](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image17.png?raw=true)

So this is how Maven works behind the scenes.
Using **Maven**, you have a project configuration file that **Maven** will read.
So that's basically your shopping list. 
So **Maven** will read your config file. 
Then **Maven** will check a **Maven Local Repository** 
that resides on your computer. 
It's like your local cache. 
If you don't have the files in your local repo, 
then **Maven** will actually go out to the internet 
at the **Maven Central Repository**, which is remote.
And it'll pull those JAR files down from the **Maven Central Repo** on the internet.
Then it'll save versions of those files in your local repository,
so you can kind of build up your local cache, 
and then **Maven** will use that to build and run your application.
So that's kind of the big picture on how **Maven** works.

Now as far as handling JAR dependencies,
**Maven** will retrieve a project dependency
and also any supporting dependencies.
So if he says, hey, **Maven**, I need **Spring**.
Well, **Maven** will also know that oh, well,
**Spring** depends on **Commons Logging**.
So let me go ahead and grab that one also.
So any dependencies, they'll go ahead and grab those additional items.
So **Maven** will do this for us automagically,
and it's really cool how this feature works out.

And finally, for building and running,
when you build and run your app,
**Maven** will handle the class and build path for you.
So based on the configuration file,
**Maven** will add the appropriate JARs accordingly.
So you don't have to manually configure your class path.
Set up the **Maven** config file and **Maven** will do all the work for you.
And again, that gives you more free time to sit there and drink your coffee.
</div>

### Maven Project Structure
<div style="text-align:justify">

Now, normally when you join a new project, 
each development team, they dream up their own directory structure,
and it's not really ideal for newcomers, and it's not standardized.
So Maven solves this problem by providing a standard directory structure
that you can use on your project.
So here's the directory structure.

![image18](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image18.png?raw=true)

So **my-super-cool-app**, at the root of the directory
I'll have this `pom.xml` file that's the **Maven** configuration file, your shopping list.
We'll cover the `pom.xml` file a little bit more in detail later.
But we have this one area here called a source main java.
So this is where you place your Java source code.
Then we have a **resources** directory.
That's where you'll place your properties files,
or config files that are used by your application.
There's also a **webapp** directory,
and this is where you place your **JSP** files,
any **web config** files, **images**, **CSS**, so on.
There's also a source **test** directory.
This is where you'll place your unit testing source code,
and any properties and configuration files
that are used by your unit testing code.
And finally, there's a **target** directory.
So this is the actual destination directory for your compiled code,
and also any artifacts that **Maven** will generate.

![image19](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image19.png?raw=true)

Alright, so let's go ahead and kind of look at an example 
using some of our coding from before.
So we have our super cool app here, right?
And our source code will place in the `src/main/java`.
So this is where we put our source code.
Under `src/main/java`.
You have your package structure and then your `.java` source code.
So that's where that code will show up in your **Maven** project structure.

![image20](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image20.png?raw=true)

And if you're working on a web project,
then you'll actually place your web assets
in the `src/main/webapp` directory.
So this is where you'll place your **JSP** files
any configuration files, **CSS**, **images**, and so on.
You'll place it under `src/main/webapp`.

Alright, now, what are the benefits of the standard directory structure?
Well, for new developers joining a project,
they can easily find code, properties files, unit tests, web files, and so on.

![image21](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image21.png?raw=true)

This is crucial, especially on a real world, or real-time projects.
You can quickly join a project and know where files are located.
Also, another benefit is that most major IDEs have built in support for **Maven**.
So like Eclipse, IntelliJ, NetBeans.
They can easily read and import **Maven** projects for you.
So **Maven** projects are portable.
So as a developer, you can easily share projects between IDEs.
So I could create a **Maven** project in **NetBeans**
and easily open at a project in Eclipse or IntelliJ, or vice versa.
The really nice thing about this is that there's no need to fight 
about which IDE is the best.
Use whatever IDE you want.
And whatever works for you, works for you.

And some additional advantages of using **Maven**.
One is dependency management.
So **Maven** will find the JAR files for you.
So no more missing JARs, and also building and running your projects, 
you know, no more worrying about buildpath or classpath.
And then finally, you have that standard directory structure.

And then finally, my personal best **Maven** benefits here 
are that once you learn **Maven**,
you can join a new project and become productive,
because you'll know how to build and run the project
with minimal local configuration.
**Maven** will handle going out to the internet,
downloading any JAR files that you need,
pulling it to your local computer, and you can run it.
So it's really, really cool.
So **Maven**'s very powerful once you understand it.
Once you get your head around it,
then it's a very powerful tool.
And you can definitely use it for your enterprise projects.
</div>

### Maven Key Concepts
<div style="text-align:justify">

In this section, we're going to cover some **Maven Key Concepts**.
So we'll start with a discussion of the **POM File**,
and then we'll also take a look at **Project Coordinates**
and how we can use it in our **pom File**.

![image22](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image22.png?raw=true)

Alright, so starting here with the **POM File**.
The **POM File** is your **Project Object Model** file.
This is your configuration file for your project.
So this is basically your shopping list for **Maven**.
This is where you tell **Maven**,
"_Hey, we depend on X number of dependencies,
go out and find those for us._"
And this **POM File** is always located in the root of your **Maven** project.

![image23](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image23.png?raw=true)

Now, let's look at the **POM File Structure**.
So basically, you'll have the project metadata,
you'll have a list of dependencies, 
and you'll have a list of plugins.
So the project metadata is basically information about your project, 
like the name of your project, which version number,
and also the output file type, like JAR file, WAR file, etc.
The dependencies basically tell it,
"_Hey, this is the list of projects that we depend on._"
So if you list, if you depend on **Spring** or **Hibernate** or **JSON**,
you'll list those dependencies there.
And then also, you'll have a list of plugins.
So these are like additional custom tasks to run.
So you can use this for generating like JUnit test reports and so on.

![image24](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image24.png?raw=true)

Alright, so let's go ahead and look at a simple POM File.
So here's the file that starts with project, modelVersion.
That's always the same.

```xml
<project>
    <groupId>com.luv2code</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>1.0.FINAL</version>
    <packaging>jar</packaging>
    
    <name>mycoolapp</name>
</project>
```

Then we have our metadata.
So this is where we can give the project name.
So here the name is _mycoolapp_.
We can give the **GroupID**, **ArtifactID**
and the **version** number,
and we can also specify the **packaging**.
So in this example, the packaging will be for a JAR file.

```xml
<project>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

So the next section here is the **dependencies**.
So this is where we list out all the dependencies
that we need for our application.
In this example, they make use of **JUnit**,
but you can also add dependencies here for **Spring**, **Hibernate**, etc.
I'll show you some examples of adding **Spring** and **Hibernate**
a little later in this section.
At the bottom, we can add plugins for customization.
So we can add some additional custom tasks,
for example, generating **JUnit** test reports and so on.

```xml
<project>
    <groupId>com.luv2code</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>1.0.FINAL</version>
</project>
```

So let's go ahead and talk about **Project Coordinates**.
So **Project Coordinates** uniquely identify a project.
It's similar to GPS coordinates for your house,
like the latitude and the longitude.
It's basically precise information on how to find your house.
So if I wanted to come visit you, I'd ask for your city,
your street, and your house number, and that'll give me the specifics
on how to find your given location.
So the **GroupID** could be like the city,
the **ArtifactID** could be the street,
and then the **version** could be the actual house number.
So some specific coordinates on how to find your house.
And we use a similar thing here in the **Maven** world to identify a project.

| Name        | Description                                                                                                               |
|-------------|---------------------------------------------------------------------------------------------------------------------------|
| Group ID    | Name of company, group, or organization.<br/> Convention is to use reverse domain name: `com.luv2code`                    |
| Artifact ID | Name for this project: **mycoolapp**                                                                                      |
| Version     | A specific release version like: **1.0, 1.6, 2.0 ...**<br/> If project is under active development then: **1.0-SNAPSHOT** |

So a bit more on these **Project Coordinates**, 
as far as the specific elements here.
So basically what we'll do is we'll have this **GroupID**.
So the **GroupID** is basically the name of your company,
group, or organization.
The convention that's used is to use the reverse domain name.
So in my example, I'll call it, `com.luv2code`.
It's very similar to the convention used for Java package naming.
And then we have the **ArtifactID**.
So the **ArtifactID** is the name for this project.
So whatever your project is called, that's the **ArtifactID**.
And then the version is the specific release version 
that you have for your project, like version **1.0, 1.6, 2.0**, and so on.
If the project is under active development, then you can use **1.0-SNAPSHOT**.
So **SNAPSHOT** means that this work is still under active development.

```xml
<dependency>
    <groupId>com.luv2code</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>1.0.RELEASE</version>
</dependency>
```

So let's look at some real examples of **Project Coordinates**.
So we saw the example up top of our `com.luv2code` example.

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.0.0</version>
</dependency>
```

And let's look at the dependency for the **Spring** project.
Notice here we have the **GroupID**, the **ArtifactID** and the **version**.

```xml
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.1.4.Final</version>
</dependency>
```

And let's also do a similar thing 
by looking at the dependency information for **Hibernate**.
So here we have the **GroupID**, the **ArtifactID**, and the **version**.

```xml
<project>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.0</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.1.4.Final</version>
        </dependency>
    </dependencies>
</project>
```

Alright, now, if I wanted to add support 
for **Spring** and **Hibernate** to my project,
then I would simply add in those dependencies in the **Dependency Section**.
So here I have a reference here, for the **Spring** framework, 
_spring-context_, and then also for the `org.hibernate`,
I add in the _hibernate-core_.
And so this is again, **Maven**'s shopping list.
So **Maven** will actually go out to the internet and pull down these, 
the related JAR files for this project, and we can use them in our application.

All right, so to add a given dependency project,
we need the **GroupID** and the **ArtifactID**,
the **version** is actually optional,
but the best practice is to include the version for **Repeatable Builds**.
And this is a best practice for using **DevOps**.
You want to be able to say, "_Hey, our project works
with **version** X, Y, Z of a given project._"
Just so you know that, that part has been tested, verified, and worked, 
and it's very, very important in the **DevOps** world.
So always use the **version**.
I recommend that as a best practice.
And also you may see this referred to as **GAV** or G-A-V.
So that basically means **GroupID**, **ArtifactID**, **version**.
Now, you may see documentation where they'll say,
"_Hey, what's your **GAV** or G-A-V?_"
Or you may hear other Java developers say,
"_Hey, what's the G-A-V for the project?
Or what's the **GAV** for the project?_"
So you may hear this nomenclature, but again, 
it's basically just a little acronym here 
for **GroupID**, **ArtifactID** and **version**.

Alright, you may wonder, 
"_Well, how'd you find those dependency coordinates?_"
Well, you have two options.
The first option is that you can actually visit the project page.
So you can visit the Spring website(`spring.io`) 
or the Hibernate website(`hibernate.org`),
and they'll give you the details that 
you'll need to add their project using **Maven**.
The other option is that you could go to the **Maven Central Repository**
(`https://central.sonatype.com`) and actually search for those projects,
and then you can also get those details.
I like to say that option two is the _easiest approach_,
because that's just one site you go to, to look for it.
If you use option one, option one's fine 
if you only have like one or two dependencies.
But imagine on an Enterprise project, real-world, real-time,
your project may use five, 10, maybe 20 dependencies,
and you don't want to have to go to all 20 websites 
to find out their **Maven Coordinates**.
Using option two, you can go to this search shop `maven.org`.
You simply type in those dependencies, 
and you'll get all the information there.
So it's kinda like a one-stop shop,
as far as getting the dependency coordinates.
</div>

## [Spring Boot Project Files]()
<div style="text-align:justify">

In this section, we're going to cover 
the **Spring Boot** project structure.
So remember, we already created a new project using a **Spring Initializr**,
so that actually created a **Maven** project for us.
And so now we'll actually explore the project structure, because remember, 
I said we'll actually cover all of those files that are created,
so we'll do that in this section.

| Directory          | Description                              |
|--------------------|------------------------------------------|
| src/main/java      | Your Java source code                    |
| src/main/resources | Properties/config files used by your app |
| src/test/java      | Unit testing source code                 |

So first off is that 
**Spring Boot** makes use of the standard **Maven** directory structure.
So for the `src/main/java`,
that's where you actually place your Java source code.
For `src/main/resources`, this is where you'll have like,
your properties files or config files used by your application.
And then under `src/test/java`,
that's where you can place your unit testing code.
So **Spring Boot** makes use of the **Maven** standard directory structure.

![image25](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image25.png?raw=true)

Now, there were some other files here that were a bit interesting here.
You may have seen these `mvnw` files, and you probably wondered, 
you know, what's up with that or what are those?
Well, those are **Maven Wrapper** files.
So the `mvnw`, or the **Maven Wrapper** files,
they allow you to run a **Maven** project.
And the really cool thing about it is 
that there's no need to have **Maven** installed
or have it present on your path.
So, what these files will do is they'll say,
"_hey, if the correct version of Maven
is not found on your computer_",
then it'll actually automatically download
the correct version of **Maven** from the internet,
and then actually run **Maven**.
So it'll basically do some smarts there.
It'll automatically download it and then run it.
So there are actually two files that are provided for you.
You have one of them, that's the `mvnw.cmd` file,
that's for Microsoft Windows,
so you can use that to say `mvnw`,
and then, you know, normal **Maven** stuff,
like _clean compile test_ or whatever.
And then there's the `mvnw.sh`,
and this is for Linux or Mac systems.
So here you could say `./mvnw clean compile test`, and so on.
So that's the basic idea here of the `mvnw` files.
They're basically wrapper files that'll automatically download
and run the correct version of **Maven** for you.

Now, if you already have **Maven** installed on your computer,
then there's actually no need for the `mvnw` files,
so you can safely ignore those files or delete them.
They're not required.
So in that case, since you already have **Maven** locally installed,
you simply use **Maven** as you normally would.
So here you'd say `mvn clean compiled test`.
So notice here there's no `mvnw`,
you simply use **Maven** by itself.
All right, so that's the `mvnw` commands
or the **Maven wrap** files.

Next we have the actual `POM` files.
So the `POM` file includes the information 
that you entered at the **Spring Initializr** website.

```xml
<dependency>
    <groupId>com.luv2code.springboot.demo</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
</dependency>
```

So for example, you gave information about the **groupID**,
the **artifactID**, and so on.
That information's automatically in your `POM` file that was generated.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

And then also, you'll have a collection of **Maven** dependencies in the file, too.
And you'll notice here that they're called _starters_.
So these are **Spring Boot** _starters_,
which is really just a collection of **Maven** dependencies.
And these are the compatible versions for these dependencies.
So for example, we have this `spring-boot-starter-web`.
Well, this is really a collection of dependencies,
so it's a collection of **spring-web**, **spring-webmvc**,
**hibernate-validator**, **tomcat**, **json**.
So what this does is that it saves the developer
from having to list all the individual dependencies,
and also makes sure you have compatible versions.
So kinda think of this kinda like a one-stop-shop
for grabbing some dependencies for your given application.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

And then also in the **Maven** `POM` file,
there's a section here for the **Spring Boot Maven** plugin,
so you'll actually see that at the bottom of the `POM` file.
So here, this plugin is used to package an executable jar file
that we can run from the command line or to create a war archive file.
And you can also use this plugin to easily run the application here.
So at the bottom, I simply say `mvnw package`,
and then I say `mvnw spring-boot:run`.
So that'll actually run the application.
So that's the idea of the **Spring Boot Maven** plugin
for packaging and also for running.
Now, if you already have **Maven** installed in your computer,
then you don't need the `mvnw` stuff,
you simply say `mvn package`, and then `mvn spring-boot:run`.
So totally up to you based on your environment
and based on your configuration.

Okay, we saw a small bit of the source code,
but I didn't really go into details on it.
So when you generate your actual project,
you have this main spring boot application class actually 
created by the **Spring Initializr**.
And then we also saw in our previous section,
we created a **RestController**, we called it _FundRestController_,
and we used the _FundRestController_ to expose a basic rest API.

Now moving ahead here, talking about `application.properties`.
So by default, **Spring Boot** will load _properties_
from a specific file called `application.properties`.
So this is a magic file that Spring knows about,
and it'll look for it in the `source/main/resources` directory
`application.properties`.
Now, this application properties file is actually created
by the **Spring Initializr** site when you actually create your project.
Now, if you look at the file,
it's actually empty at the beginning.
There's nothing in it,
but you can actually add some **Spring Boot** properties.
So you could say, `Server.port = 8585`.
So effectively, here now,
you're kind of telling **Spring Boot**,
"_Hey, don't listen on Port 8080,
I want you to listen on Port 8585_"
or whatever magic number here that you want to give
to have the **Spring Boot** server listen on.
Or, you could also add your own custom properties to this file.
So I could say, `coach.name = Mickey Mouse`.
So you can give as many properties here
as you'd like in this `application.properties` file.

Now, how would you kind of read some data
from that application properties?
So, we know it's under `source/main/resources`,
and I put my own specific information in there
or my own custom properties here.

```properties
# configure server port
server.port=8484

# configure my props
coach.name=Mickey Mouse
team.name=The Mouse Crew
```

So, here I have `coach.name` and I also have `team.name`.
Then I can actually read this or use it
in my rest controller by making use of injection.

```java
@RestController
public class FunRestController {
    
    @Value ("${coach.name}")
    private String coachName;
    
    @Value ("${team.name}")
    private String teamName;
    
    //...
}
```

So here's my **FunRestController**, 
and then I make use of that `@Value`, 
and I give a dollar sign, curly brace,
and then I give the actual property name here.
So for that `coach.name` property, 
I can inject it by using `coach.name` and then injecting it 
or assigning it to this given field.
Here I have private **String** _coachName_.
And then I could do a similar thing here for team name, right?
So I have team name's, the actual property,
I do the `@Value` injection of that property,
and then assign it or bind it to this given field 
private **String** _teamName_.
And you can repeat the process X number of times
for any other properties that you have
in your `application.properties` file.
And I'll talk more about this properties file,
and some later sections will go more in depth,
but this is kind of enough to get us going.
And then as far as static content, so, by default, 
**Spring Boot** will load static resources from the `/static` directory.
So some examples of static resources are, 
like HTML files, cascading style sheets, JavaScript, images, PDFs,
blah, blah, blah, just static content.
So you simply place it into the static directory
and **Spring Boot** will load it automatically for your given application.

Now, one warning that you need to be aware of,
is that **do not use** the `source/main/webapp` directory
if your application is packaged as a **JAR**.
So although this is a standard Maven directory,
it only works with WAR packaging.
It's silently ignored by most build tools if you generate a JAR file.
You'll waste many hours, many hours trying to resolve this
or trying to get this to work. 
But don't worry, I'll actually show you 
how to make use of templates and Thymeleaf and so on.
I'll show you how to do that in later sections,
but just kind of heads up,
if you wanted to get started quickly, just be aware of that one issue.

Alright now, as far as templates, **Spring Boot** includes auto-configuration
for the following template engines:

* FreeMarker
* Thymeleaf
* Mustache

And by default, **Spring Boot** will load templates 
from the `/templates` directory.
And **Thymeleaf** is a popular template engine,
and we'll actually use it later in the course.
If you're interested in some of the other templates
like **FreeMarker** or **Mustache**, just do an online search. 
You'll find plenty of examples out there,
and you'll find examples also 
that make use of **Spring** and **Spring Boot** if you'd like.

All right, as far as unit test,
you have this file here, `Mycoolapplicationtest.java`.
So, this is actually created by the **Spring Initializr** at the beginning.
It's just a very basic test.
I think there's just a setup item in there,
and there's no real code as far as running real unit tests.
You can simply add your own custom unit test to this file,
and it's part of the **Spring Boot** infrastructure.

All right, so that was kind of a quick tour
of the **Spring Boot** project structure.
So we went through, and we reviewed every one of the files
that are created by **Spring Initializr**.
So you should have a good idea as far as those files,
and in later sections, we'll kind of dive in a bit deeper
and see how these different files work out.
</div>

### Spring Boot Starters
<div style="text-align:justify">

In this section, we'll cover the **Spring Boot Starters**.
Okay, so what's the problem?
So, as we know, building a **Spring** application is really hard
and one of those frequently asked questions,
as far as which **Maven** dependencies do I need to use?
And this is just information that I pulled from the QA forum for this course,
is that this is a common question that developers have as far as:

* _I want to build a Spring Embassy or hibernate application.
What are the minimum dependencies that I need?_
* _if I use a pom, what are the exact dependencies?_

Because it's really tricky to figure out how to get started.
And so, you know, it'd be really great if there was 
simply a list of **Maven** dependencies
that's collected as a group of dependencies for that whole one-stop shopping.
Because as a developer, I really don't want to waste time 
having to search for each dependency and trying to see what's out there
and see what's available there really should be an easier solution for all of this.
So the solution here is by making use of **Spring Boot Starters**.
All right, so the _Spring boot starters_ are a curated list of **Maven** dependencies.
It's a collection of dependencies that are grouped together
that have been tested and verified by the **Spring Development** team.
And these starters make it much easier for the developer 
to get started with **Spring**, 
and it also reduces the amount of **Maven** configuration that you have to do.
And so also, no more need for searching or hunting for the right dependency.
Simply use a starter and you're ready to go.
So, you know, let's look at an example with **Spring MVC**.

![image26](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image26.png?raw=true)

So right, so building a traditional **Spring MVC** app,
you normally would need to add 

* **Spring MVC**
* **hibernate validator** for some form validation
* **web template**

So a lot of stuff that you would've to do individually 
as far as listing out those dependencies.
Here's a solution by making use of the Spring Boot Starters for web.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Then you simply add this **Maven** dependency to your **Maven** `pom` file.
And this is a single entry.
And so this is a **Spring Boot Starter**.
It's a collection of **Maven** dependencies with compatible versions.
So this one dependency entry, it actually contains other dependencies.
So it contains **spring-web**, **spring-webmvc**,
**hibernate-validator**, **json**, **tomcat**, and so on.
So you get a lot of stuff for the price of one,
and this saves the developer from having to list all the individual dependencies.
And it also makes sure that you have compatible versions of those dependencies.
And this is really, really helpful.

In **Spring Initializr**, to get access to this you simply select web dependency,
and you'll automatically get the **Spring-Boot-Starter-Web** in your `pom XML` file.
So in our initializr website once you select web here from the list
then this is the actual **Spring boot starters** that they'll add for you.
And this web starter here will basically give you support for **Tomcat** and **Spring MVC**.

So let's look at the example if we're building a **Spring** app
that needs web and security.
Well, then we simply select the dependencies in the **Spring Initializr**.
So here we have dependencies.
We have these items selected for web security, **JPA**
and **thymeleaf** and so then, when it generates your project,
the Spring boot starters will automatically be added to your **Maven** `pom` file.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
```

All right, so let's dig in here.
So we have our dependencies, we have them selected.
We do the generation part.
Then here's our `pom.xml`.
So we'll have this **Spring Boot starter web**,
we'll have the **Spring boot starter security**,
we'll have starter **data JPA**,
and we'll also have starter **thymeleaf**.
And so it'll do all this for us automatically
by making use of that **Spring Initializr** website.

| Name                         | Description                                                                               |
|------------------------------|-------------------------------------------------------------------------------------------|
| spring-boot-starter-web      | Building web apps, includes validation, REST.<br/> Uses Tomcat as default embedded server |
| spring-boot-starter-security | Adding Spring Security support                                                            |
| spring-boot-starter-data-jpa | Spring database support with JPA and Hibernate                                            |
| ...                          |                                                                                           |

So, we saw some starters.
So there's actually 30 plus **Spring Boot Starters**
that are created by the **Spring Development** team.
So there's the **Spring Boot Starter web**,
so that's for building web apps, includes validation and **REST**.
It uses **Tomcat** as the default embedded server.
There's the starter security for giving security support.
There's the starter data **JPA** for adding database support
with **JPA** and **Hibernate** and so on.
If you'd like a full list of **Spring Boot Starters**,
simply go to the link [here](https://www.luv2code.com/spring-boot-starters),
this will automatically redirect you to the official **Spring Documentation** 
that'll list out all the **Spring Boot Starters**.

Now, once you start working with the starters, 
you know the frequently asked question is like,
okay, I'm adding this **Spring Boot starter**, X, Y, Z,
but you know what's in it?
Like, you know, how I know what's in this starter, right?
Well, one approach is that you could view the starters,
`pom.xml` file by again, using a link above that I provided,
but this normally provides references to other starters and so on.
So you'll have to kind of dig a bit.
It's somewhat cumbersome to work through it,
but there's another solution here.
So what we can do is make use of the IDE.
So most IDEs have a dependency management 
or view feature and this is much easier to navigate.
I'll actually show you how to do this with Eclipse and IntelliJ.

![image27](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image27.png?raw=true)

Okay, so for Eclipse users, basically you open up the `pom.xml` file,
and then you select the tab for **dependency hierarchy** at the bottom, 
and then you expand the desired starter,
and then you'll actually see the content in that starter.
So from this little screenshot here, 
we can see at the top **spring boot starter web**;
it includes **starter-json**, **tomcat validator**, **web**, **web mvc**, and so on.
So that's kind of the basic approach that you can use
to kind of explore the starters to see what's in there,
to see what's available.

![image28](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image28.png?raw=true)

For the IntelliJ users you simply use the menu option,
`view > tool windows > Maven projects > expand dependencies`.
And then you'll see the list of starters,
and then you can kinda expand a given starter,
and then you can see the actual contents of that given starter.
All right, so that's the basic approach for exploring
or finding out what is in a given starter.
</div>

### Spring Boot Starter Parent
<div style="text-align:justify">

In this section, we'll cover the **Spring Boot Starter Parent**.
**Spring Boot** provides a **Starter Parent**,
so this is a special starter that provides **Maven** defaults.

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.0-RC1</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

So in your `pom.xml` file, you'll have an entry here for parent,
and you'll give the actual _groupId_, _artifactId_, and _version_.
And this is included in the `pom.xml` automatically when using the _Spring initializer_.

So the **Maven** defaults are actually defined in the **Starter Parent**,
so you get your actual default compiler levels.
It also has support for the UTF-8 source encoding,
and there are some other features out there,
but you get all of these features by default
by using the **Spring Boot Starter Parent**.

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

Now, you may wonder, 
"_Well, I don't want to use Java 8 as far as my Java version.
On our project we're using Java 12,
or Java 15, or Java 20, or Java 30._"
I'm being ridiculous here,
but Java's changing every six months now,
so that's a very valid concern.
So here, you simply override a default
or override this and simply set the property here.
So here, I say **properties**, `java.version`,
and you simply specify 
or just drop in the version of Java that your project is using.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.luv2code.springboot.demo</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mycoolapp</name>
    <description>Demo project for Spring Boot</description>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.0-RC1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
</project>
```

Now for the **Spring-Boot-starter-*** dependencies,
there's no need to list the actual version,
so basically here you give your actual parent,
so we have the **Spring Boot Starter Parent**,
and we specify the given version that we're using.
Then, for the actual dependencies,
you simply inherit the version from the **Starter Parent**,
so there's no need to list the individual versions.
This is great for maintenance,
and also it helps to make sure that all the dependencies here 
that you're using are compatible, 
and that's regarding all the **Spring Boot starter** dependencies,
making sure those are all compatible.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

Now also, the **Starter Parent** provides 
default configuration of the **Spring Boot plugin**.
So in your build section for your `pom.xml` file,
you simply reference the Spring Boot **Maven** plugin,
and it's there ready to go.
There are no additional configurations that you need to perform
to configure the Spring Boot plugin, so you can simply run it
from your command line, `mvn spring-boot:run`,
and it'll actually run your application out of the box with no additional work.

So let's take a step back and look at the benefits of the **Spring Boot Starter Parent**.
So first off, it gets you the default **Maven** configuration
for the Java version, UTF coding, and so on.
Also, it helps you with dependency management.
So you simply use the version on the parent only,
and all the **Spring Boot starter** dependencies
inherit the version from the parent,
and also you have the default configuration of the **Spring Boot plugin**.
So those are the main benefits there of using a **Spring Boot Starter Parent**.
</div>

## [Spring Boot DevTools]()
<div style="text-align:justify">

In this section, we're going to cover **Spring Boot Dev Tools**.
Here's the problem.
When running **Spring Boot** applications,
if you make changes to your source code,
then you have to manually restart your application,
and that's not good.

Here's a solution.
We can make use of **spring-boot-devtools** to the rescue.
This will automatically restart your application when your code is updated.
You simply add a dependency to your **Maven** `pom` file
and there's no need to write additional code.
Now, in other IDEs, this functionality works out of the box.
However, with IntelliJ, we need to set up some additional configurations,
and I'll actually discuss that shortly.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

To use **Spring Boot Dev Tools**,
you'll simply add a dependency to your `pom` file.
We give the _groupID_, and then we give the _artifactID_ of **Spring Boot Dev Tools**.
When we add this, 
then it'll automatically restart your application when the code is updated.

Now, as I mentioned with IntelliJ Community Edition,
it does not support **DevTools** by default.
We have to make some additional configurations.

![image29](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image29.png?raw=true)

What we'll do is we'll select the menu of 
`Preferences > Build, Execution, Deployment > Compiler`,
and then we'll check the box to `Build Project Automatically`.

![image30](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image30.png?raw=true)

We need to do one additional setting here.
We'll select the menu item `Preferences > Advanced Settings`,
and then we'll check the box, `Allow Auto-Make to Start`.
Once we make these two setups, then the IntelliJ Community Edition
will be able to work with **Spring Boot DevTools**.

Just a recap, here's the development process.

1. Apply the IntelliJ configurations
2. Edit our `pom.xml` and add the `spring-boot-devtools`
3. Add our new REST endpoint to our application
4. Verify the app is automatically reloaded

Alright, so one thing I'd like to do is just some housekeeping here.
Let's go ahead and make sure that any apps that we have running,
let's make sure that they're stopped now,
so nothing's running here with **Spring Boot**.
And let's go ahead and close the files.
And let's go ahead and close the given window on the IDE.
So we should just have the basic IntelliJ at the moment with no editor window open.
And I'd like to move over to my file system
and do some little housekeeping here on my actual project files.
So I'll open up my file system.
I'll move into this directory that we have, `dev-spring-boot`.
And inside it, that's where we have our `mycoolapp` directory.
Let's go ahead and create a new folder here.
And I will call it `01-spring-boot-overview`.
Just a way to kinda organize all these projects that we're going to work on.
And let's go ahead and move the `mycoolapp` folder
into the `01-spring-boot-overview`.
So at this point, `01-spring-boot-overview` and then `mycoolapp`.
Let's go ahead and move into this `01-spring-boot-overview` folder.
And now, let's go ahead and rename `mycoolapp`.
And I'll rename it `01-spring-boot-demo`,
because this was our first **Spring Boot** demo.
And I'd like to take this folder and then copy it.
And then I'll paste it here in the same location.
And now, I'll simply rename it here for dev tools.
So I'll call it `02-dev-tools-demo`.
Basically, this housekeeping work allows us
to keep previous copies of our projects
in case you wanted to go back to a specific point.
So at this point here, we have `01-spring-boot-overview`,
and then we have the `01-spring-boot-demo`
and the `02-dev-tools-demo`.
This is all under our `dev-spring-boot`.
Again, we're just trying to organize things
to make things a little easier for us in the future.

So let's move back to IntelliJ.
Let's remove our old project.
Now, let's go ahead and do an open,
and we're going to open up the **dev tools** project.
So it's under `01-spring-boot` and then `02-dev-tools-demo`.
And that's the folder that we want to open.
And now, it'll start opening up.
It'll synchronize, resolve Maven dependencies, and all that good stuff.
So at this point, we have our **02-dev-tools** project open.

![image31](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image31.png?raw=true)

Now, let's go ahead and move over to our IntelliJ **preferences** here,
and we'll set up those IntelliJ configurations.
And let's move down to the item here for **Build, Execution, Deployment**.
Expand this for a second, and then we'll choose **Compiler**.
And then we'll select this check box here,
**Build project automatically**.
Alright, so that looks pretty good.
Let's go ahead and click on the Apply button.

![image32](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image32.png?raw=true)

And now, let's go and select the **Advanced Settings** item.
And then, in the **Compiler** section,
we'll check the box, **Allow auto-make to start**.
And remember, these are the little IntelliJ configurations
that we need to do for the Community Edition
to allow it to work with **Spring Boot** devTools.
So let's go ahead and open up our **Maven** `pom` file.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.luv2code.springboot.demo</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mycoolapp</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

I'll expand the window here and spread out.
And so the first step here is editing the pom.xml file
and adding the `spring-boot-devtools` dependency.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

So remember, the dev tools will give us the automatic reloading
when we actually edit our actual source code.
And be sure to reload the **Maven** changes.
Alright, so let's go ahead and test this out a bit.
So let's go ahead and run our application first.
So under source main, I'll just run this `MycoolApplication.java`.
That's our main application.
Just run it as a Java app.

```html
2024-05-19T06:57:06.650+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-19T06:57:06.696+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-19T06:57:06.696+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-19T06:57:07.470+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-19T06:57:07.480+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-19T06:57:07.480+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-19T06:57:07.513+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-19T06:57:07.514+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 816 ms
2024-05-19T06:57:07.766+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-19T06:57:07.795+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-19T06:57:07.801+03:00  INFO 9540 --- [mycoolapp] [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 1.5 seconds (process running for 1.86)
```

Alright, so this guy is up and running.
Okay, so Tomcat's on 8080, and it's up and running.
Started `MycoolApplication`. 
Alright.
Now, one thing I want to do here is actually
edit our **REST API** or our **RESTController**.
And what I want to do here is 
add a new **REST endpoint** to our application.
So effectively, I'm just editing my source code,
and I just want to make sure that **Spring Boot**
will actually reload the new version,
or in particular, **dev tools** will help us out with that.

```java
@RestController
public class FunRestController {

    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
```

So I'll just write a little quick comment here.
I'm going to expose a new endpoint for workout.
And I'll just cut the code for it.
And it's just really simple, very straightforward endpoint mapping here.
Have a basic method here, **public String** _getDailyWorkout_,
and I'll just return some string here.
I'll say, `Run a hard 5k!`
Alright, so that's this new method that we just added to our code here.
Then let me go ahead and save this file.
And once I save it, I just verify
or keep an eye on the console here at the bottom,
and we should see it move.
That means that it went through and did some processing,
and it actually restarted our application.
So we know the whole dev tools thing is in place,
and they're listening, and they're noticing changes
and loading up our new application.
So this is great.
So now, I should be able to swing over to my browser here,
and I should be able to access this new endpoint.

![image33](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image33.png?raw=true)

So I'll just go to `localhost:8080/workout`.
That's the new one that we just added.
So our new endpoint was automatically loaded
just by making use of that dev tool.
So this is great.
So we're in pretty good shape here.
But let's try it one more time, right?
So let's add one more endpoint
just to verify that it is really, really working.
So here, I'll just expose a new endpoint for fortune.
And again, just a basic method, right?

```java
@RestController
public class FunRestController {

    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
```

Public String _getDailyFortune_ and returning, "`Today is your lucky day.`"
So that's the new endpoint that we just added for this **RESTController**.
And I'll swing over here and save it.
And we should see some action moving there on our screen.
So that's good.
So it did the restart on our application automatically.
And now, kinda swing back over here to our browser
and access that new endpoint `/fortune`.
So this is awesome.
It was able to load our new endpoint here
by simply making use of the **dev tools** component here.
And we simply added that to our **Maven** dependency file,
and we got the automatic reloading for free with **Spring Boot**.
So I recommend that you use this for your applications
as you go through your development process.
</div>

## [Spring Boot Actuator]()
<div style="text-align:justify">

In this section, we'll cover the **Spring Boot Actuator**.
You may have the questions:

* How can I monitor and manage my application? 
* How can I check the application status?
* How can I access some application metrics?

Well, one solution here is by making use of the **Spring Boot Actuator**.
The **Spring Boot Actuator** actually exposes endpoints for you 
to monitor and manage your application.
So, you easily get the **DevOps** functionality out-of-the-box.
You simply add the dependency to your **Maven** `pom` file,
and then these **REST** endpoints are **automatically** added to your application.
So, the nice thing about it is that there's no need to write additional code, 
you get these new REST endpoints for free, and I like free stuff.
So this is pretty cool.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Now, adding a dependency to the **Maven** `pom` file,
we simply give a reference here, and we say **spring-boot-starter-actuator**.
And that'll basically give us support for these metrics
and monitoring of our given application.
Now remember, **Spring Boot Actuator** automatically exposes endpoints 
for metrics out-of-the-box.
All the endpoints are prefixed with `/actuator`.
Now, here are some of the endpoints that we can make use of.

| Name      | Description                               |
|-----------|-------------------------------------------|
| `/health` | Health information about your application |

So, we have `/health`.
This gives you health information about your application,
and there are some other endpoints that I'll talk about in a bit.
And just remember, you get these new **REST** endpoints for free, so again, 
there's no additional coding you have to do, 
you simply add that item to the actual **Maven** dependency for **Spring Boot Actuator**,
and you're ready to go.
So, now let's go ahead and start by taking a look at the health endpoint.
So, the health endpoint, or `/health`, checks the status of your application.
This is normally used by monitoring apps to see if your application is up or down.
So, here's a little screenshot of our given application, `/actuator/health`.

![image34](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image34.png?raw=true)

Right now the status is up.
Now, this health status is customizable,
so you can actually customize this based on your own custom business logic.

Now, by default, only `/health` is exposed.
The `/info` endpoint can provide more information about your application, 
you can actually customize it.
Now, to expose `/info`, you need to make an update in the `application.properties` file.

```properties
management.endpoints.web.exposure.include=health,info
management.info.env.enabled=true
```

For this property here, `management.endpoints.web.exposure.include=health,info`.
We're simply giving a comma-delimited list of endpoints to expose.
Also, we'll set the property here
for `management.info.env.enabled=true`.
And once we set these two items up, then this will expose the `/info` endpoint.

Now, let's take a look at the info endpoint.
So, `/info` gives you information about your application.
So, the default is that it's actually empty.

![image35](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image35.png?raw=true)

So, if you access this endpoint, you'll get this empty **JSON** object, which is nothing.
You're like, huh, that's not too useful.

```properties
info.app.name=My Super Cool App
info.app.description=A crazy and fun app, yoohoo!
info.app.version=1.0.0
```

So, we actually need to customize this info endpoint,
and we can do that by updating the application properties with our given information.
So, we simply add in some properties here 
for `info.app.name`, `app.description`, and `app.version`.
And that's our actual info for our given `/info` endpoint.
Now, anything starting with `info.` as for the property name, 
then that'll be used by this `/info` endpoint.

![image36](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image36.png?raw=true)

So, here's a little screenshot here, so I have `actuator/info`, 
and it'll send back a **JSON** object that has the information 
from this given properties file.
And again, remember, everything's keyed on the `info.`.
So, anything following `info.` will be used as part of this given **JSON** object.
And you can customize it, you can add as many details here
as you'd like for this given info for your application.

| Name           | Description                                                    |
|----------------|----------------------------------------------------------------|
| `/auditevents` | Audit events for your application                              |
| `/beans`       | List of all beans registered in the Spring application context |
| `/mappings`    | List of all @RequestMapping paths                              |
| ...            |                                                                |

Now, let's take a look at some other **Spring Boot Actuator** endpoints.
So, there are actually 10+ actuator endpoints that are available.
Here's a small sample.
So, you have `/auditevents`.
It gives you a list of the audit events for your application.
`/beans` will give you a list of all beans
that are registered in the **Spring** application context.
`/mappings` will give you a list of all request mappings for your given application.
Now, for a full list of all the actuator endpoints,
you simply go to this link [here](https://www.luv2code.com/actuator-endpoints). 
It'll redirect you to the official **Spring Boot Actuator** documentation, 
and you can see a full list of all those actuator endpoints.

Alright, now, by default only the `/health` endpoints are exposed.
If you want to expose all the actuator endpoints over the web, 
then you simply make this entry here in your properties file.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
#
management.endpoints.web.exposure.include=*
```

So, in `application.properties`, we can make use of the wildcard to expose all endpoints.
We can also expose individual endpoints with a comma-delimited list.
But for right now, we'll use the wildcard.
So, I'll say `management.endpoints.web.exposure.include=*`.
So, this given entry here will expose 
all the **Spring Boot Actuator** endpoints over the web.

Now, once we have that set up to get a list of beans for a given application,
then we can go to `localhost/actuator/beans`, and it'll give us an actual dump, 
or a JSON, of all the beans that are registered with this **Spring Application** context.

![image37](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image37.png?raw=true)

So this is pretty cool, but, and hold on here,
you're probably wondering; what about security?
Because I really don't want to expose all of this information to anyone on the web.
And I got you covered.
So, we'll actually cover security in some of the later sections.
So, we'll actually add security,
and we'll lock down these **Spring Boot Actuator** endpoints
only to authorized users who have to log in with the **userID** and **password**.
So, we'll cover all of those steps in later sections.

Okay, let's go ahead and look at the development process.

1. the first step is simply editing our **Maven** `pom` file,
and we add the **spring-boot-starter-actuator** dependency.
2. Then we can go through and view the actuator endpoints for `/health` and `/info`.
And remember, we get all those endpoints for free.
3. And then we can actually edit our `application.properties`, 
and customize the `/info` endpoint, 
so we can give specific information, or custom information, for our given application.

Let's go ahead and move into our IDE.
And if your **Spring Boot** app is already running now, please stop it.
And let's do some housekeeping work.
So what we'll do is we'll actually close our IDE,
we'll copy some project folders, just to keep our projects organized.
Now, this is an optional step.
It's not required, but I'll like to do it
just so I can keep my projects organized and I can easily go back
to a previous version of the application.
Again, that's my personal preference.
There are other developers who prefer to simply work
with one project the entire course.
So it's totally up to you.
But I'll do this housekeeping task here.
If you don't want to do it, feel free to skip past it.
Alright, I'll go through and close all my tabs here, and I'll close this window.
And now I should have just a basic IntelliJ window.
I'll go ahead and open up my file system here,
and I'll move into the dev-spring-boot folder,
and I'll move into the `01-spring-boot-overview` folder,
and I'll copy this project, `02-dev-tools-demo`.
And then I'll just paste it right here in the same directory.
And then I'll just rename it, and I'll call it `03-actuator-demo`.
Now go ahead and open up this `03-actuator-demo` in IntelliJ.
And one cool trick here is that you can simply drag 
and drop your project folder over to IntelliJ, 
and it'll automatically open it for you.
So you can see up at the top `03-actuator-demo`,
so that's our project that we have open right now,
and that's the one that we'll use in this video.
And with step one, we'll actually edit the **Maven** `pom.xml` file,
and we'll add the **spring-boot-starter-actuator**.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.luv2code.springboot.demo</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mycoolapp</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- ADD SUPPORT FOR SPRING BOOT devTools-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
        
        <!-- ADD SUPPORT FOR SPRING BOOT ACTUATOR-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

So I'll open the file here, zoom out and expand the window.
I'll just go ahead and copy the dependency from above.
And then I'll just kind of move down here, and I'll just paste it, 
and I'll just move in here, and just update the entry for the _artifactId_,
so this will be `spring-boot-starter-actuator`.
And just to kind of keep myself on track,
I should have entered this earlier, but I forgot,
but I'll just add the comment right now.
So this is for adding support for **Spring Boot Actuators**.
So this will give us those additional **REST** endpoints for free.
No real need for us to write any code.
And this is all for the management and metrics of our given application.
Remember to press CTRL+S and load **Maven** changes.

```properties
management.endpoints.web.exposure.include=health,info
management.info.env.enabled=true
```

Now, by default only `/health` is exposed.
The `/info` endpoint can provide more information about your application.
You can actually customize it.
Now to expose `/info`, you need to make an update in the `application.properties` file.
For this property here, `management.endpoints.web.exposure.include=health,info`.
We're simply giving a comma delimited list of endpoints to expose.
Also, we'll set the property here for `management.info.env.enabled=true`.
And once we set these two items up, then this will expose the `/info` endpoint.
Now I just want to go ahead and test this out by just simply running my application here.

```html
2024-05-19T10:06:09.345+03:00  INFO 37144 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-19T10:06:09.377+03:00  INFO 37144 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-19T10:06:09.377+03:00  INFO 37144 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-19T10:06:10.313+03:00  INFO 37144 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-19T10:06:10.321+03:00  INFO 37144 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-19T10:06:10.322+03:00  INFO 37144 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-19T10:06:10.354+03:00  INFO 37144 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-19T10:06:10.355+03:00  INFO 37144 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 976 ms
2024-05-19T10:06:10.939+03:00  INFO 37144 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-19T10:06:10.947+03:00  INFO 37144 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
2024-05-19T10:06:11.000+03:00  INFO 37144 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-19T10:06:11.010+03:00  INFO 37144 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 1.915 seconds (process running for 2.2)
```

So I'm just going to run it as a Java application.
And if I expand the window here,
I can kind of investigate just a little bit.
So if I move to the bottom and kind of scroll over to the right-hand side,
we'll see this entry created here or this log entry.
It says exposing two endpoints beneath base `/actuator`.
So those are the new endpoints that are given to us for free,
thanks to adding a **Spring Boot Actuator**.
And remember, all the actuator endpoints are prefixed with `/actuator`.
So let's go ahead and move to step two of viewing the actuator endpoints
for `/health` and also `/info`.
So I'll just swing over here to my web browser,
and I'll open up `localhost:8080/actuator/health`.
And this gives us the `{"status" : "UP"}`.
So this can be used by external monitoring apps
to see if our application is up or down.
And remember, we can always customize this logic
for specific business cases for our given application.
Now let's also access `/actuator/info`
to give us information about our application.
And remember, by default, it's empty, but let's go ahead and fix that.
Let's go ahead and make the update, 
so we can get some customized information for our application.

```properties
management.endpoints.web.exposure.include=health,info
management.info.env.enabled=true

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0
```

So, I'll say `info.app.name=My Super Cool App`.
I'll do `info.app.description`
just give a little nice description for our application.
And then I'll also give a version, so `info.app.version=1.0.0`.
And you can give any property names as long as they start with `info.`,
and this will actually be used by the `/info` endpoint when we access it.
Alright, so I just make those changes, save it.
Spring Boot will automatically reload our application
thanks to **DevTools** since that's in place.
Now I can swing back over to my web browser.
It'd simply do a reload on this actuator `/info` and voilà.

```html
{"app":{"name":"My Super Cool App","description":"A crazy fun app, yoohoo!","version":"1.0.0"}}
```

There we go.
So now it's reading information from our `application.properties`.
So it uses any of the properties that start with `info.`.
And I'll return that as a **JSON** object.

Now, I'd like to do is add JSON pretty print plugin to the Chrome browser
just so we can see this in a nice pretty fashion.
Because right now it's all one big line or whatever.
If you're using Firefox, this already has it built in.
So in Firefox, choose the Raw Data and then choose Pretty Print.
So Firefox has it built in.
I'll just go through some additional steps here
to kind of get Chrome up and running with this plugin.
So I'll simply go [here](https://www.love2code.com/chrome-json-formatter).
This will redirect us to the plugin that we can use for Chrome.
Alright, so we'll see this **JSON Formatter** here.
We just choose the option over on the right, `Add to Chrome`,
accept the prompt here to add the extension
and any follow-on prompts that you may have.
And now if I go back to my webpage and just reload this page,
I should see the plugin show up here for Chrome.
So what you see on the top right,
that's the **Pretty Print plugin** for Chrome.
So right now it's showing it is pared.
You can see **Raw**, just the **JSON** without any formatting or **Pretty Printing**.
And **Parsed** is for **Pretty Printing**.
So just to kind of make it easier for a human to read for this given setup.
So we have the JSON data coming back for our `/info` endpoint.
All right, so let's go ahead and move forward here.
And let's expose more actuator endpoints.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0
```

So we can use the wildcard * to expose all endpoints,
or we can expose individual endpoints with a comma-delimited list.
And I'll add the entry here to expose all the endpoints.
So I give the entry here `management.endpoints.web.exposure.include=*`.
So that's the wildcards.
Expose all the given endpoints for our given application.
Alright, so let's go ahead and save this here.
Our app's going to restart.

```html
2024-05-19T10:22:28.349+03:00  INFO 37144 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-19T10:22:28.489+03:00  INFO 37144 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-19T10:22:28.489+03:00  INFO 37144 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-19T10:22:28.489+03:00  INFO 37144 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-19T10:22:28.505+03:00  INFO 37144 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-19T10:22:28.505+03:00  INFO 37144 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 155 ms
2024-05-19T10:22:28.618+03:00  INFO 37144 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-19T10:22:28.620+03:00  INFO 37144 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path '/actuator'
2024-05-19T10:22:28.633+03:00  INFO 37144 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-19T10:22:28.636+03:00  INFO 37144 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 0.298 seconds (process running for 979.827)
2024-05-19T10:22:28.641+03:00  INFO 37144 --- [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation delta:
```

And then one thing to notice here in the console,
we have more endpoints that we can take a look at and play around with, cool.
So swinging back to our web browser, let's test this out.

![image38](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image38.png?raw=true)

So I'll go to `/actuator/beans` and good.
So this is a list of all the Spring beans that are registered with our application.
This also includes internal beans and so on.
And also any beans that you've created with `@Component`.
So this is good for debugging your given application 
to see if a given beans been created or if it's being used in your app.

![image39](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image39.png?raw=true)

Also, let's take a look at another endpoint here `/actuator/threaddump`.
So this will give us a list of all threads that are running in our application.
And this is perfect for analyzing and profiling your application's performance
if you're looking for any bottlenecks or anything.

![image40](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image40.png?raw=true)

And then another endpoint we can take a look at is `/actuator/mappings`.
So this will give us a list of all the request mappings for your given application.
So if you want to say, "_hey, what endpoints are being exposed_,
_what request mappings are available that endpoint can really help you out in that regard_".
So we've exposed the **Spring Boot actuator** endpoints 
and went through and tested out a couple of them.

Now, let's look at **Spring Boot Actuator - Security**.
So previously I showed you how to expose 
all the different **Spring Boot Actuator** endpoints, and you're probably, 
"_Well, what about security? 
We may **NOT** want to expose all of this information to just anyone on the web._"
So what we'd like to do is add **Spring Security** to our project and secure our endpoints.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

So we can do that very easily by simply adding the **spring-boot-starter-security** 
dependency to our **Maven** project and secure our endpoints with one small caveat.
So the `/health` will still be available,
but I'll show you how we can actually disable those.

So once we add this **Spring Boot starter security** dependency,
then when we access `/actuator/beans`,
**Spring Security**'s gonna actually prompt for a login
and the user will have to give their username and password
to log in to or access those given endpoints.
Now, the default username is **user** and then also as for the password,
you have to look at the actual console to figure out what the default password is.
So you'll see this entry and the console logs.
It'll say, "_Hey, using generated-security password
will give you this big-long password._"
So that's one approach by using the default username of user 
and using the generated password.

```properties
spring.security.user.name=scott
spring.security.user.password=tiger
```

What if you actually wanted to override those defaults and give your own username and password?
Well, in the `application.properties` file 
you can say `spring.security.user.name=scott` and then `spring.security.user.password=tiger`
or whatever password you want to give.
So those are the actual usernames and passwords 
that **Spring Security** will use 
when they're actually authenticating a given user for accessing those **Actuator** endpoints.
Now you may wonder, "_Okay, well, I want to do something more than just that properties file._"
Well, no worries.
You can customize **Spring Security** for the **Spring Boot Actuator**, 
so you can use a database for roles, encrypted passwords and so on.
We'll cover the details of **Spring Security** later in the course.

```properties
# Exclude individual endpoints with a comma-delimited list
management.endpoints.web.exposure.exclude=health,info
```

We can also exclude endpoints.
So to exclude `/health` and `/info`,
we simply add an entry here in `application.properties`.
We say `management.endpoints.web.exposure.exclude`
and then we give a comma-delimited list,
so **health, info** and that way no one can access those given endpoints.

Alright, now if you'd like to get more details on **Spring Boot Actuator** and so on,
simply go to the link [here](https://www.luv2code.com/actuator-docs).
It'll redirect you to the official page, 
and you can get all the glory details here on **Spring Boot Actuator**.
Now let's take a look at the actual **Development Process** here.

1. The first thing we do is edit the `pom.xml` file,
and we'll add `spring-boot-starter-security` to help us secure those endpoints.
2. Then we'll actually verify security on those endpoints
by going to `/beans` and so on.
3. And then we'll also go through and disable endpoints for `/health` and `/info`.

Okay, let's move back into our application here.
And you know right now we currently have all these endpoints exposed.
And like I said earlier, we may not want to expose all this information.
We need to secure this.
We need to kinda lock it down with some spring security.
Alright, so let's go ahead and move into our IDE.
And if your **Spring Boot App** is already running now, please stop it.
Let's do some Housekeeping work.
For the Housekeeping work, I'll be quiet over here,
and you can just follow along with my steps.
I'll create a new project folder by copying and pasting `03-actuator-demo`,
and rename it `04-actuator-security-demo`.
Step-1: Edit `pom.xml` and add **spring-boot-starter-security**.
After opening that project:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.luv2code.springboot.demo</groupId>
    <artifactId>mycoolapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mycoolapp</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>

        <!-- ADD SUPPORT FOR SPRING BOOT ACTUATOR-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- ADD SUPPORT FOR SPRING SECURITY-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

Alright, so I'm in my `pom.xml` file here.
I'll add a quick comment here.
So we're going to add support for spring security.
And I'll just kind of copy this section from up top,
and just do a little copy-paste exercise
and then just move down,
and I'll just do a paste, 
and then I'll just go through and do a quick update here and an _artifactId_.
So spring boots starter security.
And by adding this dependency, we automatically have security on our **REST** endpoints.
Okay, so we have the support here for spring security in our given **Maven** `pom` file.
Please remember to load **Maven** changes.
And I'll just swing over here and just run the application one more time.

```html
2024-05-20T11:41:09.868+03:00  INFO 5568 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-20T11:41:09.911+03:00  INFO 5568 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-20T11:41:09.911+03:00  INFO 5568 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-20T11:41:11.071+03:00  INFO 5568 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-20T11:41:11.085+03:00  INFO 5568 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-20T11:41:11.086+03:00  INFO 5568 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-20T11:41:11.133+03:00  INFO 5568 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-20T11:41:11.134+03:00  INFO 5568 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1222 ms
2024-05-20T11:41:11.791+03:00  WARN 5568 --- [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: d1e359d2-dfed-40e3-b18f-5ac912225ec4

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2024-05-20T11:41:11.886+03:00  INFO 5568 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path '/actuator'
2024-05-20T11:41:11.895+03:00  INFO 5568 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@2a341243, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@76d50fe1, org.springframework.security.web.context.SecurityContextHolderFilter@40271e4f, org.springframework.security.web.header.HeaderWriterFilter@1d1d4ce4, org.springframework.web.filter.CorsFilter@69059225, org.springframework.security.web.csrf.CsrfFilter@1876912b, org.springframework.security.web.authentication.logout.LogoutFilter@2d8466d3, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@2d8770b2, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@50673517, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@6f77f9ae, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@41d3bdd1, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@7e80e665, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@23c97aa7, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@65db5809, org.springframework.security.web.access.ExceptionTranslationFilter@6f2ed4de, org.springframework.security.web.access.intercept.AuthorizationFilter@1d4cd0ce]
2024-05-20T11:41:11.921+03:00  INFO 5568 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-20T11:41:11.965+03:00  INFO 5568 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-20T11:41:11.972+03:00  INFO 5568 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 2.456 seconds (process running for 2.828)
```

Okay, so the app is up and running.
Let's do a little bit of investigating here in this console.
So in the spring boot console here and moving down.
And so the default userID is **user**, but the password is this generated password;
that's included right here, `d1e359d2-dfed-40e3-b18f-5ac912225ec4`, in the logs.
And remember, you can always customize this,
using regular spring security techniques by adding in databases or encrypted passwords.
But for now we'll just kind of make use of the default security password here.
Okay, so let's go ahead and verify the security on the actuator endpoints 
for `localhost:8080/actuator/mappings`.

![image41](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image41.png?raw=true)

Oh yeah, this is good.
So we have security, so we try to access mappings,
but it prompted us to log in, so we know the default userId as **user**.
And then for the password, we need to use that generated password.
So and it's a big long password.
So let me swing over the terminal,
and kind of do a little copy-paste exercise on this.
So I'll just kind of copy that information from the console,
swing over here to the dialogue and then just kind of paste it on the login page.
And now go ahead and log in.

![image42](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image42.png?raw=true)

Oh yeah, so this is great.
So now the system's actually prompting us for userId and password.
We give that information, and then they can give us all the different details here,
for those actuator endpoints.
So we have security in place,
so a random person can't just come through and actually log in, 
you know, and access those mappings.
So this is all secured now.
And if I try to access this `actuator/info`,
remember earlier I said that's still available,
even if you have security enabled,
that's already available by default.
So what we can do here is actually disabling those endpoints.
So we're going to disable the endpoints for /health
and also disable the endpoints for /info.

Step 3: Disable endpoints for `/health` and `/info`.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

# Exclude individual endpoints with a comma-delimited list
management.endpoints.web.exposure.exclude=health,info

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0
```

So I can disable that in my `application.properties` file.
So on application properties,
I'm going to do an excluding on a comma-delimited list of endpoints here.
So I'll just kind of copy-paste the line from above,
and I'll just paste it, and I'll make one important update.
So instead of **include**, I'm going to say **exclude**.
And now I give the list of the endpoints that I want to exclude.
So here I want to exclude **health** and **info**.
All right, so now these two new endpoints, 
they're excluded, they won't be available.
So I just save and then, if I move back to my browser,
I should get 404 on those given endpoints,
because now we've excluded them.
They're not even available anywhere.
So move over here to `localhost:8080/actuator/health`.
Just do a reload on this page.

![image43](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image43.png?raw=true)

And we get 404, the white label error.
And that's good because we actually disabled,
the `/health` endpoint or excluded that given endpoint.
Now let's do a similar thing for info.

![image44](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image44.png?raw=true)

So `actuator/info`, again, we get this error page,
and that's fine again because we disabled the info endpoint.
Now I actually want to comment out that configuration,
because /health and /info,
they're actually very useful for real-time applications and real-time projects.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

# Exclude individual endpoints with a comma-delimited list
# management.endpoints.web.exposure.exclude=health,info

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0
```

So I simply did this earlier just as an academic exercise,
but here I normally recommend to keep those available for real-time projects 
just because it's great,
for DevOps work and for application monitoring.
So I just commented that out,
and now we can actually access those given endpoints,
for `/health` and also `/info`.
So nice little test here, by making use of the spring boot actuator
and actually adding security to those given endpoints.
</div>

## [Spring Boot Run from Command-Line]()
<div style="text-align:justify">

In this section, we're going to learn how to run **Spring Boot** from the command line.
So, during development, we spend most of our time in the IDE.
So your favorite IDE of choice.
However, we may want to run our Spring Boot applications outside the IDE.
And so, one approach is running it from the command line.

Now, when running from the command line,
there's actually no need to have your IDE open and running.
So whatever IDE you're using,
you can simply do a file exit and move out of that
and just run your app standalone or from the command line.
So, since we're using **Spring Boot**,
the server is actually embedded in our JAR file.
So there's no need to have a separate server installed,
there's no need to have **Tomcat** running separately.
Because our **Spring Boot** apps are self-contained,
our **Spring Boot** apps actually include the server already.
So, since our **Spring Boot** apps are self-contained,
then we have this `mycoolapp.jar`,
and then this JAR file actually includes our application code, and the actual server.
So it's a self-contained unit.
There's nothing else you have to install.

Now, running from the command line,
you actually have two options:

1. Option one is that you can use `java -jar`, and give the name of your JAR file.
2. Or with option two, you can make use of the **Spring Boot Maven** plugin
by simply given `mvnw spring-boot:run`.
I'll show you both options here in this section.

So the first option, option one, is that we simply make use of `java -jar`.
So if we have a **Spring Boot** application called `mycoolapp.jar`,
then we can simply run it from the command line by saying `java -jar`,
and then we give the actual name of our JAR file.
In this case, `java -jar mycoolapp.jar`.
And it'll start up our application.
It'll start up the server, and it's up and running.
There's no need for an IDE.
There's no need to have a separate server installed.
Here, we're all self-contained.

So let's look at option two of using the **Spring Boot Maven** plugin.
So remember in our **Spring Boot** project we have these `mvmw` commands.
They allow us to run a **Maven** project,
and in this case with `mvmw`,
there's no need to have **Maven** installed or present on your path.
If the correct version is not there, then it'll automatically download
the correct version of **Maven**.
And remember, we covered this in a previous section.
So they give you two files here from `mvnw`.
You get the `mvnw.cmd` from Microsoft Windows.
And there's also the `mvnw.sh` for Linux/Mac.
So again, just makes it really easy for you to get started with **Maven**
without actually having to manually install it yourself.

What if you already have **Maven** installed?
Well, in that case, you can simply ignore 
or delete those `mvnw` commands and get rid of 'em.
And simply just use Maven as you normally would.
So you'd say, `mvn clean compile test`.

So a bit more here into the specifics here with this **Spring Boot Maven** plugin.
So, in your `pom.xml` file,
you already have this entry here that was generated by the **Spring Initializer**.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

We have this plugin here for the _artifactId_ of `spring-boot-maven-plugin`.
So, we can use this plugin to actually package an executable jar file or a war file.
And we can also use it to easily run our application.
So to package our application, we say `./mvnw package`.
And then to actually run our application using a **Spring Boot** plugin 
we say `./mvnw spring-boot:run`. 
And remember, we can also just use `mvn package`,
or `mvn spring-boot:run` if we already have **Maven** installed.
Alright, so let's look at our development process here.

So what we'll do in this next is 

1. We'll actually exit the IDE 
2. And then we'll actually package the application using `./mvnw package`.
3. And then we'll run it first using `java -jar`.
4. And then we'll also come back, and we'll run it using a **Spring Boot Maven** plugin
of `./mvnw spring-boot:run`.

And we'll see how different things work out, 
different scenarios of running **Spring Boot** from the given command line.

So let's go ahead and move into our IDE.
And if your Spring Boot app is already running now, please stop it.
Let's do some housekeeping work.
First, close the project window, and second copy project folders.
So we create a new project folder, named `05-command-line-demo`,
by taking a copy from `04-actuator-security-demo`.
And opening that project now.
So I'll move down here to my **Maven** `pom` file,
and I'll open it up for a second.
Alright, so let's go ahead and just do some cleanup.
Let's delete the **actuator** and **security** dependencies here
because they're not needed for this demo.
Just make things easier for us as we go through everything.
So let's save this here on our `pom` file.
And so step one, we're actually going to exit the IDE
because we're going to run it from the command line outside the IDE.
So no need for the IDE to be running.

Now, the command line steps are different depending on the operating system that you're using.
I'll only discuss for the users of Microsoft Windows.
At the moment, my files are on the D drive under `D:\JAVA_STUDY\Github\dev-spring-boot\`.
Now you can place your files anywhere in your file system.
Just make sure that they're easy to access.
Also, as a warning, don't use directories 
that have spaces in them because it'll cause problems for the Java runtime system.
So make sure you just have a regular directory file system, something without spaces.

Alright, so I'll go ahead and move into my dev-spring-boot directory.
We know that we have this **01 directory** here.
Let's go ahead and move into that directory.
And then the new directory that we created a little earlier,
we have the `05-command-line-demo`.
So let's go ahead and move into that directory.
That's the one that we're going to use for now.
Alright, so I'm in the `05-command-line-demo` directory.
Now let's go ahead and open up a command prompt window.
Just go down here to the little search icon, type in `CMD` for command,
and it'll give you this reference here.
Command Prompt, go ahead and select that app.
And now this opens up the command prompt window.
Okay, now let's check to see if **Java** is installed properly,
and `JAVA_HOME` must be edited in environment variables.
This is a requirement that we need to have in place
before we can actually run the **Spring Boot** apps from the command line.
First, type this command, `java --version`, and then you should get this output here.

![image45](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image45.png?raw=true)

Then you're okay and **Java** is installed properly.
However, if this entry is missing, then **Java** is not installed properly.
So you need to have both of these items correct to have **Java** installed properly.
So if you're missing it, then there's some work we have to do.
Simply go to the link [here](https://www.luv2code.com/install-java-on-ms-windows), 
and it'll take you to a YouTube video,
and it'll walk you through the entire process of installing **Java** properly.
But the important thing is that you have to have **Java**
installed properly before you can proceed.
Next, type `echo %JAVA_HOME%`, and hit Enter.

![image46](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image46.png?raw=true)

This means I don't have edited environment variables.
So, I should open `Edit environment variables for your account` from `settings`.

![image47](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image47.png?raw=true)

This will bring up this dialogue here.
And what we'll do is we'll create a new environment variable for java home.
It's a special environment variable used by the Java system.
I'll choose `new`.
For the variable name `JAVA_HOME`.
For the actual value, I'll use the browse directory 
to actually browse to where our jdk directory is on my file system.
It's in `C:\Users\korha\.jdks` directory.
I'll select the latest jdk version, which is `openjdk-21.0.2` for now. 

![image48](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image48.png?raw=true)

Now, we're going to edit the environment for the path 
to include a reference to our jdk path.
So I'll select the `Path` variable here,
and I'll choose `Edit`.
I'll click again, `New` button.
And I'll type `%JAVA_HOME%\bin`.

![image49](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image49.png?raw=true)

So this will actually refer to the other environment variable
that we created earlier using the percents `%`s,
and then I'll move this up to make sure that 
this value appears before the other values here.
So you should have `%JAVA_HOME%\bin` listed first.
And that's all we need to do here. 
I'll click Ok's.
Let's go ahead verify the environment variable. 
I'll open the command prompt window.
And I'll type `echo %JAVA_HOME%`, and hit Enter.

![image50](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image50.png?raw=true)

Alright, so Java's installed properly,
we can go ahead and clear this screen.
And now what I want to do is move into our project directory on the command line.
First, I'll type `d:`, and then simply hit `Enter`:

![image51](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image51.png?raw=true)

What I'll do next is I'll actually move up to the explorer window here,
and I'll just copy this directory path, 
which is `D:\JAVA_STUDY\Github\dev-spring-boot\01-spring-boot-overview\05-command-line-demo`.
I'll type `cd`, space, and then I'll paste this directory path by clicking right.
And then simply hit `Enter`, 
and now you should be in that directory `05-command-line-demo`.

![image52](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image52.png?raw=true)

Now let's go ahead and package the app using the `mvnw package command`.
I'll type in `mvnw package`,
and **Maven** will go off and do its work:

![image53](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image53.png?raw=true)

And a lot of work in the background, eventually you'll see build success.
And also the important thing here is that
this created a new file for us in this target directory
called `mycoolapp-0.0.1-SNAPSHOT.jar`.
That's the new JAR file that was created by `mvnw package`.
And that's the actual JAR file that we'll run in a second.
I'll type `java -jar target\mycoolapp-0.0.1-SNAPSHOT.jar`
and then this will actually start up our **Spring Boot** app.
So here we can see that our **Spring Boot** app is up and running.

![image54](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image54.png?raw=true)

Now let's test this out.
Let's go ahead and open up our browser,
and then we'll go to `localhost:8080`:

![image55](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image55.png?raw=true)

And that'll give us our hello world, which is great.
Let's also test out some of our other end points that we have here.
We have a `/workout`.

![image56](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image56.png?raw=true)

Okay, that's good.
And then we also have our other one, `/fortune`.

![image57](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image57.png?raw=true)

So this is great, so we see that our application is up 
and running, and it's running from the command line.
No need to have the IDE open, we're simply doing it from the command line.
Now let me swing back over to my command prompt window
and stop my application by simply doing `Control + C`.
Alright, so the app is stopped; it's not running now.

![image58](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image58.png?raw=true)

And what I'd like to do now is actually run the app
using the **Spring Boot Maven plugin**.
So just a different way of running the application.
We don't really have to give the full JAR files.
**Spring Boot** will figure all that out for us.
So here I can say `mvnw spring-boot:run`

![image59](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image59.png?raw=true)

It kind of goes through the same process, and now again, our application is up and running.
And I can confirm this by just simply going over
to the browser and just doing a reload on any one of my pages here.
And great, so I'm getting the results as desired.
So this was great.
Move back into our command prompt window here
and let's go ahead and stop our application with the `Ctrl + C`.
Now just type exit to exit out of this command prompt window.
So the app's no longer running, I can close the browser.
And so success, so we're able to run our **Spring Boot** app
from the command line on Microsoft Windows,
and there was no requirement to have the IDE up and running.
We did everything from the command line.
</div>

## [Injecting Custom Application Properties]()
<div style="text-align:justify">

In this section, we'll cover **Spring Boot and custom application properties**.
So you have a problem you need for your app to be configurable,
no hard coding of values,
and you also need to be able to read app configuration from a `properties` file.

So the solution is to make use of an app `properties` file.
So by default, **Spring Boot** will actually read information from a standard `properties` file.
And this file is located at `src/main/resources/aplication.properties`.
So that's _the standard **Spring Boot** file name_ of where to look for, 
for application properties. 
And you can actually define any custom properties in this file.
And your **Spring Boot** app can access these properties
by simply using the `@Value` annotation.
And other than that, there's no additional coding or configuration required.
**Spring Boot** will automatically load this `application.properties` file
and make it available to you via the `@Value` annotation.
Alright, so let's look at the development process:

1. So the first thing we're going to do is define custom properties
in our `application.properties` file.
2. And then we'll actually inject those properties 
into our **Spring Boot** application using the `@Value` annotation. 

Okay, so let's start with step one of defining custom application properties.

```properties
#
# Define custom properties
#
coach.name=Mickey Mouse
team.name=The Mouse Club
```

We'll make use of that standard Spring Boot file name, `application.properties`,
and we'll define our own custom properties.
So for this example, I'll define a `coach.name=Mickey Mouse`,
and `team.name=The Mouse Club`.
And again, remember, you can use any custom property names in this given file,
and there's also no limit on the number of properties you can add.
You can add as many properties here as you'd like.

```java
@RestController
public class FunRestController {

    // inject properties for: coach.name and team.name
    
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;
}
```

Alright, so let's look at step two of injecting the properties into our **Spring Boot** application.
And what we'll do is we'll make use of that rest controller from the previous sections.
So we had our **FunRestController**,
and what we'll do is we'll actually inject the properties for `coach.name` and also `team.name`.
So what I'll do here is I'll make use of that @value annotation,
and I'll inject the property `coach.name`,
and I'll assign it to this field we have here called **String** _coachName_.
And remember, this is actually injected from the actual properties file, 
`application.properties`, It'll get that actual property name,
take that value, and inject it here for this given field.
And then I just repeat the process here for `team.names`.
I use `@Value("${team.name}")`.
And again, it'll actually pull the information from the properties file `team.name`, 
give the value here, assign it to our field _teamName_, and that's basically it.
So the nice thing to notice here is that 
there's no additional coding or configuration required.
Spring Boot will automatically load that `application.properties` file,
make it available to your application, and you can access those values
by using the `@Value` annotation.


Alright, let's go and move into our IDE.
And if your **Spring Boot** app is already running now, please stop it.
And we create new package folder by copying and pasting `05-command-line-demo`.
I'll call it `06-properties-demo`.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

# Exclude individual endpoints with a comma-delimited list
# management.endpoints.web.exposure.exclude=health,info

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0

#
# Define my crazy properties
#

coach.name=Mickey Mouse
team.name=The Mouse Club
```

Step-1: Define custom properties in `application.properties`.
So I'll just move down to my `application.properties`.
So that's kind of my step one of defining my custom properties in `application.properties`,
kind of expand this window here.
And remember `application.properties`
that's the standard spring boot application properties name
that we can use to add our own custom or crazy properties.
And so my first property here
I'll call it `coach.name` equals _Mickey Mouse_.
And the `team.name` is The _Mouse Club_.
You can give any values here that you'd like.
And also we can give any custom property names here that we'd like also.

Okay, so now Step-2: Injecting those properties into our spring boot application
using the `@Value` annotation.
So let's go ahead and move into our **FunRestController**.

```java
@RestController
public class FunRestController {

    // inject properties for: coach.name and team.name
    
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
```

And let me write a quick comment here to myself,
just to keep myself on track.
Okay, great, so we're going to inject properties for `coach.name` and also `team.name`.
So let me just set up the fields here for _coachName_.
Do a similar thing here for _teamName_.
And then remember we actually inject those properties using the `@Value` annotation,
and we give the dollar sign curly brace,
and the actual property name `coach.name`,
so that portion looks good.
And then also let me just do a little copy-paste on this line,
and just paste it down here.
And let me just update the actual property name.
So that's `team.name`, save that.
What I'd like to do is actually go through and add a method or add a new endpoint
that'll use this information.
So let me just put a little quick comment here.

```java
@RestController
public class FunRestController {

    // inject properties for: coach.name and team.name
    
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;
    
    // expose new endpoint for "teamInfo"
    @GetMapping("/teamInfo")
    public String getTeamInfo() {
        return "Coach: " + coachName + ", Team name: " + teamName;
    }
    
    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
```

So this new endpoint, we'll call it **teamInfo**,
and it'll give us the information for the coach name, and also the team name.
All right, so just a basic method here just to get mapping `/teamInfo`.
We have a method public stream, `getTeamInfo`, and we'll return the _coachName_,
which is at field that we have up above,
And also the _teamName_, and that's it, just basic, very straightforward.
And so remember here, coach name is that field _coachName_,
and team name maps over to that field _teamName_.
All right, so that looks good so far, and I guess we can go ahead and run it right?
Let's go ahead and test this out.
So just run it as a Java application.

![image60](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image60.png?raw=true)

Alright, so our app is up and running.
Let's swing over to our web browser here, and let's access team info.
So we do the `localhost:8080/teamInfo`.
And, it works.
So we have the Mickey Mouse coming in, and we have a team name of the _Mouse Club_.
And this is all based on information from that properties file, right?
Those custom properties that we defined, and we simply injected those properties
using the `@Value` annotation.
So this is a good example here of how you can define your own custom properties
and use them in your application.
</div>

## [Configuring the Spring Boot Server]()
<div style="text-align:justify">

In this section, we'll cover **Spring Boot properties**.
**Spring Boot** can be configured in the `application.properties` file.
So, some of the properties you can set is 
that you can actually set up the actual server port,
the context path, actuator endpoints, security settings, and so on.
And in fact, **Spring Boot** has over 1000 properties.
But hey, don't worry; 
this link [here](https://www.luv2code.com/spring-boot-props) 
actually has a list of the common properties here.
So, it'll actually redirect you to the official **Spring Boot** documentation
that has a list of those common properties,
and you can drill down and get more details on those given properties.

But don't let those 1000 plus properties overwhelm you or scare you.
The properties are roughly grouped into the following categories.
So, you have the categories of **Core**, **Web**, **Security**, **Data**,
**Actuator**, **Integration**, **DevTools**, and **Testing**.
So that's kind of the logical grouping
of those different properties that are out there.
And what we'll do here is we'll actually review
some properties just so you can see how they're used,
become accustomed to them and so on.

```properties
# Log levels severity mapping
logging.level.org.springframework=DEBUG
logging.level.org.hibernate=TRACE
logging.level.com.luv2code=INFO

#Log file name
logging.file.name=my-crazy-stuff.log
logging.file.path=D:/JAVA_STUDY/Github/dev-spring-boot/01-spring-boot-overview/myApps/demo
```

Okay, so let's start with the core properties.
So again, in this `application.properties` file,
we can set up the logging levels and set up different severity mappings
for those given packages or for those given projects.
So for example, if you'd like to set up the logging level
for the `org.springframework`, set it to `DEBUG`.
You could set `org.hibernate` to `TRACE` levels,
and you can also set `com.luv2code` to `INFO` level.
So, basically you're setting these logging levels
based on the actual package name, and this applies to all subpackages
for those given projects or package names.

And then as far as the different logging levels that are out there, 
you can set `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, and `OFF`.
You could also have the data sent to a given specific log file as far as output,
so instead of the regular console, you can give the file of `my-crazy-stuff.log`
and all the information will be stored in that given log file.
Now if you'd like to get more details on **Spring Boot** logging, 
just access the link [here](https://www.luv2code.com/spring-boot-logging) on the page,
it'll give you the official **Spring Boot** documentation 
for actually performing all the different logging configurations and so on.

```properties
# HTTP server port
server.port=7070

# Context path of the application
server.servlet.context-path=/my-silly-app

# Default HTTP session time out
server.servlet.session.timeout=15m
```

Next, we can take a look at some of the web properties.
So again, in this `application.properties` file,
we can actually set up the actual port that the server will listen to on.
So by default, the port is `8080`, but you may want to change it
to listen on a different port here, so I'll say `server.port=7070`.
We can also change the context path of the application,
so the actual default is just a forward slash, `/`,
but here we'll give an actual context path of `/my-silly-app`, 
so then that means that when you actually access your application in the web browser, 
we give `localhost:7070`, that's the port, slash, the contact path, `/my-silly-app`,
and then slash whatever endpoints you're going to access,
like our fortune or our workout or whatever.
We can also set another property here, like the `default HTTP session timeout`,
I set that to `15m`, that's basically 15 minutes, so `m` is short for minutes.

```properties
# Endpoints to include by name or wildcard
management.endpoints.web.exposure.include=*

# Endpoints to exclude by name or wildcard
management.endpoints.web.exposure.exclude=beans,mapping

# Base path for actuator endpoints
management.endpoints.web.base-path=/actuator
```

Now, let's take a look at some of the properties we can set for **Actuator**.
You've seen some of these already, right?
So you can set up the endpoints to include,
so we can give the endpoints by name or by wildcards, I'll have `include=*`.
We can also give endpoints to exclude by the name or wildcards.
So here I'll say `.exclude=beans,mapping`.
We can also set up the base path for the actuator endpoints.
So the actual default is `/actuator`, so when we access it via our web,
we say `localhost:`, whatever our port name is, `7070/actuator/health`.
And you can easily change that value to anything you'd like 
for your actual **Actuator** endpoints.

```properties
# Default user name
spring.security.user.name=admin

# Password for default user
spring.security.user.password=topsecret
```

And then you can set up some **Security** properties here.
So, we saw earlier how we could secure the rest endpoints 
for the **Spring Boot Actuator** making use of the default _username_.
So here we can actually provide a different default username.
We say `user.name=admin`, and we can also set up a default password,
so instead of using that generated password by **Spring Boot**,
we can give the `user.password=topsecret`.
And remember, earlier I also mentioned that you can easily customize this
to make use of our normal **Spring Security** work, our **Spring Security** configuration
where you can basically hook in databases, roles, encrypted passwords, and so on,
just using the same information that we did earlier in the course
for setting up custom **Spring Security**.

```properties
# JDBC URL of the database
spring.datasource.url=jdbc:/mysql://localhost:3306/ecommerce

# Login username of the database
spring.datasource.username=scott

# Login password of the database
spring.datasource.password=tiger
```

And also Spring Boot has support for some data properties here.
So in your `application.properties` file,
we can go ahead and set up, like, the JDBC URL.
So this `datasource.url=jdbc:mysql`, localhost ecommerce.
We can set up the username of scott and the password of tiger.
And then we'll actually get into more of this later
when we move into **Spring Boot** for data access,
**Spring Boot** data, and all that good stuff.

```properties
# JDBC URL of the database
spring.datasource.url=jdbc:/mysql://localhost:3306/ecommerce

# Login username of the database
spring.datasource.username=scott

# Login password of the database
spring.datasource.password=tiger
```

So don't worry, I got you covered.
And again, just as a reminder,
a list of the **Common Spring Boot** properties is available 
here at the [link](https://www.luv2code.com/spring-boot-props).
Alright, so now with our development process:

1. What we're going to do here is we'll actually modify **Spring Boot**,
we'll configure the server port to listen to a different port,
2. and then we'll also configure the application context path.

Now let's go ahead and swing back into our IDE. 
And what we'll do is we'll make some configuration changes here for our given application.
Step-1: Configure the server port.
And what we'll do is we'll start with step one of configuring the server port.

```properties
#
# Change Spring Boot embedded server port
#
server.port=6060
```

So we'll set this property here `server.port=6060`.
The default port is `8080`, but we'll actually override it here and set the value to `6060`.
I'll save this file here and note here it'll automatically reload,
because we're using the spring boot DevTools, 
and we'll kind of scan over here and investigate the console logs.

````html
2024-05-20T19:37:50.920+03:00  INFO 432 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : No active profile set, falling back to 1 default profile: "default"
2024-05-20T19:37:50.955+03:00  INFO 432 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-20T19:37:50.955+03:00  INFO 432 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-20T19:37:51.805+03:00  INFO 432 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 6060 (http)
2024-05-20T19:37:51.814+03:00  INFO 432 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-20T19:37:51.814+03:00  INFO 432 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-20T19:37:51.846+03:00  INFO 432 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-20T19:37:51.848+03:00  INFO 432 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 891 ms
2024-05-20T19:37:52.349+03:00  WARN 432 --- [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: 2558bb2e-47e4-433d-95c9-6f057447c9d7

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2024-05-20T19:37:52.425+03:00  INFO 432 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path '/actuator'
2024-05-20T19:37:52.435+03:00  INFO 432 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@323d606e, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@3126e411, org.springframework.security.web.context.SecurityContextHolderFilter@18d91a28, org.springframework.security.web.header.HeaderWriterFilter@35504b6c, org.springframework.web.filter.CorsFilter@584a5031, org.springframework.security.web.csrf.CsrfFilter@6f7cec28, org.springframework.security.web.authentication.logout.LogoutFilter@729d9a7b, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@23ad7098, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@65f76128, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@4e3ed08a, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@4bc90a22, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@28caa894, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@5cf13d1c, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@17d9e733, org.springframework.security.web.access.ExceptionTranslationFilter@116445e2, org.springframework.security.web.access.intercept.AuthorizationFilter@5e5f531]
2024-05-20T19:37:52.463+03:00  INFO 432 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-20T19:37:52.496+03:00  INFO 432 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 6060 (http) with context path ''
2024-05-20T19:37:52.503+03:00  INFO 432 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 1.838 seconds (process running for 2.145)
````

It says **Tomcat** service started on port `6060`.
And that's all based on that configuration 
that we just set here in the application properties file.

Now let's go ahead and verify this.
Let's swing over to our browser.
And if we simply left it at `8080` and tried to access this page, 
you should get a 404 or an error.
The site can't be reached
because no one's listening on `8080` at the moment, they're only listening on `6060`.
So we need to update this URL to use the correct port.
So now I give `localhost:6060`:

![image61](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image61.png?raw=true)

Things work out just fine because we're using the
correct port number and that's based on the configurations from our `application.properties` file.
And so with Step 2: Configure the application context path.
So this is a **Spring Boot** configuration property here,
server dot server context path equals /mycoolapp.

```properties
# Use wildcard "*" to expose all endpoints
# Can also expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

# Exclude individual endpoints with a comma-delimited list
# management.endpoints.web.exposure.exclude=health,info

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0

#
# Define my crazy properties
#

coach.name=Mickey Mouse
team.name=The Mouse Club

#
# Change Spring Boot embedded server port
#
server.port=6060

#
# Set the context path of the application
#
# All requests should be prefixed with /mycoolapp
#
server.servlet.context-path=/mycoolapp
```

So that's the actual name of the application 
or the name of the context path that we'll use for this given app. 
So this basically means here that all requests 
for this application should be prefixed with `/mycoolapp`.
Alright, so this looks pretty good.
Let's go ahead and save this file,
and we'll actually see the **Spring Boot** server.
It's restarted in the background so just digging into the logs a bit more.

```html
This generated password is for development use only. Your security configuration must be updated before running your application in production.

2024-05-20T19:51:45.223+03:00  INFO 432 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path '/actuator'
2024-05-20T19:51:45.226+03:00  INFO 432 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@77d38f61, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@7ad7a5e6, org.springframework.security.web.context.SecurityContextHolderFilter@6bff03d6, org.springframework.security.web.header.HeaderWriterFilter@5e83c259, org.springframework.web.filter.CorsFilter@4eab1252, org.springframework.security.web.csrf.CsrfFilter@6a0b1aea, org.springframework.security.web.authentication.logout.LogoutFilter@453636cb, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@544c108e, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@340f719, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@88eae40, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@7817de91, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@59919809, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@43916438, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@63cd90b7, org.springframework.security.web.access.ExceptionTranslationFilter@16f18d7e, org.springframework.security.web.access.intercept.AuthorizationFilter@34e72dd0]
2024-05-20T19:51:45.231+03:00  INFO 432 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-20T19:51:45.240+03:00  INFO 432 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 6060 (http) with context path '/mycoolapp'
2024-05-20T19:51:45.245+03:00  INFO 432 --- [  restartedMain] c.l.s.d.mycoolapp.MycoolappApplication   : Started MycoolappApplication in 0.315 seconds (process running for 834.886)
2024-05-20T19:51:45.247+03:00  INFO 432 --- [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

Okay, so it's restarted, it's on port 6060, 
that's fine we know about that already.
And now, ooh, the new thing here with context path of `/mycoolapp`.
And that's all based on the configuration
that we just set up for this given **Spring Boot** application.
Okay, so let's go ahead and test this out.
Let's go ahead and swing over to our browser 
and see how this works out for us.

![image62](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image62.png?raw=true)

So now if I simply use the existing `6060/teamInfo`
that I'd URL does not work anymore, so we should get a 404.
Yeah, because we've now changed the actual context path here for a given application.
So we need to prefix that with `/mycoolapp/teamInfo`.
And again, this is all based on the configuration
that we have in our `application.properties` file.

![image63](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image63.png?raw=true)

So that works.
And again, we're just kind of connecting the dots here.
Team info properties file.
So now we could also do a similar thing here for workout
get the actual workout information, and also we can get our fortune endpoint.
And that's all using that mycoolapp prefix 
that we've set up for a given context path.
So this is great.
We are able to go through and configure **Spring Boot** using some of its properties.
</div>

# [Spring Core]()
## [What is Inversion of Control (IoC)?]()
<div style="text-align:justify">

In this section, we'll cover **inversion of control**.
What exactly is inversion of control?
Well, here's the textbook definition.
It's the approach of outsourcing the construction and management of objects.
So instead of us manually creating the objects ourselves,
we'll outsource this to another entity.

![image64](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image64.png?raw=true)

Now let's look at a coding scenario.
So here's our application, and we have a **CricketCoach** 
we'd like to call a method on the **CricketCoach** and says, 
hey, give me a _dailyWorkout_, and then it'll give us that response.
Now, this application should be configurable
such that we could easily change the coach for another sport, 
such as baseball, hockey, tennis, gymnastics, etc.
We'd like for this app to be configurable.
We can easily ask a coach for a workout, and they'll give us a given response.

![image65](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image65.png?raw=true)

Now, let's look at the ideal solution.
So we have our application.
We can talk to an object factory and say, 
_hey, give me a **coach** object_.
This object factory, based on a configuration,
it'll create a coach for us and give us a reference to it.
So it could create a **CricketCoach**, a **HockeyCoach** or a **BaseballCoach**.
This is all based on a configuration.

And so this is where the **Spring** container comes into place.
So the **Spring** container basically works as an object factory.
So we'll tell **Spring**, 
_hey, give me a given coach object in the background_.
**Spring** will determine which coach object that you need based on a configuration, 
and then give you a reference to that given coach object.

Now, a bit more here as far as the **Spring** container kind of working as an object factory.
So this **Spring** container has two primary functions:

1. Create and manage the objects (Inversion of Control) 
2. Injecting the object dependencies (Dependency Injection).

Now, how can we configure the **Spring** container?
Well, three different approaches here:

1. We can make use of XML configuration file (legacy)
2. Java annotations (modern) 
3. By configuring using Java source code (modern)

Now, XML configuration is considered legacy,
so we won't cover it in this course.
However, we will focus on the modern solutions here,
such as Java annotations and Java source code.
We have plenty of examples in this section 
as far as making this type of configuration set up.
</div>

## [Defining Spring Dependency Injection]()
<div style="text-align:justify">

In this section, we'll cover **spring dependency injection**.
Dependency injection makes use of the dependency inversion principle.
That's where the client delegates to another object
the responsibility of providing its dependencies.

Now, let's consider an example of using a car factory.
So I want to purchase a car.
So I communicate with the car factory, 
"_Hey give me a car object._"
And in the background they'll actually go off
and grab the given car and make it available to me.
Now, since it's a factory,
they may have to actually assemble the car from various parts 
like the door, the engine, the windshield, all the different components there.
And then once it's all put together, they'll give me the actual car,
the whole idea of dependency injection is saying,
"_Hey, give me a given object.
If it has any components or helper components,
then assemble all that for me ahead of time
and simply give it to me, so I can use it out of the box._"
So that's the idea of dependency injection.
Injecting the given dependencies or helper components for a given object.

Now remember here with the **Spring** container,
the **Spring** container kind of works as the object factory.
So my application can talk to the spring container.
"_Hey, give me a coach object._"
This coach object may have additional dependencies or additional helpers.
So imagine you have a head coach.
The head coach may have a staff of assistant coaches, 
physical trainers, a medical staff, and so on.
So I can say, "_Hey, give me everything that I need to make use of this given coach_", 
and then they'll give it to me, all put together, ready to go out of the box.
So that's dependency injection here using the **Spring** container.

Now, just as a refresher here, 
remember with the **Spring** container here, it has primary functions.
So the one function is creating and managing the objects.
That's the inversion of control.
And then also injecting the object's dependencies using dependency injection.
Those are the two key functions there.

Now let's look at a demo example here.
So I have a **Coach** that provides daily workouts,
and then we have the **DemoController** that wants to use a **Coach**.
So in this case, the **Coach** is the helper.
So this is known as a dependency.
And what we'd like to do is inject this dependency into our controller 
or inject the coach into the given controller.

Now, as far as injection types,
there are multiple injection types available with **Spring**.
We'll cover the two recommended types of injection:

1. Constructor Injection
2. Setter Injection

Now you may wonder, well, which type of injection should I use?
Well, use a **constructor injection** when you have required dependencies, 
and it's generally recommended by the `spring.io` development team as the first choice.
There's also setter injection,
and you can use this when you have optional dependencies.
So in this case, if the dependency is not provided,
your app can provide reasonable default logic.
And I'll show you coding examples of constructor injection
and also setter injection in some of the upcoming sections.

Now, what is **Spring AutoWiring**?
Well, for dependency injection, **Spring** can make use of auto wiring.
So **Spring** will look for a class that matches.
So it can match by type, either a class or interface
and then **Spring** will inject it automatically.
Hence, that given dependency is auto wired.

Now with an auto wiring example, injecting a coach implementation, 
**Spring** will scan for a `@Components` 
or any class annotated with the component annotation, and it'll say, 
"_Hey does anyone implement the coach interface? 
If so, then let's inject them._"
So in our example, it could say, 
_Hey there's a cricket coach.
Let's go ahead, inject this **CricketCoach** as a dependency_ 
for this given example.
And so that's an example here of auto wiring.

![image66](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image66.png?raw=true)

Now, let's look at another example application here.
So we have our **Web Browser**, we have this **DemoController**, 
and then we have a **Coach**.
So in our **Web Browser**, we'll go to this endpoint `/dailyworkout`.
Our **DemoController** will communicate with the **Coach** that says, 
"_hey, getDailyWorkout_" by calling that method.
That method's going to return a string,
"_Practice fast bowling for 15 minutes_",
and then we'll simply return that to the browser.
So that's kind of the big picture here.
This example that we want to put together.

Now here's the development process using **Constructor Injection**:

1. So the first thing that we'll do is we'll define the dependency interface and class.
2. Then we'll create our **Demo REST Controller** 
3. And then we'll create a constructor in our class for injections.
4. And then finally, we'll add a `@GetMapping` for the `/dailyworkout` endpoint.
   
Alright, starting with step 1: defining the dependency interface and class.

```java
package com.luv2code.springcoredemo;

public interface Coach {
    
    String getDailyWorkout();
}
```

So we'll have this interface called **Coach**.
It has a method _getDailyWorkout()_.

```java
package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```

Then we'll have a **CricketCoach** that implements **Coach**.
And it has this method, _getDailyWorkout()_,
"`Practice fast bowling for 15 minutes`".
Now notice here that this class has the `@Component` annotation.
So this marks the class as a **Spring Bean**
and makes it a candidate for dependency injection.

Now a bit more here on the `@Component` annotation.
It marks the class as a **Spring Bean**.
So a **Spring Bean** is just a regular Java class managed by **Spring**.
And the `@Component` annotation also makes the bean available for dependency injection.

```java
package com.luv2code.springcoredemo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
}
```

Now step 2: Creating a Demo REST Controller.
We create this **DemoController**,
we give it the `@RestController` annotation, very basic **REST** example here.

```java
package com.luv2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private Coach myCoach;
    
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }
}
```

And then in step 3: we create the constructor in our class for injections.
So we define a private field here, _myCoach_,
and then we create this constructor, **DemoController**.
We pass in, **Coach** _theCoach_, and then we make use of the `@Autowired` annotation.
And so remember the **Spring Object Factory** will handle injecting this dependency 
based on the configuration.
And notice the `@Autowired` annotation tells **Spring** to inject the dependency.
And if you only have one constructor, 
then the `@Autowired` annotation on the constructor is optional.
But I'll keep it here, just for academic purposes.
Just because we're in the early stages of learning this technology.
But this annotation here is optional in this specific case of only one constructor.
And then at the moment, we only have one **Coach** implementation, **CricketCoach**.
So **Spring** can figure out which one it needs.
Later in the course, we'll cover the case of multiple **Coach** implementations, 
and I'll show you how to configure your application accordingly.
We will cover that in some of the later sections.

Alright, we're making good progress here.
So in step 4: Adding the `@GetMapping` for `/dailyworkout`.

```java
package com.luv2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private Coach myCoach;
    
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

So in our coding here at the bottom notice we have this `@GetMapping("/dailyworkout")`.
So that's the **REST** endpoint they'll use.
We'll basically say `return myCoach.getDailyWorkout()`
and I'll return that as a string.
And so remember you're kinda pulling this all together with the **Web Browser**, 
they go to `/dailyworkout`.
We talk to this **DemoController** which in turn talks to the **Coach**, 
gets the _DailyWorkout_,
then returns that value accordingly to the application.
Alright, so this all looks pretty good. 
I want to go ahead and move inside the IDE and start writing this code
and start writing our first **Spring** project.

So let's go ahead and get started.
So go ahead and open up a web browser, 
and we're going to go to **Springs Initializer** website at `start.spring.io`.
So once we're here at the website,
let's go ahead and set up some of our project settings here.

![image67]()

In the project section, be sure to choose **Maven** for the project type.
For language, choose **Java**.
For the **Spring boot version** choose the latest 3.2.5 version.
Again, remember, avoid the snapshot versions.
For the project metadata for the group,
I'll call it com.luv2code
And then for the artifact id, I'll call it springcoredemo.
And for the packaging, make sure that Jar is selected.
And now we'll go through and add some of our dependencies.
The first dependency that we'll add is
Spring Boot Devtools.
So you can simply type in Dev
and then we'll also add in spring web support.
You can simply type in web and select that one
and just make sure your dependencies match what I have here
on the screen.
Once everything looks good, go ahead
and hit the generate button
and it'll download a zip file to your computer.
I'll go ahead and swing over to my file system here
and I'll open up two windows so I can make some updates.
And I'll move into my Dev Spring Boot folder
that we've been using already in the course.
And I'll create a new folder here just to kind
of structure all the code that we're creating.
And I'll call this 02-spring-boot-spring-core.
In the other window I'll move over
to the downloads folder that I have.
This is where my web browser downloads the files.
I'll simply unzip this file here, springcoredemo
and then I'll simply move this file
into the folder 02-spring-boot-spring-core.
And now what I'll do is I'll go through
and rename this folder
and I'll call it 01-constructor-injection.
And I'll simply just move in here and expand this.
So this is just a normal Maven project.
I can open this project
by simply dragging and dropping it into IntelliJ.
Give it some time to download and sync all the assets.
But shortly, you should be okay.
</div>



<div style="text-align:justify">


</div>