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
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image01.png" alt="image01">
</div>

You have a web browser on the left, and then on the right,
you have this protected web resource like my top-secret stuff.
The browser's attempting to access your top-secret information and get the results.
Now, this is where **Spring Security** comes into play.
These **Spring Security filters** will intercept those requests,
pre-process them, and then see if the user can actually access that protected resource.
**Spring Security** will look at your application security configuration.
And then, it'll also look at the user's passwords and roles
that are in your database to see if this user's authenticated, 
and also if they're authorized to access this web resource.
And this all happens in the background, thanks to **Spring Security**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image02.png" alt="image02">
</div>

Okay, **Spring Security** in action with a flow chart.
We have these **Spring Security filters**, and then, basically,
we'll say, "Hey, is this web resource protected?"
Yes. 
Then, "Is this user authenticated?"
If they're not authenticated, then we'll send them to the login form,
show that login form, and then take the user ID and password and authenticate it.
We'll check to see if the user ID and password are valid
based on the information we have stored in our system.
Then, if they pass a test, so they're authenticated,
then we check, "Is this user authorized?
Do they have authorization to access this resource?"
Now, just like at your job, right?
You have a security badge, or at your university,
you have a student ID that can get you into the main building.
However, you may not be able to access all the rooms in the building.
It all depends on your access level or your authorization role.
Just because you have a good user ID and password,
that doesn't mean you have access to everything.
There's still additional levels of security in place,
and **Spring** has support for this using security roles.
If you don't have the given role, then it says access denied,
or if you do have the given role, then it'll actually show you the resource
and then give you access to this secure site.

Okay, so let's review some security concepts.
We have this idea of authentication that basically handles checking the user ID and password
with the credentials that are stored in your app or in your database.
And then we have the idea of authorization.
Here we check to see if the user has an authorized role.
As you can see, there are two levels of security that are in place for the **Spring Security** framework.

