package problem12.factory;

import problem12.controller.RideController;
import problem12.ui.DriverUI;
import problem12.ui.UI;
import problem12.ui.UIFactory;

public class DriverFactory implements UIFactory {
    private RideController controller;

    public DriverFactory(RideController controller) {
        this.controller = controller;
    }

    @Override
    public UI createUI() {
        return new DriverUI(controller);
    }
}
