package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
public class Matrix implements IMatrix{
	protected int colSize = 0;
	protected int rowSize = 0;
	protected int[][] matrix;

	public Matrix(int column, int row) { 
		this.colSize = column;
		this.rowSize = row;
		this.matrix = new int[colSize][rowSize];
	}
	@ Override
	public final void setElement(int column, int row, int value){
		if((row < this.rowSize && row >= 0) || (column >= 0 && column < this.colSize)){
			this.matrix[column][row] = value;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e;}
	}
	@ Override
	public final int getElement(int column, int row){
		if((row < this.rowSize && row >= 0) || (column >= 0 && column < this.colSize)){
			return this.matrix[column][row];
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	@ Override
	public final int getColumnSize(){
		return this.colSize;
	}
	@ Override
	public final int getRowSize(){
		return this.rowSize;
	}
	public final int[] getColumn(int row){
		if (row < this.rowSize && row >= 0){
			int[] tmp = new int[this.colSize];
			for (int i = 0; i < this.colSize; i++) {
				tmp[i] = this.matrix[i][row];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}
	public final int[] getRow(int column){
		if (column < this.colSize && column >= 0){
			int[] tmp = new int[this.rowSize];
			for (int j = 0; j < this.rowSize; j++) {
				tmp[j] = this.matrix[column][j];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}
	@ Override
	public Matrix sum(IMatrix tmp){	
			if ((this.colSize == tmp.getColumnSize())&&(this.rowSize == tmp.getRowSize())){
				Matrix cur = new Matrix(this.colSize, this.rowSize);
				for(int i = 0; i < this.colSize; i++){
					for(int j = 0; j < this.rowSize; j++){
						cur.matrix[i][j] = this.matrix[i][j] + tmp.getElement(i, j);
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}
	@ Override
	public Matrix product(IMatrix tmp){
			if (this.rowSize == tmp.getColumnSize()){
				Matrix cur = new Matrix(this.colSize, tmp.getRowSize());
				for(int i = 0; i < cur.colSize; i++){
					for(int j = 0; j < cur.rowSize; j++){
						for (int k = 0; k < this.rowSize; k++) {
							cur.matrix[i][j] += (this.matrix[i][k] * tmp.getElement(k, j));
						}
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}
	public final void starFill(int max){
		for(int i = 0; i < max; i++){
			//--------------------------diagonals----------------------------------------//
			this.setElement(i, i, (int)(Math.random()* 9) + 11);
			this.setElement((i/2), (i/3), (int)(Math.random()* 9) + 11);
			this.setElement((i/3), (i/2), (int)(Math.random()* 9) + 11);
			this.setElement((i/4), (i/5), (int)(Math.random()* 9) + 11);
			this.setElement((i/5), (i/4), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/2), max-(i/3), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/3), max-(i/2), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/4), max-(i/5), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/5), max-(i/4), (int)(Math.random()* 9) + 11);
			this.setElement(max-i, i, (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/2), (i/3), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/3), (i/2), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/4), (i/5), (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/5), (i/4), (int)(Math.random()* 9) + 11);
			this.setElement((i/2), max-(i/3), (int)(Math.random()* 9) + 11);
			this.setElement((i/3), max-(i/2), (int)(Math.random()* 9) + 11);
			this.setElement((i/4), max-(i/5), (int)(Math.random()* 9) + 11);
			this.setElement((i/5), max-(i/4), (int)(Math.random()* 9) + 11);

			//------------------------------top-----------------------------------------//

			this.setElement((i/2), max/2 -(i/2), (int)(Math.random()* 9) + 11);
			this.setElement((i/2), max/2 +(i/2), (int)(Math.random()* 9) + 11);
			this.setElement(i, max/2 , (int)(Math.random()* 9) + 11);

			this.setElement((i/2), max/2 -(i/3), (int)(Math.random()* 9) + 11);
			this.setElement((i/2), max/2 +(i/3), (int)(Math.random()* 9) + 11);

			this.setElement((i/2), max/2 -(i/4), (int)(Math.random()* 9) + 11);
			this.setElement((i/2), max/2 -(i/4), (int)(Math.random()* 9) + 11);

			// this.setElement(max/2-(i/2), max/2 -(i/2), (int)(Math.random()* 9) + 11);
			// this.setElement(max/2-(i/2), max/2 +(i/2), (int)(Math.random()* 9) + 11);

			//-----------------------------bot------------------------------------------------//

			this.setElement(max/2+(i/2), (i/2) , (int)(Math.random()* 9) + 11);
			this.setElement(max/2+(i/2), max-(i/2) , (int)(Math.random()* 9) + 11);
			this.setElement(max/2, i , (int)(Math.random()* 9) + 11);

			this.setElement(max-(i/2), max/2 - (i/2) , (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/2), max/2+(i/2) , (int)(Math.random()* 9) + 11);
			
			this.setElement(max-(i/2), max/2 + (i/3) , (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/2), max/2 - (i/3) , (int)(Math.random()* 9) + 11);

			this.setElement(max-(i/2), max/2 + (i/4) , (int)(Math.random()* 9) + 11);
			this.setElement(max-(i/2), max/2 - (i/4) , (int)(Math.random()* 9) + 11);

			
		}
	}
	public final void fill(int max){
		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= max; j++) {
				if(i == j){
					this.setElement(i, j, 0);				
				} else {
					this.setElement(i, j, (int)(Math.random()* 9) + 1);
				}
			}
		}
	}
	@ Override
	public String toString(){ 
		StringBuilder str = new	StringBuilder();	
		for(int i = 0; i < this.getColumnSize(); i++){
			for(int j = 0; j < this.getRowSize(); j++){
				str.append(this.getElement(i,j));
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString(); 
	}
	@ Override
	public boolean equals(Object tmp) {
	    if(tmp instanceof Matrix)
			return ((this.toString()).equals(tmp.toString()));   
    	return false;
	}
}
