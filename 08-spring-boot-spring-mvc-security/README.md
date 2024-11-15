# [Spring MVC Security]()

## [Overview and Demo]()
<div style="text-align:justify">

In this section, we're going to get an overview of **Spring MVC Security**.
So in this section we'll actually learn how to secure a **Spring MVC** web application.
We'll also develop login pages, we'll use the default and custom pages.
We'll define users and roles with simple authentication,
and we'll also protect URLs based on a given role.
We'll also learn how to hide and show content based on the role.
And finally, we'll learn how to store user passwords and roles in a database, plain text and encrypted.

Now we're looking for practical results here.
We're going to cover the most common **Spring Security** tasks that you'll need on a daily basis.
Now this is not an A to Z reference.
For that, you can see the **Spring Security Reference Manual**.
I have a link [here](https://www.luv2code.com/spring-security-reference-manual) to the website
that'll simply redirect you to the **Official Spring Reference Manual**.

Let's look at the **Spring Security Model**.
**Spring Security** defines a framework for security.
It's implemented using **Servlet** filters in the background.
You can make use of declarative security or programmatic security.
**Spring Security** using **Servlet** filters.
Basically, **Servlet** filters are used to pre-process and post process the web request.
These filters can route the web request based on security logic 
and **Spring** provides a bulk of the security functionality with **Servlet** filters.

Let's take a look at **Spring Security** with some diagrams.
You have a web browser on the left and then on the right you have this protected web resource like my top secret stuff.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image01.png" alt="image01">
</div>

The browser's attempting to access your top secret information and get the results.
Now, this is where **Spring Security** comes into play.
These spring security filters will intercept those requests,
pre-process them, and then see if the user can actually access that protected resource.
**Spring Security** will look at your application security configuration, 
and then it'll also look at the user's passwords and roles that are
in your database to see if this user's authenticated and also if they're authorized to access this web resource.
And this all happens in the background, thanks to **Spring Security**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image02.png" alt="image02">
</div>

Okay, **Spring Security** in Action with the flow chart.
We have these **Spring Security** filters and then basically we'll say, "_Hey, is this web resource protected?_". 
Yes, then "_is this user authenticated?_" If they're not authenticated,
then we'll send them to the login form, show that login form 
and then take the `userId` and `password` and authenticate it.
We'll check to see if this `userId` and `password` is valid based on the information we have stored in our system.
Then if they pass a test, so they're authenticated, then we check, "_is this user authorized?_"
Do they have authorization to access this resource?
Now just like at your job, you have a security badge or at your university, 
you have a studentId that can get you into the main building.
However, you may not be able to access all the rooms in the building.
It all depends on your access level or your authorization role.
Just because you have a good `userId` and `password`, that doesn't mean you have access to everything.
There's still additional levels of security in place 
and **Spring** has support for this using security roles.
If you don't have the given role, then the access is denied.
Or if you do have the given role then it'll actually show you the resource and give you access to this secure site.
Basically, the security here is in place to keep the bad guys out and also allow the good folks inside.

Okay, so let's review some security concepts here.
We have this idea of authentication that basically handles checking the `userId`
and `password` with the credentials that are stored in your app or in your database.
And then we have the idea of authorization.
Here we check to see if the user has an authorized role.
As you can see, there are two levels of security that are in place for the **Spring Security Framework**.

**Spring Security** has declared a security.
Here we define our application security constraints in a configuration.
This is handled by an all **Java** config using `@Configuration` class.
And the nice thing about this is that it provides separation of concerns between application code and security code.

And then there's also programmatic security.
Basically, **Spring** provides an API for custom application coding and this is
where you can have greater customization for your specific application requirements.
For real world realtime enterprise projects, you may say,
"_Hey, **Spring Security** gives me the basics. 
However, at my company, we have additional business rules or additional logic that we need to implement._"
Well, not a problem.
You can easily extend the framework for doing that, 
and you can plug in your own custom security implementation.

To enable **Spring Security**, the first thing we do is we edit the `pom.xml` file:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image03.png" alt="image03">
</div>

and in this file we'll add the dependency for the `spring-boot-starter-security`
and by adding this dependency, spring boot will automatically secure all end points for the application.
No additional coding is required at this point.
However, we'll learn about customizing the configuration a little bit later.

And once we run our application, and we access our app, then our application is secured.
As a result, **Spring Security** will prompt us to log in.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image04.png" alt="image04">
</div>

Now by default, the username is `user` and the generated `password` is in the application logs.
Now this is just for testing.
Later on, we'll learn other techniques to customize the `userId` and the `password`
including storing the information in the database.
But at the moment, we're just looking at the defaults for right now.

Now, when you log into the application there's different options on how you can log into your application.
You can make use of **HTTP basic authentication**.
There's also support for a default login form.
So **Spring** will actually give you a **default login form** 
if you don't have one, which is pretty neat, and we'll see some examples of that in this class.
And then you can also create your own **custom login form** where you can provide your own look
and feel using whatever **HTML** and whatever **CSS**, **JavaScript** that you want.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image05.png" alt="image05">
</div>

So here's an example of the basic authentication.
And you may have seen this before.
The way a browser will simply pop up a dialogue box, a modal dialogue box,
and you have to go through and fill out `username` and `password`.
We won't use this in the course, but I'm simply just telling you
that **Spring** supports it if you'd like to use it.

One really cool thing about **Spring** is that 
they'll actually give you a **default login form** that's based on **HTML**.
So if you don't provide a login form, you can say, "_Hey, **Spring**, go ahead, and give me your default form._"
And it's really good for a quick start.
I like to start out with it just to kind of make sure 
I have the security piece in place, and then I'll go through, 
and I'll grab one of my **HTML** buddies, and they'll make it look pretty for me, 
but at least I kind of have something to work with, something to start with.

Allright, so now you can also make use of your own **custom login form**.
So basically you can create your own **HTML** page using whatever **cascading style sheets** you want, **JavaScript**, **bootstrap**, whatever.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image06.png" alt="image06">
</div>

And you simply tell **Spring**, "_Hey this is the login form that you should use_",
and then **Spring** will actually display it.
So you have full control over the look and feel.
And we'll actually see examples of this in the class also.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image07.png" alt="image07">
</div>

So basically for our example, we're going to have a login page, a **custom login page**,
and then we'll actually have our homepage that's only available to log in users role of employee.
From there, we'll have a special section of pages for the leadership retreat only for the role of `manager`,
and then we'll also have another page, admin holiday cruise only available for the role of `admin`.
So we have special pages that are only available to certain users or certain roles, and we'll configure that using **Spring Security**.

And so here's a quick little demo in the browser of our application.
Here's our custom login page.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image08.png" alt="image08">
</div>

This is **HTML** and **CSS** that I created making use of **Bootstrap**.
And then let's go ahead and just try and add a bad `userId` and a bad `password` as an attempt to log in.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image09.png" alt="image09">
</div>

Okay, so as expected, it says `Invalid username and password.`
So I know I have a user by the name of `John` and his password is `test123`, and I'll go ahead and log in with that one.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image10.png" alt="image10">
</div>

So we're logged into our homepage.
We display the user's name and also the user's role.
So we can actually access that information in our **JSP** page,
and now we're actually logged into the system, and we can go around and do other things here.
But notice here there's not many links available, because this person only has `employee` role,
they're not a `manager`, or they're not an `admin`.
Let's go ahead and log in one of those other roles, so you can see something different.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image11.png" alt="image11">
</div>

So we have another user named `Mary`, and `Mary` is a `manager`.
So we'll enter `Mary`, `test123`, and hit log in.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image12.png" alt="image12">
</div>

Okay, great, so we're logged in.
This is `Mary`, her role is `manager`.
But now notice here `Mary` has a new link.
It says `Info for Leaders Only`.
Hmm, so that's a special leadership retreat in Brazil that's only available to managers and only managers will see this link.
And then we can go ahead and click on this link:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image13.png" alt="image13">
</div>

And here it says, "_Hey, see you in Brazil, our annual leadership retreat. Keep this hush, hush, top secret._"
Okay, so we have special data, special pages that's only available to users with a given role here.
So that's kinda the basic layout here for our application,
and we'll go through and configure it all up in the following sections.

</div>

## [Project Setup]()
<div style="text-align:justify">

In this section, we're going to do our **Spring MVC Security** project setup, and here's our development process:

* Create the project at the **Spring Initializr** website
    - Add the Maven dependencies for **Spring MVC Web App**, **Security**, and **Thymeleaf**
* Develop our Spring controller 
* Develop our Thymeleaf page

In step one, we're going to create the project at the **Spring Initializr** website.
So on our web browser, we'll go to `start.spring.io`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image14.png" alt="image14">
</div>

And for the project settings here, we'll choose **Maven** project.
Choose the language of **Java**.
We'll choose the most recent version of **Spring Boot**, and your version may be slightly different.
For the project metadata in **Group**, I'll call it `com.luv2code.springboot`.
For the **ArtifactId**, I'll give `demosecurity`.
For packaging, I'll have **Jar**, and you can choose whatever version of **Java** that you have.
Now, over in the top right, I want to click on `Add Dependencies`,
and the first dependency I want to add is **web**.
And then I want to add a dependency for **Thymeleaf**.
And I also want to add a dependency here for **Spring Security**.
And finally, I'll add a dependency for **devTools**.
Allright, so just as a checkpoint here, over on the right hand side,
make sure you have these four dependencies selected.
And then we can go ahead and generate this project.

So moving into `Downloads` directory, `demosecurity.zip`, let's go ahead and unzip it.
I'll move in `dev-spring-boot`, and I'll create a new folder.
I'll call it `08-spring-boot-spring-mvc-security`.
Let's go ahead and move into this new folder.
I'll simply move that `demosecurity` from `Downloads` over to this new directory that I have here.
And now I'll simply rename `demosecurity`,
and I'll call it `01-spring-boot-spring-mvc-security-default` just as our first starter project.

Let's go ahead and open this in our IDE.
Okay, so we have this new **Spring MVC Security** project open in our IDE.
Let's go ahead and look at the `pom.xml` file.
And we'll notice a couple of entries here.

````xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
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
  <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
````

One is that we have an entry for **Spring Security** support.
There's also the **Thymeleaf View**  support.
We have the **Spring MVC Web** support.
And then finally, we have the **Thymeleaf Security** support as far as the extras as far as integrating **Thymeleaf**
and **Spring Security** together.

Step two, we're going to develop our **Spring** controller.
I'll move here into my package `demosecurity`, and I'll actually create a new package.
And the name of this package is `controller`.
And now, I'll create a new class in this package.
The name of this class, I'll call it `DemoController`.
And for this controller, I'll annotate it accordingly at the top, I'll just give it the `@Controller` annotation.

````java
package com.luv2code.springboot.demosecurity.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }
}
````

And I'll add a `@GetMapping` here, for `"/"`.
I'll define this method here, `showHome`, that returns a **String**.
We simply return `"home"`.
And that's the actual view name.

Now, in step three, we'll actually define the **Thymeleaf** view page.
So this is under `resources/templates`.
And I'll create this new file, actually a new **HTML** file.
And this new file we called `home.html`.

````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Company Home Page</title>
</head>
<body>
    <h2>Company Home Page</h2>
    <hr>
    Welcome to the company home page!
</body>
</html>
````

So it gives us a very basic **HTML** page, and we'll just fill it out with our information.
For the title, I'll call it _"Company Home Page"_.
And in the body here, I'll just make use of an `<h2>`, heading two,
and I'll say "_Company Home Page_".
Very simple, very straightforward.
And I'll also just add an `<hr>` at the bottom.
And I'll just give a very basic welcome message, "_Welcome to the company home page_".
And now, we can go ahead and test this application.
Just to make sure it can show the homepage.
And we should have security kick in for us too.
Since we have that in our `pom` file.
Go ahead and start it up.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image15.png" alt="image15">
</div>

And so we see the information here `using generated security password`, okay.
And we'll kind of work with this as is for now.
Later, we'll kind of customize this and add our own users and our own passwords.
But for right now, we'll just kind of use the defaults.
So my browser here, I'll move over and I go to `localhost:8080`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image16.png" alt="image16">
</div>

And **Spring Security** kicks in. 
So our application is secured, success.
I'll make use of the default username, which is `user`.
And then I'll make use of the password, and I can grab that from the console.
So I can swing back over to my IDE, using the generated password.
Just go ahead and copy that.
And then I can swing back over to my browser and I can paste that.
And I'll hit `Sign in`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image17.png" alt="image17">
</div>

And success. 
So we're actually into our page.
We know that **Spring security** is working.

</div>

## [Basic Configuration(In-Memory)]()
<div style="text-align:justify">

In this section, we'll configure basic security.
We'll take a look at an example here for our sample users here.

<table align="center">
  <thead>
    <tr>
      <th>User ID</th>
      <th>Password</th>
      <th>Roles</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>john</td>
      <td>test123</td>
      <td>EMPLOYEE</td>
    </tr>
    <tr>
      <td>mary</td>
      <td>test123</td>
      <td>EMPLOYEE, MANAGER</td>
    </tr>
    <tr>
      <td>susan</td>
      <td>test123</td>
      <td>EMPLOYEE, MANAGER, ADMIN</td>
    </tr>
  </tbody>
</table>

We'll have three users, `John`, `Mary`, and `Susan`.
We'll have their passwords and also their associated roles, `EMPLOYEE`, `MANAGER`, `ADMIN`.
Now, these role names, you can give any names for the roles.
You can make up whatever you'd like.
I'm using these examples here, `EMPLOYEE`, `MANAGER`, `ADMIN`.
Now, let's take a look at our development process.

* Create a **Spring Security Configuration** class
* Add the user's passwords and roles

And Spring Security passwords are stored using a specific format.

````text
{id}encodedPassword
````

In the curly braces, you give the actual `id`, and then you have the encoded password.
The idea here is that we have the encoding algorithm that's being used for this password.

<table align="center">
  <thead>
    <tr>
      <th>ID</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>noop</td>
      <td>Plain text passwords</td>
    </tr>
    <tr>
      <td>bcrypt</td>
      <td>BCrypt password hashing</td>
    </tr>
    <tr>
      <td>...</td>
      <td>...</td>
    </tr>
  </tbody>
</table>

In this example, we have `noop`, means, **no operation**.
That's just for plain text passwords, meaning, no encryption, no hashing, no nothing, just plain text.
And then you have bcrypt and that's where `bcrypt` password hashing.
Basically, that's one-way hashing or one-way encryption.
You take the password, and you hash it using given `bcrypt` algorithm, and it's stored in that fashion.
`bcrypt` is a very popular hashing algorithm that's used now, and that's the one that we'll use later.

````text
{noop}test123
````

Let's take a look at a password example.
Here, the password is "`test123`", and then we have the actual encoding algorithm id, and that's in curly braces.
In this example, it's `noop`.
This tells **Spring Security**, the passwords are stored as plain text.
`noop`, meaning, **no operation**.
And we'll start with this just for the beginning, just to help us get started.
Later on, we'll move to more advanced features using `bcrypt`.

Allright, starting with step one of creating our **Spring Security Configuration** class.
I'll move into my code here and the first thing I want to do is actually create a package for my security classes.
And for the name of the package, I'll call it `security`.
And now, in this new security package, I'll go ahead and create a new class.
And for the new class, I'll call it `DemoSecurityConfig`.

````java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}
````

And the first thing I'll do here is I'll annotate this class with the `@Configuration` annotation.
And now, I'll go through step two of adding the user's passwords and roles, 
and I'll make use of that **InMemory authentication**.
And we know that we need to create these three users for `john`, `mary`, and `susan`.
I'll start by creating the user details for `john`.
Make use of this `User.builder()`.
I give the `username` of `john`.
I get the `.password("{noop}test123")`.
Remember the `noop` means **plain text**.
And then I specify the roles.
`john` has one role, `"EMPLOYEE"`.
And then I make use a `.build()`.
I'll actually create that `UserDetails` instance.
This is the basic coding here for the `UserDetails` for `john`.
Now, let's go ahead and just copy-paste this and let's do a similar thing here from `mary`.
Simply update her username, and then we also update the roles for `mary`.
We'll give `"EMPLOYEE"` and `"MANAGER"`, and then just copy-paste one more time for `susan`.
Simply update the username here and update the roles here for `susan`.
And then finally at the end, we return an instance of this `InMemoryUserDetailManager`.
And I'm simply passing those three user details for `john`, `mary`, and `susan`.
Now note, since we defined our users here, **Spring Boot** will not use the user-password from the application of properties.
Instead, it'll use this `userDetailsManager` that we just created in this configuration.
And also, remember, we're going to add database support later with plaintext and encrypted.

Now, let's go ahead and test this out.
Go ahead and run our application.
Allright, so let's go ahead and test this out by trying to log in and seeing how the system works out for us.
So I'll just enter a bad user bad password and hit `login`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image18.png" alt="image18">
</div>

This is the actual default login page from the **Spring Security** framework.
Now, go ahead and give a good user here.
I'll say `john`, and I give the password of `test123`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image19.png" alt="image19">
</div>

and, we are logged in.
So this **Spring Security** is working.

</div>

### [Custom Login Form]()
<div style="text-align:justify">

In this section, we'll learn how to set up our custom login form with **Spring Security**.
So, we've had some login work, and we made use of the **default login form** that's given to us for free by **Spring**.
That's good for a quick start, but not really ideal.
We want to create our own **custom login form**
where we can kind of control the look and feel use our own **HTML**, **CSS**, 
maybe add our company logo and whatever we want more control over how the form looks.
And we'll do that in this section.

So just as a refresher here with that **Spring Security**, 
so you have your web browser, you're trying to access some protected web resource.
And remember we have little **Spring Security** filters in place 
that'll actually kind of pre-process and post-process.
They basically make use of their app configuration and reuse IDs and passwords and rolls,
and kind of manage the whole security process with **Spring**.
And what we'll do here with this **Spring Security** filters that we'll kind of set up a configuration here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image20.png" alt="image20">
</div>

So when they log in, they need to send the user to the login form,
we'll actually configure **Spring** to make use of our own **custom login form**.
So we'll say, "_Hey, don't use the built-in login form or the **default login form**, use our **custom login form**._"
Allright, so here's the basic development process:

* Modify the **Spring Security** configuration to reference our login form 
* Develop a controller to show the custom login form
* Create that actual custom login form, we can use html, we can add in some CSS.

Allright, so step one of modifying the **Spring Security** configuration.
So I'll open my configuration class, `DemoSecurityConfig.java`:

````java
@Configuration
public class DemoSecurityConfig {

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
     // UserDetails john, mary, susan
    return new InMemoryUserDetailsManager(john, mary, susan);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer.anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll());

    return http.build();
  }
}
````

In our class here, we create this `@Bean` our security filter bean.
It's going to pass in a `HttpSecurity` that we can make use of.
Inside of this method, I have `http.authorizeHttpRequests()`.
Then we'll say `.anyRequest().authenticated()` means that for any request coming to the app, it must be authenticated.
The user must be logged in.
And for our `.formLogin()`, we'll customize the login process.
Where for the actual `.loginPage()`, we'll show our custom form for this given request mapping of `"/showMyLoginPage"`.
And then the login form will submit the data or post the data to this URL, `"/authenticateTheUser"` for processing.
This is where **Spring Security** will go through and check the user ID and the password.
Now these two entries here you can actually give any values for this configuration.
Simply have to stay consistent in your application.
And down here at the bottom, we want to allow everyone to see the login page by "`.permitAll()`".
There's no need to be logged in.
And that's basically it.
So that's kinda how you configure the **Spring Security** to reference your **custom login form**.
So we have the coding here and we set up the configs accordingly.
Now, as far as the things that we need to do we actually need to create a controller for this request mapping.
So we need to create some code, and then we need to handle for the get mapping for `"/showMyLoginPage"`.
And then also, one other thing here is that 
there's no controller request mapping required for the processing URL, `"/authenticateTheUser"`.
This is some of the **Spring** magic that'll handle everything for you behind the scenes 
as far as checking the user ID and password.
So we have our method created, and we have our configuration all set up,
so **Spring** knows how to reference our **custom login form**.

