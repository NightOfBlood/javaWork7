package com.example.demo.services;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private final DepartmentDao departmentDao = new DepartmentDao();


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudent(int id) {
        return studentRepository.getById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public void saveStudentFromCSV(Student student) {
        student.setDepartment(departmentDao.findDepartmentById(student.getDepartmentId()));
        saveStudent(student);
    }
}
