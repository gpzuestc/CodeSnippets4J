package com.gpzuestc.hash.consistent;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gpzuestc.hash.Node;


public final class NodeLocator {
	
	private TreeMap<Long, Node> nodesMap;
	private HashAlgorithm hashAlg;
	private int numReps = 160;
	private List<Node> nodes;
	
    public NodeLocator(List<Node> nodes, HashAlgorithm alg, int nodeCopies) {
		hashAlg = alg;
		nodesMap=new TreeMap<Long, Node>();
		
        numReps= nodeCopies;
        this.nodes = nodes;
		for (Node node : nodes) {
			for (int i = 0; i < numReps / 4; i++) {
				byte[] digest = hashAlg.computeMd5(node.getName() + i);
				for(int h = 0; h < 4; h++) {
					long m = hashAlg.hash(digest, h);
					
					nodesMap.put(m, node);
				}
			}
		}
    }

	public Node getPrimary(final String k) {
		byte[] digest = hashAlg.computeMd5(k);
		Node rv=getNodeForKey(hashAlg.hash(digest, 0));
		return rv;
	}

	Node getNodeForKey(long hash) {
		final Node rv;
		Long key = hash;
		if(!nodesMap.containsKey(key)) {
			SortedMap<Long, Node> tailMap=nodesMap.tailMap(key);
			if(tailMap.isEmpty()) {
				key=nodesMap.firstKey();
			} else {
				key=tailMap.firstKey();
			}
			//For JDK1.6 version
//			key = ketamaNodes.ceilingKey(key);
//			if (key == null) {
//				key = ketamaNodes.firstKey();
//			}
		}
		
		
		rv=nodesMap.get(key);
		return rv;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

}
