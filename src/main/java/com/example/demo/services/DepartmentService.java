package com.example.demo.services;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.models.Department;
import com.example.demo.models.Student;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;

import java.util.List;

public class DepartmentService {
    private final DepartmentRepository departmentDao = new DepartmentDao();
    private final StudentRepository studentDao = new StudentDao();
    private final TeacherRepository teacherDao = new TeacherDao();

    public List<Department> getAllDepartments(){
        return departmentDao.getAllDepartments();
    }

    public Department findDepartment(int id) {
        return departmentDao.findDepartmentById(id);
    }

    public void saveDepartment(Department department) {
        departmentDao.save(department);
    }

    public void deleteDepartment(Department department) {
        departmentDao.delete(department);
    }

    public void updateDepartment(Department department) {
        departmentDao.update(department);
    }

    public void addStudent(Department department, int studentId) {
        Student student = studentDao.findStudentById(studentId);
        department.addStudent(student);
        departmentDao.save(department);
    }

    public Department findDepartmentById(int id) {
        return departmentDao.findDepartmentById(id);
    }

    public void saveDepartmentFromCSV(Department department) {
        department.setTeacher(teacherDao.findTeacherById(department.getTeacherId()));
        saveDepartment(department);
    }

}
