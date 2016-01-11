package gplx.xowa.xtns.scribunto.engines.luaj; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*; import gplx.xowa.xtns.scribunto.engines.*;
//#{Luaj_server_func_recv
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
public class Luaj_server_func_recv extends OneArgFunction {
	private Luaj_engine engine;
	public void Engine_(Luaj_engine v) {this.engine = v;}
	public LuaValue call(LuaValue tbl_val) {
		LuaTable tbl = (LuaTable)tbl_val;
		String op = Luaj_value_.Get_val_as_str(tbl, "op");
		if (!String_.Eq(op, "call")) throw Err_.new_wo_type("luaj_recvr only processes op calls");
		return engine.Server_recv_call(tbl);
	}
	public static Luaj_server_func_recv _ = new Luaj_server_func_recv();
}
//#}
