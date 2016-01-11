package gplx;
public class Err_ {
	private static String Type__gplx = "gplx"; @gplx.Internal protected static String Trace_null = null;
	public static void Noop(Exception e) {}
	public static Err as_(Object obj) {return obj instanceof Err ? (Err)obj : null;}
	public static Err new_(String type, String msg, Object... args)			{return new Err(Bool_.Y, Trace_null, type, msg, args);}
	public static Err new_wo_type(String msg, Object... args)					{return new Err(Bool_.Y, Trace_null, Type__gplx, msg, args);}
	public static Err new_exc(Exception e, String type, String msg, Object... args) {
		Err rv = cast_or_make(e);
		rv.Msgs_add(type, msg, args);
		return rv;
	}
	public static Err new_unhandled(Object val)										{return new Err(Bool_.Y, Trace_null, Type__gplx, "val is not in switch/if", "val", val);}
	public static Err new_unsupported()												{return new Err(Bool_.Y, Trace_null, Type__gplx, "method not supported");}
	public static Err new_unimplemented()											{return new Err(Bool_.Y, Trace_null, Type__gplx, "method not implemented");}
	public static Err new_unimplemented_w_msg(String msg, Object... args)		{return new Err(Bool_.Y, Trace_null, Type__gplx, msg, args);}
	public static Err new_deprecated(String s)										{return new Err(Bool_.Y, Trace_null, Type__gplx, "deprecated", "method", s);}
	public static Err new_parse_type(Class<?> c, String raw)						{return new_parse(Type_adp_.FullNameOf_type(c), raw);}
	public static Err new_parse_exc(Exception e, Class<?> c, String raw)	{return new_parse(Type_adp_.FullNameOf_type(c), raw).Args_add("e", Err_.Message_lang(e));}
	public static Err new_parse(String type, String raw)							{return new Err(Bool_.Y, Trace_null, Type__gplx, "parse failed", "type", type, "raw", raw);}
	public static Err new_null()													{return new Err(Bool_.Y, Trace_null, Type__gplx, "null obj");}
	public static Err new_null(String arg)											{return new Err(Bool_.Y, Trace_null, Type__gplx, "null obj", "arg", arg);}
	public static Err new_missing_idx(int idx, int len)								{return new Err(Bool_.Y, Trace_null, Type__gplx, "index is out of bounds", "idx", idx, "len", len);}
	public static Err new_missing_key(String key)									{return new Err(Bool_.Y, Trace_null, Type__gplx, "key not found", "key", key);}
	public static Err new_invalid_op(String msg)									{return new Err(Bool_.Y, Trace_null, Type__gplx, msg);}
	public static Err new_invalid_arg(String msg, Object... args)				{return new Err(Bool_.Y, Trace_null, Type__gplx, msg, args);}
	public static Err new_op_canceled()												{return new Err(Bool_.Y, Trace_null, Type__op_canceled, "canceled by usr");}
	public static Err new_type_mismatch_w_exc(Exception ignore, Class<?> t, Object o) {return new_type_mismatch(t, o);}
	public static Err new_type_mismatch(Class<?> t, Object o)								{return new Err(Bool_.Y, Trace_null, Type__gplx, "type mismatch", "expdType", Type_adp_.FullNameOf_type(t), "actlType", Type_adp_.NameOf_obj(o), "actlObj", Object_.Xto_str_strict_or_null_mark(o));}
	public static Err new_cast(Exception ignore, Class<?> t, Object o) {
		String o_str = "";
		try							{o_str = Object_.Xto_str_strict_or_null_mark(o);}
		catch (Exception e)	{o_str = "<ERROR>"; Err_.Noop(e);}
		return new Err(Bool_.Y, Trace_null, Type__gplx, "cast failed", "type", Type_adp_.NameOf_type(t), "obj", o_str);
	}

	public static String Message_lang(Throwable e) {return e.getMessage();} //#<>Exception e) {return e.Message;~Throwable e) {return e.getMessage();
	public static String To_str(Exception e) {return e.toString();}	// e.getMessage() is sometimes null?
	//#{Trace_lang
	public static String Trace_lang(Throwable e) 	{return Trace_lang_exec(e.getStackTrace());}
	private static String Trace_lang_exec(StackTraceElement[] ary) {
		String rv = "";
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			if (i != 0) rv += "\n";
			rv += ary[i].toString();
		}
		return rv;
	}
	//#}
	public static boolean Type_match(Exception e, String type) {
		Err exc = Err_.as_(e);
		return exc == null ? false : exc.Type_match(type);
	}
	public static String Message_gplx_full(Exception e)	{return cast_or_make(e).To_str__full();}
	public static String Message_gplx_log(Exception e)	{return cast_or_make(e).To_str__log();}
	public static Err cast_or_make(Throwable e) {return Type_adp_.Eq_typeSafe(e, Err.class) ? (Err)e : new Err(Bool_.N, Err_.Trace_lang(e), Type_adp_.NameOf_obj(e), Err_.Message_lang(e));}//#<>Exception~Throwable
	public static final String Type__op_canceled = "gplx.op_canceled";
}
