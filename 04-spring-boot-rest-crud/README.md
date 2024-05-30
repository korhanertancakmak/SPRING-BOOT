# [REST CRUD APIs]()

## [What Are REST Services?]()
<div style="text-align:justify">

In this section, we'll cover **REST API**s and **REST Web Services**.
So in this section series, you're going to:

* Create **REST API**s, web services with **Spring**
* Discuss rest concepts, JSON and HTTP messaging
* Look at how to install the **REST client** tool: `Postman`.
* Develop **REST APIs / Web services** with a `@RestController`
* Build a `CRUD` interface of the database using **Spring Rest**

Now the main focus here is that we want to focus on practical results.
So this is really just an introduction to **Spring Rest Development**.
The important thing here is that it's not an A to Z reference for that,
You can see the **Spring Reference Manual** and have a link 
[here](https://www.luv2code.com/spring-reference-manual).
That'll redirect you to the official **Spring** website.

So let's look at the business problem.
What are we trying to solve?
So we want to build a client application to provide the weather report for a city.
And so we need to get the weather data from an external service
like we don't have a local database of weather information because it changes all the time, right?
So we need to get that from an external service.

![image01](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image01.png?raw=true)

So looking at the application architecture, 
we'll have this `My Weather app` that's an app that we're creating.
So this is kinda like the **client** application.
And then we'll have this `weather service`. 
It's an external service provided by some third party.
So that's kinda like the backend **server**.
And what we'll do with our weather app is that we'll simply pass over a string for the city 
and then this weather service will return with the actual weather report.

So that's the architecture, but you may have some questions like:

* _how will we connect to the weather service?_
* _what programming language do we use?_
* _what's the data format?_
Is it **csv**?
Is it **xml**?
Is it some other weird format?

Alright, so let's get some answers to the questions here.
And let's start with the first one here.
**How will we connect to the Weather service?**
Well, we can make **REST API** calls over **HTTP**, 
so **REST** stands for the `Representational State Transfer`.
It's really just a lightweight approach for communicating between applications.

Then the next question as far as **what programming language do we use?**
Well, **REST** is language independent.
So the **client** application can use **ANY** programming language,
and the **server** application can use **ANY** programming language.
So it's totally up to you as far as which language that you'll use for your application.
And this is actually a really good thing because this gives us flexibility 
on the actual implementation on the client side and also on the server side.

Now the next question, **what is the data format?**
Well, **REST** applications can use any data format.
So you'll commonly see **XML** and **JSON**.
With **JSON** being the most popular because it's modern.
JSON stands for the `JavaScript Object Notation`.
We'll see a small example of **JSON** here in this section set.
And then in the following section, I'll talk a bit more about the **JSON** syntax.

So a possible solutions that we can use `www.openweathermap.org`.
So they basically have weather data via an API that you can use.
They give you documentation on how to use it.
The data's available in **JSON**, **XML** or **HTML** format.

```html
api.openweathermap.org/data/2.5/weather?q={city name}

OR

api.openweathermap.org/data/2.5/weather?q={city name}, {country code}
```

So call this weather service.
The API documentation gives you some information.
So it says pass in the city name.
So you can go to `www.openweathermap.org`.
You can pass in the city name, or you can pass in the actual city name, 
comma the country code because you may have a very common city name.
And then you'll actually get the weather report.

![image02](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image02.png?raw=true)

That's the response here.
So the **Weather Service** is going to respond with the **JSON**.
So they'll basically respond back with the temperature,
the temp mim, max, humidity, the name of the city, and so on.
And this is really just a condensed version of the actual weather report.
But notice here is being passed back as **JSON**.
So **JSON**'s really just a collection of name value pairs.
And then your application can parse this string and then process on it accordingly.

![image03](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image03.png?raw=true)

Now the really nice thing about making use of this approach with this **REST API** 
or **REST web service** is that this weather service that was developed
by that one group open weather map they simply provide the data feed.
So they're the external service.
And then you can have different types of clients that can access it.
So I could maybe create a client application using **Spring MVC**, Java access in this application, 
or I could make use of a maybe a **C#** application talking to this weather service.
And also I could maybe build an iPhone app using **Swift** communicating with this weather service.
So again, remember the **REST** calls can be made over **HTTP** 
and also **REST** is language independent.
So it doesn't really care what language that you're using.
As long as you can process the data being passed back, then everything's okay.
And like I mentioned earlier, that's a very good benefit.
So it gives you flexibility on the actual implementation language.

Now let's look at some other **REST** examples or scenarios 
just to kind of help you out as far as getting your head around it.
We could also create a **Currency Converter App**, right?

![image04](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image04.png?raw=true)

So we have this currency app, and then over here on the other side, 
we have this currency service.
And so then I could say _I need to convert **US dollars** to **Indian Rupi**_.
You pass over the amount and then this application will actually respond with the actual value.
Now again, this is just a demo, right?
This value is going to actually fluctuate based on the market,
but the key here is that, you know, we have an application that can use this external service,
send over a request, and then get a response.

![image05](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image05.png?raw=true)

And then also we could look at another scenario like a **Movie Tickets App**, right?
So we have this my movie app, and then
there's some external service that gives you information about movie tickets in your area.
You could say, "_Hey, I want to look at this given movie at a given location for a certain time_"
and then they'll respond with a list of movie results.
And so then you can go through and choose, 
"_Okay, I'll choose this theater over here_" or "_that theater over there_".
But again, this movie ticket service, they're saying,
"_Hey, we're simply passing back the data._"
You can use any programming language, 
any type of app or platform to process and render those results.

![image06](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image06.png?raw=true)

And then finally here, we're making it to our **Customer Relationship Manager**, the **CRM** app.
So we have a **CRM** app, and then we have a **CRM** service.
So basically they can access the list of customers by saying,
"_Hey, get the customers_", and then we'll actually send back that list of customers, right?
So instead of doing it directly in the **HTML** page or **JSP** page or webpage
we're simply going to pass back this json data or this whatever data format we choose
and then the **CRM** app can render it accordingly.
So it could be a web app, it could be a mobile app or any other type of application there.
And so **we'll actually create this code for the server in this course**.
So we'll actually create a **CRM service** that's going to pass back customer data 
as json, and we'll actually write all the code for that from scratch in the following sections.
Now you're probably thinking, like
_Well, who would ever send back customer data via a rest service?_
Well, how about Salesforce, right?

![image07](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image07.png?raw=true)

That very small company called Salesforce, actually a huge company.
They actually have a huge platform based on **CRM**,
and they also have a **REST API** for providing customer data from a given application.
So we're not, you know, off our rocker here, there's a very good use case for doing this.
And so we'll kinda build out a version of this for our **CRM** application.

![image08](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image08.png?raw=true)

Now, here's the common question that I get: **What do we call it?**
Do we call it `REST API` or `RESTful API` 
or do we call it `REST web services` or `RESTful web services`?
Or what about `REST services`, `RESTful services`?
Okay, so generally they all mean the same thing, alright?
Basically, you're building an **API**, you're building a **service**,
and you're providing an **endpoint**, 
and you're providing data based on inputs or requests and so on.
So that's the big idea, is that they're really all the same.
So don't get tied up or get mixed up with; you know really trying to pin it down.
You'll hear folks use these terms interchangeably,
so at a very high level, they're generally all the same.

Alright, so that was just a quick overview of **REST API**s, **REST services**, 
**RESTful services** and in the following sections,
we'll start to drill down a bit more, and we'll get some working examples up and running.
</div>

## [JSON-REST HTTP-Postman Basics]()
<div style="text-align:justify">

So, what exactly is **JSON**?
As I mentioned earlier, **JSON** is the `JavaScript Object Notation`.
It's really just a lightweight data format for storing and exchanging data.
It's just plain text.
And the really nice thing about **JSON** is that it's `language independent`.
So it's not just for **JavaScript**.
In fact, you can use **JSON** with any programming language, 
such as **Java**, **C#**, and **Python**.
And the reason being that, again, it's `just plain text`.
Just plain text data that any programming language can use and read.

![image09](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image09.png?raw=true)

Now let's go ahead and look at a very simple **JSON** example.
So here you're defining an object, okay?
And so in the **JSON** world, you make use of curly braces to define the object.
And then you have the object members which are simply name / value pairs.
And the name value pairs are actually delimited by colons.
So here in this example, I have the name, on the left-hand side with a colon,
and then the actual value.
And whenever you define a name in **JSON**, the name is always in double quotes.
So whatever you have on the left-hand side of that colon will always be in double quotes.
So let's go ahead and look at some **JSON** values.
So remember, the JSON value is actually on the right-hand side of the colon.
So that's the actual value.
You can have different types of values here.
So you can have numbers.
Whenever you give a number in **JSON**, the number is with no quotes.
You can also add strings.
And the strings are always in double quotes.
So here for _firstName_, `Mario`, that's the actual value.
That'll be in double quotes.
You can also make use of booleans, like `true` or `false`.
So here in this example where we have _active_, the value can be `true` or `false`.
They also have support for nested **JSON** objects.
You can also make use of arrays in **JSON**, and I have examples of that coming up.
And then finally, you have a reference to something that points to nothing or `null`.
So in this example here for _courses_, this given student doesn't have any courses.
So that reference there is `null`.

![image10](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image10.png?raw=true)

**Nested JSON Objects** are just like any other nested item like a nested four loop or something.
Basically, one item inside of another.
So in this case, we have a nested **JSON** object.
So here for this given item, for address, it's another nested object here.
So the address, and then we have the curly braces again to define an object in **JSON**.
And then we have the different fields here, _street_, _city_, _state_, _zip_, _country_, and so on.
And you can nest as many levels deep as you'd like for your given **JSON** object model.

![image11](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image11.png?raw=true)

And there's also support for arrays in **JSON**.
So in this example here, I have languages, 
and then I simply have an array of languages that a given student is learning.
So in **JSON**, you make use of arrays with a square bracket,
and you simply give a comma delimited list of items here.
So in this example, it's just a comma-delimited list of strings.
You can have an array of any type of values out there that you'd like.

Alright, so this is kind of just the basics here with **JSON**.
In some of the following sections, we'll actually show you how to read **JSON**,
and we'll read all the different value types like numbers, strings, booleans.

Let's look at now **REST HTTP** basics.
So the most common use of **REST** is over **HTTP**.
So we can actually leverage the _HTTP_ methods for **CRUD** operations.

| HTTP Method | CRUD Operation                           |
|-------------|------------------------------------------|
| POST        | Create a new entity                      |
| GET         | Read a list of entities or single entity |
| PUT         | Update an existing entity                |
| DELETE      | Delete an existing entity                |

So in this diagram here, or this table actually, whenever you send over a `POST` request,
then this can translate to the **CRUD** operation to create a new entity.
When you send over a `GET` request, this can translate to the **CRUD** operation 
for reading a list of entities or a single entity.
For the `PUT` method, this is for updating an existing entity.
And then for the **HTTP** `DELETE` method, this can be used for deleting an existing entity.
And we'll actually write the code for this in some of the following sections,
where we'll build a **REST controller**. 
Then we'll have support for these given **HTTP** methods,
and it'll translate to the appropriate **CRUD** operations that will perform on our database.

![image12](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image12.png?raw=true)

Now let's just kind of take a look at these **HTTP** methods that we'll send back and forth.
So we have this idea of our **client** application, and we also have our **server** application.
So the client's going to send over **REST** requests to a server or a **CRM REST service**.
So we'll send over this **HTTP** request message that'll have data going across,
and then our server can process it, and then our server will send back an actual response,
an **HTTP** message response.
Now let's go ahead and kind of break this down a bit,
and see what's in the request message and also what's in the response message.

![image13](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image13.png?raw=true)

Now, the actual request message has three main parts.
It has a `request line`, `header variables`, and `message body`.
So the **request line** has the actual **HTTP** command or method.
So this has the `GET` method or the `POST` method or the actual `DELETE` method.
The **header variables** have the request metadata, so additional information about this request.
And then the **message body** has the contents of the message or the actual payload.
So if you're adding a new entity, 
then the actual contents of that entity will be in the message body as **JSON**,
and we'll see examples of this coming up in some later sections.

![image14](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image14.png?raw=true)

Now let's look at a response message.
And response messages have three different areas also.
So it has a `response line`, `header variables`, and a `message body`.
So the **response line**, that's the actual server protocol and status code.
So here the status code could be one of the HTTP status codes, like 200, 404 or 500.
The **header variables**, that's the response metadata.
So this is the actual information about the data.
So the content type of the data, if it's **XML**, or if it's **JSON**,
the size or length of the data.
And then there's also there's the **message body**, so the contents of the message.
So if you said, "_Hey, give me a list of all customers_"
then that list of data will actually come back in the message body as either **XML** or **JSON**,
depending on how the server's configured.

| Code Range | Description   |
|------------|---------------|
| 100 - 199  | Informational |
| 200 - 299  | Successful    |
| 300 - 399  | Redirection   |
| 400 - 499  | Client error  |
| 500 - 599  | Server error  |

Now for the **HTTP** response, you can send back a status code to give information about the status.
So you have codes in different ranges.
So anything in the range 100 that's for informational,
the 200 range is for success,
the 300 ranges are for a redirection,
the 400 ranges are for client error,
and then we also have the range 500 for a server error.
Now, you've seen some of this before.
So for the 400 series, if you had to access a secure server,
it would send back a 401, meaning authentication required.
And then also you've all seen the 404 error, the dreaded File `Not Found` error.
And then in the 500 series you've seen this one also, right?
The 500 `Internal Server Error`.
Alright, so these are basically status codes;
the server returns that as part of the **HTTP** response.

And then we also have the idea of **MIME** content types.
So this is basically the message format for the actual payload.
And so it's described by a **MIME** content type.
So what does **MIME** mean?
Well, **MIME** stands for the `Multipurpose Internet Mail-Extension`.
So you'll normally hear folks refer to it as **MIME**.
So again, it simply describes the actual content or the format of the message being returned.
You have the basic syntax for it: `type/sub-type`.
And then here are some examples:

* text/html
* text/plain
* application/json
* application/xml

So this is information that's returned to the client,
and then the client can render it accordingly, or process on it accordingly.
So some examples here, if you return back `text/html` to a web browser, 
a web browser will render that based on the **HTML** tags.
You pass back `text/plain`, the web browser will simply just show you the plain text in the browser.
That's just for a web browser example.
You can also make use of **RESTful** clients for that.
And so in particular for **RESTful** clients, we can pass back `application/json`.
So we can tell the client, "_Hey, we're returning **JSON** data for you._"
Or `application/xml` saying, "_Hey, this content coming back is **XML**._"
And again, the client can process on it accordingly.

Alright, so now we also need a **Client Tool**.
So this client tool is something that we can use 
to send **HTTP** requests to the **REST web service / API**,
and then it'll get the response, and we can actually view it there in the tool.
So there are plenty of tools that are available like `Curl`, which is a command line tool, 
`Postman`, a GUI tool, and a lot of others.

So in this course, we'll make use of `Postman`.
So **Postman** is a really popular tool used for realtime projects.
It's basic, very easy to use, and a lot of people like to use it.
So it's very common.
You'll see it on a lot of projects.
So you can actually download Postman from this website [here](https://www.getpostman.com), 
and they also have a free developer plan.
So it's a free piece of software that you can make use of 
for testing out and accessing your **REST** applications.

Now, I won't personally walk you through all the installation steps.
It's really simple, really easy to install on your computer.
So in the following sections, I'll assume that you already have **Postman** installed,
and then I'll show you how to use it for testing out **REST** applications.

Let's go ahead and get started with **Postman**.
Now we're simply going to use it and just play around with some of the basic features.
So let me go ahead and fire up **Postman** and get it running on my computer.
Now, on the first screen, they may prompt you to log in.
It's not a requirement to log in or create an account.
At the bottom of the screen, they have a link here that says `take me straight to the app`.
So just go ahead and choose that one.
And you don't have to give any user IDs and password.
You can simply use the app as it is.
So they'll probably take you to the main screen,
and they'll give you like a template or launch wizard or whatever.
We're not going to use it here.
We're going to go ahead and use just the basic application.
So I'll go ahead and close this window.

![image15](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image15.png?raw=true)

Alright, so just a quick tour of the application.
At the top you'll have your **HTTP** request and then at the bottom, 
you'll have the **HTTP** response.
So here I'll kind of start at the top with the **HTTP** request.
You can use different **HTTP** methods.
`GET`, `POST`, `PUT`, so on and so forth.
We'll use some of these later on in the sections.
You can give the extra URL.
You can look at **authorization** items, **headers** and **body** if we're using like a `POST` and so on.
And we'll use most of these features as we go through the course.

So let's go ahead and swing back over to our browser.
And in our browser what we'll do is we'll actually go to this website `jsonplaceholder.typicode.com`.
Again, they have more endpoints that we can use to actually access some **REST Services**.
And the reason I want to try this one out is because they have
I guess more complex responses coming back with nested objects and so on.

![image16](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image16.png?raw=true)

So the one item that they have here is the `/users` item.
That's going to return a list of users.
We can actually access this link directly 
because most web browsers are smart enough to render **JSON** content.

![image17](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image17.png?raw=true)

So we can see this long list of all the users here,
and we're reviewing this directly in **Firefox**.
You can do a similar thing in **Chrome** or another browser,
but I'd like to test this out in **Postman** also.
So I'll go ahead and just copy this URL, and then I'll swing back over to **Postman**.
And in this new tab that we have, we'll just paste in that URL that we just set up.

![image18](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image18.png?raw=true)

Right, so I have a `GET` on `/users`, and then I'll do a `SEND`.
I'll send over this request, and then I get a response.
So this is very similar to what we saw in the browser,
but the nice thing about using **Postman** is that you can get additional data,
more than what you can get in the browser.
So here we have the status code of 200, and we see the big long list of everything that's coming back.
And also note here, you know, we have this user, list of users.
They also have nested objects, and we saw examples of nested objects earlier in our course
and a nested object within a nested object.
So you can have a lot of fun there.
So just this big long laundry list of all the users,
and it's set up as an array up top on line one with a square bracket.

![image19](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image19.png?raw=true)

And we can also see that the headers here, all these different headers that were set by that given server.
And again, we click the content type of `application/json`.
And just tons of other things out there as far as additional metadata
or headers that were sent back with this given response.
Alright, so that's basically it as far as doing `GET` request.
We'll use this **Postman** a lot during the next set of sections,
and we'll check out some of the other features.
We'll do the `PUT`, `POST` and `DELETE` for our `CRUD` operations.
</div>

## [Spring Boot REST Controller]()
<div style="text-align:justify">

In this section, we'll learn how to develop a **Spring REST Controller**.
Alright, let's go ahead and set up a **Spring REST Hello World** example.

![image20](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image20.png?raw=true)

So we'll have this **REST** client, we'll also have this **REST** service,
and what we'll do is we'll basically make a request for a `/test/hello`.
This **REST** service will respond with `Hello World!`
So on the far right-hand side, we'll actually write the code here for the **REST** service.
On the left-hand side, as far as the **REST** client, 
we can make use of either the web browser or we can make use of **Postman**,
and I'll show you how to use both of them.

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
```

Alright, so here's the basic coding here for a **Spring REST controller**.
So we have this `@RestController`, `@RequestMapping/test`, and then `public class DemoRestController`.
So this looks like a normal **Spring MVC(Model-View Controller) controller**.
However, now we're making use of this `@RestController`, so this adds the **REST** support.
So then in the actual code, we simply set up a mapping.
So here we'll set a `@GetMapping` for `/hello`.
We write our method, public string, _sayHello_, and then we simply return `Hello World!`.
Basically, at this point we've defined this **RESTful** endpoint, or this path that we can access.
We can access this **REST** endpoint by simply going to `/test/hello`.
And now this will actually return to the client, `Hello World`, back to the actual calling program.

Now, let's talk about clients here.
So, we can test this with the **REST** client using **Postman**.
So in **Postman** we simply drop in the URL, 
we simply go to `http://localhost:8080/{theNameOfOurApp}/test/hello`.
So that'll access that **REST** endpoint.
And so at the bottom we'll actually get the response, `Hello World!`.
So that's an example here using **Postman**.
Now, we could also do a similar thing with the web browser.
So in the web browser you can simply drop in the URL, `/test/hello` 
and it'll return the response here, `Hello World!`.

So you're probably wondering, "_Hmm, web browser or Postman?_"
Well, for simple **REST** testing for `Get` requests,
the web browser and **Postman**, they're similar.
However, for advanced **REST** testing, like using `POST` or `PUT`, etc. 
then **Postman** has much better support.
So if you need to `post` **JSON** data, `set` the content type,
`pass` over request headers, do `authentication`, then it's much easier to do this using **Postman**.
So **Postman** is really designed out of the box as a **REST** client testing tool,
so **Postman** has a lot of rich support here.
For the simple stuff you can use the browser, 
but for really advanced development you'll need to use **Postman**.
But in the sections I'll show you how to use both of them going back and forth, browser and **Postman**.
And then we'll kind of reserve **Postman**for some of the more advanced stuff 
that we'll do later on in the course.

So let's cover the **Spring RestController** development process.

1. Add the **Maven Spring** dependency for **Spring Boot Starter Web**,
2. Create the **Spring REST Service** using the `@RestController` annotation.

```xml
<!-- Add Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <articatId>spring-boot-starter-web</articatId>
</dependency>
```

Here's step 1: Adding the **Maven** dependency.
In your `pom.xml` file, you'll add an entry here for the **Spring Boot Starter Web**,
and instead of adding it manually, you can have this selected at the **Spring Initializr** website.
On that website, When you're setting up your project, simply select the **Web** dependency.

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
```

So this is just like the code we saw over earlier for building in our **Spring REST service**.
We simply write our `@RestController`, here we have `@RequestMapping("/test")`,
and then we simply write the endpoints on the mappings here.
So `@GetMapping("/hello")`, return `Hello World`.
So that's basically it here for actually building out the **REST Service**.

Let's open up a web browser.
And so the first step is configuring a project at the **Spring Initializr** website.
So let's go ahead and visit `start.spring.io` in our web browser.
Alright, so we're at the **Spring Initializr** website.
Here, we'll select **Maven** and **Java**.
And then, also, as far as the **Spring Boot** version,
I'll choose this one listed `3.3.0`.
So choose the most recent version that they have here.
Avoid the snapshot versions because they are **alpha/beta**.
So just choose the latest released version.
I guarantee you the version that you see on your computer 
will be much different than what's recorded 
because new versions of Spring Boot are released on a very frequent basis.
So simply just choose the latest released version.
Alright, so that's taken care of.
We can move into our project metadata where we can set up our coordinates here.
So I'll set up the group ID `com.luv2code` and artifact `demo`.
And for dependencies here, this is where we go through 
and basically just choose the **Spring Boot** starters 
that we want or the actual dependencies that we want for our application.
So here, I'll just keep it simple. 
I'll just choose web.
So be sure to click that option and make sure it's selected, and it appears here.
That's for its selected dependency.
And then, from there, just go down to the bottom 
and download the ZIP file by clicking on `Generate` Project.
And I can swing over to my file system, and the next step is unzipping that file.
So I'll just move into my `Downloads` directory on my computer.
I'll unzip it.
Now, what I'll do is I'll move into our `dev-spring-boot` folder,
and I'll create a new folder here.
And I'll call it `04-spring-boot-rest-crud`.
And I'll go ahead and move into this folder.
And then I'll go ahead and drag and drop that `demo`down into this folder, 
`04-spring-boot-rest-crud`.
And then I'll simply rename this `demo` folder.
And I'll call it `01-spring-boot-rest-crud`.
Now, let's go ahead and open this in IntelliJ.

Now, I think we can start working on writing some code here for our **REST** controller.
So I'll move back over to the project.
And what I want to do is actually set up a new package here for our **REST** coding.
So I'll just grab this one package, and I'll create a new one, `.rest`.
And so this is where I'll place my **REST** controllers.
So I'll go ahead and create a new class here for this **REST** controller.
And the actual name I'll give for this, I'll call it **DemoRestController**.

```java
package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    // add code for the "/hello" endpoint
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
```

And so we'll set up that **RestController** and then `@RequestMapping`.
So again, we're saying, hey, this is some **REST** support,
and then we set up the mapping for it.
Alright, so let me just write a quick comment here to myself.
So I'm going to add code for the `/hello` endpoint.
And basically, I'll simply just return `Hello World`.
So I'll just set up this `@GetMapping` and give the path here, `/hello`.
Create a method.
It's going to return a string called _sayHello_.
And keeping it very simple, right?
Just return `Hello World`.
So that's basically it as far as the coding here for this **REST** controller.
So I have this endpoint `/hello`, and I simply return `Hello World`.
All right, so let's go ahead and test this out and let's see it work.

```html
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-27T17:51:51.148+03:00  INFO 88180 --- [demo] [           main] com.luv2code.demo.DemoApplication        : Starting DemoApplication using Java 21.0.2 with PID 88180 (D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\01-spring-boot-rest-crud\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\01-spring-boot-rest-crud)
2024-05-27T17:51:51.150+03:00  INFO 88180 --- [demo] [           main] com.luv2code.demo.DemoApplication        : No active profile set, falling back to 1 default profile: "default"
2024-05-27T17:51:51.841+03:00  INFO 88180 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-27T17:51:51.851+03:00  INFO 88180 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-27T17:51:51.851+03:00  INFO 88180 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-05-27T17:51:51.897+03:00  INFO 88180 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-27T17:51:51.898+03:00  INFO 88180 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 710 ms
2024-05-27T17:51:52.138+03:00  INFO 88180 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-05-27T17:51:52.144+03:00  INFO 88180 --- [demo] [           main] com.luv2code.demo.DemoApplication        : Started DemoApplication in 1.282 seconds (process running for 1.57)
```

All we have to do is go up to this URL bar 
and just type in `localhost:8080/test/hello` and cross your fingers.

![image21](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image21.png?raw=true)

There's `Hello World`.
So that's the response here from our **REST** controller or our **REST** service.
Alright, now let's take this URL here and let's just copy it
because we want to put this in the **Postman**.
So I'll just copy that whole path of that whole URL, and then I'll swing over to **Postman**.
Then I'll just simply move up here to this section here for the `GET` request,
and I'll just paste it in here.
I'll hit the `Send` button over here on the far right.

![image22](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image22.png?raw=true)

And then, at the bottom, I get the actual response, `Hello World`.
So again, we're using **Postman** to access this **REST** controller 
or this **REST** service, and we're getting a response.
And then, over on the far right, you can get the actual status code of 200,
means that it was a successful request.
So we kinda went back and forth here.
So we did it with the web browser. 
We did it with **Postman**.
Again, like I mentioned earlier for a simple request, 
you can go back and forth between the browser and **Postman**.
**Postman**'s really good for a lot of advanced functionality,
and we'll cover that later, but this is enough for now.
So we are successful in getting our first REST service up and running!
</div>

## [REST POJO with JSON Jackson Data Binding]()
<div style="text-align:justify">

In this section first, we're going to cover **Java JSON data binding**.
Alright, so what exactly is data binding?
**Data binding** is the process of converting **JSON** data to a **Java POJO**.

![image23](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image23.png?raw=true)

So we're starting over here on the left with **JSON** data, and then we have our **Java POJO**.
And so we can use data binding to convert from **JSON** over to a **Java POJO**.
So it'll read the contents of that **JSON** string or file
and then populate the Java object with that given data.
Or you can go the other way.
You can start with the **Java POJO** 
and then send it down to an actual **JSON** string or **JSON** file.
Now this whole process is called **data binding**.
Now you may hear other terms used online or see other terms used online
such as **mapping**, **sterilization** / **deserialization**, **marshalling** / **unmarshalling**.
It's pretty much all the same thing.
It's just basically converting from one format to another.
So converting from **JSON** to a **Java POJO** or going from a **Java POJO** to **JSON**.
And remember, Java POJO is really just **plain old Java object** or any old Java class.
Let's look at doing **JSON** data binding with **Jackson**.
So **Spring** actually uses a **Jackson** project behind the scenes.
So **Jackson** handles the data binding between JSON and the Java POJO.
So **Jackson** is actually a separate project.
There are a lot of synergies between **Jackson** and **Spring**
but **Jackson**'s a separate project for doing data binding.
So they have support for doing data binding with `xml`,
support for data binding using **JSON**, and so on.
So it's a very popular project.
So if you do any type of **JSON** development in the Java world,
or any **REST** development in the Java world,
it's a very good chance you're going to run across the **Jackson** project.
It's very popular.
That's how we're covering it here in this course.

Now, by default, **Jackson** will call the appropriate getter/setter methods
when it handles the conversions.
So if you're converting from **JSON** to **POJO** it'll call up given setter methods.
When you go from **POJO** to **JSON** it'll call the getter methods.
And let's kinda walk through this here with an example.

![image24](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image24.png?raw=true)

So here on the left-hand side, I have this **JSON** object 
for `id`, `firstName`, `lastName`, and `active`.
And then over on the right-hand side, I have this **Java POJO** for a student.
And so what will happen here is that we'll actually do the conversion
between **JSON** going to a **Java POJO** or starting with a **Java POJO**, 
like a student object, and then actually sending it down to a **JSON** string.
So **Jackson** can help us with all of this processing.
And again, the important thing here is that
it'll actually use the getter and setter methods for handling some of this processing.
And we'll kind of dig into it a bit more here.

So let's look at one scenario.
So the one scenario that we'll look at here is converting **JSON** to **Java POJO**.
So in this scenario, they'll actually call the setter methods on the **POJO**.
So we have this **JSON** over the left-hand side,
for `id`, `firstName`, `lastName`, and `active`,
and then over on the right-hand side we have our **Java POJO** for a student.

![image25](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image25.png?raw=true)

So basically, using **Jackson**, they'll actually call the setter methods on your **POJO**.
So again, going from **JSON** to **Java POJO**, they're going to call the setter methods.
So they'll call set `id`, set `firstName`, set `lastName`, set `active` 
based on whatever values that you have here in the **JSON**.
And so **Jackson** will actually do all of this work for you 
behind the scenes once we get everything set up.
And we'll cover all the coding for setting this up here in this section.

Alright, so let's dig in a little deeper here.
So converting **JSON** to Java **POJO**.
So, remember, I said it's going to call the setter methods on the **POJO**.

![image26](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image26.png?raw=true)

So starting over here on the left-hand side with this **JSON** object,
then we have this **POJO**, again, plain old JAVA class **student**.
We have some fields defined, and we have the getter setter methods.
So the important thing here, again, is that **Jackson**'s going to call 
the setter methods on the **POJO**.
So for _id_, they're going to call _setId_.
Alright, and so, **Jackson** will actually do this work for you.
For _firstName_, it's going to call _setFirstName_.
For _lastName_, it's going to call _setLastName_.
And for _active_, it's going to call _setActive_.
They basically use the name from the actual **JSON**.
They call setters.
They take the first character of that name, make it cap,
and then use that to make the actual method call.
Now, again, I really want to emphasize here.
**Jackson** calls the setter methods.
So it doesn't access any of the internal private fields directly on the **POJO**.
It'll only call the given setter methods.
So you have to make sure that you have those **setter** methods defined,
and in this case, they have to match up accordingly for this given example.
So hopefully you can kind of see the power of **Jackson**,
doing all the heavy lifting for you in the background
and handling all the data binding for your application.

Now let's go the other direction.
So here we're going to convert a Java **POJO** to **JSON**.
And so, in this scenario, **Jackson**'s going to call the getter methods on the **POJO**.

![image27](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image27.png?raw=true)

Alright, so starting over on the left-hand side with this Java **POJO** for **student**.
And then we'll actually send this data over to **JSON**, or actually, generate a **JSON** based on this.
And so, again, **Jackson**'s going to call
the appropriate getter methods on your **POJO**.
And then **Jackson** will handle getting this data sent out accordingly to a **JSON** string,
either to just a memory, or to a given file.
Again, **Jackson** will do all this work for you.

Now let's look at the **Spring** in **Jackson** support.
So when you're building **Spring REST** applications,
**Spring** will automatically handle the **Jackson** integration.
So any **JSON** data that's being passed to the **REST** controller 
is automatically converted to a **POJO**.
And also any Java object that's being returned from a **REST** controller is converted to **JSON**
using the **Jackson** project.
And again, all of this happens automatically behind the scenes
thanks to the integration between **Spring** and **Jackson**.


Now let's create a spring **REST Service** for **students**.
So, we're going to create this new service that's going to return a list of students.
So basically we'll send over a get request to `/api/students`,
and this is going to return a list of students for us.

![image28](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image28.png?raw=true)

Alright, so let's look at the big picture here.
So we'll have our **REST** client, and then over on the right-hand side, 
we'll have our **REST** service.
We'll access this request mapping `/api/students` making a get request
and then this will return a list of students as **JSON**.
And then, we'll actually write the code for the service.
And for the client, we can make use of any client in this example, a web browser or a **Postman**.

Now let's talk about converting a Java **POJO** to **JSON**
because our rest service is going to return a list of student objects, `List<Student>`
and we need to convert that `List<Student>` to **JSON**.
And remember, **Jackson** can actually help us out with this.
So with the **Spring** and **Jackson** support,
**Spring** will automatically handle the **Jackson** integration.
Then the **JSON** data being passed to the **REST** controller 
is automatically converted to a Java **POJO**.
And then if a Java **POJO** is being returned from a **REST** controller,
then it's automatically converted to **JSON**.
And this all happens automatically behind the scenes thanks to **Spring** and **Jackson**.

![image29](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image29.png?raw=true)

So we're going to have this **POJO** or class called **student**.
We'll have some very basic fields, `firstName` and `lastName`, and the appropriate setters.
And we kind of saw this example little earlier
in some of our earlier **Jackson** data binding examples.
Now also, just remember here that **Jackson**'s going to call 
the appropriate getter / setter methods for the conversion. 

![image30](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image30.png?raw=true)

So here we're starting with some **JSON**
on the left-hand side, and then over on the right-hand side
we have our Java **POJO** to read those in.
Then we'll actually call the appropriate setter methods on the **POJO**.
Now to send it the other way, going from Java **POJO** down to **JSON**, 
they'll actually call the getter methods.
And remember, **Jackson**'s going to do all of this work for us behind the scenes.

![image31](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image31.png?raw=true)

Now let's talk about our **Spring REST service**.
So we're going to have our **REST** client, 
and then we're going to have our **REST** service and again `/api/students`.
So we'll actually write that code.
We'll pass back a list of student objects or our **POJO**'s,
and then we'll actually have that converted over to **JSON**.
And again, **Jackson**'s going to handle converting that data over to a **JSON** array.

![image32](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image32.png?raw=true)

So here we have our **REST** client and our **REST** service.
We make a request of `/api/students`.
And there's **Spring REST**.
So **Spring REST** and **Jackson**'s going to work together.
So any request coming in if there are any **JSON** data, they'll convert it to **POJO**'s.
But in this case, we're just simply going to that endpoint.
Well, on our **REST** service we'll create a list of objects
or a list of students, and we'll return those items.
So then **Jackson** will actually handle converting that list of students to the **JSON** array.
So remember, **Jackson** can handle 
the conversions of **JSON** to **POJO**'s and **POJO**'s to **JSON**.
So they'll call the appropriate methods and so on.
And then they'll send back a **JSON** array based on that data that we passed to it.
And that's really how it works behind the scenes.
So a lot of good synergies between **Spring REST** and the **Jackson** project here.
And it also makes your life really easy as a **Spring REST**s developer, 
so you don't have to worry about any low-level **JSON** development.

Let's look at our development process.

1. Create a Java **POJO** class for a **student** 
2. Create a **Spring REST** service using that at `@RestController` annotation.

![image33](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image33.png?raw=true)

Alright, so let's go ahead and take a look at step one.
So step one of creating a Java **POJO** class for **Student**.
Here above, the little **UML** diagram for it and then the Java code.
This Java code is very simple, very straightforward.
All we do is we define fields, we define constructors,
and we define the getters and setters.
So a very basic example here you've seen a lot of this coding before.

![image34](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image34.png?raw=true)

Now moving ahead here with step two of creating that `@RestController`.
So here's a little diagram up in the top right as far as our **REST** client, 
**Spring REST**, and the **REST** service.
What we'll do is actually define this **RestController** using those annotations
for `@RestController` and `@RequestMapping` for `/api`.
Now, I'll go through and actually define the endpoint for `/students`.
That basically returns a list of students here.
Here, I'll set up the `@GetMapping` for `/students`,
and then I define the method `public List<Student> getStudents()`.
Inside this method, I'll create a list of students and return it to the calling program.
So here I simply just create a new array list.
It's empty right now, and then I just go through 
and add three students, `Porrima`, `Mario`, and `Mary`.
At this point, I'll just hard-code the data for now,
but we could always integrate a database later.
But right now, let's just kind of keep it simple just
so we get something working, and then we can get into all the database stuff later.
So then I go through and simply say, return the students.
And remember here, **Jackson** will actually convert that list
of students to the **JSON** array as it goes back to the actual **REST** client.
And again, it all happens in the background.
So for our method for this controller, all we do is we simply define the method, 
write the code return our **POJO** or our Java object, 
and then all the **JSON** conversions and so forth will happen automatically 
thanks to **Spring REST** and **Jackson**.
Now, we're going to write this code out, 
and we're going to get this app running on our local computer.

The first thing we need to do is actually create a package to place our **POJO** class.
So I'll simply just do a right click in the `demo` folder,
say create new package, and the name that I'll give for the package,
I'll call it `.entity`.
So this is where we'll put our actual **POJO** class.
So here's step one.
I need to actually create my **Java POJO** class,
so I'm going to create a new class here.
And this is for our **POJO** called **student**.
So it's just a regular, plain, old Java object, or just a regular Java class.

```java
package com.luv2code.demo.rest.entity;

public class Student {
    
    private String firstName;
    private String lastName;
}
```

And what I'm going to do here is define some fields here,
so I'll define a field for _firstName_ and _lastName_.
I'll just go ahead and define a constructor.

```java
package com.luv2code.demo.rest.entity;

public class Student {
    
    private String firstName;
    private String lastName;

    public Student() {
        
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
```

So I'll just actually write out the first one here,
the no argument constructor, and then for the other constructor,
I'll actually have it generate this for me,
and just make sure that both of 'em are selected here for first name and last name.
All right, so there we go.
So we have this constructor for _firstName_ and _lastName_.

```java
package com.luv2code.demo.rest.entity;

public class Student {
    
    private String firstName;
    private String lastName;

    public Student() {
        
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
```

The next thing we need to do is generate the getters and setters for these fields.
And then make sure that these two are selected for _firstName_ and _lastName_.
So here, all we did was create a basic **POJO** class.
We defined some fields, constructors, and getters and setters.

Alright, what we need to do now is actually go through and create our `@RestController`.
So, I'll move down to this `rest` package, 
and I'll create a new class for our **REST** controller.

```java
package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    
}
```

And the name that I'll give for this controller, I'll call it `StudentRestController`.
So, since I'm building a **REST** controller, I'll use that spring annotation `@RestController`.
And I also set up the base `@RequestMapping` here, I'll call it `/api`.
Here's the basic layout here for our student **REST** controller.
So we have the annotations up top for the `@RESTcontroller` and `@RequestMapping`.
Now, just a quick comment here.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudent() {

        return null;
    }
}
```

I'll write in the code here though.
I need to define an endpoint for `/students`.
So this will be for a `get` request 
and basically what we'll do is we'll return a list of all the students that we have.
So we can use the same process that we saw earlier with the `hello world` example.
We simply define a `@GetMapping`.
So here I'll give `/students`.
I'll say `public List<Student>` because we're going to return a list of students, right?
Call the method _getStudent_.
And just for now, return `null`.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudent() {

        List<Student> theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
        
        //return null;
        return theStudents;
    }
}
```

So what we're going to do is create some sample students in this method 
that will return to the calling program.
And for now, we'll just hard code it.
We'll get into all the database stuff later, so don't worry.
We'll do all the full database crud stuff in some following sections.
For now, let's keep it simple, just to get things up and running.
So I'll just create a new array list right here.
It's empty at the moment for students,
and we'll actually populate it with some student objects.
And then I'll just add a `new Student`.
Use that constructor that we created in a previous example, so `Poornima Patel`.
So that is our first student.
So let's just go ahead and copy this line and paste it a couple of times,
and we'll just change the student name.
Alright, so I have three students here.
Let me update the names here.
So `Mario Rossi` and then `Mary Smith`.
So I'll just kind of fix up the returns here.
So return `theStudents` as opposed to `null`,
as we have a new object that we've just created.
So those are the three students that we've just added for this demo.
That's the endpoint here, and so we can actually access this endpoint by going to `/api/students`.
And remember, **Spring** will make use of **Jackson** in the background.
It'll take those **POJO**s and convert them to **JSON**,
and we'll go back to the client or calling program.
So this is good for the first cut.
There are some minor items here and there, but don't worry, 
we'll actually refactor this code in some later sections,
but at least this is enough to get us started.

Okay, so we have our coding completed.
Now let's go ahead and test this out and let's run it and see how it works out.

```html
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-28T16:18:45.591+03:00  INFO 88324 --- [demo] [           main] com.luv2code.demo.DemoApplication        : Starting DemoApplication using Java 21.0.2 with PID 88324 (D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\01-spring-boot-rest-crud\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\01-spring-boot-rest-crud)
2024-05-28T16:18:45.594+03:00  INFO 88324 --- [demo] [           main] com.luv2code.demo.DemoApplication        : No active profile set, falling back to 1 default profile: "default"
2024-05-28T16:18:46.354+03:00  INFO 88324 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-28T16:18:46.364+03:00  INFO 88324 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-28T16:18:46.364+03:00  INFO 88324 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-05-28T16:18:46.410+03:00  INFO 88324 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-28T16:18:46.411+03:00  INFO 88324 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 772 ms
2024-05-28T16:18:46.663+03:00  INFO 88324 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-05-28T16:18:46.670+03:00  INFO 88324 --- [demo] [           main] com.luv2code.demo.DemoApplication        : Started DemoApplication in 1.387 seconds (process running for 1.668)
```

Alright, so it's up and running.
So remember, we have to go into the URL here and do `/api/students` 
to access that new endpoint that we just created.

![image35](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image35.png?raw=true)

So this is some good **JSON**.
So we have our three students, `Poornima`, `Mario`, and `Mary`.
Remember how **Spring REST** will actually take your actual **POJO** objects 
and convert those **POJO** objects to **JSON** and pass it back.
And it makes use of the **Jackson** project in the background for handling that conversion.
So we saw how **Jackson** works, so we're good to go there.
So let's go ahead and copy this URL from the browser here, 
and then we'll move over to **Postman** and test it there.
And in the actual URL section here, paste in that URL that we copied previously.
And then we just hit the blue `send` button and then: 

![image36](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image36.png?raw=true)

So there we go.
So let me kind of scroll down a bit over here.
And here are our three students, `Poornima`, `Mario`, and `Mary`.
So we're kind of successful.
So we have this **REST** controller working, 
this **REST** web service working with this new endpoint
that we just created to get a list of students.
So we're in good shape, and we are rocking and rolling.
So good job so far.
</div>

## [Path Variables]()
<div style="text-align:justify">

In this section, we're going to use **Spring REST** with **Path Variables**.
And to kind of help us out with path variables,
we'll have this new endpoint to retrieve a single student by the id.
So we'll make a get request to `api/students/{studentId}`, and this will retrieve a single student.
Now, the `studentId` in curly braces here, this is known as a path variable,
such that when we actually access it via our **REST** client, we can include an actual value there.
So we could say `/api/student/0`, that'll give us the student with id `0`, `1`, `2` and so on.
So basically a way of parameterizing your path or the endpoint to actually accept data.

![image37](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image37.png?raw=true)

Now let's kinda look at this as far as the big picture here.
So we'll have our **REST** client, and then over on the far right, we'll have our **REST** service.
So we'll make a call `api/students/{studentId}`.
And again, that's our path variable.
So then it'll return that given the student.
So here, we're just making up an example, it'll return back `Mario Rossi`.
Now again, we'll actually write the code here for the **REST** service.
We'll add a new request mapping for this, and in our coding will actually create a new student,
or we'll retrieve that given student, and then pass it back.
And just as a reminder, **Jackson**'s gonna actually convert that student object to **JSON**.

![image38](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image38.png?raw=true)

Alright, so let's kinda look and see how this works behind the scenes.
So we have our **REST** client and our **REST** service.
We'll make a request across for `api/students/{studentId}`, and the `studentId` is in curly braces.
And then we'll have **Spring REST** along with **Jackson**,
they'll make a call to the **REST** service.
So in our code, we'll actually return that given student,
and then **Jackson** will actually convert that student object or that student **POJO** to **JSON**,
and then send it back across to the **REST** client.
And you've kind of seen this process before, 
but I simply wanted to show you the information again, 
along with just using a single student object as opposed to an array list.

So the development process is fairly straightforward.
1. Add a request mapping to our spring **REST** service, 
2. Bind the path variable to the method parameter using this new annotation called `@PathVariable`.
And we'll see coding examples of this coming up.

Actually now, we're going to refactor some code, 
because in our previous section we had some code that wasn't exactly the best.
So let's go ahead and look at our **StudentRestController**,
and we'll kinda pinpoint the issue; you may have even noticed it.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    
    private List<Student> theStudents;
    
    // define @PostConstruct to laod the student data ... only once!
    @PostConstruct
    public void loadData() {

        //List<Student> theStudents = new ArrayList<>();
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }
    
    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudent() {

        // List<Student> theStudents = new ArrayList<>();
        // theStudents.add(new Student("Poornima", "Patel"));
        // theStudents.add(new Student("Mario", "Rossi"));
        // theStudents.add(new Student("Mary", "Smith"));
        return theStudents;
    }
}
```

So down here in this _getStudents_ method, we actually create the list for every request,
and we populate it for every request.
And that's not the best, that's not good.
So let's go ahead and clean this up and refactor this code.
So basically, what I'd like to do is define a field
and then load that field with data such that we only do that work once.
Let's start here by defining our field.
So we'll create a field, `List<Student>`, and we'll call it _theStudents_.
And now I'd like to actually load that student list with some data.
And what I can do is actually make use of the `@PostConstruct` annotation 
to load the student data.
And remember, `@PostConstruct` is called only once when it's given bean is constructed.
So this will be a good fit here for our given use case.
So I'll define a method here.
I use the `@PostConstruct` annotation.
I'll do a `public void loadData()`.
We can give any method name that we want, but I'll just call it _loadData_.
So we'll create our list and then populate it with students.
And what I'd like to do is actually use the same code that I've already created.
So let's cut the code in _getStudents_ method and then, 
I'll actually move up to that _loadData_ method, and I'll actually paste it here.
Alright, so I need to make one little change here.
I need to actually delete `List<Student>` 
since we already have it declared above as a field.
And it's basically that's the field up top, and then here, 
we simply initialize it as an empty list, 
and then we simply go through add the actual students to that list.
This approach is much better because we only load the student data once.
So our _getStudents_ method is very simple now.
All we have to do is just return the list, because the data has already been loaded
thanks to the `@PostConstruct` annotation.
I wanna just run this again just to make sure it still works,
just to make sure I didn't break anything.

![image35](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image35.png?raw=true)

And so here, we'll get all three users,
or all three students, `Poornima`, `Mario`, and `Mary`.
So this looks good.
And then I also want to test it in Postman, again, 
just to make sure it works fine over there.
So I'm in **Postman** right now.
I keep the same URL, hit the blue `send` button:

![image36](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image36.png?raw=true)

And there we go.
All three students, `Poornima`, `Mario`, and `Mary`.
So this is great.
So we simply refactored the code, made use of `@PostConstruct` just to be a bit more efficient
in our use of loading data and creating the students.

Let's now go ahead and define another endpoint,
and that's the one for retrieving a single student.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    
    private List<Student> theStudents;
    
    // define @PostConstruct to laod the student data ... only once!
    @PostConstruct
    public void loadData() {

        //List<Student> theStudents = new ArrayList<>();
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }
    
    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudent() {

        // List<Student> theStudents = new ArrayList<>();
        // theStudents.add(new Student("Poornima", "Patel"));
        // theStudents.add(new Student("Mario", "Rossi"));
        // theStudents.add(new Student("Mary", "Smith"));
        return theStudents;
    }

    // define endpoint for "/student/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // just index into the list ... keep it simple for now
        return theStudents.get(studentId);
    }
}
```

So I'll write a quick comment here to myself.
So we'll define this endpoint for `/student/{studentId}`.
And this is for actually retrieving a single student by id.
And here we'll keep it simple, and we'll actually return it by the index of that array list.
So I'll set up this, `@GetMapping("/students/{studentId}")`.
And remember the curly braces here for the `studentId` is that's an actual path variable.
And I'll just go through and define a method here.
_getStudent_ will return a **Student**,
and I'll make use of this `@PathVariable` annotation to actually bind that path.
Variable _studentId_ to our parameter here _studentId_ by default, 
the variable names have to match up.
So the path variable coming in up top is in the curly braces _studentId_.
Then our method per should also be _studentId_ and that's by default.
Now what I'm going to do here is just index into the list.
And like I said before, I really just want to keep it simple for now.
So that actual _studentId_ that they're passing in 
is really just the index from the actual list.
So I can access an element in the list by simply saying the students, 
that's my actual list here, `theStudents.get(studentId)`.
And you give the index, `studentId`.
So here we simply pass in the `studentId`,
and that'll give us that person from the actual list.
And that's the basic coding.
And again, we're keeping it simple for now.
We'll get into all the fancy database stuff later on.
I'm ready to test it and see how this works.
Let's go ahead and run this on our server.
And then, once it's up and running,
then up top I give slash API slash students,
and then I give the student ID or the index.
So I say `localhost:8080/api/students/0`, the first element in that list.
And it should come back as `Poornima Patel`
because that's the first student that we added.

![image39](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image39.png?raw=true)

So _index_ is zero.
If I do one, I'll get `Mario Rossi`.

![image40](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image40.png?raw=true)

And then if I go through and enter two, I get `Mary Smith`.

![image41](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image41.png?raw=true)

So this is working out as desired.
And let me kind of copy this URL that we had before.
So let's swing over to **Postman** and let's create a new tab here.
And I'll just paste in that URL and I'll do `api/students/0`.

![image42](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image42.png?raw=true)

And again, that gives us `Poornima Patel`, because she's the first student that was added.
And then we have `Mario` for index one and then `Mary Smith` for index two.
This all kind of works out as planned,
so we're able to make use of that path variable to access a single student.
So this is great, but now you're probably wondering what if I entered some bad data?
What will happen?
We have this internal server error, the 500 error.

![image43](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image43.png?raw=true)

Let's find out why this happened.
So let's go ahead and switch back over into our IDE, and we see this ugly stack trace.

```html
2024-05-28T17:34:32.971+03:00 ERROR 50084 --- [demo] [nio-8080-exec-8] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.IndexOutOfBoundsException: Index 9999 out of bounds for length 3] with root cause

java.lang.IndexOutOfBoundsException: Index 9999 out of bounds for length 3
	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100) ~[na:na]
	...
    at java.base/java.lang.Thread.run(Thread.java:1583) ~[na:na]
