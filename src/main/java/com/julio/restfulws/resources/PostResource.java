package com.julio.restfulws.resources;

import com.julio.restfulws.entities.Post;
import com.julio.restfulws.services.PostService;
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
@RequestMapping(value = "/users/{userId}/posts", headers = "X-API-VERSION=1")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    public List<Post> findAllByUserId(@PathVariable Long userId) {
        return service.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public Resource<Post> findOne(@PathVariable Long userId, @PathVariable Long id) {
        //Implements HATEOAS in the response to return the link that has all users

        Post post = service.findOne(userId, id);
        Resource<Post> resource = new Resource<>(post);
        ControllerLinkBuilder all = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllByUserId(userId));
        ControllerLinkBuilder self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findOne(userId, id));
        resource.add(all.withRel(String.format("all-posts-from-user-%s", userId)));
        resource.add(self.withRel("self"));
        return resource;
    }

    @PostMapping
    public ResponseEntity insert(@PathVariable Long userId, @Valid @RequestBody Post post) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(service.save(post, userId).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        service.deleteById(id, userId);
    }


}
