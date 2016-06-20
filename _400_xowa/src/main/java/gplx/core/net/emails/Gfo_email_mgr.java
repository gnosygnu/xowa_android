package gplx.core.net.emails; import gplx.*; import gplx.core.*; import gplx.core.net.*;
public interface Gfo_email_mgr {
	void Send(String to, String subject, String body);
}
class Gfo_email_mgr__noop implements Gfo_email_mgr {
	public void Send(String to, String subject, String body) {}
}
