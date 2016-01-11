package gplx;
public class Gfo_usr_dlg__log_ {
        public static final Gfo_usr_dlg__log Noop = new Gfo_usr_dlg__log_noop();
}
class Gfo_usr_dlg__log_noop implements Gfo_usr_dlg__log {
	public Io_url Session_fil() {return Io_url_.Empty;}
	public Io_url Session_dir() {return Io_url_.Empty;}
	public Io_url Log_dir() {return Io_url_.Empty;} public void Log_dir_(Io_url v) {}
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled;
	public boolean Queue_enabled() {return queue_enabled;} public void Queue_enabled_(boolean v) {queue_enabled = v;} private boolean queue_enabled;
	public void Log_msg_to_url_fmt(Io_url url, String fmt, Object... args) {}
	public void Log_to_session_fmt(String fmt, Object... args) {}
	public void Log_to_session(String txt) {}
	public void Log_to_session_direct(String txt) {}
	public void Log_to_err(String txt) {}
	public void Log_term() {}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
}
