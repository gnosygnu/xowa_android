package gplx.core.threads; import gplx.*; import gplx.core.*;
public class Gfo_async_cmd_itm implements GfoInvkAble {
	private GfoInvkAble invk; private String invk_key; private GfoMsg msg = GfoMsg_.new_cast_("");
	public Gfo_async_cmd_itm Init(GfoInvkAble invk, String invk_key, Object... args) {
		this.invk = invk; this.invk_key = invk_key;
		msg.Args_reset();
		msg.Clear();
		int len = args.length; 
		for (int i = 0; i < len; i += 2) {
			String key = (String)args[i];
			Object val = args[i + 1];
			msg.Add(key, val);
		}
		return this;
	}
	public void Exec() {
		GfoInvkAble_.InvkCmd_msg(invk, invk_key, msg);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_exec))		Exec();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_exec = "exec";
	public static final Gfo_async_cmd_itm[] Ary_empty = new Gfo_async_cmd_itm[0];
}