**Spring Security** has declarative security.
Here we define our application security constraints in a configuration.
This is handled by an all-Java config using a `@configuration` class.
And the nice thing about this is that it provides separation of concerns
between application code and security code.
And then there's also programmatic security.
Basically, **Spring** provides an API for custom application coding,
and this is where you can have greater customization
for your specific application requirements.
For real-world, real-time enterprise projects, you may say,
"_Hey, **Spring Security** gives me the basics.
However, at my company, we have additional business rules
or additional logic that we need to implement._"
Well, not a problem.
You can extend the framework for doing that.
And you can plug in your own custom security implementation.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <aartifactId>spring-boot-starter-security</aartifactId>
</dependency>
```

To enable **Spring Security**, the first thing we do is we edit the `pom.xml` file.
And in this file, we'll add the dependency for the `spring-boot-starter-security`.
And by adding this dependency, spring boot will automatically
secure all endpoints for the application.
No additional coding is required at this point.
However, we'll learn about customizing the configuration a little bit later.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image03.png" alt="image03">
</div>

And once we run our application, and we access our app, then our application is secured.
As a result, **Spring Security** will prompt us to log in.
Now, by default, the username is `user`,
and the generated password is in the application logs.
Now, this is just for testing.
Later on, we'll learn other techniques to customize the user ID and the password,
including storing the information in the database.
But at the moment, we're just looking at the defaults for right now.

````properties
spring.security.user.name=scott
spring.security.user.password=tiger
````

You can also manage basic configuration with **Spring Security**.
In our application.properties file, you can override the default username and password.
We simply add the configuration for **Spring Security** username
and also **Spring Security** user password.

As I mentioned earlier, there are different techniques for defining users, passwords, and roles.
You can make use of in-memory authentication.
You can also use JDBC to define users, passwords, and roles in a database.
There's support for LDAP.
You can also use your own custom plugin,
your own authentication, and authorization coding,
and there are plenty of others out there.
You can look at the reference manual for a complete list,
but in this class, we'll actually cover the in-memory authentication.
And then, we'll also cover password storage in the database using JDBC.


Now, we need to refresh our database table,
because we added a lot of things, deleted, updated, and so on.
So let's move over to MySQL Workbench and log into our ``Spring Student Account``.
And basically, we just want to run that script
that we used earlier as far as creating a table and inserting the sample data.

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

So here it is.
So this is all of our fresh data here, so we're good to go.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image04.png" alt="image04">
</div>

So we have a standard baseline to work from.
And now what I'd like to do is download some starter code just to get us up and running.
So we have this file here, `00-spring-boot-rest-security-employee-starter-code.zip`, that you'll download.
So the same code you saw before,
I simply added some supporting SQL scripts for us to do some database work,
but the majority of the file here in the code is the same.

And now let's go ahead and swing over to our file system,
and I'll simply unzip this file, and it's just a basic **Maven** project.
A lot of the code is what we created before.
And now let's go ahead and import this project into our IDE.
I'll go ahead and just do a rebuild all, and I'll also go through
and just make the appropriate project settings for `spring-boot-dev-tools`.
So under `Build, Execution, Deployment`, under `Compiler`,
we check the box here to `build project automatically`.
And then down in advanced settings, make sure that you have the option selected
for `allow auto-make to start`, and then go ahead and hit `OK`.
So this is our `spring-boot-crud-demo` that we created in previous sections.
All of this code is the same, so we have our `DAO`, our `entity`, our `rest`, and also our `service`.
And again, we've created all this code before, there's nothing new here as far as the Java coding.

So we're going to enable **Spring Security**.
We're going to add the **Maven** dependency for **Spring Boots Starter Security**.
Let me open up my `pom.xml` file and I'll just move down to my dependency section.

````xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
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
````

I'll add a new dependency here, and I'll just go ahead and copy one of my previous dependencies and just paste it here.
And I'll make the appropriate update here for **Spring Boot Starter Security**.
I'll be sure to save this file and go over here and load the **Maven** changes.
So be sure to reload that **Maven** file.
And remember, by adding the security dependency, **Spring Boot** will automagically secure all endpoints
for the application to do this by default.
And let's test this out real quick.
Let's go ahead and run our application.

````text
C:\Users\korha\.jdks\openjdk-21.0.2\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.3\lib\idea_rt.jar=64505:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.3\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\JAVA_STUDY\Github\dev-spring-boot\05-spring-boot-rest-security\00-spring-boot-rest-security-employee-starter-code\target\classes;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-security\3.3.0\spring-boot-starter-security-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter\3.3.0\spring-boot-starter-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-logging\3.3.0\spring-boot-starter-logging-3.3.0.jar;C:\Users\korha\.m2\repository\ch\qos\logback\logback-classic\1.5.6\logback-classic-1.5.6.jar;C:\Users\korha\.m2\repository\ch\qos\logback\logback-core\1.5.6\logback-core-1.5.6.jar;C:\Users\korha\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.23.1\log4j-to-slf4j-2.23.1.jar;C:\Users\korha\.m2\repository\org\apache\logging\log4j\log4j-api\2.23.1\log4j-api-2.23.1.jar;C:\Users\korha\.m2\repository\org\slf4j\jul-to-slf4j\2.0.13\jul-to-slf4j-2.0.13.jar;C:\Users\korha\.m2\repository\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;C:\Users\korha\.m2\repository\org\yaml\snakeyaml\2.2\snakeyaml-2.2.jar;C:\Users\korha\.m2\repository\org\springframework\spring-aop\6.1.8\spring-aop-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\spring-beans\6.1.8\spring-beans-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\security\spring-security-config\6.3.0\spring-security-config-6.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\security\spring-security-core\6.3.0\spring-security-core-6.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\security\spring-security-crypto\6.3.0\spring-security-crypto-6.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\spring-context\6.1.8\spring-context-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\security\spring-security-web\6.3.0\spring-security-web-6.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\spring-expression\6.1.8\spring-expression-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-data-jpa\3.3.0\spring-boot-starter-data-jpa-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-aop\3.3.0\spring-boot-starter-aop-3.3.0.jar;C:\Users\korha\.m2\repository\org\aspectj\aspectjweaver\1.9.22\aspectjweaver-1.9.22.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-jdbc\3.3.0\spring-boot-starter-jdbc-3.3.0.jar;C:\Users\korha\.m2\repository\com\zaxxer\HikariCP\5.1.0\HikariCP-5.1.0.jar;C:\Users\korha\.m2\repository\org\springframework\spring-jdbc\6.1.8\spring-jdbc-6.1.8.jar;C:\Users\korha\.m2\repository\org\hibernate\orm\hibernate-core\6.5.2.Final\hibernate-core-6.5.2.Final.jar;C:\Users\korha\.m2\repository\jakarta\persistence\jakarta.persistence-api\3.1.0\jakarta.persistence-api-3.1.0.jar;C:\Users\korha\.m2\repository\jakarta\transaction\jakarta.transaction-api\2.0.1\jakarta.transaction-api-2.0.1.jar;C:\Users\korha\.m2\repository\org\jboss\logging\jboss-logging\3.5.3.Final\jboss-logging-3.5.3.Final.jar;C:\Users\korha\.m2\repository\org\hibernate\common\hibernate-commons-annotations\6.0.6.Final\hibernate-commons-annotations-6.0.6.Final.jar;C:\Users\korha\.m2\repository\io\smallrye\jandex\3.1.2\jandex-3.1.2.jar;C:\Users\korha\.m2\repository\com\fasterxml\classmate\1.7.0\classmate-1.7.0.jar;C:\Users\korha\.m2\repository\net\bytebuddy\byte-buddy\1.14.16\byte-buddy-1.14.16.jar;C:\Users\korha\.m2\repository\org\glassfish\jaxb\jaxb-runtime\4.0.5\jaxb-runtime-4.0.5.jar;C:\Users\korha\.m2\repository\org\glassfish\jaxb\jaxb-core\4.0.5\jaxb-core-4.0.5.jar;C:\Users\korha\.m2\repository\org\eclipse\angus\angus-activation\2.0.2\angus-activation-2.0.2.jar;C:\Users\korha\.m2\repository\org\glassfish\jaxb\txw2\4.0.5\txw2-4.0.5.jar;C:\Users\korha\.m2\repository\com\sun\istack\istack-commons-runtime\4.1.2\istack-commons-runtime-4.1.2.jar;C:\Users\korha\.m2\repository\jakarta\inject\jakarta.inject-api\2.0.1\jakarta.inject-api-2.0.1.jar;C:\Users\korha\.m2\repository\org\antlr\antlr4-runtime\4.13.0\antlr4-runtime-4.13.0.jar;C:\Users\korha\.m2\repository\org\springframework\data\spring-data-jpa\3.3.0\spring-data-jpa-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\data\spring-data-commons\3.3.0\spring-data-commons-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\spring-orm\6.1.8\spring-orm-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\spring-tx\6.1.8\spring-tx-6.1.8.jar;C:\Users\korha\.m2\repository\org\slf4j\slf4j-api\2.0.13\slf4j-api-2.0.13.jar;C:\Users\korha\.m2\repository\org\springframework\spring-aspects\6.1.8\spring-aspects-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-web\3.3.0\spring-boot-starter-web-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-json\3.3.0\spring-boot-starter-json-3.3.0.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.17.1\jackson-databind-2.17.1.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.17.1\jackson-annotations-2.17.1.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.17.1\jackson-core-2.17.1.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.17.1\jackson-datatype-jdk8-2.17.1.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.17.1\jackson-datatype-jsr310-2.17.1.jar;C:\Users\korha\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.17.1\jackson-module-parameter-names-2.17.1.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\3.3.0\spring-boot-starter-tomcat-3.3.0.jar;C:\Users\korha\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\10.1.24\tomcat-embed-core-10.1.24.jar;C:\Users\korha\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\10.1.24\tomcat-embed-el-10.1.24.jar;C:\Users\korha\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\10.1.24\tomcat-embed-websocket-10.1.24.jar;C:\Users\korha\.m2\repository\org\springframework\spring-web\6.1.8\spring-web-6.1.8.jar;C:\Users\korha\.m2\repository\io\micrometer\micrometer-observation\1.13.0\micrometer-observation-1.13.0.jar;C:\Users\korha\.m2\repository\io\micrometer\micrometer-commons\1.13.0\micrometer-commons-1.13.0.jar;C:\Users\korha\.m2\repository\org\springframework\spring-webmvc\6.1.8\spring-webmvc-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-devtools\3.3.0\spring-boot-devtools-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot\3.3.0\spring-boot-3.3.0.jar;C:\Users\korha\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\3.3.0\spring-boot-autoconfigure-3.3.0.jar;C:\Users\korha\.m2\repository\com\mysql\mysql-connector-j\8.3.0\mysql-connector-j-8.3.0.jar;C:\Users\korha\.m2\repository\jakarta\xml\bind\jakarta.xml.bind-api\4.0.2\jakarta.xml.bind-api-4.0.2.jar;C:\Users\korha\.m2\repository\jakarta\activation\jakarta.activation-api\2.1.3\jakarta.activation-api-2.1.3.jar;C:\Users\korha\.m2\repository\org\springframework\spring-core\6.1.8\spring-core-6.1.8.jar;C:\Users\korha\.m2\repository\org\springframework\spring-jcl\6.1.8\spring-jcl-6.1.8.jar com.luv2code.springboot.cruddemo.CruddemoApplication
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-09-03T19:14:16.018+03:00  INFO 17708 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : Starting CruddemoApplication using Java 21.0.2 with PID 17708 (D:\JAVA_STUDY\Github\dev-spring-boot\05-spring-boot-rest-security\00-spring-boot-rest-security-employee-starter-code\target\classes started by korha in D:\JAVA_STUDY\Github\dev-spring-boot\05-spring-boot-rest-security\00-spring-boot-rest-security-employee-starter-code)
2024-09-03T19:14:16.019+03:00  INFO 17708 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : No active profile set, falling back to 1 default profile: "default"
2024-09-03T19:14:16.063+03:00  INFO 17708 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2024-09-03T19:14:16.063+03:00  INFO 17708 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024-09-03T19:14:16.689+03:00  INFO 17708 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-09-03T19:14:16.741+03:00  INFO 17708 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 46 ms. Found 1 JPA repository interface.
2024-09-03T19:14:17.322+03:00  INFO 17708 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-09-03T19:14:17.340+03:00  INFO 17708 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-09-03T19:14:17.340+03:00  INFO 17708 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-09-03T19:14:17.394+03:00  INFO 17708 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-09-03T19:14:17.395+03:00  INFO 17708 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1331 ms
2024-09-03T19:14:17.550+03:00  INFO 17708 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-09-03T19:14:17.844+03:00  INFO 17708 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@c64c3d5
2024-09-03T19:14:17.845+03:00  INFO 17708 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-09-03T19:14:17.884+03:00  INFO 17708 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-09-03T19:14:17.949+03:00  INFO 17708 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-09-03T19:14:18.004+03:00  INFO 17708 --- [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-09-03T19:14:18.324+03:00  INFO 17708 --- [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-09-03T19:14:18.969+03:00  INFO 17708 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-09-03T19:14:18.971+03:00  INFO 17708 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-09-03T19:14:19.294+03:00  WARN 17708 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-09-03T19:14:19.604+03:00  WARN 17708 --- [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: bb1ac596-966a-4f30-9515-59506fff1639

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2024-09-03T19:14:19.620+03:00  INFO 17708 --- [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with UserDetailsService bean with name inMemoryUserDetailsManager
2024-09-03T19:14:19.673+03:00  INFO 17708 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@6a7f1307, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@7da9db73, org.springframework.security.web.context.SecurityContextHolderFilter@6c35e37f, org.springframework.security.web.header.HeaderWriterFilter@360b1f76, org.springframework.web.filter.CorsFilter@56dd363c, org.springframework.security.web.csrf.CsrfFilter@69e73a26, org.springframework.security.web.authentication.logout.LogoutFilter@6b4f8f19, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@50fd5413, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@1fd176c4, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@667fa8c5, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@14a06c2f, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@790e7694, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@28aa7345, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@748a6826, org.springframework.security.web.access.ExceptionTranslationFilter@5451a9d6, org.springframework.security.web.access.intercept.AuthorizationFilter@340bbd47]
2024-09-03T19:14:19.705+03:00  INFO 17708 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2024-09-03T19:14:19.728+03:00  INFO 17708 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-09-03T19:14:19.734+03:00  INFO 17708 --- [  restartedMain] c.l.s.cruddemo.CruddemoApplication       : Started CruddemoApplication in 4.012 seconds (process running for 4.373)
````

And one thing to notice here is that it even says,
Hey `using generated security` and it gives us a password.
So something's working a little bit differently, right?
And also remember that the default username is called `user`.
So we can use this information to log into our application.
I'll go ahead and start up a web browser, and then I'll go to my application.
So this is `localhost:8080/api/employees`.
So we're attempting to access this REST API.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image05.png" alt="image05">
</div>

Now notice here, spring Boot will automagically secure all end points for the application.
Since we're not logged in, they're going to prompt us for a user ID and password,
and the default username is `user`.
And then for the password, we need to look at our application logs again, right?
And make a copy-paste here of that information.
And then do a sign in and Success!

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image06.png" alt="image06">
</div>

So we're able to actually log into the application.
There's security in place, but we're able to use that user ID and password to log in.
And again, this is just the basics.
We'll get into more advanced stuff later, but this is just the basics.
Let's go ahead and copy this URL and let's open it up in a new private window
a new incognito window and attempt to log in.
So since we're using a new incognito window, there's no session tracking in place.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image07.png" alt="image07">
</div>

And so we have to log in again if we enter any bad information, it'll tell us,
`Hey, this is bad information.`
So we actually need to enter the correct user ID and password to access this given app.
What I'd like to do is override the default username and password.
And I can do that by making some modifications here in our `application.properties` file.
And I'll add these two new entries.

````properties
#
# Simple Spring Security
#
spring.security.user.name=scott
spring.security.user.password=test123
````

I'll set up Spring Security username equals `scott`
Spring Security password equals `test123`.
And now let's go ahead and run our application again.
Swing over to our web browser.
You may need to open up a new window or open up a new private window.
Access the URL.
And now let's go ahead and enter the information.
So user of `scott`, password of `test123`.
And that's based on information that we put in our configuration file.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image06.png" alt="image06">
</div>

And success!
So we're able to log in using those customizations that we set up in that config file.
</div>

## [Basic Configuration]()
<div style="text-align:justify">

In this section, we'll configure basic security.
We'll take a look at an example here for our sample users here.
We'll have three users, `John`, `Mary` and `Susan`.

<table align="center">
    <thead>
        <th>User ID</th>
        <th>Password</th>
        <th>Roles</th>
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

We'll have their passwords and also their associated roles, `employee`, `manager`, `admin`.
Now these role names, you can give any names for the roles.
Now let's take a look at our development process.

* Create a **Spring Security** configuration class (`@Configuration`)
* Add the users, passwords and roles

First, we create a package for my security classes. 
For the name of the package, I'll call it `security`.
And I'll go ahead and create a new class.
For the new class, I'll call it `DemoSecurityConfig`.

````java
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoSecurityConfig {
    
    // add our security configurations here ...
    
}
````

And the first thing I'll do here is I'll annotate this class with the `@Configuration`,
it's simply a class, and we'll give the configuration annotation
and then inside this class, we'll set up our security configurations.
In **Spring Security**, passwords are stored using a specific format.

````text
{id}encodedPassword
````

In the curly braces, you give the actual `id`, then you have the `encodedPassword`.
The idea here is that we have the encoding algorithm that's being used for this password.

<table align="center">
    <thead>
        <th>ID</th>
        <th>Description</th>
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

In this example, we have `noop`, means `no operation`.
That's just for `plain text passwords`, meaning no encryption, no hashing, no nothing, 
just plain text. 
Then you have `bcrypt` that's for `BCrypt password hashing`.
Basically, that's one-way hashing or one-way encryption.
You take the password, and you hash it using a given **BCrypt** algorithm,
and it's stored in that fashion.
**BCrypt** is a very popular hashing algorithm that's used now
and that's the one that we'll use in this section.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image08.png" alt="image08">
</div>

Let's take a look at a password example.
Here, the password is `test123`, 
and then we have the actual encoding algorithm id
and that's in curly braces.
In this example, it's `noop`.
This tells **Spring Security** the passwords are stored as plain text, `noop`, meaning no operation.
And we'll start with this just for the beginning, just to help us get started.
Later on, we'll move to more advanced features using `BCrypt`.

````java
package com.luv2code.springboot.cruddemo.security;

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

In step two, we'll add the users, passwords and roles.
And, we're going to use that in memory authentication.
We create this _UserDetailsManager_ method, returns an **InMemoryUserDetailsManager**.
We create our three users for `John`, `Mary`, `Susan`.
Here we have `John` equals `User.builder`.
We give John's username, password, roles, then we say `.build` 
to actually build this given **UserDetails**.
And then we simply repeat the process for `Mary`.
Note here for `Mary`, we also give the additional roles here for `employee` and `manager`.
And then for `Susan`, we do a similar thing.
We also specify the roles for `Susan`, `employee`, `manager`, `admin`.
And then finally, we return an instance of `InMemoryUserDetailsManager`, 
we pass on those three users, `John`, `Mary` and `Susan`.
Note that, since we defined our users here, 
**Spring Boot** will not use the user password from the `application.properties`.
Instead, it'll use this user details manager
that we just created in this configuration.
And, we'll add database support later in sections with plain text and encrypted.
Now, let's go ahead and test this out and run our application.
Let's go ahead and test this out with the Postman.
So we're going to use our rest api client for testing this.
And let's attempt to access the API without providing a user ID and password. 
So we'll go to `localhost:8080/api/employees` and we'll hit `Send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image09.png" alt="image09">
</div>

