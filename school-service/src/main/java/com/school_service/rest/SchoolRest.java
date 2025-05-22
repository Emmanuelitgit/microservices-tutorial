package com.school_service.rest;

import com.school_service.models.School;
import com.school_service.serviceImpl.SchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolRest {

    private final SchoolServiceImpl schoolService;

    @Autowired
    public SchoolRest(SchoolServiceImpl schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<School> saveSchool(@RequestBody School school){
        return new ResponseEntity<>(schoolService.saveSchool(school), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<Object> getSchools(@RequestBody School school){
        return new ResponseEntity<>(schoolService.getSchools(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<Object> getSchoolById(@PathVariable UUID schoolId){
        return schoolService.getSchoolById(schoolId);
    }

}