```

Alright, so scrolling up we see the root cause index out-of-bounds exception.
The index of `9999` is outside the bounds of the length of three, which makes sense, right?
But hey, don't worry about this.
We'll cover exception handling in the next section.
So instead of the dreaded 500, we'll actually handle that exception 
and then provide our own custom response for that given error message 
or that given exception condition.
</div>

## [Exception Handling]()
<div style="text-align:justify">

In this section, we're going to cover **Spring REST Exception Handling**.
Remember our problem in the previous section, right?
We would pass over a bad _studentId_ of `9999`, and then we get this ugly exception, right?

```html
{
    "timestamp": "2024-05-28T14:34:32.978+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/api/students/9999"
}
```

500, internal server error.
We found out what the root cause was.
It was an index out of bounds.
What we really want is this, we want to handle the exception and return the error as **JSON**.

```html
{
    "status": 404,
    "message": "Student id not found - 9999",
    "timeStamp": 15261496
}
```

So we'd like to have a nice little **JSON** message come back,
that has the details on the error or the exception.
So we'd like to give the actual status code of `404`, the actual error message,
and then maybe a timestamp as far as when this given error occurred.
And that's what we really want, and that's what we'll actually build out in this section.
Alright, so let's look at the big picture here.

![image44](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image44.png?raw=true)

So we have this **REST Client**, the **REST Service**, we make a call for `api/students/9999`.
That's the bad data.
It makes it into our **REST Service**, throws an exception,
and then we'd like to actually handle for that exception.
Then instead of that ugly **HTML** page, we'll send back the exception error message as **JSON**.

So let's look at the actual development process here:

1. Create a custom error response class
2. Create a custom exception class
3. Update our **REST** service to throw the exception if the student is not found
4. Add an exception handler method using springs at `@ExceptionHandler` annotation

Starting with step 1: we're going to create this custom error response class.
So this class will actually be sent back to the client as **JSON**.
We'll define this as a Java class or a **POJO**,
and we'll just keep track of three fields here.
We'll keep track of the **status**, the **message**, and the **timestamp**.
And then **Jackson** will actually be responsible
for converting this **POJO** over to **JSON** going back to the client.
Now with this **POJO** here, you can define any custom fields that you want to track.
Here I'm just keeping it simple by having these 
three fields of **status**, **messaging**, and **timestamp**.
But you can easily extend it, and easily add your own custom fields
because just a regular **Java POJO**.

```java
public class StudentErrorResponse {
    
