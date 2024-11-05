package com.jalian.soap_crud.endpoint;

import com.jalian.soap_crud.constant.NameSpace;
import com.jalian.soap_crud.dto.*;
import com.jalian.soap_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private final StudentService studentService;

    @Autowired
    public StudentEndpoint(StudentService studentService) {
        this.studentService = studentService;
    }

    @PayloadRoot(namespace = NameSpace.NAMESPACE, localPart = "createStudentRequest")
    @ResponsePayload
    public Response createStudent(@RequestPayload StudentDto request) {
        return studentService.create(request);
    }

    @PayloadRoot(namespace = NameSpace.NAMESPACE, localPart = "deleteStudentRequest")
    @ResponsePayload
    public Response deleteStudent(@RequestPayload DeleteStudentRequest request) {
        return studentService.delete(request);
    }

    @PayloadRoot(namespace = NameSpace.NAMESPACE, localPart = "addCourseToStudentRequest")
    @ResponsePayload
    public AddCourseToStudentResponse addCourseToStudent(@RequestPayload AddCourseToStudentRequest request) {
        var response = new AddCourseToStudentResponse();
        response.getCourses().addAll(studentService.addCourse(request));
        return response;
    }

    @PayloadRoot(namespace = NameSpace.NAMESPACE, localPart = "getStudentByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudentById(@RequestPayload GetStudentByIdRequest request) {
        var student = studentService.getStudentById(request);
        var response = new GetStudentByIdResponse();
        response.setStudent(student);
        return response;
    }
}
