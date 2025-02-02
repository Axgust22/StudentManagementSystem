package com.example.HandleSystem;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private List<StuCourse> students;

    public Course(String courseName) {
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<StuCourse> getStudents() {
        return students;
    }

    public void setStudents(List<StuCourse> students) {
        this.students = students;
    }

    public void loadStudentsFromDB(MongoDatabase database) {
        MongoCollection<Document> courseCollection = database.getCollection("CourseManagement");
        Document courseDoc = courseCollection.find(new Document("courseName", courseName)).first();

        students.clear();

        if (courseDoc != null && courseDoc.containsKey("students")) {
            List<Document> studentDocs = courseDoc.getList("students", Document.class);
            for (Document doc : studentDocs) {
                StuCourse student = new StuCourse(
                        doc.getString("StudentID"),
                        doc.getString("StudentName"),
                        doc.getInteger("Age", 0),
                        doc.getString("Sex"),
                        doc.getString("Major"),
                        doc.getInteger("Year", 1),
                        doc.getString("AcademyYear"),
                        doc.getString("Email"),
                        doc.getString("Address"),
                        doc.getString("PhoneNumber"));
                students.add(student);
            }
        }
    }

    public void addStudentToDB(StuCourse student, MongoDatabase database) {
        MongoCollection<Document> courseCollection = database.getCollection("CourseManagement");

        Document studentDoc = new Document("StudentID", student.getStudentID())
                .append("StudentName", student.getStudentName())
                .append("Age", student.getStudentAge())
                .append("Sex", student.getStudentGender())
                .append("Major", student.getStudentMajor())
                .append("Year", student.getStudentYear())
                .append("AcademyYear", student.getStudentAcademy())
                .append("Email", student.getStudentEmail())
                .append("Address", student.getStudentAddress())
                .append("PhoneNumber", student.getStudentPhoneNumber());

        Document update = new Document("$push", new Document("students", studentDoc));
        courseCollection.updateOne(new Document("courseName", courseName), update);

        students.add(student);
    }
}
