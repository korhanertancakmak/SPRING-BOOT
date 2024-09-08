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

In this section, we'll learn how to use **Spring Security**
with user accounts stored in the database.
So far, our user accounts were hard coded in Java source code,
and we did that just to kind of keep things simple.
But now we want to add database access.
This is an advanced feature of **Spring Security**,
and we'll actually use it in this section.
Now let's recall the user roles.

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

We have three users, `John`, `Mary`, and `Susan`.
We have our passwords, and we also have their roles,
and we used these in some of the previous sections.
Now we'll simply put this information in the database.
Out of the box, **Spring Security** can read user information from the database.
By default, you have to follow **Spring Security**'s predefined table schemas.
But the nice thing about it is that by following their schemas,
and then **Spring Security** includes all the JDBC code
to actually read information from the database.
There's very little Java code you have to write as far as JDBC code
for reading information from the database.
All you have to do is simply set up the configuration,
create the appropriate tables,
and **Spring Security** will do all the heavy lifting for you in the background.
Now you also have the option of customizing the table schemas.
This is very useful if you have custom tables specific to your given project.
The only thing that you'll be responsible for
is developing the code to access the data.
In this scenario, you'll have to write the low-level **JDBC** code
or **Hibernate** code to read the data from the appropriate tables.
You'll have to read the account information and also read the user roles.
What we'll do in this section is that we'll start off,
and we'll use **Spring Security**'s predefined table schemas.
That'll give us all the functionality
and all the code for hooking into the actual database.
And this is all given to us out of the box.
Here's the development process.

* Create an SQL script to set up the database tables
* Add database support to our project using the **Maven** `pom` file.
* Create the JDBC `properties` file
* Update the **Spring Security** configuration to use JDBC for authentication and authorization

All right, as I mentioned,
**Spring Security** has a default database schema,
so you need to provide two tables,
one called `users` and another one called `authorities`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image29.png" alt="image29">
</div>

And you have to use these exact table names.
And also the tables need to have these specific columns,
`username`, `password`, and `enabled` for the `users` table.
And then also `username` and `authority` for the `authorities` table.
You need to have the exact same table names and columns as shown here.
Also, when you see the word `authorities` here, authorities is the same,
or loosely the same thing, as `roles`.
So again, when you see `authorities`, just think `roles`.

Moving to step one, that's creating the SQL script to set up the database tables.
As you know, we need to have these two tables, `users` and `authorities`.
I'll start off with `users` here.