And notice here at the bottom it says `401 unauthorized`.
Basically, we're trying to access a secure resource.
We haven't provided any user ID and passwords,
so we're unauthorized, we're denied. 
Because we tried to get in with no credentials. 
So let's go ahead and change this. 
So under authorization, we need to tell it what type of authorization we're using. 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image10.png" alt="image10">
</div>

Here, we're making use of basic authentication. 
And then we provide our user ID and password. 
So `john`, and then password of `test123`.
And now hit the `Send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image11.png" alt="image11">
</div>

Then, success, status of 200. 
So that means everything was okay and successful.
And see the response of the actual data that just came back from that rest API.
So we're able to access it.
Let's do a similar thing here for `Mary`,
another one of our users.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image12.png" alt="image12">
</div>

And again, we get the 200, okay, which is successful. 
And then finally, our user `Susan`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image13.png" alt="image13">
</div>

And this is also successful.
And if I tried to add some bad credentials here, 
like just some unknown user, that we don't have:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image14.png" alt="image14">
</div>

And then as expected, right?
401, unauthorized.
So we have the basic security in place here. 
Again, very basic, but at least we're getting started.
We're defining our users, roles, and passwords.
</div>

## [Restrict URLs based on Roles]()
<div style="text-align:justify">

In this section, we're going to restrict access based on roles.
In our example, we have a number of REST APIs,
or a number of REST endpoints, and I'd like to restrict those endpoints based on a given role.

<table align="center">
    <thead>
        <th>HTTP Method</th>
        <th>Endpoint</th>
        <th>CRUD Action</th>
        <th>Role</th>
    </thead>
    <tbody>
        <tr>
            <td>GET</td>
            <td>/api/employees</td>
            <td>Read all</td>
            <td>EMPLOYEE</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/api/employees/{employeeId}</td>
            <td>Read single</td>
            <td>EMPLOYEE</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/employees</td>
            <td>Create</td>
            <td>MANAGER</td>
        </tr>
        <tr>
            <td>PUT</td>
            <td>/api/employees</td>
            <td>Update</td>
            <td>MANAGER</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/api/employees/{employeeId}</td>
            <td>Delete employee</td>
            <td>ADMIN</td>
        </tr>
    </tbody>
</table>

Starting out here with the first two endpoints
for `/employees`, `/employee/employeeId`,
that's for reading all and reading a single employee.
I want to make this only available to the role of employee.
And then, if we want to modify the database,
for example, using a `POST` to create a new employee,
or a `PUT` to update an employee,
I only want that to be available to the role of manager.
And then finally, to actually `DELETE` an employee,
I only want this operation available to the role of admin.
As you can see here, we have different endpoints,
and we have different roles based on those given operations for those endpoints.
And so let's go ahead and implement this using **Spring Security**.

````java
requestMatchers(<< add path to match on >>)
    .hasRole(<< authorized role >>)
````

Let's take a look at the general syntax here
for restricting access, we can make use of this method called request matchers.
We can give the path to match on, here I could say let's restrict access
to a given path of `/api/employees`,
and then we can specify for this given path,
it's only available to people that have a certain role,
or has role, and you give the name of a single role.
For example, you could say the roll of admin.

````java
requestMatchers(<< add HTTP METHOD to match on >>, << add path to match on >>)
    .hasRole(<< authorized roles >>)
````

Now one thing that you may have noticed is that for a given path, 
well, it changes depending on what type of HTTP method we're using, right?
So if we're doing a `PUT`, or a `POST`, or a `DELETE`,
we need to kind of add that support here too.
To make use of this request matchers,
we have the path to match on, but then we also specify the actual HTTP method to match on,
saying, hey, this is only available for `GET`,`POST`, `PUT`, `DELETE`, etc.
And then we can also specify the role, a given single role.

````java
requestMatchers(<< add HTTP METHOD to match on >>, << add path to match on >>)
    .hasAnyRole(<< list of authorized roles >>)
````

Now taking this a bit further, we've seen the path,
we've seen the method, but we only saw a single role.
Well, what about if something could match on multiple roles or any role?
So we make use of this other method here, _hasAnyRole_, 
and we simply give a comma-delimited list of roles that are authorized.

````java
requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE"))
requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE"))
````

Okay, so let's kinda walk through this with some examples here.
So if I want to authorize a request for the employee role,
we set up this **requestMatchers**, and we have `method.GET` for `/api/employees`, 
_hasRole_ of employee, so that's for reading all the users.
We could also say `requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE"))`.
This handles the case of reading a single employee.
Now the `**` syntax means that it'll match on all sub paths, or kinda like a wildcard, right?
So when they say `/api/employee/employeeId`, this will match on that given item.
Now let's look at examples for authorizing requests for the manager role.

