package gplx.core.threads.utils; import gplx.*; import gplx.core.*; import gplx.core.threads.*;
//#{imports
import java.util.concurrent.ArrayBlockingQueue;
//#}
public class Gfo_blocking_queue {
	//#{members
	private final ArrayBlockingQueue queue;
	//#}
	public Gfo_blocking_queue(int capacity) {
		this.capacity = capacity;
		//#{ctor
		this.queue = new ArrayBlockingQueue(capacity);
		//#}
	}
	public int Capacity() {return capacity;} private final    int capacity;
	public void Put(Object o) {
		//#{Put
		try {queue.put(o);}
		catch (InterruptedException e) {throw Err_.new_exc(e, "threads", "put interrupted");}
		//#}
	}
	public Object Take() {
		//#{Take
		try {return queue.take();}
		catch (InterruptedException e) {throw Err_.new_exc(e, "threads", "take interrupted");}
		//#}
	}
}
