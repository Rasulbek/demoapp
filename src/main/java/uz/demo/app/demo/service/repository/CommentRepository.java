package uz.demo.app.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.app.demo.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByApplicationIdOrderByCreatedDateDesc(Long applicationId);

}
