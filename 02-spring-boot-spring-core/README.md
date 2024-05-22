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

![image08](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image08.png?raw=true)

So here's a diagram to kind of pull this together.
We have our **Main Spring Boot** application class.
It automatically component scans the package and sub-packages.
We can create any other sub-packages that we want.
We can give these sub-packages any name,
and then it scans everything in core `com.luv2code.springcoredemo` package, and any sub-packages.
So basically it starts scanning at the **Main Spring Boot application** class level
and then all sub-packages underneath that.

![image09](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image09.png?raw=true)

Now, a common pitfall when you're making use of Spring Boot, 
you may say, _Hey, I'm going to use different packages
and move things around and change things up or whatever_.
Here's an example.
So we have our **Spring Core Demo**, that's the package of our **Main Spring Boot** application class.
Then, you may create other packages outside of that.
So using this example here of **demo** utils,
notice here that it's outside our **Spring Core Demo**.
And so by default, **Spring Boot** will not component scan these packages.
It will only scan the package of the **Main Spring Boot** application class and sub-packages.
So this is crucial.

So, the default scanning works fine if everything is under `com.luv2code.springcoredemo`.
But what about my other packages?
Like I want to use some different names 
or whatever like `com.luv2code.util`, or `org.acme.cart`, or `edu.cmu.srs` racing systems.
How will this kind of work out,
or how can I configure this accordingly?

```java
package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.luv2code.springcoredemo",
                            "com.luv2code.util",
                            "org.acme.cart",
                            "edu.cmu.srs"})
public class SpringcoredemoApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
```

Well, what you can do is, in your **Spring Boot** application annotation
then you can tell it to scan base packages.
So, here I'm going to explicitly list the base packages to scan.
And you simply give a comma delimited list of those packages 
that you want **Spring Boot** to scan.
So, give our `com.luv2code.sprincoredemo`,
and then `com.luv2code.util`, `org.acme.cart`, `edu.cmu.srs`.

Now, I'll show you how to make use of default scanning, 
and also I'll show you how to manually list the actual package scanning.
I'll move into my finder window,
and I'll move into our `dev-spring-boot` directory
and our `02-spring-boot-spring-core`.
And for this constructor-injection,
I'll simply copy and paste this directory,
and then I'll rename it as `02-component-scanning`.
And now I'll simply open this directory here in IntelliJ.
So let me just move in here to my code, and I'm going to create a new package.
I'll give the package name of `rest`.
I'll also create another new package.
I'll call this `common`.
And then I'll move my **DemoController** into my package `rest`.
Alright, so that looks good.
And then I'll also move my **Coach** and **CricketCoach** into the `common` package.
And then note here, these are all sub packages of our main **springcoredemo** application.
So they'll be component scanned automatically for us
using the default component scanning of **Spring Boot**.
So I can rebuild on the project because I had some old compiled code from one of the previous projects.
Now let's go ahead and swing over to our web browser
and open up our endpoint `localhost:8080/dailyworkout`.

![image10](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image10.png?raw=true)

And everything loads up for us a-ok.
So our app works because we simply just moved those subpackages of our main **Spring Boot** application.
So this is pretty good.
We're making use of the default component scanning of **Spring Boot**.

Well, everything's working just fine, but let's go ahead and break it on purpose.
I want to see how **Spring** can handle things if I change up some packages 
or move things around a bit.
Let's go ahead and select the `java` folder in the list here,
and we're going to create a new package.
And the name of the new package,
I'll call it, `com.luv2code.util`.
And so, the important thing to notice is that it's not a subpackage of our `springcoredemo`.
It's outside that.
So, we may have some issues with some of our default component scanning.
And what I'll do here is I'll move into the `springcoredemo`
`common` package and I will move **Coach** and **CricketCoach**
to this new util package that I just created.
Okay, so that looks okay so far.
Now, notice our main Spring Boot application is under `springcoredemo`.
So, **Spring** will scan everything in this package and any subpackages, 
but by default **Spring** will not component scan this new package here that I created, `com.luv2code.util`.
So, let's go ahead and test this out and see what happens.

```html
Error starting ApplicationContext. To display the condition evaluation report, re-run your application with 'debug' enabled.
2024-05-21T17:30:46.933+03:00 ERROR 42148 --- [springcoredemo] [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.luv2code.springcoredemo.rest.DemoController required a bean of type 'com.luv2code.util.Coach' that could not be found.

Action:

Consider defining a bean of type 'com.luv2code.util.Coach' in your configuration.
```

The Application failed to start.
A parameter of constructor demo controller required of being 
of `com.luv2code.util.Coach`, but could not be found.
Because it, just it's not part of the default component scanning.
It's just that just won't work.
And so what's going on here?
Well now, we need to explicitly tell **Spring Boot**
how to find these other packages out there.
And so, we can accomplish that by editing our **springcoredemo** application
and updating the annotation **Spring Boot** application.
Inside here, I'll explicitly list the base packages to scan.

```java
package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.luv2code.springcoredemo",
                            "com.luv2code.util"})
public class SpringcoredemoApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
```

So, I give scan base packages,
and then I simply set up a comma-delimited list of the packages that I want it to scan.
So, I give `com.luv2code.springcoredemo`, and then, I also give `com.luv2code.util`.
So, remember, by default of the only component scan `springcoredemo`, 
but we have this `util`, so we have to list both of those here.
Let's go ahead and run this and test it out:

