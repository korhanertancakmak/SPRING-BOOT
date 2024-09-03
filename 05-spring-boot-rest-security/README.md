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