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

In this section, we're going to cover **Hibernate** `one-to-many` **Bi-Directional**.
So we'll make use of this `one-to-many` mappings where an `instructor` can have many `course`s.
So here's our `instructor`, a list of `course`s, and we have the association.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image39.png" alt="image39">
</div>

And note, here, this will be **Bi-Directional**.
So we can start with `instructor`, go to a `course`,
or we can start with a `course` and go to an `instructor`.
We'll also have this `many-to-one` mapping where many courses can have one `instructor`.
So this is basically the inverse or opposite of one to many.
There are cases where you can have many `instructor`s on a `course`,
but let's hold off on that for now.
So, at this moment, we'll simply focus on many `course`s being mapped to one `instructor`.
Allright, so here's a real-world project requirement:

* If you delete an `instructor`, do not delete the `course`
* If you delete a `course`, do not delete the `instructor`

So what this means here is that do not apply cascading deletes.
So we can have fine-grain control over our cascade types.
We'll list all of them except for the remove or delete.
Alright, so here's our development process:

* Set up our prep work - Define database tables
* Create the `Course` class
* Update the `Instructor` class
* Pull it all together by creating a main application

</div>

### [@OneToMany Mapping - Database Setup]()
<div style="text-align:justify">

Allright, so let's look at step one of setting up our database tables.
So we're going to have this new table for `course`.
So here's our **SQL** for creating this course table.

````sql
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  ...
);
````

So, basically, our course is going to have three fields: an `id`, a `title`, and `instructor_id`.
The primary key will be the `id`.
And then we'll add a special unique constraint here to prevent duplicate `course` titles.
So we'll say `UNIQUE KEY` `"title"`.
Make sure that we don't have multiple courses with the same title.
If you try and add a course with the same title or duplicate title,
the database will actually give you an error.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image40.png" alt="image40">
</div>

Allright, so let's go ahead and set up our foreign key.
So here we have our database table for our `course`,
and we basically need to set up a relationship between the `course` and the `instructor`.
So we have this foreign key for `instructor_id` that's in the `course`.
And this will basically reference, or map back to the `instructor` table,
the `id` column of that `instructor` table.

````sql
CREATE TABLE `course` (
  
  ...
  
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `instructor` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
)
````

So that's our foreign key relationship that we're setting up on our `course` table.
Alright, now, with the `instructor` table,
nothing to change for the **SQL** for the instructor table, which is a good thing.
Allright, so one last thing I want to do is actually generate the database diagrams
just so we can see the relationship between all the tables.
So just go up to the `Database` menu and then choose `Reverse Engineer`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image41.png" alt="image41">
</div>

Allright, we have our diagram.
So we have our `instructor` table, we have the `instructor_detail` from the previous sections
and there's our new `course` table.
So we're in good shape as far as our database setup, our prep work.
In the following section, we'll dive in, we'll start writing some hibernate code
to make use of these new tables.

</div>

### [@OneToMany Mapping - Project Setup]()
<div style="text-align:justify">

Alright, step two of creating a `Course` class.
So we have this new table `course`.
We need to create a class that'll map to that given table.

````java
package com.luv2code.cruddemo.entity;
import jakarta.persistence.*;

@Entity
@Table(name="course")
public class Course {

    // define our fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    // constructors, getters / setters, toString
    
}
````

So here we have the table, give the name up top, `course`,
we have the `id` mapped to the primary key, and then we have the `title`.
So this is all kind of more the same.
You've seen all this work before.
We go through the normal steps of creating constructors, getters and setters and so on.
But now the new item here is, in the `Course` class, setting up the relationship of `@ManyToOne`.
Remember, you can have many courses mapped to one instructor.
So here we have the `@ManyToOne` annotation,
and then we specify the `@JoinColumn`, `name="instructor id"`.
So the `@JoinColumn` is the actual column in our `course` table
that'll map or associate to the `instructor` table, 
so we can find the given `instructor` for this given course.
So that's the purpose of the `@JoinColumn`.
So it basically references the column that's in our `course` table
that allow us to map back and find the associated `instructor`.
Allright, so that pretty much wraps it up there for the `Course` class.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    // other fields

    @OneToMany(mappedBy="instructor", cascade = {CascadeType.PERSIST,
                                                CascadeType.MERGE,
                                                CascadeType.DETACH,
                                                CascadeType.REFRESH})
    private List<Course> courses;

    // constructors

    // other getter/setter methods

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // toString() method
}
````

Let's move to Step 3 of updating the `instructor`.
We're simply going to reference the `courses`.
So we're going to set up a list of `courses` here, that we can use in the `instructor`,
and we'll also take care of our standard getter and setter methods also.
So `getCourses` and `setCourses`, and this is very basic.
Now we'll move ahead and we'll add that `@OneToMany` annotation.
So for an `instructor`, an `instructor` can have one-to-many courses.
So we have `@OneToMany`, and then we specify `mappedBy="instructor"`.
So this basically refers to the `instructor` property that's in the `Course` class.
So a bit more on the `mappedBy`.
So we have this class `instructor` with the one to many mappedBy `instructor`.
So the `mappedBy` tells **Hibernate**:

* Look at the `instructor` property in the `Course` class
* Use the information from the `Course` class `@JoinColumn`
* This will actually help **Hibernate** find the associated courses for the `instructor`

Allright, so that's how that all maps together,
how it all ties together using the `mappedBy` attribute for the `instructor` class.
Allright, so we also need to add support for cascading because we have that real world requirement.
If we delete an `instructor`, we don't want to delete the `Course` and vice versa.
So here, we don't want to apply the cascading deletes.
So, we'll make use of our fine grain control here
to only specify `PERSIST`, `MERGE`, `DETACH`, and `REFRESH`.
We won't list `REMOVE`, because `REMOVE` will actually perform the cascading deletes,
so we're using this fine grain, very specific control here for handling that real world project requirement.
And then we'll do a similar thing here for the `Course`.
So, remember for a `Course`, if you delete a `Course` then you don't want to delete the `instructor`.

````java
@Entity
@Table(name="course")
public class Course {
    
    // ...

    @ManyToOne(cascade = {CascadeType.PERSIST,
                        CascadeType.MERGE,
                        CascadeType.DETACH,
                        CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    // constructors, getters / setters, toString
    
}
````

So we'll specify the items `PERSIST`, `MERGE`, `DETACH`, and `REFRESH`, 
but we won't specify the `REMOVE` because we don't want to apply the cascading deletes.
Allright, and now finally, we need to add some convenience methods for **Bi-Directional**.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    // fields

    // constructors

    // getter/setter and toString methods
    
    // add convenience method for bi-directional relationship
    public void addCourse(Course tempCourse) {
        
        if (courses == null) {
            courses = new ArrayList<>();
        }
        
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }
    
    //...
}
````

So, when we add a `Course`, so we're going to add this new method here called `addCourse`.
When they add a `Course`, we'll say `Courses.add(tempCourse)`
and then we'll also say `tempCourse.setInstructor(this)`.
This will basically set up the two-way link between the `instructor` and the `Course`.
And we need this because we're making use of this **Bi-Directional** relationship between our instructors and courses.
So you can find an `instructor` and then get the courses, 
or you could find a `Course` and get the `instructor`.

So we're at step four of creating the main application.
Let's go ahead and swing into our IDE.
Now, the first thing we need to do is point to the new database schema.
We need to go ahead and update our properties for this.
Let's go ahead and move over to our `application.properties` file.

````properties
# spring.datasource.url=jdbc:mysql://localhost:3306/hb-01-one-to-one
spring.datasource.url=jdbc:mysql://localhost:3306/hb-02-one-to-many
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set logging level to warn
logging.level.root=warn

# Show JPA/Hibernate logging messages
logging.level.org.hibernate.SQL=trace
logging.level.org.hibernate.orm.jdbc.bind=trace
````

We have the old database schema for `one-to-one`, but now we're doing `one-to-many`.
And so, we need to reference that new schema that we just created in the previous sections.
And that's `hb-02-one-to-many` so make sure you update this accordingly and save this file.
So, let's go ahead and move into our main application.
Let's move down to the command line runner section.

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            
            createInstructorWithCourses(appDAO);
		};
	}

    private void createInstructorWithCourses(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, findInstructor, createInstructor methods

}
````

We'll comment out the previous method of `deleteInstructorDetail`,
and we'll add this new method here, `createInstructorWithCourses`.
Now I'll move down into this method here, `createInstructorWithCourses`.
And actually what I want to do here is a little copy-paste work 
because we already have some code for creating an `instructor` so far.
Let's go ahead and move down to this method `createInstructor`:

````java
private void createInstructorWithCourses(AppDAO appDAO) {

    // create the instructor
    Instructor tempInstructor =
            new Instructor("Susan", "Public", "susan.public@luv2code.com");

    // create the instructor detail
    InstructorDetail tempInstructorDetail =
            new InstructorDetail(
                    "http://www.youtube.com",
                    "Video Games");

    // associate the objects
    tempInstructor.setInstructorDetail(tempInstructorDetail);
}
````

And then I'll just grab the section of code here for creating an `instructor`
and also creating an `instructorDetail`.
And now I'll move back up to this new method, and then I'll paste that code.
Al right, so at least we have something to kind of get started with or starting to work with here.
And I'll simply go through and update the information regarding the `instructor`'s name.
So I'll set up `Susan Public`, update the email address accordingly. 
I'll change the actual YouTube channel to just be `youtube.com`.
And `Susan`'s hobby is `video games`.
Allright, and so that's kind of the basic code here just to get started with creating an `instructor`.
Now, we need to go ahead and write the code to add courses for the `instructor`.
An `instructor` can have zero to many courses, so let's go ahead and do that now.

````java
private void createInstructorWithCourses(AppDAO appDAO) {

    // create the instructor
    // create the instructor detail
    // associate the objects
    
    // create some courses
    Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
    Course tempCourse2 = new Course("The Pinball Masterclass");
    
    // add courses to instructor
    tempInstructor.addCourse(tempCourse1);
    tempInstructor.addCourse(tempCourse2);
}
````

So, I'll just create some courses.
I'll create this course, `tempCourse1`, and I give the actual name of the course, 
"`Air Guitar - The Ultimate Guide`".
And I'll just do a little copy-paste here on that course,
and then I'll have this `tempCourse2`, and I'll give the actual title for that temp course,
and this is "`The Pinball Masterclass`".
Now I have the courses created, I need to go ahead and add those courses to the `instructor`.
So, I'll make use of my `tempInstructor`, and I'll say `addCourse`,
and I'll give a reference to the course that I want to add, so I'll do `tempCourse1`,
and then I'll simply do a similar thing in here for `tempCourse2`.
Okay, great, so we've added those courses to the `instructor`, but this is all still in memory.
We haven't really saved anything to the database yet, and we need to move in here and do that work.

````java
private void createInstructorWithCourses(AppDAO appDAO) {

    // create the instructor
    // create the instructor detail
    // associate the objects
    // create some courses
    // add courses to instructor
    
    // save the instructor
    // 
    // NOTE: this will ALSO save the courses
    //
    System.out.println("Saving instructor: " + tempInstructor);
    System.out.println("The courses: " + tempInstructor.getCourses());
    appDAO.save(tempInstructor);
    System.out.println("Done!");
}
````

So, I'll just write a quick comment here "`save the instructor`".
I'll do a `System.out.println`, and then I'll do another print line 
where I actually list the courses that we're going to save.
I call `appDAO.save` and I parse in that `tempInstructor`.
And note here, this will also save the courses 
because we are the cascade type of `PERSIST` in the actual `instructor` entity.
So, we simply do this one save, and then it'll do all the background work for us.
And then, finally, I do a little `System.out.println("Done!")`.
Let's go ahead and run it and test it out.