<table align="center">
    <thead>
        <th>HTTP Method</th>
        <th>Endpoint</th>
        <th>CRUD Action</th>
        <th>Role</th>
    </thead>
    <tbody>
        <tr>
            <td>GET</td>
            <td>/api/employees</td>
            <td>Read all</td>
            <td>EMPLOYEE</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/api/employees/{employeeId}</td>
            <td>Read single</td>
            <td>EMPLOYEE</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/employees</td>
            <td>Create</td>
            <td>MANAGER</td>
        </tr>
        <tr>
            <td>PUT</td>
            <td>/api/employees</td>
            <td>Update</td>
            <td>MANAGER</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/api/employees/{employeeId}</td>
            <td>Delete employee</td>
            <td>ADMIN</td>
        </tr>
    </tbody>
</table>

Now remember, for managers they can do a `POST` or a `PUT`,
meaning create or update, only available for managers.

````java
requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER"))
requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER"))
````

Here I have `requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER"))`.
And a similar thing for `requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER"))`.
Again, this is handling the endpoints here for `POST` input,
meaning create an update for a given item,
and those operations are only available for managers.

````java
requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"))
````

And then finally, let's cover the case of authorizing requests for the admin role.
Remember, for the admin role, that's the only role that can delete an employee.
And remember, the `**` here is kind of the wildcard that'll match on all sub paths here.
So this covers that case for the endpoint of `DELETE`, with the `/employee/employeeId`.
This is all some really good information, let's pool it all together with the coding example,
so we saw it in small bits, now let's pull it all together here.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
    http.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
    
    // use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaaults());
    
    return http.build();
}
````

