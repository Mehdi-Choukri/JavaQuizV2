package com.briefjava.quiz;

public class Player_QUIZ implements InterListe{
	private int id_Player;  
	   private int id_quiz;
	   private  String reponseChosen;
	   private boolean goodchoice;

	   
	   public Player_QUIZ(int idP, int idQ, String RC, boolean choice)
	   {
		   setId_Player(idP);
		   setId_quiz(idQ);
		   setReponseChosen(RC);
		   setGoodchoice(choice);
	   }
	     

	public int getId_Player() {
		return id_Player;
	}

	public void setId_Player(int id_Player) {
		this.id_Player = id_Player;
	}

 
	public int getId_quiz() {
		return id_quiz;
	}

	public void setId_quiz(int id_quiz) {
		this.id_quiz = id_quiz;
	}
	public boolean isGoodchoice() {
		return goodchoice;
	}
	public void setGoodchoice(boolean goodchoice) {
		this.goodchoice = goodchoice;
	}

	public String getReponseChosen() {
		return reponseChosen;
	}

	public void setReponseChosen(String reponseChosen) {
		this.reponseChosen = reponseChosen;
	}


}
