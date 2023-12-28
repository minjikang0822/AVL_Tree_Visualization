<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>AVL Tree Visualization</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css">
</head>

<script>
	function insertNode(newValue){
		$.ajax({
			type: 'POST',
			url: '/insert',
			data: 'newValue=' + newValue
			
			/* res holds the response data sent by the server */
			success: function(res){
				
			}
		})
	}

    function resetTree() {
        alert('resetTree');
    }
    
    function undo() {
        alert('undo');
    }
</script>

<body class='body'>
<h1>
	AVL Tree Visualization
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</h1>

<button onclick="resetTree()" class="btn btn-danger">Reset</button>
<button onclick="undo()" class="btn btn-warning">Undo</button>


<form class="insertion">
    <label for="insertionInput">Insertion:</label>

    <input type="number" id="insertionInput" name="insertionInput">

    <input type="submit" value="Insert" class="btn btn-success">
</form>

<form class="deletion">
    <label for="deletionInput">Deletion:</label>

    <input type="number" id="deletionInput" name="deletionInput">

    <input type="submit" value="Delete" class="btn btn-success">
</form>


</body>
</html>
