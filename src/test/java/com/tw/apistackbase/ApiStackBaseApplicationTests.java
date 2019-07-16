package com.tw.apistackbase;

import com.tw.apistackbase.controller.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiStackBaseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testgetEmployee()throws Exception {

		String  result=this.mockMvc.perform(get("/employees")).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		Employee employee=new Employee(1,"dd",20,"man",30);
		JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
		jsonObject.put("id",employee.getId());
		jsonObject.put("name", employee.getName());
		jsonObject.put("age",employee.getAge());
		jsonObject.put("gender", employee.getGender());
		jsonObject.put("salary",employee.getSalary());
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(jsonObject);
		JSONArray jsonArray1 = new JSONArray(result);

       Assertions.assertEquals(jsonArray.getJSONObject(0).get("id"),jsonArray1.getJSONObject(0).get("id"));
//		this.mockMvc.perform(post("/employees", employee)).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated());

	}




}
