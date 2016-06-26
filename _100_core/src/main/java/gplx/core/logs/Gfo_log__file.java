package gplx.core.logs; import gplx.*; import gplx.core.*;
public class Gfo_log__file extends Gfo_log__base {
	public final    Gfo_log_itm_wtr fmtr;
	private final    Bry_bfr bfr = Bry_bfr_.New();
	public Gfo_log__file(Io_url url, Gfo_log_itm_wtr fmtr) {
		this.url = url; this.fmtr = fmtr;
	}
	public Io_url Url() {return url;} private final    Io_url url;
	@Override public List_adp Itms() {return itms;} @Override public Gfo_log Itms_(List_adp v) {this.itms = v; return this;} private List_adp itms;
	@Override public void Exec(byte type, long time, long elapsed, String msg, Object[] args) {
		if (type == Gfo_log_itm.Type__prog) return;

		// add itm
		Gfo_log_itm itm = new Gfo_log_itm(type, time, elapsed, msg, args);
		itms.Add(itm);

		// flush if warning or failure; needed for download central
		switch (type) {
			case Gfo_log_itm.Type__note:
			case Gfo_log_itm.Type__warn:
			case Gfo_log_itm.Type__fail: this.Flush(); break;
		}
	}
	@Override public void Flush() {
		int len = itms.Len();
		for (int i = 0; i < len; ++i) {
			Gfo_log_itm itm = (Gfo_log_itm)itms.Get_at(i);
			fmtr.Write(bfr, itm);
		}
		byte[] bry = bfr.To_bry_and_clear(); if (bry.length == 0) return;	// don't bother writing empty bfr; happens during Xolog.Delete
		Io_mgr.Instance.AppendFilByt(url, bry);
		itms.Clear();
	}
}
