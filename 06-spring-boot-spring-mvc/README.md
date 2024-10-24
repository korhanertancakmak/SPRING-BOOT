# [Spring MVC]()

## [Spring Model-View-Controller(MVC)]()
<div style="text-align:justify">

In this section, I'm going to give you a behind-the-scenes tour of **Spring MVC**.
So what are the components of a **Spring MVC** application?
Well, basically, it's a collection of webpages to lay out your UI components.
It's also a collection of Spring beans for controlling, handling services and so on.
And then finally, you have your Spring configuration.
You can choose XML, Annotations or pure Java,
and those are the kind of the main components of a **Spring MVC** application.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image01.png" alt="image01">
</div>

Now, how does spring MVC work behind the scene?
What I want to do is actually take this little process model
and kind of break it down and kind of dig into it a little bit and kind of go step-by-step,
do a little deep dive on how the whole process flow works.
Everything starts off with that first incoming request and an encounter, 
something called a **Front Controller**.
So the front controller is known as the `DispatcherServlet`.
This front controller is actually part of the **Spring Framework**.
It's already developed by the **Spring** development team,
so you don't have to create this.
This is part of the **Spring** jar files that you download,
so it's given to you for free.
So out of the box, you have this front controller.
What this front controller will do is it'll actually
delegate the request to some other objects or items here in our system.
So as a developer, you'll create the **Model**, the **View**, and the **Controller**, MVC, right?
So the **Model** objects are in orange, the **Model** objects contain data.
The **View** templates that are in dark green, view page, to actually render the data
and then the **Controller** classes, that's in yellow,
that's your actual business logic or your processing logic.

Allright, let's start with the **Controller**.
So when the front controller has a request, 
it delegates the request to the **Controller**, that's in yellow.
The **Controller** is the code that you'll actually create.
Basically, in this **Controller**, this contains your business logic.
So this is where you'll handle the request where you'll maybe read some form data,
and then you'll take this data and store it or retrieve it.
You may store it into a database or retrieve information from a web service.
Basically, once you have your data, and you're using it,
then you can take that data and place it into the **Model**.
So the Model is just a container for your data,
and then you pass it back to the appropriate view template.
So again, your **Controller**, code that you create, contains your business logic,
and it handles the web request.

Alright, so let's talk about the **Model**.
As I mentioned earlier, the **Model** contains your data.
So when your **Controller** goes off and performs an operation to retrieve data
from a backend system like a database or web service, or a Spring bean,
you can take that data and place it into the **Model**.
So the **Model** again, is your container, like your suitcase or your luggage,
for shipping data between various parts of your **Spring MVC** application.
So that **Model** data will actually get passed over to the **View** template,
and they can actually handle that for displaying the data.

Now, let's take a look at the **View** template.
The most common **View** template that you'll use is **Thymeleaf**, 
and we'll see some examples for it soon.
Now, **Spring MVC** is very flexible,
there's many different view templates that you can use,
I'll talk about that in a second.
But for now, let's just think about **Thymeleaf**.
This **Model** data comes over to your **View** template,
and then your **Thymeleaf** page can read that **Model** data and display it.
So say for example, you have a list of students or a list of products,
then your **Thymeleaf** page can create a table
to display that product list or that student list.
Or say for example, somebody signing up for an airline flight or signing up for a computer class,
then your **Thymeleaf** template can actually give them a confirmation, 
you're registered for the class, or here's your confirmation number.
So that's the idea of the **View** template.
It's basically a **Thymeleaf** page that will provide data to the user.

Now, as mentioned, **Spring MVC** is very flexible on the view templates, 
there's actually other view templates that are supported.
So if you don't want to use **Thymeleaf**,
then you can make use of other templates,
like **Groovy**, **Velocity**, **Freemarker**, the list goes on.
You can plug in all different types of View templates.
If you'd like to get more details on this go to this [link](https://www.luv2code.com/spring-mvc-views), 
it'll actually redirect you to the official **Spring** documentation,
and you can get more information on the various other templates that are out there and available.
Okay, great, so this is a quick behind-the-scenes view of **Spring MVC**.
You learned about the **Model**, the **View**, the **Controller**,
and the concepts at a very theoretical level.
In the following sections we'll start to explore the other features of **Spring MVC**,
such as **Form** and **Validation**, and we'll start writing more code.
</div>

### [Spring MVC with Thymeleaf]()
<div style="text-align:justify">

In this section, we will cover **Thymeleaf** with Spring Boot.
So you may wonder, "Well, what exactly is **Thymeleaf**?"
Well, it's a Java templating engine, it's an open-source project,
and you can get details on it at [thymeleaf.org](https://www.thymeleaf.org).
It's commonly used to generate HTML views for web apps.
However, it's a general purpose templating engine.
So you can actually use **Thymeleaf** outside of web apps.
One thing I'd like to point out is that it's a separate project.
It's unrelated to spring.io,
and you can actually create Java apps with **Thymeleaf** with no need for Spring.
However, there's a lot of synergy between the two projects,
so you hear them mentioned and used on similar projects.

Now, what is a **Thymeleaf** template?
Well, it can be an HTML page with some **Thymeleaf** expressions.
It can also include dynamic content from **Thymeleaf** expressions.
And for an example of an HTML page, you have HTML tags,
and then you have these **Thymeleaf** expressions.
So they can access Java code, objects, Spring beans, and so on.

The next question is, well, where's a **Thymeleaf** template processed?
Well in a web app, Thymeleaf is processed on the server.
So the results are included in the HTML and returned to the browser.
So here's an example.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image02.png" alt="image02">
</div>

The web browser sends a request over to Spring MVC Controller,
the controller adds some data to the model, sends it over to the template,
and then the template sends back HTML to the web browser.
Let's look at the Thymeleaf demo.
We'll have our web browser send a request over to a Spring MVC controller,
add some data to the model, send it over to our Thymeleaf template,
and then I'll actually send back that generated HTML.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image03.png" alt="image03">
</div>

So the output here, we'll simply say, "Time on the server is,"
and we simply plug in the actual server timestamp.
Let's look at the development process for this demo.

* Add **Thymeleaf** to the **Maven** pom file
* Develop the Spring MVC Controller
* Create the **Thymeleaf** template

So the first thing we're going to do is create a project at the Spring Initializer website.
Here we'll select **Maven**, different languages here, I'll choose **Java**.
The one thing I want to do is update the project metadata for **Group** and **Artifact**.
So for the **Group**, I'll give `com.luv2code.springboot` and for the **Artifact**,
I'll give `thymeleafdemo`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image04.png" alt="image04">
</div>

I'll move by giving the web dependency, the thymeleaf dependency,
and I'll also put in dev tools just so I can take advantage of that automatic restart.
And then from there, just go ahead and generate the project.
So 'll simply unzip the file, and I'll just drop it in here in the `dev-spring-boot`.
So the first thing I want to do is verify some information in the `pom.xml` file.
So I'll go ahead and open up this file, and I'll move down here,
and I'll just verify the dependencies that were added.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

So here for `spring-boot-starter-thymeleaf` dependency, 
this will autoconfigure to use **Thymeleaf** templates.
And that's it.
There's no additional configuration that you need to set up
to make use of **Thymeleaf** with **Spring Boot**.

Now, I'll actually create a new package,
and I'll use this new package to add my controllers.
So I'll call this package `.controller`.
So in Step 2, I'm actually going to develop a **Spring MVC Controller**,
and I'll place it in this package.
So the name of this controller, I'll call it **DemoController**.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // create a mapping for "/hello"
    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", java.time.LocalDateTime.now());
        return "helloworld";
    }
}
````

So basically we'll just create a mapping for `/hello`.
So up top, I'll give the annotation for `@Controller`,
and then I'll set up a `@GetMapping` at the bottom here for `/hello`.
I'll create a method here called `sayHello` that takes a model.
And I'll simply add an attribute to the model.
The name of the attribute is `theDate`.
And then finally, I simply return `helloworld`.
And remember, since we have the Thymeleaf dependency
in the `pom` file **Spring Boot** will autoconfigure to use **Thymeleaf**.
So when we return `helloworld`,
it's going to look in `source/main/resources/templates` for a `helloworld.html`.

So in Step 3, we're going to create a **Thymeleaf** template.
And be sure to create it in the `resources/templates` directory.
So the name of this file, we'll call it `helloworld.html`.
I'll start out by just creating a very basic **HTML** file.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <title>Thymeleaf Demo</title>
</head>
<body>

    <p th:text="'Time on the server is ' + ${theDate}"></p>

</body>
</html>
````

I'll set up a very basic head with the title.
And now I'll create this paragraph tag,
and I'll make use a **Thymeleaf** expression `th:text` to actually output some text.
I'll say time on the server is, and I'll make use of the plus to do concatenation.
And then I reference `theDate`, and that's the date that we pull from the model.
And remember, in the controller we added data to the attribute.
So the **Thymeleaf** template can actually access `theDate` from that spring MVC model.
And then based on this information, that's how we get the generated output for this page.

Now let's dig into this a bit more as far as placing a **Thymeleaf** template.
So in Spring Boot, your **Thymeleaf** template file
goes into `src/main/resources/templates`,
and then for web apps, the **Thymeleaf** templates will have a `.html` extension.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image05.png" alt="image05">
</div>

Okay, with step three we'll create the **Thymeleaf** template.
So remember our template is basically an HTML file,
and we can drop in some **Thymeleaf** expressions.
So the one thing that we'll do up top
is we'll set up the XML namespace for `:th`.
That's to use the Thymeleaf expressions.
And then we use that expression here for `th:text`
and that'll give us this output here, "Time on the server is,"
and then we plug in the actual date.

Okay, so let's go ahead and run this `ThymeleafdemoApplication` application.
The app is up and running.
Let's go ahead and swing over to our web browser,
and we'll go to `localhosts:8080/hello`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image06.png" alt="image06">
</div>

And all right, so we get our desired output.
So time on the server is, and they simply plug in that information.
Now you may wonder, well where did we get the date?
I mean, where's that come from?
Is it a magic term or how did that work out?
Well, actually it's from our Spring MVC model.
So up top we had `theModel.addAttribute`, `theDate`.
And so **Thymeleaf** is actually accessing `theDate` from the Spring MVC model,
and then we use that for the given output of this given HTML page.
So that was a nice little example of building a Spring Boot application
that makes use of Thymeleaf along with those **Thymeleaf** expressions.
</div>

### [CSS and Thymeleaf]()
<div style="text-align:justify">

In this section, we'll learn how to make use of **CSS** and **Thymeleaf**.
So in the old version of our application, our output is plain, generic, very basic.
What I'd like to do is actually add some style or color to our output.
So I'd like to apply some CSS.
And then for our output, I'd like to have it in italics and set up the color for green.
And CSS can help us with this.

Using **CSS** with **Thymeleaf** Templates, you have some options.

* use of a local CSS file as part of your project
* Reference remote CSS files

And we'll actually cover both of these options in this section.
Here's the development process.

* Create the CSS file 
* Reference that CSS in our **Thymeleaf** template
* Apply the CSS style

Okay, step one, creating the **CSS** file.
So, **Spring Boot** will actually look for static resources
in the directory `src/main/resources/static`.
So in this directory we'll create a subdirectory called `css`
and we'll have this file called `demo.css`.
And this `demo.css` is just a very basic **CSS** file, nothing special.
Now this subdirectory here, we called `css`,
we can actually give it any subdirectory name.
So once we're under static, then we can give any custom names that we want
or any custom subdirectory.
So I can have `/css`, `/images`, `/js`, so on.
You can give any custom names that you want as long as it's rooted off of source main resources static.

````html
<head>
    <title>Thymeleaf Demo</title>

    <!-- reference CSS file -->
    <link rel="stylesheet" th:href="@{/css/demo.css}" />
</head>
````

Now, in step two, we'll actually reference the **CSS** in our **Thymeleaf** Template.
So our **HelloWorld** example, we can actually reference that **CSS** file
by using a `th:href` and then a give `/css/demo.css`,
and I'll actually reference that given file.
Now a couple of things here. Notice this `@` symbol.
So this will actually reference the context path of your application.
Basically giving you the application route, allowing you to easily access files
using the **Thymeleaf** template.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <title>Thymeleaf Demo</title>
    
    <!-- reference CSS file -->
    <link rel="stylesheet" th:href="@{/css/demo.css}" />
</head>
<body>

    <p th:text="'Time on the server is ' + ${theDate}" class = "funny"></p>

</body>
</html>
````

Now, in step three, we'll actually apply the **CSS** style.
And I give `class = funny`.
So that's the actual name of the style that's in my `demo.css` file.
And then it'll apply that style, and we'll get the appropriate output, right?
So our time on the server is...
It's in italics and the color is green.
So, very basic **CSS** work there,
but at least you see how to integrate CSS with **Thymeleaf** templates.

There are other search directories.
Spring Boot will search the following directories for static resources,
and it's all rooted of `src/main/resources`.
So underneath that directory, then **Spring Boot** will search for 

* /META-INF/resources
* /resources
* /static
* /public

And it'll search these directories in a top-down fashion.
Now, most commonly on real-time projects,
you'll see the actual static resources
placed in the directories: `static` or `public`,
but you can make use any of those directories out there if you'd like.

Now to integrate third party **CSS** libraries like **Bootstrap**,
one option is that you can make use of local installation.
So you can download the Bootstrap files, 
and then you can add those files to your `static/css` directory.

````html
<head>
    ... ...
    
    <!-- reference CSS file -->
    <link rel="stylesheet" th:href="@{/css/bootstrap.min.css" />
</head>
````

So in this example here we have bootstrap.min.css in our directory.
So we can reference that CSS file by going to `/css/bootstrap.min.css`.
And that's it.
Pretty basic, pretty straightforward.

````html
<head>
    ... ...
    
    <!-- reference CSS file -->
    <link rel="stylesheet" href="https://cdn.jsdeliver.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" />
</head>
````

Another option is that you can access the **Bootstrap** files remotely on the internet.
So in this case, you simply give a **href** to wherever the **Bootstrap** files are located.
So in this example here, we'll access, and that's on the **Bootstrap CDN**.
And then give a reference to the version, css, `bootstrap.min.css`.
So that's it.
So, you have a choice, you can download **Bootstrap** locally and reference it,
or you can make use of **Bootstrap** that's hosted on the web somewhere.

Now let's create package `css` under `src/main/resources/static`.
And I'll give this folder name of css.
And remember any custom subdirectory here, you can give any name you want,
but I'll just kind of keep something consistent here.
And then I'll go through and actually create the **CSS** file in this directory.
And I'll give the name of the file as `demo.css`.

````css
.funny {
    font-style: italic;
    color: green;
}
````

I'll just set the actual font style to italics,
and I'll set the color to green.
That's it.
And now I'll move over here to my template file `helloworld.html`.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <title>Thymeleaf Demo</title>

    <!-- reference CSS file -->
    <link rel="stylesheet" th:href="@{/css/demo.css}" />
</head>
<body>

    <p th:text="'Time on the server is ' + ${theDate}" class="funny"></p>

</body>
</html>
````

And this is our next step here,
referencing the **CSS** file in our **Thymeleaf** template.
I'll write some quick comments to myself.
So we give a link here to our style sheet and make use of `th:href`,
and we give a reference to that `css/demo.css`.
And remember, the `@` symbol is a reference to the context path of your application,
basically giving you access to your application route.
And so step three of applying the CSS style.
So in **HTML** we can apply styles by using `class=`, 
and we give that actual class name or that style name.
So here we're going to apply the style of `funny`.
Again, italics, green.
And now we can simply test it out by running our application.
Just swing over to my browser here and just reload that page.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image07.png" alt="image07">
</div>

And oh yeah, there we go.
So we have our output in italics with green,
and it's all based the **CSS** styles that we defined in our `demo.css` file.
</div>

### [Spring MVC Form and Model]()
<div style="text-align:justify">

In this section, we're going to learn how to read form data with **Spring MVC**.
So at a high level, we're going to have a form.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image08.png" alt="image08">
</div>

We're going to prompt the user for their name, they'll hit **Submit Query**,
it'll go through our **Spring MVC** application, 
and then will show a confirmation page `Hello World of Spring`, `Student name`,
and we simply plug in the student name that they entered.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image09.png" alt="image09">
</div>

So here's the application flow.
So in the browser they'll go to our website, and they'll enter `/showForm`.
This will go to our `HelloWorld` controller,
and the main purpose of this is to simply show the form.
Then, once we have the form displayed, 
once they enter their information and hit **Submit**,
then they're going to pass it over to this `/processForm` request mapping.
So again, it's going to go back into our **HelloWorld** controller,
and for that mapping, we'll simply pass back a confirmation page.
So we'll say `Hello World of Spring`, `Student name`,
and we simply plug in the student name.
So the key here is that we're going to use one **HelloWorld** controller
that's going to have two request mappings.

````java
@Controller
public class HelloWorldController {
    
    // need a controller method to show the initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
    
    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }
}
````

Now let's go ahead and look at some of the source code here for the controller.
So again, with the **Spring** controller, we always annotate it with **@Controller** up top,
then I'll set up a method to show the initial form.
So I'll define a request mapping, `/showForm`,
I give a method name, `public String showForm`,
and then return `helloworld-form`.
Now we'll also need to add a second method here to process the form.
So we'll have a request mapping for `/processForm`.
And we have a method `public String processForm`.
And we simply return `helloworld`.
That'll be the actual confirmation page that'll say `Hello World of Spring`,
and we simply display the student name.
Now again, remember the method names can be anything.
We simply map the paths to the actual method.
But again, you can use any method name you want,
`showForm`, `processForm`, `doTheWork`, whatever.
Allright, so lets kind of step back and take a look at the development process.

* Create controller class
* Show the HTML form
 - Create a controller method to show the form
 - Create the view page for the HTML form
* Process the HTML form
 - Create the controller method for processing the HTML form
 - Develop the View page for confirmation (to show the actual results).

Okay let's go ahead and move into our IDE and lets starting writing codes to process the form data.
We'll continue by using our existing project.
So the first thing we're going to need to do here
is to actually create our `HelloWorldController`.
And the name of this class will be `HelloWorldController`.
All right, great so here's our basic class.
And the first thing I'll always do here is add the `@Controller` annotation 
because this is a **Spring NVC Controller**.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
}
````

