package Strategy;
import Objects.Plates;
import Objects.Clown;
import java.util.Stack;


public class DefaultPlateEntryStrategy implements PlateEntryStrategy {
    @Override
    public boolean isPlateEnteredLeftStack(Clown clown, Plates plate,Stack CollectedObjects) {
        if ((clown.getLeftCenter()-plate.getX()<=15)&&(clown.getLeftCenter()-plate.getX()>=-15))
                {
                    CollectedObjects.push(plate);
                    return true;
                }
     

        return false;

    }
        @Override
        public boolean isPlateEnteredRightStack(Clown clown, Plates plate,Stack CollectedObjects) {

     if ((clown.getRightCenter()-plate.getX()<=15)&&(clown.getRightCenter()-plate.getX()>=-15))
                {
                    CollectedObjects.push(plate);
                    return true;
                }

        return false;

        }   
}