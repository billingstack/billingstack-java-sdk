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
    </tr>
  </thead>
  <tbody>
    <tr data-ng-repeat="item in items">
      <td><input type="checkbox" /></td>
			<td><a href="{{config.url}}/customer?customer={{item.customer_id}}">{{item.customer_title}}</a></td>
      <td><a href="#/plans/{{item.plan_id}}">{{item.plan_title}}</a></td>
    </tr>
  </tbody>
</table>
<g:render template="/templates/pagination" />