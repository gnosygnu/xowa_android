//namespace gplx.xowa.htmls.core.wkrs.bfr_args {
//	using gplx.xowa.htmls.hrefs; using gplx.xowa.wikis.nss;
//	public class Bfr_arg__href : gplx.core.brys.Bfr_arg_clearable {
//		private byte[] val; private int val_bgn, val_end;
//		private int href_type;
////		public void Clear() {
////			href_type = Tid__null;
////		}
//		public void Set_by_atr(gplx.langs.htmls.docs.Gfh_atr atr) {Set_by_mid(atr.Src(), atr.Val_bgn(), atr.Val_end());}
//		public void Set_by_mid(byte[] v, int bgn, int end)	{this.val = v; this.href_type = Tid__mid; this.val_bgn = bgn; this.val_end = end;}
//		public void Set_by_raw(byte[] v)					{this.val = v; this.href_type = Tid__raw;}
//		public void Set_by_page(byte[] v)					{this.val = v; this.href_type = Tid__page;}
//		public void Set_by_file(byte[] v)					{this.val = v; this.href_type = Tid__file;}
//		public void Bfr_arg__add(Bry_bfr bfr) {
//			if (val == null) return;
//			switch (href_type) {
//				case Tid__raw:
//					bfr.Add(val);
//					break;
//				case Tid__mid:
//					bfr.Add_mid(val, val_bgn, val_end);
//					break;
//				case Tid__page:
//					bfr.Add(Xoh_href_.Bry__wiki);	// '/wiki/'
//					bfr.Add(val);					// 'File:A.png'
//					break;
//				case Tid__file:
//					bfr.Add(Xoh_href_.Bry__wiki).Add(Xow_ns_.Bry__file).Add_byte_colon();
//					bfr.Add(val);
//					break;
//			}
//		}
//		private static final int Tid__null = 0, Tid__raw = 1, Tid__page = 2, Tid__file = 3, Tid__mid = 4;
//	}
//}
