package com.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 请求(不包括登录/退出)过滤器：
 * 1、验证用户是否已登录；
 * 2、若用户已登录，验证用户是否有访问权限；
 */
public class RequestFilter implements Filter
{
	/** 忽视的请求 */
	private String[] ignoreActions;

	/** 
	 * 不需过滤的静态资源后缀，在web.xml中配置 
	 */
	/* private String excludeUrlSuffix; */

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 字符乱码处理
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

//		String method = request.getMethod();
//		if (method.equals("OPTIONS"))
//		{
//			response.setStatus(200);
//			return;
//		}

		// Access-Control-Allow-Origin解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		// response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");

		// 去掉jsessionid
		// if (request.isRequestedSessionIdFromURL())
		// {
		// HttpSession session = request.getSession();
		// if (session != null)
		// {
		// session.invalidate();
		// }
		// }
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(response) {
			public String encodeUrl(String url)
			{
				return url;
			}

			public String encodeURL(String url)
			{
				return url;
			}

			public String encodeRedirectUrl(String url)
			{
				return url;
			}

			public String encodeRedirectURL(String url)
			{
				return url;
			}
		};
		//
		// String URI = request.getRequestURI();
		//
		// 忽视请求，直接放行
		// for (String ignoreAction : ignoreActions)
		// {
		// if (URI.indexOf(ignoreAction) != -1)
		// {
		// chain.doFilter(request, wrappedResponse);
		// return;
		// }
		// }

		// token是否有效
		// String token = request.getParameter("token");
		// if (token == null || !AgentCache.tokenEffective(token))
		// {
		// ResultJson result = new ResultJson();
		// response.getWriter()
		// .write(result.pushError(request, ResMessage.TokenExpirtime.code,
		// ResMessage.getMessage(ResMessage.TokenExpirtime.code)));
		// return;
		// }

		chain.doFilter(request, wrappedResponse);
	}

	public void init(FilterConfig config) throws ServletException
	{
		String ignoreAction = config.getInitParameter("ignoreAction");
		if (ignoreAction != null)
		{
			ignoreActions = ignoreAction.split(",");
		}

		// excludeUrlSuffix = config.getInitParameter("excludeUrlSuffix");
		// if (excludeUrlSuffix == null)
		// {
		// excludeUrlSuffix = "";
		// }
	}

	public void destroy()
	{
	}

	public boolean exclude(String uri)
	{
		// int index = uri.lastIndexOf(".");
		// if (index == -1)
		// {
		// return true;
		// }
		//
		// String subffix = uri.substring(index).toLowerCase();
		// if (excludeUrlSuffix.indexOf(subffix) == -1)
		// {
		// return true;
		// }
		return false;
	}
}
