package gplx.core.threads; import gplx.*; import gplx.core.*;
//#{import
//#}
public class Thread_adp_ {
	//#{lang
	public static void Sleep(int milliseconds) {
		try {Thread.sleep(milliseconds);} catch (InterruptedException e) {throw Err_.new_exc(e, "core", "thread interrupted", "milliseconds", milliseconds);}
	}
	public static void Notify_all(Object o) {o.notifyAll();}
	public static void Wait(Object o) {
		try {o.wait();}
		catch (InterruptedException e) {throw Err_.new_exc(e, "core", "thread wait");}
	}
	//#}
	public static Thread_adp invk_(GfoInvkAble invk, String cmd)					{return invk_(Name_null, invk, cmd);}
	public static Thread_adp invk_(String name, GfoInvkAble invk, String cmd)		{return new Thread_adp(name, invk, cmd, GfoMsg_.Null);}
	public static Thread_adp invk_msg_(GfoInvkAble invk, GfoMsg msg)				{return invk_msg_(Name_null, invk, msg);}
	public static Thread_adp invk_msg_(String name, GfoInvkAble invk, GfoMsg msg)	{return new Thread_adp(name, invk, msg.Key(), msg);}
	public static void Run_invk_msg(String name, GfoInvkAble invk, GfoMsg m) {
		Thread_adp_.invk_msg_(name, invk, m).Start();
	}
	public static void Run_cmd(boolean async, String thread_name, GfoInvkAble invk, String cmd) {
		GfoMsg msg = GfoMsg_.new_cast_(cmd);
		if (async)
			Thread_adp_.invk_msg_(thread_name, invk, msg).Start();
		else
			GfoInvkAble_.InvkCmd_msg(invk, cmd, msg);
	}
	public static final    String Name_null = null;
}
