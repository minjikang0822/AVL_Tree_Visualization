package com.avl.model;

import lombok.Data;


@Data
public class AVLTreeVO {
	private NodeVO root;
	
	public AVLTreeVO(int root_key) {
		this.root = new NodeVO(root_key);
	}
}
