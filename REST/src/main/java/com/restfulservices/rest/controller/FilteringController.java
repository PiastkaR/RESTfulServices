package com.restfulservices.rest.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restfulservices.rest.domain.SomeBean;
import com.restfulservices.rest.domain.SomeBeanDynamic;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBean() {
        return Arrays.asList(new SomeBean("value11", "value12", "value13"),
                new SomeBean("value21", "value22", "value23"));
    }

    @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue retrieveDynamicallyFilteredBeans() {
        SomeBeanDynamic dynamicBean = new SomeBeanDynamic("value1", "value2", "value3");
        List<SomeBeanDynamic> list = Arrays.asList(dynamicBean, new SomeBeanDynamic("val1", "val2", "val3"));

        MappingJacksonValue mapping = new MappingJacksonValue(list); //mozna dac na obiekt
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeDynamicBeanFilter", filter);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
