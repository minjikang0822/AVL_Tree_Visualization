package com.avl.model;

import lombok.Data;

@Data
public class NodeVO {
	private int key;
	private NodeVO left;
	private NodeVO right;
	
	// Constructors
	public NodeVO(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	public NodeVO(int key, NodeVO left) {
		this.key = key;
		this.left = left;
		this.right = null;
	}
	
	public NodeVO(int key, NodeVO left, NodeVO right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
}