````text
Saving instructor: Instructor{id=0, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=0, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
The courses: [Course{id=0, title='Air Guitar - The Ultimate Guide'}, Course{id=0, title='The Pinball Masterclass'}]
DEBUG 43852 --- [           main] org.hibernate.SQL                        : insert into instructor_detail (hobby,youtube_channel) values (?,?)
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [Video Games]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [http://www.youtube.com]
DEBUG 43852 --- [           main] org.hibernate.SQL                        : insert into instructor (email,first_name,instructor_detail_id,last_name) values (?,?,?,?)
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [susan.public@luv2code.com]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Susan]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [1]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Public]
DEBUG 43852 --- [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Air Guitar - The Ultimate Guide]
DEBUG 43852 --- [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
TRACE 43852 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [The Pinball Masterclass]
Done!
Process finished with exit code 0
````

Okay, excellent, so our app ran successfully.
It said, "`Saving instructor`".
It also printed out a list of courses that it was going to save, and then,
we see the `insert into instructor_detail`, `insert into instructor`, or `Susan Public`.
That all looks pretty good, and then we have this `one-to-many` courses.
So, here I have `insert into course`, and I give that course for `Air Guitar`,
and then also `insert into course` for `The Pinball Masterclass`.
Alright, so let's go ahead and verify this in our database.
So, I'll swing over to **MySQL Workbench**.
I'll move to this schema here, `hb-02-one-to-many`.
I'll do a query here on the `instructor` tables:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image42.png" alt="image42">
</div>

so we have this one `instructor` here, `Susan Public`.
I'll check the `instructor_detail`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image43.png" alt="image43">
</div>

Okay, and then hobby of `Video Games`.
And then we go to the course table and do a query:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image44.png" alt="image44">
</div>

and we see we have those two courses there, `Air Guitar` and `The Pinball Masterclass`.
So, we have that `one-to-many` relationship here for this `instructor`.
Now, why does the course 'id' field start at 10?
Why didn't it start at one?
We can find this out by looking at the database script for creating this given table.

````sql
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `instructor` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
````

We see that the id is `AUTO_INCREMENT=10` entry here.
So, we set the initial value of `AUTO_INCREMENT` to "`10`" on the course table.
From there on out, it starts with "`10`", and it starts incrementing from there.
So, that's why we start at "`10`" and not at "`1`",
and I did that kind of on purpose just to show you
that there's some flexibility as far as starting with the initial course id.
So, that could be any number.
You simply specify that in that file.
So, we had that `one-to-many` relationship working as far as saving an `instructor`.

</div>

## [@OneToMany Mapping - Fetch Types: Eager vs Lazy]()
<div style="text-align:justify">

In this section, we're going to cover fetch types for eager vs lazy.
Now, when we load data, or we retrieve or fetch data, the question is, should we retrieve everything?
So, an **Eager** load will pull in everything.
**Lazy** load will retrieve data only on request.
So, imagine we have an `instructor`.
An `instructor` has a list of `course`s.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image03.png" alt="image45">
</div>

Depending on the loading type, that'll determine when and how the data is loaded from the database by **Hibernate**.
And it also has an implication on your actual performance of your given app.
Now, with **Eager** loading, it'll actually load all your dependent entities,
so it'll load the `instructor` and all of their `course`s at once.
So, it'll do a one quick shot to the database, grab all the data, and bring it back.
Now, this may not be a big deal if you only have a small number of `instructor`
and a small number of `course`s, but you could imagine if you had a lot of data out there,
that could actually impact the performance of your application.
Now, let's take a look at another example with **Eager** loading.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image46.png" alt="image46">
</div>

So, what about a `course` and `student`s?
So, this could easily turn into a performance nightmare.
So, if we were to load a `course` and then actually load all the `student`s for the `course`,
then most likely it will slow down our application
because just like the `course` that you're attending now,
a given course could have `10,000`, `20,000`, maybe `50,000` students,
and we really don't need all that data at this point.
So in our app, if we're simply searching for a `course` by keyword,
like just doing your normal search there, we only want a list of matching `course`s.
But **Eager** loading, it will still load all the `student`s for each `course`.
We only want the titles or the descriptions of the `course`s, but not all the `student`s.
So, the best practice in the industry is to **only load data when absolutely needed**,
so you should prefer **Lazy** loading to **Eager** loading.
Now, there's always exceptions to the rule.
And there's also special use cases, but in general,
the recommendation is to prefer **Lazy** loading instead **Eager**.
**Lazy** loading will load the main entity first, and then, 
it'll actually load the dependent entities on demand at a later time.
So let's say we have a `course`, so it'll actually load the `course` first,
and then when you need a list of `student`s,
when it'll actually go to the database it'll load those `student`s on demand.
So, they'll be loaded at a later time.
And again, remember, the preference here is on making use of **Lazy** loading
to make sure our application performs in a fast manner.
So, **Lazy** loading is preferred.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image47.png" alt="image47">
</div>

Let's pull this together with the real world use case.
And that's for searching for `instructor`s.
So I have this website here `luv2code academy`.
It'll give me a list of `instructor`s.
I can also type in the `instructor`'s name and search for them.
So in this case, I simply want to get a high level list of the `instructor`s.
And then over on the far right, there's an action link where you can actually go ahead
and drill down and view the details of a given `instructor`.
So in our real world use case, in the master view, we want to make use of **Lazy** loading.
In the detail view, we want to retrieve the entity and their necessary dependent entities.
So in this case, I'm going to use **Lazy** loading for the search results.
So basically I only want to load the `instructor`s, but not their `course`s.
If we wanted `course` information, we can select the link here, `View Details`,
to actually drill down into that given `instructor`.
So this will actually result in a faster query on our database application.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image48.png" alt="image48">
</div>

Okay, so looking at the detail view, here we'll actually retrieve the entity
and their necessary dependent entities.
So here we'll actually load the `instructor` and their `course`s.
So I have the instructor, `John Doe`, his email address, 
and then I also have the list of `course`s for this given `instructor`.
So this works out fine, right, 
because we're only looking at one `instructor` and only their list of `course`s.
We're not trying to access a thousand `instructor`s with their thousands and thousands of `course`s.
So this is a very good use case on how to load the data in a master detail view.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    // other fields

    @OneToMany(fetch=FetchType.LAZY, mappedBy="instructor", cascade = {CascadeType.PERSIST,
                                                                      CascadeType.MERGE,
                                                                      CascadeType.DETACH,
                                                                      CascadeType.REFRESH})
    private List<Course> courses;

    // constructors

    // getter/setter, toString() methods
}
````

Now let's discuss the idea of a fetch type.
So when you define the mapping relationship between two entities or classes,
you can specify the fetch type of being **EAGER** or **LAZY**.
So in this example here we have an `instructor`, and remember, an `instructor` has a list of `course`s.
Here I'll have the `@OneToMany`, and then I'll set up the `FetchType`.
I'll say `fetch=FetchType.LAZY`.
So in that case, they'll actually load the `instructor` first,
and then they'll only load the courses on demand.
They'll load the `course`s in a lazy fashion at a later time.
Now you may wonder, hey, well we've used some of these relationships before,
but we didn't specify a fetch type.
Well, in that case, they're simply making use of the defaults.
So here's a list of defaults for the different relationship types.

<table align="center">
    <thead>
        <th>Mapping</th>
        <th>Default Fetch Type</th>
    </thead>
    <tbody>
        <tr>
            <td><strong>@OneToOne</strong></td>
            <td>FetchType.EAGER</td>
        </tr>
        <tr>
            <td><strong>@OneToMany</strong></td>
            <td>FetchType.LAZY</td>
        </tr>
        <tr>
            <td><strong>@ManyToOne</strong></td>
            <td>FetchType.EAGER</td>
        </tr>
        <tr>
            <td><strong>@ManyToMany</strong></td>
            <td>FetchType.LAZY</td>
        </tr>
    </tbody>
</table>

So for `one-to-one`, the default type is `EAGER`.
For `one-to-many`, the default type is `LAZY`.
And for `many-to-one`, it is `EAGER`.
And for `many-to-many`, the default is `LAZY`.
So those are the default types if you don't specify a fetch type when you actually set up the relationship.
Now, you can also override the default fetch type.

````java
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="instructor_id")
private Instructor instructor;
````

We can simply make use of an example here
where we have this `many-to-one` the default type is `EAGER`, but we don't want to use `EAGER`.
We're gonna actually override that value.
And you simply just be explicit.
You say `@ManyToOne(fetch=FetchType.LAZY)`.
That'll actually override the default fetch type.

Allright, so a bit more about **LAZY** loading.
So when you **LAZY** load, the data is only retrieved on demand.
But the caveat here is that this requires an open **Hibernate** session.
So you basically need a connection to the database to retrieve data at a later time.
But then you're probably wondering, well, what happens if the **Hibernate** session is closed?
Well, if you attempt to retrieve that **LAZY** data once the session's closed,
then **Hibernate** will actually throw an exception.
So you really have to kinda watch out for this.
In the following sections, we'll actually write code, and we'll break it on purpose.
We'll write some bad code to actually throw this exception,
and then I'll show you how we can actually resolve this issue and work around it.

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            findInstructorsWithCourses(appDAO);
		};
	}

    private void findInstructorsWithCourses(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, 
    // findInstructor, createInstructor, createInstructorWithCourses methods

}
````

Now what I'd like to do is move into our `CruddemoApplication`.
And let's move down to our `CommandLineRunner` method.
And what I'll do is I'll comment out the `createInstructorWithCourses`,
and I'll make a call to a new method.
I'll call this method `findInstructorWithCourses`.

````java
private void findInstructorsWithCourses(AppDAO appDAO) {

    int theId = 1;
    System.out.println("Finding instructor id: " + theId);
    
    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associated courses: " + tempInstructor.getCourses());
    System.out.println("Done!");
}
````

I'll start out with the usual, set up the instructor id of one,
do a `System.out.println`, `Finding instructor id`.
And now what I'll do is I'll make a call to the `AppDAO`.
I'll say, `findInstructorById` and I'll pass in `theId`.
And so that'll just give us the basic `instructor` with `instructor_detail`
but doesn't really do the `course`s because the `course`s are `LAZY` loaded.
And then I'll just do a `System.out.println` on the `instructor`.
And also do a `System.out.println` on the associated `course`s  by saying `tempInstructor.getCourses()`.
And then finally, I'll wrap it up with a `"Done"` statement.
Allright, so just kind of standing back, this is the code here for `findInstructorWithCourses`.
Now let's go ahead and run the application.

````text
Finding instructor id: 1
DEBUG 5368 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 5368 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
ERROR 5368 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.luv2code.cruddemo.entity.Instructor.courses: could not initialize proxy - no Session
	at org.hibernate.collection.spi.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:636) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	...

Process finished with exit code 1
````

And the app is running, but We have an exception.
Here, we started out with saying, finding instructor ID of one.
We printed out the `instructor`, that was fine, but then we had an exception
when we try to get some more data here.
So we had this `LazyInitializationException`.
So remember the fetch type for `@OneToMany`, it defaults to `LAZY`.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    //...

    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST,
                                                CascadeType.MERGE,
                                                CascadeType.DETACH,
                                                CascadeType.REFRESH})
    private List<Course> courses;

    //..
}
````

This exception is telling us the real problem, "_Failed to lazily initialize a collection of role_",
and then it's basically saying, it couldn't initialize the `course`s.
That's because the courses are fetch type of `LAZY`, by default.
And over here, it says, "`could not initialize proxy - no Session`".
Now the session was closed before we could actually access the courses, hence the error.
Allright, so we kind of see that problem.
And then a quick solution is to change the fetch type to `EAGER`.
So it's not our only solution, we'll explore others later on in sections,
but we'll kind of just use this quick solution for now.
I'll go ahead and open up `Instructor`.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    //...

    @OneToMany(mappedBy = "instructor", 
               fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    //..
}
````

