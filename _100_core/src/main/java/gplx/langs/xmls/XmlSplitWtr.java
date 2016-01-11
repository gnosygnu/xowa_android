package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
import gplx.core.ios.*;
public class XmlSplitWtr {
	public Io_url Url() {return url;} Io_url url;
	public XmlSplitWtr Init_(Io_url partDir, byte[] hdr, XmlFileSplitterOpts opts) {
		this.partDir = partDir; this.hdr = hdr; this.opts = opts;
		return this;
	}
	public void Bgn(int partIdx) {
		String partStr = opts.Namer().GenStrIdxOnly(partIdx);
		url = Io_url_.mem_fil_(partStr);
		stream = Io_mgr.Instance.OpenStreamWrite(url);
		init = true;
	}	boolean init = true; byte[] hdr; XmlFileSplitterOpts opts; Io_url partDir; IoStream stream;
	public void Write(byte[] ary) {
		if (init) {
			stream.WriteAry(hdr);
			init = false;
		}
		stream.WriteAry(ary);
	}
	public void Rls() {stream.Rls();}
}
