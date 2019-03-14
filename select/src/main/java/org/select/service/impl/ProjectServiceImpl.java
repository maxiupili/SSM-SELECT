package org.select.service.impl;

import org.select.dao.ProjectDao;
import org.select.entitiy.Project;
import org.select.exception.ProjectUpdateException;
import org.select.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getProjectList() {
        return projectDao.listAllProjects();
    }
    @Transactional
    public void saveProject(Project project) {
        projectDao.insertWithoutId(project);
    }
    public Project getProject(Long id){
        return projectDao.getProjectById(id);
    }
    public int deleteProject(Long projectId){
        int deleteCount = projectDao.deleteProjectById(projectId);
        return deleteCount;
    }
    public int updateProject(Project project) throws ProjectUpdateException {
        int updateCount;
        try {
            updateCount = projectDao.updateProjectById(project);
            } catch(Exception e){
            throw new ProjectUpdateException(e.getMessage());
        }
        return updateCount;
    }
}
