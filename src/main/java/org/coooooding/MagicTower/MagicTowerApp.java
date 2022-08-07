package org.coooooding.MagicTower;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.collection.PropertyChangeListener;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.LocalTimer;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.coooooding.MagicTower.components.PlayerActionComponent;
import org.coooooding.MagicTower.components.PlayerAnimationComponent;
import org.coooooding.MagicTower.enums.Direction;
import org.coooooding.MagicTower.enums.EntityType;
import org.coooooding.MagicTower.factory.BlockFactory;
import org.coooooding.MagicTower.grid.Grid;
import org.coooooding.MagicTower.util.GlobalMap;

import java.util.HashMap;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MagicTowerApp extends GameApplication {

    private Entity player;

    private final int pixelsPerUnit = 64;
    private final int cellWidth = 11, cellHeight = 11;

    private final double moveIntervalSecond = 0.1;
    private boolean requestNewGame = false;
    private LocalTimer moveTimer;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(cellWidth*pixelsPerUnit);
        gameSettings.setHeight(cellHeight*pixelsPerUnit);
        gameSettings.setTitle("魔塔");
        gameSettings.setVersion("0.1.0");
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.UP, () ->  {
            if(ifTimer()){
                player.getComponent(PlayerAnimationComponent.class).moveUp();
                player.getComponent(PlayerActionComponent.class).moveToDirection(Direction.UP);
            }
        });
        onKey(KeyCode.DOWN, () ->   {
            if(ifTimer()){
                player.getComponent(PlayerAnimationComponent.class).moveDown();
                player.getComponent(PlayerActionComponent.class).moveToDirection(Direction.DOWN);
            }
        });
        onKey(KeyCode.LEFT, () ->   {
            if(ifTimer()){
                player.getComponent(PlayerAnimationComponent.class).moveLeft();
                player.getComponent(PlayerActionComponent.class).moveToDirection(Direction.LEFT);
            }
        });
        onKey(KeyCode.RIGHT, () ->   {
            if(ifTimer()){
                player.getComponent(PlayerAnimationComponent.class).moveRight();
                player.getComponent(PlayerActionComponent.class).moveToDirection(Direction.RIGHT);
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("currentLevel",1);
    }

    @Override
    protected void initGame() {

        int currentLevel = geti("currentLevel");
        try{
            getGameWorld().addEntityFactory(new BlockFactory());
        }catch (Exception e){

        }
        setLevelFromMap("tmx/level"+ geti("currentLevel")+".tmx");

        player = spawn("player");

        moveTimer = newLocalTimer();

        Grid grid;
        if(!Grid.getGridMap().containsKey(currentLevel)){
            grid = Grid.getGridMap().put(1,Grid.initFromWorld(getGameWorld(),11,11,64,64,(t)->{
                // add mapping method. if do not need mapping, just return null.
                return null;
            }));
        }else{
            grid = Grid.getGridMap().get(currentLevel);
        }

        player.addComponent(new PlayerActionComponent(1,0,0));

        GlobalMap.setValue("player",player);

        getGameWorld().getProperties().addListener("currentLevel", new PropertyChangeListener<Integer>() {
            @Override
            public void onChange(Integer prev, Integer now) {
                if(prev != now){
                    initGame();
                }
            }
        });
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.WALL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity wall) {
                if(player.getX() == wall.getRightX()) player.setProperty("ifLeft",false);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean ifTimer(){
        if(moveTimer.elapsed(Duration.seconds(moveIntervalSecond))) {
            moveTimer.capture();
            return true;
        }else {
            return false;
        }
    }


}
