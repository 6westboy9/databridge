package org.westboy.databridge.common.element;

public interface Record {
    
    void addColumn(Column column);

    void setColumn(int i, Column column);

    Column getColumn(int i);

    String toString();

    int getColumnSize();

    int getByteSize();

    int getMemorySize();

    
}
