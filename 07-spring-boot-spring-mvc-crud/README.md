# [Spring MVC CRUD]()

## [CRUD Database Project - Overview]()
<div style="text-align:justify">

In this section, we'll use **Thymeleaf CRUD** on a real-time project.
So here are the application requirements from the boss.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image01.png" alt="image01">
</div>

So we need to create a Web UI for the employee directory.
Users should be able to 

* get a list of employees
* add a new employee 
* update an employee
* delete an employee

And also, we should use **Thymeleaf** and **Spring Boot**.
And for this real-time project, here's a screenshot of what the application should look like.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image02.png" alt="image02">
</div>

So we have a list of employees.
Up in the top left, we have a button for adding a new employee.
And then over on the right-hand side, we have buttons here for updating and also deleting.
Now, let's take a look at our big picture here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image03.png" alt="image03">
</div>

So we'll start with our web browser.
The request will come into our `EmployeeController`, goes over to our `EmployeeService`,
to our `EmployeeRepository`, hits the database, we get the data back,
and then we'll send it all the way back over to our **Thymeleaf** templates.
And our **Thymeleaf** templates will give us a view that we'll send back to the web browser.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image04.png" alt="image04">
</div>

And in terms of our application architecture with the `controller`, `service`, and `repository`,
we'll actually reuse some code from our previous project.
So we already created the code for `service` and `repository`.
And in this section series, we'll simply focus on the `EmployeeController` 
as far as creating the new code for handling all the requests coming in from the Web UI.
In terms of project set up, 
we're going to extend our existing employee project and add database integration.
So we're going to add the `EmployeeService`, `EmployeeRepository`, and the `Employee` entity.
These are all available in one of our previous projects.
And so earlier, we created all of this code from scratch.
So here, in this section series, we'll simply copy and paste it and put it into this new project.
So this will basically allow us to focus on creating the `EmployeeController` and the **Thymeleaf** templates.
Now, our development process as far as the big picture:

* Get list of employees
* add a new employee
* update an existing employee
* delete an existing employee

And we'll spread this out over multiple sections, and we'll get everything up and running.

Let's get started.
And now what I'd like to do is just download some starter code just to get us up and running.
This file is attached in the section of the section and this is the REST API project
that we created earlier in the course, so the same code you've seen before.
Let's go ahead and swing over to our file system and take a look at it.
I'll move into my `dev-spring-boot` folder.
And now what I want to do is refresh our database just so we have a standard baseline to kind of work from.
I'll go ahead and run my SQL workbench and just log in real quick.
And now I'll go ahead and open up this SQL script.
I'll move into that `dev-spring-boot` folder.
I'll move into the `07`.
I'll move into `00`.
And move into the `sql-scripts` directory, and we'll open up the `employee-directory.sql`.

````sql
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
````

Alright, so this is the same SQL script that we had in some of our previous projects.
I'll just go ahead and run it real quick.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image05.png" alt="image05">
</div>

And now let me just move over here to my tables and just do a query on this employee table.
And I should have our standard 5 employees, `Leslie`, `Emma`, `Avani`, `Yuri`, and `Juan`.
Allright, we can go ahead and close my SQL workbench.
Now, let's go ahead and open up this project in the IDE.
So I'll move into the `07` directory, and I'll select the `00` project or the `00` folder,
and I'll move that up into the IDE.
Let's move down to `src/main/Java`.
And this is basically the same code that we had in our previous REST API projects.
So we have our dao's, our entities and our rest and our service.
This is all the same code that we had before.
Now lets just go ahead and run this application just to make sure that this REST project still works.
I'll swing over to the web browser real quick,
and then I'll go to this `localhost:8080/api/employees`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image06.png" alt="image06">
</div>

And this is for our REST API that we created earlier.
So we see all the JSON data that came back from that REST API, so we know that we have this basic code working okay.
And in upcoming sections, we'll actually modify this project to make use of **Spring MVC**.

</div>

## [CRUD Database Project - Get Employees]()
<div style="text-align:justify">

Now, we don't need the REST code anymore because we're going to do some **Spring MVC** work,
so we can safely delete the REST controller from this project.
Allright, so the REST controller's gone, but we still have our `dao`, our `entity`, and our `service`.
We'll use those a little bit later.
Now, we'll actually go through and create our **Spring MVC** controller.
And the first thing I want to do is create a new package for this.
And the name for this package is `controller`.
Now, in this `controller` package, let's go ahead and create a new class.
The name for this class, I'll call it `EmployeeController`.

````java
package com.luv2code.springboot.cruddemo.controller;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from db
        List<Employee> theEmployee = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployee);

        return "list-employees";
    }
}

````

