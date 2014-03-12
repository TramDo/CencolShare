<%@ include file="..\common\header.jsp"%>
<%@ include file="../common/sidebar.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-2 main">
<h1 class="page-header">Group</h1>
<br>
<br>
<table class="table table-bordered">
	<tr>
		<th class="text-center">Group Name</th>
		<th class="text-center">Group Description</th>
		<th class="text-center">Action</th>
	</tr>
<c:forEach items="${requestScope.groups}" var="grp">
  <tr > 
  	<td class="col-sm-2 text-info"><b>${grp.groupName}</b></td>
  	<td>${grp.groupDescription}</td>
  	<td class="col-sm-2 text-center">
  		<a href="${pageContext.request.contextPath}/group/edit/${grp.groupId}" class="btn btn-success">
  			<i class="fa fa-thumbs-up"> Join</i>
  		</a>
  		<a href="${pageContext.request.contextPath}/group/edit/${grp.groupId}" class="btn btn-danger">
  		<i class="fa fa-thumbs-down"> Leave</i>
  		</a>
  		
  		<a href="${pageContext.request.contextPath}/group/delete/${grp.groupId}" class="btn btn-primary">
  			<i class="fa fa-arrow-circle-right"> View</i>
  		</a>
  		</td>
  </tr>
</c:forEach>
</table>
</div>

<%@ include file="..\common\footer.jsp"%>