import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        boolean inputMode = true;

        while (inputMode) {
            System.out.println("Введите Ф.И.О, возраст и пол (M/F) через пробел. Для выхода нажмите 'Q':");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                inputMode = false;
            } else {
                String[] tokens = input.split(" ");
                String name = tokens[0];
                String surname = tokens[1];
                String patronymic = tokens[2];
                int age = Integer.parseInt(tokens[3]);
                Gender gender = Gender.valueOf(tokens[4]);
                Person person = new Person(name, surname, patronymic, age, gender);
                people.add(person);
            }
        }

        System.out.println("Список введенных людей:");
        for (Person person : people) {
            System.out.println(person);
        }

        System.out.println("Выберите режим сортировки: 1 - по возрасту и полу, 2 - завершить приложение");
        int choice = scanner.nextInt();
        if (choice == 1) {
            Collections.sort(people, new AgeGenderComparator());
            System.out.println("Список людей, отсортированных по возрасту и полу:");
            for (Person person : people) {
                System.out.println(person);
            }
        }

        System.out.println("Программа завершена.");
    }

}

enum Gender {
    M, F
}

class Person {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final int age;
    private final Gender gender;

    public Person(String name, String surname, String patronymic, int age, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ". " + age + gender;
    }
}

class AgeGenderComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int ageComparison = Integer.compare(person1.getAge(), person2.getAge());
        if (ageComparison != 0) {
            return ageComparison;
        } else {
            return person1.getGender().compareTo(person2.getGender());
        }
    }

}