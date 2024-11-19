# [JPA/Hibernate Advanced Mappings]()

## [Overview]()
<div style="text-align:justify">

In this section, we'll cover **Hibernate Advanced Mappings**.
So far in the course, we've covered a very basic mapping.
We had a **Student** class, and then we mapped it to a **Student** table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image01.png" alt="image01">
</div>

And that worked fine just to kind of get us started with **Hibernate**.
But now, let's go ahead and move forward into some more advanced features.
So, with advanced mappings in your database,
you most likely will have multiple tables,
and you'll also have relationships between tables.
And we need to somehow model this with **Hibernate**.
We'll look at three different types of advanced mappings.
We'll look at `One-to-One`.
We'll also cover `One-to-Many` and `Many-to-One`.
We'll take a look at `Many-to-Many` mappings.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image02.png" alt="image02">
</div>

Alright, so let's start with the `One-to-One` mapping.
So, an instructor can have an instructor-detail identity.
And this is very similar to like having an instructor profile.
So you have an instructor, and then you also have the actual instructor detail.
So, the instructor has basic information, like their first name, last name, and email.
The instructor detail has just more profile-specific information,
like their YouTube channel, their hobby, their LinkedIn channel, their Twitter handle,
their Facebook page, so on and so forth.
Just more details about a given instructor.
The key here is that this information is in two separate tables.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image03.png" alt="image03">
</div>

Let's take a look at `One-to-Many` mappings.
So, an instructor can have many courses.
So we have an instructor, and then multiple courses out here that are authored
or created by that instructor.
So this is the classic `One-to-Many` mapping.
Now, in this example, we're going to keep it simple, such that a course only has one instructor.
I know there are scenarios where a course can have multiple instructors,
but for right now, we'll just keep it simple.
We'll cover another scenario for the `Many-to-Many` later.
So right now we'll just cover `One-to-Many`.
Now, the actual opposite or inverse of this is the `Many-to-One` mapping.
So, here we have many courses that can map to one instructor.
So it's really just an inverse or the opposite of what we have here with the `One-to-Many` mapping.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image04.png" alt="image04">
</div>

And then, here's the `Many-to-Many` mapping.
So, basically, a course can have many students and a student can have many courses.
So here's our list of courses on the left-hand side
and a list of all the students on the right-hand side.
And then you have this just wild mixture of students assigned to different courses,
courses having students from all over.
So this is a classic example here of a `Many-to-Many` mapping.

So now to take advantage of this, we really have to cover some important database concepts.
So we need to cover the idea of primary keys and foreign keys, and also the idea of cascading.
So a **primary key** is basically an identifier.
It identifies a unique row in a table, and we've seen examples already of making use of primary keys.

Now the new item or the new player here is a **foreign key**.
This **foreign key** is basically what we use to link tables together.
It's a field in one table that refers to the **primary key** in another table.
So that's an important concept, **foreign key**s, and I'll actually show you some examples of this one.

<table align="center">
    <thead>
        <th>id</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>instructor_detail_id</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>1</strong></td>
            <td>Chad</td>
            <td>Darby</td>
            <td>100</td>
        </tr>
        <tr>
            <td><strong>2</strong></td>
            <td>Madhu</td>
            <td>Patel</td>
            <td>200</td>
        </tr>
    </tbody>
</table>

So for **foreign key**s, I have this one table called `instructor`, have my normal instructor information.

<table align="center">
    <thead>
        <th>id</th>
        <th>youtube_channel</th>
        <th>hobby</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>100</strong></td>
            <td>www.luv2code.com/youtube</td>
            <td>Luv 2 Code!!!</td>
        </tr>
        <tr>
            <td><strong>200</strong></td>
            <td>www.youtube.com</td>
            <td>Guitar</td>
        </tr>
    </tbody>
</table>

We also have a separate table `instructor_detail` that has all of our details for that given instructor.
Now what we can do is make use of the `instructor_detail_id` column.
So this is basically a **foreign key** column that we set up in our `instructor` table.
This basically has the link between the `instructor` and the actual `instructor_detail`.
So for Chad Darby, my `instructor_detail_id` is `100`.
That links over to the `primary key` or the `id` in the `instructor_detail`.
That's the idea of a **foreign key**, links two tables together.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image05.png" alt="image05">
</div>

Now another database concept that we need to cover here is **cascading**.
You can **cascade** operations.
So **cascade** basically means you can apply the same operation to related entities.
Allright, so here we have this `Instructor` and `Instructor Detail`.
If I were to actually save the `Instructor` then it'll also cascade that operation
and apply that same operation to `Instructor Detail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image06.png" alt="image06">
</div>

So I perform a save on `Instructor`.
It does the same operation on `Instructor Detail`.
That's **cascading**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image07.png" alt="image07">
</div>

Now also, if we delete an `Instructor`, then we should probably also delete their `Instructor Detail`.
So if they no longer have an account, well why should we even keep their detail information around?
So this is known as a cascading delete.
So here the `Instructor` and `Instructor Detail`, 
if we go in and perform a delete on the `Instructor`, 
then we should also delete their `Instructor Detail`, **cascading delete**.
And I'll show you a little example of this here,
where I have the `Instructor` `Darby`, we're going to delete that instructor:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image08.png" alt="image08">
</div>

Then it'll also find that related entity, the `instructor_detail`, 
and also delete that `instructor_detail` entry for that given `instructor`.
So that's an example of **cascading delete**.
But now with cascading delete, you have to be careful here because it really depends on the use case.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image04.png" alt="image09">
</div>

So in this scenario, with `many to many` with courses and students, should we do a cascade delete here?
So if we delete a student, should we also delete the course?
You should remove the student from the course, but you shouldn't delete the course.
So just, it really depends on your use case and your application.
But the nice thing about it is that, as a developer,
you have fine grain control over configuring the cascading,
and I'll show you all of that in some of the later sections.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image03.png" alt="image10">
</div>

Also, when you retrieve data you have this idea of `Eager` vs `Lazy` Loading.
So the question is if I retrieve the data, should we retrieve everything?
So here I have an instructor and an instructor also has courses.
So when I retrieve the instructor object, should I get all the courses for that instructor immediately 
or should I grab the courses only on the request?
So `Eager` will retrieve everything in one shot.
`Lazy` will retrieve the data on request, 
and I'll show you how to configure and set that up when we make use of these advanced mappings.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image11.png" alt="image11">
</div>

Alright, here's an example of making use of a `Uni-Directional` relationship.
So with `Uni-Directional`, you have an `Instructor` 
and then you actually have the `Instructor Detail`.
So you load the `Instructor` object, and then from there, you can access the `Instructor Detail`.
So it's really a one way relationship.
So that's `Uni-Directional`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image12.png" alt="image12">
</div>

They also have the idea of `Bi-Directional`.
So here we have the `Instructor`, and then they have their relationship with the `Instructor Detail`,
but then we can also go the other way, so we can actually load the `Instructor Detail`
and have a reference to the given `Instructor`.
So that's `Bi-Directional`, 
and I'll show you how to do all the coding here for `Uni-Directional` and also for `Bi-Directional`.
So we'll cover all of that in the following sections.

</div>

## [@OneToOne Mapping - Overview]()
<div style="text-align:justify">

In this section, we're going to cover **Hibernate** `One-to-One` mapping.
Basically, an `instructor` can have an `instructor detail` entity.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image13.png" alt="image13">
</div>

Like I mentioned earlier, this is very similar to an `instructor` profile.
So we'll have an `instructor`, and they will have an actual "instructor profile".
So that's a `One-to-One` relationship.
And we'll actually model this in the database using two separate tables.
Now this example will be `Uni-Directional`.
So we'll basically start with the `instructor`,
and then we'll have a one-way relationship with the `instructor detail`.
In following sections, we'll actually move into the use case of having a `Bi-Directional` relationship.
We'll cover that later.
Alright, so let's go ahead and look at the development process for `One-to-One`:

* Prep work and that's basically defining our database tables and setting up that foreign key relationship
* Create the `InstructorDetail` class 
* Create the `Instructor` class
* Create the main application to kind of pull it all together

And as usual, we'll cover all this step by step.
Okay, so let's go ahead and start with the `instructor_detail` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image14.png" alt="image14">
</div>

So here's the diagram here above.
So the `instructor_details` is going to have an `ID`, the `youtube_channel`, and the `hobby`.
The ID's going to be the **primary key**, and we'll auto increment that ID.
So here's the actual **SQL** script that we will use to actually create this table.

````sql
CREATE TABLE `instructor_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

...
````

So this **SQL** script is something that you'll run your **MySQL** workbench or any **MySQL** admin tool.
So we'll create our table `instructor_details`, and then we'll define those three fields,
`ID`, `youtube_channel`, and `hobby`.
And note here the `ID` is set up to be `AUTO_INCREMENT`.
We'll add one other entry here for `PRIMARY KEY`.
So we're basically telling the system, "_Hey, the **primary key** for this table is `ID`_".

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image15.png" alt="image15">
</div>

Allright, now let's go ahead and look at our other table `instructor`.
The `instructor` table's going to have five fields.
It's going to have the `ID`, `first_name`, `last_name`, `email`, and `instructor_detail_id`.
Again, here's the actual sql for creating the table:

````sql
...

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  ...
);
````

The first four lines are very straightforward.
`id`, `first_name`, `last_name`, `email`. 
We've seen that already.
And the `primary key` is `id`.
And the `id` is set up for `AUTO_INCREMENT`.
We also have a field here for `instructor_detail_id` that'll actually help us link to the other database,
but we haven't really set that up yet.
We simply have a handle here, but we haven't really defined the relationship.
So at this point, we have two separate tables.
We have an instructor detail table and an instructor table.
But we somehow need to link those tables together.
And remember, we can do this by making use of foreign keys, right?

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image16.png" alt="image16">
</div>

