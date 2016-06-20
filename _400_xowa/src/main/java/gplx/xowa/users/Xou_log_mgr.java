package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
public class Xou_log_mgr implements Gfo_invk {
	public boolean Log_redlinks() {return log_redlinks;} private boolean log_redlinks;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_log_redlinks))			return Yn.To_str(log_redlinks);
		else if	(ctx.Match(k, Invk_log_redlinks_))			log_redlinks = m.ReadYn("v");
		return this;
	}
	public static final    String 
	  Invk_log_redlinks = "log_redlinks", Invk_log_redlinks_ = "log_redlinks_"
	;
}
