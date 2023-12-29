package com.avl.service;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

public class AVLTreeServiceImpl implements AVLTreeService {

	@Override
	public AVLTreeVO insertNode(AVLTreeVO tree, int key) {
		NodeVO NewNode = new NodeVO(key);
		NodeVO crr = tree.getRoot();
		boolean heightChanged = false;
		// find the node to add a new node as its child
		while (true) {
			if (crr.getKey() > key) {
				if (crr.getLeft() == null) {
					crr.setLeft(NewNode);
					if (crr.getRight() == null) {
						heightChanged = true;
					}
					break;
				}
				crr = crr.getLeft();
			}
			else {
				if (crr.getRight() == null) {
					crr.setRight(NewNode);
					if (crr.getLeft() == null) {
						heightChanged = true;
					}
					break;
				}
				crr = crr.getRight();
			}
		}
		NewNode.setDepth(crr.getDepth() + 1);
		
		if (heightChanged) {
			recalculateHeight(NewNode);
			
			NodeVO[] unbalancedInfo = unbalancedA(tree, NewNode);
			NodeVO parentA = unbalancedInfo[0];
			NodeVO A = unbalancedInfo[1];
			
			int BF_A = calculateBF(A);
			// LEFT HEAVY
			if (BF_A > 1) {
				NodeVO L = A.getLeft();
				NodeVO LL = L.getLeft();
				NodeVO LR = L.getRight();
				
				if (LL.getLeft() == NewNode || LL.getRight() == NewNode) {
					rightRotation(tree, parentA, A);
				}
				else if (LR.getLeft() == NewNode) {
					leftRightRotationA(tree, parentA, A);
				}
				else if (LR.getRight() == NewNode) {
					leftRightRotationB(tree, parentA, A);
				}
			}
			// RIGHT HEAVY
			else if (BF_A <-1) {
				NodeVO R = A.getRight();
				NodeVO RL = R.getLeft();
				NodeVO RR = R.getRight();
				
				if (RR.getLeft() == NewNode || RR.getRight() == NewNode) {
					leftRotation(tree, parentA, A);
				}
				else if (RL.getLeft() == NewNode) {
					rightLeftRotationA(tree, parentA, A);
				}
				else if (RL.getRight() == NewNode) {
					rightLeftRotationB(tree, parentA, A);
				}
			}
		}
		
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
	private void rightRotation(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO LL = L.getLeft();
		NodeVO LR = L.getRight();

		L.setRight(A);
		A.setLeft(LR);
		
		int LR_height = (LR != null) ? LR.getHeight() : -1;
		int R_height = (R != null) ? R.getHeight() : -1;
		
		A.setHeight((LR_height > R_height) ? LR_height+1 : R_height+1);
		
		int A_height = (A != null) ? A.getHeight() : -1;
		int LL_height = (LL != null) ? LL.getHeight() : -1;
		L.setHeight((A_height > LL_height) ? A_height+1 : LL_height+1);

		setNewChild(tree, parentA, A, L);
	} // RIGHT ROTATION -------------------------------------

	// CASE 2-A: LEFT RIGHT ROTATION
	private void leftRightRotationA(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
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

		setNewChild(tree, parentA, A, LR);
	}

	// CASE 2-B: LEFT RIGHT ROTATION
	private void leftRightRotationB(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
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

		setNewChild(tree, parentA, A, LR);
	}

	// CASE 3: LEFT ROTATION
	private void leftRotation(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
		NodeVO L = A.getLeft();
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();

		R.setLeft(A);
		A.setRight(RL);

		setNewChild(tree, parentA, A, R);
	}

	// CASE 4-A: RIGHT LEFT ROTATION
	private void rightLeftRotationA(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();
		NodeVO NewNode = RL.getLeft();

		// STEP1: RIGHT ROTATION
		RL.setRight(R);
		R.setLeft(null);

		// STEP2: LEFT ROTATION
		RL.setLeft(A);
		A.setRight(NewNode);

		setNewChild(tree, parentA, A, RL);
	}

	// CASE 4-B: RIGHT LEFT ROTATION
	private void rightLeftRotationB(AVLTreeVO tree, NodeVO parentA, NodeVO A) {
		NodeVO R = A.getRight();
		NodeVO RL = R.getLeft();
		NodeVO NewNode = RL.getLeft();

		// STEP1: RIGHT ROTATION
		RL.setRight(R);
		R.setLeft(NewNode);

		// STEP2: LEFT ROTATION
		RL.setLeft(A);
		A.setRight(null);

		setNewChild(tree, parentA, A, RL);
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

	private void setNewChild(AVLTreeVO tree, NodeVO parent, NodeVO oldChild, NodeVO newChild) {
		if (tree.getRoot() == oldChild) {
			tree.setRoot(newChild);
			newChild.setDepth(0);
			return;
		}
	
		if (parent.getLeft() == oldChild) {
			parent.setLeft(newChild);
		} else {
			parent.setRight(newChild);
		}
	} // setNewChild ---------------------------------------------
	
	private void recalculateHeight(NodeVO childNode) {
		if (childNode.getDepth() == 0) return;
		
		int childVal = childNode.getKey();
		NodeVO parentNode = childNode;
		
		while (parentNode != childNode) {
			if (parentNode.getLeft() == childNode || parentNode.getRight() == childNode) {
				int leftHeight = (parentNode.getLeft() != null)? parentNode.getLeft().getHeight() : -1;
				int rightHeight = (parentNode.getRight() != null)? parentNode.getRight().getHeight() : -1;
				parentNode.setHeight((leftHeight > rightHeight)? leftHeight+1 : rightHeight+1);
				break;
			}
			parentNode = (parentNode.getKey() > childVal)? parentNode.getLeft() : parentNode.getRight();
		}
		
		recalculateHeight(parentNode);
	} // recalculateHeight ------------------------------------

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