And now what I'll do is I'll actually write some comments to myself 
just to kind of keep myself on track as far as what I need to do.
So I know I'm going to need a controller method to show the initial form.
And then once I have the form displayed, 
I'm going to need a controller method to actually process the form.
Allright, so are those the basic two things 
that I'll need to do here in this **HelloWorldController**, 
so I know I need to have at least two methods for building this out.
Allright, so let's go ahead and take this one step at a time.
So the first thing I need to do is set up my method here.
I'll call it **showForm**.
And it's going to basically return `helloworld-form`,
and we know in the background **Spring MVC** will add all the magic onto it.
I'll set up this `@RequestMapping`, and I'll have it set up for `/showForm`.
Okay, so stepping back this looks really good so far.
So this is basically our controller method to show the initial form.
So they're going to go to `/showForm`, and the background will simply say `helloworld-form`,
so we're in good shape with this layout so far for this method.

Allright, so now we need to actually go through 
and create that **View** page, or create the actual form.
Now be sure to move to the direct `src/main/resources/templates`,
and then we'll create a new **HTML** file.
The file name is `helloworld-form.html`.

````html
<!DOCTYPE html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

    <form th:action="@{/processForm}" method="GET">
        
        <input type="text" name="studentName" placeholder="What's your name?" />
        <input type="submit" />
    </form>

</body>
</html>
````

And I'll do a quick copy-paste exercise here.
I want to copy some of the code from the previous file `helloworld.html`.
Mainly I want to copy the **Thymeleaf** xml namespace(`xmlns`).
I'll copy the first two lines from the file `helloworld.html`,
and then I'll move over to `helloworld-form.html`, and I'll paste the code.
Allright so let's go ahead and define our form element.
And then we'll say the `th:action="@{/processForm}"`,
and we'll set the `method` equal to `GET`.
So basically we're going to send something over to the `path/processForm`,
and then the actual method type will be `GET`,
so it's going to pass it as a `GET` request as opposed to `POST` request,
and we'll talk about getting `POST`s a little bit later.
So the input `type` equals `text` and the `name` we give `studentName`, 
so that's the actual form field name.
And then I'll just set a `placeholder` here.
That'll be the actual prompt text that you'll see when the form is actually displayed.
So this is the prompt, `what's your name`, type stuff.
And now we'll just help you set up an input `type` for the `submit` button.
And that's pretty much it for our form.
And then we have our HTML form taken care of.

Let's go ahead and move back over to our controller,
and we see the coding here for this request mapping.

````java
@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
}
````

They're simply going to do a `/showForm`,
and this will return `helloworld-form.html`,
and we know the Spring magic in the background.
That'll add the `.HTML` for this given return.
Allright, let's go ahead and run it real quick.
Let's enter `localhost:8080/showForm`, 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image10.png" alt="image10">
</div>

and make sure you use the exact same case, because it's case-sensitive,
and this looks good.
So our form displayed, but we're only halfway there.
We're not fully there yet, so we can display the form,
but we haven't really done any work for handling the submit or handling the process.
So if I would actually hit submit here, it'll break.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image11.png" alt="image11">
</div>

Because it's a 404, because they can't find anything to handle process form,
because that's what the form's trying to do.
It's trying to send data to this path `/processForm`,
but we haven't done that yet.
So we have an item there to add a method to process the **HTML** form,
so let's go ahead and do that now.
Allright, so let's go ahead and write our method for process form.

````java
@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public string processForm() {
        return "helloworld";
    }
}
````

So here I call it `public string processForm`,
and again, remember you can use any method name.
We simply map it with the appropriate mapping here.
And then I'll say return, and we're actually going to return `helloworld`.
But let's go ahead and copy this `@RequestMapping`
and drop it in here, so for request mapping,
it's going to be a `/processForm`.
So that's where the **HTML** form's going to send it over
to this request mapping, and this is again, 
annotating this method `processForm`.
And that's basically it so far, so this method's in place, it can handle that web request,
but now we need to actually write the code here for our View template for `helloworld`.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <title>Thymeleaf Demo</title>

    <!-- reference CSS file -->
    <!--
    <link rel="stylesheet" th:href="@{/css/demo.css}" />
    -->
</head>
<body>

    <!--
    <p th:text="'Time on the server is ' + ${theDate}" class="funny"></p>
    -->
    
    Hello World of Spring!
    <br><br>
    Student name: <span th:text="${param.studentName}" />

</body>
</html>
````

Now open up `helloworld.html`, and that's the actual file 
that we'll use during this section.
And now I'll move into the head section here,
and I'll remove the reference to **CSS**,
because we're not doing any **CSS** at this point,
and I'll also remove the body information, because we'll add new body content in a second.
Alrighty, so that looks pretty good so far, and now I'll just print out some text here,
`hello world of Spring`, give some line breaks here, and now I'll simply read the form data,
so I'll say `Student name:`, and I'll read it by using it as I'll use the dollar sign "$", `param.studentName`.
Student name's the actual name of the **HTML** form field.
We can read form field data by using dollar sign, "$", `param.` 
whatever that exact form field name is.
So let's go ahead and save this file, and let's get ready to run it.
Now let's go ahead and enter `/showForm` in the URL up top, and hit enter.
Now let's go ahead and enter a student name.
and I'll hit the submit button:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image12.png" alt="image12">
</div>

and there we go, `hello world of Spring` and student name `Korhan`,
and `Korhan`'s the actual form data that we entered,
so we're able to read form data with Spring MVC.
</div>

### [Spring MVC Adding Data to Model]()
<div style="text-align:justify">

In this section, we're going to learn about adding data to the **Spring Model**.
So the main focus here is on the **model**.
So far we've covered the **controller** code, we've covered **view** templates,
but we didn't really talk about the **model**.
And so that's where we're going to see how we can use it, 
how we can add data to it, and also how we can read data from the **model**.
It's all about the **model** right now.

So the spring model is really just a container for your application data.
So in your controller code, you can put anything in the model,
you can put in strings, objects, information that you get from the database, etc.
and you can load it up and in your actual view page,
You can actually access data from the model.

Now I'll actually walk through a code example.
So I'm going to create a new method to process form data.
I'm going to read the form data, and then I want to convert the form data to all uppercase.
And then I'll add this uppercase version of the data to the model.
And then we'll display it in the page.

When we make use of our spring controller,
we can actually pass the model to our controller,
and we can also read form data in our controller.
So what I'm going to do here is create this new method called,
`letsShoutDude`, because I'm just going to shout and use everything in all caps.
That's just my little joke here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image13.png" alt="image13">
</div>

And we're going to set up a `@RequestMapping` up top.
So the name of this request will be, `/processFormVersionTwo`.
Allright, so our method, `letsShoutDude`, we're going to pass in two parameters.
So in **Spring MVC**, when you create your controller methods 
they're actually very flexible on the parameters you can pass in.
So if you need to read form data in your controller code,
then you pass in the `HttpServletRequest`.
And this works like the normal server request that you've learned already.
And also you can pass in the `Model`.
So again, the **model** is just a container that can hold your form data.
When the **model** comes in initially it's empty, and you can add data to it.
So let's walk through this code here step by step.
So the first thing we want to do is read the request parameter from the HTML form.
The form has a field name called `studentName`.
I simply say `request.getParameter("studentMame")`.
That allows me to read that form data and I simply assign it to a variable here called `theName`.
Now, in my little example, I want to convert the name to all uppercase.
So I simply say `theName.toUpperCase()` and assign it to itself.
So now I have an all caps version of the name, kind of the way of shouting.
In the online world, we say if you type in all caps, it's shouting.
So that's why we have this whole little shouting thing going on here.
Then we're going to create a message.
So our message is going to be simply, `Yo!`
And then whatever the person's name is like, `Yo! Pauly`, `Yo! Adrian`.
And that'll give us the message or the result.
So now that I have the result,
I want to add this message to the model.
So to add something to the model, I say `model.addAttribute`.
And the attribute, I give the actual name of the attribute comma the value.
So the name here is `"message"` and the actual value is `result`.
So result is the object that we just created on the previous line.
Now the actual name of the attribute `"message"`,
you can give any name you want for the attribute.
So you can call it `fubar`, `funny`, `silly`.
It's totally up to you as long as you're consistent in other parts of your application.
And that's it for actually adding data to the model.
And then finally I say `return "helloworld"`,
it'll go up to the hello world.
So that's the basic coding there for passing the model to your controller
and also adding data to the model.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image14.png" alt="image14">
</div>

Alright, now in my actual view page, I can access data from the model.
In order to access data from the model,
I simply make use of the dollar sign, curly brace,
and I give the actual model attribute name, `"${message}"`.
So in the previous example, we set something with the attribute name of `"message"`.
I can access it using that same name, `"message"`, and whatever data was placed there,
it'll be displayed right here on the screen.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image15.png" alt="image15">
</div>

And then finally, you may have the question, 
well, what if I need to add more data?
What if it's more than just one item?
Well, you can add as much data here as you'd like to the model.
All you do is you simply call, add attribute, X number of times.
So in this example, I have up top, I get data,
I have three variables, `results`, `theStudentList`, `theShoppingCart`.
Then I can add that data to the model.
I simply say `model.addAttribute("message", result)`,
`model.addAttribute("students", theStudentList)`, `model.addAttribute("shoppingCart", theShoppingCart)`.
So that's it.
So you simply give your **name-value** pair that you're adding for a given attribute,
and you can continue doing that X number of times.
That's the basic process.

Okay, let's go ahead and move into our IDE, and let's add this new controller code here
for processing the form data, and making those appropriate changes.
So, let's go ahead and move into **HelloWorldController**.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }
    
    // need a controller method to read form data and add data to the model
}
````

And again, I always like to write a little comment 
just to keep myself on track as far as what I need to do.
So, we're going to add a new controller method to read form data 
and also add data to the model.
So, that's our game plan as far as what we need to do.

````java
@Controller
public class HelloWorldController {

    ...
    
    // need a controller method to read form data and add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude (HttpServletRequest request, Model model) {
        
        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        
        // convert the data to all caps
        theName = theName.toUpperCase();
        
        // create the message
        String result = "Yo!" + theName;
        
        // add message to the model
        model.addAttribute("message", result);
        
        return "helloworld";
    }
}
````

So, let's go ahead and define that method here, `letsShoutDude`.
We're going to shout in all caps.
So, we pass in those two parameters,
the `HttpServerRequest`, and also the `model`.
And we're going to return `helloworld`.
Allright, so let's just copy that `@RequestMapping`
and let's move down and let's paste it in right above our `letsShoutDude` method.
So, we're going to change the `RequestMapping` path to processFormVersionTwo.
So, a unique path where, and we'll actually update our form to point to this path also.
Allright, so that's the basic layout here for this controller method,
but we still need to fill in the blanks and do some stuff inside.
So, the first thing we need to do is read the request parameter from the HTML form.
Then, I need to basically convert that data to all uppercase, because I want to shout.
Then, we'll actually create our message.
And then, finally, I'm going to add that message to the model.
So, that's the basic game plan of what I want to do here in this code.
So, again, to read the form data, we simply say `request.getParameter`,
and we give the actual form field name of `studentName`.
And then, we actually go through, and we convert this data to all caps.
I say `theName.toUppercase()`.
And then, next, I go through and I create the message.
So, I'll say, `result = "Yo!"`, and I'll concatenate it with `theName`.
And then, the important thing here is adding this to the model.
So, I'll say `model.addAttribute`.
And I give the actual name, comma, value.
So, the name of the attribute is `"message"` and the value is `result`.
So, that's the information from the previous line that we just set up.
And so, that's the main piece here, adding data to the model.
So, that's it for our method.
So, we have this new request mapping `/processFormVersionTwo`.
We take in some parameters, we read the form data, convert the names to uppercase,
create a new message, and add that to the model.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <title>Thymeleaf Demo</title>

</head>
<body>
    
    Hello World of Spring!
    <br><br>
    Student name: <span th:text="${param.studentName}" />
    <br><br>
    The message: <span th:text="${message}"></span>

</body>
</html>
````

Allright, so our controller's pretty much taken care of.
Now let's go ahead and update our view page,
so we can access information from the model.
I will add a little, some line breaks here.
Then I want to read data from the model, and I'll say the `message`.
And now to actually access the message or read that model data, 
use dollar sign, curly brace `message`.
So again, when I use dollar sign, curly brace, in this case message,
it's actually going to access a attribute from the model.
So whatever we placed in there under the name of message 
or the key of `message`, it's going to actually retrieve that information
and display it right here on the page.

````html
<!doctype html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

    <!-- <form th:action="@{/processForm}" method="GET"> -->
    <form th:action="@{/processFormVersionTwo}" method="GET">
        <input type="text" name="studentName" placeholder="What's your name?" />
        <input type="submit" />
    </form>

</body>
</html>
````

Alright, and now one other thing we need to do is modify our form 
or update our form for this new version of our process.
Because in our controller, that new code we just added,
this is for `/processFormVersionTwo`.
Okay, that's the request mapping.
I'll simply copy that request mapping,
and I'll move back over to my form,
and I'll paste that information in there.
So that's our form that's updated process form version two.
Alright, so save everything.
Let's go ahead and run this real quick.
And I can go ahead and enter my student name, student name of `Larry` and hit the submit.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image16.png" alt="image16">
</div>

And there we go.
`Hello World of Spring! Larry`.
And now the message.
And this is our new stuff.
`Yo! LARRY`, okay, so we're shouting at `Larry`.
We took his name and converted it to all caps, and we put yo on the front.
We added data to the Spring model.
</div>

### [Spring MVC Binding Request Parameters]()
<div style="text-align:justify">

In this section, we're going to learn how to read HTML form data using the `@RequestParam` annotation.
So we'll actually work through a code example similar to what we've already worked on
where we're going to read the form data, convert the name to uppercase and add the uppercase version to the model.

````java
@RequestMapping("/processFormVersionTwo")
public String letsShoutDude (HttpServletRequest request, Model model) {

    // read the request parameter from the HTML form
    String theName = request.getParameter("studentName");

    // convert the data to all caps
    theName = theName.toUpperCase();

    // create the message
    String result = "Yo!" + theName;

    // add message to the model
    model.addAttribute("message", result);

    return "helloworld";
}
````

And you saw some of the coding for this already with the `letsShoutDude`
where we would read the student's name by saying `request.getParameter("studentName")`, assigning it,
and then doing some work with it later on.
Now what we're going to do is use a different technique for reading the form data.

````java
@RequestMapping("/processFormVersionTwo")
public String letsShoutDude (@RequestParam("studentName") String theName, Model model) {

    // now we can use the variable: theName
}
````

So **Spring** has a special annotation call `@RequestParam`.
This will allow you to read form data and automatically bind it 
to a parameter coming into your method.
So here in this example, I make use of `@RequestParam`, and I give student name.
So behind the scenes, **Spring** will actually read that data from the request parameters,
and then it'll take that data and bind it to this variable that I have here called `theName`.
And at that point, we have the variable, and we can use it in our application.
But the key here is that **Spring** has that special annotation here called `@RequestParam` 
for reading form data for you.

So what I'll do is I'll actually move into my `HelloWorldController`,
and I'm going to make use of a little copy-paste example here.
I'm basically going to copy everything I have from `ProcessFormVersionTwo`
and then I'll just make some small changes to it.
So let me go ahead and highlight everything here for this `ProcessFormVersionTwo`.

````java
//@RequestMapping("/processFormVersionTwo")
@RequestMapping("/processFormVersionThree")
//public String letsShoutDude (HttpServletRequest request, Model model) {
//public String processFormVersionThree (HttpServletRequest request, Model model) {
public String processFormVersionThree (@RequestParam("studentName") String theName, Model model) {

    // ...
}
````

First off, I'll actually change the actual request mapping.
So instead of `ProcessFormVersionTwo` we'll call it `ProcessFormVersionThree`.
And then I'll also change the actual method name here to make the method name different.
So I'll call it `processFormVersionThree`.
Allright, so those are the basic mods I wanted to make here for this one.
So again, a new request mapping `ProcessFormVersionThree`
with the new method name of `processFormVersionThree`.
So what I'm going to do here is change the actual parameters coming into this actual method.
So instead of passing in the `HttpServletRequest`, I'm actually going to make use of that
request binding annotation parameter.
So I'll use `@RequestPAram`, and I give `studentName`.
So I'm basically saying I want to read in the HTML form field coming in the request `studentName`,
and I'm going to assign it or bind it to `theName`.
Allright, so again, we're basically going to make use of this spring annotation.
So it's going to read the `HttpServletRequest`, and read that form parameter, `studentName`,
and bind it to this param we have coming into this method called **String** `theName`.

````java
@RequestMapping("/processFormVersionThree")
public String processFormVersionThree (@RequestParam("studentName") String theName, Model model) {

    // String theName = request.getParameter("studentName");

    // convert the data to all caps
    theName = theName.toUpperCase();

    // create the message
    String result = "Hey My Friend from v3! " + theName;

    // add message to the model
    model.addAttribute("message", result);

    return "helloworld";
}
````

We no longer need this code.
**Spring** will automatically do this code for us.
So by effectively adding that `@RequestParam` behind the scenes **Spring** is doing everything.
Allright, so we don't need that. 
So that's good to go.
So we already have this, `theName` coming in already as a parameter.
So we're good to go, so there we go, **String** `theName`.
Now here we make use of it a little bit later on.
We simply take it and convert it to all caps.
And I'm simply going to change the message here.
So instead of saying `Yo!`, then, I don't know, make something else up.
`Hey My Friend from v3!`.
Allright, so I simply just wanted to create a new message here,
just to make it unique and distinct from the previous one 
that we're working on for version two.
Allright, so here's our v3 message, and then everything else stays the same here.
We simply add it to the model and we `return helloworld`.
So that's basically it.
So I have a new handler method here in my `HelloWorldController` called `processFormVersionThree`.
We make use of the `@RequestParam` for annotation parameter binding.
So I need to take this actual path here and I need to update my HTML form.
I'll choose copy:

