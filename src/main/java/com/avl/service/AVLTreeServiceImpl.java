package com.avl.service;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

public class AVLTreeServiceImpl implements AVLTreeService{

	@Override
	public AVLTreeVO insertNode(AVLTreeVO tree, int key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AVLTreeVO insertNode(int key) {
		// Create new AVL Tree
		return new AVLTreeVO(key);
	}

	@Override
	public AVLTreeVO deleteNode(AVLTreeVO tree, int key) {
		// Find the node with the specified key value and delete it
		return null;
	}

	@Override
	public AVLTreeVO deleteNode(AVLTreeVO tree, NodeVO node) {
		// Delete selected node
		return null;
	}

	@Override
	public NodeVO[] searchNode(AVLTreeVO tree, int key) {
		// TODO Auto-generated method stub
		NodeVO crr = tree.getRoot();
		NodeVO crrParent = crr;
		
		while (crr.getKey() != key) {
			int crrKey = crr.getKey();
			crrParent = crr;
			if (crrKey > key) {
				crr = crr.getLeft();
			}
			else {
				crr = crr.getRight();
			}
		}
		
		NodeVO[] result = {crrParent, crr};
		return result;
	}
	
	// Calculate Balance Factor
	public int calculateBF(NodeVO crrNode) {
		// Use ternary operator as a conditional expression
		int leftHeight = (crrNode.getLeft() != null) ? crrNode.getLeft().getHeight() : -1;
		int rightHeight = (crrNode.getRight() != null) ? crrNode.getRight().getHeight() : -1;
		int balance = leftHeight - rightHeight;
		return balance;
	}

}
