package pro.drihulias.testapp.models;

import java.util.ArrayList;
import java.util.List;

public class UserBLModel {
    public String name;
    public String avatarImage;
    public List<String> items;

    public UserBLModel() {
    }

    public UserBLModel(String image, String name, List<String> items) {
        this.name = name;
        this.avatarImage = image;
        this.items = new ArrayList<>(items);
    }
}
