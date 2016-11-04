package com.jl.test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
public class JacksonTest1 {

	public static void main(String[] args) throws IOException {
		Student student1 = new Student();
		student1.setId(5237);
		student1.setName("jingshou");
		student1.setBirthDay(new Date());

		ObjectMapper mapper = new ObjectMapper();

		// Convert object to JSON string
		String Json = mapper.writeValueAsString(student1);
		System.out.println("Change Object to JSON String: " + Json);

		// Convert Json string to Object
		Student student2 = mapper.readValue(Json, Student.class);
		System.out.println(student2);

		// Create a student list add it to Bj
		Student student3 = new Student();
		student3.setId(5117);
		student3.setName("saiya");
		student3.setBirthDay(new Date());

		List<Student> stuList = new ArrayList<Student>();
		stuList.add(student1);
		stuList.add(student3);

		Class bj = new Class();
		bj.setCname("五年二班");
		bj.setMembers(stuList);

		String json2 = mapper.writeValueAsString(bj);
		System.out.println("The JSON from Class is: " + json2);

		Class bj2 = mapper.readValue(json2, Class.class);
		System.out.println(bj2);

	}

}
