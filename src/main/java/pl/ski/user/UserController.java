package pl.ski.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{email}/{password}")
    User getUser(@PathVariable("email") String eMaile, @PathVariable("password") String password){
        return userRepository.findByEMailAndPassword(eMaile, password);
    }

    @PostMapping
    private User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping
    private User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping
    private User deleteUser(@RequestBody User user){
        userRepository.delete(user);
        return user;
    }

}
