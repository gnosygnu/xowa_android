package gplx.gfml; import gplx.*;
import gplx.core.lists.*;/*Hash_adp_list*/
interface GfmlPragma {//_20110424
	String KeyOfPragma();
	void Exec(GfmlBldr bldr, GfmlNde pragmaNde);
	GfmlType[] MakePragmaTypes(GfmlTypeMakr typeMakr);
}
class GfmlPragmaMgr {//_20110424
	public void Pragmas_add(GfmlPragma cmd) {pragmas.Add(cmd.KeyOfPragma(), cmd);}		
	public boolean Pragmas_compile(String ndeName, GfmlBldr bldr) {
		if (pragmas.Count() == 0) return false;
		GfmlPragma cmd = (GfmlPragma)pragmas.Get_by(ndeName); if (cmd == null) return false;
		GfmlNde pragmaNde = bldr.CurNde();
		pragmaNde.ObjType_set_pragma();
		cmd.Exec(bldr, pragmaNde);
		return true;
	}
	public void BgnCmds_add(GfmlDocPos pos, GfmlBldrCmd cmd) {bgnCmds.AddInList(pos.Path(), cmd);}
	public void BgnCmds_del(GfmlDocPos pos, GfmlBldrCmd cmd) {bgnCmds.DelInList(pos.Path(), cmd);}
	public void BgnCmds_exec(GfmlDocPos pos, GfmlBldr bldr)  {Exec(pos, bldr, bgnCmds);}
	public void EndCmds_add(GfmlDocPos pos, GfmlBldrCmd cmd) {endCmds.AddInList(pos.Path(), cmd);}
	public void EndCmds_del(GfmlDocPos pos, GfmlBldrCmd cmd) {endCmds.DelInList(pos.Path(), cmd);}
	public void EndCmds_exec(GfmlDocPos pos, GfmlBldr bldr)  {Exec(pos, bldr, endCmds);}
	static void Exec(GfmlDocPos pos, GfmlBldr bldr, Hash_adp_list cmds) {
		List_adp list = cmds.Get_by(pos.Path()); if (list == null) return;
		for (int i = 0; i < list.Count(); i++) {
			GfmlBldrCmd cmd = (GfmlBldrCmd)list.Get_at(i);
			cmd.Exec(bldr, GfmlTkn_.Null);
		}
	}
	Hash_adp pragmas = Hash_adp_.new_(); Hash_adp_list bgnCmds = Hash_adp_list.new_(), endCmds = Hash_adp_list.new_();
        public static GfmlPragmaMgr new_() {return new GfmlPragmaMgr();} GfmlPragmaMgr() {}
}
