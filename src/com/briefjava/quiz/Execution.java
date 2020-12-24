package com.briefjava.quiz;

import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Execution extends Application{
	
	static Timer t; 
	public static Group root;
	static Scene scene,niveau1,niveau2,niveau3;
	static int cpt =0;
	static Players player1;
	public static ArrayList<Quiz> listeQuiz = new ArrayList<Quiz>();
	public static   ArrayList<Player_QUIZ> listePlayer_QUIZ = new ArrayList<Player_QUIZ>();
	public static Stage Window;
	public static Label labelTime ;
	public static ToggleGroup group ;
	public static int tentative=0;
	public static ImageView imageHeart;
	  static MediaPlayer mediaPlayer;
	public static ArrayList<ToggleGroup> listeRadio = new ArrayList<ToggleGroup>();
	
	public static Timeline timeline;
	
	public static Button btn_valider;
	
	public static Label labelHeart ;
	
	public static Label labelScore;
	public static Label secLabelHeart;

	public static void main(String[] args) {
		 
		
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		playMusic("quiz-show.mp3",0.2);
		labelTime = new Label("");
		labelScore = new Label("");
		labelHeart = new Label("") ;
		Window=stage;
		Window.setTitle(NiveauActuel(listeQuiz));
		 root = new Group();
		 
		 login();
		
		 
		
	}
	
	public static void playMusic(String Path,double Vol)
	{
		if(mediaPlayer != null)
		{
			mediaPlayer.stop();
			mediaPlayer = null;
			
		}
		 
			Media fileMedia = new Media(Paths.get(Path).toUri().toString());
			mediaPlayer = new MediaPlayer(fileMedia);
			mediaPlayer.setVolume(Vol);
			mediaPlayer.play();
		 
		
		
	}
	public void login()
	{
		 
		// les fonts utilisés 
				Font fontLabelCpt = Font.font("Calibri", FontWeight.BOLD, 21);
				Font fontBtn = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
				//les images utilisées
				 
				 
	
				
				ImageView startImage = new ImageView("power.png");
					 
				ImageView startHoverImage = new ImageView("powerHover.png");
				  
				Label labelNom = new Label("Nom : ");
				TextField inputNom = new TextField();
				TextField inputPreom = new TextField();
				TextField inputAge = new TextField();
				labelNom.relocate(100, 100);
				inputNom.relocate(200, 100);
				labelNom.setFont(fontLabelCpt);
				labelNom.setStyle("-fx-text-fill: #0B4E54");
				Label labelPreom = new Label("Prénom : ");
				
				labelPreom.relocate(100, 200);
				inputPreom.relocate(200, 200);
				
				labelPreom.setFont(fontLabelCpt);
				labelPreom.setStyle("-fx-text-fill: #0B4E54");
				
				Label labelAge = new Label("Age : ");
				labelAge.setFont(fontLabelCpt);
				labelAge.setStyle("-fx-text-fill: #0B4E54");
				
				labelAge.relocate(100, 300);
				inputAge.relocate(200, 300);
				
				
				Button btnStart = new Button ("Démarer le quiz");
				btnStart.setStyle("-fx-background-color: #C0D6E4");
				 
				btnStart.setFont(fontBtn);
				btnStart.setGraphic(startImage);
				
				btnStart.relocate(150, 400);
				root.getChildren().addAll(labelNom,inputNom,labelPreom,inputPreom,labelAge,inputAge,btnStart);

				scene = new Scene(root,700,500);
				
				btnStart.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
					 
						btnStart.setGraphic(startHoverImage);
					}
					
				});
				btnStart.setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
					 
						btnStart.setGraphic(startImage);
					}
					
				});
				btnStart.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						cpt=0;
						player1 = new Players(inputNom.getText(), inputPreom.getText(), Integer.parseInt(inputAge.getText()));
					 
					
						Window.setTitle(NiveauActuel(listeQuiz));
						
						timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
		                    @Override
		                    public void handle(ActionEvent event) {
		                        if(player1.getDuration() > 0) {
		                            player1.setDuration(player1.getDuration()-1);
		                            
		                            labelTime.setText(LocalTime.MIN.plusSeconds(player1.getDuration()).toString());
		                        }
		                        else {
		                            timeline.stop();
		                            showAlertWithHeaderText("Game status","Main message :","GameOver");
		                             
		                            System.exit(0);
		                        }
		                    }
		                }));
						niveau1();
		                timeline.setCycleCount(Timeline.INDEFINITE);
		                timeline.play();
					 
		                
					}});
				
				 Window.setScene(scene);
				 Window.show();
				
		
				
				
		
	}
	
	public static String NiveauActuel(ArrayList<Quiz> q)
	{
		String ret ="QUIZ";
		if(q.size()==5)
		{
			ret= "Niveau 1";
		}
		else if (q.size()==10)
		{
			ret= "Niveau 2";
		}
		else if(q.size()==15)
		{
			ret= "Niveau 3";
		}
		return ret;
		
	}
	public static void niveau1() {
		Quiz quiz1 = new Quiz("JAVA est  un langage", "Compilé et interpreté", "Compilé", "Interprété", "Compilé et interpreté");
		Quiz quiz2 = new Quiz("Toutes les classes héritent de la classe", "Object", "Main", "Object", "AWT");
		Quiz quiz3 = new Quiz("Par convention une classe", "commence par une majuscule", "est en minuscule", "commence par une majuscule", "est en majuscules");
		Quiz quiz4 = new Quiz("Est-ce que on peut avoir plusieurs constructeurs pour la même classe", "oui", "oui", "non");
		Quiz quiz5 = new Quiz("Dans la ligne \"public class A implements B\", B est:", "Interfacce", "Interfacce", "Classe parent", "Package");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau(1);
		
		hearts(2);
		 btn_valider =  new Button("Valider");
		 root.getChildren().add(btn_valider);
		 Font fontBtn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
		 Font fontTimer = Font.font("Comic Sans MS", FontWeight.BOLD, 13);
		 btn_valider.setFont(fontBtn);
		 btn_valider.setStyle("-fx-text-fill: blue");
		 btn_valider.relocate(100, 550);
		 labelTime.setFont(fontTimer);
		 labelTime.relocate(600, 25);
		 labelTime.setStyle("-fx-text-fill: #36C44A");
			root.getChildren().add(labelTime);
			
			
		 
		 btn_valider.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if(checkReponseAllQuestion(1)) {
						getTentative(1);
					}
					else
					{
						 showAlertWithHeaderText("Avertissement","Merci de répondre à toute les questions :","Poursuivez");

					}
				
				 
				}});
		

		
	}
	
