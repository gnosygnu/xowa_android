package gplx.core.envs; import gplx.*; import gplx.core.*;
public class Op_sys_ {
	public static boolean Wnt_invalid_char(byte b) {
		switch (b) {
			case Byte_ascii.Slash:
			case Byte_ascii.Backslash:
			case Byte_ascii.Lt:
			case Byte_ascii.Gt:
			case Byte_ascii.Colon:
			case Byte_ascii.Pipe:
			case Byte_ascii.Question:
			case Byte_ascii.Star:
			case Byte_ascii.Quote:		return true;
			default:					return false;
		}
	}
}
