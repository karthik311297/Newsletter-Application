1. podman pull gcr.io/google.com/cloudsdktool/google-cloud-cli:emulators

2. podman run --rm -p 8085:8085 gcr.io/google.com/cloudsdktool/google-cloud-cli:emulators /bin/bash -c "gcloud beta emulators pubsub start --project=test-project --host-port='0.0.0.0:8085'"

3. curl -X GET "http://localhost:8085/v1/projects/test-project/topics"

4. curl -X GET "http://localhost:8085/v1/projects/test-project/subscriptions"

5. JAVA_HOME=/d/Java_jdk_all_versions/zulu17.48.15-ca-jdk17.0.10-win_x64/zulu17.48.15-ca-jdk17.0.10-win_x64 mvn clean install -DskipTests

6. Refer this link for spring boot and google smtp integration - https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

7. podman run --name postgres -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres



# Sql scripts
```
create table if not exists userdetail(
	id bigserial not null,
	username varchar not null,
	name varchar not null,
	emailID varchar not null,
	primary key(id)
);


create table if not exists newsletter (
	id bigserial not null,
	name varchar not null,
	userDetail bigint not null,
	foreign key(userDetail) references userdetail on delete cascade on update cascade, 
	primary key(id)
);


create table if not exists article (
	id bigserial not null,
	title varchar not null,
	content varchar not null,
	userDetail bigint not null,
	foreign key(userDetail) references userdetail on delete cascade on update cascade ,
	primary key(id)
);

create table if not exists article_newsletter (
	newsletter bigint not null,
	article bigint not null,
	foreign key(newsletter) references newsletter on delete cascade on update cascade,
	foreign key(article) references article on delete cascade on update cascade,
	primary key(newsletter, article)
);

create table if not exists subscription (
	newsletter bigint not null,
	userDetail bigint not null,
	foreign key(newsletter) references newsletter on delete cascade on update cascade,
	foreign key(userDetail) references userdetail on delete cascade on update cascade,
	primary key(newsletter, userDetail)
);

create table if not exists article_liked (
	article bigint not null,
	userDetail bigint not null,
	foreign key(article) references article on delete cascade on update cascade,
	foreign key(userDetail) references userdetail on delete cascade on update cascade,
	primary key(article, userDetail)
);


drop table userdetail ;

drop table newsletter;

drop table article ;

drop table article_newsletter ;

drop table subscription;


select * from userdetail ;

select * from article a ;

select * from newsletter n;

select * from subscription s;

select * from article_newsletter an ;

select * from article_liked ;

```