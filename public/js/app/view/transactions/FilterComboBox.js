Ext.define('PoupaNiquel.view.transactions.FilterComboBox' ,{
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.filterComboBox',
	fieldLabel: '',
	valueField: 'id',
	store: [],
	displayField: 'name',
	queryMode: 'local',
	typeAhead: true,
	selectOnFocus: true,
	forceSelection: true,
	allowBlank: true,
	triggerAction: 'all',
	listClass: 'x-combo-list-small',
	minChars: 0,
	
	initComponent: function() {
		return this.callParent(arguments);
	}
});