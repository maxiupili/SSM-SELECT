package org.select.dao;

import org.select.entitiy.Enrollment;

import java.util.List;

public interface EnrollmentDao {
    int insertEnrollment(Enrollment enrollment);
    Long selectProjectIdByUserId(Long userId);
    int deleteByUserId(Long enrollmentId);
    List<Enrollment> selectWithProjectAndUser();
}
