package com.ai.dev.demo;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class ProductControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void listFiltersByBrandId() throws Exception {
		long brandIdA = createBrand("BRAND-A", "品牌A");
		long brandIdB = createBrand("BRAND-B", "品牌B");

		createProduct("PROD-A", brandIdA);
		createProduct("PROD-B", brandIdB);

		mockMvc.perform(get("/product/list").param("brandId", String.valueOf(brandIdA)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasItem(hasEntry("code", "PROD-A"))))
				.andExpect(jsonPath("$", not(hasItem(hasEntry("code", "PROD-B")))));

		mockMvc.perform(get("/product/list").param("brandId", String.valueOf(brandIdB)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasItem(hasEntry("code", "PROD-B"))))
				.andExpect(jsonPath("$", not(hasItem(hasEntry("code", "PROD-A")))));

		mockMvc.perform(get("/product/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasItem(hasEntry("code", "PROD-A"))))
				.andExpect(jsonPath("$", hasItem(hasEntry("code", "PROD-B"))));
	}

	@Test
	void saveReturnsBrandId() throws Exception {
		long brandId = createBrand("BRAND-C", "品牌C");

		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"品牌商品\",\"code\":\"PROD-C\",\"brandId\":" + brandId + "}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("PROD-C"))
				.andExpect(jsonPath("$.brandId").value(brandId));
	}

	@Test
	void saveAndGetReturnsVender() throws Exception {
		long brandId = createBrand("BRAND-G", "品牌G");

		MvcResult result = mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"供应商商品\",\"code\":\"PROD-G\",\"brandId\":" + brandId
						+ ",\"vender\":\"测试供应商\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("PROD-G"))
				.andExpect(jsonPath("$.vender").value("测试供应商"))
				.andReturn();

		long id = ((Number) JsonPath.read(result.getResponse().getContentAsString(), "$.id")).longValue();

		mockMvc.perform(get("/product/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vender").value("测试供应商"));
	}

	@Test
	void saveReturnsCostWithFourDecimalPlaces() throws Exception {
		long brandId = createBrand("BRAND-E", "品牌E");

		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"品牌商品\",\"code\":\"PROD-E\",\"brandId\":" + brandId
						+ ",\"cost\":12.3456}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("PROD-E"))
				.andExpect(jsonPath("$.brandId").value(brandId))
				.andExpect(jsonPath("$.cost").value(12.3456));
	}

	@Test
	void saveDefaultsCostToZero() throws Exception {
		long brandId = createBrand("BRAND-F", "品牌F");

		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"默认进价商品\",\"code\":\"PROD-F\",\"brandId\":" + brandId + "}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cost").value(0));
	}

	private long createBrand(String code, String name) throws Exception {
		MvcResult result = mockMvc.perform(post("/brand")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"" + code + "\",\"name\":\"" + name + "\"}"))
				.andExpect(status().isOk())
				.andReturn();

		return ((Number) JsonPath.read(result.getResponse().getContentAsString(), "$.id")).longValue();
	}

	private void createProduct(String code, long brandId) throws Exception {
		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"商品" + code + "\",\"code\":\"" + code + "\",\"brandId\":" + brandId + "}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.brandId").value(brandId));
	}

}