```html
2024-05-21T17:35:29.387+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-21T17:35:29.440+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-21T17:35:29.440+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-21T17:35:30.213+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-21T17:35:30.226+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-21T17:35:30.227+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-21T17:35:30.258+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-21T17:35:30.259+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 818 ms
2024-05-21T17:35:30.512+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-21T17:35:30.549+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-21T17:35:30.555+03:00  INFO 57496 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 1.495 seconds (process running for 1.811)
```

And the issue's resolved.
So, the application actually starts up successfully.
We don't have the problem that we had before.
And then, we can test this in our browser
by hitting this endpoint and just doing a reload on it,
and we get the data back as desired,
so it's able to find everything.
It's able to perform the injection,
and we're all set up because we were very explicit here by listing out those packages.

Alright, now I'm going to go ahead and move things back to their original packages, 
just so we can take advantage of the default component scanning with **Spring Boots**.
So, I'll just grab **Coach** and **CricketCoach**.
I'll move those back over to the `common` package.

```java
package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication(
        scanBasePackages = {"com.luv2code.springcoredemo",
                            "com.luv2code.util"})
*/
@SpringBootApplication
public class SpringcoredemoApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
```

And then, this code that I had here, I could delete it, or in this case I'll just comment it out,
kind of leave it around for a little bit in case you wanted to refer to it later.
And then, I'll just use a regular **Spring Boot** application
by itself and make use of the default configuration the default component scanning,
and I'll save all that stuff and test it and make sure it still works and great.
So, our app starts up just fine and swings over to our browser, 
do a reload here, and the import works out just fine.
So, we kind of put things back the way they were set up originally.
</div>

## [Setter-Field Injections]()
<div style="text-align:justify">

Now let's cover **setter injection**.
Earlier I mentioned the two recommended injection types:

* Constructor Injection
* Setter Injection

In this section, we'll focus on setter injection.
Setter injection is when we inject dependencies by calling setter methods on your class.
Now let's consider an autowiring example.
Here, we want to inject a **Coach** implementation, spring's going to scan for components,
and I'll basically say, "_Hey is there anyone that implements the **Coach** interface?_"
If so, let's inject them.
For example, the **CricketCoach**.

And here's our development process:

1. The first thing we'll do is we'll create the setter methods in our class for injections
2. And then we'll configure the dependency injection using the **@Autowired** annotation.

Alright, let's start with step 1: Creating the setter methods in our class for injections.
Here we have our **DemoController**, and then we'll have this new setter method here, _setCoach_.

```java
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

    @Autowired
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    }
}
```

And then in step 2: Configure the dependency injection with the `@Autowired` annotation.
So in our _setCoach_ method, we make use of the `@Autowired` annotation.

The **Spring Framework** will perform operations behind the scenes for you.
Now let's take a look at how **Spring** will process your application.
We have our **Coach** interface, our **CricketCoach** implementation, 
and our **DemoController**, and we want to inject the dependency into our **DemoController**.

```html
Coach theCoach = new CricketCoach();
DemoController demoController = new DemoController();
demoController.setCoach(theCoach);
```

Behind the scenes, **Spring** will create an instance of the **CricketCoach**.
It'll create an instance of the **DemoController**,
and then it'll say `demoController.setCoach`,
and it'll the pass in the **Coach** implementation.

Now, we could also inject our dependencies by calling any method on our class.
We can give it any method name, simply give the `@Autowired` annotation.
And here's a coding example of this.
We have our **DemoController**, and instead of a traditional setter method,
we could say, _doSomeStuff_.

```java
@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public void doSomeStuff(Coach theCoach) {
        myCoach = theCoach;
    }
    
    // ...
}
```

We annotate this method as `@Autowired`,
and we can simply give any method name here for this given method,
and **Spring** will handle the dependency injection for us.

Let's step back a bit.
You know, we've seen the different injection types,
constructor injection, setter injection, and you're probably wondering,
well, which injection type I should use?
`Constructor injection`, this is the one that you use when you have required dependencies.
It's generally recommended by the `spring.io` development team as the first choice.
And, you should use `setter injection` when you have optional dependencies.
So if a dependency is not provided, then your app can provide some reasonable default logic.
So that's the basic guidance that the **Spring** development team provides us for,
is which injection type to use.
Let's go ahead and move into our IDE, and let's write some code.


Let's take care of our normal housekeeping;
stopping all apps and closing all windows.
And I'll move in here into my file system into my `O2-spring-boot-core`.
And then I'll copy-paste our `O2-component-scanning` directory here, 
and I'll simply rename it `03-setter-injection`.
Now go ahead and open up this project in IntelliJ.
And what I'd like to do first is do a rebuild on the project.
This will help with auto reloading of spring boot dev tools.

And step 1: Create the setter method in our class for injections.
And step 2: Configure the dependency with the `@Autowired` annotation.
Let's go ahead and move into our **DemoController**.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;
    
    @Autowired
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And what I want to do is delete some of the previous code.
So where we had our constructor injection,
I want to delete this code because now we're going to make use of setter injection.
I'll set up Autowired annotation, and I'll create this setter method _setCoach_,
and then I make the appropriate assignments inside this method.
Okay, so we have the basic things in place here for setter injection for our **DemoController**.

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

