package gplx;
import gplx.langs.gfs.*;
public class Gfo_invk_to_str {
	public static GfoMsg ReadMsg(Gfo_invk invk, String k) {
		GfsCtx ctx = GfsCtx.wtr_();
		GfoMsg m = GfoMsg_.rdr_(k);
		invk.Invk(ctx, 0, k, m);
		String invkKey = GfsCore.Instance.FetchKey(invk);
		GfoMsg root = GfoMsg_.new_cast_(invkKey);
		root.Subs_add(m);
		return root;
	}
	public static GfoMsg WriteMsg(Gfo_invk invk, String k, Object... ary) {return WriteMsg(GfsCore.Instance.FetchKey(invk), invk, k, ary);}
	public static GfoMsg WriteMsg(String invkKey, Gfo_invk invk, String k, Object... ary) {
		GfsCtx ctx = GfsCtx.wtr_();
		GfoMsg m = GfoMsg_.wtr_();
		invk.Invk(ctx, 0, k, m);
		GfoMsg rv = GfoMsg_.new_cast_(k);
		for (int i = 0; i < m.Args_count(); i++) {
			Keyval kv = m.Args_getAt(i);
			rv.Add(kv.Key(), ary[i]);
		}
		GfoMsg root = GfoMsg_.new_cast_(invkKey);
		root.Subs_add(rv);
		return root;
	}
}
