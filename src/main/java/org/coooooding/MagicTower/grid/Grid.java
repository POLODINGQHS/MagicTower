package org.coooooding.MagicTower.grid;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.pathfinding.CellState;
import org.coooooding.MagicTower.enums.EntityType;

import java.util.HashMap;
import java.util.function.Function;

public class Grid {

    private static HashMap<Integer,Grid> gridMap = new HashMap<>();
    private Cell[][] data;
    private int width;
    private int height;
    private int cellWidth;
    private int cellHeight;

    public Grid(int width, int height, int cellWidth, int cellHeight) {
        if (cellWidth >= 0 && cellHeight >= 0) {
            if (width > 0 && height > 0) {
                this.width = width;
                this.height = height;
                this.cellWidth = cellWidth;
                this.cellHeight = cellHeight;
                data = new Cell[width][height];
            } else {
                throw new IllegalArgumentException("Cannot create grid with 0 or negative size");
            }
        } else {
            throw new IllegalArgumentException("Cannot create grid with cells of negative size");
        }
    }

    public static Grid initFromWorld(GameWorld world,
                                     int worldWidth, int worldHeight,
                                     int cellWidth, int cellHeight,
                                     Function<Object, CellState> mapping) {
        Grid grid = new Grid(worldWidth, worldHeight,cellWidth,cellHeight);
        for (Entity e : world.getEntitiesCopy()) {
            if (!e.getType().toString().equals("TiledMapLayer") && !e.getType().toString().equals("PLAYER")){
                EntityType type = EntityType.valueOf(e.getType().toString());
                grid.setCell((int)e.getX()/cellWidth,(int)e.getY()/cellHeight,e);
            }
        }
        return grid;
    }

    public void setCell(int x, int y, Entity e){
        data[x][y] = new Cell(x, y, e);
    }
    
    public Cell getOffsetCell(int x, int y){
        return data[x][y];
    }

    public void clearCell(int x, int y){
        data[x][y] = null;
    }

    public static HashMap<Integer, Grid> getGridMap() {
        return gridMap;
    }
}
