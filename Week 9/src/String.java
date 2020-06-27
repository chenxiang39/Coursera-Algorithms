//String in Java
public final class String implements Comparable<String>{
    private char[] value; // characters
    private int offset;  // index of first char in array
    private int length;    // length of string
    private int hash;       //cache of hashCode()
    public int length(){
        return length;
    }
    public char charAt(int i){
        return value[i + offset];
    }
    private String(int offset, int length, char[] value){
        this.offset = offset;
        this.length = length;
        this.value = value;
    }
    public String substring(int from, int to){
        return new String(offset + from, to - from, value);   //value : copy of reference to original char array
    }
}
