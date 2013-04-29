import java.util.*;

public class Solution {
	/* Head ends here */

	// chestie facuta just in case, ar putea fi stearsa.
	public class Pair {

		public int first, second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public String toString() {
			return "(" + first + "," + second + ")";
		}

	}

	/*
	 * aici vin taierile... imi lipsesc niste linii
	 */

	static int negamax_alfa_beta(String player, int[] pos, int alpha, int beta, String[] board,
			int s_layer, int f_layer) {
		String[] board2;
		int r_x = pos[0], r_y = pos[1], g_x = pos[2], g_y = pos[3];
		int[] pos2;
		char aux;
		Pair p;
		int score = 0; // Asta e ok sa fie lasat 0 initial ? sau il facem MIN/MAX_Int
		Vector<Integer> directionsX = new Vector<Integer>();
		Vector<Integer> directionsY = new Vector<Integer>();
		directionsX.add(0);
		directionsY.add(1);
		directionsX.add(1);
		directionsY.add(0);
		directionsX.add(0);
		directionsY.add(-1);
		directionsX.add(-1);
		directionsY.add(0);

		if (s_layer == f_layer) {
			if (player == "g") {
				// apelul functiei scor
				return BFS(player, pos, board);
			} else {
				// apelul functiei scor
				return -BFS(player, pos, board);
			}
		} else {
			String player2;
			for (int i = 0; i < 4; i++) {
				if (player == "g") {
					player2 = "r";
				} else {
					player2 = "g";
				}
				aux = getCharAtXY(board, pos[0] + directionsX.get(i), pos[1] + directionsY.get(i));
				if ((aux != 'r') && (aux != '#') && (aux != 'g')) {

					if (player == "g") {
						// marchez pentru verde directia pentru i actual.
						board2 = copyBoard(board);
						pos2 = copyPos(pos);
						// inverseaza rahatul din pos2 eventual

						board2[g_x + directionsX.get(i)] = board[r_x + directionsX.get(i)]
								.substring(0, r_y + directionsY.get(i))
								+ 'g'
								+ board[r_x + directionsX.get(i)].substring(r_y + 1
										+ directionsY.get(i));

					} else {
						board2 = copyBoard(board);
						pos2 = copyPos(pos);
						// inverseaza rahatul din pos2 eventual

						board2[g_x + directionsX.get(i)] = board[r_x + directionsX.get(i)]
								.substring(0, r_y + directionsY.get(i))
								+ 'r'
								+ board[r_x + directionsX.get(i)].substring(r_y + 1
										+ directionsY.get(i));
					}

					/*
					 * apeleaza negamex cu noul board adica board2 noul pos adica pos2
					 */
					score = -negamax_alfa_beta(player2, pos2, -alpha, -beta, board2, s_layer+1,
							f_layer);
					if (score >= beta)
						return beta;
				}
				// refac marcajul
				if (score > alpha) {
					alpha = score;
				}
			}
			return alpha;
		}
	}

	/*
	 * Culori pentru alg de BFS Probabil nu vm avea nevoie de toate
	 */
	public enum Color {
		ALB, GRI, NEGRU, RED, GREEN, WALL
	}

	public static class Graph {
		/*
		 * Un fel de pseudo graph defapt pt ca nu am perechi de <nod,lista de vecini> ci doar o
		 * lista de noduri care contine vecinii in el.
		 * 
		 * Face graful pe baza matricei de stringuri .
		 */
		ArrayList<Node> Nodes = new ArrayList<Node>();
		Node red, green; /* nodurile curente pt cei 2 playeri */

		public Graph(String player, int[] pos, String[] board) {
			int index = 0;

			/* Adauga pt fiecare nod in parte indecsii vecinilor lui */
			for (int i = 1; i < board.length - 1; i++)
				for (int j = 1; j < board[1].length() - 1; j++) {
					Node a = new Node(index, i, j);

					if (i == pos[0] && j == pos[1])
						red = a;
					if (i == pos[2] && j == pos[3])
						green = a;

					if (getCharAtXY(board, i - 1, j) == '-') {
						a.neighbours_by_index.add(index - (board[1].length() - 2));
					}
					if (getCharAtXY(board, i + 1, j) == '-') {
						a.neighbours_by_index.add(index + (board[1].length() - 2));
					}
					if (getCharAtXY(board, i, j - 1) == '-') {
						a.neighbours_by_index.add(index - 1);
					}
					if (getCharAtXY(board, i, j + 1) == '-') {
						a.neighbours_by_index.add(index + 1);
					}

					Nodes.add(a);
					index++;
				}
			/* Se foloseste de indecsii gasiti anterior ca sa adauge nodurile reale */
			for (Node n : Nodes) {
				for (int x : n.neighbours_by_index) {
					n.neighbours.add(Nodes.get(x));
				}
			}
		}

