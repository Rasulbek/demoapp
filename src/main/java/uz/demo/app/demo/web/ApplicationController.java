package uz.demo.app.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.demo.app.demo.service.ApplicationService;
import uz.demo.app.demo.service.CommentService;
import uz.demo.app.demo.service.dto.ApplicationDTO;
import uz.demo.app.demo.service.dto.CommentDTO;

import java.util.List;

@Controller
@RequestMapping(path="/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final CommentService commentService;

    public ApplicationController(ApplicationService applicationService, CommentService commentService) {
        this.applicationService = applicationService;
        this.commentService = commentService;
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

    @GetMapping(path = "/{id}")
    public String getApplication(@PathVariable(name = "id") Long id, Model model) {
        ApplicationDTO applicationDTO = applicationService.getApplicationDTOById(id);
        List<CommentDTO> commentDTOList = commentService.getAllCommentsOfApplication(id);
        model.addAttribute("zayavka", applicationDTO);
        model.addAttribute("comments", commentDTOList);
        return "application.detail";
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

    @PostMapping(path = "/add-comment")
    public ModelAndView addComment(@RequestParam("application") Long applicationId,
                                   @RequestParam("comment") String comment) {
        commentService.addCommentToApplication(applicationId, comment);

        return new ModelAndView("redirect:/application/" + applicationId);
    }

}
