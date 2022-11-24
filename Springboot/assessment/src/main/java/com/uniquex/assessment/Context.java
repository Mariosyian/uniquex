package com.uniquex.assessment;

import com.uniquex.assessment.Algorithms.Algorithms;

public class Context {
    private Student[] students;
    private int time;
    private boolean saveSuccess;
    private boolean saveError;

    private final String[] algorithms = {
        Algorithms.BUBBLE_SORT,
        Algorithms.HEAP_SORT,
        // Algorithms.MERGE_SORT,
    };

    public Context(
        Student[] students,
        int time,
        boolean saveSuccess,
        boolean saveError
    ) {
        this.students = students;
        this.time = time;
        this.saveSuccess = saveSuccess;
        this.saveError = saveError;
    }

    public Context() {
        this.students = new Student[] {};
        this.time = 0;
        this.saveSuccess = false;
        this.saveError = false;
    }

    public String[] getAlgorithms() {
        return this.algorithms;
    }

    public Student[] getStudents() {
        return this.students;
    }

    public int getTime() {
        return this.time;
    }
    
    public boolean getSaveSuccess() {
        return this.saveSuccess;
    }

    public boolean getSaveError() {
        return this.saveError;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setSaveSuccess(boolean saveSuccess) {
        this.saveSuccess = saveSuccess;
    }

    public void setSaveError(boolean saveError) {
        this.saveError = saveError;
    }
}
