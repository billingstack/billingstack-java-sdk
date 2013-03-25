<div class="page-header">
  <h1>Subscriptions <small></small></h1>
</div>
<div class="well">
  <div class="row-fluid">
    <div class="pull-left">
      
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
      <th>Customer</th>
			<th>Plan</th>
			<th class="fit"><br /></th>
    </tr>
  </thead>
  <tbody>
    <tr data-ng-repeat="item in items">
      <td><input type="checkbox" /></td>
			<td><a href="{{config.url}}/customer?customer={{item.customer_id}}">{{item.customer_title}}</a></td>
      <td><a href="#/plans/{{item.plan_id}}">{{item.plan_title}}</a></td>
			<td class="btn-group">
        <button data-toggle="dropdown" class="btn dropdown-toggle">Actions <span class="caret"></span></button>
        <ul class="dropdown-menu">
          <li><a href="#/subscriptions/{{item.id}}"><i class="icon-pencil"></i> Edit</a></li>
          <li><a href="#/usage?subscription_id={{item.id}}"><i class="icon-list"></i> Show Usage</a></li>
          <li><a href="#/usage/0?subscription_id={{item.id}}"><i class="icon-plus"></i> Create Usage</a></li>
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