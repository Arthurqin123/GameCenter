package fall2018.csc207project.Memorization.Models;

import java.io.Serializable;

/**
 * The class MemoTile that implements Serializable.
 */
public class MemoTile implements Serializable{

    /**
     * unique id of a MemoTile
     */
    private int id;

    /**
     * type active of MemoTile
     */
    public static final int TYPE_ACTIVE = 1;

    /**
     * type fake of MemoTile
     */
    public static final int TYPE_FAKE = 0;

    /**
     * The color of the flashing Tiles.
     */
    public static final int FAKE_COLOR = android.R.color.holo_orange_dark;
    public static final int ACTIVE_COLOR = android.R.color.holo_green_dark;
    public static final int PRESS_COLOR = android.R.color.holo_blue_dark;
    public static final int WRONG_COLOR = android.R.color.holo_red_dark;

    /**
     * status type of this MemoTile
     */
    public final int status;

    /**
     * a new MemoTile
     * @param id unique id for this MemoTile
     * @param status current type for this MemoTile
     */
    public MemoTile(int id, int status){
        this.id = id;
        this.status = status;
    }

    /**
     * gives the id of this MemoTile
     * @return id of MemoTile
     */
    public int getId() {
        return id;
    }

    /**
     * check equivalence of this MemoTile with an object
     * @param obj object to compare with this MemoTile
     * @return is this MemoTile equal to obj
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof MemoTile)){
            return false;
        }
        MemoTile m = (MemoTile) obj;

        return Integer.compare(m.id, this.id) == 0;
    }
}
