package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
public class XmlFileSplitterOpts {
	public int FileSizeMax() {return fileSizeMax;} public XmlFileSplitterOpts FileSizeMax_(int v) {fileSizeMax = v; return this;} int fileSizeMax = 1024 * 1024;
	public String[] XmlNames() {return xmlNames;} public XmlFileSplitterOpts XmlNames_(String... v) {xmlNames = v; return this;} private String[] xmlNames;
	public String XmlBgn() {return xmlBgn;} public XmlFileSplitterOpts XmlBgn_(String v) {xmlBgn = v; return this;} private String xmlBgn;
	public String XmlEnd() {return xmlEnd;} public XmlFileSplitterOpts XmlEnd_(String v) {xmlEnd = v; return this;} private String xmlEnd;
	public Io_url PartDir() {return partDir;} public XmlFileSplitterOpts PartDir_(Io_url v) {partDir = v; return this;} Io_url partDir;
	public String StatusFmt() {return statusFmt;} public XmlFileSplitterOpts StatusFmt_(String v) {statusFmt = v; return this;} private String statusFmt = "splitting {0}";
	public HierStrBldr Namer() {return namer;} HierStrBldr namer = new HierStrBldr();
}
