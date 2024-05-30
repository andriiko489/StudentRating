package com.example.studentratingnew;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String dbUrl, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, user, password);
    }

    // Метод для створення таблиці Subjects
    public void createSubjectsTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Subjects ("
                + "id SERIAL PRIMARY KEY, "
                + "specialization_name VARCHAR(255), "
                + "subject_name VARCHAR(255), "
                + "semester INT, "
                + "credits INT)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    // Метод для отримання всіх предметів з таблиці Subjects
    public List<Subject> getAllByQuery(String query) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String specializationName = resultSet.getString("specialization_name");
                String subjectName = resultSet.getString("subject_name");
                int semester = resultSet.getInt("semester");
                int credits = resultSet.getInt("credits");

                Subject subject = new Subject(id, specializationName, subjectName, semester, credits);
                subjects.add(subject);
            }
        }
        return subjects;
    }
    public List<Subject> getAll() throws SQLException {
        return this.getAllByQuery("SELECT * FROM Subjects");
    }
    public List<Subject> getAllBySubjectAndSemester(int semester, String specializationName) throws SQLException {
        return this.getAllByQuery("SELECT * FROM Subjects WHERE specialization_name = '" + specializationName + "' AND semester = " + semester);
    }

    public void createSubject(String specializationName, String subjectName, int semester, int credits) throws SQLException {
        String insertSQL = "INSERT INTO Subjects (specialization_name, subject_name, semester, credits) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, specializationName);
            preparedStatement.setString(2, subjectName);
            preparedStatement.setInt(3, semester);
            preparedStatement.setInt(4, credits);
            preparedStatement.executeUpdate();
        }
    }
}

