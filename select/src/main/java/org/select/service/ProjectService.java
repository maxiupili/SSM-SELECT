package org.select.service;

import org.select.entitiy.Project;
import org.select.exception.ProjectUpdateException;

import java.util.List;

public interface ProjectService {
    public List<Project> getProjectList();
    public void saveProject(Project project);
    public Project getProject(Long id);
    public int deleteProject(Long id);
    public int updateProject(Project project) throws ProjectUpdateException;
}
