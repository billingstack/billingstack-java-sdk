<div class="collection" path="/invoices" filters="{'customer_id' : config.customer_id}">
	<div class="well">
	  <div class="row-fluid">
	    <div class="pull-left">
	      <a href="#/invoices/0" class="btn btn-primary"><i class="icon-plus icon-white"></i> Create Invoice</a>
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
	      <th>ID</th>
	      <th>Subtotal</th>
	      <th>Tax Percentage</th>
	      <th>Tax Total</th>
	      <th>Total</th>
				<th>Transaction</th>
	      <th class="fit"><br /></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr data-ng-repeat="item in items" class="resource" path="/invoices/{{item.id}}">
	      <td><input type="checkbox" /></td>
	      <td>{{item.customer_id}}</td>
			
	      <td>{{item.subtotal}}</td>
	      <td>{{item.tax_percentage}}</td>
	      <td>{{item.tax_total}}</td>
	      <td>{{item.total}}</td>
				<td><a href="#/transactions/{{item.transaction_id}}">{{item.transaction_id}}</a></td>
	      <td class="btn-toolbar">
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-small dropdown-toggle"><span class="icon-cog"></span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#/invoices/{{item.id}}"><i class="icon-pencil"></i> Edit</a></li>
						<li><a ng-click="remove()"><i class="icon-remove"></i> Delete</a></li>
					</ul>
				</div>
			</td>
	    </tr>
	  </tbody>
	</table>
	<g:render template="/templates/pagination" />
</div>