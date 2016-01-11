package gplx;
public class GfoMsgUtl {
	public static int SetInt(GfsCtx ctx, GfoMsg m, int cur)						{return ctx.Deny() ? cur : m.ReadIntOr("v", cur);}
	public static boolean SetBool(GfsCtx ctx, GfoMsg m, boolean cur)					{return ctx.Deny() ? cur : m.ReadBoolOr("v", cur);}
	public static String SetStr(GfsCtx ctx, GfoMsg m, String cur)				{return ctx.Deny() ? cur : m.ReadStrOr("v", cur);}
	public static Io_url SetIoUrl(GfsCtx ctx, GfoMsg m, Io_url cur)				{return ctx.Deny() ? cur : m.ReadIoUrlOr("v", cur);}
	public static Decimal_adp SetDecimal(GfsCtx ctx, GfoMsg m, Decimal_adp cur)	{return ctx.Deny() ? cur : m.ReadDecimalOr("v", cur);}
}
