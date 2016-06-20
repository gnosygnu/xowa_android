package gplx.xowa.addons.servers.https; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.servers.*;
public class Http_long_poll_cmd implements gplx.xowa.htmls.bridges.Bridge_cmd_itm {
	private final    List_adp msgs = List_adp_.New();
	private long send_time_prv = 0;
	public int Sleep_interval = 100;
	public int Send_interval = 1000;

	public void Init_by_app(Xoa_app app) {}
	public void Send_msg(String msg) {
		msgs.Add(msg);
	}
	public String Exec(gplx.langs.jsons.Json_nde data) {
		while (true) {
			if (msgs.Len() == 0) {
				gplx.core.threads.Thread_adp_.Sleep(Sleep_interval);
			}
			else {
				long send_time_cur = gplx.core.envs.Env_.TickCount();
				if (send_time_cur - send_time_prv > Send_interval) {
					send_time_prv = send_time_cur;
					break;
				}
			}
		}
		return String_.Concat_lines_nl(msgs.To_str_ary_and_clear());
	}

	public byte[] Key() {return BRIDGE_KEY;}
	public static final    byte[] BRIDGE_KEY = Bry_.new_a7("long_poll");
        public static final    Http_long_poll_cmd Instance = new Http_long_poll_cmd(); Http_long_poll_cmd() {}
}
