package org.coooooding.MagicTower.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.coooooding.MagicTower.enums.Direction;
import org.coooooding.MagicTower.grid.Cell;
import org.coooooding.MagicTower.grid.Grid;

public class PlayerAnimationComponent extends Component {


    private Direction direction = Direction.UP;
    private boolean ifChange = false;

    private AnimatedTexture texture;
    private AnimationChannel leftIdleAnim,rightIdleAnim,upIdleAnim,downIdleAnim,animLeft,animRight,animUp,animDown;

    public PlayerAnimationComponent() {
        intAnim();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16,16));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

        switch (direction){
            case UP:
                if(ifChange)    texture.loopAnimationChannel(animUp);
                break;
            case DOWN:
                if(ifChange)    texture.loopAnimationChannel(animDown);
                break;
            case RIGHT:
                if(ifChange)    texture.loopAnimationChannel(animRight);
                break;
            case LEFT:
                if(ifChange)    texture.loopAnimationChannel(animLeft);
                break;
        }
    }

    public void moveUp(){
        if(direction != Direction.UP){
            ifChange = true;
        }else {
            ifChange = false;
        }

        direction = Direction.UP;
    }

    public void moveDown(){
        if(direction != Direction.DOWN){
            ifChange = true;
        }else {
            ifChange = false;
        }

        direction = Direction.DOWN;
    }

    public void moveLeft(){
        if(direction != Direction.LEFT){
            ifChange = true;
        }else {
            ifChange = false;
        }

        direction = Direction.LEFT;
    }

    public void moveRight(){
        if(direction != Direction.RIGHT){
            ifChange = true;
        }else {
            ifChange = false;
        }

        direction = Direction.RIGHT;
    }

    public void stop(Direction direction){
        switch (direction){
            case UP:
                texture = new AnimatedTexture(upIdleAnim);
                break;
            case DOWN:
                texture = new AnimatedTexture(downIdleAnim);
                break;
            case RIGHT:
                texture = new AnimatedTexture(rightIdleAnim);
                break;
            case LEFT:
                texture = new AnimatedTexture(leftIdleAnim);
                break;
        }
        entity.getViewComponent().clearChildren();
        entity.getViewComponent().addChild(texture);
    }


    private void intAnim(){
        leftIdleAnim = new AnimationChannel(FXGL.image("hero_left.png")
                ,4,64,64,Duration.seconds(1),0,0);

        rightIdleAnim = new AnimationChannel(FXGL.image("hero_right.png")
                ,4,64,64,Duration.seconds(1),0,0);

        upIdleAnim = new AnimationChannel(FXGL.image("hero_up.png")
                ,4,64,64,Duration.seconds(1),0,0);

        downIdleAnim = new AnimationChannel(FXGL.image("hero_down.png")
                ,4,64,64,Duration.seconds(1),0,0);

        animLeft = new AnimationChannel(FXGL.image("hero_left.png")
                ,4,64,64,Duration.seconds(1),0,3);

        animRight = new AnimationChannel(FXGL.image("hero_right.png")
                ,4,64,64,Duration.seconds(1),0,3);

        animUp = new AnimationChannel(FXGL.image("hero_up.png")
                ,4,64,64,Duration.seconds(1),0,3);

        animDown = new AnimationChannel(FXGL.image("hero_down.png")
                ,4,64,64,Duration.seconds(1),0,3);

        texture = new AnimatedTexture(downIdleAnim);
    }
}
