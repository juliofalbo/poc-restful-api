# POC RESTFUL API

##### POC of a Restful API including standards such as HATEOAS and implementing annotations and Spring AOP

#

## Richardson Maturity Model

#### Level 0
Expose SOAP Web Services in REST style
    
    http://server/getUsers
    http://server/deleteUser
    http://server/doSomethig
    
#### Level 1
Expose Resources with URI, although with the **wrong HTTP methods**
    
    http://server/users
    http://server/users/1
    
#### Level 2
Expose Resources with URI by **correctly using HTTP methods**
    
    GET http://server/users
    POST http://server/users
    PUT http://server/users/1
    PATCH http://server/users/1
    DELETE http://server/users/1
    
#### Level 3
Expose resources with URI using HTTP methods correctly applying the **HATEOAS** concept
    
    GET http://server/users/1
    Response:
    {
      "name": "Julio Falbo",
      "_links": {
        "self": {
          "href": "http://server/users/1"
        },
        "all-user": {
          "href": "http://server/users"
        },
        "posts": {
          "href": "http://server/users/10001/posts"
        }
      }
    }
   
    

    
##### References:
 - [Master Microservices with Spring Boot and Spring Cloud - in28Minutes](https://www.udemy.com/microservices-with-spring-boot-and-spring-cloud/)
 - [Richardson Maturity Model - Martin Fowler](https://martinfowler.com/articles/richardsonMaturityModel.html)
 - [Understanding HATEOAS](https://spring.io/understanding/HATEOAS)


By [Júlio Falbo](http://juliofalbo.tech)