package org.mix3.openid.page;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.mix3.openid.MySession;
import org.mix3.openid.utils.ConsumerManagerWrapper;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;

public class SignInPage extends WebPage{
	private class SignInForm extends Form<Void>{
		private TextField useridField;
		
		@Override
		protected void onSubmit() {
			MySession sess = (MySession)getSession();
			sess.setUserId(useridField.getInput());
			onClickSignIn();
		}

		public SignInForm(String id) {
			super(id);
			useridField = new TextField("userid", new Model<Serializable>());
			add(useridField);
		}
	}
	public SignInPage(){
		add(new SignInForm("form"));
	}
	
	private void onClickSignIn(){
		try {
			MySession sess = (MySession)getSession();
			ConsumerManager manager = ConsumerManagerWrapper.getInstance();
			List<DiscoveryInformation> discoveries = manager.discover(sess.getUserId());
//			List<DiscoveryInformation> discoveries = manager.discover(userid);
			DiscoveryInformation discovered = manager.associate(discoveries);
			sess.setDiscoveryInformation(discovered);
			String returnURL = RequestUtils.toAbsolutePath("verify");
			AuthRequest authReq = manager.authenticate(discovered, returnURL);
			if(authReq != null){
				getRequestCycle().setRequestTarget(new RedirectRequestTarget(authReq.getDestinationUrl(true)));
			}
		} catch (DiscoveryException e) {
			e.printStackTrace();
		} catch (MessageException e) {
			e.printStackTrace();
		} catch (ConsumerException e) {
			e.printStackTrace();
		}
	}
}
