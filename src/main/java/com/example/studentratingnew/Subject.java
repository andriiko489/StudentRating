package com.example.studentratingnew;

// Клас Subject для представлення запису з таблиці Subjects
public class Subject {
    private int id;
    private String specializationName;
    private String subjectName;
    private int semester;
    private int credits;

    public Subject(int id, String specializationName, String subjectName, int semester, int credits) {
        this.id = id;
        this.specializationName = specializationName;
        this.subjectName = subjectName;
        this.semester = semester;
        this.credits = credits;
    }

    // Геттери і сеттери
    public int getId() {
        return id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", specializationName='" + specializationName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", semester=" + semester +
                ", credits=" + credits +
                '}';
    }
}
