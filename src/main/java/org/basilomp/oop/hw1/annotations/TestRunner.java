package org.basilomp.oop.hw1.annotations;

import org.basilomp.oop.hw1.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    private static final String BEFORE = "@BeforeSuite";
    private static final String AFTER = "@AfterSuite";

    public static void main(String[] args) {
        runTests(TestClass.class);
    }

    public static void runTests(Class<?> c) {
        List<Method> testMethods = new ArrayList<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        List<Method> beforeTestMethods = new ArrayList<>();
        List<Method> afterTestMethods = new ArrayList<>();

        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                validateStatic(method, BEFORE);
                validateSingleUse(beforeSuiteMethod, BEFORE);
                beforeSuiteMethod = method;
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                validateStatic(method, AFTER);
                validateSingleUse(afterSuiteMethod, AFTER);
                afterSuiteMethod = method;
            } else if (method.isAnnotationPresent(Test.class)) {
                validatePriority(method);
                testMethods.add(method);
            } else if (method.isAnnotationPresent(BeforeTest.class)) {
                beforeTestMethods.add(method);
            } else if (method.isAnnotationPresent(AfterTest.class)) {
                afterTestMethods.add(method);
            }
            testMethods.sort(Comparator.comparingInt(tm -> tm.getAnnotation(Test.class)
                    .priority()));
        }
        batchInvoke(c, beforeSuiteMethod, afterSuiteMethod, testMethods, beforeTestMethods, afterTestMethods);
    }

    public static void validateStatic(Method method, String annotationName) {
        if (!Modifier.isStatic(method.getModifiers())) {
            String errorMessage = String.format("% is not applicable to non-static methods %s", annotationName, method.getName());
            throw new RuntimeException(errorMessage);
        }
    }

    public static void validateSingleUse(Method method, String annotationName) {
        if (method != null) {
            String errorMessage = String.format("The %s annotation cannot be used with more than 1 method", annotationName);
            throw new RuntimeException(errorMessage);
        }
    }

    public static void validatePriority(Method method) {
        int methodPriority = method.getAnnotation(Test.class).priority();
        if (methodPriority < 0 || methodPriority > 10) {
            String errorMessage = String.format("Method %s with priority %d cannot be invoked. Priority must be in bounds of 0 to 10", method.getName(), methodPriority);
            throw new IllegalArgumentException(errorMessage);
        }
    }



    public static void batchInvoke(Class<?> c,
                                   Method before,
                                   Method after,
                                   List<Method> tests,
                                   List<Method> beforeTests,
                                   List<Method> afterTests) {
        try {
            if (before != null) {
                before.invoke(null);
            }
            if (after != null) {
                if (!tests.isEmpty()) {
                    for (Method test : tests) {
                        if (!beforeTests.isEmpty()) {
                            for (Method beforeMethod : beforeTests) {
                                beforeMethod.invoke(null);
                            }
                        }
                        test.invoke(c.getDeclaredConstructor().newInstance());
                        if (!afterTests.isEmpty()) {
                            for (Method afterTest : afterTests) {
                                afterTest.invoke(null);
                            }
                        }
                    }
                }
                after.invoke(null);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
