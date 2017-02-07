package space.xxv.bread.model;

import io.realm.RealmObject;

/**
 * Created by wangjiang on 2017/2/7.
 */

public class Score extends RealmObject {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
