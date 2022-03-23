package de.bkbw.fnnp.app;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import de.bkbw.fnnp.controller.CryptoController;
import de.bkbw.fnnp.controller.DocumentController;
import de.bkbw.fnnp.model.Device;
import de.bkbw.fnnp.model.User;
import de.bkbw.fnnp.model.UserRole;

public class Main {
	public static void main(String[] args) throws IllegalBlockSizeException, IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, ClassNotFoundException, BadPaddingException { 
		DocumentController jc = DocumentController.getInstance();
		CryptoController cc = CryptoController.getInstance();
		
		User user = new User(UUID.randomUUID(), "fnnp@bkbw.de", "Fabian", UserRole.USER, new ArrayList<Device>());
		jc.saveToJson(user, cc);
	}
}