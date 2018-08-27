package com.julio.restfulws.resources;

import com.julio.restfulws.entities.User;
import com.julio.restfulws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users", headers = "X-API-VERSION=1")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Resource<User> findOne(@PathVariable Long id){
        //Implements HATEOAS in the response to return the link that has all users

        User user = service.findOne(id);
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder all = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());
        ControllerLinkBuilder self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findOne(id));
        resource.add(all.withRel("all-users"));
        resource.add(self.withRel("self"));
        return resource;
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody User user){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(service.save(user).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }


}
