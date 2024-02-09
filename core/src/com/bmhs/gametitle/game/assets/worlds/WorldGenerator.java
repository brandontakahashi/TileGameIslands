package com.bmhs.gametitle.game.assets.worlds;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bmhs.gametitle.gfx.assets.tiles.statictiles.WorldTile;
import com.bmhs.gametitle.gfx.utils.TileHandler;

public class WorldGenerator {
    private int worldMapRows, worldMapColumns;

    private int seedColor;

    private int[][] worldIntMap;

    public WorldGenerator (int worldMapRows, int worldMapColumns) {
        this.worldMapRows = worldMapRows;
        this.worldMapColumns = worldMapColumns;

        worldIntMap = new int[worldMapRows][worldMapColumns];
        seedColor = 67;

        water();

//        Vector2 mapSeed = new Vector2(10, 20);
//
//        worldIntMap[(int)mapSeed.y][(int)mapSeed.x] = seedColor;
//
//        for(int r = 0; r < worldIntMap.length; r++) {
//            for(int c = 0; c < worldIntMap[r].length; c++){
//                Vector2 tempVector = new Vector2(c, r);
//                if(tempVector.dst(mapSeed) < 6){
//                    worldIntMap[r][c] = 2;
//                }
//            }
//        }

        Vector2 mapSeed = new Vector2(23, 15);
        int horizontalRadius = 10; // Horizontal radius of the oval
        int verticalRadius = 6;   // Vertical radius of the oval

        worldIntMap[(int)mapSeed.y][(int)mapSeed.x] = seedColor;

        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++){
                Vector2 tempVector = new Vector2(c, r);
                double distance = Math.pow((c - mapSeed.x) / horizontalRadius, 2) + Math.pow((r - mapSeed.y) / verticalRadius, 2);
                if(distance <= 1){
                    worldIntMap[r][c] = 67;
                }
            }
        }


        //seedIslands(1);

        //sand
        searchAndExpand(5, seedColor, 44, 0.99);
        searchAndExpand(4, seedColor, 45,0.99);
        searchAndExpand(4, seedColor, 43, 0.7);
        //grass
        searchAndExpand(2, seedColor, 72, 0.5);
        searchAndExpand(2, seedColor, 61, 0.3);


//        Vector2 mapSeed = new Vector2(MathUtils.random(worldIntMap[0].length), MathUtils.random(worldIntMap.length));
//        System.out.println(mapSeed.y + " " + mapSeed.x);
//
//        worldIntMap[(int)mapSeed.y][(int)mapSeed.x] = 4;
//
//        for(int r = 0; r < worldIntMap.length; r++) {
//            for(int c = 0; c < worldIntMap[r].length; c++){
//                Vector2 tempVector = new Vector2(c, r);
//                if(tempVector.dst(mapSeed) < 3){
//                    worldIntMap[r][c] = 2;
//                }
//            }
//        }

        //call methods to build 2D array
        //randomize();
        generateWorld();
        generateWorldTextFile();

        Gdx.app.error("WorldGenerator", "WorldGenerator(WorldTile[][][])");
    }


    public void water() {
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                worldIntMap[r][c] = 19;
            }
        }
    }

    private void seedIslands(int num) {
        for(int i = 0; i < num; i++) {
            int rSeed = MathUtils.random(worldIntMap.length - 1);
            int cSeed = MathUtils.random(worldIntMap[0].length-1);
            worldIntMap[rSeed][cSeed] = 2;
        }
    }

    private void searchAndExpand(int radius, int numToFind, int numToWrite, double probability) {
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                if(worldIntMap[r][c] == numToFind) {
                    for(int subRow = r-radius; subRow <= r+radius; subRow++){
                        for(int subCol = c-radius; subCol <= c+radius; subCol++){
                            if(subRow >= 0 && subCol >= 0 && subRow <= worldIntMap.length - 1 && subCol <= worldIntMap[0].length - 1 && worldIntMap[subRow][subCol] != numToFind) {
                                if(Math.random() < probability){
                                    worldIntMap[subRow][subCol] = numToWrite;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void searchAndExpand(int radius) {
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                if(worldIntMap[r][c] == seedColor) {
                    for(int subRow = r-radius; subRow <= r+radius; subRow++){
                        for(int subCol = c-radius; subCol <= c+radius; subCol++){
                            if(subRow >= 0 && subCol >= 0 && subRow <= worldIntMap.length - 1 && subCol <= worldIntMap[0].length && worldIntMap[subRow][subCol] != seedColor) {
                                worldIntMap[subRow][subCol] = 3;
                            }
                        }
                    }
                }
            }
        }
    }

    public String getWorld3DArrayToString() {
        String returnString = "";

        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                returnString += worldIntMap[r][c] + " ";
            }
            returnString += "\n";
        }

        return returnString;
    }

    public void randomize() {
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                worldIntMap[r][c] = MathUtils.random(TileHandler.getTileHandler().getWorldTileArray().size-1);
            }
        }
    }

    public WorldTile[][] generateWorld() {
        WorldTile[][] worldTileMap = new WorldTile[worldMapRows][worldMapColumns];
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                worldTileMap[r][c] = TileHandler.getTileHandler().getWorldTileArray().get(worldIntMap[r][c]);
            }
        }
        return worldTileMap;
    }
    private void generateWorldTextFile() {
        FileHandle file = Gdx.files.local("assets/worlds/world.txt");
        file.writeString(getWorld3DArrayToString(), false);
    }

}
