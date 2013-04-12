import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	/* Head ends here */
	static void nextMove(String player, int[] pos, String[] board) {
		if (player.equals("r")) {
			if (board[ pos[ 0 ] - 1 ].charAt(pos[ 1 ]) != '#'
					&& board[ pos[ 0 ] - 1 ].charAt(pos[ 1 ]) != 'r'
					&& board[ pos[ 0 ] - 1 ].charAt(pos[ 1 ]) != 'g') {
				System.out.println("UP");
				return;
			}

			if (board[ pos[ 0 ] ].charAt(pos[ 1 ] + 1) != '#'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] + 1) != 'r'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] + 1) != 'g') {
				System.out.println("RIGHT");
				return;
			}
			if (board[ pos[ 0 ] ].charAt(pos[ 1 ] - 1) != '#'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] - 1) != 'r'
					&& board[ pos[ 0 ] ].charAt(pos[ 1 ] - 1) != 'g') {
				System.out.println("LEFT");
				return;
			}
			System.out.println("DOWN");
			return;
		}
		else {
			if (board[ pos[ 2 ] - 1 ].charAt(pos[ 3 ]) != '#'
					&& board[ pos[ 2 ] - 1 ].charAt(pos[ 3 ]) != 'r'
					&& board[ pos[ 2 ] - 1 ].charAt(pos[ 3 ]) != 'g') {
				System.out.println("UP");
				return;
			}

			if (board[ pos[ 2 ] ].charAt(pos[ 3 ] - 1) != '#'
					&& board[ pos[ 2 ] ].charAt(pos[ 3 ] - 1) != 'r'
					&& board[ pos[ 2 ] ].charAt(pos[ 3 ] - 1) != 'g') {
				System.out.println("LEFT");
				return;
			}
			if (board[ pos[ 2 ] ].charAt(pos[ 3 ] + 1) != '#'
					&& board[ pos[ 2 ] ].charAt(pos[ 3 ] + 1) != 'r'
					&& board[ pos[ 2 ] ].charAt(pos[ 3 ] + 1) != 'g') {
				System.out.println("RIGHT");
				return;
			}
			System.out.println("DOWN");
			return;
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