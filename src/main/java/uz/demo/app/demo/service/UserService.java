package uz.demo.app.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.security.SecurityUtils;
import uz.demo.app.demo.service.dto.UserDTO;
import uz.demo.app.demo.service.repository.UserRepository;

import java.util.List;
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
}
