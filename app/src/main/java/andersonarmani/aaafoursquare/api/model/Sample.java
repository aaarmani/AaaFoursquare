
package andersonarmani.aaafoursquare.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sample {

    @SerializedName("entities")
    @Expose
    private List<Entity_> entities = null;
    @SerializedName("text")
    @Expose
    private String text;

    public List<Entity_> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity_> entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
