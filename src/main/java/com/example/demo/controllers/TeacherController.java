package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model){
        Teacher teacher = teacherService.findTeacher(1);
        model.addAttribute("teacher", teacher);
        return "index.html";
    }

    @RequestMapping(value="/addTeacher", method = RequestMethod.POST)
    public String addTeacher(@RequestParam("lastName") String lastName,
                             @RequestParam("nameOfSubject") String nameOfSubject,
                             @RequestParam("workExperience") int workExperience,
                             Model model) {
        Teacher teacher = new Teacher();
        teacher.setLastName(lastName);
        teacherService.saveTeacher(teacher);
        return "index.html";
    }

    @RequestMapping(value="/showTeacher", method = RequestMethod.POST)
    public String showTeacherList(Model model) {
        model.addAttribute("teacher", teacherService.getAllTeachers());
        return "index.html";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findTeacher(id);
        if(teacher == null)
            throw new IllegalArgumentException("Invalid teacher Id:" + id);

        model.addAttribute("teacher", teacher);
        return "updateTeacher.html";
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.POST)
    public String updateTeacher(@PathVariable("id") int id, @Validated Teacher teacher,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            teacher.setId(id);
            return "updateTeacher.html";
        }

        teacherService.saveTeacher(teacher);
        return "redirect:/index";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    public String deleteTeacher(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findTeacher(id);
        if(teacher == null)
            throw new IllegalArgumentException("Invalid teacher Id:" + id);
        teacherService.deleteTeacher(teacher);
        return "redirect:/index";
    }
}
