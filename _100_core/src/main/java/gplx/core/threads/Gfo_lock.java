package gplx.core.threads; import gplx.*; import gplx.core.*;
//#{import
import java.util.concurrent.locks.*;
//#}
public class Gfo_lock {
	//#{members
	private final ReentrantLock lock = new ReentrantLock(true);
	//#}
	public void Lock() {
		//#{Lock
		lock.lock();
		//#}
	}
	public void Unlock() {
		//#{Unlock
		lock.unlock();
		//#}
	}
}
