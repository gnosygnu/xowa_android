package gplx.langs.phps; import gplx.*; import gplx.langs.*;
import gplx.core.log_msgs.*;
public interface Php_tkn_wkr {
	void Init(Php_ctx ctx);
	void Process(Php_tkn tkn);
	void Msg_many(byte[] src, int bgn, int end, Gfo_msg_itm itm, Object... args);
}
class Php_tkn_wkr_tkn implements Php_tkn_wkr {
	public void Init(Php_ctx ctx) {}
	public List_adp List() {return lines;} List_adp lines = List_adp_.new_();
	public Gfo_msg_log Msg_log() {return msg_log;} Gfo_msg_log msg_log = new Gfo_msg_log("gplx.langs.phps");
	public void Clear() {lines.Clear(); msg_log.Clear();}
	public void Process(Php_tkn tkn) {
		lines.Add(tkn);
	}
	public void Msg_many(byte[] src, int bgn, int end, Gfo_msg_itm itm, Object... args) {
		msg_log.Add_itm_many(itm, src, bgn, end, args);
	}
}
