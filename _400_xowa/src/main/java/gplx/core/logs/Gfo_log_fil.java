package gplx.core.logs; import gplx.*; import gplx.core.*;
public class Gfo_log_fil {
	private final Bry_bfr fil_bfr = Bry_bfr.new_(), msg_bfr = Bry_bfr.new_();
	private final String key;
	private final Io_url dir;
	private final long size_max;
	private int file_idx;
	private Io_url fil_cur;
	private final Gfo_log_fmtr fmtr = new Gfo_log_fmtr();
	private final Gfo_log_fil session;
	public Gfo_log_fil(Gfo_log_fil session, String key, Io_url dir, long size_max) {
		this.session = session;
		this.key = key;
		this.dir = dir;
		this.size_max = size_max;
		this.fil_cur = Fil_new();
	}
	public void Add(String msg, Object... vals) {
		fmtr.Add(msg_bfr, msg, vals);
		Add_by_bfr(msg_bfr);
		msg_bfr.Clear();
	}
	public void Add_by_bfr(Bry_bfr msg_bfr) {
		if (msg_bfr.Len() + fil_bfr.Len() > size_max) {
			this.Flush();
			fil_cur = Fil_new();
		}
		fil_bfr.Add_bfr_and_preserve(msg_bfr);
		if (session != null) session.Add_by_bfr(msg_bfr);
	}
	public void Flush() {
		Io_mgr.Instance.AppendFilBfr(fil_cur, fil_bfr);
	}
	private Io_url Fil_new() {
		String part = size_max == -1 ? "" : "-" + Int_.To_str(++file_idx);
		return dir.OwnerDir().GenSubFil_ary(key, part, ".log");
	}
}
