package com.yc.order.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderAction {
	
	@GetMapping("order/way")
	public String order() {
		return "order";
	}

}
