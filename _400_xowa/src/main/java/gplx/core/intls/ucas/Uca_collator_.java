package gplx.core.intls.ucas; import gplx.*; import gplx.core.*; import gplx.core.intls.*;
public class Uca_collator_ {
	public static Uca_collator New(String locale, boolean numeric_ordering) {
		Uca_collator rv = new Uca_collator__icu__4_8();
		rv.Init(locale, numeric_ordering);
		return rv;
	}
}
