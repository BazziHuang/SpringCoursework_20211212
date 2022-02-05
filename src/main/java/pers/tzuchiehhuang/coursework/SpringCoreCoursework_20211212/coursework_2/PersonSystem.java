package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.MutiplePersonsException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonFormatException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonNotFoundException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity.Person;

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
					System.out.println("Please entering 0~7, or 8 to call manu, try again!");
				} else {
					System.out.println("Exit system.");
					flag = false;
				}
				break;
			}
		} catch (PersonFormatException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Input error happened, please try again!");
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
		Integer year = sc2.nextInt();
		Integer month = sc2.nextInt();
		Integer day = sc2.nextInt();
		try {
			if (personController.addPerson(name, year, month, day)) {
				System.out.println("Add person " + name + " success!");
			} else {
				System.out.println(name + " is already exists, failing to create data!");
			}
		} catch (PersonFormatException e) {
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
	private void printPersonByName() throws PersonFormatException {
		System.out.println("3. Print person's detail by name.");
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		try {
			List<Person> people = personController.getPersonsByName(name);
			printInfo(people);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// 4
	private void printPersonByBirthday(){
		System.out.println("4. Print person's detail by birthday.");
		System.out.print("Please enter birthday(mm dd): ");
		Scanner sc = new Scanner(System.in);
		Integer month = sc.nextInt();
		Integer day = sc.nextInt();
		try {
			List<Person> people = personController.getPersonsByBirth(month, day);
			printInfo(people);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// 5
	private void printPersonByAge() throws PersonFormatException{
		System.out.println("5. Print person's detail by age.");
		System.out.print("Please enter age: ");
		Scanner sc = new Scanner(System.in);
		Integer age = sc.nextInt();
		System.out.print("You want to find older, younger, or the same age? (o/y/S) ");
		Scanner sc2 = new Scanner(System.in);
		String option = sc2.next();
		AgeOptions ageOption = null;
		if (option.equalsIgnoreCase("o"))
			ageOption = AgeOptions.OLDERAGE;
		else if (option.equalsIgnoreCase("y"))
			ageOption = AgeOptions.YOUNGERAGE;
		else {
			ageOption = AgeOptions.SAMEAGE;
		}
		try {
			List<Person> people = personController.getPersonsByAge(age, ageOption);
			printInfo(people);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// 6
	private void updatePersonBirthday() throws PersonFormatException {
		System.out.println("6. Update person's birthday.");
		System.out.print("Please enter name: ");
		Scanner sc1 = new Scanner(System.in);
		String name = sc1.nextLine();
		System.out.print("Please enter new birthday(yyyy mm dd): ");
		Scanner sc2 = new Scanner(System.in);
		Integer year = sc2.nextInt();
		Integer month = sc2.nextInt();
		Integer day = sc2.nextInt();
		try {
			if (personController.updatePersonBirthday(name, year, month, day))
				System.out.println("Update " + name + "'s birthday success!");
			else {
				System.out.println("Update " + name + "'s birthday fail!");
			}
		} catch (MutiplePersonsException e) {
			System.out.println(e.getMessage());
			try {
				List<Person> people = personController.getPersonsByName(name);
				printInfo(people);
			} catch (PersonNotFoundException e2) {
				System.out.println(e2.getMessage());
			}
			updatePersonBirthday(name, year, month, day); // call advance method to distinct person with same name.
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updatePersonBirthday(String name, Integer newYear, Integer newMonth, Integer newDay)
			throws PersonFormatException {
		System.out.print("Please enter " + name + "'s origin birthday(yyyy mm dd): ");
		Scanner sc = new Scanner(System.in);
		Integer oldYear = sc.nextInt();
		Integer oldMonth = sc.nextInt();
		Integer oldDay = sc.nextInt();
		try {
			if (personController.updatePersonBirthday(name, oldYear, oldMonth, oldDay, newYear, newMonth, newDay)) {
				System.out.println("Update " + name + "'s birthday success!");
			} else {
				System.out.println(name + " not found! Update " + name + "'s birthday fail!");
			}
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// 7
	private void deletePersonByName() throws PersonFormatException {
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

			try {
				List<Person> people = personController.getPersonsByName(name);
				printInfo(people);
			} catch (PersonNotFoundException e2) {
				System.out.println(e2.getMessage());
			}

			System.out.print("You want to delete all persons named " + name + "? (y/N)");
			Scanner sc2 = new Scanner(System.in);
			String option = sc2.next();
			if (option.equalsIgnoreCase("y")) {
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
		Integer year = sc1.nextInt();
		Integer month = sc1.nextInt();
		Integer day = sc1.nextInt();
		try {
			if (personController.deletePerson(name, year, month, day)) {
				System.out.println("Delete " + name + " success!");
			} else {
				System.out.println("Delete " + name + " fail!");
			}
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deleteAllPersonByName(String name) throws PersonFormatException {
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

	private void printInfo(List<Person> people) {
		System.out.println("+-----------------------------------------------+");
		System.out.println("|        name        |   age   |    birthday    |");
		System.out.println("+--------------------+---------+----------------+");
		people.forEach(p -> {
			LocalDate birthday = p.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.printf("| %-18s | %4d    | %12s   |\n", p.getName(), p.getAge(), birthday);
			System.out.println("+--------------------+---------+----------------+");
		});
	}

	public static void main(String[] args) {
		PersonSystem personSystem = new PersonSystem();
		personSystem.start();
	}

}
