FROM openjdk:8u171-jdk-alpine3.8
MAINTAINER Arun Johnson <arun.j@thinkpalm.com>
RUN mkdir testassured
RUN apk --update add fontconfig ttf-dejavu

ADD target/restassured.jar restassured.jar
ADD testng-6.11.jar /
ADD jcommander-1.72.jar /
ADD MailCredentials.xlsx /
ADD IssueReport.jrxml /
ADD testng.xml /
ENTRYPOINT ["java", "-cp", ".:*", "com.test.AtoBeMainClass"]
#CMD exec java -cp ./* org.testng.TestNG -testclass com.test.SampleProject
#RUN java -cp "" org.testng.TestNG -testclass com.test.SampleProject
#CMD exec java -jar RestAssuredServicesTestProject-0.0.1-SNAPSHOT.jar
EXPOSE 8081
