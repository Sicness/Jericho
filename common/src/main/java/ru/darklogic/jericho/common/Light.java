package ru.darklogic.jericho.common;

/**
 * Created by Sicness on 25.10.2014.
 */
public class Light {
    int ch;
    int type;
    int mode;
    int state;

    public int MODE_MANUAL = 0;
    public int MODE_AUTO = 1;
    public int TYPE_REGULAR = 2;
    public int TYPE_NOT_REGULAR = 3;
    public int VALUE_MAX = 120;
    public int VALUE_MIN = 0;


    public Light(int ch, int type, int mode) {
        // TODO: exception on wrong ch
        this.ch = ch;
        this.type = type;
        this.mode = mode;
    }

    public Light(int ch, int type) {
        // TODO: exception on wrong ch
        this.ch = ch;
        this.type = type;
        this.mode = MODE_MANUAL;
    }

    public void set(boolean enable) {
        if (enable) {
            state = VALUE_MAX;
            // TODO: send
        } else {
            state = 0;
            // TODO: send
        }
    }

    public void set(int value) {
        if (type == TYPE_NOT_REGULAR) {
            System.out.println("Error");
            // TODO: Execption
        }
        if ((value < VALUE_MIN) || (value > VALUE_MAX)) {
            System.out.println("Error");
            // TODO: Execption
        }
        state = value;
        // TODO: send
    }

    public int getState() {
        return state;
    }

    public int getMode() {
        return mode;
    }

    public int getType() {
        return type;
    }
}
