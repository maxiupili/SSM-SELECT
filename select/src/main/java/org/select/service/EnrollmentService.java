package org.select.service;

import org.select.dto.EnrollResult;
import org.select.entitiy.Enrollment;
import org.select.entitiy.Project;
import org.select.exception.RepeatSelectException;
import org.select.exception.SelectException;
import org.select.exception.StockException;

import java.util.List;

public interface EnrollmentService {
    public EnrollResult enroll (Enrollment enrollment) throws RepeatSelectException,StockException,SelectException;
    public int deleteEnrollmentByUserId(Long userId);
    public Project getProjectByUserId(Long userId);
    public List<Enrollment> getAllEnrollments();
}