`````html
<!DOCTYPE html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

    <!-- <form th:action="@{/processForm}" method="GET"> -->
    <!-- <form th:action="@{/processFormVersionTwo}" method="GET"> -->
    <form th:action="@{/processFormVersionThree}" method="GET">

        <input type="text" name="studentName" placeholder="What's your name?" />
        <input type="submit" value="Submit"/>
    </form>

</body>
</html>
`````

and then I'll go back to the form, and I update this.
So instead of version two and do a paste for version three.
Allright, so I just want to make sure that my form is pointing to the right code that I want to execute here.
And let's go ahead and run our application.
And now this form's been updated to point to version three,
so I'll just enter some student name here.
Just enter the name of `Mary`, hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image17.png" alt="image17">
</div>

And now the message, `Hey My Friend from v3`, and then `MARY` in all caps.
So we know that it really is reading our new code for version three,
and it's making use of the `@RequestParam` binding.
So anyway, just wanted to show you an example of using that `@RequestParam`
and how it works out for reading form data.

</div>

### [Spring MVC GetMapping and PostMapping]()
<div style="text-align:justify">

In this section, we're going to learn about `@GetMapping` and `@PostMapping`.
Now, when you send data over to a **Spring MVC controller**,
you normally have an **HTML** form. 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image18.png" alt="image18">
</div>

You send over a request, the controller will process it and send you back a response.

<table style="text-align:justify">
    <thead>
        <th>Method</th>
        <th>Description</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>GET</strong></td>
            <td>Requests data from given resource</td>
        </tr>
        <tr>
            <td><strong>POST</strong></td>
            <td>Submits data to given resource</td>
        </tr>
        <tr>
            <td><i>others</i></td>
            <td>...</td>
        </tr>
    </tbody>
</table>

Now, these are the most commonly used **HTTP** methods.
You can send over a **GET** request and also a **POST** request,
and they have other methods out there.
But we'll see the differences between **GET** and **POST**
since they're the most commonly used.

````html
<form th:action="@{/processForm}" method="GET" ...>
    ...
</form>
````

So to send over data with the **GET** method,
you set up your form, and the key item here, you say method equals **GET**.
So in that case, when the form data's submitted,
it's actually appended to the end of the URL as named value pairs.

`````text
theUrl?field1=value1&field2=value2...
`````

So you'll have your URL with a question mark,
and then you'll have `field1`, `value1`, and `field2`, `value2`, and so on.
And you may have seen this on the web, if you go to different websites,
you'll see this big long URL at the top of your browser location bar.
They're actually sending data back and forth using the **GET** method.

````java
@RequestMapping("/processForm")
public String processForm(...) {
    ...
}
```` 

Now, to handle a form submission, 
as we've seen already in our Spring MVC code,
we simply set up an `@RequestMapping`, and you give the actual path.
Now, with this simple version that we have here,
it actually handles all the **HTTP** methods.
So this mapping will handle **GET** request, **POST** request and so on.
And depending on your application structure, this may be fine,
or you may want to be able to constrain or limit the actual methods that a given mapping supports.

````java
@RequestMapping(path="/processForm", method=RequestMethod.GET)
public String processForm(...) {
    ...
}
```` 

So here's a scenario on how we can constrain the request mapping.
In this case, we want this mapping to only handle **GET** requests.
So here we say `@RequestMapping`.
We get `path="/processForm"` comma `method=RequestMethod.GET`.
So again, with this little item here, this mapping will only handle **GET** requests.
Any other requests being sent to this method will get rejected.

````java
@RequestMapping("/processForm")
public String processForm(...) {
    ...
}
```` 

So instead of typing out all that long information, you simply use `@GetMapping`.
Again, it'll only handle **GET** requests,
and you simply give the path that you want to map it to.
And that's basically it.
So it's just a shorthand way of making use of this **GET** mapping.

````html
<form th:action="@{/processForm}" method="POST" ...>
    ...
</form>
````

Now let's take a look at sending data with the **POST** method.
So here we set up our form, action equals `"@{/processForm}"`, and then method `"POST"`. 
So in this case, the form data's passed in the body of the **HTTP** request message.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image19.png" alt="image19">
</div>

So here's a diagram.
So you have your form sending the data across,
and the request message going across, you have your request headers, and then you have the body.
That's where the actual form data resides when you send it over using a **POST** method.

````java
@RequestMapping(path="/processForm", method=RequestMethod.POST)
public String processForm(...) {
    ...
}
```` 

Now, we can also constrain a request mapping for **POST**.
So here again, I simply say `@RequestMapping`.
I give the path comma method equals `RequestMethod.POST`.
So again, this will only handle **POST** methods.
Any other request type coming across will get rejected.

````java
@PostMapping("/processForm")
public String processForm(...) {
    ...
}
```` 

So instead of typing out all that long information, 
you simply use `@PostMapping` and you simply give the path.
And they just make it very simple and easy to set up this configuration.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image20.png" alt="image20">
</div>

Allright, so I showed you how to send data over using **GET** and also **POST**.
You may wonder, well, which one should I use?
Well, I like to say that **GET** is good for debugging
because you can see everything on the URL.
You can also bookmark and email the URL.
However, with **GET** requests, there's limitations on data lengths.
So depending on the browser,
they may truncate some of the data.
So I say in general, you're safe with using about 1000 characters.
Anything beyond 1000 characters,
then you may want to look at using the POST method.
The **POST** method, you can't bookmark or email the URL.
But the really nice thing about **POST** method is that there's no limitations on data length.
So if you have a very large form or very large piece of data you need to send across,
you can make use of **POST**.
Also, another benefit here is that the **POST** method can also send binary data.
So if you need to do like a file attachment or a file upload,
you can make use of the **POST** method for that.
So those are kind of the pros and cons of each one.
And play around with it, see which one works best for your application scenario.

And now, we're going to demo **GetMapping** and **PostMapping**
and see the differences between the two.
And we'll also break things on purpose.
Alright, so starting here with `/showForm`,
and that's the process of actually showing the initial form, of course.
Let's go ahead and move into our `HelloWorldController`,
and just take a look at the coding for this.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    //@RequestMapping("/showForm")
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    // ...
    @RequestMapping("/processFormVersionTwo")
    // ...
    @RequestMapping("/processFormVersionThree")
    // ...
}
````

So we have this request mapping for `/showForm`,
and this will actually support any **HTTP** requests.
So it'll support **GET**, **POST**, and so on.
And what I'd like to do is change this to support `@GetMapping`.
By making this update will only support get requests to this given request mapping.
Alright, let's go ahead and run this and test it out.
And swing over to our web browser.
So in our web browser, whenever we give our given endpoint,
or URL, like in this case `localhost:8080/showForm`,
so when you type in a URL in the browser bar and submit,
it'll always submit a get request.
Let's go ahead and do this.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image21.png" alt="image21">
</div>

And this works fine.
Because in the background our actual request mapping is set up for a `@GetMapping`.
But now, let's break it.
So in our controller code we're going to change the `@GetMapping` to a `@PostMapping`.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    //@RequestMapping("/showForm")
    //@GetMapping("/showForm")
    @PostMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    // ...
    @RequestMapping("/processFormVersionTwo")
    // ...
    @RequestMapping("/processFormVersionThree")
    // ...
}
````

So we're going to kind of break it on purpose.
So I'm here in my controller code.
I'll change the `@GetMapping` to a `@PostMapping`.
And remember here, when we use `@PostMapping`,
this will only support **POST** request.
Now let's go ahead and run our application.
And in our URL bar, remember it'll always submit a **GET** request,
but now we get this ugly error:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image22.png" alt="image22">
</div>

This white label error page.
But now since we know some information,
we can actually kind of decipher and debug this.
It says there was an unexpected error, method not allowed, status 405.
Here it says method **GET** is not supported.
Because in our controller code for this given URL,
we said that this is for **PostMapping**,
but the browser's sending over a **GetMapping**.
And remember with **PostMapping**, we only support **POST** requests.
So that's the reason for this error.
So the inner workings going deep as far as 
how the request mappings are set up here for **GetMapping** and **PostMapping**.
We simply just change it back, in this case, to a **GetMapping**.
Alright, we've made that change.
This works out fine because the browser's going to send over a **GET** request,
and our controller code has support for a **GetMapping**.
And let's take a look at this `helloworld-form.html` that we currently have in place.

````html
<!DOCTYPE html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

    <form th:action="@{/processFormVersionThree}" method="GET">

        <input type="text" name="studentName" placeholder="What's your name?" />
        <input type="submit" value="Submit"/>
    </form>

</body>
</html>
````

So we have the path for `/processFormVersionThree`,
and then we're sending over **GET** request.
Now let's swing over to our controller code,
and let's move down here to this `@RequestMapping` for `processFormVersionThree`.

````java
//@RequestMapping("/processFormVersionThree")
@GetMapping("/processFormVersionThree")
public String processFormVersionThree (@RequestParam("studentName") String theName, Model model) {
    // convert the data to all caps
    theName = theName.toUpperCase();

    // create the message
    String result = "Hey My Friend from v3! " + theName;

    // add message to the model
    model.addAttribute("message", result);

    return "helloworld";
}
````

And then we know with the `@RequestMapping`
it supports any type of request, **GET**, **POST**, etc.
What I'd like to do is modify the code to only support **GET** request.
And so this will match up with what we have in the form,
and also what we have in the actual controller code.
Alright, let's go ahead and run our application,
and we have our show form set up.
Let's go ahead and add some data here.
Any student name, I'll enter `Anil` and hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image23.png" alt="image23">
</div>

And success, so this works out just fine.
Our form has a method of **GET** and our controller code has a `@GetMapping`
so this works out just fine.
So we have `ANIL` coming across and that message.
And now notice here that the form data,
since it's a **GET** request, 
the form data is added to the end of the URL as name-value pairs.
In this case, our form only had one field
but notice here that it's appended to the end of the URL.
The question mark, field1, value1.
So here the field is `studentName` and the value is `Anil`.

And now let's go ahead and play around with this a bit,
and let's go ahead and break our application again.
So in the HTML form, we're going to change the method from **GET** to **POST**.
So I'll go ahead and open up my HTML form.

````html
<!DOCTYPE html>
<html xmlns:th="http://www.thymleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

    <!-- <form th:action="@{/processFormVersionThree}" method="GET"> -->
    <form th:action="@{/processFormVersionThree}" method="POST">

        <input type="text" name="studentName" placeholder="What's your name?" />
        <input type="submit" value="Submit"/>
    </form>

</body>
</html>
````

So for this `processFormVersionThree`, I'm going to change it from **GET** to **POST**.
Let's go ahead and run our app one more time.
I'll move back to the form, just do a reload on the form.
I'll enter the student's name:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image24.png" alt="image24">
</div>

and boom, it blows up that scary page.
But again we have some knowledge,
we know how to kind of debug or decipher this ugly stack trace.
Here it says there was an unexpected error type method not allowed, status 405.
Method **POST** is not supported.
And now again with our form we're sending over method **POST**
but in our controller code it says `@GetMapping`.
So that's the root cause of this given issue.
And we can easily fix this.
One approach is by simply going back into our controller code
and then changing the mapping to the appropriate type.

````java
//@GetMapping("/processFormVersionThree")
@PostMapping("/processFormVersionThree")
public String processFormVersionThree (@RequestParam("studentName") String theName, Model model) {
    // convert the data to all caps
    theName = theName.toUpperCase();

    // create the message
    String result = "Hey My Friend from v3! " + theName;

    // add message to the model
    model.addAttribute("message", result);

    return "helloworld";
}
````

So I'll fix it by making use of `@PostMapping`.
And then this should work out just fine,
because our form is sending over method **POST**.
Now our controller code is set up for **POST**.
Run the app again.
Swing back over to the browser enter the student name:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image25.png" alt="image25">
</div>

and success.
Because our form is sending data over using a **POST**,
and our controller code is making use of **POST**.
So this is really good.
And now also the form data is processed, but notice since we're using a **POST**
the data is not appended to the URL.
The data's actually sent in the HTTP request message body.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image19.png" alt="image26">
</div>

And here's a little screenshot that we saw in some of the earlier sections.
So notice here for the request the data goes across inside the actual request body.
The nice thing here is that you can actually view the form data using web browser developer tools.
And I'll show you how to do this in the Firefox browser.
So I'll simply move back over to my form, and then I'll open up the inspector.
So there's a number of tabs here for different types of dev tools.
What I'll do here is I'll select the tab for `Network`.
So that'll allow me to view all the network data
that's going across between the browser and the server.
And now what I'll do is I'll actually move to the top,
and I'll fill out my form.
So I'll enter the student name and I'll hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image27.png" alt="image27">
</div>

And up top I have my normal **HTML** form like we've seen already.
The bottom section here at the bottom this actually shows you the actual `network` information.
So I can choose that URL that I submitted data to `processFormVersionThree`.
And they give you information about the request URL, the request method,
and then the actual `request` is the request body.
So that's the form data that goes across in the `request` of that **HTTP** request.
And notice here we have student name and the actual value of `Anil`.
So again, when you are using **POST** the data goes across in the **HTTP** request message body.
So you won't see it on the URL it's actually here in the message body or the request.
So this is a good debugging tool for you if you want to look at some of that data.
Now for other browsers simply just go online and do a Google search.
So you can use these search terms here.
Chrome, how to view HTTP request, a similar thing for Microsoft Edge\
and they'll give you the appropriate steps and details on how to move through
and view the different developer tools and the **HTTP** request data.
</div>

### [Spring MVC Form Data Binding]()
### [Text Fields]()
<div style="text-align:justify">

In this section, we're going to cover the Spring MVC form tag,
and we'll build some text fields.
Now, just a quick review on the HTML forms.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image28.png" alt="image28">
</div>

We make use of HTML forms to get input from the user.
In the earlier sections, we saw a very basic example of an **HTML** form.
Now we'll kind of flesh it out.
We'll add more form elements like text fields, radio buttons, and so on,
and we'll cover that over the next series of sections.

Now **Spring MVC** forms can make use of data binding,
and so, data binding is the process of automatically
setting and retrieving data from a Java object or a bean.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image29.png" alt="image29">
</div>

Now, let's take a look at the big picture here.
We'll start with the student form, it has a first name and a last name.
The user will hit `submit`, this student object goes across to the **Student Controller**,
and then we actually send that over to the confirmation page,
and so, we send that student object over to the confirmation page where we could say,
"_Hey the student's confirmed. Here's the first name and the last name, John Doe._"
So, the key here is that instead of making use of individual request parameters individually,
we can actually manage this form data as a Java object.

Now, showing the form in our **Spring Controller**,
before we can show the form, we must add a **model attribute**,
and this is a bean that's going to hold the form data for the **data binding**.
So, this bean here is really just a Java class that we'll create an instance of,
and we'll add it as a **model attribute**.

````java
@GetMapping("/showStudentForm")
public String showForm(Model theModel) {
    theModel.addAttribute("student", new Student());
    return "student-form";
}
````

Now, here's a little code snippet from the controller for showing the `student-form`.
Here we have this method, `showForm`, we pass in `theModel`,
and then we make use of `theModel.addAttribute`,
we give the attribute name of `"student"`,
and we give the actual value here, or the bean in this case,
it's just a new instance of the **Student** class.
And then finally we return `"student-form"`,
that's a reference to the actual **HTML** page.

````html
<form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
    First name: <input type="text" th:field="*{firstName}" />
    <br><br>
    Last name: <input type="text" th:field="*{lastName}" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
````

And now, setting up the **HTML** form to make use of data binding,
we make use of some **Thymeleaf** tags here,
and we have the normal `th:action` for `/processStudentForm`.
We have the `th:object` of `${student}`.
Now this is really important right here,
this is the actual name of the **model attribute**.
So you reference it as `th:object`,
and you put the name of the model attribute inside the `${}`.
Very important that you follow this syntax
because that's how **Spring MVC** and **ThymeLeaf** work together.
And then, also, this name of student is very important 
because this is the actual attribute name. 
So, in that previous code,
where we set up the controller for showing the form,
we had `theModel.addAttribute("student")`,
well, for the actual form,
you actually need to use that same name.
And you can actually use any name here,
as long as you're consistent in your application.
So, it doesn't have to be `student`,
it could be called `tempstudent`, `thestudent`,
or any other name that you'll want.
You simply have to stay consistent.
Allright, so that covers the `th:object`, our model attribute name.
Then, we make use of our form field `first name`,
and we have `th:field`,
so referencing a **ThymeLeaf** form field here,
and we give the `*{firstName}`.
So this `*{}` is really just a shortcut syntax for `${student.firstName}`.
So, note the difference here.
It's a `*{}`.
You simply give the shorthand or the actual field name
without having to give the full `object.fieldName`.
And then we do a similar thing here for `last name`.
So again, the `*{}`, that shortcut syntax for the `${}`.

Now, what happens behind the scenes?
Well, when the form is loaded,
the fields are going to be pre-populated with data from that model attribute.
So, in this case here, where we have this `first name`,
and we have this input type of text, `th:field` of first name, 
what will happen behind the scenes is that when the form is loaded,
**Spring MVC** will read the student from the model,
and then it'll call `student.getFirstName`, `student.getLastName`.
So basically, what it'll do here is actually make use of that field reference
that you have here on the form, and then it'll make a method call based on that.
So it'll make that method called `.getFirstName`.
A similar thing here for the `last name` but called `.getLastName`.
And that all happens behind the scenes with **Spring MVC** and **Thymeleaf** working together.
And then, the main use case for this, real time,
is that you can use this to actually pre-populate a form.
Say, for example, you're reading a student from a database,
you can pre-populate the form 
and allow the user to actually edit or modify that given entity,
and we'll actually do this later in the course
when we move into **Spring MVC** and **CRUD** examples,
you'll see some realtime, real world example code of actually reading data from the database,
and then pre-populating the forms.

````html
<form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
    First name: <input type="text" th:field="*{firstName}" />
    <br><br>
    Last name: <input type="text" th:field="*{lastName}" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
````

