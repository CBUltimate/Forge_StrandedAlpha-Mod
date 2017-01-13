package com.cbultimate.stranded.block;

import com.cbultimate.stranded.StrandedMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by CBU on 14/1/2017.
 */

public class Block_ReinforcedIron extends Block {

    public Block_ReinforcedIron(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(StrandedMod.invCreativeTab);
        this.setHardness(200F);
        this.setResistance(1000F);
        this.setSoundType(SoundType.METAL);
    }


}
