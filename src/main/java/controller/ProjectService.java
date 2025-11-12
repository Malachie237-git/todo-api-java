package com.projetapi.service;

import com.projetapi.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final String DB_FILE = "db.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Project> getAllProjects() {
        try {
            File file = new File(DB_FILE);
            if (!file.exists()) return new ArrayList<>();
            return List.of(mapper.readValue(file, Project[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Optional<Project> getProjectById(int id) {
        return getAllProjects().stream().filter(p -> p.getId() == id).findFirst();
    }

    public Project addProject(Project project) {
        List<Project> projects = new ArrayList<>(getAllProjects());
        int newId = projects.size() > 0 ? projects.get(projects.size() - 1).getId() + 1 : 1;
        project.setId(newId);
        projects.add(project);
        saveToFile(projects);
        return project;
    }

    public Project updateGrade(int id, int grade) {
        List<Project> projects = new ArrayList<>(getAllProjects());
        for (Project p : projects) {
            if (p.getId() == id) {
                p.setGrade(grade);
                saveToFile(projects);
                return p;
            }
        }
        return null;
    }

    private void saveToFile(List<Project> projects) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE), projects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
