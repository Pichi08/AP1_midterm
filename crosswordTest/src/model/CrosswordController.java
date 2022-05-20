package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;


	public CrosswordController() {
		this.crossword = new Cell[100][100];
	}
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {
		this.crossword = new Cell[puzzle.length][puzzle[0].length];
		int count=0;

		for(int i =0; i<puzzle.length; i++){
			for(int j = 0; j<puzzle[0].length; j++){

				count++;
				String letter = puzzle[i][j];

				Cell obj = new Cell(CellType.CLOSED, letter, count);

				if(puzzle[i][j]==" "){
					obj = new Cell(CellType.BLACK, letter, 0);
					count--;
				}

				crossword[i][j] = obj;

			}
		}


		
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {
		
		String out = "";
		boolean flag = false;

		for(int i =0; i<crossword.length && flag == false; i++){
			for(int j = 0; j<crossword[0].length && flag == false; j++){
				Cell obj = crossword[i][j];
				if(obj.getLetter().equalsIgnoreCase(letter) && obj.getState()==CellType.CLOSED){
					out="There is a word with the letter "+ letter +" in the crossword in the box "+obj.getNumber();
					obj.setState(CellType.OPEN);
					flag = true;
				} else {
					out="I'm sorry, there are no words with that letter";
				}

			}
		}

		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
