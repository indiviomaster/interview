package ru.indivio.hiberapp;


import ru.indivio.hiberapp.entity.Student;
import ru.indivio.hiberapp.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        StudentService studentService = new StudentService();
//        for (int i = 0; i < 1000; i++) {
//            studentService.save(new Student("Stud"+i, i));
//        }
        List<Student> studentList = studentService.findAll();
        System.out.println(studentList);
        studentService.delete(studentService.find(2));
        Student student = studentService.find(5);
        student.setMark(105);
        studentService.update(student);
        List<Student> studentList2 = studentService.findAll();
        System.out.println(studentList2);
        System.exit(0);
    }
}