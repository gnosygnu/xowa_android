package gplx.xowa.parsers.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public interface Mwh_doc_wkr extends Mwh_atr_wkr {
	Hash_adp_bry Nde_regy();
	void On_txt_end		(Mwh_doc_parser mgr, byte[] src, int nde_tid, int itm_bgn, int itm_end);
	void On_nde_head_bgn(Mwh_doc_parser mgr, byte[] src, int nde_tid, int key_bgn, int key_end);
	void On_nde_head_end(Mwh_doc_parser mgr, byte[] src, int nde_tid, int itm_bgn, int itm_end, boolean inline);
	void On_nde_tail_end(Mwh_doc_parser mgr, byte[] src, int nde_tid, int itm_bgn, int itm_end);
	void On_comment_end (Mwh_doc_parser mgr, byte[] src, int nde_tid, int itm_bgn, int itm_end);
	void On_entity_end	(Mwh_doc_parser mgr, byte[] src, int nde_tid, int itm_bgn, int itm_end);
}