Now, for this `EmployeeController`, the first thing that we'll do up tops,
we'll give the annotation for `@Controller`, that's for **Spring MVC**.
And we'll also set up the `@RequestMapping`, we'll give `/employees`.
And the `/employees` here, that's the actual base mapping for the URL request for items in this given controller.
And our `EmployeeController`'s going to make use of our `EmployeeService`,
that service talks to the repository, and we'll retrieve information from the database.
And we saw this little architecture earlier in some of the previous sections.
Alright, I'll define a field here for this `EmployeeService`.
I'll add a constructor here for constructor injection.
So, we'll inject this `EmployeeService` and we'll make the assignments here to our field accordingly.
And remember, here we're using constructor injection.
And since we only have one constructor in our class, `@Autowired` is optional,
so **Spring** will still inject this given dependency for our `EmployeeController`.
And now, what I'll do is I'll add a mapping here for listing the employees.
I'll set up this `@GetMapping` for `/list`.
I'll define my method here, `listEmployees`.
We'll make use of the **Model**.
And for **Model**, be sure to import the **Model** from the **Spring** framework.
So I'm going to get the employees from the database, and then I'll add that to the **Spring Model**.
I'll set up a variable here for our employees.
And I make use of the service, and I'll say `findAll`.
The service makes use of the repository to get information from the database.
And now, let's go ahead and add this data to the **Spring Model**.
Here, I'll say `theModel.addAttribute`, I give the name, `employees`, comma, the value, `theEmployees`.
And then, finally, I give the name of the view page that I'll return this to, `list-employees`,
and since we're using **Thymeleaf** it'll actually send it over to a page, `list-employees.html`.
So, that's the basic coding here for this method.
We need to create the **Thymeleaf** page, `list-employees.html`.

What I'll do is I'll move down here to my `resources` directory and `templates`, and I'll create a new **HTML** file.
And the name of this file, I'll call it `list-employees.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Employee Directory</title>
</head>
<body>

    <span th:text="${employees}" />

</body>
</html>
````

And the one thing I'll do is I'll add the entry here for the xml namespace for **Thymeleaf**.
And then I'll actually update the title. 
And now what I want to do is just a quick and dirty solution.
I simply want to just dump out the information or just display the contents of the employee model attribute.
So, no HTML tables, no fancy styling, just dump it out on the screen
just so I can know that this piece actually works.
And I can always clean it up later.
I'll set up the `span` `th:text`, and I'll give a reference to `employees`, and the dollar sign, curly brace.
And so remember that's the name of the model attribute that we set in that **Spring MVC** controller.
Now, let's go ahead and run this application.
All right, so the application is up and running,
I'll go over to the browser.
I'll go to `localhost:8080/employees/list`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image07.png" alt="image07">
</div>

And success.
So we have the data.
Now, it's ugly.
It's not gorgeous or whatever, but at least, we have the data that came over from the database.
Later on, we'll actually go through and clean it up.
But again, at least we have the data that's working out.
And kind of pulling this together with our big picture here as far as our architecture.
We had the request come into the browser.
I went to the controller, talked to the service.
Access the repository.
The repo retrieved the information from the database.
And then that data was sent back to this **Thymeleaf** template that we created.
And now we see it in the actual web browser.
So at least we have all the big items in place.
Now it's just more of a cosmetic thing here of making it look pretty.
Let's make it look pretty with these HTML tables and **Bootstrap CSS** styles.

