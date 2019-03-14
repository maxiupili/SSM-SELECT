package org.select.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.entitiy.Enrollment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-dao.xml"})
public class EnrollmentDaoTest {
    @Resource
    private EnrollmentDao enrollmentDao;
    @Test
    public void insertEnrollment() throws Exception {
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(1001L);
        enrollment.setProjectId(10001L);
        int enrollmentInserted = enrollmentDao.insertEnrollment(enrollment);
        System.out.println(enrollment);
    }
    @Test
    public void selectWithProjectAndUser(){
        List<Enrollment> enrollmentList = enrollmentDao.selectWithProjectAndUser();
        System.out.println("all enrollment:"+enrollmentList);
    }

}