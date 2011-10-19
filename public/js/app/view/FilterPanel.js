Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '/public/js/extjs/ux');
Ext.require(['Ext.ux.form.MultiSelect']);

Ext.define('PoupaNiquel.view.FilterPanel' ,{
	extend: 'Ext.form.Panel',
	alias: 'widget.filterpanel',

    minWidth: 200,
    width: 250,
    split: true,
    border: false,
    frame: false,
    bodyPadding: 10,
    collapsible: true,
    autoScroll: true,
    layout: 'anchor',
    labelWidth: 5,
    defaultType: 'textfield',
    defaults: { anchor: '100%' },
    
    buttons: [{
		text: 'Clear',
		action: 'clear',
	}, {
		text: 'Filter',
		action: 'filter',
	}],
     
    initComponent: function() {
    	this.items = [
	        {xtype: 'datefield', format: 'Y-m-d', name: 'startDate', emptyText: 'Start date'},
	        {xtype: 'datefield', format: 'Y-m-d', name: 'endDate', emptyText: 'End Date'},
	        {xtype: 'filtercombo', name:'category', emptyText:'Category', store:'Categories'},
	        {xtype: 'filtercombo', name:'payee', emptyText:'Payee', store:'Payees'},
        ];
    	
        return this.callParent(arguments);
    }
});