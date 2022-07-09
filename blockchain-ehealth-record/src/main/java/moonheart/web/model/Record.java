package moonheart.web.model;

import java.io.Serializable;

public class Record implements Serializable{

    /**
     * business data structure
     * 
     * @author George
     */
    private static final long serialVersionUID = 1L;
    /**
     * unique id
     */
    private String id;
    /**
     * business information
     */
    private String businessInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
}
