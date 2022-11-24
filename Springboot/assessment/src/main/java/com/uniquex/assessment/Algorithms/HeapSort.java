package com.uniquex.assessment.Algorithms;

import com.uniquex.assessment.Student;

public class HeapSort {
    public Student[] sortAndReturn(Student[] students) {
        int N = students.length;

        for (int i = (N / 2)-1; i >= 0; i--) {
            heapify(students, N, i);
        }

        for (int i = N - 1; i > 0; i--) {
            Student temp = students[0];
            students[0] = students[i];
            students[i] = temp;

            heapify(students, i, 0);
        }

        return students;
    }

    private void heapify(Student[] students, int N, int i) {
        int largest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < N && students[l].getGrade() > students[largest].getGrade()) {
            largest = l;
        }

        if (r < N && students[r].getGrade() > students[largest].getGrade()) {
            largest = r;
        }

        if (largest != i) {
            Student swap = students[i];
            students[i] = students[largest];
            students[largest] = swap;

            heapify(students, N, largest);
        }
    }
 
    public void printList(int students[]) {
        int N = students.length;
 
        for (int i = 0; i < N; i++) {
            System.out.println(students[i]);
        }
    }
}
