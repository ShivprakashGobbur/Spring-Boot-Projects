package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.exception.StudentNotFoundException;

@Service
public class StudentService {
	 private final List<Student> students = new ArrayList<>();
	    private Long nextStudentId = 1L;

	    // Register a student
	    public Student registerStudent(Student student) {
	        student.setStudentId(nextStudentId++);
	        students.add(student);
	        return student;
	    }

	    // View all students
	    public List<Student> getAllStudents() {
	        return students;
	    }

	    // Find student by ID
	    public Student getStudentById(Long studentId) {
	        return students.stream()
	                .filter(student -> student.getStudentId().equals(studentId))
	                .findFirst()
	                .orElseThrow(() ->
	                        new StudentNotFoundException(
	                                "Student not found with ID: " + studentId));
	    }

	    // Delete student registration
	    public void deleteStudent(Long studentId) {
	        Student student = getStudentById(studentId);
	        students.remove(student);
	    }
}
