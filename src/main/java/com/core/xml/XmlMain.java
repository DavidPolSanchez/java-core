package com.core.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class XmlMain {

    public static void main(String[] args) throws IOException {

        Employee employee1 = new Employee("Empleado1", 60500.77, LocalDate.now());

        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        // 1. java to xml

        // 1.1. generar xml en String
        String xml = mapper.writeValueAsString(employee1);
        System.out.println(xml);

        // 1.2. generar el xml en un archivo
        mapper.writeValue(new File("employee.xml"), employee1);

        // 2. xml to java
        Employee employeeFromFile = mapper.readValue(new File("employee.xml"), Employee.class);
        System.out.println(employeeFromFile);
    }
}
