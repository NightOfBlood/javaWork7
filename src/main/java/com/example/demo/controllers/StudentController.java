package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model){
        Student student = studentService.findStudent(1);
        model.addAttribute("student", student);
        return "index.html";
    }

    @RequestMapping(value="/addStudent", method = RequestMethod.POST)
    public String addStudent(@RequestParam("lastName") String lastName,
                             @RequestParam("age") int age,
                             @RequestParam("course") int course,
                             @RequestParam("favouriteSubject") String favouriteSubject,

                             Model model) {
        Student student = new Student();
        student.setLastName(lastName);
        studentService.saveStudent(student);
        return "index.html";
    }

    @RequestMapping(value="/showStudent", method = RequestMethod.POST)
    public String showStudentList(Model model) {
        model.addAttribute("student", studentService.getAllStudents());
        return "index.html";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.findStudent(id);
        if(student == null)
            throw new IllegalArgumentException("Invalid student Id:" + id);

        model.addAttribute("student", student);
        return "updateStudent.html";
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.POST)
    public String updateStudent(@PathVariable("id") int id, @Validated Student student,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "updateStudent.html";
        }

        studentService.saveStudent(student);
        return "redirect:/index";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        Student student = studentService.findStudent(id);
        if(student == null)
            throw new IllegalArgumentException("Invalid department Id:" + id);
        studentService.deleteStudent(student);
        return "redirect:/index";
    }

}
