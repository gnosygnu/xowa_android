package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.logs.*;
import gplx.xowa.xtns.scribunto.engines.*;
public class Scrib_xtn_mgr extends Xox_mgr_base {
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("scribunto");
	@Override public void Xtn_ctor_by_app(Xoae_app app) {this.app = app;} private Xoae_app app;
	@Override public Xox_mgr Xtn_clone_new() {return new Scrib_xtn_mgr();}
	public Scrib_lib_mgr Lib_mgr() {return lib_mgr;} private Scrib_lib_mgr lib_mgr = new Scrib_lib_mgr();
	@Override public void Enabled_(boolean v) {
		Scrib_core_mgr.Term_all(app);
		super.Enabled_(v);
	}
	public byte Engine_type() {return engine_type;} private byte engine_type = Scrib_engine_type.Type_luaj;
	public void Engine_type_(byte cmd) {
		engine_type = cmd;
		gplx.xowa.xtns.scribunto.Scrib_core_mgr.Term_all(app);
	}
	public int Lua_timeout() {return lua_timeout;} private int lua_timeout = 4000;
	public int Lua_timeout_polling() {return lua_timeout_polling;} private int lua_timeout_polling = 1;
	public int Lua_timeout_busy_wait() {return lua_timeout_busy_wait;} private int lua_timeout_busy_wait = 250;
	public int Lua_timeout_loop() {return lua_timeout_loop;} private int lua_timeout_loop = 10000000;
	public boolean Lua_log_enabled() {return lua_log_enabled;} private boolean lua_log_enabled;
	public boolean Luaj_debug_enabled() {return luaj_debug_enabled;} private boolean luaj_debug_enabled;
	public void Luaj_debug_enabled_(boolean v) {
		this.luaj_debug_enabled = v;
		gplx.xowa.xtns.scribunto.Scrib_core_mgr.Term_all(app);// restart server in case luaj caches any debug data
	}
	public Xop_log_invoke_wkr Invoke_wkr() {return invoke_wkr;} private Xop_log_invoke_wkr invoke_wkr;
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_engine_type))					return Scrib_engine_type.Xto_str(engine_type);
		else if	(ctx.Match(k, Invk_engine_type_))					Engine_type_(Scrib_engine_type.Xto_byte(m.ReadStr("v")));
		else if	(ctx.Match(k, Invk_engine_type_list))				return Scrib_engine_type.Options__list;
		else if	(ctx.Match(k, Invk_lua_timeout))					return lua_timeout;
		else if	(ctx.Match(k, Invk_lua_timeout_))					lua_timeout = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_lua_timeout_polling))			return lua_timeout_polling;
		else if	(ctx.Match(k, Invk_lua_timeout_polling_))			lua_timeout_polling = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_lua_log_enabled))				return Yn.To_str(lua_log_enabled);
		else if	(ctx.Match(k, Invk_lua_log_enabled_))				lua_log_enabled = m.ReadBool("v");
		else if	(ctx.Match(k, Invk_lua_timeout_busy_wait))			return lua_timeout_busy_wait;
		else if	(ctx.Match(k, Invk_lua_timeout_busy_wait_))			lua_timeout_busy_wait = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_lua_timeout_loop))				return lua_timeout_loop;
		else if	(ctx.Match(k, Invk_lua_timeout_loop_))				lua_timeout_loop = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_luaj_debug_enabled))				return Yn.To_str(luaj_debug_enabled);
		else if	(ctx.Match(k, Invk_luaj_debug_enabled_))			Luaj_debug_enabled_(m.ReadBool("v"));
		else if	(ctx.Match(k, Invk_invoke_wkr))						return m.ReadYnOrY("v") ? Invoke_wkr_or_new() : Gfo_invk_.Noop;
		else														return super.Invk(ctx, ikey, k, m);
		return this;
	}
	private static final String 
	  Invk_engine_type = "engine_type", Invk_engine_type_ = "engine_type_", Invk_engine_type_list = "engine_type_list"
	, Invk_lua_timeout = "lua_timeout", Invk_lua_timeout_ = "lua_timeout_"
	, Invk_lua_timeout_polling = "lua_timeout_polling", Invk_lua_timeout_polling_ = "lua_timeout_polling_"
	, Invk_lua_log_enabled = "lua_log_enabled", Invk_lua_log_enabled_ = "lua_log_enabled_"
	, Invk_lua_timeout_loop = "lua_timeout_loop", Invk_lua_timeout_loop_ = "lua_timeout_loop_"
	, Invk_lua_timeout_busy_wait = "lua_timeout_busy_wait", Invk_lua_timeout_busy_wait_ = "lua_timeout_busy_wait_"
	, Invk_luaj_debug_enabled = "luaj_debug_enabled", Invk_luaj_debug_enabled_ = "luaj_debug_enabled_"
	, Invk_invoke_wkr = "invoke_wkr"
	;
	public Xop_log_invoke_wkr Invoke_wkr_or_new() {
		if (invoke_wkr == null) invoke_wkr = app.Log_mgr().Make_wkr_invoke();
		return invoke_wkr;
	}
	public static Err err_(String fmt, Object... args)						{return Err_.new_wo_type(fmt, args).Trace_ignore_add_1_();}
	public static Err err_(Exception e, String msg, Object... args)	{return Err_.new_exc(e, "xo", msg, args).Trace_ignore_add_1_();}
}
