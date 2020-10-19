package uz.demo.app.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public String fetchApplications(@ModelAttribute("applicationStatus") String applicationStatus, Model model) {
        if (!applicationStatus.isEmpty()) {
            model.addAttribute("applicationStatus", applicationStatus);
        }
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications";
    }

    @GetMapping(path="/new")
    public String newApplication(Model model) {
        model.addAttribute("application", new ApplicationDTO());
        return "application.form";
    }

    @PostMapping(path = "/create")
    public ModelAndView createApplication(@RequestParam(name = "id", required = false) Long id,
                                          @RequestParam(name = "title") String title,
                                          @RequestParam(name = "description") String description) {
        String applicationStatus = applicationService.createOrUpdate(id, title, description);
        ModelAndView m = new ModelAndView("redirect:/application/all");
        m.addObject("applicationStatus", applicationStatus);
        return m;
    }

}
