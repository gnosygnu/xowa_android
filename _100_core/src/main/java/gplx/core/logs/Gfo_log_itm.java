package gplx.core.logs; import gplx.*; import gplx.core.*;
public class Gfo_log_itm {
	public Gfo_log_itm(byte type, long time, long elapsed, String msg, Object[] args) {
		this.Type = type;
		this.Time = time;
		this.Elapsed = elapsed;
		this.Msg = msg;
		this.Args = args;
	}
	public final    byte Type;
	public final    long Time;
	public final    long Elapsed;
	public final    String Msg;
	public final    Object[] Args;

	public static final byte Type__fail = 0, Type__warn = 1, Type__note = 2, Type__info = 3, Type__prog = 4;
}
