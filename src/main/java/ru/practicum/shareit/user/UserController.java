package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.debug("{}", user);
        return service.add(user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") Long id) {
        log.debug("{}", id);
        return service.delete(id);

    }

    @PatchMapping("/{id}")
    public User patch(@RequestBody User user, @PathVariable("id") Long id) {
        log.debug("{}", user);
        return service.patch(user, id);

    }

    @GetMapping("/users")
    public Collection<User> users() {
        log.debug("");
        return service.users();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        log.debug("{}", id);
        return service.getUser(id);
    }
}
