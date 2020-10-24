package uz.demo.app.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.demo.app.demo.service.ApplicationService;
import uz.demo.app.demo.service.CommentService;
import uz.demo.app.demo.service.UserService;
import uz.demo.app.demo.service.dto.UserDTO;
import uz.demo.app.demo.service.vm.UserVM;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@Secured({})
@Validated
@RequestMapping(path = "/api")
public class ApiController {

    private final UserService userService;

    private final ApplicationService applicationService;

    private final CommentService commentService;

    public ApiController(UserService userService, ApplicationService applicationService, CommentService commentService) {
        this.userService = userService;
        this.applicationService = applicationService;
        this.commentService = commentService;
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody(required = false) UserVM newUser) {
        try {
            Long userId = userService.registerAndGetToken(newUser);
            return ResponseEntity.ok(Collections.singletonMap("userId", userId));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
