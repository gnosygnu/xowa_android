package gplx.xowa.htmls.hrefs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
public class Xoh_href_ {
	public static final String
	  Str__file				= "file://"
	, Str__site				= "/site/"
	, Str__wiki				= "/wiki/"
	, Str__anch				= "#"
	;
	public static final byte[] 
	  Bry__file				= Bry_.new_a7(Str__file)
	, Bry__site				= Bry_.new_a7(Str__site)
	, Bry__wiki				= Bry_.new_a7(Str__wiki)
	, Bry__anch				= Bry_.new_a7(Str__anch)
	, Bry__https			= Bry_.new_a7("https://")	// NOTE: must be "https:" or wmf api won't work; DATE:2015-06-17
	, Bry__xcmd				= Bry_.new_a7("/xcmd/")
	;
	public static final int
	  Len__file				= Bry__file.length
	, Len__site				= Bry__site.length
	, Len__wiki				= Bry__wiki.length
	, Len__anch				= Bry__anch.length
	;
}
