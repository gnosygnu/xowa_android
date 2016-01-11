package gplx.xowa.htmls.core.wkrs.xndes; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.langs.htmls.docs.*;
import gplx.xowa.htmls.core.hzips.*;
import gplx.langs.htmls.*;
public class Xoh_xnde_parser {
	public int Src_bgn() {return src_bgn;} private int src_bgn;
	public int Src_end() {return src_end;} private int src_end;
	public boolean Tag_is_inline() {return tag_is_inline;} private boolean tag_is_inline;
	public int Name_id() {return name_id;} private int name_id;
	public byte[] Name_bry() {return name_bry;} private byte[] name_bry;
	public Ordered_hash Atrs() {return atrs_hash;} private Ordered_hash atrs_hash;
	public boolean Parse1(Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Gfh_tag_rdr tag_rdr, byte[] src, Gfh_tag head) {
		this.src_bgn = head.Src_bgn();
		this.name_id = head.Name_id();
		this.name_bry = head.Name_bry();
		this.tag_is_inline = head.Tag_is_inline();
		this.atrs_hash = head.Atrs__hash();
		this.src_end = head.Src_end();
		hdoc_wkr.On_xnde(this);
		return true;
	}		
}
