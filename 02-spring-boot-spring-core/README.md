# [Spring Core]()

## [What is Inversion of Control (IoC)?]()
<div style="text-align:justify">

In this section, we'll cover **inversion of control**.
What exactly is inversion of control?
Well, here's the textbook definition.
It's the approach of outsourcing the construction and management of objects.
So instead of us manually creating the objects ourselves,
we'll outsource this to another entity.

![image01](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image01.png?raw=true)

Now let's look at a coding scenario.
So here's our application, and we have a **CricketCoach** 
we'd like to call a method on the **CricketCoach** and says, 
hey, give me a _dailyWorkout_, and then it'll give us that response.
Now, this application should be configurable
such that we could easily change the coach for another sport, 
such as baseball, hockey, tennis, gymnastics, etc.
We'd like for this app to be configurable.
We can easily ask a coach for a workout, and they'll give us a given response.

![image02](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image02.png?raw=true)

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

![image03](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image03.png?raw=true)

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

![image04](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image04.png?raw=true)

In the project section, be sure to choose **Maven** for the project type.
For language, choose **Java**.
For the **Spring boot version** choose the latest 3.2.5 version.
Again, remember, avoid the snapshot versions.
For the project metadata for the group, I'll call it `com.luv2code`
And then for the artifactId, I'll call it `springcoredemo`.
And for the packaging, make sure that **Jar** is selected.
And now we'll go through and add some of our dependencies.
The first dependency that we'll add is **Spring Boot Devtools**.
So you can simply type in `Dev` and then we'll also add in spring web support.
You can simply type in `web` and select that one
and just make sure your dependencies match what I have here on the screen.
Once everything looks good, go ahead and hit the `generate` button,
and it'll download a zip file to your computer.
I'll go ahead and swing over to my file system here.
And I'll move into my `dev-spring-boot` folder
that we've been using already in the course.
And I'll create a new folder here 
just to kind of structure all the code that we're creating.
And I'll call this `02-spring-boot-spring-core`.
In the other window, I'll move over to the downloads folder that I have.
This is where my web browser downloads the files.
I'll simply unzip this file here, `springcoredemo`
and then I'll simply move this file into the folder `02-spring-boot-spring-core`.
And now what I'll do is I'll go through and rename this folder,
and I'll call it `01-constructor-injection`.
And I'll simply just move in here and expand this.
So this is just a normal **Maven** project.
I can open this project by simply dragging and dropping it into IntelliJ.
Give it some time to download and sync all the assets.
But shortly, you should be okay.

```java
package com.luv2code.springcoredemo;

public interface Coach {

    String getDailyWorkout();
}
```

In step 1: we're going to define the dependency and interface class.
I'll move over here to `com.luv2code.springcoredemo` package, 
and I'll create a new interface.
The name of this interface, we'll call it **Coach**.
And for this **coach** interface, 
we'll simply have one method called _getDailyWorkout_ that returns a string.
And that's it, basic and very straightforward.

```java
package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        //return null;
        return "Practice fast bowling for 15 minutes.";
    }
}
```

And now let's create a class that implements this interface.
We'll go ahead and create a new class.
And for this class, we'll call it **CricketCoach**.
So this **CricketCoach** will implement the **Coach** interface.
And one thing we need to do here is annotate this class with the `@Component` annotation.
And so remember, the `@Component` annotation marks the class
as a spring bean makes it available for dependency injection.
Now let's go ahead,
and implement the code here for _getDailyWorkout_.
So we'll say, hey, "`Practice fast bowling for 15 minutes.`"
Okay, so that looks pretty good.

```java
package com.luv2code.springcoredemo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

}
```

Now, step 2: Creating our **Demo REST Controller**.
I'll create a new class here.
And the name of this class is **DemoController**.
And since I'm creating a **RestController**, I give the annotation `@RestController`.

```java
package com.luv2code.springcoredemo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    // define a private field for the dependency
    private Coach myCoach;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }
}
```

Now at step 3: we want to create a constructor in our class for injections.
So the first thing I'll do is define a private field for the dependency.
So private **Coach**, _myCoach_,
and then I'll define a constructor for dependency injection.
I'll set up `@Autowired`, create the constructor here,
pass and **Coach** _theCoach_, and I make the assignment accordingly.
The `@Autowired` annotation tells **Spring** to inject a dependency.
And also remember, if you only have one constructor,
then `@Autowired` on the constructor is optional.
However, I will keep it here for academic purposes
because we're just learning this technology,
and this helps pull everything together for you.
But just note, in this case, it's optional when you only have one constructor.
Alright, so that looks pretty good.

