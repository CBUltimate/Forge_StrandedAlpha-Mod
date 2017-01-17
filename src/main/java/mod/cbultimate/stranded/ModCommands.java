package mod.cbultimate.stranded;//Created by CBU on 17/1/2017.

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModCommands implements ICommand {
    private final List aliases;

    public ModCommands(){
        aliases = new ArrayList();
        aliases.add("clearcupboards");
    }

    @Override
    public String getCommandName() {
        return "clearcupboards";
    }

    private final String cupboard_dataIdentifier = "ToolCupboards";

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote){
            System.out.println("Clearing tool cupboards...");
            ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

            if (modWorldSavedData == null) {
                modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
            }
            modWorldSavedData.ToolCupboards.clear();
            modWorldSavedData.markDirty();
        }
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return false;
    }
}
