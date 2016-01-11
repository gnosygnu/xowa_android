package gplx.gfui; import gplx.*;
public class GfuiTextBox extends GfuiElemBase {
	public static final String SelectionStartChanged_evt = "SelectionStartChanged";

	public boolean Border_on() {return textBox.Border_on();} public void Border_on_(boolean v) {BorderOn_set(v);}
	public int SelBgn() {return textBox.SelBgn();} public void SelBgn_set(int v) {textBox.SelBgn_set(v); GfoEvMgr_.Pub(this, SelectionStartChanged_evt);}
	public int SelLen() {return textBox.SelLen();} public void SelLen_set(int v) {textBox.SelLen_set(v);}
	public String SelText() {
		return String_.MidByLen(this.TextMgr().Val(), this.SelBgn(), this.SelLen());
	}
	public void Focus_select_all() {
		this.Focus();
		this.SelBgn_set(0);
		int len = String_.Len(this.Text());
		this.SelLen_set(len);
	}
	public boolean OverrideTabKey() {return textBox.OverrideTabKey();} public void OverrideTabKey_(boolean val) {textBox.OverrideTabKey_(val);}
	public void Margins_set(int left, int top, int right, int bot) {textBox.Margins_set(left, top, right, bot);}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_Margins_)) {
			int u = m.ReadInt("u");
			int l = m.ReadInt("l");
			int d = m.ReadInt("d");
			int r = m.ReadInt("r");
			if (ctx.Deny()) return this;
			Margins_set(l, u, r, d);
			return this;
		}
		else	return super.Invk (ctx, ikey, k, m);
	}	static final String Invk_Margins_ = "Margins_";
	void BorderOn_set(boolean val) {
		textBox.Border_on_(val);
		if (val == false)
			this.Height_(13); // WORKAROUND (WinForms): if border is off, height automatically becomes 13 and immutable for singleLine fields; affects statusBox in opal.imgs which will show with small gap over bottom of screen
	}
	@gplx.Internal @Override protected void Click_key_set_(String v) {}// TextBox's shouldn't have clickKeys; among other things, .Text is used to set ClickKey, which for textBox may be very large

	@gplx.Internal protected void SetTextBox(GxwTextFld textBox) {this.textBox = textBox;}
	@gplx.Internal protected void CreateControlIfNeeded() {textBox.CreateControlIfNeeded();}
	@Override public GxwElem UnderElem_make(KeyValHash ctorArgs) {return GxwElemFactory_.Instance.text_fld_();}
	@Override public void ctor_GfuiBox_base(KeyValHash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		textBox = (GxwTextFld)this.UnderElem();
	}	GxwTextFld textBox;
	@Override public void ctor_kit_GfuiElemBase(Gfui_kit kit, String key, GxwElem underElem, KeyValHash ctorArgs) {
		super.ctor_kit_GfuiElemBase(kit, key, underElem, ctorArgs);
		textBox = (GxwTextFld)underElem;
	}
	public static final String CFG_border_on_ = "border_on_";
}
