package org.mix3.openid;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.openid4java.discovery.DiscoveryInformation;

@SuppressWarnings("serial")
public class MySession extends WebSession{
	private DiscoveryInformation discoveryInformation;
	private String userId;
	
	public MySession(Request request) {
		super(request);
	}
	
	public DiscoveryInformation getDiscoveryInformation() {
		return discoveryInformation;
	}
	public void setDiscoveryInformation(DiscoveryInformation discoveryInformation) {
		this.discoveryInformation = discoveryInformation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
