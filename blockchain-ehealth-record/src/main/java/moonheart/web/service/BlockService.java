package moonheart.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import moonheart.web.model.Block;
import moonheart.web.model.Record;
import moonheart.web.util.BlockCache;
import moonheart.web.util.CryptoUtil;

/**
 * core service of block chain
 * 
 * @author George
 *
 */
@Service
public class BlockService {
    @Autowired
	BlockCache blockCache;
    /**
	 * initialize genesis block
	 * @return
	 */
	public String createGenesisBlock() {
		Block genesisBlock = new Block();
		//set the index of genesis block to 1
		genesisBlock.setIndex(1);
		genesisBlock.setTimestamp(System.currentTimeMillis());
		//pack the records
		List<Record> rdList = new ArrayList<Record>();
		Record rd = new Record();
		rd.setId("1");
		rd.setBusinessInfo("This is genesis block");
		rdList.add(rd);
		Record rd2 = new Record();
		rd2.setId("2");
		rd2.setBusinessInfo("the height is: 1");
		rdList.add(rd2);		
		genesisBlock.setRecord(rdList);
		//calculate the block hash of genesis block
		genesisBlock.setHash(calculateHash("",rdList));
		//添加到已打包保存的业务数据集合中
		blockCache.getPackedRecords().addAll(rdList);
		//添加到区块链中
		blockCache.getBlockChain().add(genesisBlock);
		return JSON.toJSONString(genesisBlock);
	}

    	/**
	 * create new block
	 * @param previousHash
	 * @param hash
	 * @param blockTxs
	 * @return new created block
	 */
	public Block createNewBlock(String previousHash, String hash, List<Transaction> blockTxs) {
		Block block = new Block();
		block.setIndex(blockCache.getBlockChain().size() + 1);
		//timestamp
		block.setTimestamp(System.currentTimeMillis());
		block.setRecords(blockTxs);
		//block hash of previous block
		block.setPreviousHash(previousHash);
		//current block
		block.setHash(hash);
		if (addBlock(block)) {
			return block;
		}
		return null;
	}

    /**
     * add the new created block to the current node
     * 
     * @param newBlock
     */
	public boolean addBlock(Block newBlock) {
		//verify the legitimacy of the new block
		if (isValidNewBlock(newBlock, blockCache.getLatestBlock())) {
			blockCache.getBlockChain().add(newBlock);
			// add the new block to the packed record list
			blockCache.getPackedRecords().addAll(newBlock.getReocrd());
			return true;
		}
		return false;
	}

    /**
	 * validate the legitimacy of block
	 * 
	 * @param newBlock
	 * @param previousBlock
	 * @return if the block is valid
	 */
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
		if (!previousBlock.getHash().equals(newBlock.getPreviousHash())) {
			System.out.println("the previous block hash doesn't match");
			return false;
		} else {
			// 验证新区块hash值的正确
			String hash = calculateHash(newBlock.getPreviousHash(), newBlock.getReocrd());
			if (!hash.equals(newBlock.getHash())) {
				System.out.println("新区块的hash无效: " + hash + " " + newBlock.getHash());
				return false;
			}
			if (!isValidHash(newBlock.getHash())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * left blank for another way of hash value
	 * 
	 * @param hash
	 * @return
	 */
	public boolean isValidHash(String hash) {
		return true;
	}

    /**
	 * validate the whole chain
     * 
	 * @param chain
	 * @return
	 */
	public boolean isValidChain(List<Block> chain) {
		Block block = null;
		Block lastBlock = chain.get(0);
		int currentIndex = 1;
		while (currentIndex < chain.size()) {
			block = chain.get(currentIndex);

			if (!isValidNewBlock(block, lastBlock)) {
				return false;
			}

			lastBlock = block;
			currentIndex++;
		}
		return true;
	}

    /**
	 * replace the local invalid chain
	 * 
	 * @param newBlocks
	 */
	public void replaceChain(List<Block> newBlocks) {
		List<Block> localBlockChain = blockCache.getBlockChain();
		List<Record> localpackedRecords = blockCache.getPackedRecords();
		if (isValidChain(newBlocks) && newBlocks.size() > localBlockChain.size()) {
			localBlockChain = newBlocks;
			//替换已打包保存的业务数据集合
			localpackedRecords.clear();
			localBlockChain.forEach(block -> {
				localpackedRecords.addAll(block.getReocrd());
			});
			blockCache.setBlockChain(localBlockChain);
			blockCache.setPackedRecords(localpackedRecords);
			System.out.println("替换后的本节点区块链："+JSON.toJSONString(blockCache.getBlockChain()));
		} else {
			System.out.println("接收的区块链无效");
		}
	}
    
    /**
	 * calculate the block hash
	 * 
	 * @param previousHash
	 * @param currentRecords
	 * @return hash value of head 
	 */
	public String calculateHash(String previousHash, List<Record> currentRecords) {
		return CryptoUtil.SHA256(previousHash + JSON.toJSONString(currentRecords));
	}
}
