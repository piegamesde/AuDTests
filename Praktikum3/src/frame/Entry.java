package frame;
/*
 * Entry.java
 *
 */


public class Entry implements EntryInterface, Comparable<Entry> {
    
    private String key;
    private String data;
    
    /** Creates a new instance of Entry */
    public Entry() {
        this.key = null;
        this.data = null;
    }
    
    public Entry(String bookSerialNumber, String ReaderID, String Status) {
        this.key = new String(bookSerialNumber.concat(ReaderID));
        this.data = new String(Status);
    }
    
    public void setKey(String newKey) {
        this.key = new String(newKey);
    }
    
    public void setData(String newData) {
        this.data = new String(newData);
    }
    
    public String getKey() {
        if(this.key != null) {
            return this.key;
        } else {
            return null;
        }
    }
    
    public String getData() {
        if(this.data != null) {
            return this.data;
        } else {
            return null;
        }
    }
    
    public String toString() {
        String r = new String();
        if (this.key != null) {
            r = this.key.substring(0, 5) + ";" + this.key.substring(5);
        }
        if(this.data != null) {
            r = r + ";" + this.data;
        }
        
        return r;
    }
    
    public int compareTo(Entry e) {
        return this.key.compareTo(e.getKey());
    }
}