And now let me swing over to my spring boot application.
I'm going to delete the previous code I had for all the scanned based packages.
Just kind of get rid of it for right now.
We already have backups of it in some of the other directories.
And great so that's all cleaned up now.
Now let's go ahead and run our application.
And our application is up and running.
Just swing over to our browser, and we go to our `localhost:8080/dailyworkout`.

![image11](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image11.png?raw=true)

And our app's going to work the same.
We're going to get the output here for this **CricketCoach** `practice fast bowling for 15 minutes`.
So we know that our application is actually performing the setter injection at this point.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        //return "Practice fast bowling for 15 minutes";
        return "Practice fast bowling for 15 minutes :-)";
    }
}
```

Now what I'd like to do is move in here and just change this method real quick
just to make the text a little bit different.
Just do a quick save on it should reload for us.
And then swinging back over into our browser reloading, we should see the new output.
So that's our recent update that we have here.

![image12](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image12.png?raw=true)

And now what I'd like to do in our **DemoController** is actually making a modification.
So instead of using a traditional setter method, I want to just give it any method name here.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;
    
    @Autowired
    public void doSomeStuff(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

So instead of _setCoach_, I'll give any method name I'll call it, I don't know, _doSomeStuff_.
You can give whatever name you'd like here.
The fact that this method is annotated with the `@Autowired` annotation
then **Spring** will use this for dependency injection.
Alright, so if we save that accordingly come back over here.
Reload, and our app works just fine.
So we're getting the message.
So we know that our app is working with injection, based on any method name that we provide.
And again, because of that `@Autowired` annotation.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
        //return "Practice fast bowling for 15 minutes :-)";
    }
}
```

Just do a quick little update on the method here, and okay, great.
So we're getting the latest and greatest here.
So this part is working out as desired.
Now what I'd like to do is kind of just swing back
and just put it back to the traditional set of methods
just to make it straightforward and easy to read and understandable.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;
    
    @Autowired
    //public void doSomeStuff(Coach theCoach) {
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

Instead of _doSomeStuff_, I'll call it _setCoach_ that's exactly what we're doing,
we're setting a coach.
So that's in place. 
We're all set up.
So that's a quick example there of using setter injection with **Spring**.

Let's cover field injection with annotations and autowiring.
Now, as I discussed earlier, there are different **Spring** injection types,
and so there are the types that are recommended by the `spring.io` development team.
That's constructor injection for required dependencies,
setter injection for optional dependencies.
Now, here's an injection type that's not recommended by the `spring.io` development team
and that's field injection.

And field injection is no longer cool.
So in the early days, field injection was very popular on **Spring** projects
but in recent years it has fallen out of favor.
And why is that?
Because in general, it makes the code harder to unit test.
Now, as a result, the `spring.io` team does not recommend field injection,
however, you'll still see it being used on legacy projects,
and also you'll see it being used
in a lot of old blog posts on the internet,
and even in previous versions of this course,
I actually used field injection, but now with modern times here,
removing the useless field injection,
and instead making use of construction or setter injection,
but I'll still show you a little quick example here of using field injection 
just in case you encounter it on some of your legacy projects.

Field injection is the idea of injecting in dependencies
by setting the values on your class directly, even on private fields,
and this is accomplished by using Java reflection.
So in step 1: Configure the dependency injection using the `@Autowired` annotation.

```java
@RestController
public class DemoController{

    @Autowired
    private Coach myCoach;

    // no need for constructors or setters

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And so here's a code example on our **DemoController**.
Notice here we have this field private **Coach**, _myCoach_,
and we give the `@Autowired` annotation, and behind the scenes,
**Spring** will inject a given **Coach** implementation,
and it'll do it behind the scenes even on a private field.
It'll automatically or directly set it on this controller,
and notice here; `there's no need for constructors`,
`there's no need for setters`, **Spring** will set the field directly.
However, like I mentioned, field injection is not recommended
by the `spring.io` development team because it makes the code harder to unit test.
All right, so that's field injection.
So I wanted to show it to you just in case you encounter it
on some of your legacy projects.
</div>

## [Qualifiers]()
<div style="text-align:justify">

In this section, we will cover annotation **Autowiring** and **Qualifiers**.
For auto wiring, we're injecting a **Coach** implementation.
So, **Spring** will scan for `@Components`,
check to see if anyone implements a given **Coach** interface.
If so, let's inject them.
But if we have multiple implementations, which one,
like what algorithm will **Spring** use to determine
which **Coach** that it should implement?

![image13](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image13.png?raw=true)

So, here's a diagram here of our multiple **Coach** implementations.
So, we have **Coach**, **CricketCoach**, **BaseballCoach**, **TrackCoach**, **TennisCoach**, etc.
And then, we have the actual source code for these implementations.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
```

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
```

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
```

All implement the **Coach** interface.
So, when we ask for a coach implementation, which one will **Spring** pick?
Well, we have a little problem.

```html
Parameter 0 of constructor in com.luv2code.springcoredemo.rest.DemoController
required a single bean, but 4 were found:

— baseballCoach
- cricketCoach
- tennisCoach
- trackCoach

