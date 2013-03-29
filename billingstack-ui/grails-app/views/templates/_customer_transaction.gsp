<div class="well">
  <form data-ng-submit="save()">
    <fieldset>
      <legend>General</legend>
      <div class="row-fluid">
        <div class="span12">
          <label for="invoice">Invoices</label>
          <select id="invoice" data-ng-model="item.invoices" data-ng-options="i.id as i.id for i in invoices" class="span12" multiple="multiple"></select>
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