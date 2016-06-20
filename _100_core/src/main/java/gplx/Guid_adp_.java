package gplx;
public class Guid_adp_ {//_20101127
	public static final String Cls_ref_name = "Guid";
	public static final    Guid_adp Empty = Parse("00000000-0000-0000-0000-000000000000");
	public static String New_str() {return New().To_str();}
	public static Guid_adp New() {return new Guid_adp(java.util.UUID.randomUUID());}//#<>System.Guid.NewGuid()~java.util.UUID.randomUUID()
	public static Guid_adp Parse(String s) {return new Guid_adp(java.util.UUID.fromString(s));}//#<>new System.Guid~java.util.UUID.fromString
}