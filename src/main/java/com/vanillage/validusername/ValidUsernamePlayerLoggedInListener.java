package com.vanillage.validusername;

import java.util.regex.Pattern;

import com.vanillage.minecraftalphaserver.event.PlayerLoggedInEvent;
import com.vanillage.minecraftalphaserver.event.PlayerLoggedInListener;

public final class ValidUsernamePlayerLoggedInListener extends PlayerLoggedInListener {
    private final ValidUsername plugin;
    private Pattern userNamePattern;
    private String kickReason;

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin) {
        this(plugin, -20, null, null);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, int priority) {
        this(plugin, priority, null, null);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, Pattern userNamePattern) {
        this(plugin, -20, userNamePattern, null);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, String kickReason) {
        this(plugin, -20, null, kickReason);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, int priority, Pattern userNamePattern) {
        this(plugin, priority, userNamePattern, null);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, int priority, String kickReason) {
        this(plugin, priority, null, kickReason);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, Pattern userNamePattern, String kickReason) {
        this(plugin, -20, userNamePattern, kickReason);
    }

    public ValidUsernamePlayerLoggedInListener(ValidUsername plugin, int priority, Pattern userNamePattern, String kickReason) {
        super(priority);

        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }

        this.plugin = plugin;
        this.userNamePattern = userNamePattern == null ? Pattern.compile("[A-Za-z0-9_]{2,16}") : userNamePattern;
        this.kickReason = kickReason == null ? "Invalid username <username>, try: " + this.userNamePattern.pattern() : kickReason;
    }

    public ValidUsername getPlugin() {
        return plugin;
    }

    public Pattern getUserNamePattern() {
        return userNamePattern;
    }

    public void setUserNamePattern(Pattern userNamePattern) {
        if (userNamePattern == null) {
            throw new IllegalArgumentException("userNamePattern cannot be null");
        }

        this.userNamePattern = userNamePattern;
    }

    public String getKickReason() {
        return kickReason;
    }

    public void setKickReason(String kickReason) {
        if (kickReason == null) {
            throw new IllegalArgumentException("kickReason cannot be null");
        }

        this.kickReason = kickReason;
    }

    @Override
    protected void onEvent(PlayerLoggedInEvent event) {
        if (!userNamePattern.matcher(event.getPacket().username).matches()) {
            event.setKickReason(kickReason.replace("<username>", event.getPacket().username));
            event.deny();
        }
    }
}
