package gplx.xowa.xtns.scribunto.engines; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
public interface Scrib_server {
	void Init(String... process_args);
	int Server_timeout(); Scrib_server Server_timeout_(int v);
	int Server_timeout_polling(); Scrib_server Server_timeout_polling_(int v);
	int Server_timeout_busy_wait(); Scrib_server Server_timeout_busy_wait_(int v);
	byte[] Server_comm(byte[] cmd, Object[] cmd_objs);
	void Server_send(byte[] cmd, Object[] cmd_objs);
	byte[] Server_recv();
	void Term();
}
