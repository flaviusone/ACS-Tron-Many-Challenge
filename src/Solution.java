import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	/* Head ends here */
	static void nextMove(String player, int[] pos, String[] board) {
		int counter1=0,counter2=0,counter3=0,counter4=0,i;
		if (player.equals("r")) {
			/*Search Down*/
			i=1;
			while(board[ pos[ 0 ] + i ].charAt(pos[ 1 ]) != '#'
					&& board[ pos[ 0 ] + i ].charAt(pos[ 1 ]) != 'r'
					&& board[ pos[ 0 ] + i ].charAt(pos[ 1 ]) != 'g')
			{
				counter1++;
				i++;
			}
			/*Search Up*/
			i=1;
			while(board[ pos[ 0 ] - i ].charAt(pos[ 1 ]) != '#'
					&& board[ pos[ 0 ] - i ].charAt(pos[ 1 ]) != 'r'
					&& board[ pos[ 0 ] - i ].charAt(pos[ 1 ]) != 'g')
			{
				counter2++;
				i++;
			}
			/*Search Right*/
			i=1;
			while(board[ pos[ 0 ] ].charAt(pos[ 1 ] + i) != '#'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] + i) != 'r'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] + i) != 'g')
			{
				counter3++;
				i++;
			}
			/*Search Left*/
			i=1;
			while(board[ pos[ 0 ] ].charAt(pos[ 1 ] - i) != '#'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] - i) != 'r'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] - i) != 'g')
			{
				counter4++;
				i++;
			}
			
			/*See whitch way is the best one*/
			int way;
			if(counter1 > counter2 && counter1 > counter3 && counter1 > counter4){
				way=0; /*Down*/
			}else if(counter2 > counter1 && counter2 > counter3 && counter2 > counter4)
				way=1; /*Up*/
			else if(counter3 > counter2 && counter3 > counter1 && counter3 > counter4)
				way=2; /*Right*/
			else way=3; /*Left*/
			
			/*Print the best way to go*/
			switch(way){
			case 0 : System.out.println("DOWN");
			case 1 : System.out.println("UP");
			case 2 : System.out.println("RIGHT");
			default: System.out.println("LEFT");
			}
			
			

		}
		else {
			/*Search Down*/
			i=1;
			while(board[ pos[2] + i ].charAt(pos[3]) != '#'
					&& board[ pos[2] + i ].charAt(pos[3]) != 'r'
					&& board[ pos[2] + i ].charAt(pos[3]) != 'g')
			{
				counter1++;
				i++;
			}
			/*Search Up*/
			i=1;
			while(board[ pos[2] - i ].charAt(pos[3]) != '#'
					&& board[ pos[2] - i ].charAt(pos[3]) != 'r'
					&& board[ pos[2] - i ].charAt(pos[3]) != 'g')
			{
				counter2++;
				i++;
			}
			/*Search Right*/
			i=1;
			while(board[ pos[2] ].charAt(pos[3] + i) != '#'
					&& board[ pos[2] ].charAt(pos[3] + i) != 'r'
					&& board[ pos[2] ].charAt(pos[3] + i) != 'g')
			{
				counter3++;
				i++;
			}
			/*Search Left*/
			i=1;
			while(board[ pos[2] ].charAt(pos[3] - i) != '#'
					&& board[ pos[2] ].charAt(pos[3] - i) != 'r'
					&& board[ pos[2] ].charAt(pos[3] - i) != 'g')
			{
				counter4++;
				i++;
			}
			
			/*See whitch way is the best one*/
			int way;
			if(counter1 > counter2 && counter1 > counter3 && counter1 > counter4){
				way=0; /*Down*/
			}else if(counter2 > counter1 && counter2 > counter3 && counter2 > counter4)
				way=1; /*Up*/
			else if(counter3 > counter2 && counter3 > counter1 && counter3 > counter4)
				way=2; /*Right*/
			else way=3; /*Left*/
			
			/*Print the best way to go*/
			switch(way){
			case 0 : System.out.println("DOWN");
			case 1 : System.out.println("UP");
			case 2 : System.out.println("RIGHT");
			default: System.out.println("LEFT");
			}
			
		}

	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		String player = in.next();
		String pos = in.next();
		String[] str_pos = pos.split(" ");
		int[] position = new int[4];
		int[] sizes = new int[2];

		for (int i = 0; i < 4; i++) {
			position[ i ] = Integer.parseInt(str_pos[ i ]);
		}
		pos = in.next();
		str_pos = pos.split(" ");
		for (int i = 0; i < 2; i++) {
			sizes[ i ] = Integer.parseInt(str_pos[ i ]);
		}

		String board[] = new String[sizes[ 0 ]];

		for (int i = 0; i < sizes[ 0 ]; i++) {
			board[ i ] = in.next();
		}

		nextMove(player, position, board);
	}
}