package gplx.xowa.bldrs.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.strings.*;
public class Xoac_wiki_cfg_bldr_cmd {
	public Xoac_wiki_cfg_bldr_cmd(String key, String text) {this.key = key; this.text = text;}
	public String Key() {return key;} private String key;
	public String Text() {return text;} private String text;
	public String Exec(String_bldr sb, String wiki, String src) {
		String sect_txt_bgn = sb.Add("// ").Add(key).Add(".bgn\n").To_str_and_clear();
		String sect_txt_end = sb.Add("// ").Add(key).Add(".end\n").To_str_and_clear();
		String sect_txt_all = sb.Add(sect_txt_bgn).Add(text + "\n").Add(sect_txt_end).To_str_and_clear();	// NOTE: always add \n; convenience for single line cmds
		return src + sect_txt_all;
	}
}
