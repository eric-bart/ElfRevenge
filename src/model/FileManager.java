package model;

import java.io.*;
import java.util.*;


public class FileManager {

	public boolean isFileCreated(String nom) {
		File f = new File("src\\data\\"+nom);
		return(f.exists() && !f.isDirectory());
	}
	
	/**
	 * Fonction permettant d'�crire dans le fichier choisi et en rentrant les donn�es souhait�.
	 * @param nom
	 * @param data
	 * @return
	 */
	public boolean writeToFile(String nom, HashMap<String, DonneesNiveau> data) {
		try{
				FileOutputStream f = new FileOutputStream("src\\data\\"+nom);
			    @SuppressWarnings("resource")
				ObjectOutput s = new ObjectOutputStream(f); 
			    s.writeObject(data);
			    return true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return false;
	    }
	}
	/**
	 * Permet d'�crire dans le fichier choisis pour s�lectionner le skin voulu.
	 * @param nom
	 * @param data
	 * @return
	 */
	public boolean setSkin(String nom, HashMap<String, Integer> data) {
		try{
				FileOutputStream f = new FileOutputStream("src\\data\\"+nom);
			    @SuppressWarnings("resource")
				ObjectOutput s = new ObjectOutputStream(f); 
			    s.writeObject(data);
			    return true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return false;
	    }
	}
	/**
	 * Permet de lire dans le fichier choisit pour retrouver les donn�es que l'on souhaite.
	 * @param nom
	 * @return
	 */
	public Object readFile(String nom) {
			try {
				 
	            FileInputStream fileIn = new FileInputStream("src\\data\\"+nom);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	 
	            Object obj = objectIn.readObject();
	 
	            System.out.println("The Object has been read from the file");
	            objectIn.close();
	            return obj;
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	}