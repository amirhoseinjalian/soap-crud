package com.jalian.soap_crud.service.impl;

import com.jalian.soap_crud.dto.*;
import com.jalian.soap_crud.entity.Course;
import com.jalian.soap_crud.entity.Student;
import com.jalian.soap_crud.mapper.CourseMapper;
import com.jalian.soap_crud.mapper.StudentMapper;
import com.jalian.soap_crud.repository.StudentRepository;
import com.jalian.soap_crud.service.CourseService;
import com.jalian.soap_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public Response create(StudentDto studentDto) {
        var student = studentRepository.save(new Student(studentDto));
        var response = new Response();
        response.setResult(student.toString());
        return response;
    }

    @Override
    public Response delete(DeleteStudentRequest studentId) {
        studentRepository.deleteById(studentId.getId().longValue());
        var response = new Response();
        response.setResult("student with this id: " + studentId.getId() + " deleted successfully");
        return response;
    }

    @Override
    public List<CourseDto> addCourse(AddCourseToStudentRequest courses) {
        var student = studentRepository.findById(courses.getStudentId().longValue())
                .orElseThrow(() -> new RuntimeException("student not found"));
        var coursesInDb = new ArrayList<Course>();
        courses.getCourses().forEach(courseDto -> coursesInDb.add(courseService.save(CourseMapper.toCourse(courseDto))));
        student.getCourses().addAll(coursesInDb);
        return CourseMapper.toCourseDto(studentRepository.save(student).getCourses());
    }

    @Override
    public StudentDto getStudentById(GetStudentByIdRequest id) {
        var student = studentRepository.findById(id.getId().longValue()).orElseThrow();
        return StudentMapper.toStudentDto(student);
    }
}
