import java.util.Random;

public class main {
	
	public static int[][] maze;
	public static int N = 8;
	public static int mazeSize = N * N;
	public static int initialX = 0;
	public static int initialY = 0;
	public static int finalX = 7;
	public static int finalY = 7;
	private final static int[] H = {1, -1, 0,  0};
	private final static int[] V = {0,  0, 1, -1};
	private final static String[] S = {"->",  "<-", "|", "|"};
	
	public static void main(String[] args) {
		
		buildMaze();
		drawMaze();
		if (tryMove(initialX, initialY)) {
			System.out.println("SUCESSO!");
		} else {
			System.out.println("Falha, não há caminhos possíveis!");
		}
		drawMaze();
		
		
	}
	
	public static boolean tryMove(int currentX, int currentY){
		boolean done = (currentX == finalX && currentY == finalY);
		int move = 0;
		int newX, newY;
		
		while (!done && move < 4) {
			newX = currentX + H[move]; 
			newY = currentY + V[move];
			
			if (newX >= 0 && newX < N && newY >= 0 && newY < N && (maze[newX][newY] == 0 || maze[newX][newY] == 3)) {
				if (newX == finalX && newY == finalY) {
					done = true;
				} else {
					maze[newX][newY] = 1;
					
					//System.out.println("X: " + newX + " - " + "Y: " + newY);
					done = tryMove(newX, newY);
					
				//if (!done) {
				//	maze[newX][newY] = "   ";
				//}
				}
			}
			move++;
		}
		return done;	
	}
	
	public static void buildMaze(){
		
		maze = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (getRandomicBoolean())
					maze[i][j] = 8;
				else
					maze[i][j] = 0;
			}
		}
		
		maze[initialX][initialY] = 2;
		maze[finalX][finalY] = 3;
		
	}
	
	
	public static boolean getRandomicBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public static void drawMaze(){
		
		String drawableMaze[][] = new String[N][N];
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maze[i][j] == 2)
					drawableMaze[i][j] = "INI";
				else if (maze[i][j] == 0)
					drawableMaze[i][j] = "   ";
				else if (maze[i][j] == 8)
					drawableMaze[i][j] = "|||";
				else if (maze[i][j] == 1)
					drawableMaze[i][j] = " X ";
				else
					drawableMaze[i][j] = "END";
			}
		}
		
		System.out.println("::::::::::::::::::::::::::::");
        for (int i = 0; i < 8; i++) {
        	System.out.print("::");
            for (int j = 0; j < 8; j++) {
                System.out.print(drawableMaze[i][j]);
            }
            System.out.println("::");
        }
        System.out.println("::::::::::::::::::::::::::::");
		
	}

}
