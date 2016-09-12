package gplx.xowa.wikis.pages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_db_protection {
	public Xopg_db_protection() {this.Clear();}
	public byte[]	User() {return user;} public Xopg_db_protection User_(byte[] v) {user = v; return this;} private byte[] user;
	public byte[]	Protection_level() {return protection_level;} public Xopg_db_protection Protection_level_(byte[] v) {protection_level = v; return this;} private byte[] protection_level;
	public byte[]	Protection_expiry() {return protection_expiry;} private byte[] protection_expiry;

	public void Clear() {
		this.user = Bry_.Empty;
		this.protection_level = Bry_.Empty;
		this.protection_expiry = Bry__protection_expiry__infinite;
	}

	public static final    byte[] Bry__protection_expiry__infinite = Bry_.new_a7("infinity");// NOTE: means page never expires; must be "infinity" as per en.w:Module:Effective_protection_expiry DATE:2016-08-05
}
