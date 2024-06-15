package org.basilomp.oop.reflection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Box {
    private final Long id;
    private final String name;
    private final int size;

    @MyAnnotation
    private boolean validateSmt(boolean result) {
        return true;
    }

    @MyLogging
    @MyAnnotation(value = 10)
    public String getInfo() {
        return toString();
    }
}
