package org.select.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.entitiy.Project;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-dao.xml"})
public class ProjectDaoTest {
    @Resource
    ProjectDao projectDao;

    @Test
    public void listAllProjects() throws Exception {
        List<Project> projects = projectDao.listAllProjects();
        for(Project project:projects)
            System.out.println(project);
    }

    @Test
    public void reduceStock() throws Exception {
        Long id = 10000L;
        int countReduce = projectDao.reduceStock(id);
        System.out.println("countReduce"+countReduce);
    }
    @Test
    public void insertWithoutId() throws Exception{
        Project project = new Project("测试项目","测试描述",1);
        int countInsert = projectDao.insertWithoutId(project);
        System.out.println("countInsert"+countInsert);
    }

    @Test
    public void increaseStock() throws Exception{
        Long id = 10001L;
        int increaseCount = projectDao.increaseStock(id);
        System.out.println("increaseCount"+increaseCount);
    }
}