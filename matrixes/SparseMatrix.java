package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class SparseMatrix implements IMatrix{
	
	protected static class SMEl {
		public int value = 0;
		public int column = 0;
		public int row = 0;

		public SMEl(int column, int row, int value) {
			this.column = column;
			this.row = row;
			this.value = value;
		}
		public final String toString(){
			return "" + this.value;
		}
	}

	protected LinkedList<SMEl> matrix;
	protected int rowSize = 0;
	protected int colSize = 0;
	  
	// public SparseMatrix() {
	// 	this.matrix = new LinkedList<SMEl>();
	// }
	public SparseMatrix(int col, int row) {
		this.matrix = new LinkedList<SMEl>();
		this.rowSize = row;
		this.colSize = col;
	}
	@Override
	public final void setElement(int curColumn, int curRow, int curValue){
		if((curRow >= this.rowSize || curRow < 0) || (curColumn < 0 || curColumn >= this.colSize)){
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}
		if(curValue == 0){
			this.remove(curColumn, curRow);
			// if(this.rowSize < curRow+1){
	  //       	this.rowSize = curRow+1;
	  //   	}
	  //   	if(this.colSize < curColumn+1){
	  //      		this.colSize = curColumn+1;
	  //      	}
			return;
		}
		SMEl cur = new SMEl(curColumn, curRow, curValue);
	    ListIterator<SMEl> iter = this.matrix.listIterator();
	    while (iter.hasNext()) {
	      SMEl element = iter.next();
	        if(cur.value == element.value && cur.row == element.row && cur.column == element.column){ //if such en element is sibling
	        	return;
	        } else if(cur.value != element.value && cur.row == element.row && cur.column == element.column){ //if such position exsist
	        	iter.set(cur);
	        	return;
	        } else if(cur.row < element.row && cur.column == element.column){ // if no such position in this row, but there have higher row position
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	return;
	        } else if(cur.column < element.column){ // if no such position in this row, but there have not higher row position,
	        	iter.previous();                    // but have higher column position (last element of cur row)
	        	iter.add(cur);
	        	iter.next();
	        	if(this.rowSize < curRow+1){
	        		this.rowSize = curRow+1;
	    		}
				return;
	        }
	    }
	    this.matrix.addLast(cur);
		// if(this.rowSize < curRow+1){
	 //    	this.rowSize = curRow+1;
	 //    }
	 //    if(this.colSize < curColumn+1){
	 //   		this.colSize = curColumn+1;
	 //    }
		return;
	}
	@ Override
	public int getElement(int column, int row){
		if((row < this.rowSize && row >= 0) || (column >= 0 && column < this.colSize)){
			for(SMEl element : this.matrix){
				if(element.column > column){
					return 0;
				} else if(element.column == column){
					if(element.row == row){
						return element.value;
					} else if(element.row > row){
						return 0;
					}
				}
			}
			return 0;			
		} else {
			MatrixException e = new MatrixException("Memory access error"); 
			throw e;
		}
	}
	@ Override
	public final int getColumnSize(){
		return this.colSize;
	}
	@ Override
	public final int getRowSize(){
		return this.rowSize;
	}
	@ Override
	public final SparseMatrix sum(IMatrix tmp){
		if((this.colSize == tmp.getColumnSize()) && (this.rowSize == tmp.getRowSize())){
			SparseMatrix cur = new SparseMatrix(this.colSize, this.rowSize );
			Iterator<SMEl> iter = this.matrix.iterator();
	    	SMEl element = new SMEl(-1, -1, 0);
	    	if(iter.hasNext()){
	    		element = iter.next();	    			
	    	}
	    	for (int i = 0; i < this.colSize; i++) {
	    		for (int j = 0; j < this.rowSize; j++) {
	    			if(i == element.column && j == element.row){ //finding elements != 0
	    				cur.setElement(i, j, (tmp.getElement(i, j) + element.value) );
	    				if(iter.hasNext()){
	    					element = iter.next();
	    				}
	    			} else {
	    				cur.setElement(i, j, (tmp.getElement(i, j)));
	    			}
	    		}
	    	}
	    	return cur;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	@ Override
	public final SparseMatrix product(IMatrix tmp){
		if (this.rowSize == tmp.getColumnSize()){

			class RowTaker {                      //for optimization of the product method
					
					Iterator<SMEl> rowTaker;      //iterator run only 1 time
					SMEl rowElement;
				public RowTaker(){
					rowTaker = matrix.iterator(); // begining run
					rowElement = rowTaker.next(); //first element
				}
				public final int[] getRow(int x){
					int[] curRow = new int[rowSize];
					for (int m = 0; m < rowSize; m++) curRow[m] = 0; //zero filling (if no such element)

	    			for (; rowElement.column == x;) {                 // find all elements, wich have 'x' colum
	    				curRow[rowElement.row] = rowElement.value;
						if(rowTaker.hasNext()){                       //taking next or end
	    					rowElement = rowTaker.next();	    			
	    				} else { break; }
	    			}
	    			return curRow;
				}
			}
			RowTaker taker = new RowTaker();
			int[] tmpRow;                     //array of current row
			SparseMatrix cur = new SparseMatrix(this.colSize, tmp.getRowSize() );

			for (int i = 0; i < cur.colSize; i++) {
				tmpRow = taker.getRow(i);    // taking 'i' row
				for (int j = 0; j < cur.rowSize; j++) {
					int intersect = 0;
					for (int k = 0; k < this.rowSize; k++) {
	    				// cur.addElement(i, j, this.getElement(i, k) * (tmp.getElement(k, j)) );
	    				intersect += tmpRow[k] * (tmp.getElement(k, j));  //fast accessing an array element of current row for SparseMatrix
	    			}	
	    			// cur.addElement(i, j, intersect );
	    			if(intersect != 0){
	    				SMEl el_ = new SMEl(i, j, intersect);
	    				cur.matrix.addLast(el_);
	    			}
				}
			}
			return cur;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	private final void remove(int column, int row){
	    for (Iterator<SMEl> iter = this.matrix.iterator(); iter.hasNext(); ) {
	    	SMEl element = iter.next();
	    	if(element.row == row && element.column == column){
	        	iter.remove();
	    		return;
	    	}
	    }
	}
	private final void removeAll(){
		 for (Iterator<SMEl> iter = this.matrix.iterator(); iter.hasNext(); ) {
	    	SMEl element = iter.next();
	        iter.remove();
	    }
	}
	public final void starFill(int max){
		for(int i = 0; i < max; i++){
			this.setElement(i, i, (int)(Math.random()* 9) + 1);
			this.setElement((i/2), (i/3), (int)(Math.random()* 9) + 1);
			this.setElement((i/3), (i/2), (int)(Math.random()* 9) + 1);
			this.setElement((i/4), (i/5), (int)(Math.random()* 9) + 1);
			this.setElement((i/5), (i/4), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/2), max-(i/3), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/3), max-(i/2), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/4), max-(i/5), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/5), max-(i/4), (int)(Math.random()* 9) + 1);
			this.setElement(max-i, i, (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/2), (i/3), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/3), (i/2), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/4), (i/5), (int)(Math.random()* 9) + 1);
			this.setElement(max-(i/5), (i/4), (int)(Math.random()* 9) + 1);
			this.setElement((i/2), max-(i/3), (int)(Math.random()* 9) + 1);
			this.setElement((i/3), max-(i/2), (int)(Math.random()* 9) + 1);
			this.setElement((i/4), max-(i/5), (int)(Math.random()* 9) + 1);
			this.setElement((i/5), max-(i/4), (int)(Math.random()* 9) + 1);
		}
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
	private final void shrinkToFit(){
		int maxCol = 0;
		int maxRow = 0;
		for(SMEl element : this.matrix){
			if(element.row >= maxRow){
				maxRow = element.row;
			}
			if(element.column >= maxCol){
				maxCol = element.column;
			}
		}
		this.colSize = maxCol+1;
		this.rowSize = maxRow+1;	
	}
	public final boolean equals(Object tmp) {
	      if(tmp instanceof SparseMatrix)
	        return ((this.toString()).equals(tmp.toString())); 
	      return false;
	  }
	public final String toString(){
	    StringBuilder str = new StringBuilder(); 
	    Iterator<SMEl> iter = this.matrix.iterator();
	    SMEl element = new SMEl(-1, -1, 0);
	    if(iter.hasNext()){
	    	element = iter.next();
	    }
	    for (int i = 0; i < this.colSize; i++) {
	    	for(int j = 0; j < this.rowSize; j++){
	    		if(i == element.column && j == element.row){
	    			str.append(element.value);
	    			str.append(" ");
	    			if(iter.hasNext()){
	    				element = iter.next();	    			
	    			}
	    		} else {
	    			str.append("0 ");
	    		}
	    	}
	    	str.append("\n");
	    }
	    return str.toString(); 
	  }
}