public static void niveau2() {
		
		Quiz quiz1 = new Quiz("Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode. Quelle est l’extension du programme en bytecode ?", ".Class", "aucun des choix", ".JAVA", ".Class");
		Quiz quiz2 = new Quiz("Class Test{Public Test () {System.out.println(”Bonjour”);}public Test (int i) {this(); System.out.println(”Nous sommes en ”+i+”!”);}; }qu’affichera l’instruction suivante? Test t1=new Test (2020);", "Bonjour Nous sommes en 2020 !", "aucun des choix", "Bonjour Nous sommes en 2020 !", "Nous sommes en 2020 !");
		Quiz quiz3 = new Quiz("Voici un constructeur de la classe Employee, y-a-t'il un problème Public void Employe(String n){Nom=n;}", "vrai", "vrai", "faux");
		Quiz quiz4 = new Quiz("Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé", "final", "aucun des choix", "final","const");
		Quiz quiz5 = new Quiz("Dans une classe, on accède à ses variables grâce au mot clé", "this", "aucun des choix", "this", "super");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau(2);
		
		btn_valider =  new Button("Valider");
		 root.getChildren().add(btn_valider);
		 Font fontBtn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
		 Font fontTimer = Font.font("Comic Sans MS", FontWeight.BOLD, 13);
		 btn_valider.setFont(fontBtn);
		 btn_valider.setStyle("-fx-text-fill: blue");
		 btn_valider.relocate(100, 550);
		 labelTime.setFont(fontTimer);
		 labelTime.relocate(600, 10);
		 labelTime.setStyle("-fx-text-fill: green");
			root.getChildren().add(labelTime);
			
			
		 
		 btn_valider.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if(checkReponseAllQuestion(2)) {
						getTentative(2);
						
					}
					else
					{
						 showAlertWithHeaderText("Avertissement","Merci de répondre à toute les questions :","Poursuivez");

					}
				
				 
				}});
	}