    private int status;
    private String message;
    private long timeStamp;
    
    // constructors
    
    // getters / setters
}
```

Okay, so let's go ahead and look at the coding here.
So we'll have `this StudentErrorResponse.java`.
We'll create this class.
We'll define those three fields for **status**, **message** and **timestamp**.
And then from there we simply create our constructors,
and also our getters and setters.
And that's it.
It's just a regular basic Java, **POJO**, or Java class here.

```html
{
    "status": 404,
    "message": "Student id not found - 9999",
    "timeStamp": 1526149650271
}
```

And again, in the bottom right, you'll see the little **JSON** portion here.
**Jackson** will actually convert this **POJO** over 
to the appropriate **JSON** when we send it back.
And that's it for this example.
Now, moving ahead to step 2 as far as creating our custom student exception,
this custom student exception will be used by our **REST** service.
So, in our code, if we can't find a **Student**,
then we'll actually throw an exception.
So, we need to define the custom student exception class.
We'll actually call it `StudentNotFoundException`.

```java
public class StudentNotFoundException extends RuntimeException {
    
    public StudentNotFoundException(String message) {
        super(message);
    }
}
```

Alright, so let's go ahead and define our custom student exception.
So our class extends `RuntimeException`, and then we define a constructor here.
And this constructor will basically make use of super,
so just call the superclass constructor for the message.
And that's it.
So since we're using inheritance, 
there's not much code we have to write here for this given exception.
And this is a very common approach for creating exceptions.

```java
@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // ...
    
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // check the studentId against list size
        
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        
        return theStudents.get(studentId);
    }
    
    // ...
}
```

In step 3, we're going to update the **REST** service to throw an exception.
So here's the basic coding here for our student **REST** controller
and the method for _getStudent_ for a given _studentId_,
we're going to check the _studentId_ against the list size.
So we check the _studentId_.
If it's greater or equal to `theStudents.size` 
or the _studentId_ is less than `0`, then we're going to throw an exception,
and we're going to throw out custom exception **StudentNotFoundException**.
And if we were using a database, 
we could easily check this against database results like we could do a query, 
see if we got any results.
If we didn't get any results, then we could throw an exception accordingly.
But here we're just keeping it simple by using the _studentId_ to index into our array list.
And if this statement works out just fine
meaning that the studentId is within the range
then we simply return `theStudents.get`, the `studentId`.
And that's the happy path here.
Now at this point, we have an exception that's thrown.

So again, if our bad data is coming in of `9999` we'll throw the exception.
So that part is taken care of. 
However, the next thing or the missing link right now 
is the exception handler, who's going to handle the exception,
and how will they get the appropriate **JSON** data back to the client.
So that takes us up to Step 4, adding the exception handler method.
So what we're going to do here is define an exception handler method 
using this `@ExceptionHandler` annotation from **Spring**.
This exception handler is going to return a **ResponseEntity**.
The **ResponseEntity** is really just a wrapper for the **HTTP** response object.
It gives you fine-grained control over specifying the actual status code,
the actual **HTTP** headers, and also the **Response** body.
So you have a bit more control over 
how you send back the response to the calling program.

```java
@RestController
@RequestMapping("/api")
public class StudentRestController {

