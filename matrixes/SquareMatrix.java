package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
public class SquareMatrix extends Matrix implements IMatrix{
	private int size = 0;
	public SquareMatrix(int n){
		super(n, n);
		this.size = n;
		for(int i =0; i < n; i++){
			matrix[i][i] = 1;
		}
	}
	private SquareMatrix(int n, int t){
		super(n,n);
		this.size = n;
	}
	public final int getSize(){
		return size;
	}
	@ Override
	public SquareMatrix sum( IMatrix tmp){ //add exception err
		if((tmp instanceof SquareMatrix) || (tmp instanceof Matrix)) {
			if ((this.col_size == tmp.getColumnSize())&&(this.row_size == tmp.getRowSize())){
				SquareMatrix cur = new SquareMatrix(this.size, 0);
				for(int i = 0; i < this.getSize(); i++){
					for(int j = 0; j < this.getSize(); j++){
						cur.matrix[i][j] = this.matrix[i][j] + tmp.getElement(i, j);
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
		} else { MatrixException e = new MatrixException("There is not SquareMatrix or Matrix"); throw e; }
	}
	@ Override
	public SquareMatrix product( IMatrix tmp){	
		if((tmp instanceof SquareMatrix) || (tmp instanceof Matrix)){
			if (this.row_size == tmp.getColumnSize()){
				SquareMatrix cur = new SquareMatrix(this.size, 0);
				for(int i = 0; i < this.getColumnSize(); i++){
					for(int j = 0; j < this.getRowSize(); j++){
						for (int k = 0; k < this.getRowSize(); k++) {
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
		for(int i = 0; i < this.getSize(); i++){
			for(int j = 0; j < this.getSize(); j++){
				str.append(this.getElement(i,j));
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString(); 
	}
	@ Override
	public boolean equals(Object tmp) {
	    if(tmp instanceof SquareMatrix)
			return ((this.toString()).equals(tmp.toString()));   
    	return false;
	}
}