So the `FOREIGN KEY` will allow us to link the tables together.
So basically a field in one table is going to refer to the `PRIMARY KEY` in another table.
So we'll actually have a field in the `instructor` table.
That'll be the `instructor_detail_id`.
It's going to refer to the `PRIMARY KEY` in the `instructor_detail` table.
Allright, let's kinda walk through this with an example.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image08.png" alt="image17">
</div>

So we'll have the `instructor` table and we'll also have the `instructor_detail`.
We'll make use of this `instructor_detail_id` which is our `FOREIGN KEY` column.
And then this will basically map over or link to the actual `instructor_detail` table. 
So we can find the detail for a given `instructor`.
Now, the big question is, well, how do you define the `FOREIGN KEY`?
So, you know, we've seen these pretty diagrams here, but how do you set up that relationship?
I mean, what's the **SQL** coding for that?

````sql
... 

CREATE TABLE `instructor` (
  
  ...
  
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
  REFERENCES `instructor_detail` (`id`) 
);
````

Well, here it is right here.
So we have our **SQL** file.
We're going to add this new item in here called `CONSTRAINT`.
So we're going to set up a `CONSTRAINT`, which is a `FOREIGN KEY`,
and it's going to map the `instructor_detail_id`,
and it'll actually reference the `instructor_detail` table,
and it'll reference the `id` in that `instructor_detail` table.
So that's how you set up the `FOREIGN KEY`.
Allright, so a little bit more on the `FOREIGN KEY`.

* Its main purpose is to preserve the relationship between the tables.
And you may hear this buzzword, "**Referential Integrity**".
* This helps prevent operations that would destroy the relationship between those tables.
* It also ensures that only valid data is inserted into the `FOREIGN KEY` column.
So that `FOREIGN KEY` column can only contain valid reference to a `PRIMARY KEY` in another table.
If not, the database will actually throw some errors, and it'll tell you about it.

So make sure you follow the rules.
So that's it.
So we kind of covered the basics here on how to set up our database table 
and also how to set up our foreign key.
And some of the following sections will actually get into 
the actual **Java** coding for setting up this `One-to-One` mapping.

Alright, we're just moving along with our development process.
We've already covered step one, the prep work of defining our database tables.
Now what we're going to do is move forward with steps two through four,
creating our instructor detail class, creating the instructor class, and also creating our main application.
So step two of creating the `InstructorDetail` class,
so we already have the table that's been created for us, the `instructor_detail`.
Now we need to create the actual **Java** class and map that class to this given table.
So here's my `InstructorDetail` class.

````java
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="youtube_channel")
    private String youtubeChannel;
    
    @Column(name="hobby")
    private String hobby;
    
    // constructors & getters & setters
}
````

At the top, I have `@Entity` and I have `@Table`.
I give name, `instructor_detail`.
That's the actual name of the table that we're mapping to.
Then I go through and actually set up a reference for the `id`.
Then I map the `youtube_channel`, and then I map the `hobby`.
And then we go through, and we can set up constructors, getters, and setters.
So you've seen all this stuff before.
There's really nothing new here.
Basically, we're mapping a class to a given table.
Now we'll actually go through and create the `Instructor` class.
So we have an `instructor` table.
Now we need to create a **Java** class and actually map it to this table.
So here's our class `Instructor`:

````java
@Entity
@Table(name="instructor")
public class Instructor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    // constructors & getters & setters
}
````

At the top, we give `@Entity`, `@Table`, `name="instructor"`, 
that's the actual table name, and then we map the `id`,
then we do `firstName`, `lastName`, and `email`.
This is all kind of more of the same.
So basically here at this point, we've mapped this `instructor` to the table.
Now here, we really just have two independent classes right now.
We have an `InstructorDetail` class and an `Instructor` class, but they're not linked.
They're not mapped together, and we need to take care of that.
So what we can do is make use of this `@one-to-one` annotation.
So this will basically allow us to map our `InstructorDetail` and our `Instructor` class together.
So here's the coding here for the `Instructor`:

````java
@Entity
@Table(name="instructor")
public class Instructor {
    ...
    
    @OneToOne
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;
    
    ...
}
````

We have this new entry here.
We have `@one-to-one`, so that's a new annotation for our one-to-one relationship,
and then we need to tell them how to hook this up, so we're basically saying,
"_Hey, **Hibernate**, for the `InstructorDetail`, we have a `JoinColumn` called `instructor_detail_id`.
That's the `instructor_detail_id` that's defined in this `instructor` table_".
Then using that information, **Hibernate** can go off and use the foreign key,
find the `instructor_detail` record, and load that data accordingly.
So **Hibernate** will go ahead and do that work for you in the background, and then in memory,
you'll have an `instructor` object along with its related entity, the `instructor_detail`,
and that's all handled by **Hibernate** and the reference here for this `@one-to-one` and the `JoinColumn`.
Allright, so we need to make a small digression here and cover some concepts,
because we need to learn more about **cascade** types.
But before we can really understand cascading types,
we need to learn a bit more about the **Entity Lifecycle**.

<table align="center">
    <thead>
        <th>Operations</th>
        <th>Description</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>Detach</strong></td>
            <td>If entity is detached, it is not associated with a Hibernate session</td>
        </tr>
        <tr>
            <td><strong>Merge</strong></td>
            <td>If instance is detached from session, then merge will reattach to session</td>
        </tr>
        <tr>
            <td><strong>Persist</strong></td>
            <td>Transitions new instances to managed state. Next flush / commit will save in db.</td>
        </tr>
        <tr>
            <td><strong>Remove</strong></td>
            <td>Transitions managed entity to be removed. Next flush / commit will delete from db.</td>
        </tr>
        <tr>
            <td><strong>Refresh</strong></td>
            <td>Reload / sync object with data from db. Prevents stale data</td>
        </tr>
    </tbody>
</table>

The entity lifecycle is basically a set of states that 
a **Hibernate** entity can go through when you're using it in your application.
So here we have a `Detach`.
Basically, if an entity is detached, then it's not associated with a **Hibernate** session.
Then we have `Merge`, so if an instance is detached from the session, 
then we can actually merge it to reattach it to the session.
We can make use of `Persist`, basically allowing you to take a new instance and transition it to a managed state,
and basically, on your next flush or commit, it'll actually save it to the database.
Then we have the idea of `Remove`, so that basically takes a managed entity 
and remove it so the next flush commit will actually delete it from the database, 
and then we have the idea of `Refresh`.
So `Refresh` allows you to kind of reload or sync your object with data from the database.
This actually helps you prevent from having stale data in memory,
which is different from data in the actual database.
Let's look at a nice little diagram to kind of help us pull it together.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image18.png" alt="image18">
</div>

This is very useful if you're a visual learner.
So we have this state where an object is a new object
we call a new keyword, or it's transient.
Then what we'll do is we can actually save it or persist it, and at that point,
it's like a persistent object or a managed object.
If we wanted to, we could do a rollback or create a new object, and it's in the new/transient state.
If we wanted to sync the object with information from the database, then we can do a refresh.
Next, if we have a persistent object, we can do a commit, rollback, or close,
and that'll actually make that object detached, so it's not associated with a given **Hibernate** session.
If we'd like to reattach that object to the **Hibernate** session, 
then we simply get a reference to that object, and we simply do a merge on it.
If we do a delete or remove, then that object is now in the removed state.
We could also do a rollback or persist to actually make it available, and make it back into a managed state.
Now, up top here in the top right, we can do a commit on a removed object,
so now it's removed from the database, and now it's just in a transient state.
And then we could say rollback, so that'll basically take the object and move it back to the detached state.
Now, again, a lot of stuff here, don't worry about every special transition state,
but the key is that you understand the major concepts, and we'll make use of some of these operations,
we'll have our objects go through different states, and you'll kind of see it work in real time.
It's good to have a good example to kind of help bring everything home,
but at least we want to cover these concepts upfront, 
because you're going to see them when we make use of some other techniques,
so kind of like just giving you a heads-up. 

Now with cascading, remember you can cascade operations.
This basically means you're going to apply the same operation to related entities.
So here we have our instructor, and then we have instructor detail,
so if we were to actually save our instructor, then it'll also save that instructor detail.
As a developer, we can specify the actual cascade type that we want them to use.
So here's a list of the cascade types.

<table align="center">
    <thead>
        <th>Cascade Type</th>
        <th>Description</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>PERSIST</strong></td>
            <td>If entity is persisted/saved, related entity will also be persisted</td>
        </tr>
        <tr>
            <td><strong>REMOVE</strong></td>
            <td>If entity is removed/deleted, related entity will also be deleted</td>
        </tr>
        <tr>
            <td><strong>REFRESH</strong></td>
            <td>If entity is refreshed, related entity will also be refreshed</td>
        </tr>
        <tr>
            <td><strong>DETACH</strong></td>
            <td>If entity is detached(not associated w/ session), then related entity will also be detached</td>
        </tr>
        <tr>
            <td><strong>MERGE</strong></td>
            <td>If entity is merged, then related entity will also be merged</td>
        </tr>
        <tr>
            <td><strong>ALL</strong></td>
            <td>All above cascade types</td>
        </tr>
    </tbody>
</table>

