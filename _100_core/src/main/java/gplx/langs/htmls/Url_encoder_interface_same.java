package gplx.langs.htmls; import gplx.*; import gplx.langs.*;
public class Url_encoder_interface_same implements Url_encoder_interface {
	public String Encode_str(String v) {return v;}
	public byte[] Encode_bry(String v) {return Bry_.new_u8(v);}
        public static final Url_encoder_interface_same Instance = new Url_encoder_interface_same(); Url_encoder_interface_same() {}
}
