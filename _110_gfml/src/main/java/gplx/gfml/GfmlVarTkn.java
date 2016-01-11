package gplx.gfml; import gplx.*;
class GfmlVarTkn implements GfmlTkn {
	public int ObjType() {return GfmlObj_.Type_tkn;}
	public String Key() {return key;} private String key;
	public String Raw() {return GfmlTknAry_.XtoRaw(ary);}
	public String Val() {
		String s = ctx.Fetch_Val(varKey); if (s == null) return val;
		val = s;
		return val;
	}	String val;
	public GfmlBldrCmd Cmd_of_Tkn() {return GfmlBldrCmd_.Null;}
	public GfmlTkn[] SubTkns() {return ary;} GfmlTkn[] ary;
	public GfmlTkn MakeNew(String rawNew, String valNew) {throw Err_.new_invalid_op("makeNew cannot make copy of token with only raw").Args_add("key", key, "rawNew", rawNew, "valNew", valNew);}
	public String TknType() {return "evalTkn";}

	GfmlVarCtx ctx; String varKey;
	@gplx.Internal protected GfmlVarTkn(String key, GfmlTkn[] ary, GfmlVarCtx ctx, String varKey) {
		this.key = key; this.ary = ary;
		this.ctx = ctx; this.varKey = varKey;
		this.val = ctx.Fetch_Val(varKey);
	}
}
