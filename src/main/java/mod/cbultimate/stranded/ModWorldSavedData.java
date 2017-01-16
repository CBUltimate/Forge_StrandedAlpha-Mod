package mod.cbultimate.stranded;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;
import java.util.ArrayList;

//Created by CBU on 17/1/2017.

public class ModWorldSavedData extends WorldSavedData {
    public ArrayList<Vector3> ToolCupboards = new ArrayList<Vector3>();

    public ModWorldSavedData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList cupboardList = nbt.getTagList("ToolCupboards", 10);
        ToolCupboards.clear();
        for (int i=0; i< cupboardList.tagCount(); i++){
            NBTTagCompound newCompound = cupboardList.getCompoundTagAt(i);
            int X = newCompound.getInteger("x");
            int Y = newCompound.getInteger("y");
            int Z = newCompound.getInteger("z");
            ToolCupboards.add(new Vector3(X, Y, Z));
        }
        System.out.println("Loaded " + ToolCupboards.size() + " cupboards.");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList cupboardList = new NBTTagList();
        for (int i=0;i<ToolCupboards.size(); i++){
            NBTTagCompound newCompound = new NBTTagCompound();
            Vector3 position = ToolCupboards.get(i);
            newCompound.setInteger("x", position.x);
            newCompound.setInteger("y", position.y);
            newCompound.setInteger("z", position.z);
            cupboardList.appendTag(newCompound);
        }
        compound.setTag("ToolCupboards", cupboardList);
        return compound;
    }
}