    // ...
    
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
```

So we'll actually add the exception handler method here.
I'll kind of spec out the basic coding for it.
So I'll make use of this annotation `@ExceptionHandler`.
So we're saying, it's an exception handler method.
And then we also specify the type of the response body.
So we have **ResponseEntity** and we say in brackets **StudentErrorResponse**.
So that's the type of data we're going to pass back in the response body.
And then we also tell it, 
_hey, these are the types of exceptions that we can handle for this given handler method_.
So here I'll have **StudentNotFoundException**.
So any **StudentNotFoundException**s that are thrown this actual handler method 
will catch it and then work on it accordingly.
So we're inside the method here.
We go ahead and create our **StudentErrorResponse**.
That's that custom POJO that we created earlier.
And then we simply set some fields here.
So I set the actual status of `Http.Status,Not_Found`.
So that's basically a 404. 
We set the actual error message based on the exception `exc.getMessage`.
And then we also set the _timestamping_ here.
I just use a `System.currentTimeMillis()` to give us the current timestamp.
So that's the basic piece here as far as creating that **StudentErrorResponse**
and then loading it with the appropriate data.
And then, now, what I do is actually move through,
and I return the appropriate **ResponseEntity** here.
So I say return new **ResponseEntity**, and then I give _error_, the status code.
So the actual first argument here, that's the actual body.
So **error**, that's that custom **StudentErrorResponse** that we've just created.
And then we give `HttpStatus.NOT_FOUND`.
That's the 404 error that will return to the calling program.
And then **Jackson** will be responsible for converting the actual body 
to the appropriate **JSON**.
And so we'll get this **JSON** going back to the calling program.
And so that's the basic layout there as far as the coding.

So let's create our custom error response class,
and also we'll create our custom exception.
So we'll start with step one of creating our custom error response class.
And the name that I'll give for this class I'll call it **StudentErrorResponse**.
And so remember, this is just our pojo, and we'll simply define the fields here
for a status message and timestamp.

```java
package com.luv2code.demo.rest;

public class StudentErrorResponse {
    
    private int status;
    private String message;
    private long timeStamp;

    public StudentErrorResponse() {

    }

    public StudentErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

```

This is all very basic, very straightforward.
Alright, so those are our three fields that we have set up kind of based on our uml diagram.
And then the next thing I'll go through and define the constructors for it.
So I'll start by just creating a default no argument constructor,
and then I'll just make sure I have those three fields selected there,
or just kind of do the select all, and then we're ready to go.
And there we go.
So we have these two constructors, and so I may not use all, but I have them here.
And just in case I need to use them later on in the future.
So the next thing here is generating our getters and setters,
and then we're ready to go with this portion of it.
That's basically about it.
So we have our getters and setters, we have our fields, and we have our constructors.
So we're in pretty good shape with this **StudentErrorResponse**.
So we can say that step one is complete, and we can move on to step number two.

```java
package com.luv2code.demo.rest;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}

```

So with step two is simply creating a custom exception class for this example.
And so again in our `rest` package, we'll create this class here.
Let's create a new class.
And so this will be for our **StudentNotFoundException**, 
and I'll say `extends runtimeException`
**runtimeException**s from the `java.lang` package.
And basically all I want to do here is define a constructor here 
for this **StudentNotFoundException**, 
I'll actually generate the constructor from the superclass.
**Generate**, so those are three that we want.
And again, I may not use all of them, but it's just good to have them
in case I need to use them again in the future.
Alright, so that's my **StudentNotFoundException**
and I can use this in my **REST** service when it's actually time to throw an exception,
if the _studentId_ is a bad _studentId_, or I can't find the given **Student**.
Alright, so that's our custom student exception.

So, we've already covered step one and two.
Now we'll actually cover step three of updating our **REST** service to throw the exception
if the student is not found.
Okay, so let's go ahead and move over to our **REST** service here,
our student rest controller, and we'll make those updates to throw the exception.
So I'm in my student **REST** controller, my **REST** service.
I'll move down to that _getStudent_ method.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudent() {
        return theStudents;
    }

    // define endpoint for "/student/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // just index into the list ... keep it simple for now
        
        // check the studentId again list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        
        return theStudents.get(studentId);
    }
}
```

And so this is where I want to add some logic here to check the _studentId_.
So I'll write a quick little comment here to myself.
So I'm going to check the _studentId_ against the list size,
and I can do this with an `if` statement.
So here, I'll just check to see if the _studentId_ is greater than 
or equal to `list.size`, or if the _studentId_ is less than zero.
If that's the case, then I'm actually going to throw an exception.
And so when I throw the exception here, I'll throw a new **StudentNotFoundException**.
Again, that's our custom exception class we created earlier.
And so I'll simply give the message, "_Hey, I couldn't find a given student ID_"
and provide the actual student ID that we couldn't find.
So that's the basic coding there for checking that.
So that's the logic to kind of handle that part of it.
And then if everything's okay, we make it past that if statement,
then we simply return `theStudents.get(studentId)`.
So that's the happy path.
So they pass on a valid ID or okay,
we simply get the student and return it accordingly.
So that kind of takes care of step three of throwing the exception.

So we've covered steps one through three so far.
Now we'll cover step four of adding an exception 
handler using the `@ExceptionHandler` annotation.
So with this step four of adding exception handler,
we have the code for throwing the exception, 
but nothing for handling the exception.
So that's where we need to do this now.
And we'll actually add this code in,
our **StudentRestController** will add an exception handler for this given application, 
and we'll make use of that `@ExceptionHandler` annotation.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // ...

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
```

I give this `@ExceptionHandler` annotation,
and then I'll actually write out the method signature.
And for now, say return `null`, just to kind of give us some basics here.
And then we can kind of focus on and drill down on some different parts of this coding.
So remember the `@ExceptionHandler` annotation says
"_Hey, this method's an exception handler._"
We tell the actual response type.
And then we also have the actual exception type as far
as what we're going to catch.
So here we're saying that this coding or this **ExceptionHandler** can handle
or catch **StudentNotFoundException**s.
So we have the basic coding stubbed out.
Let me just write some quick comments here to myself 
just so I know what I need to do inside this method.
So I need to create the student error response and also return a **ResponseEntity**.
Alright, so let me just go ahead and create a **StudentErrorResponse**.
So I'll just call the constructor here for this **StudentErrorResponse**.
And remember, that's the custom error response object
that we created earlier in some of the previous sections.
And then I'll simply go through and just set the values here.
So I'll set the actual _status_ that's the actual **HTTP** status code of `NOT_FOUND`.
That means 404.
Then I set the actual _message_ for this error response 
to be whatever the exception message is.
So I'll say `exception.getMessage()`.
And then finally I'll set the actual _timestamps_.
So I'll say `error.setTimeStamp()`
and I'll get the `System.currentTimeMillis()`.
Alright, so that's the basic code in there for setting the appropriate values
on this error response object.
Okay, so now moving to this next area here,
I need to return the **ResponseEntity**.
So here I'm going to create a new **ResponseEntity**, and then I give the actual _error_
which is the body of the response and then the actual status code for this response.
So the status code will be `HttpStatus.NOT_FOUND`.
So I give the body comma the actual status code.
And remember, **Jackson** will be responsible for taking that **POJO** class
and converting it to **JSON** accordingly.

```html
{
    "status": 404,
    "message": "Student id not found - 9999",
    "timeStamp": 1526149650271
}
```

So when it goes back to the client, we'll see this nice error response just
like we have here on the screen.
Let's go ahead and test this thing out.
And now just go ahead and break it with this `localhost:8080/api/student/9999`:

![image45](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image45.png?raw=true)

Success.
So that's the error response that we wanted.
We wanted **JSON** error response.
Now let's copy this url, and then let's go ahead and swing over to **Postman**,
and just see how it works out in **Postman** for us.
I'll move up here, and I'll simply paste in that url that I just copied.
And then I'll just kind of hit the blue `send` button.

![image46](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image46.png?raw=true)

And, oh yeah, looking good.
So down here at the bottom, we have this **JSON** response for the error.
So, again, mission accomplished here.
And also in the top right we have `404 not found`.
And that's great, because that id is not found.
And so we're giving the correct status code.
And then you can test other bad IDs, like some really large numbers or whatever.
You could also swing over and put in like,
negative numbers to see how that works out for us.
So if we enter like a negative five,
then that should also handle accordingly with our exception handling code.
But we're not really done.
There are some edge cases out there and you may have discovered them already.
If you enter some text characters
for the student ID like `localhost:8080/api/student/sadfsdfs` or something,
then that won't really work for our application.
So we added some exception handling, but we didn't cover all the edge cases.
And we can get some more details by actually looking
at the server logs to find out what exactly happened here.

```html
2024-05-28T21:48:02.361+03:00  INFO 97036 --- [demo] [           main] com.luv2code.demo.DemoApplication        : No active profile set, falling back to 1 default profile: "default"
2024-05-28T21:48:03.007+03:00  INFO 97036 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-28T21:48:03.016+03:00  INFO 97036 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-28T21:48:03.016+03:00  INFO 97036 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-05-28T21:48:03.060+03:00  INFO 97036 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-28T21:48:03.061+03:00  INFO 97036 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 661 ms
2024-05-28T21:48:03.277+03:00  INFO 97036 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-05-28T21:48:03.282+03:00  INFO 97036 --- [demo] [           main] com.luv2code.demo.DemoApplication        : Started DemoApplication in 1.199 seconds (process running for 1.456)
2024-05-28T21:48:08.713+03:00  INFO 97036 --- [demo] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-05-28T21:48:08.713+03:00  INFO 97036 --- [demo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-05-28T21:48:08.713+03:00  INFO 97036 --- [demo] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
2024-05-28T21:53:12.733+03:00  WARN 97036 --- [demo] [io-8080-exec-10] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: "fasdfasdfs"]
```

So this last line is a warning.
And then we can kind of start scrolling over to the right with this error message.
So it says fail to bind request, **MethodArgumentTypeMismatchException**.
_Failed to convert value of type `java.lang.String` to required type `int`_.
That weird text that we entered, you can't really convert that to an integer.
But we didn't give a good **JSON** error response.
So, we'll actually need to modify our code to handle for these edge cases.
Or, kind of set up like a generic exception handler.
So for any error that happens, we can catch it.
And then send back that response as **JSON**.
And we'll actually cover that in the next.

Let's add a generic exception handler for this issue.
And basically we're going to set up a catch-all exception 
to catch any of those edge cases that pop up in our application.
So I'll just kind of move back into my **StudentRestController** here.

```java
package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // ...

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    // add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

And basically what I want to do is add a new exception handler
or add another exception handler.
And here again, like I mentioned, I'll do the catch-all
for any type of exception that's being thrown.
So I simply create the method signature very similar to what I did before.
So I might use the `@ExceptionHandler`.
I have the **ResponseEntity** for a **StudentErrorResponse**, 
but for _handleException_ I'll actually specify the generic `Exception` object.
As far as the one that we're going to handle for.
Previously, we had _handleException_ for `StudentNotFoundException`.
But here we're going to say _handleException_ for just the generic `Exception` object.
So this is kinda like our catch-all for any exception that's thrown.
Now for the actual body, I can actually copy some of that information 
from our previous `ExceptionHandler`.
So again, just a little copy and paste exercise,
but the one thing I want to modify here is I actually want to change the status code.
Because currently we have `HttpStatus.NOT_FOUND.value()`,
but in this case it's just a bad request.
Alright, so they're just passing over some bad data or a bad request,
and we want to reflect that accordingly in the status code.
So here I'll say `HttpStatus.BAD_REQUEST.value()`.
So that's actually going to be a `400` error as opposed to a `404`.
And here just more fine grain control over the response that we're sending.
So I'll do a similar thing, I'll copy that request,
and I'll also do a similar thing down here on the `ResponseEntity`.
Making sure we cover it in both locations.
But everything else for this coding remains the same.
There are no major changes there.
So again, we're just adding another `ExceptionHandler` to do the catch-all portion here.
Okay, so let's go ahead and test this out and let's run it and see how it works out for us.
We'll actually just use the `9999` to test that portion.
So that part is still working, but again let's really break it by just
entering some text values:

![image47](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image47.png?raw=true)

And success.
We're getting the air packet back as **JSON** data.
And again, let's swing over to **Postman** and do a similar thing.
Just enter some characters again and then we hit the blue button for `send`.

![image48](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image48.png?raw=true)

Yeah, and good.
Once more, we're getting this error response as **JSON**
and in the top right it says that's a `bad request`.
Now this message portion here it gives the details as far as what happened.
If you'd like, you can leave it as is,
or you can change the error message to be a bit more friendly to the user.
In your Java code, you simply update this one line `error.setMessage`, 
and you can give whatever plain text area that you want instead
of giving the full exception _getMessage_ or whatever.
So it's totally up to you as far as how you want to control and customize that portion.
So we've handled the regular case of `StudentNotFoundException`,
and we've also handled any other edge cases
or any other exceptions by passing back that data as **JSON**.
 </div>

## [Global Exception Handling]()
<div style="text-align:justify">

In this section, we'll cover `Spring REST Global Exception Handling`.
Now, in the previous section, we added code for exception handling.
So we had bad data coming across, we had our **REST** service that were throwing exception.
And then inside the **REST** service, we had an exception handler
that'll actually pass back the exception as **JSON** data.

That coding works, but the exception handler code is only for the specific **REST** controller.
The coding can't be reused by other controllers.
And on large projects, or on realtime projects, you'll normally have multiple **REST** controllers.
So ideally, what we need is **global** exception handlers.
So this promotes the idea of reuse, and also helps you centralize your exception handling,
and minimizes the amount of code that you need to duplicate 
across multiple controllers on very large projects.

So to help solve this, we can make use of this **Spring** `@ControllerAdvice`.
The `@ControllerAdvice` annotation, it's similar to an **interceptor** or **filter**.
So we can use it to, kind of, pre-process requests to controllers.
We can also use it to post-process responses to handle exceptions.
And this is perfect for making use of global exception handling.
And if you notice, the name here, `@ControllerAdvice`, 
this is actually realtime use of `AOP`, or aspect ordain programming.
So if you remember from the `AOP` days, 
we had the ideas like, before advice, and after advice, and so on.
So here in **Spring**, they make use of an `@ControllerAdvice`.
So this is `AOP` that you can use to, kind of, pre-process and post-process on controllers.

![image49](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image49.png?raw=true)

So now, with this **Spring REST** exception handling, with this advice, 
or the `@ControllerAdvice`, we have our **REST** client, our **REST** service, 
we make a request across to the actual service, then we'll have this controller advice,
to, kind of, pre-process the request, and then the **REST** service will execute.
If there is something wrong, we'll throw an exception.
And now, instead of the exception being in the **REST** service,
the exception handler is going to be moved out and placed in the controller advice.
So this will give us support for global exception handling.
So no need to write all the exception handling in each **REST** service.
We can place it in one global location, to handle all of those requests.
And we can do all of that, thanks to this `@ControllerAdvice`.

As far as the development process: 

1. Create a new `@ControllerAdvice`
2. Refactor our **REST** service or remove the exception handling code from our **REST** service
3. Add the exception handling code to the `@ControllerAdvice`

So it's basically just taking existing code, and just, kind of, moving it around a bit,
and placing it in a global exception handler.

```java
package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // add exception handling code here

}
```

All right, so it's step one of creating a new `@ControllerAdvice`.
So I'll simply create this new class in the `rest` package.
So I'll move here to the `rest` package, and I'll just say new class.
And the name that I'll give for this class, I'll call it `StudentRestExceptionHandler`.
And basically what I need to do here is just add the `@ControllerAdvice`.
So that's a special annotation from the **Spring** framework.
I'll add some quick comments here to this class just so I know what I need to do.
So for step two, we need to kind of refactor our **Rest** service
and remove the exception handling code.
So I'll move over to `StudentRestController.java`.
And basically, I want to move down to those two methods that we defined as `@ExceptionHandler`.
So both of those methods with the annotation of `@ExceptionHandler`, 
I want to cut both of those methods and move them to another class.
So I'll just go ahead and cut, and then I'll move over to the `StudentRestExceptionHandler`.

```java
package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // add exception handling code here

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

And this is the exact same code.
So no changes are required for this code.
You can just drop it in as it is, and use it in this `@ControllerAdvice`.
So here's the first exception handler that we had for just handling for a `StudentNotFoundException`.
And then the other one that we have is our catch-all for handling any type of exception,
any type of generic exception.
Let's go ahead and test it out by just running our application.
Okay, it's up running.
I'll hit the get all students, and I'll just give `9999` here, student, I found.
That works.
And then I'll just give some bad text data here and good.
So this is all the same output as before.
We simply just refactored our code to make use of the `@ControllerAdvice` for global exception handling.
And so this is the best practice for large scale projects 
or real time projects by making use of this `@ControllerAdvice`. 
</div>

## [API Design - Create Project]()
<div style="text-align:justify">

In this section, we'll cover `Spring REST API design`.
For **REST API** design, when building a real-time project, 
we need to wonder who will use our **API**?
Also, we need to be aware of how they will use our **API**.
And then we need to design the **API** based on the requirements.

Alright, let's go ahead and look at the **API** design process:

1. Review the **API** requirements
2. Identify the main resource / entity
3. Use the **HTTP** methods to assign the action on a given resource

Now let's start with step one of reviewing the **API** requirements.
And here's an email that we received from the boss,
and it says, "_Create a **REST API** for our `Employee Directory` system._"
Basically, what we'd like to do is allow the **REST** clients should be able to 

* Get a list of employees
* Get an employee by ID
* Add a new employee
* Update an employee
* Delete an employee

Effectively, we want to have full `CRUD` support here via our **REST API**.

In step two, we need to identify the main resource or the main entity.
What we want to do is look for the most prominent `noun` in the requirements document.
For our project, the most prominent `noun` is `employee` 
because we saw that over and over again during those requirements.
The convention is to make use of a plural form of the resource or entity.
Here we have `employees`.
And for our endpoint we have `/api/employees`.
Now, this is not a hard and fast rule, but this is the general convention used in **REST API design**.

| HTTP Method  | CRUD Action                              |
|--------------|------------------------------------------|
| `POST`       | Create a new entity                      |
| `GET`        | Read a list of entities or single entity |
| `PUT`        | Update an existing entity                |
| `DELETE`     | Delete an existing entity                |

Moving ahead to step three, we need to use the **HTTP** methods to assign an action on a resource.
Here, for our given `POST` method, we'll use that for creating a new entity.
And then for a `GET` method, we'll use that to read a list of entities or a single entity.
Then we'll use the `PUT` method to actually update an existing entity.
And then we'll also use the `DELETE` method to delete an existing entity.
Effectively, here we'll have full **CRUD** support 
by specifying the appropriate **HTTP** method in our operation.
And this is the actual best practice that's used for real-time projects.

| HTTP Method   | Endpoint                      | CRUD Action                 |
|---------------|-------------------------------|-----------------------------|
| `POST`        | `/api/employees`              | Create a new employee       |
| `GET`         | `/api/employees/{employeeId}` | Read a list of employees    |
| `GET`         | `/api/employees`              | Read a single employee      |
| `PUT`         | `/api/employees`              | Update an existing employee |
| `DELETE`      | `/api/employees/{employeeId}` | Delete an existing employee |

Okay, let's take a look at some **CRUD** endpoint examples for our application.
We can use the `POST` method, and we'll send it to `/api/employees`
and this will actually create a new employee.
We can also use the `GET` method and send it to the same endpoint,
and we can use that to read a list of employees.
We can also use the `GET` method and send it to `/api/employees/{employeeId}`.
That's the path variable or path parameter, and we'll use that to read a single employee by ID.
We can use the `PUT` method to actually update an existing employee,
and we can use the `DELETE` method to actually delete an employee by going to `/api/employees/{employeeId}`.
Now, for `POST` and `PUT`, we'll need to send over the employee data. 
We can send it over as **JSON** in the actual request message body,
and we'll see examples of that later on in the course
and also when we actually run our application.
When we run our app, we'll make use of **Postman**,
and we'll provide that employee data as **JSON** for handling the `POST` and the `PUT`.

![image50](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image50.png?raw=true)

Now, let's talk about some `Anti-Patterns`.
Don't do this, because these are **REST** anti-patterns, they're considered bad practice.
When you actually list out your endpoints don't include the actions in the endpoint.
For example, don't say `employeesList` or `deleteEmployee`, `addEmployee` or `updateEmployee`,
that's not the best practice here.
Instead, you should use the **HTTP** methods to assign the actions.
Alright, so the big thing here is don't include the actions or verbs in the actual endpoint,
use the **HTTP** methods instead.
So make use of the `GET`, `PUT`, `POST`, and the `DELETE`.
And notice here how our endpoints we simply have the entity name or the resource name,
we don't place any behavior in the actual endpoint itself,
we pass that behavior over our action using the appropriate **HTTP** method.
And then, kind of to pull this all together, we'll have **Employee Service**,
we'll make use of **Spring REST**, and we'll have full **CRUD** support for our backend database.

Let's take a look at some other **API** examples.
And so these are some **API** examples from other real-time projects.
Just so you can see how they've designed their **API**s, and also just to see the similarities
between what we've created and also what other larger companies are creating.
So we'll take a look at the **API**s for **PayPal**, **GitHub**, and also **SalesForce**.

![image51](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image51.png?raw=true)

**PayPal** provides an invoicing **API** and you can get full details
on this **API** at the link [here](https://developer.paypal.com/docs/api/invoicing).
Using our **API**, you can perform **CRUD** operations on invoices.
So here, to create an invoice, we simply use a `POST`.
To retrieve or list invoices, we use a `GET`.
And also to show a specific invoice, we do a `GET`, and we pass that invoice ID as a path parameter.
And then, to update an invoice, we make use of a `PUT` method, and we again pass over that invoice ID.
And then finally, to delete an invoice, we simply use the `DELETE` method, 
and then we pass over the given invoice ID as a path variable.
And that's basically it.
So this follows what we've seen in some of our previous examples for our **CRM** application.

![image52](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image52.png?raw=true)

Now, let's take a look at **GitHub**.
They provide a **GitHub Repositories API**.
You can get full details at the link [here](https://developer.github.com/v3/repos/#repositories).
And then using their **API**, you can create a new repository by simply using a `POST` method
to this given endpoints `/user/repos`.
You can delete a repository by going to `/repos/:owner/:repo`.
Now, the `:owner` and the `:repo`, that's the same thing 
as (`{owner}` and `{repo}`) putting curly braces around those items.
So effectively it's a path parameter or a path variable that you can send in 
when you actually submit that request.
Also, you can list repositories by simply doing a `GET` on `/user/repos`.
And then finally, you can get a specific repository by doing a `GET` on `/repos/:owner/:repo`.

![image53](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image53.png?raw=true)

And then finally, we can take a look at the **SalesForce REST API**.
So SalesForce has a large number of **API**s, 
and we'll take a look at one of them here, the **Industries REST API**.
And this matches up very nicely with our **CRM** application
because these are kind of in the same space.
You can get details on this **REST API** at this link [here](https://sforce.co/2J40ALH).
And then basically they give you different endpoints.
So one to retrieve all individuals, you simply do a `GET` on this `/individual`.
To retrieve one individual, you do a `GET` on `individual/{individual_id}`.
To create an individual, we use a `POST`. 
And then, to do an update, we use a `PUT`.
And again, basic format.
Now, one thing that's slightly different here with the **SalesForce API** is 
that they didn't use the plural form of their entity or their resource instead of individuals.
They simply called it individual, singular.
And that's fine.
It's a small, minor thing.
As I mentioned earlier, the convention is to use plural, but there's no hard requirement to do that.
So different **API**s will things slightly different,
but overall here this kind of follows the same pattern
and the same process that we've seen so far with **REST API**s and this is pretty cool.

Upfront we kind of covered the design process for our **CRM** application.
We also looked at some of the other **REST API**s from other real-time projects.
Now, we'll start getting our hands dirty.
We'll get things set up for our given **CRM REST API**, 
and then we'll go ahead and build it out, alright?

So what we'll do is we'll create this real-time project.
We'll develop a **REST API** with **Spring Boot** that'll connect to a database.
Alright, so let's go ahead and look at the **API** requirements
and here's the information from the boss.
We need to create a REST API for the employee directory,
and REST clients should be able to: 

* Get a list of employees
* Get a single employee by ID
* Add a new employee
* Update an employee
* Delete an employee

And these are the basic **CRUD** operations that we'll create with our **REST API**.
Alright, so let's break down the different **HTTP** methods here for our **REST API**.
So we'll use the `POST` method to create a new employee.
We'll use the `GET` method to get a list of employees,
and we'll also use the `GET` method to read a single employee by employee ID.
We'll use `PUT` to update an employee.
And then, of course, we'll use the `DELETE` method to delete an employee by their given employee ID.
And that's our basic **REST API**.

Now let's look at the development process for this project:

1. Set up Database Dev Environment
2. Create a **Spring Boot** project using a **Spring initializer**
3. Get a list of employees
4. Get a single employee by ID
5. Add a new employee
6. Update an existing employee
7. Delete an existing employee

And of course, we'll break this down step by step.

![image54](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image54.png?raw=true)

Now let's take a quick look at our application architecture.
So we'll start with an employee **REST** controller that'll communicate with an employee service,
which in turn will talk to an employee **DAO** to connect to our backend database.

Let's begin with setting up our database table.
So we'll have this file called `employee.sql`.
And so this `employee.sql` file will basically create a new database table for us called `employee`.
And this table will have four fields:

* id
* first_name
* last_name
* email

Also, this script will actually load the table with sample data,
just so we have some employees to work with to get started.
So let's go and swing over to our file system and take a look at that file.
And I'll move into this `dev-spring-boot`.
I'll move into this `04-spring-boot-rest-crud` directory.
It's just a folder.
Just has one file, `employee.sql`.
And what we'll do is we'll actually open this file in **MySQL Workbench**.
I'll go ahead and log into the account here using `springstudent`.
I'll just open the **SQL** script:

```sql
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
```

So this is a very basic file here.
We basically just create this database called `employee_directory`.
And then we move down, or we drop a table if it already exists,
and we simply create the table, `employee`.
Just `id`, `first_name`, `last_name` and `email`.
And then for the insert, I just kind of insert some sample data here for five employees,
just so we can have some data to play with when we start out.
So then let's just hit the little gold lightning bolt on our toolbar 
and that should execute the script.
So on the bottom you should see green and yellow.
So we can see our new schema that we just created, `employee_directory`.
And we should have just one table here called `employee`.
And we can do a real quick query here,
just to select the data to see what's out there.

![image55](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image55.png?raw=true)

And there we go.
So five employees that we'll start with for this project.

Now we'll create a Spring Boot Project using the **Spring Initializr** website.
Let's move over to our web browser and let's generate the **Spring Boot Project**.
So up top here, I'll simply go to `start.spring.io`.
And this will bring us to the **Spring Initializr** website.
Up top, we'll keep all the basic defaults here for the **Maven** project.
We'll keep Java, and then we'll make use of the latest version of **Spring Boot** that's there.
And then for the group ID, I'll put in `com.luv2code.springboot`.
And then for the actual artifact ID, I'll give `cruddemo`.
And then I'll swing over here for our dependencies,
so this is things for our **Maven** `pom` file.
So the one thing we'll need is **web** because we're building a **restful web** application here.
And then we also need to make use of **JPA** because we're doing some database work
with **Hibernate** and **ORM**, and also **JPA** a little later.
Then we'll add in **DevTools** to give us that automatic reloading of our application during development.
And then finally, this one nice little thing here is that
since we're using the **MySQL Database**, 
we can actually reference the actual **MySQL Database Driver** right here in the initializr
and they'll put in the appropriate entry into our `pom` file.
So this is great, so this basically minimizes the amount of work we have to do inside our `pom` file.
Okay, so down at the bottom here just make sure you have the appropriate items selected,
`Web`, `JPA`, `DevTools`, and `MySQL`.
And then just go ahead and generate the project,
and then your web browser will download it, and you'll have this file,
and we'll actually import this into our IDE.
Alright, so I'll go ahead and close my browser here, and I'll just move over to my file system.
I'll move into that downloads directory.
And there's my `cruddemo.zip` that was just created by the **Spring Initializr** website.
I'll go ahead and unzip this file, and then I'll just copy this folder.
I'll just move to my `luv2code` directory, move into my `dev-spring-boot`,
and then I'll just paste the file there.
And again, you can paste it anywhere you'd like on your file system.
So with the import, we have this new project here, `cruddemo`, so this is awesome.
So it's imported into our IDE, and right now it's very bare bones, right?
That **Spring Boot Initializr** just gives you this one `CruddemoApplication.java` file to start with.
But don't worry, we'll add more code to this project over time.

What I'd like to do is look at the `pom.xml` file, just to review what's in here.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.luv2code.springboot</groupId>
	<artifactId>cruddemo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>cruddemo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
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
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
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

So we've seen a lot of this work before, right?
We're simply using the `spring-boot-starter-parent`,
so we can inherit the defaults for like **Maven** configs and so on.
And here we have the actual dependencies that are listed.
So we're adding the `spring-boot-starter-data-jpa` for **Hibernate ORM** support, 
we're putting in `spring-boot-starter-web` for **MVC** and **REST** support.
Also, remember here we have the `spring-boot-devtools` for that automatic reloading, 
and we saw that in some of the previous sections.
And then also the nice little bonus here that we have is this idea of the **MySQL connector Java**.
So that's the **MySQL JDBC Driver** that was automatically added by that **Spring Boot Initializr** website.
And also with **Spring Boot**, they have some built-in support for testing.
So that's also in the given **Maven** `pom` file.
And then just moving down here to the actual build section for the plugins.
So we have this **Spring Boot Maven** plugin.
And remember this is used for packaging our application and running it.
And we did all of that work in some of the previous sections of packaging the app 
and actually running it from **Command** line.
So that's a good start here.
We'll start getting into more details here of setting up our **API**.

![image56](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image56.png?raw=true)

Okay now looking at our application architecture, and we'll make use of the **JPA API**,
the standard **JPA API**, and we'll focus on that.
So we need to perform for our **CRUD** operations,
and what we'll do is we'll build a **DAO** layer for this,
and in this scenario we'll make use of the standard **JPA API** throughout.
Alright, so let's go ahead and look at a **DAO** implementation.

```java
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
}
```

So I'll create this **EmployeeDAOJpaImpl** implements the **employeeDAO** interface.
I'm using the same interface, so we have a consistent **API**.
I'll make use of our field here, `private EntityManager`,
and then I'll set up a constructor here.
And remember that the **EntityManager** is automatically created by **Spring Boot**,
and this is just an example here of making use of constructor injection for that **EntityManager**.

```java
@Override
public List<Employee> findAll() {
    
    // create a query
    TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
    
    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();
    
    // return the results;
    return employees;
}
```

Okay, so let's go ahead and look at some methods here.
So to get a list of employees using the standard **JPA API**,
and then I go ahead and create a query, so I'll say `entityManager.createQuery`,
make use of my JPQL from employee and I give the `employee.class`,
I go ahead and execute the query and get the result list,
and then finally, I return the results or return the `employees`,
and that's basically it, and here we're using the standard **JPA API** throughout.
This is all standard code here.

So let's go and look at the development process here for our DAO.

1. Update our database configs in the `application.properties`,
2. Create the **Employee** entity
3. Create the **DAO** interface
4. Create the **DAO** implementation
5. Create a **REST** controller to use the **DAO**

Let's move to our IntelliJ preferences here, and we'll set up those IntelliJ configurations.
And let's move down to the `Build, Execution, Deployment` and expand it.
And then we'll choose `Compiler`,
and then we'll select this check box here, `Build project automatically`.
Let's go ahead and click on the `apply` button.
And now let's go and select the `Advanced Settings` item.
And then in the `Compiler` section, we'll check the box, `Allow auto-make to start`.
And remember, these are the little IntelliJ configurations 
that we need to do for the community edition to allow it to work with **Spring Boot DevTools**.

Okay, so with our development process, 
we'll focus on the first two steps here of updating the database configs and the app properties, 
and also creating our **Employee** entity.
Let's go ahead and get started with step one of updating our database configs.
And remember, in **Spring Boot**, we have this special properties file `application.properties`,
and that file resides in your source, `main.resources` directory.
And so right now this file is empty, and so this is where I'll add my data source properties.

```properties
#
# JDBC properties
#
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

