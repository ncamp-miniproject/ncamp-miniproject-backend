TARGET_FILE=ncamp_miniproject.war
rm $TOMCAT_HOME/webapps/$TARGET_FILE;
mvn clean package -Dmaven.test.skip &&
mv target/*.war $TOMCAT_HOME/webapps &&
sh $TOMCAT_HOME/bin/catalina.sh run