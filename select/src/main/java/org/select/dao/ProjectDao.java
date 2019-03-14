package org.select.dao;


import org.select.entitiy.Project;

import java.util.List;

public interface ProjectDao {

    List<Project> listAllProjects();
    int reduceStock(Long projectId);
    int getStock(Long projectId);
    Project selectProjectById(Long projectId);
    int insertWithoutId(Project project);
    Project getProjectById(Long id);
    int deleteProjectById(Long id);
    int updateProjectById(Project project);
    int increaseStock(Long projectId);
}
