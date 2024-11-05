package com.jalian.soap_crud.entity;

import com.jalian.soap_crud.dto.StudentDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Course> courses;

    public Student(StudentDto studentDto) {
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.courses = new ArrayList<>();
    }
}
