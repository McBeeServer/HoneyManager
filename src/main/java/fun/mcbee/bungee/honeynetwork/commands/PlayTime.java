package fun.mcbee.bungee.honeynetwork.commands;

import fun.mcbee.bungee.honeynetwork.HoneyNetwork;
import fun.mcbee.bungee.honeynetwork.data.PData;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PlayTime extends Command {
    public PlayTime() {
        super("playtime");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                PData pd = HoneyNetwork.listPlayerData.get(p.getUniqueId());
                if(pd == null) {
                    System.out.println("Data is null");
                }

                if (pd != null) {
                    String timeTotal = PData.GetTextTime(Long.valueOf(pd.GetSecondsTotalOnline()));
                    String timeWeek = PData.GetTextTime(Long.valueOf(pd.GetSecondsWeekOnline()));
                    System.out.println(timeTotal + " " + timeWeek);

                    p.sendMessage((ChatColor.of("#FFBF00") + "Playtime: "));
                    p.sendMessage((ChatColor.of("#F28C28") + "Total: " + ChatColor.of("#738291") + "" + ChatColor.of("#FAD5A5") + timeTotal));
                    p.sendMessage((ChatColor.of("#F28C28") + "Last Week: " + ChatColor.of("#738291") + "" + ChatColor.of("#FAD5A5") + timeWeek));
                }
            } else if (args.length == 1 && p.hasPermission("bee.bungee.admin")) {
                ProxiedPlayer player = BungeeCord.getInstance().getPlayer(args[0]);
                if (player != null) {
                    PData pd = HoneyNetwork.listPlayerData.get(player.getUniqueId());
                    if (pd != null) {
                        System.out.println(pd.GetSecondsTotalOnline() + " " + pd.GetSecondsWeekOnline());
                        String timeTotal = PData.GetTextTime(Long.valueOf(pd.GetSecondsTotalOnline()));
                        String timeWeek = PData.GetTextTime(Long.valueOf(pd.GetSecondsWeekOnline()));
                        p.sendMessage((ChatColor.of("#FFBF00") + "Playtime: "));
                        p.sendMessage((ChatColor.of("#F28C28") + "Total: " + ChatColor.of("#738291") + "" + ChatColor.of("#FAD5A5") + timeTotal));
                        p.sendMessage((ChatColor.of("#F28C28") + "Last Week: " + ChatColor.of("#738291") + "" + ChatColor.of("#FAD5A5") + timeWeek));
                    }
                } else {
                    p.sendMessage((ChatColor.of("#FFBF00") + "Can't check players that are offline!"));
                }
            }
        }
    }
}

