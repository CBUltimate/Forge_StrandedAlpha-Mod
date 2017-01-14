package com.cbultimate.stranded.block;

import com.cbultimate.stranded.StrandedMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by CBU on 14/1/2017.
 */

public class BlockReinforcedDirt extends Block {

    public BlockReinforcedDirt(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(StrandedMod.invCreativeTab);
        this.setResistance(200F);
        this.setSoundType(SoundType.STONE);
    }
}
