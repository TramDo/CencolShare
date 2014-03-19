<%@ include file="..\common\header.jsp"%>

<sec:authorize access="isAuthenticated()"> 
	<%@ include file="../common/sidebar.jsp"%>
</sec:authorize>

<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-2 main">
		
	<div class="row">
			<div class="col-sm-2 text-center">
				<img class="pull-left " width="120px" height="120px" alt=""
					src="${group.groupImage}">
			</div>
			<div class="col-sm-10">

				<h2 class="text-primary">${group.groupName}</h2>
				<p class="text-info">${group.groupDescription} </p>
				<span
					class="label label-warning">Members:1222</span>
			
<span class="pull-right">
<sec:authorize access="isAuthenticated()"> 

	<c:if test="${not (group.user.userId ==loggedInUser.userId)}">
    <c:if test="${check=='1'}">
	<a href="${pageContext.request.contextPath}/group/add/${group.groupId}" class="btn btn-success">
  			<i class="fa fa-thumbs-up"> Join</i></a>
  			</c:if>
  			<c:if test="${check=='0'}">
  			<a href="${pageContext.request.contextPath}/group/remove/${group.groupId}" class="btn btn-danger">
  		<i class="fa fa-thumbs-down"> Leave</i>
  		</a></c:if>
  		</c:if>
</sec:authorize>
			</div>

		</div>
		<hr class="colorgraph" />
		
	
</div>

<%@ include file="..\common\footer.jsp"%>
		