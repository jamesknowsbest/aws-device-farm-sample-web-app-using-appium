# aws-device-farm-sample-web-app-using-appium
 This fork of the aws project will go to the Device Farm documentation web page and also to http://speedtest.googlefiber.net/ to check the WiFi connection speed. 

## Building

 First install maven 

 **mac**
 `brew install maven`

 **windows**
 get download here: 
 http://maven.apache.org/download.cgi

Second run the following command in this projects root directory
`mvn clean package -DskipTests=true`

## Running in Device Farm

 Follow the documentation here for scheduling a run
 https://docs.aws.amazon.com/devicefarm/latest/developerguide/how-to-create-test-run.html#how-to-create-test-run-console

 
