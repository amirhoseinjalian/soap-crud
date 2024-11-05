package com.jalian.soap_crud.mapper;

import com.jalian.soap_crud.dto.StudentDto;
import com.jalian.soap_crud.entity.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setCourses(CourseMapper.toCourse(studentDto.getCourses()));
        return student;
    }

    public static List<Student> toStudent(List<StudentDto> studentDtos) {
       var students = new ArrayList<Student>();
       studentDtos.forEach(student -> students.add(toStudent(student)));
       return students;
    }

    public static StudentDto toStudentDto(Student student) {
        var studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.getCourses().addAll(CourseMapper.toCourseDto(student.getCourses()));
        return studentDto;
    }

    public static List<StudentDto> toStudentDto(List<Student> students) {
        var studentDtos = new ArrayList<StudentDto>();
        students.forEach(student -> studentDtos.add(toStudentDto(student)));
        return studentDtos;
    }
}
