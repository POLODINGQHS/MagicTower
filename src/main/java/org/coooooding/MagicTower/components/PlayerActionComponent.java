package org.coooooding.MagicTower.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import org.coooooding.MagicTower.enums.Direction;
import org.coooooding.MagicTower.enums.EntityType;
import org.coooooding.MagicTower.grid.Cell;
import org.coooooding.MagicTower.grid.Grid;
import org.coooooding.MagicTower.process.LevelProcess;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;


public class PlayerActionComponent extends Component {

    private Grid grid;
    private int speed = 64;

    private int positionX,positionY;

    public PlayerActionComponent(int currentlevel, int positionX, int positionY) {
        this.grid = Grid.getGridMap().get(currentlevel);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void moveToDirection(Direction direction){
        int offsetX = positionX, offsetY = positionY;
        switch (direction){
            case UP -> offsetY--;
            case DOWN -> offsetY++;
            case LEFT -> offsetX--;
            case RIGHT -> offsetX++;
        }

        if(offsetX >=0 && offsetX<=10 && offsetY>=0 && offsetY<=10){
            Cell cell = grid.getOffsetCell(offsetX,offsetY);
            if(cell == null){
                move(direction);
            }else {
                doAction(grid.getOffsetCell(offsetX,offsetY));
            }
        }
    }

    public void move(Direction direction){
        switch (direction){
            case UP:
                entity.translateY(-1 * speed);
                positionY--;
                break;
            case DOWN:
                entity.translateY(1 * speed);
                positionY++;
                break;
            case RIGHT:
                entity.translateX(1 * speed);
                positionX++;
                break;
            case LEFT:
                entity.translateX(-1 * speed);
                positionX--;
                break;
        }
    }

    public void doAction(Cell cell){
        if(cell.getEntity().getType().equals(EntityType.ENEMY)) {
            FXGL.play("fight.wav");
            try {
                Thread.sleep(1000); //1000 毫秒，也就是1秒.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            cell.getEntity().removeFromWorld();
            grid.clearCell(cell.getX(), cell.getY());
        }else if(cell.getEntity().getType().equals(EntityType.UPSTAIRS)){
            LevelProcess.upstairs();
        }
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