Allright with step two of developing the controller to show the **custom login form**.
So we'll create a new controller and name for this controller called `LoginController`.

````java
package com.luv2code.springboot.demosecurity.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "plain-login";
    }
}
````

And then I simply set up a `@GetMapping` for `"/showMyLoginPage"`.
So that `form.loginPage("/showMyLoginPage")` here,
we're actually writing the code for this given request mapping.
So this is required.
So remember this `@GetMapping("/showMyLoginPage")` has to match up with the `form.loginPage("/showMyLoginPage")` in our
**Spring Security** configuration file.
And then here we say `return "plain-login"`.
And we know based on our configurations that's the view name.

And then finally create that actual **custom login form** in "`src/main/resources/templates/plain-login.html`".
When we send data to the login processing URL `"/authenticateTheUser"` we must post the data across.
That's part of the requirements here.
So in the header section, I'll just go through, I'll just add a title here for our login forms.
I'll call it `Custom Login Page`.
And then I'll set up an `<h3>` here to give us a header for `My Custom Login Page`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Custom Login Page</title>
</head>
<body>
    <h3>My Custom Login Page</h3>
    <form action="#" th:action="@{/authenticateTheUser}" method="POST">
        <p>
            User name: <input type="text" name="username" />
        </p>
        <p>
            Password: <input type="password" name="password" />
        </p>
        
        <input type="submit" value="Login" />
    </form>
</body>
</html>
````

So I'll create the form, I'll set up `th:action`, and so I'll say `th:action="@{/authenticateTheUser}"`,
and because we submit data, we have `method` equals `"POST"`.
Now, the reason we're sending it to `"/authenticateTheUser"`, because that's based on the configuration that we set up.
Now, **Spring Security** actually defined some default names for the login form fields.
I just go ahead and drop in the fields here for `username` and also `password`.
Allright, so when you create the form for the username field, you'll call it `username`.
For the password field, you'll call it `password`.
These are the default values.
So when you create your form, you simply set up an input text type, `name="username"`.
For password input type, `name="password"`.
So **Spring Security** is going to read the form data and use that to authenticate the user.
So **Spring** will do all the authentication and checking in the background
as long as you provide the form field names that **Spring** is expecting.
And then I create my `submit` button.

Okay so we finished the development of our form.
Now we're at a point where we can actually run it and test it out.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image21.png" alt="image21">
</div>

Great, so here's our form, and we know that this is `My Custom Login Form`.
Because of the text that we entered.
I'll go ahead and attempt to log in here.
So I'll give the user ID of `john` and the password of `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image22.png" alt="image22">
</div>

So our custom login page really is configured with the **Spring Security** system.
But, there's a problem here, and we can actually break this, 
so I'm going to enter a bad username and a bad password.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image23.png" alt="image23">
</div>

Okay, it didn't log us in, but notice there's no error message.
Now, earlier in the class when we used that **default login page** provided by **Spring**,
**Spring** gave us a lot of functionality in that page.
So they would even tell you if the user failed to log in.
But now, since we're doing everything on our own, we're creating our own **custom login page**.
Well, now we need to add logic to handle for that.
So we need to actually modify our form to display an error message if the login fails.
We'll show it in the next section.

</div>

### [Login Form Error Message]()
<div style="text-align:justify">

In this section, you'll learn how to show a login error message.
Now with our **custom login form**, you notice that if the login failed, 
well we didn't see an error message, it simply sent us back to the login form.
And you may wonder, "_What's up with that?_"
Well, earlier in the course we made use of **Spring Security**'s **default login form**.
So the **default login form**, has the username and password.
They also have built-in support for displaying error messages.
Well, now since we're creating our own **custom login form**,
well we need to add additional code to handle for failed logins.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image24.png" alt="image24">
</div>

So we need an error message ASAP.
So we're going to add support for a message.
"_Hey, you entered the invalid username and password._"
And we'll cover all of that here.
Now, when login fails, by default, **Spring Security** will actually send the user back to your login page,
and also append an error parameter, called, "**error**".

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image23.png" alt="image25">
</div>

Now remember, this will be on the URL, so this is how it maps up here with the URL that's in the browser,
and then the actual parameter here.
And we can check for that parameter and then write code accordingly.
Here's our development process:

* Modify our `custom login form`,
* Check for the `error` parameter, and if the `error` parameter exists,
then we're actually going to show an error message

Alright, so step one of modifying the form, checking for an error.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Custom Login Page</title>
</head>
<body>
    <h3>My Custom Login Page</h3>
    <form action="#" th:action="@{/authenticateTheUser}" method="POST">
        <div th:if="${param.error}">
          <i>Sorry! You entered invalid username/password.</i>
        </div>
      
        <p>
            User name: <input type="text" name="username" />
        </p>
        <p>
            Password: <input type="password" name="password" />
        </p>
        
        <input type="submit" value="Login" />
    </form>
</body>
</html>
````

Then we're just going to add some new code that's listed here,
and we're basically going to test to see if the `error` parameter is set.
If it's not, then we'll actually show an error message.
So here inside of this block we have, "_Sorry, you enter the invalid username and password._"
That's the error message that'll show up.
Now remember, that error parameters actually passed over by **Spring Security** automatically, in the case of a failed login.
So it's simply writing code to handle for that.
So that's the little bit of coding here that we needed to add to have support
for login error messages in our own custom form.
And I think I'm ready to test this out.
So let's go ahead and run it.
Let's go ahead and just do a login with nothing:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image26.png" alt="image26">
</div>

So here, this is our error message saying,
Hey, _"sorry you entered an invalid username/password"_ 
because of that error parameter that's passed on the end of the URL.
So this is working out really good for us.
And the error message, it's there, but it's kinda boring.
I'd like for it to look different, right?
You want to style it up some more.
You maybe want to add some color to it.
So what we'll do is we'll actually apply some **CSS**
to this error message just to kind of add some color, and make it look official.
Allright, so that's what we'll do.
So we'll do a bit of **CSS** here in this course.
So I'll cover the basics here, and you can feel free to extend it
with your own fancy awesome **CSS** coding skills.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Custom Login Page</title>
    <style>
        .failed {
            color: red;
        }
    </style>
</head>
<body>
    <h3>My Custom Login Page</h3>
    <form action="#" th:action="@{/authenticateTheUser}" method="POST">
        <div th:if="${param.error}">
          <i class="failed">Sorry! You entered invalid username/password.</i>
        </div>
      
        <p>
            User name: <input type="text" name="username" />
        </p>
        <p>
            Password: <input type="password" name="password" />
        </p>
        
        <input type="submit" value="Login" />
    </form>
</body>
</html>
````

Allright, so what I'll do here in the head of this **HTML** page is I'll add a style.
And the actual **CSS** `<style>` I'll add, I'll call it `.failed`.
Just `color`.
And I'll say `red`.
And that's basically it.
So that's my **CSS** style that I've set up.
Again, **CSS-101** here.
Then we'll actually move down into our actual code, and then we'll actually apply the **CSS** style.
I'll apply the CSS style.
So here I'll say `class` equals `failed`.
Allright, so that's how you apply a **CSS** style.
So you use the actual `style` name without the dot in this case,
and it'll apply the actual style to that text.
So we should see some italics text in red.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image27.png" alt="image27">
</div>

Allright, so that's good.
So we applied some **CSS** to this example.
So it's totally up to you.
You can customize this **HTML** page, and using any **HTML**, any **CSS** styles, frameworks that you want.

</div>

### [Custom Login Form with Bootstrap]()
<div style="text-align:justify">

In this section, we're going to learn how to set up a **Bootstrap Login Page**.
So far we have our login page, but it's kinda ugly, and that's how before picture.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image28.png" alt="image28">
</div>

And here we're going to have this really fancy custom login page, with colors, and CSS styles, and so on.
And what we'll do is we'll make use of **Bootstrap** to make our login form beautiful.
So what is Bootstrap?
Well Bootstraps a web framework that includes **CSS** styles and **JavaScript**.
It's really focused on front-end UI development. 
And for our project, you do not need to have any experience with **Bootstrap**
because we have some help that's going to give us a jump start.
So again, you don't need any **Bootstrap** experience.
However, if you wanted to learn **Bootstrap** on your own,
there are tons of free tutorials online, simply do a Google search for a **Bootstrap** tutorial.

So it's really just an **HTML** page, and it has **Bootstrap CSS** styles applied to it.
We'll simply modify the form to use **Spring Security** for our project.
So here's our Development Process:

* Update the form to point to our login processing URL
* Verify the form fields for username and password
* Change our controller to use our new **Bootstrap** login form 

Here, this is just a template:

````html
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Bootstrap demo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    </head>

    <body>
        <div class="container">
            <div id="loginbox" style="margin-top: 50px;" class="col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
                <div class="card border-info">
                    <div class="card-header bg-info">
                        Sign In
                    </div>

                    <div class="card-body">
                        <div class="card-text">

                            <!-- Login Form -->
                            <form action="" th:action="" method="" class="form-horizontal">

                                <!-- Place for messages: error, alert etc ... -->
                                <div class="form-group">
                                    <div class="col-xs-15">
                                        <div>
                                            <!-- Check for login error -->

                                            <!--
                                            <div>

                                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                                    Invalid username and password.
                                                </div>

                                            </div>
                                            -->
                                        </div>
                                    </div>
                                </div>

                                <!-- User name -->
                                <div style="margin-bottom: 25px;" class="input-group">
                                    <input type="text" name="username" placeholder="username" class="form-control" />
                                </div>

                                <!-- Password -->
                                <div style="margin-bottom: 25px;" class="input-group">
                                    <input type="password" name="password" placeholder="password" class="form-control" />
                                </div>

                                <!-- Login/Submit Button -->
                                <div style="margin-top: 10px;" class="form-group">
                                    <div class="col-sm-6 controls">
                                        <button type="submit" class="btn btn-success">Login</button>
                                    </div>
                                </div>

                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
````

It's just plain **HTML**, we're going to copy this to our project, and we'll update it accordingly,
but at least we have some basic **HTML** to work with.
Now, let's go ahead and copy this file, and then we'll swing over to our IDE,
and we'll place this file in our source main resources templates directory.
Let's go ahead and paste the file there.
Excellent, so as a checkpoint here, we have our `fancy-login.html`
that's in the `src/main/resources/templates` directory.
So I'll add the XML namespace for **Thymeleaf**.

Allright, so in step one, we're going to update the form to use our login processing URL.
Let's move down here to the form section.
And we have this login form.

````html
<!-- Login Form -->
<form action="#" th:action="@{/authenticateTheUser}" method="POST" class="form-horizontal">
  <!-- Place for messages: error, alert etc ... -->
  <div class="form-group">
    <div class="col-xs-15">
      <div>
        <!-- Check for login error -->
        <!--
        <div>
          <div class="alert alert-danger col-xs-offset-1 col-xs-10">
              Invalid username and password.
          </div>
        </div>
        -->
      </div>
    </div>
  </div>

  <!-- User name -->
  <div style="margin-bottom: 25px;" class="input-group">
    <input type="text" name="username" placeholder="username" class="form-control" />
  </div>
  <!-- Password -->
  <div style="margin-bottom: 25px;" class="input-group">
    <input type="password" name="password" placeholder="password" class="form-control" />
  </div>
  <!-- Login/Submit Button -->
  <div style="margin-top: 10px;" class="form-group">
    <div class="col-sm-6 controls">
      <button type="submit" class="btn btn-success">Login</button>
    </div>
  </div>
</form>
````

And for the `th:action`, we're going to make use of our login processing URL, `@{/authenticateTheUser}`.
Just as a checkpoint for your form, for `th:action`, make sure you have `/authenticateTheUser`, that's our login processing URL.
And also for the `method`, set the method to `POST`.
I'll just uncomment the section here for checking for login error.

````html
<!-- Place for messages: error, alert etc ... -->
<div class="form-group">
  <div class="col-xs-15">
    <div>
      <!-- Check for login error -->
      <div th:if="${param.error}">
          <div class="alert alert-danger col-xs-offset-1 col-xs-10">
              Invalid username and password.
          </div>
      </div>
    </div>
  </div>
</div>
````

And I'll copy some information from my `plain-login` for checking for login error, 
mainly that `th:if="${param.error}"` I'll just copy this from `plain-login`.
I'll move back over to `fancy-login`, and I'll paste that information.
Again, we wrote all this code before.
We're just simply adding this to this `fancy-login` page.
And that's kind of the checkpoint here for checking for login error for this example, and this is in `fancy-login`.

Now in step two, we're going to verify the form fields for username and password.
So we'll just move down here in this form, and we'll see that we have this `<input>` `type` of `"text"`, name of username, 
that's good.

````html
<!-- User name -->
<div style="margin-bottom: 25px;" class="input-group">
  <input type="text" name="username" placeholder="username" class="form-control" />
</div>
<!-- Password -->
<div style="margin-bottom: 25px;" class="input-group">
  <input type="password" name="password" placeholder="password" class="form-control" />
</div>
<!-- Login/Submit Button -->
<div style="margin-top: 10px;" class="form-group">
  <div class="col-sm-6 controls">
    <button type="submit" class="btn btn-success">Login</button>
  </div>
</div>
````

And also `password`, `name` equals `password`, and that's good also.
So that matches up with everything else.
And we also have our login `submit` button.

`````java
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        //return "plain-login";
        return "fancy-login";
    }
}
`````

And now in step three, we need to change our controller to use our new **Bootstrap** login form.
And what I do in this controller here is instead of returning `plain-login`, I'll actually return `fancy-login`.
Let's go ahead and run our application.
And now our app is up and running:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image29.png" alt="image29">
</div>

And, success.
We're using this fancy **Bootstrap** login form.
So this is great.
So we're using that template that was provided.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image30.png" alt="image30">
</div>

