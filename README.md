# noTouchOnboarding
Hackathon 2018

## To run
mvn clean package  
java -jar target/notouch-onboarding-0.1.0.jar

## End points

#### UI pages
http://localhost:8080/welcome  
http://localhost:8080/getstatus

####
http://localhost:8080/status?org=ORG  
Dockertoken is optional  
http://localhost:8080/requestJenkins?org=ORG&gitToken=1234qwerty&dockerToken=1234DOCKER
