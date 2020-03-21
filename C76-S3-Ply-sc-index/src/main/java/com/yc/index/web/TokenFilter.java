package com.yc.index.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TokenFilter extends ZuulFilter{

	@Override
	//判断是否执行该过滤器 true开 false关
	public boolean shouldFilter() {
		return true;
	}

	@Override
	//所要执行的具体过滤动作
	public Object run() throws ZuulException {
		//获取zuul上下文对象
		RequestContext requestContext = RequestContext.getCurrentContext();
		//获取servlet请求
		HttpServletRequest req = requestContext.getRequest();
		//获取令牌
		String token = req.getParameter("token");
		if(token == null) {
			//设置是否发送zull响应
			requestContext.setSendZuulResponse(false);
			//设置结果码
			requestContext.setResponseStatusCode(401);
			//设置响应信息
			requestContext.setResponseBody("{\"result\":\"accessToken is empty!\"}");
		}
		return null;
	}

	@Override
	//pre前置，post后置，route路由中，error错误
	public String filterType() {
		return "pre";
	}

	@Override
	//拦截顺序，值越小优先级越高 0表示第一个
	public int filterOrder() {
		return 0;
	}

}