If I try and log in right now, I see the error message,
so that's really good with all the pretty **Bootstrap** styles.
I'll go ahead and use some correct information here for our user.
So we have a user of `john` and a password of `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image22.png" alt="image31">
</div>

And if we go ahead and just do a login here, so we're in our application.
So this is a good example here of using a custom login form, in fact using a fancy **Bootstrap** login form for our application.

</div>

### [Logout]()
<div style="text-align:justify">

In this section, we'll learn how to use **Spring Security Logout**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image32.png" alt="image32">
</div>

So we'll add this new button to our application, `Logout`, on our homepage,
then click the button, it's going to clear the user's session and then redirect the user back to the login page.
Alright, so here's our development process:

* Add logout support to the **Spring Security Configuration**
* Add a logout button to our page
* Update the login form to display a logged out message

Okay, so moving ahead here with step one of adding the logout support to our **Spring Security Configuration**.
So we have this existing code.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer.anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll())
            .logout(logout -> logout.permitAll());

    return http.build();
}
````

The new piece here is `logout.permitAll()`.
So this basically gives us logout support that'll expose the default URL for logging out, `/logout`.
Pretty simple and straightforward.

And then with step two of adding the logout button,
we're going to send data to that default logout URL, `/logout`.
So this logout URL is going to be handled by the **Spring Security** filters.
You get it for free.
There's no coding required, and, again, this is some more of that **Spring Security** magic,
which is really good stuff here.
Now a little bit more on adding this logout button.
We're going to send data to that default logout URL of `/logout`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Company Home Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>
<body>
  <h2>Company Home Page</h2>
  <hr>
  <p>
    Welcome to the company home page!
  </p>
  <!-- Logout Button -->
  <form action="@{/fancy-login}" th:action="@{/logout}" method="POST" class="form-horizontal">
    <input type="submit" class="btn btn-danger" value="Logout" />
  </form>
</body>
</html>
````

So here's our form action, and then we'll have a `/logout` `method=POST`.
So we must post the data across, and then we'll simply place the input type or place the button inside of this form.
So effectively, you need to post data across for `/logout` and you need to make use of a form.
And the **GET** method is disabled by default.
When a logout is processed, by default **Spring Security** will actually invalidate the user's **HTTP** session
and remove their session cookies.
It'll also send the user back to your login page, and they'll append a logout parameter.
And we can check for that in the login form to display a logged out message as needed.
Alright, so here's the coding here for displaying that logged out message.

````html
<!-- Check for logout -->
<div th:if="${param.logout}">

  <div class="alert alert-success col-xs-offset-1 col-xs-10">
    You have been logged out.
  </div>

</div>
````

Alright, so we'll modify our login form, and we'll check for `logout`.
We're going to check for that `logout` parameter.
If the `logout` parameter exists, we're going to show the logged out message.
So here, "_Hey, you've been logged out._"
And remember, we're checking for that param because **Spring Security** will send that parameter
or pin that parameter to the end of the URL, so we know that the user's actually been logged out.
Let's go ahead and run the application.
And log in with `john`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image33.png" alt="image33">
</div>

And, there's our new `logout` button.
Let's test it out.
So we hit logout and:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image34.png" alt="image34">
</div>

Nice.
We're logged out.
And it also gives us this nice message.
"_You've been logged out_".

</div>

### [User ID and Roles]()
<div style="text-align:justify">

In this section, we're going to learn how to display user ID and roles.
So we'll have our homepage here, and then we'll make use of **Spring Security** to display the user ID and also the role.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image35.png" alt="image35">
</div>

And this is very useful.
So once a user logs into our system, we know who they are.
And also, we have a list of roles that they're assigned to.
**Spring Security** provides support for accessing the user IDs and the roles.
So they make it very easy for us to add the support to our webpage.

So here's our basic development process:
* Display the user ID
* Display the user roles

In step one, we're going to display the user ID.
So on our webpage, we make use of the **security authentication**, `principle.username`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title>Company Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>
<body>

    <h2>Company Home Page</h2>
    <hr>
    <p>
        Welcome to the company home page!
    </p>
    <hr>
    
    <!-- display user name and role -->
    <p>
      User: <span sec:authentication="principal.username"></span>
      <br><br>
      Role(s): <span sec:authentication="principal.authorities"></span>
    </p>
    
    <hr>
    <!-- Logout Button -->
    <form action="@{/fancy-login}" th:action="@{/logout}" method="POST" class="form-horizontal">
        <input type="submit" class="btn btn-danger" value="Logout" />
    </form>

</body>
</html>
````

This will actually display the user ID for the person who is currently logged in.
And then in step two, we're going to display the user roles.
So I make use of **security authentication**. 
And here I say, `principle.authorities`.
So authorities is the same thing as user roles.
This will actually display the user roles that are assigned to this given user.
And that's basically it for the coding here, for displaying the user ID and the roles.

Allright, so let's go ahead and get ready to test this thing out and let's run it.
Here's our login page.
So we'll just log in with "`john`", "`test123`".

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image36.png" alt="image36">
</div>

Okay, so we're logged in and nice.
Take a look at this new section here.
So we have the actual username, the user ID of "`john`", and then we also have the role, or the list of roles for this given user.
So we have "`ROLE_EMPLOYEE`".
This is actually a list or a collection of roles.
Because a user can have multiple roles.
And so you'll see it in this fashion.
Now, out of the box, by default, **Spring Security** will make use of the role underscore prefix for the given roles.
Because remember in our source code, we had `ADMIN`, `EMPLOYEE`, and `MANAGER`.
But here it's saying, "ROLE_EMPLOYEE".
So that's the default prefix out of the box but, we can change that, it's customizable or configurable.
So one thing, I want to do here is to go ahead and log out.
So I should be able to go to my login page now and log in as "`mary`".

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image37.png" alt="image37">
</div>

There we go, the new roles, `EMPLOYEE` and `MANAGER`.
And remember, like I said earlier, **Spring Security** uses that "`ROLE_`" prefix,
but we'll actually change that in some later section, so don't worry about it for now.

</div>

### [Restrict URLs Based on Roles]()
<div style="text-align:justify">

In this section, we're going to learn how to restrict access based on roles.
So with our example, we're going to have our **Login Page**,
and, once we log in, we're going to go to our **Home Page**,
and this will be restricted to only people with the role of `EMPLOYEE`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image38.png" alt="image38">
</div>

And then we'll go to another page, `/leaders`.
This is the Leadership Retreat Page.
This will only be available to users with the role of `MANAGER`.
And then we'll have a link to our page, `/systems`.
That's for the **Admin Holiday Cruise**, which is pretty cool.
This will be restricted to users with the role of `ADMIN`.
Here's our development process:

* Create the supporting controller code and view pages
* Restrict access based on the user roles

Looking at step one of creating a supporting controller code and view pages.
Again, we're going to have this little diagram with the pages 
for **Home Page**, **Leadership Retreat**, and **Admin Holiday Cruise**.
This is all plain vanilla **Spring MVC** work as far as setting up the request mappings and the view pages.
Nothing security-specific here, so we'll just cover that later.

Alright, so in step two, we're going to restrict access to roles.
We're going to update our **Spring Security Java** configuration and here's the general syntax.

````text
requestMatchers(<< add path to match on >>).hasRole(<< authorized role >>)
````

We'll have `requestMatchers`, and we'll add a path to match on, `.hasRole()`, for the given authorized role.
So, for example, we can restrict access to a given path `"/systems/**"`,
and then we want to give access to a user that has a given role. 
So we say `hasRole`, and then we actually give the role as a string.
So we could use the role of `"ADMIN"`.
Allright, so that's the general syntax.
Now, what about the case where we have multiple roles?

````text
requestMatchers(<< add path to match on >>).hasAnyRole(<< list of authorized roles >>)
````

So here I could say `.hasAnyRole()`.
So, we want to match any role in the list, and we simply give a comma-delimited list of roles.
So here I could have `"ADMIN", "DEVELOPER", "VIP", "PLATINUM"`, and so on.
So this will actually authorize access for any role that's in that given list.
And, again, the role names can be anything that you want, anything that you dream up.
Alright, so let's go ahead and restrict on the paths to `"EMPLOYEE"`.

````java
requestMatchers("/").hasRole("EMPLOYEE")
````

So here we have `requestMatchers("/").hasRole()`.
And so, in our diagram, we see the **Home Page**, it's the root of our application for the role, `"EMPLOYEE"`.
This actually has a match on the root path of our application.
Alright, so now let's go ahead and look at restricting the path for "`/leaders`" to the actual `"MANAGER"` role.

````java
requestMatchers("/leaders/**").hasRole("MANAGER")
````

So I have `requestMatchers("/leaders/**").hasRole("MANAGER")`.
And the "`**`" will actually match on all subdirectories here,
and this will be restricted to the role of `"MANAGER"`.
So only managers can see the leadership retreat page.
Alright, now let's take a look at this example of restricting the path for "`/systems`" to `"ADMIN"`.

````java
requestMatchers("/systems/**").hasRole("ADMIN")
````

Here I have `requestMatchers("/systems/**").hasRole("ADMIN")`.
So this will match on the path of systems and all subdirectories.
So, **Admin Holiday Cruise**, that's our special cruise,
that admins can go on, and it's secured, and it's secret.
It's only for them to get the details on the special cruise.
Now let's kinda pull it all together with an example.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/").hasRole("EMPLOYEE")
                    .requestMatchers("/leaders/**").hasRole("MANAGER")
                    .requestMatchers("/systems/**").hasRole("ADMIN")
                    .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll())
            .logout(logout -> logout.permitAll());

    return http.build();
}
````

So, in our method, we have the `authorizeHttpRequests`, and we give these specific `requestMatchers`,
so we get the `requestMatchers` for `"/"` for role `"EMPLOYEE"`,
`requestMatchers("/leaders/**")` for `"MANAGER"`, and for `"/systems/**"` for `"ADMIN"`.
And this matches up with the actual role names that are defined for our given users.
And, again, you can make use of any role name that you want.
You simply have to stay consistent within your application.
And that's basically it.
That's how we can restrict access to paths based on user roles.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image39.png" alt="image39">
</div>

And so, on our homepage in the center, we'll actually have those **href** links.
And so, we'll write some code to actually show those links that'll point to those given pages.
And then again, remember, up top we have these users with roles, so `john`, `mary`, `susan`, roles of `"EMPLOYEE"`, `"MANAGER"`, `"ADMIN"`.
So we'll have all that stuff in place working for us too.
Now, our development process, remember, we're going to create the supporting controller code and view pages.
And then finally, we'll restrict access based on those roles, and we'll kind of go through all the steps for this.
And we'll simply go through and create the supporting controller code and view pages.
So the first thing I'm going to do is go ahead and open up this homepage.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http//www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Company Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>
<body>
    <h2>Company Home Page</h2>
    <hr>
    <p>
        Welcome to the company home page!
    </p>
    <hr>
    <!-- display user name and role -->
    <p>
        User: <span sec:authentication="principal.username"></span>
        <br><br>
        Role(s): <span sec:authentication="principal.authorities"></span>
    </p>
    <hr>
    <!-- Add a link to point to /leaders ... this is for the managers -->
    <p>
      <a href="@{/leaders}">Leadership Meeting</a>(Only for Manager peeps)
    </p>
    <hr>
    <!-- Logout Button -->
    <form action="@{/fancy-login}" th:action="@{/logout}" method="POST" class="form-horizontal">
        <input type="submit" class="btn btn-danger" value="Logout" />
    </form>
</body>
</html>
````

And basically, what I'm want to do here is update this homepage 
and add a link to actually point to this path `"/leaders"`.
So I'll just write a quick little note here to myself to keep myself on track.
So I'm going to add a link to point to `"/leaders"`, and this is for the managers.
Allright, so this is kind of desired output, 
this little **href** link that we want to have on our actual page that'll point over to that given item.
Allright, so I'll just set up a quick little paragraph here,
and then I'll just set up an anchor tag with a **href**.
So a **href** equals, I'll just put double quotes for now, and I'll just kind of write my text,
and I'll come back and put in the **href** stuff.
So, `Leadership Meeting`.
So that'll show up in the hyperlink.
And then, just in parentheses, I'll say `Only for Manager peeps`.
This is mainly just for display purposes only.
This is more just cosmetic text just to kind of keep us on track as we go through some of our demos here.
So now I need to actually write some code in this href to actually point to leaders.
So that's going to go to the path `@{/leaders}`, and that's basically what we want to have here
for this link for the leadership meeting `/leaders`.

So we're going to go ahead and create our controller code and also our view code for `/leaders`.
So we'll open up `DemoController.java`.
Right now, we just have one `@GetMapping`.

````java
package com.luv2code.springboot.demosecurity.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }
}
````

And what I need to do is actually go through and add another request mapping for `/leaders`.
And what I'll do actually, is I'll kind of move up to the previous `@GetMapping`, 
and I'll just copy that code, because I want to do something very similar.

````java
// add aa request mapping for /leaders
@GetMapping("/leaders")
public String showLeaders() {
    return "leaders";
}
````

So let's just go ahead and copy that, move down here, and just paste it.
So let's just update the `@GetMapping` to say `"/leaders"`.
We'll update the method for `showLeaders`, and then we'll simply return `"leaders"`.
Allright, so we're just updating some of the code that we just did a little copy-paste on.

So this is all plain vanilla stuff that we've already done before with **Spring MVC**, creating controllers and creating views.
So here's my very basic page that I'm creating right now.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>LEADERS Home Page</title>
</head>
<body>
  <h2>LEADERS Home Page</h2>
  <hr>
  <p>
    See you in Brazil ... for our annual Leadership retreat!
    <br>
    Keep this trip a secret, don't tell the regular employees LOL :-)
  </p>
  <hr>
  <a th:href="@{/}">Back to Home Page</a>
</body>
</html>
````

Let's go ahead and set up a title here for this page, `LEADERS Home Page`.
And then I'll just set up a little `<h2>` section here.
And I'll actually just copy what I have up top from the title.
I'll just copy that information, and I'll just move down here into this `<h2>`, and paste it, 
just so I have the same title on the same page, you know, all that kinda stuff.
Allright, and then I just do an `<hr>`.
And now what I'm going to do is give that secret information that's only available to leaders.
So "_see you in Brazil for our annual Leadership retreat_".
And then keep this trip a secret.
"_Don't tell the regular employees about it, only for leaders_".
So let's do an `<hr>`.
And then let's simply add a link back to the homepage.
So I'll do an `href`, and I'll just say, `Back to Home Page`, just so they can go back to the main page of our application.
And that's pretty much it for this leader's page.
And let's go through and test it.
Alright, so we're at the homepage here.
So we simply log in to the application.
So we just make use of that user, `john`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image40.png" alt="image40">
</div>

Allright, so we're in the page here, the company homepage, 
and we have that new link, leadership meeting, only for manager peeps.
We see that we're logged in as `john`, he's an employee.
And if I access leadership meeting for `"/leaders"`,

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image41.png" alt="image41">
</div>

then boom, it blows up, and yes, this is exactly what I wanted.
So here, the server understood the request but refuses to authorize it,
so that means that `john` is not authorized to access this URL.
He doesn't have the correct access role in order to access `"/leaders"`, and that's a good thing.
That's kinda how we designed it, and kind of how we wanted it to work out.
And again, this all matches up based on the user IDs that we have set up,
and also the roles and the actual configuration we set up.

So now let's go ahead and click on back.
And let's go ahead and log out, because I want to log out now and try and log in with `mary`,
a different user with a manager role, and see how this part works out for us.
So we'll do a log out there, and then I'll log in here as `mary`.
So again, username `mary`, password `test123`.
Allright, so we're logged in.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image42.png" alt="image42">
</div>

So we're logged in as `mary`, so we see the user ID up top, and we also see the role,
so she has the role of employee and also of manager.
So that's good, so we're in good shape here.
Allright, so now she should be able to access this link leadership meeting for `"/leaders"`, and it should work out.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image43.png" alt="image43">
</div>

Allright, so success.
So now `mary` can access `"/leaders"`.

Alright, so we took care of our homepage, and we also took care of the `"/leaders"` page for managers.
Now we're going to make use of the actual systems portion, the IT systems meeting only for admin peeps,
so the `"/systems"`, and we'll restrict that for the role of `"ADMIN"`.
So moving back over here, I'm going to update my homepage.
I'm going to add a link to point to `"/systems"`.

````html
<!-- Add a link to point to /systems ... this is for the admins -->
<p>
  <a href="/systems">IT Systems Meeting</a>(Only for Admin peeps)
</p>
````

And I'll write a quick little comment here to myself just to kind of keep myself on track.
So we'll add a link to point to `"/systems"`, and this is only for admins.
And then for the link part of it, I'll simply just move up here,
I'll copy the stuff from the leadership section, and I'll just kind of copy that information,
move down, and then just paste it here for systems.
And then I'll just kind of go through and update this accordingly.
So over here instead of `/leaders`, I'll say `/systems`.
So make sure it's `/systems` there, for the `IT Systems Meeting`.
And also this is only for our admin peeps, or our admin peoples.
Forgive me for using the American slang there.
Peeps, that's short for peoples or friends or folks.
Alright, so this looks pretty good so far.
So now we're in position to go ahead and create the controller code and the view code for `"/systems"`.

````java
// add aa request mapping for /systems
@GetMapping("/systems")
public String showSystems() {
    return "systems";
}
````

So I'll move back over to my `DemoController.java`, and I'll add this `"/systems"` here.
So, and I'll write a quick comment here to myself.
I'll say "`add request mapping for /systems.`"
I'll move up here, and I'll copy this leader section, and then I'll just kind of move down and paste it.
And then I'll just go through the exercise, right, of just renaming those items instead of `/leaders`, we'll say `/systems`.
And here I'll say `showSystems`, and then I'll return `"systems"`.
And that's basically it here for our controller code for systems.
And so right now we're in systems, it was a copy of leaders, so we need to go through and update some of the information here.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>SYSTEMS Home Page</title>
</head>
<body>
    <h2>LEADERS Home Page</h2>
    <hr>
    <p>
        We have our annual holiday Caribbean cruise coming up. Register now!
        <br>
        Keep this trip a secret, don't tell the regular employees LOL :-)
    </p>
    <hr>
    <a th:href="@{/}">Back to Home Page</a>
</body>
</html>
````

So instead of saying "`LEADERS Home Page`", we'll call it "`SYSTEMS Home Page`".
So we'll take care of that on line three, we'll do a similar thing there.
So instead of the Brazil trip, we're going to have our annual holiday cruise in the Caribbean.
Register now.
Allright, so that's kind of basically it, have some specific or a secret information for the systems group, and that's it.
And that's kinda associated with the admin role for this given demo or example that we're setting up.
So let's go ahead and log out, and then we'll actually log in as `john` first,
because `john`'s just a regular employee, and he shouldn't be able to see the systems page.
So we'll log in as `john`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image44.png" alt="image44">
</div>

Alright, so `john`'s logged in.
So user john, his role, employee.
When we try to access `/systems`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image41.png" alt="image45">
</div>

So this is kinda what I expected.
So he only has employee roles, so his access is forbidden.
He's not authorized to access this page.
So we're good to go here.
Now let's go ahead and click on the back button, and let's go ahead and actually log out,
and we'll log in with a different user.
So I'm logging out here, I'll log in as `susan`, and her password is `test123`.
Okay, great.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image46.png" alt="image46">
</div>

So we're logged in as susan,
and then we see that susan has the roles admin and employee.
And then in particular, note that she's an admin.
Allright, so she's an admin, so she should be able to access this IT systems meeting.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image47.png" alt="image47">
</div>

And yeah, this is good.
So she has the admin role, so she's able to access information on this systems page that we set up with our nice little diagram.
And so she's aware of the Caribbean cruise coming up and so on.
It's top secret, only for admins, okay.
So we had multiple roles, we had different paths.
And based on a given path, we set up access restrictions accordingly, and we did all the coding for that.

</div>

### [Custom Access Denied Page]()
<div style="text-align:justify">

In this section, we'll learn how to set up **custom access denied** page.
So we've seen the **default access denied** page, and it's actually pretty scary.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image48.png" alt="image48">
</div>

This will definitely scare a normal user of your application.
So what we're going to do is actually provide our own custom page.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image49.png" alt="image49">
</div>

We'll give our own custom error message, along with controlling the look and feel.
So in our example here, we'll actually create a custom page.
We'll say access denied, and we'll give our message.
You can customize this page, you can give your own **HTML**, you can give your own **CSS**, 
you can add in some fancy **Bootstrap**.
You can also give your own custom error message.
It's totally up to you on how this actual page looks.
So here's the development process:

* Configure the custom page for access denied
* Create the supporting controller code and view page

Allright, so step one of configuring the custom page for access denied.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/").hasRole("EMPLOYEE")
                    .requestMatchers("/leaders/**").hasRole("MANAGER")
                    .requestMatchers("/systems/**").hasRole("ADMIN")
                    .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

    return http.build();
}
````

So here we have `exceptionHandling()`, `.accessDeniedPage`, and then you simply give the request mapping path.
Now, this will be used if there's an authorization error or the user cannot access a given page.
**Spring Security** will use this request mapping path to show the user the access denied page.
And I give `"/access-denied"`.
And that's our request mapping path that we'll set up for this example.
And this item here could be anything. 
I just chose `"/access-denied"`, but you can call it anything you'd like.
So, now let's go ahead and create the supporting controller code and view pages.

So, I'll go ahead and open up `DemoController.java`.
I like to put my access denied code into the demo controller.

````java
// add a request mapping for /access-denied
@GetMapping("/access-denied")
public String showAccessDenied() {
    return "access-denied";
}
````

Because this code is more security-related, you could actually place the request mapping anywhere.
But I like to kind of group my personal-related stuff into a single controller here.
So, this is the demo controller.
We could call it security controller or whatever.
We'll just drop it here.
And what I'll do is write a quick little comment here to myself.
For the `@GetMapping`, we'll have `"/access-denied"`.
That's the actual request mapping path we specified in the configuration.
And then I'll simply update the method name.
And then return, I'll say return `"access-denied"`.
And that's basically the coding here.
So we need to go out and create `access-denied`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Access Denied</title>
</head>
<body>

  <h2>Access Denied - You are not authorized to access this resource.</h2>
  <hr>
  <a th:href="@{/}">Back to Home Page</a>

</body>
</html>
````

And then I'll go through, and I'll just drop in a quick title for this page.
I'll just say `Access Denied` just so that we know that, hey, this is our custom page that we've created.
And then remember the body for this page we can display anything that we want, any error message.
So here I'll say "_Access Denied, and then you are not authorized to access this page_".
Again, we have full control over this error message as far as what we want to say.
And then finally, one best practice here is to always give a link back to the homepage just to be nice.
So, if they made it to the page, well at least give them a way to return back to the main homepage.
So that'll give us a link back to the homepage.
And that's basically it.
So we have this page that we've set up, plain vanilla.
This is our custom **Access Denied** page, we have full control over the content.
Allright, so let's go ahead and test this out and check out our custom page.
So let's just go ahead and run this application real quick.
And we'll just log in as `john`, `test123`.
So we're logged in, so he can not access any of these links here at the bottom, Leadership or IT Systems.
That'll give an error message.
So here, if we click the link:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image50.png" alt="image50">
</div>

This is good.
So `john` only has employee role, he can't access "`/leaders`".
So you went through the process of setting up our own custom page for spring security.

</div>

### [Display Content Based on Roles]()
<div style="text-align:justify">

In this section, we're going to learn how to display content based on roles.
So we have our homepage that we've been using so far.
But the big question is, why show these links?

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image51.png" alt="image51">
</div>

Since `john` is an employee, he really shouldn't be able to see this content or links for the leadership meeting or the IT systems meeting.
Now I know that we already have those URLs locked down with our security configurations,
but we really shouldn't even show him this on the page since he does not have the given role for manager or admin.
What I'd like to do is display the content based on roles.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image52.png" alt="image52">
</div>

So right now I'm logged in as `mary`, we know that she's an employee and a manager.
So since she's a manager, she can actually see the manager specific content.
What we can do is make use of spring security tags to display content based on the user role.

````html
<!-- Add a link to point to /leaders ... this is for the managers -->
<div sec:authorize="hasRole('MANAGER')">
  <p>
    <a href="/leaders">Leadership Meeting</a>(Only for Manager peeps)
  </p>
</div>
````

So here I have `security:authorize`, and then I give the role, access equals `hasRole('MANAGER')`.
So basically here we'll only show this section for users with the manager role.
So basically everything inside of this tag will only be displayed for a user with the manager role.
Even if you have a user who's somewhat savvy with that web browser, the user may attempt to view document source.
This content isn't hidden, it's simply not included.
So the resulting **HTML** page will not even have this content.
So they can't even try and backdoor the system and view your source,
because they won't be able to see the generated content.
Allright, so let's move forward here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image53.png" alt="image53">
</div>

So in this example, we're actually logged in as `susan`, 
and so we know that `susan` is an administrator and an employee.
So based on this, `susan` can see the admin content.
So again, we're controlling that content based on the actual user role.

````html
<!-- Add a link to point to /systems ... this is for the admins -->
<div sec:authorize="hasRole('ADMIN')">
  <p>
    <a href="/systems">IT Systems Meeting</a>(Only for Admin peeps)
  </p>
</div>
````

We'll simply use the same approach using those spring security tags.
So here I have security colon authorize, access equals has role admin.
Basically everything inside of this tag here will be shown for users with the admin role.

</div>

## [JDBC Authentication and Authorization]()
### [JDBC Authentication - Plain Text]()
<div style="text-align:justify">

In this section, we'll learn how to use **Spring Security** with user accounts stored in the database.
So far, our user accounts were hard coded in **Java** source code,
and we did that just to kind of keep things simple, but now we want to add database access.
This is an advanced feature of **Spring Security**, and we'll actually use it in this section series.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image54.png" alt="image54">
</div>

Now, let's recall the user roles.
We have three users, `john`, `mary`, and `susan`, we have our passwords, and we also have their roles.
And we used these in some of the previous sections.
Now we'll simply put this information in the database.
Out of the box, **Spring Security** can read user information from the database.
By to default, you have to follow **Spring Security**'s predefined table schemas.
But the nice thing about it is that by following their schemas,
and then **Spring Security** includes all the **JDBC Code** to actually read information from the database,
there's very little **Java** code you have to write as far as **JDBC Code** for reading information from the database.
All you have to do is simply set up the configuration,
create the appropriate tables, and **Spring Security** will do all the heavy lifting for you in the background.
Now, you also have the option of customizing the table schemas.
This is very useful if you have custom tables specific to your given project.
The only thing that you'll be responsible for is developing the code to access the data.
In this scenario, you'll have to write the low level **JDBC Code** or **Hibernate Code** to read the data
from the appropriate tables.
You'll have to read the account information and also read the user roles.
What we'll do in this section series is that we'll start off, 
and we'll use **Spring Security**'s predefined table schemas.
That'll give us all the functionality, and all the code for hooking into the actual database.
And this is all given to us out of the box.
Here's the development process:

* Create an **SQL Script** to set up the database tables
* Add database support to our project using the **Maven** `pom` file
* Create the **JDBC** properties file
* Update the **Spring Security** configuration to use **JDBC** for authentication and authorization

Allright, as I mentioned, **Spring Security** has a default database schema.
So you need to provide two tables, one called users and another one called authorities.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image55.png" alt="image55">
</div>

And you have to use these exact table names.
And also the tables need to have these specific columns,
`username`, `password`, and `enabled` for the user's table, and then also `username` and `authority` for the `authorities` table.
You need to have the exact same table names and columns as shown here.
Also, when you see the word `authorities` here,
`authorities` is the same or loosely the same thing as `roles`.
So again, when you see `authorities`, just think `roles`.

Moving to step one, that's creating the `SQL Script` to set up the database tables.
As you know, we need to have these two tables, `users` and `authorities`.

````sql
USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);
````

I'll start off with `users` here.
I'll go ahead and create the table `users`, and then I'll create those columns, `username`, `password`, and `enabled`.
And then we'll also set up the primary key based on the `username`.
And that's basically it here for the table.
Again, the exact same table name of `users` and the exact same column names, `username`, `password` and `enabled`.
Now, let's go ahead and handle the password storage here and `insert` our `users`.
And then we'll do an `INSERT INTO 'users'`.
We'll get the values here for `john`, `mary`, `susan`.
And we give the `username`, the `password`, the `enabled status`.
And now what we have here is the actual password.
So the `password` is `test123`.
And remember, we have the actual encoding algorithm id, and that's in curly braces.
Here, it's `{noop}`.
That basically says the `password` is stored as **plain text**.
And we'll start with this just for the beginning.
Later on, we'll move into more advanced features by using **bcrypt** encryption.

Now, let's go ahead and move into the `roles`.
And we have to create this table called `authorities`.

````sql
--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
````

Here we have two fields for `username` and `authority`.
And then we go through it and set up the unique key based on the `username` and `authority`.
We also set up our constraints.
And here, the foreign key references the `user`'s table for `username`.
And that'll basically create the `authorities` table. 
And again, remember, the `authorities` table here's the same thing as `roles`.
Allright, so now let's go ahead and insert some of our user roles into our database table.
So we have our `users`, the tables here on the bottom, and then we simply do an `INSERT INTO 'authorities'`.
And we'll go ahead and do an insert here for `'john'`.
He's an employee.
We do an insert here for `'mary'`.
She's an employee and manager.
And we also do an insert for `'susan'`.
She's an employee, manager, and admin.
And internally, **Spring Security** will use the `ROLE_` prefix for the actual role entries.

Now, moving ahead here to step two, we add the database support to our **Maven** `pom` file.
Here we make sure to give a reference to our JDBC driver.

````xml
<!-- MySQL -->
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
````

In this example, we're using `MySQL`, so we give the appropriate entry here for the `MySQL` **groupId** and **artifactId**.

Next, we have step three of creating our **JDBC** `properties` file.
Basically, we can reuse our existing properties because we'll have this one database schema for our employee directory.
And we'll place our security tables within that same database schema,
so we can simply just reuse the same information.

````properties
#
# JDBC connection properties
#
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent

#
# Log JDBC SQL statements
#
# Only use for dev/testing
# DO NOT use for PRODUCTION since it will log user names
logging.level.org.springframework.jdbc.core=TRACE
````

And now I can move into my `DemoSecurityConfig.java`.
This is where I'm telling **Spring Security** to use **JDBC** authentication.
And then I inject the data source.

````java
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
}
````

This is the one that's autoconfigured by **Spring Boot**.
And then I tell **Spring Security** to use **JDBC** authentication with our data source.
And the really nice thing here is that we're no longer hard coding the users.
We're actually reading the `users` and `roles` from the database.
And now **Spring Security** will handle all the low level work of reading the user, password roles,
and so on from our database because we're following their table schema,
and we're using their actual table names and their actual column names.
So **Spring Security** will do a lot of the heavy lifting for us in the background.

Alright, let's run our application and test it out.
So our app is up and running.
Go to our login page.
We'll log in as `john`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image56.png" alt="image56">
</div>

And excellent.
So we're able to log into our application, so our user ID of `john`, and his role of `"EMPLOYEE"`.
Now I'd like to verify to make sure that it's really reading this information from the database.
Make sure it's not using hard code data.
So I can go look at the logs here in my application.

````text
  INFO 508 --- [demosecurity] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
  INFO 508 --- [demosecurity] [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@7a80a636
  INFO 508 --- [demosecurity] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
  INFO 508 --- [demosecurity] [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
  INFO 508 --- [demosecurity] [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.3.Final
  INFO 508 --- [demosecurity] [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
  INFO 508 --- [demosecurity] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
  INFO 508 --- [demosecurity] [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
  INFO 508 --- [demosecurity] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
  WARN 508 --- [demosecurity] [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
  INFO 508 --- [demosecurity] [  restartedMain] o.s.s.p.JdbcUserDetailsManager           : No authentication manager set. Reauthentication of users when changing passwords will not be performed.
  INFO 508 --- [demosecurity] [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with UserDetailsService bean with name userDetailsManager
  INFO 508 --- [demosecurity] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
  INFO 508 --- [demosecurity] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
  INFO 508 --- [demosecurity] [  restartedMain] c.l.s.d.DemosecurityApplication          : Started DemosecurityApplication in 3.323 seconds (process running for 3.763)
  INFO 508 --- [demosecurity] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
  INFO 508 --- [demosecurity] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
  INFO 508 --- [demosecurity] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
 DEBUG 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.JdbcTemplate               : Executing prepared SQL query
 DEBUG 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.JdbcTemplate               : Executing prepared SQL statement [select username,password,enabled from users where username = ?]
 TRACE 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.StatementCreatorUtils      : Setting SQL statement parameter value: column index 1, parameter value [john], value class [java.lang.String], SQL type unknown
 DEBUG 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.JdbcTemplate               : Executing prepared SQL query
 DEBUG 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.JdbcTemplate               : Executing prepared SQL statement [select username,authority from authorities where username = ?]
 TRACE 508 --- [demosecurity] [nio-8080-exec-3] o.s.jdbc.core.StatementCreatorUtils      : Setting SQL statement parameter value: column index 1, parameter value [john], value class [java.lang.String], SQL type unknown
````

I can see here that it's connecting to the database, 
and it has a connection to **MySQL** database, and the connection started, completed.
But I'd still like to verify the whole security piece of it as far as the authentication and the authorization piece.
And I can move down here a bit and I can see that it's **executing a prepared SQL statement**,
and it's retrieving the `username`, `password`, and `enabled` for authentication.
So we know that it's grabbing some information from the database,
and we can even find out the actual `username` that it's checking for.
And so the parameter value of `john`, so that's the `username`.
So it's checking for `john`'s information for authentication.
And then we see there's another query here where it's going to retrieve the `username` and `authority`.
And this is being used for authorization or retrieving the roles for this given user.
So we're able to kinda use that logging that we set up earlier
to see the information that it's using behind the scenes for querying the actual database for authentication and authorization.
So it is actually connecting to the database and retrieving our information for security.

Now we can swing back to our application.
Let's do a logout here, and let's try and log in with another one of our users.
Let's use `mary`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image57.png" alt="image57">
</div>

So she's logged in, username of `mary`, and then her roles, `"EMPLOYEE"` and `"MANAGER"`.
And also the roles work out just fine, so accessing certain pages and everything.

And also, just for sanity's sake, I want to do one more test here.
I want to make sure that it's really, really using the database.
And what I'll do is I'll actually go into the database, and change `mary`'s password,
and then try and go back and log into the application 
and see if it's really getting that information from the database, just to check, check, double check.
I'll open up the **MySQL Workbench**.
I'll do a query here on the user's table.
And then I'll actually go through and modify `mary`'s password.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image58.png" alt="image58">
</div>

Instead of `test123`, I'll give it `abc123`, just something different.
And then hit Enter there.
Now be sure that you click this `Apply` button in the bottom right.
They'll show the actual SQL statement, they'll execute.
So they'll update the password to `abc123` for the username of `mary`.
And now let's swing back to our application.
Let's use `mary`, `test123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image59.png" alt="image59">
</div>

