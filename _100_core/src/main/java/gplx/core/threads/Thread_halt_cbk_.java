package gplx.core.threads; import gplx.*; import gplx.core.*;
public class Thread_halt_cbk_ {
	public static final    Thread_halt_cbk Noop = new Thread_halt_cbk_noop();
}
class Thread_halt_cbk_noop implements Thread_halt_cbk {
	public void Thread__on_halt(boolean interrupted) {}
}
