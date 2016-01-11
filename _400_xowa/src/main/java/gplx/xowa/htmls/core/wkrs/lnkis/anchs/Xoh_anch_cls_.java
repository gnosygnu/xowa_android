package gplx.xowa.htmls.core.wkrs.lnkis.anchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.core.btries.*;
public class Xoh_anch_cls_ {
	public static final byte 
	  Tid__none			= 0
	, Tid__unknown		= 1
	, Tid__media_play	= 1
	, Tid__media_info	= 2
	, Tid__ctg_main		= 3
	, Tid__ctg_tree		= 4
	, Tid__ctg_xnav		= 5
	;
	public static final byte[] 
	  Bry__media_play	= Bry_.new_a7("xowa_media_play")
	, Bry__media_info	= Bry_.new_a7("xowa_media_info")
	, Bry__ctg_main		= Bry_.new_a7("inte"+"rnal")
	, Bry__ctg_tree		= gplx.xowa.wikis.ctgs.Xoa_ctg_mgr.Html__cls__bry
	, Bry__ctg_xnav		= Bry_.new_a7("xowa_nav")
	;
	public static final Btrie_slim_mgr Trie = Btrie_slim_mgr.cs()
	.Add_bry_byte(Bry__media_play	, Tid__media_play)
	.Add_bry_byte(Bry__media_info	, Tid__media_info)
	.Add_bry_byte(Bry__ctg_main		, Tid__ctg_main)
	.Add_bry_byte(Bry__ctg_tree		, Tid__ctg_tree)
	.Add_bry_byte(Bry__ctg_xnav		, Tid__ctg_xnav)
	;
}
