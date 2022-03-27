# SpringBoot based microservice template

1. REST service:
   - Sample REST controller
   - OPEN API Swagger for API documentation
  
2. Logging :
   - aspect based logging enabled for before and after method execution and exceptions 
   - logback file rolling appender
   - Log streaming to Logstash using LogstashTcpSocketAppender
   - Actuator for dynamic log level control

3. Automated getter/setter/constructor
   - project lombok implementation in sample model class

4. Central Config dependencies from Spring cloud config server
   - Single config repo to manage config for multiple microservices - https://github.com/sau2382/ConfigRepo
   - Retry to fetch config in case config server is down temporarily  
