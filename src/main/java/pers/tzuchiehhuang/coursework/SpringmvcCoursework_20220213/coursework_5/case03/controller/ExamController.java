package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.entity.Exam;
import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.service.ExamService;

@Controller
@RequestMapping("/case03/exam")
public class ExamController {
	@Autowired
	private ExamService examService;
	
	@GetMapping("/")
	public String index(@ModelAttribute Exam exam, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("exams", examService.query());
		model.addAttribute("examSubjects", examService.queryExamSubjectList());
		return "case03/exam";
	}
	
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model) {
		Optional<Exam> optExam = examService.get(index);
		if(optExam.isPresent()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("exams", examService.query());
			model.addAttribute("examSubjects", examService.queryExamSubjectList());
			model.addAttribute("exam", optExam.get());
			return "case03/exam";
		}
		// 沒找到資料，應該要透過統一錯誤處理機制來進行...
		return "redirect:./";
	}
	
	@PostMapping("/")
	public String add(Exam exam) {
		examService.add(exam);
		return "redirect:./";
	}
	
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, Exam exam) {
		examService.update(index, exam);
		return "redirect:./";
	}
	
	@PutMapping("/{index}/exam_note")
	public String updateExamNote(@PathVariable("index") int index, Exam exam) {
		examService.updateExamNote(index, exam.getExamNote());
		return "redirect:../";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		examService.delete(index);
		return "redirect:./";
	}
	
	
}
