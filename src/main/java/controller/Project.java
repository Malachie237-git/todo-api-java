package com.projetapi.model;

public class Project {
    private int id;
    private String studentName;
    private String course;
    private String githubUrl;
    private Integer grade; // Integer pour pouvoir être null au début

    // Constructeurs
    public Project() {}
    
    public Project(int id, String studentName, String course, String githubUrl) {
        this.id = id;
        this.studentName = studentName;
        this.course = course;
        this.githubUrl = githubUrl;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }
}
