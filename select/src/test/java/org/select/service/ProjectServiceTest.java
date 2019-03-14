package org.select.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.entitiy.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ProjectServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectService projectService;
    @Test
    public void getProjectList() throws Exception {
        List<Project> list = projectService.getProjectList();
        System.out.println(list);

    }
    @Test
    public void saveProject() throws  Exception{
        Project project = new Project("测试","测试",1);
         projectService.saveProject(project);
    }
    @Test
    public void getProject() throws  Exception{
        Project project = projectService.getProject(10001L);
        System.out.println(project);
    }
    @Test
    public void updateProject() throws  Exception{
        Project project = new Project();
        project.setProjectId(10015L);
        project.setStock(10000);
        project.setProjectDescription("测试修改描述");
        int updateCount = projectService.updateProject(project);
        System.out.println("更新的项目数量："+updateCount);

    }

}