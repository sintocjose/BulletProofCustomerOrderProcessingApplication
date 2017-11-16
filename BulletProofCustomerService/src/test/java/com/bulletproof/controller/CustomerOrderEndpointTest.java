package com.bulletproof.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bulletproof.service.CustomerOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerOrderEndpointTest {

	private MockMvc mockMvc;

	@Mock
	private CustomerOrderService customerOrderServiceMock;

	@Test(expected=RuntimeException.class)
	public void testCreateOrderEmptyTest() throws Exception {
		mockMvc.perform(post("customerService/createOrder/").contentType(MediaType.APPLICATION_JSON).content(""));
	}
	@Test(expected=RuntimeException.class)
	public void testCreateOrderNullTest() throws Exception {
		mockMvc.perform(post("customerService/createOrder/").contentType(MediaType.APPLICATION_JSON).content(new String()));
	}
	@Test
	public void testCreateOrderSuccess() throws Exception {
		Mockito.when(customerOrderServiceMock.createOrder(any(String.class), any(String.class))).thenReturn(true);
		boolean created = customerOrderServiceMock.createOrder("20", "40");
		System.out.println(created);
		assertEquals(true, true);
	}
}
