package de.bkbw.fnnp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import de.bkbw.fnnp.model.Ticket;
import de.bkbw.fnnp.model.User;

public class DocumentController {
	private static DocumentController documentController;
	private Gson gson;
	private String workingDir = System.getenv("APPDATA") + "\\Ticketverwaltung";
	private String ticketsDir = this.workingDir + "\\tickets";
	private String usersDir = this.workingDir + "\\users";
	
	private DocumentController() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		
		if (!this.dirExists(this.workingDir)) 
			new File(this.workingDir).mkdir();	
			new File(this.ticketsDir).mkdir();
			new File(this.usersDir).mkdir();
		
		if (!this.dirExists(this.ticketsDir)) 
			new File(this.ticketsDir).mkdir();
		
		if (!this.dirExists(this.usersDir)) 
			new File(this.usersDir).mkdir();
	}

	public static DocumentController getInstance() {
		if (documentController == null)
			documentController = new DocumentController();

	    return documentController;
	}
	
	private boolean dirExists(String folderPath) {
		if (Files.isDirectory(Paths.get(folderPath)))
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
				//if (br.readLine() == null)
				//	continue;
				tickets.add(this.gson.fromJson(br, Ticket.class));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}
	
	@SuppressWarnings("unchecked")
	public <E> ArrayList<E> loadJson(E fromClass, CryptoController cc) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, ClassNotFoundException {
		ArrayList<E> result = new ArrayList<>();
		String folderPath = null;
		
		if (fromClass == Ticket.class)
			folderPath = this.ticketsDir;
		
		if (fromClass == User.class)
			folderPath = this.usersDir;
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, cc.getKeyFromPassword("NickolaiIstDoof", "123456789"), cc.generateIv());

		try {
			if (folderPath == null) 
				throw new IOException();
			
			String files[] = new File(folderPath).list();
					
			// TODO: Add decryption
			for (String file : files) {
				String filePath = folderPath + file;
				
				Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
						
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public <E> void saveJson(E object, CryptoController cc) throws ClassNotFoundException, BadPaddingException, IllegalBlockSizeException {
		String folderPath = null;
		UUID fileId = UUID.randomUUID();
		
		if (object.getClass() == Ticket.class)
			folderPath = this.ticketsDir;
		
		if (object.getClass() == User.class)
			folderPath = this.usersDir;
		
		try {			
			if (folderPath == null) 
				throw new IOException();
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, cc.getKeyFromPassword("NickolaiIstDoof", "123456789"), cc.generateIv());
			
			// TODO: Add encryption
		    SealedObject sealedObject = new SealedObject((Serializable) object, cipher);
		    
			
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
}