The first cascade type is `PERSIST`.
So if an entity is persistent, then the related entity will also be persistent.
So in our case, when the `instructor` is saved, then the related item, the `instructor_detail`, will also be saved.
We also have the cascade type of `REMOVE`.
So if the entity is removed or deleted, the related entity will also be deleted.
Again, you delete the `instructor`, we delete the related item, `instructor_detail`.
There's also `REFRESH`, so you can refresh the entity, meaning sync it from the database,
and the related item will also be refreshed.
And they also have `DETACH` and `MERGE`.
And then finally `ALL`.
So `ALL` is a combination of all of the above cascade types together.
So here's how we configure a cascade type.

````java
@Entity
@Table(name="instructor")
public class Instructor {
    ...
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;
    
    ...
}
````

So `@OneToOne`, and I say, "`cascade=CascadeType.ALL`".
That means that all operations we apply to our entity, like `instructor`, 
it'll also cascade to our related entity, `instructor_detail`.
So they'll apply for a saving, deleting, and so on.
Now, it's really important to note, by default, no operations are cascaded,
so if you don't specify `cascade`, then none of the operations will `cascade`.
So you have to explicitly reference a given cascade type that you'd like to apply for your given relationship.
Now, also, we're moving along here.
This is how you can configure multiple cascade types
if you want finer-grain control over which cascade types are applied, because you may not want all.

````java
@OneToOne(cascade={CascadeType.DETACH,
                  CascadeType.MERGE,
                  CascadeType.PERSIST,
                  CascadeType.REFRESH,
                  CascadeType.REMOVE})
````

So here, you can list each cascade type individually.
You simply provide a comma-delimited list,
and you specify the cascade types that you want to apply for a given relationship.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image19.png" alt="image19">
</div>

Allright, let's take a look at step four of creating a **Spring Boot - Command Line App**.
This will allow us to focus on **JPA/Hibernate** 
and also will leverage our **DAO** pattern, as in the previous sections.
So we'll have our main application.
It'll talk to our data access object.
We'll call it `AppDAO`, and that'll have all the appropriate code for interfacing with the backend database.
And we used this pattern earlier in the course.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
}
````

Allright, starting with our first step here of defining a **DAO** interface,
we'll have this interface called `AppDAO`, and then we'll have this one method `save`.
You pass in `theInstructor`.
It'll actually save that instructor and its associated components.
Now, let's define the **DAO implementation**.

````java
@Repository
public class AppDAOImpl implements AppDAO {
    
    // define field for entity manager
    private EntityManager entityManager;
    
    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }
}
````

We have this class `AppDAOImpl`, implements `AppDAO`.
We have this **EntityManager** field.
And we also make use of this constructor injection here.
We inject the `entityManager`.
We've seen a lot of this coding before.
And then the new pieces here at the bottom where we actually save `theInstructor`,
so we make use of this `entityManager.persist`, and we pass in `theInstructor`.
Now, one thing that's really important here about this `entityManager.persist`,
this will also save the `instructor_detail` object.
Because we're making use of this `CascadeType.ALL`,
it's going to cascade that `persist` operation from `instructor`,
also apply that same `persist` operation to `instructor_detail`.
So we have this `one-to-one`, and we'll actually save both of those items
by simply making a call so that `entityManager.persist` for `theInstructor`.

````java
@SpringBootApplication
public class MainApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            createInstructor(appDAO);
        }
    }
}
````

Now, let's move ahead here and update our main application.
In our `commandLineRunner` will inject the `AppDAO`,
and then we'll make use of this `AppDAO` in this method here `createInstructor`, passing in `AppDAO`.
This method's defined right here:

````java
private void createInstructor(AppDao appDao) {
    
    // create the instructor
    Instructor tempInstructor = 
            new Instructor("Chad", "Darby", "darby@luv2code.com");
    
    // create the instructor detail
    InstructorDetail tempInstructorDetail = 
            new InstructorDetail(
                    "http://www.luv2code.com/youtube",
                    "Luv 2 code!!!");
    
    // associate the objects
    tempInstructor.setInstructorDetail(tempInstructorDetail);
    
    // save the instructor
    System.out.println("Saving instrutor: " + tempInstructor);
    appDao.save(tempInstructor);
    System.out.println("Done!");
}
````

First off, we create `tempInstructor` by saying `new Instructor()`.
Then we create `tempInstructorDetail` by making use that `new InstructorDetail()`.
Then we associate the objects.
So we're make use of this `tempInstructor.setInstructorDetail()`,
and I give a reference to `tempInstructorDetail`.
So we're basically connecting those two objects together for this `one-to-one` relationship.
And then finally, we make use of this `appDAO.save`, and we pass in `tempInstructor`.
And now, remember here, this will also save `tempInstructorDetail` object,
because we're making use of the `CascadeType.ALL`.
Behind the scenes, in `AppDAOImpl`, they'll actually delegate the call to `entityManager.persist`.
So by simply persisting the `tempInstructor`, we're also persisting the associated `tempInstructorDetail`.

</div>

### [@OneToOne Mapping - Database Setup]()
<div style="text-align:justify">

In this section, what we'll do is actually run our database scripts to create our tables.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image16.png" alt="image20">
</div>

In particular, we're going to have tables created for the `instructor` and also the `instructor_detail`.
This is for our `one-to-one` relationship, and we'll also set up the foreign key with our `instructor` table,
pointing over to our `instructor_detail` table.
Right now, I'll basically go through and open up the scripts, walk through the scripts,
and then execute them just to set up this table structure and the table relationship or mapping.
Now let's go ahead and open up **MySQL Workbench**
and let's go ahead and log in and now what I'll do is I'll open that **SQL** script.

````sql
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;

CREATE SCHEMA `hb-01-one-to-one-uni`;

use `hb-01-one-to-one-uni`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
  REFERENCES `instructor_detail` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

````

So at the beginning up here, we're going to create this schema,
we'll call it `hb-01--one one-to-one-uni`.
So a schema is basically just a collection of tables.
So actually create that schema and then we'll use it.
We'll disable the foreign key checks for now.
We'll also drop the previous table if it's out there, `instructor_detail`.
And then we'll go ahead and create the `instructor_detail` table.
Now, this table's going to have three fields.
It's going to have an actual `id`, which we'll set to auto increment.
It'll have an entry here for the instructor's YouTube channel, and also an entry for the instructor's hobby.
So the primary key for this table is going to be the `id`, and again, it's set to auto increment.
Allright, so let's go ahead and do a similar thing here for the `instructor` table.
So, the first thing we'll do is we'll drop it if it exists,
and then we'll go ahead and create the table `instructor`.
Now ,the `instructor` table has five fields, so it has the `id` set up for auto increment,
`first_name`, `last_name`, `email`, and then we have the `instructor_detail_id`.
That's going to be our link to the actual detail table.
And we set this up by making use of a foreign key.
So this foreign key is basically a link from our table that points over to the detail table.

````sql
KEY `FK_DETAIL_idx` (`instructor_detail_id`),
CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
REFERENCES `instructor_detail` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
````

So, this is where we kinda set up the foreign key.
So we're setting up a foreign key constraint.
So we're basically saying that our constraint foreign key of `instructor_detail_id`
references the `instructor_detail` table, the `id` column,
so we're referencing the `id` column in the `instructor_detail` table.
And as far as the on deletes and no action, that's for cascading stuff,
we're not going to define it here in the **SQL** script.
We'll actually manage that portion of it using **Hibernate**, and I'll show you that later.
Allright, so that's basically it for creating the tables.

So now I'll go through and actually execute the script.
So just hit that yellow lightning bolt on your toolbar,
and I'll actually execute this script and do all the work.
So over here on the left-hand side, just do a right-click and choose `Refresh All`
just so we can see our new schema that was created.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image21.png" alt="image21">
</div>

And there it is right there, `hb-01-one-to-one-uni`,
so that's the new schema that's created.
And again, a schema is a collection of tables.
And so we have our tables here, `instructor` and `instructor_detail`,
and we can go ahead and set this as our default schema.
And actually to be technical, a schema is more than just tables.
You can have views, store procedures, and functions, but for right now, we're just focusing on tables.
So let's go ahead and run some queries on these tables.
And right now, this `instructor` table's empty.
A similar thing here for `instructor_detail`.
And again, this is empty, so right now it's empty.
We'll actually write some **Java** codes to actually populate these tables in the following sections.
But what I want to do is actually just kinda generate the database diagrams that you just saw earlier.
So you can actually reverse engineer.
So I'll go to `Database` and choose `Reverse Engineer`,
and it'll basically read the tables and then generate diagrams for you.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image22.png" alt="image22">
</div>

Allright, so here's our database diagram for our `instructor` table and `instructor_detail`.
However, there's one minor cosmetic issue.
At times, the **MySQL Workbench** may not be able to figure out the correct cardinality for the items.
Here, we can simply edit the relationship to be `one-to-one` 
just so the diagram looks fine as far as the cosmetic diagram.
But don't worry, our codes will actually manage this as a `one-to-one`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image23.png" alt="image23">
</div>

Here, I'll go ahead and select the line for `edit relationship`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image24.png" alt="image24">
</div>

And then down at the bottom, select the tab for foreign key.
And then for `cardinality`, choose the `cardinality` of `one-to-one`.
And then that'll update the diagram accordingly.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image25.png" alt="image25">
</div>

And in our code in the following sections,
we'll actually make use of the `one-to-one` annotation to model this relationship accordingly.

</div>

### [@OneToOne Mapping - Project Setup]()
<div style="text-align:justify">

Let's get started with creating our **Spring Boot project**.
So, on a web browser, let's go to the spring initializer website, `start.spring.io`.
At the website, in the **Project** section, choose **Maven**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image26.png" alt="image26">
</div>