...
```

So, this is the error message that you'll actually encounter when you run your application. 
**Spring** will say there's a parameter zero of the constructor
and the controller required a single bean, but four were found.
So, I need a **Coach**, but there are too many of them out here.
And at this point, **Spring** will not start up.
The application will not start because there's too much ambiguity.
**Spring** can't figure out which one you want.

So, one solution here is to be specific
and that's by making use of the `@Qualifier` annotation.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// ...

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And so, here's our coding here for our **DemoController**.
And everything looks the same except for this one new entry here for `@Qualifier`.
So, here we give `@Qualifier`, and then we specify the bean id of _cricketCoach_.
Now the bean id has the same name as the class except for the first character as lowercase.
And this will actually resolve the issue because now we're being very specific.
We're saying, _hey, use cricket coach as the injection for this given item_.
And there are other bean IDs out there that we could use
such as _baseballCoach_, _trackCoach_, or _tennisCoach_.
But in this scenario here, we're making use of the _cricketCoach_.
That's for constructor injection.

For setter injection, you can do a similar thing.
You can also use the `@Qualifier` annotation.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// ...

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public void setCoach(@Qualifier("cricketCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And then, here are our code examples, our normal setter method _setCoach_,
and then we specify `@Qualifier` annotation and then give _cricketCoach_.
Again, the bean ID is the same name as the class except for the first character is lowercase.
Alright, this looks pretty good.
Let's go ahead and move into our IDE and let's write the code.


Let's take care of our normal housekeeping work.
Let's stop all apps and close all windows.
And now I'll go ahead and do a copy, paste here on our `03-setter-injection`,
and then I'll rename it as `04-qualifiers`.
And then I'll go ahead and open this project in IntelliJ.
I'll go ahead and do a rebuild project 
just to make sure all of our auto loading will work accordingly.
And then let's go ahead and move into our **DemoController**.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    //public void setCoach(Coach theCoach) {
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And what I'd like to do is I'd like to change the code back to make use of constructor injection.
So we have constructor injection set up. 

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
```

Now I'd like to go through and create multiple implementations of the **Coach** interface 
just so we can kind of test this all out.
I'll move into this `common` package here, and I'll create a new class.
And this new class is called **BaseballCoach**
and this **BaseballCoach** implements, **Coach**.
And I also add the `@Component` annotation on this class.
And then I'll go ahead and return a given string here for the daily workout.
I'll tell the person to `spend 30 minutes in batting practice`.
And now let's repeat the process here.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
```

I'll create a new class here for **TennisCoach**.
Implement the **Coach** interface.
I'll also annotate this class with `@Component`.
And for **TennisCoach** here, we'll say `practice your backhand volley`.
All right, and let's repeat the drill one more time here.
Let's create a new class and this will be for our **TrackCoach**.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

The **TrackCoach** implements the **Coach** interface.
We'll say, `run a hard 5k`.
For a second and let's run our application.

```html
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of method setCoach in com.luv2code.springcoredemo.rest.DemoController required a single bean, but 4 were found:
	- baseballCoach: defined in file [D:\JAVA_STUDY\Github\dev-spring-boot\02-spring-boot-spring-core\04-qualifiers\target\classes\com\luv2code\springcoredemo\common\BaseballCoach.class]
	- cricketCoach: defined in file [D:\JAVA_STUDY\Github\dev-spring-boot\02-spring-boot-spring-core\04-qualifiers\target\classes\com\luv2code\springcoredemo\common\CricketCoach.class]
	- tennisCoach: defined in file [D:\JAVA_STUDY\Github\dev-spring-boot\02-spring-boot-spring-core\04-qualifiers\target\classes\com\luv2code\springcoredemo\common\TennisCoach.class]
	- trackCoach: defined in file [D:\JAVA_STUDY\Github\dev-spring-boot\02-spring-boot-spring-core\04-qualifiers\target\classes\com\luv2code\springcoredemo\common\TrackCoach.class]

