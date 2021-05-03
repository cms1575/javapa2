package pa2자바실;

import java.util.HashMap;
import java.util.Scanner;

public class Board {
	Scanner sc= new Scanner(System.in);
	static int a=1;
	static int emptycount=90;
	static int origini;
	static int originj;
	static boolean whiteturn=true;
	HashMap<Integer, King> Kingmap = new HashMap<Integer, King>();
	HashMap<Integer, Queen> Queenmap = new HashMap<Integer,Queen>();
	HashMap<Integer, Knight> Knightmap = new HashMap<Integer, Knight>();
	HashMap<Integer, Pawn> Pawnmap = new HashMap<Integer, Pawn>();
	HashMap<Integer, Bishop> Bishopmap = new HashMap<Integer, Bishop>();
	HashMap<Integer, Rook> Rookmap = new HashMap<Integer, Rook>();
	HashMap<Integer, empty> emptymap = new HashMap<Integer, empty>();
	Board(boolean withFile) { //생성자 withFile의 참거짓유무가 중요할까?
		/* Your code */
		for(int i=0;i<8;i++) { 
			Pawn p=new Pawn(1,i,'P','b',' ');
			Pawnmap.put(i, p);
		}
		for(int i=0;i<8;i++) { 
			Pawn p=new Pawn(6,i,'P','w',' ');
			Pawnmap.put(i+8, p);
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<8;j++) {
				empty e=new empty(i+2,j,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
			}
		}
		King k=new King(0,4,'K','b',' ');
		Kingmap.put(0,k);
		King k1=new King(7,4,'K','w',' ');
		Kingmap.put(1,k1);
		
		Queen q=new Queen(0,3,'Q','b',' ');
		Queenmap.put(0, q);
		Queen q1=new Queen(7,3,'Q','w',' ');
		Queenmap.put(1, q1);
		
		Knight n=new Knight(0,1,'N','b',' ');
		Knightmap.put(0, n);
		n=new Knight(0,6,'N','b',' ');
		Knightmap.put(1, n);
		n=new Knight(7,1,'N','w',' ');
		Knightmap.put(2, n);
		n=new Knight(7,6,'N','w',' ');
		Knightmap.put(3, n);
		
		Rook r=new Rook(0,0,'R','b',' ');
		Rookmap.put(0, r);
		r=new Rook(0,7,'R','b',' ');
		Rookmap.put(1, r);
		r=new Rook(7,0,'R','w',' ');
		Rookmap.put(2, r);
		r=new Rook(7,7,'R','w',' ');
		Rookmap.put(3, r);
		
		Bishop b=new Bishop(0, 2, 'B', 'b', ' ');
		Bishopmap.put(0, b);
		b=new Bishop(0, 5, 'B', 'b', ' ');
		Bishopmap.put(1, b);
		b=new Bishop(7, 2, 'B', 'w', ' ');
		Bishopmap.put(2, b);
		b=new Bishop(7, 5, 'B', 'w', ' ');
		Bishopmap.put(3, b);
		
		
	}

	public boolean isFinish(boolean withFile) { // 끝났냐? 끝났으면 true 반환 안끝났으면 false반환, 입력도계속받아야할듯?맞나?
		/* Your code */
		if(Kingmap.get(1)==null) {
			System.out.println("Black Wins");
			return true;
		}else if(Kingmap.get(0)==null) {
			System.out.println("White Wins");
			return true;
		}
		return false;
	}
	
