package gplx;
public interface Gfo_usr_dlg__log extends Gfo_invk {
	boolean Enabled(); void Enabled_(boolean v);
	boolean Queue_enabled(); void Queue_enabled_(boolean v);
	Io_url Log_dir(); void Log_dir_(Io_url v);
	Io_url Session_dir();
	Io_url Session_fil();
	void Log_msg_to_url_fmt(Io_url url, String fmt, Object... args);
	void Log_to_session(String txt);
	void Log_to_session_fmt(String fmt, Object... args);
	void Log_to_session_direct(String txt);
	void Log_to_err(String txt);
	void Log_term();
}
