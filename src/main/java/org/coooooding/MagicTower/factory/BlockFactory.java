package org.coooooding.MagicTower.factory;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.AnimatedTexture;
import javafx.util.Duration;
import org.coooooding.MagicTower.components.PlayerAnimationComponent;
import org.coooooding.MagicTower.enums.EntityType;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BlockFactory implements EntityFactory {

    @Spawns("wall")
    public Entity newWall(SpawnData data){

        return entityBuilder(data)
                .type(EntityType.WALL)
                .bbox(BoundingShape.box(data.getX(), data.getY()))
                .neverUpdated()
                .collidable()
                .build();
    }

    @Spawns("upstairs")
    public Entity newUpstairs(SpawnData data){

        return entityBuilder(data)
                .type(EntityType.UPSTAIRS)
                .bbox(BoundingShape.box(data.getX(), data.getY()))
                .neverUpdated()
                .collidable()
                .build();
    }

    @Spawns("downstairs")
    public Entity newDownstairs(SpawnData data){

        return entityBuilder(data)
                .type(EntityType.DOWNSTAIRS)
                .bbox(BoundingShape.box(data.getX(), data.getY()))
                .neverUpdated()
                .collidable()
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .at(0,0)
                .anchorFromCenter()
                .bbox(BoundingShape.box(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .with(new PlayerAnimationComponent())
                .build();
    }

    @Spawns("skeletonSoldier")
    public Entity newSkeletonSoldier(SpawnData data){
        AnimatedTexture view = texture("enemy/skeletonSoldier.png").toAnimatedTexture(4, Duration.seconds(0.5));
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(view.loop())
                .with(new CollidableComponent(true))
                .build();
    }
}
