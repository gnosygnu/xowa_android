package gplx.core.ios; import gplx.*; import gplx.core.*;
import gplx.core.envs.*;
import gplx.langs.gfs.*;
public class IoUrlInfoRegy implements Gfo_invk {//_20101005 
	public void Reg(IoUrlInfo info) {hash.Add_if_dupe_use_nth(info.Key(), info);}
	public IoUrlInfo Match(String raw) {
		if (String_.Len(raw) == 0) return IoUrlInfo_.Nil;
		for (int i = hash.Count(); i > 0; i--) {
			IoUrlInfo info = (IoUrlInfo)hash.Get_at(i - 1);
			if (info.Match(raw)) return info;
		}
		throw Err_.new_wo_type("could not match ioPathInfo", "raw", raw, "count", hash.Count());
	}
	public void Reset() {
		hash.Clear();
		Reg(IoUrlInfo_rel.new_(Op_sys.Cur().Tid_is_wnt() ? (IoUrlInfo)IoUrlInfo_wnt.Instance : (IoUrlInfo)IoUrlInfo_lnx.Instance));
		Reg(IoUrlInfo_.Mem);
		Reg(IoUrlInfo_lnx.Instance);
		Reg(IoUrlInfo_wnt.Instance);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_Add)) {
			String srcDirStr = m.ReadStr("srcDir");
			String trgDirStr = m.ReadStr("trgDir");
			String engineKey = m.ReadStrOr("engineKey", IoEngine_.SysKey);
			if (ctx.Deny()) return this;
			IoUrlInfo_alias alias =  IoUrlInfo_alias.new_(srcDirStr, trgDirStr, engineKey);
			IoUrlInfoRegy.Instance.Reg(alias);
		}
		return this;
	}	public static final    String Invk_Add = "Add";
	Ordered_hash hash = Ordered_hash_.New();
        public static final    IoUrlInfoRegy Instance = new IoUrlInfoRegy();
	IoUrlInfoRegy() {
		this.Reset();
	}
}