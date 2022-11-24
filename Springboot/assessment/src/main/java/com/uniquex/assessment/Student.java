package com.uniquex.assessment;

public class Student {
    private String name;
    private Double grade;

    public Student(String name, Double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public Double getGrade() {
        return this.grade;
    }

    public String toString() {
        return String.format("%s,%f", this.name, this.grade);
    }
}
