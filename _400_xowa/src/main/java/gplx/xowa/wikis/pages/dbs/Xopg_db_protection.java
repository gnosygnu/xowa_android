package gplx.xowa.wikis.pages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_db_protection {
	public byte[]	User() {return user;} public Xopg_db_protection User_(byte[] v) {user = v; return this;} private byte[] user = Bry_.Empty;
	public byte[]	Protection_level() {return protection_level;} public Xopg_db_protection Protection_level_(byte[] v) {protection_level = v; return this;} private byte[] protection_level = Bry_.Empty;
	public byte[]	Protection_expiry() {return protection_expiry;} private byte[] protection_expiry = Bry__protection_expiry__infinite;

	public static final    byte[] Bry__protection_expiry__infinite = Bry_.new_a7("infinite");
}
