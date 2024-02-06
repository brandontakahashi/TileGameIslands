package com.bmhs.gametitle.gfx.assets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.bmhs.gametitle.game.assets.characters.NonPlayerCharacter;
import com.bmhs.gametitle.game.utils.GameHandler;
import com.bmhs.gametitle.gfx.assets.tiles.statictiles.WorldTile;
import com.bmhs.gametitle.gfx.utils.TileHandler;

public class NPCTestScreen implements Screen {

    GameHandler game;
    Screen parent;

    Array<NonPlayerCharacter> npcArray;

    public NPCTestScreen(GameHandler game, Screen parent) {
        this.game = game;
        this.parent = parent;

        npcArray = new Array<>();

        int numOfNPCs = 30;
        for(int i = 0; i < numOfNPCs; i++) {
            WorldTile tempTile = TileHandler.getTileHandler().getWorldTileArray().get(2);
            float tempX = (float)Math.random() * Gdx.graphics.getWidth();
            float tempY = (float)Math.random() * Gdx.graphics.getHeight();
            String tempName = "npc " + i;
            npcArray.add(new NonPlayerCharacter(tempTile, tempX, tempY, tempName));
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
