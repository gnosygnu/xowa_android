package gplx.gfml; import gplx.*;
public interface GfmlBldrCmd {//_20101105
	String Key();
	void Exec(GfmlBldr bldr, GfmlTkn tkn);
}
class GfmlBldrCmd_ {//_20101105
	public static final GfmlBldrCmd Null = new GfmlBldrCmd_null();
}
class GfmlBldrCmd_null implements GfmlBldrCmd {//_20101105
	public String Key() {return "gfml.nullCmd";}
	public void Exec(GfmlBldr bldr, GfmlTkn tkn) {}
}
class GfmlBldrCmdRegy {//_20101105
	public void Add(GfmlBldrCmd cmd) {hash.Add(cmd.Key(), cmd);}
	public GfmlBldrCmd GetOrFail(String key) {return (GfmlBldrCmd)hash.Get_by_or_fail(key);}
	Hash_adp hash = Hash_adp_.New();
	public static GfmlBldrCmdRegy new_() {
		GfmlBldrCmdRegy rv = new GfmlBldrCmdRegy();
		rv.Add(GfmlBldrCmd_elemKey_set.Instance);
		rv.Add(GfmlBldrCmd_dataTkn_set.Instance);
		return rv;
	}	GfmlBldrCmdRegy() {}
}
