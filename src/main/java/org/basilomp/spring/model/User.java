package org.basilomp.spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String username;
    private List<Product> products;
}