In our class here we create this bean our security filter **Bean**,
it's going to pass in a HTTP security that we can make use of.
Inside this method I have `http.authorizeHTTPRequests`,
so again, we're authorizing based on roles.
Here I have `configure.requestMatchers`,
I give the `method.GET`, I give an endpoint`.hasRole("EMPLOYEE")`,
and I just repeat the process for each one of the authorizations that I'm setting up.
I have the authorizations here for those two employee endpoints,
and then for the `POST` input, and then finally for `DELETE`.
So that's how we kind of define the authorization roles here, 
as far as who can access certain endpoints in our application.
And remember, the `**` means, handle that's given wildcard section in all sub paths.
And also I tell the app to use HTTP basic authentication,
so since we're overriding providing our own security filter chain, 
we have to be explicit in saying the type of authentication method that we're using.
And finally, when this is all done, we return this `http.build()`,
gives us an instance of the security filter chain that'll be used by **Spring Security**.
And users can log in based on those user IDs and passwords that we created earlier, 
and also those roles, and that'll all be applied accordingly
when a user logs in trying accessing our system,
we'll check the user ID and password,
make sure that's correct, so they're authenticated,
and then based on their role will authorize 
if they can access a given endpoint based on their role.

Now we have another topic to discuss here,
and that's cross-site request forgery, or **CSRF**.
**Spring Security** can protect against **CSRF** attacks.
Basically, what they do in the background for like web applications that use forms,
they'll embed additional authentication data, or tokens in all the **HTML** forms, 
and on follow on requests, the web app will verify the token before processing.
This is the primary use case in traditional web applications where you have web pages,
**HTML** forms submitting data, and so on.
So that's the main use of **CSRF**, is for making use of web apps with web forms.

