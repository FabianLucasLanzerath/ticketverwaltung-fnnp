package de.bkbw.fnnp.app;

import java.util.ArrayList;
import java.util.UUID;

import de.bkbw.fnnp.controller.DocumentController;
import de.bkbw.fnnp.model.Comment;
import de.bkbw.fnnp.model.Device;
import de.bkbw.fnnp.model.Ticket;
import de.bkbw.fnnp.model.User;
import de.bkbw.fnnp.model.UserRole;

public class Main {
	public static void main(String[] args) { 
		DocumentController jc = DocumentController.getInstance();
		
		// read tickets in
		ArrayList<Ticket> tickets = jc.getTickets();
		if (!tickets.isEmpty())
			for (Ticket ticket : tickets) 
				System.out.println(ticket.uuid);
		
		// save tickets to working dir
		User user = new User(UUID.randomUUID(), "fnnp@bkbw.de", "ich", UserRole.USER, new ArrayList<Device>());
		Ticket ticket = new Ticket(UUID.randomUUID(), "NickolaiIsDoof", "NickolaiIsDoofer", user, null, user, null, new ArrayList<Comment>());
		jc.saveTicket(ticket);
	}
}