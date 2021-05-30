package com.example.demo.repositories;

import com.example.demo.models.Department;
import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
