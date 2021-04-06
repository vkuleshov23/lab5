package prog.lab5;
import prog.lab5.matrixes.*;
import prog.lab5.excpt.*;
public class main{
	public static void main(String[] args){
		try {
			System.out.println("-------------------Default Matrix check-----------------------");

			Matrix a = new Matrix(6, 3);
			Matrix b = new Matrix(3, 4);
			for(int i = 0; i < 3; i++){
				b.setElement(i, i, 2);
				a.setElement(i, i, 3);
			}
			b.setElement(2, 1, 5);
			b.setElement(0, 2, 4);
			b.setElement(0, 1, 6);
			a.setElement(0, 1, 4);
			a.setElement(3, 2, 7);
			a.setElement(2, 0, 12);
			a.setElement(5, 1, 13);

			System.out.println("a:");
			System.out.println(a);
			System.out.println("b:");
			System.out.println(b);
				 a = a.product(b);
			System.out.println("a = a*b:");
			System.out.println(a);
		
			Matrix c = new Matrix(6, 4);
			for(int i = 0, j = 2; i < 2; i++, j++){
				c.setElement(i, i, 2);
				c.setElement(j, i, 3);
				c.setElement(i, j, 5);
			}
			System.out.println("c:");
			System.out.println(c);
			System.out.println("c+a: \n" + c.sum(a));

		
			System.out.println("-------------------Sparse Matrix check-----------------------");
			SparseMatrix ab = new SparseMatrix();
			ab.setElement(4, 2, 42);
			ab.setElement(0, 1, 1);
			ab.setElement(2, 2, 22);
			ab.setElement(3, 2, 32);
			ab.setElement(1, 2, 12);
			ab.setElement(1, 0, 10);
			ab.setElement(5, 3, 53);
			System.out.println("ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);
			System.out.println("ab+(c+a):\n" + ab.sum(c.sum(a)));


			System.out.println("-------------------Square Matrix check-----------------------");
			
			Matrix w = new Matrix(3, 3);
			w.setElement(2, 1, 7);
			w.setElement(1, 2, 8);
			w.setElement(0, 0, 9);
			System.out.println("(UsualMatrix) w:\n"+w);

			SquareMatrix d = new SquareMatrix(3);
			d.setElement(2, 1, 5);
			d.setElement(0, 2, 4);
			d.setElement(0, 1, 6);
			System.out.println("d:");
			System.out.println(d);	
			SquareMatrix e = new SquareMatrix(3);
			e.setElement(0, 1, 2);
			e.setElement(1, 2, 4);
			e.setElement(0, 2, 7);
			System.out.println("e:");
			System.out.println(e);
			System.out.println("d+e:");
			System.out.println(d.sum(e));
			System.out.println("d*e:\n" + d.product(e));
			System.out.println("d*w:\n" + d.product(w));


			System.out.println("-------------------Matrix Equals check-----------------------");
		

			System.out.println("a == b? : " + a.equals(b));
			System.out.println("a+c == c+a? : " + (c.sum(a)).equals(a.sum(c)));
			System.out.println("d == e? : " + d.equals(e));
			System.out.println("d == a? (different classes) : " + d.equals(a));
			// System.out.println("-------------------Default Vector check-----------------------");
			// Vector f = new Vector();
			// for (int i = 0; i < 5; i++) {
			// 	f.pushBack(i+(i*2));
			// }
			// System.out.println(f);

			// Vector g = new Vector();
			// for (int i = 0; i < 6; i++) {
			// 	g.pushBack(i+((i-1)*3));
			// }
			// System.out.println(g);
			// System.out.println(g.sum(f));
			// System.out.println(f.sum(g));
			// System.out.println(g.productForVector(c));
			// System.out.println(g.productForMatrix(c.sum(a)));
			
			// System.out.println("-------------------Mirror Vector check-----------------------");
		
			// MirrorVector h = new MirrorVector();
			// for (int i = 0; i < 5; i++) {
			// 	h.pushBack(i+(i*5));
			// }
			// System.out.println("size: "+ h.empty() + ": " + h);

			// MirrorVector l = new MirrorVector();
			// for (int i = 0; i < 3; i++) {
			// 	l.pushBack(i);
			// }

			// System.out.println("get(5): "+ l.get(5));
			// System.out.println(l);

			// System.out.println(l.sum(h));
			// System.out.println(h.sum(l));
			// System.out.println("product: " + l.productForVector(c));
			// System.out.println("product: " + l.productForMatrix(c));

			System.out.println("-------------------Exception check-----------------------");
			System.out.println("c+b(must be error):\n");
			c.sum(b);


		} catch(MatrixException err){
			err.getMassage();
			err.printStackTrace();
		} finally {
			System.out.println("\nIt is just a check how to \"finally\" working\n");
		}
	}
}