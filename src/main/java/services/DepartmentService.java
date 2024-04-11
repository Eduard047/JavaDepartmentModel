package services;

import lombok.RequiredArgsConstructor;
import models.Department;
import models.Subject;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final SubjectService subjectService;

    @GetMapping("/departments/{id}")
    public String departmentInfo(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            model.addAttribute("department", department);
            model.addAttribute("allSubjects", subjectService.listSubjects());
            return "department-info";
        }
        return "redirect:/departments";
    }


    // Оновлений метод для додавання предметів до кафедри
    @PostMapping("/departments/{id}")
    public String addSubjectsToDepartment(@PathVariable Long id, @RequestParam List<Long> subjectIds) {
        departmentService.addSubjectsToDepartment(id, subjectIds);
        return "redirect:/departments/" + id;
    }

    // решта методів…
    @Service
    public class DepartmentService{
        private final List<Department> departments = new ArrayList<>();
        private final List<Subject> subjects = new ArrayList<>();
        private long departmentId = 0;
        private long subjectId = 0;

        public List<Department> listDepartments() {
            return departments;
        }

        // решта методів…


        // Метод для додавання предметів до кафедри
        public void addSubjectsToDepartment(Long departmentId, List<Long> subjectIds) {
            Department department = getDepartmentById(departmentId);
            if (department != null) {
                List<Subject> departmentSubjects = department.getSubjects();
                subjectIds.forEach(id -> subjects.stream()
                        .filter(subject -> subject.getId().equals(id))
                        .findFirst()
                        .ifPresent(departmentSubjects::add));
            }
        }
    }

}

