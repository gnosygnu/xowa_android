package gplx.xowa.htmls.tocs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
class Xoh_toc_wtr {
	private final    Gfh_tag_rdr tag_rdr = Gfh_tag_rdr.New__html();
	private byte[] page_name = Bry_.Empty;
	private final    Bry_bfr bfr = Bry_bfr_.New();
	public void Clear() {
		bfr.Clear();
	}
	public void Page_name_(byte[] page_name) {this.page_name = page_name;}
	public byte[] Convert(byte[] src) {	// text within hdr; EX: <h2>Abc</h2> -> Abc
		int end = src.length;
		tag_rdr.Init(page_name, src, 0, end);
		Convert_recurse(0, src, 0, end);
		return bfr.To_bry_and_clear_and_trim();
	}
	private static void Add_wo_comment(Bry_bfr bfr, byte[] src, int bgn, int end) {
		int pos = bgn;
		while (true) {
			int comm_bgn = Bry_find_.Find_fwd(src, Gfh_tag_.Comm_bgn, pos, end);
			if (comm_bgn != -1) {
				int tmp_pos = comm_bgn + Gfh_tag_.Comm_bgn_len;
				int comm_end = Bry_find_.Find_fwd(src, Gfh_tag_.Comm_end, tmp_pos, end);
				if (comm_end != -1) {
					bfr.Add_mid(src, pos, comm_bgn);
					pos = comm_end + Gfh_tag_.Comm_end_len;
					continue;
				}
			}
			bfr.Add_mid(src, pos, end);
			break;
		}
	}
	private void Convert_recurse(int depth, byte[] src, int pos, int end) {
		tag_rdr.Src_rng_(pos, end);
		while (pos < end) {
			Gfh_tag lhs = tag_rdr.Tag__move_fwd_head();
			int tag_id = lhs.Name_id();

			// add any text before lhs;
			int txt_end = lhs.Src_bgn();
			switch (tag_id) {
				case Gfh_tag_.Id__eos:		txt_end = end; break;			// eos; print everything until end
			}

			if (pos < txt_end) Add_wo_comment(bfr, src, pos, txt_end);	

			// set print_tag / recurse based on tag
			boolean print_tag = false, recurse = true;
			switch (tag_id) {
				case Gfh_tag_.Id__eos:		// eos; return;
					return;
				case Gfh_tag_.Id__i:		// always print tag
				case Gfh_tag_.Id__b:
					print_tag = true;
					break;
				case Gfh_tag_.Id__small:	// only print tag if not nested
				case Gfh_tag_.Id__sup:
				case Gfh_tag_.Id__any:
				default: {
					if (depth > 0)
						print_tag = true;
					break;
				}
				case Gfh_tag_.Id__comment:	// never print tag
				case Gfh_tag_.Id__img:		
				case Gfh_tag_.Id__br:
				case Gfh_tag_.Id__hr:
					print_tag = false;
					recurse = false;
					break;
				case Gfh_tag_.Id__a:		// a never prints tag; also, also, do not recurse if ref
					print_tag = false;						
					byte[] href_val = lhs.Atrs__get_as_bry(Gfh_atr_.Bry__href);
					if (Bry_.Has_at_bgn(href_val, gplx.xowa.xtns.cites.Ref_html_wtr_cfg.Note_href_bgn))	// do not print if href='#cite_note-...'; EX: <a href=\"#cite_note-0\">[1]</a>
						recurse = false;
					break;
			}

			// get lhs / rhs vars
			int lhs_bgn = lhs.Src_bgn(), lhs_end = lhs.Src_end();
			boolean lhs_is_pair = !lhs.Tag_is_inline();
			int rhs_bgn = -1, rhs_end = -1, new_pos = lhs_end;
			if (lhs_is_pair) {	// get rhs unless inline
				Gfh_tag rhs = tag_rdr.Tag__move_fwd_tail(tag_id);
				rhs_bgn = rhs.Src_bgn(); rhs_end = rhs.Src_end();
				new_pos = rhs_end;
			}

			// print "<tag></tag>"; also, recurse
			if (print_tag) bfr.Add_mid(src, lhs_bgn, lhs_end);
			if (recurse) {
				Convert_recurse(depth + 1, src, lhs_end, rhs_bgn);
			}
			if (print_tag && lhs_is_pair)
				bfr.Add_mid(src, rhs_bgn, rhs_end);

			// set new_pos
			pos = new_pos;
			tag_rdr.Src_rng_(new_pos, end);	// NOTE: must reinit pos and especially end
		}
	}
}
