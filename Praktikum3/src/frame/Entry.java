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

	public Entry(String data) {
		this(data.split(";")[0], data.split(";")[1], data.split(";")[2]);
    }

    public Entry(String bookSerialNumber, String ReaderID, String Status) {
        this.key = new String(bookSerialNumber.concat(ReaderID));
        this.data = new String(Status);
    }

    @Override
	public void setKey(String newKey) {
        this.key = new String(newKey);
    }

    @Override
	public void setData(String newData) {
        this.data = new String(newData);
    }

    @Override
	public String getKey() {
        if(this.key != null) {
            return this.key;
        } else {
            return null;
        }
    }

    @Override
	public String getData() {
        if(this.data != null) {
            return this.data;
        } else {
            return null;
        }
    }

    @Override
	public String toString() {
        String r = new String();
		if (this.key != null && this.key.length() >= 5) {
            r = this.key.substring(0, 5) + ";" + this.key.substring(5);
		} else
			r = this.key;
        if(this.data != null) {
            r = r + ";" + this.data;
        }

        return r;
    }

    @Override
	public int compareTo(Entry e) {
        return this.key.compareTo(e.getKey());
    }
}