This may be due to missing parameter name information

Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```

We see that we have a problem, right?
Things didn't work out.
It said the application failed to start.
And if we can kind of expand our window here for a bit and just scroll
through the log messages here.
Parameter zero of the constructor required a single bean, but three were found four.
Baseball, Cricket, Tennis, Track.
And so basically we kind of broke it the way we plan to break it, right?
We plan to break it with four **Coach** implementations.
So anyway, it, it's broken as desired here at the bottom 
to give us some actions that we could possibly make use of consider doing this stuff.
And it says, Hey, one of the options is making use of `@Qualifier` annotation 
to identify the bean that should be consumed.
Well, thanks for that hint, let's go ahead and implement that hint.
I'll move into our **DemoController** here.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    //public DemoController(Coach theCoach) {
    public DemoController(@Qualifier("baseballCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And in my **DemoController** I'll make use of that `@Qualifier` annotation.
So I'll specify the bean ID that I want,
and I'll specify the bean ID of **baseballCoach**.
So I'll use the same name as the class except for the first character is lowercase.
So I'm being super specific saying _Use this implementation of the **Coach** interface_.
So now we can go ahead and run our application

```html
2024-05-21T21:06:58.450+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-21T21:06:58.487+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-21T21:06:58.487+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-21T21:06:59.111+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-21T21:06:59.121+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-21T21:06:59.121+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-21T21:06:59.150+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-21T21:06:59.150+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 663 ms
2024-05-21T21:06:59.363+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-21T21:06:59.395+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-21T21:06:59.401+03:00  INFO 53576 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 1.193 seconds (process running for 1.474)
```

We see success, so the application started successfully
So, we kind of resolved the whole ambiguity issue,
and I can go over to my browser `localhost8080/dailyworkout`,
and I should get a baseball workout.

![image14](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image14.png?raw=true)

Great. 
So it says, `Hey, spend 30 minutes in batting practice`.
And that's coming from our **baseballCoach** implementation.
This is good.
And now let's swing back over and let's change this up a bit,
based on some configuration here.
So instead of **baseballCoach**, I'd like to modify to make use of **trackCoach**,
and it should reload the new version for me out there
and just do a reload on the browser over here and run a hard 5k.

![image15](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image15.png?raw=true)

Great.
So this is good.
And then, just to kind of set everything back to what we originally had, 
we'll make use of this **cricketCoach** as part of the `@Qualifier` here. 

![image16](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image16.png?raw=true)

It makes sure that we actually load the **cricketCoach** implementation
`practice our fast bowling for 15 minutes`.
Awesome. 
So as you can see here using a spring object factory 
spring container, we can actually configure how we want to use a given bean
and how we can inject a given bean based on the configuration.
So this is good.
</div>

## [Primary]()
<div style="text-align:justify">

Now resolving the issue with multiple **Coach** implementations,
we saw the example in the previous section by making use of the `@Qualifier` annotation.
We were super specific by mentioning a coach by name.
However, there's an alternate solution available.

Instead of specifying a coach by name using a `@Qualifier` annotation,
I simply need a **Coach**.
I don't care which **Coach**.
And I could say, _Hey, if there are multiple coaches out there, 
then you figure it out_.
You tell me who's the **primary** coach.
I really don't care.
I simply need a **Coach** to help me out here.
That's it.
So as before, we had our multiple coach implementations,
our track coach, baseball coach, tennis coach, and cricket coach.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

And then the one thing to notice here is that now we make use of this new annotation `@Primary`.
So this annotation basically says, out of the multiple coach implementations, 
this is going to be the **primary** implementation that you should use.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(Coach theCoach) {
    //public DemoController(@Qualifier("trackCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

Now we can resolve this using the `@Primary`.
So here's our code for our **DemoController**.
And now one thing to notice here, with our **DemoController**,
and our constructor injection, is that there's no need to use the `@Qualifier` annotation.
Because now, if there are multiple coaches,
we know the primary coach, based on that primary annotation,
that's on the track coach.
All right, so notice the difference here.

There's one warning here.
When you are using the primary annotation, 
and you probably were wondering about this, and thought about this.
When using the primary annotation can have **only one** for multiple implementations.
If you mark multiple classes with primary, then we have a little problem.
And when you run your application, if you try to mark multiple classes with primary,
then, in the error messages it'll say:

```html
Unsatisfied dependency expressed through constructor parameter 0:
No qualifying bean of type 'com.luv2code.springcoredemo.common.Coach' available:

