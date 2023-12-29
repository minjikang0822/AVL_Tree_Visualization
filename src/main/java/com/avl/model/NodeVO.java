package com.avl.model;

import lombok.Data;

@Data
public class NodeVO {
	private int key;
	private NodeVO left;
	private NodeVO right;
	private int depth = 0;
	private int height = 0;
	
	// Constructors
	public NodeVO(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	public NodeVO(int key, NodeVO left, NodeVO right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
}
