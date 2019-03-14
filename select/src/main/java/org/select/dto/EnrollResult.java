package org.select.dto;

public class EnrollResult {
    private Boolean enrolled;
    private Long enrollmentId;
    private Long userId;
    private Long projectId;

    public EnrollResult(Boolean enrolled) {
        this.enrolled = enrolled;
    }

    public EnrollResult(Boolean enrolled, Long enrollmentId, Long userId, Long projectId) {
        this.enrolled = enrolled;
        this.enrollmentId = enrollmentId;
        this.userId = userId;
        this.projectId = projectId;
    }

    public Boolean getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Boolean enrolled) {
        this.enrolled = enrolled;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "EnrollResult{" +
                "enrolled=" + enrolled +
                ", enrollmentId=" + enrollmentId +
                ", userId=" + userId +
                ", projectId=" + projectId +
                '}';
    }
}
