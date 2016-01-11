package gplx.xowa.htmls.hrefs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.btries.*; import gplx.core.primitives.*;
public class Xoh_href_gui_utl {
	public static String Html_extract_text(String site, String page, String text_str) {
		byte[] text_bry = Bry_.new_u8(text_str);
		int text_len = text_bry.length;
		int text_tid = Byte_ascii.To_a7_int(text_bry[0]);
		switch (text_tid) {
			case Text_tid_none: return "";	// "0"
			case Text_tid_text: return String_.new_u8(text_bry, 2, text_len);	// 2 to skip "1|"
			case Text_tid_href: break;	// fall through to below
			default:			throw Err_.new_unhandled(text_tid);
		}
		int href_bgn = 2;							// 2 to skip "2|"
		if (Bry_.Has_at_bgn(text_bry, Xoh_href_.Bry__file, href_bgn, text_len))
			href_bgn += Xoh_href_.Len__file;	// skip "file://"
		Byte_obj_val href_tid = (Byte_obj_val)href_trie.Match_bgn(text_bry, href_bgn, text_len);
		if (href_tid != null) {
			switch (href_tid.Val()) {
				case Href_tid_wiki:			return site + String_.new_u8(text_bry, href_bgn, text_len);		
				case Href_tid_site:			return String_.new_u8(text_bry, href_bgn + 6, text_len);			// +6 to skip "site/"
				case Href_tid_anch:			return site + "/wiki/" + page + String_.new_u8(text_bry, href_bgn, text_len);
			}
		}
		return String_.new_u8(text_bry, 2, text_len);	// 2 to skip "2|"; handles "http://" text as well as any fall-thru from above
	}
	public static String Standardize_xowa_link(String str) {
		byte[] bry = Bry_.new_u8(str);
		int skip = Skip_start_of_xowa_link(bry, bry.length, 0);
		return skip == 0 ? str : String_.Mid(str, skip);
	}
	private static int Skip_start_of_xowa_link(byte[] src, int src_len, int bgn) {
		if (!Bry_.Has_at_bgn(src, Xoh_href_.Bry__file, bgn, src_len)) return bgn;	// does not start with "file://"
		int pos = bgn + Xoh_href_.Len__file;	// skip "file://"
		Object tid_obj = href_trie.Match_bgn(src, pos, src_len);
		if (tid_obj == null) {
			return bgn; // if not a known xowa link, return original bgn; 
		}
		switch (((Byte_obj_val)tid_obj).Val()) {
			case Href_tid_site:			return pos;
			case Href_tid_wiki:			return pos;
			case Href_tid_anch:			return pos;
			default:					throw Err_.new_unhandled(tid_obj);
		}
	}
	private static final byte Text_tid_none = 0, Text_tid_text = 1, Text_tid_href = 2;
	private static final byte Href_tid_wiki = 1, Href_tid_site = 2, Href_tid_anch = 3;
	private static final Btrie_slim_mgr href_trie = Btrie_slim_mgr.cs()
	.Add_bry_byte(Xoh_href_.Bry__site		, Href_tid_site)
	.Add_bry_byte(Xoh_href_.Bry__wiki		, Href_tid_wiki)
	.Add_bry_byte(Xoh_href_.Bry__anch		, Href_tid_anch)
	;
}
/*
NOTE_1:
. swt/mozilla treats text differently in href="{text}" when content_editable=n; occurs in LocationListener.changing
http://a.org						-> http://a.org								does nothing
A									-> file:///A								adds "file:///"
/wiki/A								-> file:///wiki/A							adds "file://"
Category:A							-> Category:A								noops; Category is assumed to be protocol?
//en.wiktionary.org/wiki/a			-> file:///wiki/a							strips out site name and prepends "file://"; no idea why

. so, to handle the above, the code does the following
http://a.org						-> http://a.org								does nothing; nothing needed
A									-> /wiki/A									always prepend /wiki/
Category:A							-> /wiki/Category:A							always prepend /wiki/
//en.wiktionary.org/wiki/A			-> /site/en.wiktionary.org/wiki/A			always transform relative url to /site/

. the href will still come here as file:///wiki/A or file:///site/en.wiktionary.org/wiki/A.
. however, the file:// can be lopped off and discarded and the rest of the href will fall into one of the following cases
.. /wiki/
.. /site/
.. /xcmd/
.. #
.. anything else -> assume to be really a file:// url; EX: file://C/dir/fil.txt -> C/dir/fil.txt
. the other advantage of this approach is that this proc can be reused outside of swt calls; i.e.: it can parse both "file:///wiki/A" and "/wiki/A"
*/
