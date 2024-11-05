package com.jalian.soap_crud.mapper;

import com.jalian.soap_crud.dto.CourseDto;
import com.jalian.soap_crud.entity.Course;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseMapper {

    public static Course toCourse(CourseDto courseDto) {
        var course = new Course();
        course.setName(courseDto.getName());
        var xmlStart = courseDto.getStartDate();
        course.setStartDate(LocalDate.of(xmlStart.getYear(), xmlStart.getMonth(), xmlStart.getDay()));
        var xmlEnd = courseDto.getEndDate();
        course.setEndDate(LocalDate.of(xmlEnd.getYear(), xmlEnd.getMonth(), xmlEnd.getDay()));
        return course;
    }

    public static List<Course> toCourse(List<CourseDto> courseDtos) {
        var courses = new ArrayList<Course>();
        courseDtos.forEach(courseDto -> courses.add(toCourse(courseDto)));
        return courses;
    }

    public static CourseDto toCourseDto(Course course) {
        var courseDto = new CourseDto();
        courseDto.setName(course.getName());
        try {
            courseDto.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(course.getStartDate().toString()));
            courseDto.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(course.getEndDate().toString()));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return courseDto;
    }

    public static List<CourseDto> toCourseDto(List<Course> courses) {
        var courseDtos = new ArrayList<CourseDto>();
        courses.forEach(courseDto -> courseDtos.add(toCourseDto(courseDto)));
        return courseDtos;
    }
}
