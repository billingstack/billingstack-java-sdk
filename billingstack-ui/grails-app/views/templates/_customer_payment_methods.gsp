<div class="collection" path="/customers/{{config.customer_id}}/payment-methods">
	<div class="well">
	  <div class="row-fluid">
	    <div class="pull-left">
	      <a href="#/payment-methods/0" class="btn btn-primary"><i class="icon-plus icon-white"></i> Create Payment Method</a>
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
	      <th>Holder</th>
			  <th>Type</th>
			  <th>Number</th>
			  <th>Expiration</th>
			  <th class="fit"><br /></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr data-ng-repeat="item in items" class="resource" path="/customers/{{config.customer_id}}/payment-methods/{{item.id}}">
	      <td><input type="checkbox" /></td>
	      <td>{{item.properties.holder}}</td>
			  <td>credit-card</td>
			  <td>{{item.properties.number}}</td>
			  <td>{{item.expires}}</td>
			  <td class="btn-toolbar">
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-small dropdown-toggle"><span class="icon-cog"></span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a ng-click="remove()"><i class="icon-remove"></i> Delete</a></li>
					</ul>
				</div>
			</td>
	   </tr>
	  </tbody>
	</table>
	<g:render template="/templates/pagination" />
</div>