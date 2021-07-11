package henryandalex.tinkersaddonmod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelSewerCroc extends ModelBase {
	
	private final ModelRenderer croc;
	private final ModelRenderer body;
	private final ModelRenderer bottomleftleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer bottomrightleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer lowerjaw;
	private final ModelRenderer upperjaw;
	private final ModelRenderer tailtop;
	private final ModelRenderer tailbottom;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;

	public ModelSewerCroc() {
		textureWidth = 64;
		textureHeight = 64;
		
		croc = new ModelRenderer(this);
		croc.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(croc, 3.1416F, 3.1416F, -3.1416F);
		
		tailtop = new ModelRenderer(this);
		tailtop.setRotationPoint(0.0F, 0.0F, 0.0F);
		croc.addChild(tailtop);
		
		tailbottom = new ModelRenderer(this);
		tailbottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		tailtop.addChild(tailbottom);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		croc.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -2.0F, -7.0F, -14.0F, 3, 3, 30, 1.0F, false));
		body.cubeList.add(new ModelBox(body, 60, 62, 0.0F, -9.0F, 14.0F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 60, 62, -2.0F, -9.0F, 14.0F, 1, 1, 1, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, 0.0F, 18.0F);
		croc.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 52, -6.0F, -5.0F, -8.0F, 3, 5, 2, 0.0F, false));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 62, -6.0F, -1.0F, -6.0F, 3, 1, 1, 0.0F, false));

		bottomleftleg = new ModelRenderer(this);
		bottomleftleg.setRotationPoint(0.0F, 0.0F, 0.0F);
		croc.addChild(bottomleftleg);
		bottomleftleg.cubeList.add(new ModelBox(bottomleftleg, 0, 52, -6.0F, -5.0F, -8.0F, 3, 5, 2, 0.0F, false));
		bottomleftleg.cubeList.add(new ModelBox(bottomleftleg, 0, 62, -6.0F, -1.0F, -6.0F, 3, 1, 1, 0.0F, false));

		lowerjaw = new ModelRenderer(this);
		lowerjaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		croc.addChild(lowerjaw);
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 34, -3.0F, -4.0F, 17.0F, 5, 1, 13, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 48, 45, -2.0F, -4.0F, 30.0F, 3, 1, 5, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 55, 51, -1.0F, -4.0F, 35.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 17.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 19.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 21.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 23.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 25.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 27.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 1.0F, -5.0F, 29.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 0.0F, -5.0F, 30.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, 0.0F, -5.0F, 32.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -1.0F, -5.0F, 35.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -2.0F, -5.0F, 32.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -2.0F, -5.0F, 30.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 29.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 27.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 25.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 23.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 21.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 19.0F, 1, 1, 1, 0.0F, false));
		lowerjaw.cubeList.add(new ModelBox(lowerjaw, 0, 0, -3.0F, -5.0F, 17.0F, 1, 1, 1, 0.0F, false));

		upperjaw = new ModelRenderer(this);
		upperjaw.setRotationPoint(0.0F, -5.0F, 17.0F);
		croc.addChild(upperjaw);
		upperjaw.cubeList.add(new ModelBox(upperjaw, 10, 49, -3.0F, -2.0F, 0.0F, 5, 2, 13, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 50, 55, -2.0F, -2.0F, 13.0F, 3, 2, 4, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 58, 34, -1.0F, -1.0F, 17.0F, 1, 1, 2, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 1.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 3.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 5.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 7.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 9.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 1.0F, 0.0F, 11.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 0.0F, 0.0F, 14.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 0.0F, 0.0F, 16.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, 0.0F, 0.0F, 17.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -2.0F, 0.0F, 17.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -2.0F, 0.0F, 16.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -2.0F, 0.0F, 14.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 11.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 9.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 7.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 5.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 3.0F, 1, 1, 1, 0.0F, false));
		upperjaw.cubeList.add(new ModelBox(upperjaw, 0, 0, -3.0F, 0.0F, 1.0F, 1, 1, 1, 0.0F, false));

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tailtop.addChild(tail1);
		tail1.cubeList.add(new ModelBox(tail1, 24, 31, -2.0F, -8.0F, -26.0F, 3, 5, 11, 0.0F, false));
		tail1.cubeList.add(new ModelBox(tail1, 23, 42, -3.0F, -7.0F, -16.0F, 1, 4, 1, 0.0F, false));
		tail1.cubeList.add(new ModelBox(tail1, 23, 33, 1.0F, -7.0F, -16.0F, 1, 4, 1, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, -28.0F);
		tailbottom.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 18, 33, -2.0F, -6.0F, -7.0F, 3, 3, 9, 0.0F, false));
		tail2.cubeList.add(new ModelBox(tail2, 33, 35, -2.0F, -5.0F, -14.0F, 3, 2, 7, 0.0F, false));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tailbottom.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 24, 33, -1.0F, -5.0F, -51.0F, 1, 2, 9, 0.0F, false));
		tail3.cubeList.add(new ModelBox(tail3, 31, 0, -1.0F, -4.0F, -52.0F, 1, 1, 1, 0.0F, false));

		bottomrightleg = new ModelRenderer(this);
		bottomrightleg.setRotationPoint(8.0F, 0.0F, 0.0F);
		croc.addChild(bottomrightleg);
		bottomrightleg.cubeList.add(new ModelBox(bottomrightleg, 0, 52, -6.0F, -5.0F, -8.0F, 3, 5, 2, 0.0F, false));
		bottomrightleg.cubeList.add(new ModelBox(bottomrightleg, 0, 62, -6.0F, -1.0F, -6.0F, 3, 1, 1, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(8.0F, 0.0F, 18.0F);
		croc.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 52, -6.0F, -5.0F, -8.0F, 3, 5, 2, 0.0F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 62, -6.0F, -1.0F, -6.0F, 3, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		/*body.render(f5);
		bottomleftleg.render(f5);
		leftleg.render(f5);
		bottomrightleg.render(f5);
		rightleg.render(f5);
		lowerjaw.render(f5);
		upperjaw.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);*/
		croc.render(f5);
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
		this.croc.rotateAngleY = (float) (2*Math.PI);
		
		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F + (float)Math.PI) * 1.0F * limbSwingAmount;
        this.bottomleftleg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F + (float)Math.PI) * 1.0F * limbSwingAmount;
        this.bottomrightleg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.tailtop.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 0.8F * limbSwingAmount;
        this.tailbottom.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 0.3F * limbSwingAmount;
        this.tail3.rotateAngleY = MathHelper.cos(limbSwing * 1.0F) * 0.2F * limbSwingAmount;
        this.upperjaw.rotateAngleX = Math.abs(MathHelper.cos(limbSwing * 0.5F) * 2.0F * limbSwingAmount);
	}
}