<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.avl.model.AVLTreeVO" %>
<%@ page import="com.avl.model.NodeVO" %>
<%@ page session="false"%>

<html>
<head>
<meta charset="UTF-8">
<title>AVL Tree Visualization</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css">

<!-- import jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		$('#insertForm').submit(function(e) {
			var newValue = document.getElementById('insertionInput').value;

			if (newValue.length < 1) {
				alert("put a value");
				return false;
			}

			$.ajax({
				type : 'POST',
				url : '/insert',
				data : {
					newValue : newValue
				},

				success : function(res) {
					updateTree();
					alert("SUCCESS");
				},
				error : function(e) {
					alert("ERROR")
					alert(e.status);
				}
			});

			// Prevent the default form submission
			e.preventDefault();
		});
	});
	
	function updateTree(treeInfo) {
		alert("hello");
	}

	function resetTree() {
		alert('resetTree');
	}

	function undo() {
		alert('undo');
	}
</script>

<body class='body'>
	<h1>AVL Tree Visualization</h1>

	<button onclick="resetTree()" class="btn btn-danger">Reset</button>
	<button onclick="undo()" class="btn btn-warning">Undo</button>

	<form class="insertion" id="insertForm">
		<label for="insertionInput">Insertion:</label> <input type="number"
			id="insertionInput" name="insertionInput"> <input
			type="submit" value="Insert" class="btn btn-success">
	</form>

	<form class="deletion">
		<label for="deletionInput">Deletion:</label> <input type="number"
			id="deletionInput" name="deletionInput"> <input type="submit"
			value="Delete" class="btn btn-success">
	</form>
	
	<div class="node-container">
	
	</div>
	




	<c:choose>
		<c:when test="${not empty tree and not empty tree.root}">
			<p>Root Key: ${tree.root.key}</p>
			<!-- 여기서 tree의 다른 속성들을 필요에 따라 출력할 수 있습니다. -->
		</c:when>
		<c:otherwise>
			<p>Tree is empty</p>
		</c:otherwise>
	</c:choose>


</body>
</html>
