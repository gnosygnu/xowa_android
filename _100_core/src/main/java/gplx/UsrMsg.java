package gplx;
import gplx.core.strings.*; import gplx.core.envs.*;
public class UsrMsg {//_20101127
	public int VisibilityDuration() {return visibilityDuration;} public UsrMsg VisibilityDuration_(int v) {visibilityDuration = v; return this;} int visibilityDuration = 3000;
	public String Hdr() {return hdr;} public UsrMsg Hdr_(String val) {hdr = val; return this;} private String hdr;
	public Ordered_hash Args() {return args;} Ordered_hash args = Ordered_hash_.New();
	public UsrMsg Add(String k, Object v) {
		args.Add(k, Keyval_.new_(k, v));
		return this;
	}
	public UsrMsg Add_if_dupe_use_nth(String k, Object v) {
		args.Add_if_dupe_use_nth(k, Keyval_.new_(k, v));
		return this;
	}
	public String XtoStrSingleLine()	{return To_str(" ");}
	public String To_str()				{return To_str(Op_sys.Cur().Nl_str());}
	String To_str(String spr) {
		if (hdr == null) {
			GfoMsg m = GfoMsg_.new_cast_(cmd);
			for (int i = 0; i < args.Count(); i++) {
				Keyval kv = (Keyval)args.Get_at(i);
				m.Add(kv.Key(), kv.Val());
			}
			return Object_.Xto_str_strict_or_null_mark(invk.Invk(GfsCtx.Instance, 0, cmd, m));
		}
		String_bldr sb = String_bldr_.new_();
		sb.Add(hdr).Add(spr);
		for (int i = 0; i < args.Count(); i++) {
			Keyval kv = (Keyval)args.Get_at(i);
			sb.Add_spr_unless_first("", " ", i);
			sb.Add_fmt("{0}={1}", kv.Key(), kv.Val(), spr);
		}
		return sb.To_str();
	}		
        public static UsrMsg fmt_(String hdr, Object... ary) {
		UsrMsg rv = new UsrMsg();
		rv.hdr = String_.Format(hdr, ary);
		return rv;
	}	UsrMsg() {}
        public static UsrMsg new_(String hdr) {
		UsrMsg rv = new UsrMsg();
		rv.hdr = hdr;
		return rv;
	}
        public static UsrMsg invk_(Gfo_invk invk, String cmd) {
		UsrMsg rv = new UsrMsg();
		rv.invk = invk;
		rv.cmd = cmd;
		return rv;
	}	Gfo_invk invk; String cmd;
}