So the first thing we need to do is get **Bootstrap** or get a reference to those **Bootstrap** styles.
So I can go to [this](https://www.getbootstrap.com).
And we'll make it to the main **Bootstrap** website.
Now notice your web browser may look slightly different because they change this site on a regular basis.
However, just follow the links for `Docs`, and then it should give you information
as far as how to get started, or a Quick start section.
So I'll move down for a bit.
So I'll simply copy the link for the Bootstrap CSS style.

````html
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  
````

So I'll go ahead and select these three lines here and copy them.
And then I'll swing back over to my IDE.
And I'll move into the head section for my application, and I'll simply paste that information.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <!-- <title>Employee Directory</title> -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

    <span th:text="${employees}" />

</body>
</html>
````

I'll remove the title because we don't need that.
We already have a title.
And yeah, so there we go.
So we have the basics in place here for loading the **Bootstrap CSS** styles,
and it'll actually load it from the network using this approach.
Allright, so let's go ahead and move down to our body section.
Let's remove that little quick and dirty solution because we don't need it anymore.
We're actually going to make use of some HTML tables here.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="tempEmployee : ${employees}">
                <td th:text="${tempEmployee.firstName}" />
                <td th:text="${tempEmployee.lastName}" />
                <td th:text="${tempEmployee.email}" />
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
````

I'll set up this `div`.
Give the `class` of container.
And so this is a reference to a **CSS** style, and it's actually a reference to one of the **Bootstrap CSS** styles.
And now we'll kind of go through the process of setting up this screenshot here or this table.
So I'll set up an `h3` for `Employee Directory`, just kind of heading or the title for this section.
And I'll set up a table.
I'll set the `class`, `table`, `table-bordered` and `table-striped`.
I'll set up a heading section.
I'll make use of the `class="table-dark"`.
And again, these classes here are all **Bootstrap CSS** styles that I'm using.
Now I'll go ahead and set up a table row here in th for `First Name`.
I'll copy-paste this x number of times, and I'll go ahead and change it for `Last Name`.
And also `Email`.
So these are the actual table headings that we'll see at the top of the table.
That's the basic coding there for that piece of it.
And now let's move down here a bit, and we will make use of the table body.
And we'll set up our different table rows.
So a table row for each one of the employees.
Here I make use of that `th:each="tempEmployee : ${employees}"`.
This will actually loop over the list of `employees`.
So, `employees` is the name of that model attribute.
`tempEmployee` will be the temp loop variable that we can use inside of this actual loop.
And now I simply want to print out the employee's `firstName`, `lastName`, and `email`.
Here I'll set up a `<td th:text`.
And I'll assign curly brace and `tempEmployee.firstName`.
And then what I'll do is I'll just copy-paste this x number of times.
And then I'll make the update here for `lastName`.
And also for `email`.
So let's go ahead and run this application and now let's test it out.
Our app is up and running.
We can go over to our link, and we can simply just do a reload on this page for `employee/list`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image08.png" alt="image08">
</div>

And success.
So we've kind of pulled everything together here as far as processing or reading data from the database
and displaying it in a Spring MVC application.

But if you notice, if you go to `localhost:8080` without the full URL, then we get this `404` error:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image09.png" alt="image09">
</div>

and that's not good, it's kind of ugly, and we'd like to somehow fix this or resolve this.
So what we can do here is actually add an `index.html` page to redirect to our `employees/list`.
So under my `src/main/resources/static` folder, I'll create a new file, and I'll give it the name of `index.html`.

````html
<meta http-equiv="refresh" content="0; URL='employees/list'">
````

So we'll add some code here to redirect to our given mapping.
So I'll set a meta tag that is just an HTML trick that'll automatically redirect to another URL.
In this case, we're going to redirect to our mapping.
And now I'll just run the app one more time.
So now our app is up, now let's do a refresh here, `localhost:8080`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image08.png" alt="image10">
</div>

and so it automatically redirects to `employees/list`.
So no more `404`, so this is great.

</div>

## [CRUD Database Project - Add Employees]()
<div style="text-align:justify">

In this section, we're going to use **Thymeleaf** to add an employee.
So I'll show you a quick demo of the application, just so you can see the application flow.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image11.png" alt="image11">
</div>

So with our employee directory, we'll have this new button in the top left, `Add Employee`.
This will show us our form: 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image12.png" alt="image12">
</div>

and so we can enter the employee's information for first name, last name, and email address.
And so I'll enter some data here for `Michael Zeno`, and then I'll just hit the `Save` button.
And then at the bottom:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image13.png" alt="image13">
</div>

you'll see that we have this new employee that's added, `Michael Zeno`.
And so this information is in the database, and it's being displayed here for our application.
So here's the development process:

* add this button for `Add Employee`
* create the HTML form for a new employee
* process the form data to save it to the database  

Allright, step one, the `Add Employee` button.
So this will basically be a href link to the request mapping for `/employees/showFormForAdd`.
So we set up this `th:href/employees/showFormForAdd`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image14.png" alt="image14">
</div>

And then remember, that `@` symbol there is simply a reference to the context path for our application.
That's the application root.
So this will give us a plain vanilla link, but we want to apply some bootstrap styles,
so we can have a nice pretty button here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image15.png" alt="image15">
</div>

So we apply these bootstrap styles for class, and then we set up the classes for button,
button primary, button small, margin bottom, 3 pixels.
That three pixels will give us a white space at the bottom of our given button.
And all of these different styles are documented at the bootstrap website.
So go to `bootstrap.com` to get all the gory details of the various bootstrap styles.
And so we still have some work to do here.
We need to add a controller request mapping for `/employees/showFormForAdd`.

Now, in our **Spring Controller**, before we show the form, we must add a model attribute.
So this is an object that will hold the form data for the data binding.
Allright, so here's the controller code to show the form.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image16.png" alt="image16">
</div>

So we have our employee controller, and then we set up this `@GetMapping` for `/showFormForAdd`.
We have a method we pass in the model, and we basically create a model attribute to bind the form data.
So I create this `new Employee()`, and then I'll say, `theModel.addAttribute`, `"employee"`, `theEmployee`.
And the nice thing here is that our **Thymeleaf** template will be able to access this data for binding the form data.
And then finally at the end, I just do a return on `/employees/employee-form`.
And remember, there's a look at our templates directory for `/employees/employees-form.html`.
Allright, so **Thymeleaf** has special expressions for binding **Spring MVC** form data.
They'll automatically set and retrieve the data from a given **Java** object.

<table align="center">
    <thead>
        <tr>
            <th>Expression</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><strong>th:action</strong></td>
            <td>Location to send form data</td>
        </tr>
        <tr>
            <td><strong>th:object</strong></td>
            <td>Reference to model attribute</td>
        </tr>
        <tr>
            <td><strong>th:field</strong></td>
            <td>Bind input field to a property on model attribute</td>
        </tr>
        <tr>
            <td><i>more ...</i></td>
            <td>See - <a>www.luv2code.com/thymeleaf-create-form</a></td>
        </tr>
    </tbody>
</table>

And so these **Thymeleaf** expressions can actually help you build the **HTML** form.
So here I make use of this `th:action`, that's the location I'll send the form data,
`th:object` is a reference to our model attribute, and then `th:field` to bind the input field
to a property on the model attribute.
And there's actually some more expressions out there, visit my site [here](https://www.luv2code.com/thymeleaf-create-form).
I'll redirect you to the official **Thymeleaf** documentation for all the details on that.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image17.png" alt="image17">
</div>

Okay, step two of creating the HTML form for the new employee. 
So we set up this form tag, and then we have `action=#`, that's just an empty placeholder.
**Thymeleaf** will actually handle the real work right here.
So on the right we have `th:action`, and then `/employees/save`.
And then we have this `th:object`, that's our model attribute.
That's the same model attribute that was set up in our `EmployeeController`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image18.png" alt="image18">
</div>

So now what we need to do is actually define the form input fields for first name, last name, and email.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image19.png" alt="image19">
</div>

So here's my form, and then I'll set up an `input` field, input `type=text` and then `placeholder="First name"`.
So that'll kind of relate to that first field here, `First name`.
And then we have this `th:field`, which is a star, curly brace, `*{firstName}`.
So this basically selects a property on the referenced `th:object`.
So that's the first name property on the `employee` object.
And now just kind of repeat the process for last name and also for email.
And then at the bottom, I'll set up a `Submit` button.
And this is the actual button for saving the given `employee`.
The important thing here is that when the form is first loaded, it's going to call the getter methods.
So they'll call employee `getFirstName`, `getLastName`, 
and it'll use that information to populate the fields initially.
And then when we submit the form, it's going to call the setter methods.
And so basically, any form data that you have, it'll call the appropriate setter methods.
So it'll call employee `setFirstName`, `setLastName`, so on and so forth.
And that's an example here of data binding with **Thymeleaf** and **Spring MVC**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image20.png" alt="image20">
</div>

Let's apply some bootstrap styles.
And then I just repeat the process for last name and email.
And we'll also apply the bootstrap styles to our `Submit` button.
So the styles that we'll apply here are button, button info and column span 2.
So this given button will span across two columns for this given form.
We have one more thing to do.
We need to add the controller request mapping for `/employees/save`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image21.png" alt="image21">
</div>

So that's step three, processing the form data to save the employee.
So here's the `EmployeeController` again.
I know I need to make use of the `employeeService`,
so I'll actually inject this `employeeService` using constructor injection.
And remember, since we only have one constructor, `@Autowired` is optional.
And then I'll set up this `@PostMapping` for `/save`.
I have this method, `saveEmployee`, we pass in the `@ModelAttribute`, `"employee"`, `theEmployee`.
And so this comes from the form.
So the forms can actually pass the data into this given method.
And the form data binding will occur when this data is passed in.
Allright, so now we go through, and we save the `theEmployee`, so we say, `employeeService.save`, `theEmployee`.
So the controller calls a service, calls the repo and puts it in the actual database.
And now I redirect to the request mapping for `"redirect:/employees/list"`.
So this is to help prevent duplicate submissions, just in case the user hits `Reload`
in their browser multiple times, will prevent those duplicate submissions.
This is actually an implementation of the `Post`, `Redirect`, `Get` pattern.
For more information on the pattern, see the link [here](https://www.luv2code.com/post-redirect-get).
So that's basically it for the development process.

Now we need to move `list-employees` form to the `/employees` folder for URL and path consistency.
I want everything to be under the `/employees`.
So under `templates`, let's create a new folder called `employees`.
I'm going to drag and drop `list-employees` and place it into this `employees` folder.
That's the desired location here for our file.
Now, I also need to do some minor factoring for my controller here.
So I'll move to this controller package and open up `EmployeeController.java`.

````java
@GetMapping("/list")
    public String listEmployees(Model theModel) {

    // get the employees from db
    List<Employee> theEmployee = employeeService.findAll();

    // add to the spring model
    theModel.addAttribute("employees", theEmployee);

    return "employees/list-employees";
}
````

And I'll say return `/employees/list-employees`.
So remember, it's going to look in that `templates` directory,
so now it has to match up with `/employees/list-employees` and it'll match up with that given file.
Now let's take care of step one, the new `Add Employee` button.
So I'll write a little quick comment here to myself, and then I'll kind of stub out this anchor tag, `Add Employee`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <!-- <title>Employee Directory</title> -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <!-- Add a button -->
        <a th:href="@{/employees/showFormForAdd}" class="btn btn-primary btn-sm mb-3">
            Add Employee
        </a>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="tempEmployee : ${employees}">
                    <td th:text="${tempEmployee.firstName}" />
                    <td th:text="${tempEmployee.lastName}" />
                    <td th:text="${tempEmployee.email}" />
                </tr>
            </tbody>
        </table>
    </div>

</body>
</html>
````

And then I'll add my `th:href`, `@{/employees/showFormForAdd}`.
And so these are the styles here for Button, Button Primary, Button Small and Margin Bottom, 3 pixels,
basically just to give us some space at the bottom of that given button.
Allright, and that's the basic code in there for adding our button to our page.
Now, I want to run this application real quick, just to see this new button on my page.
Now, just do a reload on the page here:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image22.png" alt="image22">
</div>

and there's our button.
We don't have anything behind it, so we need to actually add that code now.
Now, I need to add the controller code to show the form.
So moving into the `EmployeeController`, I need to set up a new `@GetMapping`.

````java
@GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

    // create model attribute to bind form data
    Employee theEmployee = new Employee();

    // add to the spring model
    theModel.addAttribute("employee", theEmployee);

    return "employees/employee-form";
}
````

So I'll set up this `@GetMapping` for `/showFormForAdd`.
I'll create this method `showFormForAdd`, has one parameter, the **Model**.
Alright, so the first thing we need to do here is create the model attribute to bind the form data.
So I'll simply create a new **employee** here, call it `theEmployee`,
and then I'll add that as an attribute to the model, give the attribute name of `"employee"`,
and then I give a reference to `theEmployee`.
And remember, the nice thing here is that our timely template will access this data for binding the form data.
And then, we simply return the actual template that we want to use for this form.
And so remember, it's going to look in at `templates` folder for `employees/employee-form.html`.
And that's the basic coding here for showing the form.

Allright, step two is creating the HTML form for the new employee.
And so we'll create this as a new file here in this `employee`'s subdirectory.
And this file is called `employee-form.html`.
And what I'd like to do is copy and paste the HTML header section just to give us its bootstrap support for our file.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Save Employee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    
    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <p class="h4 mb-4">Save Employee</p>
        <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST">

            <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
            <button type="submit" class="btn btn-info col-2">Save</button>

        </form>
    </div>

</body>
</html>
````

So I'll wrap all of this `body` content into a bootstrap `container`.
We'll just set up a heading here for this page.
And now I want to create this form here, `save employee` that has three fields, first name and last name and email.
So now I just kind of stub out my form, and now I need to go through here, and add some of the form attributes.
Alright, so have the basic form attributes in place here, so this is the real work, 
the `th:action` where we're going to send the form data to `/employees/save`.
And then the `th:object` as a reference to our model attribute.
And that's the same model attribute that was set up in our employee controller.
Let's go ahead and define our input fields.
Okay, so here's my first input field here for `firstName`.
And remember, the star curly braces selects the property on the referenced object.
So `*{firstName}`'s a property of employee.
Okay, so that's in place, now, it's just more of the same.
Just a copy-paste, all copy this section, and then just paste it x number of times for `lastName` and `email`.
And now I'll simply go through here, and make the updates here.
So for `lastName`, this should be `*{lastName}`, and then also update the placeholder.
And a similar thing for `email`.
So those are my input fields for `firstName` and `lastName` and `email`.
Now the last thing I need to do here is set up my `submit` button.
And the actual label I have for the `submit` button, I'll call it `save`.
So that's basically all the real coding here for this form.
Allright, so remember the first thing that happens when the form is loaded is that they'll call the getter methods,
so they'll call `employee.getFirstName`, `employee.getLastName`, and so on.
And that's the data that you'll initially see in the form.
And then when we actually go through, and submit the data then it's going to call the setter methods.
So it's going to take all that form data, and call the appropriate setter methods 
for `setFirstName`, `setLastName`, `setEmail`, and so on.

Now, I want to add a navigation link at the bottom of the page
just to allow the user to go back to the main employees list.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Save Employee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <p class="h4 mb-4">Save Employee</p>
        <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST">
    
            <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
            <button type="submit" class="btn btn-info col-2">Save</button>
    
        </form>
        <hr>
        <a th:href="@{/employees/list}">Back to Employee List</a>
    </div>

</body>
</html>
````

So I'll just set this up as `<a th:href` and then it's basically `/employees/list`.
Allright, so that's it for setting up that navigation link. 
So let's go ahead and check out our form and also test the link.
So let's go ahead and run our application.
And in my browser, just a little quick refresh here, and then click on `Add Employee`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image23.png" alt="image23">
</div>

First name, last name, email, and a save button.
Let's go ahead and test this link here at the bottom.
It goes back to the employee list.
So we have this whole navigation thing working out just fine for us, as desired.
But we still have a little bit of work to do.
We need to write the code to actually save the employee to the database.
And we'll go through and do that now.

Allright, so this is step three of processing the form data to save the employee.
So I'll move into my controller here.

````java
@PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

    // save the employee
    employeeService.save(theEmployee);

    // use a redirect to prevent duplicate submissions
    return "redirect:/employees/list";
}
````

