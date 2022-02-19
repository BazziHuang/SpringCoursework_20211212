package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case02.service.*;


@Controller
@RequestMapping("/case02/lotto")
public class LottoController {
	
	@Autowired
	private LottoService lottoService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("lottos", lottoService.getLottos());
		return "case02/show_lotto";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)   //這邊jsp呼叫post方法, 不能用@GetMapping
	public String add() {
		lottoService.addLotto();
		return "redirect:./";
	}
	
	@RequestMapping("/update/{index}")
	public String update(
			@PathVariable int index
			) {
		lottoService.updateLotto(index);
		return "redirect:../";
		
	}
	
	@RequestMapping("/delete/{index}")
	public String delete(
			@PathVariable int index
			) {
		lottoService.deleteLotto(index);
		return "redirect:../";
		
	}
	
	//********   Homework   ********  
	@RequestMapping("/data")
	public String data(RedirectAttributes attributes) {
		attributes.addFlashAttribute("lottosData",  lottoService.getLottosData());
		return "redirect:./";
	}
}
