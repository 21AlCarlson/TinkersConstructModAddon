package henryandalex.tinkersaddonmod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelSewerCroc extends ModelBase {
	private final ModelRenderer base;
	private final ModelRenderer bottomjaw;
	private final ModelRenderer bb_main;

	public ModelSewerCroc() {
		textureWidth = 48;
		textureHeight = 48;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 26, -2.0F, -4.0F, -8.0F, 5, 3, 19, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -1.0F, -5.0F, -5.0F, 1, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 2.0F, -5.0F, -5.0F, -1, 1, 1, 0.0F, false));

		bottomjaw = new ModelRenderer(this);
		bottomjaw.setRotationPoint(0.0F, 24.0F, 0.0F);
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 48, 8, -1.0F, -2.0F, -8.0F, 3, 1, -8, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, -1.0F, -3.0F, -12.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, 1.0F, -3.0F, -10.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, 1.0F, -3.0F, -12.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, -1.0F, -3.0F, -14.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, 1.0F, -3.0F, -16.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, 1.0F, -3.0F, -14.0F, 1, 1, 1, 0.0F, false));
		bottomjaw.cubeList.add(new ModelBox(bottomjaw, 0, 0, -1.0F, -3.0F, -16.0F, 1, 1, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 23, 3.0F, -2.0F, 5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 23, -3.0F, -2.0F, 5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 23, -3.0F, -2.0F, -5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 23, 3.0F, -2.0F, -5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 2, 14, -1.0F, -4.0F, -8.0F, 3, 1, -8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 22, 15, -1.0F, -3.0F, 11.0F, 3, 2, 10, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
		bottomjaw.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		// TODO Auto-generated method stub
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}
}