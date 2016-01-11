package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public class IptBnd_chkBox implements InjectAble, GfoEvObj {
	public GfoEvMgr EvMgr() {if (evMgr == null) evMgr = GfoEvMgr.new_(this); return evMgr;} GfoEvMgr evMgr;
	public void Inject(Object owner) {
		chkBox = GfuiChkBox_.cast(owner);
		GfoEvMgr_.Sub(chkBox, "Check_end", this, setCmd);
		GfoEvMgr_.SubSame(fwd, setEvt, this);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, setCmd))
			GfoInvkAble_.InvkCmd_val(invkAble, setCmd, chkBox.Val());
		else if	(ctx.Match(k, setEvt)) {
			boolean v = m.ReadBool(msgArg);
			chkBox.Val_sync(v);
		}
		else	return GfoInvkAble_.Rv_unhandled;
		return GfoInvkAble_.Rv_handled;
	}
	GfoEvObj fwd; GfoInvkAble invkAble; String setCmd, setEvt, msgArg; GfuiChkBox chkBox;
	public static IptBnd_chkBox new_(GfoEvObj src, String setCmd, String setEvt, String msgArg) {
		IptBnd_chkBox rv = new IptBnd_chkBox();
		rv.fwd = src; rv.invkAble = GfoInvkAble_.cast(src); rv.setCmd = setCmd; rv.setEvt = setEvt; rv.msgArg = msgArg;
		return rv;
	}
}
