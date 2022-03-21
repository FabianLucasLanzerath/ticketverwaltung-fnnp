package de.bkbw.fnnp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import de.bkbw.fnnp.model.Ticket;

public class DocumentController {
	private static DocumentController jsonController;
	private Gson gson;
	private String workingDir = System.getenv("APPDATA") + "\\Ticketverwaltung";
	private String ticketsDir = this.workingDir + "\\tickets";
	
	private DocumentController() {
		this.gson = new Gson();
		
		if (!this.workingDirExists()) 
			new File(this.workingDir).mkdir();	
			new File(this.ticketsDir).mkdir();
		
		if (!this.ticketDirExists()) 
			new File(this.ticketsDir).mkdir();
	}

	public static DocumentController getInstance() {
		if (jsonController == null)
			jsonController = new DocumentController();

	    return jsonController;
	}
	
	private boolean workingDirExists() {
		if (Files.isDirectory(Paths.get(this.workingDir)))
		    return true;

		return false;
	}
	
	private boolean ticketDirExists() {
		if (Files.isDirectory(Paths.get(this.ticketsDir)))
		    return true;

		return false;
	}
	
	public String getWorkingDir() {
		return this.workingDir;
	}
	
	// TODO: if file is not empty there is a gson error because of lines 64/65
	public ArrayList<Ticket> getTickets() { 
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		String files[] = new File(this.ticketsDir).list();
		try {
			for (String file : files) {
				BufferedReader br = new BufferedReader(new FileReader(this.ticketsDir + "/" + file));
				if (br.readLine() == null)
					continue;
				tickets.add(this.gson.fromJson(br, Ticket.class));	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}

	public void saveTicket(Ticket ticket) {
		try {
			FileWriter writer = new FileWriter(this.ticketsDir + "/" + ticket.getTitle() + ".json");
			writer.write(this.gson.toJson(ticket));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
