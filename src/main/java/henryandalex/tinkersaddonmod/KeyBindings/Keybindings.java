package henryandalex.tinkersaddonmod.KeyBindings;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;


public enum Keybindings {
	
	BEAM("key.TCAddonMod.beam", Keyboard.KEY_G), SPIN("key.TCAddonMod.spin", Keyboard.KEY_H);
	

	private final KeyBinding keybinding;
	
	private Keybindings(String keyName, int defaultKeyCode) {
		keybinding = new KeyBinding(keyName, defaultKeyCode, "key.categories.TCAddonMod");
	}
	
	public KeyBinding getKeyBind() {
		return keybinding;
	}
	
	public boolean isPressed() {
		return keybinding.isPressed();
	}

}
