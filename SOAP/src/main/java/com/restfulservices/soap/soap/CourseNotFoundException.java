package com.restfulservices.soap.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import java.util.UUID;

@SoapFault(faultCode = FaultCode.CLIENT, customFaultCode = "{http://www.soap.restfulservices.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {
    private static final UUID serialVersionUID = UUID.randomUUID();

    public CourseNotFoundException(String message) {
    }
}
