package gplx;
public interface Cancelable {
	boolean Canceled();
	void Cancel();
	void Cancel_reset();
}
