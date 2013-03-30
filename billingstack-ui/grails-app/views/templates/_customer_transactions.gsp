<div class="collection" path="/transactions" filters="{'q.op' : 'eq', 'q.field' : 'customer_id', 'q.value' : config.customer_id}">
	<div class="well">
    <div class="row-fluid">
      <div class="pull-left">
        <a href="#/transactions/0" class="btn btn-primary"><i class="icon-plus icon-white"></i> Create Transaction</a>
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
        <th>Date Created</th>
				<th>Amount</th>
				<th>Status</th>
      </tr>
    </thead>
    <tbody>
      <tr data-ng-repeat="item in items">
	      <td><input type="checkbox" /></td>
	      <td><a href="#/transactions/{{item.id}}">{{item.date_created}}</a></td>
				<td>{{item.amount}}</td>
				<td>{{item.status}}</td>
	    </tr>
    </tbody>
  </table>
  <g:render template="/templates/pagination" />
</div>