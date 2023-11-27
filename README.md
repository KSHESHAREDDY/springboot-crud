Requirements :
    Java version : 17
    Spring Boot  : 3.0+

Spring Modules Covered :
    Spring boot Web
    Spring boot Security
    Spring data Jpa

Spring Modules Will Covered in the future :
    Spring boot Actuator
    Spring boot AOP

TO Run Backend Application :
    On Initial Run (mvn install) on your IDE Selected Build Goals.
    Next Run (mvn clean spring-boot:run)

DataBase Related:
    Update your Database details on application.properties based on your Db Server.

Spring Security :
    Annotate SecurityConfig file with @EnableWebSecurity So Spring boot will initialize security related beans behalf us
    if we use @PreAuthorize("") in any controller we need add @EnableWebSecurity annotation SecurityConfig Class So 
    That Spring Boot will Understand Service Are Protected with Roles.
    
    hasAuthority('ROLE_ADMIN') means the User is Associated with ADMIN Role or not. ROLE_ prefix is mandatory while Useing
    hasAuthority().
    
    hasRole('ADMIN') we don't need to add ROLE_ prefix.
    

