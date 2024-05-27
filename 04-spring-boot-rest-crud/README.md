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

So a possible solutions that we can use `openweathermap.org`.
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
So you can go to `openweathermap.org`.
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
"_Hey we're simply passing back the data._"
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


</div>

## [Spring Boot REST Controller]()
<div style="text-align:justify">


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

## [API Design - Create Poject]()
<div style="text-align:justify">


</div>

## [Data Access Object (DAO)]()
<div style="text-align:justify">


</div>

