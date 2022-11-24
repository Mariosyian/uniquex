package com.uniquex.assessment.Algorithms;

import com.uniquex.assessment.Student;

public class MergeSort {
    public Student[] merge(Student[] students, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
 
        Student[] L = { students[n1] };
        Student[] R = { students[n2] };
 
        for (int i = 0; i < n1; i++) {
            L[i] = students[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = students[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].getGrade() <= R[j].getGrade()) {
                students[k] = L[i];
                i++;
            }
            else {
                students[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (i < n1) {
            students[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2) {
            students[k] = R[j];
            j++;
            k++;
        }

        return students;
    }
 
    public Student[] sortAndReturn(Student[] students, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            students = sortAndReturn(students, left, mid);
            students = sortAndReturn(students, mid + 1, right);

            students = merge(students, left, mid, right);
        }

        return students;
    }
 
    static void printList(Student[] students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
