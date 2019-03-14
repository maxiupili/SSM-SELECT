package org.select.service.impl;

import org.select.dao.EnrollmentDao;
import org.select.dao.ProjectDao;
import org.select.dto.EnrollResult;
import org.select.entitiy.Enrollment;
import org.select.entitiy.Project;
import org.select.exception.RepeatSelectException;
import org.select.exception.SelectException;
import org.select.exception.StockException;
import org.select.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnrollmentDao enrollmentDao;
    @Autowired
    private ProjectDao projectDao;

    @Transactional
    public EnrollResult enroll(Enrollment enrollment) throws RepeatSelectException, StockException, SelectException {
        try {
            int enrollCount = enrollmentDao.insertEnrollment(enrollment);
            if (enrollCount <= 0) {
                throw new SelectException("选题失败");
            } else {
                int updateCount = projectDao.reduceStock(enrollment.getProjectId());
                if (updateCount <= 0) {
                    throw new StockException("库存不足");
                } else
                    return new EnrollResult(true, enrollment.getEnrollmentId(), enrollment.getUserId(), enrollment.getProjectId());

            }
        } catch (StockException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SelectException("select inner error:" + e.getMessage());
        }
    }

    @Transactional
    public int deleteEnrollmentByUserId(Long userId) {
        Long projectId = enrollmentDao.selectProjectIdByUserId(userId);
        System.out.println("要减去库存的projectId"+projectId);
        projectDao.increaseStock(projectId);
        return enrollmentDao.deleteByUserId(userId);
    }

    public Project getProjectByUserId(Long userId) {
        Long projectId = enrollmentDao.selectProjectIdByUserId(userId);
        Project project = projectDao.selectProjectById(projectId);
        return project;
    }

    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentDao.selectWithProjectAndUser();
        return enrollments;
    }
}
