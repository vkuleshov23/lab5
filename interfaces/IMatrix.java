package prog.lab5.interfaces;
import prog.lab5.matrixes.*;

public interface IMatrix{

	void setElement(int column, int row, int value);
	int getElement(int column, int row);
	
	int getColumnSize();
	int getRowSize();

	IMatrix sum( IMatrix tmp );
	IMatrix product( IMatrix tmp);

}