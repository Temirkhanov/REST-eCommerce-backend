package com.goruslan.eCommerce.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.goruslan.eCommerce.entity.Product;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;

public class ProductFilter {

    public ProductFilter(){}

    public static MappingJacksonValue filterProductList(List<Product> list, String... propertyArray) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray);
        String filterName = Product.class.getAnnotation(JsonFilter.class).value();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }

}
