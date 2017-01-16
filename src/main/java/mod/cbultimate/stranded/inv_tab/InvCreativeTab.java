package mod.cbultimate.stranded.inv_tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by CBU on 13/1/2017.
 */
public class InvCreativeTab extends CreativeTabs {
    public InvCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.TNT_MINECART);
    }
}
