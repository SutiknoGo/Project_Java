
package com.mygdx.game;

public class CPosisi {
    protected int x,y;
    protected String namaFile;
    public CPosisi(){}  
    public CPosisi(int x, int y, String namaFile) {
        this.x = x;
        this.y = y;
        this.namaFile = namaFile;
    }
    public CPosisi(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX()       { return x;  }
    public void setX(int x) { this.x = x;}
    public int getY()       { return y;  }
    public void setY(int y) { this.y = y;}
    public String getNamaFile() { return namaFile; }
    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }    
}
