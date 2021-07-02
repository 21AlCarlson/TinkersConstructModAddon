package henryandalex.tinkersaddonmod.utils;

public class Util {
	
	public static final String RESOURCE = Reference.MODID;
	
	/**
	 * Returns the given Resource prefixed with tinkers resource location. Use this function instead of hardcoding
	 * resource locations.
	 * {@link slimeknights.tconstruct.library.Util#resource(String)
	 */
	public static String resource(String res) {
		return String.format("%s:%s", RESOURCE, res);
	}
}