````sql
CREATE TABLE `users` (
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `enabled` tinyint NOT NULL,
    
    PRIMARY KEY (`username`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
````

I'll go ahead and create the table users,
and then I'll create those columns, `username`, `password`, and `enabled`.
And then we'll also set up the primary key based on the `username`.
And that's basically it here for the table.
Again, the exact same table name of `users` and the exact same column names,
`username`, `password`, and `enabled`.

````sql
INSERT INTO `users`
VALUES
('john', '{noop}test123', 1),
('mary', '{noop}test123', 1),
('susan', '{noop}test123', 1);
````

Now let's go ahead and handle the password storage here and `INSERT` our users.
And then we'll do an `INSERT INTO 'users'`.
We'll get the values here for `john`, `mary`, `susan`,
and we give the `username` comma the `password` comma the `enabled` status.
And now what we have here is the actual `password`.
So the password is `test123`.
And remember, we have the actual encoding algorithm id and that's in curly braces.
Here, it's `noop`.
That basically says the password is stored as plain text.
And we'll start with this just for the beginning.
Later on, we'll move into more advanced features by using bcrypt encryption.

````sql
CREATE TABLE `authorities` (
    `username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    
    CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
````

Now let's go ahead and move into the roles.
And we have to create this table called `authorities`.
Here we have two fields for `username` and `authority`,
and then we go through it and set up the `unique key`
based on the `username` and `authority`.
We also set up our `constraints`.
And here the `foreign key` references the `users` table for `username`.
And that'll basically create the `authorities` table.
And again, remember the `authorities` table, here's the same thing as roles.

````sql
INSERT INTO `authorities`
VALUES
('john', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_MANAGER'),
('susan', 'ROLE_EMPLOYEE'),
('susan', 'ROLE_MANAGER'),
('susan', 'ROLE_ADMIN');
````

All right, so now let's go ahead and insert some of our user roles into our database table.
So we have our `users`, and then we simply do an insert into `authorities`.
And again, `authorities` here, same as roles.
And we'll go ahead and do an insert here for `John`.
He's an employee.
We do an insert here for `Mary`.
She's an employee and manager.
And we also do an insert for `Susan`.
She's an employee, manager, and admin.
And internally, **Spring Security** will use the `ROLE_` prefix for the actual role entries.

```xml
<!-- MySQL -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

Now moving ahead here to step two, we add the database support to our **Maven** `pom` file.
Here we make sure to give a reference to our **JDBC** driver.
In this example, we're using **MySQL**.
So we give the appropriate entry here for the **MySQL** `groupId` and `artifactId`.

```properties
#
# JDBC connection properties
#
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

Next, we have step three of creating our **JDBC** properties file.
Basically, we can reuse our existing properties
because we'll have this one database schema for our employee directory.
And we'll place our security tables within that same database schema,
so we can simply just reuse this same information.

```java
@Configuration
public class DemoSecurityConfig {
    
    @Bean 
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        
        return new JdbcUserDetailsManager(dataSource);
    }
    
    // ...
}
```

And now I can move into my **DemoSecurityConfig**.
This is where I'm telling **Spring Security** to use **JDBC** authentication.
And then I inject the data source.
This is the one that's autoconfigured by **Spring Boot**.
And then I tell **Spring Security** to use **JDBC** authentication with our data source.
And the really nice thing here is that we're no longer hard-coding the `users`.
We're actually reading the `users` and `roles` from the database.
And now **Spring Security** will handle all the low-level work of reading the user password, roles, 
and so on from our database because we're following their table schema,
and we're using their actual table names and their actual column names.
So **Spring Security** will do a lot of the heavy lifting for us in the background.
So let's go ahead and get our database stuff configured, 
and then we'll start writing some of this code, 
and we'll actually hook **Spring Security** up to our database.

We'll start off by running the **SQL** script to set up the database tables for security.
And we already have the files available in this directory called `sql-scripts`.
And this will make use of this `04-setup-spring-security.sql` file.
And this will set everything up for our plain text database.
Now let's go ahead and swing over to MySQL Workbench, and we'll open up this **SQL** script.
And now I'm logged in here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image30.png" alt="image30">
</div>

Let's go ahead and do a file, open SQL script,
and I'll move down to my project directory.
So `05-spring-boot-rest-security`, and then we'll move
into this `00-spring-boot-rest-security` folder.
And then we'll move into **SQL** Scripts, and then we'll select the file
`04-setup-spring-security-demo-database-plaintext.sql`.
Alright, so we have this file open.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image31.png" alt="image31">
</div>

We'll go ahead and drop any of the previous tables that are there.
And then I'll create this table for `users`.
And I'll specify the columns for a `username`, `password` and `enabled`.
And again, make sure the table names and columns are exactly the same, 
because they'll follow spring security's predefined table schema.
And now let's go ahead and insert some sample data for our `users`.
So our three users, `John`, `Mary`, and `Susan`.
And remember, `test123` is the password, and then
we have the `noop` and curly brace.
That's the encoding algorithm id.
And remember for `noop`, it lets spring security
know that the passwords are stored as plain text.
And we'll get into all the encryption stuff in some of the later sections.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image32.png" alt="image32">
</div>

And then we move down here to this next section
of creating a table for `authorities`, the `username`, and the `authority`.
And remember, `authorities` is the same, or loosely the same as `roles`.
And then we move down here, we insert the roles for a given user.
So here we insert the roles for `John`.
He's an employee.
`Mary`'s an employee and a manager.
`Susan`'s an employee, manager, and admin.
And remember, spring security will prefix the `ROLE_` for each of those roll names.
Let's go ahead and execute this SQL script.
And then over on the left hand side, let's do a refresh all here,
so we can get those new tables.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image33.png" alt="image33">
</div>

So we have these two new tables, `users` and `authorities`.
Let's go ahead and do a quick query here on the `users` table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image34.png" alt="image34">
</div>

And we see our three users here, `John`, `Mary`, and `Susan`.
We can go ahead and check on the `authorities` of the roles here, by doing a query here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image35.png" alt="image35">
</div>

And now, we can see the appropriate roles here, for `John`, `Mary`, and `Susan`.
Now one thing I want to show you here, 
is just a way of how to generate the **actual database diagram**,
just so you can see the relationship between the different tables.
So I'll go up here, `Database`, `reverse engineer`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image36.png" alt="image36">
</div>

I'll choose my connection.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image37.png" alt="image37">
</div>

I'll go ahead, and log in accordingly.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image38.png" alt="image38">
</div>

And I'll just, kind of, hit continue over here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image39.png" alt="image39">
</div>

I select my database schema, employee directory,
and continue, and continue again.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image40.png" alt="image40">
</div>

Make sure those items are checked there.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image41.png" alt="image41">
</div>

Go ahead, and hit execute.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image42.png" alt="image42">
</div>

And then one more item here.
And then finally, we can hit close.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image43.png" alt="image43">
</div>

And now we have the schema.
At the moment, there's three tables.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image44.png" alt="image44">
</div>

The employee table that we've been using earlier,
and now these are the security tables, `users` and `authorities`.
So we have a `user` table, and then `authorities`.
So a user can have one-to-many roles,
or one-to-many authority entries, or whatever.
So that's the idea here, as far as the relationship.
So we have our information in the database.
Now we're going to update our **Spring Security** configuration to use **JDBC**.
I'll swing back into my IDE.
I'll move into my code here, and I'll open up **DemoSecurityConfig**.

````java
@Configuration
public class DemoSecurityConfig {

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
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

/*    
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
*/
}
````

And what I'd like to do is comment out the code where we hard-coded the users.
We don't want to use this anymore, so I'm just going to comment it out,
and I'll move it to the end of this file.
All right, so now I can get down to work.
I'll write a quick comment to myself.

````java
@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... no more hardcoded users
    @Bean 
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        
        return new JdbcUserDetailsManager(dataSource);
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
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

/*    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // ...
    }  
*/
}
````

Here, we'll add support for JDBC and no more hard-coded users.
I'll create a bean that returns a **userDetailsManager**.
To find the method _userDetailsManager_ that takes a **DataSource**.
So basically, we're going to inject the **DataSource**,
and this is the **DataSource** that's autoconfigured by **Spring Boot**.
And now I'll create this new **JdbcUserDetailsManager**.
And I pass in the **DataSource**.
And again, this tells **Spring Security** to use **JDBC** authentication with our data source.
And then **Spring Security** knows that it's using a predefined table schema.
So **Spring Security** will look in a table called `Users`
and another table called `Rows`.
It knows the exact column names that it'll use.
It's kind of just built-in out of the box.
Allright, so let's run our application and test it out.
Our app is up and running,
let's go ahead and swing over into **Postman**.
And I'll go ahead and create a new request.
So this will be a new `GET` request.
And I'll simply just do the GET on `/api/employees`.
And I'll go through under `Authorization`.
I'll choose `Basic Auth`, and then I will add the user information.
So, username of `john`, password, `test123`.
I show it just to kind of make sure I'm on track.
Hit `Send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image45.png" alt="image45">
</div>

And this is successful.
But are we really using information from the database
or is it still using some in-memory data source or whatever,
like some hard-coded users?
So, just for sanity's sake, let's go in MySQL Workbench,
and actually change the password for the user, `John`.
I want to see if it's really using the database.
You can change it to anything you want.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image46.png" alt="image46">
</div>

To keep it simple here,
I'll just change it to `abc123`,
and once you change it here,
be sure to click on the `apply` button in the bottom right,
and then kind of review the SQL, so they'll update the `user`,
set the password to `abc123` where username equals `John`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image47.png" alt="image47">
</div>

So, it's going to run this `update` here,
so be sure you hit `apply` to actually make this take effect,
because simply changing it over there is not enough.
Hit `apply`. 
Now we're good to go, and that's the new password, `abc123`.
Now if I run this same example,
and I'll try that old password, I'll try using
`John` `test123` and hit `send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image48.png" alt="image48">
</div>

Alright, `401`, it doesn't work
because it checked against the database,
and also the nice thing here is that, a couple of things.
One, we know it's actually using our database because,
hey, the `test123` didn't work because we changed it in the database,
and then also two, there was no need to restart the application.
**Spring Security** will query the database for each login.
Allright, so that's a nice feature there.
There's no need to stop and restart again.
It simply hits the database, whatever is in the database
that's what it'll process on at that given time.
At this point, we're confident that it really is reading information from our database.
All of our **Spring Security**, JDBC, **userDetailsManager**,
all that stuff is set up, and working as desired,
and we can also verify this by trying the new password here,
`abc123` and hit `send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image49.png" alt="image49">
</div>

and then success, we get the `200` status code.
Let's go ahead and test roles,
and I'll move up here and duplicate this tab,
and I'll change the method to a `delete`,
and I'll say delete `/1`,
and we know that `John` can't delete because of his role.
He's only an employee.
He does not have permissions to perform a `delete`.
The only person that can do delete is a person who has the admin role.
So, this little test here should fail, and then
we'll do a `send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image50.png" alt="image50">
</div>

And we get the `403` forbidden.
So the user and password was okay,
so they were authenticated, but they were not authorized to perform 
this given operation based on their role,
and it's still a similar thing for `Mary`.
This should fail also because `Mary`'s a manager.
`Mary` does not have the admin role,
and this is forbidden also as desired.
Now remember, the only person that can `delete` here
is a person who has the role of admin.
So I'll move up here for `Susan`,
and then we'll do a `send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image51.png" alt="image51">
</div>

and then success `200`.
So that's a-okay, so we're in good shape here.
Alright, so this all looks pretty good
I'm going to set John's password back to `test123`,
just so I don't forget what his password is.
So, I'll just set it to `test123`.
Be sure to do to `apply` again to make the changes take effect.
Allright, we made some really good progress.
We're no longer using hard-coded data,
instead we're using data from the database.
We went through the process of creating the **SQL** tables for security,
and then also updating our code accordingly to
make use of **Spring Security** for the database.

</div>

## [Bcrypt Encryption]()
<div style="text-align:justify">

In this section, we'll use **Spring Security** with password encryption.
So far, our user passwords are stored in plain text.

<table align="center">
    <thead>
        <th>username</th>
        <th>password</th>
        <th>enabled</th>
    </thead>
    <tbody>
        <tr>
            <td>john</td>
            <td>{noop}test123</td>
            <td>1</td>
        </tr>
        <tr>
            <td>mary</td>
            <td>{noop}test123</td>
            <td>1</td>
        </tr>
        <tr>
            <td>susan</td>
            <td>{noop}test123</td>
            <td>1</td>
        </tr>
    </tbody>
</table>

So this is okay for getting started,
but it's not for production,
not ready for real-time projects.
So the best practice is to store the password in an encrypted format.

<table align="center">
    <thead>
        <th>username</th>
        <th>password</th>
        <th>enabled</th>
    </thead>
    <tbody>
        <tr>
            <td>john</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
        <tr>
            <td>mary</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
        <tr>
            <td>susan</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
    </tbody>
</table>

So here's the users, `John`, `Mary` and `Susan`
and their passwords and notice,
it's an encrypted version of the password.
So if our databases were hacked,
the hackers wouldn't be able to figure out
these passwords, wouldn't be able to figure out
the plain text version of these passwords,
because they're encrypted.

The **Spring Security** team recommends
using the popular **bcrypt** algorithm.
So the **bcrypt** algorithm performs a one-way encrypted hashing.
It adds a random salt to the password for additional protection,
and it also includes supports to defeat brute force attacks.
So this is the current recommendation from the **Spring** team,
and it's a popular one-way password hashing algorithm
that's used by other projects.

Now if you'd like to get more background information
or additional information on bcrypt,
I have some links [here](https://www.luv2code.com/why-bcrypt) for you.
So if you'd like to know why you should use bcrypt
to hash passwords, go to the site.
If you'd also like to get a detailed bcrypt algorithm analysis, 
simply go [here](https://www.luv2code.com/bcrypt-wiki-page).
And finally, if you'd like to learn best practices on password hashing, 
simply go [here](https://www.luv2code.com/password-hashing-best-practices).
Now these links will basically redirect you to other websites to provide
all the detailed information for you.

So now you may wonder how to get a bcrypt password.
So you have a plain text password
and you want to encrypt it using bcrypt.
So you have one option is to use a website utility to perform the encryption.
Another option is to write some Java code to perform the encryption.
So we'll actually cover option one in this section
and then for option two, we'll have information on that
in some of the later videos later in the course.

Alright, so getting a bcrypt password using a website.
So you can simply go [here](https://www.luv2code.com/generate-bcrypt-password).
It's going to redirect you to a website utility.
You'll enter your plain text password
and then that website's going to generate a bcrypt password for you.

So let's go ahead and look at a quick demo of this.
Move to your web browser and go ahead and access
this website [here](https://www.luv2code.com/generate-bcrypt-password).
As I mentioned, it's going to redirect you to this website.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image52.png" alt="image52">
</div>

And basically, the way it works here is they have some text fills.
So you'll enter your plain text password,
and you hit calculate, and it'll generate the encrypted password for you.
So for the plain text password, I'm going to enter `test123`
and then I'll move down here and hit the `calculate` button and right here at the bottom,
that's the generated password.
So this is an encrypted version of that plain text, `test123`.
That's an encrypted version using **bcrypt**.
Now one important thing to note is that multiple runs
will generate a different password due to the random password salting.
So you can start with the same plain text password,
`test123`, but if you hit `calculate` multiple times,
you'll actually get a different generated password
and that's, again, due to random password salting.
Effectively, salting is random bits of data
they'll add to the password to make it unique.
And you can find more details on password salting
using those links I provided earlier in this section.
So taking a look at this example here,
we have a generated passwords and let's just keep an eye
on the last couple of digits here.
Let's go ahead and hit calculate
one more time for this `test123`

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image53.png" alt="image53">
</div>

and then notice here that it changes.
Basically, the whole thing changed,
but I wanted you to, at least, focus on the last couple of characters,
so you can get an idea of things that are being changed.
So that's the idea of generating or calculating a **bcrypt** password.
And what we can do with this is that we can use these encrypted passwords
and add them to our user accounts in our database and effectively, 
we can seed our user accounts with encrypted passwords out of the box.
Alright, let's look at our development process.

* Run the SQL script that contains the encrypted passwords
* Modify the DDL for the password field because the length should be 68 characters

There's no need to change any Java source code.
So this is mainly just a configuration exercise but no need to modify any
of the Java code that we've created before.
Will all work the same out of the box. 

<table align="center">
    <thead>
        <th>username</th>
        <th>password</th>
        <th>enabled</th>
    </thead>
    <tbody>
        <tr>
            <td>john</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
        <tr>
            <td>mary</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
        <tr>
            <td>susan</td>
            <td>{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q</td>
            <td>1</td>
        </tr>
    </tbody>
</table>

So here we'll have Bcrypt.
And so this kind of maps to the actual ID that we have in our database table for password.
And then for the encoded password, that'll be the actual generated
or hashed value that we retrieved from that website or that we created using Java code.
Now, one thing that's really important here about this password column,
it must be at least 68 characters wide 
because for Bcrypt and curly braces, that's eight characters.
And then the encoded password is 60 characters.
When you use BCrypt, your encoded password or your encrypted password is always 60 characters in length.
Regardless of the input of the plain text,
it's always 60 characters in length.

````sql
CREATE TABLE `users` (
    `username` varchar(50) NOT NULL,
    `password`    char(68) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    
    PRIMARY KEY (`username`)
    
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
````

Let's go ahead and modify the DDL for the password field.
So here we have `'password' char(68)`
because the password column must be least
68 characters wide, like I mentioned earlier,
Bcrypt and curly braces, eight characters the encoded or encrypted password, 60 characters.

````sql
INSERT INTO `users`
VALUES
('john', '{bcrypt}$2a$10$qeSOHEh7urweMojsnwNAR.vcXJeXR1UMRZ2WcGQI9YeuspUdgF.q', 1),
('mary', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyO0BuFdK2IwjyWefYkMpiBEF1pBwDH.5PMOK', 1),
('susan', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyO0BuFdK2IwjyWefYkMpiBEF1pBwDH.5PMOK', 1);
````

Now we need to actually insert some users here with encrypted passwords.
So here's `John` and here's his encrypted password of plain text `fun123`.
And remember, the Bcrypt is the encoding algorithm ID
and that lets **Spring Security** know that the
passwords are stored as encrypted passwords using `Bcrypt`.
In previous examples, we made use of `noop` that was plain text, but here we're using `Bcrypt`.
And now we just kind of repeat the process for `Mary` and `Susan`.
We simply add in the encrypted passwords for those users.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image54.png" alt="image54">
</div>

Now, one thing I want to do is just kind of give you a quick
behind the scenes tour about some of the spring security login process.
So we have our login form here.
We have our database that has encrypted passwords.
We have these spring security filters in place.
The user's going to enter their plain text password on the form and hit log.
So then behind the scenes, the spring security will read this information 
and perform some operations on it using JDBC authentication.

And let's dig in here on this JDBC authentication source what happens behind the scenes.

* Retrieve a password from the database for the user.
* Read the encoding algorithm like Bcrypt
* For the case of Bcrypt, it'll encrypt the plaintext password from the login form (using the salt from the database password)
* Compare the encrypted password from the login form **WITH** the encrypted password from the database
* If there's a match, then the login is successful
* If no match, then the login is not successful, login fails.

Now one thing that's really important here is
that the password from the databases is never decrypted,
because **Bcrypt** is a one-way encryption algorithm.
So we never grab the password from the database
and decrypt it and have it as plain text
because that algorithm just doesn't support that.
It's only a one-way algorithm.
So again, the process is that you read that plain text password
from the user form, you encrypt it,
and then you compare those two encrypted values,
the one from the form and then the one that you read from the database.

Now, we'll start off by running the SQL script to set up the database tables for security,
and we already have the files available in this directory called `sql-scripts`.
Now let's go ahead and swing over to MySQL Workbench,
and we'll open up this SQL script.
Let's go ahead and do a `File`, `Open SQL Script`,
and I'll move down to my project directory,
we have everything in `dev-spring-boot`, `05-spring-boot-rest-security`,
and then we'll move into this `00-spring-boot-rest-security` folder,
and then we'll move into `sql-scripts`.
Alright, so we have this file open.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image55.png" alt="image55">
</div>

We'll move in here, we'll go ahead,
and drop any of the previous tables that are there.
And then we'll have this `user`'s table,
very similar to what we had before,
except for we're simply going to make this password field longer, `char(68)`,
and I gave the reasoning for it again for bcrypt and those encoded passwords.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image56.png" alt="image56">
</div>

Alright, so down where we insert the users,
I'll add the encrypted password.
So I had this plain text, `fun123`,
the encrypted version is this big long string that we see here,
and we also have the encoding algorithm ID of bcrypt
and that lets **Spring Security** know
that the passwords are stored as encrypted passwords using the bcrypt algorithm,
and that's basically it.
We covered for `John`, `Mary`, and `Susan`.
Now, let's go ahead and click on the lightning bolt to execute this query.
We'll simply do a refresh,
and we'll go ahead and look at our tables here.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image57.png" alt="image57">
</div>

So we have our `authorities` and our `users`.
I'll do a quick query here on the users table,
and we have those encrypted passwords for those users.
So this looks really good so far.
First, let's go ahead and run our application.
Now let's go ahead and swing into Postman and test this out.
I'll select the `GET` request for `/api/employees`,
and I'll hit the `Authorization` tab here,
and I'll look at the `username` and `password`.
Now we know that `John` has a password of `fun123`,
that's the encrypted password in the database.
Well, let's go ahead and try it with `abc123`,
and let's go ahead and hit `Send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image58.png" alt="image58">
</div>

and on purpose, we're sending the wrong password, right?
So this is going to fail, or it has failed,
and we get the `401` Unauthorized and that's fine.
Now instead of `abc123`,
we'll give the correct password of `fun123`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image59.png" alt="image59">
</div>

and then that's successful, so that's awesome.
Now we know the database has the encrypted passwords of `fun123`,
and all the encryption and so forth is working behind the scenes
using **Bcrypt** and **Spring Security**.

Now, what I'd like to do here is actually change `John`'s password to something else,
`update` the database, and just see if those changes are reflected in the database.
I'll swing back over here to MySQL Workbench,
and I'll look at that generation tool,
or I'll go to that generation tool at [here](https://www.luv2code.com/generate-bcrypt-password).
Let's just copy that URL, swing over to our browser and drop it in.
So I can just simply enter our password here,
and it'll perform the bcrypt work behind the scenes.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image60.png" alt="image60">
</div>

So I entered the password of `crazy123`,
I have this hash result, so I can still click copy this,
and it'll automatically copy it to your buffer,
and then I can swing back over to MySQL workbench and I can update it.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image61.png" alt="image61">
</div>

So, be sure to keep the Bcrypt portion,
as far as those words in the curly braces.
Keep that piece, just drop in that new password, that Bcrypt hash,
and then also remember hit the `Apply` button over here on the bottom right,
it'll pop up a little window where you can review the SQL.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image62.png" alt="image62">
</div>

Now if we swing back over to Postman,
first off, we'll send the bad password of `fun123`.
That fails because now our database has the password of `crazy123`,
or the bcrypt hash version of `crazy123`.
Do a `Send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image63.png" alt="image63">
</div>

And now that's successful.
So we know, again, it's handling all the Bcrypt workforce in the background,
**Spring Security** is aware of that,
and also we didn't have to restart our application, it all happens on the fly.
So we have the bcrypt encryption working out just fine.

</div>

## [Custom Tables]()
<div style="text-align:justify">

In this section, we'll configure **Spring Security** to use custom tables.
So far in the sections we have used the default **Spring Security Database Schema**.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image64.png" alt="image64">
</div>

And remember I said you had to use the exact same table names and the exact same column names.
That works okay, but you may have thought internally that this is a bit restrictive.
Also, you may have thought, what if we have our own custom tables, like our own names?
For example, at your company you may already have existing security tables,
and you want to configure **Spring Security** to use those tables.
Or you may work for a large multinational company,
a large enterprise company, and you have custom tables that are specific to your company.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image65.png" alt="image65">
</div>

For example, in the database diagram on the screen we have two tables, `members` and `roles`.
This is an example of custom tables.
Nothing matches with the default **Spring Security** table schema.
You should be able to use any tables and any columns,
and that's what we'll do here in this section.
And don't worry, you can use your own custom tables with **Spring Security**.
The only thing you need to do is tell **Spring Security** how to query your custom tables.
You need to provide a query to find a user by name.
And also you need to provide a query to find authorities or roles by the username.
So again, you can use any tables design that you want,
but you simply need to tell **Spring Security**,
"_Hey, this is how you find the given user" and here's how you find the roles for that given user._"
Here's the development process.

* Create our custom tables with SQL
* Update the **Spring Security** Configuration
* Provide a query to find a user by username
* Provide a query to find authorities/roles by username

Alright, let's get started with step one of creating our custom tables with SQL.
So here's the database diagram of our custom tables.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image66.png" alt="image66">
</div>

We'll have a table for `members`.
This is where we'll store our users.
And note here the column names of `user_id`, `pw` for password, and `active`.
We'll also have another table called `roles`.
And we'll have the column names of `user_id` and the `role`.
In the coding, we'll run the appropriate SQL script to actually create the tables.
But here I wanted to make sure you could see the diagram.
Now, notice here that this is all custom.
Nothing matches with a default **Spring Security** table schema.
In fact, you can use any table name, any column names.
You have total freedom here as far as the table names and the table columns.

````java
@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailManager theUserDetailsManager = new JdbcUserDetailManager(dataSource);

        theUserDetailsManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        
        return theUserDetailsManager;
    }

    // ...
}
````

Now in step two, we need to update our **Spring Security** configuration.
We need to modify the code where we created that **JDBCUserDetailsManager**, 
and we have to provide the query how to find users by giving username.
We give the appropriate SQL query to access our custom `members`'table.
And also we have to provide the query how to find roles by a given username.
We have to provide the appropriate SQL query to access the roles table.
Now the question mark here, that's basically a parameter placeholder.
The actual parameter value will be the username that's supplied during login.
As you can see, we can use any custom tables.
We simply have to tell **Spring Security** how to find the `users` and `roles` accordingly.

Let's start off by running the SQL script to set up the database tables for security.
And we already have the files available in this directory called `sql-scripts`.
Now let's go ahead and swing over to MySQL Workbench,
and we'll open up this SQL script.
And now, before we get started, I'd like to manually drop some of our previous tables.
So we have our old tables for `authorities` and `users`.
Let's go ahead and drop those now
because we're going to make use of custom tables.
We could actually keep them around,
but I simply want to remove them just so there's
no confusion as far as which tables we're using.
I'll go ahead and select the `authorities` table, and I'll drop that table.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image67.png" alt="image67">
</div>

Select the option here drop now, and then we simply repeat the process for the `user`'s table.
And we'll keep `employee` because that has our normal employee information.
We'll keep that, that's fine.
And now let's go ahead and go to the `file` menu, and `open SQL script`.
And I'll move down to my project directory,
then we'll select the file here
`06-setup-spring-security-demo-database-bcrypt-custom-table-names.sql`.
Alright, so we have this file open.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image68.png" alt="image68">
</div>

Now the first thing that we'll do is we'll set up the `drop` table for any previous tables
for `roles` and `members`.
And then we'll move down here, and we'll `create` this table `members`.
This is where we actually store our users, but it's a custom table.
Has a custom table name and columns.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image69.png" alt="image69">
</div>

And then we'll move down here, and we'll insert data into the `members` table,
so we'll insert data for our three members, or users, `John`, `Mary`, `Susan`.
The default password is `fun123`.
We'll go ahead and create the roles here.
Again, custom table name and custom columns.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image70.png" alt="image70">
</div>

Then finally here we do the `insert` on those roles for `John`, `Mary`, and `Susan`.
Now let's go ahead and hit the gold lightning bolt to execute this SQL script.
And then over on the left-hand side, let's go ahead and do a refresh.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image71.png" alt="image70">
</div>

And we have these two new tables here, `members` and `roles`.
Go ahead and do a quick query here on members,
so we have our three members, or our three users, `John`, `Mary`, `Susan`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image72.png" alt="image72">
</div>

And again, this is a custom table with custom column names.
And then for `roles`, we do a query also.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image73.png" alt="image73">
</div>

And then we have the roles here for `John`, `Mary`, and `Susan`.
Alright, so this looks pretty good.
Let's swing into our IDE and let's write some code.
So the main thing we want to do here is update our **Spring Security Configuration**
and give it the appropriate queries for making use of our custom tables.
I'll move in here into this **DemoSecurityConfig**.

````java
@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailManager jdbcUserDetailsManager = new JdbcUserDetailManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        
        return theUserDetailsManager;
        
        // new JdbcUserDetailsManager(dataSource);
    }

    // ...
}
````

And what I'd like to do is make some modifications here for this **JdbcUserDetailsManager**.
The first thing I want to do is refactor this item, `JdbcUserDetailsManager(dataSource)`, 
and set it up as a local variable.
I'll choose `Refactor` and I'll say `Introduce Variable`,
and I could give it any variable name, but I'll go ahead and take the one that they provide here,
**jdbcUserDetailsManager**.
Alright, so this coding here looks pretty good so far.
Let me write some quick comments to myself to keep myself on track.
So kinda standing back here, we want to define the query to retrieve a user by the username
and also define the query to retrieve the authorities/roles by username.
So, again, we have custom tables.
So now we need to tell **Spring Security**,
"_Hey, this is how you find my custom tables. This is the query that you should use,
and, also, these are the column names that you should use._"
And I'll go through here, and I'll say `jdbcUserDetailsManager.setUsersByUsernameQuery`.
And this is where we tell **Spring Security**,
"_This is how you can find a given user in our system based on a given username._"
What I'm providing here is just regular SQL,
`select user_id, pw, active from members where user_ID=?`.
And remember, that'll actually be passed in from the login form for our application.
And this takes care of it for this piece of it, 
the actual SQL matches with the actual database design that we have here, and, again,
telling **Spring Security** how to access our custom table.
Now let's do a similar thing here for `jdbcUserDetailsManager.setAuthoritiesByUsernameQuery`.
And, here, we're telling **Spring Security**, 
"_This is how to retrieve the authorities/roles for a given username._"
We provide the SQL, `select user_id, roles from roles where user_id=?`.
And, again, that user id is passed in by the login form.
Alright, so standing back here,
this is the updated code here for our **userDetailsManager**.
We added those comments there.
We provided the queries to retrieve the user 
and also retrieve the roles, using our custom tables.
Let's go ahead and test this out.
Alright, our app is up and running.
Let's go ahead and swing over to Postman.
I'll move over to the `GET` tab for getting all the employees.
I select the `Authorization` tab here, for the user `john`, I'll give a bad password.
I'll hit `Send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image74.png" alt="image74">
</div>

And here, authentication failed because we gave a bad password, and that was expected
because `John`'s real password is `fun123`.
Let's go ahead and enter that real password,
or correct password, and hit `Send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image75.png" alt="image75">
</div>

And yes, success.
We have the `200` status code.
So that's successful.
Now let's swing back over to Postman and let's go to this `delete` tab.
And what we want to do now is test the roles.
So making sure it can really make use of the roles and apply those accordingly.
We have authentication working but now what about the roles?
Now we know that `John` can't delete because of his role of employee,
so we'll do a delete on `employee/2`.
We'll hit authorization, username of John, password of `fun123`.
So be sure to change that to `fun123`.
And then hit `send`.

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image76.png" alt="image76">
</div>

And we get the `403` forbidden, which is good.
That's expected because you know, based on the roles,
`John` can't perform that operation or the employee
can't perform that operation of deleting.
But we know that `Susan` can delete because she has the role of admin.
So if I change the username to `Susan`,
password of `fun123` and then do a `send`:

<div align="center">
    <img src="https://github.com/korhanertancakmak/SPRING-BOOT/blob/master/05-spring-boot-rest-security/images/image77.png" alt="image77">
</div>

And then we get the status of `200`, successful.
The nice thing about this is that now we know that
we have a **Spring security** setup using our own custom tables.
These are our custom table names and custom column names.
We simply configured **Spring Security** on how to find a user by the username 
and also the query on how to find the authorities slash roles based on a given name.
So again, we gave it some customs SQL on how to access our custom tables.


</div>