Make sure your language is selected as **Java**, and you can choose the latest **Spring Boot** version.
Down in the **Project Metadata**, let's go ahead and set up our group as `com.luv2code`.
And then, for **Artifact ID**, I'll call it `cruddemo`.
Over in the **Dependencies** section, let's go ahead and add a new dependency here for the **MySQL Driver**.
And also, let's add a dependency here for **Spring Data JPA**.
Allright, and just as a checkpoint, make sure you have these two dependencies, as I have shown here above.
So, let's go ahead and click on `Generate` and get this project created for us and download it to our system.
We have the `cruddemo.zip`.
I'll unzip it.
I'll move into our `dev-spring-boot` directory, and into the `09-directory`.
And in this folder here, I'll actually paste that item.
And rename to `01-jpa-one-one-uni`.
Now, let's go ahead and open this in **IntelliJ**.
Now, let's get started with creating our command line app.
I'll open up my main **Spring Boot** application here,
and I'll define this new bean here for `CommandLineRunner`.

````java
package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("Hello World");
		};
	}
}
````

This is for creating a command line application.
And again, remember, the `CommandLineRunner` is from the **Spring Boot** framework.
This little snippet of code who will be executed after the **Spring Beans** have been loaded,
and we can use it within this given method.
I'll make use of this `return runner`, this Lambda expression, and I'll simply say, "`Hello World`".
So again, this is a **Java** lambda expression.
It's simply like a shortcut notation for providing an implementation of the `CommandLineRunner` interface.
And at the moment, we simply just have "`Hello World`" as our custom code, 
but later on, we'll actually make use of some beans that we've created, 
and we'll use 'em within this given setup.
So right now, like I said earlier, we're just kinda setting up the infrastructure,
just the framework, and we'll kinda expand on this later,
but at least we have this piece set up and ready to go.

Okay so we have the `CommandLineRunner` in place.
We need to do one more thing before we move forward,
and that's setting up our `application.properties` file.
In our `application.properties` we need to add our **JDBC** database connection information.

````properties
spring.datasource.url=jdbc://mysql://localhost:3306/hb-01-one-to-one-uni
spring.datasource.username=springstudent
spring.datasource.password=springstudent
````

We need to add an entry here for our data source URL.
So we give the property name of `spring.datasource.url`, and then we give that at your url.
And that's that database schema that we set up in previous sections.
We need to set up another property here for the `spring.datasource.username`,
and recall we have a user called `springsstudent`.
I'll do a quick copy-paste here, and I'll simply update the property name for `spring.datasource.password`.
And again, remember our password's the same as our username purely for academic purposes.
So now at this point we can go ahead and run this application and test it out.

`````text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.5)

INFO 30272 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Starting CruddemoApplication using Java 23.0.1 with PID 30272 (D:\JAVA_STUDY\Github\dev-spring-boot\09-spring-boot-spring-jpa-hibernate-advanced-mappings\01-jpa-one-one-uni\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\09-spring-boot-spring-jpa-hibernate-advanced-mappings\01-jpa-one-one-uni)
INFO 30272 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
INFO 30272 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
INFO 30272 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 6 ms. Found 0 JPA repository interfaces.
INFO 30272 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
INFO 30272 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@4d21c56e
INFO 30272 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
INFO 30272 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
INFO 30272 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.3.Final
INFO 30272 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
INFO 30272 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
INFO 30272 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
INFO 30272 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
INFO 30272 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Started CruddemoApplication in 2.022 seconds (process running for 2.325)
Hello World
INFO 30272 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
INFO 30272 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
INFO 30272 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
`````

And good stuff here.
We can go look at some of the logs here.
So it says that it's starting our application.
So it says it added a connection so that means that the database connection was successful.
So if you see it's able to successfully connect to your database.
And then also if we scroll over a bit, then we see "`Hello World`".
So that's our custom code that we set up for our command line application.
So we know that our code is being executed accordingly, which is good.
And then once it's done, then the application stops.
It's just a simple command and application that'll run and perform an operation.
One thing I'd like to do is add, when we're running these standalone applications, 
I really want to focus on doing some operation and then printing out the results.
I want to turn off some of the chatter and one piece of chatter I want to turn off is this "**Spring Boot** banner".
When I'm running a standalone application,
I really don't want to see the "**Spring Boot** banner" every time.
There's no need for you to remind me every time I run the application.
So I'd like to turn off this banner, and you can easily do this via a configuration.
So let's swing over to our `application.properties`, 
and then we'll add an entry here to turn off the "**Spring Boot** banner".

````properties
# Turn off the Spring Boot banner
spring.main.banner-mode=off
````

I'll simply add this entry here, `spring.main.banner-mode` equals `off`.
That'll actually turn off the "**Spring Boot** banner" so be sure to save that file.
And then when you run the application again:

````text
INFO 9940 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Starting CruddemoApplication using Java 23.0.1 with PID 9940 (D:\JAVA_STUDY\Github\dev-spring-boot\09-spring-boot-spring-jpa-hibernate-advanced-mappings\01-jpa-one-one-uni\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\09-spring-boot-spring-jpa-hibernate-advanced-mappings\01-jpa-one-one-uni)
INFO 9940 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : No active profile set, falling back to 1 default profile: "default"
INFO 9940 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
INFO 9940 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 5 ms. Found 0 JPA repository interfaces.
INFO 9940 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
INFO 9940 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@56da7487
INFO 9940 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
INFO 9940 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
INFO 9940 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.3.Final
INFO 9940 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
INFO 9940 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
INFO 9940 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
INFO 9940 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
INFO 9940 --- [           main] c.luv2code.cruddemo.CruddemoApplication  : Started CruddemoApplication in 1.856 seconds (process running for 2.144)
Hello World
INFO 9940 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
INFO 9940 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
INFO 9940 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
````

you'll see that it runs without the "**Spring Boot** banner" being displayed every time.
Again, we're just trying to minimize some of the logging and minimize some of the chatter
that we see when we run our application.
And then also I want to reduce the logging level.
So I want to set the logging level to warn such that it'll only show the warnings and the errors.
But not all the normal background, logging information for **Spring**.
And again, I'm simply doing this just because we're creating a standalone application.
In normal production environments, you may want to keep this on depending on your application requirements.

````properties
# Reduce logging level. Set logging level to warn
logging.level.root=warn
````

But for now we're simply going to change this `logging.level.route=warn`.
So we'll see warning messages and we'll see error messages.
But I basically want to say, hey, don't print all that logging stuff.
I simply want to see the information or the output from my application.

````text
Hello World

Process finished with exit code 0
````

And here we see this `Hello World`.
That's our own custom code.
We don't have all the "**Spring Boot** banner" stuff, and we don't have all the **Spring Boot** logger information.
But one thing here is that don't worry if there are problems or issues.
It'll still log the warnings, and it'll also log the errors.
So if there's an exception or something, **Spring** will still tell you about it.

Now, we're actually going to cover step two of creating the **InstructorDetail** class.
So, the first thing we want to do is create this new package called `entity` to hold our entity class.
Let's move here and let's go ahead and create a new package.
And the name of the package is `entity`.
And so, let's go ahead and create our **InstructorDetail** class.
So, here's our InstructorDetail class:

````java
package com.luv2code.cruddemo.entity;

// annotate the class as an entity and map to db table
public class InstructorDetail {

    // define the fields

    // annotate the fields with db column names

    // create constructors

    // generate getter/setter methods

    // generate toString() method
}
````

Let me just go ahead and write some notes to myself just so I know what I need to do in this given class.
So, I'm going to need to annotate the class as an entity and map it to a database table.
I'll need to define the fields then annotate those fields with database column names.
I'll also need to create the constructors, generate getters and setters,
and finally, I want to generate a `toString` method 
just to help me out with printing or displaying this **InstructorDetail** object.
And that's my basic game plan here for this class.

````java
// annotate the class as an entity and map to db table
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    // ...
}
````

So let's start with the first step here of annotating the class as an entity.
So up top, I'll give `@Entity`, and also I'll say `@Table`, `name`, `instructor_detail`.
The `@Table` basically maps this to a specific table in our database.
Allright, so, that's our annotation part.

````java
// define the fields
// annotate the fields with db column names
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id;
@Column(name="youtube_channel")
private String youtubeChannel;
@Column(name="hobby")
private String hobby;
````

Now, let's go ahead and define the fields for this given class.
So, for this **InstructorDetail** class we're going to have an `ID`, 
and then we'll also have an entry here for the instructor's YouTube channel
and also an entry for the instructor's hobby.
Now that we have those fields created, let's go ahead and annotate those fields with the database column names.
So, here for the first one, it's our `@Id`.
So, it's our primary key.
And then for `@GeneratedValue`, we say `strategy` equals `IDENTITY`.
That'll help us with our auto increments on this table.
And then for the `@Column`, we'll give the column name of `"id"`,
and we've seen all this stuff before in some of our previous sections.
And then for the YouTube channel, we simply say `@Column`, 
and we have `name` equals and then the actual database column name, `youtube_channel`,
and then a similar process here for `hobby`.
Again, these are the actual column names in the database,
and we're mapping those to the fields here in this class.
So, that takes care of the first three items here on our to-do list.
So, the remaining task we have is creating a constructor,
generating getters and setters, and the `toString`.
So, let's go ahead and work with our constructors here.

````java
// create constructors

public InstructorDetail() {
}

public InstructorDetail(String youtubeChannel, String hobby) {
    this.youtubeChannel = youtubeChannel;
    this.hobby = hobby;
}
````

So, the first thing I'll do here is I'll just create a no-arg constructor.
And what I'll do is I'll also create a constructor that accepts arguments.
Basically for this constructor, I want to pass in the channel and the hobby.
So, I'll deselect the ID.
So, you should get a constructor similar to this.

