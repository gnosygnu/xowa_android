package gplx;
public class Char_ {//_20101203
	public static final Class<?> Cls_ref_type = Character.class;//#<>typeof(char)~Character.class
	public static final char Null = '\0', NewLine = '\n';
	public static boolean IsCaseLower(char c) {return Character.isLowerCase(c);}				//#<>char.IsLower~Character.isLowerCase
	public static boolean IsLetterOrDigit(char c) {return Character.isLetterOrDigit(c);}	//#<>char.IsLetterOrDigit~Character.isLetterOrDigit
	public static boolean IsLetterEnglish(char c) {
		switch (c) {
			case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': 
			case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': 
			case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
			case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J': 
			case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': 
			case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z': return true;
			default: return false;
		}
	}
	public static boolean IsLetterLowerEnglish(char c) {
		switch (c) {
			case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': 
			case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': 
			case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': return true;
			default: return false;
		}
	}
	public static boolean IsNumber(char c) {
		switch (c) {
			case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return true;
			default: return false;
		}
	}
	public static boolean IsWhitespace(char c) {
		switch (c) {
			case ' ': case '\t': case '\n': case '\r': return true;
			default: return false;
		}
	}
	public static boolean In(char match, char... ary) {
		for (char itm : ary)
			if (itm == match) return true;
		return false;
	}
	public static int To_int_or(char c, int or) {
		switch (c) {
			case '0': return 0; case '1': return 1; case '2': return 2; case '3': return 3; case '4': return 4;
			case '5': return 5; case '6': return 6; case '7': return 7; case '8': return 8; case '9': return 9;
			default: return or;
		}
	}
	public static String To_str(char[] ary, int pos, int length) {return new String(ary, pos, length);}
	public static String To_str(int b) {return To_str((char)b);}
	public static String To_str(char c) {return String.valueOf(c);}	//#<>c.toString()~String.valueOf(c)
	public static char By_int(int i) {return (char)i;}
	public static char cast(Object o) {try {return (Character)o;} catch(Exception e) {throw Err_.new_type_mismatch_w_exc(e, char.class, o);}}//#<>(char)o~(Character)o
	public static char parse(String raw) {try {return raw.charAt(0);} catch(Exception exc) {throw Err_.new_parse_exc(exc, char.class, raw);}}//#<>char.Parse(raw)~raw.charAt(0)
}
