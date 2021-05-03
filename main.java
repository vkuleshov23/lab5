package prog.lab5;
import prog.lab5.matrixes.*;
import prog.lab5.excpt.*;
public class main{
	public static void main(String[] args){
		try {
			System.out.println("-------------------Default Matrix check-----------------------");

			int max = 4;

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

			long start = System.currentTimeMillis();
			SparseMatrix t1 = new SparseMatrix(4, 6);
			t1.setElement(0, 1, 1);
			t1.setElement(3, 2, 2);
			t1.setElement(3, 5, 3);
			t1.setElement(1, 2, 6);
			t1.setElement(2, 3, 7);
			t1.setElement(0, 5, 9);
			// t1.fill(3);
			System.out.println("t1: \n" + t1);
			System.out.println("t1*a1: \n" + t1.product(a1));
			long end = System.currentTimeMillis();
			System.out.println("Passed " + (end - start) + " millis");

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


			System.out.println("-------------------Sparse Matrix HS check-----------------------");
			SparseMatrixHS u1 = new SparseMatrixHS(6, 4);
			u1.setElement(0,0,1);
			u1.setElement(0,1,2);
			u1.setElement(1,0,3);
			u1.setElement(1,2,12);
			u1.setElement(2,0,34);
			System.out.println("u1:\n" + u1);
			System.out.println("1, 1:  " + u1.getElement(1,1));
			System.out.println("0, 0:  " + u1.getElement(0,0));

			start = System.currentTimeMillis();
			
			SparseMatrixHS u2 = new SparseMatrixHS(4, 6);
			u2.setElement(0, 1, 1);
			u2.setElement(3, 2, 2);
			u2.setElement(3, 5, 3);
			u2.setElement(1, 2, 6);
			u2.setElement(2, 3, 7);
			u2.setElement(0, 5, 9);
			System.out.println("u2:\n" + u2);

			// System.out.println("u1+u2:\n" + u1.sum(u2));

			System.out.println("u1*u2:\n" + u2.product(a1));

			end = System.currentTimeMillis();
			System.out.println("Passed " + (end - start) + " millis");


			System.out.println("-------------------Matrix Equals check-----------------------");

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