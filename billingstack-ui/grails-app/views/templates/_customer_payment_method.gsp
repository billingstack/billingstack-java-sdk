<div class="well">
  <form data-ng-submit="save()">
		<fieldset>
			<legend></legend>
				<div class="row-fluid">
					<div class="span12">
						<label>Payement Gateway</label>
						<select type="text" class="span12 collection" data-ng-model="item.provider_config_id" path="/payment-gateways" ng-options="pg.id as pg.title for pg in items"></select>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label>Card Holder Name</label>
						<input type="text" class="span12" data-ng-model="item.name" />
						<input type="text" class="span12" data-ng-model="item.identifier" />
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<label>Card Number</label>
						<input type="text" class="span12" data-ng-model="item.properties.number" />
					</div>
					<div class="span3">
						<label>Expiration (MM/YYYY)</label>
						<input type="text" class="span12" data-ng-model="item.expires" />
					</div>
					<div class="span3">
						<label>CVV</label>
						<input type="text" class="span12" data-ng-model="item.properties.cvv" />
					</div>
				</div>
		</fieldset>
		<div class="row-fluid">
      <div class="pull-left">
        <a href="#/customers/{{params.customer}}/payment-methods">Cancel</a>
      </div>
      <div class="pull-right">
        <button class="btn btn-primary">Save</button>
      </div>
    </div>
	</form>
</div>