package gplx.gfml; import gplx.*;
import gplx.core.texts.*; /*CharStream*/
public interface GfmlLxr extends Gfo_evt_itm {//_20101025
	String Key();
	String[] Hooks();
	GfmlTkn CmdTkn();
	void CmdTkn_set(GfmlTkn val); // needed for lxr pragma
	GfmlTkn MakeTkn(CharStream stream, int hookLength);
	GfmlLxr SubLxr();
	void SubLxr_Add(GfmlLxr... lexer);
}
class GfmlLxrRegy {
	public int Count() {return hash.Count();}
	public void Add(GfmlLxr lxr) {hash.Add(lxr.Key(), lxr);}
	public GfmlLxr Get_by(String key) {return (GfmlLxr)hash.Get_by(key);}
	Hash_adp hash = Hash_adp_.New();
}
