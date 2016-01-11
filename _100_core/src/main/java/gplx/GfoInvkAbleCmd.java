package gplx;
public class GfoInvkAbleCmd {
	private GfoMsg m;
	public GfoInvkAble InvkAble() {return invkAble;} private GfoInvkAble invkAble;
	public String Cmd() {return cmd;} private String cmd;
	public Object Arg() {return arg;} private Object arg;
	public Object Invk() {
		return invkAble.Invk(GfsCtx.Instance, 0, cmd, m);
	}
	public static final GfoInvkAbleCmd Null = new GfoInvkAbleCmd();
        public static GfoInvkAbleCmd new_(GfoInvkAble invkAble, String cmd) {return arg_(invkAble, cmd, null);}
        public static GfoInvkAbleCmd arg_(GfoInvkAble invkAble, String cmd, Object arg) {
		GfoInvkAbleCmd rv = new GfoInvkAbleCmd();
		rv.invkAble = invkAble; rv.cmd = cmd; rv.arg = arg;
		rv.m = (arg == null) ? GfoMsg_.Null : GfoMsg_.new_parse_(cmd).Add("v", arg);
		return rv;
	}	GfoInvkAbleCmd() {}
}
