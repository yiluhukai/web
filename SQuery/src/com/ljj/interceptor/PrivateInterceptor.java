package com.ljj.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivateInterceptor extends MethodFilterInterceptor {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String doIntercept(ActionInvocation arg0) throws Exception {
		 Object admin=arg0.getInvocationContext().getSession().get("admin");
		    if(admin!=null)
		    {
		    	return arg0.invoke();
		    }
		    else
		    	arg0.getInvocationContext().getSession().put("error", "你没有权限操作");
			return "error";
	}

}
