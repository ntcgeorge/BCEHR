package moonheart.web.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import moonheart.web.model.Block;
import moonheart.web.model.Record;

@ConfigurationProperties(prefix = "block")
@Component
public class BlockCache {

	/**
	 * current block structure
	 */
	private List<Block> blockChain = new CopyOnWriteArrayList<Block>();

	/**
	 * packed records
	 */
	private List<Record> packedRecords = new CopyOnWriteArrayList<Record>();
	
	/**
	 * socket object of current node
	 */
	private List<WebSocket> socketsList = new CopyOnWriteArrayList<WebSocket>();
	
	/**
	 * the p2pserver port number of current node
	 */
	@Value("${block.p2pport}")
	private int p2pport;
	
	/**
	 * the address of block to connect
	 */
	@Value("${block.address}")
	private String address;
	
	/**
	 * get the lastest block on the chain
	 * 
	 * @return the latest block on the chain
	 */
	public Block getLatestBlock() {
		return blockChain.size() > 0 ? blockChain.get(blockChain.size() - 1) : null;
	}

	public List<Block> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(List<Block> blockChain) {
		this.blockChain = blockChain;
	}

	public List<Record> getPackedRecords() {
		return packedRecords;
	}

	public void setPackedRecords(List<Record> packedRecords) {
		this.packedRecords = packedRecords;
	}

	public List<WebSocket> getSocketsList() {
		return socketsList;
	}

	public void setSocketsList(List<WebSocket> socketsList) {
		this.socketsList = socketsList;
	}

	public int getP2pport() {
		return p2pport;
	}

	public void setP2pport(int p2pport) {
		this.p2pport = p2pport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}