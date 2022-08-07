package org.coooooding.MagicTower.process;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.coooooding.MagicTower.components.PlayerActionComponent;
import org.coooooding.MagicTower.components.PlayerAnimationComponent;
import org.coooooding.MagicTower.enums.EntityType;
import org.coooooding.MagicTower.grid.Grid;
import org.coooooding.MagicTower.util.GlobalMap;

import static com.almasb.fxgl.dsl.FXGL.*;

public class LevelProcess {

    public static void upstairs(){

        getGameWorld().removeEntities();
        inc("currentLevel",1);
    }
}
