package uz.demo.app.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.demo.app.demo.service.ApplicationService;
import uz.demo.app.demo.service.CommentService;
import uz.demo.app.demo.service.UserService;
import uz.demo.app.demo.service.dto.ApplicationDTO;
import uz.demo.app.demo.service.dto.UserDTO;
import uz.demo.app.demo.service.vm.ApplicationVM;
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

    //APIs for users

    @GetMapping(path = "/users", name = "Получения списки пользователей")
    public @ResponseBody
    List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/users/{id}", name = "Получения данных о пользователя по ИД номеру")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", String.format("User by ID: %d not found", id)));
        }
    }

    @PostMapping(path = "/users/register", name = "Регистрация нового пользователя")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserVM newUser) {
        try {
            Long userId = userService.registerAndGetToken(newUser);
            return ResponseEntity.ok(Collections.singletonMap("userId", userId));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PutMapping(path = "/users/update", name = "Изменения данных пользователя")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserVM userVM) {
        try {
            return ResponseEntity.ok(userService.update(userVM));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    //APIs for Applications

    @GetMapping(path = "/applications", name = "Получения списки заявок")
    public @ResponseBody
    List<ApplicationDTO> getApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping(path = "/applications/{id}", name = "Получения заявки по ИД номеру")
    public ResponseEntity<?> getApplicationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(applicationService.getApplicationWithComments(id));
        } catch (IllegalAccessError e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", String.format("Application by ID: %d not found", id)));
        }
    }

    @PostMapping(path = "/applications", name = "Создания или изменения заявки")
    public ResponseEntity<?> createApplication(@Valid @RequestBody ApplicationVM applicationVM) {
        String status = applicationService.createOrUpdate(applicationVM.getId(), applicationVM.getTitle(), applicationVM.getDescription());
        if ("success".equals(status) || "updated".equals(status)) {
            return ResponseEntity.ok(Collections.singletonMap("status", status));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", status));
        }
    }

    @DeleteMapping(path = "/application/{id}/delete", name = "Удаления заявки по ИД")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(applicationService.deleteById(id));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