And now behind the scenes, when the form is submitted,
it'll actually call the setter methods.
So when the form is submitted, **Spring MVC** will create a new **student** instance,
and then add it to the **model**, then it'll call `student.setFirstName`,
and also it'll call `student.setLastName`,
and then it'll pass that appropriate data over to the actual controller method.

````java
@PostMapping("/processStudentForm")
public String processForm(@ModelAttribute("student") Student theStudent) {
    
    // log the input data
    System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
    return "student-confirmation";
}
````

Now, moving to that controller method, 
this is how we can actually handle the form submission in the controller.
So we have this method here, `processForm`,
takes in that model attribute of **Student**,
and we have student, `"theStudent"`.
And then, once we have it, then we can do anything that we want to it.
In this example, I'll keep it real simple,
I'll just simply log the input data by doing a `System.out.println`.
I'll say the `student.getFirstName`, `student.getLastName`,
but really we could do anything we wanted to with this object at this time.
We could maybe insert it into a database, make a rest API call, whatever you'd like to do.
And we'll get into more advanced examples of this later in the course.
But this is just kind of the basics right now, just to kind of get us going.

````html
<html>
<body>
    The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />
</body>
</html>
````

And then, finally, with this whole wrap up here,
our last stop here is the actual confirmation page.
And so, here we'll say `the student is confirmed`,
and we make use of this **Thymeleaf** `th:text`,
and then we give the `${student.firstName}`,
that'll actually call `student.getFirstName()`,
and then a similar thing here for `${student.lastName}`,
that'll actually call `student.getLastName()`.
And then that'll give us this output here, `The student is confirmed: John Doe`.
It's the first name and the last name.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image29.png" alt="image30">
</div>

Allright, so kind of stepping back here, there's a lot of information.
Lets kind of pull it all together, so we can see it on one screen here.
So we start with the student form.
They fill out their first name and last name, they hit submit, it goes across,
we have this object here called **Student**,
goes to the controller, we can process on it, log it, and so forth,
and then we can send it over to the actual confirmation page,
and we can get that student confirmation.
Allright, so that's the big picture.
What's the development process?

* Create the student class
* Create the student controller
* Create the HTML page
* Create the processing form
* Create the confirmation page

Alright, so let's go ahead and move into our IDE
and let's get started with our development process.
And the first thing that we'll do is, we'll start off by creating our **Student** class.
And actually, I have one intermediate step that I need to do.
I want to go ahead and create a package called `model`.
This is where we'll place the actual **Student** class.
Allright, so we have our new package.
Now step one, let's go ahead and create the **Student** class,
and we'll create it in this `model` package.

````java
package com.luv2code.springboot.thymeleafdemo.model;

public class Student {

    private String firstName;
    private String lastName;

    public Student() {
    }

    // getters and setters
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
}
````

This student is going to have two fields.
It's going to have a `firstName` and a `lastName`.
And I'll also set up a no argument constructor here for our **Student**.
Now what I need to do is actually generate some getter and setter methods for this student.
We have `getFirstName`, `setFirstName` and `getLastName`, `setLastName`.
Alright, so that's basically it here for this **Student** class.

Moving over to step two is creating a **StudentController** class.
So that's our **Spring MVC** controller.
And again, as you would expect, the name here will be `StudentController`.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        return "student-form";
    }
}
````

So up top, we basically say it's `@Controller`.
So the first thing we need to do here
is actually create a method for actually showing our form.
So I'll go ahead and give the method name `showForm`.
It's going to take a parameter for `Model theModel`.
And I'll just go ahead and do a quick return here.
I'll just say `return "student-form"`.
So creating the Student, we simply say `Student theStudent = new Student()`.
Again, very simple Java coding there.
Now we need to add this to the model.
So I'll say `theModel.addAttribute`.
I'll give the actual name of the attribute,
and the actual value for the attribute.
So we create the **Student** object,
and then we add it as an attribute given the name of student
and then the value of `theStudent`.
Alright, so that's basically it here for this `showForm` 
that we simply create our object add it to the model,
and then we simply do a return over the `"student-form"`.

Now, the next step is we want to create the HTML form.
This form will have our input fields and our submit button.
Let's move into our template directory, and let's create a new file.
The name for this file is `student-form.html`.

`````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

</body>
</html>
`````

Let's add the XML namespace for **Thymeleaf**, and then let's update the title,
and then we'll set up the heading for student registration form.

`````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    <form th:action="@{/processStudentFrom}" th:object="${student}" method="POST">
        
    </form>

</body>
</html>
`````

And now setting up the HTML form to make use of data binding, 
we make use of some **Thymeleaf** tags here,
and we have the normal `th:action` for `processStudentFrom`.
We have the `th:object` of dollar sign curly brace student(`${student}`),
and also this name of student is very important,
because this is the actual attribute name in the `showForm` method of the `StudentController`.
Well, for the actual form, you actually need to use that same name.

`````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    <form th:action="@{/processStudentFrom}" th:object="${student}" method="POST">
        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />
        <br><br>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
`````

Then we make use of our form field `firstName` and we have `th:field`,
so referencing a **Thymeleaf** form field here,
and we give the star curly brace `firstName`.
So this star curly brace(`*{}`) is really just a shortcut syntax for`${student.firstName}`,
so note the difference here.
You simply give the shorthand of the actual field name
without having to give the full `object.fieldName`.
So that's the key there.
And then we do a similar thing here for last name.
And add our input type for our `submit` button.
So that's actual button the user will click to send the data over,
and now that's in place.
What will happen behind the scenes is that when the form is **loaded**,
**Spring MVC** will read the student from the model,
and then it'll call `student.getFirstName()`, `student.getLastName()`,
and that all happens behind the scenes with **Spring MVC** and **Thymeleaf** working together.
And now behind the scenes when the form is **submitted**, it'll actually call the setter methods.
So when the form is submitted, **Spring MVC** will create a **new** student instance, 
and then add it to the model.
Then it'll call `student.setFirstName()`,
and also it'll call `student.setLastName()`,
and then it'll pass that appropriate data over to the actual controller method.

Alright, now let's go ahead and take care of step four, creating the form processing code.
So that's code that's back in our controller,
so I'll move back over to our `StudentController.java`, and I need to go through
and actually define a new method here.

````java
@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    // ...
    
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        
        return "student-confirmation";
    }
}
````

I'll set up a `@PostMapping`, and I make use of `/processStudentForm`.
That's the same name that we used in the actual HTML form.
Now I set up the process form, I set up `@ModelAttribute("student") Student theStudent`,
and remember, they're going to actually bind that model attribute to the parameter being passed in.
And I simply do a return on `"student-confirmation"`.
And also just as a reminder here on that `@ModelAttribute`, so when they send it over,
that form data's going to populate this `"student"` object, and then we can access it.
So in the form, you have the model attribute of student.
Here in our code, we use the same name with the same case.
And **Spring** does all the work behind the scenes of populating 
that student object with all the data, so there's no need for us to manually do `request.getParameters`.
**Spring** will just push everything in there.
That's a nice feature of using **Spring MVC**.
Allright, so I'm going to go ahead and do my `System.out.printf`.
I just want to print out some of the data that came in,
just to make sure that **Spring** actually did populate it,
make sure it's doing its work as advertised.

````java
@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    // ...
    
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        
        // log the input data
        System.out.printf("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        
        return "student-confirmation";
    }
}
````

So here, I'll say `theStudent.getFirstName()`, give myself a white space, and then I say `theStudent.getLastName()`.
So again, I just wanted to print out the information to the console, 
just to make sure the controller gets the data, and they can make use of it accordingly.
Allright, so that's basically the code there for processing the form data.

So our final step here is creating a confirmation page.
So we simply want to display the information on the webpage as far as what the user entered.
So we'll create this file here called `"student-confirmation"`.
And actual name of the file, I'll call it `student-confirmation.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Confirmation</title>
</head>
<body>

    <h3>Student Confirmation</h3>
    The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />
    
</body>
</html>
````

And actually what I want to do is copy some of the basic HTML stuff from our other `student-form.html`.
So I'll just copy all the `DOCTYPE`, `header`, `body` stuff.
So that's the basics there.
And instead of "`Student Registration Form`",
I'll say `"Student Confirmation"`.
Now I simply want to give the confirmation message here.
So I'll say, `The student is confirmed`
and basically, I need to get the student's first name and last name.
So I use the dollar sign curly brace, `${student.firstName}`,
and then the space with dollar sign curly brace, ${student.lastName}.
So again, remember here, first one will simply call `student.getFirstName`,
and likewise, other one will call `student.getLastName`.
I think we're at the point where we can actually go through and test out the application.
And let's go ahead and swing over to our web browser.
And let's enter the URL, `localhost:8080/showStudentForm`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image31.png" alt="image31">
</div>

And now let's go ahead and enter information for our student;
first name of `John`, last name of Doe, and hit the `submit` button.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image32.png" alt="image32">
</div>

Cool, so there's our confirmation page, `The student is confirmed.`
Oh, and also, let's take a peek here at our output for our controller.

````text
.   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.4)

2024-10-23T08:14:27.236+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] c.l.s.t.ThymeleafdemoApplication         : Starting ThymeleafdemoApplication using Java 23.0.1 with PID 34128 (D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\01-thymeleafdemo-helloworld\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\01-thymeleafdemo-helloworld)
2024-10-23T08:14:27.236+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] c.l.s.t.ThymeleafdemoApplication         : No active profile set, falling back to 1 default profile: "default"
2024-10-23T08:14:27.345+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-10-23T08:14:27.345+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-10-23T08:14:27.345+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.30]
2024-10-23T08:14:27.356+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-10-23T08:14:27.356+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 118 ms
2024-10-23T08:14:27.407+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-10-23T08:14:27.413+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-10-23T08:14:27.415+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] c.l.s.t.ThymeleafdemoApplication         : Started ThymeleafdemoApplication in 0.191 seconds (process running for 37.77)
2024-10-23T08:14:27.417+03:00  INFO 34128 --- [thymeleafdemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
2024-10-23T08:15:29.372+03:00  INFO 34128 --- [thymeleafdemo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-10-23T08:15:29.372+03:00  INFO 34128 --- [thymeleafdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-10-23T08:15:29.372+03:00  INFO 34128 --- [thymeleafdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
theStudent: John Doe
````

So when we log that information, it's showing up right here in the console window for that controller output.

</div>

### [Drop-Down Lists]()
<div style="text-align:justify">

In this section, we'll cover Spring MVC forms and the dropdown list.
Now here's a quick review of the HTML `<select>` tag for building a dropdown list.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image33.png" alt="image33">
</div>

Normally on an e-commerce checkout page, you can select your country for your address,
and you can just choose an item there from the dropdown list.
Now, here's the actual **HTML** code, and it's just regular **HTML** code 
that you would use for setting up a dropdown list or a `<select>` tag.
So here we have select, we give the name of the field, country, and then we give a list of options.
So for the different countries, Brazil, France, Germany, India, and so on.
Now, this `<select>` tag here, you have the `option`, then you have something called `value`.
That's the actual value that's sent during form submission, and then you have the actual text.
That's what's actually displayed to the user.
Now, normally this information is the same, but it gives you the flexibility to change things up.
So for example, here to use the country code like BR, FR, so on, but it's totally up to you.
But this is kind of the basic layout here for setting up a dropdown list.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image34.png" alt="image34">
</div>

Now with **Thymeleaf** and a `<select>` tag, it's very similar to what we've done so far.
We have select we give `th:field`, and we give `*{country}`,
so that's the actual name of the property on our backend class or our `student.java` class.
And then we give a list of the options here.
So we have Brazil, France, Germany.
Notice here we have option `th:value` for **Thymeleaf**,
and then the actual text to display to the user, Brazil.
And we just repeat the process for X number of countries.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image35.png" alt="image35">
</div>

And then it's kind of the same process that we've done before.
So we'll have the student form, we'll submit the data, it'll go across to the controller,
and then over to our confirmation page.
Now, our development process, again, very similar.

* Update our HTML form - add this new dropdown list using a `<select>` tag.
* Update our student class - add getters and setters for our new property for country
* Update the confirmation page to display the country that the user selected.


Allright, so let's start with step one of updating our `student-form`.
And then I'll move down here in the form.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    <form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />
        <br><br>
        Country:
        <select th:field="*{country}">
            <option th:value="Brazil">Brazil</option>
            <option th:value="France">France</option>
            <option th:value="Germany">Germany</option>
            <option th:value="India">India</option>
        </select>
        <br><br>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
````

I'll add a label here for country.
And now I'll go ahead and set up my `select` tag.
And I'll set up the `th:field` and then we'll make use of the star curly brace,
and we referenced the property on our Java class, so that's **country**.
And now we need to give some options here for the user to select
or a list of countries, basically, for the user to select.
We'll make use of this option, `th:value`.
We give the country name **Brazil**, and then also **Brazil**.
And remember, the `th:value` is the actual value
that'll be sent over once we submit the form,
and then we also have the information that actually be displayed to the user.
Now, let me just go ahead and copy, paste this X number of time for some of the other countries,
and then I'll just make a quick update here for **France**, **Germany**, and **India**.
Allright, so that's basically the coding there for our select tag or our dropdown list of countries.

And now we need to do step two of updating our **Student** class.
We need to add a getter/setter method for this new property, **Country**.
Allright, so we're inside our **Student** class here.

````java
package com.luv2code.springboot.thymeleafdemo.model;

public class Student {

    private String firstName;
    private String lastName;
    private String country;

    public Student() {
    }

    // getters and setters
}

````

First off, we'll define a field, `country`,
and then we'll also define our getter/setter methods.
We have `getCountry` and `setCountry`.

Now in step three here, we need to update our confirmation page
to simply display the country that the user selected.
I'm inside `student-confirmation.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />
    <br><br>
    Country: <span th:text="${student.country}" />

</body>
</html>
````

I'll just add a little `br`, br for line break.
I'll give the `Country`, and then I'll just grab this `span` here for displaying the actual country here.
Here, I'll give `${student.country}` and that'll call the appropriate getter method on that student object.
So that'll actually display the country that the user selected.
Alright, so we're ready to run this application and test it out.
And now let's just swing over to our web browser.
We'll go to `localhost:8080/showStudentForm`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image36.png" alt="image36">
</div>

And let's go ahead and take a look at our dropdown list and good.
So we have items there for our dropdown.
We can go ahead and enter our student information,
go through and select the country here, and then hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image37.png" alt="image37">
</div>

And excellent.
So here, `country` and the user selected `Germany`.
So we have our dropdown list working.
We can submit form data and then see the actual confirmation or display that information on the page.

Now, one thing that you may have noticed is that our list of countries are hard coded.
So, in our actual **HTML** form, we hard code those values.
Wouldn't it be great if we could read that information from a properties file?
So, that's what we'll do here now.
And so, to add this new feature, here's our basic development process.

* Add a list of countries to the `application.properties` file
* Inject the countries in the `StudentController` using the `@Value` annotation.
* Add the list of countries to the model
* Generate a list of option tags for the countries in the HTML form

Let's swing back over to our IDE.
And let's go ahead and open up our properties file.
So, in this properties file actually define the list of countries.

````properties
countries = Brazil,France,Germany,India,Mexico,Spain,Turkey,United States
````

And we can simply just give a name value pair here.
So, I will say the name of this property is called `countries`.
You can give any name that you want.
And I'll simply give a comma delimited list of countries.
I'll add some additional countries just so we know
that we're actually getting it from the properties file.
You can add as many countries there if you'd like.
Be sure to add your country in the list.
So, that's in our properties file.
And now, we need to move over to our controller,
and we can actually inject the data from that props file into our controller.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class StudentController {
    
    @Value("${countries}")
    private List<String> countries;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {
        // ...
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        // ...
        return "student-confirmation";
    }
}

````

First off, I'll define a field called `countries`.
It's a list of strings.
Be sure to choose import from `java.util.List`.
And now, to actually inject this from springs `application.properties` file,
I can make use of the **value** annotation that's used for injecting data from the properties file.
So, I'll make use of the value annotation
and inside of quotes, I use the dollar sign, curly brace,
and I give the actual name of the property that I'm going to inject.
So, here, from our props file that was called `${countries}`, that was the name of the property.
And be sure that this is in double quotes.
And also, dollar sign, curly brace, very important.
And what this will do is since it's a comma delimited list,
it'll simply split those items up based on that comma,
and then it'll add it to this list of strings here or this list of countries.
Now, we can move down to this `showStudentForm`.
And what we'll do here is we'll actually add that data to the model.
And then, once it's in the model, then the actual form can use it.

````java
@GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);
        
        // add the list of countries
        theModel.addAttribute("countries", countries);

        return "student-form";
    }
````

Here, I'll say `theModel.addAttribute`.
Give the name value pair.
So, for the name of this model attribute, I'll call it `countries`.
Then, I give a reference to `countries`.
That's the actual list that we just created up top.
Allright, so that's in the model.
And then, our form can actually use this.
So, let's go ahead and move over to our form now.

Now, our form's going to be a little different.
So, instead of having the data hard-coded, instead what we'll do is we'll actually use the data from the model.
And then, we'll actually loop over that data and do all of this work dynamically.
So, I'll go ahead and take this hard-coded stuff and delete it.

````html
Country:
<select th:field="*{country}">
    <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />
</select>
````

And then, I'll make use of this `option`.
And now, here, I'll say `th:each`.
I give `tempCountry : ${countries}`.
And effectively, what this will do is this will actually loop over `countries`
that's add model attribute, that list of strings.
And then, temp countries, the temporary variable as we go through that loop.
And then, we can use that `tempCountry` to actually dynamically set up the value
and the text to display for this given option.
So, we're dynamically looping over this and building this out on the fly.
So, here, I'll say `th:value`.
I'll make use of dollar sign, curly brace, `${tempCountry}`.
And then `th:text`, dollar sign, curly brace, `${tempCountry}`.
So, what we're going to do is we're going to build a list of options dynamically using a for loop.
In a timely world, we make use of a for loop by saying `th:each`.
Here, I have `tempCountry :` and then I give dollar sign, curly brace, `${countries}`.
That's that model attribute that we have, that list of `countries`, that list of strings.
And then, I have `th:value`.
That's the actual value that'll be submitted once they hit the `submit` button.
Here, I make use of dollar sign, curly brace, `${tempCountry}`.
And then the `th:text`, this is what they'll actually display to the user.
For value and text, in this case, they're both the same.
And that's basically the coding here for setting this up.
Again, we're dynamically building a list of countries here based on the information from that model attribute.
Allright, let's go ahead and test this out and let's run it.
We have `showStudentForm`.
Just go ahead and do a reload on this form.
And let's check the country in.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image38.png" alt="image38">
</div>

And, success.
So, we know that it's actually reading all of these countries here from the properties file.
We put it into the controller, we put it into the model.
And then, here, our form can actually use that information
from the model to display those list of countries.
Just go ahead and do a quick test here.
Choose a country, hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image39.png" alt="image39">
</div>

and excellent.
So, this all works out.

</div>

### [Radio Buttons]()
<div style="text-align:justify">

In this section, we'll cover **Spring MVC** forms with radio buttons.
Now, here's a screenshot of a form using radio buttons.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image40.png" alt="image40">
</div>

And you may have seen this before on other websites,
but basically, we'll have this new entry here,
`Favorite Programming Language`,
and the user can choose their favorite language.
And so the radio buttons, `Go`, `Java`, `Python`.
So we'll create this here with the form.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image41.png" alt="image41">
</div>

And now here's the basic coding example for creating a form with radio buttons.
Here we have favorite programming language, and then we set up the input type of radio,
and then we have the `th:field` of favorite language,
and then we have the `th:value` of `Go`, and then finally `Go`.
The `th:field`, that basically binds to the property on the **student** object, `*{favoriteLanguage}`.
And then we have the `th:value`.
That's actual value we'll send over during form submission.
And then finally, the actual information within the element tag input.
That's what's actually displayed to the user.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image35.png" alt="image42">
</div>

Now, kind of pulling this all together, this is kind of like what we've done before.
We'll have the student form, we'll submit the data sends over that student object,
goes to the controller, we can log the information,
and then we send it over to the student confirmation page.

Allright, so our development process.

* Update the HTML form
* Update the student class - add a new getter and setter method for that new property, `favoriteLanguage`
* Update the confirmation page to display that favorite language

So the first thing that we'll do is we'll update our HTML form.
I'll open up my `student-form.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    <form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />
        <br><br>
        Country:
        <select th:field="*{country}">
            <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />
        </select>
        <br><br>
        Favorite Programming Languaage: 
        <input type="radio" th:field="*{favoriteLanguage}" th:value="Go">Go</input>
        <br><br>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
````

And right underneath `country`, I'll grab that `<br>`, the line breaks.
Do a little copy-paste exercise here.
And then I'll enter a label here for `Favorite Programming Languaage:`.
And now, I'll set up this input type using the `radio` button.
I'll set up the `th:field` with a `*{favoriteLanguage}`.
The `th:value` of `"Go"`, for the **Go** programming language.
And then inside the actual form, I'll make use of `Go`.
So the `th:field` that's basically binding to a property on the **student** object.
The `th:value="Go"`, that's sent over during the form submission.
And then the actual information inside the angle brackets here,
that's actually displayed to the user.

````html
Favorite Programming Languaage: 
<input type="radio" th:field="*{favoriteLanguage}" th:value="Go">Go</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Java">Java</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Python">Python</input>
````

And now, I'll just kind of do a copy-paste on this line X number of times for my other programming languages.
And then I'll just go through, and I'll update the value here.
This one's for `Java`.
And the next entry is here for `Python`.
And you can feel free to experiment, add your own favorite programming language in there.
And that's the basic coding here for setting up radio buttons here for this given form.

Now, in step two, we actually need to add the property to the student class, 
that property for favorite lang.
I'll open up the student class here.

````java
public class Student {

    // ... other fields
    private String favoriteLanguage;

    public Student() {
    }

    // ... other getters and setters

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }
}

````

And I'll add this new field for `favoriteLanguage`.
And then I'll do the normal process here of generating getters and setters for this `favoriteLanguage`.
We have our getter setter methods here for favorite language.

Now, for step three, we're actually going to update our confirmation page,
and we'll actually display that favorite language that the user selected.
I'll move in here to `student-confirmation.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />
    <br><br>
    Country: <span th:text="${student.country}" />
    <br><br>
    Favorite Programing Language: <span th:text="${student.favoriteLanguage}" />
</body>
</html>
````

And I'll just copy the section here where I displayed the `country` and copy those `<br>`s.
And then, instead of country, we'll say `Favorite Programing Language:`.
And then I'll simply do an appropriate update here, `student.favoriteLanguage`.
And we know here, it'll call that getter method, `.getFavoriteLanguage`.
Let's go ahead and run our application.
Swinging over to our web browser.
We'll go to `localhost:8080/showStudentForm`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image43.png" alt="image43">
</div>

And this looks good.
We have these radio buttons here at the bottom.
I'll go ahead and fill in information for this student.
I'll choose one of these radio button options here.
Favorite language of `Java`.
I'll hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image44.png" alt="image44">
</div>

And success, there we go.
So we're able to display that actual favorite language that that user selected.
We covered the basics here on making use of radio buttons with our Spring MVC forms.

Well, we had those languages, but they were all hardcoded in our **HTML** file.
Let's update our application to read the list of languages from the props file.
And we know how to do this because we've seen this process before.
So our development process here:

* Add the list of languages to the `application.properties` file
* Inject the languages in the **StudentController** using `@Value`.
* Add the list of languages to the model
* Generate the list of input tags for those languages in the HTML form

Very similar process to what we did in previous sections.
Alright, starting here with step one of adding a list of languages to the `application.properties` file.

````properties
countries = Brazil,France,Germany,India,Mexico,Spain,Turkey,United States
languages = Go,Java,Python
````

And I'll add this new property here just using a name value pair.
We can give any property name that we want, but here I'll just call it languages.
And then I'll simply give a comma-delimited list of languages.
So I'll start with those first three, `Go`, `Java` and `Python`.
I'll also add in Rust and TypeScript.
And go ahead and add some additional languages there that's one of your favorites.

Now in step two we're going to inject the languages in the controller using the `@Value` annotation.
I'll open up my StudentController here.

`````java
@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {
        // ...
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        // ...
        return "student-confirmation";
    }
}

`````

And what I'll do is a little copy-paste exercise.
We did a similar thing already for `countries`,
I'll do a similar thing here for `languages`.
And I'll simply update this for `languages`.
And, remember, this has to match up with the actual name of the property in the `application.properties` file.
So just make sure those items match up accordingly.

Now in step three we're going to add the list of languages to the model.
I'll move down here into the `showStudentForm` method.

````java
@GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

    // create a student object
    Student theStudent = new Student();

    // add student object to the model
    theModel.addAttribute("student", theStudent);

    // add the list of countries
    theModel.addAttribute("countries", countries);

    // add the list of languages
    theModel.addAttribute("languages", languages);

    return "student-form";
}
````

