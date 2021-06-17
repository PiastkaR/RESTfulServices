package com.restfulservices.rest.resource;

import com.restfulservices.rest.domain.Post;
import com.restfulservices.rest.domain.User;
import com.restfulservices.rest.repository.JpaUserRepository;
import com.restfulservices.rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.UnknownServiceException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class JpaUserResource {
    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers from link
        EntityModel<User> resource = EntityModel.of(user.get()); //moge dac Resource zwykly
        WebMvcLinkBuilder linkToAllUsers =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder linkToDeleteUser =
                linkTo(methodOn(this.getClass()).deleteUser(id));
        WebMvcLinkBuilder linkToAddUser =
                linkTo(methodOn(this.getClass()).addUser(user.get()));

        resource.add(linkToAllUsers.withRel("all-users"));
        resource.add(linkToDeleteUser.withRel("delete-user"));
        resource.add(linkToAddUser.withRel("add-user"));

        //HATEOAS
        return resource;
    }

    //Returns status and created URI (location)
    @PostMapping("/jpa/users/create")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public Class<?> deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return null;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) throws UnknownServiceException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new UnknownServiceException();

        return optionalUser.get().getPosts();
    }

    @GetMapping("/jpa/user/{id}/post")
    public ResponseEntity<Post> retrieveAllPostsForUser(@PathVariable int id, @RequestBody Post post) throws UnknownServiceException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new UnknownServiceException();

        User user = optionalUser.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
