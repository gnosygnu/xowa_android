package gplx.core.ios; import gplx.*; import gplx.core.*;
public class Io_line_rdr_key_gen_ {//_20120616
	public static final Io_line_rdr_key_gen first_pipe	= new Io_line_rdr_key_gen_first(Byte_ascii.Pipe);
	public static final Io_line_rdr_key_gen last_pipe	= new Io_line_rdr_key_gen_last(Byte_ascii.Pipe);
	public static final Io_line_rdr_key_gen noop			= new Io_line_rdr_key_gen_noop();
}
class Io_line_rdr_key_gen_last implements Io_line_rdr_key_gen {//_20120616
	public Io_line_rdr_key_gen_last(byte fld_dlm) {this.fld_dlm = fld_dlm;} private byte fld_dlm;
	public void Gen(Io_line_rdr bfr) {
		int bgn = bfr.Itm_pos_bgn(), end = bfr.Itm_pos_end() - 1;		// -1: ignore row_dlm
		bfr.Key_pos_bgn_(end).Key_pos_end_(end);
		byte[] bry = bfr.Bfr();
		for (int i = end; i >= bgn; i--) {
			if (bry[i] == fld_dlm) {
				bfr.Key_pos_bgn_(i + 1);	// +1 to position after fldDlm
				return;
			}
		}
		bfr.Key_pos_bgn_(0);	// nothing found; position at bgn
	}
}
class Io_line_rdr_key_gen_first implements Io_line_rdr_key_gen {//_20120616
	public Io_line_rdr_key_gen_first(byte fld_dlm) {this.fld_dlm = fld_dlm;} private byte fld_dlm;
	public void Gen(Io_line_rdr bfr) {
		int bgn = bfr.Itm_pos_bgn(), end = bfr.Itm_pos_end();
		bfr.Key_pos_bgn_(bgn).Key_pos_end_(bgn);
		byte[] bry = bfr.Bfr();
		for (int i = bgn; i < end; i++) {
			if (bry[i] == fld_dlm) {
				bfr.Key_pos_end_(i);
				return;
			}
		}
		bfr.Key_pos_end_(end);	// nothing found; position at end
	}
}
class Io_line_rdr_key_gen_noop implements Io_line_rdr_key_gen {
	public void Gen(Io_line_rdr bfr) {}
}
