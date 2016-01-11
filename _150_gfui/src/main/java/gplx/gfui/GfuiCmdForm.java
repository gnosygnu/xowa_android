package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public class GfuiCmdForm implements GfoInvkAble, InjectAble {
	public void Inject(Object ownerObj) {
		GfuiElem owner = (GfuiElem)ownerObj;
		GfuiCmdForm.host_(key, cmdElem, owner);
	}
	public static GfuiCmdForm new_(String key, GfuiElem cmdElem) {
		GfuiCmdForm rv = new GfuiCmdForm();
		rv.key = key;
		rv.cmdElem = cmdElem;
		return rv;
	}	String key; GfuiElem cmdElem;
	public static GfuiWin host_(String key, GfuiElem cmdElem, GfuiElem hostElem) {
		GfuiWin rv = GfuiWin_.sub_(key, hostElem.OwnerWin()); rv.Size_(cmdElem.Size());
		cmdElem.Owner_(rv);
		GfuiCmdForm cmd = new GfuiCmdForm(); cmd.cmdForm = rv;

		IptBnd_.cmd_to_(IptCfg_.Null, rv, cmd, HideMe_cmd, IptKey_.Escape);
		IptBnd_.cmd_to_(IptCfg_.Null, hostElem, cmd, DoStuff, IptKey_.add_(IptKey_.Ctrl, IptKey_.Space), IptMouseBtn_.Right);
		return rv;
	}

	GfuiWin cmdForm;
	static final String DoStuff = "doStuff", HideMe_cmd = "HideMe";
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, DoStuff))					ActivateMe((GfuiElem)ctx.MsgSrc());
		else if	(ctx.Match(k, HideMe_cmd))				((GfuiWin)ctx.MsgSrc()).Hide();
		else return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	void ActivateMe(GfuiElem elem) {
		cmdForm.Pos_(elem.Pos().Op_add(elem.OwnerWin().Pos()));
		cmdForm.Show();
	}
}
