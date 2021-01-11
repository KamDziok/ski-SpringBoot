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

    @GetMapping("/user/{id}")
    List<User> getAllUserWithOutCurrentUser(@PathVariable("id") Long id){
        List<User> userListAll = (List<User>) userRepository.findAll();
        List<User> result = new ArrayList<>();
        userListAll.forEach(user -> {
            if(user.getId().intValue() != id.intValue()){
                result.add(user);
            }
        });
        return result;
    }

    @GetMapping("/email/{email}/password/{password}")
    User getUserByeMailAndPassword(@PathVariable("email") String eMaile, @PathVariable("password") String password){
        return userRepository.findByeMailAndPassword(eMaile, password);
    }

    @GetMapping("/email/{email}")
    Boolean isUserByEMail(@PathVariable("email") String eMaile){
        if(!userRepository.findByeMail(eMaile).equals(null)) {
            return true;
        }
        return false;
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
