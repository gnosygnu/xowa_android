package gplx.core.log_msgs; import gplx.*; import gplx.core.*;
public class Gfo_msg_data {
	public int Uid() {return uid;} int uid = uid_next++;
	public Gfo_msg_itm Item() {return item;} Gfo_msg_itm item;
	public Object[] Vals() {return vals;} Object[] vals;
	public byte[] Src_bry() {return src_bry;} private byte[] src_bry;
	public int Src_bgn() {return src_bgn;} int src_bgn;
	public int Src_end() {return src_end;} int src_end;
	public Gfo_msg_data Ctor_val_many(Gfo_msg_itm item, Object[] vals) {this.item = item; this.vals = vals; return this;}
	public Gfo_msg_data Ctor_src_many(Gfo_msg_itm item, byte[] src_bry, int src_bgn, int src_end, Object[] vals) {this.item = item; this.src_bry = src_bry; this.src_bgn = src_bgn; this.src_end = src_end; this.vals = vals; return this;}
	public void Clear() {
		item = null; vals = null; src_bry = null;
	}
	public String Gen_str_ary() {return item.Gen_str_ary(vals);}
	static int uid_next = 0;
	public static final Gfo_msg_data[] Ary_empty = new Gfo_msg_data[0];
}
