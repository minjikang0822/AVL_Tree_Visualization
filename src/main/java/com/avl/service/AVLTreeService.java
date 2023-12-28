package com.avl.service;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

public interface AVLTreeService {
	AVLTreeVO insertNode(AVLTreeVO tree, int key);
	AVLTreeVO deleteNode(AVLTreeVO tree, int key);
	AVLTreeVO deleteNode(AVLTreeVO tree, NodeVO node);
	AVLTreeVO searchNode(AVLTreeVO tree, int key);
}
