package uz.demo.app.demo.service;

import org.springframework.stereotype.Service;
import uz.demo.app.demo.Constants;
import uz.demo.app.demo.model.Application;
import uz.demo.app.demo.model.User;
import uz.demo.app.demo.service.dto.ApplicationDTO;
import uz.demo.app.demo.service.repository.ApplicationRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final UserService userService;

    public ApplicationService(ApplicationRepository applicationRepository, UserService userService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
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
    public void deleteById(Long id) {
        ApplicationDTO applicationDTO = getApplicationDTOById(id);
        if (userService.getCurrentUserId().equals(applicationDTO.getAuthorId())) {
            applicationRepository.findById(id).ifPresent(application -> application.setStatus(Constants.DELETED));
        }
    }
}
