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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiStackBaseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetEmployee()throws Exception {

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


	@Test
	public void should_return_0_4_employee_when_give_a_page_and_pagesize()throws Exception {

		String  result=this.mockMvc.perform(get("/employees?page=2&pageSize=2")).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		JSONArray jsonArray1 = new JSONArray(result);
		Assertions.assertEquals(3,jsonArray1.getJSONObject(0).get("id"));
		Assertions.assertEquals(4,jsonArray1.getJSONObject(1).get("id"));

//		this.mockMvc.perform(post("/employees", employee)).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void should_return_man_employee_when_give_gender_is_man()throws Exception {

		String  result=this.mockMvc.perform(get("/employees?gender=flmale")).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
		JSONArray jsonArray1 = new JSONArray(result);
		Assertions.assertEquals("flmale",jsonArray1.getJSONObject(0).get("gender"));

//		this.mockMvc.perform(post("/employees", employee)).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated());

	}
	@Test
	public void should_return_employee_when_give_employee_id_is_id()throws Exception {

		String  result=this.mockMvc.perform(get("/employees/"+1)).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
		JSONArray jsonArray1 = new JSONArray(result);
		Assertions.assertEquals(1,jsonArray1.getJSONObject(0).get("id"));

//		this.mockMvc.perform(post("/employees", employee)).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void postEmployeeTest()throws Exception {
		Employee employee = new Employee(5, "dd", 33, "man",100);
		JSONObject jsonObject = new JSONObject(employee);

		this.mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonObject.toString())).andExpect(status().isCreated());


	}
}