Now you may wonder when should I use **CSRF** protection?
Well, the **Spring Security** team recommends using 
**CSRF** protection for any normal browser-based web request.
So think of your traditional web apps that have **HTML** forms,
the user's clicking something, adding, modifying data, and so on,
that's when you want to make use of **CSRF** protection.
However, if you're building a REST API for non-browsers clients, 
meaning non-web browsers, you may want to disable **CSRF**.
In general, **CSRF** is not required for stateless REST APIs
that use `POST`, `PUT`, `DELETE`, and/or `PATCH`,
in most cases will disable the **CSRF** protection.
However, don't be alarmed;
this is a recommendation actually documented in the **Spring** reference manual.

````java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
    http.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
    
    // use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaaults());
    
    // disable Cross Site Request Forgery (CSRF)
    http.csrf(csrf -> csrf.disable());
    
    return http.build();
}
````

Now pulling it all together here,
there's one entry that I added here in this configuration.
Disabled cross-site press forgery, **CSRF**,
here we make use of the **http** `csrf.disable`.
Again, like I mentioned earlier, in general, **CSRF** is not required for stateless REST APIs
that use `POST`, `PUT`, `DELETE`, and/or `PATCH`.

Let's restrict access based on roles by coding.
Let's move in here and let's
open up our `DemoSecurityConfig` class, 
and we'll add this new section of code here for restricting access based on roles.

