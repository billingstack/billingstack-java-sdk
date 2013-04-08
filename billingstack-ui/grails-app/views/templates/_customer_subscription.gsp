<div class="well">
  <form data-ng-submit="save()">
    <fieldset>
      <legend>Subscription</legend>
      <div class="row-fluid">
        <div class="span6">
          <label for="payment_method">Payment Method ID</label>
          <select id="payment_method" data-ng-model="item.payment_method_id" data-ng-options="pm.id as pm.name for pm in payment_methods" class="span12">
          	<option value="">-- choose payment method --</option>
          </select>
        </div>
				<div class="span6">
          <label for="billing_day">Billing Day</label>
          <input id="billing_day" type="text" data-ng-model="item.billing_day" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="span6">
          <label for="resource">Resource Type</label>
          <input id="resource" type="text" data-ng-model="item.resource_type" class="span12" />
        </div>
				<div class="span6">
          <label for="resource">Resource ID</label>
          <input id="resource" type="text" data-ng-model="item.resource_id" class="span12" />
        </div>
      </div>
      <table class="table table-stripped collection" path="/plans">
        <thead>
          <tr>
            <th class="fit"><br /></th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          <tr data-ng-repeat="plan in items">
            <td><input type="radio" data-ng-model="item.plan_id" value="{{plan.id}}"/></td>
            <td>{{plan.title}}</td>
          </tr>
        </tbody>
      </table>
      <div class="row-fluid">
        <div class="pull-left">
          <a href="#/subscriptions?customer_id={{params.customer}}">Cancel</a>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary">Save</button>
        </div>
      </div>
    </fieldset>
  </form>
</div>