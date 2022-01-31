package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.template;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoImpl implements EmpDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> queryAll() {
		String query="SELECT eid, ename, age, createtime from emp";
		List<Map<String, Object>> emps= jdbcTemplate.queryForList(query);
		return emps;
	}

}
