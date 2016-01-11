package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
import gplx.core.ios.*;
public class XmlSplitRdr {
	public byte[] CurAry() {return curAry;} private byte[] curAry;
	public long CurSum() {return curSum;} long curSum;
	public int CurRead() {return curRead;} int curRead;
	public boolean Done() {return done;} private boolean done;		
	public XmlSplitRdr InitAll_(Io_url url) {
		stream = Io_mgr.Instance.OpenStreamRead(url);
		curLen = stream.Len();
		curAry = new byte[(int)curLen];
		curSum = 0;
		curRead = 0;
		done = false;
		return this;
	}
	public XmlSplitRdr Init_(Io_url url, int curArySize) {
		stream = Io_mgr.Instance.OpenStreamRead(url);
		curLen = Io_mgr.Instance.QueryFil(url).Size();
		curAry = new byte[curArySize];
		curSum = 0;
		curRead = 0;
		done = false;
		return this;
	}	IoStream stream; long curLen;
	public void Read() {
		curRead = stream.ReadAry(curAry);
		curSum += curRead;
		done = curSum == curLen;
		if (done && curRead != curAry.length) // on last pass, readAry may have garbage at end, remove
			curAry = Bry_.Resize(curAry, curRead);
	}
	public void Rls() {stream.Rls();}
}
