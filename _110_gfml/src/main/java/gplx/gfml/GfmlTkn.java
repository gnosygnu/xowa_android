package gplx.gfml; import gplx.*;
import gplx.core.strings.*;
public interface GfmlTkn extends GfmlObj {//_20101204
	String TknType();
	String Raw();
	String Val();
	GfmlTkn[] SubTkns();
	GfmlBldrCmd Cmd_of_Tkn();
	GfmlTkn MakeNew(String raw, String val);
}
class GfmlTknAry_ {//_20101204
	public static final GfmlTkn[] Empty = new GfmlTkn[0];
	public static GfmlTkn[] ary_(GfmlTkn... ary) {return ary;}
	@gplx.Internal protected static String XtoRaw(GfmlTkn[] ary) {
		String_bldr sb = String_bldr_.new_();
		for (GfmlTkn tkn : ary)
			sb.Add(tkn.Raw());
		return sb.To_str();
	}
	@gplx.Internal protected static String XtoVal(GfmlTkn[] ary) {return XtoVal(ary, 0, ary.length);}
	static String XtoVal(GfmlTkn[] ary, int bgn, int end) {
		String_bldr sb = String_bldr_.new_();
		for (int i = bgn; i < end; i++) {
			GfmlTkn tkn = ary[i];
			sb.Add(tkn.Val());
		}
		return sb.To_str();
	}
}
