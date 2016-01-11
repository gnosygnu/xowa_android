package gplx.core.intls; import gplx.*; import gplx.core.*;
public class String_surrogate_utl {
	public int Byte_pos() {return byte_pos;} int byte_pos;
	public int Count_surrogates__char_idx(byte[] src, int src_len, int byte_bgn, int char_idx)				{return Count_surrogates(src, src_len, byte_bgn, Bool_.Y, char_idx);}	
	public int Count_surrogates__codepoint_idx1(byte[] src, int src_len, int byte_bgn, int codepoint_idx)	{return Count_surrogates(src, src_len, byte_bgn, Bool_.N, codepoint_idx);}	
	private int Count_surrogates(byte[] src, int src_len, int byte_bgn, boolean stop_idx_is_char, int stop_idx) {
		int char_count = 0, codepoint_count = 0;
		byte_pos = byte_bgn;
		while (true) {
			if (	stop_idx == (stop_idx_is_char ? char_count : codepoint_count)		// requested # of chars found
				||	byte_pos >= src_len													// eos reached; DATE:2014-09-02
				) return codepoint_count - char_count;
			int char_len_in_bytes = gplx.core.intls.Utf8_.Len_of_char_by_1st_byte(src[byte_pos]);
			++char_count;												// char_count always incremented by 1
			codepoint_count += (char_len_in_bytes == 4) ? 2 : 1;		// codepoint_count incremented by 2 if surrogate pair; else 1
			byte_pos += char_len_in_bytes;
		} 
	}	
}
