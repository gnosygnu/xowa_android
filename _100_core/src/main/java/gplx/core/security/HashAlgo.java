package gplx.core.security; import gplx.*; import gplx.core.*;
import gplx.core.consoles.*;
import gplx.core.ios.*; /*IoStream*/
public interface HashAlgo {
	String Key();
	String CalcHash(Console_adp dialog, IoStream stream);
	byte[] Calc_hash_bry(byte[] v);
}
