package org.select.entitiy;

public class Project {
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private int stock;

    public Project(Long projectId, String projectName, String projectDescription, int stock) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.stock = stock;
    }

    public Project(String projectName, String projectDescription, int stock) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.stock = stock;
    }

    public Project() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", stock=" + stock +
                '}';
    }
}
