package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.xowa.bldrs.wkrs.*;
public class Xob_cmd_regy {
	private final    Ordered_hash regy = Ordered_hash_.New();
	public Xob_cmd Get_or_null(String key) {return (Xob_cmd)regy.Get_by(key);}
	public void Add_many(Xob_cmd... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Xob_cmd cmd = ary[i];
			regy.Add(cmd.Cmd_key(), cmd);
		}
	}
}
