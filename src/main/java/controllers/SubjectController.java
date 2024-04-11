package controllers;


import lombok.RequiredArgsConstructor;
import models.Subject;
import services.SubjectService;

@Controller
@RequiredArgsConstructor
public class SubjectController<Model> {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.listSubjects());
        return "subjects";
    }

    @PostMapping("/subjects")
    public String createSubject(Subject subject) {
        subjectService.addSubject(subject);
        return "redirect:/subjects";
    }
}

