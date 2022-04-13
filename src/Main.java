import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long youngPeopleCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("\nКоличество несовершеннолетних людей в городе: " + youngPeopleCount + "человек.\n");

        List<String> surnames = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("В списке призывников " + surnames.size() + " фамилий.");
        System.out.println("Первые 18 фамилий из них:");
        for (int i = 0; i < 20; i++) {
            System.out.print(surnames.get(i) + ", ");
        }
        System.out.println("\n");

        List<Person> workers = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 65)
                .filter(person -> !(person.getSex().equals(Sex.WOMAN) && person.getAge() > 60))
                .sorted((Person p1, Person p2) -> p1.getFamily().compareTo(p2.getFamily()))
                .toList();
        System.out.println("В списке потенциально работоспособных людей с высшим образованием "
                + workers.size() + " человек.");
        System.out.println("Первые 10 человек из этого списка:");
        for (int i = 0; i < 10; i++) {
            System.out.println(workers.get(i));
        }
    }
}