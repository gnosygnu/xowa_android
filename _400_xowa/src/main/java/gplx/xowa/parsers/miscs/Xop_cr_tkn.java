package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_cr_tkn extends Xop_tkn_itm_base {
	public Xop_cr_tkn(int bgn, int end) {this.Tkn_ini_pos(true, -1, -1);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_cr;}
}
/*
NOTE_1:tabs
. tabs exist in wikimedia source; note that tabs (\t) are not a meaningful HTML character
. xowa uses tabs for delimiters in its xowa files
. in order to maintain some semblance of fidelity, "\t" was replaced with &#09;
. unfortunately, "\t" is generally trimmed as whitespace throughout mediawiki; "&#09;" is not
. so, as a HACK, replace "&#09;" with "\t\s\s\s\s";
.. note that all 5 chars of "&#09;" must be replaced; hence "\t\s\s\s\s"
.. note that they all need to be ws in order to be trimmed out
.. note that shrinking the src[] would be (a) memory-expensive (b) complexity-expensive (many functions assume a static src size)
.. note that "\t\t\t\t\t" was the 1st attempt, but this resulted in exponential growth of "\t"s with each save (1 -> 5 -> 25 -> 125). "\t\s\s\s\s" is less worse with its linear growth (1 -> 5 -> 10)
. TODO: swap out the "&#09;" at point of file-read;
*/