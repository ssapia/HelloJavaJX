package net.ssapia.address;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by salvador on 25/04/15.
 */
public class LoadResource {

    private final FXMLLoader loader;

    public LoadResource(String resources) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(MainApp.class.getResource(resources));
    }

    public <T extends Parent> T get() throws IOException {
        return (T) loader.load();
    }

    public <T> T getController() {
        return (T) loader.getController();
    }
}
