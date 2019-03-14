package org.select.entitiy;

public class Enrollment {
    public Long enrollmentId;
    public Long userId;
    public Long projectId;
    public Project project;
    public User user;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Enrollment() {
    }

    public Enrollment(Long enrollmentId, Long userId, Long projectId) {
        this.enrollmentId = enrollmentId;
        this.userId = userId;
        this.projectId = projectId;
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
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", project=" + project +
                ", user=" + user +
                '}';
    }
}
