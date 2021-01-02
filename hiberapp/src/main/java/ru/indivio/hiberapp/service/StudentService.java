package ru.indivio.hiberapp.service;


import ru.indivio.hiberapp.dao.StudentDao;
import ru.indivio.hiberapp.entity.Student;

import java.util.List;

public class StudentService {

    private StudentDao studentDao = new StudentDao();

    public StudentService() {
        }

    public Student find(int id) {
        return studentDao.findById(id);
    }

    public void save(Student student) {
        studentDao.save(student);
    }

    public void delete(Student student) {
        studentDao.delete(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }
}