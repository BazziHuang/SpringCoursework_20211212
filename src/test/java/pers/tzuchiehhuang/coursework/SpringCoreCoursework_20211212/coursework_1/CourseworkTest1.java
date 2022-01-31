package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CourseworkTest1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
		Student jack = ctx.getBean("s1", Student.class);
		// System.out.println(jack);
		Student mary = ctx.getBean("s2", Student.class);
		// System.out.println(marry);

		Teacher teacher1 = ctx.getBean("t1", Teacher.class);
		// System.out.println(teacher1);
		Teacher teacher2 = ctx.getBean("t2", Teacher.class);
		// System.out.println(teacher2);
		
		List<String> teachers= Stream.of(teacher1, teacher2).filter(t ->{
			for(Clazz c: mary.getClazzs()) {
				if(t.getClazzs().contains(c))
					return true;
			}
			return false;
		}).map(Teacher::getName).collect(Collectors.toList());
		
		if(teachers.size()==0) {
			System.out.println("There are no "+ mary.getName() +"'s teacher.");
		}else if(teachers.size()==1) {
			System.out.println(mary.getName() + "'s teacher is "+ teachers +".");
		}else if (teachers.size()>1) {
			System.out.println(mary.getName() + "'s teachers are "+ teachers +".");
		}
		
		//Homework: use java8 as solution.
		/*
		// 請問 mary 的老師有誰 ? 印出 name
				System.out.println(mary.getName() + "的課程：" + mary.getCourses());
				Teacher[] teachers = {ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class)};
				Set<Teacher> teachers2 = new LinkedHashSet<>();
				for(Teacher teacher : teachers) {
					clazz_loop:
					for(Courses cla1 : teacher.getCourses()) {
						for(Courses cla2 : mary.getCourses()) {
							if(cla1.getId() == cla2.getId()) {
								//System.out.println(teacher.getName());
								teachers2.add(teacher);
								break clazz_loop;
							}
						}
					}
				}
				System.out.println(mary.getName() + "的老師(物件)：" + teachers2);
				System.out.println(mary.getName() + "的老師(名字)：" + teachers2.stream()
																.map(Teacher::getName)
																.collect(Collectors.toSet()));
		*/

	}

}
