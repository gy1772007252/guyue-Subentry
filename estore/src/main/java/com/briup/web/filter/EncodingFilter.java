package com.briup.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(value = "/*",initParams = {@WebInitParam(name = "encoding",value = "UTF-8")},
		dispatcherTypes = {DispatcherType.REQUEST})
public class EncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null) {
			encoding = "UTF-8";
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("test/html;charset=" + encoding);
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
	}

}
