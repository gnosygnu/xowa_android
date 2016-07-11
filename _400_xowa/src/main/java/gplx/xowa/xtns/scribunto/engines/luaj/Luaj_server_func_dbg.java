package gplx.xowa.xtns.scribunto.engines.luaj; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*; import gplx.xowa.xtns.scribunto.engines.*;
//#{Luaj_server_func_dbg
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaThread;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
public class Luaj_server_func_dbg extends VarArgFunction {
	private final Scrib_core core;
	public Luaj_server_func_dbg(Scrib_core v) {this.core = v;}	
	public Varargs invoke(Varargs args) {
		int len = args.narg();
		Bry_bfr bfr = Bry_bfr_.New();
		bfr.Add_str_a7("<span class='xowa_dbg' style='color:red'>");
		for (int i = 1; i <= len; ++i) {
			String s = args.arg(i).toString();
			bfr.Add_str_u8(gplx.langs.htmls.Gfh_utl.Escape_html_as_str(s) + "&nbsp;");
		}
		bfr.Add_str_a7("</span><br/>");
		core.Page().Html_data().Xtn_scribunto_dbg_(bfr.To_bry_and_clear());
		return NONE;
	}
}
//#}
