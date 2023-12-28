package com.avl.mapper;

import org.springframework.stereotype.Repository;

import com.avl.model.AVLTreeVO;
import com.avl.model.NodeVO;

// cmd + shift + O => auto import

@Repository
public interface AVLTreeMapper {
	AVLTreeVO insertNode(AVLTreeVO tree, int key);
	AVLTreeVO insertNode(int key);
	AVLTreeVO deleteNode(AVLTreeVO tree, int key);
	AVLTreeVO deleteNode(AVLTreeVO tree, NodeVO node);
	NodeVO[] searchNode(AVLTreeVO tree, int key);
}
