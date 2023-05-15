package com.example.block7crudvalidation.feign;

import com.example.block7crudvalidation.controller.dto.ProfesorSimpleOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "profesorClient")
public interface ProfesorClient {

    @GetMapping("/profesor/{id}")
    ProfesorSimpleOutputDto getProfesor(@PathVariable int id);

}