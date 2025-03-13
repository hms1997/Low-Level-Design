package factory;

import tickets.Epic;
import tickets.OnCall;
import tickets.Story;
import tickets.Ticket;

public class TicketFactory {
    public static Ticket createTicket(String type, String id, String title, String description) {
        return switch(type.toUpperCase()) {
            case "STORY" -> new Story(id, title, description);
            case "EPIC" -> new Epic(id, title, description);
            case "ON-CALL" -> new OnCall(id, title, description);
            default -> throw new IllegalArgumentException("Invalid ticket type");
        };
    }
}
