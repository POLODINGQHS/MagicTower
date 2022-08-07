package org.coooooding.MagicTower.grid;

import com.almasb.fxgl.entity.Entity;
import org.coooooding.MagicTower.enums.EntityType;

public class Cell {
    private int x;
    private int y;
    private Object userData = null;

    private Entity entity;

    public Cell(int x, int y, Entity entity) {
        this.x = x;
        this.y = y;
        this.entity = entity;
    }


    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final void setUserData(Object userData) {
        this.userData = userData;
    }

    public final Object getUserData() {
        return this.userData;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public String toString() {
        return "Cell(" + this.x + "," + this.y + "):   "+entity.toString();
    }
}
