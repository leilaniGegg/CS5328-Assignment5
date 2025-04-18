package problem12.ui;

import problem12.controller.RideController;

public abstract class UI {
    protected RideController controller;

    public UI(RideController controller) {
        this.controller = controller;
    }
}
