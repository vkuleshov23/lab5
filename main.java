package prog.lab5;
import prog.lab5.matrixes.*;
import prog.lab5.excpt.*;
public class main{
	public static void main(String[] args){
		try {
			System.out.println("-------------------Default Matrix check-----------------------");

			int max = 199;

			Matrix a1 = new Matrix(6, 3);
			Matrix b = new Matrix(3, 4);
			for(int i = 0; i < 3; i++){
				b.setElement(i, i, 2);
				a1.setElement(i, i, 3);
			}
			b.setElement(2, 1, 5);
			b.setElement(0, 2, 4);
			b.setElement(0, 1, 6);
			a1.setElement(0, 1, 4);
			a1.setElement(3, 2, 7);
			a1.setElement(2, 0, 12);
			a1.setElement(5, 1, 13);
			System.out.println("a1:");
			System.out.println(a1);
			System.out.println("b:");
			System.out.println(b);
			
			Matrix a = a1.product(b);
			System.out.println("a = a1*b:");
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
			
			System.out.println("ab+ab:\n" + ab.sum(ab));
			
			ab.setElement(0, 5, 5);
			System.out.println("''with more size'' ab:\n" + ab);
			
			System.out.println("ab*a1:\n" + ab.product(a1));
			
			ab.remove(0, 5);
			ab.remove(5, 3);
			System.out.println("remove elements (0 5, 5 3, then shrinkToFit())");
			System.out.println("ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);
			ab.shrinkToFit();
			System.out.println("ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);
			
			System.out.println("ab*b:\n" + ab.product(b));
			
			ab.setElement(max, max, 1111);
			ab.starFill(max);
			System.out.println("ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);
			
			Matrix ac = new Matrix(max+1, max+1);
			ac.starFill(max);
			System.out.println("ac size: " + ac.getColumnSize() + " " + ac.getRowSize() + "\nac: \n" + ac);
			
			System.out.println("ab+ac:\n" + ab.sum(ac));
			
			System.out.println("ab+ac:\n" + ab.sum(ac));
			
			System.out.println("ab*ac:\n" + ab.product(ac));
			
			System.out.println("ac*ab:\n" + ac.product(ab));
			
			System.out.println("ac*ac:\n" + ac.product(ac));
			
			ac.fill(max);
			System.out.println("ac size: " + ac.getColumnSize() + " " + ac.getRowSize() + "\nac: \n" + ac);
			ab.removeAll();
			ab.fill(max);
			System.out.println("ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);

			ab.removeAll();
			ab.shrinkToFit();
			System.out.println("(removeAll && shrinkToFit)ab size: " + ab.getColumnSize() + " " + ab.getRowSize() + "\nab: \n" + ab);
			
			SparseMatrix vw = new SparseMatrix(1, 1);
			vw.setElement(0, 0, 3);
			System.out.println("vw:\n" + vw);
			System.out.println("vw+vw:\n" + vw.sum(vw));
			System.out.println("vw*vw:\n" + vw.product(vw));

			System.out.println("-------------------Square Matrix check-----------------------");
			
			Matrix w = new Matrix(3, 3);
			w.setElement(2, 1, 7);
			w.setElement(1, 2, 8);
			w.setElement(0, 0, 9);
			System.out.println("(UsualMatrix) w:\n" + w);

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

			Matrix v = new Matrix(1, 1);
			v.setElement(0, 0, 3);
			System.out.println("v:\n" + v);
			System.out.println("v+v:\n" + v.sum(v));
			System.out.println("v*v:\n" + v.product(v));


			System.out.println("-------------------Matrix Equals check-----------------------");
		

			System.out.println("a == b? : " + a.equals(b));
			System.out.println("a+c == c+a? : " + (c.sum(a)).equals(a.sum(c)));
			System.out.println("d == e? : " + d.equals(e));
			System.out.println("d == a? (different classes) : " + d.equals(a));

			// System.out.println("-------------------Exception check-----------------------");
			// System.out.println("c+b(must be error):\n");
			// c.sum(b);


		} catch(MatrixException err){
			err.getMassage();
			err.printStackTrace();
		} finally {
			System.out.println("\nIt is just a check how to \"finally\" working\n");
		}
	}
}