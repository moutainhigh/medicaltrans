<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/static/default/document/css/pure-min.css">
<title>服务接口列表</title>
<style type="text/css">
body {
	padding: 0px 20px
}
</style>
</head>
<body>
	<div class="pure-g">
		<h1>服务接口列表</h1>
	</div>

	<!-- 接口清单 -->
	<div class="pure-g">
		<c:forEach var="item" items="${data}" step="1" varStatus="idx">
			<div class="pure-u-1-5">
				<p>
					<a href="#${item.className}" class="pure-button pure-button-primary"
						title="${ item.description}">${idx.index+1},${item.name}</a>
				</p>
			</div>
		</c:forEach>
	</div>
	<hr>


	<!-- 接口名细 -->
	<div class="pure-g">
		<c:forEach var="item" items="${data }" step="1" varStatus="idx">
			<div class="pure-u-1-8">
				<b>${idx.index+1}, 服务接口名称: </b>
			</div>
			<div class="pure-u-7-8">
				<a name="${item.className }">${item.name}</a>
			</div>
			<div class="pure-u-1-8">
				<b>服务接口类名:</b>
			</div>
			<div class="pure-u-7-8">${item.className }</div>
			<div class="pure-u-1-8">
				<b>服务接口描述:</b>
			</div>
			<div class="pure-u-7-8">${ item.description}</div>
			<div class="pure-u-1">
				<table class="pure-table" width="100%">
					<thead>
						<tr>
							<th>序号</th>
							<th>方法名称</th>
							<th>方法描述</th>
							<th>请求URL</th>
							<th>调用类方法</th>
							<th>请求方式</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="method" items="${item.methods}" varStatus="idx1">
							<tr>
								<td>${idx1.index+1}</td>
								<td><a target="_blank"
									href="${ctx}/rest-api/document.do?method=detail&className=${method.className}&url=${method.url}">${method.name}</a></td>
								<td>${method.description}</td>
								<td>${method.url}</td>
								<td>${method.methodName}</td>
								<td>${method.methods}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>
	<div style="position: fixed; right: 50px; bottom: 100px;">
        <button onclick="window.scroll(0, 0);" style="width: 45px; height: 45px; background-color: dodgerblue">TOP</button>
	</div>
</body>
</html>