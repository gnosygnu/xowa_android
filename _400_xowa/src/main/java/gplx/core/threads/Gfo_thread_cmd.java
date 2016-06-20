package gplx.core.threads; import gplx.*; import gplx.core.*;
public interface Gfo_thread_cmd extends Gfo_invk {
	void Cmd_ctor();
	String Async_key();
	int Async_sleep_interval();
	boolean Async_prog_enabled(); 
	void Async_prog_run(int async_sleep_sum);
	byte Async_init();
	boolean Async_term();
	void Async_run();
	boolean Async_running();
	Gfo_thread_cmd Async_next_cmd(); void Async_next_cmd_(Gfo_thread_cmd next);
}