	public void selectObject(boolean withFile) {//  오브젝트선택, 선택후에 맞으면 *로 타겟 명시해야함
		/* Your code */
		String sob;
		int i,j;
		
		while(true) {
			
			System.out.print("Select piece: ");
			sob = sc.next();
			if(sob.contains("F")==true) {
				System.exit(0);
			}
			
			char a = sob.charAt(0);
			char b= sob.charAt(1);
			j=a-'a';
			i=8-(b-'0');
			if(findhim(i,j).contains("w")==true&&whiteturn==true) {
				whiteturn=false;
			}else if(findhim(i,j).contains("b")==true&&whiteturn==false) {
				whiteturn=true;
			}else {
				continue;
			}
			
			
			
			
			
			
			if(findhim(i, j).contains("   ")!=true) {
				for( Integer key : Pawnmap.keySet() ){
					if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) {//폰이움직일 수 있을때
						if(Pawnmap.get(key).getColor()=='w') {
							if(i-1>=0&&findhim(i-1,j).contains("   ")==true) {
								findhimandaddstar(i-1, j);
								if(j-1>=0&&findhim(i-1,j-1).contains("b")==true) {
									findhimandaddstar(i-1, j-1);
								}
								if(j+1<=7&&findhim(i-1,j+1).contains("b")==true) {
									findhimandaddstar(i-1, j+1);
								}
							}else {
								if(j-1>=0&&findhim(i-1,j-1).contains("b")==true) {
									findhimandaddstar(i-1, j-1);
								}
								if(j+1<=7&&findhim(i-1,j+1).contains("b")==true) {
									findhimandaddstar(i-1, j+1);
								}
								
							}
							
						}else if(Pawnmap.get(key).getColor()=='b') {
							if(i+1<=7&&findhim(i+1,j).contains("   ")==true) {
								findhimandaddstar(i+1, j);
								if(j-1>=0&&findhim(i+1,j-1).contains("w")==true) {
									findhimandaddstar(i+1, j-1);
								}
								if(j+1<=7&&findhim(i+1,j+1).contains("w")==true) {
									findhimandaddstar(i+1, j+1);
								}
							}else {
								if(j-1>=0&&findhim(i+1,j-1).contains("w")==true) {
									findhimandaddstar(i+1, j-1);
								}
								if(j+1<=7&&findhim(i+1,j+1).contains("w")==true) {
									findhimandaddstar(i+1, j+1);
								}
							}
						}
						
					}	
				
				
				}
			for( Integer key : Knightmap.keySet() ) {
				if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
					if(Knightmap.get(key).getColor()=='w') {
						if(((i-1>=0&&j-2>=0&&findhim(i-1,j-2).contains("b")==true))||(i-1>=0&&j-2>=0&&findhim(i-1,j-2).contains("   ")==true)) {
							findhimandaddstar(i-1, j-2);
						}
						if(((i-2>=0&&j-1>=0&&findhim(i-2,j-1).contains("b")==true))||(i-2>=0&&j-1>=0&&findhim(i-2,j-1).contains("   ")==true)) {
							findhimandaddstar(i-2, j-1);
						}
						if(((i-2>=0&&j+1<=7&&findhim(i-2,j+1).contains("b")==true))||(i-2>=0&&j+1<=7&&findhim(i-2,j+1).contains("   ")==true)) {
							findhimandaddstar(i-2, j+1);
						}
						if(((i-1>=0&&j+2<=7&&findhim(i-1,j+2).contains("b")==true))||(i-1>=0&&j+2<=7&&findhim(i-1,j+2).contains("   ")==true)) {
							findhimandaddstar(i-1, j+2);
						}
						if(((i+1<=7&&j-2>=0&&findhim(i+1,j-2).contains("b")==true))||(i+1<=7&&j-2>=0&&findhim(i+1,j-2).contains("   ")==true)) {
							findhimandaddstar(i+1, j-2);
						}
						if(((i+2<=7&&j-1>=0&&findhim(i+2,j-1).contains("b")==true))||(i+2<=7&&j-1>=0&&findhim(i+2,j-1).contains("   ")==true)) {
							findhimandaddstar(i+2, j-1);
						}
						if(((i+2<=7&&j+1<=7&&findhim(i+2,j+1).contains("b")==true))||(i+2<=7&&j+1<=7&&findhim(i+2,j+1).contains("   ")==true)) {
							findhimandaddstar(i+2, j+1);
						}
						if(((i+1<=7&&j+2<=7&&findhim(i+1,j+2).contains("b")==true))||(i+1<=7&&j+2<=7&&findhim(i+1,j+2).contains("   ")==true)) {
							findhimandaddstar(i+1, j+2);
						}
					}else {
						if(((i-1>=0&&j-2>=0&&findhim(i-1,j-2).contains("w")==true))||(i-1>=0&&j-2>=0&&findhim(i-1,j-2).contains("   ")==true)) {
							findhimandaddstar(i-1, j-2);
						}
						if(((i-2>=0&&j-1>=0&&findhim(i-2,j-1).contains("w")==true))||(i-2>=0&&j-1>=0&&findhim(i-2,j-1).contains("   ")==true)) {
							findhimandaddstar(i-2, j-1);
						}
						if(((i-2>=0&&j+1<=7&&findhim(i-2,j+1).contains("w")==true))||(i-2>=0&&j+1<=7&&findhim(i-2,j+1).contains("   ")==true)) {
							findhimandaddstar(i-2, j+1);
						}
						if(((i-1>=0&&j+2<=7&&findhim(i-1,j+2).contains("w")==true))||(i-1>=0&&j+2<=7&&findhim(i-1,j+2).contains("   ")==true)) {
							findhimandaddstar(i-1, j+2);
						}
						if(((i+1<=7&&j-2>=0&&findhim(i+1,j-2).contains("w")==true))||(i+1<=7&&j-2>=0&&findhim(i+1,j-2).contains("   ")==true)) {
							findhimandaddstar(i+1, j-2);
						}
						if(((i+2<=7&&j-1>=0&&findhim(i+2,j-1).contains("w")==true))||(i+2<=7&&j-1>=0&&findhim(i+2,j-1).contains("   ")==true)) {
							findhimandaddstar(i+2, j-1);
						}
						if(((i+2<=7&&j+1<=7&&findhim(i+2,j+1).contains("w")==true))||(i+2<=7&&j+1<=7&&findhim(i+2,j+1).contains("   ")==true)) {
							findhimandaddstar(i+2, j+1);
						}
						if(((i+1<=7&&j+2<=7&&findhim(i+1,j+2).contains("w")==true))||(i+1<=7&&j+2<=7&&findhim(i+1,j+2).contains("   ")==true)) {
							findhimandaddstar(i+1, j+2);
						}
					}
						
				}
			}
			for( Integer key : Kingmap.keySet() ){
				if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
					if(Kingmap.get(key).getColor()=='w') {
						if(findhim(i+1,j+1).contains("   ")==true||findhim(i+1,j+1).contains("b")==true) {
							findhimandaddstar(i+1, j+1);
						}
						if(findhim(i+1,j).contains("   ")==true||findhim(i+1,j).contains("b")==true) {
							findhimandaddstar(i+1, j);
						}
						if(findhim(i,j+1).contains("   ")==true||findhim(i,j+1).contains("b")==true) {
							findhimandaddstar(i, j+1);
						}
						if(findhim(i-1,j+1).contains("   ")==true||findhim(i-1,j+1).contains("b")==true) {
							findhimandaddstar(i-1, j+1);
						}
						if(findhim(i-1,j).contains("   ")==true||findhim(i-1,j).contains("b")==true) {
							findhimandaddstar(i-1, j);
						}
						if(findhim(i-1,j-1).contains("   ")==true||findhim(i-1,j-1).contains("b")==true) {
							findhimandaddstar(i-1, j-1);
						}
						if(findhim(i,j-1).contains("   ")==true||findhim(i,j-1).contains("b")==true) {
							findhimandaddstar(i, j-1);
						}
						if(findhim(i+1,j-1).contains("   ")==true||findhim(i+1,j-1).contains("b")==true) {
							findhimandaddstar(i+1, j-1);
						}
						
					}else {
						if(findhim(i+1,j+1).contains("   ")==true||findhim(i+1,j+1).contains("w")==true) {
							findhimandaddstar(i+1, j+1);
						}
						if(findhim(i+1,j).contains("   ")==true||findhim(i+1,j).contains("w")==true) {
							findhimandaddstar(i+1, j);
						}
						if(findhim(i,j+1).contains("   ")==true||findhim(i,j+1).contains("w")==true) {
							findhimandaddstar(i, j+1);
						}
						if(findhim(i-1,j+1).contains("   ")==true||findhim(i-1,j+1).contains("w")==true) {
							findhimandaddstar(i-1, j+1);
						}
						if(findhim(i-1,j).contains("   ")==true||findhim(i-1,j).contains("w")==true) {
							findhimandaddstar(i-1, j);
						}
						if(findhim(i-1,j-1).contains("   ")==true||findhim(i-1,j-1).contains("w")==true) {
							findhimandaddstar(i-1, j-1);
						}
						if(findhim(i,j-1).contains("   ")==true||findhim(i,j-1).contains("w")==true) {
							findhimandaddstar(i, j-1);
						}
						if(findhim(i+1,j-1).contains("   ")==true||findhim(i+1,j-1).contains("w")==true) {
							findhimandaddstar(i+1, j-1);
						}
					}
					
				}	
	        }
			for( Integer key : Queenmap.keySet() ){
				if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
					if(Queenmap.get(key).getColor()=='w') {
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7) {
								break;
							}
							if(findhim(i+a1, j).contains("w")==true) {
								
								break;
							}
							if(findhim(i+a1, j).contains("b")==true) {
								findhimandaddstar(i+a1, j);
								break;
							}
							if(findhim(i+a1,j).contains("   ")==true) {
								findhimandaddstar(i+a1, j);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7||j+a1>7) {
								break;
							}
							if(findhim(i+a1, j+a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i+a1, j+a1).contains("b")==true) {
								findhimandaddstar(i+a1, j+a1);
								break;
							}
							if(findhim(i+a1,j+a1).contains("   ")==true) {
								findhimandaddstar(i+a1, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7||j-a1<0) {
								break;
							}
							if(findhim(i+a1, j-a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i+a1, j-a1).contains("b")==true) {
								findhimandaddstar(i+a1, j-a1);
								break;
							}
							if(findhim(i+a1,j-a1).contains("   ")==true) {
								findhimandaddstar(i+a1, j-a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(j+a1>7) {
								break;
							}
							if(findhim(i, j+a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i, j+a1).contains("b")==true) {
								findhimandaddstar(i, j+a1);
								break;
							}
							if(findhim(i,j+a1).contains("   ")==true) {
								findhimandaddstar(i, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0||j+a1>7) {
								break;
							}
							if(findhim(i-a1, j+a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i-a1, j+a1).contains("b")==true) {
								findhimandaddstar(i-a1, j+a1);
								break;
							}
							if(findhim(i-a1,j+a1).contains("   ")==true) {
								findhimandaddstar(i-a1, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0) {
								break;
							}
							if(findhim(i-a1, j).contains("w")==true) {
								
								break;
							}
							if(findhim(i-a1, j).contains("b")==true) {
								findhimandaddstar(i-a1, j);
								break;
							}
							if(findhim(i-a1,j).contains("   ")==true) {
								findhimandaddstar(i-a1, j);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0||j-a1<0) {
								break;
							}
							if(findhim(i-a1, j-a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i-a1, j-a1).contains("b")==true) {
								findhimandaddstar(i-a1, j-a1);
								break;
							}
							if(findhim(i-a1,j-a1).contains("   ")==true) {
								findhimandaddstar(i-a1, j-a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(j-a1<0) {
								break;
							}
							if(findhim(i, j-a1).contains("w")==true) {
								
								break;
							}
							if(findhim(i, j-a1).contains("b")==true) {
								findhimandaddstar(i, j-a1);
								break;
							}
							if(findhim(i,j-a1).contains("   ")==true) {
								findhimandaddstar(i, j-a1);
							}
						}
					}else{
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7) {
								break;
							}
							if(findhim(i+a1, j).contains("b")==true) {
								
								break;
							}
							if(findhim(i+a1, j).contains("w")==true) {
								findhimandaddstar(i+a1, j);
								break;
							}
							if(findhim(i+a1,j).contains("   ")==true) {
								findhimandaddstar(i+a1, j);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7||j+a1>7) {
								break;
							}
							if(findhim(i+a1, j+a1).contains("b")==true) {
								
								break;
							}
							if(findhim(i+a1, j+a1).contains("w")==true) {
								findhimandaddstar(i+a1, j+a1);
								break;
							}
							if(findhim(i+a1,j+a1).contains("   ")==true) {
								findhimandaddstar(i+a1, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i+a1>7||j-a1<0) {
								break;
							}if(findhim(i+a1, j-a1).contains("b")==true) {
								
								break;
							}
							if(findhim(i+a1, j-a1).contains("w")==true) {
								findhimandaddstar(i+a1, j-a1);
								break;
							}
							if(findhim(i+a1,j-a1).contains("   ")==true) {
								findhimandaddstar(i+a1, j-a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(j+a1>7) {
								break;
							}
							if(findhim(i, j+a1).contains("b")==true) {
								
								break;
							}
							if(findhim(i, j+a1).contains("w")==true) {
								findhimandaddstar(i, j+a1);
								break;
							}
							if(findhim(i,j+a1).contains("   ")==true) {
								findhimandaddstar(i, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0||j+a1>7) {
								break;
							}
							if(findhim(i-a1, j+a1).contains("b")==true) {
								
								break;
							}
							if(findhim(i-a1, j+a1).contains("w")==true) {
								findhimandaddstar(i-a1, j+a1);
								break;
							}
							if(findhim(i-a1,j+a1).contains("   ")==true) {
								findhimandaddstar(i-a1, j+a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0) {
								break;
							}
							if(findhim(i-a1, j).contains("b")==true) {
								
								break;
							}
							if(findhim(i-a1, j).contains("w")==true) {
								findhimandaddstar(i-a1, j);
								break;
							}
							if(findhim(i-a1,j).contains("   ")==true) {
								findhimandaddstar(i-a1, j);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(i-a1<0||j-a1<0) {
								break;
							}
							if(findhim(i-a1, j-a1).contains("b")==true) {
								
								break;
							}
							if(findhim(i-a1, j-a1).contains("w")==true) {
								findhimandaddstar(i-a1, j-a1);
								break;
							}
							if(findhim(i-a1,j-a1).contains("   ")==true) {
								findhimandaddstar(i-a1, j-a1);
							}
						}
						for(int a1=1;a1<8;a1++) {
							if(j-a1<0) {
								break;
							}
							if(findhim(i, j-a1).contains("b")==true) {
							
								break;
							}
							if(findhim(i, j-a1).contains("w")==true) {
								findhimandaddstar(i, j-a1);
								break;
							}
							if(findhim(i,j-a1).contains("   ")==true) {
								findhimandaddstar(i, j-a1);
							}
						}
					
					
					
					
				}
	        }
			}
			for( Integer key : Rookmap.keySet() ){
				if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
					if(Rookmap.get(key).getColor()=='w') {
						for(int z=1;z<8;z++) {
							if(i+z>7) {
								break;
							}
							if(findhim(i+z, j).contains("w")==true) {
								
								break;
							}
							if(findhim(i+z, j).contains("b")==true) {
								findhimandaddstar(i+z, j);
								break;
							}
							if(findhim(i+z, j).contains("   ")==true) {
								findhimandaddstar(i+z, j);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0) {
								break;
							}
							if(findhim(i-z, j).contains("w")==true) {
								
								break;
							}
							if(findhim(i-z, j).contains("b")==true) {
								findhimandaddstar(i-z, j);
								break;
							}
							if(findhim(i-z, j).contains("   ")==true) {
								findhimandaddstar(i-z, j);
							}
						}
						for(int z=1;z<8;z++) {
							if(j-z<0) {
								break;
							}
							if(findhim(i, j-z).contains("w")==true) {
								
								break;
							}
							if(findhim(i, j-z).contains("b")==true) {
								findhimandaddstar(i, j-z);
								break;
							}
							if(findhim(i, j-z).contains("   ")==true) {
								findhimandaddstar(i, j-z);
							}
						}
						for(int z=1;z<8;z++) {
							if(j+z>7) {
								break;
							}
							if(findhim(i, j+z).contains("w")==true) {
								
								break;
							}
							if(findhim(i, j+z).contains("b")==true) {
								findhimandaddstar(i, j+z);
								break;
							}
							if(findhim(i, j+z).contains("   ")==true) {
								findhimandaddstar(i, j+z);
							}
						}
					}else {
						for(int z=1;z<8;z++) {
							if(i+z>7) {
								break;
							}
							if(findhim(i+z, j).contains("b")==true) {
								
								break;
							}
							if(findhim(i+z, j).contains("w")==true) {
								findhimandaddstar(i+z, j);
								break;
							}
							if(findhim(i+z, j).contains("   ")==true) {
								findhimandaddstar(i+z, j);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0) {
								break;
							}
							if(findhim(i-z, j).contains("b")==true) {
								
								break;
							}
							if(findhim(i-z, j).contains("w")==true) {
								findhimandaddstar(i-z, j);
								break;
							}
							if(findhim(i-z, j).contains("   ")==true) {
								findhimandaddstar(i-z, j);
							}
						}
						for(int z=1;z<8;z++) {
							if(j-z<0) {
								break;
							}
							if(findhim(i, j-z).contains("b")==true) {
								
								break;
							}
							if(findhim(i, j-z).contains("w")==true) {
								findhimandaddstar(i, j-z);
								break;
							}
							if(findhim(i, j-z).contains("   ")==true) {
								findhimandaddstar(i, j-z);
							}
						}
						for(int z=1;z<8;z++) {
							if(j+z>7) {
								break;
							}
							if(findhim(i, j+z).contains("b")==true) {
								
								break;
							}
							if(findhim(i, j+z).contains("w")==true) {
								findhimandaddstar(i, j+z);
								break;
							}
							if(findhim(i, j+z).contains("   ")==true) {
								findhimandaddstar(i, j+z);
							}
						}
					}
					
				}	
	        }
			for( Integer key : Bishopmap.keySet() ){
				if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
					if(Bishopmap.get(key).getColor()=='w') {
						for(int z=1;z<8;z++) {
							if(j+z>7||i+z>7) {
								break;
							}
							if(findhim(i+z, j+z).contains("w")==true) {
								
								break;
							}
							if(findhim(i+z, j+z).contains("b")==true) {
								findhimandaddstar(i+z, j+z);
								break;
							}
							if(findhim(i+z, j+z).contains("   ")==true) {
								findhimandaddstar(i+z, j+z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0||j+z>7) {
								break;
							}
							if(findhim(i-z, j+z).contains("w")==true) {
								
								break;
							}
							if(findhim(i-z, j+z).contains("b")==true) {
								findhimandaddstar(i-z, j+z);
								break;
							}
							if(findhim(i-z, j+z).contains("   ")==true) {
								findhimandaddstar(i-z, j+z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0||j-z<0) {
								break;
							}
							if(findhim(i-z, j-z).contains("w")==true) {
								
								break;
							}
							if(findhim(i-z, j-z).contains("b")==true) {
								findhimandaddstar(i-z, j-z);
								break;
							}
							if(findhim(i-z, j-z).contains("   ")==true) {
								findhimandaddstar(i-z, j-z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i+z>7||j-z<0) {
								break;
							}
							if(findhim(i+z, j-z).contains("w")==true) {
								
								break;
							}
							if(findhim(i+z, j-z).contains("b")==true) {
								findhimandaddstar(i+z, j-z);
								break;
							}
							if(findhim(i+z, j-z).contains("   ")==true) {
								findhimandaddstar(i+z, j-z);
							}
						}
					}else {
						for(int z=1;z<8;z++) {
							if(j+z>7||i+z>7) {
								break;
							}
							if(findhim(i+z, j+z).contains("b")==true) {
								//
								break;
							}
							if(findhim(i+z, j+z).contains("w")==true) {
								findhimandaddstar(i+z, j+z);
								break;
							}
							if(findhim(i+z, j+z).contains("   ")==true) {
								findhimandaddstar(i+z, j+z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0||j+z>7) {
								break;
							}
							if(findhim(i-z, j+z).contains("b")==true) {
								//
								break;
							}
							if(findhim(i-z, j+z).contains("w")==true) {
								findhimandaddstar(i-z, j+z);
								break;
							}
							if(findhim(i-z, j+z).contains("   ")==true) {
								findhimandaddstar(i-z, j+z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i-z<0||j-z<0) {
								break;
							}
							if(findhim(i-z, j-z).contains("b")==true) {
								//
								break;
							}
							if(findhim(i-z, j-z).contains("w")==true) {
								findhimandaddstar(i-z, j-z);
								break;
							}
							if(findhim(i-z, j-z).contains("   ")==true) {
								findhimandaddstar(i-z, j-z);
							}
						}
						for(int z=1;z<8;z++) {
							if(i+z>7||j-z<0) {
								break;
							}
							if(findhim(i+z, j-z).contains("b")==true) {
								//
								break;
							}
							if(findhim(i+z, j-z).contains("w")==true) {
								findhimandaddstar(i+z, j-z);
								break;
							}
							if(findhim(i+z, j-z).contains("   ")==true) {
								findhimandaddstar(i+z, j-z);
							}
						}
					}
					
				}	
	        }
			int mode=1;
			/////
			for(int l=0;l<8;l++) {
				for(int h=0;h<8;h++) {
					if(alreadystar(l, h)==true) {
						mode=0;
					}
				}
				
			}
			
			if(mode==1) {
				whiteturn=!whiteturn;
				continue;
			}
			
			
			origini=i; originj=j;
			String ANSI_RESET = "\033[0m";
			String ANSI_FG_BLACK = "\033[30m";
			String ANSI_FG_WHITE = "\033[37m";
			String ANSI_BG_BLACK = "\033[40m";
			String ANSI_BG_WHITE = "\033[47m";
			System.out.print("   a  b  c  d  e  f  g  h \n");
			String tmp;
			for(int i1 = 0; i1 < 8; i1++) {
				//r=row[8-i];
				System.out.print(8-i1 + " ");
				for (int j1 = 0; j1 < 8; j1++) {
					tmp=findhim(i1, j1);
					//c=column[j]; // 따라서 a1 이라 할때 c r 이 되는거다  c는 알파벳
					if(isBlackSquare(i1, j1)) {
						// Black background, white character
						System.out.print(ANSI_BG_BLACK + ANSI_FG_WHITE
								+tmp + ANSI_RESET + ANSI_RESET);
					} else {
						/// White background, black character
						System.out.print(ANSI_BG_WHITE + ANSI_FG_BLACK
								+ tmp
								+ ANSI_RESET + ANSI_RESET);
					}
				}
				System.out.print("\n");
			}
			break;
			////////
				
			}else {
				//System.out.println("Wrong input"); // 공백선택했을때
				continue;
				}
			
			
			}
		
		
	}
	
	public void moveObject(boolean withFile) {// 오브젝트 움직이기, 적 없애면 제거해야함
		/* Your code */
		while(true) {
			String sob;
			int i,j;
			System.out.print("Move piece: ");
			sob = sc.next();
			char a = sob.charAt(0);
			char b= sob.charAt(1);
			j=a-'a';
			i=8-(b-'0');
			if(alreadystar(i, j)==true) {
				if(isempty(i, j)==true) {
					killhim(i, j); //System.out.println("빈공간으로 이동");
					movetrace(origini, originj, i, j);
					
					break;
				}else {
					killhim(i, j); //System.out.println("차있는 공간으로 이동 상대방 말 없애기");
					movetrace(origini, originj, i, j);
					
					break;
				}
				
				
			}else {
				//System.out.print("not starpos");
				continue;
			}
			
			
			
		}
		for(int q=0;q<8;q++) {
			for(int w=0;w<8;w++) {
				starreset(q, w);
			}
		}
		
		}
	public void movetrace(int oi, int oj, int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==oi&&Pawnmap.get(key).getY()==oj) {
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Pawnmap.get(key).getColor()=='w') {
					Pawn p=new Pawn(i,j,'P','w',' ');
					//Pawnmap.remove(key);
					Pawnmap.put(key, p);
				}else {
					Pawn p=new Pawn(i,j,'P','b',' ');
					//Pawnmap.remove(key);
					Pawnmap.put(key, p);
				}
				
				
				
			}	
	    }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==oi&&Knightmap.get(key).getY()==oj) {
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Knightmap.get(key).getColor()=='w') {
					Knight p=new Knight(i,j,'N','w',' ');
					Knightmap.put(key, p);
				}else {
					Knight p=new Knight(i,j,'N','b',' ');
					Knightmap.put(key, p);
				}
				
			}	
	    }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==oi&&Kingmap.get(key).getY()==oj) {
				
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Kingmap.get(key).getColor()=='w') {
					King p=new King(i,j,'K','w',' ');
					Kingmap.put(key, p);
				}else {
					King p=new King(i,j,'K','b',' ');
					Kingmap.put(key, p);
				}
			}	
	    }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==oi&&Queenmap.get(key).getY()==oj) {
				
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Queenmap.get(key).getColor()=='w') {
					Queen p=new Queen(i,j,'Q','w',' ');
					Queenmap.put(key, p);
				}else {
					Queen p=new Queen(i,j,'Q','b',' ');
					Queenmap.put(key, p);
				}
			}	
	    }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==oi&&Rookmap.get(key).getY()==oj) {
				
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Rookmap.get(key).getColor()=='w') {
					Rook p=new Rook(i,j,'R','w',' ');
					Rookmap.put(key, p);
				}else {
					Rook p=new Rook(i,j,'R','b',' ');
					Rookmap.put(key, p);
				}
				
			}	
	    }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==oi&&Bishopmap.get(key).getY()==oj) {
				
				empty e=new empty(oi,oj,' ',' ',' ');
				emptymap.put(emptycount, e);
				emptycount++;
				if(Bishopmap.get(key).getColor()=='w') {
					Bishop p=new Bishop(i,j,'B','w',' ');
					Bishopmap.put(key, p);
				}else {
					Bishop p=new Bishop(i,j,'B','b',' ');
					Bishopmap.put(key, p);
				}
				
			}	
	    }
		
	}

	public boolean isBlackSquare(int i, int j) {
		if(i%2==0&&j%2==1) {
			return true;
		}else if(i%2==0&&j%2==0) {
			return false;
		}else if(i%2==1&&j%2==0) {
			return true;
		}else{
			return false;
		}
		
	}
	public void killhim(int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) {
				
				Pawnmap.remove(key);
				break;
				
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				
				Knightmap.remove(key);
				break;
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				
				Kingmap.remove(key);
				break;
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				
				Queenmap.remove(key);
				break;
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				
				Rookmap.remove(key);
				break;
				
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				
				Bishopmap.remove(key);
				break;
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				//System.out.println("test");
				emptymap.remove(key); break;
				//System.out.println("test");
			}	
        }
		
		
	}
	public void starreset(int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) {
				
				Pawnmap.get(key).setTarget(' ');
				
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				
				Knightmap.get(key).setTarget(' ');
				
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				
				Kingmap.get(key).setTarget(' ');
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				
				Queenmap.get(key).setTarget(' ');;
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				
				Rookmap.get(key).setTarget(' ');
				
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				
				Bishopmap.get(key).setTarget(' ');
				
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				
				emptymap.get(key).setTarget(' ');
				
			}	
        }
		
		
	}
	public boolean isempty(int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) { 
				if(Pawnmap.get(key).getType()==' ') {
					return true;
				}
				else return false;//System.out.println(Pawnmap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				if(Knightmap.get(key).getType()==' ') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				if(Kingmap.get(key).getType()==' ') {
					return true;
				}
				else return false;
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				if(Queenmap.get(key).getType()==' ') {
					return true;
				}
				else return false;
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				if(Rookmap.get(key).getType()==' ') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				if(Bishopmap.get(key).getType()==' ') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				if(emptymap.get(key).getType()==' ') {
					return true;
				}
				else return false;
				//System.out.println(emptymap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		return false;
	}
	
	public boolean alreadystar(int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) { 
				if(Pawnmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;//System.out.println(Pawnmap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				if(Knightmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				if(Kingmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				if(Queenmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				if(Rookmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				if(Bishopmap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
				
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				if(emptymap.get(key).getTarget()=='*') {
					return true;
				}
				else return false;
				//System.out.println(emptymap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		return false;
	}
	public void findhimandaddstar(int i, int j) {
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) { 
				Pawnmap.get(key).setTarget('*');
				//System.out.println(Pawnmap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				Knightmap.get(key).setTarget('*');
				
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				
				Kingmap.get(key).setTarget('*');
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				
				Queenmap.get(key).setTarget('*');
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				Rookmap.get(key).setTarget('*');
				
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				Bishopmap.get(key).setTarget('*');
				
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				emptymap.get(key).setTarget('*');
				//System.out.println(emptymap.get(key).getTarget()); ////////////////xptmxmtest
			}	
        }
		
	}
	public String findhim(int i, int j) {
		String result = "ERR";
		for( Integer key : Pawnmap.keySet() ){
			if(Pawnmap.get(key).getX()==i&&Pawnmap.get(key).getY()==j) {
				result=""+Pawnmap.get(key).getColor()+Pawnmap.get(key).getType()+Pawnmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : Knightmap.keySet() ){
			if(Knightmap.get(key).getX()==i&&Knightmap.get(key).getY()==j) {
				result=""+Knightmap.get(key).getColor()+Knightmap.get(key).getType()+Knightmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : Kingmap.keySet() ){
			if(Kingmap.get(key).getX()==i&&Kingmap.get(key).getY()==j) {
				result=""+Kingmap.get(key).getColor()+Kingmap.get(key).getType()+Kingmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : Queenmap.keySet() ){
			if(Queenmap.get(key).getX()==i&&Queenmap.get(key).getY()==j) {
				result=""+Queenmap.get(key).getColor()+Queenmap.get(key).getType()+Queenmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : Rookmap.keySet() ){
			if(Rookmap.get(key).getX()==i&&Rookmap.get(key).getY()==j) {
				result=""+Rookmap.get(key).getColor()+Rookmap.get(key).getType()+Rookmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : Bishopmap.keySet() ){
			if(Bishopmap.get(key).getX()==i&&Bishopmap.get(key).getY()==j) {
				result=""+Bishopmap.get(key).getColor()+Bishopmap.get(key).getType()+Bishopmap.get(key).getTarget()+"";
				return result;
			}	
        }
		for( Integer key : emptymap.keySet() ){
			if(emptymap.get(key).getX()==i&&emptymap.get(key).getY()==j) {
				result=""+emptymap.get(key).getColor()+emptymap.get(key).getType()+emptymap.get(key).getTarget()+"";
				return result;
			}	
        }
		return result;
	}
	public void printmap() {
		 for(Integer key : Pawnmap.keySet()){
			 
	            int valuex = Pawnmap.get(key).getX();
	            int valuey = Pawnmap.get(key).getY();
	            System.out.println(key+" (Pawn): "+valuex+", "+valuey);
	 
	        }
	}
	
	public void printBoard(boolean withFile) {
		/* Your code */
		//String[] column= {"a","b","c","d","e","f","g","h"};
		//String[] row= {"1","2","3","4","5","6","7","8"};
		//String c,r;
		String ANSI_RESET = "\033[0m";
		String ANSI_FG_BLACK = "\033[30m";
		String ANSI_FG_WHITE = "\033[37m";
		String ANSI_BG_BLACK = "\033[40m";
		String ANSI_BG_WHITE = "\033[47m";
		System.out.print("   a  b  c  d  e  f  g  h \n");
		String tmp;
		for(int i = 0; i < 8; i++) {
			//r=row[8-i];
			System.out.print(8-i + " ");
			for (int j = 0; j < 8; j++) {
				tmp=findhim(i, j);
				//c=column[j]; // 따라서 a1 이라 할때 c r 이 되는거다  c는 알파벳
				if(isBlackSquare(i, j)) {
					// Black background, white character
					System.out.print(ANSI_BG_BLACK + ANSI_FG_WHITE
							+tmp + ANSI_RESET + ANSI_RESET);
				} else {
					/// White background, black character
					System.out.print(ANSI_BG_WHITE + ANSI_FG_BLACK
							+ tmp
							+ ANSI_RESET + ANSI_RESET);
				}
			}
			System.out.print("\n");
		}
		
	}
}