public static void niveau3() {
	Quiz quiz1 = new Quiz("calculerSalaire(int) calculerSalaire(int, double)La méthode calculerSalaire est:", "surchargée", "aucun des choix", "surchargée", "redéfinie");
	Quiz quiz2 = new Quiz("Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite.", "vrai", "vrai", "faux");
	Quiz quiz3 = new Quiz("Est-ce qu’une classe peut implémenter plusieurs interfaces?", "vrai", "vrai", "faux");
	Quiz quiz4 = new Quiz("La déclaration d'une méthode suivante :public void traitement() throws IOExceptionprécise que la méthode propage une exception contrôlée", "vrai", "vrai", "faux");
	Quiz quiz5 = new Quiz("class Test{public static void main (String[] args) {try {int a =10;System.out.println (\"a = \" + a );int b = 0 / a;System.out.println (\"b = \" + b);}catch(ArithmeticException e){System.out.println (\"diviser par 0!\"); }finally{System.out.println(\"je suis à l’intérieur de finally\");}}}", "a=10 b=0 Je suis à l’intérieur de finally", "aucun des choix", "a=10 b=0 Je suis à l’intérieur de finally", "a=10 b=0 diviser par 0! je suis à l’intérieur de finally");
	
	listeQuiz.add(quiz1);
	listeQuiz.add(quiz2);
	listeQuiz.add(quiz3);
	listeQuiz.add(quiz4);
	listeQuiz.add(quiz5);
	
	remplirePanelNiveau(3);

	btn_valider =  new Button("Valider");
	 root.getChildren().add(btn_valider);
	 Font fontBtn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
	 Font fontTimer = Font.font("Comic Sans MS", FontWeight.BOLD, 13);
	 btn_valider.setFont(fontBtn);
	 btn_valider.setStyle("-fx-text-fill: blue");
	 btn_valider.relocate(100, 550);
	 labelTime.setFont(fontTimer);
	 labelTime.relocate(600, 25);
	 labelTime.setStyle("-fx-text-fill: green");
		root.getChildren().add(labelTime);
		
	
	 
	 btn_valider.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if(checkReponseAllQuestion(3)) {
					getTentative(3);
				}
				else
				{
					 showAlertWithHeaderText("Avertissement","Merci de répondre à toute les questions :","Poursuivez");

				}
			
			 
			}});
	
}

public static void hearts(int nbrHeart)
{
	 
	 ImageView imageHeart2 = new ImageView("doubleHeart.png");
	 ImageView imageHeart = new ImageView("heart.png");
 
	if(nbrHeart == 1)
	{
		
		  if(root.getChildren().contains(labelHeart))
		  {
			  root.getChildren().remove(labelHeart);
		  }
		  root.getChildren().add(labelHeart);
		labelHeart.setGraphic(imageHeart);
	 
	 
		 
		
	}else if(nbrHeart == 2)
	{ 
		labelHeart.setGraphic(imageHeart2);
		  if(root.getChildren().contains(labelHeart))
		  {
			  root.getChildren().remove(labelHeart);
		  }
		root.getChildren().add(labelHeart);
		 
		
	} 
	
	  labelHeart.relocate(3,0);
}