So here it is right here, the `@OneToMany` for our `courses`.
Here, I'll change the fetch type to `EAGER`.
So `fetch = FetchType.EAGER`.
And now when we've retrieved the `instructor`,
then we'll also retrieve the `courses` for that `instructor`.
Allright, so let's go ahead and run this again.

````text
Finding instructor id: 1
DEBUG 39032 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.instructor_id,c1_0.id,c1_0.title from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id left join course c1_0 on i1_0.id=c1_0.instructor_id where i1_0.id=?
TRACE 39032 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
the associated courses: [Course{id=10, title='Air Guitar - The Ultimate Guide'}, Course{id=11, title='The Pinball Masterclass'}]
Done!

Process finished with exit code 0
````

And success.
We see here, we're finding instructor id of one,
we print out the `tempInstructor` information, and we also print out the associated `courses`.
`Air guitar` and `The Pinball Masterclass`.
So we have courses now, no more errors, no more exceptions.
That's all because of that quick solution we did by changing the `@OneToMany` mapping to `EAGER`.

</div>

### [@OneToMany Mapping - Lazy Find]()
<div style="text-align:justify">

In this section, we're going to do a `LAZY: Find Courses`.
Now our previous solution, `EAGER`, it'll retrieve everything, all the `course`s for an `instructor`.
But we may not want this all the time.
We'd like to have "**the option**" to load courses as needed.
So, I'd like to be able to retrieve the `instructor`.
And at a later time if I wanted the `course`s, I'd like to be able to load those.
So basically, loading those `course`s in a lazy fashion, as needed.

````java
@Entity
@Table(name="instructor")
public class Instructor {

    //...

    @OneToMany(mappedBy = "instructor", 
               fetch = FetchType.LAZY ,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    //..
}
````

Now, what we can do is we can actually change the fetch type back to "`LAZY`".
So, in our `instructor` class where we have that `one-to-many` relationship with `course`s,
Currently, we have it as `EAGER`.
we can say `FetchType.LAZY`.
Now, the fetch type for `one-to-many` defaults to `LAZY`.
So, I really don't need to specify it here.
However, I'll explicitly list it just for readability purposes,
just so that we're all on the same page, and it's very clear to what's happening here.
Now, let's add a new method to find courses for instructors.
So, we may already have the `instructor`.
Now, I simply want to go back and get the `course`s for that given `instructor`.
What I'll do is I'll add this new method in our `AppDAO` interface and in its implementation class.
So I open `AppDAO` interface:

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
}
````

It's called, "findCoursesByInstructorId".
Let's use IDE's warning to create the method in the implementation class:

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, findInstructorDetailById, deleteInstructorDetailById methods

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);
        
        // execute query
        List<Course> courses = query.getResultList();
        
        return courses;
    }
}
````

I'll say, `"from Course where instructor.id = :data"`, and that's basically a parameter.
Here, I say, "`query.setParameter("data",theId)`".
So, this will basically give us the `course`s for a given `instructor` id.
Then we execute the query, we get a list of courses, and then we finally return those courses.
So, that's the new method that we've added to our `AppDAOlmpl`.
Then in our main application here: 

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            findCoursesForInstructor(appDAO);
		};
	}

    private void findCoursesForInstructor(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, 
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses methods

}
````

I'll comment out the previous code, and then I'll make a call to this new method, `findCoursesForInstructor`.
I have this new method, `findCoursesForInstructor`, to find courses for instructor.
Let's move into the new method here:

````java
private void findCoursesForInstructor(AppDAO appDAO) {
    
    int theId = 1;
    
    // find the instructor
    Instructor tempInstructor = appDAO.findInstructorById(theId);
    System.out.println("tempInstructor: " + tempInstructor);
    
    // find courses for instructor
    List<Course> courses = appDAO.findCorusesByInstructorId(theId);
    
    // associate the objects
    tempInstructor.setCourses(courses);
    
    System.out.println("the associated courses: " + tempInstructor.getCourses());
    System.out.println("Done!");
}
````

And then I say to find the `instructor`,
I make use of this new method, `appDAO.findInstructorById`.
I pass in `theId`.
Now, since the fetch type for courses is lazy, 
this will retrieve the `instructor` without the courses.
Because it's lazy, they're not going to get it for us by default.
And then what I'll do is I'll make use of that other method 
or that new method that I just created `AppDAO.findCoursesByInstructorId`.
Allright, so that'll give us the list of courses for this given instructor id.
And then we have to go through and associate the objects.
So I have the `instructor`, I have the `courses`.
I need to kind of associate those two together and kind of put those two together.
So here I'll say, "`tempInstructor.setCourses(courses)`".
Then, I can print out the associated `courses` by making use of "`tempInstructor.getCourses`",
and we finally finish it up with the little "`Done`" statement.
Allright, here's the code here for finding courses by an instructor.
Now let's go ahead and run it, test it out.

````text
DEBUG 3260 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 3260 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
DEBUG 3260 --- [           main] org.hibernate.SQL                        : select c1_0.id,c1_0.instructor_id,c1_0.title from course c1_0 where c1_0.instructor_id=?
TRACE 3260 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 3260 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 3260 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
the associated courses: [Course{id=10, title='Air Guitar - The Ultimate Guide'}, Course{id=11, title='The Pinball Masterclass'}]
Done!

Process finished with exit code 0
````

And success.
So we're able to find the `instructor` ID of 1.
We have an instructor finding courses.
And then we can also print out the associated `courses`.
Excellent, so `Air Guitar`, `The Pinball Masterclass`.
Now the nice thing here with this example is that we were able to find the `instructor`.
We knew the courses were lazy loaded.
We had another query to find those courses.
We put everything in together, and then we could have the `instructor` along with the `courses`.

</div>

### [@OneToMany Mapping - Join Fetch]()
<div style="text-align:justify">

In this section, we'll make use of `LAZY`, 
and we'll find instructor with courses with a slight refinement or refactoring.
The previous solution for finding courses for instructor, it was okay, but it required an extra query.
We had to first, go get the `instructor` and then later on, go get the `courses`.
And I wish we could have a new method that would get the instructor and courses in a single query
and also keep the `LAZY` option available.
I don't want to change the `FetchType`.
Well, what we can do is add a new method into `appDAO` interface and its implementation
to find instructor with courses.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    
    Instructor findInstructorByIdJoinFetch(int theId);
}
````

Let's open the implementation class and add this method:

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, 
    // findInstructorDetailById, deleteInstructorDetailById, findCoursesByInstructorId methods

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }
}
````

And in this method I'll make use of this new technique called `JOIN FETCH`.
So, I'll have this `entityManager.createQuery`.
I'll do a, "`select i from Instructor i`".
So here, `i` is just an alias for the word `instructor`.
And I'll say "`JOIN FETCH i.courses where i.id = :data`".
Now what this `JOIN FETCH` would do is that
even with `instructor` relationship of `one-to-many` FetchType of `LAZY`,
this code will still retrieve the `instructor` and `courses`,
and that's because we're using `JOIN FETCH`.
The `JOIN FETCH` is similar to `EAGER` loading without having to hard code `EAGER` loading.
So that's the idea of `JOIN FETCH`.
Now we have this query set up.
We do our standard work of saying `query.setParameter("data", theId)` for that instructor id accordingly.
We simply go through and execute the query.
We know here that we're simply getting a single `instructor` so we say `query.getSingleResult()`
and then I simply return that `instructor`.
Now in my main application:

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            findInstructorWithCoursesJoinFetch(appDAO);
        };
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, 
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor methods

}
````

I can actually make use of this given method.
Let's open the method:

````java
private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

    int theId = 1;

    // find the instructor
    System.out.println("Finding instructor id: " + theId);
    Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associated courses: " + tempInstructor.getCourses());
    System.out.println("Done!");
}
````

So I can say `appDAO.findInstructorByIdJoinFetch`, passing that `theId`
and it's going to give me the `tempInstructor`.
Now that's the new code that we just created.
And remember, this code will still retrieve the `instructor` and `courses` for that given `instructor`,
because we're making use of `JOIN FETCH`.
Now the really nice thing here is that we have options now 
as far as how to retrieve the `instructor` and `courses`.
If you only need `instructor` and no `courses` then we simply call `appDAO.findInstructorById`.
If you need the `instructor` and `courses`, then call `appDAO.findInstructorByIdJoinFetch`.
So this gives us flexibility instead of having to hard code `EAGER` fetch into our entity relationship.
So this is a really good approach.
Now let's go ahead and run this application.
And the app is up and running.

````text
Finding instructor id: 1
DEBUG 30992 --- [           main] org.hibernate.SQL                        : select i1_0.id,c1_0.instructor_id,c1_0.id,c1_0.title,i1_0.email,i1_0.first_name,i1_0.instructor_detail_id,i1_0.last_name from instructor i1_0 join course c1_0 on i1_0.id=c1_0.instructor_id where i1_0.id=?
TRACE 30992 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 30992 --- [           main] org.hibernate.SQL                        : select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
TRACE 30992 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
the associated courses: [Course{id=10, title='Air Guitar - The Ultimate Guide'}, Course{id=11, title='The Pinball Masterclass'}]
Done!

Process finished with exit code 0
````

It ran successfully.
Here we have finding instructor ID of one.
And if we look at the **SQL**, we'll see that this first bit here, 
`select`, grabs all those fields from `instructor`.
Okay, we get all the `instructor` information.
Then we do a `join` on `course`, allright, so for that given `instructor` id.
We'll get the appropriate information for those `courses` for that given `instructor`.
Now, this next query here we do a `select from instructor_detail`.
We `join` on `instructor`, so we get the appropriate details for that given `instructor`.
And so now we can print out the actual `instructor` and also the associated `courses`.
And so we can take a look at these queries here.
So we have a query for `instructor` and `courses`.
Previously we had to do it one by one, but now we're getting the `instructor` and `courses` together.
And then we have this other query here for `instructor_detail`.
Now, we can actually refine this or refactor this a bit more where we can minimize the queries.
And we can do that by adding the `instructor_detail` to the `JOIN FETCH`.
Let's try and get it down to just one big query.
I'll swing back over to my `AppDAOimpl`.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, 
    // findInstructorDetailById, deleteInstructorDetailById, findCoursesByInstructorId methods

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                "select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                        + "JOIN FETCH i.instructorDetail "
                                                        + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }
}
````

And in that method, `findInstructorByIdJoinFetch`, I'll just add another join here.
I'll say `JOIN FETCH i.instructorDetail`.
So that's the new item that we're adding here for this query.
And so now we should be able to get everything in just one query as opposed to having multiple queries.
Let's go ahead and run this application and test it out.

````text
Finding instructor id: 1
DEBUG 19968 --- [           main] org.hibernate.SQL                        : select i1_0.id,c1_0.instructor_id,c1_0.id,c1_0.title,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 join course c1_0 on i1_0.id=c1_0.instructor_id join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 19968 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
tempInstructor: Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}
the associated courses: [Course{id=10, title='Air Guitar - The Ultimate Guide'}, Course{id=11, title='The Pinball Masterclass'}]
Done!

Process finished with exit code 0
````

Allright, so find the `instructor` id of one.
Now let's go look over here at this generated **SQL**.
And then notice here we've minimized the queries by adding `instructorDetail` to `JOIN FETCH`.
So we have this `select`, and then we have from `instructor`, and then we `join` on `course`,
and then we `join` on `instructor_detail`.
So we can do all of this in this one query.
It's a big query, right?
But we're doing it all in one query as far as hitting the database.
And then we can get the output here, the `tempInstructor`, and also the associated courses.
So we were successful here in minimizing the number of queries for that data.

</div>

### [@OneToMany Mapping - Update / Delete]()
<div style="text-align:justify">

In this section, first we're going to cover `@OneToMany` for updating an `instructor`.
Now, to update an `instructor`, we need to first find the `instructor` by id, 
and then we'll change the `instructor`'s data by calling setter methods.
And finally we'll update the instructor using the **DAO**.
Let's add a new **DAO** method to update the `instructor`.
We'll start by adding this method to our **DAO** interface.

