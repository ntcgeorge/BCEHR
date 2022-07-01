# Block Chain Electronic Health Records

Description: Vanilla version of electronic health record on private block chain.

Tools Pack: Java, Maven, Spring Boot

Created on: Jul 1 2022



## 1. Project Overview -- Where Two Streams Meets

As Satoshi Nakamoto suggested on his passage [Bitcoin: A Peer-to-Peer Electronic Cash System](https://bitcoin.org/bitcoin.pdf)，transactions can be stored on the distributed ledgers using block chain technology.  This revolutionary design is called block chain and the transaction ledger is the famous Bitcoin. The block chain technology then be modified and developed to become extensible and customizable which can maintain the records immutable and execute smart contract -- a set of pre-defined rules for transactions. Another motivation about this project is the need for a better way of organizing health record data for conducting research and secure data sharing method for broader collaboration with commercial organizations especially with Insurance Companies. That is where two streams , block chain technology and traditional public health discipline meets -- Block Chain Electronic Health Records.

The System includes applications below:

1. Consortium block chains (行業區塊鏈)

2. Researcher Interface

     -  Data cleaning and records traversing.
     -  Verified portal for paper data reproducing.
     -  Built-in Analysis Algorithm

3. Insurers Interface

   - Built-in Pricing Model or Customized Model
   - Group Risk Monitoring

Multiple organizations share the responsibilities of maintaining the block chain. The chain is not visible to any other organizations than the chain members. It provides API to Researcher and Insurers to implement sophisticated and data-driven models without any possibility of leaking individual information but still holding the capacity of enormous data, the encryption algorithm and consensus mechanism ensures the authenticity of the records and data safety.

The head of every block contains the information fields of every block and the corresponding block hash which is generated based on the block head, the head also remember the previous block.



##  2. Consortium Block Chain

To the contrary of the public property of traditional block chain, health records need more exclusive membership, that means the system only remain  accessible to a certain group of organizations such as Hospital Unions, Hospital Authority and Universities. The chain remains invisible to other organizations.

Every block memorize the previous hash block, it points to the previous block. Since the blocks are generated on chronological order, the chain can present the time relationship very clearly.



### 2.1  Block Structure

For every single block, it has head and meta data, the meta data stores of all the information of a single visit to hospital of a single patient, for example, it can record the admission date, lab test date and result, diagnosis record, prescriptions and so on. The head of every block contains:

- Self block hash: the hash value of all the text fields except for itself 
- Previous block hash: the hash value of father block
- Time stamp: records the birth time of this block
- Merkle root: the hash value of all the text record of this block









