
package andersonarmani.aaafoursquare.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lists {

    @SerializedName("groups")
    @Expose
    private List<Group__> groups = null;

    public List<Group__> getGroups() {
        return groups;
    }

    public void setGroups(List<Group__> groups) {
        this.groups = groups;
    }

}
