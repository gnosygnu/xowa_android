package gplx;
public class Guid_adp_ {//_20101127
	public static final String Cls_ref_name = "Guid";
	public static final Guid_adp Empty = parse("00000000-0000-0000-0000-000000000000");
	public static Guid_adp new_() {return new Guid_adp(java.util.UUID.randomUUID());}//#<>System.Guid.NewGuid()~java.util.UUID.randomUUID()
	public static Guid_adp parse(String s) {return new Guid_adp(java.util.UUID.fromString(s));}//#<>new System.Guid~java.util.UUID.fromString
}