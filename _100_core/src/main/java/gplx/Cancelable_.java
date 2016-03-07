package gplx;
public class Cancelable_ {
	public static final Cancelable Never = new Cancelable_never();
	public static Cancelable New_proxy() {return new Cancelable_proxy();}
}
class Cancelable_never implements Cancelable {
	public boolean Canceled() {return false;}
	public void Cancel() {}
	public void Cancel_reset() {}
	public void Cancel_notify() {}
	public boolean Cancel_ackd() {return false;} public void Cancel_ackd_() {}
}
class Cancelable_proxy implements Cancelable {
	private boolean canceled = false;
	private boolean cancel_ackd = false;
	public boolean Canceled()		{return canceled;}
	public void Cancel()		{canceled = true;}
	public void Cancel_reset()	{canceled = false; cancel_ackd = false;}
	public boolean Cancel_ackd() {return cancel_ackd;} public void Cancel_ackd_() {cancel_ackd = true;}
	public void Cancel_notify() {}
}