So I'll add an entry here for the spring data source URL, and I'll give a URL to my database.
And then we simply go through, and we add our spring data source username and also password,
and that's the database connection information for our given user account.
I'll just do a quick copy and paste here on this first line,
paste it, and then just update the property name for password.
And you may need to update this accordingly based on your local environment,
if you have different user IDs or passwords for your local database.
And then remember, **Spring Boot** will automatically
create the beans for data source and entity manager, and we can inject those into our application.

Okay, so let's go ahead and move to our root package, and we'll actually create a new package.
And this package is to actually hold our entity, so I'll just say, `.entity`.
And now I'll actually go through and create the employee entity in that package.
So I'll just create a new class here.
For the class, I give **Employee** as the name.

```java
package com.luv2code.springboot.cruddemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    
    // define fields
    
    // define constructors
    
    // define getter/setter
    
    // define toString
    
}
```

So what we need to do here is create an entity and map this entity to our given database table.
So we make use of the `@Entity` annotation and also the actual `@Table` annotation,
we map it to our given table, **employee**.
And now what I'll do is I'll just write some quick comments
to myself just so I know what I need to create in this given file.
Alright, so basically I need to define fields, define constructors, 
define getters and setters, and also define a two stringing method.

```java
package com.luv2code.springboot.cruddemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    
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
    
    // define getter/setter
    
    // define toString
    
}
```

Let's go ahead and get down to business.
So I'll start here with defining the fields.
So I'll set up a field for _id_, _firstName_, _lastName_, and _email_.
And now I need to add the annotations here for each one of the fields.
So my id is the primary key, so I'll make use of the `@Id` and `@GeneratedValue`.
And we've seen all this coding before.
And now I'll make use of the `@Column` annotation to map these fields 
to a given database column name.
So I'll set up `first_name`,
and I'll actually just copy this line, and then move down and paste it.
And I'll just update the name there.
So these actually have to be the column name,
they have to be `first_name` and `last_name`.
Okay, that's all the work here for defining the fields for this `Employee` entity.

```java
// define constructors
public Employee() {
    
}

public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
}
```

So we can move down to this next section here of defining constructors.
And I'll start off here by just creating a no argument constructor.
And now I'll also create a constructor using the fields,
so I use a source generate constructor, and I'll select all of them except _id_ 
because _id_ is going to be automatically generated.
So have _firstName_, _lastName_, and _email_.
So those are the three that you should have selected for this example.
So those are our constructors here for this `Employee` entity.

```java
// define getter/setter
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
```

Alright, now just more of the same, right? 
You know how to do all this stuff, just defining the getters and setters.
So we'll actually have the IDE to generate these for us.
So we simply go to source, generate getters and setters, just do `select all` here.

```java
// define toString
@Override
public String toString() {
    return "Employee{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
}
```

Now let's define our `toString()` method, and again, 
just using the IDE to generate that for us.
There we go, we're in pretty good shape there with our `toString()` method.
And we have everything in place here for our entity class.

Now, we'll focus on steps three and four of creating the `DAO` interface and the implementation.
So the first thing we should do is create a new package here for our `DAO`.
And of course, the name that we'll give for the package is `DAO`.
Okay, so there's our new `DAO` package.
So let's go ahead and create our **DAO** interface.
So just do a new interface.
And the name we'll give here is `EmployeeDAO`.

```java
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
```

This DAO will simply have just one method. 
Just to get things up and running,
we'll add more functionality in some of the later sections.
So we'll simply add this method here, `findAll()` that'll return a list of employees.

```java
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
```

Alright, so with our next step here is creating the `DAO` implementation.
So I'll move back to my `DAO` package, and I'll just create a new class named by `EmployeeDAOJpaImpl`.
Now I need to mark this or annotate this as `@Repository`.
So what I need to do here is to define the field for **EntityManager** 
and also set up constructor injection.
I'll set up the constructor injection with the `@Autowired` and an actual constructor.
And I provide item or the beam that we're that we want to inject here
to the **EntityManager** and simply doing assignment.
Remember, the **EntityManager**s automatically created by **Spring Boot**,
and we can simply inject it here into our application.

```java
@Override
public List<Employee> findAll() {
    
    // create a query
    TypedQuery<Employee> theQuery = entityManager.createQuery("from Emplyoee", Employee.class);
    
    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();
    
    // return the results
    return employees;
}
```

Okay, so let's go ahead and move down to this _findAll_ method,
and I'll write some quick comments here to myself.
And so this is kind of our basic game plan here for finding all the `employees`.
Okay, so let's go ahead and just write the coding here for creating a query.
So I use `entityManager.createQuery()` and I'll say `From employee`.
Now we just go through and execute `theQuery.getResultList()`.
And then finally, we return results or the `employees`.
And this is basically it for using a standard **JPA API**.
Very simple, very straightforward.

