package com.Squallz.Orbs;

import com.sun.javafx.binding.SelectBinding;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CORBA.INVALID_ACTIVITY;

public class Commands implements CommandExecutor {

    MainClass main;
    public Commands(MainClass main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
        if(cmd.getName().equalsIgnoreCase("orbs")){
            if(!(sender instanceof  Player)){
                if(args.length == 0){
                    sender.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args.length == 1){
                    if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("help")){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("reload")){
                        main.saveConfig();
                        main.reloadConfig();
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADED);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("set")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.SETFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("give")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.GIVEFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("remove")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.REMOVEFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("send")){
                        sender.sendMessage(main.msgs.PREFIX + " " + ChatColor.RED + "This command is Player-Only!");
                        return true;
                    }
                }
                if(args.length == 2){
                    if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("help")){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("reload")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("set")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.SETFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("give")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.GIVEFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("remove")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.REMOVEFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("send")){
                        sender.sendMessage(main.msgs.PREFIX + " " + ChatColor.RED + "This command is Player-Only!");
                        return true;
                    }
                }
                if(args.length == 3){
                    if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("help")){
                        sender.sendMessage(main.msgs.ORBSHELP);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("reload")){
                        sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADFORMAT);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("set")){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                        if(targetPlayer == null){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID.replace("{TARGET}", targetPlayer.getName()));
                            return true;
                        }
                        try{
                            int amountToSet = Integer.parseInt(args[2]);
                            main.orbs.setOrbs(targetPlayer, amountToSet);
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSET.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                            targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSETTARGET.replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2])).replace("{PLAYER}", "Console")));
                        }catch(NumberFormatException e){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("give")){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                        if(targetPlayer == null){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID);
                            return true;
                        }
                        try{
                            int amountToGive = Integer.parseInt(args[2]);
                            main.orbs.addOrbs(targetPlayer, amountToGive);
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSET.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                            targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSETTARGET.replace("{PLAYER}", "Console").replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                        }catch(NumberFormatException e){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("remove")){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                        if(targetPlayer == null){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID);
                            return true;
                        }
                        try{
                            int amountToRemove = Integer.parseInt(args[2]);
                            if(main.orbs.getOrbs(targetPlayer) < amountToRemove){
                                sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOTENOUGHORBSTARGET);
                                return true;
                            }else{
                                main.orbs.removeOrbs(targetPlayer, amountToRemove);
                                sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSREMOVED.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                                targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSREMOVEDTARGET.replace("{PLAYER}", "Console").replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                            }
                        }catch(NumberFormatException e){
                            sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("send")){
                        sender.sendMessage(main.msgs.PREFIX + " " + ChatColor.RED + "This command is Player-Only!");
                        return true;
                    }
                }
                if(args.length > 3){
                    sender.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSHELP);
                    return true;
                }
            }
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSDISPLAY.replace("{ORBAMOUNT}", main.orbs.commaFormat(main.orbs.getOrbs(player))));
                return true;
            }
            if(args.length == 1){
                if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("reload")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    main.reloadConfig();
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADED);
                    return true;
                }
                if(args[0].equalsIgnoreCase("set")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.SETFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("give")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.GIVEFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("remove")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.REMOVEFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("send")){
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.SENDFORMAT);
                    return true;
                }
            }
            if(args.length == 2){
                if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("reload")){
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("set")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.SETFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("give")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.GIVEFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("remove")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.REMOVEFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("send")){
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.SENDFORMAT);
                    return true;
                }
            }
            if(args.length == 3){
                if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("send"))){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage(main.msgs.ORBSHELP);
                    return true;
                }
                if(args[0].equalsIgnoreCase("reload")){
                    player.sendMessage(main.msgs.PREFIX + " " + main.msgs.RELOADFORMAT);
                    return true;
                }
                if(args[0].equalsIgnoreCase("set")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if(targetPlayer == null){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID.replace("{TARGET}", targetPlayer.getName()));
                        return true;
                    }
                    try{
                        int amountToSet = Integer.parseInt(args[2]);
                        main.orbs.setOrbs(targetPlayer, amountToSet);
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSET.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                        targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSETTARGET.replace("{PLAYER}", player.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                    }catch(NumberFormatException e){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                    }
                }
                if(args[0].equalsIgnoreCase("give")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if(targetPlayer == null){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID.replace("{TARGET}", targetPlayer.getName()));
                        return true;
                    }
                    try{
                        int amountToGive = Integer.parseInt(args[2]);
                        main.orbs.addOrbs(targetPlayer, amountToGive);
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSGIVEN.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                        targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSGIVENTARGET.replace("{PLAYER}", player.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                    }catch(NumberFormatException e){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("remove")){
                    if(!(player.hasPermission("orbs.*") || player.isOp())){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOPERMISSION);
                        return true;
                    }
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if(targetPlayer == null){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID.replace("{TARGET}", targetPlayer.getName()));
                        return true;
                    }
                    try{
                        int amountToRemove = Integer.parseInt(args[2]);
                        if(main.orbs.getOrbs(targetPlayer) < amountToRemove){
                            player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOTENOUGHORBSTARGET.replace("{TARGET}", targetPlayer.getName()));
                            return true;
                        }else{
                            main.orbs.removeOrbs(targetPlayer, amountToRemove);
                            player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSREMOVED.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                            targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSREMOVEDTARGET.replace("{PLAYER}", player.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                        }
                    }catch(NumberFormatException e){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("send")){
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if(targetPlayer == null){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.TARGETINVALID.replace("{TARGET}", targetPlayer.getName()));
                        return true;
                    }
                    try{
                        int amountToSend = Integer.parseInt(args[2]);
                        if(main.orbs.getOrbs(player) < amountToSend){
                            player.sendMessage(main.msgs.PREFIX + " " + main.msgs.NOTENOUGHORBS);
                            return true;
                        }else{
                            main.orbs.removeOrbs(player, amountToSend);
                            main.orbs.addOrbs(targetPlayer, amountToSend);
                            player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSENT.replace("{TARGET}", targetPlayer.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                            targetPlayer.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSSENTTARGET.replace("{PLAYER}", player.getName()).replace("{ORBAMOUNT}", main.orbs.commaFormat(Double.parseDouble(args[2]))));
                        }
                    }catch(NumberFormatException e){
                        player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSNOTNUMBERS);
                    }
                }
            }
            if(args.length > 3){
                player.sendMessage(main.msgs.PREFIX + " " + main.msgs.ORBSHELP);
                return true;
            }
        }
        return false;
    }
}
