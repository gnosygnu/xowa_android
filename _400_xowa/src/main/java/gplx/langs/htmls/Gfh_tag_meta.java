package gplx.langs.htmls; import gplx.*; import gplx.langs.*;
public class Gfh_tag_meta {
	public Gfh_tag_meta(int id, String key_str) {
		this.id = id;
		this.key_str = key_str;
		this.key_bry = Bry_.new_u8(key_str);
	}
	public int Id() {return id;} private final    int id;
	public String Key_str() {return key_str;} private final    String key_str;
	public byte[] Key_bry() {return key_bry;} private final    byte[] key_bry;
}