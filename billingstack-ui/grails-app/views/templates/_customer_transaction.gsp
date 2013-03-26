<div class="well">
  <form data-ng-submit="save()">
    <fieldset>
      <legend>General</legend>
      <div class="row-fluid">
        <div class="span12">
          <label for="id">Name</label>
          <input id="id" type="text" data-ng-model="item.id" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12">
          <label for="invoice">Title</label>
          <select id="invoice" data-ng-model="item.invoices" data-ng-options="i.id as i.number for pm in invoices" class="span12"></select>
          <input id="" type="text" data-ng-model="item.invoice_id" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12">
          <label for="invoice">Amount</label>
          <input id="invoice" type="text" data-ng-model="item.amount" class="span12" />
        </div>
      </div>
			<div class="row-fluid">
        <div class="span12">
          <label for="invoice">Status</label>
          <input id="invoice" type="text" data-ng-model="item.status" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="pull-left">
          <a href="#/customers/{{params.customer}}/transactions">Cancel</a>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary">Save</button>
        </div>
      </div>
    </fieldset>
  </form>
</div>