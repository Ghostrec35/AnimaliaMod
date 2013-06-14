package animalia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelExtractor extends ModelBase
{
    ModelRenderer base;
    ModelRenderer glass_case;
    ModelRenderer arm_base;
    ModelRenderer arm_1;
    ModelRenderer arm_2;
    ModelRenderer arm_joint;
    ModelRenderer syringe_;
    ModelRenderer plate;
    ModelRenderer plate_legs_R;
    ModelRenderer plate_legs_L;
    ModelRenderer base_rim_1_R;
    ModelRenderer base_rim_1_L;
    ModelRenderer base_rim_2_L;
    ModelRenderer base_rim_2_R;
    ModelRenderer computer_screen;
    ModelRenderer computer_stand;
  
    public ModelExtractor()
  	{
	    textureWidth = 128;
	    textureHeight = 64;
   
	    base = new ModelRenderer(this, 0, 40);
	    base.addBox(-8F, 5F, -8F, 16, 8, 16);
	    base.setRotationPoint(0F, 11F, 0F);
	    base.setTextureSize(64, 32);
	    base.mirror = true;
	    setRotation(base, 0F, 0F, 0F);
	    glass_case = new ModelRenderer(this, 64, 37);
	    glass_case.addBox(-8F, -22F, -8F, 16, 11, 16);
	    glass_case.setRotationPoint(0F, 27F, 0F);
	    glass_case.setTextureSize(64, 32);
	    glass_case.mirror = true;
	    setRotation(glass_case, 0F, 0F, 0F);
	    arm_base = new ModelRenderer(this, 0, 33);
	    arm_base.addBox(-2F, -1F, -0.7666667F, 4, 2, 4);
	    arm_base.setRotationPoint(0F, 16F, 6F);
	    arm_base.setTextureSize(64, 32);
	    arm_base.mirror = true;
	    setRotation(arm_base, 0F, 0F, 0F);
	    arm_1 = new ModelRenderer(this, 25, 0);
	    arm_1.addBox(-1F, -12F, 0F, 2, 20, 3);
	    arm_1.setRotationPoint(0F, 16F, 6F);
	    arm_1.setTextureSize(64, 32);
	    arm_1.mirror = true;
	    setRotation(arm_1, 0F, 0F, 0F);
	    arm_2 = new ModelRenderer(this, 36, 0);
	    arm_2.addBox(-1F, 0F, -1F, 2, 7, 2);
	    arm_2.setRotationPoint(0F, 4F, 7F);
	    arm_2.setTextureSize(64, 32);
	    arm_2.mirror = true;
	    setRotation(arm_2, -0.9948377F, 0F, 0F);
	    arm_joint = new ModelRenderer(this, 0, 0);
	    arm_joint.addBox(-1.5F, -0.8F, -1.6F, 3, 4, 4);
	    arm_joint.setRotationPoint(0F, 4F, 7F);
	    arm_joint.setTextureSize(64, 32);
	    arm_joint.mirror = true;
	    setRotation(arm_joint, -0.9948377F, 0F, 0F);
	    syringe_ = new ModelRenderer(this, 0, 13);
	    syringe_.addBox(-0.5F, 0F, -1F, 1, 3, 1);
	    syringe_.setRotationPoint(0F, 7.533333F, 2F);
      	syringe_.setTextureSize(64, 32);
      	syringe_.mirror = true;
      	setRotation(syringe_, -0.4014257F, 0F, 0F);
      	plate = new ModelRenderer(this, 0, 25);
      	plate.addBox(-3F, -1.4F, -3F, 6, 1, 6);
      	plate.setRotationPoint(0F, 16F, 0F);
      	plate.setTextureSize(64, 32);
      	plate.mirror = true;
      	setRotation(plate, 0F, 0F, 0F);
      	plate_legs_R = new ModelRenderer(this, 0, 18);
      	plate_legs_R.addBox(-1F, -0.3333333F, -0.4F, 1, 2, 1);
      	plate_legs_R.setRotationPoint(0F, 16F, 0F);
      	plate_legs_R.setTextureSize(64, 32);
      	plate_legs_R.mirror = true;
      	setRotation(plate_legs_R, 0F, 0F, 0.6457718F);
      	plate_legs_L = new ModelRenderer(this, 0, 18);
      	plate_legs_L.addBox(0F, -0.3333333F, -0.4F, 1, 2, 1);
      	plate_legs_L.setRotationPoint(0F, 16F, 0F);
      	plate_legs_L.setTextureSize(64, 32);
      	plate_legs_L.mirror = true;
      	setRotation(plate_legs_L, 0F, 0F, -0.6457718F);
      	base_rim_1_R = new ModelRenderer(this, 45, 0);
      	base_rim_1_R.addBox(-9F, 4F, -9F, 3, 9, 3);
      	base_rim_1_R.setRotationPoint(0F, 11F, 0F);
      	base_rim_1_R.setTextureSize(64, 32);
      	base_rim_1_R.mirror = true;
      	setRotation(base_rim_1_R, 0F, 0F, 0F);
      	base_rim_1_L = new ModelRenderer(this, 45, 0);
      	base_rim_1_L.addBox(6F, 4F, -9F, 3, 9, 3);
      	base_rim_1_L.setRotationPoint(0F, 11F, 0F);
      	base_rim_1_L.setTextureSize(64, 32);
      	base_rim_1_L.mirror = true;
      	setRotation(base_rim_1_L, 0F, 0F, 0F);
      	base_rim_2_L = new ModelRenderer(this, 45, 0);
      	base_rim_2_L.addBox(6F, 4F, 6F, 3, 9, 3);
      	base_rim_2_L.setRotationPoint(0F, 11F, 0F);
      	base_rim_2_L.setTextureSize(64, 32);
      	base_rim_2_L.mirror = true;
      	setRotation(base_rim_2_L, 0F, 0F, 0F);
      	base_rim_2_R = new ModelRenderer(this, 45, 0);
      	base_rim_2_R.addBox(-9F, 4F, 6F, 3, 9, 3);
      	base_rim_2_R.setRotationPoint(0F, 11F, 0F);
      	base_rim_2_R.setTextureSize(64, 32);
      	base_rim_2_R.mirror = true;
      	setRotation(base_rim_2_R, 0F, 0F, 0F);
      	computer_screen = new ModelRenderer(this, 59, 0);
      	computer_screen.addBox(-4F, -2F, 0F, 8, 4, 1);
      	computer_screen.setRotationPoint(0F, 20F, -9F);
      	computer_screen.setTextureSize(64, 32);
      	computer_screen.mirror = true;
      	setRotation(computer_screen, -0.5410521F, 0F, 0F);
      	computer_stand = new ModelRenderer(this, 58, 6);
      	computer_stand.addBox(-3.5F, -2F, 1F, 7, 3, 3);
      	computer_stand.setRotationPoint(0F, 20F, -9F);
      	computer_stand.setTextureSize(64, 32);
      	computer_stand.mirror = true;
      	setRotation(computer_stand, -1.082104F, 0F, 0F);
  	}
  
  	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  	{
	  super.render(entity, f, f1, f2, f3, f4, f5);
    	setRotationAngles(f, f1, f2, f3, f4, f5);
    	base.render(f5);
    	glass_case.render(f5);
    	arm_base.render(f5);
    	arm_1.render(f5);
    	arm_2.render(f5);
    	arm_joint.render(f5);
    	syringe_.render(f5);
    	plate.render(f5);
    	plate_legs_R.render(f5);
    	plate_legs_L.render(f5);
    	base_rim_1_R.render(f5);
    	base_rim_1_L.render(f5);
    	base_rim_2_L.render(f5);
    	base_rim_2_R.render(f5);
    	computer_screen.render(f5);
    	computer_stand.render(f5);
  	}
  
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
  
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

	public void renderTileEntity() 
	{
		base.render(0.0625F);
		glass_case.render(0.0625F);
		arm_base.render(0.0625F);
		arm_1.render(0.0625F);
		arm_2.render(0.0625F);
		arm_joint.render(0.0625F);
		syringe_.render(0.0625F);
		plate.render(0.0625F);
		plate_legs_R.render(0.0625F);
		plate_legs_L.render(0.0625F);
		base_rim_1_R.render(0.0625F);
	    base_rim_1_L.render(0.0625F);
	    base_rim_2_L.render(0.0625F);
	    base_rim_2_R.render(0.0625F);
	    computer_screen.render(0.0625F);
	    computer_stand.render(0.0625F);
	}
}