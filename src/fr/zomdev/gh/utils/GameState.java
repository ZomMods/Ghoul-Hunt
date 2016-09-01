package fr.zomdev.gh.utils;

public enum GameState {

    LOBBY,
    PREGAME,
    GAME,
    FINISH;

    /**
     * Created by ZomDev on 30/08/2016.
     */
    public static GameState state;

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameState.state = state;
    }

    public static boolean isState(GameState state){
        return state == GameState.state;
    }
}
