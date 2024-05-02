FROM tomcat:9.0.74-jre17
COPY target/ROOT.war /usr/local/tomcat/webapps/