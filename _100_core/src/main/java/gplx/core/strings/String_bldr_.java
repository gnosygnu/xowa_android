package gplx.core.strings; import gplx.*; import gplx.core.*;
public class String_bldr_ {
	public static String_bldr new_()					{return new String_bldr_thread_single();}
	public static String_bldr new_thread()				{return new String_bldr_thread_multiple();}
}
