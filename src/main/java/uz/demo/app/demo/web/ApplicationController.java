package uz.demo.app.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.demo.app.demo.service.ApplicationService;
import uz.demo.app.demo.service.dto.ApplicationDTO;

@Controller
@RequestMapping(path="/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(path="/all")
    public String fetchApplications(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications";
    }

    @GetMapping(path="/new")
    public String newApplication(Model model) {
        model.addAttribute("application", new ApplicationDTO());
        return "application.form";
    }

    @PostMapping(path = "/create")
    public String createApplication(@RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "title") String title,
                                    @RequestParam(name = "description") String description,
                                    Model model) {
        String applicationStatus = applicationService.createOrUpdate(id, title, description);
        model.addAttribute("applicationStatus", applicationStatus);
        return "applications";
    }

}