I'll add this `@PostMapping("/save")`.
I'll add this method here, `saveEmployee`, that'll pass in a model attribute, for `"employee"` that's from that form.
So that's the actual form data being passed in using that data binding.
Okay, I'll add some comments.
So I'll make use of the `employeeService.save`, pass in `theEmployee`.
So our controller's going to use the service to the repository to the backend database.
And then I'll do a `redirect` to the request mapping `/employees/list`.
And I want to prevent duplicate submissions.
So like I said earlier, just in case a user hits reload in their browser,
we want to prevent those duplicate submissions.
So again, we're making use of this `Post`/`Redirect`/`Get` pattern,
and you can get details at the website on the screen.
And now that's pretty much it.
So this is the real work of saving the employee to the database.
Let's run it.
So the app is up and running.
Go ahead and hit `Add Employee` and let's enter some employee data.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image24.png" alt="image24">
</div>

I'll hit `save` here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image25.png" alt="image25">
</div>

And looking here at the bottom.
So we have this new employee that was just added to our database.

But there's one small improvement I'd like to make.
I want to sort the data by last name.
Notice here that the data is unsorted, and I'd like to kind of fix that, make this very small improvement.
So what I can do is add a new custom method to my employee repository.
So I'll open it up here for a second, and I'll add a method to sort by last name.

