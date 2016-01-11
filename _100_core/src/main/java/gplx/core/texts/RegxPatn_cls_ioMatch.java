package gplx.core.texts; import gplx.*; import gplx.core.*;
import gplx.langs.regxs.*;
public class RegxPatn_cls_ioMatch {
	public String Raw() {return raw;} private String raw;
	public boolean CaseSensitive() {return caseSensitive;} private boolean caseSensitive;
	public boolean Matches(String text) {
		text = String_.CaseNormalize(caseSensitive, text);
		return Regx_adp_.Match(text, compiled);}	// WNT-centric: Io_mgr paths are case-insensitive;
	@Override public String toString() {return raw;}

	String compiled;
	@gplx.Internal protected RegxPatn_cls_ioMatch(String raw, String compiled, boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		this.raw = raw;
		compiled = String_.CaseNormalize(caseSensitive, compiled);
		this.compiled = compiled;
	}
}
