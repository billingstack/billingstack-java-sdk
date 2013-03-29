<%--
<div class="collection" path="/subscriptions" filters="{'customer_id' : config.customer_id}">
--%>
<div class="collection" path="/subscriptions" filters="{'q.op' : 'eq', 'q.name' : 'customer_id', 'q.value' : config.customer_id}">
	<div class="well">
	  <div class="row-fluid">
	    <div class="pull-left">
	      <a href="#/subscriptions/0" class="btn btn-primary"><i class="icon-plus icon-white"></i> Create Subscription</a>
	    </div>
	    <div class="pull-right">
	      <a data-ng-click="refresh()" class="btn"><i class="icon-refresh"></i> Refresh</a>
	    </div>
	  </div>
	</div>
	<table class="table table-stripped">
	  <thead>
	    <tr>
	      <th class="fit"><br /></th>
	      <th>Plan</th>
	    <th class="fit"><br /></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr data-ng-repeat="item in items" class="resource" path="/subscriptions/{{item.id}}">
	      <td><input type="checkbox" /></td>
	      <td><a href="{{config.url}}/merchant#/plans/{{item.plan_id}}">{{item.plan_title}}</a></td>
	      <td class="btn-group">
	        <button data-toggle="dropdown" class="btn dropdown-toggle">Actions <span class="caret"></span></button>
	        <ul class="dropdown-menu">
	          <li><a href="#/subscriptions/{{item.id}}"><i class="icon-pencil"></i> Edit</a></li>
	          <li><a href="#/subscriptions/{{item.id}}/usage"><i class="icon-list"></i> Show Usage</a></li>
	          <li><a href="#/subscriptions/{{item.id}}/usage/0"><i class="icon-plus"></i> Create Usage</a></li>
	          <li class="divider"></li>
	          <li><a ng-click="bill()"><i class="icon-certificate"></i> Bill Now</a></li>
	          <li class="divider"></li>
	          <li><a ng-click="remove()"><i class="icon-remove"></i> Delete</a></li>
	        </ul>
	      </td>
	   </tr>
	  </tbody>
	</table>
	<g:render template="/templates/pagination" />
</div>