import java.util.LinkedList;
package compsci424.p1.java;

public class Version1PCB {

    Version1PCB parent;
    LinkedList<Version1PCB> children;
    int parentPid;

   
    public Version1PCB(Version1PCB parent, int parentPid) {
        this.parent = parent;
        this.children = new LinkedList<>();
        this.parentPid = parentPid;
    }


    public void initializePCBArray(Version1PCB[] pcbArray, int parent) {
        for (int i = 0; i < pcbArray.length; i++) {
            pcbArray[i] = null;
        }
        int parentIndex = indexOf(pcbArray, parent);
        if (parentIndex != -1 && pcbArray[parentIndex] == null) {
            pcbArray[parentIndex] = this;
        }
    }

    
    private int indexOf(Version1PCB[] pcbArray, int parent) {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] != null && pcbArray[i].parentPid == parent) {
                return i;
            }
        }
        return -1;
    }
}

