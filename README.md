# Newsletter Application Like Substack. 

### Overview:

__Project workflow :__ A user can create/save an article, additionally user will be able to create a newsletter where he/she can publish articles.
Other users will be able to subscribe to multiple newsletters, and when an article is published in that newsletter they will receive an email notification.
Also users can like and comment on the articles.

_Note: Will be uploading the updated High level Design diagram soon, where things will be clearer!_

### Technology Used : 

1. Java, Spring boot, Maven.

2. PostgreSql, GCP pubsub. (currently using local docker containers for testing).

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
- [ ] Commenting on articles.
- [ ] Like and Count likes for articles