package uz.demo.app.demo.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import uz.demo.app.demo.Constants;
import uz.demo.app.demo.model.Application;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.security.SecurityUtils;
import uz.demo.app.demo.service.dto.ApplicationDTO;
import uz.demo.app.demo.service.dto.CommentDTO;
import uz.demo.app.demo.service.repository.ApplicationRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final UserService userService;

    private final CommentService commentService;

    public ApplicationService(ApplicationRepository applicationRepository, UserService userService, CommentService commentService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
        this.commentService = commentService;
    }

    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate()))
                .filter(Application::notDeleted)
                .map(ApplicationDTO::new)
                .collect(Collectors.toList());
    }

    public ApplicationDTO getApplicationDTOById(Long id) {
        return applicationRepository.findById(id)
                .filter(Application::notDeleted)
                .map(ApplicationDTO::new)
                .orElseThrow(IllegalAccessError::new);
    }

    public ApplicationDTO getApplicationWithComments(Long id) {
        ApplicationDTO applicationDTO = applicationRepository.findById(id)
                .filter(Application::notDeleted)
                .map(ApplicationDTO::new)
                .orElseThrow(IllegalAccessError::new);

        List<CommentDTO> comments = commentService.getAllCommentsOfApplication(id);
        applicationDTO.setComments(comments);
        return applicationDTO;
    }

    @Transactional
    public String createOrUpdate(Long id, String title, String description) {
        Long userId = userService.getCurrentUserId();
        if (userId == -1L) {
            return "not.authorized";
        }

        if (id != null) {
            Optional<Application> application = applicationRepository.findFirstByIdAndAuthorId(id, userId);
            if (application.isPresent()) {
                Application modifyingApplication = application.get();
                modifyingApplication.setTitle(title);
                modifyingApplication.setDescription(description);
                applicationRepository.save(modifyingApplication);
                return "updated";
            } else {
                return "not.owner";
            }
        }
        User author = new User();
        author.setId(userId);
        Application application = new Application();
        application.setAuthor(author);
        application.setTitle(title);
        application.setDescription(description);
        application.setCreatedDate(ZonedDateTime.now());
        application.setStatus(Constants.APPLICATION_PUBLISHED);
        application = applicationRepository.save(application);
        if (application.getId() != null) {
            return "success";
        }
        return "error";
    }

    @Transactional
    public Map<String, String> deleteById(Long id) throws IllegalAccessException {
        Application application = applicationRepository.findFirstByIdAndStatusNotLike(id, Constants.DELETED).orElseThrow(() -> new IllegalAccessException("Application not found"));
        String owner = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new IllegalAccessException("You are not authorized"));
        if (application.getAuthor().getUsername().equals(owner)) {
            application.setStatus(Constants.DELETED);
            return Collections.singletonMap("status", "deleted");
        } else {
            throw new IllegalAccessException("Only owner can delete application");
        }
    }
}
