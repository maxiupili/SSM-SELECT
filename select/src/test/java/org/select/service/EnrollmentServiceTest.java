package org.select.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.dto.EnrollResult;
import org.select.entitiy.Enrollment;
import org.select.entitiy.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class EnrollmentServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    public void enrollTest() throws Exception{
        Enrollment enrollment = new Enrollment();
        enrollment.setProjectId(10001L);
        enrollment.setUserId(1001L);
        EnrollResult enrollResult = enrollmentService.enroll(enrollment);
        System.out.println(enrollment);
        System.out.println(enrollResult);
    }
    @Test
    public void deleteEnrollment() throws Exception{
        int deleteCount = enrollmentService.deleteEnrollmentByUserId(1001L);
        System.out.println("删除的选题数量："+deleteCount);
    }
    @Test
    public void getProjectByUserId() throws Exception{
        Long userId = 1001L;
        Project project = enrollmentService.getProjectByUserId(userId);
        System.out.println("选中的项目"+project);
    }
    @Test
    public void getAllEnrollments() throws  Exception{
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        System.out.println("enrollments:"+enrollments);
    }

}