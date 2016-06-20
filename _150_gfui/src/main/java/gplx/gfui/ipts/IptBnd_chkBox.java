package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
import gplx.core.interfaces.*; import gplx.gfui.controls.standards.*;
public class IptBnd_chkBox implements InjectAble, Gfo_evt_itm {
	public Gfo_evt_mgr Evt_mgr() {if (evt_mgr == null) evt_mgr = new Gfo_evt_mgr(this); return evt_mgr;} Gfo_evt_mgr evt_mgr;
	public void Inject(Object owner) {
		chkBox = GfuiChkBox_.cast(owner);
		Gfo_evt_mgr_.Sub(chkBox, "Check_end", this, setCmd);
		Gfo_evt_mgr_.Sub_same(fwd, setEvt, this);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, setCmd))
			Gfo_invk_.Invk_by_val(invkAble, setCmd, chkBox.Val());
		else if	(ctx.Match(k, setEvt)) {
			boolean v = m.ReadBool(msgArg);
			chkBox.Val_sync(v);
		}
		else	return Gfo_invk_.Rv_unhandled;
		return Gfo_invk_.Rv_handled;
	}
	Gfo_evt_itm fwd; Gfo_invk invkAble; String setCmd, setEvt, msgArg; GfuiChkBox chkBox;
	public static IptBnd_chkBox new_(Gfo_evt_itm src, String setCmd, String setEvt, String msgArg) {
		IptBnd_chkBox rv = new IptBnd_chkBox();
		rv.fwd = src; rv.invkAble = (Gfo_invk)src; rv.setCmd = setCmd; rv.setEvt = setEvt; rv.msgArg = msgArg;
		return rv;
	}
}