````java
// generate getter/setter methods
public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getYoutubeChannel() {
    return youtubeChannel;
}

public void setYoutubeChannel(String youtubeChannel) {
    this.youtubeChannel = youtubeChannel;
}

public String getHobby() {
    return hobby;
}

public void setHobby(String hobby) {
    this.hobby = hobby;
}
````

Now, we'll go through and actually generate the getters and setters and also that `toString` method here.
And here, we'll just do a select all, and then we'll click on okay, 
so we have those getters and setters that were generated.
And then here, make sure all your fields are selected for ID, channel and hobby.
And then go ahead and hit the okay button.

````java
// generate toString() method
@Override
public String toString() {
    return "InstructorDetail{" +
            "id=" + id +
            ", youtubeChannel='" + youtubeChannel + '\'' +
            ", hobby='" + hobby + '\'' +
            '}';
}
````

So, we have this `toString` method created.
So we wrote the code to annotate the class, define the fields 
and then map those fields to the database columns.

Next, we're going to cover step three of creating the **Instructor** class.
And the name for this new class, I'll call it `Instructor`.
So in `InstructorDetail`, all those little notes I wrote to myself,
let's go ahead and copy these, and then we'll actually paste those into our `Instructor`.
Because we're basically going to do about the same thing in the `Instructor` class.
We have one little thing that's different, but the majority of it is the same.
So let's go ahead and start with the first thing here of annotating an entity class
and then mapping it to a given table.

````java
package com.luv2code.cruddemo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// annotate the class as an entity and map to db table
@Entity
@Table(name="instructor")
public class Instructor {

    // define the fields

    // annotate the fields with db column names

    // create constructors

    // generate getter/setter methods

    // generate toString() method
}
````

So I have that table name equals instructor.
This is all very straightforward, and we've seen this stuff before.
So I'll go through and define the fields here for this **Instructor** class.

````java
// define the fields
// annotate the fields with db column names
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
````

So this class will have an integer ID, first name, last name, and then email.
So those are my fields here for this **Instructor** class.
Now I'm going to annotate these fields with the database column names.
So, again, our `@Id`, and then we set up our `GeneratedValue`.
Using the generation type of identity help us with the auto increment in **MySQL**,
and also `@Column` and just map this column to the one named id.
So first name, map it to `first_name` that's the actual column name in the database.
So then we'll have `@Column` `last_name`, and then here `@Column` `email`.
So this handles annotating those fields and mapping them to our database columns.

Allright, so now let's go ahead and actually there's one other thing I need to do here
and this is the one special thing is that we need to actually set up the relationship 
or the mapping between the `Instructor` and the `InstructorDetail`,
and this is kinda what we saw earlier, and we'll set up this relationship here at this time.

````java
// ** set up mapping to InstructorDetail entity
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="instructor_detail_id")
private InstructorDetail instructorDetail;
````

So I'm going to create a new entry here, and it's called `InstructorDetail`
because we have a relationship between the `Instructor` class and the `InstructorDetail`.
And then I need to make use of my annotations to map it.
So here I have `@OneToOne` `cascade` equals `CascadeType.ALL`.
So this will apply to any operations for persisting, deleting, or anything regarding this object.
It'll update the associated object accordingly.
And now for `@JoinColumn`, we'll basically map our column in the `Instructor` class,
and we'll have that map over to the primary key in the `InstructorDetail`.
So here I have `@JoinColumn`, and I give `name` equals `instructor_detail_id`.
That's a column that's in the `instructor` table 
and that's already configured via those scripts to reference the ID in the actual `instructor_detail` table.
And here's a quick refresher of that.

````sql
KEY `FK_DETAIL_idx` (`instructor_detail_id`),
CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
REFERENCES `instructor_detail` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
````

So remember those scripts that we ran earlier.
So we set this foreign key `CONSTRAINT`, 
and we have this `CONSTRAINT` where `instructor_detail_id` `REFERENCES`, that's key there,
references `instructor_detail` `"id"`.
So that's how we have the association or the hookup 
between our `instructor` table and the `instructor_detail` table.
So **Hibernate** helps pulls it together there.
So we're going to create our constructors, generate getters and setters, and do our `toStrings`.

````java
// create constructors
public Instructor() {
}

public Instructor(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
}
````

So this is our default constructor for **Instructor**.
And here I'll select all the fields except for `ID`.
So just uncheck `ID` and also uncheck `InstructorDetail`, because we wanna manually pass it in.
We could leave it here, but I only wanna just pass in `firstName`, `lastName`, `email`.
Now let's go ahead and generate our getters and setters for this class.

````java
// generate getter/setter methods
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

public InstructorDetail getInstructorDetail() {
    return instructorDetail;
}

public void setInstructorDetail(InstructorDetail instructorDetail) {
    this.instructorDetail = instructorDetail;
}

// generate toString() method
@Override
public String toString() {
    return "Instructor{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", instructorDetail=" + instructorDetail +
            '}';
}
````

And for generating getters and setters, let's go ahead and select all,
because we want to have all of those selected, so we can use them in our application,
and keep all the other defaults here and hit `OK`.
And then the one last item here is generating our `toString` method
just to kinda help us with printing out the object when we run our application.
So we're in pretty good shape here with this **Instructor** class.

So we're at the final step of our development process.
This is step four of creating the main application.
And we actually need to kind of break this down a bit.
There are some intermediate items here.
So, we'll have: 

* creating the DAO interface 
* creating the DAO impl
* pulling together the main app

Allright, let's go ahead and dive in.
The first thing we want to do is create this new package for `DAO`.
And the name of this package, I'll call it `DAO`.
And now in this `DAO` package, we're creating an interface called `AppDAO`.
So here's our `AppDAO` interface:

````java
package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);
}
````

We simply want to define one method in this interface, and that's for saving the instructor.
So the method of `save`, parameter's coming in for `theInstructor`.
Now let's go ahead and create a new class for our `AppDAOImpl`.
And now for this `AppDAOImpl`, we'll say it implements the `AppDAO` interface.

````java
package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Instructor;

public class AppDAOImpl implements AppDAO {

    // define field for entity manager

    // inject entity manager using constructor injection

    @Override
    public void save(Instructor theInstructor) {
    }
}
````

Allright, so they'll kind of stub out a basic method for us here for save,
but we need to do a couple of other things.
I'll swing up here at the top and write some quick comments to myself.
So I need to define a field for our **EntityManager**, 
and also I need to inject the `entityManager` using constructor injection.

````java
package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }
}
````

Now we'll go ahead and define this field for the **EntityManager**.
And now I'll simply inject this `entityManager` using constructor injection.
I'll use `@Autowired`.
This is actually optional, but I keep it here just for readability.
And then I move in here and I make an assignment.
I say `this.entityManager = entityManager`.
And now I'll go and add the `@Transactional` annotation
since we're performing an update on the database, we're actually saving or storing an object in the database.
So I'll make use of this `@Transactional` annotation from the **Spring Framework**.
And here I'll have `entityManager.persist(theInstructor)`.
And remember, one thing that's important here is that 
it'll also save the details object because we have the `CascadeType.All`.
So by saving `instructor`, that persist operation will also cascade to the actual `instructor_detail`.
We get to save two objects for the price of one.

````java
package com.luv2code.cruddemo;
import com.luv2code.cruddemo.dao.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//System.out.println("Hello World");
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
	}
}

````

Now I'll move down to my main application, our CRUD demo application.
and what I'd like to do here is inject the `AppDAO`.
This method here is annotated with the `@Bean` annotation, so Spring will inject the `AppDAO` automatically.
Now let's go ahead and move in here.
I'll remove that `"Hello World"`,
and I'll call this method `createInstructor`, passing the `appDAO`.
This method hasn't been created yet, so I'll allow the IDE to create this method stub for me.

````java
private void createInstructor(AppDAO appDAO) {

    // create the instructor
    Instructor tempInstructor =
            new Instructor("Chad", "Darby", "darby@luv2code.com");

    // create the instructor detail
    InstructorDetail tempInstructorDetail =
            new InstructorDetail(
                    "http://www.luv2code.com/youtube",
                    "Luv 2 code!!!");

    // associate the objects
    tempInstructor.setInstructorDetail(tempInstructorDetail);

    // save the instructor
    //
    // NOTE: this will ALSO save the details object 
    // because of CascadeType.ALL
    //
    System.out.println("Saving instrutor: " + tempInstructor);
    appDAO.save(tempInstructor);
    System.out.println("Done!");
}
````

The first thing I need to do is create the instructor.
Here, I'll have `tempInstructor` equals `new Instructor`,
and now I also need to create `tempInstructorDetail`.
Here, I have `tempInstructorDetail` equals `new InstructorDetail`.
I pass in a reference to the YouTube channel, and also the actual hobby for this instructor,
and I'll just clean up the parentheses there.
Allright, so now I have these two independent objects, 
I need to associate these objects together for this `one-to-one` relationship,
and I can accomplish this here by saying `tempInstructor.setInstructorDetail`.
I pass in a reference to the `tempInstructorDetail`.
Next and most important piece here is actually saving `tempInstructor`.
I'll do a quick `System.out.println`, just printing the actual `tempInstructor`,
and then I simply delegate this call to the `appDao.save(tempInstructor)`,
and let me write some quick notes to myself 
regarding what's gonna kind of happen in the background.
Allright, so based on the information we've learned so far,
this will also save the details object because of `CascadeType.ALL`.
This allows us to save those two objects by simply saving instructor,
and then at the end, I simply do a `System.out.println("Done!")`.
Allright, so that's the main coding here for creating an instructor,
and now before I run this application, I'd like to add some logging statements for **JPA Hibernate**.
Let's go ahead and open up our `application.properties` file,
and I'd like to actually log the **SQL** that's being executed,
along with the actual values that are used for that given **SQL** statement.

