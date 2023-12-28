package com.avl.service;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

public class AVLTreeServiceImpl implements AVLTreeService{

	@Override
	public AVLTreeVO insertNode(AVLTreeVO tree, int key) {
		// TODO Auto-generated method stub
		return null;
	}  // insertNode ----------------------------------------
	
	@Override
	public AVLTreeVO insertNode(int key) {
		// Create new AVL Tree
		return new AVLTreeVO(key);
	}  // insertNode ----------------------------------------

	@Override
	public AVLTreeVO deleteNode(AVLTreeVO tree, int key) {
		// Find the node with the specified key value and delete it
		return null;
	} // deleteNode ----------------------------------------

	@Override
	public AVLTreeVO deleteNode(AVLTreeVO tree, NodeVO node) {
		// Delete selected node
		return null;
	} // deleteNode ----------------------------------------

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
	} // searchNode ----------------------------------------
	
	// Calculate Balance Factor
	private int calculateBF(NodeVO crrNode) {
		// Use ternary operator as a conditional expression
		int leftHeight = (crrNode.getLeft() != null) ? crrNode.getLeft().getHeight() : -1;
		int rightHeight = (crrNode.getRight() != null) ? crrNode.getRight().getHeight() : -1;
		int balance = leftHeight - rightHeight;
		return balance;
	} // calculateBF ----------------------------------------
	
	// CASE 1: RIGHT ROTATION
	private void rightRotation(NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO LR = L.getRight();
		
		
	}
	
	// CASE 3: LEFT ROTATION
	private void leftRotation(NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();
		
		R.setLeft(A);
		A.setRight(RL);
		
		if (parentA.getLeft() == A) {
			parentA.setLeft(R);
		}
		else {
			parentA.setRight(R);
		}
	}
	
	private void setNewChild(NodeVO parent, NodeVO oldChild, NodeVO newChild) {
		if (parent.getLeft() == oldChild) {
			parent.setLeft(newChild);
		}
		else {
			parent.setRight(newChild);
		}
	}

} ////////////////////////////////////////////////////////////
