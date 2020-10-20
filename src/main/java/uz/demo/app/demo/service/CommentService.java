package uz.demo.app.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.demo.app.demo.model.Application;
import uz.demo.app.demo.model.Comment;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.service.dto.CommentDTO;
import uz.demo.app.demo.service.repository.CommentRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> getAllCommentsOfApplication(Long applicationId) {
        log.debug("CommentService getAllCommentsOfApplication by ID: {}", applicationId);
        return commentRepository.findAllByApplicationIdOrderByCreatedDateDesc(applicationId)
                .stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }


    public void addCommentToApplication(Long applicationId, String comment) {
        User author = new User();
        author.setId(userService.getCurrentUserId());
        Application application = new Application();
        application.setId(applicationId);

        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setAuthor(author);
        newComment.setApplication(application);
        newComment.setCreatedDate(ZonedDateTime.now());
        commentRepository.save(newComment);
    }

}
