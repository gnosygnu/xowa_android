package gplx.dbs.metas.parsers; import gplx.*; import gplx.dbs.*; import gplx.dbs.metas.*;
import gplx.core.brys.*;
public class Sql_bry_rdr extends Bry_rdr {	//#*inherit
	public byte[] Read_sql_identifier() {
		this.Skip_ws();
		int bgn = pos, end = -1;
		if (pos == src_end) return null;
		if (src[pos] == Byte_ascii.Brack_bgn) {	// EX: [name with space]
			bgn = ++pos;	// set bgn after [
			end = this.Find_fwd_lr(Byte_ascii.Brack_end);
		}
		else {
			this.Skip_alpha_num_under();	// ASSUME: identifier is ASCII and alpha / num / underscore
			if (pos == bgn) return null;	// String is not identifier; EX: "!@#"
			end = pos;
		}
		return Bry_.Mid(src, bgn, end);
	}
	@Override public Bry_rdr Skip_ws() {
		byte b_0 = pos < src_end ? src[pos] : Byte_ascii.Null;
		byte bgn_1 = Byte_ascii.Null;
		byte[] end_bry = null;
		switch (b_0) {
			case Byte_ascii.Dash:	bgn_1 = Byte_ascii.Dash;		end_bry = Comm_end_line; break;
			case Byte_ascii.Slash:	bgn_1 = Byte_ascii.Star;	end_bry = Comm_end_multi; break;
			case Byte_ascii.Tab: case Byte_ascii.Nl: case Byte_ascii.Cr: case Byte_ascii.Space:
				++pos;
				return super.Skip_ws();
			default:
				return this;					
		}
		byte b_1 = pos + 1 < src_end ? src[pos + 1] : Byte_ascii.Null;
		if (b_1 != bgn_1) return this;
		int end_pos = Bry_find_.Find_fwd(src, end_bry, pos + 2, src_end);
		if (end_pos == Bry_find_.Not_found) return this;
		pos = end_pos + end_bry.length;
		return super.Skip_ws();
	}
	private static final byte[] Comm_end_line = Byte_ascii.Nl_bry, Comm_end_multi = Bry_.new_a7("*/");
}