`````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    
    void update(Instructor tempInstructor);
}
`````

Let's open the implementation class and create the method:

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, 
    // findInstructorDetailById, deleteInstructorDetailById, 
    // findCoursesByInstructorId, findInstructorByIdJoinFetch methods

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);
    }
}
````

Now in our **DAO** we have this method called `update`.
We pass in an `instructor`.
And then here I say `entityManager.merge(tempInstructor)`.
Now the merge method will update an existing entity, and in this case we get to update the `instructor`.
Now in our main application, we comment `findInstructorWithCoursesJoinFetch`,
what we'll do is we'll have this new method called `updateInstructor`:

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            updateInstructor(appDAO);
        };
    }

    private void updateInstructor(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, 
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor, findInstructorWithCoursesJoinFetch methods

}
````

Pass in the `appDAO`.
We have the `instructor` id.
Let's move into this method `updateInstructor`:

````java
private void updateInstructor(AppDAO appDAO) {

    int theId = 1;

    System.out.println("Finding instructor id: " + theId);
    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("Updating instructor id: " + theId);
    tempInstructor.setLastName("TESTER");

    appDAO.update(tempInstructor);
    System.out.println("Done!");
}
````

We do a little `System.out.println` on finding `instructor` id.
We grab that `instructor` accordingly from the **DAO**.
And then we go through it, and we actually update the `instructor`'s `lastName`.
Here I'll say `tempInstructor.setLastName("TESTER")`.
And I could give any value there, I'm just simply giving the name of `TESTER`.
And then we make use of `appDAO.update()`, and we pass in that `tempInstructor`.
And that's the coding there for updating an `instructor`.
And I'll go ahead and run this application.

````text
Finding instructor id: 1
DEBUG 38788 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Updating instructor id: 1
DEBUG 38788 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.instructor_id,c1_0.id,c1_0.title from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id left join course c1_0 on i1_0.id=c1_0.instructor_id where i1_0.id=?
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 38788 --- [           main] org.hibernate.SQL                        : update instructor set email=?,first_name=?,instructor_detail_id=?,last_name=? where id=?
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [susan.public@luv2code.com]
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Susan]
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [1]
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [TESTER]
TRACE 38788 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (5:INTEGER) <- [1]
Done!

Process finished with exit code 0
````

And the application ran successfully.
Here it says, "`Finding instructor id: 1`".
And then we update that instructor ID of 1.
Now let's swing over to our database and check the results.
So we had earlier, `Susan Public`.
Now, if we do a refresh then this will refresh and give us the latest data, or the new data.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image49.png" alt="image49">
</div>

And then success.
The instructor was updated because we said `tempInstructor.setLastName` to `TESTER`.
We were successful here in updating an instructor.

Now, we're going to update a `course`.
In order to update a `course`, what we need to do is find the course by ID,
and then we can change the course's data by calling setter methods.
And finally, we'll update the course using the **DAO**.
Now let's look at adding a new **DAO** method to update the course.
I'll start here by opening up my `AppDAO` interface:

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    
    void update(Course tempCourse);
    Course findCourseById(int theId);
}
````

I'll add this new method here to `update`.
We pass in a `Course`.
And also, we'll need to add a method to find a course by ID
because we don't really have this functionality yet.
So we'll go ahead and add this new method, `findCourseById`.
Allright, so let's go ahead and swing over to our `AppDAOImpl` and implement those methods.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById, 
    // findInstructorDetailById, deleteInstructorDetailById, 
    // findCoursesByInstructorId, findInstructorByIdJoinFetch
    // update(Instructor tempInstructor), methods

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }
    
    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }
}
````

And then for `update`, we need to start with the `@Transactional`
because we're modifying the database,
and then we make use of the `entityManager.merge`, just like we did earlier.
In this case, we simply pass in the `tempCourse`.
So this will allow us to update the existing entity or update the `course` in the database.
And then we have some quick work we need to do down here for finding course by ID.
We simply make use of `entityManager.find`, we give the `Course.class`, and then we pass in `theId`.
And that's it.
Very simple, very straightforward.
Then moving over to our main application:

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            
            updateCourse(appDAO);
        };
    }

    private void updateCourse(AppDAO appDAO) {
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor, 
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor, findInstructorWithCoursesJoinFetch 
    // updateInstructor methods

}
````

We'll have this new method here called `updateCourse`.
Let's just focus on the new method now:

````java
private void updateCourse(AppDAO appDAO) {

    int theId = 10;

    // ...
}
````

I'll go ahead and set up the course ID.
And let's just kind of verify the course ID because right now I have `theId = 10`.
You may wonder how did I arrive at that number.
Let's go ahead and look at our database real quick and check on the course IDs.
In **MySQL Workbench**, let's go ahead and do a query on `course`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image50.png" alt="image50">
</div>

And we can see here we have two courses, one of those courses has the ID of `10`,
so that's why we're using the given course ID of `10`.
And also your values may be slightly different.
Simply use a valid course ID that you have in your database.

````java
private void updateCourse(AppDAO appDAO) {

    int theId = 10;

    System.out.println("Finding course id: " + theId);
    Course tempCourse = appDAO.findCourseById(theId);

    System.out.println("Updating course id: " + theId);
    tempCourse.setTitle("Enjoy the Simple Things");

    appDAO.update(tempCourse);
    System.out.println("Done!");
}
````

Allright, swinging back to the **IDE**, the first thing we want to do here is find the course.
Here I'll have `tempCourse = appDAO.findCourseById`.
And now I want to go ahead and update the course.
And basically here, I'm simply going to change the `course`'s title.
And I'll say `tempCourse.setTitle()`, and I give a value of `Enjoy the Simple Things`.
And now we'll go ahead, and we'll make use of our `appDAO`
and we'll call `update`, and we'll pass in our `tempCourse`.
And by calling this **DAO** method, it'll actually update the information in the database.
Allright, this is all the code here required to update the course from our main application.
Let's go ahead and run it.

