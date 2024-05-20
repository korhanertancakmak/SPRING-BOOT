# [Spring Core]()
## [What is Inversion of Control (IoC)?]()
<div style="text-align:justify">

In this section, we'll cover **inversion of control**.
What exactly is inversion of control?
Well, here's the textbook definition.
It's the approach of outsourcing the construction and management of objects.
So instead of us manually creating the objects ourselves,
we'll outsource this to another entity.

![image01](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image64.png?raw=true)

Now let's look at a coding scenario.
So here's our application, and we have a **CricketCoach** 
we'd like to call a method on the **CricketCoach** and says, 
hey, give me a _dailyWorkout_, and then it'll give us that response.
Now, this application should be configurable
such that we could easily change the coach for another sport, 
such as baseball, hockey, tennis, gymnastics, etc.
We'd like for this app to be configurable.
We can easily ask a coach for a workout, and they'll give us a given response.

![image02](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image65.png?raw=true)

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

![image03](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image66.png?raw=true)

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

![image04](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/01-spring-boot-overview/images/image67.png?raw=true)

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


</div>


<div style="text-align:justify">


</div>



<div style="text-align:justify">


</div>



<div style="text-align:justify">


</div>



<div style="text-align:justify">


</div>



<div style="text-align:justify">


</div>



<div style="text-align:justify">


</div>