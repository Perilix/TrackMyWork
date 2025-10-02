package com.timetracker.service;

import com.timetracker.entity.Project;
import com.timetracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByUpdatedAtDesc();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }

    @Transactional
    public Project createProject(Project project) {
        if (project.getColor() == null || project.getColor().isEmpty()) {
            project.setColor("#00d4ff");
        }
        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);

        if (projectDetails.getName() != null) {
            project.setName(projectDetails.getName());
        }
        if (projectDetails.getClient() != null) {
            project.setClient(projectDetails.getClient());
        }
        if (projectDetails.getColor() != null) {
            project.setColor(projectDetails.getColor());
        }
        if (projectDetails.getIsActive() != null) {
            project.setIsActive(projectDetails.getIsActive());
        }

        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}