`````properties
# Show JPA/Hibernate logging messages
logging.level.org.hibernate.SQL=trace
logging.level.org.hibernate.orm.jdbc.bind=trace
`````

I'll set the `logging.level.org.hibernate.SQL` equals `trace`,
and I'll also do a similar thing here for `logging.level.org.hibernate.org.JDBC.bind` equals `trace`.
And now what these configs will do is that the first one here will log these **SQL** statements,
and then the second one will log the values that are used in those **SQL** statements,
and we'll see that once we start running the application.
Let's go ahead and run our application.

````text
Saving instrutor: Instructor{id=0, firstName='Chad', lastName='Darby', email='darby@luv2code.com', instructorDetail=InstructorDetail{id=0, youtubeChannel='http://www.luv2code.com/youtube', hobby='Luv 2 code!!!'}}
DEBUG 15732 --- [           main] org.hibernate.SQL                        : insert into instructor_detail (hobby,youtube_channel) values (?,?)
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [Luv 2 code!!!]
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [http://www.luv2code.com/youtube]
DEBUG 15732 --- [           main] org.hibernate.SQL                        : insert into instructor (email,first_name,instructor_detail_id,last_name) values (?,?,?,?)
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [darby@luv2code.com]
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Chad]
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [1]
TRACE 15732 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Darby]
Done!

Process finished with exit code 0
````

And success, great.
So our app is running, it didn't crash, we didn't have a problem.
Here it says S`aving instructor`, gives the instructor name, email, and `instructorDetail`.
We also make use of our logging statements, so the **SQL**,
and then the values that they're binding for this insert statement.
And then also another insert statement here, and we have the email, the first name, and so forth.
And one thing to be aware of here, you may have noticed this,
is that it inserts the associated entity first, that's the `instructorDetail`,
and then it inserts the `instructor`.
This is mainly due to the relationship of the foreign key.
The instructor needs to know the ID of the `instructorDetail`, hence the order.
And then at the end it said `Done!`.
Okay, so we're good.
And then let's also verify this in our database.
So let's swing over to **MySQL Workbench**, let's go to the SCHEMA here,
and let's do a query on the `instructor` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image27.png" alt="image27">
</div>

And also do a query here on the `instructor_details` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image28.png" alt="image28">
</div>

Allright, so this all looks pretty good.
So we're getting the data that we used in our actual application.
Now let's swing back over to our IDE.
I want to do one small modification here, I want to add another instructor.
I'll move down here to this `createInstructor` method,
and I'll copy-paste some code and then comment out some code.
Let's go ahead and copy the section above, and then we'll just paste it below.
And let's just comment out the first one here.
Because now I want to add a new instructor with a new `InstructorDetail`.

````java
private void createInstructor(AppDAO appDAO) {

    /*
    // create the instructor
    Instructor tempInstructor =
            new Instructor("Chad", "Darby", "darby@luv2code.com");

    // create the instructor detail
    InstructorDetail tempInstructorDetail =
            new InstructorDetail(
                    "http://www.luv2code.com/youtube",
                    "Luv 2 code!!!");
    */
    
    // create the instructor
    Instructor tempInstructor =
            new Instructor("Madhu", "Patel", "madhu@luv2code.com");

    // create the instructor detail
    InstructorDetail tempInstructorDetail =
            new InstructorDetail(
                    "http://www.luv2code.com/youtube",
                    "Guitar");

    // ...
}
````

I'll move in here and modify the instructor's name, `Madhu Patel`.
I'll update his email address accordingly.
And then for the `instructorDetail` here
I'll leave the same YouTube channel, I'll simply change his hobby.
`Madhu` is really good at playing the guitar, so I'll say his hobby is `"Guitar"`.
So this is the update here for our new instructor that we want to add to our database.
Allright, let's go ahead and run this application.

````text
Saving instrutor: Instructor{id=0, firstName='Madhu', lastName='Patel', email='madhu@luv2code.com', instructorDetail=InstructorDetail{id=0, youtubeChannel='http://www.luv2code.com/youtube', hobby='Guitar'}}
DEBUG 37812 --- [           main] org.hibernate.SQL                        : insert into instructor_detail (hobby,youtube_channel) values (?,?)
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [Guitar]
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [http://www.luv2code.com/youtube]
DEBUG 37812 --- [           main] org.hibernate.SQL                        : insert into instructor (email,first_name,instructor_detail_id,last_name) values (?,?,?,?)
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [madhu@luv2code.com]
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Madhu]
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [2]
TRACE 37812 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Patel]
Done!

Process finished with exit code 0
````

And we see a similar set of logging statements.
This time saving the instructor, first name `Madhu`, last name `Patel`.
And we can swing over to **MySQL Workbench**, we can do a query here on the instructor table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image29.png" alt="image29">
</div>

