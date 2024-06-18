package org.basilomp.oop.hw1;

import org.basilomp.oop.hw1.annotations.*;

public class TestClass {

    @AfterTest
    public static void afterTest() {
        System.out.println("@AfterTest test");
    }

    @BeforeSuite
    public static void before() {
        System.out.println("@BeforeSuite test");
    }

    @Test(priority = 1)
    public static void priorityOne() {
        System.out.println("@Test: priority 1");
    }

    @Test(priority = 7)
    public static void anotherPriorityOne() {
        System.out.println("@Test: priority 7");
    }

    @Test(priority = 6)
    public void prioritySix() {
        System.out.println("@Test: priority 6");
    }

    @Test(priority = 5)
    public void priorityFive() {
        System.out.println("@Test: priority 5");
    }

    @Test(priority = 9)
    public void priorityNine() {
        System.out.println("@Test: priority 9");
    }

    @AfterSuite
    public static void after() {
        System.out.println("@AfterSuite test");
    }

    @BeforeTest
    public static void beforeTest() {
        System.out.println("@BeforeTest test");
    }
}
