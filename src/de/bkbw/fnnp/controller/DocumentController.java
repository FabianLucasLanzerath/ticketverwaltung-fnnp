package de.bkbw.fnnp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public <E> ArrayList<E> loadJson(E fromClass, CryptoController cc) {
		try {
			String folderPath = null;
			if (fromClass.getClass() == Ticket.class)
				folderPath = this.ticketsDir;
			
			if (fromClass.getClass() == User.class)
				folderPath = this.usersDir;
			
			if (folderPath == null) 
				throw new IOException();
			
			String files[] = new File(folderPath).list();
			
			for (String file : files) {
				String filePath = folderPath + file;
				
				SecretKey key = cc.getKeyFromPassword("NickolaiIstDoof", "12345678");
			    String algorithm = "AES/CBC/PKCS5Padding";
			    IvParameterSpec ivParameterSpec = cc.generateIv();
			    InputStream resource = InputStream.class.getClassLoader().getResourceAsStream(filePath);
			    File inputFile = resource.getFile();
			    String encryptedFile = new File(filePath).toString();
			    String decryptedFile = new File(filePath).toString();
			    cc.encrypt(encryptedFile, key, ivParameterSpec);
			    cc.decrypt(
			      decryptedFile, key, ivParameterSpec);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public <E> void saveToJson(E classToSave, CryptoController cc) {
		try {
			String folderPath = null;
			UUID fileId = UUID.randomUUID();
			if (classToSave.getClass() == Ticket.class)
				folderPath = this.ticketsDir;
			
			if (classToSave.getClass() == User.class)
				folderPath = this.usersDir;
			if (folderPath == null) 
				throw new IOException();
			
			FileWriter writer = new FileWriter(folderPath + "/" + fileId + ".json");
			writer.write(this.gson.toJson(classToSave));
			writer.close();
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, cc.getKeyFromPassword("NickolaiIstDoof", "12345678"), cc.generateIv());;
		    FileInputStream inputStream = new FileInputStream(folderPath + "/" + fileId + ".json");
		    FileOutputStream outputStream = new FileOutputStream(folderPath + "/" + fileId + ".json");
		    byte[] buffer = new byte[64];
		    int bytesRead;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        byte[] output = cipher.update(buffer, 0, bytesRead);
		        if (output != null) {
		            outputStream.write(output);
		        }
		    }
		    byte[] outputBytes = cipher.doFinal();
		    if (outputBytes != null) {
		        outputStream.write(outputBytes);
		    }
		    inputStream.close();
		    outputStream.close();
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
