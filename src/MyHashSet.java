import java.lang.String;
import java.util.ArrayList;

public class MyHashSet {
    public int max;
    public int size;
    ArrayList<String> entry = new ArrayList<>(max);


    public MyHashSet(){
        max = 8;
        size = 0;
        entry.ensureCapacity(max);
        for (int i = 0; i < max ; i++){
            entry.add(i,"");
        }
    }

    public int hashCode(String value){
        int result = 0;
        for (int i = 0; i < value.length(); i++){
            result += (int) value.charAt(i);
        }
        result %= max;
        return result;
    }

    public void rehash() {
        max = max * 2;
        entry.ensureCapacity(max);

        ArrayList<String> subEntry = new ArrayList<>(max);
        for (int j = 0; j < max; j++) {
            subEntry.add(j, "");}

        for (int i = 0; i < max / 2; i++) {
            if (entry.get(i) != "") {
                subEntry.set(hashCode(entry.get(i)), entry.get(i));
                entry.set(i, "");
            }
        }

        for (int c = 0; c < max; c++) {
                entry.add(c,subEntry.get(c));
                entry.set(c,subEntry.get(c));
            }
        }


    public void printset(){
        System.out.println("\nHashset:");
        for (int i = 0; i < max; i++) {
            if (entry.get(i) != "") {
                System.out.println(entry.get(i));
            }
        }

    System.out.println("\n");
    }

    public void remove(String value){
        entry.set(hashCode(value), "");
        size --;
        System.out.println(value + " has removed from Hashset");
    }

    public void insert(String value){
        if (size == max/2) {
            rehash();
        }
        entry.set(hashCode(value), value);
        size ++;
        System.out.println(value + " has added to Hashset");
    }
    public void printin(String value){
        if(entry.get(hashCode(value)) != ""){
            if(entry.get(hashCode(value)).equals(value)) {
                System.out.println("Hashset contains " + value);
            }
        }
        else
        {
            System.out.println("Hashset doen't contain " + value);
        }
    }
}
