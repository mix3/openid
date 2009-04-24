package org.mix3.openid;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.mix3.openid.page.SignInPage;
import org.mix3.openid.page.VerifyPage;

public class WicketApplication extends WebApplication{
	@Override
	public Session newSession(Request request, org.apache.wicket.Response response) {
		return new MySession(request);
	}
	
	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("signin", SignInPage.class);
		mountBookmarkablePage("verify", VerifyPage.class);
	}

	public WicketApplication(){}
	
	public Class<? extends WebPage> getHomePage(){
		return SignInPage.class;
	}
}
