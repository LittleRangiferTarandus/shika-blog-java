package kokoroAme.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import kokoroAme.common.Result;
import kokoroAme.util.JwtUtils;

@Component
public class JwtFilter extends AuthenticatingFilter{
	@Autowired
	JwtUtils jwtUtils;
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String jwt = httpServletRequest.getHeader("Authorization");
		if(StringUtils.isEmpty(jwt)) {
			return null;
		}
		return new JwtToken(jwt);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String jwt = httpServletRequest.getHeader("Authorization");
		if(StringUtils.isEmpty(jwt)) {
			return true;
		}else {
			//У��jwt
			Claims claim = jwtUtils.getClaimByToken(jwt);
			if(claim==null||jwtUtils.isTokenExpired(claim.getExpiration())) {
				throw new ExpiredCredentialsException("token��ʧЧ�������µ�¼");
			}
			//ִ�е�¼
			return executeLogin(request, response);
		}
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		Throwable throwable =  e.getCause() == null ? e : e.getCause();
		Result result = Result.fail(throwable.getMessage());
		String json = JSONUtil.toJsonStr(result);
		try {
			httpServletResponse.getWriter().write(json);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return false;
	}
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);

	}
}
