package prog.lab5.matrixes;
import prog.lab5.excpt.*;
import prog.lab5.interfaces.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class SparseMatrix implements IMatrix{
	
	protected class SMEl {
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
	protected int row_size = 0;
	protected int col_size = 0;
	  
	public SparseMatrix() {
		this.matrix = new LinkedList<SMEl>();
	}
	public SparseMatrix(int col, int row) {
		this.matrix = new LinkedList<SMEl>();
		this.row_size = row;
		this.col_size = col;
	}
	@Override
	public final void setElement(int column_, int row_, int value_){
		if(column_ < 0 || row_ < 0) {
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}
		if(value_ == 0){
			if(this.row_size < row_+1){
	        	this.row_size = row_+1;
	        	if(this.col_size < column_+1){
	        		this.col_size = column_+1;
	        	}
	    	}
			return;
		}
		SMEl cur = new SMEl(column_, row_, value_);
	    ListIterator<SMEl> iter = this.matrix.listIterator();
	    while (iter.hasNext()) {
	      SMEl element = iter.next();
	        if(cur.value == element.value && cur.row == element.row && cur.column == element.column){
	        	return;
	        } else if(cur.value != element.value && cur.row == element.row && cur.column == element.column){
	        	iter.set(cur);
	        	return;
	        } else if(cur.row < element.row && cur.column == element.column){
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	if(this.row_size < cur.row+1){
	          		this.row_size = cur.row+1;
	        	}
	        	return;
	        } else if(cur.column < element.column){
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	if(this.row_size < cur.row+1){
	        		this.row_size = cur.row+1;
	        		if(this.col_size < cur.column+1){
	        			this.col_size = cur.column+1;
	        		}
	    		}
	        	return;
	        }
	    }
	    this.matrix.addLast(cur);
		if(this.row_size < cur.row+1){
	        this.row_size = cur.row+1;
	        if(this.col_size < cur.column+1){
	        	this.col_size = cur.column+1;
	        }
	    }
	}
	public final void addElement(int column_, int row_, int value_){
		if(column_ < 0 || row_ < 0) {
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}
		if(value_ == 0){
			return;
		}
		SMEl cur = new SMEl(column_, row_, value_);
	    ListIterator<SMEl> iter = this.matrix.listIterator();
	    while (iter.hasNext()) {
	      SMEl element = iter.next();
	        if(cur.row == element.row && cur.column == element.column){
	        	cur.value += element.value;
	        	iter.set(cur);
	        	return;
	        } else if(cur.row < element.row && cur.column == element.column){
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	if(this.row_size < cur.row+1){
	          		this.row_size = cur.row+1;
	        	}
	        	return;
	        } else if(cur.column < element.column){
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	if(this.row_size < cur.row+1){
	        		this.row_size = cur.row+1;
	        		if(this.col_size < cur.column+1){
	        			this.col_size = cur.column+1;
	        		}
	    		}
	        	return;
	        }
	    }
	    this.matrix.addLast(cur);
		if(this.row_size < cur.row+1){
	        this.row_size = cur.row+1;
	        if(this.col_size < cur.column+1){
	        	this.col_size = cur.column+1;
	        }
	    }	
	}
	@ Override
	public int getElement(int column, int row){
		if((row < this.row_size && row >= 0) || (column >= 0 && column < this.col_size)){
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
		return this.col_size;
	}
	@ Override
	public final int getRowSize(){
		return this.row_size;
	}
	@ Override
	public final SparseMatrix sum(IMatrix tmp){
		// if(tmp instanceof SparseMatrix) {
			if((this.col_size == tmp.getColumnSize()) && (this.row_size == tmp.getRowSize())){
				SparseMatrix cur = new SparseMatrix(this.col_size, this.row_size );
				Iterator<SMEl> iter = this.matrix.iterator();
	    		SMEl element = new SMEl(-1, -1, 0);
	    		if(iter.hasNext()){
	    			element = iter.next();	    			
	    		}
	    		for (int i = 0; i < this.col_size; i++) {
	    			for (int j = 0; j < this.row_size; j++) {
	    				if(i == element.column && j == element.row){
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
		// } else { MatrixException e = new MatrixException("There is not SparseMatrix"); throw e; }	
	}
	@ Override
	public final SparseMatrix product(IMatrix tmp){
		if (this.row_size == tmp.getColumnSize()){
			SparseMatrix cur = new SparseMatrix(this.col_size, tmp.getRowSize() );
			for (int i = 0; i < cur.col_size; i++) {
				for (int j = 0; j < cur.row_size; j++) {
					for (int k = 0; k < this.row_size; k++) {
	    				cur.addElement(i, j, this.getElement(i, k) * (tmp.getElement(k, j)) );
	    			}	
				}
			}
			return cur;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	public final void remove(int column, int row){
	    for (Iterator<SMEl> iter = this.matrix.iterator(); iter.hasNext(); ) {
	    	SMEl element = iter.next();
	    	if(element.row == row && element.column == column){
	        	iter.remove();
	    		return;
	    	}
	    }
	}
	public final void removeAll(){
		 for (Iterator<SMEl> iter = this.matrix.iterator(); iter.hasNext(); ) {
	    	SMEl element = iter.next();
	        iter.remove();
	    }
	}
	public final void starFill(int max){
		for(int i = 0; i < max; i++){
			this.setElement(i, i, (int)(Math.random()*100));
			this.setElement((i/2), (i/3), (int)(Math.random()*100));
			this.setElement((i/3), (i/2), (int)(Math.random()*100));
			this.setElement((i/4), (i/5), (int)(Math.random()*100));
			this.setElement((i/5), (i/4), (int)(Math.random()*100));
			this.setElement(max-(i/2), max-(i/3), (int)(Math.random()*100));
			this.setElement(max-(i/3), max-(i/2), (int)(Math.random()*100));
			this.setElement(max-(i/4), max-(i/5), (int)(Math.random()*100));
			this.setElement(max-(i/5), max-(i/4), (int)(Math.random()*100));
			this.setElement(max-i, i, (int)(Math.random()*100));
			this.setElement(max-(i/2), (i/3), (int)(Math.random()*100));
			this.setElement(max-(i/3), (i/2), (int)(Math.random()*100));
			this.setElement(max-(i/4), (i/5), (int)(Math.random()*100));
			this.setElement(max-(i/5), (i/4), (int)(Math.random()*100));
			this.setElement((i/2), max-(i/3), (int)(Math.random()*100));
			this.setElement((i/3), max-(i/2), (int)(Math.random()*100));
			this.setElement((i/4), max-(i/5), (int)(Math.random()*100));
			this.setElement((i/5), max-(i/4), (int)(Math.random()*100));
		}
	}
	public final void fill(int max){
		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= max; j++) {
				if(i != j){
					this.setElement(i, j, (int)(Math.random()*10));
				}
			}
		}
	}
	public final void shrinkToFit(){
		int max_col = -1;
		int max_row = -1;
		for(SMEl element : this.matrix){
			if(element.row > max_row){
				max_row = element.row;
			}
			if(element.column > max_col){
				max_col = element.column;
			}
		}
		this.col_size = max_col+1;
		this.row_size = max_row+1;	
	}
	public boolean equals(Object tmp) {
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
	    for (int i = 0; i < this.col_size; i++) {
	    	for(int j = 0; j < this.row_size; j++){
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