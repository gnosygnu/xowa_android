package gplx.core.texts; import gplx.*; import gplx.core.*;
import gplx.langs.regxs.*;
public class RegxPatn_cls_like {
	public char Escape() {return escape;} char escape; public static final char EscapeDefault = '|';
	public String Raw() {return raw;} private String raw;
	public boolean Matches(String text) {return Regx_adp_.Match(text, compiled);}
	@Override public String toString() {return String_.Format("LIKE {0} ESCAPE {1} -> {2}", raw, escape, compiled);}

	String compiled;
	@gplx.Internal protected RegxPatn_cls_like(String raw, String compiled, char escape) {
		this.raw = raw;
		this.compiled = compiled;
		this.escape = escape;
	}
}
