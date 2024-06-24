package org.basilomp.oop.hw2.employees;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Employee {
    private String name;
    private int age;
    private Position position;
}
