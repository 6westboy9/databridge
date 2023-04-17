package org.westboy.databridge.common.element;

public abstract class Column {
    
    private Type type;
    private Object rawData;
    private int size;

    public Column(Type type, Object rawData, int size) {
        this.type = type;
        this.rawData = rawData;
        this.size = size;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getRawData() {
        return this.rawData;
    }

    public void setRawData(Object rawData) {
        this.rawData = rawData;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public enum Type {
        INT, LONG, DOUBLE, STRING, BOOL, DATE, BYTES;
    }
}
