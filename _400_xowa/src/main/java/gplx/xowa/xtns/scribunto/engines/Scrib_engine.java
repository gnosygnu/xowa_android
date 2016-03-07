package gplx.xowa.xtns.scribunto.engines; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
public interface Scrib_engine {
	boolean			Dbg_print();	void Dbg_print_(boolean v);
	Scrib_server	Server();		void Server_(Scrib_server v);
	Scrib_lua_proc	LoadString(String name, String text);
	Keyval[]		CallFunction(int id, Keyval[] args);
	void			RegisterLibrary(Keyval[] functions_ary);
	Keyval[]		ExecuteModule(int mod_id);
	void			CleanupChunks(Keyval[] ids);
}
