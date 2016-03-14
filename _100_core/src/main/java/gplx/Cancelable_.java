package gplx;
public class Cancelable_ {
	public static final Cancelable Never = new Cancelable_never();
	public static Cancelable New_proxy() {return new Cancelable_proxy();}
}
class Cancelable_never implements Cancelable {
	public boolean Canceled() {return false;}
	public void Cancel() {}
}
class Cancelable_proxy implements Cancelable {
	private boolean canceled = false;
	public boolean Canceled()		{return canceled;}
	public void Cancel()		{canceled = true;}
}
