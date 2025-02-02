package com.example.HandleSystem;

import java.util.*;

public class Score {
    private String studentID;
    private String studentName;
    private Map<Integer, Map<Integer, Map<String, Double>>> yearSemesterScores;

    public Score(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.yearSemesterScores = new HashMap<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void addOrUpdateScore(int year, int semester, String courseName, double score) {
        yearSemesterScores
                .computeIfAbsent(year, y -> new HashMap<>())
                .computeIfAbsent(semester, s -> new HashMap<>())
                .put(courseName, score);
    }

    public Map<String, Double> getScores(int year, int semester) {
        return yearSemesterScores.getOrDefault(year, Collections.emptyMap())
                .getOrDefault(semester, Collections.emptyMap());
    }

    public double calculateGPA(int year, int semester) {
        Map<String, Double> scores = getScores(year, semester);
        return scores.isEmpty() ? 0.0 : scores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double getTotalScore(int year, int semester) {
        Map<String, Double> scores = getScores(year, semester);
        return scores.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public void updateScore(int year, int semester, String courseName, double newScore) {
        addOrUpdateScore(year, semester, courseName, newScore);
    }

    public void removeScore(int year, int semester, String courseName) {
        if (yearSemesterScores.containsKey(year) &&
                yearSemesterScores.get(year).containsKey(semester)) {
            yearSemesterScores.get(year).get(semester).remove(courseName);
        }
    }

    public void removeSemester(int year, int semester) {
        if (yearSemesterScores.containsKey(year)) {
            yearSemesterScores.get(year).remove(semester);
            if (yearSemesterScores.get(year).isEmpty()) {
                yearSemesterScores.remove(year);
            }
        }
    }

    public Map<String, Double> searchScores(int year, int semester) {
        return getScores(year, semester);
    }

    public List<Object[]> toTableData() {
        List<Object[]> tableData = new ArrayList<>();
        yearSemesterScores.forEach((year, semesters) -> {
            semesters.forEach((semester, courses) -> {
                courses.forEach((course, score) -> {
                    tableData.add(new Object[] { studentID, studentName, year, semester, course, score });
                });
            });
        });
        return tableData;
    }

    // public String getAllScores() {
    // StringBuilder sb = new StringBuilder("Scores for " + studentName + " (" +
    // studentID + "):\n");
    // yearSemesterScores.forEach((year, semesters) -> {
    // sb.append("Year ").append(year).append(":\n");
    // semesters.forEach((semester, courses) -> {
    // sb.append(" Semester ").append(semester).append(":\n");
    // courses.forEach(
    // (course, score) -> sb.append(" ").append(course).append(":
    // ").append(score).append("\n"));
    // });
    // });
    // return sb.toString();
    // }

    // public double calculateOverallGPA() {
    // double totalScore = 0.0;
    // int totalCourses = 0;
    // for (Map<Integer, Map<String, Double>> semesters :
    // yearSemesterScores.values()) {
    // for (Map<String, Double> courseScores : semesters.values()) {
    // totalScore +=
    // courseScores.values().stream().mapToDouble(Double::doubleValue).sum();
    // totalCourses += courseScores.size();
    // }
    // }
    // return totalCourses > 0 ? totalScore / totalCourses : 0.0;
    // }

}
