package pl.ski.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    public void setIUserRepository(IUserRepository IUserRepository) {
        this.iUserRepository = IUserRepository;
    }

    @GetMapping
    List<User> getAllUser(){
        return (List<User>) iUserRepository.findAll();
    }

    @GetMapping("/user/{id}")
    List<User> getAllUserWithOutCurrentUser(@PathVariable("id") Long id){
        return iUserRepository.findAllByIdNot(id);
    }

    @GetMapping("/email/{email}/password/{password}")
    User getUserByeMailAndPassword(@PathVariable("email") String eMaile, @PathVariable("password") String password){
        return iUserRepository.findByeMailAndPassword(eMaile, password);
    }

    @GetMapping("/email/{email}")
    Boolean isUserByEMail(@PathVariable("email") String eMaile){
        return iUserRepository.existsUserByeMail(eMaile);
    }

    @PostMapping
    private User addUser(@RequestBody User user){
        return iUserRepository.save(user);
    }

    @PutMapping
    private User updateUser(@RequestBody User user){
        return iUserRepository.save(user);
    }

    @DeleteMapping
    private User deleteUser(@RequestBody User user){
        iUserRepository.delete(user);
        return user;
    }

}
