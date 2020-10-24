package uz.demo.app.demo.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.demo.app.demo.service.ApplicationService;
import uz.demo.app.demo.service.CommentService;
import uz.demo.app.demo.service.UserService;
import uz.demo.app.demo.service.dto.UserDTO;

import java.util.List;

@Controller
@Secured({})
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

}
