package gplx;
import java.util.*;	//#<>System~java.util
public class RandomAdp {//_20110317
	private final Random under;
	public RandomAdp(Random v) {this.under = v;}
	public int Next(int max) {return under.nextInt(max);}	//#<>.Next~.nextInt
}
