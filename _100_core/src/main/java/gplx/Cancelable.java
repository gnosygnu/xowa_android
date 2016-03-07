package gplx;
public interface Cancelable {
	boolean Canceled();
	void Cancel();
	void Cancel_reset();
	boolean Cancel_ackd(); void Cancel_ackd_();
}
