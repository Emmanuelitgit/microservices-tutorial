package com.student_service.student_service.rest;

import com.student_service.student_service.models.Student;
import com.student_service.student_service.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRest {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentRest(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> saveStudent(@RequestBody Student student){
        Student studentRes = studentService.saveStudent(student);
        return new ResponseEntity<>(studentRes, HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<Object> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable UUID studentId){
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatusCode.valueOf(200));
    }
}