````java
package com.luv2code.springboot.cruddemo.security;

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

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

    // use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaaults());

    // disable Cross Site Request Forgery (CSRF)
    // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
    http.csrf(csrf -> csrf.disable());

    return http.build();
}
````

We'll create this _filterChain_, we'll pass in the **HttpSecurity**,
and then we'll go through, and we'll use _authorizeHttpRequests_.
We'll set up this config, and based on the rules that we have from earlier,
we'll go ahead and define our request matchers.
Here, I'll define this request matcher, for `GET`.
I'll give the path of `"/api/employees"` and `hasRole("EMPLOYEE")`.
And now I simply just copy and paste this X number of times,
and I'll simply update this based on the rules that I've set up.
Just starting here with the second line, it's a `GET` request `"/api/employees/**"`
for reading a single employee.
So I do a `/**`, a wild card that'll match on all sub paths, 
and that's for the role of employee.
And now for the next item here,
I'll have `POST`, and the path is `"/api/employees"`, has the role of manager.
So that's for adding a new employee or creating a new employee.
For the next item here, I set the method to `PUT` `"/api/employees"` and again,
for manager, and that's for updating an existing employee.
And then finally, for deleting an employee,
it's only available for admin.
Here I have the method of `.delete` `"/api/employees/**"` to match
on the wildcard and all sub paths.
And I update the role here for admin.
All right, so at the moment, this looks pretty good here
as far as setting up our authorization rules
for the endpoints based on the given user roles.
Now we're not done.
There's still some additional coding that we need to do here,
and let's dive in and get this wrapped up.
I need to tell **Spring Security** that we're using basic authentication,
and this is for basic auth.
And also I need to disable **CSRF**.
And again, remember **CSRF** is for web applications with **HTML** forms.
In general, **CSRF** protection is not required for stateless APIs that use `post`, `put`, `delete` and so on.
Here I can say it's `csrf.disabled()`.
And then finally, I just make use of `http.build()` to return that given information.
Right, so just kind of standing back here
Let's go ahead and run an application and get it up and running.
I'll open up Postman, and I'll go through a number of different test cases here.
The first thing I'll do is I'll test the role of employee for our user, `John`, and a couple of things here.
The first two tests should pass, so `John` can go through and `GET` all employees and also `GET` a single employee.
However, the next three tests should fail.
So trying to `ADD` an employee, that should fail, `UPDATE` and `DELETE` should fail
because `John` doesn't have the roles for those given operations.
He's not authorized.
Let's go ahead and start with the first test of getting all employees.
We have this local host API `/employees`.
Make sure we have `Basic Authentication` selected and then the username of `John` password `test123`.
So this test should pass:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image15.png" alt="image15">
</div>

And yes, it passes.
So notice here, down here we get the `200` of okay, so that's successful.
He's authorized to access this given endpoint.
Now let's try the next endpoint here of getting a single employee.
And again, this one should pass too.
But let's go ahead and test it out.
I'll move up here, and I'll simply duplicate this tab.
And this is for getting a single employee.
I'll just say `/employees/` and I give the employee id.
You all start with maybe the number one and then check the authorization,
the user's `John`, password, and then hit `Send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image16.png" alt="image16">
</div>

