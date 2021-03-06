package com.restfulservices.soap.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    //servlet to handle all the request via given url..
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace("http://www.soap.restfulservices.com/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);

        return definition;
    }

    // /ws/courses.wsdl
    @Bean
    public XsdSchema coursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }


    //SECURITY
//    @Bean
//    public XwsSecurityInterceptor securityInterceptor() {
//        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
//        securityInterceptor.setCallbackHandler(callbackHandler());
//        securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
//
//        return securityInterceptor;
//    }

//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        interceptors.add(securityInterceptor());
//    }

//    @Bean
//    public SimplePasswordValidationCallbackHandler callbackHandler() {
//        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
//        handler.setUsersMap(Collections.singletonMap("user", "password"));
//
//        return handler;
//    }

}
