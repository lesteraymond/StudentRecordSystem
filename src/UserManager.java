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
        save(save);
        return true;
    }

    public void save(boolean save) {
        if (save) {
            String data = jsonUtil.getGSONInstance().toJson(existingUsers);
            jsonUtil.save(data);
        } else {
            System.out.println("You cancelled it.");
        }
    }

    public void delete(User user, boolean save) {
        existingUsers.remove(user);
        save(save);
    }

    public void edit(int userIndex, String mode, String newValue) {
        if (mode.equals("id")) {
            setID(userIndex, newValue);
        } else if (mode.equals("name")) {
            setName(userIndex, newValue);
        } else if (mode.equals("age")) {
            setAge(userIndex, newValue);
        } else if (mode.equals("course")) {
            setCourse(userIndex, newValue);
        }
    }

    private void setID(int userIndex, String value) {
        existingUsers.get(userIndex).setId(value);
    }

    private void setName(int userIndex, String value) {
        existingUsers.get(userIndex).setName(value);
    }

    private void setAge(int userIndex, String value) {
        existingUsers.get(userIndex).setAge(Integer.parseInt(value));
    }

    private void setCourse(int userIndex, String value) {
        existingUsers.get(userIndex).setCourse(value);
    }

    public int searchUserIndexByID(String id) {
        Type typeList = new TypeToken<List<User>>() {
        }.getType();

        String data = jsonUtil.read();
        if (data == null) {
            return -1;
        }

        existingUsers = jsonUtil.getGSONInstance().fromJson(data, typeList);
        for (int i = 0; i < existingUsers.size(); i++) {
            if (existingUsers.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void showUserInfo(User user) {
        System.out.println("User #" + user.getId());
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Course: " + user.getCourse());
    }

    public void showUserInfo(int userIndex) {
        System.out.println("User #" + existingUsers.get(userIndex).getId());
        System.out.println("Name: " + existingUsers.get(userIndex).getName());
        System.out.println("Age: " + existingUsers.get(userIndex).getAge());
        System.out.println("Course: " + existingUsers.get(userIndex).getCourse());
    }

    public User getCurrentUserByID(String userID) {
        int index = searchUserIndexByID(userID);
        if (index == -1)
            return null;

        return existingUsers.get(index);
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
