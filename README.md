# Newsletter Application Like Substack. 

### Overview:

__Project workflow :__ A user can create/save an article, additionally user will be able to create a newsletter where he/she can publish articles.
Other users will be able to subscribe to multiple newsletters, and when an article is published in that newsletter they will receive an email notification.
Also users can like and comment on the articles.

![High level design](/designdoc/NewsletterApp_HLD.png)

### Technology Used : 

1. Java, Spring boot, Maven.

2. PostgreSql. (currently using local container for testing) 

3. GCP pubsub. (currently using local emulator container for testing)

``` This is a backend project, not planning for UI as of now!. ```

---

__Rough Design document__ (_Some things have been tweaked during implementation_) :

1. __E-R Diagram :__

   ![E-R Diagram](/designdoc/NewsletterApp_ER.png)

2. __Entities Identified :__

   ![Entities](/designdoc/NewsletterApp_Entities.png)

3. __Class Diagram :__

   ![Entities](/designdoc/NewsletterApp_class_diagram.png)


---

### Project Progress : 
Basic workflow has been developed as an MVP.

Tasks Status :

- [x] Creation of User.
- [x] Creation of Article.
- [x] Creation of Newsletter.
- [x] Publishing of Article to Newsletter using GCP publisher.
- [x] Subscribing to a Newsletter using GCP pubsub topic subscription.
- [x] Sending email when article is published in newsletter.
- [x] Get all articles published in newsletter.
- [x] Commenting on articles.
- [x] Like and Count likes for articles.
- [ ] Refactor the Code.
- [x] Verify project run as jars in command line.
- [x] Configure logging. 
- [ ] Optimize the jpa repositories, so that SQL scripts generated are optimized.

---

### Steps to build

1. mvn clean install -DskipTests

2. Running the tests in the child modules : 
   1. mvn test (This will only run the Unit Tests)
   2. mvn verify -DskipUTs (This will run only the Integration Tests)

_Please note : for running the Integration tests, currently the docker containers for PostgreSql and GCP pubsub emulator needs to be manually started first. Will soon be shifting to TestContainers 
Library which does not need these manual steps!_