Excellent.
Okay, this fails as expected, because we gave the wrong password.
Now let's go ahead and do it again, and let's use our new password, `abc123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image60.png" alt="image60">
</div>

And success.
We're logged-in user, `mary`.
The roles, `"EMPLOYEE"` and `"MANAGER"`.
So now we know that it's really using the database.
And also one other thing to notice here is that there's no need to restart our application.
**Spring Security** will actually check the database each time during login.
So this is a very nice feature.

And now what I'd like to do, just to kind of keep myself on track here,
let's go ahead and change the password back just so I make sure
that I use the proper passwords in the future here.
I'll change it back to `test123`.
And again, remember to go through the steps there of doing the `Apply` to actually apply this to the database.
So the key thing here is that we've modified our application to now use,
we've used **JDBC** to actually store the account information.
And this is just kind of the basic steps so far.
In some of the following sections, we'll get into more advanced features,
as far as encrypting the password and so on.
But at least we have the basic infrastructure in place.

</div>

### [JDBC Authentication - BCrypt Encryption]()
<div style="text-align:justify">

In this section we'll use Spring Security with password encryption.
So far our use of passwords are stored in **plain text**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image61.png" alt="image61">
</div>

So this is okay for getting started, but it's not for production, not ready for real-time projects.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image62.png" alt="image62">
</div>

So the best practice is to store the password in an encrypted format.
So here's the users, `john`, `mary`, and `susan` and their passwords.
And notice it's an encrypted version of the password.
So, if our databases were hacked the hackers wouldn't be able to figure out these passwords,
wouldn't be able to figure out the **plain text** version of these passwords.
Because they're encrypted.
The **Spring Security Team** recommends using the popular **bcrypt** algorithm.
So the **bcrypt** algorithm performs a one-way encrypted hash.
It adds a random salt to the password for additional protection.
And it also includes support to defeat brute force attacks.
So, this is the current recommendation from the **Spring Team**,
and it's a popular one-way password hashing algorithm that's used by other projects.

Now, if you'd like to get more background information or additional information on bcrypt,
I have some links [here](https://www.luv2code.com/why-bcrypt) for you.
So, if you'd like to know why you should use bcrypt to hash passwords,
go to the [site](https://www.luv2code.com/bcrypt-wiki-page), 
If you'd also like to get a detailed **bcrypt** algorithm analysis.
And finally, if you'd like to learn more practices on password hashing, 
simply go [here](https://www.luv2code.com/password-hashing-best-practices).
Now, these links will basically redirect you to other websites that provide all the detailed information for you.
And also don't worry about having to write down each one of these links.

So now you may wonder how to get a **bcrypt** password,
So, you have a plain text password, and you want to encrypt using bcrypt.
So, you have one option is to use a website utility to perform the encryption.
Another option is to write some **Java** code to perform the encryption.
So we'll actually cover option one in this section.
And then for option two, we'll have information on that in some of the later sections in the course.

Alright, so getting a **bcrypt** password using a website.
So you can simply go to this [link](https://www.luv2code.com/generate-bcrypt-password).
It's going to redirect you to a website utility, you'll enter your plain text password,
and then that website's going to generate a **bcrypt** password for you.
Allright, so let's go ahead and look at a quick demo of this.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image63.png" alt="image63">
</div>

And basically the way it works here is they have some text fills.
So you'll enter your plain text password, and you hit calculate, and it'll generate the encrypted password for you.
So for the plain text password I'm going to enter `test123`,
and then I'll move down here and hit the `calculate` button and then:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image64.png" alt="image64">
</div>

right here at the bottom, that's the generated password.
So this is an encrypted version of that **plain text** `test123`.
That's an encrypted version using **bcrypt**.
Now, one important thing to note is that multiple runs will generate a different password 
due to the random password salting.
So you can start with the same **plain text** password `test123`.
But if you hit `calculate` multiple times you'll actually get a different generated password.
And that's again, due to random password salting.
Effectively salting is random bits of data that'll add to the password to make it unique.
So taking a look at this example here we have a generated passwords 
and let's just keep an eye on the last couple of digits here.
Let's go ahead and hit `calculate` one more time for this `test123`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image65.png" alt="image65">
</div>

And then notice here that it changes.
Basically the whole thing changed, but I wanted you to focus on the last couple of characters,
so you can get an idea of things that are being changed.
Alright, so that's the idea of generating or calculating a **bcrypt** password.
And what we can do with this is that we can use these encrypted passwords,
and add them to our user accounts in our database.
And effectively we can seed our user accounts with encrypted passwords out of the box.


So let's go ahead and look at our development process:

* run the SQL Script that contains the encrypted passwords 

We also need to modify the DDL for the password field because the length should be 68 characters.
And, that's it.

There's no need to change any **Java** source code,
so this is mainly just a configuration exercise but no need to modify any of the **Java** code that we've created before.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image66.png" alt="image66">
</div>

So here we'll have **bcrypt**, and so this kind of maps to the actual ID that we have in our database table for password.
And then for the encoded password that'll be the actual generated or hashed value 
that we retrieved from that website or that we created using Java code.
Now, one thing that's really important here about this password column,
it must be at least 68 characters wide, because for `{bcrypt}`, that's eight characters,
and then the encoded password is 60 characters.
When you use **bcrypt**, your encoded password, or your encrypted password, is always 60 characters in length.
Regardless of the input of the plain text, it's always 60 characters in length.

````sql
USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password`    char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users` bcrypt password 'fun123'
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$10$J0bn7oskh1MiAUCjSlHAzudbspx8Tc1fzw73R.RY3oFcxOuir4NEu',1),
('mary','{bcrypt}$2a$10$AaH0AaU3loWzviBKz.yhmulBsvGQ0cMo4o5fLb.8.b4n3dYV6NGgO',1),
('susan','{bcrypt}$2a$10$WM5VdGryWM.RaOFWQH5Vh.4Cwug4PUvczvJvrySzWBgEZeW4EB19O',1);
````

Let's go ahead and modify the DDL for the password field.
So here we have `'password' char(68)` because the password column must be at least 68 characters wide,
like I mentioned earlier, `{bcrypt}` is eight characters, the encoded or encrypted password 60 characters.
Now we need to actually insert some users here with encrypted passwords.
So here's `john`, and here's his encrypted password of plaintext `fun123`.
And remember, the **bcrypt** is the encoding algorithm ID 
and that lets **Spring Security** know that the passwords are stored as encrypted passwords using **bcrypt**.
In previous examples, we made use of `{noop}` that was **plain text**, but here we're using **bcrypt**.
And now we just kind of repeat the process for `mary` and `susan`.
We simply add in the encrypted passwords for those users.

We'll start off by running the SQL script to set up the database tables for security.
Now, let's go ahead and swing over to **MySQL Workbench**, and we'll open up this SQL script.
Now, let's go ahead and click on the lightning bolt to execute this query.
We'll kind of slide over here to the left, and we'll simply do a refresh,
and we'll go ahead and look at our tables here.
So we have our authorities and our users.
I'll do a quick query here on the users table,
and we have those encrypted passwords for those users.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image67.png" alt="image67">
</div>

So this looks really good so far.
Allright, so lets kind of swing out here and let's run our application and test it out.
So our app is up and running.
Go to our login page.
We'll log in as `john`, `test123`.
And on purpose, we're sending the wrong password:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image68.png" alt="image68">
</div>

So this is going to fail, or it has failed.
Now, we know that john has a password of `fun123`.
That's the encrypted password in the database.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image69.png" alt="image69">
</div>

And excellent, so we're able to log into our application.
So our User ID of `john` and his role of `EMPLOYEE`.
So we have the **bcrypt** encryption working out just fine.

</div>

### [JDBC Authentication - Custom Tables]()
<div style="text-align:justify">

In this section, we will configure **Spring Security** to use custom tables.
So far in the sections, we have used the default **Spring Security** database schema.
And remember I said, you had to use the exact same table names and the exact same column names.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image70.png" alt="image70">
</div>

That works okay, but you may have thought internally that this is a bit restrictive.
Also, you may have thought, what if we have our own custom tables, like our own names?
For example, at your company, you may already have existing security tables,
and you want to configure **Spring Security** to use those tables.
Or you may work for a large multinational company, a large enterprise company,
and you have custom tables that are specific to your company.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image71.png" alt="image71">
</div>

For example, in the database diagram above, we have two tables `members` and `roles`.
This is an example of custom tables.
Nothing matches with a default **Spring Security** table schema.
You should be able to use any tables and any columns and that's what we'll do here in this section.
The only thing you need to do is tell **Spring Security** how to query your custom tables.
You need to provide a query to find a `user` by `name`.
And also you need to provide a query to find `authorities` or `roles` by the `username`.
So again, you can use any tables design that you want,
but you simply need to tell **Spring Security**, "_hey, this is how you find the given user_",
and "_here's how you find the roles for that given user_".
Here's the development process:

* create our custom tables with SQL
* update the **Spring Security** configuration
  - provide a query to find a `user` by `username`
  - provide a query to find `authorities`/`roles` by `username`

Alright, let's get started with step one of creating our custom tables with **SQL**.
We'll have a table for `members`.
This is where we'll store our `users`.
And note here the column names of `user_id`, `pw` for `password`, and `active`.
We'll also have another table called `roles`.
And we'll have the column names of `user_id` and `role`.

Now let's go ahead and swing over to MySQL Workbench.
And, I like to manually drop some of our previous tables.
So we have our old tables for `authorities` and `users`.
We'll run the appropriate SQL script to actually create the tables.

````sql
USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `members`
VALUES
('john','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('susan','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

````

Now the first thing that we'll do is we'll set up the drop table
for any previous tables for `roles` and `members`,
and then we'll move down here, and we'll create this table `members`.
This is where we actually store our `users`, but it's a custom table.
Has a custom table name and columns.
And then we'll move down here, and we'll insert data into the `members` table.
So we'll insert data for our three `members` or `users`.
`john`, `mary`, `susan`.
The default password is `fun123`.

````sql
--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
````

We'll go ahead and create the `roles` here.
Again, custom table name and custom columns.
And then finally here we do the insert on those roles for `john`, `mary`, `susan`.

Now in step two, we need to update our **Spring Security** configuration.
We need to modify the code where we created that `JdbcUserDetailsManager`,
and we have to provide the query how to find `users` by a giving `username`.

````java
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {

    JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // define query to retrieve a user by username
    theUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
    // define query to retrieve the authorities/roles by username
    theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

    return theUserDetailsManager;
}
````

We give the appropriate SQL query to access our custom `members` table.
And also we have to provide the query how to find `roles` by a given `username`.
We have to provide the appropriate SQL query to access the `roles` table.
Now the question mark here, that's basically a parameter placeholder.
The actual parameter value will be the `username` that's supplied during login.
As you can see, we can use any custom tables.
We simply have to tell **Spring Security** how to find the `users` and `roles` accordingly.

Let's go ahead and test this out.
Let's run it.
Go to our login page.
We'll log in as `john`, `test123`.
Now, we know that `john` has a password of `fun123`.
That's the encrypted password in the database.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image71.png" alt="image72">
</div>

And excellent.
So we're able to log into our application.
So our user ID of `john` and his role of `"EMPLOYEE"`.
The nice thing about this is that now we know that we have **Spring Security** setup using our own custom tables.
These are our custom table names and custom column names.
We simply configured **Spring Security** on how to find a user by the `username`,
and also the query on how to find the `authority` slash roles based on a given name.

</div>

## [Spring MVC Security by using JPA/Hibernate]()
<div style="text-align:justify">

In this section, we will learn how to secure a **Spring MVC Web App** with **Spring Security** using **JPA/Hibernate**.
So far, we have used basic "In Memory" and "JDBC Authentication" configurations with plain-text and BCrypt encryption
techniques to authenticate the users based on their roles. 
In terms of our application architecture, we'll actually reuse some code from our previous project. 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image03.png" alt="image73">
</div>

So we already created the code for service and repository. 
And in this section series, we'll redesign our database structure for user authorization,
and we'll focus on customization for users and their roles
as far as creating the new code for our **Entity**, **Dao**, **Service** and **Database** layers. 
In terms of project set up, we're going to extend our existing employee project and add database integration.
So we're going to add the `EmployeeController`, `EmployeeService`, `EmployeeRepository`, and the `Employee` entity.
Also, we're going to add the view side for the Web UI, 
which has `index.html`, `employee-form.html` and `list-employees.html`.
These are all available in one of our previous projects.
If you want to look at it in details for once more, 
you can click on this 
[link](https://github.com/korhanertancakmak/SPRING-BOOT/tree/master/07-spring-boot-spring-mvc-crud/README.md#crud-database-project---overview) 
to redirect you for the content.
And so earlier, we created all of this code from scratch. 
So here, in this section series, we'll simply copy and paste it and put it into this new project. 
So this will basically allow us to focus on updating our database tables for security and our `DemoSecurityConfig` file, 
and then we'll create new entities for users and roles and their service and dao implementations. 
Now, our development process as far as the big picture:

* Run a new database script that includes 3 tables instead of 2(which were `users` and `roles`)
* Update the entity, dao and service layers for security
* Update the Spring Security configuration file
* Update/Create controllers and view pages that will be required

As you can remember from previous sections, 
we have already 2 table database schemes for the security that has **one-to-many relationship**
as shown below in a diagram.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image70.png" alt="image74">
</div>

Which is equivalent to the `members` and `roles` tables 
that we can create with an update in the **Spring Security** configuration 
by providing queries one to find a `user` by `username` 
and one to find `authorities`/`roles` by `username`.
The `user` table contained user details, including `username`, `password`, and `enabled` status,
while the `authorities`/`roles` table mapped each username to a specific authority(role) 
such as `ROLE_EMPLOYEE`, `ROLE_MANAGER`, or `ROLE_ADMIN`.
This setup worked but had a limitation.
`roles` were tied directly to usernames, meaning that each user-role association required a row in authorities.
Now, In order to increase flexibility and enhance normalization in database design,
we'll introduce a new database setup that has 3 tables instead of 2.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image75.png" alt="image75">
</div>

In contrast, the new 3-table setup separates `roles` from `users` through the `role` table 
and introduces a **many-to-many relationship** using `users_roles`. 
The `user` table still holds essential user information, but each user now has a unique **id**, 
allowing for easier **role** assignments via **id** references in `users_roles`. 
The `role` table now independently defines each role by `id` and `name`, 
making it possible to assign or modify roles without affecting user data. 
Finally, `users_roles` acts as a bridge table, mapping `user_id` to `role_id`
to support multiple roles per user efficiently. 
This update allows for better scalability, 
as roles can now be easily assigned or removed without duplicating user details, 
and any number of roles can be added or updated independently, increasing data integrity and reducing redundancy.
Here the first of our sql scripts to create new `user` table:

````sql
USE `employee_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET foreign_key_checks = 1;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `user` (`username`,`password`,`enabled`)
VALUES 
('john','$2a$10$y3bVe1ZDCX3gQlcDxewL9u.A.BLP2T1Lb4Uf5EN4teYuoPWH2Ogj6',1),
('mary','$2a$10$zINYdUaeFJo4tMWRgs4A0.8ZIW2niKlUm2M3y4PATyYUTUMQdpbgC',1),
('susan','$2a$10$B.0mZlB1WjtBjol4dJ2wk.Z2Zaeny5H1aNfQTlTvQdzb2X9RVVVJe',1);
````

