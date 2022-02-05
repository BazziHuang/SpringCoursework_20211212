package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//This class used to read and write log.json

@Component
public class JsonDB {
	
	@Autowired
	private Gson gson;
	private final static Path PATH = Paths.get("src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_3/jdbc/logger/log.json");
	
	public List<MethodLog> getMethodLog() throws IOException{
		String jsonStr= Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type= new TypeToken<ArrayList<MethodLog>>() {}.getType();
		List<MethodLog> log= gson.fromJson(jsonStr, type);
		return log;
	}
	
	public void writeLogJson(List<MethodLog> log) throws IOException {
		String newJsonStr = gson.toJson(log);
		Files.write(PATH, newJsonStr.getBytes("UTF-8"));
	}
	
	//For testing
	public void readMethodLog() throws IOException {
		List<MethodLog> log= getMethodLog();
		System.out.println("+---------------+----------------------------------------+");
		System.out.println("|  method_name  |              log_timestamp             |");
		System.out.println("|---------------+----------------------------------------|");
		for(MethodLog ml: log) {
			System.out.printf("|   %-12s|      %14s      |\n", ml.getMethod(), ml.getTime().toString());
			System.out.println("+---------------+----------------------------------------+");
		}
		
	}


}
