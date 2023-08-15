# Maven build container
FROM maven:3.8.7-eclipse-temurin-17-alpine AS maven_build

RUN rm -r /tmp/

COPY pom.xml /tmp/
COPY src /tmp/src/

WORKDIR /tmp/
RUN mkdir -p /root/.m2 && ln -s ~\.m2 /root/.m2 && mvn clean package


# Use the official JBoss WildFly image as the base image
FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17

# Set the working directory to the WildFly home directory
WORKDIR /opt/jboss/wildfly

# Copy the PostgreSQL JDBC driver to the WildFly modules directory
COPY --chown=jboss:jboss postgresql-42.6.0.jar /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/

# Create a new module for the PostgreSQL JDBC driver
RUN echo "<module xmlns=\"urn:jboss:module:1.5\" name=\"org.postgresql\">\n    <resources>\n        <resource-root path=\"postgresql-42.6.0.jar\"/>\n    </resources>\n    <dependencies>\n        <module name=\"javax.api\"/>\n        <module name=\"javax.transaction.api\"/>\n    </dependencies>\n</module>" > /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/module.xml

# Copy your WAR file to the WildFly deployments directory
#COPY --chown=jboss:jboss target/sigenu-ead.war /opt/jboss/wildfly/standalone/deployments/
COPY --from=maven_build --chown=jboss:jboss /tmp/target/sigenu-ead.war /opt/jboss/wildfly/standalone/deployments/

# Expose the HTTP and HTTPS ports
EXPOSE 8080 8443 9990

# Start WildFly in standalone mode
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]