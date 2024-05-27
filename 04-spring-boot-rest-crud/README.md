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

## [Java POJO with JSON Jackson Data Binding]()
<div style="text-align:justify">


</div>

## [Path Variables]()
<div style="text-align:justify">


</div>

## [Exception Handling]()
<div style="text-align:justify">


</div>

## [Global Exception Handling]()
<div style="text-align:justify">


</div>

## [API Design - Create Project]()
<div style="text-align:justify">


</div>

## [Data Access Object (DAO)]()
<div style="text-align:justify">


</div>

