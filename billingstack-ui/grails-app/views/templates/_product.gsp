<div class="page-header">
  <h1>Product <small></small></h1>
</div>
<div class="well">
  <form data-ng-submit="save()">
    <fieldset>
      <legend>General</legend>
      <div class="row-fluid">
        <div class="span12">
          <label for="name">Name</label>
          <input id="name" type="text" data-ng-model="item.name" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12">
          <label for="title">Title</label>
          <input id="title" type="text" data-ng-model="item.title" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12">
          <label for="description">Description</label>
          <textarea id="description" data-ng-model="item.description" class="span12"></textarea>
        </div>
      </div>
			<div class="row-fluid">
        <div class="span4">
          <label for="source">Source</label>
          <input id="source" type="text" data-ng-model="item.properties.source" class="span12" />
        </div>
				<div class="span4">
          <label for="type">Type</label>
          <input id="type" type="text" data-ng-model="item.properties.type" class="span12" />
        </div>
				<div class="span4">
          <label for="measure">Measure</label>
          <input id="measure" type="text" data-ng-model="item.properties.measure" class="span12" />
        </div>
      </div>
      <div class="row-fluid">
        <div class="pull-left">
          <a href="#/products">Cancel</a>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary">Save</button>
        </div>
      </div>
    </fieldset>
  </form>
</div>