		public int nodeCount() {
			return Nodes.size();
		}

		public ArrayList<Node> getNodes() {
			return Nodes;
		}

		public String toString() {
			String res = "Graph\n";
			res += "Red= " + this.red.index + "\n";
			res += "Green= " + this.green.index + "\n";
			for (Node n : Nodes) {
				res += n.toString();
				res += "\n";
			}
			return res;
		}

	}

	/* Node class */
	public static class Node {
		int index;
		int x, y;
		ArrayList<Node> neighbours = new ArrayList<Node>();
		ArrayList<Integer> neighbours_by_index = new ArrayList<Integer>();

		Color culoare;

		public Node(int index, int x, int y) {
			this.index = index;
			culoare = Color.ALB;
			this.x = x;
			this.y = y;
		}

		public Node(int index, Color culoare) {
			this.index = index;
			this.culoare = culoare;
		}

		public int getIndex() {
			return index;
		}

		public Color getColor() {
			return culoare;
		}

		public void setColor(Color x) {
			this.culoare = x;
		}

		public ArrayList<Node> getNeighbours() {
			return neighbours;
		}

		public String toString() {
			String res = "Node index = ";
			res += Integer.toString(index) + " si culoare = " + culoare.toString() + "\n";
			res += "Coordonate: x=" + this.x + " y=" + this.y + " \n";
			res += "Vecini: ";

			for (int i = 0; i < neighbours.size(); i++) {
				res += Integer.toString(neighbours.get(i).index) + ", ";
			}
			return res;
		}

	}

	static void survival(String player, int[] pos, String[] board) {
		int r_x = pos[0], r_y = pos[1], g_x = pos[2], g_y = pos[3];
		int way = 0;
		char aux;
		if (player.equals("r")) {
			/* try to go after the wall. go kind of right */

			aux = getCharAtXY(board, r_x, r_y - 1);
			way = -1;
			// try left
			if (aux != '#' && aux != 'r' && aux != 'g')
				way = 3;
			else {
				aux = getCharAtXY(board, r_x - 1, r_y);
				if (aux != '#' && aux != 'r' && aux != 'g')
					way = 2;
				else {
					aux = getCharAtXY(board, r_x, r_y + 1);
					if (aux != '#' && aux != 'r' && aux != 'g')
						way = 1;
					else
						way = 0;
				}
			}
		} else {// player == 'g'
			way = -1;
			aux = getCharAtXY(board, g_x, g_y + 1);
			if (aux != '#' && aux != 'r' && aux != 'g')
				way = 1;
			else {
				aux = getCharAtXY(board, g_x + 1, g_y);
				if (aux != '#' && aux != 'r' && aux != 'g') {
					way = 0;
				} else {
					aux = getCharAtXY(board, g_x, g_y - 1);
					if (aux != '#' && aux != 'r' && aux != 'g')
						way = 3;
					else
						way = 2;
				}
			}
		}

		/* Print the best way to go */
		switch (way) {
		case 0:
			System.out.println("DOWN");
			break;
		case 1:
			System.out.println("RIGHT");
			break;
		case 2:
			System.out.println("UP");
			break;
		default:
			System.out.println("LEFT");
			break;
		}
	}

