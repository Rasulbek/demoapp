package uz.demo.app.demo.service;

import org.springframework.stereotype.Service;
import uz.demo.app.demo.service.dto.ApplicationDTO;
import uz.demo.app.demo.service.repository.ApplicationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(ApplicationDTO::new)
                .collect(Collectors.toList());
    }
}