And excellent.
Success status code of `200`.
That means that everything's okay,
we're able to access that given data.
Now, let's go ahead and try a failing case here.
Let's try and add an employee.
So we provide the credentials for `John`,
but we're trying to add an employee.
I'll move up here, and I'll duplicate this tab.
And remember, to add an employee, we have to change the method here.
So we're trying to do a `POST` and then update the endpoint, 
so there's no ID here, just `/employees`.
Have to specify the `Body`, right?
So in the `Body` we choose `raw`.
And then for the content type, we choose `JSON`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image17.png" alt="image17">
</div>

Now let's simply give our employee information that we want to add.
`firstName` and `lastName`, `Matt Lee`.
And then we give the email `matt@luv2code.com`.
Let's check the authorization here.
The user's `John`, the password `test123`.
Now let's do a `Send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image18.png" alt="image18">
</div>

And this fails.
403 Forbidden. 
Basically no authorization.
But this kind of works as desired, right?
We wanted this test to fail because John's not authorized.
John's an employee.
John should not be able to add employees.
Now, let's go ahead and try the other scenario here a fail updating an employee.
So let's go ahead and duplicate this tab.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image19.png" alt="image19">
</div>

And to update an employee, we have to change the method.
So instead of `POST`, it's going to be `PUT`
and for `Body`, I'm just going to give a new first name and last name.
You can choose any name that you want here.
And then, since we're doing an update, we have to put the `ID` in the `JSON`.
Here I add this new entry `ID` of `1`.
So basically, I'm saying I'm going to update the employee ID of 1 
with this new first name, last name, and email.
And then we'll click on authorization.
Notice here we have the user of `John`.
We click `Send`: 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image20.png" alt="image20">
</div>

And, we get a 403 Forbidden, unauthorized.
And again, this works out just fine, right?
Because we wanted this test to fail,
because John is an employee, and he's not authorized to perform an update.
Now our final test case here of doing a delete.
Now remember, the only person that can do a delete is an admin.
I'll just go ahead and duplicate this tab one more time.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image21.png" alt="image21">
</div>

I changed the method here to `DELETE`.
And then for `DELETE`, we specify the employee ID that we want to delete.
So /employees/4, let's just say,
let's delete employee `ID` of number `4`.
There's no `Body` that we need to send over, so I'll click on none.
I check the authorization.
So this is for user, `John`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image22.png" alt="image22">
</div>

And excellent. 
So this fails as desired.
`403` Forbidden.
And this fails because the only people that can delete are admins,
and `John` does not have the admin role.
We kind of went through the basic test case of going through for the employee role and seeing what they can perform
and what they can't perform.

So we covered the role of employee.
Now let's go ahead and cover the roles for manager and also admin.
Now we have this role of manager.
So `Mary`'s a manager.
So based on this user, they'll be able to `GET` all employees and single, 
`ADD`, `UPDATE`, but they won't be able to `DELETE` because that's only for admins.
So let's start off with this first test here, `GET` all employees.
So I'll go back to that first tab.
I'll update the username to be `Mary` and then hit send.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image23.png" alt="image23">
</div>

And that's successful.
`200`.
Let's move to that next tab of getting a single employee.
Update the user for `Mary`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image24.png" alt="image24">
</div>

And again, that's `200`.
Okay, let's move over to `POST` and attempt to add an employee,
and instead of the username of `John`, we'll use `Mary`.
And then hit `SEND`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image25.png" alt="image25">
</div>

And an excellent 200.
So the manager is allowed to add new employees.
So this is great.
And now let's test the case of updating an employee.
I move over to this other tab here for `PUT`, that's for update.
Previously, that was forbidden because `John` couldn't perform an update
but now let's change the username to `Mary`,
and hit `SEND`: 

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image26.png" alt="image26">
</div>

And excellent this was successful,
and we could perform an update on a given employee because of our role.
And now finally, let's test a case of deleting an employee.
Now this should fail, right?
Because `Mary` does not have the role of admin but let's test it out.
I'll select the `DELETE` tab here.
And previously it failed because `John` could not delete.
Let's try it with `Mary` and let's hit `SEND`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image27.png" alt="image27">
</div>

And this is also forbidden.
So this fails, but this fails as desired, right?
Because `Mary` does not have the role of admin,
so she can't perform any admin operations.
Now let's switch gears here and let's take a look at the admin role,
and we'll make use of `Susan`.
So Susan has all of these roles, and it should work out for `Susan`.
So we can just test the first four tabs here to `GET` all employees and single employee, `POST` to add new one,
`PUT` to update one, and `DELETE` an employee.
But let's cut the edges here,
And choose that last tab here for deleting an employees.
This should only be available to people with the admin role,
but `Susan` has the admin role, so this should be okay.
Previously, `Mary` failed because `Mary` didn't have that role.
We update the username to `Susan` and we hit `SEND`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image28.png" alt="image28">
</div>

And an excellent success 200.
So this works out as desired.
So we're able to kind of restrict these endpoints here based on roles.
And we went through the different use cases of employee role, manager role, and also admin role.
So this works out exactly the way we planned it.
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