And excellent, there's a new entry there for `Madhu`.
And then we can do a similar thing here for `instructor_detail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image30.png" alt="image30">
</div>

Great, so here's the information for `Madhu` and his hobby is `"Guitar"`.
So I'd like to say we're successful here in doing the `One-To-One` mapping,
mapping an `instructor` to an `instructorDetail` and saving both of those objects to the database.
Now, we're going to cover finding an entity with `one-to-one` mapping.
Let's take a look at our DAO implementation.
The code here is fairly simple.
We'll have this new method in our DAO called `findInstructorById`.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // ...
    
    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }
}
````

We'll pass in `theId`, and then we'll make use of `entityManager.find`.
We'll give `Instructor.class, theId`.
And that's the actual instructor that we'll find.
Now this will also retrieve the `instructorDetail` object 
because of the default behavior of the `@OneToOne` annotation.
The fetch type is `Eager`.
I'll talk more about fetch types later, but in this case,
just be aware that it'll return the associated object in an `Eager` fashion.
So we'll get the `instructor` and `instructorDetail`.
Okay, so the other thing we need to do is update our `AppDAO` interface and add this new method `findInstructorById`.
I'll open up my AppDAO:

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
}
````

So that's the coding here.
Now let's go ahead and move into our main application, our `CruddemoApplication`,
and let's add some code to call this new method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
            findInstructor(appDAO);
		};
	}

    private void findInstructor(AppDAO appDAO) {
    }
    
    // ...
}
````

I'll just comment out `createInstructor` for now, 
and I'll add this new method, `findInstructor`. I'll pass in the `appDAO`.
And of course this is a new method that we haven't created yet.
So we'll let the IDE create a stub for us.

````java
private void findInstructor(AppDAO appDAO) {
    
    int theId = 1;
    System.out.println("Finding instructor id: " + theId);
    
    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
}
````

I'll give a value for `theId` of one, and I'll do a `System.out.println`,
finding instructor with id of X, or in this case, `theId`.
And now I'll actually delegate the call to the `appDAO`.
Here I'll have `Instructor tempInstructor = appDAO.findInstructorById()`, and I pass in `theId`.
And now I can go ahead and print out information about this instructor.
I'll do a `System.out.println` on `tempInstructor`.
And then if I only wanted to print just the associated instructorDetail,
I can do this in a separate statement.
Here I could say `tempInstructor.getInstructorDetail()`.
So either way you can print out the entire instructor along with their details, or simply the details by itself.
And that's basically it here as far as the coding for finding an instructor by ID.
Now let's go ahead and run this application and test it out.

````text
Finding instructor id: 1
DEBUG 6320 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 6320 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Chad', lastName='Darby', email='darby@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.luv2code.com/youtube', hobby='Luv 2 code!!!'}}
the associated instructorDetail only: InstructorDetail{id=1, youtubeChannel='http://www.luv2code.com/youtube', hobby='Luv 2 code!!!'}

Process finished with exit code 0
````

And we can take a look at the output here.
So finding an instructor ID of one, and we see that we have this temp instructor ID of one.
First name `Chad`, last name `Darby`.
Allright, that looks good based on information from our database.
And also the associated instructorDetail only.
And again, InstructorDetail ID of one, YouTube channel and hobby.
So this matches up with the information that we have in our database for instructor ID of one.
So we're able to retrieve an instructor by ID using `One-To-One`.
We noticed that it retrieves the associated instructor details by default, and we can display that information.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // ...
    
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        
        // delete the instructor
        entityManager.remove(tempInstructor);
    }
}
````

Now, we'll use `One-To-One` and we'll delete an entity.
Now, it's important here, since we're making a modification to the database,
we make use of that `@Transactional` annotation.
We'll have this new method here, `deleteInstructorById`, that will pass in the ID.
The first thing we do is we retrieve the instructor by using the `entityManager.find()`,
and then we'll delete the instructor using `entityManager.remove()`, and we pass in that `tempInstructor`.
Now, this will also delete the `instructorDetail` object because of `CascadeType.ALL`,
so similar to doing a `save` or `persist` where we save the `instructor` and the `instructorDetail`,
here when we delete, it'll delete the `instructor` and the `instructorDetail`.
So next we'll add the code for the interface and the main app and so on.
So let's update our `appDAO` interface, and we'll add that new method to `deleteInstructorById`.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
````

Allright, so this is very similar to what we've done a little bit earlier,
so our interface looks pretty good.
Let's go ahead and move into our main application 
and add the appropriate code here to call this method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
            // findInstructor(appDAO);
            deleteInstructor(appDAO);
		};
	}

    private void deleteInstructor(AppDAO appDAO) {
        
        int theId = 1;
        System.out.println("Deleting instructor id: " + theId);
        
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }
    
    // ...
}
````

I'll just comment out `findInstructor`.
I'll call this method `deleteInstructor`, passing in the `appDAO`.
I'll go ahead and create the stub for this method.
I'll set up the ID of one, so I'll delete the instructor with the ID of one, that's the instructor `Darby`.
I'll do a little `System.out.println` here.
And now, I'll make a call to that DAO, delete the instructor by ID, and I pass in the ID.
And then, I finally do a little `System.out.println` saying I'm done.
Allright, let's go ahead and run this application and test it out.

````text
Deleting instructor id: 1
DEBUG 32516 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 32516 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 32516 --- [           main] org.hibernate.SQL                        : delete from instructor where id=?
TRACE 32516 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 32516 --- [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
TRACE 32516 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Done!
````

So, it says deleting instructor ID of one.
And then, we can look at the SQL statements here.
We see that we first retrieve the `instructor`, then we delete the `instructor` with the ID of one,
and we also delete the associated `instructorDetail`.
So remember, we have that cascade all, 
so that same operation's going to cascade to the appropriate or associated object,
in this case, `instructor` with `instructorDetail`.
And then, we finally we print out, `"Done!"`.
Now, let's go ahead and swing over to **MySQL Workbench** and verify this.
I'll do a query on the `instructor` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image31.png" alt="image31">
</div>

And success.
So, instructor ID of one was deleted and we can verify the associated `instructorDetail`.
Now, a query on `instructorDetail`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image32.png" alt="image32">
</div>

And excellent.
So the associated `instructorDetail` for `Darby`, his hobby was love to code, that's been deleted also.
Let's now cover **Hibernate** `one-to-one` **Bi-Directional**.
So we currently have a **Uni-Directional** mapping between our `Instructor` and our `InstructorDetail`,
and that's working just fine for us.
But now we have a new use case.
Basically we want to load an `InstructorDetail` object,
and then we'd like to get the associated `Instructor` for that detail object.
But we can't really do this with our current **Uni-Directional** relationship.
So right now we can only start with `Instructor` and then move to `InstructorDetail`.
We can't go the other way.
With **Uni-Directional** it's really just a one-way street.
But we can actually solve this by making use of a **Bi-Directional** relationship.
So what we can do is start with the `InstructorDetail`,
and then we can actually make it back to the `Instructor`.
And also, since we already have the previous **Uni-Directional** relationship in place 
we effectively have **Bi-Directional** now.

So the nice thing about using **Bi-Directional** is that we can actually keep our existing database schema.
So there's really no changes required to our database setup.
So we can continue to use the `Instructor`, `InstructorDetail` table with the foreign keys that we set up earlier.
So really the only thing that we have to change now is to simply update the **Java** code,
and we'll cover that part now.
So here's our development process.
So we basically need to:

1. Make some updates to the `InstructorDetail` class:
    * Add a new field to reference the `Instructor`
    * Add the appropriate getter/setter methods for `Instructor`
    * Add a `@OneToOne` annotation such that we can point back to the original `Instructor`
2. And then pull it all together by creating a main application

Alright, so let's start with step 1, adding a new field to reference `Instructor`.
So here's our class here, `InstructorDetail`.

````java
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    // ... other fields
    
    private Instructor instructor;
    
    // ... constructors and other getters/setters

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
````

And we have the code in place right now for adding that new field.
So we have a new field named `instructor`.
So step 2, add in getter setter methods for `instructor`.
So here we just have a getter and a setter.
Again, there's no rocket science here, but the key thing here is that we can call the setter method
for adding an `instructor` or setting an `instructor`.
Use the getter method for getting the associated `instructor` with this given `InstructorDetail` instance.

````java
@OneToOne(mappedBy = "instructorDetail")
private Instructor instructor;
````

Now we're moving head to step 3.
So note here, on our field for our `instructor` we add the `@OneToOne`.
And then we have a new entry here that's a little interesting.
We say `mappedBy` `"instructorDetail"`.
So what does this mean?
Well, this basically refers to the `instructorDetail` property in the `Instructor` class.
So what we're telling **Hibernate** is that 
this `instructor` field is actually **mapped by** the `instructorDetail` property in the `Instructor` class.
So, the `mappedBy` tells **Hibernate** to look at the `instructorDetail` property in the `Instructor` class.
So **Hibernate** can actually use the information from the `Instructor` class's `JoinColumn`
to figure out how to find the associated `instructor`.
Allright, so you're basically telling **Hibernate**,
"Hey, this field `instructor` in `InstructorDetail`, it's mapped by `instructorDetail`."
**Hibernate** will go figure that out, look at the foreign key relationship, and match everything up.
So effectively **Hibernate** can use this information to find out 
how these two items are linked up, and also finding the appropriate `instructor` for this given `instructorDetail`.

````java
@OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
private Instructor instructor;
````

Now the other thing here is cascading.
So we'll add support for cascading.
So this new entry that I just added here, `cascade=CascadeType.ALL`.
So remember, this will actually cascade all operations to the associated `instructor`.
So if I load an `instructorDetail`, then if I want to delete that `instructorDetail`
this will also cascade the delete operation to that given `instructor`.
And remember, you have fine grain control over the cascading types,
so if you don't want to cascade all, you can choose the appropriate cascade types
that you'd like to use for your given use case.
Now let's go ahead and define our **DAO** interface.

````java
package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    
    InstructorDetail findInstructorDetailById(int theId);
}
````

In our interface we're going to add this new method, `findInstructorDetailById`.
And we pass in `theId`.
Now let's swing over to the **DAO** implementation.

`````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById methods

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }
}

`````

We have this new method, `findInstructorDetailById`.
And then we make use of this `entityManager.find()`.
This will actually retrieve the `instructorDetail` 
and this will also retrieve the associated `instructor` object.
And that's because of the default behavior of `one-to-one`.
Now let's pull it together in this main application.

````java
@SpringBootApplication
public class CruddemoApplication {

	// main method

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//System.out.println("Hello World");
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
            findInstructorDetail(appDAO);
		};
	}

    private void findInstructorDetail(AppDAO appDAO) {
    }
    
    // deleteInstructor, findInstructor, createInstructor methods
}
````

In our command line runner will inject the `appDAO`.
And then we'll have this method `findInstructorDetail`, and we pass in that `appDAO`.
And then of course we have to create this actual method here, `findInstructorDetail`.

````java
private void findInstructorDetail(AppDAO appDAO) {
    
    int theId = 2;
    System.out.println("Finding instructor detail id: " + theId);
    
    InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
    
    System.out.println("tempInstructorDetail: " + tempInstructorDetail);
    System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
}
````

So inside of this method, I simply set up the `instructor` ID, do a `System.out.println()`.
I make use the `appDAO.findInstructorDetailById()`.
I pass in `theId`.
And then I go through and I simply print out the `tempInstructorDetail` by itself.
And then I also retrieve the associated instructor.
So that's the whole **Bi-Directional** thing.
So I can actually print out the actual `instructor` that's associated with this `instructorDetail`.
Allright, this looks really good.
So, let's go ahead and run the application.

````text
Finding instructor detail id: 2
DEBUG 4120 --- [           main] org.hibernate.SQL                        : select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
TRACE 4120 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
tempInstructorDetail: InstructorDetail{id=2, youtubeChannel='http://www.luv2code.com/youtube', hobby='Guitar'}
the associated instructor: Instructor{id=2, firstName='Madhu', lastName='Patel', email='madhu@luv2code.com', instructorDetail=InstructorDetail{id=2, youtubeChannel='http://www.luv2code.com/youtube', hobby='Guitar'}}

Process finished with exit code 0
````

It ran successfully.
We can go look at some of the **SQL** statements here.
So it's doing a `select`. 
And it's selecting fields from `instructorDetail`.
And it's also joining an `instructor` because of that relationship.
And then we can see the information regarding the `tempInstructorDetail`.
We know we have a `tempInstructorDetail` with an ID of `2`.
So this is the whole YouTube channel and guitar is the hobby.
So that matches.
And then also the associated `instructor`, the associated `instructor` is `Madhu`.
And his favorite `hobby` is a `Guitar`.
So this all matches up exactly with what we have in the database.
So we were successful here in having this `one-to-one` relationship **Bi-Directional**,
where we could start with the `instructor`, get the `instructorDetail`,
or in this case here, start with the `instructor` detail and find the associated `instructor`.
So we have this **Bi-Directional** support.
We have the two-way street.

Now, we're going to do is actually delete the `instructorDetail` entity and also cascade delete.
That means deleting the associated `instructor`.
We'll start off by opening up our `AppDAO` interface.

````java
package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
````

And I'll add this new method here, `deleteInstructorDetailById`.
All right, that method there looks pretty good.
Let's go ahead and move over into our **DAO** implementation,
and provide an implementation for that method.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, findInstructorDetailById methods

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }
}
````

We'll let the IDE generate a stub for us.
Now let's go ahead and move down to the `deleteInstructorDetailById` method.
One thing I want to do is add the `@Transactional` annotation,
since we're going to modify the database.
And now I'll go ahead and start coding.
I'll add a quick little comment to myself.
We'll retrieve the instructor detail by ID.
Here, I have `entityManager.find`, `InstructorDetail.class`, and I give `theId`.
And now we can actually go through and delete the `tempInstructorDetail`.
Here, I have the `entityManager.remove`, and I provide a reference there to the `tempInstructorDetail`.
And now remember here with the cascading options that we have set up, we have cascade all set up.
So that means when we delete `tempInstructorDetail`, it'll also delete the associated `instructor`.
Now let's go ahead and move over to our main application.

````java
@SpringBootApplication
public class CruddemoApplication {

	// main method

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//System.out.println("Hello World");
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);

            deleteInstructorDetail(appDAO);
		};
	}

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Deleting instructor detail id: " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }
    
    // deleteInstructor, findInstructor, createInstructor, findInstructorDetail methods
}
````

I'll move down here to this `CommandLineRunner` method.
I'll comment out the `findInstructorDetail` and I'll add this new method here, `deleteInstructorDetail`.
I'll go ahead and create a stub for this method.
I'll go ahead and set up `theId` for the `instructorDetail` an ID of `2`.
It'll `System.out.println` here.
And now I'll simply call that method that we just created `appDAO.deleteInstructorDetailById(()`.
And then finally, I just print out a `"Done!"` message at the end.
Allright, and so that's the basic coding there for deleting an instructor detail by ID.
So let's go ahead and run this application.

````text
Deleting instructor detail id: 2
DEBUG 40944 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 40944 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
DEBUG 40944 --- [           main] org.hibernate.SQL                        : delete from instructor where id=?
TRACE 40944 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
DEBUG 40944 --- [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
TRACE 40944 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
Done!
````

Okay, it says done.
So that looks pretty good so far.
Deleting instructor detail ID of 2.
And we can see the `select` statement.
Allright, so notice the queries here.
We have a delete from `instructor` and also delete from `instructor_detail`.
So here we can see that it's deleting both those entries there.
Let's swing over to **MySQL Workbench** just to verify this in the database.
So we'll do a query here on `instructor_detail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image33.png" alt="image33">
</div>

It's empty.
That's good, successful.
And now let's do a query here on `instructor`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image34.png" alt="image34">
</div>

And that's empty also.
So we know that we've deleted both of those items there, the `instructor_detail` ID of 2
and also the associated `instructor`.
So we're successful here.

Now, let's look at another scenario, we only want to delete the `instructorDetail`.
We want to actually keep the `instructor` in the database,
so what we need to do is actually modify the cascade type on the `instructorDetail` entity.
Now, before we get started, let's just go ahead 
and add a new `instructor` and `instructorDetail` to our database, just so we have a baseline to work with.
And what I'll do is I'll actually open up my main application 
and I'll use some of the code that we've already created before.
I'll go to command line runner, and I'll comment out the code :

````java
@SpringBootApplication
public class CruddemoApplication {

	// main method

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//System.out.println("Hello World");
			createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);
		};
	}
    
    // deleteInstructor, findInstructor, createInstructor, findInstructorDetail, deleteInstructorDetail methods
}
````

