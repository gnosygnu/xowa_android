package gplx.core.threads; import gplx.*; import gplx.core.*;
//#{import
import java.lang.*;
//#}
//#{Thread_adp
public class Thread_adp implements Runnable {
//#}
	private String name; private GfoInvkAble invk; private String cmd; private GfoMsg msg;
	@gplx.Internal protected Thread_adp(String name, GfoInvkAble invk, String cmd, GfoMsg msg) {
		this.name = name; this.invk = invk; this.cmd = cmd; this.msg = msg;
		this.ctor_ThreadAdp();
	}
	//#{lang
	void ctor_ThreadAdp() {
		this.thread = name == null ? new Thread(this) : new Thread(this, name);
	}
	public Thread Under_thread() {return thread;} private Thread thread;
	public Thread_adp Start() {
		thread.start();
		return this;
	}
	public void Interrupt() {thread.interrupt();}
	public void Join() {
		try {thread.join();}
		catch (Exception e) {Err_.Noop(e);}
	}
//	public void Stop() {thread.stop();}
	public boolean IsAlive() {return thread.isAlive();}
	@Override public void run() {
		invk.Invk(GfsCtx.Instance, 0, cmd, msg);
	}
	//#}
	public static final Thread_adp Null = new Thread_adp(Thread_adp_.Name_null, GfoInvkAble_.Null, "", GfoMsg_.Null);
}
