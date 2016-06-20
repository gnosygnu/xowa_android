package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
import gplx.gfui.controls.elems.*;
public class IptEventType {//_20101217
	public int Val() {return val;} int val;
	public String Name() {return name;} private String name;
	public IptEventType Add(IptEventType comp) {return IptEventType_.add_(this, comp);}
	@Override public String toString() {return name;}
	@gplx.Internal protected IptEventType(int v, String s) {this.val = v; this.name = s;}
	public static Object HandleEvt(IptBndsOwner owner, GfsCtx ctx, GfoMsg m) {
		IptEventData iptData = IptEventData.ctx_(ctx, m);
		boolean processed = owner.IptBnds().Process(iptData);
		if (processed || IptEventMgr.StopFwd(iptData)) {	// NOTE: IptMsgs are single-dispatch;
		}
		else {
			Gfo_evt_mgr_.Pub_msg(owner, ctx, GfuiElemKeys.IptRcvd_evt, m);
		}
		return Gfo_invk_.Rv_handled;
	}
}
