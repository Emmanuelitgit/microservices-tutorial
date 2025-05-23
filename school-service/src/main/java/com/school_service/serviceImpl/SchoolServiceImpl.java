package com.school_service.serviceImpl;

import com.school_service.exception.NotFoundException;
import com.school_service.models.School;
import com.school_service.repo.SchoolRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SchoolServiceImpl {

    private final SchoolRepo schoolRepo;

    @Autowired
    public SchoolServiceImpl(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    /**
     * @description this method is used to save school record. it makes external call to school service
     * @param school
     * @return
     */
    public School saveSchool(School school){
        return schoolRepo.save(school);
    }

    /**
     * @description this method is used to fetch all schools and their records.
     * @param
     * @return
     */
    public List<School> getSchools(){
        return schoolRepo.findAll();
    }

    /**
     * @description this method is used to fetch school details given the school id. it makes external call to school service
     * @param id
     * @return
     */
    public ResponseEntity<Object> getSchoolById(UUID id){
        Optional<School> schoolOptional = schoolRepo.findById(id);
        if (schoolOptional.isEmpty()){
            Map<String, Object> res = new HashMap<>();
            res.put("status", 404);
            res.put("message", "school record not found");
            return new ResponseEntity<>(res, HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(schoolOptional.get(), HttpStatusCode.valueOf(200));
    }

    @KafkaListener(topics = "topic1", groupId = "my-group-id")
    public void getMessage(String message){
        log.info("MESSAGE:->>>>{}", message);
    }
}