````text
Finding course id: 10
DEBUG 41808 --- [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
Updating course id: 10
DEBUG 41808 --- [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
DEBUG 41808 --- [           main] org.hibernate.SQL                        : select c1_0.instructor_id,c1_0.id,c1_0.title from course c1_0 where c1_0.instructor_id=?
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 41808 --- [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Enjoy the Simple Things]
TRACE 41808 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [10]
Done!

Process finished with exit code 0
````

It says `Finding course id: 10`, and then `Updating course id: 10`.
And then we'll go ahead and refresh that data.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image51.png" alt="image51">
</div>

And success, the course was updated.
So we actually updated the course title to `Enjoy the Simple Things`.

Now with `one-to-many`, we will delete an `instructor` that has associated courses.
In order to delete an `instructor`, we first need to find the instructor by ID,
and then we need to break the association of all the `instructor`'s `courses`, 
and finally delete the `instructor`.
Now we already have a method to delete an instructor in our `AppDAO` interface 
called `deleteInstructorById`. 

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    
    void deleteInstructorById(int theId);
    
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
}
````

So we need to update it in the implementation class:

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById,  
    // findInstructorDetailById, deleteInstructorDetailById, 
    // findCoursesByInstructorId, findInstructorByIdJoinFetch,
    // update(Instructor tempInstructor), update(Course tempCourse),
    // findCourseById, methods

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

The first thing we do is we retrieve the `instructor`, and directly deleting it.
But then we get an error.
So we need to set the `courses`'s instructor columns to `null`.
Let's focus on the method we have:

````java
@Override
@Transactional
public void deleteInstructorById(int theId) {

    // retrieve the instructor
    Instructor tempInstructor = entityManager.find(Instructor.class, theId);

    List<Course> courses = tempInstructor.getCourses();

    // break associations of all courses for instructor
    for (Course tempCourse : courses) {
        tempCourse.setInstructor(null);
    }

    // delete the instructor
    entityManager.remove(tempInstructor);
}
````

So we get a list of courses for that `instructor`, 
and we break the association of all courses for the `instructor`.
We simply do a for each loop through the courses, and we say `tempCourse.setInstructor` to `null`.
This will effectively remove the `instructor` from the `courses` and break that association.
Because once we're deleting the `instructor` then we need to tell the `courses`,
"_Hey, this `instructor`'s no longer available. 
We need to remove them from that `course`_".
Now you may wonder, well, what happens if I try and delete an `instructor` even if it's referenced by another `course`?
Well, let's see. 

````text
Caused by: java.sql.SQLIntegrigtyConstraintViolationException:
Cannot delete or update a parent row: a foreign key constraint fails

(`hb-02-one-to-many`.`course`,
CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`))
````

It'll actually give us an error message here, and this will actually be a constraint violation.
And here's the ugly exception or the ugly error that you'll see.
You'll see this **SQL** integrity constraint violation exception,
and it'll tell you why this exception occurred.
It says that the foreign key constraint that fails.
So in simple terms, this means that an `instructor` cannot be deleted if it's referenced by a `course`.
You must remove the `instructor` from the `course` first before deleting the `instructor`.
And that's the process that you'll have to follow.
If not, you'll have this ugly exception.
So this kind of explains the reasoning here in our code 
where we have to break the association of all courses for the `instructor`,
effectively removing the `instructor` from each one of its courses.
Now, once that's done, then we can actually delete the `instructor`
by saying `entityManager.remove(tempInstructor)`.
Now also another thing here is that we only delete the `instructor`.
We don't delete the associated `course`, and that's based on the cascade types
that we worked on in some of the earlier sections.
Now we're moving ahead to our main application, and here we have this method called `deleteInstructor`.

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updateCourse(appDAO);
        };
    }

    private void deleteInstructor(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }
    
    // deleteInstructorDetail, findInstructorDetail, 
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor, findInstructorWithCoursesJoinFetch 
    // updateInstructor methods

}
````

Let's focus on the method:

````java
private void deleteInstructor(AppDAO appDAO) {

    int theId = 1;
    System.out.println("Deleting instructor id: " + theId);

    appDAO.deleteInstructorById(theId);
    System.out.println("Done!");
}
````

We set up the instructor ID, and then we simply do an `appDAO.deleteInstructorById` and we pass in `theId`.
And that's it, very simple, very straightforward.
There is no update here.
Let's go ahead and verify this ID of 1 for the `instructor`.
I'll move over here to this `hb-02-one-to-many`.
I'll do a query here on the `instructor` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image49.png" alt="image52">
</div>

And I have this one instructor, `Susan Tester`.
I'll also take a look at the courses here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image51.png" alt="image53">
</div>

So I see I have two courses.
And also notice here the `instructor_id` of 1 that refers to the actual `instructor` that I have.
I'll have to set those values to `null`  before I actually delete the `instructor`,
and we'll do all of that in the code.
Our code's already set up for that.
Allright, so that's the coding here for `appDAO.deleteInstructorById`.
And let's go ahead and run it and test it out.

````text
Deleting instructor id: 1
DEBUG 8408 --- [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 8408 --- [           main] org.hibernate.SQL                        : select c1_0.instructor_id,c1_0.id,c1_0.title from course c1_0 where c1_0.instructor_id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 8408 --- [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Enjoy the Simple Things]
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [10]
DEBUG 8408 --- [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [The Pinball Masterclass]
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [11]
DEBUG 8408 --- [           main] org.hibernate.SQL                        : delete from instructor where id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
DEBUG 8408 --- [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
TRACE 8408 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Done!

Process finished with exit code 0
````

Okay, so the application ran.
It says `Deleting instructor id: 1`.
Now let's swing over to our database.
And we have some old data here for `Susan Tester`.
Let's go ahead and refresh.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image54.png" alt="image54">
</div>

And success, we deleted the `instructor` ID of 1.
And then also, let's go and check on our `courses` here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image55.png" alt="image55">
</div>

And we have these two courses.
So the course is still here as desired, 
but notice here that the `instructor_id` was removed from the courses.
So for those courses here, notice `instructor_id` is `null`.
Allright, and that's that preliminary work we had to do of removing the `instructor` from the `course`
before we could actually delete the `instructor` based on those constraints.

Now, we'll delete a course with this `One-To-Many` relationship.
We simply delete the `course` by ID.
And now, what we'll do is we'll add a new method into `AppDAO` interface 
and its implementation class to delete a `course`.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
    
    void deleteCourseById(int theId);
}
````

Here we have this `deleteCourseById`.
Let's add this into the implementation class now:

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // save, findInstructorById, deleteInstructorById,
    // findInstructorDetailById, deleteInstructorDetailById, 
    // findCoursesByInstructorId, findInstructorByIdJoinFetch,
    // update(Instructor tempInstructor), update(Course tempCourse),
    // findCourseById, methods

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the instructor
        entityManager.remove(tempCourse);
    }
}
````

We simply retrieve the course by using `entityManager.find()`,
and then we delete the course by using `entityManager.remove(tempCourse)`.
And that basically deletes the `course` from the database.
And now, let's go ahead and move over to our Main Application:

````java
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorsWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updateCourse(appDAO);
            
            deleteCourseById(appDAO);
        };
    }

    private void deleteCourseById(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor,
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor, findInstructorWithCoursesJoinFetch 
    // updateInstructor methods

}
````

So we'll have this method here, `deleteCourseById`.
We simply set up the ID of 10 for the actual course ID,
and then we simply use `appDAO.deleteCourseById`, and we pass in `theId`.
And that's very simple, very straightforward.
Let's go ahead and run this application.

````text
Deleting course id: 10
DEBUG   org.hibernate.SQL           : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : delete from course where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
Done!

Process finished with exit code 0
````

It ran successfully here.
`Deleting course id: 10`.
I'll go ahead and verify this in the database.
So we had this old Course ID of 10, `Enjoy the Simple Things`.
Simply do a Refresh.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image56.png" alt="image56">
</div>

And success!
The `course` id of 10 was deleted, so that's awesome.
So we're able to delete a `course` from the database.

</div>

### [@OneToMany Mapping - Uni-Directional]()
<div style="text-align:justify">

In this section, we're going to cover **Hibernate** `one-to-many` with **Uni-Directional** support.
So with this `one-to-many` mapping, we're going to set it up
where we can have a `course` that can have many `reviews`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image57.png" alt="image57">
</div>

So here's our `course` and a list of `reviews`,
and we'll have this **Uni-Directional** relationship.
So we'll start with `course`, and we'll go one-way to the actual `review` or `reviews` for a given `course`.
So with our real world project requirement, if we delete a `course`,
then we also should delete the `reviews`.
Basically, `reviews` without a `course` have no meaning.
If we delete a `course`, then we should also apply
the cascading deletes for the appropriate `reviews`.
So basically, with our `one-to-many`, here's our table structure or our table relationship.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image58.png" alt="image58">
</div>

On the left hand side, we have a `course`.
On the right hand side, we have the `reviews`.
And we'll have this `one-to-many` relationship set up in a **Uni-Directional** fashion,
and we'll use the **Hibernate** annotation to model this.
Now, this is kinda where we do a little slight digression here. 
Because we've done a lot of great things in this course so far with **Hibernate**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image59.png" alt="image59">
</div>

So up top, we have this `instructor` and `instructor_detail`.
That's our `one-to-one` mapping that we've already set up.
And then also at the bottom with the `instructor` and `course`,
we have this `one-to-many` and `many-to-one` in a **Bi-Directional** format.
And now, we're making use of a `course`, has a collection of `reviews`,
so that's a `one-to-many` **Uni-Directional**.
And this is kinda like our new section.
So here's our development process:

* Define our database tables
* Create a `Review` class
* Update our `Course` class
* Pull it all together by creating a main application

Allright, so let's take a look at this `review` table here.
So here's the actual **SQL** for this table.

````sql
CREATE TABLE `review` (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  ...
)
````

So we're creating table `Review`, we set up three fields, `id`, `comment` and `course_id`.
So the `comment`, that's really just the text field where the user will actually leave their comment.
Like, "`Wow, this course is awesome`".
It's a very basic table here.
And then we'll look at the foreign key relationship
because we have to map this `review` back to a given `course`.

````sql
CREATE TABLE `review` (
  
  ...
  
  KEY `FK_COURSE_ID_idx` (`course_id`),
  CONSTRAINT `FK_COURSE` 
  FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
)
````

So we have our constraints set up.
We set up this foreign key for `course_id`, and then it references the `course` table and the `id` column in that table.
So here with this structure, we have the `review`-`course_id`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image60.png" alt="image60">
</div>

It maps back to the actual `course` table and the `id` column for that `course` table.
And as far as the actual `course` table itself, there's no changes that we need to make.
You can kinda leave that one as is.
There's no work to do there.

Allright, so in step 2, we need to create a `Review` class 
and basically map this class to our database table.

````java
@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // constructors, getters / setters
}

````

So up top, we have this `@Table`, `name` equals `"review"`.
And then we actually set up a reference to the `id` column, and then we map a column for `comment`.
And that's basically it.
And then we have our standard constructors and getters and setters.
And you've seen all this stuff before for basically mapping a very simple entity class.

````java
@Entity
@Table(name="course")
public class Course {

    // other fields

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    // constructors, getters/setters
}
````

Allright, so with step 3, we need to update the `course` to reference the `reviews`.
So remember, the course has a collection of `reviews`.
So here in our actual code here for our `Course` class, we'll have a new field here called `reviews`.
And again, we'll set up the appropriate getter, setter methods for this field.
And next, we need to go through and add our `@OneToMany` annotation on our field here.
So we have `@OneToMany` and we set up a `@JoinColumn`, `name` equals `course_id`.
So this `@JoinColumn` basically refers to the `course_id` column in the `review` table.

Allright, let's get a little bit more information about this `@JoinColumn`.
Let's kinda dig in here and figure out what it means.
Then basically, in this scenario, the `JoinColumn` tells **Hibernate**,
"_Hey, look at the `course_id` column in the `review` table,
and then use this information to help you find the associated `reviews` for a `course`_".
So remember, from our database diagram, we had this `review` table:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image60.png" alt="image61">
</div>

So it has a `course_id` column that's a foreign key that maps back to the `course` table and their given `id`.
So that's how we kind of associate everything together
and pull it all together for a `course` and a collection of `reviews`.

````java
@Entity
@Table(name="course")
public class Course {

    // other fields

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    // constructors, getters/setters
}
````

Now, the other thing we need to do here is add support for cascading.
Because remember, the business requirement, meaning if we delete a `course`,
then we should also delete the `reviews`.
So here, we'll say cascade type of `ALL`,
because we simply want to cascade all operations including deletes.
Allright, so now that we know something about `LAZY` loading, let's go ahead and add support.
So on this `@OneToMany` annotation, I'll set the `fetch` equals `FetchType.LAZY`.
So this will basically lazy load the `reviews`,
it'll load them on demand as requested by our application.
Allright, and then one last thing here, we'll add a convenience method for adding a review.

````java
@Entity
@Table(name="course")
public class Course {
    // fields
    // constructors, getters/setters
    
    // add convenience method for adding reviews
    public void addReview(Review tempReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        
        reviews.add(tempReview);
    }
}
````

So in our `Course` class, we'll simply have this method called `addReview`
that can pass in a `review`, and we'll basically add it to our list of reviews that we have already.
And again, you've seen some of this code before in some of our previous sections.

So next, we're going to add a new method to our **DAO**,
and we'll also make updates to our main application.
Now, let's go ahead and move over to our `AppDAO` interface.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    
    void save(Course theCourse);
}
````

And what I'll do here is I'll add a new method to save a course.
I list this method here, `save`. 
We pass in `theCourse`.
Now, let's swing over to `AppDAOImpl` and let's implement this method.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    
    // other methods

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }
}
````

And then, I'll move down here to this new `save` method.
And since we're modifying the database, we make use of the `@Transactional` annotation.
We simply do `entityManager.persist` and then we give `theCourse`.
Now, this will actually save the `course` and associated `reviews` 
because we have that cascade type of `ALL` defined in our `course` entity.
Now, let's go ahead and make some updates here to our main application.
And what I'll do is I'll move down to the command line runner.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            createCourseAndReviews(appDAO);
        };
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // add some reviews
        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done"));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);
        System.out.println("Done!");
    }
    
    // deleteInstructorDetail, findInstructorDetail, deleteInstructor,
    // findInstructor, createInstructor, createInstructorWithCourses,
    // findInstructorsWithCourses, findCoursesForInstructor, findInstructorWithCoursesJoinFetch 
    // updateInstructor, deleteCourseById methods

}
````

I'll call this new method `createCourseAndReviews`.
And now, inside of this method, let me write some quick comments to myself just to keep myself on track.
To get started here, I'll go ahead and create a `course`.
And this is a new `course`, and the title here is "`Pacman - How To Score One Million Points`".
Allright, let's go ahead and add some `reviews` here to this course.
We say `addReview`.
One student said, "`Hey, great course. Loved it`".
And then, I'll just kind of copy, paste this X number of times,
and I'll just go through and update the information for the `reviews`.
So, here's another good review.
"`Cool course, job well done`".
And now, it's time for a bad review.
"`Hey, what a dumb course. You're an idiot`".
And also one other thing here about saving the `course`,
we'll be able to leverage the cascade `ALL`.
So, we simply save the `course`, and it'll automatically save the associated `reviews`
because we have cascade `ALL` set up.
So, I simply just print out the course, and then I just do a print line on the `reviews`.
We actually say `appDAO.save` now, and then we pass in `tempCourse`.
All right, let's go ahead and run and test it out.

````text
Saving the course
Course{id=0, title='Pacman - How To Score One Million Points'}
[Review{id=0, comment='Great course ... loved it!'}, Review{id=0, comment='Cool course, job well done'}, Review{id=0, comment='What a dumb course, you are an idiot!'}]
DEBUG   org.hibernate.SQL           : insert into course (instructor_id,title) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [null]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [Pacman - How To Score One Million Points]
DEBUG   org.hibernate.SQL           : insert into review (comment) values (?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:VARCHAR) <- [Great course ... loved it!]
DEBUG   org.hibernate.SQL           : insert into review (comment) values (?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:VARCHAR) <- [Cool course, job well done]
DEBUG   org.hibernate.SQL           : insert into review (comment) values (?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:VARCHAR) <- [What a dumb course, you are an idiot!]
DEBUG   org.hibernate.SQL           : update review set course_id=? where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [1]
DEBUG   org.hibernate.SQL           : update review set course_id=? where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [2]
DEBUG   org.hibernate.SQL           : update review set course_id=? where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [3]
Done!

Process finished with exit code 0
````

It ran successfully.
Up top, it said `Saving the course`.
We printed out the `course` and also printed out a list of `reviews` for that given `course`.
And then we have all the **SQL** debug statements there.
So basically, you'll see an `insert` over there for saving the `course`
and also `insert`s for saving the actual `reviews`.
Now, let's go ahead and swing over to **MySQL Workbench** and let's verify this information in the database.
Be sure to make use of the schema, `hb-02-one-to-many-uni`
and then do a query on the `course` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image62.png" alt="image62">
</div>

And we see we have that course,
"`Pacman - How to Score 1 Million Points`".
And then let's go ahead and take a look at the `reviews` for this `course`.
You select on rows on `review`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image63.png" alt="image63">
</div>

And then those are the three reviews.
This all looks really good as far as the actual code that we created
and the actual results that are here in the database.
Let's go ahead and enhance our application.
Let's add some code to retrieve a course and reviews.
And to get started here, let's go ahead and open up our `appDAO` interface.

````java
public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    
    Course findCourseAndReviewsByCourseId(int theId);
}
````

And I'll move down here to the bottom, and I'll add a new method.
And this new method is named `findCourseAndReviewsByCourseId`, and it'll retrieve that actual `course` for us.
Allright, and let's go ahead and move over to our `AppDAOImpl`, and let's implement that method.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // constructor injection for entity manager and other methods

    @Override
    public Course findCourseAndReviewsByCourseId(Course theCourse) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                                                "select c from Course c "
                                                + "JOIN FETCH c.reviews "
                                                + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }
}
````

