package gplx.langs.htmls; import gplx.*; import gplx.langs.*;
public class Gfh_atr_ {
	public static final    byte[] 
	// "coreattrs"
	  Bry__id					= Bry_.new_a7("id")
	, Bry__class				= Bry_.new_a7("cl"+"ass")
	, Bry__style				= Bry_.new_a7("style")
	, Bry__title				= Bry_.new_a7("title")
	// "i18n"
	, Bry__lang					= Bry_.new_a7("lang")
	, Bry__dir					= Bry_.new_a7("dir")
	// <a>
	, Bry__href					= Bry_.new_a7("href")
	, Bry__rel					= Bry_.new_a7("rel")
	// <img>
	, Bry__alt					= Bry_.new_a7("alt")
	, Bry__src					= Bry_.new_a7("src")
	, Bry__width				= Bry_.new_a7("width")
	, Bry__height				= Bry_.new_a7("height")
	// <table>
	//, Bry__width				= Bry_.new_a7("width")
	, Bry__cellpadding			= Bry_.new_a7("cellpadding")
	, Bry__cellspacing			= Bry_.new_a7("cellspacing")
	, Bry__summary				= Bry_.new_a7("summary")		// HTML.ua
	// <table>.borders_and_rules
	, Bry__border				= Bry_.new_a7("border")
	, Bry__frames				= Bry_.new_a7("frames")
	, Bry__rules				= Bry_.new_a7("rules")
	// <th>,<td>
	, Bry__scope				= Bry_.new_a7("scope")
	, Bry__rowspan				= Bry_.new_a7("rowspan")
	, Bry__colspan				= Bry_.new_a7("colspan")
	, Bry__align				= Bry_.new_a7("align")			// HTML.v4
	, Bry__bgcolor				= Bry_.new_a7("bgcolor")		// HTML.v4
	, Bry__abbr					= Bry_.new_a7("abbr")			// HTML.ua
	;
	public static byte[] Make(Bry_bfr bfr, byte[] key, byte[] val) {
		return bfr.Add_byte_space().Add(key).Add_byte_eq().Add_byte_quote().Add(val).Add_byte_quote().To_bry_and_clear();
	}
	public static byte[] Add_to_bry(Bry_bfr bfr, byte[] key, byte[] val) {
		bfr.Add_byte_space().Add(key).Add_byte_eq().Add_byte_quote().Add(val).Add_byte_quote();
		return bfr.To_bry_and_clear();
	}
	public static void Add(Bry_bfr bfr, byte[] key, byte[] val) {
		bfr.Add_byte_space().Add(key).Add_byte_eq().Add_byte_quote().Add(val).Add_byte_quote();
	}
	public static void Add(Bry_bfr bfr, byte[] key, int val) {
		bfr.Add_byte_space().Add(key).Add_byte_eq().Add_byte_quote();
		bfr.Add_int_variable(val);
		bfr.Add_byte_quote();
	}
}