And then I'll say `theModel.addAttribute`.
I give `"languages"` comma `languages`.
And so that's how we add the `languages` to `theModel`.
So, whatever field name we defined up top, 
that's what we use here, and we add it to the model.

For step four in the **HTML** form we're going to generate the list of input tags for the languages.
I'll open up my `student-form.html`.

````html
Favorite Programming Languaage:
<input type="radio" th:field="*{favoriteLanguage}" th:value="Go">Go</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Java">Java</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Python">Python</input>
````

At the moment these values are all hardcoded.
I'll go ahead and delete the hardcoded sections.
And then I'll go ahead and set up my input `type` equals `radio`.

````html
Favorite Programming Languaage:
<input type="radio" th:field="*{favoriteLanguage}" th:each="tempLang : ${languages}" th:value="${tempLang}" th:text="${tempLang}" />
````

And then I'll say th:field, star, curly brace `*{favoriteLanguage}`.
I'll give `th:each`.
And then I'll have `tempLang :` dollar sign, curly brace, `${languages}`.
That's for our for loop.
And then `th:value="${tempLang}"`.
And then `th:text="${tempLang}`.
Allright, so a lot of information here.
So up top here we have the binding for that property on **Student** object for `favoriteLang`.
And then we set up this for loop,
so we're going to loop over the list of `languages`.
We get those languages from that model.
And then `tempLang` is the temporary loop variable that we'll use.
And then we have the `th:value`.
That's what's sent during form submission.
And then the text is what's displayed to the user.
I think we're ready to test this out.
So let's go ahead and run this application.
Swing over to our web browser.
And let's just do a reload on this page.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image45.png" alt="image45">
</div>

And success, this looks good.
So, notice here we're getting all of these additional programming languages,
and it's reading that information from our `application.properties` file.
Let's go ahead and fill out some information here for this user.
And we'll choose a different programming language here like `TypeScript`.
And then we hit `Submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image46.png" alt="image46">
</div>

And success.
So we're able to display that favorite programming language, `TypeScript`.
And remember, all those languages are coming from that `application.properties` file.

</div>

### [Check Boxes]()
<div style="text-align:justify">

In this section, we'll cover **Spring MVC** forms using check boxes.
We'll have this new section here on our form where the user can choose their favorite operating systems.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image47.png" alt="image47">
</div>

And the nice thing about using check boxes is that the user can make multiple selections.
Now, let's take a look at a code example.
It's very similar to what we've done in the previous sections.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image48.png" alt="image48">
</div>

Here, we'll have input `type` equals `checkbox`.
Then we'll set up the `th:field` for `favoriteSystems`, and then the `th:value` of `Linux`.
And then we kind of just repeat that process for the other operating systems
like `Mac` and `Microsoft Windows`.
Now, there's a special case here.
If a value has spaces like `Microsoft Windows`,
then we need to place that in single quotes.
So we still use the double quotes, but then we also place single quotes there
to kind of handle that special case of a space.
And now lets kind of pull it all together like we've done before.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image35.png" alt="image49">
</div>

We have our student form.
We simply submit the data, goes over to the controller
and then goes to the confirmation page.
And taking a look at our development process here:

* Update the HTML form
* Update the student class for the new getter and setter method of that new property for our `favoriteSystems`
* Update our confirmation page

Very similar process to what we did in previous sections.
Alright, starting here with step one of adding a list of languages to the `application.properties` file.

````properties
countries = Brazil,France,Germany,India,Mexico,Spain,Turkey,United States
languages = Go,Java,Python
systems = Linux, macOS, Microsoft Windows
````

And I'll add this new property here just using a name value pair.
We can give any property name that we want, but here I'll just call it `systems`.
And then I'll simply give a comma-delimited list of systems.
So I'll start with those first three, `Linux`, `macOS` and `Microsoft Windows`.
And go ahead and add some additional languages there that's one of your favorites.

Now we'll do in step one is that we'll update our HTML form.
I'll open up `student-form.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    <form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />
        <br><br>
        Country:
        <select th:field="*{country}">
            <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />
        </select>
        <br><br>
        Favorite Programming Languaage:
        <input type="radio" th:field="*{favoriteLanguage}" th:each="tempLang : ${languages}" th:value="${tempLang}" th:text="${tempLang}" />
        <br><br>
        Favorite Operating Systems:
        <input type="checkbox" th:field="*{favoriteSystem}" th:each="tempSys : ${systems}" th:value="${tempSys}" th:text="${tempSys}" />
        <br><br>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
````

And I'll move down here to this section right below `Favorite Programming Languages`.
I'll just do a little copy-paste here on this `<br>`,
and then I'll give our information here for `Favorite Operating Systems`.
I'll simply set up the input `type` of `checkbox`.
`th:field` and I'll bind this to `favoriteSystem`.
I'll give `th:each`.
And then I'll have `tempSys :` dollar sign, curly brace, `${systems}`.
That's for our for loop.
And then `th:value="${tempSys}"`.
And then `th:text="${tempSys}`.

Now, in step two we need to add the property to the **student** class.
I'll move in here into this `student.java`.

`````java
public class Student {

    // ... other fields
    private List<String> favoriteSystems;

    public Student() {
    }

    // ... other getters and setters

    public List<String> getFavoriteSystems() {
        return favoriteSystems;
    }

    public void setFavoriteSystems(List<String> favoriteSystems) {
        this.favoriteSystems = favoriteSystems;
    }
}

`````

And now for favorite systems, they can actually choose more than one.
So we'll actually have a list of strings for `favoriteSystems`.
Allright, so that takes care of the field here for `favoriteSystems`.
Let's go ahead and generate the getter and setter methods.

Now let's go ahead and move to step three of updating our confirmation page.
I'll open up `student-confirmation.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Student Regsitration Form</title>
</head>
<body>

    <h3>Student Registration Form</h3>
    The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />
    <br><br>
    Country: <span th:text="${student.country}" />
    <br><br>
    Favorite Programing Language: <span th:text="${student.favoriteLanguage}" />
    <br><br>
    Favorite Operating Systems: <span th:text="${student.favoriteSystems}" />
</body>
</html>
````

Now move down here to the bottom underneath `Favorite Programming Language`.
And I'll kind of just copy-paste this little section here, and I'll just update the label here.
`Favorite operating systems`, and here I'll say `favoriteSystems`.
Now one thing I want you to notice here is that it's going to just display that list directly.
So it's going to place that information inside of square brackets just by default.
But what I'd like to do is instead of showing it in this fashion,
I'd like to make use of a bullet list or a list of bullets to show each operating system
and show them on separate lines.
So, I'll make a slight modification to make use of a list for that.

````html
Favorite Operating Systems: <!-- <span th:text="${student.favoriteSystems}" /> -->
<ul>
    <li th:each="tempSystem : ${student.favoriteSystems}" th:text="${tempSystem}"></li>
</ul>
````

I want to use `<ul>` and `<li>` html elements for displaying a list. 
So basically, the list item (`<li>`) is the actual bullet item there.
So we simply want to just do a loop over each one of those operating systems here.
So I'll make use of that `th:each="tempSystem`.
And then we will loop over `student.favoriteSystems`.
And then I'll make use of this `th:text`,
and I'll use the dollar sign curly brace temp system, `${tempSystem}`.
So basically, this should generate a collection of list items
for each one of those operating systems as a bullet list. 
So this information at the top this `span` for `favoriteSystems`,
we actually don't need that now.
Alright, so that's the coding there for our favorite operating systems.
Let's go ahead and run it, and let's test it out.
Swing over to our web browser, `localHost8080/ShowStudentForm`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image50.png" alt="image50">
</div>

And this looks good.
So we have a list of favorite operating systems there at the bottom.
Let's go ahead and fill out this form and use some of these fields here.
And I'll choose favorite operating systems, so I can choose more than one.
I'll hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image51.png" alt="image51">
</div>

And success.
So notice here we have our operating systems being listed out, 
and again, we're using those values that come in from the props file, and displaying them here. 

</div>

## [Spring MVC Validation]()
<div style="text-align:justify">

In this section, we're going to learn about **Spring MVC Form Validation**.
So what's the need for validation?
Well, you may have a form, and you may want to

* validate the fields to make sure you have required fields
* validate numbers in a given range
* validate the format, for example, for a postal code
* add your own custom business rule for validation

