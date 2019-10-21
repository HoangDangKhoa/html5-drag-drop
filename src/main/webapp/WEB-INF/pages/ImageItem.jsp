<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image Items</title>
</head>
<body>
	<div align="center">
		<h1>Image List</h1>
		
		<ul id="columns">
			<c:forEach var="item" items="${listImageItem}">
				<li id="${item.id}" class="column" draggable="true">
					<img src="${item.imageUrl}" width="100px" height="100px">
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
<style>
      li {
        float: left;
		  margin-left: 10px;
		  margin-top: 10px;
      }
      ul {
      	list-style-type: none;
      }
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<script type="text/javascript">
	var listItem = ${listImageItemString};
	var dragSrcEl = null;
	
	function handleDragStart(e) {
	  // Target (this) element is the source node.
	  dragSrcEl = this;
	
	  e.dataTransfer.effectAllowed = 'move';
	  e.dataTransfer.setData('text/html', this.outerHTML);
	  e.dataTransfer.setData('drag-id', this.id);
	
	  this.classList.add('dragElem');
	}
	function handleDragOver(e) {
	  if (e.preventDefault) {
	    e.preventDefault(); // Necessary. Allows us to drop.
	  }
	  this.classList.add('over');
	
	  e.dataTransfer.dropEffect = 'move';  // See the section on the DataTransfer object.
	
	  return false;
	}
	
	function handleDragEnter(e) {
	  // this / e.target is the current hover target.
	}
	
	function handleDragLeave(e) {
	  this.classList.remove('over');  // this / e.target is previous target element.
	}
	
	function handleDrop(e) {
	  // this/e.target is current target element.
	
	  if (e.stopPropagation) {
	    e.stopPropagation(); // Stops some browsers from redirecting.
	  }
	
	  // Don't do anything if dropping the same column we're dragging.
	  if (dragSrcEl != this) {
	    var dragId = e.dataTransfer.getData('drag-id');
	    var dropId = this.id;
	    var oldIndex = listItem.findIndex((item, index) => item.id == dragId);
	  	var newIndex = listItem.findIndex((item, index) => item.id == dropId);
	  	if (oldIndex > newIndex) {
		    this.parentNode.removeChild(dragSrcEl);
		    var dropHTML = e.dataTransfer.getData('text/html');
		    this.insertAdjacentHTML('beforebegin',dropHTML);
		    var dropElem = this.previousSibling;
		    addDnDHandlers(dropElem);		    
		    listItem = reOrderAndSendToServer(dragId, dropId);
		    console.log(listItem);
	  	} else {
	  		this.parentNode.removeChild(dragSrcEl);
		    var dropHTML = e.dataTransfer.getData('text/html');
		    this.insertAdjacentHTML('afterend',dropHTML);
		    var dropElem = this.nextSibling;
		    addDnDHandlers(dropElem);		    
		    listItem = reOrderAndSendToServer(dragId, dropId);
		    console.log(listItem);
	  	}
	  }
	  this.classList.remove('over');
	  return false;
	}
	
	function handleDragEnd(e) {
	  this.classList.remove('over');
	}
	
	function addDnDHandlers(elem) {
	  elem.addEventListener('dragstart', handleDragStart, false);
	  elem.addEventListener('dragenter', handleDragEnter, false)
	  elem.addEventListener('dragover', handleDragOver, false);
	  elem.addEventListener('dragleave', handleDragLeave, false);
	  elem.addEventListener('drop', handleDrop, false);
	  elem.addEventListener('dragend', handleDragEnd, false);
	
	}
	
	var cols = document.querySelectorAll('#columns .column');
	[].forEach.call(cols, addDnDHandlers);
	
	function reOrderAndSendToServer(dragId, dropId) {
		const newIndex = listItem.findIndex((item, index) => item.id == dropId);
		const movedItem = listItem.find((item, index) => item.id == dragId);
	    const remainingItems = listItem.filter((item, index) => item.id != dragId);
	    const reorderedItems = [];

	    remainingItems.forEach((item, index) => {
	        if (index == newIndex) {
	        	reorderedItems.push(movedItem);
	        	reorderedItems.push(item);
	        } else {
	        	reorderedItems.push(item);
	        }
	    });
	    if (newIndex == remainingItems.length) reorderedItems.push(movedItem);
	    reorderedItems.forEach((item, index) => {
	    	item.imageItemOrder.order = index;
	    });
	    var itemString = JSON.stringify(reorderedItems);
	    $.ajax({    //create an ajax request
	        type: "POST",
	        url: ctx + "/update-item-order",        
	        data: itemString,
	        dataType: "json",   //expect json data to be returned                
	        success: function(data){        	
	        	
	        }
	    });

	    return reorderedItems;
	}

</script>



</html>