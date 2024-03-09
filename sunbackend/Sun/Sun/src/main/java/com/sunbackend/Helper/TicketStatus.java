package com.sunbackend.Helper;

public enum TicketStatus {

    PRIORITY("PRIORITY"),
    READY("READY"),
    PROGRESS("PROGRESS"),
    REVIEW("REVIEW"),
    TESTING("TESTING"),
    FINISHED("FINISHED");
    private final String displayName;

    TicketStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