Java has  **Standard Bean Validation API**.
It defines a metadata model and API for entity validation.
**Spring Boot** and **Thymeleaf** also support the **Bean Validation API**.
The homepage for the **Bean Validation API** is listed here on this [link](https://www.beanvalidation.org).
It has documentation, reference manual, and links to other resources.
So the Bean Validation features:

* you can check to see if a field is required,
* you can validate a length,
* you can validate numbers,
* you can validate with regular expressions,
* you can make custom validation.

All right, so let's take a look at some validation annotations.

<table align="center">
    <thead>
        <th>Annotation</th>
        <th>Description</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>@NotNull</strong></td>
            <td>Checks that the annotated value is not null</td>
        </tr>
        <tr>
            <td><strong>@Min</strong></td>
            <td>Must be a number >= value</td>
        </tr>
        <tr>
            <td><strong>@Max</strong></td>
            <td>Must be a number <= value</td>
        </tr>
        <tr>
            <td><strong>@Size</strong></td>
            <td>Size must match the given size</td>
        </tr>
        <tr>
            <td><strong>@Pattern</strong></td>
            <td>Must match a regular expression pattern</td>
        </tr>
        <tr>
            <td><strong>@Future / @Past</strong></td>
            <td>Date must be in future or past of given date</td>
        </tr>
        <tr>
            <td><i><strong>others ...</strong></i></td>
        </tr>
    </tbody>
</table>

And these are all fairly self-explanatory, but I'll go through them.
`@NotNull` makes sure that the given value is not null.
`@Min`, and `@Max`.
Those basically apply to a number to make sure it falls within a given range.
You also have `@Size` to make sure that the value matches a given size
as far as the number of characters or the number of digits.
You can also apply regular expression patterns, using `@Pattern`.
And then you have `@Future`, and `@Past`.
These apply to dates.
So the date must be in the future, or it must be in the past.
And there are also some others.

Allright, so let's go ahead and take a look at our road map here,
as far as what we're going to do.

* set up our development environment
* required field
* validate number range: min, max
* validate using regular expression(regexp)
* custom validation

First thing we're going to do is set up our development environment.
Then we'll write code to check for a required field.
Next, we'll validate a number range with min and max.
Next, we'll move forward and we'll apply regular expressions.
And then we'll do custom validation.
This is kind of like the big grand finale.
We'll actually create our own custom validation rule, along with our own custom Java annotation.
We'll walk through each of these step-by-step in the following sections.

We'll start here at the Spring Initializr website, `start.spring.io`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image52.png" alt="image52">
</div>

And for the project type, we'll choose `Maven`.
Be sure to choose the language of `Java`.
And for **Spring Boot** versions, choose the latest released version.
Avoid the `SNAPSHOT` versions.
For the `Group`, we'll give `com.luv2code.springdemo.mvc`.
For `Artifact`, we'll give `validationdemo`.
And I'd like to make a change here to the `Package` name,
that should just have the `com.luv2code.springdemo.mvc`.
Be sure you have `Jar` packaging selected and also choose your `Java` version.
Now let's move up to the actual Dependencies section and `ADD DEPENDENCIES`.
And let's choose the web dependency, and this case, we're building a **Spring MVC** app.
And then we'll select **Thymeleaf**, the **Thymeleaf** template engine for our view pages for our MVC app.
We'll also add **Validation** support.
And then finally, we'll add **DevTools** to give us that auto-reload feature of **Spring Boot**.
Make sure you have these dependencies that I have listed here on the image above.

Let's go ahead and generate our project.
It'll download our `validationdemo.zip`.
I'll unzip that zip file.
And now what I'll do is I'll simply copy this file over to another directory,
and I'll move up to my `dev-spring-boot` directory,
I'll move into the `06` directory, and then I'll simply paste it here.
So now let's go ahead and open this project in our IDE.
Let me do a quick rebuild on the project.
And now let's go ahead and do our housekeeping here,
setting up our in IntelliJ settings to have support for the auto-reload.
I'll move here under `Settings`, I'll go to `Build, Execution, Deployment`,
choose `Compiler`, make sure we have this box checked for `Build project automatically`,
then we `Apply` that change.
And then also go down to the `Advanced Settings`,
and make sure you have this option already selected.
`Allow auto-make`, so make sure that's already checked.
If not, go ahead and check it, but make sure it's enabled or on.
And then we hit `OK`.
So at this point, our project is set up and ready to go.
</div>

### [Required Fields]()
<div style="text-align:justify">

In this section, we're going to learn about **Spring MVC** form validation for required fields.
What we'll do here is we'll have this example.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image53.png" alt="image53">
</div>

We'll have a form where we ask for a customer's first name and last name.
And here what we'd like to do is make the last name required.
So if they hit the `Submit` button with an empty form,
then we'll give an error message saying, `hey, last name is required`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image54.png" alt="image54">
</div>

And now let's take a look at the big picture.
So here we'll have our customer form.
The user enters their data, they hit `Submit`, pass over a **Customer** object to our `CustomerController`,
and the `CustomerController` will actually perform the validation.
If the validation is successful, we'll send them to the confirmation page.
If the validation fails, then we'll send them back to the customer form
for them to fix up the form and resubmit again.
Allright, so let's go ahead and look at the development process.

* Create the **Customer** class and add validation rules
* Add the Controller code to show the HTML form
* Develop the HTML form and add validation support
* Perform the validation in the Controller class
* Create the confirmation page

Starting here with the first step of creating the **Customer** class and adding validation rules.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image55.png" alt="image55">
</div>

Now remember in our example, we want to add a validation rule to the last name and make it required.
What we'll have to do is make use of these two annotations here, `@Notnull` and `@Size`.
So for null, we'll give the error message is required.
That'll display if the values actually null.
Also, we want to have `min=1` for `@Size`, 
meaning that we need to have a minimum of one character here to make sure this is not empty.
So we're just checking for null and size on the given last name.
Those are the validation rules that we've just added to this **Customer** class.
And note for the first name, there's nothing on it.
So in this case, there's no validation rules for the first name field.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image56.png" alt="image56">
</div>

Now moving ahead to step two, we're going to add the Controller code to show the HTML form.
We set up the `@GetMapping` for `"/"` this will map to the root URL, so we can show our HTML form directly.
So here we're making use of HTML form binding.
And so we pass in `theModel`.
`theModel` is a special logic that allows us to share information between controllers and view pages.
We can basically add information to the model and the controller,
and then the view page can actually access that data from the model.
For `theModel`, we need to create an instance of the **Customer** class and add it as a model attribute.
We provide the **name** and the **value**.
The **name** is important because this is the name that we'll reference in the HTML form.
And also that's the **name** that we'll use when we process the HTML form.
Since we're using HTML form data binding, we must provide a model attribute to the form.
Finally, we return the string `"customer-form"` 
and this will map over to the timely file `customer-form.html`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image57.png" alt="image57">
</div>

Allright, so moving ahead to step three,
we're going to develop the HTML form and add validation support.
Here's our `customer-form.html`.
For the form, we'll make use of the `th:action`.
This tells it where to submit the form data.
And since we're using form data binding, we need to give the reference here
to the model attribute named `customer`.
And it's the same naming we used from the previous image in the controller as far as adding that model attribute.
And then here we'll send the data over using **HTTP POST**.
For the first name field, we make use of the input `type` equals `text`.
And then we give `th:field` equals `*{firstName}`.
This maps to the `firstName` property on the **Customer** class.
And then we repeat the process for the `lastName` field.
And just make note of special notation there for the star, curly braces,
and the actual name of the property.
And now we need to add this new entry here to show the error message if present.
Based on our previous code for **Customer** class,
we know we added a validation rule to the `lastName` field.
So there may be a validation error on that field.
So we give this reference here to actually show that given item.
And then we also apply a **CSS** class.
The actual name of that `class` is `error`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image58.png" alt="image58">
</div>

Moving along to step number four, 
this is where we actually perform the validation in the **Controller** class.
Here's our method here for this post mapping of `processForm`.
We have two new items here as far as parameters.
The first one is called `@Valid`.
So this is a new annotation.
Basically we're saying perform validation rules on this `theCustomer` object.
And since we're using form data binding, 
we need to give the reference here to the model attribute named `"customer"`.
Also, the results of that validation will be placed in this object `BindingResult`.
So binding result will be the result of the actual validation.
So behind the scenes when **Spring** calls this method,
**Spring** will should perform the validation rules using the validator API,
and then the results will be placed in the object `BindingResult`.
In your code, you can check the value of the binding result and see if it has any errors.
So here in the coding, I can say `if (theBindingResult.hasErrors())`,
then we'll send the user back to the `"customer-form"`.
else, that means there are no errors,
we'll simply send the user to the `"customer-confirmation"` page.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image59.png" alt="image59">
</div>

And then finally, step five, very simple.
We create our confirmation page.
We say, `hey, the customer is confirmed`, and we get the customer's first name
and also the customer's last name.

Allright, let's get started with step one of creating a customer class and adding validation rules.
Let's create a new class for **customer**.
And this class is very simple, we'll have a customer first name and last name.
And then I'll also go ahead and generate the getters and setters.

````java
public class Customer {

    private String firstName;
    private String lastName;

    public Customer() {
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
}
````

Now the next step here is adding the validation rules.
And we know that we want last name to be a required field.

````java
private String firstName;
@NotNull(message = "is required")
@Size(min = 1, message = "is required")
private String lastName;
````

We'll make use of the annotation `@NotNull`,
and we give the error message `"is required"`.
And now, I'll just do a little copy-paste on this.
And for this next annotation here, I'll say `@Size`, `min = 1`, fix the imports.
So, this is the coding here in place for making the last name field required.

Okay, let's add a controller code to show the HTML form.
Let's go ahead and move into our code here and let's create a new controller.
I'll create this new Java class, and I'll give the name `CustomerController`.
And I'll go ahead and annotate this controller class with a controller annotation.

````java
package com.luv2code.springdemo.mvc.controller;
import com.luv2code.springdemo.mvc.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model theModel) {
        theModel.addAttribute("customer", new Customer());
        return "customer-form";
    }
}

````

I'll get the `@GetMapping` for `"/"` to map on the root URL,
so we can show the page automatically or show the form automatically.
I'll define this method here, `public String showForm`. 
Pass in `theModel`.
And I'll go ahead and fix the imports real quick.
And for model, be sure to choose the model from the **Spring** framework.
And remember, the model allows us to share information between the controllers and view pages.
So we can add data to the model and the controller code,
and then the view pages will be able to access that data from the model.
So here we'll go ahead and add data to the model, we'll say `theModel.addAttribute`.
And then we get the attribute name and the actual attribute value.
So the first item here is the `name` and then the second item's the `value`.
So the name is something important because we'll actually use that name in our view pages
like our HTML forms and so forth.
And also the value here we simply start off with an empty **customer** instance.
And this is required because we're using HTML form data binding.
And then finally we return the actual name of the view,
so `return "customer-form"`, and this will map over to `customer-form.html`.
And that's the basic coding there for showing our HTML form.

So I'll move under resources, templates, and I'll create this new HTML file.
And the name of this file is `customer-form.html`.
Since I'm going to make use of some **thymeleaf** expressions here
I set up the namespace for thymeleaf.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration Form</title>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>
    <form th:action="@{/processForm}" th:object="${customer}" method="POST">

        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />

        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span>
    </form>
</body>
</html>
````

I'll go ahead and update the title.
And now I'll start adding some information here regarding the form.
So just some quick instructions to the user, 
`Fill out the form. Anything with an asterisk is required`.
Again, just basic instructions for the user.
So I make use of the `th:action`, and I give a reference to `processForm`.
That's where we'll actually send the data.
I make use of `th:object`.
That's the name of the form model attribute.
And here we'll post the data across.
Where we're going to submit the data,
the actual model attribute name of `customer`,
and then finally, we're posting the data across.
And the model attribute name has to match with the actual model attribute name
that was set up by the controller when they showed this form.
That's very important.
And now let's go ahead and start setting up our fields here for `firstName`, and the input `type` of `text`.
And then we map it to `th:field`, and we give `firstName`.
So that's the actual property name from the **customer** class.
And note the syntax here with the star, curly brace.
And then I'll add some `<br>`'s, some line breaks.
And then this will be for `lastName`.
And again, that's the property name from our **customer** class.
And I need to display the error message if present, or add the error message if present.
I make use of this `th:if`, and I basically say, `hey, does the 'lastName' field have an error?`
So the syntax is a bit scary, but it's a dollar sign, curly brace.
We give the pound, `fields.hasErrors('lastName')`.
And then we give the `th:errors="*{lastName}"`that'll actually display the error message.
And then I specify the class, so `class="error"`.
So that's going to be a **CSS** style or a class that we'll actually define a bit later in this file.
So for now, it's just a little placeholder.
But that's kind of the basics here for laying out the error message,
or basically checking to see if there's an error message for this given field
and then displaying that error message.
Now the one missing piece here is the actual **CSS** style
that I mentioned that we would actually add.
And so let's go ahead and do this real quick,
and I'll just kind of do a very simple version.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration Form</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>
    <form th:action="@{/processForm}" th:object="${customer}" method="POST">

        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name: <input type="text" th:field="*{lastName}" />

        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span>
    </form>
</body>
</html>
````

There's some more advanced ways of doing **CSS** styles, like in separate files.
But right now, just to keep things simple,
I'll place the CSS style in this file.
And I'll just define it as style.
I give the name of `.error`, and then this particular style will have the font color of red.
So up top here we have this style error and that matches with the actual class error
that we have down at the bottom.
So the error message will actually show up in red.
So that's the connection here between those two items.
And you can give any name that you want,
just be consistent here as far as what names you give for that given **CSS** style.
And then the actual error message that's displayed
will be information from those annotations from our **customer** class.

````html
<input type="submit" value="Submit" />
````

Let's go ahead and set up our final item, our `Submit` button.
So input `type` equals `submit` and `value` equals `Submit`.
That's the actual label that you'll see on that given button.
We know that last name could possibly have some validation errors,
and so we added the code accordingly to display those error messages,
also making use of those **CSS** styles.

Moving ahead to Step Four, we'll perform the validation in the **Controller** class.
I'll go ahead and move back into my **CustomerController**.
And I'll add this new `@PostMapping`, `processForm`.

````java
@PostMapping("/processForm")
public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
}
````

That's the same year URL they used in the HTML form.
I'll define this method here, `public string processForm`.
And here I need to add some new parameters here that are fairly important.
I make use of the `@Valid` annotation.
I make use of the `@ModelAttribute` annotation.
Now this `@Valid` annotation will tell **Spring MVC** to perform validation for this given form data.
And the `@ModelAttribute` says read the form data from that model attribute customer.
So that's the customer that was submitted by the HTML form,
and it'll use those validation rules that's been defined in that **Customer** class.
And we added those validation rules earlier in previous sections
where we made that last name field required using `@NotNull` and `@Size`.
So it'll perform the validations here based on this coding.
And then I add in this `BindingResult`.
And `theBindingResult` actually holds the results of the validation.
So **Spring MVC** will go through and perform all the validation, get the results,
if there were any errors, what the error messages were. 
So on and so forth.
If everything was successful, then it'll have that data in this given object here, `theBindingResult`.
And then we can actually write some logic based on this binding result to find out what we need to do.
If we need to show the form again or send them off to a confirmation page.
And so we'll actually kind of move into the code, and actually do that now.

````java
@PostMapping("/processForm")
public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

    if (theBindingResult.hasErrors()) {
        return "customer-form";
    } else {
        return "customer-confirmation";
    }
}
````

Here I'll say if `theBindingResult.hasErrors()`, then that means, 
`hey, there were some validation errors`, some validation problems.
Let's go ahead and send them back to the `"customer-form"`.
If everything was okay, then we'll send them off to our confirmation page, 
or whatever our success page is.
So in this case, we have `"customer-confirmation"`. 
And so that's the coding logic there.
So with this given method we process the form, we perform the validation.
The results are in that binding result.
And then based on the data or the logic here for that binding result,
then we can route the user to either the form,
or over to the customer confirmation page.

All right, we're almost done. 
We're at our last step here, step five of creating the confirmation page.
Let's go ahead and move back into our project.
And I'll move down to this `resources` directory, `templates`, and I'll create a new HTML file.
And the name for this file is `customer-confirmation.html`.
We're going to use some of the **Thymeleaf** expressions in this page,
so, update the namespace accordingly.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Confirmation</title>
</head>
<body>

    The customer is confirmed: <span th:text="${customer.firstName + ' ' + customer.lastName}" />

</body>
</html>
````

I'll go ahead and update the title, `"Customer Confirmation"`.
And now, for the confirmation, we basically want to say the customer is confirmed
and give their first name and their last name.
I make use of this `th:text`.
And give the dollar sign curly brace.
I give the information that I want to output here.
Here, I'll make use of this `customer.firstName`.
That's whatever the information the user entered on their form.
And also, `customer.lastName`.
And I also put a white space in there just to kind of give us some white space
between the first name and the last name.
So, real simple way of doing some stream concatenation here.
But this will give me the output of,
"`Hey, here's the customer's first name and their last name.`"
Let's go ahead and run our application.
And test it out.
My application is up and running.
Let's go ahead and open up our web browser.
And we'll go to `localhost:8080`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image60.png" alt="image60">
</div>

Now, let's go ahead and test out some of our validation here.
So, let's leave the form totally blank.
Don't enter anything for first name or last name.
We know last name's required.
Let's leave everything empty and let's simply hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image61.png" alt="image61">
</div>

We see here that the `last name field is required` and it shows the error message.
And this is all kind of based on that coding that we put in place here
for our customer class where we said `NotNull` and `Size` to make this given field required.
Now, let's go ahead and add a customer first name and last name, John Doe.
And hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image62.png" alt="image62">
</div>

So, the customer's confirmed, `John Doe`.
That's based on that code that we created in that confirmation page.
So, kind of pulling everything together here.
We started out with our customer form.
We would send data over to our customer controller.
We'd perform the validation in our controller.
And then if everything was successful, we would send them to the confirmation page.
If the binding result had errors or the validation failed
we'd send the user back to the `customer-form`.
So, we kind of pulled everything together.
And now, we have **Spring MVC** validation working out as planned.

Now, one thing here though is that we still have a little problem.
Now, the edge case here is that if we enter all spaces for last name,
just all spaces, one too many, and then we hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image63.png" alt="image63">
</div>

And ooh, the validation passed, but it shouldn't have passed
because we only entered spaces for last name.
So, the problem's that we need to do some additional validation here
or some additional updates to our code to handle for the case of all spaces or white spaces.
So what we need to do is actually trim the white space from the input fields to make sure
that the white space doesn't pass.
So what we'll do is, we'll make use of this annotation called `@InitBinder`.
This annotation works as a pre-processor.
So what it'll do for every web request that comes through our controller, this code will execute first.
So what we'll do is, we'll actually create a method,
and we'll annotate it with the `@InitBinder` annotation.
So all requests coming in are pre-processed.

What are we going to do in our `@InitBinder`?
Remember it's going to pre-process, so we're going to use it to trim our **Strings**.
We're going to remove the leading and trailing white space from our **Strings**.
Also, if the **String** only has white space, then we're going to trim it all the way down to a null object,
and this will actually resolve our validation problem.
Alright, so let's look at some code here.

````java
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;

//...

@InitBinder
public void initBinder(WebDataBinder dataBinder) {

    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
}
````

So what we're going to do is actually register a custom editor
in our controller, and we do that by making use of this `@InitBinder`.
So remember, `@InitBinder` will basically pre-process all web requests coming into our controller.
Allright, so here's the method signature, `public void InitBinder`.
Has a parameter coming in called **WebDataBinder**.
I'll talk about that in a second.
What I'll do on the next line here is I'll actually create this **StringTrimmerEditor**.
So, this is actually a class that's defined in the **Spring API**.
Its purpose is to trim **Strings**.
So it'll basically trim or remove the leading white space, and also the trailing white space.
And also in the constructor, I have the value of `true`.
So that means that it'll actually trim the String down to `null` if it's entirely all white space.
Alright, so trim it down to `null`.
So that's the **StringTrimmerEditor**.
Now, once I have this object created, then I'll use that `dataBinder`,
and I'll register this as a custom editor.
Alright, so what I'm doing here is for every **String** class, apply the `StringTrimmerEditor`.
Alright, what this does is, it'll pre-process every String as far as its form data.
It'll remove the leading and trailing white space.
And again, if the **String** only has white space, it'll trim it down to `null`.
And this is basically what this method will do.

Let's go ahead and add some debug statements for `lastName`,
just so we can kind of see what's happening in the background.
I'll open up my customer controller, and I'll move down to this `processForm` method.

````java
@PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

    System.out.println("Last name: |" + theCustomer.getLastName() + "|");
    if (theBindingResult.hasErrors()) {
        return "customer-form";
    } else {
        return "customer-confirmation";
    }
}
````

