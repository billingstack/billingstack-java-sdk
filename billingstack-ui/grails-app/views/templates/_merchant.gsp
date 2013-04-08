<div class="well">
   <form data-ng-submit="save()">
      <fieldset>
        <legend>Merchant</legend>
        <div class="row-fluid">
          <div class="span12">
            <label for="name">Name</label>
            <input id="name" type="text" data-ng-model="item.name" class="span12" />
          </div>
        </div>
        <div class="row-fluid">
          <div class="span6">
            <label for="title">Language</label>
            <g:select id="language" name="language" class="span12" from="${languages}" optionKey="name" optionValue="name" />
          </div>
          <div class="span6">
            <label for="title">Currency</label>
            <g:select id="currency" name="currency" class="span12" from="${currencies}" optionKey="name" optionValue="name" />
          </div>
        </div>
      </fieldset>
      <div class="row-fluid">
        <div class="pull-left">
          <a href="#/customers">Cancel</a>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary">Save</button>
        </div>
      </div>
  </form>
 </div>