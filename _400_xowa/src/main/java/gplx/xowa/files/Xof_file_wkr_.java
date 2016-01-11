package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.consoles.*; import gplx.langs.htmls.encoders.*;
public class Xof_file_wkr_ {
	public static final Gfo_url_encoder Md5_decoder = Gfo_url_encoder_.New__http_url().Init__same__many(Byte_ascii.Plus).Make();
	public static byte[] Md5_fast(byte[] v) {return Bry_.new_a7(gplx.core.security.HashAlgo_.Md5.CalcHash(Console_adp_.Noop, gplx.core.ios.IoStream_.ary_(v)));}
	public static byte[] Md5(byte[] ttl) {
		ttl = Md5_decoder.Decode(Ttl_standardize(ttl));
		return Xof_file_wkr_.Md5_fast(ttl);					// NOTE: md5 is calculated off of url_decoded ttl; EX: A%2Cb is converted to A,b and then md5'd. note that A%2Cb still remains the title
	}
	public static byte[] Ttl_standardize(byte[] ttl) {
		int ttl_len = ttl.length;
		for (int i = 0; i < ttl_len; i++) {	// convert all spaces to _; NOTE: not same as lnki.Ttl().Page_url(), b/c Page_url does incompatible encoding
			byte b = ttl[i];
			if (b == Byte_ascii.Space) ttl[i] = Byte_ascii.Underline;
			if (i == 0) {
				if (b > 96 && b < 123) ttl[i] -= 32;	// NOTE: file automatically uppercases 1st letter
			}
		}
		return ttl;
	}
}
