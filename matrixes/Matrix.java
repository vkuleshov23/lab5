package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
public class Matrix implements IMatrix{
	protected int col_size = 0;
	protected int row_size = 0;
	protected int[][] matrix;

	public Matrix(int column, int row) { 
		this.col_size = column;
		this.row_size = row;
		this.matrix = new int[col_size][row_size];
	}
	@ Override
	public final void setElement(int column, int row, int value){ //add exception
		if((row < this.row_size && column < this.col_size) && (row >= 0 && column >= 0)){
			this.matrix[column][row] = value;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e;}
	}
	public final void addElement(int column, int row, int value){ //add exception
		if((row < this.row_size && column < this.col_size) && (row >= 0 && column >= 0)){
			this.matrix[column][row] += value;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }	
	}
	@ Override
	public final int getElement(int column, int row){ //add exception
		if((row < this.row_size && row >= 0) && (column >= 0 && column < this.col_size)){
			return this.matrix[column][row];
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	@ Override
	public final int getColumnSize(){
		return this.col_size;
	}
	@ Override
	public final int getRowSize(){
		return this.row_size;
	}
	public final int[] getColumn(int n){
		if (n < this.row_size && n >= 0){
			int[] tmp = new int[this.col_size];
			for (int i = 0; i < this.col_size; i++) {
				tmp[i] = this.matrix[i][n];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}
	protected final int[] getRow(int n){
		if (n < this.col_size && n >= 0){
			int[] tmp = new int[this.row_size];
			for (int i = 0; i < this.row_size; i++) {
				tmp[i] = this.matrix[n][i];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}
	@ Override
	public Matrix sum(IMatrix tmp){	
		if((tmp instanceof SquareMatrix) || (tmp instanceof Matrix)){
			if ((this.col_size == tmp.getColumnSize())&&(this.row_size == tmp.getRowSize())){
				Matrix cur = new Matrix(this.col_size, this.row_size);
				for(int i = 0; i < this.col_size; i++){
					for(int j = 0; j < this.row_size; j++){
						cur.matrix[i][j] = this.matrix[i][j] + tmp.getElement(i, j);
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
		} else { MatrixException e = new MatrixException("There is not SquareMatrix or Matrix"); throw e; }
	}
	@ Override
	public Matrix product(IMatrix tmp){
		if((tmp instanceof SquareMatrix) || (tmp instanceof Matrix)){
			if (this.row_size == tmp.getColumnSize()){
				Matrix cur = new Matrix(this.col_size, tmp.getRowSize());
				for(int i = 0; i < cur.col_size; i++){
					for(int j = 0; j < cur.row_size; j++){
						for (int k = 0; k < this.row_size; k++) {
							cur.matrix[i][j] += (this.matrix[i][k] * tmp.getElement(k, j));
						}
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
		} else { MatrixException e = new MatrixException("There is not SquareMatrix or Matrix"); throw e; }
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
