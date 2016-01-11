package gplx;
public interface Gfo_usr_dlg extends Cancelable {
	void				Canceled_y_(); void Canceled_n_();
	Gfo_usr_dlg__log	Log_wkr(); void Log_wkr_(Gfo_usr_dlg__log v);
	Gfo_usr_dlg__gui	Gui_wkr(); void Gui_wkr_(Gfo_usr_dlg__gui v);
	String				Log_many(String grp_key, String msg_key, String fmt, Object... args);
	String				Warn_many(String grp_key, String msg_key, String fmt, Object... args);
	Err					Fail_many(String grp_key, String msg_key, String fmt, Object... args);
	String				Prog_many(String grp_key, String msg_key, String fmt, Object... args);
	String				Prog_none(String grp_key, String msg_key, String fmt);
	String				Note_many(String grp_key, String msg_key, String fmt, Object... args);
	String				Note_none(String grp_key, String msg_key, String fmt);
	String				Note_gui_none(String grp_key, String msg_key, String fmt);
	String				Prog_one(String grp_key, String msg_key, String fmt, Object arg);
	String				Prog_direct(String msg);
	String				Log_direct(String msg);
	String				Plog_many(String grp_key, String msg_key, String fmt, Object... args);
}
