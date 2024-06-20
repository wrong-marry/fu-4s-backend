package core.fu4sbackend.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@NoArgsConstructor
public class PriorityRangeList extends ArrayList<PriorityRange> {
    public void addRange(int low, int high, int priorityLevel) {
        add(new PriorityRange(low, high, priorityLevel));
    }
    public int findPriorityOfNumber(int value) {
        for(PriorityRange p : this) {
            if(p.contains(value)){
                return p.getPriorityLevel();
            }
        }
        return -1;
    }
}

@Getter
@Setter
@AllArgsConstructor
class PriorityRange {
    private int low;
    private int high;
    private int priorityLevel;
    public boolean contains(int value) {
        return low <= value && value <= high;
    }
}
