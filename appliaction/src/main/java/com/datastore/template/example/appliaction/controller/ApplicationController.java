package com.datastore.template.example.appliaction.controller;

import com.datastore.template.example.appliaction.model.Student;
import com.datastore.template.example.appliaction.model.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.data.datastore.core.DatastoreTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class ApplicationController {
    private final DatastoreTemplate datastoreTemplate;

    public ApplicationController(DatastoreTemplate datastoreTemplate) {
        this.datastoreTemplate = datastoreTemplate;
    }

    @PostMapping("/saveDataToDB")
    public ResponseEntity<?> saveDataToDB(@RequestBody Object o, @RequestParam("tableName") String tableName) {
        try {
            if (tableName.equalsIgnoreCase("Student")) {
                ObjectMapper objectMapper = new ObjectMapper();
                Student student = objectMapper.convertValue(o, Student.class);
                datastoreTemplate.save(student);
                return ResponseEntity.ok(student);
            } else if (tableName.equalsIgnoreCase("Teacher")) {
                ObjectMapper objectMapper = new ObjectMapper();
                Teacher teacher = objectMapper.convertValue(o, Teacher.class);
                datastoreTemplate.save(teacher);
                return ResponseEntity.ok(teacher);
            }
        } catch (Exception e) {
            log.info("Error occurred due to : {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred");
        }
        return null;
    }

    @GetMapping("retrieveFromDB")
    public ResponseEntity<?> retrieveFromDB(@RequestParam("id") String id) {
        Student student = datastoreTemplate.findById(id, Student.class);
        Teacher teacher = datastoreTemplate.findById(id, Teacher.class);
        return ResponseEntity.ok("Student data:" + (null != student ? student.toString() : " ") + " " + "Teacher data: " + (null != teacher ? teacher.toString() : ""));

    }
}