More than one 'primary' bean found among candidates:
[baseballCoach, cricketCoach, tennisCoach, trackCoach]
...
```

_Hey, more than one primary bean found_.
So spring will say, _I don't know what you want me to do, here_.
_There's more than one primary.
I'm not sure which one to use, so I'm not going to start_.
So, have to be aware of that.
You can only mark one class with primary.

Now, you may also wonder, 
well, what about mixing `@Primary` and `@Qualifier` at the same time?
Can I use both of those in the same class?
The answer is yes, but you're asking for trouble.
Not really, but you have to be aware of this is,
that `@Qualifier` has the higher priority.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

So even if you mention a given class as the `@Primary` class,
that can be overridden, or have higher `@Primary`,
by making use of the `@Qualifier` annotation.
So even though there's a `@Primary` in **trackCoach**,
this example will actually make use of **cricketCoach**.
Alright, so just something to be aware of.

And so you may wonder, well, which one should I use?
Should I use `@Primary`, or should I use `@Qualifier`?
Well, `@Primary` leads it up to the implementation classes.
You could have issues of multiple `@Primary` classes leading to an error.
`@Qualifier` allows you to be very specific on which bean you want.
And so in general, I recommend using `@Qualifier`,
and the reasoning is that it's more specific,
and it also has a higher priority compared to `@Primary` annotation.
Let's go ahead and write some code using the `@Primary` annotation.


First, we take care of our normal housekeeping work.
We'll stop all of our apps and close all of our windows.
And I'll just do a little copy-paste here in this `04-qualifiers directory`,
and I'll rename it as `05-primary`.
And I'll go ahead and open this up in IntelliJ.
I'll just do a rebuild on the project.
I'll just do a quick cleanup here.
There's an old folder that we have, our `util` folder.
We're not using that anymore.
I'll just delete that.
And now I'll go ahead and remove the code for the `@Qualifier` annotation.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    //public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

I'll move into this class here, **DemoController**.
And we had the `@Qualifier` annotation.
I'll just remove that piece of code here. 
And this should fail, right?
Because we have multiple implementations out here,
and it's not sure which one it's going to use.
Let's just go ahead and run it real quick just to verify.
Alright, so we see the issue here.
The Application failed to start multiple implementations,
not sure which one that it should use.
We've seen all this stuff before.
Down in the action section, this is where it gave you
some hints or tips on how to resolve this issue.
All the way over to the right,
we already saw the example here of using the `@Qualifier` annotation.
We implemented that already, but notice here they also mentioned the `@Primary` annotation.
So let's mark one of the beans as `@Primary`.
All right. 
Let's try it out.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
@Primary
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

I'll move into my **TrackCoach** here,
and I'll use the `@Primary` annotation here.
Basically what I'm saying here is since there are multiple implementations,
then make the **TrackCoach** as the `@Primary` coach.
In our **DemoController**, one thing to notice here,
since we're using `@Primary` and **DemoController**
there's no need to use `@Qualifier` because we have a `@Primary` coach.
In this example, we set it up on the **TrackCoach**
such to resolve any issues or questions
that **Spring** may have as far as which one to use.
Make use of the `@Primary` one.
Let's go ahead and run our application,

```html
2024-05-21T23:18:29.958+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-21T23:18:29.994+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-21T23:18:29.994+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-21T23:18:30.650+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-21T23:18:30.659+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-21T23:18:30.660+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-21T23:18:30.693+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-21T23:18:30.696+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 699 ms
2024-05-21T23:18:30.925+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-21T23:18:30.953+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-21T23:18:30.960+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 1.252 seconds (process running for 1.541)
```

And success. 
The application starts successfully.
So there's no problem, there are no errors.
**Spring** knows which one of the coaches it should use 
because we're making use of the `@Primary` annotation.
Swing over into my web browser.
Go to `localhost:8080/dailyworkout`, and I should get a track workout.

![image15](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image15.png?raw=true)

Excellent.
Run a hard 5K because we're making use of that **TrackCoach** because it's `@Primary`.
Alright, swing back in, and let's try and break it on purpose.
So let's go ahead and add multiple `@Primary` components and let's see what happens.
We know that **TrackCoach** is already `@Primary`.
Let's go ahead and make **CricketCoach** `@Primary`.
We're trying to add multiple primaries, what will **Spring** do?

```html
Error starting ApplicationContext. To display the condition evaluation report, re-run your application with 'debug' enabled.
2024-05-21T23:23:26.820+03:00 ERROR 57712 --- [springcoredemo] [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'demoController' defined in file [D:\JAVA_STUDY\Github\dev-spring-boot\02-spring-boot-spring-core\05-primary\target\classes\com\luv2code\springcoredemo\rest\DemoController.class]: Unsatisfied dependency expressed through constructor parameter 0: No qualifying bean of type 'com.luv2code.springcoredemo.common.Coach' available: more than one 'primary' bean found among candidates: [baseballCoach, cricketCoach, tennisCoach, trackCoach]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:795) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1355) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1192) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:962) ~[spring-context-6.1.6.jar:6.1.6]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624) ~[spring-context-6.1.6.jar:6.1.6]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.2.5.jar:3.2.5]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.2.5.jar:3.2.5]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.2.5.jar:3.2.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:334) ~[spring-boot-3.2.5.jar:3.2.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1354) ~[spring-boot-3.2.5.jar:3.2.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1343) ~[spring-boot-3.2.5.jar:3.2.5]
	at com.luv2code.springcoredemo.SpringcoredemoApplication.main(SpringcoredemoApplication.java:10) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50) ~[spring-boot-devtools-3.2.5.jar:3.2.5]
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.luv2code.springcoredemo.common.Coach' available: more than one 'primary' bean found among candidates: [baseballCoach, cricketCoach, tennisCoach, trackCoach]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.determinePrimaryCandidate(DefaultListableBeanFactory.java:1755) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.determineAutowireCandidate(DefaultListableBeanFactory.java:1715) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1416) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:904) ~[spring-beans-6.1.6.jar:6.1.6]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:782) ~[spring-beans-6.1.6.jar:6.1.6]
	... 22 common frames omitted
