package com.restfulservices.soap.soap.service;

import com.restfulservices.soap.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.restfulservices.soap.soap.service.CourseDetailsService.Status.FAILURE;
import static com.restfulservices.soap.soap.service.CourseDetailsService.Status.SUCCESS;

@Component
public class CourseDetailsService {

    private static final List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Spring", "10 Steps");
        courses.add(course1);

        Course course2 = new Course(2, "Spring", "10 Steps");
        courses.add(course2);

        Course course3 = new Course(2, "Spring", "10 Steps");
        courses.add(course3);
    }

    public Status deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id)
                iterator.remove();

            return SUCCESS;
        }
        return FAILURE;
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id)
                return course;
        }
        return null;
    }

    public enum Status {
        SUCCESS, FAILURE
    }
}
