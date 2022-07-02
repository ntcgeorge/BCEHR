package moonheart.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * Block Structure
 * 
 * @author George
 */



public class Block implements Serializable {
    private static  final long  serialVersionUID = 1L;
    /**
     * block Index
     */
    private int index;
    /**
     * blcok hash
     */
    private String hash;
    /**
     * previous block hash
     */
    private String previousHash;
    /**
     * time stamp of block
     */
    private long timestamp;
    /**
     * proof of work
     */
    private int nonce;
    /**
     * the health record of single visit of single patient
     */
    private List<Records> records;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timeStamp) {
        this.timestamp = timeStamp;
    }

    public List<Records> getReocrds() {
        return records;
    }

    public void setTimestamp(List<Records> records) {
        this.records = records;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
