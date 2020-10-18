package uz.demo.app.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.app.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}