package pa2자바실;

import java.util.Scanner;
import java.io.IOException;

public class runGame {
	static Board game;
	static boolean withFile;

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String str;

		while(true) {
			System.out.println("Game with file? [Y/N]");

			str = scan.nextLine();//이게 중요할 것 같음 다음입력값을 갱신하려면 이거를 board.java에서 계속해서 갱신해야하지않을까?
			if(str.equals("Y")) {
				withFile = true;
				break;
			} else if(str.equals("N")) {
				withFile = false;
				break;
			} else {
				System.out.println("Invalid command");
			}
		}

		game = new Board(withFile);
		
		while(!game.isFinish(withFile)) {
			game.printBoard(withFile);
			game.selectObject(withFile);
			game.moveObject(withFile);
		}
		scan.close();
	}
}


