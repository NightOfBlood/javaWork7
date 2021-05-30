package com.example.demo.services;

import com.example.demo.dao.TeacherDao;
import com.example.demo.models.Teacher;

import java.util.List;

public class TeacherService {
    private final TeacherDao teacherDao = new TeacherDao();

    public List<Teacher> getAllTeachers(){
        return teacherRepository.getAllTeachers();
    }

    public Teacher findTeacher(int id) {
        return teacherDao.findTeacherById(id);
    }

    public void saveTeacher(Teacher teacher) {
        Teacher teacherById = teacherDao.findTeacherById(teacher.getId());
        if(teacherById==null)
            teacherDao.save(teacher);
        else
            teacherDao.update(teacher);

    }

    public void deleteTeacher(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }


    public Teacher findTeacherById(int id) {
        return teacherDao.findTeacherById(id);
    }
}

