package com.datastore.template.example.appliaction.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Teacher {
    @Id
    private String id;
    private String name;
    private String dob;
    private String department;
    private String yoe;
}
