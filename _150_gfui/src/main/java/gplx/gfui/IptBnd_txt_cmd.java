package gplx.gfui; import gplx.*;
import gplx.core.type_xtns.*; import gplx.core.interfaces.*;
public class IptBnd_txt_cmd implements InjectAble, GfoInvkAble, GfoEvObj {
	public GfoEvMgr EvMgr() {if (evMgr == null) evMgr = GfoEvMgr.new_(this); return evMgr;} GfoEvMgr evMgr;
	public void Inject(Object owner) {
		txtBox = GfuiTextBox_.cast(owner);
		txtBox.TextAlignH_center_();
		IptBnd_.cmd_to_(IptCfg_.Null, txtBox, this, TxtBox_exec, IptKey_.Enter);
		GfoEvMgr_.SubSame(fwd, setEvt, this);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, TxtBox_exec))	GfoInvkAble_.InvkCmd_val(src, setCmd, cls.ParseOrNull(txtBox.Text()));
		else if	(ctx.Match(k, setEvt))			{
			int v = m.ReadInt("v");
			txtBox.Text_(cls.XtoUi(v, ClassXtnPool.Format_null));
		}
		else	return GfoInvkAble_.Rv_unhandled;
		return GfoInvkAble_.Rv_handled;
	}	static final String TxtBox_exec = "TxtBox_exec";
	GfuiTextBox txtBox; GfoInvkAble src; GfoEvObj fwd; String setCmd, setEvt; ClassXtn cls;		
	public static IptBnd_txt_cmd new_(GfoEvObj fwd, String setCmd, String setEvt, ClassXtn cls) {
		IptBnd_txt_cmd rv = new IptBnd_txt_cmd();
		rv.fwd = fwd; rv.src = GfoInvkAble_.cast(fwd);
		rv.setCmd = setCmd; rv.setEvt = setEvt; rv.cls = cls;
		return rv;
	}
}