First, we set the database context to `employee_directory`. 
We then turn off `foreign_key_checks` temporarily, so we can drop any existing `user` and `role` tables 
without issues related to foreign key dependencies. 
After dropping these tables, we turn `foreign_key_checks` back on to enforce referential integrity again.
Next, we define the structure of the `user` table. 
This table stores each user’s information, including a unique `id`, `username`, encrypted `password`, and an `enabled` status. 
The `id` is an `AUTO_INCREMENT` **integer**, making it easier to manage relationships with other tables. 
The table is created with the **InnoDB engine**, supporting transactions and foreign keys.
And then, we insert data for the users `john`, `mary`, and `susan` into the `user` table. 
The password field stores **BCrypt** encrypted passwords, which were generated with an external tool. 
The default password for each user is `fun123`.
So the second sql script is here:

````sql
--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');
````

We then create the `role` table, which will store the various user roles. 
Each role has a unique `id` and a `name` field, such as `ROLE_EMPLOYEE` or `ROLE_MANAGER`. 
The `AUTO_INCREMENT` on `id` allows each role to have a unique identifier, 
ensuring simplicity and clarity when associating roles with users.
Next, we insert data into the `role` table, adding three roles: `ROLE_EMPLOYEE`, `ROLE_MANAGER`, and `ROLE_ADMIN`. 
This setup allows flexibility, as we can add new roles as needed without modifying the structure.
This is done.
Last sql script is here:

````sql
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3)
````

Here, we define the `users_roles` table, which establishes a **many-to-many relationship** between `user` and `role`. 
This table links each user to one or more roles through `user_id` and `role_id` columns, 
both of which are foreign keys referencing the `user` and `role` tables. 
We also define a composite primary key (`user_id`, `role_id`) to ensure that each user-role pair is unique.
And then, we re-enable `foreign_key_checks` to ensure referential integrity is maintained from this point on.
Finally, we populate the `users_roles` table with data, assigning each user to various roles. 
Here, user `john` (ID 1) has `ROLE_EMPLOYEE` (ID 1), `mary` (ID 2) has `ROLE_EMPLOYEE` and `ROLE_MANAGER`, 
and `susan` (ID 3) has all three roles. 
This approach allows a flexible and scalable way to manage user roles.

Let's open MySQL Workbench and run these script as in 1 sql script file, and let's check our `user` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image76.png" alt="image76">
</div>

Here we have our users.
And let's query the `role` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image77.png" alt="image77">
</div>

Here we have our roles.
Finally, let's query the `users_roles` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image78.png" alt="image78">
</div>

Alright. 
Now we have done setting up the database for the second stage.
Now we'll need to create new classes for **User** and **Role** entities.
Actually, we can use any name for these entities, but we'll keep it simple.
Alright, this is what we have already done for several times in previous sections. 

````java
package com.luv2code.springboot.thymeleafdemo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    // define constructors

    public User() {
    }

    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    // define getter/setter

    // define toString
}
````

So firstly, I write down some comments to track myself.
We add the `@Entity` annotation which specifies that this class is a **JPA** entity, 
meaning it represents a table in the database. 
And then we add `@Table(name = "user")` that defines the table name as `user` in the database. 
This ensures that the **User** class maps directly to the `user` table we created in our SQL setup.
And then we create our first field, `id`, and we add the `@Id` annotation marks `id` as the primary key of this entity. 
The `@GeneratedValue(strategy = GenerationType.IDENTITY)` annotation sets up an auto-incremented identity strategy, 
meaning `id` values will be generated by the database automatically. 
And, `@Column(name = "id")` links this field to the `id` column in the `user` table, ensuring correct mapping.
The next two fields, `userName` and `password`, 
are mapped to the `username` and `password` columns in the `user` table using `@Column`. 
This aligns the properties with their respective columns, making the data accessible and modifiable in the application.
And then, we define two constructors. 
A no-argument constructor and a parameterized constructor.
The no-argument constructor is required by **JPA**, 
enabling the framework to instantiate **User** objects when fetching data from the database. 
The parameterized constructor allows creating new **User** instances with specified `userName` and `password`.
The class includes getters and setters for each field (not shown for brevity). 
These methods are essential for encapsulating data and allowing controlled access to **User** properties. 
They also facilitate mapping data to and from the database.
Finally, the `toString` method (not shown for brevity) is commonly added to format and display **User** data as a string. 
This is particularly useful for debugging and logging purposes.
And the same for **User** entity:

````java
package com.luv2code.springboot.thymeleafdemo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    // define constructors
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // define getter/setter
  
    // define toString
}
````

Here it's pretty much similar to the **User** entity with declarations for fields and getter/setter with `toString()` methods.

So now, we'll move on updating **DAO** layer for **User** and **Role** entities. 
For now, we have `EmployeeRepository` that extends `JpaRepository`, 
which provides built-in **JPA** functionalities like **CRUD** operations,
sorting, and pagination for the **Employee** entity.
So here our **UserDao** interface:

````java
package com.luv2code.springboot.thymeleafdemo.dao;
import com.luv2code.springboot.thymeleafdemo.entity.User;

public interface UserDao {
    User findByUserName(String userName);
}
````

However, `UserDao` can  not extend `JpaRepository`
and instead we'll directly define a single method, `findByUserName`, to retrieve a **User** by their `username`.
Since **User** typically represents a security-focused entity (for login and role assignment),
a custom **DAO** might help control user-related operations more securely
or integrate with a specific authentication provider.
And now, we need to provide a concrete implementation of the **UserDao** interface, 
allowing for custom database interaction logic with the **User** entity.

````java
@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String theUserName) {

        // retrieve/read from database using username
        TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:uName and enabled=true", User.class);
        theQuery.setParameter("uName", theUserName);

        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }
}
````

The purpose here is to retrieve a **User** object based on the `username`, 
but unlike typical **JpaRepository** methods, this approach uses a custom query. 
The **EntityManager** is injected to directly manage the database operations, 
offering more control over the query process. 
The `findByUserName` method defines a **TypedQuery** to select a `user` 
where the `userName` matches the given parameter 
and the `enabled` flag is set to `true`, indicating active users. 
The query parameter, `uName`, is set with the given `username`, 
and an attempt is made to retrieve a single result. 
If no match is found, an exception is caught, and `null` is returned. 
This approach allows for fine-grained control over user retrieval, 
which is useful for custom authentication or user management tasks.

Now let's update the service layer starting with creating **UserService** interface.
The **UserService** interface defines the service layer for user-related operations within the application,
serving as an intermediary between the data access layer (**UserDao**) and the controller layer.

````java
import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  public User findByUserName(String userName);
}
````

By extending `UserDetailsService`, this interface is set up to integrate with **Spring Security**, 
allowing it to provide user authentication details needed for security configurations.
The `findByUserName` method is defined to locate a **User** entity based on the provided `username`, 
which is useful for retrieving specific user details for authentication purposes. 
Implementing `UserDetailsService` means that the service must also implement the `loadUserByUsername` method, 
required by **Spring Security**, enabling it to retrieve user information as part of the application's authentication flow.
`loadUserByUsername` method must return a **UserDetails** type, rather than a **User** entity directly, 
so it requires adaptation of the **User** entity to meet the **UserDetails** interface requirements.
This approach helps maintain separation of concerns 
by centralizing user-related operations during the login process within the service layer.
And then we implement the methods to look up a user by username.
The **UserServiceImpl** class is an implementation of the **UserService** interface,
providing the core user-related business logic for the application.
It integrates **Spring Security**'s **UserDetailsService** for authentication
and leverages the custom `UserDao` to interact with the database.

````java
package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.UserDao;
import com.luv2code.springboot.thymeleafdemo.entity.Role;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userDao.findByUserName(userName);
    }

    // ...
}
````

The **UserServiceImpl** is annotated with `@Service`, 
indicating it’s a service layer component, 
which is instantiated and managed by **Spring**. 
The class has two main methods.
First, `findByUserName` calls `userDao.findByUserName` to check 
if a user exists in the database with the given `username`. 
This method provides flexibility for fetching the **User** entity directly, 
useful for other parts of the application outside of authentication. 

````java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userDao.findByUserName(username);
    if (user == null) {
        throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
}

private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {     
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
}
````

The `loadUserByUsername` method, which overrides **UserDetailsService**'s method, is specifically for **Spring Security**. 
It retrieves a **User** from the database and converts it into a **UserDetails** object, 
which **Spring Security** uses to handle authentication and authorization. 
If the user isn't found, a **UsernameNotFoundException** is thrown with a clear error message.
The `mapRolesToAuthorities` method handles the conversion of the user’s roles (represented by the **Role** entity) 
into a collection of `GrantedAuthority` objects. 
These authorities represent the user’s permissions in a format **Spring Security** requires, 
ensuring that each role (such as "`ROLE_EMPLOYEE`" or "`ROLE_ADMIN`") is wrapped as a `SimpleGrantedAuthority`. 
The use of **Java Streams** in `mapRolesToAuthorities` provides a streamlined way 
to transform each role into the required authority type. 
Overall, `UserServiceImpl` effectively bridges the application’s user model with **Spring Security**’s requirements, 
ensuring seamless user authentication and role-based authorization.
To enable the `mapRolesToAuthorities` method in the **UserServiceImpl** class to work correctly, 
we need to add a `getRoles` method to the **User** entity that retrieves the roles associated with a user. 
This assumes a relationship between the **User** and **Role** entities, 
typically a many-to-many relationship, where a user can have multiple roles.
Here's how you can implement the `getRoles` method in the **User** entity class:

````java
import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    // ... other fields declerations

    // Collection to store user's roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles; 

    // ... constructors

    // define getter/setter for roles
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    // ... other getter/setter for other fields
}
````

To integrate a `getRoles` method in the **User** entity that returns a collection of roles, `Collection<Role>`, 
we'll first need to ensure that your **User** class maintains a relationship with the **Role** entity. 
Typically, a user can have multiple roles, so we can use a `Collection<Role>` to store these roles.
We define a `@ManyToMany` relationship between **User** and **Role**, 
indicating that a user can have multiple roles and a role can belong to multiple users.
The `@JoinTable` annotation specifies the join table (`user_roles`) that maps users to their roles, 
along with the appropriate join columns.
The `roles` field is declared as a `Collection<Role>`, which can be used to hold multiple roles associated with a user.
The `fetch = FetchType.EAGER` parameter ensures that roles are fetched from the database eagerly 
when a user is retrieved, so they are available immediately.
The `getRoles` method returns the roles collection, which is utilized in the `mapRolesToAuthorities` method of `UserServiceImpl`.
A corresponding `setRoles` method is provided to allow roles to be set.
With these modifications, the **User** entity can now support 
the retrieval of roles necessary for the `mapRolesToAuthorities` method, 
fulfilling the application's requirements for role-based access control.
So, updating service layer task is done.

Next, we need to update the **Spring Security** configuration file.
Let's open most recent `SecurityConfig.java` in the `security` package:

````java
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return theUserDetailsManager;
    }
}
````

To refresh our memories, here we configure the security filters for incoming **HTTP** requests.
`filterChain` method accepts an instance of **HttpSecurity**, 
which allows for configuring security aspects of **HTTP** requests.
`requestMatchers("/")` specifies that the root URL (`/`) is accessible only to users with the role `EMPLOYEE`.
`requestMatchers("/leaders/**")` restricts access to any URL under `/leaders` to users with the role `MANAGER`.
`requestMatchers("/systems/**")` restricts access to any URL under `/systems` to users with the role `ADMIN`.
`anyRequest().authenticated()` requires authentication for any other requests that do not match the previous patterns.
The `formLogin` method specifies that a custom login page is available at `/showMyLoginPage`.
The `loginProcessingUrl` method indicates the URL to which the login form will be submitted (`/authenticateTheUser`).
`permitAll()` allows all users (authenticated or not) to access the login page.
The `logout()` method allows all users to log out without restrictions.
The `exceptionHandling()` method specifies that if a user attempts to access a page 
they are not authorized to view, they will be redirected to `/access-denied`.
`http.build()` builds and returns a `SecurityFilterChain` that contains the configurations applied.
That's how we can restrict accesses to the paths based on user roles.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image39.png" alt="image79">
</div>

And so, our homepage in the center, we actually had those **href** links.
And so, our code was actually to show those links to those given pages.
And then we created the supporting controller code and view pages.
But, we don't need those view pages that are restricted URLs based on user roles, anymore.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image43.png" alt="image80">
</div>

Instead, we'll implement role-based access control. 
We need to update the filterChain method to apply different access restrictions to each of the CRUD endpoints.
The root endpoint ("`/`") is publicly accessible to all users, allowing anyone to view the homepage. 
Additionally, the signup and login endpoints ("`/signup`" and "`/login`") are also available to everyone, 
enabling new users to register and existing users to log in without restriction.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/employees/list").authenticated()
                    .requestMatchers("/employees/showFormForAdd").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/employees/showFormForUpdate").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/employees/save").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/employees/save").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/employees/delete").hasRole("ADMIN")
                    .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

    return http.build();
}
````