````java
package com.luv2code.springboot.thymeleafdemo.dao;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();
}
````

So at **Spring Data JPA**, they'll parse the method name.
They'll look for a specific format and pattern, and they'll create the appropriate query for you behind the scenes.
So `findAllBy`, is part of the pattern.
And then we have `OrderByLastNameAsc`.
So they'll parse that for your order by clause for your given query statement.
So behind the scenes **Spring Data JPA** will say,
"_Okay that means from employee, order by last name, ascending_"
And this work happens all behind the scenes for you automagically.
And this is all part of that **Spring Data JPA** magic.
So for details on this, as far as the query methods, go to this link [here](https://www.luv2code.com/query-methods), 
and they'll give you details as far as the actual syntax, the pattern, and so on.
And that's from the official **Spring Data JPA** documentation.
So we need to make one modification in our code here.
So in our service, we need to update our service implementation to call this new method.
So I move into this `EmployeeServiceimpl`.

````java
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

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
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
````

I'll move into the `findAll()` method.

````java
public List<Employee> findAll() {
    //return employeeRepository.findAll();
    return employeeRepository.findAllByOrderLastNameAsc();
}
````

So instead of calling `employeeRepository.findAll()`, I'll make use of my new method name.
So that's our new method name, `findAllByOrderLastNameAsc`.
Let's go ahead and test it out.
So my application here, just do a little quick reload.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image26.png" alt="image26">
</div>

Notice here that our employees are sorted by last name.
So everything is coming in alphabetically.

</div>

## [CRUD Database Project - Update Employees]()
<div style="text-align:justify">

In this section, we'll use **Thymeleaf** to update an employee.
So here's a quick screenshot of the application.
Notice, on the far right, we have this new Action column for updating an employee,
and I'll run a quick demo here to show you how this works.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image27.png" alt="image27">
</div>

Okay, to update an employee, we simply move over here to the `Action` column, select the `Update` button for this employee.
So I'll choose a `Update` button here for `Trupti Bose`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image24.png" alt="image28">
</div>

And notice here how the form is pre-populated with her information that's coming from the database.
And then I'll simply make an update here for this given employee.
So I'll change her last name and also change her email address.
And then I'll hit `Save`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image29.png" alt="image29">
</div>

Okay, great. So notice here her last name has been updated and also her email address.
Allright, so here's the development process to update an employee:

* add an "Update" button to the page
* write the codes to pre-populate the form
* process the form data to actually save it in the database

Allright, step one, "Update" button.
So we'll have this new column called `Action`, and we'll have these "Update" buttons or links.
And so, each row will actually have an "Update" link that has the current `employeeID` embedded.
So when we actually click this link, 
we'll actually load the employee from the database and then pre-populate the form, just like you saw in the demo.
Allright, so let's look at the coding for creating this "Update" button.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image30.png" alt="image30">
</div>

So the button's going to actually include the `employeeId`.
Alright, so let's set up an `href` link then, `/employees/showFormForUpdate`, and then we pass an `employeeId`.
So here I have `employeeId` equals `tempEmployee.id`.
So in the background, it'll actually append to the URL `?employeeId=xxx`.
So it'll simply plug in that given employee's ID.
And this will be for each row for each employee.

Alright, step two of pre-populating the form.
So in our controller code for `showFormForUpdate`, they're going to pass in the `employeeId`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image31.png" alt="image31">
</div>

And remember that's passed in from that `href` link that we just saw above.
And then we'll use that `employeeId` to actually get the employee from the database or from the service.
So I'll have `employeeService.findById` pass in `theId`.
And then we simply set `theEmployee` as a model attribute to pre-populate the form.
And then I simply send this over to our `employee-form`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image32.png" alt="image32">
</div>

Allright, so, here's our employee form, as far as pre-populating.
So, remember, when this form is first loaded, they're going to call the getter methods.
So they're going to call `employee.getFirstName`, `employee.getLastName`, so on and so forth.
So, this is how the form is pre-populated thanks to the calls to the getters.
It'll set the initial values of this form based on information from our model attribute.
And then one other thing here is that we need to add a hidden form field to handle updates.
And so, this is required for doing updates.
So, basically, what this does is that it binds to the model attribute.
This tells your app which employee to update.

So step three, processing the form data to save employee.
This is easy, there's no need for new code, we can simply reuse our existing code.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image33.png" alt="image33">
</div>

It works the same for add or updates.
So on our `saveEmployee` method, we simply say `employeeService.save`.
And it already has the built-in logic for handling as far as doing an insert or doing an update.
And this is all already built into our given application.
So, we kind of get this for free.

So the first step is adding the "update" button to our page.
So I'll open up `src/main/resources/templates/employeessrc/main/resources/templates/employees/list-employees.html`.
And so I need to do is I need to add a new table heading here.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <!-- <title>Employee Directory</title> -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

<div class="container">
    <h3>Employee Directory</h3>
    <hr>
    <!-- Add a button -->
    <a th:href="@{/employees/showFormForAdd}" class="btn btn-primary btn-sm mb-3">
        Add Employee
    </a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="tempEmployee : ${employees}">
            <td th:text="${tempEmployee.firstName}" />
            <td th:text="${tempEmployee.lastName}" />
            <td th:text="${tempEmployee.email}" />
            <!-- Add update buttton link -->
            <td>
                <a th:href="@{employees/showFormUpdate(employeeId=${tempEmployee.id})}"
                    class="btn btn-info btn-sm">
                    Update
                </a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
````

So in this heading section, I need to add a new table heading for `action`.
So basically I'm just setting up a new column here for this table.
So down in the `tr` section I'm going to add a new button or a link for the `update` function.
Set up my `td` and I set up my `<a>` for anchor `update`.
And now just add the attributes here for this anchor tag.
So the `th:href` will be `employees/showFormUpdate` for `update`.
And I need to add some code here to embed or append the `employeeId`.
So here I have `employeeId` equals `tempEmployee.id`.
Allright, so this actually appends to the URL `?employeeId=xxx`.
So basically whatever the temporary employeeId is, it'll append it accordingly.
So that's how we basically embed that ID into that link.
So we can use it on the backend for pre-populating the form.
And I'll also dress it up here by applying some **Bootstrap** styles.

So let's go ahead and move to step two of adding a controller code to pre-populate the form.
So I'll move up here to my controller and I'll open up `EmployeeController.java`.

````java
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        // ...
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // ...
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        // ...
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form

        return "employees/employee-form";
    }
}
````

