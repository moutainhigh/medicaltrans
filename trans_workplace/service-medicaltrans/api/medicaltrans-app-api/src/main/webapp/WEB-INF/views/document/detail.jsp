<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/static/default/document/css/pure-min.css">
<script src="${ctx}/static/default/document/js/json/c.js" type="text/javascript"></script>
<link href="${ctx}/static/default/document/js/json/s.css" type="text/css" rel="stylesheet"></link>
<title>${data.name}</title>
<style type="text/css">
body {
	padding: 0px 20px
}
</style>
</head>
<body>
	<div class="pure-g">
		<h1>接口方法说明</h1>
	</div>
	<div class="pure-g">
		<div class="pure-u-1-8">
			<b>方法名称: </b>
		</div>
		<div class="pure-u-7-8">${data.name}</div>
		<div class="pure-u-1-8">
			<b>接口方法调用路径:</b>
		</div>
		<div class="pure-u-7-8">${ctx}${data.url}<c:if test="${empty urlSuffix}"></c:if></div>
		<div class="pure-u-1-8">
			<b>服务接口类名称: </b>
		</div>
		<div class="pure-u-7-8">${data.className}</div>
		<div class="pure-u-1-8">
			<b>调用类方法: </b>
		</div>
		<div class="pure-u-7-8">${data.methodName}</div>
		<div class="pure-u-1-8">
			<b>请求方式: </b>
		</div>
		<div class="pure-u-7-8">${data.methods}</div>
		<div class="pure-u-1-8">
			<b>方法描述: </b>
		</div>
		<div class="pure-u-7-8">${data.description}</div>
	</div>
	<table class="pure-table" width="100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>参数名称</th>
				<th>参数描述</th>
				<th>参数类型</th>
				<th>允许为空</th>
				<th>长度限制</th>
				<th>是否为数组</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="param1" items="${data.paramDescs}" varStatus="idx1">
				<tr>
					<td>${idx1.index+1}</td>
					<td>${param1.name}</td>
					<td>${param1.description}</td>
					<td>${param1.type}</td>
					<td><c:if test="${param1.nullable}">是</c:if> <c:if
							test="${not param1.nullable}">否</c:if></td>
					<td>${param1.length}</td>
					<td><c:if test="${param1.islist}">是</c:if> <c:if
							test="${not param1.islist}">否</c:if></td>
					<td>${param1.meto}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<div class="pure-g">
		<div class="pure-u-1-2">
			<input type="hidden" id="req_url" value="${ctx}${data.url}.json">
			<input type="hidden" id="req_method" value="${data.methods}">
			<form class="pure-form pure-form-aligned" onsubmit="return false"
				accept-charset="UTF-8" id="form1"
				<c:if test="${data.hasfile}">enctype="multipart/form-data"</c:if>>
				<fieldset>
					<legend>接口测试</legend>
					<c:forEach var="item" items="${data.paramDescs}">
						<div class="pure-control-group">
							<c:set var="end" value="0"></c:set>
							<c:if test="${item.islist }">
								<c:set var="end" value="0"></c:set>
							</c:if>
							<c:forEach begin="0" end="${end}">
								<c:choose>
									<c:when test="${item.type=='file' }">
										<label>${item.description}(${item.name}):</label>
										<input type="file" name="${item.name }">&nbsp;&nbsp;${item.type}<c:if
											test="${!item.nullable }">
											<font color="red">*</font>
										</c:if>
										<br>
									</c:when>
									<c:when test="${item.type=='date' }">
										<label>${item.description}(${item.name}):</label>
										<input type="text" name="${item.name }" style="height: 30px"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${item.format}'})"
											class="Wdate">&nbsp;&nbsp;${item.type}<c:if
											test="${!item.nullable }">
											<font color="red">*</font>
										</c:if>
										<br>
									</c:when>
									<c:otherwise>
										<label>${item.description}(${item.name}):</label>
										<input type="text" name="${item.name }"
											<c:if test="${item.type=='long' or item.type=='int'}">onkeyUp = "DigitInput(this,event);"</c:if>
											<c:if test="${item.type=='float'}">onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)"</c:if>>&nbsp;&nbsp;${item.type}<c:if
											test="${!item.nullable }">
											<font color="red">*</font>

										</c:if>
										<br>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</c:forEach>

                    <!--添加token begin-->
                    <div class="pure-control-group">
                        <label>令牌(token):</label>
                        <input type="text" name="token">&nbsp;&nbsp;String<font color="red">*</font>
                        <br>
                    </div>
                    <!--添加token end-->

					<div class="pure-controls">
						<input type="submit" value="提交" id="submitBtn"
							class="pure-button pure-button-primary">
					</div>
				</fieldset>
			</form>
		</div>
		<div class="pure-u-1-2" style="padding-top: 15px">
			<div class="pure-u-1">
				<b>请求参数:</b>
				<div id="request_param"
					style="overflow: scroll; border: 1px solid #CECECE; background: none repeat scroll 0 0 #ECECEC; border-radius: 3px; color: black; height: 80px">

				</div>
				<br> <b>返回结果:</b>
			</div>
			<div id="ControlsRow" class="pure-u-1">
				<input type="Button" value="格式化" onclick="Process()" /> <span
					id="TabSizeHolder"> 缩进量 <select id="TabSize"
					onchange="TabSizeChanged()">
						<option value="1">1</option>
						<option value="2" selected="true">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
				</select>
				</span> <label for="QuoteKeys"> <input type="checkbox"
					id="QuoteKeys" onclick="QuoteKeysClicked()" checked="true" /> 引号
				</label>&nbsp; <a href="javascript:void(0);" onclick="SelectAllClicked()">全选</a>
				&nbsp; <span id="CollapsibleViewHolder"> <label
					for="CollapsibleView"> <input type="checkbox"
						id="CollapsibleView" onclick="CollapsibleViewClicked()"
						checked="true" /> 显示控制
				</label>
				</span> <span id="CollapsibleViewDetail"> <a
					href="javascript:void(0);" onclick="ExpandAllClicked()">展开</a> <a
					href="javascript:void(0);" onclick="CollapseAllClicked()">叠起</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(3)">2级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(4)">3级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(5)">4级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(6)">5级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(7)">6级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(8)">7级</a> <a
					href="javascript:void(0);" onclick="CollapseLevel(9)">8级</a>
				</span>
			</div>
			<div
				style="height: 275px; overflow: auto; margin-bottom: 0px; opacity: 1;"
				class="Canvas well resizable processed" id="Canvas"></div>
			<div class="grippie" style="margin-right: 0px;"></div>
			<textarea id="RawJson" rows="0" cols="0"
				style="width: 0px; height: 0px"></textarea>
		</div>
	</div>
	<div id="output1">AJAX response will replace this content.</div>


	<%--<script src="${ctx }/static/default/document/js/base/jquery-1.7.2.min.js"--%>
	<script src="${ctx }/static/default/component/jquery-2.0.2/jquery-2.0.2.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		<%--src="${ctx }/static/default/document/js/base/jquery.form.min.js"></script>--%>
		src="${ctx }/static/default/component/jquery-2.0.2/jquery-form.min.js"></script>
	<script src="${ctx }/static/default/document/js/json/c.js"
		type="text/javascript"></script>
	<script src="${ctx }/static/default/document/js/date/WdatePicker.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		window.ImgCollapsed = "${ctx }/static/default/document/js/json/Collapsed.gif";
		window.ImgExpanded = "${ctx }/static/default/document/js/json/Expanded.gif";
	</script>

	<script type="text/javascript">
		String.prototype.formaturl = function() {
			var args = arguments;
			return this.replace(/\{(\w+)\}/g, function(m, i) {
				//alert("m:" + m + "i:" + i);
				//return args[0]['' + i];
				if (document.getElementsByName(i).length == 0) {
					return m;
				}
				return document.getElementsByName(i)[0].value;
			});
		};

		//alert("{dd}adsf{d}".format({dd:1, d:2}));

		// prepare the form when the DOM is ready 
		$(document).ready(function() {

			var requrl = document.getElementById('req_url').value.formaturl();
			var reqmethod = document.getElementById('req_method').value;
			var type = 'post';
			if (reqmethod.indexOf('GET') >= 0) {
				type = 'get';
			}
			var options = {
				target : '#output1', // target element(s) to be updated with server response 
				beforeSubmit : showRequest, // pre-submit callback 
				success : showResponse, // post-submit callback 
				dataType : 'text',
				url : requrl,
				type : type,
				timeout : 200000

			// other available options: 
			//dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
			//clearForm: true        // clear all form fields after successful submit 
			//resetForm: true        // reset the form after successful submit 

			// $.ajax options can be used here too, for example: 
			};

			// bind form using 'ajaxForm' 
			$('#form1').submit(function() {
				$(this).ajaxSubmit(options);
				return false;
			});
		});

		// pre-submit callback 
		function showRequest(formData, jqForm, options) {
			var queryString = $.param(formData);
			$('#request_param').html(queryString);
			return true;
		}

		// post-submit callback 
		function showResponse(responseText, statusText, xhr, $form) {
			$('#RawJson').val(responseText);
			Process();
			//alert(responseText.toString());
			//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
			//'\n\nThe output div should have already been updated with the responseText.'); 
		}

		function clearNoNum(event, obj) {
			//响应鼠标事件，允许左右方向键移动 
			event = window.event || event;
			if (event.keyCode == 37 | event.keyCode == 39) {
				return;
			}
			//先把非数字的都替换掉，除了数字和. 
			obj.value = obj.value.replace(/[^\d.]/g, "");
			//必须保证第一个为数字而不是. 
			obj.value = obj.value.replace(/^\./g, "");
			//保证只有出现一个.而没有多个. 
			obj.value = obj.value.replace(/\.{2,}/g, ".");
			//保证.只出现一次，而不能出现两次以上 
			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "")
					.replace("$#$", ".");
		}
		function checkNum(obj) {
			//为了去除最后一个. 
			obj.value = obj.value.replace(/\.$/g, "");
		}

		function DigitInput(obj, event) {
			//响应鼠标事件，允许左右方向键移动 
			event = window.event || event;
			if (event.keyCode == 37 | event.keyCode == 39) {
				return;
			}
			obj.value = obj.value.replace(/\D/g, "");
		}
	</script>
</body>
</html>