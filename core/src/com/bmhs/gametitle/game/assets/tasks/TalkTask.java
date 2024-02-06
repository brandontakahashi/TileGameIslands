package com.bmhs.gametitle.game.assets.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.bmhs.gametitle.game.assets.characters.NonPlayerCharacter;

public class TalkTask extends LeafTask<NonPlayerCharacter> {

    @Override
    public void start() {
        System.out.println("starting talk task");
    }

    @Override
    public Status execute() {
        System.out.println("executing talk task");

        return null;
    }

    @Override
    protected Task<NonPlayerCharacter> copyTo(Task<NonPlayerCharacter> task) {
        return null;
    }
}
