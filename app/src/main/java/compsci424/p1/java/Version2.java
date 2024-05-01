/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version2 {
    // Declare any class/instance variables that you need here.
   
    private Version2PCB[] pcbArray;
 

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed.
     */
    public Version2(int n) {
  
        pcbArray = new Version2PCB[n];
       
        for(int i=0;i<n;i++){
            pcbArray[i]=new Version2PCB(-1);
            
        }
    }

    
    /**
     * Creates a new child process of the process with ID parentPid.
     * 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int create(int parentPid) {
        int childPid=findFreePCB();
        if(childPid!=-1){
            pcbArray[childPid].parent=parentPid;
            if( parentPid >= 0 && parentPid < pcbArray.length) {
    			int youngerSibling = pcbArray[parentPid].firstChild;
    			if(youngerSibling == -1) {
    				pcbArray[parentPid].firstChild = childPid;
    			}
    			else {
    				while (pcbArray[youngerSibling].youngerSibling != -1) {
    					youngerSibling = pcbArray[youngerSibling].youngerSibling;
    				}
    				pcbArray[youngerSibling].youngerSibling = childPid;
    				pcbArray[childPid].olderSibling = youngerSibling;
    			}
    		}
    	}
        return 0;
    }
        
    
            private int findFreePCB() {
                for (int i = 0; i < pcbArray.length; i++) {
                    if (pcbArray[i].parent == -1) {
                        return i;
                    }
                }
                return -1; // No free PCB slot
            }
        

        // If parentPid is not in the process hierarchy, do nothing;
        // your code may return an error code or message in this case,
        // but it should not halt
    
    // Assuming you've found the PCB for parentPid in the PCB array:
    // 1. Allocate and initialize a free PCB object from the array
    // of PCB objects

    // 2. Insert the newly allocated PCB object into parentPid's
    // list of children

    // You can decide what the return value(s), if any, should be.
    // If you change the return type/value(s), update the Javadoc.
    // often means "success" or "terminated normally"

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * 
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int destroy(int targetPid) {
        
    	if(targetPid < 0 || targetPid >= pcbArray.length) {
    		System.out.println("");
    		return - 1; // failed
    	}
    	destroyRecursively(targetPid);
       return 0;
   }
   private void destroyRecursively(int targetPid) {
    int olderSibling = pcbArray[targetPid].olderSibling;
    if (olderSibling != -1) {
        pcbArray[olderSibling].youngerSibling = pcbArray[targetPid].youngerSibling;
    }
    int parentPid = pcbArray[targetPid].parent;
    if (parentPid != -1) {
        if (pcbArray[parentPid].firstChild == targetPid) {
            pcbArray[parentPid].firstChild = pcbArray[targetPid].youngerSibling;
        }
    }
    pcbArray[targetPid].parent = -1;
    pcbArray[targetPid].firstChild = -1;
    pcbArray[targetPid].youngerSibling = -1;
    pcbArray[targetPid].olderSibling = -1;
}
    
            
        // If targetPid is not in the process hierarchy, do nothing;
        // your code may return an error code or message in this case,
        // but it should not halt
        

        // Assuming you've found the PCB for targetPid in the PCB array:
        // 1. Recursively destroy all descendants of targetPid, if it
        // has any, and mark their PCBs as "free" in the PCB array
        // (i.e., deallocate them)

        // 2. Remove targetPid from its parent's list of children

        // 3. Deallocate targetPid's PCB and mark its PCB array entry
        // as "free"

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
       // often means "success" or "terminated normally"
    

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format.
     * 
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice.
     */
    void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i].parent != -1) {
            StringBuilder info = new StringBuilder("Process " + i + ": ");
            info.append("parent is ").append(pcbArray[i].parent).append(" ");
            int child=pcbArray[i].firstChild;
            if(child!=-1){
            info.append("and children are ");
          
            while(child!=-1){
                
                    info.append(child).append(" ");
                    child=pcbArray[child].youngerSibling;
            
                }
            }
            else{
                info.append(" has no children");
            }
            System.out.println(info.toString());
               
            }
}
}
}