So our final step here is creating our **REST** controller to use our **DAO**.
And the first thing I want to do here is actually create a new package.
And so this will be a package for holding our **REST** controllers.
So we'll give the name of the package `.rest`.
Okay, so now we have our new package.
The next step is creating a **REST** controller to use our **DAO**.
So I'll just create a new class here.
And the name of the class, I'll call it `EmployeeRestController`.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    // quick and dirty: inject employee dao (use constructor injection)
    public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}

```

Now I'll write some quick comments to myself.
So we'll do a quick and dirty solution.
We'll inject the `EmployeeDAO` directly.
We'll refactor this later with the service layer, but for now we'll just put it in here,
so we can run some examples.
And then also we'll expose the employee's endpoint that'll actually return all the employees.
We'll just give our annotations up top for `@RestController` and `@RequestMapping("/api")`.
So the base mapping will be `/api`.
So I'll define the field here for the `employeeDAO`.
And now I'll set up constructor injection here to inject that `EmployeeDAO`.
Alright, so that's our constructor then.
We're using constructor injection here.
Okay, so let's go ahead and write the code to return a list of employees.
So I'll simply set up a `@GetMapping("/employees")`.
And I simply write the code here for this `findAll` that returns a list of employees,
and I simply delegate the call to my **DAO**.
So I'll say `employeeDAO.findAll()`.
And that's the basic coding there to return a list of employees.
So that's the basic coding there for our controller.
We simply to define the field.
We set up constructive injection, and then we expose that endpoint to return a list of employees.
Very simple, very straightforward.
I think I'm ready to run it.
So let's go ahead and move to our `CruddemoApplication`.
That's our main spring boot application and run it as an application.

```html
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-29T15:38:56.249+03:00  INFO 94224 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : Starting CruddemoApplication using Java 21.0.2 with PID 94224 (D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\02-spring-boot-rest-crud-employee\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\04-spring-boot-rest-crud\02-spring-boot-rest-crud-employee)
2024-05-29T15:38:56.251+03:00  INFO 94224 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : No active profile set, falling back to 1 default profile: "default"
2024-05-29T15:38:56.301+03:00  INFO 94224 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-05-29T15:38:56.302+03:00  INFO 94224 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-05-29T15:38:56.742+03:00  INFO 94224 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-05-29T15:38:56.757+03:00  INFO 94224 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 9 ms. Found 0 JPA repository interfaces.
2024-05-29T15:38:57.170+03:00  INFO 94224 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-05-29T15:38:57.181+03:00  INFO 94224 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-05-29T15:38:57.181+03:00  INFO 94224 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-05-29T15:38:57.225+03:00  INFO 94224 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-05-29T15:38:57.226+03:00  INFO 94224 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 923 ms
2024-05-29T15:38:57.408+03:00  INFO 94224 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-05-29T15:38:57.823+03:00  INFO 94224 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@18ba8b8e
2024-05-29T15:38:57.824+03:00  INFO 94224 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-05-29T15:38:57.855+03:00  INFO 94224 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-05-29T15:38:57.902+03:00  INFO 94224 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-05-29T15:38:57.930+03:00  INFO 94224 --- [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-05-29T15:38:58.207+03:00  INFO 94224 --- [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-05-29T15:38:58.821+03:00  INFO 94224 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-05-29T15:38:58.822+03:00  INFO 94224 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-05-29T15:38:58.915+03:00  WARN 94224 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-05-29T15:38:59.266+03:00  INFO 94224 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-05-29T15:38:59.295+03:00  INFO 94224 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-05-29T15:38:59.303+03:00  INFO 94224 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : Started CruddemoApplication in 3.305 seconds (process running for 3.618)
```

Excellent. 
So our app is now up and running.
So let's go ahead and swing over to our browser 
and let's visit the url, `localhost:8080/api/employees`.

![image57](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image57.png?raw=true)

Success. 
So we have data.
So this is data coming from the database right now via our **REST API**.
But again, I always like to verify this, right?
Like to go over to the MySQLWorkbench and actually do a query on the database
to make sure that really is the right data.
All right, so let's go ahead and move over to our tables, our employee table,
and just run a quick query here and verify the results.

![image58](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image58.png?raw=true)

And yay, this works out as desired.
So this is the real data coming from the database.
So at the moment now, we only have the list function running or returning all employees.
Let's finish up the remaining **CRUD** operations.

Now, we're going to define services with the `@Service` annotation.
We're going to add a service layer. 
This service layer will actually sit between our **Employee** controller and our **employeeDAO**.
Now, you may wonder, what's the purpose of the service layer?

![image59](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image59.png?raw=true)

Well, it's actually an implementation of the **Service Facade** design pattern.
It's an intermediate layer for your custom business logic,
and you can also use it to integrate data from multiple sources.

![image60](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image60.png?raw=true)

Now, here's an example of integrating data from multiple sources.
For example, we have our employee service, 
and we need to pull in data about an employee from different types of data sources.
Up top, we'll make use of the **EmployeeDAO**, which gives us the basic information,
like the first name, last name, email address, and so on.
We also may need to get a list of skills for an employee.
We can make use of the **SkillsDAO**.
And then finally, at the bottom, we need to make use of the **PayrollDAO**
to retrieve salary information for an employee.
And then, we'll integrate all of this together,
so that we can give the controller a single view 
of all the data that we've integrated and pulled together.

Now, most times when you start off, you'll have just a very simple architecture.
You'll have your employee service that simply delegates the calls to the **EmployeeDAO**.
And this is fine, and this is a very good pattern to start with.
So this is actually a best practice, to create a service layer and a DAO layer,
because now once you have the basic architecture in place,
you can easily expand and extend on it.
And this is actually a best practice, and you'll see this in place
on large-scale enterprise applications.

![image61](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image61.png?raw=true)

Alright, so we've covered the architecture stuff.
Now, **Spring** provides the `@Service` annotation.
It's a specialized annotation for services.
Looking at the diagram here, we've seen some of these annotations already.
We've already covered `@Component`, `@RestController` and `@Repository`.
Now, the new guy on the block here is `@Service`.
The `@Service` annotation is actually applied to the **Service implementation**.
**Spring** will automatically register the **Service implementation** thanks to component scanning.
Alright, now let's go ahead and look at our development process for building up employee service:

1. Define the **Service** interface
2. Define the **Service implementation**
3. Inject the **EmployeeDAO**

Starting here with step one of defining the **Service** interface, it's very simple.
So the first thing we'll need to do is create a new package for the service.
So I'll just start here at my root package,
and I'll just create a brand-new package.
And the name that I'll give for this package is `service`.
Alright, so there's our new package.
Now let's go ahead and create a new interface in this package.
It's a public interface called **EmployeeService**, 
and we have a method called _findAll_, and that's it.

```java
package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
}
```

Very simple, very straightforward.
Now moving ahead to step two, we actually need to define the **Service implementation**.
We'll create this class here called **EmployeeServiceImpl**
that implements the **EmployeeService** interface.

```java
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    // inject EmployeeDAO
    private EmployeeDAO employeeDAO;

    // set up constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
```

Now, note here at the top you have the `@Service` annotation.
**Spring** will automatically register this component thanks to component scanning.
Then we inject our DAO using constructive injection for this example.
So that's all set up, our field and our constructor injection.
And these service methods are really simple, 
all we're doing is simply delegating the calls to the **DAO**.
We're basically calling the same methods on the given **DAO**.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    // quick and dirty: inject employee dao (use constructor injection)
    public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
```

Alright, so let's go ahead and move into the **EmployeeRestController**,
because we need to kind of refactor the code.
Earlier, we had that quick and dirty solution
and let's actually remove that quick and dirty solution.
So, instead of the controller using the **DAO** directly,
we're going to refactor our code where the controller will actually make use of the service.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
}
```

So I'll just delete these lines here, for the **DAO**, 
and I'll add in a reference here to use the **EmployeeService** to
kind of follow our application architecture.
And I'll update the constructor here to make use of the correct types and params coming in.
And one last thing to fix here.
So instead of doing the `EmployeeDAO`, we'll change it to use the `EmployeeService`.
So that's the refactored code.
So now our rest controllers are going to make use of our `EmployeeService`,
and we already know behind the scenes, our **service** actually delegates the calls to the **DAO**.
Okay, so this is good.
So let's go ahead and run our application just so we can view the output here.
And we'll swing over to our web browser.

![image62](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image62.png?raw=true)

It works out just fine.
So we're getting the same data as before.
Now we're using the **service** instead of using the **DAO** directly.
</div>

## [Data Access Object (DAO): Add, Update, Delete]()
<div style="text-align:justify">

In this section, we're going to cover the **DAO** methods to find **add**, **update**, and **delete**.
Now, before we get into the **DAO** discussion,
I want to mention a best practice regarding the **service** layer.

The best practice is to apply transactional boundaries on the **service** layer.
It's really the **service** layer's responsibility to manage the transaction boundaries.
What this means for our implementation code is 
that we'll apply the `@Transactional` annotation on our service methods,
and we'll actually remove the `@Transactional` annotation on our DAO methods if they already exist.
So effectively here we're going to shift the `@Transactional` annotations
from the **DAO** methods over to the **service** methods,
and that's the best practice for managing transactions
whenever you're making use of **service** layers and **DAO**s at the same time in the same architecture.

Now, just picking back up on our development process,
we're going to focus on the last couple of steps of adding our DAO methods
for getting a single employee, adding a new, updating, and also deleting an existing employee.
And so we'll focus on the **DAO** aspect of it here.

```java
@Override
public Employee findById(int theId) {
    
    // get employee
    Employee theEmployee = entityManager.find(Employee.class, theId);
    
    // return employee
    return theEmployee;
}
```

Alright, let's get started with the **DAO** of getting a single employee.
So we'll have this method here _findById_, pass in the integer _theId_.
We make use of this `entityManager.find`, and we give that _theId_.
This will return _theEmployee_, and then we simply return this given employee from our method.

```java
@Override
public Employee save(Employee theEmployee) {
    
    // save or update the employee
    Employee dbEmployee = entityManager.merge(theEmployee);
    
    // return dbEmployee
    return dbEmployee;
}
```

And now the **DAO** method to add or update an employee will have this method 
called _save_ that'll pass on _theEmployee_,
and what we'll do is we'll make use of a save or update feature here.
So we'll make use of this `entityManager.merge`, and we pass in `theEmployee`.
Now, the way this _merge_ method works here is 
that it'll perform a save or update depending on the actual id of the entity.
If the id of the entity is equal to zero, 
then it'll actually save or insert that given entity into the database.
So that's like a new entry there.
Else if the id is not equal to zero, it'll simply perform an update.
So effectively here if id is zero, it's going to be an `insert`, else it'll be an `update`.
And this method will return the updated employee.
So here at the bottom here, we say return `dbEmployee`.
So it's really important here is that you need to return the `dbEmployee`.
This is the one that's been updated from the database,
so it has the updated id from the actual database in the case of an `insert`.
Alright, so make sure you return the correct one 
when you make this given return statement,
because effectively you want the latest version from the database, not the one that was passed in.
Also, notice here at the top, we don't use the `@Transactional` annotation at the **DAO** layer
because this will be handled at the **service** layer.
So again, remember, when you're making use of **DAO**s and **service**s together, 
the best practice is the **DAO** does not handle the `@Transactional`,
instead, it's managed at the **service** layer.

```java
@Override
public void deleteById(int theId) {
    
    // find the employee by id
    Employee theEmployee = entityManager.find(Employee.class, theId);
    
    // delete the employee
    entityManager.remove(theEmployee);
}
```

Now, here's the DAO method for deleting an existing employee.
So we have this method here, _deleteById_.
We pass in `theId`.
The first thing we do is we find the employee, and then we simply say, 
`entityManager.remove(theEmployee)`.
And I'd like you to delete that employee from the database.
And a similar thing here for this _delete_,
since we're modifying the database, we don't use `@Transactional` at the **DAO** layer,
it'll be handled at the **service** layer.

So we'll add these new methods to our **employeeDAO**.
So I'll just go ahead and open up `EmployeeDao.java`.
And I'll just add the other methods here, to give us our full **CRUD** support.
And I'll just go ahead and just type them out real quickly.

```java
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
```

So we can find an employee by id, and then we can save an employee,
and then also delete an employee by id.
Alright, so those are the three methods here that we added for this given **DAO** interface.
We add these methods to our **EmployeeDAO implementation**.
</div>

## [Spring Boot Service: Add, Update, Delete]()
<div style="text-align:justify">

In this section, we'll create our service in the associated methods,
and we'll also refactor some of our code.
Alright, so in our development process, 
what we're going to do is focus on the methods for accessing our system, 
and we'll do that via the **Employee Service**.
So we'll actually create the **Service** methods for our given application architecture.
And as I mentioned, we'll do a small bit of refactoring on some of our code too.
Let's add the new methods to our **Service** interface.
We can actually copy these methods from the **DAO** interface.
Let's go ahead and do that now.
I'll open up my **EmployeeDAO**, and I'll grab these three methods here,
`findById`, `save` and `deleteById`.
And I'll go ahead and copy those methods.
Now, move over to my **EmployeeService** interface, and I'll go ahead and paste those methods.
Now, let's move to the service implementation and let's add the code for these methods.

```java
package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // inject EmployeeDAO ...
    private EmployeeDAO employeeDAO;

    // set up constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}
```

And these service methods are really simple.
All we're doing is simply delegating the calls to the **DAO**.
So we're basically calling the same methods on the given **DAO**.
It's very, very straightforward.
We're simply delegating the calls from the **service** to the **EmployeeDAO**.
Since we're modifying the database, we need to make use of the `@Transactional` annotation.
Remember we're letting the **service** layer manage the transactions.
Hence, we'll add the `@Transactional` annotation on this **service** method.
Because remember earlier, we didn't place it on the **DAO**'s
because we were going to let the **service** handle it for us.
And now a similar thing on `deleteById`, since we're modifying the database,
we need to make use of the `@Transactional` annotation,
and we'll place it here in this method in the **service** layer.

Now, we'll look at the **REST** controller methods for finding and adding an employee.
So again, in our big development process, 
we'll focus in on these steps here of getting a single employee by id 
and also adding a new employee.
This is primarily for the **REST** controller methods.
We already did this work for the **services** and the **DAOs** already.

| HTTP Method   | Endpoint                      | CRUD Action                 |
|---------------|-------------------------------|-----------------------------|
| `POST`        | `/api/employees`              | Create a new employee       |
| `GET`         | `/api/employees/{employeeId}` | Read a list of employees    |
| `GET`         | `/api/employees`              | Read a single employee      |
| `PUT`         | `/api/employees`              | Update an existing employee |
| `DELETE`      | `/api/employees/{employeeId}` | Delete an existing employee |

Okay, so now just doing a checkpoint here on our realtime project 
as far as the list of **API** methods.
So we've already completed the first one here of `GET` to read a list of employees.
Now we'll focus on actually doing a `GET` for a given employee id for a single employee
and also doing a `POST` to create a new employee.

![image63](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image63.png?raw=true)

Now let's look at the application interaction 
between our **REST** client and our **REST** controller.
So with the **REST** client, we'll do a `GET` `/api/employees/{employeeId}`
and it'll return that single employee in this case `David Adams`.

![image64](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image64.png?raw=true)

Now let's look at creating a new employee.
So to create a new employee, we use the `POST` method
and in the **JSON** body we give the actual information for the `firstName`, `lastName`, and `email`.
And since this is a new employee, we're not passing over an `id` or a primary key 
because that'll actually be generated for us on the backend by the database.
So then we'll get the actual response back.
So they'll basically echo the response, and they'll also include the actual new idea
of the primary key that was generated by the database.

Now a couple of things here, just as a refresher or reminder.
So when sending **JSON** data to **Spring REST** controllers,
we have to make sure that we send over the correct **HTTP** request header.
And that header is the content-type of `application/json`.
So we need to configure our **REST** client to send the correct request header 
just so it processes the data accordingly. 

Now again, as a refresher here, we're using **Postman** as our client.
So once we have the url up for **Postman**, then we click on `Body`, 
then we select `Raw`, and then we choose the content-type of `JSON(application/json)`.
And based on these configs, **Postman** will automatically set the correct request header.
</div>

## [Spring Boot REST: Get Single Employee]()
<div style="text-align:justify">

Okay, so we're moving ahead with our development process,
so here we're going to focus on the **REST** controller methods
to get a single employee by id and also add a new employee.
Let's go ahead and open up our `EmployeeRestController.java`.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }
}
```

And so this is step four of reading a single employee.
So we'll make use of a get mapping, and we'll read the employee by the `employeeId`.
So I'll set up a get mapping for `/employees/{employeeId}` as a path variable in curly brace.
And the method signature is `getEmployee`, you take a parameter for path variable
as `employeeId` and it returns a given **employee** object.
And then I'll simply delegate these calls to the `employeeService`.
So I'll make use of the `employeeService` at `findById` and I pass in the `employeeId`.
And also I'll just add a `if-then` check here to check if it's `null`
that means that we didn't find the employee in the database.
And so we'll simply throw an exception here.
And if everything's fine, we simply return the employee.
And that's basically it there for the coding for `getEmployee` by given `employeeId`.
Now I can actually run our application and test this out here.
So I'll just run this as a Java application.
Alright, so let's go ahead and swing over to our browser.

![image65](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image65.png?raw=true)

And I see that I have `Leslie Andrews` with id of one, `Emma Baumgarten`, id of two.
So let's just test this out here.
So `/1` for `Leslie`, it should return `Leslie`:

![image66](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image66.png?raw=true)

Success.
This is exactly what we're looking for.
And let's try it for ID of two.

![image67](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image67.png?raw=true)

And that returns `Emma`.
So this is working.
Now one thing I want to do is actually also test this same thing in **Postman**
just to see how things work over on the **Postman** side of things.
So I have Postman open.
I need to give the URL.
So I'll just do a little copy and paste from my browser over here,
swing back into **Postman** and paste that.
Alright, so that's the id of two right now, and if I send that, we should get `Emma` coming back.

![image68](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image68.png?raw=true)

Great.
So this is working just the same in **Postman** also so this is great.
And let's just go ahead and add a new tab here and let's just get all employees,
so I could paste that same piece and just take off the id,
and should give me everyone in the list.

![image69](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image69.png?raw=true)

Okay, great, the same thing we saw in the browser.
So `Leslie`, `Emma`, and so on.
Great, so we're in good shape here with that method implementation
for getting a single employee by id.
</div>

## [Spring Boot REST: Add Employee]()
<div style="text-align:justify">

