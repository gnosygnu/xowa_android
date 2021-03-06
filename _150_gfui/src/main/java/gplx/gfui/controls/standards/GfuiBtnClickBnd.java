package gplx.gfui.controls.standards; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.ipts.*; import gplx.gfui.controls.elems.*;
import gplx.core.interfaces.*;
class GfuiBtnClickBnd implements InjectAble, Gfo_invk {
	public void Inject(Object owner) {
		GfuiElem elem = GfuiElem_.cast(owner);
		IptBnd_.cmd_(IptCfg_.Null, elem, GfuiElemKeys.ActionExec_cmd, IptKey_.Enter, IptKey_.Space);
		IptBnd_.cmd_(IptCfg_.Null, elem, GfuiElemKeys.Focus_cmd, IptMouseBtn_.Left);
		IptBnd_.ipt_to_(IptCfg_.Null, elem, this, ExecMouseUp_cmd, IptEventType_.MouseUp, IptMouseBtn_.Left);
	}
	void ExecMouseUp(IptEventData iptData) {
		GfuiElem elem = GfuiElem_.cast(iptData.Sender());
		int x = iptData.MousePos().X(), y = iptData.MousePos().Y();
		SizeAdp buttonSize = elem.Size();
		if (	x >= 0 && x <= buttonSize.Width()
			&&	y >= 0 && y <= buttonSize.Height())
			elem.Click();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, ExecMouseUp_cmd))		ExecMouseUp(IptEventData.ctx_(ctx, m));
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}	static final    String ExecMouseUp_cmd = "ExecMouseUp";
	public static final    GfuiBtnClickBnd Instance = new GfuiBtnClickBnd(); GfuiBtnClickBnd() {}
}
