package gplx;
public class Gfo_invk_cmd {
	private final    Gfo_invk itm; private final    String cmd; private final    GfoMsg msg;		
	public Gfo_invk_cmd(Gfo_invk itm, String cmd, GfoMsg msg) {
		this.itm = itm; this.cmd = cmd; this.msg = msg;
	}
	public Object Exec()								{return itm.Invk(GfsCtx.Instance, 0, cmd, msg);}
	public Object Exec_by_ctx(GfsCtx ctx, GfoMsg msg)	{return itm.Invk(ctx, 0, cmd, msg);}

	public static final    Gfo_invk_cmd Noop = new Gfo_invk_cmd(Gfo_invk_.Noop, "", GfoMsg_.Null);
        public static Gfo_invk_cmd New_by_key(Gfo_invk itm, String cmd) {return New_by_val(itm, cmd, null);}
        public static Gfo_invk_cmd New_by_val(Gfo_invk itm, String cmd, Object val) {
		return new Gfo_invk_cmd(itm, cmd, (val == null) ? GfoMsg_.Null : GfoMsg_.new_parse_(cmd).Add("v", val));
	}
}
