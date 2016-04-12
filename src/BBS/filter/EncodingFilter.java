package BBS.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
