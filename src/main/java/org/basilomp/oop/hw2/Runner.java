package org.basilomp.oop.hw2;

import lombok.extern.slf4j.Slf4j;
import org.basilomp.oop.hw2.employees.Employee;
import org.basilomp.oop.hw2.employees.Position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Runner {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        List<Integer> toBeDistinctIntegers = Arrays.asList(1, 2, 3, 1, 5, 2, 7, 3, 9, 5);
        List<Employee> employees = Arrays.asList(new Employee("Basil", 47, Position.ENGINEER),
                new Employee("Alex", 35, Position.CEO),
                new Employee("Sergey", 58, Position.MANAGER),
                new Employee("Andrey", 99, Position.ENGINEER),
                new Employee("Mikhail", 18, Position.ENGINEER),
                new Employee("Pavel", 42, Position.ENGINEER));
        List<String> words = Arrays.asList("One", "Two", "Three", "Two", "Four", "Five", "Forty-two", "Six", "Seven", "Eight", "Nine");
        String lowerCaseWords = "apple orange plum apple grape grape apple lemon";

        System.out.println("1. Реализуйте удаление из листа всех дубликатов");
        System.out.println("Исходный список: " + Arrays.toString(toBeDistinctIntegers.toArray()));
        System.out.print("Список с уникальными элементами: ");
        toBeDistinctIntegers.stream().distinct().forEach(System.out::print);
        System.out.println();

        System.out.println("-------------------------------------------------");
        System.out.println("2. Найдите в списке целых чисел 3-е наибольшее число");
        integers.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().ifPresent(System.out::println);

        System.out.println("-------------------------------------------------");
        System.out.println("3. Нахождение 3-го наибольшего «уникального» числа в списке целых чисел");
        integers.stream().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst().ifPresent(System.out::println);

        System.out.println("-------------------------------------------------");
        System.out.println("4. необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста");
        List<String> engineerNames = employees.stream()
                .filter(e -> e.getPosition()
                        .equals(Position.ENGINEER))
                .sorted(Comparator.comparingInt(Employee::getAge)
                        .reversed())
                .limit(3)
                .map(Employee::getName)
                .collect(toUnmodifiableList());
        System.out.println(engineerNames);

        System.out.println("-------------------------------------------------");
        System.out.println("5. Посчитайте средний возраст сотрудников с должностью «Инженер».");
        //На выходе должно быть: 51.5
        double avgAge = employees.stream().filter(e -> e.getPosition().equals(Position.ENGINEER))
                .mapToInt(Employee::getAge)
                .average().orElse(0);
        System.out.println("Avg age among engineers: " + avgAge);


        System.out.println("-------------------------------------------------");
        System.out.println("6. Найдите в списке слов самое длинное");
        //На выходе должно быть: Forty-two
        words.stream()
                .max(Comparator.comparingInt(String::length))
                .ifPresent(System.out::println);


        System.out.println("-------------------------------------------------");
        System.out.println("7. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке");
        Map<String, Long> lowerCaseMap = Arrays.stream(lowerCaseWords.split(" "))
                .collect(groupingBy(Function.identity(), counting()));
        System.out.println(lowerCaseMap);

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        System.out.println("-------------------------------------------------");
        System.out.println("8. Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок");
        List<String> sortedWords = words.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(toList());
        System.out.println(sortedWords);

        System.out.println("-------------------------------------------------");
        System.out.println("9. Имеется массив строк, в каждой из которых лежит набор из 5 строк, " +
                           "разделенных пробелом, найдите среди всех слов самое длинное, " +
                           "если таких слов несколько, получите любое из них");
        String[] strs = {
                "one two three four five",
                "Urna iaculis bibendum praesent",
                "Josephine Bibi Alicia Kaur Usha Iqbal, Jianjun Qin, Santosh Mou",
                "six seven pneumonoultramicroscopicsilicovolcanoconiosis nine ten eleven",
        };

        Arrays.stream(strs)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .stream().findAny().ifPresent(System.out::println);
    }
}
