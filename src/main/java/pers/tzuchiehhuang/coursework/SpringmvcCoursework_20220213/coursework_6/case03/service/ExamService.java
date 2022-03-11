package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity.Exam;
import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity.ExamPay;
import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity.ExamSlot;
import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity.ExamSubject;

@Service
public class ExamService {
	// CopyOnWriteArrayList 是執行緒安全的集合-適合多執行緒操作
	// ArrayList 是非執行緒安全的集合-適合單緒操作
	// 因為預設的情況下 Spring 會將 ExamController 定義為 Singleton（單一實體）
	// 所以可以使用 CopyOnWriteArrayList 來作為多執行緒資料操作的集合類
	private List<Exam> exams = new CopyOnWriteArrayList<Exam>();
	private List<ExamSubject> examSubjects = new CopyOnWriteArrayList<ExamSubject>();
	private List<ExamSlot> examSlots = new CopyOnWriteArrayList<ExamSlot>();
	private List<ExamPay> examPays = new CopyOnWriteArrayList<ExamPay>();
	
	{
		examSubjects.add(new ExamSubject("808","JavaSE 8 OCP I"));
		examSubjects.add(new ExamSubject("809","JavaSE 8 OCP II"));
		examSubjects.add(new ExamSubject("819","JavaSE 11 OCP"));
		examSubjects.add(new ExamSubject("900","JavaEE 7 OCP"));
		examSlots.add(new ExamSlot("上午", "A"));
		examSlots.add(new ExamSlot("下午", "B"));
		examSlots.add(new ExamSlot("晚上", "C"));
		examPays.add(new ExamPay("已繳費", true));
		examPays.add(new ExamPay("未繳費", false));
	}
	
	// query all (index)
	public Optional<List<Exam>> queryAll() {
		return Optional.ofNullable(exams);
	}
	
	public List<ExamSubject> queryExamSubjects(){
		return examSubjects;
	}
	
	public List<ExamSlot> queryExamSlots(){
		return examSlots;
	}
	
	public List<ExamPay> queryExamPays(){
		return examPays;
	}

	// query single
	public Optional<Exam> get(int index) {
		if (index < 0 || index >= exams.size() || exams.isEmpty()) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(exams.get(index));
	}

	// add
	public synchronized Boolean add(Exam exam) {
		int previousSize = exams.size();
		exams.add(exam);
		int nextSize = exams.size();
		return nextSize > previousSize;
	}

	// update
	public synchronized Boolean update(int index, Exam exam) {
		if (index < 0 || index >= exams.size() || exams.isEmpty()) {
			return false;
		}
		exams.set(index, exam);
		return true;
	}
	
	public synchronized Boolean updateExamNote(int index, String note) {
		if (index < 0 || index >= exams.size() || exams.isEmpty()) {
			return false;
		}
		Exam exam = exams.get(index);
		exam.setExamNote(note);
		exams.set(index, exam);
		return true;
	}

	// delete
	public synchronized Boolean delete(int index) {
		if (index < 0 || index >= exams.size() || exams.isEmpty()) {
			return false;
		}
		exams.remove(index);
		return true;
	}

}
