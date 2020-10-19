package uz.demo.app.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

}
