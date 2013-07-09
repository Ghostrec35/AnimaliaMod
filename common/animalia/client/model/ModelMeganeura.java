package animalia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMeganeura extends ModelBase
{
  //fields
    ModelRenderer EyeR;
    ModelRenderer TelsonL;
    ModelRenderer Tail;
    ModelRenderer Forehead;
    ModelRenderer AntennaR;
    ModelRenderer Wingattach;
    ModelRenderer AntennaL;
    ModelRenderer Head;
    ModelRenderer EyeL;
    ModelRenderer Body;
    ModelRenderer WingR3;
    ModelRenderer WingR1;
    ModelRenderer WingL1;
    ModelRenderer WingL3;
    ModelRenderer Mouth;
    ModelRenderer TelsonR;
    ModelRenderer LegR3;
    ModelRenderer LegR2;
    ModelRenderer LegR1;
    ModelRenderer LegL1;
    ModelRenderer LegL2;
    ModelRenderer LegL3;
  
  public ModelMeganeura()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      EyeR = new ModelRenderer(this, 18, 11);
      EyeR.addBox(-1F, 0F, -2F, 1, 2, 1);
      EyeR.setRotationPoint(0F, 18F, 0F);
      EyeR.setTextureSize(128, 64);
      EyeR.mirror = true;
      setRotation(EyeR, 0.2617994F, 0F, 0.3490659F);
      TelsonL = new ModelRenderer(this, 103, 22);
      TelsonL.addBox(0.5F, -0.8F, 7F, 1, 1, 1);
      TelsonL.setRotationPoint(0F, 19.5F, 3.4F);
      TelsonL.setTextureSize(128, 64);
      TelsonL.mirror = true;
      setRotation(TelsonL, 0.0349066F, 0F, 0F);
      Tail = new ModelRenderer(this, 112, 24);
      Tail.addBox(-0.5F, -0.8F, 0F, 1, 1, 7);
      Tail.setRotationPoint(0F, 19.5F, 3.4F);
      Tail.setTextureSize(128, 64);
      Tail.mirror = true;
      setRotation(Tail, 0.0349066F, 0F, 0F);
      Forehead = new ModelRenderer(this, 40, 6);
      Forehead.addBox(-1F, 0F, -1F, 2, 1, 1);
      Forehead.setRotationPoint(0F, 18.5F, -1.5F);
      Forehead.setTextureSize(128, 64);
      Forehead.mirror = true;
      setRotation(Forehead, 0.0872665F, 0F, 0F);
      AntennaR = new ModelRenderer(this, 32, 8);
      AntennaR.addBox(0F, -1F, -1F, 1, 1, 1);
      AntennaR.setRotationPoint(-1F, 18.6F, -2F);
      AntennaR.setTextureSize(128, 64);
      AntennaR.mirror = true;
      setRotation(AntennaR, -0.1745329F, 0.2617994F, 0F);
      Wingattach = new ModelRenderer(this, 92, 21);
      Wingattach.addBox(-0.5F, -1.2F, 0.3F, 1, 1, 3);
      Wingattach.setRotationPoint(0F, 19F, -0.6F);
      Wingattach.setTextureSize(128, 64);
      Wingattach.mirror = true;
      setRotation(Wingattach, 0F, 0F, 0F);
      AntennaL = new ModelRenderer(this, 27, 8);
      AntennaL.addBox(0F, -1F, -1F, 1, 1, 1);
      AntennaL.setRotationPoint(1F, 18.6F, -2F);
      AntennaL.setTextureSize(128, 64);
      AntennaL.mirror = true;
      setRotation(AntennaL, -0.1745329F, -0.2617994F, 0F);
      Head = new ModelRenderer(this, 77, 4);
      Head.addBox(-1F, 0F, -2F, 2, 2, 1);
      Head.setRotationPoint(0F, 18F, 0F);
      Head.setTextureSize(128, 64);
      Head.mirror = true;
      setRotation(Head, 0.2617994F, 0F, 0F);
      EyeL = new ModelRenderer(this, 23, 11);
      EyeL.addBox(0F, 0F, -2F, 1, 2, 1);
      EyeL.setRotationPoint(0F, 18F, 0F);
      EyeL.setTextureSize(128, 64);
      EyeL.mirror = true;
      setRotation(EyeL, 0.2617994F, 0F, -0.3490659F);
      Body = new ModelRenderer(this, 92, 26);
      Body.addBox(-1F, -0.7F, 0F, 2, 2, 4);
      Body.setRotationPoint(0F, 19F, -0.6F);
      Body.setTextureSize(128, 64);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      WingR3 = new ModelRenderer(this, 90, 10);
      WingR3.addBox(-9F, 0F, 0F, 9, 1, 6);
      WingR3.setRotationPoint(-0.5F, 18F, 1F);
      WingR3.setTextureSize(128, 64);
      WingR3.mirror = true;
      setRotation(WingR3, 0.0174533F, 0.0698132F, 0.0872665F);
      WingR1 = new ModelRenderer(this, 0, 0);
      WingR1.addBox(-10F, 0F, 0F, 10, 1, 4);
      WingR1.setRotationPoint(-0.5F, 18F, 0F);
      WingR1.setTextureSize(128, 64);
      WingR1.mirror = true;
      setRotation(WingR1, 0.0523599F, -0.296706F, 0.1396263F);
      WingL1 = new ModelRenderer(this, 29, 0);
      WingL1.addBox(0F, 0F, 0F, 10, 1, 4);
      WingL1.setRotationPoint(0.5F, 18F, 0F);
      WingL1.setTextureSize(128, 64);
      WingL1.mirror = true;
      setRotation(WingL1, 0.0523599F, 0.296706F, -0.1396263F);
      WingL3 = new ModelRenderer(this, 90, 2);
      WingL3.addBox(0F, 0F, 0F, 9, 1, 6);
      WingL3.setRotationPoint(0.5F, 18F, 1F);
      WingL3.setTextureSize(128, 64);
      WingL3.mirror = true;
      setRotation(WingL3, 0.0174533F, -0.0698132F, -0.0872665F);
      Mouth = new ModelRenderer(this, 42, 8);
      Mouth.addBox(-0.5F, 1F, -1F, 1, 1, 1);
      Mouth.setRotationPoint(0F, 18.5F, -1.5F);
      Mouth.setTextureSize(128, 64);
      Mouth.mirror = true;
      setRotation(Mouth, 0.0872665F, 0F, 0F);
      TelsonR = new ModelRenderer(this, 108, 22);
      TelsonR.addBox(-0.5F, -0.8F, 7F, 1, 1, 1);
      TelsonR.setRotationPoint(0F, 19.5F, 3.4F);
      TelsonR.setTextureSize(128, 64);
      TelsonR.mirror = true;
      setRotation(TelsonR, 0.0349066F, 0F, 0F);
      LegR3 = new ModelRenderer(this, 49, 21);
      LegR3.addBox(0F, 0F, -4F, 1, 1, 4);
      LegR3.setRotationPoint(-0.5F, 20.3F, 1.3F);
      LegR3.setTextureSize(128, 64);
      LegR3.mirror = true;
      setRotation(LegR3, 0.6981317F, 0.0872665F, 0.1745329F);
      LegR2 = new ModelRenderer(this, 39, 21);
      LegR2.addBox(0F, 0F, -3F, 1, 1, 3);
      LegR2.setRotationPoint(-0.5F, 20.3F, 0.5F);
      LegR2.setTextureSize(128, 64);
      LegR2.mirror = true;
      setRotation(LegR2, 0.5235988F, 0.1745329F, 0.0872665F);
      LegR1 = new ModelRenderer(this, 35, 17);
      LegR1.addBox(0F, 0F, -2F, 1, 1, 2);
      LegR1.setRotationPoint(-0.5F, 20.3F, 0F);
      LegR1.setTextureSize(128, 64);
      LegR1.mirror = true;
      setRotation(LegR1, 0.2617994F, 0.0174533F, 0F);
      LegR1 = new ModelRenderer(this, 28, 17);
      LegR1.addBox(0F, 0F, -2F, 1, 1, 2);
      LegR1.setRotationPoint(0.5F, 20.3F, 0F);
      LegR1.setTextureSize(128, 64);
      LegR1.mirror = true;
      setRotation(LegR1, 0.2617994F, -0.0174533F, 0F);
      LegL2 = new ModelRenderer(this, 30, 21);
      LegL2.addBox(0F, 0F, -3F, 1, 1, 3);
      LegL2.setRotationPoint(0.5F, 20.3F, 0.5F);
      LegL2.setTextureSize(128, 64);
      LegL2.mirror = true;
      setRotation(LegL2, 0.5235988F, -0.1745329F, -0.0872665F);
      LegL3 = new ModelRenderer(this, 60, 21);
      LegL3.addBox(0F, 0F, -4F, 1, 1, 4);
      LegL3.setRotationPoint(0.5F, 20.3F, 1.3F);
      LegL3.setTextureSize(128, 64);
      LegL3.mirror = true;
      setRotation(LegL3, 0.715585F, -0.0872665F, -0.1745329F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    EyeR.render(f5);
    TelsonL.render(f5);
    Tail.render(f5);
    Forehead.render(f5);
    AntennaR.render(f5);
    Wingattach.render(f5);
    AntennaL.render(f5);
    Head.render(f5);
    EyeL.render(f5);
    Body.render(f5);
    WingR3.render(f5);
    WingR1.render(f5);
    WingL1.render(f5);
    WingL3.render(f5);
    Mouth.render(f5);
    TelsonR.render(f5);
    LegR3.render(f5);
    LegR2.render(f5);
    LegR1.render(f5);
    LegR1.render(f5);
    LegL2.render(f5);
    LegL3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
