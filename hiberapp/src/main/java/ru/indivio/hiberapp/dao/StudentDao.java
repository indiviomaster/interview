package ru.indivio.hiberapp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.indivio.hiberapp.HibernateSessionFactory;
import ru.indivio.hiberapp.entity.Student;

import java.util.List;

public class StudentDao {

    public Student findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Student.class, id);
    }

    public void save(Student student) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    public void delete(Student student) {
        if (student!=null){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
        session.close();
        }
    }

    public List<Student> findAll() {
        List<Student> studentList = (List<Student>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Student").list();
        return studentList;
    }
}