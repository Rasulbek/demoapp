package uz.demo.app.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.app.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}