For authenticated users, the endpoint for listing employees ("`/employees/list`") 
requires the user to be logged in, regardless of their role. 
This ensures that only authenticated users can view the employee list. 
However, for actions related to adding and updating employee records, 
such as the endpoints "`/employees/AddNewEmployee`", "`/employees/updateEmployee`", and "`/employees/save`", 
we restrict access to users who possess either the "`MANAGER`" or "`ADMIN`" roles. 
This approach ensures that only authorized personnel can modify employee data, 
thus maintaining data integrity and security.
The delete operation, available at "`/employees/delete`", is restricted solely to users with the "`ADMIN`" role. 
This limitation is critical, as it prevents unauthorized users from deleting employee records, 
which is a sensitive action that should only be performed by designated administrators.
Overall, this security configuration effectively enforces role-based access control, 
ensuring that each endpoint is secured according to the permissions assigned to different user roles within the application.
Our second method inside the `SecurityConfig.java` file is `userDetailsManager()`.

````java
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {

    JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    theUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
    theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
    return theUserDetailsManager;
}
````

Given that the `userDetailsManager()` method sets up a `JdbcUserDetailsManager`, 
which retrieves user details directly from a database, 
we may not need the earlier methods related to user authentication 
if they duplicate functionality provided by the `UserService` we implemented.
So we can delete this method instead, and write these new 2 methods:

````java
@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

//authenticationProvider bean definition
@Bean
public DaoAuthenticationProvider authenticationProvider(UserService userService) {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userService); //set the custom user details service
    auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
    return auth;
}
````

This is based on our `UserService`. 
It provides access to the database for creating users. 
We provide the `passwordEncoder` for bcrypt.
We'll also use the `UserService` and `UserDao` to check if a user exists.
The `UserDao` has the low-level code for accessing the security database.
The `authenticationProvider()` method sets up `DaoAuthenticationProvider`, 
which will use the `userService` to load user details. 
By integrating `UserService`, it ensures that the application pulls in custom user data, including roles, 
and checks the password with `BCryptPasswordEncoder`. 
This provides a secure and custom setup for validating credentials, so we’re fully covered for authentication.
With `BCryptPasswordEncoder()` defined in `passwordEncoder()`, passwords are hashed, 
and **Spring Security** will use this encoder to match incoming login attempts against the stored hashes. 
This is a key part of ensuring secure authentication and storing passwords safely.
The `filterChain` method in `SecurityFilterChain` configures which roles can access specific URLs, 
ensuring only users with the right roles (`EMPLOYEE`, `MANAGER`, `ADMIN`) can reach certain areas of the application. 
This role-based access control is what we need for authorization.
So the configuration we've implemented should be sufficient for handling both secure authentication 
and robust authorization in the app.

Now, we finally at the final stage that is updating the controllers and view pages 
or creating the new ones if we need more.
Let's open our one and only current EmployeeController file in this project:

````java
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployee = employeeService.findAll();
        theModel.addAttribute("employees", theEmployee);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        Employee theEmployee = employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}
````

The **EmployeeController** is mapped to the `/employees` URL, 
and each method within it defines specific actions for different operations. 
The constructor injects an **EmployeeService**, enabling the controller to access employee-related services. 
The `listEmployees()` method, mapped to `"/employees/list"`, retrieves all employees from the service layer 
and adds them to the model to be displayed in the `"employees/list-employees"` view. 
The `showFormForAdd()` method prepares a blank **Employee** object and adds it to `theModel`, 
directing the user to the `"employees/employee-form"` view for adding new employees. 
In the `saveEmployee()` method, a **POST** request saves the provided **Employee** object to the database 
and redirects to the `"/employees/list"` view upon completion. 
The `showFormForUpdate()` method retrieves an employee by ID, `theId`, 
pre-fills the form for editing, and directs to `"employees/employee-form"`. 
Finally, the delete method deletes an employee by ID and then redirects back to the `"/employees/list"` view. 
This structure efficiently separates tasks related to listing, adding, updating, and deleting employees, 
while handling each request methodically for clear user interactions.
I'll change the names of the end points(methods) to increase readability of the code.

````java
// add mapping for "/list"
@GetMapping("/list")
public String listEmployees(Model theModel) {
    // get the employees from db
    List<Employee> theEmployee = employeeService.findAll();
    // add to the spring model
    theModel.addAttribute("employees", theEmployee);
    return "employees/list";
}
@GetMapping("/addNewEmployee")
public String addNewEmployee(Model theModel) {

    // create model attribute to bind form data
    Employee theEmployee = new Employee();
    // add to the spring model
    theModel.addAttribute("employee", theEmployee);
    return "employees/addNewEmployee";
}
@PostMapping("/save")
public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

  // save the employee
  employeeService.save(theEmployee);
  // use a redirect to prevent duplicate submissions
  return "redirect:/employees/list";
}
@GetMapping("/updateEmployee")
public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel) {

  // get the employee from the service
  Employee theEmployee = employeeService.findById(theId);
  // set employee in the model to pre-populate the form
  theModel.addAttribute("employee", theEmployee);
  // send over to our form
  return "/employees/addNewEmployee";
}
@GetMapping("/delete")
public String delete(@RequestParam("employeeId") int theId) {

  // delete the employee
  employeeService.deleteById(theId);
  // redirect to the /employees/list
  return "redirect:/employees/list";
}
````

These are the minor changed ones based on the current **EmployeeController** file.
We'll configure the view pages, but let's first create our **MainController** to control our main page, login, logout, 
signup and access-denied pages.

````java
package com.luv2code.springboot.thymeleafdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "home";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
````

So this class serves as a central point for handling various **HTTP GET** requests within the application. 
It is annotated with `@Controller`, indicating that it is a **Spring MVC** controller. 
The class defines several methods, each mapped to specific URL patterns using the `@GetMapping` annotation. 
The `home` method is linked to the root URL ("`/`") and returns the "`home`" view, 
which is typically the landing page of the application. 
The `signup` method handles requests to the "`/signup`" URL, returning the "`signup`" view for user registration. 
Similarly, the `login` method processes requests to the "`/login`" URL, directing users to the "`login`" view for authentication. 
The `logout` method is also mapped to a URL and redirects users back to the "`home`" view 
after they log out, ensuring a seamless user experience. 
Lastly, the `accessDenied` method is invoked when users attempt to access restricted resources without proper authorization, 
returning the "`access-denied`" view that informs them of their lack of access rights. 
Overall, this controller manages the navigation flow of the application 
by linking specific routes to their corresponding views, facilitating user interactions with the system.
So, I kept this basic again.
Let's work with our view pages now.
We create a new html file for our `home` view.
I'll divide the "`<head>`" and "`<body>`" tags to get more focused on the subjects.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
<head>
  <title>Home Page</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
````

In the "`<head>`" section of the **HTML** document, 
several important elements are defined to set up the page's basic structure. 
The `html` tag includes namespaces for **Thymeleaf** and **Spring Security**. 
The "`<title>`" tag sets the title of the page as "`Home Page`" which will appear in the browser tab. 
The "`<meta>`" tags provide essential information about the character set and responsive design, 
ensuring the page is rendered correctly on various devices. 
The page also links to an external **Bootstrap** stylesheet for responsive design and styling, 
enhancing the visual appearance of the application.
So we'll have a navigation bar and this will be almost the same on all other pages as design.

````html
<body>
  <nav class="navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" th:href="@{/}">Employee Application</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a th:href="@{/}">Home</a></li>
        <li sec:authorize="isAuthenticated()"><a class="nav-link" th:href="@{/employees/list}">Employees</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="display: flex; align-items: center;">
        <li sec:authorize="!isAuthenticated()"><a th:href="@{/signup}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li sec:authorize="!isAuthenticated()"><a th:href="@{/login}"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li sec:authorize="isAuthenticated()">
          <form action="@{/login}" th:action="@{/logout}" method="POST">
            <button type="submit" class="btn btn-danger">
              <span class="glyphicon glyphicon-log-out"></span> Logout
            </button>
          </form>
        </li>
      </ul>
    </div>
  </nav>
</body>
````

So we begin with a navigation bar, "`<nav>`", that serves as the primary interface for user interaction.
The `navbar` is created using **Bootstrap** classes,
offering a clean and functional layout that includes a brand link to the home page
and navigation links for both unauthenticated and authenticated users.
The links adjust their visibility based on the user's authentication status, controlled by the `sec:authorize` attribute.

````html
<head>
  <!-- title, meta and links -->
  <style>
    .navbar-nav.navbar-right button {
        border: none;
        background: transparent;
        padding: 16px 8px;
        color: white;
    }
    .navbar-nav.navbar-right button:hover {
        background: rgba(255, 0, 0, 0.8);
    }
  </style>
</head>
````

Additionally, custom CSS is included to modify the appearance of the logout button, 
which is visible if the user logged in, within the navbar, providing specific hover effects to enhance user interaction.

````html
<body>
    <!-- navbar -->

    <div class="container mt-5">
      <h1>Welcome to the Employee Management System!</h1>
      <p>This is a simple application to manage employees.</p>

      <!-- Welcome message for authenticated users -->
      <div sec:authorize="isAuthenticated()">
        <div class="alert alert-success">
          Welcome, <span sec:authentication="name"></span>!
        </div>
      </div>
    </div>
</body>
````

The main content area is wrapped in a "`<div>`" with a class for styling,
where a welcome message and brief application description are displayed. 
If the **user** is authenticated, a personalized welcome message is shown, 
incorporating **Thymeleaf** syntax to dynamically insert the user's name. 
This setup provides a user-friendly interface for navigating the employee management system 
while maintaining access control based on user roles.
So our login page will have almost the same "`<head>`" tag with the `home` page.
Just one change will be the title, I'll change it to "`Login Page`".
And for the body tag, we'll have the same navbar but, I'll use the logic that 
a user can see this page only if he/she has not logged in yet.
So we don't need a logout button here.

````html
<nav class="navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" th:href="@{/}">Employee Application</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a th:href="@{/}">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a th:href="@{/signup}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a th:href="@{/login}"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
````

The "`<nav>`" element, assigned the `class=navbar-inverse`, creates a dark-themed navigation bar. 
Within the `<div class="container-fluid">`, which allows for a full-width layout, 
the `<div class="navbar-header">` contains a brand link represented by an anchor tag `<a>` 
that utilizes **Thymeleaf**'s `th:href` attribute to dynamically link back to the home page of the application. 
The text "`Employee Application`" serves as the title of the application, 
making it clear to users what system they are interacting with. 
This brand link is important for navigation, allowing users to easily return to the main page.
The navigation bar is further organized into two unordered lists ("`<ul>`"), 
which group the links for better organization and styling. 
The first list contains a single item that links back to the home page, providing straightforward navigation for users. 
The second list, aligned to the right of the navbar, includes links for "`Sign Up`" and "`Login`," 
making it easy for new and returning users to access these critical functionalities. 
Each link in this section features a corresponding glyphicon icon, 
enhancing the visual clarity and appeal of the options presented. 
Overall, this navbar design facilitates user navigation within the employee management system, 
ensuring a user-friendly experience, especially for those accessing the login functionalities.
So for the rest of our login page will be a form.

````html
<div class="container">
  <div style="margin-top:20px;" class="col-md-5 col-md-offset-3 well">
    <div class="card border-info">
      <div class="card-body">
        <div class="card-text">
          <!-- Login Form -->
          <form action="#" th:action="@{/authenticateTheUser}" method="POST" class="form-horizontal">
            <!-- Place for messages: error, alert etc ... -->
            <div class="form-group">
              <div class="col-xs-15">
                <div>
                  <!-- Check for login error -->
                  <div th:if="${param.error}">
                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                      Invalid username and password.
                    </div>
                  </div>
                  <!-- Check for logout -->
                  <div th:if="${param.logout}">
                    <div class="alert alert-success col-xs-offset-1 col-xs-10">
                      You have been logged out.
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- User name -->
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
              <input type="text" name="username" placeholder="username" class="form-control" />
            </div>
            <!-- Password -->
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              <input id="password" type="password" class="form-control" name="password" placeholder="Password">
            </div>

            <!-- Login/Submit Button -->
            <div style="margin-top: 10px;" class="form-group">
              <div class="col-sm-6 controls">
                <button type="submit" class="btn btn-success">Login</button>
              </div>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>
</div>
````

The outer "`<div class="container">`" serves as a wrapper, 
providing a responsive layout that adapts to different screen sizes. 
Inside this container, another "`<div>`" is styled with `col-md-5` and `col-md-offset-3` classes 
to center the form in the middle of the page while the well class adds a subtle background and padding, enhancing visual appeal. 
The nested "`<div class="card border-info">`" creates a card-like structure, 
further organizing the login elements and ensuring a clean presentation. 
The "`<div class="card-body">`" houses the main content of the card, 
while `<div class="card-text">` delineates the area for the actual form, 
promoting better readability and separation of concerns within the layout.
Within the form, which is marked by the "`<form>`" tag and configured to **POST** data 
to the "`/authenticateTheUser`" endpoint, 
there is space for displaying feedback messages related to user actions. 
The "`<div class="form-group">`" contains conditional alerts that provide immediate user feedback, 
showing an error message when login fails and a success message when the user has logged out, 
using **Thymeleaf**'s `th:if` attribute for dynamic content rendering that we already used before. 
The form itself includes input fields for the username and password, 
each accompanied by a glyphicon to enhance the user interface. 
The use of **Bootstrap**'s `input-group` class allows for seamless integration of these icons with the input fields, 
ensuring they are visually aligned and accessible. 
Finally, the login button is styled with the `btn-success` class to make it visually distinct and inviting, 
encouraging users to submit their credentials. 
Overall, this structure effectively balances functionality with aesthetic design, 
making it an intuitive login interface for the application.
We'll look at signup view page at the next section,
but for now we'll leave it as a blank page with the same navbar.
Let's update our "/access-denied" view page:

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
<head>
    <title>Access Denied</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        .navbar-nav.navbar-right button {
            border: none;
            background: transparent;
            padding: 16px 8px;
            color: white;
        }
        .navbar-nav.navbar-right button:hover {
            background: rgba(255, 0, 0, 0.8);
        }
    </style>
</head>
<body>

    <nav class="navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/}">Employee Application</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a th:href="@{/}">Home</a></li>
            <li sec:authorize="isAuthenticated()"><a class="nav-link" th:href="@{/employees/list}">Employees</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="display: flex; align-items: center;">
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/signup}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/login}"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li sec:authorize="isAuthenticated()">
                <form action="@{/login}" th:action="@{/logout}" method="POST">
                    <button type="submit" class="btn btn-danger">
                        <span class="glyphicon glyphicon-log-out"></span> Logout
                    </button>
                </form>
            </li>
        </ul>
    </div>
</nav>

    <h2 class="text-center">Access Denied - You are not authorized to access this resource.</h2>
</body>
</html>
````

This is basically the same with our old access-denied code.
I'll change the title, and I'll add the same navbar with the home's one.
And that's it.
And now our list view page.
That's the page all CRUD functions and end points of **EmployeeController** in.
It must have the same "`<head>`" and "`navbar`" with the home's one.

````html
<div class="container">
  <h3>Employee Directory</h3>
  <!-- Welcome message for authenticated users -->
  <div>
    <div class="alert alert-success">
      Welcome, <span sec:authentication="name"></span>!
    </div>
  </div>
  <!-- EmployeeAdd button only available for MANAGER and ADMIN roles-->
  <div sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">
    <a th:href="@{/employees/addNewEmployee}" class="btn btn-primary btn-sm mb-3">
      Add Employee
    </a>
  </div>
  
  <!-- employee list table -->
</div>
````

So I'll add the welcome message to here just before the table inside the container div element.
This welcome message greets authenticated users by displaying their username 
through **Thymeleaf**'s `sec:authentication` feature.
The document conditionally renders an "`Add Employee`" button for users with `ADMIN` or `MANAGER` roles, 
allowing them to navigate to the employee addition form.

````html
<div class="container">
  <!-- headers and welcome message for authenticated users with add button -->

  <table class="table table-bordered table-striped">
    <thead class="table-dark">
    <tr>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
      <th sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">Action</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="tempEmployee : ${employees}">
      <td th:text="${tempEmployee.firstName}" />
      <td th:text="${tempEmployee.lastName}" />
      <td th:text="${tempEmployee.email}" />
      <!-- Add update button link -->
      <td sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">
        <a th:href="@{/employees/updateEmployee(employeeId=${tempEmployee.id})}"
           class="btn btn-info btn-sm">
          Update
        </a>
        <!-- Add "delete" button/link -->
        <a sec:authorize="hasRole('ADMIN')"
           th:href="@{/employees/delete(employeeId=${tempEmployee.id})}"
           class="btn btn-danger btn-sm"
           onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
          Delete
        </a>
      </td>
    </tr>
    </tbody>
  </table>
</div>
````

This well-structured table displays employee information, including `firstName`, `lastName`, and `email` addresses. 
Each row is generated dynamically using **Thymeleaf**’s `th:each` directive to iterate over a list of employees. 
For users with `ADMIN` or `MANAGER` roles, action buttons for updating and deleting employee records are made available. 
The delete button includes a confirmation dialog to prevent accidental deletions, 
reinforcing a user-friendly and secure interface. 
Overall, this document effectively combines security, functionality, and a clean layout, 
providing users with essential tools for managing employee data while ensuring role-based access control.

Let's run the application now, and test it out:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image81.png" alt="image81">
</div>

And yes, this is our home page.
Let's open try the links in the navigation bar.
When I open "`Employee Application`" and "`Home`" links, we get redirected to home page again.
Let's try "`login`" page, because we know "`signup`" page will be done later.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image82.png" alt="image82">
</div>

Here, our login page.
Let's try to log in with the user details, `john`, `fun123`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image83.png" alt="image83">
</div>

And, succeed.
Our welcome message, and "`Employees`" list in the navbar now.
Also, "`signup`" and "`login`" links are disappeared.
Let's go try to log out first.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image84.png" alt="image84">
</div>

That's good.
We got the log-out message and redirected again to the login page.
Let's try the same user to log in to see the Employee page now.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image85.png" alt="image85">
</div>

Yes, succeed.
There is no CRUD functions available for `john` who has the role of `"EMPLOYEE"`.
Let's try `mary` which has the role of "MANAGER".
So we log out, and log in with `mary` and `fun123` again.
And we open the employee list:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image86.png" alt="image86">
</div>

And, yes.
That's the "`Add Employee`" and "`Update`" buttons with the `action` column here.
Those are active for only "`MANAGER`" and "`ADMIN`" roles.
So our last CRUD function should be available for "`susan`".
Because she is the "`ADMIN`".
Let's try her account.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image87.png" alt="image87">
</div>

That's it.
We have the last CRUD function, that is "`delete`", is here.
So we're good so far.
Now we'll update the CRUD functions view pages now.
Firstly I'll create a new html file named `addNewEmployee.html`.
That has the same navbar and similar head tag except the styling, 
that's why I will omit that parts from the code to prevent code duplication here.
All we got in the body tag are a navbar and a form container.

````html
<div class="container">
  <h3>Add New Employee</h3>
  <hr>
  <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST"
        style="margin: 0 auto; width: 50%; background-color: #6c757d; padding: 20px; border-radius: 8px;">
    <p class="h4 mb-4">Employee Details</p>
            
    <!-- firstName input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">First name:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
      </div>
    </div>

    <!-- lastName input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">Last name:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
      </div>
    </div>

    <!-- email input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">Email:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
      </div>
    </div>
    
    <!-- submit button -->
    <button type="submit" class="btn btn-info col-2" style="color: black; border: none;">Save</button>
  </form>
  <hr>
  <a th:href="@{/employees/list}">Back to Employee List</a>