And what I want to do here is simply print out the `lastName` for that customer,
just so I can see what it looks like.
And I'll also put it inside of these vertical pipes or vertical bars `"|"`,
just so I can kind of see where the spaces are if they exist in this `lastName`.
All right, let's go ahead and run this application
Let's go ahead and open up our `customer-form`.
And let's go ahead and move to `localhost:8080`.
I'll go ahead and enter our first name.
And then for last name,
I'll enter all spaces, and I'll hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image63.png" alt="image64">
</div>

And so, you know, this passed validation,
however, it shouldn't, we saw that earlier.
But lets kind of go ahead and look at the console and see what's going on in the background.

````text
.   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.4)

2024-10-23T19:48:39.192+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Starting ValidationdemoApplication using Java 23.0.1 with PID 16724 (D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo)
2024-10-23T19:48:39.192+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : No active profile set, falling back to 1 default profile: "default"
2024-10-23T19:48:39.305+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-10-23T19:48:39.306+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-10-23T19:48:39.306+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.30]
2024-10-23T19:48:39.327+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-10-23T19:48:39.328+03:00  INFO 16724 --- [validationdemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 135 ms
2024-10-23T19:48:39.418+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-10-23T19:48:39.426+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-10-23T19:48:39.430+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Started ValidationdemoApplication in 0.255 seconds (process running for 80.672)
2024-10-23T19:48:39.434+03:00  INFO 16724 --- [validationdemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
2024-10-23T19:48:40.251+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-10-23T19:48:40.251+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-10-23T19:48:40.253+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
Last name: |        |
````

So down here at the bottom, notice here that the last name inside of those vertical bars there,
those pipes, last name is nothing but white space.
So that's why it passed.
It said, "`Hey, I had a lot of white space. It was not null and the length was greater than one.`"
So it's quote-unquote valid.
And what we can do is we can make use of that **StringTrimmerEditor**
to trim or handle the cases for those white spaces.
Let's go ahead and stop our application,
and let's move back in here into our customer controller.

````java
@InitBinder
public void initBinder(WebDataBinder dataBinder) {

    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
}
````

And now what we'll do is we'll add support
for the `@InitBinder` and the **StringTrimmerEditor**.
I want to remove the leading and trailing white space,
and also resolve this issue for our validation.
Alright, so I'm going to make use of this at `@InitBinder` annotation
that we discussed in the previous set of slides.
And then I'll simply give a method.
The method signature will always have **WebDataBinder** being passed in.
So I'll create this `new StringTrimmerEditor`.
Remember, it's part of the **Spring API**.
I pass in the constructive value of `true`,
meaning that if it's all white space, trim it down to null.
And then what I'll do is I'll basically register this custom editor on the `dataBinder`.
I'll say, "`For the string class, then make use of this **StringTrimmerEditor** that I just created.`"

We'll enter all white space here for the name.
So all white spaces for last name, and hit `Submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image65.png" alt="image65">
</div>

Then, oh yeah, so this is great.
So we're getting our desired error message.

````text
.   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.4)

2024-10-23T19:51:48.328+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Starting ValidationdemoApplication using Java 23.0.1 with PID 16724 (D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo)
2024-10-23T19:51:48.328+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : No active profile set, falling back to 1 default profile: "default"
2024-10-23T19:51:48.415+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-10-23T19:51:48.416+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-10-23T19:51:48.416+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.30]
2024-10-23T19:51:48.427+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-10-23T19:51:48.427+03:00  INFO 16724 --- [validationdemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 98 ms
2024-10-23T19:51:48.463+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-10-23T19:51:48.471+03:00  INFO 16724 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-10-23T19:51:48.474+03:00  INFO 16724 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Started ValidationdemoApplication in 0.157 seconds (process running for 269.715)
2024-10-23T19:51:48.474+03:00  INFO 16724 --- [validationdemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
2024-10-23T19:54:17.266+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-10-23T19:54:17.266+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-10-23T19:54:17.266+03:00  INFO 16724 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
Last name: |null|
````

And also note here in the bottom, last name of `null`.
It's trimming it all to `null`.
So validation is working as desired.

</div>

### [Validate a Number Range]()
<div style="text-align:justify">

In this section, we're going to learn about **Spring MVC** validation for number ranges using `@Min` and `@Max`.
So what we'll do here is we will actually add a new field to our HTML form for free passes.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image67.png" alt="image67">
</div>

So this free passes field, the user will be able to enter a value between 0 and 10, and that's our number range.
Anything outside of that range, then, we should have a validation error displayed above.
So let's look at the development process:

* Add the validation rule to the **Customer** class
* Display the error message on the HTML form
* Perform the validation in the Controller class
* Update our confirmation page with the appropriate information

Alright, so let's dive in and take a look at step one adding the validation rule to the customer class.
So, basically what we're going to do here is have this new field in our customer class called `freePasses`,
so I'll define it as `private int freePasses`.

````java
// ... other packages
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Customer {

    // ... other fields

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;

    // .... other getters and setters with constructor

    public int getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(int freePasses) {
        this.freePasses = freePasses;
    }
}

````

What I'd like to do is actually make use of two new annotations to set up our validation rules.
I'll make use of an `@Min` and an `@Max`.
So `@Min`, that's the minimum value that we'll expect or accept,
and then the `message` equals, that's the actual error message
that's generated if this validation fails, so that's for `@Min`.
Then we'll also add one for `@Max`.
So the maximum value is 10, and then the appropriate error message in case this validation fails.
So we've actually placed two annotations here on this given field for a given range.
Now, we go through and do our normal work of adding the getter and setter methods,
so we can actually retrieve the value for the `freePasses`
and also set the value for the `freePasses`,
but that's basically it as far as the coding here for the **Customer** class.

Okay, so now what we need to do next is actually move to step two
and that's displaying the error message on the HTML form.
So again, basically I'm just going to define a form field and also the form errors.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration Form</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>
    <form th:action="@{/processForm}" th:object="${customer}" method="POST">

        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name (*): <input type="text" th:field="*{lastName}" />

        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span>
        <br><br>
        
        Free passes: <input type="text" th:field="*{freePasses}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('freePasses')}" th:errors="*{freePasses}" class="error"></span>
        <br><br>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
````

I'll just add a new entry here for `Free passes:`.
And I map that to `freePasses`.
That's the field that we just created in our **Customer** class.
And now that's basically it.
So this is really just a repeat of what we've done so far for `lastName`, a similar thing here for `freePasses`.

Alright, now the next step in our process is actually performing the validation in the controller.
Now one little caveat here is that there's nothing that we really have to do here.
The code that we already have in our `CustomerController.java`,
we can use the exact same code.
There's no changes, no modifications needed here.

````java
@PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

    System.out.println("Last name: |" + theCustomer.getLastName() + "|");
    if (theBindingResult.hasErrors()) {
        return "customer-form";
    } else {
        return "customer-confirmation";
    }
}
````

I'm simply showing you this file here and showing you this step just out of completeness.
But again, no changes, no code needed for our controller.
We can kind of leave that as is.

So next, what we'll do here is we'll cover step four, and that's updating our confirmation page.
And basically what we want to do here is just give an output, just echo whatever they entered
for the value for the `freePasses`.

`````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Confirmation</title>
</head>
<body>

    The customer is confirmed: <span th:text="${customer.firstName + ' ' + customer.lastName}" />
    <br><br>
    Free passes: <span th:text="${customer.freePasses}" />
</body>
</html>
`````

So here, I'll just put in a little `<br>` there, and then I'll just say `Free passes:`,
and I'll just drop in the value of `freePasses`.
So again, it'll just say `customer.freePasses` and whatever the user entered,
it'll be echoed here on the screen.
Very simple, very straightforward, nothing, there's no rocket science here.
Alright, so we're at a point
where we can actually run our application.
Okay, our app is up and running.
We hit customer form, and then we'll just enter the fields here for first name, last name, and then for free passes,
let's add some bad data:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image68.png" alt="image68">
</div>

We're trying to break the system.
So a large value of `555`.
It says it must be less than or equal to 10.
Okay, great.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image69.png" alt="image69">
</div>

Let's try a negative value.
Hit `Submit`.
Again, must be greater than or equal to zero.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image70.png" alt="image70">
</div>

And then we can hit a value of `4`, which is in that range.
So it's good.
So, as we can see, our validation rules are working as expected.
It only accepts values between the range of zero and 10.

</div>

### [Applying Regular Expressions]()
<div style="text-align:justify">

In this section, we're going to learn about Spring MVC Validation with Regular Expressions.
So a regular expression is just a sequence of characters that define a search pattern.
You can use this pattern to find or match strings, and you can also use it for validation.
Now, regular expressions, it's like its own language.
This is really an advanced topic, so I'm going to assume that you already know about regular expressions,
and I'll simply show you how to apply it with Spring MVC.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image71.png" alt="image71">
</div>

So what we'll do is, we'll actually use regular expressions to validate a postal code.
So our form will have a new field for postal code,
and the user can only enter 5 characters or digits in order to pass validation.
And again, we'll actually apply a pattern for regular expressions for this validation.

Now, let's look at the development process:

* Add a validation rule to the **Customer** class
* Display the error messages on the HTML form
* Update the confirmation page

So this is all very similar to what we've seen before in some of the previous sections.

````java
public class Customer {

    // ... other fields

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    // ... other getters and setters with constructor

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

````

Alright, so adding the validation rule to the **Customer** class.
So basically what we do is we make use of a pattern,
and then we have `regexp`, that's for regular expression,
and we simply drop in that regular expression.
So what you see there in blue, that's the actual regular expression
that will only match on 5 characters and digits.
So once you know regular expressions,
you can simply drop in your appropriate expression, and you're ready to go.
So again, here we have a regular expression, and then we have the actual error message.
We simply apply this pattern to the field for postal code.
And that's it.
And we create our normal getters and setters,
and then we're up and running, and we're ready to go.

Let's go ahead and take a look at step two.
And that's actually displaying the error message on the HTML form.
So again, go back to my view and open this one up.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration Form</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>
    <form th:action="@{/processForm}" th:object="${customer}" method="POST">

        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name (*): <input type="text" th:field="*{lastName}" />

        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span>
        <br><br>

        Free passes: <input type="text" th:field="*{freePasses}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('freePasses')}" th:errors="*{freePasses}" class="error"></span>
        <br><br>

        Postal Code: <input type="text" th:field="*{postalCode}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('postalCode')}" th:errors="*{postalCode}" class="error"></span>
        <br><br>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
````

And basically what I'll do first,
I'll just do a little copy-paste here of my existing field so that free passes,
I'll just go ahead and copy that information.
And then I'll just move down a bit and actually paste it right below.
Because basically postal code is going to be an input field.
We're simply just going to change the actual label and the path for it.
So this is postal code, the path will be postal code which maps to that actual field
that we just defined in our `customer.java` class.
And a similar thing here for the error messages.
So really just some copy-paste programming here.
So here we go.
Our new postal code that's entered along with the appropriate field here
for error messages if the validation fails.

Great, so then our final step here is just updating our confirmation page.
So assuming that validation passes, 
then we'd at least like to display the actual postal code that the user entered.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Confirmation</title>
</head>
<body>

    The customer is confirmed: <span th:text="${customer.firstName + ' ' + customer.lastName}" />
    <br><br>
    Free passes: <span th:text="${customer.freePasses}" />
    <br><br>
    Postal code: <span th:text="${customer.postalCode}" />

</body>
</html>
````

And again here, just some copy-paste programming.
I'll drop in some line breaks, and then I'll say postal code, `customer.postalCode`.
And there we go.

Now test the application.
And now I'll go ahead and enter some data.
So the last name is required, so I have to enter that data.
So I'll enter a last name.
Postal code is not required, so I can do a submit.
So that works out.
Now I want you to actually enter some bad data for postal code.
So I'll just enter a lot of gibberish here on my keyboard and I'll hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image72.png" alt="image72">
</div>

And it'll say, hey, you can only have 5 characters or digits.
So I'll go ahead and clear that out.
I'm going to enter 55 characters, so `abcde`.
And now I hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image73.png" alt="image73">
</div>

And boom, it works out just fine.
So they entered a postal code of 5 characters.
And I can enter five numbers, and it works out just fine.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image74.png" alt="image74">
</div>

That's all based on a regular expression that I'm using in the application.
Now note here, postal code is optional.
So it's not required that you enter a postal code.
So that's why you can enter nothing and it'll still pass.
But if you do enter values, then it's actually going to apply it with that given regular expression.
Allright, so this is good.

</div>

### [Make Integer Fields Required]()
<div style="text-align:justify">

In this section, we're going to learn how to make an integer field required.
So far we've created this nice form.
We've added some validation rules already.
So we have last name that's required.
We have our free passes, and we've also added `@Pattern` matching to our postal code.
And so what I'd like to do is actually make this free passes field required
such that if the user doesn't provide a value
then we'll actually give them an error message saying, `hey, this given field is required`.
Allright, so let's go ahead and move into our code.
So let's go ahead and move into our source directory and let's open up `customer.java`.
So yeah, so we've already added a required fields for `lastName`, 
so hopefully we can do something similar here for `freePasses`.

````java
public class Customer {

    private String firstName;
    
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    // all getters and setters with constructor
}
````

So for `freePasses` here we have our min and our max value.
And this is the field that I want to make required.
So basically I simply do a copy-paste exercise.
I'll copy the `@Notnull` from `lastName`,
and I'll simply move down to my `freePasses` field and paste it.

````java
public class Customer {

    private String firstName;
    
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;
    
    @NotNull(message = "is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    // all getters and setters with constructor
}
````

Allright, so there we go.
So we have this `@NotNull` that we've added here for our `freePasses` field.
And this will basically make sure that this given field is required.
If they didn't provide a value then we'll actually display the error message "`is required`".
And that's the game plan, we've kind of seen this before.
Test if field `freePasses` is required.
So I'll give something for last name.
For `freePasses` I'll empty this field out, make it empty, make it blank.
And now I'll go ahead and hit on the `submit` button here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image75.png" alt="image75">
</div>

So I get this long message here.
Okay, so the root cause is it's a type conversion error.
Couldn't convert a **string** to the primitive type of **int**.
Allright, so let's go ahead and try and fix this or resolve it.

````java
public class Customer {

    private String firstName;
    
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;
    
    @NotNull(message = "is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    //private int freePasses;
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    // all getters and setters with constructor
}
````

So we can actually resolve this by refactoring our field.
So instead of using the primitive type, we can use the **Integer** type that's with a capital `I`.
And so this is basically a wrapper class.
And so why will this work?
Because if our field is blank or has spaces then they'll actually trim it to `null`
using that `StringTrimmerEditor` that we've added before.
And then this will handle the appropriate issue.
So again, we're going from `int` to `Integer`.
And `Integer` is a special wrapper class in **Java**.
We also have to update our getter and setter methods in this class.
Alright, so let's go ahead and save all this stuff.
Move back over here and let's run our application.
So we move in here to customer form.
And `freePasses`, we'll leave it blank.
We won't put any information in there.
And we'll hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image76.png" alt="image76">
</div>

and just the way we want it.
So `is required` so they can handle the nulls
and all that stuff and that's working out just fine.
Let's just check it with last name.
So we'll give a last name, we hit `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image77.png" alt="image77">
</div>

And great, so `is required` is, you know, being applied to our `freePasses` field.
So that's kind of working out as desired.
You give real values, it goes through and the validation passes.
So the key here was simply converting from an `int` to an `Integer` type
and that helped us resolve this issue.

</div>

### [Strings for Int Fields and Custom Messages]()
<div style="text-align:justify">

In this section, we're going to learn how to handle string input for integer fields.
So, we have our form that we've created.
In the previous section, we added support to make sure that the `freePasses` field is required,
but I hinted that we have a little problem, so let me show you here real quick.
So, I'll go ahead and enter something for last name, and then for `freePasses`,
let's break it by entering some text and then hit the `submit` button:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image78.png" alt="image78">
</div>

So, this thing's failing again.
We've entered some text, and it just blows up.
Alright, failed to convert property value, number format exception.
So, what we'll do is we'll first create a custom error message,
so we won't see that big, long, ugly error message,
we'll simply give them a simple message of `hey invalid number`.
And then that information will be displayed and used on our screen during spring validation.
So, in effect, we're simply going to add our own custom error message for that scenario.
Now, let's go ahead and swing over into our IDE.
And what I'll do here, is I'll create a new file under the `resources` directory called `messages.properties`.
Be sure to use the exact same file name that I have listed
because this is a special file that **Spring MVC** will use during validation.

````properties
typeMismatch.customer.freePasses=Invalid number
````

So, we have this blank file here called `messages.properties`.
What we're going to do here is, actually, add our own custom error message for that scenario.
So, here I'll give `typeMismatch.customer.freePasses` equals,
and then I give the actual custom error message that we're going to use for this scenario.
So, `Invalid number`.
So, the first item here is the actual error type, `typeMismatch`,
then we have the actual attribute name, `customer`,
next, we give the actual field name, `freePasses`,
and then, finally, we specify our own custom error message.
Alright, so we have the file saved.
This is our custom error message for the spring validation.
But one thing that's really important, is that the location is very important.
So, this file has to be under `resources`, and then the file name is `messages.properties`.
That's very, very important.
Let's go ahead and test this out and see if it works.
So, I'll go over to my application and I'll get it up and running on my server.
For last name I'll just give `Smith`, and now I'll add a lot of text for `freePasses`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image79.png" alt="image79">
</div>

So, there's our custom error message, `Invalid number`.
So, it's actually reading our custom message from the file and displaying it to the user.
So, no more of that long-winded Java stuff.
We get a simple message that we can show to the user and, again, that was defined in our `messages.properties` file.
So, you can simply change that to anything you want, reload the app,
and it'll have that new error message being displayed there on the screen.

Now, you may notice that there was a lot of stuff
that we put there in that `messages.properties`.
In the following section, I'll break it down even further.
I'll go in depth, and I'll decipher how we came up with that big, 
long piece of text that we added in that `messages.properties`.

</div>

### [Debugging Tips for Custom Error Names]()
<div style="text-align:justify">

Alright, so we have this working as far as giving our own custom message.
But there's a lot of things that we did in that `messages.properties` file
and I want to break it down, kind of, decipher it and, you know, figure out exactly
what those different components were and also how you could use this on your own project
if you had a totally different object model in a totally different form set.

What we'll do is, we'll actually add some logging or debug messages here in our `CustomerController`.
So in our `CustomerController` I'm going to actually inspect `theBindingResult` object
because this object has a wealth of information,
a lot of good data that we can use to, kind of,
decipher what's going on during the validation process.

````java
@PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

    System.out.println("Last name: |" + theCustomer.getLastName() + "|");
    System.out.println("Binding results: " + theBindingResult.toString());
    System.out.println("\n\n\n\n");
    if (theBindingResult.hasErrors()) {
        return "customer-form";
    } else {
        return "customer-confirmation";
    }
}
````

So what I'll do is I'll simply add a `System.out.println`, 
and I'll print out `theBindingResult`.
And then, also, I'm going to add some line breaks or some line spaces or new lines
just to kind of move things up on the console window, but this is not really required.
Allright, so let's go ahead and save this.
And let's go back and get it up and running.
So just go to the `customer-form` on the browser.
And I'll just enter some data here for `Smith`.
I'll enter some bad data here for `freePasses`,
and then I'll go ahead and hit on the `submit` button.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image79.png" alt="image80">
</div>

Okay, great.
So now we should have some good data in our console window.

````text
.   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.4)

