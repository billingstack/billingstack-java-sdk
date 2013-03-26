<div class="page-header">
  <h1>Invoice <small></small></h1>
</div>
<div class="well">
  <div class="well">
    <form data-ng-submit="save()">
      <fieldset>
        <div class="row-fluid">
          <div class="span8">
            <div class="row-fluid">
              <div class="span6">
              <label for="email">Email</label>
              <input id="email" type="text" data-ng-model="invoice.email" class="span12">
              </div>
              <div class="span6"> 
              <label for="contact">Contact Name</label>
              <input id="contact" type="text" data-ng-model="invoice.contact_name" class="span12">
              </div>
            </div>
          </div>
          <div class="span4">
            <label for="number">Invoice Number</label>
            <input id="number" type="text" data-ng-model="invoice.number" class="span12">
            <label for="issued">Date of Issue</label>
            <input id="issued" type="text" data-ng-model="invoice.issued"  class="span12">
          </div>
        </div>
      </fieldset>
      <fieldset class="row-fluid">
        <table class="table">
          <thead>
              <tr>
                  <th></th>
                  <th>Description</th>
                  <th>Unit Cost ($)</th>
                  <th>Qty</th>
                  <th>Total ($)</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <td data-ng-click="addLine(newLine)"><i class="icon-plus inv_clone_btn"></i></td>
                  <td><input type="text" data-ng-model="newLine.description" class="span12"></td>
                  <td><input type="text" data-ng-model="newLine.price" class="span12"></td>
                  <td><input type="text" data-ng-model="newLine.quantity" class="span12"></td>
                  <td><input type="text" data-ng-model="newLine.subtotal" class="span12" readonly=""></td>
              </tr>
              <tr data-ng-repeat="line in invoice.lines" class="invoice">
                  <td class="inv_clone_row" data-ng-click="removeLine($index)"><i class="icon-minus inv_remove_btn"></i></td>
                  <td><input type="text" data-ng-model="line.description" class="span12"></td>
                  <td><input type="text" data-ng-model="line.price" class="span12"></td>
                  <td><input type="text" data-ng-model="line.quantity" class="span12"></td>
                  <td><input type="text" data-ng-model="line.subtotal" class="span12" readonly=""></td>
              </tr><tr class="last_row">
                  <td colspan="4">&nbsp;</td>
                  <td colspan="2">
                      <p class="clearfix">Subtotal: <span class="subtotal">$<span>{{invoice.subtotal}}</span></span></p>
                      <p>Tax: <span class="tax">$<span>{{invoice.tax}}</span></span></p>
                      <p><strong>Total: <span class="total">$<span>{{invoice.total}}</span></span></strong></p>
                  </td>
              </tr>
          </tbody>
        </table>
      </fieldset>
      <div class="row-fluid">
        <div class="pull-left">
          <a href="#/invoices">Cancel</a>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary">Save</button>
        </div>
      </div>
    </form>
  </div>
</div>