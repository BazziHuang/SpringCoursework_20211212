package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case02.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	
	private List<Set<Integer>> lottos = new ArrayList<Set<Integer>>();
	
	public List<Set<Integer>> getLottos(){
		return lottos;
	}
	
	public void addLotto() {
		lottos.add(0,generateLotto());
	}
	
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
	}
	
	public void deleteLotto(int index) {
		lottos.remove(index);
	}
	
	//********   Homework   ********  
	public Map<Integer, Integer> getLottosData(){
		List<Integer> data= new ArrayList<Integer>();
		lottos.forEach(set ->{
			set.forEach(i -> data.add(i));
		});
		Map<Integer, Integer> map = data.stream().collect(Collectors.toMap(i -> i, j -> 1, (a,b) -> a+b)); 
		return map;
	}
	
	private Set<Integer> generateLotto() {
		
		Random r = new Random();
		
		Set<Integer> lotto = new LinkedHashSet<Integer>();
		
		while(lotto.size()<5) {
			lotto.add(r.nextInt(39)+1);
		}
		
		return lotto;
		
	}
	
	
}
