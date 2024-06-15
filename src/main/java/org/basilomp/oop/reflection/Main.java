package org.basilomp.oop.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {

        //Конкретный объект, содержащий определенные данные
        Box whitebox = new Box(1l, "Whitebox", 10);

        //Class позволяет работать с метаданными непосредственно класса
        Class<Box> boxClass = Box.class;
        Class<?> forName = Class.forName("org.basilomp.oop.reflection.Box");

        Method[] declaredMethods = boxClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation instanceof MyAnnotation) {
                    MyAnnotation currentAnnotation = declaredMethod.getAnnotation(MyAnnotation.class);
                    String info = "Called method: " + declaredMethod.getName()
                                  + "\nAnnotation value: " + currentAnnotation.value();
                    System.out.println(info);
                } else if(annotation instanceof MyLogging) {
                    System.out.println("Logging method calling: " + declaredMethod.getName());
                }
            }
        }
//        //Получаем набор полей класса
//        Field[] fields = boxClass.getDeclaredFields();
//        //Выводим список полей
//        System.out.println("Declared fields:");
//        for (Field field : fields) {
//            System.out.println("-----------------");
//            System.out.print(field.getName() + ", ");
//            System.out.print(field.getType() + ", ");
//            System.out.print(field.getModifiers() + ", ");
//            System.out.println("private: " + Modifier.isPrivate(field.getModifiers()));
//        }
//
//        //Изначальный объект
//        System.out.println(whitebox);
//
//        //Меняем значение объекта
//        Field name = boxClass.getDeclaredField("name");
//        name.set(whitebox, "Blackbox");
//        System.out.println(whitebox);
//
//        //Меняем значение приватного поля
//        System.out.println("---------------------");
//        System.out.println(whitebox);
//        Field id = boxClass.getDeclaredField("id");
//        id.setAccessible(true);
//        id.set(whitebox, 10l);
//        System.out.println(whitebox);

//        //Получаем список всех методов
//        System.out.println("---------------------");
//        Method[] declaredMethods = boxClass.getMethods();
//        for (Method declaredMethod : declaredMethods) {
//            System.out.println(declaredMethod.getName());
//        }
//
//        //Вызываем полученный метод для конкретного объекта через рефлексию
//        System.out.println("---------------------");
//        Method getName = boxClass.getMethod("getName");
//        Object invoke = getName.invoke(whitebox);
//        System.out.println(invoke);
//
//        //Вызываем параметризованный метод конкретного объекта через рефлексию
//        System.out.println("---------------------");
//        Method setSize = boxClass.getMethod("setSize", int.class);
//        setSize.invoke(whitebox, 20);
//        System.out.println(whitebox);
//
//        //Вызываем приватный метод конкретного объекта через рефлексию
//        System.out.println("---------------------");
//        Method validateSmt = boxClass.getDeclaredMethod("validateSmt", boolean.class);
//        validateSmt.setAccessible(true);
//        Object invoke1 = validateSmt.invoke(whitebox, true);
//        System.out.println(invoke1);
//
//        //Работа с конструкторами
//        System.out.println("---------------------");
//        System.out.println("Конструкторы:");
//        Constructor<?>[] constructors = boxClass.getConstructors();
//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructor.getName());
//            System.out.println(constructor.getParameterCount());
//        }
//        Constructor<Box> constructor = boxClass.getConstructor(Long.class, String.class, int.class);
//        Box createdManually = constructor.newInstance(15l, "Created manually", 80);
//        System.out.println(createdManually);
    }
}