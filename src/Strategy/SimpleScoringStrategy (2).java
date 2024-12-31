package Strategy;
import Game.ClownGame;
import Objects.Plates;

public class SimpleScoringStrategy implements ScoringStrategy {
    //bomb and shapes and gifts
 @Override
 public void updateRightScore() {

        try {
            Plates temp1 = (Plates) ClownGame.getRight().get(ClownGame.right.size() - 1);
            Plates temp2 = (Plates) ClownGame.right.get(ClownGame.right.size() - 2);
            Plates temp3 = (Plates) ClownGame.right.get(ClownGame.right.size() - 3);
            if   ((temp1.getColor().equals(temp2.getColor()))&&(temp1.getColor().equals(temp3.getColor()))) {
                ClownGame.score += 5;
                ClownGame.right.remove(temp1);
                ClownGame.right.remove(temp2);
                ClownGame.right.remove(temp3);
                ClownGame.rightCollectedObjects.pop();
                ClownGame.rightCollectedObjects.pop();
                ClownGame.rightCollectedObjects.pop();
                ClownGame.controlable.remove(temp1);
                ClownGame.controlable.remove(temp2);
                ClownGame.controlable.remove(temp3);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
 @Override
 public void updateLeftScore() {

        try {
            Plates temp1 = (Plates) ClownGame.getLeft().get(ClownGame.left.size() - 1);
            Plates temp2 = (Plates) ClownGame.left.get(ClownGame.left.size() - 2);
            Plates temp3 = (Plates) ClownGame.left.get(ClownGame.left.size() - 3);
            if   ((temp1.getColor().equals(temp2.getColor()))&&(temp1.getColor().equals(temp3.getColor()))) {
                ClownGame.score += 5;
                ClownGame.left.remove(temp1);
                ClownGame.left.remove(temp2);
                ClownGame.left.remove(temp3);
                ClownGame.leftCollectedObjects.pop();
                ClownGame.leftCollectedObjects.pop();
                ClownGame.leftCollectedObjects.pop();
                ClownGame.controlable.remove(temp1);
                ClownGame.controlable.remove(temp2);
                ClownGame.controlable.remove(temp3);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
 }


