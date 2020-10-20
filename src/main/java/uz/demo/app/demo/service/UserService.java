package uz.demo.app.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.security.SecurityUtils;
import uz.demo.app.demo.service.dto.UserDTO;
import uz.demo.app.demo.service.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
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
}