	static void Longest_way_bot(String player, int[] pos, String[] board) {
		int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0, i;
		int r_x = pos[0], r_y = pos[1], g_x = pos[2], g_y = pos[3];
		char aux;
		if (player.equals("r")) {
			/* Search Down */
			i = 1;
			aux = getCharAtXY(board, r_x + i, r_y);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter1++;
				i++;
				aux = getCharAtXY(board, r_x + i, r_y);
			}
			/* Search Up */
			i = 1;
			aux = getCharAtXY(board, r_x - i, r_y);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter2++;
				i++;
				aux = getCharAtXY(board, r_x - i, r_y);
			}
			/* Search Right */
			i = 1;
			aux = getCharAtXY(board, r_x, r_y + i);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter3++;
				i++;
				aux = getCharAtXY(board, r_x, r_y + i);
			}
			/* Search Left */
			i = 1;
			aux = getCharAtXY(board, r_x, r_y - i);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter4++;
				i++;
				aux = getCharAtXY(board, r_x, r_y - i);
			}

		} else {
			/* Search Down */
			i = 1;
			aux = getCharAtXY(board, g_x + i, g_y);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter1++;
				i++;
				aux = getCharAtXY(board, g_x + i, g_y);
			}
			/* Search Up */
			i = 1;
			aux = getCharAtXY(board, g_x - i, g_y);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter2++;
				i++;
				aux = getCharAtXY(board, g_x - i, g_y);
			}
			/* Search Right */
			i = 1;
			aux = getCharAtXY(board, g_x, g_y + i);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter3++;
				i++;
				aux = getCharAtXY(board, g_x, g_y + i);
			}
			/* Search Left */
			i = 1;
			aux = getCharAtXY(board, g_x, g_y - i);
			while (aux != '#' && aux != 'r' && aux != 'g') {
				counter4++;
				i++;
				aux = getCharAtXY(board, g_x, g_y - i);
			}

		}

		/* See whitch way is the best one */
		int way;
		if (counter1 >= counter2 && counter1 >= counter3 && counter1 >= counter4) {
			way = 0; /* Down */
		} else if (counter2 >= counter1 && counter2 >= counter3 && counter2 >= counter4)
			way = 1; /* Up */
		else if (counter3 >= counter2 && counter3 >= counter1 && counter3 >= counter4)
			way = 2; /* Right */
		else
			way = 3; /* Left */

		/* Print the best way to go */
		switch (way) {
		case 0:
			System.out.println("DOWN");
			break;
		case 1:
			System.out.println("UP");
			break;
		case 2:
			System.out.println("RIGHT");
			break;
		default:
			System.out.println("LEFT");
			break;
		}

	}

	static char getCharAtXY(String[] board, int x, int y) {
		return board[x].charAt(y);
	}

	static long getTime() {
		return System.currentTimeMillis();
	}

	static int BFS(String player, int[] pos, String[] board) {
		Graph g = new Graph(player, pos, board);
		/* noduri de inceput pt bfs */
		Node s1 = g.red;
		Node s2 = g.green;
		Node u1, u2;
		int arie1 = 1, arie2 = 1;

		/* incepem prelucrearea nodurilor deci culoare devine gri */

		s1.culoare = Color.GRI;
		s2.culoare = Color.GRI;
		/* Initializare cele 2 cozi */
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();
		q1.add(s1);
		q2.add(s2);

		while (!q1.isEmpty() && !q2.isEmpty()) {
			u1 = q1.poll();
			u2 = q2.poll();

			/* pentru toti vecinii */
			for (Node v : u1.neighbours) {
				if (v.culoare == Color.ALB) {
					v.culoare = Color.GRI;

					// debug
					// board[v.x] = board[v.x].substring(0, v.y) + 'R' + board[v.x].substring(v.y +
					// 1);
					// q1.add(v);

				}
			}
			arie1++;
			u1.culoare = Color.NEGRU;

			/* pentru toti vecinii */
			for (Node v : u2.neighbours) {
				if (v.culoare == Color.ALB) {
					v.culoare = Color.GRI;
					// debug
					// board[v.x] = board[v.x].substring(0, v.y) + 'G' + board[v.x].substring(v.y +
					// 1);
					// q2.add(v);
				}
			}
			arie2++;
			u2.culoare = Color.NEGRU;
		}

		/* daca se termina prematur q1 */
		if (q1.isEmpty()) {
			while (!q2.isEmpty()) {

				u2 = q2.poll();
				arie2++;
				/* pentru toti vecinii */
				for (Node v : u2.neighbours) {
					if (v.culoare == Color.ALB) {
						v.culoare = Color.GRI;
						// debug
						// board[v.x] = board[v.x].substring(0, v.y) + 'R'
						// + board[v.x].substring(v.y + 1);
						q2.add(v);
					}
				}

				u2.culoare = Color.NEGRU;
			}
		}
		if (q2.isEmpty()) {
			while (!q1.isEmpty()) {
				u1 = q1.poll();
				arie1++;
				/* pentru toti vecinii */
				for (Node v : u1.neighbours) {
					if (v.culoare == Color.ALB) {
						v.culoare = Color.GRI;
						// debug
						// board[v.x] = board[v.x].substring(0, v.y) + 'R'
						// + board[v.x].substring(v.y + 1);
						q1.add(v);
					}
				}
				u1.culoare = Color.NEGRU;
			}
		}
		if (player.contains("r"))
			return arie1 - 1 - arie2;
		else
			return arie2 - 1 - arie1;

	}

	/* Afiseaza board pt debug */
	static void printBoard(String[] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[1].length(); j++) {
				System.out.print(getCharAtXY(board, i, j));
			}
		}

	}

	/* Metoda care copiaza un board */
	static String[] copyBoard(String[] board) {
		String[] board2 = new String[board.length];
		for (int i = 0; i < board2.length; i++) {
			board2[i] = new String(board[i]);
		}
		return board2;
	}

	static int[] copyPos(int[] pos) {
		int[] res = new int[pos.length];

		for (int i = 0; i < pos.length; i++) {
			res[i] = pos[i];
		}

		return res;
	}

	/* Doar muta unde poate si apoi face BFS */
	static void BFS_Bot(String player, int[] pos, String[] board) {
		String[] board2;
		int[] pos2;
		int counter1 = Integer.MIN_VALUE, counter2 = Integer.MIN_VALUE, counter3 = Integer.MIN_VALUE, counter4 = Integer.MIN_VALUE;
		int r_x = pos[0], r_y = pos[1], g_x = pos[2], g_y = pos[3];
		char aux;
		if (player.contains("r")) {
			/* Search Down */
			aux = getCharAtXY(board, r_x + 1, r_y);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[0]++;
				board2[r_x + 1] = board[r_x + 1].substring(0, r_y) + 'r'
						+ board[r_x + 1].substring(r_y + 1);
				counter1 = BFS(player, pos2, board2);
			}
			/* Search Up */
			aux = getCharAtXY(board, r_x - 1, r_y);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[0]--;
				board2[r_x - 1] = board[r_x - 1].substring(0, r_y) + 'r'
						+ board[r_x - 1].substring(r_y + 1);
				counter2 = BFS(player, pos2, board2);
			}
			/* Search Right */
			aux = getCharAtXY(board, r_x, r_y + 1);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[1]++;
				board2[r_x] = board[r_x].substring(0, r_y + 1) + 'r'
						+ board[r_x].substring(r_y + 2);
				counter3 = BFS(player, pos2, board2);
			}
			/* Search Left */
			aux = getCharAtXY(board, r_x, r_y - 1);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[1]--;
				board2[r_x] = board[r_x].substring(0, r_y - 1) + 'r' + board[r_x].substring(r_y);
				counter4 = BFS(player, pos2, board2);

			}

		} else {
			/* Search Down */
			aux = getCharAtXY(board, g_x + 1, g_y);

			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[2]++;
				board2[g_x + 1] = board[g_x + 1].substring(0, g_y) + 'g'
						+ board[g_x + 1].substring(g_y + 1);
				counter1 = BFS(player, pos2, board2);

			}
			/* Search Up */
			aux = getCharAtXY(board, g_x - 1, g_y);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[2]--;
				board2[g_x - 1] = board[g_x - 1].substring(0, g_y) + 'g'
						+ board[g_x - 1].substring(g_y + 1);
				counter2 = BFS(player, pos2, board2);
			}
			/* Search Right */
			aux = getCharAtXY(board, g_x, g_y + 1);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[3]++;
				board2[g_x] = board[g_x].substring(0, g_y + 1) + 'g'
						+ board[g_x].substring(g_y + 2);
				counter3 = BFS(player, pos2, board2);
			}
			/* Search Left */
			aux = getCharAtXY(board, g_x, g_y - 1);
			if (aux != '#' && aux != 'r' && aux != 'g') {
				board2 = copyBoard(board);
				pos2 = copyPos(pos);
				pos2[3]--;
				board2[g_x] = board[g_x].substring(0, g_y - 1) + 'g' + board[g_x].substring(g_y);
				counter4 = BFS(player, pos2, board2);
			}
		}

		/* See whitch way is the best one */
		int way;
		if (counter1 >= counter2 && counter1 >= counter3 && counter1 >= counter4) {
			way = 0; /* Down */
		} else if (counter2 >= counter1 && counter2 >= counter3 && counter2 >= counter4)
			way = 1; /* Up */
		else if (counter3 >= counter2 && counter3 >= counter1 && counter3 >= counter4)
			way = 2; /* Right */
		else
			way = 3; /* Left */

		/* Print the best way to go */
		switch (way) {
		case 0:
			System.out.println("DOWN");
			break;
		case 1:
			System.out.println("UP");
			break;
		case 2:
			System.out.println("RIGHT");
			break;
		default:
			System.out.println("LEFT");
			break;
		}
	}

	static void nextMove(String player, int[] pos, String[] board) {
		BFS_Bot(player, pos, board);
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
			position[i] = Integer.parseInt(str_pos[i]);
		}
		pos = in.next();
		str_pos = pos.split(" ");
		for (int i = 0; i < 2; i++) {
			sizes[i] = Integer.parseInt(str_pos[i]);
		}

		String board[] = new String[sizes[0]];

		for (int i = 0; i < sizes[0]; i++) {
			board[i] = in.next();
		}

		nextMove(player, position, board);

	}
}