package com.projetapi.controller;

import com.projetapi.model.Project;
import com.projetapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // POST /projects
    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    // GET /projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET /projects/{id}
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable int id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.orElse(null);
    }

    // PUT /projects/{id}/grade
    @PutMapping("/{id}/grade")
    public Project updateGrade(@PathVariable int id, @RequestBody Map<String, Integer> body) {
        int grade = body.get("grade");
        return projectService.updateGrade(id, grade);
    }
}
