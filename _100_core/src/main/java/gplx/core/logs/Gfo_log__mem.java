package gplx.core.logs; import gplx.*; import gplx.core.*;
public class Gfo_log__mem extends Gfo_log__base {		
	@Override public List_adp Itms() {return itms;} @Override public Gfo_log Itms_(List_adp v) {this.itms = v; return this;} private List_adp itms = List_adp_.New();		
	@Override public void Exec(byte type, long time, long elapsed, String msg, Object[] args) {
		Gfo_log_itm itm = new Gfo_log_itm(type, time, elapsed, msg, args);
		itms.Add(itm);
	}
	@Override public void Flush() {}
}
