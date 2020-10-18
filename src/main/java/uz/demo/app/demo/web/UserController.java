package uz.demo.app.demo.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.security.AuthoritiesConstants;
import uz.demo.app.demo.service.UserService;
import uz.demo.app.demo.service.dto.UserDTO;

@Controller
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Secured({AuthoritiesConstants.ADMIN})
    @PostMapping(path = "/create")
    public @ResponseBody Long saveUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

}
