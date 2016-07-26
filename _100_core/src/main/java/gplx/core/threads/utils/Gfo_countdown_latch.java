package gplx.core.threads.utils; import gplx.*; import gplx.core.*; import gplx.core.threads.*;
//#{imports
import java.util.concurrent.CountDownLatch;
//#}
public class Gfo_countdown_latch {
	//#{members
	private final CountDownLatch latch;
	//#}
	public Gfo_countdown_latch(int count) {
		//#{ctor
		latch = new CountDownLatch(count);
		//#}
	}
	public void Countdown() {
		//#{Countdown
		latch.countDown();
		//#}
	}
	public void Await() {
		//#{Await
		try {latch.await();}
		catch (InterruptedException e) {throw Err_.new_exc(e, "threads", "await interrupted");}
		//#}
	}
}