So I'll move down here in the code, and I'll add this new `@GetMapping` for `showFormUpdate`.
Okay, so we have this method here called `showFormForUpdate`.
It accepts a `@RequestParam` for `employeeId`.
That parameter's actually passed over by the link that we just created from the previous page.
So that actually has the `employeeId` 
that we can use in this given method for actually looking up that employee from the database.
Okay, so I'll add some comments here to the code.
We want to get the `"employee"` from the service or database, 
set `theEmployee` as a model attribute to pre-populate the form.
And then finally send this over to our form.
I'll do a return `"employees/employee-form"`, and now I'll kind of go back to the top here,
and actually get the employee from the service.
So I have `Employee`, `theEmployee = employeeService.findById()` and I pass in `theId`.
And again, that's the `employeeId` that was passed in from that embedded link.
Allright, so now that I have the employee from the database,
then I can set that employee as a model attribute to pre-populate the form.
Allright, so that's the basic coding there for `showFormForUpdate`.

Let's move over to our form.
We have some updates that we need to make.
So just a reminder, when the form is loaded, it's going to call the getter methods.
So they'll call `employee.getFirstName`, `employee.getLastName`.

````html
<body>
    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <p class="h4 mb-4">Save Employee</p>
        <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST">
            <!-- Add hidden from field to handle the update -->
            <input type="hidden" th:field="*{id}" />
            <input type="text" th:field="*{firstName}" placeholder="First name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{lastName}" placeholder="Last name" class="form-control mb-4 w-25">
            <input type="text" th:field="*{email}" placeholder="Email" class="form-control mb-4 w-25">
            <button type="submit" class="btn btn-info col-2">Save</button>
        </form>
        <hr>
        <a th:href="@{/employees/list}">Back to Employee List</a>
    </div>
