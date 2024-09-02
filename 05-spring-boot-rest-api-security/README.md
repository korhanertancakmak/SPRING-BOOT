# [REST API SECURITY]()

## [Overview]()
<div style="text-align:justify">

In this section, we'll get an overview of **Spring Boot REST API Security**.
In this section, we'll learn how to 

* secure a **Spring Boot REST API** 
* define users and roles 
* protect URLs based on a given role
* store users, passwords, and roles in a database using plain text 
and also an encrypted format.

We're going to cover the most common **Spring Security** tasks that you'll need on a daily basis.
Now, this is not an A to Z reference.
For that, you can see the **Spring Security Reference Manual**.
I have a link [here](http://www.luv2code.com/spring-security-reference-manual).
That'll simply redirect you to the official **Spring Reference Manual**.

Let's look at the **Spring Security Model**.
**Spring Security** defines a framework for security.
It's implemented using servlet filters in the background.
You can make use of declarative security or programmatic security.
**Spring Security** using servlet filters.
Basically, servlet filters are used to pre-process and post-process the web requests.
These filters can route the web request based on security logic,
and **Spring** provides a bulk of the security functionality with servlet filters.
Let's take a look at Spring Security with some diagrams.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/04-spring-boot-rest-crud/images/image01.png?raw=true" alt="image01">
</div>

You have a web browser on the left, and then on the right,
you have this protected web resource like my top-secret stuff.
The browser's attempting to access your top-secret information and get the results.
Now, this is where Spring Security comes into play.
These Spring Security filters will intercept those requests,
pre-process them, and then see if the user can actually access that protected resource.
Spring Security will look at your application security configuration.
And then, it'll also look at the user's passwords and roles
that are in your database to see if this user's authenticated
and also if they're authorized to access this web resource.
And this all happens in the background, thanks to Spring Security.
Okay, Spring Security in action with a flow chart.
We have these Spring Security filters, and then, basically,
we'll say, "Hey, is this web resource protected?"
Yes. Then, "Is this user authenticated?"
If they're not authenticated, then we'll send them to the login form,
show that login form, and then take the user ID and password and authenticate it.
We'll check to see if this user ID and password is valid
based on the information we have stored in our system.
Then, if they pass a test, so they're authenticated,
then we check, "Is this user authorized?
Do they have authorization to access this resource?"
Now, just like at your job, right,
you have a security badge, or at your university,
you have a student ID that can get you into the main building.
However, you may not be able to access all the rooms in the building.
It all depends on your access level or your authorization role.
Just because you have a good user ID and password,
that doesn't mean you have access to everything.
There's still additional levels of security in place,
and Spring has support for this using security roles.
If you don't have the given role, then it says access denied,
or if you do have the given role, then it'll actually show you the resource
and then give you access to this secure site.
Basically, the security here is in place to keep the bad guys out
and also allow the good folks inside.
This is pretty cool. I like it.
Okay, so let's review some security concepts here.
We have this idea of authentication that basically handles checking the user ID and password
with the credentials that are stored in your app or in your database.
And then we have the idea of authorization.
Here we check to see if the user has an authorized role.
As you can see, there are two levels of security that are in place for the Spring Security framework.
Spring Security has declarative security.
Here we define our application security constraints in a configuration.
This is handled by an all-Java config using a configuration class.
And the nice thing about this is that it provides separation of concerns
between application code and security code.
And then there's also programmatic security.
Basically, Spring provides an API for custom application coding,
and this is where you can have greater customization
for your specific application requirements.
For real-world, real-time enterprise projects, you may say,
"Hey, Spring Security gives me the basics.
However, at my company, we have additional business rules
or additional logic that we need to implement."
Well, not a problem.
You can easily extend the framework for doing that.
And you can plug in your own custom security implementation.
To enable Spring Security, the first thing we do is we edit the pom.xml file.
And in this file, we'll add the dependency for the spring-boot-starter-security.
And by adding this dependency, spring-boot will automatically
secure all endpoints for the application.
No additional coding is required at this point.
However, we'll learn about customizing the configuration a little bit later.
And once we run our application, and we access our app, then our application is secured.
As a result, Spring Security will prompt us to log in.
Now, by default, the username is user,
and the generated password is in the application logs.
Now, this is just for testing.
Later on, we'll learn other techniques to customize the user ID and the password,
including storing the information in the database.
But at the moment, we're just looking at the defaults for right now.
You can also manage basic configuration with Spring Security.
In our application.properties file, you can override the default username and password.
We simply add the configuration for Spring Security username
and also Spring Security user password.
As I mentioned earlier, there are different techniques
for defining users, passwords, and roles.
You can make use of in-memory authentication.
You can also use JDBC to define users, passwords, and roles in a database.
There's support for LDAP.
You can also use your own custom plugin,
your own authentication, and authorization coding,
and there are plenty of others out there.
You can look at the reference manual for a complete list,
but in this class, we'll actually cover the in-memory authentication.
And then, we'll also cover password storage in the database using JDBC.
I'll show you how to store your passwords using plain text,
and we'll also look at how to encrypt the passwords in the database,
and we'll cover that all in this section.
Okay, so let's go ahead and let's test out some basic Spring Security.






```html
api.openweathermap.org/data/2.5/weather?q={city name}

OR

api.openweathermap.org/data/2.5/weather?q={city name}, {country code}
```

</div>

## [Basic Configuration]()
<div style="text-align:justify">

</div>

## [Restrict URLs based on Roles]()
<div style="text-align:justify">

</div>


## [JDBC Authentication]()
<div style="text-align:justify">

</div>


## [Bcrypt Encryption]()
<div style="text-align:justify">

</div>


## [Custom Tables]()
<div style="text-align:justify">

</div>