2024-10-24T08:42:44.883+03:00  INFO 45688 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Starting ValidationdemoApplication using Java 23.0.1 with PID 45688 (D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\06-spring-boot-spring-mvc\validationdemo)
2024-10-24T08:42:44.884+03:00  INFO 45688 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : No active profile set, falling back to 1 default profile: "default"
2024-10-24T08:42:44.999+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-10-24T08:42:44.999+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-10-24T08:42:44.999+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.30]
2024-10-24T08:42:45.011+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-10-24T08:42:45.011+03:00  INFO 45688 --- [validationdemo] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 126 ms
2024-10-24T08:42:45.069+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-10-24T08:42:45.077+03:00  INFO 45688 --- [validationdemo] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-10-24T08:42:45.079+03:00  INFO 45688 --- [validationdemo] [  restartedMain] c.l.s.mvc.ValidationdemoApplication      : Started ValidationdemoApplication in 0.208 seconds (process running for 18.681)
2024-10-24T08:42:45.081+03:00  INFO 45688 --- [validationdemo] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
2024-10-24T08:42:48.257+03:00  INFO 45688 --- [validationdemo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-10-24T08:42:48.257+03:00  INFO 45688 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-10-24T08:42:48.257+03:00  INFO 45688 --- [validationdemo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
Last name: |smith|
Binding results: org.springframework.validation.BeanPropertyBindingResult: 1 errors
Field error in object 'customer' on field 'freePasses': rejected value [asdfasdfasdafasw]; 
codes [typeMismatch.customer.freePasses,typeMismatch.freePasses,typeMismatch.java.lang.Integer,typeMismatch]; 
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [customer.freePasses,freePasses]; 
arguments []; default message [freePasses]]; 
default message [Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'freePasses'; 
For input string: "asdfasdfasdafasw"]
````

See these last lines here are very important for us.
Alright, so this line here, the binding result basically says,
"`Hey, this binding result had one error.`"
It's on `freePasses` of course.
And the second line I'll actually give you more details as far as where it occurred.
So it'll say that, "`Hey, there's a field error on object 'customer'`,"
and that's the actual **Spring MVC** attribute name.
And then on field `freePasses` of that `customer` object.
And then it says the `rejected value`.
So, whatever the data that the user entered.
So you can grab that if you want to, I don't know, play around with that or do something with it.
But here comes the really good part,
these are all the error codes that **Spring MVC** will use when it displays a custom message.
So it'll go out and look for these.
So they have one of their error codes, it's `typeMismatch.customer.freePasses`.
So what we'll do is we're simply overriding their default error code,
and we're simply providing our own custom message.
So here in the config file we say,
"_Hey, for that `typeMismatch.customer.freePasses` use our custom message_".
And that's how that piece works out there.
And then they also have other error codes here.
You could simply have a `typeMismatch.freePasses`, not related to a `customer`, any field that has `freePasses`.
Or you can have a `typeMismatch.java.lang.Integer` on any field that has integer,
or you can have just a more generic `typeMismatch`.
So, basically, it goes from very specific starting at the left to very generic all the way over to the right.
But we want to make use of that first one there 
because we want it to be specific only for that given specific field.
So you can use this same approach on your own project.
You simply just do a print line on the binding result and that'll give you all the codes
that Spring's using or looking for, and you can simply override it accordingly.
So just a nice little debugging trick there for you where you can override the error code
and give your own custom message.
So you can apply this same technique on your own project.

</div>

### [Custom Validation]()
<div style="text-align:justify">

In this video, we're going to learn how to perform custom validation with **Spring MVC**.
So, let me give you a quick demo here of the custom validation.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image81.png" alt="image81">
</div>

So, we're going to have a form that has three fields for first name, last name, and course code.
So, I can go ahead and enter the first name, last name, and a course code.
The course code is simply an identifier for a course.
Now we're going to attach a custom business rule here to this course code.
If I hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image82.png" alt="image82">
</div>

notice it gives a validation error.
It says `the course code must start with LUV`.
That's the custom business rule that we're adding here for this course identifier.
We'd have to go back and add a course code that starts with `LUV`,
and then everything will work out just fine when we hit the `submit` button.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image83.png" alt="image83">
</div>

So, notice our course code and it starts with `LUV`.
That's our custom business rule that we just made up.
So, I'll actually show you how to write the code behind the scenes
that will allow Spring to perform the validation using your own custom validation rule,
or your own custom business logic.

So, with the custom validation, as I mentioned,
we're going to create our own custom business rule here.
So, in our example, the course code must start with `LUV`.
And what we're going to do,
**Spring** will actually call our custom validation rule,
and if there's an error, then we'll actually display it on the page.
So, this custom validation is going to return a Boolean value of true or false,
based on the code that the user entered on the form.
Allright, so we'll actually do something really fun here.
We're going to create a custom Java annotation from scratch.
So far, you've used some of the predefined validation rules like `@Min`, `@Max`, 
but what we're going to do here is we're actually going to create our own custom Java annotation.
And our custom annotation is going to be called `@CourseCode`.
So, `@CourseCode`, this is a validation rule that will actually apply to a given field.

````java
@CourseCode(value="LUV", message="must start with LUV")
private String courseCode;
````

So, we'll have this field in our entity.
We'll call this field `courseCode`, lowercase,
and then we'll add this annotation `@CourseCode`.
That's the custom annotation that we're going to create.
This annotation, we can give it any name.
We can call it `@FooBar`, `@Luv2Code`, `@CrazyStuff`.
Any name you can give to this annotation.
This annotation is going to take in two parameters here.
It's going to take in the actual `value` that it must start with, 
and then an actual error `message` that's displayed on the HTML form,
and it's kind of parameterized right now,
but I'll show you how to kind of write all the code for this annotation from scratch.

Now, we always have our step-by-step process for it:

* Create the custom validation rule
* Add that rule to the **Customer** class
* Display the messages to the HTML form
* Update our confirmation page

So, steps two through four, you've done that many times before.
We're simply going to focus on step number one, creating that custom validation rule.
So, step 1A is we have to create that `@CourseCode` annotation.
And then we need to create the `CourseCodeConstraintValidator`.
This is where we'll actually place our own validation logic or our own business rules 
to determine `true` or `false` if the value passes our validation.
So, that's where we'll put our own custom logic.

Okay, so let's go ahead and look at step one, creating the `@CourseCode` annotation.
Now, first I just want to show you the usage example of how we're going to use our annotation.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image84.png" alt="image84">
</div>

So in your entity class, you'll have a field.
And then, it'll make use of the `@CourseCode` annotation.
So we're going to attach this validation rule to this given field,
and I'll pass into parameters for it, the `value` and the `message`.
So `value` is the actual value it must start with,
and then the `message` is the actual error message that you can use on the HTML page.

Allright, so step 1a, creating the `@CourseCode` annotation.
So the first thing to notice here is that we're creating this annotation.
Now when you create an annotation, you make use of some special syntax.
So I'll move over here to my project and I'll move down to the java under `src.main.java.com.luv2code.springdemo.mvc`,
and I'm going to create a package.
And I'll pretty much keep the same package structure.
I'll just say `.validation`.
Now I need to add something to it.
Allright, so the first thing we're going to do is add a new annotation.
So our annotation is called `@CourseCode` and that's the value that we'll enter there.

````java
package com.luv2code.springdemo.mvc.validation;
public @interface CourseCode {
}
````

it helped us out by setting up our annotation note on line three,
to have that special notation, `@interface CourseCode`.
Allright, so this is the basics here.
In fact, this is how you define a custom annotation for anything in **Java**.

````java
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CourseCodeConstraintValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    
    //...
    
}
````

Then up top, we add some additional annotations to describe this annotation.
So up top, we have `@Constraint`.
It's going to be `validateBy`, and you give the actual class that has the business rules
for validating this process, so `CourseCodeConstraintValidator.class`.
We'll cover that in step 1b.
That's coming up.
Then, we have `@Target`.
So this basically says, `hey, where can you apply this annotation?` or `where can you list this annotation?`
So here we're going to say you can use this annotation on a method or on a field.
So either way, a method or a field.
Then for `@Retention`, it says, `How long should we retain it?`.
So there's different types here.
Here we're going to say `RetentionPolicy.RUNTIME`,
meaning keep this annotation in the compiled java bytecode,
so we can use it and introspect on it and instrument on it during runtime.
So that's the basics there with setting this up.

Now, just taking a look at some other items here, 
we actually have to set up parameters here for this annotation.
And this annotation is going to have two parameters, `value` and `message`.
Alright, so now I'll actually add some method declarations here.

````java
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CourseCodeConstraintValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    
    // define default course code
    public String value() default "LUV";
    
    // define default error message
    public String message() default "must start with LUV";
    
    // define default groups
    public Class<?>[] groups() default {};
    
    // define default payloads
    public Class<? extends Payload>[] payload() default {};
    
}
````

So we have `public String value()`.
That basically says this annotation has a parameter called `value`.
Now if the user doesn't provide a `value`, then we'll use the default value of `LUV`.
And then, we'll also add another method here for `message`, so `public String message()`.
And if they don't give an actual message, we'll default to `must start with LUV`.
Now you're probably wondering, well, hey, why are you using these defaults, and you're passing in values here?
Well, I want to keep this annotation customizable.
I could have hard coded those values in there, but I want to keep it customizable.
So we could actually apply `@CourseCode` and give a different prefix.
I'll actually show you how we can customize the usage of this annotation in the following demos.
That's the basics here on actually defining the `@CourseCode` annotation.
Then, we'll also make use of two other entries here for this annotation for groups and also for payloads.
So groups is where you can actually group validation constraints together.
And then, payload is where you can give additional information about the validation error.
Allright, so that's kind of like the basic layout here of kind of things 
that we need to do and things that we need to fill in.
Then, we set up our default groups.
And so here we're not going to use any grouping of grouping constraints.
So we'll simply just give the basic boilerplate here of groups,
and we'll simply just give an empty collection.
And then for payloads, we're not going to use any payloads.
So we'll simply just give the default here for payload.
And again, payload can basically give you additional details about the error message that has occurred.
And we went through, and we listed the attributes that the user can call or pass in to this given annotation.
So we went through and defined the default course code, error message, and then also groups and payloads.
And so the two key things that we're going to use in this demo
is that we're going to make use of the actual value for course code and the message for error messages.

So, we have our annotation taken care of.
We still need to fix this `CourseCodeConstraintValidator`.
I'll move into my validation package, and I'll create a new class, `CourseCodeConstraintValidator`.
We have the basic layout here for our class.

````java
package com.luv2code.springdemo.mvc.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    @Override
    public void initialize(CourseCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
````

Now I know this class has to implement the constraint validator interface.
So I'll say `implements ConstraintValidator`.
Then I'll pass in `CourseCode` and `string`.
So, now we need to override or implement the methods of the constraint validator interface.
The IDE can help us generate the method stubs.
As you can see, this is some nice stub code that was generated by the IDE force.
It generated two methods here.
There's an `initialize` method, that we have to implement or override,
and also there's the `isValid` method, and that's where we'll put our actual business logic
to return `true` or `false` if the given string validates.
So, what I want to do is define a course prefix that we can validate it against.

````java
package com.luv2code.springdemo.mvc.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;
    
    @Override
    //public void initialize(CourseCode constraintAnnotation) {
    public void initialize(CourseCode theCourseCode) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
````

So, this is the actual prefix that they've set up with the annotation to say,
"_hey, we validate it against a given prefix_".
I need to actually assign this when this annotation or constraint validator is initialized.
So, here I'll say `coursePrefix` equals `theCourseCode.value`.
That `.value()` actually accesses the attribute value for that given annotation.
Here, it'll actually be `LUV`, but we could easily customize that to be something different.
Allright, so we have the basic setup.
Now let's write our real business rule here.
Check and see if something is valid.

````java
package com.luv2code.springdemo.mvc.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;
    
    @Override
    //public void initialize(CourseCode constraintAnnotation) {
    public void initialize(CourseCode theCourseCode) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (theCode != null) {
            result = theCode.startsWith(coursePrefix);
        } else {
            result = true;
        }
        return result;
    }
}
````

That first parameter coming is the actual text that the user entered on their HTML form.
And then the second argument is the `constraintValidatorContext`.
That's just an actual parameter that we can use to give additional error messages
if we need it for this given validation routine.
Now let's go ahead and do some of our work inside of this method.
So, basically we want to check to see if something's `true` or `false` and give a `result`.
So here I can say if `theCode` is not equal to `null`,
then I'll set the boolean `result` equals `theCode.startsWith(coursePrefix)`.
and I put the curly brace and then else I'll actually return the value of `true`
because "_hey, you didn't pass anything, there's nothing for me to validate_"
and we'll let you go on because this field is not required.
And that's basically our business rule or our business logic.
If you're writing your own custom validation rule,
you can really do anything that you want in this method.
You can talk to a database, call a web service, a REST API,
do some special number crunching.
Whatever you need to do, you can do it inside of this `isValid` method.
It's totally up to you.
So all you have to do is simply return a `true` or `false` based on your own custom business logic.

Now step two, we'll simply add the validation rules to the `Customer` class.
So at this point, we simply need to open up our `Customer` class, our entity,
and actually place the rule there.

````java
public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    @CourseCode
    private String courseCode;

    // getter/ setter methods and constructor
}
````

I need to create a field here for `courseCode`.
So I'll just create a field `private String courseCode`.
And then I'll also generate some getters and setters here for this field.
Allright, so now let's go ahead and do the real work.
Let's drop our `@CourseCode` annotation.
And we're not going to pass in any attribute values here.
We're simply going to use the defaults.
Because we've already coded it accordingly to handle the appropriate defaults.
So we're in good shape with this so far.

Now I need to display the error messages on the HTML form.
And again, just follow the same process I've done before for displaying the error messages.
So I just need to add a new field here for course code.

`````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration Form</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>
    <form th:action="@{/processForm}" th:object="${customer}" method="POST">

        First name: <input type="text" th:field="*{firstName}" />
        <br><br>
        Last name (*): <input type="text" th:field="*{lastName}" />

        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span>
        <br><br>

        Free passes: <input type="text" th:field="*{freePasses}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('freePasses')}" th:errors="*{freePasses}" class="error"></span>
        <br><br>

        Postal Code: <input type="text" th:field="*{postalCode}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('postalCode')}" th:errors="*{postalCode}" class="error"></span>
        <br><br>

        Course Code: <input type="text" th:field="*{courseCode}" />
        <!-- Add error message (if present) -->
        <span th:if="${#fields.hasErrors('courseCode')}" th:errors="*{courseCode}" class="error"></span>
        <br><br>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
`````

So I'm just going to copy postal code,
and I'll just do a little copy-paste exercise and drop postal code below,
but I'll go through and rename this to `Course Code:` and `courseCode`.
Now moving ahead to step four, updating our confirmation page.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Confirmation</title>
</head>
<body>

    The customer is confirmed: <span th:text="${customer.firstName + ' ' + customer.lastName}" />
    <br><br>
    Free passes: <span th:text="${customer.freePasses}" />
    <br><br>
    Postal code: <span th:text="${customer.postalCode}" />
    <br><br>
    Course code: <span th:text="${customer.courseCode}" />

</body>
</html>
````

We simply want to echo whatever the value the user entered.
So again, I'll just do another little copy-paste here,
and I will say course code and then a dollar sign `customer.courseCode` and save that, and there we go.
So we'll just echo whatever the user entered on their HTML form.
I'm eager to run this application.
So let's go ahead and run it and test it out.
And I'll just go ahead and enter some data here,
for last name and then hit the `submit`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image85.png" alt="image85">
</div>

Now let's actually enter a code, and let's enter a bad code like `ABC123`.
And this should fail: 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image86.png" alt="image86">
</div>

And yeah, so it says that it must start with `LUV`.
I'll start it with `LUV` and hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image87.png" alt="image87">
</div>

There we go.
Course code `LUV123`.
Now what I want to do is actually customize this such that it's not using `LUV`
but instead using something different, a different code.

````java
public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    //@CourseCode
    @CourseCode(value="TOPS", message="must start with TOPS")
    private String courseCode;

    // getter/setter methods and constructor
}
````

So here I'll customize it by saying, must start with `TOPS`.
I'm just giving a customized version of this annotation and making use of it.
So I'll let my container go through in the background and reload.
Allright, so for my course code here, I'll just enter some bad data like `ABC123` and hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image88.png" alt="image88">
</div>

So notice here it's saying `it must start with TOPS`
because we've changed our source code now to actually validate against this new prefix.
So then I'll go ahead and hit `TOPS123`, hit `submit`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/06-spring-boot-spring-mvc/images/image89.png" alt="image89">
</div>

and it passed.
So we have our annotation up and running.
We can even customize our annotation as far as what code or prefix to use.
And it all works out fine with the Spring MVC validation.

</div>