</body>
````

And this process will basically pre-populate the form fields initially.
So whatever you have in your model attribute, 
those will be the initial values that you'll see on the form.
So this basically allows you to handle the pre-population of that given form.
This is the real magic right here.
And then we also need to add a hidden form field to handle the `update`.
The hidden form field is required for updates.
This binds to the model attribute.
This basically tells your app which employee to update.
Let's go ahead and run our application and test it out.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image34.png" alt="image34">
</div>

Allright, so here's my application.
Notice on the far right, we have this new column for `Action`.
We have these `update` buttons and each button has the `employeeId` embedded.
That's thanks to the code that we created a little bit earlier for setting up those **href** links.
I'll go ahead and do an update on this one employee here, `Trupti Bose`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image35.png" alt="image35">
</div>

And notice here how the form is pre-populated based on the `employeeId`.
And this is all from the code that we created in the background
as far as loading the employee from the database and setting it as a model attribute.
And this helps us with the pre-population part of it.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image36.png" alt="image36">
</div>

So I'll just go through here, and I'll just update her last name
and also do an update on her email address.
And then I'll hit `save`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image37.png" alt="image37">
</div>

And then success.
Notice here at the bottom we have `Trupti Sampath` and her new email address.
This is working out as desired.

</div>

## [CRUD Database Project - Delete Employees]()
<div style="text-align:justify">

