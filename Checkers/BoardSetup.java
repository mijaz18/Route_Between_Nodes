import java.util.Scanner;

public class BoardSetup {
	
	
	public static String[][] PromptBoard() {
		System.out.println("Checkers by John and Usama \nChoose your game:\n1. Small 4x4 Checkers\n2. "
				+ "Standard 8x8 Checkers\nYour choice?");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		if(choice==1) {
			return FourbyFour();
		} else {
			return eightbyEight();
		}		
	}
		
	
	
	public static int promptAlgorithm() {
		Scanner in = new Scanner(System.in);
		System.out.println("Choose your opponent:\n1. An agent that plays randomly\n"
				+ "2. An agent that uses MINIMAX\n3. An agent that uses MINIMAX with alpha-beta pruning.\n4."
				+ " An agent that uses H-MINIMAX with a fixed depth cutoff.\nYour Choice?");
		int algochoice=in.nextInt();
		switch (algochoice){
			case 1: 
				//Randomly
				return 1;
				//break;
			case 2:
				return 2;
				//MINIMAX
				//break;
			case 3:
				//MINIMAX alpha-beta pruning
				return 3;
			case 4:
				return 4;
			default:
				return -1;
				//H-MINIMAX fixed depth cutoff
				/*
				System.out.println("Do you want to play BLACK (b) or WHITE (w)?");
				String color=in.next();
				if(color.equals("w")) {
					
				}
				*/
				//break;
		}
		
	}
	
	public static String[][] FourbyFour() {
		String board[][]=new String[10][5];
		for (int i=0; i<10;i++) {
			for(int j=0; j<5;j++) {
				if(i==0) {				
					board[i][j]=Integer.toString(j);
				}else if(i%2==1) {
					board[i][j]="-";
				}else if (i%2==0) {
					if(j==0) {
						if(i==2) {
							board[i][j]="A";
						}else if(i==4) {
							board[i][j]="B";
						}else if(i==6) {
							board[i][j]="C";
						}else if(i==8) {
							board[i][j]="D";
						}	
					}else {
						board[i][j]="| |";
					}
				} 
				System.out.print(board[i][j]+ "\t");
			}
			System.out.println();
		}		
		return board;
	}
	
	
	public static String[][] eightbyEight() {
		String board[][]=new String[18][9];
		for (int i=0; i<18;i++) {
			for(int j=0; j<9;j++) {
				if(i==0) {				
					board[i][j]=Integer.toString(j);
				}else if(i%2==1) {
					board[i][j]="-";
				}else if (i%2==0) {
					if(j==0) {
						if(i==2) {
							board[i][j]="A";
						}else if(i==4) {
							board[i][j]="B";
						}else if(i==6) {
							board[i][j]="C";
						}else if(i==8) {
							board[i][j]="D";
						}else if(i==10) {
							board[i][j]="E";
						}else if(i==12) {
							board[i][j]="F";
						}else if(i==14) {
							board[i][j]="G";
						}else if(i==16) {
							board[i][j]="H";
						}	
					}else {
						board[i][j]="| |";
						}
					} 
				System.out.print(board[i][j]+ "\t");
				}
			System.out.println();
			}	
			return board;
		}
	
	
	
	}



