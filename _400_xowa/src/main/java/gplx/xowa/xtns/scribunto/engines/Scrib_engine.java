package gplx.xowa.xtns.scribunto.engines; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
public interface Scrib_engine {
	boolean Dbg_print(); void Dbg_print_(boolean v);
	Scrib_server Server(); void Server_(Scrib_server v);
	Scrib_lua_proc LoadString(String name, String text);
	KeyVal[] CallFunction(int id, KeyVal[] args);
	void RegisterLibrary(KeyVal[] functions_ary);
	KeyVal[] ExecuteModule(int mod_id);
	void CleanupChunks(KeyVal[] ids);
}