```

Your app should reload automatically, and it's going to reload, 
and it's going to fail on the reload.
So if we scroll up a bit and take a look at the error message,
it says, "`Unsatisfied dependency exception.`"
In all this ugly stacked tray stuff, they're creating bean with **DemoController**.
And unsatisfied dependency.
So no qualifying bean of type coach available.
More than one `@Primary` bean found among the candidates.
So **Spring** figured us out.
It says, "_Hey, you have too many primaries out here. 
You can't do that._"
And so that's the main issue, that's the root cause.
And we can resolve that by simply having a single `@Primary` component.
And so I'll just do that here by just going to **CricketCoach**,
removing that `@Primary` annotation, saving, and it should reload 

```html
2024-05-21T23:26:54.413+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-21T23:26:54.499+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-21T23:26:54.500+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-21T23:26:54.500+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-21T23:26:54.510+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat-1].[localhost].[/]     : Initializing Spring embedded WebApplicationContext
2024-05-21T23:26:54.510+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 96 ms
2024-05-21T23:26:54.541+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-21T23:26:54.549+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-21T23:26:54.551+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 0.149 seconds (process running for 505.132)
2024-05-21T23:26:54.552+03:00  INFO 57712 --- [springcoredemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

And reload successfully.
So our app is up and running, and everything's up.
All right, so this is a good example of handling the issue of multiple implementations
by making use of the `@Primary` annotation.
And just swinging back into our **DemoController** here,
we see that there's no need to use `@Qualifier`
because we actually have a `@Primary` coach,
and by swinging over to **TrackCoach** here,
you can see that this given **TrackCoach** is marked as `@Primary`.
</div>

## [Lazy Initialization]()
<div style="text-align:justify">

In this section, we're going to cover **lazy initialization**.
Now by default, when your application starts, all beans are initialized.
So it'll scan for all the `@Component`s, and all those `@Component`s will be initialized.
So **Spring** will create a new instance of each, and make them available.

```java
@Component
public class CricketCoach implements Coach{

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    // ...
}
```

Now we can get some insights into this by setting up some diagnostics,
or adding some println statements to our constructors.
So on our **CricketCoach**, we'll add a `println` statement 
where we'll simply print out the name of the class that we're processing on.
We'll do a similar thing here for **BaseballCoach**:

```java
@Component
public class BaseballCoach implements Coach{

    public BaseballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    // ...
}
```

**TrackCoach**: 

```java
@Component
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    // ...
}
```

And also **TennisCoach**:

```java
@Component
public class TennisCoach implements Coach{

    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    // ...
}
```

And now, when we start our application, then in the actual logs we should see:

```html
...
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
In constructor: TrackCoach
...
```

Again, by default when your application starts, all beans are initialized, 
and **Spring** will create an instance of each and make them available.
Now we could make use of lazy initialization,
where instead of creating all the beans up front, we can specify **lazy initialization**.
So, a bean will only be initialized in the following cases:

* Either when it's needed for a dependency injection, 
* Or it is explicitly requested.

We simply add the `@Lazy` annotation to a given class 
and those rules will come into play.

```java
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    // ...
}
```

And here's a coding example. 
So for our **TrackCoach**, we'll say that our **TrackCoach** is lazy,
so we'll make use of the `@Lazy` annotation.
This given bean will only be initialized if it's needed for dependency injection.
If it's not needed, they won't create it.

```java
@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
    //public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }
    // ...
}
```

In this coding example here, with our **DemoController** 
and our constructor injection, we're going to inject the **CricketCoach**.
And when we run the application: 

```html
...
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
...
```

We'll see the outputs for our **BaseballCoach**, **CricketCoach**, and **TennisCoach**.
Since we're not injecting the **TrackCoach** in this given scenario, 
then **TrackCoach** is not initialized.
So basically we're saying,
"_Hey, don't create me unless I'm actually needed.
I don't wanna simply stand around and do nothing._"

Now, to configure the other beans for **lazy initialization**,
well, we'd need to add the `@Lazy` annotation to each class.
Not a big deal if we have a small number of classes,
but it turns into some real tedious work for a large number of classes. 
I wish there was a way we could set up a global configuration property,
just do it across the board.

And the answer's, yes, we can actually do that.
So in our `application.properties` file, we can set this **Spring Boot** property,
`spring.main.lazy-initialization=true`.
All beans are lazy, no beans are created 
until they're explicitly needed, including our **DemoController**.
Once we access our **REST** endpoint of `/dailyworkout`,
then **Spring** will determine the dependencies for the **DemoController** 
and for the dependency resolution,
**Spring** will create an instance of the **CricketCoach** first,
and then create an instance of the **DemoController**
and inject the actual **CricketCoach** into the **DemoController**.

For more diagnostics, let's add a print line to our **DemoController** constructor.
So here at **DemoController**:

```java
@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }
    // ...
}
```

We have the `@Autowired` annotation here `System.out.println` 
in constructor, print out the actual class name.
So again, for the dependency resolution,
**Spring** will create an instance of the **CricketCoach** first,
then create an instance of the **DemoController**,
and inject that into the actual **DemoController**.

```html
...
In constructor: CricketCoach
In constructor: DemoController
...
```

Alright, so kind of stepping back here and looking at lazy initialization, 
as far as the advantages:

* It only creates the objects as needed
* It may help you with faster startup time if you have a large number of components.

The disadvantages: 

* If you have some web related components like `@RestController` not created until requested.
So the first time out, you'll have to kind of create it first and then use it.
* May not discover any configuration issues until too late. 
* And you also need to make sure you have enough memory for all beans once created.

So with this lazy initialization feature, it's actually disabled by default.
You should really profile your application before configuring **lazy initialization**,
to see if the advantages will even help you.
And also, I want to say, avoid the common pitfall of premature optimization.
Because you could try and optimize something that's really not even worth it.
Alright, so just be aware of that
if you're moving to thinking about **lazy initialization**.
However, I did want to cover it here, just so you understand the concept,
and just so you understand the techniques.
Let's do a code example.

First, take care of our normal housekeeping, 
stopping all the apps and closing all the windows.
And let's do a little copy and paste on this `05-primary`.
And then we'll rename it as `06-lazy-initialization`.
Now let's go ahead and open this project up and IntelliJ.
What I'd like to do here is change the code bag
to using the `@Qualifier` annotation and removing the `@Primary` annotation.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

And we'll set the qualifier annotation here to make use of the **CricketCoach**.

```java
package com.luv2code.springcoredemo.common;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

And we'll open up the **TrackCoach** implementation and remove the `@Primary` annotation.
Now let's go ahead and run the application
just to make sure everything still works as desired.
Accessing this endpoint `localhost:8080/dailyworkout`:

![image16](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/02-spring-boot-spring-core/images/image16.png?raw=true)

And we're getting the **TrackCoach** workout
`practice our fast bowling for 15 minutes`.

What I'd like to do here is add some diagnostics here
or add some print line statements to the constructors
just so we can see everyone being created when the application starts up.
I'll just start here at the top with BaseballCoach.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
```

And I'll add a no argument constructor here for **BaseballCoach**.
And I'll add a `System.out.println here` to display the actual class name.
And I'll just copy this information,
and I'll move over to **CricketCoach**, and I'll paste in the information.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```

I'll update the constructor name - **CricketCoach**,
and then we have our existing code here for printing out the actual class name.
Move over to **TennisCoach**.
Do the same.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
```

And then update the actual constructor name accordingly.
And then more the same here for **TrackCoach**.

```java
package com.luv2code.springcoredemo.common;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

And I'll copy this print line statement,
and I'll use that print line statement in my **DemoController**.
It's on this **DemoController** here where we do the injection.

```java
package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

I'll also paste in that print line statement and that piece should be in place.
So, this coding all looks pretty good.
I've added all the diagnostics here for the various classes here 
that we're using in this example.

Now we can go ahead and start our application and test it out.

```html
2024-05-22T13:20:45.597+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-22T13:20:45.643+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-22T13:20:45.643+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-22T13:20:46.456+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-22T13:20:46.466+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-22T13:20:46.466+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-22T13:20:46.500+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-22T13:20:46.500+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 856 ms
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
In constructor: TrackCoach
In constructor: DemoController
2024-05-22T13:20:46.764+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-22T13:20:46.793+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-22T13:20:46.799+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 1.496 seconds (process running for 1.869)
```

And when we look at the logs here,
we can see that we have all of these constructor print line statements here
for baseball coach, cricket coach, and so on.
So notice here all the beans are created at application startup.
Now let's go ahead and mark the track coach as `@Lazy`.

```java
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "run a hard 5k";
    }
}
```

I'll open up my track coach implementation here,
and I'll simply annotate it with the lazy annotation.

```html
2024-05-22T13:23:06.566+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-22T13:23:06.674+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-22T13:23:06.675+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-22T13:23:06.675+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-22T13:23:06.684+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-22T13:23:06.684+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 117 ms
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
In constructor: DemoController
2024-05-22T13:23:06.734+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-22T13:23:06.741+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-22T13:23:06.743+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 0.191 seconds (process running for 141.812)
2024-05-22T13:23:06.745+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

Alright and so now when we run our application again,
we should see this output here baseball coach, cricket coach, tennis coach.
And notice, since we're not injecting the track coach,
it is not initialized, it's lazy.
That's the person that says,
_Hey, call me only if you really need me.
If you don't need me, I'm not gonna just show up and do nothing_.
So that's how lazy works out.

Now let's go ahead and set up lazy initialization
on a global scale or global configuration here.
We can set that up in our `application.properties` file.

```properties
spring.main.lazy-initialization=true
```

I'll give the actual property `spring.main.lazy-initialization=true`.
And remember that all beans are lazy.
No beans are created until needed, including our `@DemoController`.
Now let's zoom out here and let's go ahead,
and run our application and test it out.
And then our application is up and running.

```html
2024-05-22T13:27:10.283+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-22T13:27:10.373+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-22T13:27:10.374+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-22T13:27:10.374+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-22T13:27:10.384+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-22T13:27:10.384+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 98 ms
2024-05-22T13:27:10.398+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-22T13:27:10.399+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-22T13:27:10.428+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 0.163 seconds (process running for 385.497)
```

Notice there were no log statements or print line statements
for any of our beans because they weren't constructed yet or needed.
They're all lazy, including our `@DemoController`.
Now if we go ahead and hit this endpoint,
then it's actually going to reference our `@DemoController`
and also its dependencies accordingly.

```html
2024-05-22T13:27:10.283+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : No active profile set, falling back to 1 default profile: "default"
2024-05-22T13:27:10.373+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-22T13:27:10.374+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-22T13:27:10.374+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-22T13:27:10.384+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-22T13:27:10.384+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 98 ms
2024-05-22T13:27:10.398+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-05-22T13:27:10.399+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-22T13:27:10.428+03:00  INFO 33508 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 0.163 seconds (process running for 385.497)
2024-05-22T13:29:09.594+03:00  INFO 33508 --- [springcoredemo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-05-22T13:29:09.595+03:00  INFO 33508 --- [springcoredemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-05-22T13:29:09.598+03:00  INFO 33508 --- [springcoredemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 3 ms
In constructor: CricketCoach
In constructor: DemoController
```

So swinging back over here into our IDE and looking at the logs,
we see that we're in constructor 
for **CricketCoach** and also in constructor for **DemoController**.
So for dependency resolution, **Spring** creates an instance of the **CricketCoach** first, 
then it creates an instance of the **DemoController** and injects that **CricketCoach**.
Alright, so now you kinda see how the lazy initialization works out 
and also kinda how **Spring** is doing some work behind the scenes.
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