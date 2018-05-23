package frame;
/*
 * TestNode.java
 *
 */

import java.util.ArrayList;


public class TestNode {
    private ArrayList<Entry> nodeEntries;  //stored entries in the node
    private ArrayList<TestNode> children;    //pointers to the children nodes
    
    /** Creates a new instance of TestNode */
    public TestNode(int t) {
        this.nodeEntries = new ArrayList<Entry>(2*t-1);
        this.children = new ArrayList<TestNode>(2*t);
    }
    
    public void addEntry(Entry e) {
        nodeEntries.add(e);
    }
    
    public void addChild(TestNode b) {
        children.add(b);
    }
    
    public ArrayList<Entry> getEntries() {
        return this.nodeEntries;
    }
    
    public String entriesToString() {
        String r = new String();
        for(int i=0;i<nodeEntries.size();i++) {
            r = r + nodeEntries.get(i).getKey();
        }
        return r;
    }
    
    public ArrayList<TestNode> getChildren() {
        return this.children;
    }
}
