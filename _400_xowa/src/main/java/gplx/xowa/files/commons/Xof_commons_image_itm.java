package gplx.xowa.files.commons; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public class Xof_commons_image_itm {
	public Xof_commons_image_itm(String name, String media_type, String minor_mime, int size, int width, int height, int bits, int ext_id, String timestamp) {
		this.name = name; this.media_type = media_type; this.minor_mime = minor_mime; this.size = size; this.width = width; this.height = height; this.bits = bits; this.ext_id = ext_id; this.timestamp = timestamp;
	}
	public String Name() {return name;} private final String name;
	public String Media_type() {return media_type;} private final String media_type;
	public String Minor_mime() {return minor_mime;} private final String minor_mime;
	public int Size() {return size;} private final int size;
	public int Width() {return width;} private final int width;
	public int Height() {return height;} private final int height;
	public int Bits() {return bits;} private final int bits;
	public int Ext_id() {return ext_id;} private final int ext_id;
	public String Timestamp() {return timestamp;} private final String timestamp;
}
