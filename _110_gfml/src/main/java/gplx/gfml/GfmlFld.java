package gplx.gfml; import gplx.*;
import gplx.core.strings.*;
public class GfmlFld {//_20101027 // GfmlFld is used for both GfmlAtr and GfmlNde
	public String Name() {return name;} private String name;
	public boolean Name_isKey() {return name_isKey;} private boolean name_isKey;
	public String TypeKey() {return typeKey;} private String typeKey;
	public GfmlObj DefaultTkn() {return defaultTkn;} public GfmlFld DefaultTkn_(GfmlObj val) {defaultTkn = val; return this;} GfmlObj defaultTkn = GfmlTkn_.Null;
	public GfmlFld Clone() {
		GfmlFld rv = new GfmlFld();
		rv.name = name; rv.name_isKey = name_isKey; rv.typeKey = typeKey;
		rv.defaultTkn = defaultTkn;	// FIXME: defaultTkn.clone_()
		return rv;
	}
	public String To_str() {String_bldr sb = String_bldr_.new_(); this.To_str(sb); return sb.To_str_and_clear();}
	public void To_str(String_bldr sb) {sb.Add_fmt("name={0} typeKey={1}", name, typeKey);}

	public static final GfmlFld Null = new_(false, GfmlItmKeys.NullKey, GfmlType_.AnyKey);
	public static GfmlFld new_(boolean name_isKey, String name, String typeKey) {
		GfmlFld rv = new GfmlFld();
		rv.name_isKey = name_isKey; rv.name = name; rv.typeKey = typeKey;
		return rv;
	}	GfmlFld() {}
}
