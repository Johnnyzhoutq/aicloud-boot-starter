package com.aicloud.boot.security.configuration;
/**
 * @author 周天琪
 * @Date 校验日期：2018.01.10
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AicloudAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		/**
		 * 登录页面填写的userName、password封装的实体   与  aicloudCustomUserDetailsService接口根据你输入的userName从数据库获取的实例比较
		 * 如果是同一个玩意，就让你验证通过了，你就能跳到这里
		 * 不是同一个玩意，你还来这里干嘛
		*/
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 * 上面的英文很拽 实际上就是根据你的角色把你转到对应的主界面
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isDba(roles)) {
			url = "/db";
		} else if (isAdmin(roles)) {
			url = "/admin/admin_homepage";
		} else if (isUser(roles)) {
			url = "/user/user_homepage";
		} else {
			url = "/deny";
		}

		return url;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	private boolean isDba(List<String> roles) {
		if (roles.contains("ROLE_SUPER")) {
			return true;
		}
		return false;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}