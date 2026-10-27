package com.example.demo.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	 private final StudentService studentService;

	    public StudentController(StudentService studentService) {
	        this.studentService = studentService;
	    }

	    // Register a student
	    @PostMapping
	    public ResponseEntity<Student> registerStudent(
	            @Valid @RequestBody Student student) {

	        Student registeredStudent = studentService.registerStudent(student);
	        return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
	    }

	    // View all students
	    @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        return ResponseEntity.ok(studentService.getAllStudents());
	    }

	    // Find student by ID
	    @GetMapping("/{studentId}")
	    public ResponseEntity<Student> getStudentById(
	            @PathVariable Long studentId) {

	        return ResponseEntity.ok(
	                studentService.getStudentById(studentId));
	    }

	    // Delete student registration
	    @DeleteMapping("/{studentId}")
	    public ResponseEntity<String> deleteStudent(
	            @PathVariable Long studentId) {

	        studentService.deleteStudent(studentId);
	        return ResponseEntity.ok(
	                "Student registration deleted successfully");
	    }
}
