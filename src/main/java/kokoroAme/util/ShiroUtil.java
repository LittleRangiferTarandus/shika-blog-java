package kokoroAme.util;

import org.apache.shiro.SecurityUtils;

import kokoroAme.shiro.AccountProfile;

public class ShiroUtil {
	public static AccountProfile getProfile() {
		return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
	}
}
