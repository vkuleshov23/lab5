package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
import java.util.HashMap;

public class SparseMatrixHS implements IMatrix{
	protected static class Key {
		public int column = 0;
		public int row = 0;
		public Key(int column, int row) {
			this.column = column;
			this.row = row;
		}
		@Override
  		public int hashCode(){
     		return (column * row);
  		}
		@Override
  		public String toString(){
  			return ("" + column + ' ' + row + '\n');
  		}
		@Override
  		public boolean equals(Object obj) {
	    	if(obj instanceof Key){
    			return (this.toString().equals(obj.toString()));
    		} return false;
  		}
	}
	protected HashMap<Key, Integer> matrix;
	protected int rowSize = 0;
	protected int colSize = 0;

	public SparseMatrixHS(int col, int row) {
		this.matrix = new HashMap<>();
		this.rowSize = row;
		this.colSize = col;
	}
	@Override
	public final void setElement(int curColumn, int curRow, int value){
		if((curRow >= this.rowSize || curRow < 0) || (curColumn < 0 || curColumn >= this.colSize)){
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}
		Key key = new Key(curColumn, curRow);
		if(value == 0){
			this.matrix.remove(key);
			return;
		}
		this.matrix.put(key, value);
		return;
	}
	@Override
	public final int getElement(int curColumn, int curRow){
		if((curRow >= this.rowSize || curRow < 0) || (curColumn < 0 || curColumn >= this.colSize)){
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}	
			try{
				return matrix.get(new Key(curColumn, curRow));
			} catch(NullPointerException e){
				return 0;
			}
	}
	@Override
	public final int getRowSize(){
		return this.rowSize;
	}
	@Override
	public final int getColumnSize(){
		return this.colSize;
	}
	@Override
	public final SparseMatrixHS sum( IMatrix tmp ){
		if ((this.colSize == tmp.getColumnSize())&&(this.rowSize == tmp.getRowSize())){
			SparseMatrixHS cur = new SparseMatrixHS(this.getColumnSize(), this.getRowSize());
			for(int i = 0; i < this.getColumnSize(); i++){
				for(int j = 0; j < this.getRowSize(); j++){
					cur.setElement(i, j, this.getElement(i, j) + tmp.getElement(i, j));
				}
			}
			return cur;
		} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}
	@Override
	public final SparseMatrixHS product( IMatrix tmp){
		if (this.rowSize == tmp.getColumnSize()){
			SparseMatrixHS cur = new SparseMatrixHS(this.getColumnSize(), tmp.getRowSize());
			for(int i = 0; i < cur.getColumnSize(); i++){
				for(int j = 0; j < cur.getRowSize(); j++){
					int res = 0;
					for (int k = 0; k < this.getRowSize(); k++) {
						res += this.getElement(i, k) * tmp.getElement(k, j);
					}
					if(res != 0)
						cur.setElement(i, j, res);
				}
			}
			return cur;
		} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}
	public final void fill(int max){
		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= max; j++) {
				if(i != j){
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
	    if(tmp instanceof SparseMatrixHS)
			return ((this.toString()).equals(tmp.toString()));   
    	return false;
	}
}