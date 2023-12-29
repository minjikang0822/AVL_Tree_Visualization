package com.avl.service;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

public class AVLTreeServiceImpl implements AVLTreeService {

	@Override
	public AVLTreeVO insertNode(AVLTreeVO tree, int key) {
		NodeVO NewNode = new NodeVO(key);
		NodeVO crr = tree.getRoot();
		
		while (true) {
			if (crr.getKey() > key) {
				if (crr.getLeft() == null) {
					crr.setLeft(NewNode);
					break;
				}
				crr = crr.getLeft();
			}
			else {
				if (crr.getRight() == null) {
					crr.setRight(NewNode);
					break;
				}
				crr = crr.getRight();
			}
		}
		NewNode.setDepth(crr.getDepth() + 1);
		
		
		NodeVO rootNode = tree.getRoot();
		rootNode.setDepth(0);
		recalculateDepth(rootNode);
		return null;
	} // insertNode ----------------------------------------

	@Override
	public AVLTreeVO insertNode(int key) {
		// Create new AVL Tree
		return new AVLTreeVO(key);
	} // insertNode ----------------------------------------

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
			} else {
				crr = crr.getRight();
			}
		}

		NodeVO[] result = { crrParent, crr };
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

		L.setRight(A);
		A.setLeft(LR);

		setNewChild(parentA, A, L);
	}

	// CASE 2-A: LEFT RIGHT ROTATION
	private void leftRightRotationA(NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO LR = L.getRight();
		NodeVO NewNode = LR.getRight();

		// STEP1: LEFT ROTATION
		LR.setLeft(L);
		L.setRight(NewNode);

		// STEP2: RIGHT ROTATION
		LR.setRight(A);
		A.setLeft(null);

		setNewChild(parentA, A, LR);
	}

	// CASE 2-B: LEFT RIGHT ROTATION
	private void leftRightRotationB(NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO LR = L.getRight();
		NodeVO NewNode = LR.getRight();

		// STEP1: LEFT ROTATION
		LR.setLeft(L);
		L.setRight(null);

		// STEP2: RIGHT ROTATION
		LR.setRight(A);
		A.setLeft(NewNode);

		setNewChild(parentA, A, LR);
	}

	// CASE 3: LEFT ROTATION
	private void leftRotation(NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();

		R.setLeft(A);
		A.setRight(RL);

		setNewChild(parentA, A, R);
	}

	// CASE 4-A: RIGHT LEFT ROTATION
	private void rightLeftRotationA(NodeVO parentA, NodeVO A) {
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();
		NodeVO NewNode = RL.getLeft();

		// STEP1: RIGHT ROTATION
		RL.setRight(R);
		R.setLeft(null);

		// STEP2: LEFT ROTATION
		RL.setLeft(A);
		A.setRight(NewNode);

		setNewChild(parentA, A, RL);
	}

	// CASE 4-B: RIGHT LEFT ROTATION
	private void rightLeftRotationB(NodeVO parentA, NodeVO A) {
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();
		NodeVO NewNode = RL.getLeft();

		// STEP1: RIGHT ROTATION
		RL.setRight(R);
		R.setLeft(NewNode);

		// STEP2: LEFT ROTATION
		RL.setLeft(A);
		A.setRight(null);

		setNewChild(parentA, A, RL);
	}
	
	private NodeVO[] unbalancedA(AVLTreeVO tree, NodeVO NewNode) {
		int newVal = NewNode.getKey();
		
		NodeVO parentA = tree.getRoot();
		NodeVO A = parentA;
		NodeVO crrParent = parentA;
		for (NodeVO crr=A; crr != NewNode; crr = (crr.getKey() > newVal)? crr.getLeft() : crr.getRight()) {
			if (Math.abs(calculateBF(crr)) > 1) {
				parentA = crrParent;
				A = crr;
			}
			crrParent = crr;
		}
		
		NodeVO[] result = {parentA, A};
		return result;
	} // unbalancedA ---------------------------------------------

	private void setNewChild(NodeVO parent, NodeVO oldChild, NodeVO newChild) {
		if (parent.getLeft() == oldChild) {
			parent.setLeft(newChild);
		} else {
			parent.setRight(newChild);
		}
	} // setNewChild ---------------------------------------------

	private void recalculateDepth(NodeVO parentNode) {
		int parentDepth = parentNode.getDepth();

		NodeVO leftChild = parentNode.getLeft();
		NodeVO rightChild = parentNode.getRight();

		if (leftChild != null) {
			leftChild.setDepth(parentDepth + 1);
			recalculateDepth(leftChild);
		}

		if (rightChild != null) {
			rightChild.setDepth(parentDepth + 1);
			recalculateDepth(rightChild);
		}
	} // recalculateDepth ---------------------------------------------

} ////////////////////////////////////////////////////////////