</div>
````

So here our form container.
The important thing is here the input fields. 
We have `firstName`, `lastName` and `email`.
If we create a new **Employee** object, 
then our save method in the service layer will have a `null` **Employee** id.
That's the important thing, because when we look at the update view form:

````html
<div class="container">
  <h3>Update The Employee</h3>
  <hr>
  <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST"
        style="margin: 0 auto; width: 50%; background-color: #6c757d; padding: 20px; border-radius: 8px;">
    <p class="h4 mb-4">Employee Details</p>
  
    <!-- Id input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">ID:</label>
      <div class="col-sm-10">
        <p class="form-control-static"><span th:text="*{id}"></span></p>
      </div>
    </div>
  
    <!-- firstName input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">First name:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
      </div>
    </div>
  
    <!-- lastName input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">Last name:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
      </div>
    </div>
  
    <!-- email input field -->
    <div class="form-group d-flex align-items-center">
      <label class="control-label col-sm-2">Email:</label>
      <div class="col-sm-10">
        <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
      </div>
    </div>
  
    <!-- submit button -->
    <button type="submit" class="btn btn-info col-2">Save</button>
  
  </form>
  <hr>
  <a th:href="@{/employees/list}">Back to Employee List</a>
</div>
````

The only different thing in the form is the additional "`Id`" field.
That's the only difference here, and we have to submit it in the form,
otherwise the form will call `save` method in the controller, 
and it will call `save` method in the service layer.
And if the upcoming **Employee** object has no `id` field or has an `id` field with a `null` value,
it will create a new **Employee** rather than updating it.
So here, firstly we used a "`<p>`" tag to be able to show it on the form to the user.
But it will not be submitted by the form to the controller, that's why we add a hidden "`<input>`" tag.

````html
<!-- Id input field -->
<div class="form-group d-flex align-items-center">
  <label class="control-label col-sm-2">ID:</label>
  <div class="col-sm-10">
    <p class="form-control-static"><span th:text="*{id}"></span></p>
    <!-- This input will NOT shown on the form to the user, it's just for submitting the id field to handle the update not create! -->
    <input type="hidden" th:field="*{id}" />    
  </div>
</div>
````

Alright, now let's try to open "`Add Employee`", "`Update`" and "`delete`" links respectively.
Firstly we try "`Add Employee`":

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image88.png" alt="image88">
</div>

Here, it is.
Our new **Bootstrap** code result shows a new styling.
That looks something better now.
Let's add a new employee now.
I'll add myself, `Korhan`, `Cakmak` and `korhancakmak@luv2code.com`.
After I save it:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image89.png" alt="image89">
</div>

And, that's we added a new employee.
Let's click on the update button now.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image90.png" alt="image90">
</div>

That's the record of the employee we just added.
We can change the details and save it.
Let's add my second name to the name field and email:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image91.png" alt="image91">
</div>

That's it.
It did not create a new **Employee**.
It did update the existing one.
So let's try to delete it.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image92.png" alt="image92">
</div>

We know this onClick javascript inline code does this,
a confirmation for the user.
Let's say yes:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/08-spring-boot-spring-mvc-security/images/image93.png" alt="image93">
</div>

And yes, succeed.
This is it.
What we have done so far, is actually combine 2 separate projects into 1 here.
We got **REST API Employee** app, and we got **Spring MVC CRUD** functionalities.
We used some **HTML** and **CSS** with of course **Thymeleaf**, and finally we added 
**Spring Security** with **JPA/Hibernate** to this combination.

</div>

### [Spring MVC Security - Registration User]()
<div style="text-align:justify">

Before we take care of the registration function and its view page.
We'll have a change in our user class for a specific purpose.
Remember that right after the user log in, the page directs to home-page.
Because we don't have any configurations for post-login behavior in the **Spring Security** configuration file.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/employees/list").authenticated()
                    .requestMatchers("/employees/AddNewEmployee", "/employees/updateEmployee", "/employees/save").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/employees/delete").hasRole("ADMIN")
                    .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

    return http.build();
}
````

Here we have all restrictions for the end points and `formLogin` and `loginProcessingUrl` methods.
Right now, **Spring Security** provides ways to access basic information about the authenticated user, 
such as username and roles, without needing to manually store anything in the session. 
Using expressions like `sec:authentication="name"` with **Thymeleaf** 
or directly accessing `SecurityContextHolder.getContext().getAuthentication()` are efficient for many common scenarios.
However, there are certain situations where storing more detailed user data in the session can still be advantageous.
Here's why we may need to add a custom `successHandler`:

* Minimizing database queries: If we frequently need detailed information about the logged-in user(email, full profile 
information or preferences) across multiple views or controllers, 
storing this data in the session can help reduce database calls. 
While UserService layer can fetch user data as needed, 
accessing session data is typically faster since it avoids repeated
database queries for each page or action.
* Customizing views with detailed data: In cases where we need more than just the `username` or `role`, 
like full profile details, storing that in the session allows easy access 
throughout the application without additional lookups.
* Handling more complex data models: For applications with rich user profiles 
or complex data structures associated with each user, storing a full user object in the session can simplify access
and improve performance.
This approach is common in larger systems where user data includes more than just `username` and `role` 
but also custom permissions, organization details, or specific business logic associated with the user.
* Reducing authentication context lookups: `SecurityContextHolder` provides only a limited view of the user’s identity, 
usually just username and roles. 
When you need more user attributes than are available in the authentication object, 
storing the full user profile in the session allows you to bypass re-fetching data 
that is not in the security context.

So the point is here, If your application is small and does not frequently need detailed user attributes 
beyond `username` and `role`, you’re correct that it’s often more efficient 
to simply use `SecurityContextHolder` or `sec:authentication` in **Thymeleaf** 
and fetch data from **UserService** only when absolutely necessary.
So if the advantages above don’t apply to your use case, 
using **UserService** directly without session storage is perfectly fine and often simpler.

So we want to implement custom successHandler into our **Spring Security Configuration** file.
But before doing that, we need to have a more complex user table in the database 
and validations for the fields in User entity class to make the application much complexer.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image94.png" alt="image94">
</div>

So this is what we have so far. 
Let's rewrite the user table sql script:

