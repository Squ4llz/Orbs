package com.Squallz.Orbs;

import org.bukkit.ChatColor;

public class Messages {

    MainClass main;
    Messages(MainClass main){
        this.main = main;

        PREFIX = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".prefix"));

        NOPERMISSION = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".errors" + ".nopermission"));
        NOTENOUGHORBS = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".errors" + ".not-enough-orbs"));
        NOTENOUGHORBSTARGET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".errors" + ".not-enough-orbs-target"));
        TARGETINVALID = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".errors" + ".target-invalid"));
        ORBSNOTNUMBERS = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".errors" + ".orbs-must-be-numbers"));    ORBSDISPLAY = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-display"));
        ORBSSET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-set"));
        ORBSSETTARGET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-set-target"));
        ORBSGIVEN = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-given"));
        ORBSGIVENTARGET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-given-target"));
        ORBSSENT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-sent"));
        ORBSSENTTARGET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-sent-target"));
        ORBSREMOVED = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-removed"));
        ORBSREMOVEDTARGET = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".orbs-removed-target"));
        ORBSHELP = ChatColor.GRAY + "--------- " + ChatColor.AQUA + "" + ChatColor.BOLD + "Orbs" + ChatColor.GRAY + " ---------" + "\n" + ChatColor.AQUA + "/orbs" + ChatColor.GRAY + " - View your Orbs" + "\n" + ChatColor.AQUA + "/orbs set <player> <amount>" + ChatColor.GRAY + " - Set a player's Orbs" + "\n" + ChatColor.AQUA + "/orbs give <player> <amount>" + ChatColor.GRAY + " - Give a player Orbs" + "\n" + ChatColor.AQUA + "/orbs remove <player> <amoount>" + ChatColor.GRAY + " - Remove a player's orbs" + "\n" + ChatColor.AQUA + "/orbs reload" + ChatColor.GRAY + " - Reload Orbs Config" + "\n" + ChatColor.AQUA + "/orbs send <player> <amount>" + ChatColor.GRAY + " - Send Orbs to a player";
        RELOADED = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".general" + ".reloaded"));

        RELOADFORMAT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".formats" + ".reload"));
        SETFORMAT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".formats" + ".set"));
        GIVEFORMAT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".formats" + ".give"));
        REMOVEFORMAT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".formats" + ".remove"));
        SENDFORMAT = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages" + ".formats" + ".send"));
    }

    public String PREFIX;
    public String NOPERMISSION;
    public String NOTENOUGHORBS;
    public String NOTENOUGHORBSTARGET;
    public String TARGETINVALID;
    public String ORBSNOTNUMBERS;
    public String ORBSDISPLAY;
    public String ORBSSET;
    public String ORBSSETTARGET;
    public String ORBSGIVEN;
    public String ORBSGIVENTARGET;
    public String ORBSREMOVED;
    public String ORBSREMOVEDTARGET;
    public String ORBSHELP;
    public String RELOADED;
    public String RELOADFORMAT;
    public String SETFORMAT;
    public String GIVEFORMAT;
    public String REMOVEFORMAT;
    public String SENDFORMAT;
    public String ORBSSENT;
    public String ORBSSENTTARGET;



}
