package moonheart.web.model;

import java.io.Serializable;

/**
 * p2p message
 * @author George
 */

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * message type
     */
    private int type; 
    /**
     * message content
     */
    private String data;

    public Message() {
	}

    public Message(int type) {
        this.type = type;
    }

    public Message(int type, String data) {
		this.type = type;
		this.data = data;
	}

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
}
