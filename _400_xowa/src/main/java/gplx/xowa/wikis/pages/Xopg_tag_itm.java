package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.langs.htmls.*;
public class Xopg_tag_itm {
	public Xopg_tag_itm(byte[] name, byte[] text, Keyval... atrs_ary) {
		this.Name = name;
		this.Text = text;
		this.Atrs_ary = atrs_ary;
	}
	public final    byte[] Name;
	public final    byte[] Text;
	public final    Keyval[] Atrs_ary;
	public void To_html(Bry_bfr bfr) {
		bfr.Add_byte(Byte_ascii.Angle_bgn);
		bfr.Add(Name);
		int len = Atrs_ary.length;
		for (int i = 0; i < len; ++i) {
			bfr.Add_byte_space();
			Keyval atr = Atrs_ary[i];
			bfr.Add_str_a7(atr.Key());
			bfr.Add_byte(Byte_ascii.Eq);
			bfr.Add_byte(Byte_ascii.Quote);
			bfr.Add_str_a7(atr.Val_to_str_or_empty());
			bfr.Add_byte(Byte_ascii.Quote);
		}
		bfr.Add_byte(Byte_ascii.Angle_end);
		if (Bry_.Eq(Name, Gfh_tag_.Bry__link)) return;
		if (Text != null) {
			bfr.Add_byte_nl();
			bfr.Add(Text);
			bfr.Add_byte_nl();
		}
		bfr.Add_byte(Byte_ascii.Angle_bgn).Add_byte(Byte_ascii.Slash);
		bfr.Add(Name);
		bfr.Add_byte(Byte_ascii.Angle_end);
	}

	public static Xopg_tag_itm New_css_file(Io_url href) {
		return new Xopg_tag_itm(Gfh_tag_.Bry__link		, null, Keyval_.new_("type", "text/css"), Keyval_.new_("rel", "stylesheet"), Keyval_.new_("href", href.To_http_file_str()));
	}
	public static Xopg_tag_itm New_css_code(byte[] code) {
		return new Xopg_tag_itm(Gfh_tag_.Bry__style		, code, Keyval_.new_("type", "text/css"));
	}
	public static Xopg_tag_itm New_js_file(Io_url src) {
		return new Xopg_tag_itm(Gfh_tag_.Bry__script	, null, Keyval_.new_("type", "text/javascript"), Keyval_.new_("src", src.To_http_file_str()));
	}
	public static Xopg_tag_itm New_js_code(byte[] code) {
		return new Xopg_tag_itm(Gfh_tag_.Bry__script	, code, Keyval_.new_("type", "text/javascript"));
	}
}
