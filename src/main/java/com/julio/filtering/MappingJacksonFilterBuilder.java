package com.julio.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class MappingJacksonFilterBuilder {

    private SimpleFilterProvider provider = new SimpleFilterProvider();

    public static MappingJacksonFilterBuilder init() {
        return new MappingJacksonFilterBuilder();
    }

    public MappingJacksonFilterBuilder addFilter(String filterName, String... fieldsToShow) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToShow);
        provider.addFilter(filterName, filter);
        return this;
    }

    public MappingJacksonValue build(Object bean) {
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        mapping.setFilters(provider);
        return mapping;
    }
}
