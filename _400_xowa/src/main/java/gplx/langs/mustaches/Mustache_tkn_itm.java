package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
public interface Mustache_tkn_itm { 
	int Tid();
	String Key();
	Mustache_tkn_itm[] Subs_ary();
	void Subs_ary_(Mustache_tkn_itm[] v);
	void Render(Bry_bfr bfr, Mustache_render_ctx ctx);
}
class Mustache_tkn_itm_ {// for types, see http://mustache.github.io/mustache.5.html
	public static final int Tid__root = 0, Tid__text = 1, Tid__variable = 2, Tid__escape = 3, Tid__section = 4, Tid__inverted = 5, Tid__comment = 6, Tid__partial = 7, Tid__delimiter = 8;
	public static final Mustache_tkn_itm[] Ary_empty = new Mustache_tkn_itm[0];
}
abstract class Mustache_tkn_base implements Mustache_tkn_itm {
	public Mustache_tkn_base(int tid, byte[] key_bry) {this.tid = tid; this.key = String_.new_u8(key_bry);}
	public int Tid() {return tid;} private final int tid;
	public String Key() {return key;} private final String key;
	@gplx.Virtual public Mustache_tkn_itm[] Subs_ary()						{return Mustache_tkn_itm_.Ary_empty;}
	@gplx.Virtual public void Subs_ary_(Mustache_tkn_itm[] v)					{throw Err_.new_unsupported();}	// fail if trying to set and not overridden
	@gplx.Virtual public void Render(Bry_bfr bfr, Mustache_render_ctx ctx)	{throw Err_.new_unsupported();}	// should be abstract
}
class Mustache_tkn_root extends Mustache_tkn_base {		// EX: {{variable}} -> &lt;a&gt; 
	private Mustache_tkn_itm[] subs_ary;
	public Mustache_tkn_root() {super(Mustache_tkn_itm_.Tid__root, Bry_.Empty);}
	@Override public Mustache_tkn_itm[] Subs_ary() {return subs_ary;}
	@Override public void Subs_ary_(Mustache_tkn_itm[] v) {subs_ary = v;}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {
		int subs_len = subs_ary.length;
		for (int i = 0; i < subs_len; ++i) {
			Mustache_tkn_itm sub = subs_ary[i];
			sub.Render(bfr, ctx);
		}
	}
}
class Mustache_tkn_text extends Mustache_tkn_base {		// EX: text -> text
	private final byte[] src; private final int src_bgn, src_end; 
	public Mustache_tkn_text(byte[] src, int src_bgn, int src_end) {super(Mustache_tkn_itm_.Tid__text, Bry_.Empty);
		this.src = src;
		this.src_bgn = src_bgn;
		this.src_end = src_end;
	}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {
		bfr.Add_mid(src, src_bgn, src_end);
	}
}
class Mustache_tkn_comment extends Mustache_tkn_base {	// EX: {{!section}}comment{{/section}} -> 
	public Mustache_tkn_comment() {super(Mustache_tkn_itm_.Tid__comment, Bry_.Empty);}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {}
}
class Mustache_tkn_variable extends Mustache_tkn_base {		// EX: {{variable}} -> &lt;a&gt;
	public Mustache_tkn_variable(byte[] key) {super(Mustache_tkn_itm_.Tid__variable, key);}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {
		String key = this.Key();
		byte[] val = ctx.Render_variable(key);
		if (val != Mustache_doc_itm_.Null_val)			// if not found, return empty String by default
			gplx.langs.htmls.Gfh_utl.Escape_html_to_bfr(bfr, val, 0, val.length, Bool_.Y, Bool_.Y, Bool_.Y, Bool_.Y, Bool_.Y);
	}
}
class Mustache_tkn_escape extends Mustache_tkn_base {	// EX: {{{variable}}} -> <a>
	public Mustache_tkn_escape(byte[] key) {super(Mustache_tkn_itm_.Tid__escape, key);}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {
		String key = this.Key();
		byte[] val = ctx.Render_variable(key);
		if (val != Mustache_doc_itm_.Null_val)	// if not found, return empty String by default
			bfr.Add(val);
	}
}
class Mustache_tkn_section extends Mustache_tkn_base {	// EX: {{#section}}val{{/section}} -> val (if boolean) or valvalval (if list)
	private Mustache_tkn_itm[] subs_ary;
	public Mustache_tkn_section(byte[] key) {super(Mustache_tkn_itm_.Tid__section, key);}
	@Override public Mustache_tkn_itm[] Subs_ary() {return subs_ary;}
	@Override public void Subs_ary_(Mustache_tkn_itm[] v) {subs_ary = v;}
	@Override public void Render(Bry_bfr bfr, Mustache_render_ctx ctx) {
		String key = this.Key();
		Mustache_tkn_itm[] subs_ary = this.Subs_ary();
		ctx.Section_bgn(key);
		while (ctx.Section_do()) {
			int subs_len = subs_ary.length;
			for (int i = 0; i < subs_len; ++i) {
				Mustache_tkn_itm sub = subs_ary[i];
				sub.Render(bfr, ctx);
			}
		}
		ctx.Section_end();
	}
}
class Mustache_tkn_inverted extends Mustache_tkn_base {	// EX: {{^section}}missing{{/section}} -> missing
	public Mustache_tkn_inverted(byte[] key) {super(Mustache_tkn_itm_.Tid__inverted, key);}
}
class Mustache_tkn_partial extends Mustache_tkn_base {	// EX: {{>a}} -> abc (deferred eval)
	public Mustache_tkn_partial(byte[] key) {super(Mustache_tkn_itm_.Tid__partial, key);}
}
class Mustache_tkn_delimiter extends Mustache_tkn_base {// EX: {{=<% %>=}} -> <% variable %>
	public Mustache_tkn_delimiter(byte[] key) {super(Mustache_tkn_itm_.Tid__delimiter, key);}
}