Now step five, we need to write some code to add a new employee via our **REST API**.
And this will actually be a `POST` to `/api/employees` for adding or creating a new employee.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee,
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
}
```

So we know that the employee data is going to come in the request body as **JSON**.
So we need to make use of this `@RequestBody` annotation to handle binding that **JSON**.
Alright, so this is very similar to what we did in some of our previous sections.
So, just in case they pass an idea in the **JSON**,
we're gonna manually or explicitly set it to zero.
This is to force a save of a new item and **insert** instead of doing an **update**.
So here I have `theEmployee.setId(0)`.
And that's the basic method here for adding an employee,
so uses `@PostMapping` to actually add a given employee
and that employee data comes across in the `@RequestBody`.

Let's swing back over to **Postman**.
And we're going to change this method here to `POST`.
So we're posting data, and we'll select `Body`, `Raw`, `JSON`,
because we're sending over `JSON` data, and we need to do this.
**Postman** will send over the correct content type.
And now in this `Body` here, we can actually just go ahead and write the actual `JSON` data
that we're going to send over for this new employee.

```json
{
    "firstName" : "Hector",
    "lastName" : "Perez",
    "email" : "hector@luv2code.com"
}
```

So I give `firstName`, `lastName` and `email` for this given employee.
Let's go ahead and send it.
And then we scroll down to the bottom:

![image70](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image70.png?raw=true)

And, success!
We see this new employee ID.
So that was generated by the database.
Alright, now let's go ahead and confirm this.
So back in **MySQL Workbench** let's go ahead and just execute that query again,
and just see the results from our database:

![image71](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image71.png?raw=true)

And oh yeah, success!
So we have this new employee, `Hector Perez`, and he has the id of six.
So this all matches up with what we have on the frontend 
and also what we have here on the backend.
</div>

## [Spring Boot REST: Update Employee]()
<div style="text-align:justify">

Now, we'll look at **Rest Controller** methods for `update`.
So we're almost done here, almost finished with the development process.
So step six and seven of updating an existing employee,
and also `delete` an existing employee,
and we'll add these as our **Rest Controller** methods.

| HTTP Method   | Endpoint                      | CRUD Action                 |
|---------------|-------------------------------|-----------------------------|
| `POST`        | `/api/employees`              | Create a new employee       |
| `GET`         | `/api/employees/{employeeId}` | Read a list of employees    |
| `GET`         | `/api/employees`              | Read a single employee      |
| `PUT`         | `/api/employees`              | Update an existing employee |
| `DELETE`      | `/api/employees/{employeeId}` | Delete an existing employee |

And just as a checkpoint here, we've already covered the first three methods.
Now we'll cover `PUT` for doing an `update`,
and also `DELETE` for actually deleting an employee.

![image72](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image72.png?raw=true)

Okay, to update an employee, we'll send over a `PUT` request
for `/api/employees` and in the body we'll have **JSON**,
and this will actually have the id of the employee 
to update with the updated information for that employee.
So then our controller will send back the actual response here,
and this response is basically just the updated information,
and we simply echo back to the **REST** client.

![image73](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image73.png?raw=true)

And now look at the other scenario here of deleting an employee, 
so we send over a `DELETE` request for `api/employees/{employeeId}`
and then the **Rest Controller** will send back deleted employee id, 
and they'll simply plug in that id.

Okay, let's knock out the last two steps of this development process
of adding the `update` and `delete` methods to our **Rest Controller**.
I'll open up our employee **Rest Controller** here.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee,
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }
}
```

And so this is `update` employee.
So I'll make use of the `@PutMapping`.
So, I have this method here, _updateEmployee_.
I'll make use of the annotation `@RequestBody`
because the employee data's going to come in as **JSON** in the `@RequestBody`.
And this is a really straightforward example here.
I simply give `employeeService.save`, just delegated off to the service.
And that's pretty much it.
Very straightforward for this example here.
And this will actually handle the `update` for the given employee.
Okay, so let's test this in **Postman**.
I'll change the method to `PUT` because we're doing an `update`.
And for the actual body, I'll give some updated information for a given id.

```json
{
    "id" : 1,
    "firstName" : "Timothy",
    "lastName" : "Patterson",
    "email" : "tim@luv2code.com"
}
```

So here I want to change the ID of one, or the employee that has the ID of one,
and give them some new information.
So I'll just change the data.
I'll give a different `firstName`, `lastName`, and `email` address.
This is all should apply to `employeeId` of one.
So an update on `employeeId` of one.
So we have `Tim Patterson`.
Alright, so this looks good.
Let's go ahead and send it.
And let's just scroll down to the bottom for the response.

![image74](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image74.png?raw=true)

Okay, so basically the response is just an echo of the data that we passed on.
So that's good, but again, I always like to verify this in **MySQL Workbench**.
So let's go in here.
And so first, for our employee ID of one, this is our old data.

![image71](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image71.png?raw=true)

The old data is `Leslie Andrews`.
But if we run the query again, we should see the new data or the updated data.

![image75](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image75.png?raw=true)

And success.
So for employee ID of one, the name is now `Tim Patterson`.
So this `update` is working as desired.
Okay, so that's basically it here for the `update` example.
</div>

## [Spring Boot REST: Delete Employee]()
<div style="text-align:justify">

Now look at step six to `delete` an employee.
So we're going to delete an employee by employee id.
So we'll set up a `@DeleteMapping` for `/employees/{employeeId}`.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee,
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
```

So we have a method here, `deleteEmployee` and we simply make use of `@PathVariable`,
and we bind that path variable to this method parameter `employeeId`.
And first, I'll actually find the employee by id.
So I'll check the employee here to see if they're `null`.
If they're `null`, I'll simply throw an exception meaning that
"_hey, this employee didn't exist in our database_".
If everything's okay, I simply just do `employeeService.deleteById`.
Again, just delegating it to the actual service.
And then I just return the string as far as what I did.
"_Hey, I deleted employee ID of blah_".
So that's all the main coding there for deleting an employee.

Okay, so moving back to the **Postman**,
what I'll do is I'll actually duplicate this tab again, and I'll change the method to `delete`.
And for `Body`, since we're not sending any data in the `Body`, I'll hit `none`.
And now I simply give the employee id that I want to delete.
So here I'll say `delete employeeId = 1` with the link, `http://localhost:8080/api/employees/1`.
Hit send.

![image76](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image76.png?raw=true)

All right, success.
So it says that `deleted employee ID of 1`.
Alright, so let's swing over to **MySQL Workbench** and let's just do a refresh on this data.

![image77](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image77.png?raw=true)

Okay, excellent.
So the employee with the ID of 1 has been deleted, so that's successful.
Everything is working as desired.
Let's try one more example.
Let's delete `id = 5`.
So on our **Postman** we'll say `http://localhost:8080/api/employees/5`, we'll do a `send`:

![image78](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image78.png?raw=true)

We get the response that it's been deleted successfully.
Swing back over to **MySQL Workbench**, and just go through the drill again.
So there's the old data, we do a refresh.

![image79](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image79.png?raw=true)

Employee id of 5 has been deleted successfully.
So we took care of the `update` portion, and we also took care of the `delete` portion.
</div>

## [Spring Boot REST: Spring Data JPA]()
<div style="text-align:justify">

In this section, we'll cover **Spring Data JPA** in **Spring Boot**.
Now just reviewing our application architecture,
so in previous sections we made use of the standard **JPA API**.

![image80](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image80.png?raw=true)

And so now we'll kind of shift over and make use of **Spring Data JPA** for our backend **DAO**.
So we saw how to create a **DAO** for `Employee`.
So we defined an interface for `EmployeeDAO` and then we created an `EmployeeDAOImpl` implementation,
and we wrote all of that code.
But now the question is what if we needed to create a **DAO** for another entity like `Customer`, 
`Student`, `Product`, `Book`, etc.?
Do we have to repeat all the same code again?
Because that's a lot of work just for one entity.

```java
@Override
public Employee findById(int theId) {
    
    // get data
    Employee theData = entityManager.find(Employee.class, theId);
    
    // return data
    return theData;
}
```

One thing that you may have noticed with creating a **DAO** is that 
there's a pattern for creating these **DAO**s.
So looking at this one method here, `findById`, most of the code is the same.
The only difference is the entity type and primary key.
So here we have `Employee`, and `theId`.
So `Employee.class` is the entity type and then `theId` is the actual type of the primary key.
But if we wanted to use something else like product, book or customer, it's really the same thing.
And it would feel like a copy and paste exercise to do this again and again 
for all these other entities.
And so there really should be an easier way.
I wish I could tell Spring, "_Hey, create a DAO for me.
I'll simply plug in my entity type and primary key,
and you will give me all the basic **CRUD** features for free._"
That's my wish.
I hope I'm not asking you for too much but there should be a simple solution for this.
So, I want some **CRUD** methods 
like `findAll()`, `findById(..)`, `save(..)`, `deleteById(..)` and others.
And I'll simply tell you my entity type of `Employee`,
and I'll tell you the type of my primary key of `Integer` and just give me everything for free.
And then if I have another entity type like `Customer`,
I simply plug that in or `Product`, I'll plug that one in.
Or here back to my `Employee`.
I'll simply just plug in the entity type, and you'll simply give me these **CRUD** methods for free.

There is a solution and it's called **Spring Data JPA**.
It's one of the projects in the [website](https://spring.io/projects/spring-data-jpa),
and you can use it with **Spring** and also **Spring Boot**.
So basically you create a **DAO**, and you just plug in your entity type,
and you also plug in your primary key 
and then **Spring** will give you a **CRUD** implementation for free like magic.
What do you mean here?
Well, this will actually help us minimize our boilerplate **DAO** code.
So we don't have to write all of that code, 
so we can actually get more than a 70% reduction in code depending on the use case.
If you take everything off the shelf or out of the box,
you can actually get more of a reduction.
If you have to do a lot of low-level customizations, well then your mileage may vary.
But in general, you can greatly reduce the amount of code you have to write 
by using this **Spring Data JPA**.

Alright, so **Spring Data** provides an interface called **JPA Repository**, 
and it exposes methods, some of them inherited from their parents,
and these are **CRUD** methods that you can use.
So it exposes `findAll()`, `findById(..)`, `save(..)`, `deleteById(..)` and others.
And so you simply plug in your entity type and the type of your primary key,
and then you'll magically get access to all of these CRUD methods for free
without having to write any implementation code.

So for the development process:

1. Extend the **JPA Repository** interface
2. Use the **Repository** in our application

And the key thing here is that there's no need for an implementation class.
You don't have to write an **Impl**, you don't have to write a lot of code.
You simply extend the interface, and then you get all these **CRUD** methods for free.

```java
public interface EmployeeRepository extends JpaRepository<Emplyoee, Integer> {
    
    // that's it ... no need to write any code...
}
```