that'll actually delete an `instructorDetail`, and then I'll uncomment the line for creating `instructor`.
Again, I simply wanna just run this to add a new instructor to our database.
And I'll go ahead and move to this method simply by going to the implementation code for this method.
So here's our `createInstructor`:

````java
private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instrutor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
}
````

We had some code from before, and we have this `instructor`, `Madhu Patel`, his YouTube channel, his guitar.
So let's just go ahead and run this, and it should add this new `instructor` and `instructorDetail` to our database.
Allright, so the app ran successfully.

````text
Saving instrutor: Instructor{id=0, firstName='Madhu', lastName='Patel', email='madhu@luv2code.com', instructorDetail=InstructorDetail{id=0, youtubeChannel='http://www.luv2code.com/youtube', hobby='Guitar'}}
DEBUG 39828 --- [           main] org.hibernate.SQL                        : insert into instructor_detail (hobby,youtube_channel) values (?,?)
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [Guitar]
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [http://www.luv2code.com/youtube]
DEBUG 39828 --- [           main] org.hibernate.SQL                        : insert into instructor (email,first_name,instructor_detail_id,last_name) values (?,?,?,?)
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [madhu@luv2code.com]
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Madhu]
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [3]
TRACE 39828 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Patel]
Done!

Process finished with exit code 0
````

We can see that inserts into `instructorDetail`, and also `instructor` from `Mudhu Patel`, so that's fine.
Let's swing over to **MySQL Workbench** and verify this information.
I'll do a query on `instructorDetail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image35.png" alt="image35">
</div>

So notice here we have a new `instructorDetail` id of 3 for guitar, 
and then also doing a select an `instructor`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image36.png" alt="image36">
</div>

And notice we have a new `instructor` id 3 for `Mudhu Patel`, and also his `instructorDetail` id maps to id 3.
Allright, so we're successful.
So we have a `instructor` and an `instructorDetail` out there.
We're good to go with that.
Let's modify the cascade type and `instructorDetail`.
We want to be able to delete an `instructorDetail`, but not delete the associated `instructor`
because we want to keep the `instructor`.
So let's open up `instructorDetail`.

````java
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="youtube_channel")
    private String youtubeChannel;
    @Column(name="hobby")
    private String hobby;

    //@OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH,
                                                        CascadeType.MERGE,
                                                        CascadeType.PERSIST,
                                                        CascadeType.REFRESH})
    private Instructor instructor;

    // constructors, getter/setter and toString methods
}
````

And then let's move down to the entry here
where we have the reference to the associated `instructor`,
and we want to change this `@OneToOne` annotation as far as the attributes.
So we want to change the cascade type.
Instead of "ALL", we want to change it to something else.
What I'll do here is I'll put this inside of curly braces 
because I'll give an array of different cascade types that I'll use.
So instead of `CascadeType.ALL`, I'll choose everything except for remove.
Remember, we're inside curly braces here.
`CascadeType.MERGE`, `CascadeType.PERSIST`, and also `CascadeType.REFRESH`.
But the important thing here is that we don't have the `CascadeType.REMOVE`
because we don't want to remove that associated entity or cascade it,
and also we're not using `CascadeType.ALL`.
Those are the four cascade types you should have here.
This will allow us to delete an `instructorDetail`,
but not cascade the delete operation or the remove operation to the given `instructor`
so that will allow us to keep the `instructor` in the event that we delete an `instructorDetail`.
Now let's move up to our app **DAO** implementation.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, findInstructorDetailById methods

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        
        // remove the associated object reference
        // break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }
}
````

There's one little thing or one modification we need to do here in this **DAO** impl,
so again, checkpoint, I'm in at **DAO** impl.
We'll move down to the `deleteInstructorDetailById`.
And now what I want to do here is actually remove the associated object reference,
break the **Bi-Directional** link, and I'll show you the coding here for this in a second.
So here I'll say `tempInstructorDetail.getInstructor()`
and then I'll set the `instructorDetail` to `null`
so here I'm breaking that two-way link between these two associated entities.
So let's go ahead and save this.
Let's move back to our main application.

````java
@SpringBootApplication
public class CruddemoApplication {

	// main method

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//System.out.println("Hello World");
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            deleteInstructorDetail(appDAO);
		};
	}
    
    // deleteInstructor, findInstructor, createInstructor, findInstructorDetail, deleteInstructorDetail methods
}
````

And our command line runner will comment out the code for `createInstructor`,
and then we'll uncomment the call to `deleteInstructorDetail`.

````java
private void deleteInstructorDetail(AppDAO appDAO) {

    int theId = 3;
    System.out.println("Deleting instructor detail id: " + theId);

    appDAO.deleteInstructorDetailById(theId);
    System.out.println("Done!");
}
````

Now, one thing we need to do here as far as deleting instructor details
is that we need to update the ID to three
because that's that new entry that we added, right?
And again, depending on your database, that number may be different.
Just use the appropriate instructor detail ID for your database.
Now let's go ahead and run this application and test it out.

````text
Deleting instructor detail id: 3
DEBUG 16608 --- [           main] org.hibernate.SQL                        : select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
DEBUG 16608 --- [           main] org.hibernate.SQL                        : update instructor set email=?,first_name=?,instructor_detail_id=?,last_name=? where id=?
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [madhu@luv2code.com]
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Madhu]
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [null]
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Patel]
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (5:INTEGER) <- [3]
DEBUG 16608 --- [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
TRACE 16608 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
Done!

Process finished with exit code 0
````

And so it ran successfully.
Here it says deleting instructor detail ID of three.
So we do a `select`, and then we perform an `update` on `instructor`, 
so it sets the `instructor_detail_id` to `null`.
Based on that coding that we added in that **DAOImpl**.
And then notice here that it did not `delete` the `instructor`.
It simply did an `update` on the `instructor`.
However, we did `delete` the `instructor_detail`, so that's the whole thing that we wanted.
We wanted to be able to delete the `instructor_detail`,
but not delete the `instructor` so that kind of looks good here as far as the logging statements.
And now let's swing over to **MySQL Workbench** and verify this in the database.
So we'll move up here, and we'll do a query on `instructor_detail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image37.png" alt="image37">
</div>

I'll just simply run this query or do a refresh
and notice here that the `instructor_detail` is deleted,
so that entry or that ID of `instructor_detail` ID of three is no longer there, so that was successful.
Now let's take a look at `instructor`.
We do a refresher or a query again:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image38.png" alt="image38">
</div>

and notice here the `instructor` is still there.
It simply updated the `instructor_detail_id` reference to `null`, so we're successful here.
We were able to delete the `instructor_detail`, but keep the `instructor`,
and we did this by simply modifying the cascade type on an `instructor_detail` entity
and making a minor update in our **AppDAOImpl**. 

</div>

## [@OneToMany Mapping - Overview]()
<div style="text-align:justify">

</div>

### [@OneToMany Mapping - Database Setup]()
<div style="text-align:justify">


</div>

### [@OneToMany Mapping - Project Setup]()
<div style="text-align:justify">


</div>

## [@ManyToMany Mapping - Overview]()
<div style="text-align:justify">

</div>

### [@ManyToMany Mapping - Database Setup]()
<div style="text-align:justify">


</div>

### [@ManyToMany Mapping - Project Setup]()
<div style="text-align:justify">


</div>