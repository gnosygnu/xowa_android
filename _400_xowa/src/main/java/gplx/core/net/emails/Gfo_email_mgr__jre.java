package gplx.core.net.emails; import gplx.*; import gplx.core.*; import gplx.core.net.*;
import gplx.langs.htmls.encoders.*;
//#{Gfo_email_mgr__jre
import java.awt.Desktop;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
class Gfo_email_mgr__jre implements Gfo_email_mgr {
	public void Send(String to, String subject, String body) {
		try {
			Gfo_url_encoder url_encoder = gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_wnt;
			subject = url_encoder.Encode_str(subject);
			body = url_encoder.Encode_str(body);
			body = String_.Replace(body, "`", "%60");
			String url_str = "mailto:gnosygnu+xowa_xolog@gmail.com?subject=" + subject + "&body=" + body;
			URI uri = new URI(url_str);
			Desktop.getDesktop().mail(uri);
		} catch (Exception e) {
			Gfo_log_.Instance.Warn("email failed", "subject", subject, "body", body, "err", Err_.Message_gplx_log(e));
		}
	}
}
//#}