So step one, extending the **JPA Repository interface**.
So we have this employee repository extends `JpaRepository`,
and then we simply plug in our entity type of `Emplyoee`,
and then we plug in the type of our primary key of `Integer`.
That's kind of it.
There's no need to write any other code.
That's the real work.
So by doing that, you get these **CRUD** methods for free,
`findAll()`, `findById(..)`, `save(..)`, `deleteById(..)` and others.
You get all of these methods for free.
And it's like magic, simply by extending this `JPARepository` interface.
And again, remember, there's no need for an implementation class.
There's no need to write **Impl** or to write a ton of code.
This is basically it as far as the real coding here.
And you can look at the documentation for `JPARepository` to get the full list of methods.
So you can go to my site [here](https://www.luv2code.com/jpa-repository-javadoc),
and I'll redirect you to the official **Spring** documentation for this interface.

```java
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }
    
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    
    // ...
}
```

Alright, so with step two of using a repository in your application,
we basically set up our service here, our `EmployeeServiceImpl`, 
implements `EmployeeService` and make use of our new repository 
that we just created, `EmployeeRepository`.
And then I'll just set up some constructor injection here.
And then our employee service is going to make use of our `EmployeeRepository`
and our repository makes use of **Spring Data JPA** to give us that **DAO** functionality.
And so here's a quick example of a method here.
So our method in our service called `findAll()`.
What we'll do is we'll simply return `employeeRepository.findAll()`.
So we're calling a method on our repository.
And this is one of those magic methods that are available via the repository 
that we get for free like magic by simply extending off of that **JPARepository** interface.

![image81](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image81.png?raw=true)

Now, the key thing here is that by using **Spring Data JPA**, we've minimized the boilerplate code.
So now this is how we would write code before **Spring Data JPA**.
We'd create our interface for our given entity, 
and we'd write our repository or our **DAO** implementation.
So 2 files, 30 plus lines of code.
Actually more lines of code, but I'm just being nice here and saying 30 plus.
But now over on the right-hand side, after **Spring Data JPA**,
we create this interface employee repository.
So it's one file, three lines of code.
No need for an implementation class.
And we get all of those **CRUD** methods for free like magic.
It makes it very simple for us to create a repository or a **DAO** back in.
And then over on the left-hand side, you know, we don't have to write that code anymore.
We're done with that type of work.
Now that we're using **Spring Data JPA**,
our life is much easier, our life is much simpler.

Now, **Spring Data JPA** has some advanced features:

* Extend and add custom queries with JPQL
* Query Domain Specific Language (query DSL)
* Defining custom methods (low-level coding)

And so I have a link [here](https://www.luv2code.com/spring-data-jpa-defining-custom-queries) 
where you can go to get more details on some of those advanced features.


One other thing I want to do is actually refresh our database table,
because we added a lot of things deleted, updated, and so on.
So let's move over to **MySQL workbench** and just log into our `springstudent` account.
And basically, we just want to run that script that we used earlier
as far as creating the table and inserting the sample data.

```sql
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
```

So here it is.
So this is all of our fresh data here, so we're good to go.
So we have a standard baseline to work from.
And so basically, we'll just do a copy-paste on `02-spring-boot-rest-crud-employee`,
to create a new package, named as `03-spring-boot-rest-crud-employee-with-spring-data-jpa`.
I'll delete `target` folder in it.
Now, we can import the project into IntelliJ.
So one thing I want to do is delete our old **DAO** code.
We don't need it anymore, so I'll just go ahead and remove it and get rid of it all together.
And don't worry, we have different copies of projects.
So we have this code in some previous projects.
So for this one, we'll just remove it or delete it.
And what I'll do here with step one is to create a **Spring Data JPA Repository**.
So that's actually a new interface.
And so I'll give a name for this interface, I'll call it `EmployeeRepository`.
And I'll say `extends JPARepository`.
So make sure you choose **JpaRepository**, just the interface.

```java
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // no need to write any code here
}
```

So we should have our interface **EmployeeRepository** extends **JpaRepository**.
And then for the entity type, I'll say `Employee`.
And then for the actual primary key type, I'll give `Integer` 
because our **Employee** class is based on an **Integer** primary key.
So again, Entity type, Primary key.
And that's basically it.
There's no need to write any code.
We get these **CRUD** methods for free, and there's no need to write an implementation class.
It's all the beauty of **Spring Data JPA**.

In step two, we need to use the **Repository** in our application.
So here I'll move into this **EmployeeServiceImpl**,
and I'll just kinda refactor the code from using our old **DAO** to make use
of this new **EmployeeRepository**.

```java
package com.luv2code.springboot.cruddemo.service;

// import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // inject EmployeeDAO ...
    //private EmployeeDAO employeeDAO;
    private EmployeeRepository employeeRepository;

    // set up constructor injection
    @Autowired
    // public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        //return employeeDAO.findAll();
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        //return employeeDAO.findById(theId);
        return employeeRepository.findById(theId);  // Error -> Required type: Employee Provided: Optional <com.luv2code.springboot.cruddemo.entity.Employee>
    }

    // @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        //return employeeDAO.save(theEmployee);
        return employeeRepository.save(theEmployee);
    }

    // @Transactional
    @Override
    public void deleteById(int theId) {
        //employeeDAO.deleteById(theId);
        employeeRepository.deleteById(theId);
    }
}
```

Then, I'll kinda just rip out the `EmployeeDAO` work,
and instead I'll replace it with `EmployeeRepository`.
And I'll just make use of the `EmployeeRepository` that I'm passing in here.
So I'll say `theEmployeeRepository`,
and I'll just fix up the work here inside the constructor.
And now, I'll just make use of this `employeeRepository` and all the other methods here.
So instead of `employeeDAO`, we paste in `employeeRepository`,
and I'll just kinda do a copy and paste all the way through here.
So now we're using that `employeeRepository` on all of our methods.
And then also another thing, we can remove the `@Transactional`
since `JPARepository` provides this functionality out of the box.
So no need to list it here.
So we just took care of defining the field and handling the constructor injection
for that `employeeRepository`.
And now, we're simply delegating the methods off because we get those methods for free
since we extend off of **JPA Repository** with **Spring Data**.
Now we still have an issue on `findById()`, 
_type mismatch can't convert optional employee to employee_, 
and no worries, we simply have to make some adjustments here,
and I'll kinda walk through the coding to resolve this issue.

```java
@Override
public Employee findById(int theId) {
    // return employeeRepository.findById(theId);  // Error -> Required type: Employee Provided: Optional <com.luv2code.springboot.cruddemo.entity.Employee>
    Optional<Employee> result = employeeRepository.findById(theId);
    
    Employee theEmployee = null;
    if (result.isPresent()) {
        theEmployee = result.get(); 
    } else {
        // we didn't find the employee
        throw new RuntimeException("Did not found employee id - " + theId);
    }
    return theEmployee;
}
```

So I'll select this piece of code here,
and I'll just kinda do a refactor and extract it as a local variable.
And then, for the variable name, I'll call it `result`.
That's the result of that call that we're making.
So now, `Optional` is a different pattern.
So instead of having the right code to check for `null`s, 
you can make use of `Optional` to see if a given value is present.
So I'll say `Employee theEmployee`, set it to `null`.
So here I'll say if `result.isPresent`, so that returns `true` 
if there's a value present, then I can actually retrieve that given value.
So here I'll say `return_result.get`.
And then, I'll make the assignment inside this `if` statement here.
So I'll say `theEmployee = result.get()`.
So that'll give us the actual value.
And then, this is a different pattern of coding that was introduced in Java 8.
And at the bottom I say `return theEmployee`.
So that's kinda the basic approach here for getting the data 
without having to explicitly check for `null`.
So the `JPARepository` makes use of `Optional`s.
And now, here I could say, `else`, a value wasn't present.
Well, that meant that I couldn't find the employee by that given id.
So, in that case, I'll simply just throw an exception.
And I'll simply say, "_Hey, did not find employee ID of blah_".
And so, that's the basic code in there for `findByid` using this `Optional` approach.
And so, this is kind of a new feature, new code in it, 
you may not have seen before, but this is introduced with the **JPARepository** of part of Java 8.
I have a link [here](https://www.luv2code.com/java-optional-tutorial) 
that'll give you more details on how to use `Java Optionals`.
It's official documentation or tutorials from the **Oracle** website.
So you can go to that link, and it'll redirect you to that official tutorial.
So we've done all the work here.
So we have our application, it's simply delegating the calls off to that repository, 
and we're making use of all those magic methods that are provided by **Spring Data JPA**, 
and that `JPARepository` interface.

I'm ready to run this application and test it out.
So I'll just go ahead and run our `CruddemoApplication` and get it up and running.
Alright, so we can go ahead and swing over to **Postman** and just test it out here.
So we'll run our first test of get all employees.

![image82](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image82.png?raw=true)

And that's successful,
so we're getting all of our data, so we know that 
this `JPARepository` is working just fine for get all employees.
And we can do a similar thing here to test to get a single employee by ID,
so I'll get employee number two.

![image83](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image83.png?raw=true)

Okay, great, that's for `Emma`.
Excellent, success, success.
And then do another test here of getting employee five, we get `Juan Vega`, that's good.
Now let's move ahead to add a new employee, so that's the `POST` method,
and we'll just clear out any of the previous information there,
and we'll just add some new information for a new employee to add.

```json
{
    "firstName" : "Natalia",
    "lastName" : "Kublanov",
    "email" : "natalia@luv2code.com"
}
```

And so we'll add this new employee, `Natalia Kublanov`, and then we'll simply do a `send`.

![image84](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image84.png?raw=true)

So success, we have a new employee here with the ID of six.
Swing over to our database, and just do a refresh here, and excellent.
So we have the new employee here, `Natalia`, ID of six.
So we know that our `JPARepository`, our `save` feature, 
our `save` functionality is working out just fine, so that's cool.
And then I'll swing over, and we'll test for update employee using a `PUT` method.
So I wanna update employee ID of number one,
and I'll just change the first name, and last name, and email for that given employee.

```json
{
    "id" : 1,
    "firstName" : "Evan",
    "lastName" : "Richards",
    "email" : "evan@luv2code.com"
}
```

So we'll update employee ID of number one, first name will be `Evan`, 
last name `Richards`, `evan@luv2code.com`.
Go ahead and send this data across:

![image85](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image85.png?raw=true)

And we'll see that response that was our code.
So we have `Evan Richards`, `evan@luv2code.com`, ID of one.
Just go back to our database and just do a quick refresh here.
Actually, just check the old data, `Leslie Andrews`, that's the old data.
And now we do a refresh, and we should see `Evan Richards`.

![image86](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image86.png?raw=true)

All right, good job.
And so now we're at our final step here of deleting an employee,
so we choose that `DELETE` tab there.
And for this example, we'll delete employee number four.
Just do a `send`.
Comes back with a response, _hey, we deleted employee ID of four_.
We simply swing back to our database.
This is our old data employee ID of number four, `Yuri`.
And if we do a refresh, then we should have success here.

![image87](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image87.png?raw=true)

Yes, that ID number four was deleted, so the `delete` functionality is working.
So all the major **CRUD** features are working with our **Spring Data JPA**.
So we're able to kinda leverage this magic here
of **Spring Data JPA** by using this interface, 
getting all these methods for free, and minimizing all the source code.
</div>

## [Spring Boot REST: Spring Data REST]()
<div style="text-align:justify">

In this section, we'll cover **Spring Data REST** in **Spring Boot**.
Earlier, we saw the magic of **Spring Data JPA**.
So we had our entity type of `Employee` and our primary key of `Integer`.
We plugged that in, and it'll give us those methods for free
for finding, saving, deleting, and so on.
And this really helped to eliminate the boilerplate codes.
The coding after **Spring Data JPA** made it really easy.

And I wonder like, "_Hmm, can this same thing apply to REST APIs?_"
So here's the problem.
We saw how to create a **REST API** for `Employee`.

```java
package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee,
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
```

We would set up our `EmployeeRestController`, inject a `@Service`:

```java
package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
```

Set up the `@GetMapping`, `@PostMapping`s, and so on.

```java
package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // inject EmployeeDAO ...
    private EmployeeRepository employeeRepository;

    // set up constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        // return employeeRepository.findById(theId);
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not found employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
```

We'd set up our `Service` interface, our `@Service` implementation,
and then we also had our application architecture.

![image88](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image88.png?raw=true)

We had our `RESTController`, our `Service`, our `Repository`.
And that worked fine and all.
But what if I needed to create a **REST API** for another entity 
like `Customer`, `Student`, `Product` or `Book`?
Would I have to go back and repeat all the same code again?
And I don't want to do that over and over again for each entity.

I wish we could tell **Spring**, "_Hey, create a **REST API** for me,
use my existing **JPARepository**, my entity and primary key,
and give all the basic **REST API CRUD** features for free._"

So **Spring Data REST** is the solution.
It's a separate **Spring** project.
You can use it with regular **Spring** or **Spring Boot**.
It leverages your existing **JPARepository**, and **Spring** will give you 
a **REST CRUD Implementation** for free. 
And the nice thing here is that it helps to minimize boilerplate **REST** code.
And in fact, there's no new coding required to get set up with this **Spring Data REST**.

| HTTP Method | Endpoint                  | CRUD Action                  |
|-------------|---------------------------|------------------------------|
| `POST`      | `/employees`              | Create a new employee        |
| `GET`       | `/employees`              | Read a list of employees     |
| `GET`       | `/employees/{employeeId}` | Read a single employees      |
| `PUT`       | `/employees/{employeeId}` | Update an existing employees |
| `DELETE`    | `/employees/{employeeId}` | Delete an existing employees |

So **Spring Data REST** will expose these endpoints for free.
So you'll have methods to `POST` to create a new employee.
`GET` to retrieve, `PUT` for update, and `DELETE` for, of course, deleting an existing employee.
And you get all of these **REST** endpoints for free
when you make use of **Spring Data REST**.

So you may wonder: how does it work in the background?
Well, **Spring Data REST** will actually scan your project for `JpaRepository` sub interfaces.
It'll expose the **REST API** for each entity type for your `JpaRepository`.
So in this example here, I have this `EmployeeRepository`:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
```

Extends `JpaRepository` entity, got this entity type of `Employee`,
it will actually expose an employee's endpoint.
So a bit more on the endpoint here.
It will create the endpoints based on an entity type.
It'll make use of the simple pluralized form, 
meaning it'll take the first character of the entity type,
that's lowercase, and then it'll simply add an `s` to the end of the entity.
So in this case, we have this `EmployeeRepository`.
The entity type is `Employee` in brackets, and then it'll create a `/employees` endpoint.
And so that's the basic approach for how they'll expose these **REST** endpoints.
Now I'll talk more about the pluralized form later in some later sections,
and also how you can kind of customize it.
But for now, we'll kind of take this out of the box.

Alright, so let's look at the actual development process:

1. Add a **Spring Data REST** to your **Maven** `pom` file, 

And that's it.
There's absolutely no coding required.
You simply make a **Maven** `pom` entry, and you get these endpoints for free.
So a bit more here on step one is adding it to your `pom` file.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

So you simply give a dependency here,
and you give a reference for `spring-boot-starter-data-rest`, and that's it.
No coding required.
**Spring Data REST** will scan for `JpaRepositories` and expose endpoints.
And so you'll get these endpoints for free,
based on the various entity types in your `JpaRepositories`.

So for **Spring Data REST**, you only need three items:

1. Your entity type of `Employee`
2. `JpaRepository` : `EmployeeRepository extends JpaRepository`
3. The **Maven** `pom` dependency for `spring-boot-starter-data-rest`.

So the first two items, we already have those.
The only new item here is number three, the **Maven** `pom` dependency, and then that's it.
Now some changes to our application architecture here.

![image89](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image89.png?raw=true)

So before we had: 

* Employee REST Controller
* Employee Service 
* Repository on the backend

And now, after we're using **Spring Data REST**,
they'll give us a `/employees` endpoint for free.
So there's no additional coding required to get this given **REST** endpoint.
So in our original architecture up top,
we can delete the code from our **Employee REST Controller** and also delete the **Employee Service**.
We no longer need that code, because **Spring Data REST** will provide 
that functionality for us for free.

Now with minimizing the boilerplate code.
So before **Spring Data REST**, we had our `RESTController`, we had our `EmployeeService`,
we had our `ServiceImplementation`, so about three files and 100 plus lines of code.
And then after **Spring Data REST**, you're going to love this.
No coding required. 
Just give a **Maven** `pom` entry.
**Spring Data REST** will scan for those repositories, 
and automatically expose those endpoints for free.
You can easily see that we no longer need their original code on the left-hand side,
because **Spring Data REST** will give us all of that functionality for free.

The **Spring Data REST** endpoints are `HATEOAS` compliant.
So what is `HATEOAS`?
It's the `Hypermedia As The Engine Of Application State`.
Basically, hypermedia-driven sites that provide information to access our **REST** interfaces.
And so really, you can just think of this as metadata for the **REST** data
that's coming back from these given **REST API**s.
You can read more about `HATEOAS` at this link [here](https://spring.io/projects/spring-hateoas),
but I'll actually go through some quick examples
to show you the basics of how this `HATEOAS` works.

**Spring Data REST** responses make use of `HATEOAS`.
So for example, a **REST** response from **Get** `/employee/3` to get a single employee.
Then it'll return this **JSON** data as a response.

```json
{
    "firstName" : "Avani",
    "lastName" : "Gupta",
    "email" : "avani@luv2code.com",
    "_links" : {
        "self" : {
          "href" : "http://localhost:8080/employees/3"
        },
        "employee" : {
          "href" : "http://localhost:8080/employee/3"
        }
    }
}
```

So up top we'll actually have our content or our real data, like our `Employee` data,
`firstName`, `lastName`, `email` for given `Employee`.
And then at the bottom, we actually have response metadata.
So this metadata here basically has links to the actual entry or the data.
So this is for `employeeId` of number three.
Just a way of describing the data or linking to the data.

Now for a collection, the metadata includes the page size,
the total number of elements, the pages, and so on.
So if I wanted to get a list of employees by doing **GET** `/employees`,
then the **JSON** response will be this piece of information here.

```json
{
    "_embedded" : {
        "employee" : [
           {
                "firstName" : "Leslie",
                ...
           },
           ...
        ]
    },
    "page" : {
        "size" : 20,
        "totalElements" : 5,
        "totalPaages" : 1,
        "number" : 0
    }
}
```

And up top, we have embedded a **JSON** array of employees for all the different employees there.
And then down at the bottom, we have metadata about the page or information about the page.
So the size of the page, this total number of elements, pages, number, and so on.
And I'll talk more about configuring the page sizes a little bit later.

So for details on `HATEOAS`, 
see the link [here](https://spring.io/projects/spring-hateoas).
`HATEOAS` also makes use of the `Hypertext Application Language` data format or `HAL`,
and that's the actual format of the **JSON** data that's being returned in the response.
So, if you'd like to get more information on `HAL`,
you can see the link [here](https://en.wikipedia.org/wiki/Hypertext_Application_Language) 
at the Wikipedia website.

**Spring Data REST** also has some advanced features.
So there's support for a pagination, sorting and searching.
You can also extend and add custom queries with **JPQL**.
You can also customize the **REST API** by making use of the query domain specific language 
or the query **DSL**.
For more information, 
you can see the link [here](https://spring.io/projects/spring-data-rest), **Spring Data REST**.

```sql
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
```

Let's get started with the first thing that is move into **MySQL Workbench**
and just refresh our database table, just so we have some standard data to start with.
So again, using that employee script, just go ahead and execute it
just so it can update everything for us.

After refreshing the database, We'll simply copy and paste the previous project.
I'll name the new folder as `04-spring-boot-rest-crud-employee-with-spring-data-rest`.
Now let's go ahead and move over to our IntelliJ preferences here,
and we'll set up those IntelliJ configurations.
And let's move down to the item here for `Build, Execution, Deployment`.
Then we'll choose `Compiler`, and then we'll select this check box here,
`Build project automatically`.
And now let's go and select the `Advanced Settings` item.
And then in the `Compiler` section, we'll check the box, `Allow auto-make to start`.
And remember, these are the little IntelliJ configurations 
that we need to do for the community edition,
to allow it to work with **Spring Boot DevTools**.
So I'll start by opening up the `pom.xml` file.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.luv2code.springboot</groupId>
	<artifactId>cruddemo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>cruddemo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
        <!-- Add dependency for Spring Data REST -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
        
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
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
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
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

And so basically it's, step one is, add in a **Spring Data REST** to the `pom.xml`.
So I'll just scroll down here in the file and write a quick little comment to myself.
So I need to add that dependency for **Spring Data REST**.
What I'll do is, simply copy one of the dependencies from up above,
and simply just paste it down here and make a quick update to it.
Alright, so that was the little copy, paste exercise,
and now I'll simply change this entry here, this artifactId.
I'll change `spring-boot-starter-data-jpa` to `spring-boot-starter-data-rest`.
Be sure to reload the **Maven**.
So that's our updated entry here for **Spring Data REST**.
And so remember, that's it, absolutely no coding required.
**Spring Data**'s going to scan for `JpaRepositories`
and give us those rest endpoints for free.
So the next thing I'll do here is delete the controller and service packages,
because remember with our new architecture, **Spring Data REST** is going to give us
those **REST** endpoints for free.
So I can actually delete the code for the `EmployeeRestController`,
and also delete the code for the `EmployeeService`.
It's no longer required in our project.
So I'll go ahead and delete the `rest` package.
I'll delete the `service` package.
Okay great, so at this point, I should simply have my entity, `Employee`,
the `dao` package of the `EmployeeRepository` 
and finally the main **Spring Boot** application.
And then just remember, for **Spring Data REST** we only need three items,
our `entity`, our `JpaRepository` and that **Maven** `pom` entry.
And that's all we need to get those endpoints for free.
Alright, so let's go ahead and run our application and let's test it out.

![image90](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image90.png?raw=true)

I'll move over to a web browser, and I'll access `localhost:8080/employees`.
It works for us.
So there's our data.
So we're actually getting our list of employees,
`Leslie Andrews`, `Emma Baumgarten`, and so on.
So this is all coming back thanks to **Spring Data REST**,
and also it's making use of this `HATEOAS` format
as far as the actual data and the structure of the data as it's being passed back.

![image91](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image91.png?raw=true)

And also we have the meta-data here at the bottom of this given page.
The `size`, `totalElements`, `totalPages`, `number`, and so on.
Alright, and so we can confirm 
that this data is really coming back using **Spring Data REST**.
And I can even make use of some links here, `http://localhost:8080/employees/1`
to drill down for like employee one.
I can select this link, and it'll take me to the details here for employee one.

![image92](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image92.png?raw=true)

Okay, there we go.
So **Spring Data REST** is up and running.
So I want to play around with some other features here of **Spring Data REST**.
So what I'll do is I'll take this [URL](http://localhost:8080/employees), 
I'll copy it, and I'll move over to **Postman** and paste it in there.
And check the results here in **Postman**: 

![image93](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image93.png?raw=true)

And the same thing.
So we're getting the data back as desired in **Postman** also.

Okay, so one thing I'd like to do is actually customize our endpoint base path
just to kind of give us a little bit more control over that given URL.
So I'll open up my `application.properties` file 
and add some **Spring Data REST** properties.

```properties
#
# JDBC properties
#
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent

#
# Spring Data REST properties
#
spring.data.rest.base-path=/magic-api
```

So I'll give `spring.data.rest.base-path`, and I'll call it `magic-api`.
So that's the base path for the URL that we'll have for these **Spring Data REST** endpoints.
And then I'll simply save this file, 
and we'll see at the bottom that it's reloading.
And now if I swing back over to **Postman**, then I can access this information.
So first off, if I simply use the existing `/employees`,
it's going to give me a 404 of not found.
That's because we've actually changed the actual base path.
So here I have to say `magic-api/employees`,
and that's based on that information from that configuration file.
So now when I hit `send`, 

![image94](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image94.png?raw=true)

I'll actually get the data back as desired.
So it's using our `magic-api`.
You can use any base path that you want here.
So now let's go ahead and play around with posting or adding a new employee.
So I'll select the tab here for post.
Make sure that you update your URL for `magic-api`.
And then for the body, you may already have some existing data here.

```json
{
    "firstName" : "Natalia",
    "lastName" : "Kublanov",
    "email" : "natalia@luv2code.com"
}
```

You can keep it the same, or you can change it.
I'll go ahead and keep mine as is for `Natalia`, and I'll simply `send` that across.
And then just scrolling down to the bottom, we'll see that we have success.
It added that new employee, `Natalia`, and she has the ID of six.
So the employee ID will show up in the `HATEOAS` meta-data section.
Just swinging over to my database real quick, and just doing a quick refresh here.

![image95](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image95.png?raw=true)

And so we see that `Natalia` is the new entry here of ID six.
So this looks pretty good.
Now we'll also go through and using a put to actually update an employee.
And make sure you update your URL here for `magic-api`.
So let's perform an update on employee ID equals four.
And one thing that's different here is that 
**Spring Data REST** only uses the id on the URL.
So don't pass the id in the **JSON** body like we did before.
Only place the id on the URL.
If you place the id in the body, it'll simply ignore it.
It'll only use the id on the URL.
Alright, so I'll select body over here, and I can give some new updated information.

```json
{
    "firstName" : "Pappa",
    "lastName" : "Ray",
    "email" : "pappa@luv2code.com"
}
```

First name of `Pappa`, last name of `Ray`, just again, making this up.
And then, once we send this across, then we should update employee ID of four
with this new data that we provided here.
It'll show us that update for ID four.
And if we just swing over to our database, we can see our old data of ID four was `Yuri`.
And I simply do a refresh here:

![image96](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image96.png?raw=true)

And I'll see the first name of `Pappa`, last name `Ray`.
That's the data that we just sent across,
so it successfully updated given employee ID of four.
Alright, so swinging back into **Postman**, I'll go through and delete an employee.
So I'm in queues of `delete`.
And again, just make sure you update your URL here, `magic-api/employees/4`.
So we're going to delete employee number four.
Just do a `send`.
Alright, so we have a success here.
With **Spring Data REST**, there's no data in the response body.
They simply give you a status code of 204.
So that means that it was successful, no content.
So this is fine, we're good to go here.
So swinging back to our database, we see that ID equals four, `Pappa Ray`, 
that's our old data.
Simply go through and do a refresh:

![image97](https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image97.png?raw=true)

And we'll see that given employee was deleted successfully.
So `id = 4` was deleted.
Alright, so that kind of covers all the **CRUD** stuff.
So we know that **Spring Data REST** is working out just fine
for our **CRUD** operations with our given database.
And again, we simply added this functionality
by putting in the **Maven** dependency here for `spring-boot-starter-data-rest`.
And again, no coding required.
And in fact, we actually went through and deleted some code.
So this minimized the boilerplate code.
So we removed all the **REST** controllers and all the service samples, 
got rid of all that stuff, and we simply made use of the **Maven** entry,
and that will give us those **REST** endpoints for free.
</div>

## [Spring Boot REST: Spring Data REST Configs and Sorting]()
<div style="text-align:justify">


</div>