And then I'll move down here to `findCourseAndReviewsByCourseId`.
And I'll just write some quick comments to myself just to kind of keep myself on track.
Basically here we're just going to `create the query` and then `execute the query`.
And for `creating the query`, I make use of some JPQL(Java Persistence Query Language),
I'll `select c from Course c`, and remember, `c` is just an alias, just a shortcut for that name `Course`.
And then I'll also make use of `JOIN FETCH` because I want to retrieve the `course` and `reviews`.
And then I just set up the `where` clause `where c.id` equals `:data`.
That's the coding there for our query. 
Let's go ahead and set the parameter for `data`, comma `theId`,
and then we can move down here and actually execute that query and get that result.
And it's actually going to be a single result, a single `course`.
Here I make use of `query.getSingleResult()`, and I assign it accordingly.
And now I simply return the `course`.
So this is all the code that's required here for finding a `course` and `reviews` by the course id.
Now that we have this method taken care of,
let's go ahead and move over to our main application.
And I'll move down here to this command line runner method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndReviews(appDAO);
            retrieveCourseAndReviews(appDAO);
        };
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
    }
    
    // other methods

}
````

I'll comment out my previous method, and I'll add a call to this new method, `retrieveCourseAndReviews`.
Now let's go ahead and move down to this method here, `retrieveCourseAndReviews`.
And again, just to keep myself on track, I'll add some quick comments here.

````java
private void retrieveCourseAndReviews(AppDAO appDAO) {

    // get the course and reviews
    int theId = 10;
    Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

    // print the course
    System.out.println(tempCourse);

    // print the reviews
    System.out.println(tempCourse.getReviews());
    System.out.println("Done!");
}
````

So I'll get the `course`, and then I'll print the `course` and also print out the associated `reviews`.
I'll set up the course id, I'll have `tempCourse` equals `appDAO.findCourseAndReviewsByCourseId()`.
And remember that implementation makes use of join fetch on the background,
so it'll actually retrieve the `course` and the `reviews`.
And now this is really easy, I simply just print the `course`, and then print the `reviews`.
Let's go ahead and run this application and test it out.

````text
DEBUG   org.hibernate.SQL           : select c1_0.id,c1_0.instructor_id,r1_0.course_id,r1_0.id,r1_0.comment,c1_0.title from course c1_0 join review r1_0 on c1_0.id=r1_0.course_id where c1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
Course{id=10, title='Pacman - How To Score One Million Points'}
[Review{id=1, comment='Great course ... loved it!'}, Review{id=2, comment='Cool course, job well done'}, Review{id=3, comment='What a dumb course, you are an idiot!'}]
Done!

Process finished with exit code 0
````

Success, because we can actually print out the actual `course`,
and also print out the associated `reviews` for that `course`.
Let's add one more enhancement to our application.
Let's add some code to delete a `course` and its associated `reviews`.
And what I'll do is I'll start here on our main application
and I'll move down to the CommandLineRunner method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndReviews(appDAO);
            //retrieveCourseAndReviews(appDAO);
            deleteCourseAndReviews(appDAO);
        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }
    
    // other methods

}
````

I'll comment out our previous code and I'll add this new method, `deleteCourseAndReviews`.
Now, move down to this method.
I'll set up the course ID of 10, and I'll call this **DAO** method, `appDAO.deleteCourseById()`.
We already have this method in place but now, 
the important thing here is that this will delete the `course` and the associated `reviews`
because we have a cascade type `ALL`, so when we delete the `course` 
and it cascades that delete operation to the actual `reviews`.
Let's go ahead and run our application.
Our app is up and running.

````text
Deleting course id: 10
DEBUG   org.hibernate.SQL           : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : select r1_0.course_id,r1_0.id,r1_0.comment from review r1_0 where r1_0.course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : update review set course_id=null where course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : delete from review where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [1]
DEBUG   org.hibernate.SQL           : delete from review where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
DEBUG   org.hibernate.SQL           : delete from review where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [3]
DEBUG   org.hibernate.SQL           : delete from course where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
Done!

Process finished with exit code 0
````

We see the message here, `Deleting course id: 10`, and you'll see all the `DEBUG` statements
for actually deleting the `course` and then also, deleting all the associated `reviews` for that `course`.
And now, let's verify this in the **MySQL Workbench**.
Now, let's go ahead and refresh for course.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image64.png" alt="image64">
</div>

And then, success.
We see that the `course` is no longer there.
We deleted the `course`.
And now, let's swing over to `reviews`.
Let's go ahead and do a refresh.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image65.png" alt="image65">
</div>

And then again, success.
We deleted the `reviews` for the actual `course`.
We were successful in deleting a `course`and its associated `reviews`.

</div>

## [@ManyToMany Mapping - Overview]()
<div style="text-align:justify">

In this section, we'll cover **Hibernate** `@ManyToMany`, 
and we'll have the scenario where a `course` can have many `students`
and a `student` can be signed up for many `courses`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image66.png" alt="image66">
</div>

So we have our `courses`, and we have our `students` 
and then we have this `many-to-many` relationship between `courses` and `students`.
Now, we somehow need to keep track of relationships.
We need to keep track of which `student` is in which `course` and vice versa.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image67.png" alt="image67">
</div>

And what we can do is actually make use of a `join` table.
So this `join` table is a special table that'll maintain relationships between a `course` and `students`.
So you can use this `join` table to find out who signed up for which `course`.
`join` table is basically a table that provides a mapping between two tables.
It has foreign keys for each table, and it's used to define the mapping relationship.
And we'll see a lot of examples here using `join` tables in this section series.
So here's our diagram.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image68.png" alt="image68">
</div>

So we have our `courses` up top, we have our `students` in the bottom right
and then in the bottom left, we have this `join` table.
So this `join` table's called `course_student`.
This `join` table maintains the relationship between `courses` and `students`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image69.png" alt="image69">
</div>

Allright, so let's go ahead and look at a `join` table example.
In this example, I want to find `John`'s courses.
So here's our database diagram.
Recall, in the bottom left is our `join` table `course_student`.
This will kind of maintain a relationship between `courses` and `students`.
And then let's overlay some data.
So up top we have data for `courses`.
So we have three `courses`, `Pacman`, `Rubik's Cube`, and `Atari` and in the bottom right,
we have some sample data here for `students`.
So we have two students, `John Doe` and `Mary Public`.
And then overlaying some data here for our `join` table and `course_student`,
we have data and some entries here.
And we'll kinda walk through how these numbers match up and how they work out.
So now if I want to find `John`'s courses, then I go and look in the `student` table for `John` 
and I get `John`'s `student_id`.
So I see that `John` has a `student_id` of one.
That takes me over to the `join` table.
And in the `join` table, we have an entry.
So hey, here's a list of courses where `student_id` equals one.
That maps to `course_id` `10`, which in turn points back to the `course` table.
Now we see that `John` is in the `Pacman` course.
So that's how we use the `join` table to kind of find `courses` for a given `student`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image70.png" alt="image70">
</div>

Now, let's do another example here and let's find `Mary`'s courses.
So again, in the `student` table, we see that `Mary` has a `student_id` of two
that maps over to the `join` table and there's three entries here for `student_id` equals two.
Now let's go ahead and look up those `course_id`s in the `course` table.
And yeah, we see three `courses`, `Pacman`, `Rubik's Cube` and `Atari 2600`.
So we know that `Mary`'s assigned to three `courses` here.
So again, a good example of using our `join` table to find `courses` for a given `student`.
We'll build all of this step by step.

* Prep work - Define database tables 
* Update `Course` class
* Update `Student` class

</div>

### [@ManyToMany Mapping - Database Setup]()
<div style="text-align:justify">

Alright, so let's go ahead and look at our database table for the `join` table `course_student`.
So here's the **SQL** script.

````sql
CREATE TABLE `course_student` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  
  PRIMARY KEY (`course_id`,`student_id`),
  
  ...
);
````

So we'll basically create this `join` table.
It's going to have two columns, `course_id` and `student_id`.
And we'll also set those columns up to be the primary key.
So the primary key will be composed of two columns, `course_id` and `student_id`.
Now we need to set up the foreign keys.
This will basically tell the `join` table how to point back to the appropriate table.
So here's our SQL up top.

````sql
CREATE TABLE `course_student` (
  
  ...
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_COURSE_05` 
  FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`), 
  
  ...
  
  CONSTRAINT `FK_STUDENT` 
  FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  
  ...
);
````

We'll set up this foreign key constraint for `course_id`
and it's going to reference the `course` table, the `id` column.
So that'll be a link between this `join` table `course_id` and the actual `course` table.
And now we'll do a similar thing here for `student`.
So set up this constraint, foreign key student, `FK_STUDENT`, `student_id`.
And then we'll go ahead and say it's going to reference the `student` table, the `id` column.
So our `course_student` join table, `student_id`, links back to or points to the `student` table `id` column.
So that's how our whole foreign key relationship works out.

</div>

### [@ManyToMany Mapping - Project Setup]()
<div style="text-align:justify">

Now moving to step two, we're going to update the `Course` class to reference `students`.

````java
@Entity
@Table(name="course")
public class Course {

    // other fields

    private List<Students> students;

    // constructors, getters/setters

    // add convenience method for adding single student to the course
    public void addStudent(Student tempStudent) {
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(tempStudent);
    }
}
````

So basically `Course` is going to have a list of `students`
and then we'll also define the appropriate getter and setter methods for this field.
One other thing I need to do here is I need to add that convenience method
for adding a single `student` to a `course`.
So I write code here for this method called `addStudent`.
They'll pass in `theStudent` and I basically just check my list, make sure it's not `null`.
If it's `null`, I'll go ahead and create it.
Then I'll just simply go through and simply add the `student` to my list of `students`.
And again, we've seen this before as far as creating these convenience methods
to help us out with these advanced **Hibernate** relationships.

````java
@Entity
@Table(name="course")
public class Course {

    // other fields

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Students> students;

    // constructors, getters/setters
}
````

Now the important thing here is adding the `@ManyToMany` annotation.
So we're going to annotate this field with `@ManyToMany` and then we'll add this `JoinTable` annotation.
So the `JoinTable` basically tells **Hibernate** which `join` table that you'll use.
So `name` equals `course_student` and then we'll also specify the `@JoinColumn`.
So here I have `@JoinColumn` `name` equals `course_id`.
So this basically tells **Hibernate**, refer to the `course_id` column in the `course_student` join table.
And then we'll do a similar thing here for a `student`.
So for the inverse column, we'll say `@JoinColumn` `name` equals `student_id`.
So again, look into this `join` table and look for the column `student_id`
to find the appropriate `students` for this `course`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image71.png" alt="image71">
</div>

Now a bit more on this `@JoinTable`.
So above, we have the code and the diagram.
So the `@JoinTable` tells **Hibernate**, look at the `course_id` column in the `course_student` table.
And for the other side or the inverse, look at the `student_id` column in the `course_student` table.
And then **Hibernate** will use this information to find relationships between the `course` and `students`.
So here we have `@JoinColumn` `course_id` points to the `course_student`, over to the actual `course`,
and then a similar thing here for the `student_id`.
So it points to `student_id` in the `join` table, which then points back to the `student` table.
And this is all based on the foreign keys that we set up earlier.
So we're defining the relationship in the `Course` class.
The `Student` class is on the other side.
So it's considered the "inverse".
So inverse refers to the other side of the relationship.
So we're defining code in `Course`.
The inverse is the actual `Student` reference.
So that's the other side.
Allright, now let's go ahead and do a similar thing for `Student`.

