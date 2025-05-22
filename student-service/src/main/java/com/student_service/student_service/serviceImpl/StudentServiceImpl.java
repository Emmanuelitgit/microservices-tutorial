package com.student_service.student_service.serviceImpl;

import com.student_service.student_service.dto.SchoolResponse;
import com.student_service.student_service.dto.StudentResponse;
import com.student_service.student_service.exception.NotFoundException;
import com.student_service.student_service.exception.ServerException;
import com.student_service.student_service.models.Student;
import com.student_service.student_service.repo.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class StudentServiceImpl {

    private final StudentRepo studentRepo;
    private final RestTemplate restTemplate;
    @Value("${app.base-url}")
    private String BASE_URL;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, RestTemplate restTemplate) {
        this.studentRepo = studentRepo;
        this.restTemplate = restTemplate;
    }

    /**
     * @description this method is used to save student record. it makes external call to school service
     * @param student
     * @return
     */
    public Student saveStudent(Student student){
       try{
           ResponseEntity<SchoolResponse> schoolResponse = restTemplate.getForEntity(BASE_URL+student.getSchoolId(), SchoolResponse.class);
           log.debug("Response:->>>{}", schoolResponse.getBody());
           if (Objects.requireNonNull(schoolResponse.getBody()).getId() == null){
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "school record not found");
           }
           return studentRepo.save(student);
       }
       catch (Exception e){
           throw new ServerException(e.getMessage());
       }
    }

    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    /**
     * @description this method is used to fetch student details given the student id. it makes external call to school service
     * @param studentId
     * @return
     */
    public StudentResponse getStudentById(UUID studentId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new NotFoundException("student record not found"));

        ResponseEntity<SchoolResponse> schoolResponse = restTemplate.getForEntity(BASE_URL+student.getSchoolId(), SchoolResponse.class);

        if (schoolResponse.getBody().getId() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "school record not found");
        }
        return StudentResponse
                .builder()
                .school(schoolResponse.getBody().getName())
                .course(student.getCourse())
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .principal(schoolResponse.getBody().getPrincipal())
                .build();
    }
}
