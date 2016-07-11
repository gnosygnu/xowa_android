package gplx.core.btries; import gplx.*; import gplx.core.*;
import gplx.core.threads.poolables.*;
public class Btrie_rv {
	public Object Obj() {return obj;} private Object obj;
	public int Pos() {return pos;} private int pos;
	public Btrie_rv Init(int pos, Object obj) {
		this.obj = obj;
		this.pos = pos;
		return this;
	}
}