```java
package com.luv2code.springcoredemo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    // define a private field for the dependency
    private Coach myCoach;

    // define a constructor for dependency injection
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

Now in step 4: we're going to add a `@GetMapping` for the `/dailyworkout` endpoint.
So again, remember the old big picture here we have the **web browser;**
they're going to call `/dailyworkout`, talks to our **DemoController**.
Our **DemoController** will make a call on the **Coach**,
saying, _"hey, get dailyworkout"_, it'll return the string,
and we'll simply return that to the browser.
So I'll set up this `@GetMapping("/dailyworkout")`
and I'll create this method here,
_getDailyWorkout_ that returns a string,
and then I simply do a return on `myCoach.getDailyWorkout`.
So that kind of pulls it all together with the little architecture 
that we have set up top.
Alright, so this looks pretty good.
Let's go ahead and test this out.
And let's run our spring boot application.
Okay, so we have our Spring application.
It's up and running.

![image05](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image05.png?raw=true)

Let's go ahead and swing over to a web browser.
And let's go ahead and visit this URL here,
`localhost:8080/dailyworkout` and success.
So we get the message here,
"`Practice fast bowling for 15 minutes.`"
And so we know that all the different components
in the background are kind of working out as desired.

So in our **DemoController**, forget _dailyworkout;_
we delegate the call to _myCoach_.
We know that we only have one **Coach** in this example, right?
So that's the **CricketCoach**.
And that says practice fast bowling.
Okay, and I want to play around with the code for a bit,
just kind show you some little troubleshooting tips here.

```java
package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        //return null;
        return "Practice fast bowling for 15 minutes!!!!!!";
    }
}
```

I want to change the message here,
giving the exclamation mark a couple of times,
and then save, and then notice here that
**Spring Boot DevTools** is not automatically reloading.
So one way to resolve this is in your IntelliJ settings.
So a couple of things I want to check here.
So under `Advanced Settings`, make sure that allow automaker is checked.
And then also under `build execution deployment`.
And then under `compiler`, then make sure that `build project automatically` is checked.
In this case, it's not checked.
So let's go ahead and check it now and then hit okay.
And now if I move back to my application,
and, the automatic reload is working.
And then I can go back to the browser,
do a reload on this page, and I'll see that new message.

![image06](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image06.png?raw=true)

Okay, good.

```java
package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        //return null;
        return "Practice fast bowling for 15 minutes. NOW!!!!!";
    }
}
```

Just test it one more time just for sanity's sake.

![image07](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image07.png?raw=true)

Okay, excellent.
And I'll just go ahead and put it back to the original text that I had,
getting rid of all the exclamation points, and now and so forth.
So we kind of went through a process of making use of inversion of control,
and also dependency injection.
And also we kind of got some hands on to actually see this all come together.

One thing that you may have noticed, 
your IDE may have given you a warning notice about `no usages`.
For example, on the class **CricketCoach** it's listed as `no usages`.
However, we know that we're using **CricketCoach** in our **Spring Boot** project, right?
We just ran our application and tested it.
We received the daily workout from the **CricketCoach**, practice fast bowling.
So you may wonder why the IDE says `no usages`?
Well, due to the dynamic nature of **Spring**,
sometimes the IDE is not able to figure out how beans are injected.
And also from most times we're coding to the interface,
we may not explicitly reference the implementation class in our **Spring** application.
We simply let **Spring** do its work behind the scenes,
of injecting the appropriate implementation as needed.
So there are a lot of things that happen behind the scenes at runtime, 
and your IDE may not be able to determine
if a given class, or method is used, at runtime.
In these cases, you can kinda disregard the warning.
But anyway, that's it.
I just want to mention that in case you were wondering about it.

In this section, we're going to learn about constructor injection
and how it works behind the scenes.
Now, how does **Spring** process your application?
We have our interface for coach,
we have our cricket coach implementation,
and then we have our demo controller.
And so the **Spring framework** will perform operations for you
behind the scenes for processing these items.

```java
Coach theCoach = new CricketCoach();
DemoController demoController = new DemoController(theCoach);
```

Behind the scenes, **Spring** will create a new instance of your coach, new cricket coach.
It'll also perform constructor injection with your demo controller.
It'll actually inject the coach into the demo controller.
And remember, the whole idea of injection is that there's a dependency or a helper.
So in this case, the coach is a dependency or a helper for the actual controller.
The **new** keyword, is that it?
So you're probably wondering: 

- _is it just the **new** keyword?_
- _I really don't need **Spring** for this. 
I could do this myself._

Okay, and you're saying, what's the point of **Spring** if it's just the **new** keyword?
It has to be more to it than that.
Well, it actually is.
So **Spring** is more than just **Inversion of Control**,
and it's more than just **Dependency Injection**.
For small basic applications like we're building here,
it may be hard to see the real benefits of **Spring**.

**Spring** is designed for enterprise applications.
So it's targeted for enterprise, real-time, real-world applications.
And **Spring** provides features such as: 

* Database access and transactions 
* REST APIs and Web MVC 
* Security
* etc...

Later in the course, we'll build a real **CRUD REST API** with database access.
You'll see these **Spring** features in action.
So a lot of good things are coming.
So again, **Spring** is more than 
just **IoC**(Inversion of Control) and **DI**(Dependency Injection).
There's a lot of other perfect features that you can make use of with **Spring**.
</div>

## [Component Scanning]()
<div style="text-align:justify">

This section will cover **Component Scanning** with **Spring**. 
**Spring** will scan your Java classes or special annotations such as `@Component`,
and it'll automatically register the beans in the **Spring** container.

Let's look at some Java source code here.
So in our project, we currently have this `SpringcoredemoApplication.java`.
That's our main **Spring Boot** application class created by the **Spring Initializer**.
We also have our **RestController** that we created
in an earlier section, called `DemoController.java`.

```java
package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
```

Now, in that `SpringcoredemoApplication`,
the one that was created by the **Spring Initializer**
note here that it does an import on Spring Boot Application.
So this enables Auto-configuration, Component scanning
and Additional configuration with **Spring Boot**.
And really behind the scenes, 
this annotation is composed of the following annotations: 

* `@EnableAutoConfiguration`
* `@ComponentScan` 
* `@Configuration`

So, a bit more here in these annotations.
The `@SpringBootApplication` is composed of the following annotations:

| Annotation                 | Description                                                                            |
|----------------------------|----------------------------------------------------------------------------------------|
| `@EnableAutoConfiguration` | Enables Spring Boot's auto-configuration support                                       |
| `@ComponentScan`           | Enables component scanning of current package<br/> Also recursively scans sub-packages |
| `@Configuration`           | Able to register extra beans with `@Bean` <br/> or import other configuration classes  |

`@EnableAutoConfiguration` enables **Spring Boot**'s autoconfiguration support,
`@ComponentScan` enables component scanning of the current package 
and also recursively scans the sub-packages,
and the `@Configuration` annotation is able to register extra beans with the `@Bean` annotation
or import other configuration classes.
I'll show you some examples of the configuration annotation a bit later in the course.

```java
package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
```

And now back to our Java source code.
So we know the information here about the **Spring Boot** application.
Next, we have `import org.springframework.boot.SpringApplication;` 
**Spring** application.
And so this allows us to bootstrap our **Spring Boot** application, 
and then we give a reference here to the actual name of our class, 
in this case, `SpringcoredemoApplication`.
So behind the scenes this will create the application context, 
register all the beans, and also start the embedded server such as **Tomcat**, etc., by default.

Now, a bit more here on component scanning.
By default, **Spring Boot** starts component scanning
from the same package as your main **Spring Boot** application.
And also it scans the sub-packages recursively.
This implicitly defines a base search package that you can make use of.
So it allows you to leverage default component scanning
without having to explicitly referencing the base package name.

![image08]()

So here's a diagram to kind of pull this together.
We have our Main Spring Boot application class.
It automatically component scans
the package and sub-packages.
We can create any other sub-packages that we want.
We can give these sub-packages any name,
and then it scans everything in core luv2code.springcoredemo
package, and any sub-packages.
So basically it starts scanning
at the Main Spring Boot application class level
and then all sub-packages underneath that.
Now, a common pitfall when you're making use
of Spring Boot, you may say,
Hey, I'm going to use different packages
and move things around and change things up or whatever.
Here's an example.
So we have our Spring Core Demo, that's the package
of our Main Spring Boot application class.
Then, you may create other packages outside of that.
So using this example here of demo utils,
notice here that it's outside of our Spring Core Demo.
And so by default,
Spring Boot will not component scan these packages.
It will only scan the package
of the Main Spring Boot application class and sub-packages.
So this is very important.
So, the default scanning works fine
if everything is under com.luv2code.springcoredemo.
But what about my other packages?
Like I want to use some different names or whatever
like com.luv2code.util, or org.acme.cart,
or edu.cmu.spirit racing systems.
How will this kind of work out,
or how can I configure this accordingly?
Well, what you can do is,
in your Spring Boot application annotation
then you can tell it to scan base packages.
So, here I'm going to explicitly list
the base packages to scan.
And you simply give a comma'd limited list
of those packages that you want Spring Boot to scan.
So, give our com.luv2code.sprincoredemo,
and then luv2code.util, org.acme.cart, edu.cmu.srs.
All right, so this is all really good stuff.
We're going to move into the next video,
we're going to write the code.
I'll show you how to make use
of default scanning, and also I'll show you how
to manually list the actual package scanning.
I'll see ya in the next video.
</div>

## [Setter-Field Injections]()
<div style="text-align:justify">


</div>

## [Qualifiers]()
<div style="text-align:justify">


</div>

## [Primary]()
<div style="text-align:justify">


</div>

## [Lazy Initialization]()
<div style="text-align:justify">


</div>

## [Bean Scopes]()
<div style="text-align:justify">


</div>

## [Bean Lifecycle Methods]()
<div style="text-align:justify">


</div>

## [Java Config Bean]()
<div style="text-align:justify">


</div>