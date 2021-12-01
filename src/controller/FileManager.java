package controller;

import java.io.*;
import java.util.*;

import model.DonneesNiveau;


public class FileManager {

	public boolean isFileCreated(String nom) {
		File f = new File("src\\data\\"+nom);
		return(f.exists() && !f.isDirectory());
	}
	
	public boolean writeToFile(String nom, HashMap<String, DonneesNiveau> data) {
		try{
				FileOutputStream f = new FileOutputStream("src\\data\\"+nom);
			    ObjectOutput s = new ObjectOutputStream(f); 
			    s.writeObject(data);
			    return true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return false;
	    }
	}
	public boolean setSkin(String nom, HashMap<String, Integer> data) {
		try{
				FileOutputStream f = new FileOutputStream("src\\data\\"+nom);
			    ObjectOutput s = new ObjectOutputStream(f); 
			    s.writeObject(data);
			    return true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return false;
	    }
	}
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