In this section, we'll use **Thymeleaf** to delete an employee.
So here's a screenshot of our application.
I'll actually move into a quick demo of the application.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image38.png" alt="image38">
</div>

So over on the far right, we have this `delete` button for each one of the employees,
and I can select this employee here, `Yuri Petrov`, and do a delete:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image39.png" alt="image39">
</div>

The system will actually prompt us first before deleting, saying, "_Are you sure?_".
You hit `cancel` and, of course, no operation is taken.
But let's go ahead and try it again.
So let's go ahead and hit `delete`, and then we'll actually follow through on our action and hit `okay`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image40.png" alt="image40">
</div>

And now `Yuri` has been deleted from the database.
So this is all information coming from the database.
Those employees are being deleted, and we're updating the screen accordingly.

Alright, so here's our development process:

* add a delete button to the page
* add the controller code to actually perform the "`delete`" on the backend database

So, step one, "`delete`" button.
So, each row's going to have a "`delete`" button link.
So, the current `employeeId` is embedded in the link.
When clicked, it's going to prompt the user, just like we saw above,
and it'll delete the employee from the given database.

The "`delete`" button will actually include the `employeeId`.
Was very similar to what we did for the "`update`" button.
Just some very small changes here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image41.png" alt="image41">
</div>

So, we have this `href` set up to `/employees/delete`.
We pass in the `employeeId` of `tempEmployee.id`.
So again, just like the work we did for the "`update`" button.
And then at the bottom here, we're going to add some **JavaScript** to prompt the user before deleting,
just to kind of be nice to the user and give them a chance to kind of back out or cancel if they need to.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image42.png" alt="image42">
</div>

Now moving ahead to step two, we'll add the controller code to perform the "`delete`".
So, I'll set up this `@GetMapping` for `/delete`, pass in the `@RequestParam` for `"employeeId"`.
And again, remember, that's coming from that **href** link that we just created.
So, to delete the employee, we simply call `employeeService.deleteById()`.
And that's basically it.
And then finally, we simply do a `redirect` to our `/employees/list`,
and that's basically all the coding required to actually delete an employee from the database.

So step one is adding a "`Delete`" button.
So I'll move into my template `list-employees.html`.

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <!-- <title>Employee Directory</title> -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

    <div class="container">
        <h3>Employee Directory</h3>
        <hr>
        <!-- Add a button -->
        <a th:href="@{/employees/showFormForAdd}" class="btn btn-primary btn-sm mb-3">
            Add Employee
        </a>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <tr th:each="tempEmployee : ${employees}">
                <td th:text="${tempEmployee.firstName}" />
                <td th:text="${tempEmployee.lastName}" />
                <td th:text="${tempEmployee.email}" />
                <!-- Add update buttton link -->
                <td>
                    <a th:href="@{/employees/showFormForUpdate(employeeId=${tempEmployee.id})}"
                       class="btn btn-info btn-sm">
                        Update
                    </a>
                    <!-- Add "delete" button/link -->
                    <a th:href="@{/employees/delete(employeeId=${tempEmployee.id})}"
                       class="btn btn-danger btn-sm"
                       onclick="if (!(confirm('Are you sure you want to delete this employee?')))">
                        Delete
                    </a>
                </td>
                </tr>
            </tbody>
        </table>
    </div>

</body>
</html>
````

So I'll move down here to where I have the "`Update`" button, and I'll keep everything else the same
because I want to pin that `employeeId` to the end of the URL or embed that `?employeeId=xxx` on the URL link.
And also one other thing here for the "`Delete`" button, 
we actually want to prompt the user before performing the delete.
So I'll make use of some small **JavaScript** here to handle this support.

So at step two, we need to add the controller code for "`Delete`".
So I move down here in my controller and I'll add this new `@GetMapping` for "`Delete`".

`````java
@GetMapping("/delete")
public String delete(@RequestParam("employeeId") int theId) {

    // delete the employee
    employeeService.deleteById(theId);

    // redirect to the /employees/list
    return "redirect:/employees/list";
}
`````

So I have this method here, `delete`, takes a `@RequestParam` for the `employeeId`.
And now, I'll just write some quick comments here.
So we want to delete the employee and then redirect to our `employees/list`.
I call `employeeService.deleteById()` and I pass in `theId`.
And then, we just do the standard thing here with the return on `"redirect:/employees/list"`.
And so that's the basic coding here for delete.
Very simple, very straightforward.
Allright, so I'd like to test this feature out and see how it works for us.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image43.png" alt="image43">
</div>

And so, we simply bring up our application and cool.
So notice on the far right, we have this new "`Delete`" button for each one of those employees.
So it has the `employeeId` embedded in that link.
Do a delete on `Trupti`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image44.png" alt="image44">
</div>

We'll hit okay.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/07-spring-boot-spring-mvc-crud/images/image45.png" alt="image45">
</div>

And then, success.
So `Trupti`'s been deleted also,
so we can see that the delete functionality is working out as desired.

</div>