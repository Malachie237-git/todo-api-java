package com.projetapi.model;

public class Project {

    private int id;
    private String studentName;
    private String course;
    private String githubUrl;
    private int grade; // facultatif au départ

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
}
