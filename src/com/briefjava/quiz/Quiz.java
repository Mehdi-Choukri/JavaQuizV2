package com.briefjava.quiz;

public class Quiz {

	 private static int id_quiz;
	 
	   private String question;
	  
	   private String reponse;

	   private String choice_one;
	 
	   private String choice_two;
	   
	   private String choice_three;


	public int getId_quiz() {
		return id_quiz;
	}

	public void setId_quiz(int id_quiz) {
		Quiz.id_quiz = id_quiz;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public Quiz( String question, String reponse, String choice_one, String choice_two,
			String choice_three) {
		 
		id_quiz++;
		this.question = question;
		setReponse(reponse);
		setChoice_one(choice_one);
		setChoice_two(choice_two);
		setChoice_three( choice_three);
	}
	
	public Quiz( String question, String reponse, String choice_one, String choice_two) {
		 
		id_quiz++;
		this.question = question;
		setReponse(reponse);
		setChoice_one(choice_one);
		setChoice_two(choice_two);
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getChoice_one() {
		return choice_one;
	}

	public void setChoice_one(String choice_one) {
		this.choice_one = choice_one;
	}

	public String getChoice_two() {
		return choice_two;
	}

	public void setChoice_two(String choice_two) {
		this.choice_two = choice_two;
	}

	public String getChoice_three() {
		return choice_three;
	}

	public void setChoice_three(String choice_three) {
		this.choice_three = choice_three;
	}

}
