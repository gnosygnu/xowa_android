package gplx.core.scripts; import gplx.*; import gplx.core.*;
public class Gfo_script_engine_ {
	public static Gfo_script_engine New_by_key(String key) {
		if		(String_.Eq(key, "javascript.java"))	return new Gfo_script_engine__javascript();
		else if	(String_.Eq(key, "lua.luaj"))			return new Gfo_script_engine__luaj();
		else if	(String_.Eq(key, "noop"))				return new Gfo_script_engine__noop();
		else													throw Err_.new_unhandled(key);
	}
	public static Gfo_script_engine New_by_ext(String ext) {
		if		(String_.Eq(ext, ".js"))				return new Gfo_script_engine__javascript();
		else if	(String_.Eq(ext, ".lua"))				return new Gfo_script_engine__luaj();
		else											throw Err_.new_unhandled(ext);
	}
}