public static void getTentative(int niveau) {
	
	int start , end;
	if(niveau == 1)
	{
		start=0;
		end  =5;

	}
	else if(niveau == 2)
	{
		start=5;
		end  =10;
	}
	else
	{
		start=10;
		end  =15;
	}
    if(cpt==0) {
        getreponses(niveau);
        if (tentative == 0) {
            if ((niveau == 1  && calculeScore(1) >= 40) || (niveau == 2  && calculeScore(2) >= 60) || (niveau == 3  && calculeScore(3) >= 80)) {
            	{
            		 afficheCorrection(niveau);
                     hearts(2);
            	}
            }else {
            	 hearts(1);
                tentative = 1;
                for (int i = start; i < end; i++) {
                    listePlayer_QUIZ.remove(listePlayer_QUIZ.size()-1);
                    listeRadio.get(i).selectToggle(null);
                    
                }
               
            }
        }
        else {
            tentative = 0;
            afficheCorrection(niveau);
            hearts(1);
        }
    }
    else {
        afficheCorrection(niveau);
        hearts(2);
    }
}
	 
	public static void getAlertWithImage(String chemin, String Title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(null);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(400, 320);
        Image image = new Image("file:"+chemin);
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.showAndWait();
    }
	public static void remplirePanelNiveau(int niveau) {
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		 root = new Group();
		 
		 Font fontLabelQuestion = Font.font("Calibri", FontWeight.BOLD, 14);
			Font fontBtn = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
			
			
	 
		  
		int y=40;
		for(int i = start;i<end;i++) {
			int x=30;
			Label label_question = new Label(listeQuiz.get(i).getQuestion());
			
			label_question.relocate(x,y);
		 
		 
			
			 
			
			label_question.setStyle("-fx-text-fill: blue");
			label_question.setFont(fontLabelQuestion);
			label_question.setMaxWidth(780);
			label_question.setWrapText(true);
			root.getChildren().add(label_question);
			
			y += 50;
			RadioButton choice1 = new RadioButton(listeQuiz.get(i).getChoice_one());
			choice1.setUserData(listeQuiz.get(i).getChoice_one());
			choice1.relocate(x,y);
		 
			choice1.setMaxWidth(200);
			choice1.setMaxHeight(50);
			 
			choice1.setWrapText(true);
			root.getChildren().add(choice1);
			
			x += 200;
			RadioButton choice2 = new RadioButton(listeQuiz.get(i).getChoice_two());
			choice2.setUserData(listeQuiz.get(i).getChoice_two());
			 choice2.relocate(x,y);
		 
			choice2.setMaxWidth(200);
			choice2.setMaxHeight(50);
			 
			choice2.setWrapText(true);
			root.getChildren().add(choice2);

			 
			 group = new ToggleGroup();
			 group.getToggles().addAll(choice1,choice2);
			 
			if(listeQuiz.get(i).getChoice_three() != null) {
				x += 200;
				RadioButton choice3 = new RadioButton(listeQuiz.get(i).getChoice_three());
				choice3.setUserData(listeQuiz.get(i).getChoice_three());
				 choice3.relocate(x,y);
			 
				choice3.setMaxWidth(200);
				choice3.setMaxHeight(50);
				 
				choice3.setWrapText(true);
				group.getToggles().add(choice3);
				root.getChildren().add(choice3);
			}
			y += 40;
			listeRadio.add(group);
			

			
		}
		 
		niveau1 = new Scene(root,1100,700);
			
		 Window.setScene(niveau1);
		 Window.show();
	}
	
	private static void showAlertWithHeaderText(String title,String midText,String message) {
		
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(midText);
        alert.setContentText(message);
 
        alert.showAndWait();
    }
	public static boolean checkReponseAllQuestion(int niveau){
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		boolean ret=true;
		for(int i = start;i<end;i++) {
			if(listeRadio.get(i).getSelectedToggle() == null) {
				ret = false;
			}
		}
		return ret;
	}
	public static int calculeScore(int niveau)
	{
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		int score = 0;
		for(int i = start;i<end;i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				score += 20;
			}
		}
		return score;
	}
	
	public static void getreponses(int niveau)
	{ 
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		for(int i = start;i<end;i++) {
			boolean choice; 
			RadioButton Rb = (RadioButton) listeRadio.get(i).getSelectedToggle();
			if(listeQuiz.get(i).getReponse() == Rb.getText()) {
				choice=true;
			}
			else {
				choice=false;
			}
			Player_QUIZ player_QUIZ = new Player_QUIZ(player1.getId_Player(), listeQuiz.get(i).getId_quiz(), listeRadio.get(i).getSelectedToggle().toString(), choice);
			listePlayer_QUIZ.add(player_QUIZ);
			 
		}
		
	}
	
	public static void correction(int niveau){
		 
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		for(int i = start;i<end;i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				RadioButton Rb = (RadioButton) listeRadio.get(i).getSelectedToggle();
				Rb.setStyle("-fx-text-fill :#36C44A");
		    
		        
			}
			else{
				ToggleGroup t1 = (ToggleGroup) listeRadio.get(i);
				for(Toggle t: t1.getToggles())
				{
					RadioButton rd = (RadioButton)t;
					if(rd.isSelected())
					{
						rd.setStyle("-fx-text-fill :red");
					}else if(rd.getText() == listeQuiz.get(i).getReponse())
					{
						rd.setStyle("-fx-text-fill :#36C44A");
					}
				}
				
			 
		        }
			}
	}
	
	// function affiche resultat : 
 
	public static void afficheCorrection(int niveau) {
		 
		if(cpt==0) {
			 
			cpt=1;
			btn_valider.setText("Suivant");
			correction(niveau);
			
			labelScore.setText("score : " + calculeScore(niveau));
			 Font fontTScore = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
			labelScore.setFont(fontTScore);
			labelScore.relocate(710, 10);
			labelScore.setStyle("-fx-text-fill: blue");
			
			 root.getChildren().add(labelScore);
			 
		}
		else {
			cpt=0;
			if(niveau==1) {
				if(calculeScore(1) >= 40) {
					
					niveau2();
					
				}
				else {
					playMusic("lose.mp3",0.2);
					getAlertWithImage("lose.gif","You LOST Better Luck Next time!");
                    System.exit(0);
				}
			}
			else if(niveau==2) {
				if(calculeScore(2) >= 60) {
					niveau3();
					
				}
				else {
					playMusic("lose.mp3",0.2);
					getAlertWithImage("lose.gif","You LOST Better Luck Next time!");
                    System.exit(0);
				}
			}
			else {
				if(calculeScore(3) >= 80) {
					playMusic("win.mp3",0.2);
					getAlertWithImage("source.gif","You WON Congratulations !!");
					  System.exit(0);
					
				}
				else {
					
					playMusic("lose.mp3",0.2);
					getAlertWithImage("lose.gif","You LOST Better Luck Next time!");
                    System.exit(0);
				}
				
			}
	}
}
	 
}
	

 
   
