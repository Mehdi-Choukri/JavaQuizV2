package com.briefjava.quiz;

public class Players {
	private int id_Player;
	   
	   private String nom;
	    
	   private String prenom;
	  
	   private int age;
	   
	   private int duration ;
	   
	   private static int id=0;
	  
	   public Players(String nom, String prenom, int age) {
	 
		 setId_Player(++id);
		 setNom(nom);
		 setPrenom(prenom);
		 setAge(age);
		 setDuration(300);
	}

	   

	public int getId_Player() {
		return id_Player;
	}

	public void setId_Player(int id_Player) {
		this.id_Player = id_Player;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
