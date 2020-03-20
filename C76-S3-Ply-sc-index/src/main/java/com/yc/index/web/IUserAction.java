package com.yc.index.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//服务名
@FeignClient(name = "sc-user",fallback = UserAction.class)
public interface IUserAction {
	
	//在sc-user内的GetMapping
	@GetMapping("user/way")
	public String user();

}
