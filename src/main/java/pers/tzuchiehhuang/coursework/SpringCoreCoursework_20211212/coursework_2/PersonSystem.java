package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.MutiplePersonsException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonNotFoundException;

public class PersonSystem {

	ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext2.xml");
	PersonController personController = cxt.getBean("personController", PersonController.class);
	private boolean flag = true;
	private int count = 0;

	private void menu() {
		System.out.println("=========================================");
		System.out.println("1. Create new person data.");
		System.out.println("2. Print all persons.");
		System.out.println("3. Print person's detail by name.");
		System.out.println("4. Print person's detail by birthday.");
		System.out.println("5. Print person's detail by age.");
		System.out.println("6. Update person's birthday.");
		System.out.println("7. Delete person.");
		System.out.println("8. Call menu.");
		System.out.println("0. Exist.");
		System.out.println("=========================================");
		System.out.print("Please choose: ");
		runController();
	}

	private void runController() {
		try {
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				createPerson();
				break;
			case 2:
				printPersonList();
				break;
			case 3:
				printPersonByName();
				break;
			case 4:
				printPersonByBirthday();
				break;
			case 5:
				printPersonByAge();
				break;
			case 6:
				updatePersonBirthday();
				break;
			case 7:
				deletePersonByName();
				break;
			case 8:
				menu();
				break;
			case 0:
				System.out.println("Exit system.");
				flag = false;
				break;
			default:
				if (++count < 3) {
					System.out.print("Please entering 0~7, or 8 to call manu, try again!");
				} else {
					System.out.println("Exit system.");
					flag = false;
				}
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Please entering 0~7, or 8 to call manu, try again!");
		}
	}

	private void start() {
		menu();
		while (flag) {
			System.out.print("Please choose 0~7 or 8 to call manu: ");
			runController();
		}
	}

	// 1
	private void createPerson() {
		System.out.println("1. Create new person data.");
		System.out.print("Please enter new person's name: ");
		Scanner sc1 = new Scanner(System.in);
		String name = sc1.nextLine();
		System.out.print("Please enter new person's birthday (yyyy mm dd): ");
		Scanner sc2 = new Scanner(System.in);
		int year = sc2.nextInt();
		int month = sc2.nextInt();
		int day = sc2.nextInt();
		try {
			if (personController.addPerson(name, year, month, day)) {
				System.out.println("Add person " + name + " success!");
			} else {
				System.out.println(name + " is already exists! Adding fail!");
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	// 2
	private void printPersonList() {
		System.out.println("2. Print all persons.");
		personController.printAllPersons();
		System.out.println();
	}

	// 3
	private void printPersonByName() {
		System.out.println("3. Print person's detail by name.");
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		List<Person> people = personController.getPersonsByName(name);
		printInfo(people, name + " not exists!");
	}

	// 4
	private void printPersonByBirthday() {
		System.out.println("4. Print person's detail by birthday.");
		System.out.print("Please enter birthday(mm dd): ");
		Scanner sc = new Scanner(System.in);
		int month = sc.nextInt();
		int day = sc.nextInt();
		List<Person> people = personController.getPersonsByBirth(month, day);
		printInfo(people, "No one's birthday at " + month + "/" + day + " !");
	}

	// 5
	private void printPersonByAge() {
		System.out.println("5. Print person's detail by age.");
		System.out.print("Please enter age: ");
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		System.out.print("You want to find older or younger? (o/y) ");
		Scanner sc2 = new Scanner(System.in);
		String option = sc2.next();
		int value = 0;
		if (option.equalsIgnoreCase("o"))
			value = 1;
		else if (option.equalsIgnoreCase("y"))
			value = -1;
		else {
			value = 0;
		}
		List<Person> people = personController.getPersonsByAge(age, value);
		printInfo(people, "Person not found!");
	}

	// 6
	private void updatePersonBirthday() {
		System.out.println("6. Update person's birthday.");
		System.out.print("Please enter name: ");
		Scanner sc1 = new Scanner(System.in);
		String name = sc1.nextLine();
		System.out.print("Please enter new birthday(yyyy mm dd): ");
		Scanner sc2 = new Scanner(System.in);
		int year = sc2.nextInt();
		int month = sc2.nextInt();
		int day = sc2.nextInt();
		try {
			if (personController.updatePersonBirthday(name, year, month, day))
				System.out.println("Update " + name + "'s birthday success!");
			else {
				System.out.println("Update " + name + "'s birthday fail!");
			}
		} catch (MutiplePersonsException e) {
			System.out.println(e.getMessage());
			List<Person> people = personController.getPersonsByName(name);
			printInfo(people, name + " not exists!");
			updatePersonBirthday(name, year, month, day);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updatePersonBirthday(String name, int newYear, int newMonth, int newDay) {
		System.out.print("Please enter " + name + "'s origin birthday(yyyy mm dd): ");
		Scanner sc = new Scanner(System.in);
		int oldYear = sc.nextInt();
		int oldMonth = sc.nextInt();
		int oldDay = sc.nextInt();
		if (personController.updatePersonBirthday(name, oldYear, oldMonth, oldDay, newYear, newMonth, newDay)) {
			System.out.println("Rivise " + name + "'s birthday success!");
		} else {
			System.out.println(name+ " not found! Rivise " + name + "'s birthday fail!");
		}
	}

	// 7
	private void deletePersonByName() {
		System.out.println("7. Delete person.");
		System.out.print("Please enter name: ");
		Scanner sc1 = new Scanner(System.in);
		String name = sc1.nextLine();
		try {
			if (personController.deletePerson(name)) {
				System.out.println("Delete " + name + " success!");
			} else {
				System.out.println("Delete " + name + " fail!");
			}
		} catch (MutiplePersonsException e) {
			System.out.println(e.getMessage());
			List<Person> people = personController.getPersonsByName(name);
			printInfo(people, name + " not exists!");
			System.out.print("You want to delete all persons named " + name + "? (y/n)");
			Scanner sc2 = new Scanner(System.in);
			String option = sc2.next();
			if (option.equals("y")) {
				deleteAllPersonByName(name);
			} else {
				deletePersonByName(name);
			}
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deletePersonByName(String name) {
		System.out.print("Please enter " + name + "'s birthday(yyyy mm dd): ");
		Scanner sc1 = new Scanner(System.in);
		int year = sc1.nextInt();
		int month = sc1.nextInt();
		int day = sc1.nextInt();
		if (personController.deletePerson(name, year, month, day)) {
			System.out.println("Delete " + name + " success!");
		} else {
			System.out.println("Delete " + name + " fail!");
		}
	}

	private void deleteAllPersonByName(String name) {
		try {
			if (personController.deleteAllPersonsByName(name)) {
				System.out.println("Delete " + name + " success!");
			} else {
				System.out.println("Delete " + name + " fail!");
			}
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void printInfo(List<Person> people, String notFoundMessage) {

		if (!people.isEmpty()) {
			System.out.println("+-----------------------------------------------+");
			System.out.println("|        name        |   age   |    birthday    |");
			System.out.println("+--------------------+---------+----------------+");
			people.forEach(p -> {
				LocalDate birthday = p.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.printf("| %-18s | %4d    | %12s   |\n", p.getName(), p.getAge(), birthday);
				System.out.println("+--------------------+---------+----------------+");
			});
		} else {
			System.out.println(notFoundMessage);
		}
	}

	public static void main(String[] args) throws Exception {

		PersonSystem personSystem = new PersonSystem();
		personSystem.start();

	}

}
