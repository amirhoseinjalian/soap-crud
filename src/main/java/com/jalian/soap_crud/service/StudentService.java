package com.jalian.soap_crud.service;

import com.jalian.soap_crud.dto.*;

import java.util.List;

public interface StudentService {

    Response create(StudentDto studentDto);
    Response delete(DeleteStudentRequest id);
    List<CourseDto> addCourse(AddCourseToStudentRequest courses);
    StudentDto getStudentById(GetStudentByIdRequest id);
}
