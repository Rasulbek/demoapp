package uz.demo.app.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.security.SecurityUtils;
import uz.demo.app.demo.service.dto.UserDTO;
import uz.demo.app.demo.service.repository.UserRepository;
import uz.demo.app.demo.service.vm.UserVM;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public Long save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        return userRepository.save(user).getId();
    }

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserLogin()
                .map(username -> userRepository.findByUsername(username)
                        .map(User::getId)
                        .orElse(-1L))
                .orElse(-1L);
    }

    public Long registerAndGetToken(UserVM userVM) throws IllegalAccessException {
        if (userRepository.findByUsername(userVM.getUsername()).isPresent()) {
            throw new IllegalAccessException("User name already exists");
        }
        User user = new User();
        user.setUsername(userVM.getUsername());
        user.setPassword(passwordEncoder.encode(userVM.getPassword()));
        user.setFirstName(userVM.getFirstName());
        user.setLastName(userVM.getLastName());
        user.setBirthday(userVM.getBirthday());
        user.setAddress(userVM.getAddress());
        user.setCreatedDate(ZonedDateTime.now());
        user = userRepository.save(user);
        if (user.getId() != null) {
            return user.getId();
        } else {
            throw new IllegalAccessException("Can't register a new user");
        }
    }

    public UserDTO getById(Long id) throws Exception {
        return userRepository.findById(id).map(UserDTO::new).orElseThrow(Exception::new);
    }

    @Transactional
    public Map<String, String> update(UserVM userVM) throws IllegalArgumentException {
        String currentUser = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new IllegalArgumentException("You are not authorized"));
        User user = userRepository.findByUsername(userVM.getUsername()).orElseThrow(() -> new IllegalArgumentException("User: " + userVM.getUsername() + " not found"));
        if (currentUser.equals(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(userVM.getPassword()));
            user.setFirstName(userVM.getFirstName());
            user.setLastName(userVM.getLastName());
            user.setBirthday(userVM.getBirthday());
            user.setAddress(userVM.getAddress());
            return Collections.singletonMap("status", "updated");
        } else {
            throw new IllegalArgumentException("You can update only your profile");
        }
    }
}
