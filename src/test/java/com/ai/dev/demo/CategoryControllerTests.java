package com.ai.dev.demo;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CategoryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void categoryCrudAndSave() throws Exception {
		// 新增
		MvcResult createResult = mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"CATEGORY-001\",\"name\":\"测试类别\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.code").value("CATEGORY-001"))
				.andExpect(jsonPath("$.name").value("测试类别"))
				.andReturn();

		String createJson = createResult.getResponse().getContentAsString();
		long id = ((Number) JsonPath.read(createJson, "$.id")).longValue();

		// 查询详情
		mockMvc.perform(get("/category/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("CATEGORY-001"));

		// 列表查询
		mockMvc.perform(get("/category/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasItem(hasEntry("code", "CATEGORY-001"))));

		// 修改
		mockMvc.perform(put("/category/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"更新后的类别\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("更新后的类别"));

		// 保存接口更新已有记录
		mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":" + id + ",\"code\":\"CATEGORY-001\",\"name\":\"保存更新类别\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("保存更新类别"));

		// 删除
		mockMvc.perform(delete("/category/{id}", id))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));

		mockMvc.perform(get("/category/{id}", id))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	void saveRequiresCodeAndName() throws Exception {
		mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"缺少编码\"}"))
				.andExpect(status().isBadRequest());

		mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"CATEGORY-002\"}"))
				.andExpect(status().isBadRequest());
	}

}
