package com.uniquex.assessment.Algorithms;

import com.uniquex.assessment.Student;

public class BubbleSort {
    public Student[] sortAndReturn(Student[] students) {
        int size = students.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (students[j].getGrade() > students[j + 1].getGrade()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        return students;
    }

    public void printList(Student[] students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}