package mod.cbultimate.stranded.block;

import mod.cbultimate.stranded.StrandedMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by CBU on 14/1/2017.
 */

public class BlockReinforcedIron extends Block {

    public BlockReinforcedIron(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(StrandedMod.invCreativeTab);
        this.setResistance(10000F);
        this.setSoundType(SoundType.METAL);
    }
}
