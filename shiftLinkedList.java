/*

Function takes in the head of a SLL an an integer <K>
Shifts the list "in-place" ( do not create a brand new list ) by k positions
Return the new head

Shift = move fwd or move bkwd : wrap around where needed
Signage of k determines a fwds or a bkwds shift
ONLY the TAIL or a SLL points to the NULLPTR as well
Assume list node always has at least ONE node here




*/

import java.util.*;

class Program {
  public static LinkedList shiftLinkedList(LinkedList head, int k) {
    // Write your code here.
    return null;
  }

  static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      next = null;
    }
  }
}