````java
@Entity
@Table(name = "student")
public class Student {
    
    // other fields
    
    private List<Course> courses;
    
    // constructors, getters/setters

    // add convenience method for adding single course to the student
    public void addCourse(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);
    }
}
````

We'll simply just go the other way.
So this is kind of step three of updating the `Student` to reference `courses`.
So a `Student` class has a list of `courses`, and then we'll also, again, do the getters and setters for this.
Now, the next thing we need to do is add a convenience method to add a `course`.
We'll add this new method, `addCourse`, and I'll pass in a `course`.
So I'll call it `tempCourse`.
And I basically want to check my list of `courses` and see if it's `null`.
If it is `null`, then I'll go ahead and put a new instance of this **ArrayList**.
And now, I'll simply add the `tempCourse` to the list.
Here, I'll say `courses.add()`, pass in `tempCourse`.

````java
@Entity
@Table(name = "student")
public class Student {
    
    // other fields
    
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
    
    // constructors, getters/setters
}
````

The other important thing here is adding the `@ManyToMany` annotation.
And then we'll also set up a reference for `@JoinTable`.
So again, `@JoinTable` will give the actual name of it `course_student`
and then the join columns and the inverse join columns.
So here, join column `student_id` refers to the `student_id` in the `course_student` table,
because right now we're in the `Student` class.
Now the inverse is the `course_id` in the `course_student` table.
That's the other side.
So note here, we're writing code in `Student` right now.
The inverse is the other side.
That's the `Course`.
So we reference `course_id`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image72.png" alt="image72">
</div>

So the `@JoinTable` tells **Hibernate**, look at the `student_id` in the `course_student` table.
And then for the other side or the inverse, look at the `course_id` in the `course_student` table.
And again, use this information to build a relationship between `students` and `courses`.
So at the bottom, we have `student`, pointing to `student_id`.
And then we also have this `course_id`, pointing to the actual `course` table.
So that kind of pulls together the whole relationship between these.
So again, we're writing code right now in `student`, and we're pointing to an actual `course` or a list of `courses`.
Now I always like to kind of pull this into a real-world project.
So this project requirement here is that if we delete a `course`, do not delete the `students`.
So basically, do not apply cascading deletes.
And the same holds true for the other side.
If you delete a `student`, don't delete the `course`.
And later, we're going to add support for some other features.
So we'll add support for `LAZY` loading of `students` and `courses`.
So in our `Course.java`, we need to go ahead and I'll make a slight change here.

````java
@Entity
@Table(name="course")
public class Course {

    // other fields

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Students> students;

    // constructors, getters/setters
}
````

I'll set the fetch type to `LAZY`.
So lazy fetch on the students for performance reasons.
We don't want to pull all the `students` all at once.
Now, let's go ahead and update our `Student.java` for this `many-to-many` relationship.
So we're in `Student.java` now:

````java
@Entity
@Table(name = "student")
public class Student {
    
    // other fields
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
    
    // constructors, getters/setters
}
````

We'll actually use the same `@ManyToMany` relationship in our `Student.java`.
So copying from `course`, and we'll paste this into `student`.
Allright, so we're in good shape here.
So we have this `many-to-many` relationship set up.
So next, we'll look at building our main application.
I'll move down to my main app.
And then I'll move down to this command line runner method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            createCourseAndStudents(appDAO);
        };
    }

    private void createCourseAndStudents(AppDAO appDAO) {
    }
    
    // other methods

}
````

And I'll call this method here, `createCourseAndStudents()`.
And before I get started with the coding let me write some quick comments to myself just to keep myself on track.

````java
private void createCourseAndStudents(AppDAO appDAO) {

    // create a course
    Course tempCourse = new Course("Pacman - How To Score One Million Points");

    // create the students
    Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
    Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

    // add students to the course
    tempCourse.addStudent(tempStudent1);
    tempCourse.addStudent(tempStudent2);

    // save the course and associated students
    System.out.println("Saving the course: " + tempCourse);
    System.out.println("asspciated students: " + tempCourse.getStudents());

    appDAO.save(tempCourse);
    System.out.println("Done!");
}
````

Now, looking at the list here the first thing we want to do is create a `course`
then create the `students`, add `students` to the `course`,
and finally save the `course` and the associated `students`.
Here I'll have `tempCourse` equals new `Course()` and I give the course title.
And now I'll go ahead and create the `students`.
I'll set up this `tempStudent1`.
And then I get a student's first name, last name, and email address.
And then I'll copy and paste this line just so I can have another student.
And then I simply update the student's first name, last name, and email address.
So at this point, we should have two students.
And now I'll write the code to add `students` to the `course`.
Here I say `tempCourse.addStudent()` and I give `tempStudent1`.
And then I do a similar thing here for adding `tempStudent2`.
Now we'll move down here, and we'll save the `course` and its associated `students`.
I'll just do some `System.out.println()`  to this effect to display the `course` and the `students`.
I make used to this `appDAO.save()`, and I give `tempCourse`.
The nice thing here is that when we save the `course` 
that save operation or that persist operation will cascade to the actual `students`.
And so those `students` will also be persistent.
So simply giving us one call to save then it'll cascade that save to the associated elements or entities.
Let's go ahead and run this app.

````text
Saving the course: Course{id=0, title='Pacman - How To Score One Million Points'}
asspciated students: [Student{firstName='John', lastName='john@luv2code.com', email='Doe', id=0}, Student{firstName='Mary', lastName='mary@luv2code.com', email='Public', id=0}]
DEBUG   org.hibernate.SQL           : insert into course (instructor_id,title) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [null]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [Pacman - How To Score One Million Points]
DEBUG   org.hibernate.SQL           : insert into student (email,first_name,last_name) values (?,?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:VARCHAR) <- [Doe]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [John]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (3:VARCHAR) <- [john@luv2code.com]
DEBUG   org.hibernate.SQL           : insert into student (email,first_name,last_name) values (?,?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:VARCHAR) <- [Public]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [Mary]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (3:VARCHAR) <- [mary@luv2code.com]
DEBUG   org.hibernate.SQL           : insert into course_student (course_id,student_id) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [1]
DEBUG   org.hibernate.SQL           : insert into course_student (course_id,student_id) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [2]
Done!

Process finished with exit code 0
````

Up top, we see that it's `Saving the course`, and also the associated `students` for that `course`.
And then we have all the `DEBUG` statements over on the right hand side.
We see here that we're doing an `insert` on `course` 
then we do an `insert` on `student` and then another `insert` on `student`.
And then also notice here at the bottom we do an `insert` into `course_student`.
That's the `join` table.
It's basically setting up that association for that `many-to-many` between a `student` and a `course`.
And we have two entries there.
So let's swing over into the **MySQL workbench** here
just so we can kind of verify what's going on.
let's do a query on the `course` table to see what's there.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image73.png" alt="image73">
</div>

And this looks good.
So we only have one `course` right now the `Pacman` course that has the `course_id` of 10.
And then let's also do a query here on our `students`.
So we should have two students here, `John` and `Mary`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image74.png" alt="image74">
</div>

And that looks good.
So `John` has the id of one, and `Mary` has the id of two.
But now we kinda need that `many-to-many` relationship, that's in our `join` table `course_student`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image75.png" alt="image75">
</div>

So this basically joins the items together.
Here's our `join` table.
So note, we have `course_id` of 10, `student_id` 1, `course_id` 10, `student_id` of 2.
So that means that `John` and `Mary` both assigned to that `Pacman` course.
So we have that `many-to-many` relationship between our `course` and the `students`.

Now, we'll find a `course` and its associated `students`.
Now, the first thing we need to do here is add a new method to `AppDAO` interface.
So let's go ahead and open it up now.

````java
public interface AppDAO {
    
    // other methods

    Course findCourseAndStudentsByCourseId(int theId);
}
````

And we'll move down here to the bottom, and we'll create this new method, `findCourseAndStudentsByCourseId()`.
So this method here looks pretty good.
Now let's swing over to `AppDAOimpl` to add this method.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // constructor injection for entity manager and other methods

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                                            "select c from Course c " 
                                            + "JOIN FETCH c.students "
                                            + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        Course course = query.getSingleResult();

        return course;
    }
}
````

Before I start with the coding, let me write some quick comments to myself.
And this is very simple, very straightforward.
The first thing that we'll do is we'll create the query, and then we'll execute the query.
Now this query that we're going to write is actually a join fetch.
I'll do a join fetch on `course.student`, and also be sure to keep that white space in the query at the end.
And that's it for the coding on the query.
Now let's go ahead and execute the query, by saying `query.getSingleResult()`, and assigning it to the `course`. 
And then finally, we return the `course`.
Now let's go ahead and move into our main application.
I'll move down here to the command line runner method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndStudents(appDAO);
            findCourseAndStudents(appDAO);
        };
    }

    private void findCourseAndStudents(AppDAO appDAO) {
    }
    
    // other methods

}
````

I'll comment out the previous method, and then I'll call this new method `findCourseAndStudents()`.
I'll move down here to this method stub.

````java
private void findCourseAndStudents(AppDAO appDAO) {

    int theId = 10;
    Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

    System.out.println("Loaded course: " + tempCourse);
    System.out.println("Students: " + tempCourse.getStudents());

    System.out.println("Done!");
}
````

I'll set up the `course_id` of 10, and then I'll call that new `appDAO` method that we just created,
`appDAO.findCourseAndStudentsByCourseId()`.
And now we simply print out the data that we retrieved.
So we print out the actual `course` that we loaded, `tempCourse`,
and also the associated `students` for that `course`, by saying `tempCourse.getStudents()`.
And that's all the coding here for this method.
Now let's go ahead and run our application, and test it out.

````text
DEBUG   org.hibernate.SQL           : select c1_0.id,c1_0.instructor_id,s1_0.course_id,s1_1.id,s1_1.email,s1_1.first_name,s1_1.last_name,c1_0.title from course c1_0 join course_student s1_0 on c1_0.id=s1_0.course_id join student s1_1 on s1_1.id=s1_0.student_id where c1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
Loaded course: Course{id=10, title='Pacman - How To Score One Million Points'}
Students: [Student{firstName='John', lastName='john@luv2code.com', email='Doe', id=1}, Student{firstName='Mary', lastName='mary@luv2code.com', email='Public', id=2}]
Done!

Process finished with exit code 0
````

Okay, this looks good.
So it loaded the `course`, `course_id` of 10, that's `Pacman`.
And then also our `students`.
So we had two students, `John` and `Mary`, and those are listed here also.

Let's now find the `student` and student's `courses`.
So we're actually go the other way.
So instead of starting with `course` we're going to start with `student`.
So, find a given `student` and then display the list of `courses` 
that the student is assigned to or associate it with.
We'll start in our `AppDAO` interface, and we'll add this new method, `findStudentAndCoursesByStudentId()`.

````java
public interface AppDAO {
    
    // other methods

    Student findStudentAndCoursesByStudentId(int theId);
}
````

And now let's go ahead and move into our `AppDAOImpl` and implement that method.
I'll move down here to the method stub.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // constructor injection for entity manager and other methods

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                                            "select s from Student s " 
                                            + "JOIN FETCH s.courses "
                                            + "where s.id = :data", Student.class);
        query.setParameter("data", theId);

        //execute query
        Student student = query.getSingleResult();

        return student;
    }
}
````

