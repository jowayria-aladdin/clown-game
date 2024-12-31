package Strategy;

import Objects.Clown;
import Objects.Plates;
import java.util.Stack;

public interface PlateEntryStrategy {
     boolean isPlateEnteredLeftStack(Clown clown, Plates plate,Stack CollectedObjects) ;
     boolean isPlateEnteredRightStack(Clown clown, Plates plate,Stack CollectedObjects) ;
}
