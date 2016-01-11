package gplx.xowa.htmls.modules.popups.keeplists; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.modules.*; import gplx.xowa.htmls.modules.popups.*;
import gplx.langs.regxs.*;
public class Xop_keeplist_rule {
	private Gfo_pattern[] excludes; private int excludes_len;
	public Xop_keeplist_rule(Gfo_pattern[] includes, Gfo_pattern[] excludes) {
		this.includes = includes; this.includes_len = includes.length;
		this.excludes = excludes; this.excludes_len = excludes.length;
	}
	public Gfo_pattern[] Includes() {return includes;} private Gfo_pattern[] includes; private int includes_len;
	public boolean Match(byte[] ttl) {
		boolean match_found = false;
		for (int i = 0; i < includes_len; ++i) {
			Gfo_pattern skip = includes[i];
			if (skip.Match(ttl)) {
				match_found = true;
				break;
			}
		}
		if (match_found) {
			for (int i = 0; i < excludes_len; ++i) {
				Gfo_pattern keep = excludes[i];
				if (keep.Match(ttl)) {
					match_found = false;
					break;
				}
			}
		}
		return match_found;
	}
}