And we'll follow the same process.
We'll just create the query and then execute the query.
And, again, I can move up to one of my previous methods and copy some of the code.
And I'll make some slight updates on the query
so this query's going to return a `student`, so it's a typed query of `student`.
I'll also change the return here, `Student.class`.
And now I need to make some updates here in the query.
Here I'll say `select s from Student s`.
Remember, `s` is just the alias, the shortcut.
And then I'll do a join fetch on `s.courses`.
And then I'll compare the `student_id` that's actually passed in.
So we're doing a join fetch, `s.courses where s.id = :data`.
And we'll make use of the `student.class` for the return.
Now let's go ahead and move down here and let's execute the query.
So, the same process, `query.getSingleResult()`.
We assign it to the `student`.
And then finally, we return the `student`.
Now let's go ahead and move over into our main application.
I'll comment out the previous call, and now I'll call this new method, `findStudentAndCourses`.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            findStudentAndCourses(appDAO);
        };
    }

    private void findStudentAndCourses(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        System.out.println("Done!");
    }
    
    // other methods

}
````

I'll set up the student ID of two.
And now I'll call this `AppDAO` method, `findStudentAndCoursesByStudentId`.
And now I'll simply do the `System.out.println`.
I'll print out the `student` that I loaded and also the associated `courses` for that `student`.
Let's go ahead and run it.

````text
DEBUG   org.hibernate.SQL           : select s1_0.id,c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 join course_student c1_0 on s1_0.id=c1_0.student_id join course c1_1 on c1_1.id=c1_0.course_id where s1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
Loaded student: Student{firstName='Mary', lastName='mary@luv2code.com', email='Public', id=2}
Courses: [Course{id=10, title='Pacman - How To Score One Million Points'}]
Done!

Process finished with exit code 0
````

Excellent, so here it says, Loaded Student.
Name, `Mary Public`, and then the actual `courses` that `Mary`'s assigned to.
In this case, she's only assigned to one course and that's the `Pacman` course with the `course_id` of 10.

Now let's go ahead and add an enhancement here.
Let's write some code to add more `courses` for a `student`.
I'll start by adding a new method to my `AppDAO` interface.
And I'll add this new method here called `update`.
And we pass in a `student`.

````java
public interface AppDAO {
    
    // other methods

    void update(Student tempStudent);
}
````

And what we'll do is we'll actually add more `courses` to the `student`, 
and then we save the `student` and then it'll persist accordingly.
Now, let's go ahead and move into our `AppDAOImpl` and implement this method.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // constructor injection for entity manager and other methods

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }
}
````

And we'll move down here to this `update` method.
Since we're modifying the database I'll make use of the `@Transactional` annotation.
And then I make use of this `entityManager.merge` since we're performing an `update`.
And I'll say, `merge` on `tempStudent`.
And that's the main coding here for performing an update on this given `student`.
Now let's go ahead and move back over to our main application.
And then I'll move down to this `CommandLineRunner` method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            addMoreCoursesForStudent(appDAO);
        };
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);
        System.out.println("Done!");
    }
    
    // other methods

}
````

I'll comment out the previous code.
And then I'll call this new method, `addMoreCoursesForStudent`.
I'll move down here to this method stub.
I'll set up the student ID of 2.
And then I'll make use of this, `appDAO.findStudentAndCoursesByStudentId()`.
That'll give me the `student`.
And now what I want to do here is add more `courses` for the `student`.
Let's go ahead and create another `course`.
Here I have `tempCourse1` equals `new Course()`.
I give the course title, "`Rubik's Cube, How to Speed Cube`".
And then also another course, "`Atari 2600 Game Development`".
And then I'll add the `courses` to the `student`.
Here I have `tempStudent.addCourse`, `tempCourse1`, and I repeat the process for `tempCourse2`.
And now I'll do some `System.out.println`.
I'll say `appDAO.update(tempStudent)`.
So this will update the `student` and also its associated `courses`.
In particular, it'll add that information to the `join` table to associate the `student` with these `courses`. 
Let's go ahead and run this application and test it out.

````java
DEBUG   org.hibernate.SQL : select s1_0.id,c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 join course_student c1_0 on s1_0.id=c1_0.student_id join course c1_1 on c1_1.id=c1_0.course_id where s1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
Saving student: Student{firstName='Mary', lastName='mary@luv2code.com', email='Public', id=2}
associated courses: [Course{id=10, title='Pacman - How To Score One Million Points'}, Course{id=0, title='Rubik's Cube - How to Speed Cube'}, Course{id=0, title='Atari 2600 - Game Development'}]
DEBUG   org.hibernate.SQL           : select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name,c1_0.student_id,c1_1.id,i1_0.id,i1_0.email,i1_0.first_name,i1_0.instructor_detail_id,i1_0.last_name,c1_1.title from student s1_0 left join course_student c1_0 on s1_0.id=c1_0.student_id left join course c1_1 on c1_1.id=c1_0.course_id left join instructor i1_0 on i1_0.id=c1_1.instructor_id where s1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
DEBUG   org.hibernate.SQL           : select s1_0.course_id,s1_1.id,s1_1.email,s1_1.first_name,s1_1.last_name from course_student s1_0 join student s1_1 on s1_1.id=s1_0.student_id where s1_0.course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : select r1_0.course_id,r1_0.id,r1_0.comment from review r1_0 where r1_0.course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : insert into course (instructor_id,title) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [null]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [Rubik's Cube - How to Speed Cube]
DEBUG   org.hibernate.SQL           : insert into course (instructor_id,title) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [null]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:VARCHAR) <- [Atari 2600 - Game Development]
DEBUG   org.hibernate.SQL           : delete from course_student where student_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
DEBUG   org.hibernate.SQL           : insert into course_student (student_id,course_id) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : insert into course_student (student_id,course_id) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [11]
DEBUG   org.hibernate.SQL           : insert into course_student (student_id,course_id) values (?,?)
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [2]
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (2:INTEGER) <- [12]
Done!

Process finished with exit code 0
````

And we see here that we're updating the `student_id` of 2.
That's `Mary`.
And then we see `Mary`'s associated `courses`.
So we already have her first course that we saw earlier.
ID of 10, that's the `PacMan` course.
And then we can see there's other `courses` that will be assigned to her.
These are the new `courses`.
They currently have the ID of zero, but once they're saved, 
they'll have a proper ID that's been provided by the database.
And that's for the `Rubik's Cube` course.
And also the `Atari 2600` course.
And all of this processed accordingly based on all these `DEBUG` and `TRACE` statements.
Okay, so now we have this association here between the `student` and those new `courses`.
So let's swing over into the **MySQL Workbench** here just so we can kind of verify what's going on.
So let's look at the `course` tab here.
And again let's do the little refresh here to refresh this query.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image76.png" alt="image76">
</div>

And so now we have those three `courses`.
So `Pac-Man`, `Rubik's Cube`, and `Atari 2600`.
So ids 11 and 12, those are the new courses that were just added with our application.
Now clicking on the `student` tab, again let's do a little refresh here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image77.png" alt="image77">
</div>

And `Mary` has a `student_id` of 2.
But now for our join table, okay, `course_student`.
Let's hit the refresh button here. 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image78.png" alt="image78">
</div>

And oh yeah, this is great.
So now we have those new `courses`, 11 and 12.
And that's `student_id` of 2.
So those are the new `courses` that were added for `Mary`.
This is really working out for us.
So that join column is holding that data, holding the relationship as far as which `students` are assigned to a given `course`.
So that's really our `many-to-many` mapping working out in real time.

Alright, let's delete a `course` now.
We only want to delete the `course`-`student` relationship, 
so delete the `course` but don't delete the `students`, just their relationship.
I'll move down here to the `commandLineRunner()` method.

````java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	//public CommandLineRunner commandLineRunner(String[] args) {
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            deleteCourseById(appDAO);
        };
    }

    private void deleteCourseById(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }
    
    // other methods

}
````

I'll comment out our previous code.
And I'll make a call to this method `deleteCourseById()`.
And the nice thing about it is that we already have this method in place.
There's no need for us to really write a new method,
but let's go take a look at the implementation of this method here.
Allright, so we're at our implementation here for delete a `course` 
and this is from work that we did previously but this all looks pretty good.
We have the id equals 10.
So that's for the `Pacman` course.
And so we're going to delete the Pacman `course` by `course_id`.
And that's pretty much it.
Let's go ahead and run this application

````text
Deleting course id: 10
DEBUG   org.hibernate.SQL           : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : select r1_0.course_id,r1_0.id,r1_0.comment from review r1_0 where r1_0.course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : delete from course_student where course_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
DEBUG   org.hibernate.SQL           : delete from course where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [10]
Done!

Process finished with exit code 0
````

And see if we can delete the `course_id` of 10, that's the `Pacman` course.
And here the application ran successfully, `Deleting course id: 10`.
And we can see that it removes the association from the join table.
And then it finally deletes the `course`.
So basically anyone who's enrolled in the `course`,
it removes that association and then finally deletes the `course`.
Notice here that it doesn't really delete the `student`.
Allright, simply just making updates here in that `join` table and also removing the `course` from the `course` table.
And we can verify that here in the database.
Let's go ahead and refresh.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image79.png" alt="image79">
</div>

And success, we can see that `course_id` of 10 has been deleted, the `Pacman` course.
And then we can look in this `join` table here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image80.png" alt="image80">
</div>

And we can notice here that for the `course_id`s,
there's no reference here to `course_id` of 10 
because that code actually removed those references for `course_id` of 10, the `Pacman` course, from this given `join` table.
Allright, so this all looks good.

Now, we're going to go the other way, work on the other side.
We'll delete a `student`, and we'll test all these out with some real code here on our computers.
Let's get started here by adding a new method to our `AppDAO` Interface.
We'll add this new method, `deleteStudentById`.

````java
public interface AppDAO {
    
    // other methods

    void deleteStudentById(int theId);
}
````

Now let's go ahead and move into our `AppDAOImpl`, and let's implement the method.
I'll move down to this method stub.

````java
@Repository
public class AppDAOImpl implements AppDAO {

    // entity manager field
    private EntityManager entityManager;

    // constructor injection for entity manager and other methods

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        
        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);
        
        // delete the student
        entityManager.remove(tempStudent);
    }
}
````

And remember, since we're modifying the database, we need to make use of the `@Transactional` annotation.
I'll write some quick comments to myself. 
The first thing I want to do is retrieve the `student`.
And then next, I want to delete the `student`.
Here, we simply make use of `entityManager.find()`.
And then to delete the student, we make use of this `entityManager.remove()`.
And just standing back here for a second, this is all the code here for deleting a student by id.
Now let's go ahead and move over to our main application.
I'll move down to the `commandLineRunner()` method.

````java
@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    //public CommandLineRunner commandLineRunner(String[] args) {
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {

            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            //deleteCourseById(appDAO);
            deleteStudentById(appDAO);
        };
    }

    private void deleteStudentById(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);
        System.out.println("Done!");
    }

    // other methods

}
````

I'll comment out the previous code, and then I'll call this method, `deleteStudentById`.
I'll move to the method stub.
I'll set the id to one.
I'll say `appDAO.deleteStudentById`, pass in the id, and before I run this application,
I wanna just check the database on the student id of one.
I'll simply move into the **MySQL Workbench**, and I'll do a query on this `student` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image77.png" alt="image81">
</div>

Here, we have the id of one, that's for `John Doe`.
Now let's go ahead and run the application and see how things work out for us.

````text
Deleting student id: 1
DEBUG   org.hibernate.SQL           : select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 where s1_0.id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [1]
DEBUG   org.hibernate.SQL           : delete from course_student where student_id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [1]
DEBUG   org.hibernate.SQL           : delete from student where id=?
TRACE   org.hibernate.orm.jdbc.bind : binding parameter (1:INTEGER) <- [1]
Done!

Process finished with exit code 0
````

Allright, so up top, it said, "`Deleting student id: 1`"
and we can see that it removed the association from that `join` table, `course_student`.
And then it actually deleted the `student` from the database.
And now we can verify this in our database.
I'll swing over to **MySQL Workbench**.
And now, if I do a refresh:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/09-spring-boot-spring-jpa-hibernate-advanced-mappings/images/image82.png" alt="image82">
</div>

Then success.
We can see that `student_id` of one or `John Doe`, was deleted successfully.
So we were successful here in deleting a `student`.

</div>