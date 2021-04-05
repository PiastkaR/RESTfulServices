package com.restfulservices.soap.soap;

import com.restfulservices.soap.courses.*;
import com.restfulservices.soap.soap.bean.Course;
import com.restfulservices.soap.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    //output
    @PayloadRoot(namespace = "http://www.soap.restfulservices.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId());
        if (course == null)
            throw new CourseNotFoundException("Invalid Course id " + request.getId());

        return mapCourse(course);
    }

    @PayloadRoot(namespace = "http://www.soap.restfulservices.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courses = service.findAll();

        return mapCourseDetails(courses);
    }

    @PayloadRoot(namespace = "http://www.soap.restfulservices.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
        CourseDetailsService.Status status = service.deleteById(request.getId());
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));

        return response;
    }

    private GetCourseDetailsResponse mapCourse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = mapCourseDetails(course);
        response.setCourseDetails(courseDetails);
        return response;
    }

    private CourseDetails mapCourseDetails(Course course) {
        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    private GetAllCourseDetailsResponse mapCourseDetails(List<Course> courses) {
        CourseDetails courseDetails = new CourseDetails();
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        courses.forEach(course -> {
            courseDetails.setId(course.getId());
            courseDetails.setName(course.getName());
            courseDetails.setDescription(course.getDescription());
            response.getCourseDetails().add(courseDetails);
        });

        return response;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if (status == CourseDetailsService.Status.FAILURE)
            return Status.FAILURE;
        return Status.SUCCESS;
    }
}
