package uz.demo.app.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.demo.app.demo.service.dto.CommentDTO;
import uz.demo.app.demo.service.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getAllCommentsOfApplication(Long applicationId) {
        log.debug("CommentService getAllCommentsOfApplication by ID: {}", applicationId);
        return commentRepository.findAllByApplicationIdOrderByCreatedDateDesc(applicationId)
                .stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

}
