package gplx.xowa.htmls.modules.popups.keeplists; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.modules.*; import gplx.xowa.htmls.modules.popups.*;
import gplx.langs.regxs.*;
public class Xop_keeplist_wiki {
	public Xop_keeplist_wiki(Xowe_wiki wiki) {
		srl = new Xop_keeplist_wiki_srl(wiki);
	}
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled = false;	// NOTE: default to false, b/c wikis that are not listed in cfg will not call Rules_seal
	public Xop_keeplist_rule[] Rules() {return rules;} private Xop_keeplist_rule[] rules; private int rules_len;
	public Xop_keeplist_wiki_srl Srl() {return srl;} private Xop_keeplist_wiki_srl srl;
	public void Rules_add(Xop_keeplist_rule rule) {rules_list.Add(rule);} private List_adp rules_list = List_adp_.New();
	public void Rules_seal() {
		this.rules = (Xop_keeplist_rule[])rules_list.To_ary_and_clear(Xop_keeplist_rule.class);
		this.rules_len = rules.length;
		if (rules_len == 0) return;
		if (rules_len == 1) {
			Xop_keeplist_rule rule_0 = rules[0];
			if (rule_0.Includes().length == 0)
				enabled = false;
			else
				enabled = true;
		}
		else
			enabled = true;
	}
	public boolean Match(byte[] ttl) {
		for (int i = 0; i < rules_len; ++i) {
			Xop_keeplist_rule rule = rules[i];
			if (rule.Match(ttl)) return true;
		}
		return false;
	}
}
