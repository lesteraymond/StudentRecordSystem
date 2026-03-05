import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

    private String fileName;
    private Gson gson;
    private File file;

    /**
     * setPrettyPrinting() — I see her in the best way possible, everything about
     * her looks pretty to me.
     */
    public JSONUtil(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.file = new File(fileName);
    }

    public void save(String data) {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String read() {
        if (!file.exists()) {
            return null;
        }

        StringBuilder str = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            int data;
            while ((data = reader.read()) != -1) {
                str.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.isEmpty() ? null : str.toString();
    }

    public Gson getGSONInstance() {
        return this.gson;
    }
}