````sql
USE `employee_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET foreign_key_checks = 1;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
````

Here the only differences are the new columns that are, `first_name`, `last_name` and `email`.
So we need to insert these with their sample values:

````sql
--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123user
--

INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES
('john','$2a$10$y3bVe1ZDCX3gQlcDxewL9u.A.BLP2T1Lb4Uf5EN4teYuoPWH2Ogj6',1,'John', 'Doe', 'john@luv2code.com'),
('mary','$2a$10$zINYdUaeFJo4tMWRgs4A0.8ZIW2niKlUm2M3y4PATyYUTUMQdpbgC',1,'Mary', 'Smith', 'mary@luv2code.com'),
('susan','$2a$10$B.0mZlB1WjtBjol4dJ2wk.Z2Zaeny5H1aNfQTlTvQdzb2X9RVVVJe',1,'Susan', 'Public', 'susan@luv2code.com');
````

Here we're adding `'John', 'Doe', 'john@luv2code.com'` for `john`,
`'Mary', 'Smith', 'mary@luv2code.com'` for `mary`,
and `'Susan', 'Public', 'susan@luv2code.com'` for `susan`.
These details must be able to be retrieved by any forms or by any controller/controller's methods.
So first we run the sql scripts.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image95.png" alt="image95">
</div>

Here only our user table has been changed.
So we need to change our **User** entity class accordingly.
We want to add some basic validation rules to the registration form 
to make sure the `username` and `password` are not empty.
As a result, we have an entry for the **Hibernate Validator** in the `pom.xml` file.

````xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
````

This dependency provides support for validation annotations like `@NotNull`, `@Size`, `@Email`, etc., 
which can be used on our entity fields. 
These annotations enable automatic validation of input data, 
especially useful when working with **Spring**'s form binding or when handling user input in web applications.
In our `User` entity, we have not yet defined any validation constraints.
And also we have not yet add the new fields that are exists now in the database.
So we need to remap our `User` entity and add these validations would look like this:

````java
package com.luv2code.springboot.thymeleafdemo.entity;
import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    // additional new fields
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 64, message = "First name must not exceed 64 characters")
    private String firstName;
  
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 64, message = "Last name must not exceed 64 characters")
    private String lastName;
  
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 64, message = "Email must not exceed 64 characters")
    private String email;

    // Collection to store user's roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    // constructors
    public User() {
    }

    public User(String userName, String password, boolean enabled, String firstName, String lastName, String email) {
      this.userName = userName;
      this.password = password;
      this.enabled = enabled;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
    }

    // getters/setters with toString()
}
````

In this revised `User` entity, each field now has basic validation annotations 
that play an essential role in ensuring data integrity at the application level before reaching the database.
For `userName`, we’ve added constraints to make it required 
and ensure it falls within a reasonable length (3 to 50 characters). 
This helps prevent overly short or excessively long usernames, 
which can affect readability and consistency across the application. 
Using the `unique = true` constraint at the database level ensures there are no duplicate usernames.
For the `password`, the `@NotBlank` constraint ensures that this field is always filled. 
Additionally, `@Size(min = 8)` enforces a minimum length of 8 characters, 
encouraging stronger passwords, which can help enhance security.
The `firstName` and `lastName` fields have similar `@NotBlank` and `@Size` constraints, 
limiting their length to 64 characters. 
This ensures a practical maximum name length without data overflow in the database and prevents unusual display issues.
Finally, the `email` field is constrained with `@Email` 
to ensure the data conforms to a valid email format. 
`@NotBlank` makes sure the email is provided, 
while `@Size(max = 64)` keeps it manageable in length. 
By setting `unique = true`, we prevent duplicate emails, 
essential in applications where email addresses are used for user identification or communication.
With these modifications, the `User` entity aligns with the database schema 
and provides robust validation directly on the entity level. 
This ensures that invalid data does not enter the system, 
simplifying data management and maintaining integrity.

Now we want to implement custom successHandler into our **Spring Security Configuration** file.
To implement a custom success handler for **Spring Security** login, 
we’ll update the **SecurityConfig** to include `.successHandler(customAuthenticationSuccessHandler)` 
in the `.formLogin` configuration. 

````java
@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/registration-confirmation").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/employees/list").authenticated()
                        .requestMatchers("/employees/AddNewEmployee", "/employees/updateEmployee", "/employees/save").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/employees/delete").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(UserService userService) {
        return new CustomAuthenticationSuccessHandler(userService);  // Use your custom success handler
    }
    
    // passwordEncoder and authenticationProvider methods
}
````

This requires creating a bean in **SecurityConfig**and defining 
the `CustomAuthenticationSuccessHandler` class itself with logic to handle successful authentication.

````java
package com.luv2code.springboot.thymeleafdemo.security;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
  
    public CustomAuthenticationSuccessHandler(UserService theUserService) {
        this.userService = theUserService;
    }
  
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
  
        System.out.println("In customAuthenticationSuccessHandler");
    
        // Get the logged-in username
        String userName = authentication.getName();
        System.out.println("userName=" + userName);
    
        // Retrieve the user details using the username
        User theUser = userService.findByUserName(userName);
    
        // Store user details in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);
    
        // Redirect to the homepage
        response.sendRedirect(request.getContextPath() + "/");
    }
}
````

The `CustomAuthenticationSuccessHandler` class is responsible for handling post-login actions. 
In this case, it retrieves the user’s details, places them in the session, and redirects to the homepage.
So this class implements `AuthenticationSuccessHandler` and overrides the `onAuthenticationSuccess` method.
Within this method, `authentication.getName()` retrieves the `username` of the currently authenticated user.
It then uses `userService.findByUserName(userName)` to fetch the full user details, 
which are then stored in the session under the attribute "`user`". 
This allows you to access the `User` object throughout the application via the session.
Finally, `response.sendRedirect(request.getContextPath() + "/")` sends the user 
to the application’s home page upon successful login.
This setup integrates a custom success handler into **Spring Security**, enabling specific actions, 
like loading user details into the session, whenever a user successfully logs in.

Now let's update our `/signup` get request in the **MainController** file now:

````java
@Controller
public class MainController {

    @GetMapping("/signup")
    public String signup(Model theModel) {

        theModel.addAttribute("User", new User());
        return "signup";
    }
    
    // other get requests
}
````

In the form action, we'll refer to the get request, which needs to imply that 
there’s an attribute named `User` in the model. 
We have to confirm that our controller provides this attribute with the correct name and casing.
So if the attribute is named `"User"` in your controller, then we'll update the template as "`th:object="${User}"`".
Let's update now our `/signup` form:

````html
<!-- Registration Form -->
<form action="#" th:action="@{/register}" th:object="${User}" method="POST" class="form-horizontal">

    <!-- User name -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" th:field="*{userName}" placeholder="Username" class="form-control" />
    </div>

    <!-- Password -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
        <input type="password" th:field="*{password}" placeholder="Password" class="form-control" />
    </div>

    <!-- First name -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control" />
    </div>
    
    <!-- Last name -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control" />
    </div>

    <!-- Email -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
        <input type="text" th:field="*{email}" placeholder="Email" class="form-control" />
    </div>

    <!-- Registration Button -->
    <div style="margin-top: 10px;" class="form-group">
        <div class="col-sm-6 controls">
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </div>
</form>
````

So this **Thymeleaf** form must be a registration form to capture user information for a signup process. 
The form's `th:action` attribute is bound to the `/register` endpoint, 
indicating where the form data will be sent upon submission, using a **POST** request. 
It is also bound to a `User` model object through the `th:object` attribute, 
which allows easy mapping of form fields to the object's properties. 
Each input field (`username`, `password`, `firstName`, `lastName`, and `email`) 
is styled with **Bootstrap**’s input-group for a compact 
and visually appealing layout, with an icon prefix in each field to denote its purpose 
(a user icon for `username` and `firstName`, a lock icon for `password`, and an envelope icon for `email`). 
Each input uses the `th:field` attribute to bind to specific fields in the `User` model, 
ensuring data is mapped accurately. 
At the bottom, a styled "`Register`" button is provided to submit the form, 
completing the registration process for the user.
Now, we need to create a post request in the **MainController** file.
However, our main controller must perform some operations 
such as finding users by username and saving new users to the database.
Then we must inject the `UserService` into the `MainController` using a constructor.

````java
@Controller
public class MainController {

    private final UserService userService;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/signup")
    public String signup(Model theModel) {
  
        theModel.addAttribute("User", new User());
        return "signup";
    }
    // "/", "/login", /logout", "/access-denied" get requests
}
````

In this enhanced **MainController** class, the `UserService` dependency is injected via a constructor, 
allowing access to user-related business logic. 
An `@InitBinder` method is used to register a **StringTrimmerEditor** that trims whitespace from form inputs, 
ensuring validation accuracy and preventing accidental errors. 
The `/signup` endpoint initializes a new `User` object and returns the user to a `signup` page, 
where they can enter their information. 

````java
@PostMapping("/register")
public String register(
        @Valid @ModelAttribute("webUser") WebUser theWebUser,
        BindingResult theBindingResult,
        HttpSession session, Model theModel) {

    String userName = theWebUser.getUserName();
    logger.info("Processing registration form for: " + userName);

    if (theBindingResult.hasErrors()) {
        return "signup";
    }

    User existing = userService.findByUserName(userName);
    if (existing != null) {
        theModel.addAttribute("webUser", new WebUser());
        theModel.addAttribute("registrationError", "User name already exists.");
        logger.warning("User name already exists.");
        return "signup";
    }

    userService.save(theWebUser);
    logger.info("Successfully created user: " + userName);
    session.setAttribute("user", theWebUser);
    return "signup";
}
````

When the form is submitted, the `register` method performs several checks. 
It first validates the form fields, then verifies if a user with the same `username` already exists in the database. 
If the `username` is taken, an error message is added to the model, prompting the user to choose a different username. 
If the `username` is available, the new user is saved to the database, a success message is logged, 
and the `user` is placed in the **HTTP** session for future interactions.
Here, at the end, we will have an issue with a red flag that's due to the fact that
we don't have a `save` method in the `userService` layer.
Let's solve it quickly.

````java
package com.luv2code.springboot.thymeleafdemo.service;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);
    void save(User user);
}
````

First, we add our new method, that is, `save` to the `UserService` interface.
And then its implementation raise another red flag.
We open `UserServiceImpl.java` file:

````java
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        // Create a new user entity with encoded password
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setEnabled(true);

        userDao.save(newUser);  // Save the user to the database
    }
    
    //...
}
````

Firstly, we'll create a new `User` object, and populate it with data from the form, 
encrypting the password with the `PasswordEncoder`. 
The `UserServiceImpl` constructor now includes an injected `PasswordEncoder`, 
typically configured as a **BCrypt** password encoder in the **Spring** configuration. 
This encrypted password, along with other user details like `userName`, `firstName`, `lastName`, and `email`, 
is saved into the database by calling the save method of `UserDao`. 
However, this time `UserDao` has no a `save` method.
Since `UserDao` is a **Spring Data JPA** repository or a **DAO** with direct access to the **EntityManager**,
it requires a transactional context for this operation.
That's why we need to mark `save` method in **UserServiceImpl** as `@Transactional`.
This will ensure that a transaction is opened when this method is called, 
allowing `userDao.save(newUser)` to work correctly.
Let's open `UserDao` interface:

````java
package com.luv2code.springboot.thymeleafdemo.dao;
import com.luv2code.springboot.thymeleafdemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    void save(User user);  // Add this method to the interface
}
````

We should add a `save` method to the interface to allow the `UserService` to persist a `User` object.
Now, we'll need to implement the `save` method in `UserDaoImpl`. 

````java
@Repository
public class UserDaoImpl implements UserDao {

    // ...

    @Override
    public void save(User user) {
        // If the user has an id, we perform an update, else we persist a new user
        if (user.getId() == null) {
            // Persist new user
            entityManager.persist(user);
        } else {
            // Merge to update an existing user
            entityManager.merge(user);
        }
    }
}
````

The `save` method should persist a new `User` or update an existing one. 
The **EntityManager**'s `persist` method will be used for new users, 
and `merge` can be used to update an existing one if the `User` has an `ID`.
With these changes, our `UserDao` now supports both finding and saving users to the database, 
enabling your `UserService` to properly save a new user during registration.
Let's now run and test our application.
Click on the "`/signup`" end point and see what we get:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image96.png" alt="image96">
</div>

That's our register form to create a new user.
Let's input the details for our new user.
"`kcakmak`" for username,
"`test1234`" for password,
"`Korhan`" for first name,
"`Cakmak`" for last name,
"`korhancakmak@luv2code.com`" for email.
And register:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image97.png" alt="image97">
</div>

Here we got our user registered.
Let's try to log in with his user information.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image98.png" alt="image98">
</div>

That's it.
We created our first user that has no roles.
Also, we used `customAuthenticationSuccessHandler` class in the **Spring Security**.
That's why we see the credential for the user at the end of the console log:

````text
INFO 48668 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
INFO 48668 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
INFO 48668 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
INFO 48668 --- [nio-8080-exec-7] c.l.s.t.controller.MainController        : Processing registration form for: kcakmak
INFO 48668 --- [nio-8080-exec-7] c.l.s.t.controller.MainController        : Successfully created user: kcakmak
In customAuthenticationSuccessHandler
userName=kcakmak
````

Here, it is.
Since the user for now got no roles, our **Spring Security** allows us to access to the list.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image99.png" alt="image99">
</div>

However, **Spring Security** allows other CRUD functions than reading(or listing) 
to only the users that has role of "`MANAGER`" or "`ADMIN`".
That's why, `kcakmak` cannot make any adding, updating or deleting in the list.
In next section, we'll handle that.

</div>

### [Spring MVC Security - Using Spring Data JPA]()
<div style="text-align:justify">

We'll use **Spring Data JPA** instead of **DAO** classes for `User` and `Role` entities.
Because we can eliminate the need for a custom implementation like `UserDaoImpl`. 
**JpaRepository** provides all the basic CRUD operations, 
and **Spring Data JPA** will automatically generate the necessary implementation at runtime. 
This approach simplifies the code and reduces boilerplate.
So our development process:

* Create UserRepository class
* Update UserService interface and its implementation class using the CRUD methods
* 

So we create set up `UserRepository` interface:

````java
package com.luv2code.springboot.thymeleafdemo.dao;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndEnabledTrue(String userName);
}
````

This gives `UserRepository` all the standard CRUD methods (`save`, `findById`, `findAll`, `deleteById`, etc.) 
without requiring a custom implementation.
**Spring Data JPA** will automatically detect and manage `UserRepository` as a repository bean, 
so no `@Repository` annotation is necessary.
The `findByUserNameAndEnabledTrue` method is defined using **Spring Data JPA**'s query derivation mechanism. 
**Spring Data** will automatically generate a query for it based on the method name.
It recognizes `findByUserName` as part of the query, which matches the `userName` field.
And then, it recognizes `AndEnabledTrue` to filter on the `enabled` field with a `true` condition.
This method name is equivalent to a query like:

````sql
SELECT u FROM User u WHERE u.userName = :userName AND u.enabled = true
````

So we can remove `UserDao` and `UserDaaImpl` java files from the `dao` package.
`UserService` interface does not need any changes. 
It’s fine as it is since it defines the required methods (`findByUserName` and `save`).

````java
public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    List<User> findAll();
    User findById(Long userId);
    User save(User theUser);
    void deleteById(Long theUserId);
}
````

But I want to add other CRUD methods just like `Employee` entity.
So here, we add listing them, finding by id and deleting by id additionally.
These methods will be implemented by `UserServiceImpl`.
With `UserRepository` in place, we can now update `UserServiceImpl` to inject `UserRepository` instead of `UserDao`. 
Let’s make the necessary changes.

````java
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    //private UserDao userDao;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, PasswordEncoder passwordEncoder, RoleRepository theRoleRepository) {
        this.userRepository = theUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = theRoleRepository;
    }
  
    @Override
    public User findByUserName(String userName) {
        //return userDao.findByUserName(userName);
        return userRepository.findByUserNameAndEnabledTrue(userName);
    }
    
    //...
}
````

We replace any `userDao` references with `userRepository`.
We also inject `roleRepository` here to add roles with the registering a user.
But we have not got a `roleRepository` class.
We'll create that class right after the methods here.

````java
@Override
public List<User> findAll() {
    return userRepository.findAll();
}
````

`findAll()` method retrieves a list of all `User` entities from the database 
by calling the `findAll()` method of the userRepository. 
It provides a straightforward way to access all users, 
which can be useful for administrative purposes or user management features where a list of users is needed.

````java
@Override
public User findById(Long userId) {
    Optional<User> result = userRepository.findById(userId);
  
    User theUser = null;
  
    if (result.isPresent()) {
      theUser = result.get();
    }
    else {
      // we didn't find the employee
      throw new RuntimeException("Did not find user id - " + theUser);
    }
  
    return theUser;
}
````

`findById(Long userId)` method is responsible for retrieving a specific `User` by its unique identifier (`userId`). 
It first attempts to retrieve the `User` object wrapped in an **Optional**, 
and then checks if the **Optional** is present. 
If the user is found, it returns the user object; 
otherwise, it throws a **RuntimeException** indicating that the user was not found. 
This method ensures safe retrieval of a user while handling cases where the user ID may not exist.

````java
@Override
public void deleteById(Long userId) {
    userRepository.deleteById(userId);
}
````

`deleteById(Long userId)` method deletes a `User` based on the provided userId 
by calling `deleteById` from `userRepository`. 
It's helpful for scenarios where admins need to remove a user from the database by ID.
These methods collectively provide comprehensive **CRUD** (Create, Read, Update, Delete) 
functionality for managing users, ensuring secure handling of sensitive information (like passwords) 
and robust error handling for missing entities. 

````java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUserNameAndEnabledTrue(username);
    if (user == null) {
        throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
}
 
private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
}
````

The existing `loadUserByUsername` method, used for authentication, 
and the helper `mapRolesToAuthorities` method for mapping user roles to **Spring Security** authorities, 
round out this service class, allowing it to manage both user data and security aspects effectively.

````java
@Override
public User save(User user) {
    // Ensure the user has at least one role
    if (user.getRoles() == null || user.getRoles().isEmpty()) {
        // Assuming 'ROLE_EMPLOYEE' is a default role that you want to assign
        Role defaultRole = roleRepository.findByName("ROLE_EMPLOYEE");
        if (defaultRole != null) {
            user.setRoles(Collections.singletonList(defaultRole));
        } else {
            throw new RuntimeException("Default role not found in the database.");
        }
    }
    
    // Encode the password and enable the user
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(true);

    return userRepository.save(user);
}
````

We want that the new user has at least one role in the `save` method of `UserServiceImpl`.
So, we can make the following updates.
Firstly, we modify the `save` method to accept a role or list of roles for the `user`. 
So, we check in this method If the `User` object has any roles assigned. 
If `user.getRoles()` is null or empty, it retrieves a default role from the `roleRepository` (like `ROLE_EMPLOYEE`). 
If `ROLE_EMPLOYEE` exists in the database, it assigns this role to the `user`.
Then we save the `user` along with the assigned role(s) in the database.
So this code assumes we have a `RoleRepository` to fetch roles by their name. 

````java
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
````

We would need to add this interface since it was not exist.
Also, we need to inject `RoleRepository` in `UserServiceImpl`.

````java
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, PasswordEncoder passwordEncoder, RoleRepository theRoleRepository) {
        this.userRepository = theUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = theRoleRepository;
    }
}
````

This approach ensures that every user created has at least one role, 
thus meeting the requirement of having an initial role associated with every new user.
Let's update our `signup.html` view's "`<form>`" tag to include the role checkboxes.

````html
<!-- Registration Form -->
<form action="#" th:action="@{/register}" th:object="${User}" method="POST" class="form-horizontal">

    <!-- User name -->

    <!-- Password -->

    <!-- First name -->

    <!-- Last name -->

    <!-- Email -->

    <!-- Roles -->
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-briefcase"></i></span>
        <div style="width: 100%; display: flex; justify-content: space-between;">
            <div th:each="role : ${roles}">
                <!-- <label th:for="${role.id}" th:text="${role.name}"></label> -->
                <label th:for="${role.id}" th:text="${#strings.substring(role.name, 5)}"></label>
                <input type="checkbox" th:field="*{roles}" th:value="${role.id}" />
            </div>
        </div>
    </div>

  <!-- Registration Button -->

</form>
````

So we add checkboxes for roles and align them within a single input-group like the other fields. 
Each checkbox will represent a different role, and they will be displayed inline within the same input-group.
The `checked` attribute on `ROLE_EMPLOYEE` ensures that 
the "**Employee**" role is pre-selected for every user by default.



So just like `EmployeeController`, now i need to create a `UserController` for these CRUD functions.
This controller would manage users by mapping requests to endpoints for listing, adding, updating, 
and deleting users, similar to how `EmployeeController` handles employees.

````java
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService theUserService) {
        userService = theUserService;
    }
}
````

So we begin with injection our service layer to the controller.
And we'll generate our constructor that initializes the `userService`.

````java
// Mapping for "/users"
@GetMapping("/users")
public String listUsers(Model theModel) {

    // Get the users from the database
  List<User> users = userService.findAll();

  // Add to the Spring model
  theModel.addAttribute("users", users);

  return "users/list";
}
````

It retrieves a list of all users from the database via the `UserService` and adds it to the model, 
making it available in the "`users/list`" view for display.
And next, we create `updateUser` method:

````java
// Mapping for "/updateUser"
@GetMapping("/updateUser")
public String updateUser(@RequestParam("userId") Long userId, Model theModel) {

    // Get the user from the service
    User user = userService.findById(userId);

    // Set user in the model to pre-populate the form
    theModel.addAttribute("user", user);

    // Send over to our form
    return "/users/updateUser";
}

@PostMapping("/updateUser")
public String update(@Valid @ModelAttribute("user") User theUser,
                     BindingResult theBindingResult, Model theModel) {

    // Check for validation errors
    if (theBindingResult.hasErrors()) {
      return "/users/updateUser";
    }
  
    // Save the updated user (the ID will ensure it's an update)
    userService.save(theUser);
  
    // Redirect to the list of users
    return "redirect:/users";
}
````

It retrieves the `user` by ID from the database 
and adds it to the model to populate the form with the current user details. 
The view "`/users/updateUser`" displays this form for editing.
And we create its post request for updating the database.
The `update` method checks for validation errors first, 
and then saves the user using `userService.save(theUser)`. 
Since the `User` entity includes the user ID, 
**Spring Data JPA** will recognize this as an update rather than a new save.
So finally, we create `deleteUser` method:

````java
// Mapping for "/delete"
@GetMapping("/delete")
public String deleteUser(@RequestParam("userId") Long userId) {

    // Delete the user
    userService.deleteById(userId);

    // Redirect to the /users/list
    return "redirect:/users/list";
}
````

It deletes a `user` by ID, provided via the request parameter, 
and then redirects to `/users/list` to refresh the list.
We already have a `MainController` that handling get and post requests for registering or adding a user.
So we can move all those functions to the `UserController` to keep all user-related actions in a single controller.

````java
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/signup")
    public String signup(Model theModel) {
        theModel.addAttribute("User", new User());
        return "/users/signup";
    }
  
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("User") User theUser,
                           BindingResult theBindingResult, HttpSession session, Model theModel) {
  
        String userName = theUser.getUserName();
        logger.info("Processing registration form for: " + userName);
    
        if (theBindingResult.hasErrors()) {
          logger.warning("Form validation errors: " + theBindingResult.getAllErrors());
          return "/users/signup";
        }
    
        User existing = userService.findByUserName(userName);
        if (existing != null) {
          theModel.addAttribute("User", new User());
          theModel.addAttribute("registrationError", "User name already exists.");
          logger.warning("User name already exists.");
          return "/users/signup";
        }
    
        userService.save(theUser);
        logger.info("Successfully created user: " + userName);
        session.setAttribute("user", theUser);
    
        return "/users/registration-confirmation";
    }
    
    // ...
}
````

So we begin with importing `java.util.logging.Logger` class.
And then we move the `signup` and `register` mappings into `UserController`.
Now, we should update our present form pages especially for user list link in the nav bar.
Our first form we have `login.html`.
But, we don't need any user-related links on this form.
Next, we have `registration-confirmation.html`.
We need to move this form into the `/users` folder.
But on this form, we don't need any updates neither.
Just like this one, we don't need to change `signup.html` but its location must be inside `/users` folder.
Also, we need to add these end points into our **Spring Security** 
to allow the access to the users that have role of `MANAGER` or `ADMIN`.
But we'll handle this part at the end.
So we open `home.html` form first:

````html
<nav class="navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/}">Employee Application</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a th:href="@{/}">Home</a></li>
            <li sec:authorize="isAuthenticated()"><a class="nav-link" th:href="@{/employees/list}">Employees</a></li>
            <li sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')"><a class="nav-link" th:href="@{/users/list}">Users</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="display: flex; align-items: center;">
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/signup}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/login}"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li sec:authorize="isAuthenticated()">
                <form action="@{/login}" th:action="@{/logout}" method="POST">
                    <button type="submit" class="btn btn-danger">
                        <span class="glyphicon glyphicon-log-out"></span> Logout
                    </button>
                </form>
            </li>
        </ul>
    </div>
</nav>
````

In the "`<head>`" tag, we don't need to change anything. 
But in the navigation bar, we add `Users` link next to the `Employees`,
that will be displayed only by `MANAGER`s and `ADMIN`s.
When we look at the below of the navigation part, we have:

````html
<div class="container mt-5">
    <h1>Welcome to the Employee Management System!</h1>
    <p>This is a simple application to manage employees.</p>

    <!-- Welcome message for authenticated users -->
    <div sec:authorize="isAuthenticated()">
        <div class="alert alert-success">
            Welcome, <span sec:authentication="name"></span>!
        </div>
    </div> 
</div>
````

We can display more detailed user information in **Thymeleaf** here.
However, in order to that we should change our `UserController` as well
so that `User` object is available in the model and accessible in the template.
So we choose to leave this as it is.
Now, we need to create the following **Thymeleaf** templates; `list.html`, and `updateUser.html`
under `src/main/resources/templates/users/`.
First we open `list.html` after copying and pasting the **Employee** `list.html` file in the `/users` folder.
We add the new nav bar link for users here as well. 

````html
<div class="container">
    <h3>User Directory</h3>
    <!-- Welcome message for authenticated users -->
    <div>
        <div class="alert alert-success">
            Welcome, <span sec:authentication="name"></span>!
        </div>
    </div>
    <!-- UserAdd button only available for MANAGER and ADMIN roles-->
    <div sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">
        <a th:href="@{/users/addNewUser}" class="btn btn-primary btn-sm mb-3">
            Add User
        </a>
    </div>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">Action</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="tempUser : ${users}">
                <td th:text="${tempUser.userName}" />
                <td th:text="${tempUser.firstName}" />
                <td th:text="${tempUser.lastName}" />
                <td th:text="${tempUser.email}" />
                <!-- Add update button link -->
                <td sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')">
                    <a th:href="@{/users/updateUser(userId=${tempUser.id})}"
                       class="btn btn-info btn-sm">
                        Update
                    </a>
                    <!-- Add "delete" button/link -->
                    <a sec:authorize="hasRole('ADMIN')"
                       th:href="@{/users/delete(userId=${tempUser.id})}"
                       class="btn btn-danger btn-sm"
                       onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">
                        Delete
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
````

First we change the heading, from "Employee Directory" to "User Directory".
And then, we need to change `href`s of the **Thymeleaf** expressions.
In the table, we update each cell by using for each loop for the users rather than employees.
That's basically it.
Let's look at `updateUser.html` form now.
We create it and open the form:

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
  <head>
      <title>Update User</title>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <style>
          .navbar-nav.navbar-right button {
              border: none;
              background: transparent;
              padding: 16px 8px;
              color: white;
          }
          .navbar-nav.navbar-right button:hover {
              background: rgba(255, 0, 0, 0.8);
          }
          .container form input {
              margin: 10px 0;
          }
          .form-group {
              display: flex;
              align-items: center;
              margin-bottom: 1rem;
          }
          .control-label {
              margin-bottom: 0;
              width: 20%;
          }
          .col-sm-10 {
              flex: 1;
          }
      </style>
  </head>
<body>
    <!-- navbar and form -->
</body>
</html>
````

Same as before, in the "`<head>`" section of this **HTML** form, we set the metadata, title, 
and styling resources necessary for the page. 
The title is defined as "`Update User`" 
and meta tags for character encoding and viewport settings are included to ensure proper rendering across devices. 
**Bootstrap CSS** is included from external CDNs to enable responsive styling. 
Additional CSS in the "`<style>`" tag customizes specific elements, 
like aligning navbar buttons and styling form elements.

````html
<nav class="navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/}">Employee Application</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a th:href="@{/}">Home</a></li>
            <li sec:authorize="isAuthenticated()"><a class="nav-link" th:href="@{/employees/list}">Employees</a></li>
            <li sec:authorize="hasRole('ADMIN') or hasRole('MANAGER')"><a class="nav-link" th:href="@{/users}">Users</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="display: flex; align-items: center;">
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/signup}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li sec:authorize="!isAuthenticated()"><a th:href="@{/login}"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li sec:authorize="isAuthenticated()">
                <form action="@{/login}" th:action="@{/logout}" method="POST">
                    <button type="submit" class="btn btn-danger">
                        <span class="glyphicon glyphicon-log-out"></span> Logout
                    </button>
                </form>
            </li>
        </ul>
    </div>
</nav>
````

The navigation bar is styled with **Bootstrap**’s `navbar-inverse` class for a dark theme 
and contains links to different sections of the application. 
The `navbar-header` contains a brand link to the home page, labeled "`Employee Application`". 
The left-aligned links include "`Home`," "`Employees`," and "`Users`" 
(the latter two being conditionally rendered based on user authentication or authorization roles). 
On the right side, users who are not authenticated see links to "`Sign Up`" and "`Login`". 
Authenticated users see a logout button styled as a form submission with a red hover effect, 
ensuring easy access for user management functions.

````html
<div class="container">
    <h3>Update The User</h3>
    <hr>
    <form action="#" th:action="@{/updateUser}" th:object="${User}" method="POST"
            style="margin: 0 auto; width: 50%; background-color: #6c757d; padding: 20px; border-radius: 8px;">
        <p class="h4 mb-4">User Details</p>

        <!-- hidden ID field -->
        <div class="form-group d-flex align-items-center">
            <label class="control-label col-sm-2">ID:</label>
            <div class="col-sm-10">
                <p class="form-control-static"><span th:text="*{id}"></span></p>
                <input type="hidden" th:field="*{id}" />
            </div>
        </div>

        <!-- userName field -->
        <div class="form-group d-flex align-items-center">
            <label class="control-label col-sm-2">User name:</label>
            <div class="col-sm-10">
                <input type="text" th:field="*{userName}" placeholder="User name" class="form-control mb-4 w-25">
            </div>
        </div>
    
        <!-- hidden password field -->
        <div class="form-group d-flex align-items-center">
            <div class="col-sm-10">
                <input type="hidden" th:field="*{password}" class="form-control mb-4 w-25" readonly>
            </div>
        </div>

        <!-- firstName field -->
        <div class="form-group d-flex align-items-center">
            <label class="control-label col-sm-2">First name:</label>
            <div class="col-sm-10">
                <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
            </div>
        </div>

        <!-- lastName field -->
        <div class="form-group d-flex align-items-center">
            <label class="control-label col-sm-2">Last name:</label>
            <div class="col-sm-10">
                <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
            </div>
        </div>

        <!-- email field -->
        <div class="form-group d-flex align-items-center">
            <label class="control-label col-sm-2">Email:</label>
            <div class="col-sm-10">
                <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
            </div>
        </div>

        <!-- submit button -->
        <button type="submit" class="btn btn-info col-2">Save</button>

    </form>
    <hr>
    <a th:href="@{/users/list}">Back to User List</a>
</div>
````

The "`<form>`" tag allows users to update their account information, 
with fields for ID, username, first and last name, email, 
and a hidden password field to prevent it from being displayed or altered. 
Each field is wrapped in a form-group for consistent spacing and styling. 
The form itself is styled with a **Bootstrap** gray background, centered, 
and given rounded corners for a modern appearance. 
The ID field is displayed as static text, 
while all other fields are editable, except for the password, 
which is hidden but submitted in the form. 
Upon submitting, the form sends data to the `/updateUser` endpoint via a **POST** request.


</div>