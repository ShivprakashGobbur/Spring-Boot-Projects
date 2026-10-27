package com.example.demo.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	 private Long studentId;

	    @NotBlank(message = "Student name cannot be empty")
	    private String studentName;

	    @NotBlank(message = "Course name cannot be empty")
	    private String courseName;

	    @Email(message = "Enter valid email")
	    @NotBlank(message = "Email cannot be empty")
	    private String email;

	    @Min(value = 18, message = "Age must be above 18")
	    private Integer age;

	    @NotNull(message = "Phone number cannot be empty")
	    private Long phoneNumber;

	    
	    @NotBlank(message = "College name cannot be empty")
	    private String collegeName;
}
