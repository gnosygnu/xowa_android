package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
public class IptCfgRegy implements Gfo_invk {
	public void Clear() {hash.Clear();}
	public IptCfg GetOrNew(String k) {
		IptCfg rv = (IptCfg)hash.Get_by(k);
		if (rv == null) {
			rv = (IptCfg)IptCfg_base.HashProto.NewByKey(k);
			hash.Add(k, rv);
		}
		return rv;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.MatchIn(k, Invk_Get, Invk_get)) {
			String key = m.ReadStr("key");
			if (ctx.Deny()) return this;
			return GetOrNew(key);
		}
		return this;
	}	public static final    String Invk_Get = "Get", Invk_get = "get";
	Ordered_hash hash = Ordered_hash_.New();
	public static final    IptCfgRegy Instance = new IptCfgRegy();
	public IptCfgRegy() {}
}
