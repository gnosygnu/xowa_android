package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.type_xtns.*;
public class GfoFld {//_20110416
	public String Key() {return key;} private String key;
	public ClassXtn Type() {return type;} ClassXtn type;
	public static final GfoFld Null = new_(String_.Null_mark, ObjectClassXtn.Instance);
	public static GfoFld new_(String key, ClassXtn c) {
		GfoFld rv = new GfoFld();
		rv.key = key; rv.type = c;
		return rv;
	}
}
