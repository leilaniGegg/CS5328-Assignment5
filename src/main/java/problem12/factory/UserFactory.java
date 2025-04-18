package problem12.factory;


import problem12.controller.RideController;
import problem12.ui.UI;
import problem12.ui.UIFactory;
import problem12.ui.UserUI;

public class UserFactory implements UIFactory {
    private RideController controller;

    public UserFactory(RideController controller) {
        this.controller = controller;
    }

    @Override
    public UI createUI() {
        return new UserUI(controller);
    }
}
