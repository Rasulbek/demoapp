package uz.demo.app.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.app.demo.model.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findFirstByIdAndAuthorId(Long id, Long authorId);

    Optional<Application> findFirstByIdAndStatusNotLike(Long id, String exceptStatus);

    Optional<List<Application>> findAllByAuthorId(Long authorId);

}