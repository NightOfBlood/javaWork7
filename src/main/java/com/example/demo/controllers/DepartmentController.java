package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model){
        Department department = departmentService.findDepartment(1);
        model.addAttribute("department", department);
        return "index.html";
    }

    @RequestMapping(value="/addDepartment", method = RequestMethod.POST)
    public String addDepartment(@RequestParam("name") String lastName,
                                Model model) {
        Department department = new Department();
        //department.setName(name);
        departmentService.saveDepartment(department);
        return "index.html";
    }

    @RequestMapping(value="/showDepartment", method = RequestMethod.POST)
    public String showDepartmentList(Model model) {
        model.addAttribute("department", departmentService.getAllDepartments());
        return "index.html";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findDepartment(id);
        if(department == null)
            throw new IllegalArgumentException("Invalid department Id:" + id);

        model.addAttribute("department", department);
        return "updateDepartment";
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.POST)
    public String updateDepartment(@PathVariable("id") int id, @Validated Department department,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            department.setId(id);
            return "updateDepartment.html";
        }

        departmentService.saveDepartment(department);
        return "redirect:/index";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    public String deleteDepartment(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findDepartment(id);
        if(department == null)
            throw new IllegalArgumentException("Invalid department Id:" + id);
        departmentService.deleteDepartment(department);
        return "redirect:/index";
    }

}
