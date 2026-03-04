import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class UserManager {

    private List<User> existingUsers;
    private JSONUtil jsonUtil;

    public UserManager() {
        existingUsers = new ArrayList<>();
        jsonUtil = new JSONUtil("data.json");
    }

    public boolean add(User user, boolean save) {
        if (isExists(user)) {
            System.out.println("User cannot be duplicated!");
            return false;
        }

        existingUsers.add(user);
        if (save) {
            String data = jsonUtil.getGSONInstance().toJson(existingUsers);
            jsonUtil.save(data);
        } else {
            System.out.println("You cancelled it.");
        }
        return true;
    }

    public boolean isExists(User user) {
        Type typeList = new TypeToken<List<User>>() {
        }.getType();

        String data = jsonUtil.read();
        if (data == null) {
            return false;
        }

        existingUsers = jsonUtil.getGSONInstance().fromJson(data, typeList);
        if (existingUsers != null) {
            return existingUsers
                    .stream()
                    .anyMatch(existingUser -> existingUser.getId().equals(user.getId()));
        }

        return false;
    }

    public List<User> getUsers() {
        return existingUsers;
    }

    public JSONUtil getJsonUtil() {
        return jsonUtil;
    }
}
